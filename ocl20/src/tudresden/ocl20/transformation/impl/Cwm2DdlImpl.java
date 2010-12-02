/*
 * Created on 26.01.2006
 *
 */
package tudresden.ocl20.transformation.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.codegen.decl.template.Template;
import tudresden.ocl20.codegen.decl.template.TemplateEngine;
import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.jmi.cwm.keysindexes.UniqueFeature;
import tudresden.ocl20.core.jmi.cwm.relational.Column;
import tudresden.ocl20.core.jmi.cwm.relational.ForeignKey;
import tudresden.ocl20.core.jmi.cwm.relational.PrimaryKey;
import tudresden.ocl20.core.jmi.cwm.relational.RelationalPackage;
import tudresden.ocl20.core.jmi.cwm.relational.Table;
import tudresden.ocl20.core.jmi.cwm.relational.View;
import tudresden.ocl20.transformation.exception.TemplateException;
import tudresden.ocl20.transformation.exception.TransformationException;
import tudresden.ocl20.transformation.interfaces.M2XTransformation;
import tudresden.ocl20.transformation.interfaces.TConfiguration;
import tudresden.ocl20.transformation.interfaces.TParameter;
import tudresden.ocl20.transformation.interfaces.TraceType;
import tudresden.ocl20.transformation.templates.StringHelper;
import tudresden.ocl20.transformation.templates.StringTemplateEngineAdapter;
import tudresden.ocl20.transformation.util.ModelAnalyser;

/**
 * The class Cwm2DdlImpl is a realisation of the DDL Codegeneration for a CWM-based model.
 * It represents a Model-to-X Transformation.
 * @author Christian Wende
 *
 */
public class Cwm2DdlImpl extends M2XTransformation<RelationalPackage, String>{

	private static final String ERROR_TEMPLATE_FACTORY = "An error occured while loading the SQL Templates";

	/**
	 * The configuration parameter for the DDL language, the code should be generated for.
	 */
	public static TParameter DDL_LANGUAGE;
	/**
	 * The unique ID of the transformation
	 */
	public static String transformationID = "CWM2DDL";
	/**
	 * The type of the transformations in model.
	 */
	public static String in_type = MetaModelConst.CWM;
	/**
	 * The type of the transformations out model.
	 */
	public static String out_type = "A DDL";
	
	private TemplateEngine templateFactory;
	private String output = "";


	/**
	 * The Constructor for a Cwm2Ddl Transformation 
	 * @param modelInName The name of the in model.
	 * @param outname The name for the out entity.
	 * @throws Exception
	 */
	public Cwm2DdlImpl(String modelInName, String outname) throws Exception {
		super(modelInName, outname);
	}


	@Override
	protected void initRequiredParameters() {
		Set<TParameter> paras = new HashSet<TParameter>();

		DDL_LANGUAGE = new TParameter("Destination Language", new String[]{StringTemplateEngineAdapter.POSTGRES, StringTemplateEngineAdapter.ORACLE, StringTemplateEngineAdapter.STANDARD, StringTemplateEngineAdapter.MY_SQL}, TParameter.SELECT);
		
		paras.add(DDL_LANGUAGE);
		
		conf = new TConfiguration(paras);
	}

	@Override
	public void invoke() throws TransformationException {
		trace(TraceType.TRANSFORMATION ,"Started CWM to DDL transformation");
		
		try {
			templateFactory = new StringTemplateEngineAdapter(conf.get(DDL_LANGUAGE.getKey()));
		} catch (TemplateException e1) {
			throw new TransformationException(ERROR_TEMPLATE_FACTORY, this);
		}
		
		Set<Table> tables = ModelAnalyser.getInstancesOfType(model_in, Table.class);
		for(Table table : tables) {
			map_table2ddlTable(table);
		}
		Set<View> views = ModelAnalyser.getInstancesOfType(model_in, View.class);
		for(View view : views) {
			map_view2ddlView(view);
		}
		
		Set<ForeignKey> fks = ModelAnalyser.getInstancesOfType(model_in, ForeignKey.class);
		for(ForeignKey fk : fks) {
			map_fk2ddlFkConstraint(fk);
		}
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(out_name);
			fos.write( output.getBytes() );
			fos.close();
		} catch (FileNotFoundException e) {
			throw new TransformationException("Error while generating an output file for the ddl.", this);
		} catch (IOException e) {
			throw new TransformationException("Error while generating an output file for the ddl.", this);
		}
		
		this.out = output;
		
		trace(TraceType.TRANSFORMATION , "\n" + output);
		trace(TraceType.TRANSFORMATION ,"Wrote generated DDL in file: " + out_name );
		trace(TraceType.TRANSFORMATION ,"Finished CWM to DDL transformation");
	}


	private void map_fk2ddlFkConstraint(ForeignKey fk) {
		Template createFKCon = templateFactory.getTemplate("createForeignKeyConstraint");
		PrimaryKey pk = (PrimaryKey) fk.getUniqueKey();
		Table pkt = (Table) pk.getNamespace();
		Table fkt = (Table) fk.getNamespace();
		
		createFKCon.setAttribute("tablename", fkt.getName());
		createFKCon.setAttribute("columnname", fk.getName());
		createFKCon.setAttribute("reference", pkt.getName()+"("+pk.getName()+")");
		
		output += "\n\n" + createFKCon.toString();

	}


	private Set<ForeignKey> query_FKForTable(Table table) {
		Set<ForeignKey> fks = ModelAnalyser.getInstancesOfType(table.getOwnedElement(), ForeignKey.class);
		return fks;
	}

	private Set<Column> query_PKColumnsForTable(Table table) {
		Set<Column> pk_columns = new HashSet();
		Set<PrimaryKey> pks = ModelAnalyser.getInstancesOfType(table.getOwnedElement(), PrimaryKey.class);
		UniqueFeature uf = model_in.getKeysIndexes().getUniqueFeature();
		for (PrimaryKey key : pks) {
			pk_columns.addAll(ModelAnalyser.getInstancesOfType(uf.getFeature(key), Column.class));
		}
		return pk_columns;
	}
	
	private void map_view2ddlView(View view) {
		trace(TraceType.CREATION, "Create database view for CwmView " + view.getName());
		Template createView = templateFactory.getTemplate("createView");
		createView.setAttribute("viewname", view.getName());
		createView.setAttribute("body", view.getQueryExpression().getBody());
		//System.out.println(createView);
		output += "\n\n" + createView.toString();
	}

	private void map_table2ddlTable(Table table) {
		trace(TraceType.CREATION, "Create database table for CwmTabel " + table.getName());
		
		Set<Column> columns = query_ColumnsForTable(table);
		Set<Column> pk_columns = query_PKColumnsForTable(table);
		Set<Column> fk_columns = new HashSet<Column>();
		Set<ForeignKey> fks = query_FKForTable(table);
		List<String> concreteColumns = new ArrayList<String>();
		
		
		for (Column column : pk_columns) {
			Template createColumn = templateFactory.getTemplate("createColumn");
			
			createColumn.setAttribute("name", column.getName());
			createColumn.setAttribute("primaryKey", "true");
			createColumn.setAttribute("type", "String");
			Set<ForeignKey> fksToRemove = new HashSet<ForeignKey>();
			
			for (ForeignKey fk : fks) {
				if (fk.getName().equals(column.getName())) {
//					System.out.println("Duplicate column found.");
					fksToRemove.add(fk);
					
					PrimaryKey pk = (PrimaryKey) fk.getUniqueKey();
					Table t = (Table) pk.getNamespace();
					createColumn.setAttribute("foreignKey", t.getName()+"("+pk.getName()+")");
				}	
			}
			
			fks.removeAll(fksToRemove);
			
			concreteColumns.add(createColumn.toString());
		}
		columns.removeAll(pk_columns);
		
		for (ForeignKey fk : fks) {
			Template createColumn = templateFactory.getTemplate("createColumn");
			
			fk_columns.addAll(query_columnsForFK(fk));
			PrimaryKey pk = (PrimaryKey)fk.getUniqueKey();
			Table t = (Table) pk.getNamespace();
			createColumn.setAttribute("name", fk.getName());
			createColumn.setAttribute("foreignKey", t.getName()+"("+pk.getName()+")");
			createColumn.setAttribute("type", "String");
			concreteColumns.add(createColumn.toString());
		}
		columns.removeAll(fk_columns);
	
		for (Column column : columns) {
			Template createColumn = templateFactory.getTemplate("createColumn");
				
			createColumn.setAttribute("name", column.getName());
			createColumn.setAttribute("type", column.getType().getName());
			concreteColumns.add(createColumn.toString());
		}
	
		String columnsString =StringHelper.getValuesCommaSeperated(concreteColumns);

		
		Template createTable = templateFactory.getTemplate("createTable");
		createTable.setAttribute("tablename",table.getName());
		createTable.setAttribute("columns", columnsString);

		output += "\n\n" + createTable.toString();
	}


	private Set<Column> query_columnsForFK(ForeignKey fk) {
		Set<Column> columns = ModelAnalyser.getInstancesOfType(model_in.getKeysIndexes().getKeyRelationshipFeatures().getFeature(fk), Column.class);
		return columns;
	}

	private Set<Column> query_ColumnsForTable(Table table) {
		Set<Column> columns = ModelAnalyser.getInstancesOfType(table.getFeature(), Column.class);
		return columns;
	}

				
}
