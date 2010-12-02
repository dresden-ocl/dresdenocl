/*
 * ObjectViewSchema.java
 * 
 * Copyright (c) 2001 Sten Loecher
 *
 * This file is part of the Dresden OCL2.0 Toolkit
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.codegen.sql.codegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tudresden.ocl20.codegen.sql.orm.ORMapping;
import tudresden.ocl20.codegen.sql.orm.ORMappingScheme;
import tudresden.ocl20.codegen.sql.orm.Table;
import tudresden.ocl20.codegen.sql.orm.View;

/** 
 * Implements ORMappingSchema and realizes the object-view-concept. That is,
 * for each class an according view is going to be generated. The view 
 * queries all tables which belong to the class and thus enables to query 
 * all tupels respectivly objects related to a class via one virtual table.
 * 
 * @author Sten Loecher
 * @deprecated See tudresden.ocl20.codegen.decl.tools.sql
 */
public class ObjectViewSchema implements ORMappingScheme, SQLDirector {

	ORMapping theORM;
	SQLBuilder theSQLBuilder;
	Map<String,MappedClass> classifiersToMappedClasses;
	Map<String,View> classifiersToViews;
	
	/**
	 * @param orm an object relational mapping
	 * @param sqlb a SQLBuilder
	 */
	public ObjectViewSchema(ORMapping orm, SQLBuilder sqlb) {
		theORM = orm;
		theSQLBuilder = sqlb;
		classifiersToViews = new HashMap<String,View>();
		classifiersToMappedClasses = new HashMap<String,MappedClass>();
				
		createObjectViews();
		createMappedClassLinks();
		insertQueryMethods();
	}
		
	/**
	 * @param classifier a classifier
	 * @return a MappedClass object containing object views 
	 */
	public MappedClass getMappedClass(String classifier) {
		return (MappedClass)classifiersToMappedClasses.get(classifier);
	}
	
	/**
	 * @param classifier a classifier
	 * @return the definition of the view that belongs to the classifier 
	 */
	public String getViewDefinition(String classifier) {
		return ((View)classifiersToViews.get(classifier)).getStatement();
	}
        
	/**
	 * @return a String that contains the view definitions of all classifiers
	 */
	public String getViewDefinitions() {
		Set<String> classifiers = classifiersToViews.keySet();
		StringBuffer result = new StringBuffer();
		
		for (String clsName : classifiers) {
			result.append(classifiersToViews.get(clsName).getStatement());
			result.append("\n\n");
		}
		
		return result.toString();
	}
        
        /**
         *  @return a String array that contains the names of all tables the view queries
         *          or a String array that contains just the viewName itself, if the view does not exist
         *  @param viewName the name of a view
         */
        public String[] getQueriedTables(String viewName) {
            String result[] = {};
            String defaultResult[] = {viewName};
            View v;
            boolean exception = true;
            
            for (String clsName : classifiersToViews.keySet()) {
                v = classifiersToViews.get(clsName);
                if (v.getTableName().equals(viewName)) {
                    exception = false;
                    result = (String[])v.getTableNames().toArray(new String[v.getTableNames().size()]);
                    break;
                }
            }
            
            if (exception) {
                return defaultResult;
            } else {
                return result;
            }
        }
        
        /**
         * @param sqlb a builder used by the director to build database specific code
         */
        public void setBuilder(SQLBuilder sqlb) {
            theSQLBuilder = sqlb;
        }
        
        /**
         * Does the construction of the SQL code.
         * This is also done during the creation of this object.
         */
        public void construct() {
            classifiersToViews = new HashMap<String,View>();
            createObjectViews();            
        }
        
        /**
         * @return the resulting SQL code from the construction process
         */
        public String getCode() {
            return getViewDefinitions();
        }
        
	// ---------------------------------------------------------------------------------	
	/**
	 * Basic mapping of class tables to one view including attributes. 
	 */
	private void createObjectViews() {
		String currClass, viewName,  pkCols[], cols[], attribute, tableName;
		List<String> colsOrdered;
		List<String> tableNames;
		List<String> tables;
		List<Table> classTables;
		List<Table> tmpClassTables;
		Set<String> attributes;
		Set supertypes;
		Map<String, String> columns;
		View view;
		Table table;
		MappedClass mc;
						
		for (String classifier : theORM.getClassifiers()) {
			classTables = theORM.getClassTables(classifier);
			mc = new MappedClass(classifier);
			
			if (classTables.size() > 0) {
				table = classTables.get(0);
				theSQLBuilder.reset();
				
				// create table names list
				tableNames = new ArrayList<String>();
				for (Table t : classTables) {
					tableNames.add(t.getTableName());
				}
							
				// create view
				viewName = "OV_" + classifier.toUpperCase();
				view = new View(viewName, null, tableNames, classifier);
				theSQLBuilder.createView(viewName, true);
			
				// add primary keys 
				pkCols = table.getPrimaryKeyColumns();
				for (int k=0; k<pkCols.length; k++) {
					view.addColumn("", pkCols[k], true);
					theSQLBuilder.addAlias(pkCols[k]);
				}	
				
				// add attribute columns --> pay attention to inherited attributes
				currClass = classifier;
				tmpClassTables = classTables;
				attributes = new HashSet<String>();
				tables = new ArrayList<String>();
				columns = new HashMap<String,String>();
				colsOrdered = new ArrayList<String>();
				do {					
					if (tmpClassTables.size() == 1) {
						table = tmpClassTables.get(0);
						tables.add(table.getTableName());
					}
					
					// attribute columns						
					for (Iterator<String> k = theORM.getAttributes(currClass).iterator(); (k.hasNext()) && (tmpClassTables.size() > 0); ) {
						attribute = k.next();
						
						cols = table.getAttributeColumns(attribute);
						
						if (cols.length>0) {
							if (!attributes.contains(attribute)) {
								attributes.add(attribute);
							
							
								for (int l=0; l<cols.length; l++) {
									columns.put(cols[l], table.getTableName());
									view.addColumn(	table.getColumnAttribute(cols[l]),
						               	       			table.getColumnType(cols[l]),
						                       			cols[l]);
						        		theSQLBuilder.addAlias(cols[l]);
						        		colsOrdered.add(cols[l]);
								}
							}
						}
					}
					
					// foreign keys from associations
					cols = table.getForeignKeyColumns();
					for (int k=0; k<cols.length; k++) {
						if ((!columns.containsKey(cols[k])) && (!table.isPrimaryKeyColumn(cols[k]))) {
							columns.put(cols[k], table.getTableName());
							view.addColumn( "",	
					               	       		table.getColumnType(cols[k]),
					                       		cols[k]);
					                view.setForeignKey(cols[k],
					                		   "some view",
					                		   table.getForeignColumn(cols[k]));
							theSQLBuilder.addAlias(cols[k]);
							colsOrdered.add(cols[k]);
						}						
					}
													
					// prepare for next supertype		
					supertypes = theORM.getDirectSupertypeNames(currClass);
					if (supertypes.size() > 1) throw new RuntimeException("No handling of multiple inheritance !");
					if (supertypes.size() > 0) {
						currClass = (String)((Object[])supertypes.toArray())[0];
						tmpClassTables = theORM.getClassTables(currClass);
					}					
				} while (supertypes.size() > 0);
								
				// complete view definition (select statement)
				if (classTables.size() == 1) {
					// attributes in different tables --> joins
					tableName = classTables.get(0).getTableName();
					theSQLBuilder.createSelect();
					
					for (String colName : pkCols) {
						theSQLBuilder.addColumn(tableName + "." + colName);
					}
					
					for (String colName : colsOrdered) {
						theSQLBuilder.addColumn(columns.get(colName) + "." + colName);
					}
					
					theSQLBuilder.createFrom();
					for (String tn : tables) {
						theSQLBuilder.addTable(tn);
					}
					
					if (tables.size() > 1) {
						theSQLBuilder.createWhere();
						for (int l=0; l<pkCols.length; l++) {
							for (int m=0; (m+1)<tables.size(); m++) {
								if (m!=0) {
									theSQLBuilder.addAnd();
								}
								theSQLBuilder.addEquation(tables.get(m) + "." + pkCols[l],
											  tables.get(m+1) + "." + pkCols[l]);
							}
							if ((l+1)<pkCols.length) theSQLBuilder.addAnd();
						}
					}														
				} else {
					// one table per leaf class --> union
					for (Iterator<Table> k = classTables.iterator(); k.hasNext(); ) {
						table = k.next();
						theSQLBuilder.createSelect();
						
						for (String colName : pkCols) {
							theSQLBuilder.addColumn(colName);
						}
						
						for (String colName : columns.keySet()) {
							theSQLBuilder.addColumn(colName);
						}
						
						theSQLBuilder.createFrom();
						theSQLBuilder.addTable(table.getTableName());
						
						if (k.hasNext()) {
							theSQLBuilder.createUnion();
						}
					}
				}
											
				// add view to the according map
				theSQLBuilder.endView();
				view.setStatement(theSQLBuilder.getCode());
				mc.addTable(view);
				classifiersToViews.put(classifier, view);
			}
				
			classifiersToMappedClasses.put(classifier, mc);			
		}		
	}	
	
	/**
   	 * Creates all necessary links between MappedClass objects. These links
   	 * result from association ends and generalization relationships.
   	 * Futhermore, the according guides will be inserted.
   	 */
  	private void createMappedClassLinks() {
  		Guide g1;
  		Guide g2;
  		List<Guide> guides;
  		Map<String, String> assEnds;
  		MappedClass mc, assEndMc;
  		Set<String> dstn;
  		String  assEndClassifier;

 		for (String classifier : classifiersToMappedClasses.keySet()) {
  			mc = classifiersToMappedClasses.get(classifier);

  			// links resulting from generalization relationships
  			dstn = theORM.getDirectSupertypeNames(classifier);
  			for (String superclassName : dstn) {
 				mc.addSuperclass(classifier, classifiersToMappedClasses.get(superclassName));
  			}

	  		// links resulting from association ends
  			assEnds = theORM.getAssociationEnds(classifier);
	  		for (String assEndName : assEnds.keySet()) {
  				assEndClassifier = assEnds.get(assEndName);
  				assEndMc = classifiersToMappedClasses.get(assEndClassifier);
  				mc.addAssociationEnd(assEndName, assEndMc);
  			
	  			
  				// add according guides
  				guides = theORM.guidesToAssociationEnds(classifier, assEndName);                                
  				if (guides.size() > 0) {
  					g1 = guides.get(0);
  					g2 = new Guide(true);
  					
  					g1.next();
  					g2.add(g1.getSelect(),
  					       assEndMc.getTables().get(0).getTableName(),
  					       g1.getWhere());
  					       
  					if (g1.numberOfSteps() > 2) {
  						g1.next();
  						g2.add(g1.getSelect(),
  					       	       g1.getFrom(),	
  					       	       g1.getWhere());
  					}
  					
  					g1.next();
  					g2.add(g1.getSelect(),
  					       mc.getTables().get(0).getTableName(),
  					       g1.getWhere());
  					       
  					if (g1.hasMoreSteps()) {
  						throw new RuntimeException("Unexpected kind of navigation guide !");  					  					
  					}
  					mc.addJoinGuide(assEndName, g2);
  				}  				  				  				 			
  			}
  		}
   	}	
	
  	/**
   	 * Inserts information about query methods contained by the classes in
   	 * the class model.
   	 */
  	private void insertQueryMethods() {
  		MappedClass mc;
  		Set<String> operations;

  		for (String classifier : classifiersToMappedClasses.keySet()) {
  			mc = classifiersToMappedClasses.get(classifier);
  			operations = theORM.getOperations(classifier);
  			for (String op : operations) {
				mc.addQuery(op);
  			}
  		}
  	}		                
}
