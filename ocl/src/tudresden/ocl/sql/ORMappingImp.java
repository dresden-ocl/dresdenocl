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
 * Associations map to tables and foreign key references. It can be an association table
 * for each association regardless the multiplicity or as follows:
 * <ul>
 * <li>many-to-many: one association table</li>
 * <li>one-to-many: the primary key of the one-side will be buried to the many-side's table</li>
 * <li>one-to-one: then primary keys of both sides will be buried at the opposite sides's tables</li>
 * </ul>
 * </li>
 * <li>
 * Attributes map to columns according to the class to table strategy. 
 * Complex attributes are going to be mapped to a table. The table's primary
 * key then is referenced if the attribute is member of a class. That is,
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
   * @element-type Table
   */
  private ArrayList tables;
  
  /**
   * Maps class names to a List of table names (the element type of the List is String). 
   * @key-type String
   * @element-type List 
   */
  private HashMap classesToTables;
  
  /**
   * Maps class names to a Map of navigation guides. The map of
   * navigation guides maps role names to the actual guides
   * (key-type String, element-type Guide).
   * @key-type String
   * @element-type Map
   */
  private HashMap navGuides;
  
  /**
   * Maps class names to a Map (key-type String, element-type String)
   * of association ends that result from
   * the treatment of complex attributes.
   * @key-type String
   * @element-type Map
   */
  private HashMap addAssEnds;
  
  /**
   * Mapping mode for classes to tables. Possible values are:<br>
   * 0 - use one table for an entire class hierarchy<br>
   * 1 - use one table per leaf class<br>
   * 2 - use one table per class (which is the default)
   */
  private int classToTableMode = 2;
  
  /**
   * The number of desired primary key columns (default is 1).
   */
  private int numOfPKCols = 1; 
  
  /**
   * The type name of the primary key columns (default is int).
   */
  private String pKColTypeName = "int";
  
  /**
   * The flag to determine whether each association is mapped to a 
   * separate table or not (default is no or rather false).
   */
  private boolean oneTablePerAssociation;
  
  public static String JAVATYPES = "Vector void?? Rectangle Byte Character Float Integer Double Stack Hashtable char Point Long Color Boolean Date";
  public static String PRIMKEYNAME = "PK";
  public static String ASSTABNAME = "ASSTAB";
  public static String PACKID = "::";
  public static String SEPARATOR = "_";
  
  /**
   * A constructor to create the object relational mapping informations. This constructor
   * uses default parameters for the mapping.
   * @param theModel a model for which isRough() must hold
   * @exception IllegalArgumentException if the theModel.isRough() evaluates to false
   */
  public ORMappingImp(Model theModel) 
  throws IllegalArgumentException {
  	this(theModel, 2, 1, "int", false);
  }
  
  /**
   * A constructor to create the object relational mapping informations. This constructor
   * uses the given parameters for the mapping.
   * @param theModel a model for which isRough() must hold
   * @param classToTableMode see field description of classToTableMode
   * @param numOfPKCols see field description of numOfPKCols
   * @param pKColTypeName see field description of pKColTypeName
   * @param oneTablePerAssociation see field description of oneTablePerAssociation
   * @exception IllegalArgumentException if the theModel.isRough() evaluates to false or the parameters are not valid
   */
  public ORMappingImp(Model theModel, int classToTableMode, int numOfPKCols, String pKColTypeName, boolean oneTablePerAssociation) 
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
    	
    	this.oneTablePerAssociation = oneTablePerAssociation;
    	
    	// initialize class variables
	tables = new ArrayList();
	classesToTables = new HashMap();
	navGuides = new HashMap();
	addAssEnds = new HashMap();
    	
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
  
  public Map associationEnds(String classifier) {
  	Map result = new HashMap();
  	ModelClass mc = (ModelClass)theModel.getClassifier(classifier), endClass, assEndClass;
  	ModelAssociation ma;
  	ModelAssociationEnd end, assEnd;
  	
  	for (Iterator i=theModel.associations().iterator(); i.hasNext(); ) {
  		ma = (ModelAssociation)i.next();
  		// first search for membership of the classifier in current association
  		for (Iterator k=ma.getEnds().iterator(); k.hasNext(); ) {
  			end = (ModelAssociationEnd)k.next();
  			endClass = end.getModelClass();
  			if (getClassName(endClass).equals(classifier)) {
  				// if classifier is association end, determine all other ends
  				for (Iterator l=ma.getEnds().iterator(); l.hasNext(); ) {
  					assEnd = (ModelAssociationEnd)l.next();
  					assEndClass = assEnd.getModelClass();
  					if (!end.getName().equals(assEnd.getName())) {
  						result.put(assEnd.getName(), getClassName(assEndClass));	  				
  						//System.err.println("classifier:" + classifier + " to " + assEnd.getName() + " on " + getClassName(assEndClass));
  					}
  				}  				
  			}
  		}  		  		
  	}
  	
  	// association ends that result from complex attributes
  	result.putAll((Map)addAssEnds.get(classifier));
  	
  	return result;
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
  
  public List guidesToAssociationEnds(String classifier, String assEnd) {
  	/*
  	String temp, key, assEndName;
  	Map m;
  	
  	System.err.println("Anzahl Classifiers:" + navGuides.size());
  	  	
  	for (Iterator i=navGuides.keySet().iterator(); i.hasNext(); ) {
  		key = (String)i.next();
  		temp = "classifier " + key + " has ass ends: ";
  		for (Iterator k=((Map)navGuides.get(key)).keySet().iterator(); k.hasNext(); ) {
  			assEndName = (String)k.next();
  			temp += assEndName + " ";
  		}
  		System.err.println(temp);
  	}
  	*/
  	  	
  	return (List)((Map)navGuides.get(classifier)).get(assEnd);
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
  			//System.err.println("straggler is:" + c.getShortName());
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
  						allTableNames = new ArrayList((List)classesToTables.get(className));
  					} else {
  						allTableNames.addAll((List)classesToTables.get(className));
  					}
  					straggler.put(getClassName(c), allTableNames);
  				}
  			}
		}
	
		//System.err.println("stragglers are:" + straggler.toString());
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
   * Does the attribute mapping for one class.
   * @param mc the model class
   */
  private void mapClassAttributes(ModelClass mc) {
  	Iterator j;
  	String attName, typeName;
  	ModelAttribute ma;
  	Table t, t1;
  	List tableNames, cat, pkTables, fkTables;
  	Map attributes, assEndNameToGuidesList, assEnds = new HashMap();
  	Set fkSet;
  	
  	attributes = mc.attributes();
  	j = attributes.keySet().iterator();  		  	
  	tableNames = (List)classesToTables.get(getClassName(mc));	
	while (j.hasNext()) {
  		attName = (String)j.next();
  		ma = (ModelAttribute)attributes.get(attName);  		

		if (!classesToTables.containsKey(ma.getType().toString())) {
			// basic types
			for (int k=0; k<tableNames.size(); k++) { 				
				t = getTable((String)tableNames.get(k));
  				t.addColumn(attName, ma.getType().toString(), getColumnName(attName, t), false);   							
 			}
		} else {
			// complex types --> use primary key in attribute owner class
			typeName = ma.getType().toString();
			pkTables = getTableList((ArrayList)classesToTables.get(typeName));
			fkTables = getTableList(tableNames);
			
			fkSet = buryForeignKeys(pkTables, fkTables ,attName ,new HashSet());
						
			// guides --> directed one-to-one association 
			assEndNameToGuidesList = getAssEndMap(mc);
			assEndNameToGuidesList.put(attName, createGuideList(mc, (ModelClass)theModel.getClassifier(typeName), null, false, fkSet));
			navGuides.put(getClassName(mc), assEndNameToGuidesList);  
			
			// keep new association end in mind
			assEnds.put(attName, typeName);								
		}  		
  	}
  	
  	addAssEnds.put(getClassName(mc), assEnds);
  }
  
  /**
   * Mapping of the attributes for all classes.
   */
  private void mapAttributes() {
  	Iterator i = classesToTables.keySet().iterator();
    	  	
  	while (i.hasNext()) {
  		mapClassAttributes((ModelClass)theModel.getClassifier((String)i.next()));
  	}  	
  }

  /**
   * Mapping of Associations to foreign key relations and association tables.
   */
  private void mapAssociations() { 
  	Iterator i = theModel.associations().iterator();
  	ModelAssociationEnd mae, mae1, mae2, mae3;
  	ModelAssociation ma;
  	ModelClass ac, aec, startClass, endClass;
  	Table assTab, tempTab;
  	int assTabCount = 0;
  	List assEnds, tableNames, guides;
  	String className, pkc[];
  	Guide guide;
  	Map assEndNameToGuidesList;
  	List tempList, tabList, pkTables, fkTables;
  	Set fkSet;
  	
  	while (i.hasNext()) {
  		ma = (ModelAssociation)i.next();
  		assEnds = ma.getEnds();
  		  		  		
  		if ((ma.allEndsAreMultiple()) || (oneTablePerAssociation)) {
  			// tables for all associations with multiplicity * on all ends
  			// or for each association if oneTablePerAssociation == true
  			ac = ma.getAttribute();
  			
  			// create the association table
  			assTabCount++;
  			fkTables = new ArrayList();
  			if (ac == null) {
  				// create standard table
  				assTab = new Table(ASSTABNAME + assTabCount);
  				fkTables.add(assTab.getTableName());
  			} else {
  				// create table from association class
  				assTab = new Table(getTableName(ac));
  				fkTables.add(assTab.getTableName());
  				classesToTables.put(getClassName(ac), fkTables);
  				mapClassAttributes(ac);  				
  			}
  			tables.add(assTab);
  			
  			// bury all necessary foreign keys
  			fkSet = new HashSet();
  			for (int k=0; k<assEnds.size(); k++) {
  				mae = (ModelAssociationEnd)assEnds.get(k);
  				className = getClassName(mae.getModelClass());
  				pkTables = getTables((List)classesToTables.get(className));
  				fkSet = buryForeignKeys(pkTables, getTableList(fkTables), mae.getName(), fkSet); 				
  			}
  			
  			// create guides to association ends
  			for (int k=0; k<assEnds.size(); k++) {
  				startClass = ((ModelAssociationEnd)assEnds.get(k)).getModelClass();
  				assEndNameToGuidesList = getAssEndMap(startClass);
  				for (int l=0; l<assEnds.size(); l++) {
  					if (l != k) {
  						endClass = ((ModelAssociationEnd)assEnds.get(l)).getModelClass();
  						assEndNameToGuidesList.put(((ModelAssociationEnd)assEnds.get(l)).getName(), createGuideList(startClass, endClass, assTab, false, fkSet));
  					}  						
  				}
  				navGuides.put(getClassName(startClass), assEndNameToGuidesList);
  			}  			
  		} else {
  			// only foreign key references for the other associations
  			for (int k=1; k<assEnds.size(); k++) {
  				mae1 = (ModelAssociationEnd)assEnds.get(0);
  				mae2 = (ModelAssociationEnd)assEnds.get(k);
  				
  				if (mae1.isMultiple() || mae2.isMultiple()) {
  					// 1:* association
  					if (mae1.isMultiple()) {
  						mae3 = mae1;
  						mae1 = mae2;
  						mae2 = mae3;
  					}
  					
  					// get primary keys from mae1 class
  					pkTables = getTableList((List)classesToTables.get(getClassName(mae1.getModelClass())));
  					fkTables = getTableList((List)classesToTables.get(getClassName(mae2.getModelClass())));
  					  					
  					// insert the primary keys into mae2 class
  					fkSet = buryForeignKeys(pkTables, fkTables, mae1.getName(), new HashSet());
  					  					
  					// guides
  					assEndNameToGuidesList = getAssEndMap(mae1.getModelClass());
  					assEndNameToGuidesList.put(mae2.getName(), createGuideList(mae1.getModelClass(), mae2.getModelClass(), null, true, fkSet));
  					navGuides.put(getClassName(mae1.getModelClass()), assEndNameToGuidesList);
  					
  					assEndNameToGuidesList = getAssEndMap(mae2.getModelClass());
  					assEndNameToGuidesList.put(mae1.getName(), createGuideList(mae2.getModelClass(), mae1.getModelClass(), null, false, fkSet));
  					navGuides.put(getClassName(mae2.getModelClass()), assEndNameToGuidesList);
  				} else {
  					// 1:1 association
  					pkTables = getTableList((List)classesToTables.get(getClassName(mae1.getModelClass())));
  					fkTables = getTableList((List)classesToTables.get(getClassName(mae2.getModelClass())));
  					
  					// primary keys from mae1 into mae2 class
  					fkSet = buryForeignKeys(pkTables, fkTables, mae1.getName(), new HashSet());
  					
  					// guide
  					assEndNameToGuidesList = getAssEndMap(mae1.getModelClass());
  					assEndNameToGuidesList.put(mae2.getName(), createGuideList(mae1.getModelClass(), mae2.getModelClass(), null, false, fkSet));
  					navGuides.put(getClassName(mae1.getModelClass()), assEndNameToGuidesList);
  					
  					// primary keys from mae2 into mae1 class
  					fkSet = buryForeignKeys(fkTables, pkTables, mae2.getName(), new HashSet());
  					 					
  					// guide
  					assEndNameToGuidesList = getAssEndMap(mae2.getModelClass());
  					assEndNameToGuidesList.put(mae1.getName(), createGuideList(mae2.getModelClass(), mae1.getModelClass(), null, false, fkSet));
  					navGuides.put(getClassName(mae2.getModelClass()), assEndNameToGuidesList);
  				}
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
   * Mapping of class names to table names.
   */
  private String getTableName(ModelClass mc) {
  	return new String(getClassName(mc).toUpperCase());
  }
    
  /**
   * Important: does not care about package names yet !
   * @return the Name of the class to work with
   */
  private String getClassName(ModelClass mc) {
  	return new String(mc.getShortName());
  }
  
  /**
   * Mapping of attribute names to column names.
   */
  private String getColumnName(String attName, Table table) {
  	if (table.isColumn(attName.toUpperCase())) {
  		for (int i=1;;i++) {
  			if (!table.isColumn(attName.toUpperCase() + "_" + i)) {
  				return new String(attName.toUpperCase() + "_" + i);
  			}
  		}
  	} else {
  		return new String(attName.toUpperCase());
  	}
  } 
  
  /**
   * @param the name of a table
   * @return the related table object
   */
  public Table getTable(String tabName)
  throws NoSuchElementException {
  	Table t;
  	
  	for (int i=0; i<tables.size(); i++) {
  		t = (Table)tables.get(i);
  		if (t.getTableName().equals(tabName))
  			return t;
  	}
  	
  	throw new NoSuchElementException();
  }
  
  /**
   * @param tableNames a List with table names
   * @return a List that contains the actual tables related to the table names
   * @exception NoSuchElementException if a table to a given name does not exist
   */
  private List getTableList(List tableNames)
  throws NoSuchElementException {
  	List result = new ArrayList();
  	
  	for (Iterator i=tableNames.iterator(); i.hasNext(); ) {
  		result.add(getTable((String)i.next()));
  	}
  	
  	return result;
  }
  
  /**
   * @param a List with the names of all tables
   * @return a List with all related table object
   * @exception NoSuchElementException if one of the tables does not exist
   */
  public List getTables(List tabNames) {
  	List result = new ArrayList();
  	Table t;
  	
  	for (int i=0; i<tabNames.size(); i++) {
  		result.add(getTable((String)tabNames.get(i)));
  	}
  	
  	return Collections.unmodifiableList(result);
  }
  
  /**
   * @result a List of Guides that lead from startClass to endClass over assTab
   * @param startClass the class to start from
   * @param endClass the class to lead to
   * @param assTab a possible association Table or null, if such a table does not exist
   * @param nTo1 a flag that indicates whether the association is a many-to-one association or not
   * @param fkSet a Set of foreign key names that belong to the association
   */
  private List createGuideList(ModelClass startClass, ModelClass endClass, Table assTab, boolean nTo1, Set fkSet) {
  	List result = new ArrayList(), startClassTables, endClassTables;
  	Guide guide, guide2;
  	Table t1, t2;
  	
  	startClassTables = (List)classesToTables.get(getClassName(startClass));
  	for (int i=0; i<startClassTables.size(); i++) {
  		t1 = getTable((String)startClassTables.get(i));
  			  		
  		endClassTables = (List)classesToTables.get(getClassName(endClass));
  		for (int k=0; k<endClassTables.size(); k++) {
  				t2 = getTable((String)endClassTables.get(k));

				guide = new Guide(true);  				
  				if (assTab != null) {
  					guide.add(getPrimaryKeyString(t2), t2.getTableName(), getPrimaryKeyString(t2));
  					guide.add(getForeignKeyString(assTab, t2, fkSet), assTab.getTableName(), getForeignKeyString(assTab, t1, fkSet));
  					guide.add(getPrimaryKeyString(t1), t1.getTableName(), getPrimaryKeyString(t1));
  				} else {
  					if (nTo1) {
  						guide.add(getPrimaryKeyString(t2), t2.getTableName(), getForeignKeyString(t2, t1, fkSet));
	  					guide.add(getPrimaryKeyString(t1), t1.getTableName(), getPrimaryKeyString(t1));
  					} else {
  						guide.add(getPrimaryKeyString(t2), t2.getTableName(), getPrimaryKeyString(t2));
	  					guide.add(getForeignKeyString(t1, t2, fkSet), t1.getTableName(), getPrimaryKeyString(t1));
	  				}
	  			}
	  			result.add(guide);
  		}  		  		
  	}
  	  	
  	return result;
  }
  
  /**
   * Helper methode.
   */
  private String getPrimaryKeyString(Table t) {
  	String pkc[] = t.getPrimaryKeyColumns(), result;
  	
  	if (pkc.length == 1) {
  		 return pkc[0];
  	} else {
  		result = "(";
  	 	for (int i=0; i<pkc.length; i++) {
  	 		if (i>0) result +=",";
  	 		result += pkc[i];  	 		 
  		}
  		result += ")";
  		return result;
  	}
  }
  
  
  /**
   * Helper methode.
   */
  private String getForeignKeyString(Table fkTable, Table pkTable, Set fkSet) {
  	String fkc[] = fkTable.getForeignKeyColumns(), result = "?";
  	List theFK = new ArrayList();
  	
  	for (int i=0; i<fkc.length; i++) {
  		if ((fkTable.getForeignTable(fkc[i]).equals(pkTable.getTableName()))
  		    && (fkSet.contains(fkc[i]))) {
  			theFK.add(fkc[i]);  			
  		}
  	}
  		
  	
  	if (theFK.size() == 1) {
  		result = (String)theFK.get(0);
  	} else {
  		result = "(";
  	 	for (int i=0; i<theFK.size(); i++) {
  	 		if (i>0) result +=",";
  	 		result += theFK.get(i);   			  	 		 	 		 
  		}
  		result += ")";
  	}
  	
    	return result;
  } 
  
  /**
   * Helper methode.
   */
  private Map getAssEndMap(ModelClass mc) {
  	Map result = (Map)navGuides.get(getClassName(mc));
  	
  	if (result == null) {
  		return new HashMap();
  	} else {
  		return result;
  	}
  }
  
  /**
   * Helper methode.
   */
  private String getAssociationName(ModelAssociation ma) {
  	String result = "";
  	
  	for (Iterator i=ma.getEnds().iterator(); i.hasNext(); ) {
  		result += ((ModelAssociationEnd)i.next()).getName();
  	}
  	
  	return result;
  }
  
  /**
   * Buries the primary keys of the pkTables to all fkTables as foreign keys. 
   * If pkTables size is greater than one, the number of the according buried foreign
   * keys is related to the actual number of pkTables. That is, for each pkTable
   * a foreign key will be added to the foreign key Tables. 
   * @return a List of Strings that contains the names of all buried keys
   * @param pkTables a List of tables which contain the primary keys
   * @param fkTables a List of tables to which the foreign keys are going to be buried
   * @param assEndName the name of the association end which belongs to the pkTables
   * @param fkSet a Set as initialization for the result Set
   */
  private Set buryForeignKeys(List pkTables, List fkTables, String assEndName, Set fkSet) {
  	Set result;
  	Table fkTable, pkTable;
  	String buriedKeyName, pkNames[];
  	
  	if (fkSet == null) {
  		result = new HashSet();
  	} else {
  		result = fkSet;
  	}
  	  	
  	for (Iterator i=fkTables.iterator(); i.hasNext(); ) {
  		fkTable = (Table)i.next();
  		
  		for (Iterator k=pkTables.iterator(); k.hasNext(); ) {
  			pkTable = (Table)k.next();
  			pkNames = pkTable.getPrimaryKeyColumns();
  			
  			for (int l=0; l<pkNames.length; l++) {
  				buriedKeyName = getColumnName(assEndName + SEPARATOR + pkNames[l], fkTable);
  				fkTable.addColumn("", pkTable.getColumnType(pkNames[l]), buriedKeyName, false);
  				fkTable.setForeignKey(buriedKeyName, pkTable.getTableName(), pkNames[l] );
  				result.add(buriedKeyName);
  			}
  		}
  	}
  	
  	return result;
  }
  
  //-------------------------------- private methode tests --------------------------------
  public void testBuryForeignKeys(boolean verbose) 
  throws RuntimeException {
  	List pkTables, fkTables;
  	Set result;
  	Table t, fk1, fk2;
  	
  	if (verbose) System.err.println("testBuryForeignKeys is running ...");
  	if (verbose) System.err.print("initializing test objects ...");
  	pkTables = new ArrayList();
  	fkTables = new ArrayList();
  	
  	t = new Table("pkTable1");
  	t.addColumn("", "int", "PK11", true);
  	t.addColumn("", "int", "PK12", true);
  	pkTables.add(t);
  	
  	t = new Table("pkTable2");
  	t.addColumn("", "int", "PK21", true);
  	t.addColumn("", "int", "PK22", true);
  	pkTables.add(t);
  	  	
  	fk1 = new Table("fkTable1");
  	fkTables.add(fk1);
  	
  	fk2 = new Table("fkTable2");
  	fkTables.add(fk2);
  	if (verbose) System.err.print("done\n");
  	
  	if (verbose) System.err.print("calling methode ... ");
  	result = buryForeignKeys(pkTables, fkTables, "test", null);
  	if (verbose) System.err.print("done\n");
  	
  	if (verbose) System.err.print("check result ... ");
  	t = new Table("temp");
  	if (result.size() != 4) {
  		throw new RuntimeException("Wrong number of List entries:" + result.toString());
  	}
  	if ((!result.contains(getColumnName("TEST" + SEPARATOR + "PK11", t))) ||
  	    (!result.contains(getColumnName("TEST" + SEPARATOR + "PK12", t))) ||
  	    (!result.contains(getColumnName("TEST" + SEPARATOR + "PK21", t))) ||
  	    (!result.contains(getColumnName("TEST" + SEPARATOR + "PK22", t)))) {
  	   	throw new RuntimeException("List entries do not match excpected values:"  + result.toString());
  	}
  	if (verbose) System.err.print("done\n");
  	   	
  	if (verbose) System.err.print("check buried keys ... ");
  	if ((!fk1.isForeignKeyColumn(getColumnName("TEST" + SEPARATOR + "PK11", t))) ||
  	    (!fk1.isForeignKeyColumn(getColumnName("TEST" + SEPARATOR + "PK12", t))) ||
  	    (!fk1.isForeignKeyColumn(getColumnName("TEST" + SEPARATOR + "PK21", t))) ||
  	    (!fk1.isForeignKeyColumn(getColumnName("TEST" + SEPARATOR + "PK22", t)))) {
  	   	throw new RuntimeException("Table entries of fk1 do not match excpected values !");
  	}
  	if ((!fk2.isForeignKeyColumn(getColumnName("TEST" + SEPARATOR + "PK11", t))) ||
  	    (!fk2.isForeignKeyColumn(getColumnName("TEST" + SEPARATOR + "PK12", t))) ||
  	    (!fk2.isForeignKeyColumn(getColumnName("TEST" + SEPARATOR + "PK21", t))) ||
  	    (!fk2.isForeignKeyColumn(getColumnName("TEST" + SEPARATOR + "PK22", t)))) {
  	   	throw new RuntimeException("Table entries of fk2 do not match excpected values !");
  	}
  	if ((!fk2.getForeignTable(getColumnName("TEST" + SEPARATOR + "PK11", t)).equals("pkTable1")) ||
  	    (!fk2.getForeignColumn(getColumnName("TEST" + SEPARATOR + "PK11", t)).equals("PK11")) ||
  	    (!fk2.getForeignTable(getColumnName("TEST" + SEPARATOR + "PK21", t)).equals("pkTable2")) ||
  	    (!fk2.getForeignColumn(getColumnName("TEST" + SEPARATOR + "PK21", t)).equals("PK21"))) {
  	    	throw new RuntimeException("Foreign references not properly set in fk2 !");
  	}
  	if (verbose) System.err.print("done\n");
  }
}