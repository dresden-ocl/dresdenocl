/*
 * ORMappingImpl.java
 * 
 * Copyright (c) 2005 Florian Heidenreich
 * Contact: <mail@fheidenreich.de>
 * 
 * Based on ORMappingImp (c) 2001 Sten Loecher
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

package tudresden.ocl20.codegen.sql.orm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.OclModelHelper;
import tudresden.ocl20.core.jmi.uml15.core.Association;
import tudresden.ocl20.core.jmi.uml15.core.AssociationEnd;
import tudresden.ocl20.core.jmi.uml15.core.Attribute;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.core.CorePackage;
import tudresden.ocl20.core.jmi.uml15.core.Operation;
import tudresden.ocl20.core.jmi.uml15.core.UmlAssociationClass;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.codegen.sql.codegen.Guide;
import tudresden.ocl20.codegen.sql.util.MetaModelHelper;

/**
 * An implementation of the ORMapping interface. It generates a number of tables
 * for classes and associations according some rules that are parameterized. 
 * The source of the object oriented information is a OclModel object.
 * 
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
 * 
 * @author Florian Heidenreich
 * 
 */
public class ORMappingImpl implements ORMapping {

	public static String ASSTABNAME = "ASSTAB";

	public static String PACKID = "::";

	public static String PRIMKEYNAME = "PK";

	public static String SEPARATOR = "_";

	/**
	 * Maps class names to a Map (key-type String, element-type String) of
	 * association ends that result from the treatment of complex attributes.
	 */
	private Map<String,Map<String,String>> addAssEnds;

	/**
	 * Maps class names to a List of table names (the element type of the List
	 * is String).
	 */
	private HashMap<String, List<String>> classesToTables;

	/**
	 * Mapping mode for classes to tables.
	 * The default is to use one table per class.
	 * 
	 * @see tudresden.ocl20.sql.orm.ORMappingMode
	 */
	private ORMappingMode classToTableMode = ORMappingMode.oneTablePerClass;

	/**
	 * Helper object to find classifiers in the OclModel
	 */
	private OclModelHelper hlp;

	/**
	 * Maps class names to a Map of navigation guides. The map of navigation
	 * guides maps role names to the actual guides (key-type String,
	 * element-type Guide).
	 */
	private Map<String,Map<String,List<Guide>>> navGuides;

	/**
	 * The number of desired primary key columns (default is 1).
	 */
	private int numOfPKCols = 1;

	/**
	 * The flag to determine whether each association is mapped to a separate
	 * table or not (default is no or rather false).
	 */
	private boolean oneTablePerAssociation;

	/**
	 * The type name of the primary key columns (default is int).
	 */
	private String pKColTypeName = "int";

	/**
	 * A list that contains all the tables from the object relational mapping.
	 */
	private List<Table> tables;

	/**
	 * The model containing the object oriented information.
	 */
	private OclModel theModel;

	/**
	 * A constructor to create the object relational mapping informations. This
	 * constructor uses default parameters for the mapping.
	 * 
	 * @param model a model containing the object oriented information.
	 */
	public ORMappingImpl(OclModel model) {
		this(model, ORMappingMode.oneTablePerClass, 1, "int", false);
	}

	/**
	 * A constructor to create the object relational mapping informations. This
	 * constructor uses the given parameters for the mapping.
	 * 
	 * @param model
	 *            a model containing the object oriented information.
	 * @param classToTableMode
	 *            see field description of classToTableMode
	 * @param numOfPKCols
	 *            see field description of numOfPKCols
	 * @param pKColTypeName
	 *            see field description of pKColTypeName
	 * @param oneTablePerAssociation
	 *            see field description of oneTablePerAssociation
	 * @exception IllegalArgumentException
	 *                if one of the parameters is not valid
	 */
	public ORMappingImpl(OclModel model, ORMappingMode classToTableMode, int numOfPKCols,
			String pKColTypeName, boolean oneTablePerAssociation)
			throws IllegalArgumentException {
		
		if (numOfPKCols < 1) {
			throw new IllegalArgumentException(
					"Number of primary key columns must not be less than 1 !");
		} else {
			this.numOfPKCols = numOfPKCols;
		}

		if (pKColTypeName == null) {
			throw new IllegalArgumentException(
					"Primary key type name must not be null !");
		} else {
			this.pKColTypeName = pKColTypeName;
		}

		if (model != null) {
			this.theModel = model;
			this.hlp = new OclModelHelper(theModel);
		} else {
			throw new IllegalArgumentException("Model must not be null!");
		}

		this.classToTableMode = classToTableMode;
		this.oneTablePerAssociation = oneTablePerAssociation;

		// initialize class variables
		tables = new ArrayList<Table>();
		classesToTables = new HashMap<String, List<String>>();
		navGuides = new HashMap<String,Map<String,List<Guide>>>();
		addAssEnds = new HashMap<String,Map<String,String>>();

		// run through the mapping
		createClassTables();
		generatePrimaryKeys();
		mapAttributes();
		mapAssociations();
	}

	public Map<String,String> getAssociationEnds(String classifier)
	{
		Map<String,String> result = new HashMap<String,String>();
		Classifier c = (Classifier)hlp.findClassifier(classifier);
		
		Iterator ownEnds = ((CorePackage)c.refImmediatePackage()).getAParticipantAssociation().getAssociation(c).iterator();
        while(ownEnds.hasNext()){
            AssociationEnd ownEnd = (AssociationEnd) ownEnds.next();
            Association a = ownEnd.getAssociation();
            Iterator oppositeEnds = a.getConnection().iterator();
            while(oppositeEnds.hasNext()){
                AssociationEnd oppositeEnd = (AssociationEnd) oppositeEnds.next();
                if(!oppositeEnd.equals(ownEnd)){
					result.put(oppositeEnd.getNameA(), oppositeEnd.getTypeA().getNameA());
                    //Issue: What about unnamed AssociationEnds?
                }
            }
        }
		  	
	  	// association ends that result from complex attributes
	  	result.putAll((Map<String,String>)addAssEnds.get(classifier));
	  	
	  	return result;
	}

	public Set<String> getAttributes(String classifier) {
		Classifier c = (Classifier) hlp.findClassifier(classifier);
		
		Set<String> attrs = new HashSet<String>();
		Iterator it = c.allAttributes().iterator();
		while (it.hasNext()) {
			attrs.add(((Attribute)it.next()).getNameA());
		}
		return attrs;
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
	  private Set<String> buryForeignKeys(List<Table> pkTables, List<Table> fkTables, String assEndName, Set<String> fkSet) {
	  	Set<String> result;
	  	String buriedKeyName;
		String[] pkNames;
	  	
	  	if (fkSet == null) {
	  		result = new HashSet<String>();
	  	} else {
	  		result = fkSet;
	  	}
	  	  	
	  	for (Table fkTable : fkTables) {
	  		for (Table pkTable : pkTables) {
	  			pkNames = pkTable.getPrimaryKeyColumns();
	  			
	  			for (String pkName : pkNames) {
	  				buriedKeyName = getColumnName(assEndName + SEPARATOR + pkName, fkTable);
	  				fkTable.addColumn("", pkTable.getColumnType(pkName), buriedKeyName, false);
	  				fkTable.setForeignKey(buriedKeyName, pkTable.getTableName(), pkName);
	  				result.add(buriedKeyName);
	  			}
	  		}
	  	}
	  	
	  	return result;
	  }

	public Set<String> getClassifiers() {
		Set<String> retSet = new HashSet<String>();  

		for (String clsName : classesToTables.keySet()) {
			retSet.add(clsName);
		}
		  	
		return retSet;
	}

	/**
	 * Creates all the class tables with respect to the mapping mode.
	 */
	private void createClassTables() {
		List<String> allTableNames;
		Classifier classifier;
		HashSet<String> dupl = new HashSet<String>();
		HashSet<Classifier> tempSet = new HashSet<Classifier>();
		String fullName;

		Uml15Package mdl = (Uml15Package) theModel.getModel();
		
		// Iterator over all Classifiers from the Model
		Iterator itCls = mdl.getCore().getClassifier().refAllOfType().iterator();

		while (itCls.hasNext()) {
			classifier = (Classifier) itCls.next();
			fullName = classifier.getName();
			
			// skip Java and OCL-Metamodel types
			if (MetaModelHelper.isJavaType(fullName)) {
				continue;
			}
			
			// skip duplicates
			if (!dupl.add(fullName)) {
				continue;
			}
			
			// map classes to tables
			switch (classToTableMode) {
			case oneTablePerHierarchy:
				// one table for for an entire class hierarchy
				if (!MetaModelHelper.hasSupertypes(classifier)) {
					// create a table for each generalization root
					mapClassToTable(classifier);
				} else {
					// keep all child classes in mind
					tempSet.add(classifier);
				}
				break;
				
			case oneTablePerLeafClass:
				// one table per leaf class
				if (!MetaModelHelper.hasSubtypes(theModel, classifier)) {
					// create a table for each leaf class
					mapClassToTable(classifier);
				} else {
					// keep all non leaf classes in mind
					tempSet.add(classifier);
				}
				break;
				
			case oneTablePerClass:
				// one table per class
				mapClassToTable(classifier);
				break;
				
			default:
				throw new IllegalStateException("Illegal mode for class to table mapping.");
			}
		}

		if (classToTableMode == ORMappingMode.oneTablePerHierarchy) {
			// associate all child classes to the table of the their hierarchy root class
			for (Classifier cls : tempSet) {
				Classifier root = MetaModelHelper.getGeneralizationRoot(cls);
				assert(root != null): "Generalization root of child class is null !";
				allTableNames = classesToTables.get(root.getNameA());
				classesToTables.put(cls.getName(), allTableNames);
			}
		}

		if (classToTableMode == ORMappingMode.oneTablePerLeafClass) {
			// associate all ancestor classes to the tables of their childs (leaf classes)
			HashMap<String, List<String>> straggler = new HashMap<String, List<String>>();
			OclModelHelper mh = new OclModelHelper(theModel);

			for (Classifier cls : tempSet) {
				String clsName = cls.getNameA();
				
				for (String className : classesToTables.keySet()) {
					Classifier c1 = (Classifier) mh.findClassifier(className);
					List<Classifier> parents = MetaModelHelper.getAllSupertypes(c1);

					if (parents.contains(cls)) {
						allTableNames = straggler.get(clsName);
						if (allTableNames == null) {
							allTableNames = new ArrayList<String>(classesToTables.get(className));
						} else {
							allTableNames.addAll(classesToTables.get(className));
						}
						straggler.put(clsName, allTableNames);
					}
				}
			}

			classesToTables.putAll(straggler);
		}
	}
	
	/**
	 * @result a List of Guides that lead from startClass to endClass over assTab
	 * @param startClass the class to start from
	 * @param endClass the class to lead to
	 * @param assTab a possible association Table or null, if such a table does not exist
	 * @param nTo1 a flag that indicates whether the association is a many-to-one association or not
	 * @param fkSet a Set of foreign key names that belong to the association
	 */
	private List<Guide> createGuideList(Classifier startClass, Classifier endClass, Table assTab, boolean nTo1, Set<String> fkSet) {
		Guide guide;
		List<Guide> result = new ArrayList<Guide>();
		List<String> startClassTables;
		List<String> endClassTables;
	  	Table t1;
		Table t2;
	  	
	  	startClassTables = classesToTables.get(startClass.getNameA());
	  	for (String startClassTableName : startClassTables) {
	  		t1 = getTable(startClassTableName);
			
	  		endClassTables = classesToTables.get(endClass.getNameA());
	  		for (String endClassTableName : endClassTables) {
				t2 = getTable(endClassTableName);
					
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

	public Set<String> getDirectSupertypeNames(String classifier) {
		Set<String> supertypes = new HashSet<String>();
		Classifier c = (Classifier)hlp.findClassifier(classifier);
		
		Iterator<Classifier> it = MetaModelHelper.getSupertypes(c).iterator();
		while (it.hasNext()) {
			supertypes.add(it.next().getNameA());
		}
		
		return supertypes;
	}

	/**
	 * Generates the primary keys for all classes.
	 */
	private void generatePrimaryKeys() {
		int keyCount = 0;
		HashSet<String> tempSet = new HashSet<String>();
		Iterator<String> i = classesToTables.keySet().iterator();
		List<String> tableNames;
		String className;
		String keyName;		
		Table t;

		// iterate over all classes and generate a key for all hierarchy roots
		while (i.hasNext()) {
			className = i.next();
			Classifier c = (Classifier) hlp.findClassifier(className);

			if (!MetaModelHelper.hasSupertypes(c)) {
				// get all table names of the class
				tableNames = classesToTables.get(className);

				// generate key
				keyCount++;
				keyName = PRIMKEYNAME + keyCount;

				// add primary key columns to all class tables
				for (String tableName : tableNames) {
					t = getTable(tableName);
					
					if (numOfPKCols == 1) {
						try {
							t.addColumn("", pKColTypeName, keyName, true);
						} catch (IllegalArgumentException e) {
							// key still exists
						}
					} else {
						for (int l = 1; l <= numOfPKCols; l++) {
							try {
								t.addColumn("", pKColTypeName, keyName + "_" + l, true);
							} catch (IllegalArgumentException e) {
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
		if (classToTableMode == ORMappingMode.oneTablePerClass) {
			String rootName;
			String rootTable;
			String[] pkCols;
			
			for (String childName : tempSet) {
				rootName = MetaModelHelper.getGeneralizationRoot((Classifier) hlp.findClassifier(childName)).getNameA();
				rootTable = classesToTables.get(rootName).get(0);
				pkCols = (getTable(rootTable)).getPrimaryKeyColumns();

				tableNames = classesToTables.get(childName);
				for (int k = 0; k < tableNames.size(); k++) {
					t = getTable((String) tableNames.get(k));

					for (int l = 0; l < pkCols.length; l++) {
						try {
							t.addColumn("", pKColTypeName, pkCols[l], true);
							t.setForeignKey(pkCols[l], rootTable, pkCols[l]);
						} catch (IllegalArgumentException e) {
							// key still exists
						}
					}
				}
			}
		}
	}

	private Map<String,List<Guide>> getAssEndMap(Classifier mc) {
	  	Map<String,List<Guide>> result = navGuides.get(mc.getNameA());
	  	
	  	if (result == null) {
	  		result = new HashMap<String,List<Guide>>();
	  	}
		
	  	return result;
	  }

	public List<Table> getClassTables(String classifier)
			throws IllegalArgumentException {
		List<Table> tabList;
		List<String> tabNameList;

		if (classifier == null) {
			throw new IllegalArgumentException("Classifier must not be null !");
		}
		
		tabNameList = classesToTables.get(classifier);
		if (tabNameList == null) {
			throw new IllegalArgumentException("Classifier does not exist: "
					+ classifier);
		}
		
		tabList = new ArrayList<Table>();
		for (String tabName : tabNameList) {
			tabList.add(getTable(tabName));
		}

		return Collections.unmodifiableList(tabList);
	}

	/**
	 * Mapping of attribute names to column names.
	 */
	private String getColumnName(String attName, Table t) {
		if (t.isColumn(attName.toUpperCase())) {
			for (int i = 1;; i++) {
				if (!t.isColumn(attName.toUpperCase() + "_" + i)) {
					return new String(attName.toUpperCase() + "_" + i);
				}
			}
		} else {
			return new String(attName.toUpperCase());
		}
	}

	private String getForeignKeyString(Table fkTable, Table pkTable, Set<String> fkSet) {
		String result = "?";
		String[] fkc = fkTable.getForeignKeyColumns();
		List<String> theFK = new ArrayList<String>();
		  	
		for (String columnName : fkc) {
			if ((fkTable.getForeignTable(columnName).equals(pkTable.getTableName()))
					&& (fkSet.contains(columnName))) {
				theFK.add(columnName);  			
			}
		}
		  	
		if (theFK.size() == 1) {
			result = theFK.get(0);
		} else {
			result = "(";
			for (int i = 0; i < theFK.size(); i++) {
				if (i > 0) {
					result +=",";
				}
				result += theFK.get(i);   			  	 		 	 		 
			}
			result += ")";
		}
		return result;
	} 

	private String getPrimaryKeyString(Table t) {
		String result;
		String pkc[] = t.getPrimaryKeyColumns();
		  	
		if (pkc.length == 1) {
			result = pkc[0];
		} else {
			result = "(";
			for (int i=0; i<pkc.length; i++) {
				if (i>0) {
					result +=",";
				}
				result += pkc[i];  	 		 
			}
			result += ")";
		}
		return result;
	}

	/**
	 * @param tableName the name of a table
	 * @return the related table object
	 */
	public Table getTable(String tableName) throws NoSuchElementException {
		for (Table t : tables) {
			if (t.getTableName().equals(tableName)) {
				return t;
			}
		}

		throw new NoSuchElementException();
	}
	
	/**
	   * @param tableNames a List with the names of all tables
	   * @return a List with all related table object
	   * @exception NoSuchElementException if one of the tables does not exist
	   */
	  public List<Table> getTables(List<String> tableNames) {
	  	List<Table> result = new ArrayList<Table>();
	  	
		for (String tableName : tableNames) {
			result.add(getTable(tableName));
		}
	  	
	  	return Collections.unmodifiableList(result);
	  }

	/**
	 * @param tableNames a List with table names
	 * @return a List that contains the actual tables related to the table names
	 * @exception NoSuchElementException if a table to a given name does not exist
	 */
	private List<Table> getTableList(List<String> tableNames) throws NoSuchElementException {
		List<Table> result = new ArrayList<Table>();

		for (String tableName : tableNames) {
			result.add(getTable(tableName));
		}

		return result;
	}

	/**
	 * Mapping of class names to table names.
	 */
	private String getTableName(String className) {
		return new String(className.toUpperCase());
	}

	public List<Guide> guidesToAssociationEnds(String classifier, String assEnd) {
		return navGuides.get(classifier).get(assEnd);
	}

	/**
	 * Mapping of Associations to foreign key relations and association tables.
	 */
	private void mapAssociations() { 
		AssociationEnd assEnd, assEnd1, assEnd2, assEnd3;
	  	Association ass;
	  	Classifier startClass;
	  	Classifier endClass;
	  	Table assTab;
	  	int assTabCount = 0;
	  	List assEnds;
	  	String className;
	  	Map<String,List<Guide>> assEndNameToGuidesList;
		List<Table> fkTables;
		List<Table> pkTables;
	  	Set<String> fkSet;
		Set<String> fkSet2;
		
		Iterator itAss = ((Uml15Package)theModel.getModel()).getCore().getAssociation().refAllOfType().iterator();
	  	
	  	while (itAss.hasNext()) {
	  		ass = (Association)itAss.next();
	  		assEnds = ass.getConnection();
	  		  		  		
	  		if ((MetaModelHelper.allEndsAreMultiple(ass)) || (oneTablePerAssociation)) {
	  			// tables for all associations with multiplicity * on all ends
	  			// or for each association if oneTablePerAssociation == true
	  			
	  			assTabCount++;
	  			fkTables = new ArrayList<Table>();

				// determine if the class is an AssociationClass and map the 
				// class attributes to a new table (or to an already mapped table)
	  			if (ass instanceof UmlAssociationClass) {
	  				// only create new Table if the Class wasn't mapped already
	  				if (classesToTables.get(ass.getNameA()) == null) {
	  					assTab = new Table(getTableName(ass.getNameA()));
	  					mapClassToTable((Classifier)ass);
	  					mapClassAttributes((Classifier)ass);
	  					tables.add(assTab);
	  				} else {
	  					assTab = getTable(getTableName(ass.getNameA()));
	  				}
	  			} else {
					// create the association table
		  			assTab = new Table(ASSTABNAME + assTabCount);
		  			tables.add(assTab);
	  			}
	  			fkTables.add(assTab);
	  			
	  			// bury all necessary foreign keys
	  			fkSet = new HashSet<String>();
	  			for (int k=0; k<assEnds.size(); k++) {
	  				assEnd = (AssociationEnd)assEnds.get(k);
	  				className = assEnd.getTypeA().getNameA();
	  				pkTables = getTables(classesToTables.get(className));
	  				fkSet = buryForeignKeys(pkTables, fkTables, assEnd.getName(), fkSet); 				
	  			}
	  			
	  			// create guides to association ends
	  			for (int k=0; k<assEnds.size(); k++) {
	  				startClass = ((AssociationEnd)assEnds.get(k)).getParticipant();
	  				assEndNameToGuidesList = getAssEndMap(startClass);
	  				for (int l=0; l<assEnds.size(); l++) {
	  					if (l != k) {
	  						endClass = ((AssociationEnd)assEnds.get(l)).getParticipant();
	  						assEndNameToGuidesList.put(((AssociationEnd)assEnds.get(l)).getName(), createGuideList(startClass, endClass, assTab, false, fkSet));
	  					}  						
	  				}
	  				navGuides.put(startClass.getNameA(), assEndNameToGuidesList);
	  			}  			
	  		} else {
	  			// only foreign key references for the other associations
	  			for (int k=1; k<assEnds.size(); k++) {
	  				assEnd1 = (AssociationEnd)assEnds.get(0);
	  				assEnd2 = (AssociationEnd)assEnds.get(k);
	  				
	  				if (assEnd1.isMultipleA() || assEnd2.isMultipleA()) {
	  					// 1:* association
	  					if (assEnd1.isMultipleA()) {
	  						assEnd3 = assEnd1;
	  						assEnd1 = assEnd2;
	  						assEnd2 = assEnd3;
	  					}
	  					
	  					// get primary keys from assEnd1 class
	  					pkTables = getTableList(classesToTables.get(assEnd1.getParticipant().getNameA()));
	  					fkTables = getTableList(classesToTables.get(assEnd2.getParticipant().getNameA()));
	  					  					
	  					// insert the primary keys into assEnd2 class
	  					fkSet = buryForeignKeys(pkTables, fkTables, assEnd1.getName(), new HashSet<String>());
	  					  					
	  					// guides
	  					assEndNameToGuidesList = getAssEndMap(assEnd1.getParticipant());
	  					assEndNameToGuidesList.put(assEnd2.getName(), createGuideList(assEnd1.getParticipant(), assEnd2.getParticipant(), null, true, fkSet));
	  					navGuides.put(assEnd1.getParticipant().getNameA(), assEndNameToGuidesList);
	  					
	  					assEndNameToGuidesList = getAssEndMap(assEnd2.getParticipant());
	  					assEndNameToGuidesList.put(assEnd1.getName(), createGuideList(assEnd2.getParticipant(), assEnd1.getParticipant(), null, false, fkSet));
	  					navGuides.put(assEnd2.getParticipant().getNameA(), assEndNameToGuidesList);
	  				} else {
	  					// 1:1 association
	  					pkTables = getTableList(classesToTables.get(assEnd1.getParticipant().getNameA()));
	  					fkTables = getTableList(classesToTables.get(assEnd2.getParticipant().getNameA()));  
	  					
	  					// primary keys from assEnd1 into assEnd2 class
	  					fkSet = buryForeignKeys(pkTables, fkTables, assEnd1.getName(), new HashSet<String>());
	                                        
	                    // primary keys from assEnd2 into assEnd1 class
	  					fkSet2 = buryForeignKeys(fkTables, pkTables, assEnd2.getName(), new HashSet<String>());
	                         
	    				// guide assEnd1 --> assEnd2
	  					assEndNameToGuidesList = getAssEndMap(assEnd1.getParticipant());
	  					assEndNameToGuidesList.put(assEnd2.getName(), createGuideList(assEnd1.getParticipant(), assEnd2.getParticipant(), null, false, fkSet2));
	  					navGuides.put(assEnd1.getParticipant().getNameA(), assEndNameToGuidesList);
	  							 					
	  					// guide assEnd2 --> assEnd1
	  					assEndNameToGuidesList = getAssEndMap(assEnd2.getParticipant());
	  					assEndNameToGuidesList.put(assEnd1.getName(), createGuideList(assEnd2.getParticipant(), assEnd1.getParticipant(), null, false, fkSet));
	  					navGuides.put(assEnd2.getParticipant().getNameA(), assEndNameToGuidesList);
	  				}
	  			}
	  		}
	  	}
	  }

	/**
	 * Mapping of the attributes for all classes.
	 */
	private void mapAttributes() {
		Iterator<String> i = classesToTables.keySet().iterator();

		while (i.hasNext()) {
			mapClassAttributes((Classifier) hlp.findClassifier(i.next()));
		}
	}

	/**
	 * Mapping of the attributes for the given class.
	 * 
	 * @param c a Classifier for which the attributes are mapped to columns and tables
	 */
	private void mapClassAttributes(Classifier c) {
		String attrName;
		String attrTypeName;	
		Table t;
		List<Attribute> attributes;
		List<String> tableNames;
		List<Table> pkTables;
		List<Table> fkTables;
		Map<String,List<Guide>> assEndNameToGuidesList = new HashMap<String, List<Guide>>();
		Map<String,String> assEnds = new HashMap<String,String>();
		Set<String> fkSet;
		
		attributes = MetaModelHelper.getAttributes(c);
		tableNames = classesToTables.get(c.getNameA());
		
		for (Attribute attr : attributes) {
			attrName = attr.getNameA();
			attrTypeName = attr.getType().toString();

			if (!classesToTables.containsKey(attrTypeName)) {
				// basic types
				for (String tableName : tableNames) {
					t = getTable(tableName);
					t.addColumn(attrName, attr.getType().getNameA(), getColumnName(attrName, t), false);
				}
			} else {
				// complex types --> use primary key in attribute owner class
				pkTables = getTableList(classesToTables.get(attrTypeName));
				fkTables = getTableList(tableNames);

				fkSet = buryForeignKeys(pkTables, fkTables, attrName, new HashSet<String>());

				// guides --> directed one-to-one association 
				assEndNameToGuidesList = getAssEndMap(c);
				assEndNameToGuidesList.put(attrName, createGuideList(c, (Classifier)hlp.findClassifier(attrTypeName), null, false, fkSet));
				navGuides.put(c.getNameA(), assEndNameToGuidesList);

				// keep new association end in mind
				assEnds.put(attrName, attrTypeName);
			}
		}

		addAssEnds.put(c.getNameA(), assEnds);
	}

	private void mapClassToTable(Classifier c) {
		List<String> tabList = new ArrayList<String>();
		String className = c.getName();

		tables.add(new Table(getTableName(className)));
		tabList.add(getTableName(className));
		classesToTables.put(className, tabList);
	}

	public Set<String> getOperations(String classifier) {
		Classifier c = (Classifier) hlp.findClassifier(classifier);
		
		Set<String> ops = new HashSet<String>();
		Iterator it = c.allOperations().iterator();
		while (it.hasNext()) {
			ops.add(((Operation)it.next()).getNameA());
		}
		return ops;
	}

	public List<Table> getTables() {
		return Collections.unmodifiableList(tables);
	}
}
