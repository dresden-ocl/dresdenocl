/*
 * Created on 26.01.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.ForeignKey;
import orgomg.cwm.resource.relational.PrimaryKey;
import orgomg.cwm.resource.relational.Schema;
import orgomg.cwm.resource.relational.Table;
import orgomg.cwm.resource.relational.View;

import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.template.ITemplate;
import tudresden.ocl20.pivot.tools.template.impl.TemplateHelper;
import tudresden.ocl20.pivot.tools.transformation.M2XTransformation;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.Pivot2SqlPlugin;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.util.CwmModelAnalyser;

/**
 * The class Cwm2DdlImpl is a realisation of the DDL Codegeneration for a
 * CWM-based model. It represents a Model-to-X Transformation.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 * 
 */
public class Cwm2DdlImpl extends M2XTransformation<Schema, String> {

	private Logger LOGGER = Pivot2SqlPlugin.getLogger(Cwm2DdlImpl.class);

	/**
	 * The unique ID of the transformation
	 */
	public static String transformationID = "CWM2DDL";
	/**
	 * The type of the transformations in model.
	 */
	public static String in_type = "CWM";
	/**
	 * The type of the transformations out model.
	 */
	public static String out_type = "A DDL";

	private IOcl2DeclSettings ocl2DeclSettings;

	private String output = "";

	private CwmModelAnalyser cwmModelAnalyser;

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
	public Cwm2DdlImpl(String modelInName, String outname) throws Exception {

		super(modelInName, outname);
	}

	public Cwm2DdlImpl() {

		super();
	}

	public void invoke() throws TransformationException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Started CWM to DDL transformation");
		}
		this.cwmModelAnalyser = new CwmModelAnalyser(model_in);

		Set<Table> tables = cwmModelAnalyser.getInstancesOfType(Table.class);
		for (Table table : tables) {
			map_table2ddlTable(table);
		}
		Set<View> views = cwmModelAnalyser.getInstancesOfType(View.class);
		for (View view : views) {
			map_view2ddlView(view);
		}

		Set<ForeignKey> fks = cwmModelAnalyser.getInstancesOfType(ForeignKey.class);
		for (ForeignKey fk : fks) {
			map_fk2ddlFkConstraint(fk);
		}

		this.out = output;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("\n" + output);
			LOGGER.debug("Finished CWM to DDL transformation");
		}
	}

	private void map_fk2ddlFkConstraint(ForeignKey fk) {

		ITemplate createFKCon =
				this.ocl2DeclSettings.getTemplateGroup().getTemplate(
						"createForeignKeyConstraint");
		PrimaryKey pk = (PrimaryKey) fk.getUniqueKey();
		Table pkt = (Table) pk.getNamespace();
		Table fkt = (Table) fk.getNamespace();

		createFKCon.setAttribute("tablename", fkt.getName());
		createFKCon.setAttribute("columnname", fk.getName());
		createFKCon.setAttribute("reference", pkt.getName() + "(" + pk.getName()
				+ ")");

		output += "\n\n" + createFKCon.toString();

	}

	private Set<ForeignKey> query_FKForTable(Table table) {

		return cwmModelAnalyser.getInstancesOfType(table.getOwnedElement(),
				ForeignKey.class);
	}

	private Set<Column> query_PKColumnsForTable(Table table) {

		Set<Column> pk_columns = new HashSet<Column>();
		Set<PrimaryKey> pks =
				cwmModelAnalyser.getInstancesOfType(table.getOwnedElement(),
						PrimaryKey.class);
		for (PrimaryKey key : pks) {
			pk_columns.addAll(cwmModelAnalyser.getInstancesOfType(key.getFeature(),
					Column.class));
		}
		return pk_columns;
	}

	private void map_view2ddlView(View view) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Create database view for CwmView " + view.getName());
		}
		ITemplate createView =
				this.ocl2DeclSettings.getTemplateGroup().getTemplate("createView");
		createView.setAttribute("viewname", view.getName());
		createView.setAttribute("body", view.getQueryExpression().getBody());
		output += "\n\n" + createView.toString();
	}

	private void map_table2ddlTable(Table table) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Create database table for CwmTabel " + table.getName());
		}

		Set<Column> columns = query_ColumnsForTable(table);
		Set<Column> pk_columns = query_PKColumnsForTable(table);
		Set<Column> fk_columns = new HashSet<Column>();
		Set<ForeignKey> fks = query_FKForTable(table);
		List<String> concreteColumns = new ArrayList<String>();

		for (Column column : pk_columns) {
			ITemplate createColumn =
					this.ocl2DeclSettings.getTemplateGroup().getTemplate("createColumn");

			createColumn.setAttribute("name", column.getName());
			createColumn.setAttribute("primaryKey", "true");
			createColumn.setAttribute("type", getTypeString("String"));
			Set<ForeignKey> fksToRemove = new HashSet<ForeignKey>();

			for (ForeignKey fk : fks) {
				if (fk.getName().equals(column.getName())) {
					fksToRemove.add(fk);

					PrimaryKey pk = (PrimaryKey) fk.getUniqueKey();
					Table t = (Table) pk.getNamespace();
					createColumn.setAttribute("foreignKey",
							t.getName() + "(" + pk.getName() + ")");
				}
			}

			fks.removeAll(fksToRemove);

			concreteColumns.add(createColumn.toString());
		}
		columns.removeAll(pk_columns);

		for (ForeignKey fk : fks) {
			ITemplate createColumn =
					this.ocl2DeclSettings.getTemplateGroup().getTemplate("createColumn");

			fk_columns.addAll(query_columnsForFK(fk));
			PrimaryKey pk = (PrimaryKey) fk.getUniqueKey();
			Table t = (Table) pk.getNamespace();
			createColumn.setAttribute("name", fk.getName());
			createColumn.setAttribute("foreignKey", t.getName() + "(" + pk.getName()
					+ ")");
			createColumn.setAttribute("type", getTypeString("String"));
			concreteColumns.add(createColumn.toString());
		}
		columns.removeAll(fk_columns);

		for (Column column : columns) {
			ITemplate createColumn =
					this.ocl2DeclSettings.getTemplateGroup().getTemplate("createColumn");
			createColumn.setAttribute("name", column.getName());
			createColumn.setAttribute("type", getTypeString(column.getType()
					.getName()));
			concreteColumns.add(createColumn.toString());
		}

		String columnsString =
				TemplateHelper.getValuesCommaSeparated(concreteColumns);

		ITemplate createTable =
				this.ocl2DeclSettings.getTemplateGroup().getTemplate("createTable");
		createTable.setAttribute("tablename", table.getName());
		createTable.setAttribute("columns", columnsString);

		output += "\n\n" + createTable.toString();
	}

	private Set<Column> query_columnsForFK(ForeignKey fk) {

		Set<Column> columns =
				cwmModelAnalyser.getInstancesOfType(fk.getFeature(), Column.class);
		return columns;
	}

	private Set<Column> query_ColumnsForTable(Table table) {

		Set<Column> columns =
				cwmModelAnalyser.getInstancesOfType(table.getOwnedElement(),
						Column.class);
		return columns;
	}

	private String getTypeString(String type) {

		ITemplate createType =
				this.ocl2DeclSettings.getTemplateGroup().getTemplate("createType");
		createType.setAttribute("type", type);
		return createType.toString();
	}

	public void setSettings(IOcl2CodeSettings ocl2CodeSettings) {

		if (ocl2CodeSettings instanceof IOcl2DeclSettings) {
			this.ocl2DeclSettings = (IOcl2DeclSettings) ocl2CodeSettings;
		}

	}

}
