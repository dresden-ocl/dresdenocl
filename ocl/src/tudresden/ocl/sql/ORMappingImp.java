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

import tudresden.ocl.check.types.xmifacade.*;
import tudresden.ocl.codegen.decl.*;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.security.*;

/**
 * An implementation of the ORMapping interface. It generates a number of tables
 * for classes and associations according some rules that are parameterized. 
 * The source of the object oriented information is a Model object that must be 
 * of rough quality.
 * The rules are as follows:
 * <ol>
 * <li>Classes map to one ore more tables according to the parameter <i>classToTableMode</i>.</li>
 * <li>
 * The class tables are going to get a primary key consisting of a certain number of columns
 * (<i>numOfPKCols</i>) of a specified datatype (<i>pKColTypeName</i>). All primary key columns
 * have the same datatype. The keys follow the existence based approach and are going to
 * be inherited throughout an inheritance hierarchy. 
 * </li>
 * <li>
 * Associations map to tables and foreign key references as follows:
 * <ul>
 * <li>many-to-many: one association table</li>
 * <li>one-to-many: the primary key of the one-side is going to be inserted to the many-side's table</li>
 * <li>one-to-one: then primary keys of both sides are going to be inserted at the opposite sides's tables</li>
 * </ul>
 * </li>
 * <li>
 * Attributes map to columns according to the class to table strategy. 
 * Complex attributes are going to be mapped to a table. The table's primary
 * key is going to be referenced if the attribute is member of a class. That is,
 * complex attributes are treated as if they were objects with an directed one-to-one 
 * association to them. 
 * </li>
 * </ol>
 * @author Sten Loecher
 * @see tudresden.ocl.check.types.xmifacade.Model
 * @invariant theModel.isRough() = true
 */
public class ORMappingImp implements ORMapping {
  /**
   * The model containing the object oriented information. 
   */
  private Model theModel;
  
  /**
   * An array that contains all the tables from the object relational mapping.
   */
  private ArrayList tables;
  
  /**
   * Maps class names to a list of table names. 
   */
  private HashMap classesToTables;
  
  /**
   * Maps class names to a map of navigation guides. The map of
   * navigation guides maps role names to the actual guides.
   */
  private HashMap navGuides;
  
  /**
   * Mapping mode for classes to tables. Possible values are:<br>
   * 0 - use one table for an entire class hierarchy<br>
   * 1 - use one table per leaf class<br>
   * 2 - use one table per class (which is the default)
   */
  private int classToTableMode;
  
  /**
   * The number of desired primary key columns (default is 1).
   */
  private int numOfPKCols; 
  
  /**
   * The type name of the primary key columns (default is int).
   */
  private String pKColTypeName;
   
  private static String JAVATYPES = "Vector void?? Rectangle Byte Character Float Integer Double Stack Hashtable char Point Long Color Boolean Date";
  private static String PRIMKEYNAME = "PK_";
  private static String ASSTABNAME = "ASSTAB_";
  private static String PACKID = "::";
  
  /**
   * A constructor to create the object relational mapping informations. This constructor
   * uses default parameters for the mapping.
   * @param theModel a model for which isRough() must hold
   * @exception IllegalArgumentException if the theModel.isRough() evaluates to false
   */
  public ORMappingImp(Model theModel) 
  throws IllegalArgumentException {
  	this(theModel, 2, 1, "int");
  }
  
  /**
   * A constructor to create the object relational mapping informations. This constructor
   * uses the given parameters for the mapping.
   * @param theModel a model for which isRough() must hold
   * @param classToTableMode see field description of classToTableMode
   * @param numOfPKCols see field description of numOfPKCols
   * @param pKColTypeName see field description of pKColTypeName
   * @exception IllegalArgumentException if the theModel.isRough() evaluates to false or the parameters are not valid
   */
  public ORMappingImp(Model theModel, int classToTableMode, int numOfPKCols, String pKColTypeName) 
  throws IllegalArgumentException {
  	if ((classToTableMode < 0) || (classToTableMode > 2)) {
  		throw new IllegalArgumentException("Illegal classToTableMode !");
  	} else {
  		this.classToTableMode = classToTableMode;
  	}
  	
  	if (numOfPKCols < 1) {
  		throw new IllegalArgumentException("Number of primary key columns must not be less than 1 !");
  	} else {
  		this.numOfPKCols = numOfPKCols;
  	}
  	
  	if (pKColTypeName == null) {
  		throw new IllegalArgumentException("Primary key type name must not be null !");
  	} else {
  		this.pKColTypeName = pKColTypeName;
  	}
  	
  	if ((theModel != null) && (theModel.isRough())) {
  		this.theModel = theModel;
  	} else {
  		throw new IllegalArgumentException("Model must be in rough mode !");
  	}
    	
    	// initialize class variables
	tables = new ArrayList();
	classesToTables = new HashMap();
	navGuides = new HashMap();
    	
	// run through the mapping
	createClassTables();
    	generatePrimaryKeys();
    	mapAttributes();
    	mapAssociations();   
  }
  
  //------------------------- implementation of ORMapping interface -------------------------
  public List tables() {
  	return Collections.unmodifiableList(tables);
  }
  
  public List getClassTables(String classifier) 
  throws IllegalArgumentException {
  	List tabNameList, tabList;
  	
  	if (classifier == null) throw new IllegalArgumentException("Classifier must not be null !");
  	tabNameList = (List)classesToTables.get(classifier);
  	if (tabNameList == null) throw new IllegalArgumentException("Classifier does not exist: " + classifier);
  	tabList = new ArrayList();
  	for (Iterator i=tabNameList.iterator(); i.hasNext(); ) {
  		tabList.add(getTable((String)i.next()));
  	}
  	  	
  	return Collections.unmodifiableList(tabList);
  }
  
  public Set classifiers() {
  	Set retSet = new HashSet();
  	
  	for (Iterator i=classesToTables.keySet().iterator(); i.hasNext(); ) {
  		retSet.add(new String((String)i.next()));
  	}
  	
  	return retSet;
  }
  
  public Set directSupertypeNames(String classifier) {
  	Set retSet = new HashSet();
  	ModelClass mc = (ModelClass)theModel.getClassifier(classifier), temp;
  	
  	for (Iterator i=theModel.classifiers().values().iterator(); i.hasNext(); ) {
  		temp = (ModelClass)i.next();
  		if (mc.isDirectSupertype(temp)) {
  			retSet.add(temp.getShortName());
  		}
  	}
  	
  	return Collections.unmodifiableSet(retSet);
  }
  
  public Map guidesToAssociationEnds(String classifier) {
  	Object result = navGuides.get(classifier);
  	
  	if (result == null) {
  		return new HashMap();
	} else {
		return Collections.unmodifiableMap((Map)result);
	}  	
  }
  
  public Set operations(String classifier) {
  	Set retSet = new HashSet();
  	Map ops = ((ModelClass)theModel.getClassifier(classifier)).operations();
  	String mo;
  	Collection co;
  	
  	for (Iterator i=ops.keySet().iterator(); i.hasNext(); ) {
  		mo = (String)i.next();
  		co = (Collection)ops.get(mo);
  		
  		for (Iterator k=co.iterator(); k.hasNext(); ) {
  			if (((ModelOperation)k.next()).isQuery()) {
  				retSet.add(mo);
  			}
  		}
  	}
  	  	
  	
  	return Collections.unmodifiableSet(retSet);
  }
    
  //----------------------------------- mapping methodes ------------------------------------
    				
  /**
   * Creates all the class tables with respect to the mapping mode.
   */
  private void createClassTables() {
  	Iterator j, i = theModel.classifiers().values().iterator();
  	ModelClass c, c1;
  	Table table;
  	String tableName, fullName, className;
  	ArrayList allTableNames;
  	HashSet tempSet = new HashSet(), dupl = new HashSet();
  	HashMap straggler;	
  	
  	while (i.hasNext()) {
  	  c = (ModelClass)i.next();
  	  fullName = c.getFullName();
  	  if (isJavaType(fullName)) continue;
  	  if (!dupl.add(fullName)) continue;
  	  switch(classToTableMode) {
  		case 0:
  			// one table for for an entire class hierarchy
  			if (!c.hasSupertypes()) {
  				// create a table for root class
  				mapClassToTable(c);
  			} else {
  				// keep all non root classes in mind
  				tempSet.add(c);
  			}
  			break;
  		case 1:
  			// one table per leaf class
  			if (theModel.getDirectClassSubtypes(c).isEmpty()) {
  				// create a table for each leaf class
  				mapClassToTable(c);
  			} else {
  				// keep all non leaf classes in mind
  				tempSet.add(c);
  			}
  			break;
  		case 2:
  			// one table per class
  			mapClassToTable(c);
  			break;
	  	default: throw new IllegalStateException("Illegal mode for class to table mapping.");
  	  }
  	}
  	
  	if (classToTableMode == 0) {
 		// associate all child classes to the table of the their hierarchy root class
 		j = tempSet.iterator();
 		
 		while (j.hasNext()) {
 			c = (ModelClass)j.next();
 			className = c.generalizationRoot();
 			allTableNames = (ArrayList)classesToTables.get(className);
  			classesToTables.put(getClassName(c), allTableNames);
  			System.err.println("straggler is:" + c.getShortName());
 		}
 	}
  	
  	if (classToTableMode == 1) {
  		// associate all ancestor classes to the tables of their childs (leaf classes)
  		straggler = new HashMap(); 		
  		j = tempSet.iterator();
  		
  		while (j.hasNext()) {
  			c = (ModelClass)j.next();
  			
  			i = classesToTables.keySet().iterator();
  		  	while (i.hasNext()) {
  				className = (String)i.next();
  				c1 = (ModelClass)theModel.getClassifier(className);
  				
  				if (c1.isSupertype(c)) {
  					allTableNames = (ArrayList)straggler.get(getClassName(c));
  					if (allTableNames == null) {
  						allTableNames = (ArrayList)classesToTables.get(className);
  					} else {
  						allTableNames.addAll((List)classesToTables.get(className));
  					}
  					straggler.put(getClassName(c), allTableNames);
  				}
  			}
		}
	
		System.err.println("stragglers are:" + straggler.toString());	
		classesToTables.putAll(straggler);
 	}
  }
  
  /**
   * Generates the primary keys for all classes.
   */
  private void generatePrimaryKeys() {
  	Iterator i = classesToTables.keySet().iterator();
  	String className, keyName, childName, rootName, pkCols[], rootTable;
  	ModelClass mc;
  	HashSet tempSet = new HashSet();
  	int keyCount = 0;
  	ArrayList tableNames;
  	Table t;
  	
  	// iterate all classes and generate a key for all hierarchy roots
  	while (i.hasNext()) {
  		className = (String)i.next();
  		mc = (ModelClass)theModel.getClassifier(className);
  		
  		if (!mc.hasSupertypes()) {
  			// get all table names of the class
  			tableNames = (ArrayList)classesToTables.get(className);
  			  			
  			// generate key
  			keyCount++;
  			keyName = PRIMKEYNAME + keyCount;
  			
  			// add primary key columns to all class tables
  			for (int k=0; k<tableNames.size(); k++) {
  				 t = getTable((String)tableNames.get(k));
  				 if (numOfPKCols == 1) {
  				 	try {
  				 		t.addColumn("", pKColTypeName, keyName, true);
  				 	} catch(IllegalArgumentException e) {
  				 		// key still exists
  				 	}
  				 } else {
  				 	for (int l=1; l<=numOfPKCols; l++) {
  				 		try {
  				 			t.addColumn("", pKColTypeName, keyName + "_" + l, true);
  				 		} catch(IllegalArgumentException e) {
  				 			// key still exists
  				 		}
  				 	}
  				 }
  			}  			
  		} else {
  			// keep childs in mind
  			tempSet.add(className);
  		}  		
  	}
  	  	
  	// inherit the primary keys of the root classes to all child classes
  	if (classToTableMode == 2) {
  		i = tempSet.iterator();
  		
  		while (i.hasNext()) {
  			childName = (String)i.next();
  			rootName =  ((ModelClass)(theModel.getClassifier(childName))).generalizationRoot();
  			rootTable = (String)((ArrayList)classesToTables.get(rootName)).get(0);
  			pkCols = (getTable(rootTable)).getPrimaryKeyColumns();
  			
  			tableNames = (ArrayList)classesToTables.get(childName);
  			for (int k=0; k<tableNames.size(); k++) {
  				t = getTable((String)tableNames.get(k));
  				
  				for (int l=0; l<pkCols.length; l++) {
  					try {
  						t.addColumn("", pKColTypeName, pkCols[l], true);
  						t.setForeignKey(pkCols[l], rootTable, pkCols[l]);
  					} catch(IllegalArgumentException e) {
  				 		// key still exists
  				 	}
  				}
  			}
  		}
  	}  	
  }
  
  /**
   * Mapping of all attributes.
   */
  private void mapAttributes() {
  	Iterator j, i = classesToTables.keySet().iterator();
  	ModelClass mc;
  	ModelAttribute ma;
  	String className, attName, pkCols[];
  	Map attributes;
  	ArrayList tableNames;
  	Table t,t1;
  	
  	while (i.hasNext()) {
  		className = (String)i.next();
  		mc = (ModelClass)theModel.getClassifier(className);
  		tableNames = (ArrayList)classesToTables.get(className);
  		
  		attributes = mc.attributes();
  		j = attributes.keySet().iterator();  		  		
  		while (j.hasNext()) {
  			attName = (String)j.next();
  			ma = (ModelAttribute)attributes.get(attName);
  			  			
  			for (int k=0; k<tableNames.size(); k++) {
  				t = getTable((String)tableNames.get(k));
  				
  				if (!classesToTables.containsKey(ma.getType().toString())) {
  					// basic types
  					t.addColumn(attName, ma.getType().toString(), getColumnName(attName), false);   							
  				} else {
  					// complex types --> use primary key in attribute owner class
  					t1 = getTable((String)((ArrayList)classesToTables.get(ma.getType().toString())).get(0));
  					pkCols = t1.getPrimaryKeyColumns();
  					
  					for (int l=0; l<pkCols.length; l++) {
  						try {
  							t.addColumn(attName, pKColTypeName, getColumnName(attName), false);
  							t.setForeignKey(getColumnName(attName), t1.getTableName(), pkCols[l]);
  						} catch(IllegalArgumentException e) {
  				 		// key still exists
  				 		}
 					}
 				}
 			}
  		}
  	}  	
  }

  /**
   * Mapping of Associations to foreign key relations and association tables.
   */
  private void mapAssociations() { 
  	Iterator i = theModel.associations().iterator();
  	ModelAssociationEnd mae1, mae2, mae3;
  	ModelAssociation ma;
  	ModelClass ac, aec;
  	Table assTab, tempTab;
  	int assTabCount = 0;
  	List assEnds, tableNames;
  	String className, pkc[];
  	Guide guide;
  	Map ng;
  	
  	while (i.hasNext()) {
  		ma = (ModelAssociation)i.next();
  		
  		if (ma.allEndsAreMultiple()) {
  			// tables for all associations with multiplicity * on all ends
  			ac = ma.getAttribute();
  			
  			if (ac == null) {
  				// there is no association table
  				assTabCount++;
  				assTab = new Table(ASSTABNAME + assTabCount);
  				tables.add(assTab);
  				assEnds = ma.getEnds();
  				
  				for (int k=0; k<assEnds.size(); k++) {
  					className = getClassName(((ModelAssociationEnd)assEnds.get(k)).getModelClass());
  					tempTab = getTable((String)((List)classesToTables.get(className)).get(0));
  					pkc = tempTab.getPrimaryKeyColumns();
  					
  					for (int l=0; l<pkc.length; l++) {
  						try {
  							assTab.addColumn("", pKColTypeName, pkc[l], false);
  							assTab.setForeignKey(pkc[l], tempTab.getTableName(), pkc[l]);
  						} catch(IllegalArgumentException e) {
  				 			// key still exists
  				 		}
  					}
  				}
  				
  				// create guides to association ends
  				for (int k=0; k<assEnds.size(); k++) {
  					ng = new HashMap();
  					for (int l=0; l<assEnds.size(); l++) {
  						if (l != k) {
  							guide = new Guide(true);
  							guide.add("","","");
  							guide.add("","","");
  							guide.add("","","");
  							ng.put(((ModelAssociationEnd)assEnds.get(l)).getName(), guide);
  						}  						
  					}
  					navGuides.put(((ModelAssociationEnd)assEnds.get(k)).getModelClass().getShortName(), ng);
  				}
  			} else {
  				// create table from association class
  				System.err.println("Association classes not yet supported !");
  			}
  			
  		} else {
  			// only foreign key references for the other associations
  			if (ma.getEnds().size() == 2) {
  				mae1 = (ModelAssociationEnd)ma.getEnds().get(0);
  				mae2 = (ModelAssociationEnd)ma.getEnds().get(1);
  				
  				if (mae1.isMultiple() || mae2.isMultiple()) {
  					// 1:* association
  					if (mae1.isMultiple()) {
  						mae3 = mae1;
  						mae1 = mae2;
  						mae2 = mae3;
  					}
  					
  					// get primary keys from mae1 class
  					tempTab = getTable((String)((List)classesToTables.get(getClassName(mae1.getModelClass()))).get(0));
  					  					
  					// insert the primary keys into mae2 class
  					insertKeysIntoAllClassTables(tempTab, getClassName(mae2.getModelClass()));
  				} else {
  					// 1:1 association
  					// primary keys from mae1 into mae2 class
  					tempTab = getTable((String)((List)classesToTables.get(getClassName(mae1.getModelClass()))).get(0));
  					insertKeysIntoAllClassTables(tempTab, getClassName(mae2.getModelClass()));
  					// primary keys from mae2 into mae1 class
  					tempTab = getTable((String)((List)classesToTables.get(getClassName(mae2.getModelClass()))).get(0));
  					insertKeysIntoAllClassTables(tempTab, getClassName(mae1.getModelClass()));
  				}
  			} else {
				System.err.println("Only binary associations are supported !");			
  			}
  		}
  	}
  }
  	
  /**
   * @param typeName a type name
   * @return true if typeName is a Java predefined type
   */  	
  private boolean isJavaType(String typeName) {
  	StringTokenizer st = new StringTokenizer(JAVATYPES);
  	
  	while (st.hasMoreTokens()) { 
  		if (st.nextToken().equals(typeName)) return true;
	}

	return false;
  }
  
  /**
   * @param typeName a type name
   * @return true if typeName is a Package member, false otherwise
   */  
  private boolean isPackageMember(String typeName) {
  	if (typeName.indexOf(PACKID) == -1) {
  		return false;
	} else {
		return true;
 	}
  }
  
  /**
   * Maps one class to one table.<br>
   * @param mc the class        
   */
  private void mapClassToTable(ModelClass mc) {
  	ArrayList tabList = new ArrayList();
  	String className = getClassName(mc);
  	
  	tables.add(new Table(getTableName(className)));
  	tabList.add(getTableName(className));
  	classesToTables.put(className, tabList);
  }
  
  /**
   * Mapping of class names to table names.
   */
  private String getTableName(String className) {
  	return new String(className.toUpperCase());
  }
  
  /**
   * Important: does not care about package names yet !
   * @return the Name of the class to work with
   */
  private String getClassName(ModelClass mc) {
  	return new String(mc.getShortName());
  }
  
  /**
   * @param the name of a table
   * @return the related table object
   */
  private Table getTable(String tabName) {
  	Table t;
  	
  	for (int i=0; i<tables.size(); i++) {
  		t = (Table)tables.get(i);
  		if (t.getTableName().equals(tabName))
  			return t;
  	}
  	
  	return null;
  }
  
  /**
   * Mapping of attribute names to column names.
   */
  private String getColumnName(String attName) {
  	return new String(attName.toUpperCase());
  } 
  
  /**
   * Helper Method. Copies all primary keys from fkTable to the tables of
   * the given class.
   * @param fkTable the table that contains the primary keys.
   * @param className the class name of the target class.  
   */
  private void insertKeysIntoAllClassTables(Table fkTabel, String className) {
  	List tableNames = (List)classesToTables.get(className);
  	Table table;
  	String pkc[] = fkTabel.getPrimaryKeyColumns();
  	
  	for (int i=0; i<tableNames.size(); i++) {
  		table = getTable((String)tableNames.get(i));
  		
  		for (int k=0; k<pkc.length; k++) {
  			try {
  				table.addColumn("", pKColTypeName, pkc[k], false);
  				table.setForeignKey(pkc[k], fkTabel.getTableName(), pkc[k]);
  			} catch(IllegalArgumentException e) {
  				// key still exists
  			}
  		}
  	}
  }

}