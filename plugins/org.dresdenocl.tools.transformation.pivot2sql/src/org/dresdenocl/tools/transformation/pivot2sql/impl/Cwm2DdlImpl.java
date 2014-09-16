/*
 * Created on 26.01.2006
 *
 */
package org.dresdenocl.tools.transformation.pivot2sql.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.template.ITemplate;
import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.Transformation;
import org.dresdenocl.tools.transformation.exception.TransformationException;
import org.dresdenocl.tools.transformation.pivot2sql.Pivot2SqlPlugin;
import org.dresdenocl.tools.transformation.pivot2sql.util.CwmModelAnalyser;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.ColumnSet;
import orgomg.cwm.resource.relational.ForeignKey;
import orgomg.cwm.resource.relational.PrimaryKey;
import orgomg.cwm.resource.relational.Schema;
import orgomg.cwm.resource.relational.Table;
import orgomg.cwm.resource.relational.View;

/**
 * The class Cwm2DdlImpl is a realisation of the DDL Codegeneration for a
 * CWM-based model. It represents a Model-to-X Transformation.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 * 
 */
public class Cwm2DdlImpl extends
		Transformation<Catalog, IOcl2DeclSettings, SchemaStringMap> implements
		ITransformation<Catalog, IOcl2DeclSettings, SchemaStringMap> {

	private Logger LOGGER = Pivot2SqlPlugin.getLogger(Cwm2DdlImpl.class);

	private CwmModelAnalyser cwmModelAnalyser;
	
	private boolean schemaUse;

	/**
	 * The Constructor for a Cwm2Ddl Transformation
	 * 
	 * @param modelInName
	 *          The name of the in model.
	 * @param outname
	 *          The name for the out entity.
	 * @param ocl2DeclSettings
	 * @throws Exception
	 */
	public Cwm2DdlImpl(String modelInName, String outname) {

		super(modelInName, outname);
	}

	public void invoke() throws TransformationException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Started CWM to DDL transformation");
		}
		this.cwmModelAnalyser = new CwmModelAnalyser(in);

		if (out == null)
			out = new SchemaStringMap();

		Set<Schema> schemas = cwmModelAnalyser.getInstancesOfType(Schema.class);
		Set<Table> tables = cwmModelAnalyser.getInstancesOfType(Table.class);
		Set<View> views = cwmModelAnalyser.getInstancesOfType(View.class);
		Set<PrimaryKey> pks = cwmModelAnalyser.getInstancesOfType(PrimaryKey.class);
		Set<ForeignKey> fks = cwmModelAnalyser.getInstancesOfType(ForeignKey.class);
		
		if (settings.isSchemaUsing() && schemas.size() > 1) schemaUse = true;
		else schemaUse = false;
		
		for (Schema schema : schemas) {
			out.put(schema, new StringBuilder());
			if (settings.getSaveCode() == 1) {
				for (Table table : tables) {
					if (table.getNamespace().equals(schema)) map_table2ddlTable(table);
				}
				}
				for (View view : views) {
					if (view.getNamespace().equals(schema)) map_view2ddlView(view);
				}
				if (settings.getSaveCode() == 1) {
				for (PrimaryKey pk : pks) {
					if (pk.getNamespace().getNamespace().equals(schema))  map_pk2ddlPkConstraint(pk);
				}

				for (ForeignKey fk : fks) {
					if (fk.getNamespace().getNamespace().equals(schema))  map_fk2ddlFkConstraint(fk);
				}
				}
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("\n" + out);
			LOGGER.debug("Finished CWM to DDL transformation");
		}
	}
	
	private void map_pk2ddlPkConstraint(PrimaryKey pk) {

		ITemplate createPKCon =
				this.settings.getTemplateGroup().getTemplate(
						"createPrimaryKeyConstraint");
		Table pkt = (Table) pk.getNamespace();
		createPKCon.setAttribute("tablename", getName(pkt));
		createPKCon.setAttribute("pkname", pk.getName());
		List<String> columnname = new ArrayList<String>();
		for (Column column : cwmModelAnalyser.getInstancesOfType(pk.getFeature(),
					Column.class)) {
			columnname.add(column.getName());
		}
		createPKCon.setAttribute("columnname", columnname);
		out.get(pkt.getNamespace()).append("\n\n" + createPKCon.toString());

	}

	private void map_fk2ddlFkConstraint(ForeignKey fk) {

		ITemplate createFKCon =
				this.settings.getTemplateGroup().getTemplate(
						"createForeignKeyConstraint");
		PrimaryKey pk = (PrimaryKey) fk.getUniqueKey();
		Table pkt = (Table) pk.getNamespace();
		Table fkt = (Table) fk.getNamespace();

		createFKCon.setAttribute("tablename", getName(fkt));
		createFKCon.setAttribute("columnname", fk.getName());
		createFKCon.setAttribute("pktablename", pkt.getName());
		createFKCon.setAttribute("pkname", pk.getName());
		out.get(pkt.getNamespace()).append("\n\n" + createFKCon.toString());

	}
	
	private String getName(ColumnSet columnSet) {
		ITemplate template =
				this.settings.getTemplateGroup().getTemplate("createName");
		template.setAttribute("name", columnSet.getName());
		if (schemaUse) template.setAttribute("schema", columnSet.getNamespace().getName());
		return template.toString();
	}

	private void map_view2ddlView(View view) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Create database view for CwmView " + view.getName());
		}
		ITemplate createView =
				this.settings.getTemplateGroup().getTemplate("createView");
		createView.setAttribute("viewname", getName(view));
		createView.setAttribute("body", view.getQueryExpression().getBody());
		out.get(view.getNamespace()).append("\n\n" + createView.toString());
	}

	private void map_table2ddlTable(Table table) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Create database table for CwmTabel " + table.getName());
		}

		Set<Column> columns = query_ColumnsForTable(table);
		List<String> concreteColumns = new ArrayList<String>();

		for (Column column : columns) {
			ITemplate createColumn =
					this.settings.getTemplateGroup().getTemplate("createColumn");
			createColumn.setAttribute("name", column.getName());
			createColumn.setAttribute("type", getTypeString(column.getType()
					.getName()));
			concreteColumns.add(createColumn.toString());
		}

		ITemplate createTable =
				this.settings.getTemplateGroup().getTemplate("createTable");
		createTable.setAttribute("tablename", getName(table));
		createTable.setAttribute("columns", concreteColumns);

		out.get(table.getNamespace()).append("\n\n" + createTable.toString());
	}


	private Set<Column> query_ColumnsForTable(Table table) {
				
		return cwmModelAnalyser.getInstancesOfType(table.getFeature(),
						Column.class);
	}

	private String getTypeString(String type) {

		ITemplate createType =
				this.settings.getTemplateGroup().getTemplate("createType");
		if (!type.contains(" ARRAY")) {
			createType.setAttribute("type", type);
		} else if (settings.getTemplateGroup().getTemplate("check_database_array").toString().equals("true")) {
			createType.setAttribute("type", type+settings.getTemplateGroup().getTemplate("check_database_array_field").toString());
		} else {
			createType.setAttribute("type", "String");
		}
		return createType.toString();
	}

	public void setSettings(IOcl2DeclSettings ocl2CodeSettings) {

		this.settings = ocl2CodeSettings;

	}

}
