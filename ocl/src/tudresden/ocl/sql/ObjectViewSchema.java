/*
Copyright (C) 2001  Sten Loecher

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package tudresden.ocl.sql;

import java.util.*;
import tudresden.ocl.codegen.decl.*;

/** 
 * Implements ORMappingSchema and realizes the object-view-concept. That is,
 * for each class an according view is going to be generated. The view 
 * queries all tables which belong to the class and thus enables to query 
 * all tupels respectivly objects related to a class via one virtual table. 
 */
public class ObjectViewSchema implements ORMappingScheme {

	ORMapping theORM;
	SQLBuilder theSQLBuilder;
	Map classifiersToMappedClasses;
	Map classifiersToViews;
	
	/**
	 * @param orm an object relational mapping
	 * @param sqlb a SQLBuilder
	 */
	public ObjectViewSchema(ORMapping orm, SQLBuilder sqlb) {
		theORM = orm;
		theSQLBuilder = sqlb;
		classifiersToViews = new HashMap();
		classifiersToMappedClasses = new HashMap();
				
		createObjectViews();
		createMappedClassLinks();
		insertQueryMethodes();
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
         *  @return a String that contains the view definitions of all classifiers
         */
        public String getViewDefinitions() {
            Set classifiers = classifiersToViews.keySet();
            StringBuffer result = new StringBuffer();
            
            for (Iterator i=classifiers.iterator(); i.hasNext(); ) {
                result.append(((View)classifiersToViews.get(i.next())).getStatement());
                result.append("\n\n");
            }
            
            return result.toString();
        }
        
        /**
         *  @return a String array that contains the names of all tables the view queries
         *  @param viewName the name of a view
         *  @exception IllegalArgumentException if no view with the specified named exists
         */
        public String[] getQueriedTables(String viewName) 
        throws IllegalArgumentException {
            String result[] = {};
            String cn;
            View v;
            boolean exception = true;
            
            for (Iterator i=classifiersToViews.keySet().iterator(); i.hasNext(); ) {
                cn = (String)i.next();
                v = (View)classifiersToViews.get(cn);
                if (v.getTableName().equals(viewName)) {
                    exception = false;
                    result = (String[])v.getTableNames().toArray(new String[v.getTableNames().size()]);
                    break;
                }
            }
            
            if (exception) {
                throw new IllegalArgumentException("View " + viewName + " does not exist !");
            } else {
                return result;
            }
        }
        
	// ---------------------------------------------------------------------------------	
	/**
	 * Basic mapping of class tables to one view including attributes. 
	 */
	private void createObjectViews() {
		String classifier, currClass, statement, viewName,  pkCols[], cols[], attribute, tableName, colName;
		List tableNames, classTables, tmpClassTables, colsOrdered, tables;
		Set attributes, supertypes;
		Map tableToPks, columns;
		View view;
		Table table;
		MappedClass mc;
						
		for (Iterator i=theORM.classifiers().iterator(); i.hasNext(); ) {
			classifier = (String)i.next();
			classTables = theORM.getClassTables(classifier);
			mc = new MappedClass(classifier);
			
			if (classTables.size() > 0) {
				table = (Table)classTables.get(0);
				theSQLBuilder.reset();
				
				// create table names list
				tableNames = new ArrayList();
				for (Iterator k=classTables.iterator(); k.hasNext(); ) {
					tableNames.add(((Table)k.next()).getTableName());
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
				attributes = new HashSet();
				tables = new ArrayList();
				columns = new HashMap();
				colsOrdered = new ArrayList();
				do {					
					if (tmpClassTables.size() == 1) {
						table = (Table)tmpClassTables.get(0);
						tables.add(table.getTableName());
					}
					
					// attribute columns						
					for (Iterator k=theORM.attributes(currClass).iterator(); (k.hasNext()) && (tmpClassTables.size() > 0); ) {
						attribute = (String)k.next();
						if (!attributes.contains(attribute)) {
							attributes.add(attribute);
							cols = table.getAttributeColumns(attribute);
							
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
					supertypes = theORM.directSupertypeNames(currClass);
					if (supertypes.size() > 1) throw new RuntimeException("No handling of multiple inheritance !");
					if (supertypes.size() > 0) {
						currClass = (String)((Object[])supertypes.toArray())[0];
						tmpClassTables = theORM.getClassTables(currClass);
					}					
				} while (supertypes.size() > 0);
								
				// complete view definition (select statement)
				if (classTables.size() == 1) {
					// attributes in different tables --> joins
					tableName = ((Table)classTables.get(0)).getTableName();
					theSQLBuilder.createSelect();
					
					for (int l=0; l<pkCols.length; l++) {
						theSQLBuilder.addColumn(tableName + "." + pkCols[l]);
					}
					
					for (Iterator l=colsOrdered.iterator(); l.hasNext(); ) {
						colName = (String)l.next();
						theSQLBuilder.addColumn(columns.get(colName) + "." + colName);
					}
					
					theSQLBuilder.createFrom();
					for (Iterator l=tables.iterator(); l.hasNext(); ) {
						theSQLBuilder.addTable((String)l.next());
					}
					
					if (tables.size() > 1) {
						theSQLBuilder.createWhere();
						for (int l=0; l<pkCols.length; l++) {
							for (int m=0; (m+1)<tables.size(); m++) {
								theSQLBuilder.addEquation(tables.get(m) + "." + pkCols[l],
											  tables.get(m+1) + "." + pkCols[l]);
							}
							if ((l+1)<pkCols.length) theSQLBuilder.addAnd();
						}
					}														
				} else {
					// one table per leaf class --> union
					for (Iterator k=classTables.iterator(); k.hasNext(); ) {
						table = (Table)k.next();
						theSQLBuilder.createSelect();
						
						for (int l=0; l<pkCols.length; l++) {
							theSQLBuilder.addColumn(pkCols[l]);
						}
						
						for (Iterator l=columns.keySet().iterator(); l.hasNext(); ) {
							theSQLBuilder.addColumn((String)l.next());
						}
						
						theSQLBuilder.createFrom();
						theSQLBuilder.addTable(table.getTableName());
						if (k.hasNext()) theSQLBuilder.createUnion();
					}
				}
											
				// add view the according map
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
  		Set dstn;
  		Map gtae, assEnds;
  		String classifier, temp, assEndName, assEndClassifier;
  		MappedClass mc, assEndMc;
  		List guides;
  		Guide g1, g2;

 		for (Iterator i=classifiersToMappedClasses.keySet().iterator(); i.hasNext(); ) {
  			classifier = (String)i.next();
  			mc = (MappedClass)classifiersToMappedClasses.get(classifier);

  			// links resulting from generalization relationships
  			dstn = theORM.directSupertypeNames(classifier);
  			for (Iterator k=dstn.iterator(); k.hasNext(); ) {
 				mc.addSuperclass(classifier, (MappedClass)classifiersToMappedClasses.get((String)k.next()));
  			}

	  		// links resulting from association ends
  			assEnds = theORM.associationEnds(classifier);
	  		for (Iterator k=assEnds.keySet().iterator(); k.hasNext(); ) {
  				assEndName = (String)k.next();
  				assEndClassifier = (String)assEnds.get(assEndName);
  				assEndMc = (MappedClass)classifiersToMappedClasses.get(assEndClassifier);
  				mc.addAssociationEnd(assEndName, assEndMc);
  			
	  			
  				// add according guides
  				guides = theORM.guidesToAssociationEnds(classifier, assEndName);
  				if (guides.size() > 0) {
  					g1 = (Guide)guides.get(0);
  					g2 = new Guide(true);
  					
  					g1.next();
  					g2.add(g1.getSelect(),
  					       ((Table)assEndMc.getTables().get(0)).getTableName(),
  					       g1.getWhere());
  					       
  					if (g1.numberOfSteps() > 2) {
  						g1.next();
  						g2.add(g1.getSelect(),
  					       	       g1.getFrom(),	
  					       	       g1.getWhere());
  					}
  					
  					g1.next();
  					g2.add(g1.getSelect(),
  					       ((Table)mc.getTables().get(0)).getTableName(),
  					       g1.getWhere());
  					       
  					if (g1.hasMoreSteps()) throw new RuntimeException("Unexpected kind of navigation guide !");  					  					
  					mc.addJoinGuide(assEndName, g2);
  				}  				  				  				 			
  			}
  		}
   	}	
	
  	/**
   	 * Inserts information about query methodes contained by the classes in
   	 * the class model.
   	 */
  	private void insertQueryMethodes() {
  		String classifier;
  		MappedClass mc;
  		Set ops;

  		for (Iterator i=classifiersToMappedClasses.keySet().iterator(); i.hasNext(); ) {
	  		classifier = (String)i.next();
  			mc = (MappedClass)classifiersToMappedClasses.get(classifier);

  			ops = theORM.operations(classifier);
  			for (Iterator k=ops.iterator(); k.hasNext(); ) {
				mc.addQuery((String)k.next());
  			}
  		}
  	}		
}
