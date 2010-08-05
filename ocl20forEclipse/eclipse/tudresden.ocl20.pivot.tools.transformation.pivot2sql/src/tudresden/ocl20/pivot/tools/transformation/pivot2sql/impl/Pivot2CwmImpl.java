/*
 * Created on 26.01.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EPackage;
import orgomg.cwm.foundation.datatypes.DatatypesPackage;
import orgomg.cwm.foundation.datatypes.QueryExpression;
import orgomg.cwm.objectmodel.core.DataType;
import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.ForeignKey;
import orgomg.cwm.resource.relational.PrimaryKey;
import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.relational.SQLSimpleType;
import orgomg.cwm.resource.relational.Schema;
import orgomg.cwm.resource.relational.Table;
import orgomg.cwm.resource.relational.View;
import orgomg.cwm.resource.relational.impl.RelationalPackageImpl;

import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.pivotmodel.AssociationProperty;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.impl.Ocl2DeclSettings;
import tudresden.ocl20.pivot.tools.template.ITemplate;
import tudresden.ocl20.pivot.tools.template.impl.TemplateHelper;
import tudresden.ocl20.pivot.tools.transformation.M2MTransformation;
import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.Pivot2SqlPlugin;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.util.PivotModelAnalyser;


/**
 * The class Uml2CwmImpl realises the transformation of an instance of the Uml Metamodel into an instance of the 
 * CWM Metamodel. The Implementation contains the objectrelational mapping.
 * @author Christian Wende
 * @author Bjoern Freitag
 *
 */
public class Pivot2CwmImpl extends M2MTransformation<Namespace, Schema>{
	
	private Logger LOGGER = Pivot2SqlPlugin.getLogger(Pivot2CwmImpl.class);
	
	private static final String ONE_PK = "A Table must contain exactly on PrimaryKey. ";
	private static final String UNKNOWN_TYPE = "While mapping an Attribute an unknown datatype was found. ";
	private static final String PART_CLASS = "The partitipants of an Association must be UmlClasses. ";
	private static final String ASS_ROLE = "The Roles at the AssociationEnds must be named.";
	private static final String ONLY_SINGE_INHERIT = "The model has multiple inheritance structures. ";

	/**
	 * The type of the transformations in model.
	 */
	public static String in_type = "pivotmodel";
	/**
	 * The type of the transformations out model.  
	 */
	public static String out_type = "cwm";
	
	
	private IOcl2DeclSettings ocl2DeclSettings;
	
	/* Factory that is used to create model elements in the target model */
	private RelationalPackage elementPackage;
				
	/* Stores the Table to which the attributes and associations of a specific class will be mapped */
	private Map<String, Table> typename2table;
	
	/* Stores all FKs that where created for a specific class from the associations mapping */
	private Map<String, List<String>> typename2fknames;
	
	private PivotModelAnalyser pivotModelAnalyser;
	
	private int assTabCounter = 0;
	
	
	public Pivot2CwmImpl() {
		super();
	}
	
	/**
	 * The constructor for a Uml2Cwm transformation.
	 * @param modelInName The name for the in model.
	 * @param modelOutName The name for the out model.
	 * @throws Exception
	 */
	public Pivot2CwmImpl(String modelInName, String modelOutName) throws Exception {
		super(modelInName, modelOutName, out_type);
		elementPackage = RelationalPackageImpl.init();
		this.setParameterOUT((Schema) elementPackage.getEFactoryInstance().create(elementPackage.getSchema()));
	}
	
	public void invoke() throws InvalidModelException, TransformationException {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Starting Pivot to CWM Transformation");
			String settings = "TablePrefix:"+this.ocl2DeclSettings.getTablePrefix()+"\n";
			settings += "ObjectViewPrefix:"+this.ocl2DeclSettings.getObjectViewPrefix()+"\n";
			settings += "AssociationTablePrefix:"+this.ocl2DeclSettings.getAssociationTablePrefix()+"\n";
			settings += "PrimaryKeyPrefix:"+this.ocl2DeclSettings.getPrimaryKeyPrefix()+"\n";			
			settings += "ForeignKeyPrefix:"+this.ocl2DeclSettings.getForeignKeyPrefix()+"\n";
			settings += "Modus:"+this.ocl2DeclSettings.getModus() + "(1 =typed, 2=vertical)";
			LOGGER.debug(settings);
		}
		this.pivotModelAnalyser = new PivotModelAnalyser(model_in);
		
		/** HELPERS **/
		
		typename2table = new HashMap<String, Table>();
		typename2fknames = new HashMap<String, List<String>>();
		
	
		/** INVOKE MAPPINGS **/
		Set<Type> types = pivotModelAnalyser.getInstancesOfType(Type.class);
				
		for(Type type : types) {
			map_type2table(type);
			typename2fknames.put(type.getName(), new ArrayList<String>());
		}
		
		
		Set<Property> properties = pivotModelAnalyser.getInstancesOfType(Property.class);
		for(Property property : properties) {
			map_property2column(property);
		}
		
		Set<AssociationProperty> filterAssociation = new HashSet<AssociationProperty>();
		Set<AssociationProperty> associations = pivotModelAnalyser.getInstancesOfType(AssociationProperty.class);
		for(AssociationProperty association : associations) {
			if (filterAssociation.contains(association)) continue;
			map_property2keyRelationship(association);
			filterAssociation.addAll(association.getInverseAssociationProperties());
		}
		
	
		for(Type type : types) {
			map_type2view(type);
		}
			
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Finished pivot to CWM transformation");
		}
	}

	public void setSettings(IOcl2CodeSettings ocl2CodeSettings) {
		if (ocl2CodeSettings instanceof IOcl2DeclSettings) {
			this.ocl2DeclSettings = (IOcl2DeclSettings)ocl2CodeSettings;
		}
		
	}
	
	private String getUniqueAssTabName() {
		assTabCounter++;
		return "ASSTAB" + assTabCounter;
	}

	
	private void map_type2table(Type type) throws InvalidModelException {
		if(this.ocl2DeclSettings.getModus() == Ocl2DeclSettings.MODUS_TYPED) {
			map_type2table_typed(type);
		} else if(this.ocl2DeclSettings.getModus() == Ocl2DeclSettings.MODUS_VERTICAL) {
			map_type2table_vertical(type);
		}
	}
	
		
	private void map_type2view(Type type) throws InvalidModelException {
		if(this.ocl2DeclSettings.getModus() == IOcl2DeclSettings.MODUS_TYPED) {
			map_type2view_typed(type);
		}
		if(this.ocl2DeclSettings.getModus() == IOcl2DeclSettings.MODUS_VERTICAL) {
			map_type2view_vertical(type);
		}
	}

	private void map_property2column(Property property) throws TransformationException {
		/** INITIALISATION PHASE **/
		if (!(property instanceof AssociationProperty)) {
			String name = property.getName();
			Type type = property.getType();
			Type owningType = (Type) property.getOwner();
			Table owningTable = typename2table.get(owningType.getName());
		
			/** MAPPING PHASE**/
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Mapping attribute " + property.getName() + " to a column of type " +type.getName() + " in Table " + owningTable.getName());
			}
			
			if(type instanceof PrimitiveType) {
				create_Column(name,owningTable,create_SQLSimpleType(type.getName()));					
			}
			else if(type instanceof CollectionType) {
				create_Column(name,owningTable,create_SQLSimpleType("String"));	
			}
			else {
				throw new TransformationException(UNKNOWN_TYPE + property.getName()+ " : " + type, this);
			}
		}
	}
		
	private void map_property2keyRelationship(AssociationProperty property) throws InvalidModelException {
		/** TRACING **/
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Mapping association " + property.getName() + " to a KeyRelationship");
		}
			
		/** INITIALISATION PHASE**/
		
		String assName = property.getName();
		if (assName == null) assName = getUniqueAssTabName();
		
		String assTableName = this.ocl2DeclSettings.getAssociationTablePrefix() + assName;
		
		Type tA = property.getType();
		String nameA = property.getName();
		Property pB = ((AssociationProperty)property).getInverseAssociationProperties().get(0);
		Type tB = pB.getType();
		String nameB = pB.getName();
			
		if (nameA == null || nameA.equals("")) {
			throw new InvalidModelException(ASS_ROLE + "[" + assName + ", " +tA.getName() + "]", this.model_in, this);
		}

		if (nameB == null || nameB.equals("")) {
			throw new InvalidModelException(ASS_ROLE + "[" + assName + ", " +tB.getName() + "]", this.model_in, this);
		}

		Type typeA = null;
		if(!(pivotModelAnalyser.instanceIsOfType(tA, Type.class))) {
			throw new InvalidModelException(PART_CLASS +"[" + assName + ", " +tA.getName() + "]", this.model_in, this);
		}
		else {
			if (!pivotModelAnalyser.instanceIsOfType(tA, CollectionType.class)) {
				typeA = (Type) tA;
			} else {
				typeA = ((CollectionType) tA).getElementType();
				nameA = typeA.getName();
			}
		}
		Type typeB = null;
		if(!(pivotModelAnalyser.instanceIsOfType(tB, Type.class))) {
			throw new InvalidModelException(PART_CLASS +"[" + assName + ", " +tB.getName() + "]", this.model_in, this);
		}
		else {
			if (!pivotModelAnalyser.instanceIsOfType(tB, CollectionType.class)) {
				typeB = (Type) tB;
			} else {
				typeB = ((CollectionType) tB).getElementType();
				nameB = typeB.getName();
			}
		}
				
		/** MAPPING PHASE**/
	
		if(!pivotModelAnalyser.isMultiple(property) && !pivotModelAnalyser.isMultiple(pB)) {
		    	map_1to1_Association2KeyRelationship(typeA, nameA, typeB, nameB);
	    }
		else if(pivotModelAnalyser.isMultiple(property) && !pivotModelAnalyser.isMultiple(pB)) {
	    	map_1toN_Association2KeyRelationship(typeB, nameB, typeA, nameA);
	    }
		else if(!pivotModelAnalyser.isMultiple(property) && pivotModelAnalyser.isMultiple(pB)) {
	    	map_1toN_Association2KeyRelationship(typeA, nameA, typeB , nameB);
	    }
	    else if(pivotModelAnalyser.isMultiple(property) && pivotModelAnalyser.isMultiple(pB)) {
	    	map_MtoN_Association2KeyRelationship(typeA, nameA, typeB, nameB, assTableName);
	    }
	}
	
	private void map_1to1_Association2KeyRelationship(Type typeA, String nameA, Type typeB, String nameB) throws InvalidModelException {
	
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Mapping 1 to 1 Association between " + typeA.getName() + " and " + 
					typeB.getName() + " by putting a Foreign Key to the PrimaryKey of each side into the opposite site");
		}

		/** INITIALISATION PHASE **/
		Table tA = typename2table.get(typeA.getName());
		Table tB = typename2table.get(typeB.getName());
		PrimaryKey pkA = query_pkForTable(tA);
		PrimaryKey pkB = query_pkForTable(tB);
		
		/** MAPPING PHASE**/
		create_FK(pkA, nameA, typeB);
		create_FK(pkB, nameB, typeA);
	}

	private void map_1toN_Association2KeyRelationship(Type typeA, String nameA, Type typeB, String nameB) throws InvalidModelException {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Mapping 1 to N Association between " + typeA.getName() + " and " + 
					typeB.getName() + " by putting a Foreign Key to the PrimaryKey of the persistent table of" +
					" Type " + typeA.getName() + " in the persistent table of " + typeB.getName());
		}

		/** INITIALISATION PHASE **/
		Table tA = typename2table.get(typeA.getName());
		PrimaryKey pkA = query_pkForTable(tA);
		
		/** MAPPING PHASE**/
		create_FK(pkA, nameA, typeB);
	}
	
	private void map_MtoN_Association2KeyRelationship(Type typeA, String nameA, Type typeB, String nameB, String assTableName) throws InvalidModelException {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Mapping M to N Association between " + typeA.getName() + " and " + 
					typeB.getName() + " by putting creating an association table that holds ForeignKeys to "
					+ " the both PrimaryKeys of the persistent tables for the Types " + typeA.getName() +" and " +typeB.getName() );
		}

		/** INITIALISATION PHASE **/
		Table tA = typename2table.get(typeA.getName());
		PrimaryKey pkA = query_pkForTable(tA);
		Table tB = typename2table.get(typeB.getName());
		PrimaryKey pkB = query_pkForTable(tB);
		
		/** MAPPING PHASE**/
		Table asTable = create_Table(assTableName);
		create_FK(pkA, nameA, asTable);
		create_FK(pkB, nameB, asTable);
	}
	
	/** typed MAPPING **/
	
	private void map_type2table_typed(Type type) {
		/** INITIALISATION PHASE **/
		String typename =  type.getName();
		String tablename = this.ocl2DeclSettings.getTablePrefix() + typename;
		String pkname = this.ocl2DeclSettings.getPrimaryKeyPrefix() + typename;
		String typeColumnName = "type";
		List<Type> superTypes = query_supertypesForType(type, true);
		boolean hasSuperTypes = superTypes.size() > 0;
		Set<Type> subTypes = query_subtypesForType(type);
		boolean hasSubTypes = subTypes.size() > 0;
		
		/** MAPPING PHASE**/
		if(!hasSuperTypes) { // if generalisation root is found the persitent table can be created
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Mapping Type " + typename + " to persitent table " + tablename +
							" because it is the root of a generalisation hierarchy.");
			}
			Table table = create_Table(tablename);
			typename2table.put(typename, table);
			
			create_PrimaryKey(pkname,table);
			
			if (hasSubTypes) { // if subTypes exists the must be a column to distinct the different object types
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Creating a type column to discint subtypes of Type " + typename + 
							"in persistent table " + tablename + "." );
				}
				
				create_Column(typeColumnName,table,create_SQLSimpleType("String"));

			}
			
			// all subTypes will be mapped as a view to the same persitent table
			for(Type subType : subTypes) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Set persistent table " + tablename + " as storage table for UmlClass " + subType.getName() );
				}
				typename2table.put(subType.getName(), table);
			}		
					
		}
		
	}

	private void map_type2view_typed(Type type) throws InvalidModelException {
		List<Type> superTypes = query_supertypesForType(type, true);
		boolean hasSuperTypes = superTypes.size() > 0;
		String classname =  type.getName();
		String viewname = this.ocl2DeclSettings.getObjectViewPrefix() + classname;
		String pkname = query_pkNameForType(type);
		Set<String> persistentTables = new HashSet<String>();
		SortedSet<String> columns = new TreeSet<String>();
		SortedSet<String> pks = new TreeSet<String>();
		SortedSet<String> fks = new TreeSet<String>();
	
		// also consider the actual class when constructing the view
		superTypes.add(type);
		
		pks.add(pkname);
		
		// The view contains all attributes and associations of supeTypes and the actual class and
		// the primary key of the generalisation root.
		for (Type forType : superTypes) {
			//if (clazz instanceof OclAny)
			Table table = typename2table.get(classname);
			String tableName = table.getName();
			for(Property a : query_propertyForType(forType)){
				// add attributes of superTypes
				columns.add(tableName + "." + a.getName() + " as " + a.getName());
			}
			for (String fk : typename2fknames.get(forType.getName())) {
				// add fks for associations and attributes that refer to internal types
				fks.add(fk);
			}
			
			// add pk of generalisation root
			persistentTables.add(tableName);
		}
		
							
		/** TRACING **/
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug( "Create an object view "+ viewname + " for Type " + type.getName());
		}

		List<String> allColumns = new ArrayList<String>();
		allColumns.addAll(pks);
		allColumns.addAll(columns);
		allColumns.addAll(fks);
		
		ITemplate body = ocl2DeclSettings.getTemplateGroup().getTemplate("selectStmt");
		
		String columnstring = TemplateHelper.getValuesCommaSeparated(allColumns);
		String tableString = TemplateHelper.getValuesCommaSeparated(persistentTables);
		
		body.setAttribute("columns", columnstring);
		body.setAttribute("tables", tableString);
		if (hasSuperTypes) {
			body.setAttribute("where", "type=\""+type.getName()+"\"");
		}
		
		create_View(viewname,body.toString());

	}

	/** vertical MAPPING *
	 * @throws InvalidModelException */

	private void map_type2table_vertical(Type type) throws InvalidModelException {
		/** INITIALISATION PHASE **/
		String classname =  type.getName();
		String tablename = this.ocl2DeclSettings.getTablePrefix() + classname;
		String pkname = query_pkNameForType(type);
		List<Type> directSuperClass = query_supertypesForType(type, false);
		boolean hasDirectSuperClass = directSuperClass.size() > 0;
		
		/** MAPPING PHASE**/
		if (typename2table.get(classname) == null) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Mapping UmlClass " + classname + " to persitent table " + tablename);
			}
		
			Table table = create_Table(tablename);
			typename2table.put(classname, table);
			
			create_PrimaryKey(pkname,table);
	
			if(hasDirectSuperClass) {
				if (directSuperClass.size() > 1) {
					throw new InvalidModelException(ONLY_SINGE_INHERIT + type.getName(), this.model_in, this);
				}
				else {
					Type superClass = (Type) directSuperClass.toArray()[0];
					map_type2table_vertical(superClass);
					Table st = typename2table.get(superClass.getName());
					PrimaryKey stpk = query_pkForTable(st);
					
					create_ForeignKey(pkname,table,stpk);

				}
			}
		}
			
		
		
		
	}
	
	private void map_type2view_vertical(Type type) throws InvalidModelException {

		List<Type> directSuperType = query_supertypesForType(type, false);
		boolean hasDirectSuperType = directSuperType.size() > 0;
		String classname =  type.getName();
		String viewname = this.ocl2DeclSettings.getObjectViewPrefix() + classname;
		
		Set<String> persistentTables = new HashSet<String>();
		SortedSet<String> columns = new TreeSet<String>();
		SortedSet<String> pks = new TreeSet<String>();
		SortedSet<String> fks = new TreeSet<String>();

		Table tab = typename2table.get(type.getName());
		PrimaryKey pk = query_pkForTable(tab);
		pks.add(tab.getName() + "." + pk.getName());
		
		// also consider the actual class when constructing the view
		Table subClassTable = typename2table.get(type.getName());
		String subTypeTableName = subClassTable.getName();
		for(Property a : query_propertyForType(type)){
			// add attributes
			columns.add(subTypeTableName + "." + a.getName() + " as " + a.getName());
		}
		for (String fk : typename2fknames.get(type.getName())) {
			// add fks for associations
			fks.add(subTypeTableName + "." + fk + " as " + fk);
		}
		persistentTables.add(subTypeTableName);

		
		// The view contains all attributes and associations of supeTypes and
		// the primary key of the generalisation root.
		String whereString = "";
		while(directSuperType.size() > 0) {
			if (directSuperType.size()>1) {
				throw new InvalidModelException(ONLY_SINGE_INHERIT + type.getName(), this.model_in, this);
			}
			else {
				Type superType = (Type) directSuperType.toArray()[0];
				Table table = typename2table.get(superType.getName());
				String tableName = table.getName();
				for(Property a : query_propertyForType(superType)){
					// add attributes of superTypes
					columns.add(tableName + "." + a.getName() + " as " + a.getName());
				}
				for (String fk : typename2fknames.get(superType.getName())) {
					// add fks for associations of superclass
					fks.add(tableName + "." + fk + " as " + fk);
				}
				// add where statement to join this class with its superclass over the generalisation key
				String pkname = query_pkForTable(table).getName();
				String ref_name = pkname;
				
				whereString += subTypeTableName+"." + ref_name + "=" + tableName + "." + pkname;
				persistentTables.add(tableName);
				
				// query next superclass
				directSuperType = query_supertypesForType(superType, false);
				subTypeTableName = tableName;
				if(directSuperType.size()>0) {
					whereString += " AND ";
				}
			}
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Create an object view "+ viewname + " for UmlClass " + type.getName());
		}
		
		List<String> allColumns = new ArrayList<String>();
		allColumns.addAll(pks);
		allColumns.addAll(columns);
		allColumns.addAll(fks);
		
		ITemplate body = ocl2DeclSettings.getTemplateGroup().getTemplate("selectStmt");
		String columnstring = TemplateHelper.getValuesCommaSeparated(allColumns);
		String tableString = TemplateHelper.getValuesCommaSeparated(persistentTables);
		body.setAttribute("columns", columnstring);
		body.setAttribute("tables", tableString);
		if (hasDirectSuperType) {
			body.setAttribute("where", whereString);
		}
		
		create_View(viewname,body.toString());
	}
	


	private Table create_Table(String name) {
		Table table = (Table) elementPackage.getEFactoryInstance().create(elementPackage.getTable()); 
		table.setName(name);
		out.getOwnedElement().add(table);
		return table;
	}
	
	private View create_View(String name, String body) {
		View scView = (View) elementPackage.getEFactoryInstance().create(elementPackage.getView());
		scView.setName(name);
		scView.setQueryExpression(create_QueryExpression(body));

		out.getOwnedElement().add(scView);
		
		return scView;
	}
	
	private Column create_Column(String columnName,Table table) {
		Column c = (Column) elementPackage.getEFactoryInstance().create(elementPackage.getColumn());
		c.setName(columnName);
		c.setOwner(table);
		return c;
	}
		
	private Column create_Column(String columnName, Table owningTable, DataType propertyType) {
		Column c = create_Column(columnName,owningTable);
		c.setType(propertyType);
		owningTable.getOwnedElement().add(c);		
		return c;
	}
	
	private PrimaryKey create_PrimaryKey(String pkname, Table table) {
		PrimaryKey pk = (PrimaryKey) elementPackage.getEFactoryInstance().create(elementPackage.getPrimaryKey());
		pk.setName(pkname);
		pk.setNamespace(table);
		table.getOwnedElement().add(pk);
		pk.getFeature().add(create_Column(pkname,table));
		return pk;
	}
	
	private ForeignKey create_ForeignKey(String fkname, Table destTable, PrimaryKey primaryKey) {
		ForeignKey fk = (ForeignKey) elementPackage.getEFactoryInstance().create(elementPackage.getForeignKey());
		fk.setName(fkname);
		fk.setNamespace(destTable);
		fk.setUniqueKey(primaryKey);
		destTable.getOwnedElement().add(fk);
		return fk;
	}
	
	private QueryExpression create_QueryExpression(String body) {
		DatatypesPackage dtp = null;
		for (EPackage ep : elementPackage.getESubpackages()) {
			if (ep instanceof DatatypesPackage) {
				dtp = (DatatypesPackage)ep;
				break;
			}
		}
		QueryExpression qE = (QueryExpression) dtp.getEFactoryInstance().create(dtp.getQueryExpression());
		qE.setBody(body);
		return qE;	
	}
	
	private SQLSimpleType create_SQLSimpleType(String name) {
		SQLSimpleType dataType = (SQLSimpleType) elementPackage.getEFactoryInstance().create(elementPackage.getSQLSimpleType()); 
		dataType.setName(name);
		return dataType;
	}
	

	private ForeignKey create_FK(PrimaryKey associatedPK, String foreignName, Table destTable) {
		String fkname = this.ocl2DeclSettings.getForeignKeyPrefix() + foreignName;
		
		ForeignKey fk = create_ForeignKey(fkname,destTable,associatedPK);
		Column fkCol = create_Column(fkname,destTable);
		fk.getFeature().add(fkCol);
		
		return fk;
	}


	private void create_FK(PrimaryKey associatedPK, String foreignName, Type destType) {
		Table destTable = typename2table.get(destType.getName());
		ForeignKey fk = create_FK(associatedPK, foreignName, destTable);
		typename2fknames.get(destType.getName()).add(fk.getName());
	}
				
	private List<Type> query_supertypesForType(Type type, boolean recurse) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Query all subtypes for Type: " + type.getName());
		}		
		return pivotModelAnalyser.query_supertypesForType(type, recurse);
	}
	
	private Set<Type> query_subtypesForType(Type type) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Query all subtypes for Type: " + type.getName());
		}
		return pivotModelAnalyser.query_subtypesForType(type);
	}
	
	private Set<Property> query_propertyForType(Type type) {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Query all attributes for Type: " + type.getName());
		}
			
		Set<Property> properties = new HashSet<Property>();
		for (Property prop : pivotModelAnalyser.getInstancesOfType(type.allProperties(),Property.class)) {
			if (!(prop instanceof AssociationProperty)) properties.add(prop);
		}
		return properties;
	}
	
	private String query_nameOfGenroot(Type type) throws InvalidModelException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Query the name of genroot for Type: " + type.getName());
		}
		
		try {
			return pivotModelAnalyser.query_nameOfGenroot(type);
		} catch (InvalidModelException ime) {
			throw new InvalidModelException(ime.getMessage(),model_in,this);
		}
	
	}

	private String query_pkNameForType(Type type) throws InvalidModelException {
		return this.ocl2DeclSettings.getPrimaryKeyPrefix() + query_nameOfGenroot(type);
	}
	
	private PrimaryKey query_pkForTable(Table table) throws InvalidModelException {
		Set<PrimaryKey> pks = pivotModelAnalyser.getInstancesOfType(table.getOwnedElement(), PrimaryKey.class);
		if(pks.size() > 1) {
			throw new InvalidModelException(ONE_PK  + "[" + table.getName() + "]", this.out, this);
		}
		return (PrimaryKey) pks.toArray()[0];
	}


}
