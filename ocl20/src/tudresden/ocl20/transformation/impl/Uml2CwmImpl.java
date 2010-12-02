/*
 * Created on 26.01.2006
 *
 */
package tudresden.ocl20.transformation.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import tudresden.ocl20.codegen.decl.template.Template;
import tudresden.ocl20.codegen.decl.template.TemplateEngine;
import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.jmi.cwm.datatypes.QueryExpression;
import tudresden.ocl20.core.jmi.cwm.keysindexes.KeyRelationshipFeatures;
import tudresden.ocl20.core.jmi.cwm.keysindexes.UniqueFeature;
import tudresden.ocl20.core.jmi.cwm.relational.Column;
import tudresden.ocl20.core.jmi.cwm.relational.ForeignKey;
import tudresden.ocl20.core.jmi.cwm.relational.PrimaryKey;
import tudresden.ocl20.core.jmi.cwm.relational.RelationalPackage;
import tudresden.ocl20.core.jmi.cwm.relational.SqlsimpleType;
import tudresden.ocl20.core.jmi.cwm.relational.Table;
import tudresden.ocl20.core.jmi.cwm.relational.View;
import tudresden.ocl20.core.jmi.uml15.core.Association;
import tudresden.ocl20.core.jmi.uml15.core.AssociationEnd;
import tudresden.ocl20.core.jmi.uml15.core.Attribute;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.core.GeneralizableElement;
import tudresden.ocl20.core.jmi.uml15.core.Generalization;
import tudresden.ocl20.core.jmi.uml15.core.UmlClass;
import tudresden.ocl20.core.jmi.uml15.datatypes.MultiplicityRange;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.transformation.exception.InvalidModelException;
import tudresden.ocl20.transformation.exception.TemplateException;
import tudresden.ocl20.transformation.exception.TransformationException;
import tudresden.ocl20.transformation.interfaces.M2MTransformation;
import tudresden.ocl20.transformation.interfaces.TConfiguration;
import tudresden.ocl20.transformation.interfaces.TParameter;
import tudresden.ocl20.transformation.interfaces.TraceType;
import tudresden.ocl20.transformation.templates.StringHelper;
import tudresden.ocl20.transformation.templates.StringTemplateEngineAdapter;
import tudresden.ocl20.transformation.util.ElementFactory;
import tudresden.ocl20.transformation.util.Field;
import tudresden.ocl20.transformation.util.ModelAnalyser;

/**
 * The class Uml2CwmImpl realises the transformation of an instance of the Uml Metamodel into an instance of the 
 * CWM Metamodel. The Implementation contains the objectrelational mapping.
 * @author Christian Wende
 *
 */
public class Uml2CwmImpl extends M2MTransformation<Uml15Package, RelationalPackage>{
	
	
	private static final String ONE_PK = "A Table must contain exactly on PrimaryKey. ";
	private static final String UNKNOWN_TYPE = "While mapping an Attribute an unknown datatype was found. ";
	private static final String ASS_2_ENDS = "An Association must have exactly 2 Association Ends. ";
	private static final String PART_CLASS = "The partitipants of an Association must be UmlClasses. ";
	private static final String ASS_ROLE = "The Roles at the AssociationEnds must be named.";
	private static final String ONLY_SINGE_INHERIT = "The model has multiple inheritance structures. ";
	private static final String ERROR_TEMPLATE_FACTORY = "An error occured while loading the SQL Templates";
	private static final String ASS_NAME = "The model contains unnamed Associations.";

	/**
	 * The type of the transformations in model.
	 */
	public static String in_type = MetaModelConst.UML15;
	/**
	 * The type of the transformations out model.  
	 */
	public static String out_type = MetaModelConst.CWM;
	
	
	/**
	 * The configuration parameter for the Foreign Key Prefix.
	 */
	public static TParameter FK_PREFIX;
	/**
	 * The configuration parameter for the Associtaion Table Prefix.
	 */
	public static TParameter ASSOCIATION_TABLE_PREFIX;
	/**
	 * The configuration parameter for the Object View Prefix.
	 */
	public static TParameter OBJECT_VIEW_PREFIX;
	/**
	 * The configuration parameter for the Inheritance Mapping Mode.
	 */
	public static TParameter MAPPING_MODE;
	/**
	 * The configuration parameter for the persitent table prefix.
 	 */
	public static TParameter TABLE_PREFIX;
	/**
	 * The configuration parameter for the Primary Key Prefix
	 */	
	public static TParameter PRIMARY_KEY_PREFIX;
	/**
	 * The configuration parameter for the ddl language used in the definition of views
	 */
	public static TParameter DDL_LANGUAGE;
	
	
	/* Factory that is used to create model elements in the target model */
	private ElementFactory<RelationalPackage> elementFactory; 
				
	/* Stores the Table to which the attributes and associations of a specific class will be mapped */
	private Map<String, Table> classname2table;
	
	/* Stores all FKs that where created for a specific class from the associations mapping */
	private Map<String, List<String>> classname2fknames;
	
	/* A Template Factory to generate SQL String Templates*/
	private TemplateEngine templateFactory;
	
	
	/**
	 * The constructor for a Uml2Cwm transformation.
	 * @param modelInName The name for the in model.
	 * @param modelOutName The name for the out model.
	 * @throws Exception
	 */
	public Uml2CwmImpl(String modelInName, String modelOutName) throws Exception {
		super(modelInName, modelOutName, MetaModelConst.CWM);
	}

	
	
	protected void initRequiredParameters() {
		Set<TParameter> paras = new HashSet<TParameter>();

		OBJECT_VIEW_PREFIX = new TParameter("Object View Prefix", new String[]{"OV_", "O_"}, TParameter.FREE);
		MAPPING_MODE = new TParameter("Mapping Modus", new String[] {"typed", "vertical"}, TParameter.SELECT);
		TABLE_PREFIX = new TParameter("Table Prefix", new String[]{"T_", "PT_"}, TParameter.FREE);
		PRIMARY_KEY_PREFIX = new TParameter("Primary Key Prefix", new String[]{"PK_"}, TParameter.FREE);
		ASSOCIATION_TABLE_PREFIX = new TParameter("Association Table Prefix", new String[]{"ASS_", "A_"}, TParameter.FREE);
		FK_PREFIX = new TParameter("Foreign Key Prefix", new String[]{"FK_"}, TParameter.FREE);
		DDL_LANGUAGE = new TParameter("Destination Language", new String[]{StringTemplateEngineAdapter.POSTGRES, StringTemplateEngineAdapter.ORACLE, StringTemplateEngineAdapter.STANDARD, StringTemplateEngineAdapter.MY_SQL}, TParameter.SELECT);
		
		paras.add(DDL_LANGUAGE);
		paras.add(MAPPING_MODE);
		paras.add(TABLE_PREFIX);
		paras.add(OBJECT_VIEW_PREFIX);
		paras.add(PRIMARY_KEY_PREFIX);
		paras.add(ASSOCIATION_TABLE_PREFIX);
		paras.add(FK_PREFIX);
	
		conf = new TConfiguration(paras);
	}

	
	public void invoke() throws InvalidModelException, TransformationException {
		/** TRACING **/
		trace(TraceType.TRANSFORMATION ,"Starting UML to CWM transformation");
		
		String settings = "\n\n";
		for (TParameter p : conf.getRequiredParameters()) {
			
			settings += "\t\t" + p.getKey() + "::= "+conf.get(p.getKey()) + "\n";
		}
		trace(TraceType.TRANSFORMATION, "Parameter Settings: " + settings + "\n");
		
		/** HELPERS **/
		
		elementFactory = new ElementFactory<RelationalPackage>(model_out, trace);
		classname2table = new HashMap<String, Table>();
		classname2fknames = new HashMap<String, List<String>>();
		
		try {
			templateFactory = new StringTemplateEngineAdapter(conf.get(DDL_LANGUAGE.getKey()));
		} catch (TemplateException e1) {
			throw new TransformationException(ERROR_TEMPLATE_FACTORY, this);
		}
		
		
		/** INVOKE MAPPINGS **/
		Set<UmlClass> classes = query_allClasses();
				
		for(UmlClass umlCls : classes) {
			map_class2table(umlCls);
			classname2fknames.put(umlCls.getName(), new ArrayList<String>());
		}
		
		
		Set<Attribute> attributes = ModelAnalyser.getInstancesOfType(model_in, Attribute.class);
		for(Attribute attribute : attributes) {
			map_attribute2column(attribute); 
		}
	
		
		Set<Association> associations = query_allAssociations();
		for (Association association : associations) {
			map_association2keyRelationship(association);
		}
	
		for(UmlClass umlCls : classes) {
			map_class2view(umlCls);
		}
		
		trace(TraceType.TRANSFORMATION ,"Finished UML to CWM transformation");
	}

	/** QUERY DEFINITIONS **/
	
	private Set<UmlClass> query_allClasses() {
		/** TRACING **/
		trace(TraceType.QUERY, "Query all UmlClass of the input model");

		Set<UmlClass> classes = ModelAnalyser.getInstancesOfType(model_in, UmlClass.class); 
		Set<UmlClass> primitiveClasses = new HashSet<UmlClass>();
		for (UmlClass clazz : classes) {
			if (ModelAnalyser.isPrimitive(clazz.getName())) {
				primitiveClasses.add(clazz);
			}
		}
		classes.removeAll(primitiveClasses);
		return classes;
	}


	private Set<Association> query_allAssociations() {
		/** TRACING **/
		trace(TraceType.QUERY, "Query all associations of input model");
		
		Set<Association> associations = new HashSet<Association>();
	  	associations.addAll(model_in.getCore().getAssociation().refAllOfType());
		return associations;
	}

	private Set<Attribute> query_attributesForClass(UmlClass umlCls) {
		/** TRACING **/
		trace(TraceType.QUERY, "Query all attributes for UmlClass: " + umlCls.getName());

		Set<Attribute> attributes = ModelAnalyser.getInstancesOfType(umlCls.getFeature(), Attribute.class);
		return attributes;
	}

	private Set<UmlClass> query_superclassesForClass(UmlClass umlCls, boolean recurse) {
		/** TRACING **/
		trace(TraceType.QUERY, "Query all superclasses for UmlClass: " + umlCls.getName());

		Set<UmlClass> allSuperclasses = new HashSet<UmlClass>();
		Set<UmlClass> superclasses = ModelAnalyser.getInstancesOfType(umlCls.getParents(), UmlClass.class);
		
		while(superclasses.size()>0) {
			Set<UmlClass> newSuperclasses = new HashSet<UmlClass>();
			for(UmlClass obj : superclasses) {
				allSuperclasses.add(obj);
				if(recurse) {
					newSuperclasses.addAll( ModelAnalyser.getInstancesOfType(obj.getParents(), UmlClass.class));
				}
			}
			superclasses = newSuperclasses;
		}
		return allSuperclasses;
	}
	
	private Set<UmlClass> query_subclassesForClass(UmlClass umlCls) {
		/** TRACING **/
		trace(TraceType.QUERY, "Query all subclasses for UmlClass: " + umlCls.getName());

		Set<UmlClass> allSubclasses = new HashSet<UmlClass>();
		Set<UmlClass> subclasses = new HashSet<UmlClass>();
		Collection<Generalization> allSpecialisations = this.model_in.getCore().getAParentSpecialization().getSpecialization(umlCls);
		for(Generalization obj : allSpecialisations) {
			GeneralizableElement element = obj.getChild();
			if(!element.getNameA().equals("OclVoid")) {
				if(ModelAnalyser.instanceIsOfType(element, UmlClass.class)) {
					subclasses.add((UmlClass)element);
					allSubclasses.add((UmlClass) element);
				}
			}
		}
		while(subclasses.size()>0) {
			Set<UmlClass> newSubclasses = new HashSet<UmlClass>();
			for(UmlClass cls : subclasses) {
				allSpecialisations = this.model_in.getCore().getAParentSpecialization().getSpecialization(cls);
				for(Generalization obj : allSpecialisations) {
					GeneralizableElement element = obj.getChild();
					if(!element.getNameA().equals("OclVoid")) {
						if(ModelAnalyser.instanceIsOfType(element, UmlClass.class)) {
							newSubclasses.add((UmlClass)element);
							allSubclasses.add((UmlClass) element);
						}
					}
				}
			}
			subclasses = newSubclasses;
		}
		
		return allSubclasses;
	}
	
	private String query_pkNameForClass(UmlClass cls) throws InvalidModelException {
		UmlClass current = cls;
		Set<UmlClass> directsuperclass = query_superclassesForClass(cls, false);
		
		while (directsuperclass.size() > 0) {
			if (directsuperclass.size()>1) {
				throw new InvalidModelException(ONLY_SINGE_INHERIT + cls.getName(), this.model_in, this);
			}
			current = (UmlClass) directsuperclass.toArray()[0];
			directsuperclass = query_superclassesForClass(current, false);
				
			}
		String pkname = conf.get(PRIMARY_KEY_PREFIX.getKey()) + current.getName();
//		System.out.println("Primary key for " + cls.getName() +" is " + pkname);
		return pkname;
	}
	
	
	private PrimaryKey query_pkForTable(Table table) throws InvalidModelException {
		Set<PrimaryKey> pks = ModelAnalyser.getInstancesOfType(table.getOwnedElement(), PrimaryKey.class);
		if(pks.size() > 1) {
			throw new InvalidModelException(ONE_PK  + "[" + table.getName() + "]", this.model_out, this);
		}
		return (PrimaryKey) pks.toArray()[0];
	}
					
	
	/** MAPPING DEFINITION *
	 * @throws InvalidModelException */
	
	private void map_class2table(UmlClass umlCls) throws InvalidModelException {
		if(conf.get(MAPPING_MODE.getKey()).equals("typed")) {
			map_class2table_typed(umlCls);
		}
		if(conf.get(MAPPING_MODE.getKey()).equals("vertical")) {
			map_class2table_vertical(umlCls);
		}
	}
	
		
	private void map_class2view(UmlClass umlCls) throws InvalidModelException {
		if(conf.get(MAPPING_MODE.getKey()).equals("typed")) {
			map_class2view_typed(umlCls);
		}
		if(conf.get(MAPPING_MODE.getKey()).equals("vertical")) {
			map_class2view_vertical(umlCls);
		}
	}

	private void map_attribute2column(Attribute attribute) throws TransformationException {
		/** INITIALISATION PHASE **/
		String name = attribute.getName();
		String type = attribute.getType().getName();
		UmlClass owningClass = (UmlClass) attribute.getOwner();
		Table owningTable = classname2table.get(owningClass.getName());
		
		List<String> collectionTypes = new ArrayList();
		collectionTypes.add("Collection");
		collectionTypes.add("List");
		collectionTypes.add("Set");
		
		
		
		/** TRACING **/
	


		/** MAPPING PHASE**/					
		trace(TraceType.MAPPING, "Mapping attribute " + attribute.getName() + " to a column of type " +type + " in Table " + owningTable.getName());

		if(ModelAnalyser.isPrimitive(type)) {
			SqlsimpleType datatype = elementFactory.createObject(
							SqlsimpleType.class,
							new Field[] {
								new Field("Name", type)
							});
			Column c = elementFactory.createObject(
							Column.class,
							new Field[] {
								new Field("Name", name),
								new Field("Owner", owningTable),
								new Field("Type", datatype)
							});
							
		}
		else if(collectionTypes.contains(type)) {
			System.out.println("Collection TYPE !!!");
		}
		else {
			throw new TransformationException(UNKNOWN_TYPE + attribute.getName()+ " : " + type, this);
		}

	}
	
	private int assTabCounter = 0;
	
	private String getUniqueAssTabName() {
		assTabCounter++;
		return "ASSTAB" + assTabCounter;
	}


	private void map_association2keyRelationship(Association association) throws InvalidModelException {
		/** TRACING **/
		trace(TraceType.MAPPING, "Mapping association " + association.getName() + " to a KeyRelationship");
		
		/** INITIALISATION PHASE**/
		
		String assName = association.getName();
		if (assName == null) assName = getUniqueAssTabName();
		
		String assTableName = conf.get(ASSOCIATION_TABLE_PREFIX.getKey()) + assName;
		List<AssociationEnd> aEnds = association.getConnection();
		if(!(aEnds.size()==2) ) {
			throw new InvalidModelException(ASS_2_ENDS + "[" +assName + "]", this.model_in, this);
		}
		
		Classifier cA =((AssociationEnd) aEnds.toArray()[0]).getParticipant();
		String nameA = ((AssociationEnd) aEnds.toArray()[0]).getName();
		Classifier cB = (UmlClass) ((AssociationEnd) aEnds.toArray()[1]).getParticipant();
		String nameB = ((AssociationEnd) aEnds.toArray()[1]).getName();
		
		if (nameA == null || nameA.equals("")) {
			throw new InvalidModelException(ASS_ROLE + "[" + assName + ", " +cA.getName() + "]", this.model_in, this);
		}

		if (nameB == null || nameB.equals("")) {
			throw new InvalidModelException(ASS_ROLE + "[" + assName + ", " +cB.getName() + "]", this.model_in, this);
		}

		UmlClass classA = null;
		if(!(ModelAnalyser.instanceIsOfType(cA, UmlClass.class))) {
			throw new InvalidModelException(PART_CLASS +"[" + assName + ", " +cA.getName() + "]", this.model_in, this);
		}
		else {
			classA = (UmlClass) cA;
		}
		UmlClass classB = null;
		if(!(ModelAnalyser.instanceIsOfType(cB, UmlClass.class))) {
			throw new InvalidModelException(PART_CLASS +"[" + assName + ", " +cB.getName() + "]", this.model_in, this);
		}
		else {
			classB = (UmlClass) cB;
		}
		MultiplicityRange mulRangeA = (MultiplicityRange) ((AssociationEnd) aEnds.toArray()[0]).getMultiplicity().getRange().toArray()[0];
		MultiplicityRange mulRangeB = (MultiplicityRange) ((AssociationEnd) aEnds.toArray()[1]).getMultiplicity().getRange().toArray()[0];
		
		

		/** MAPPING PHASE**/
	
		if(mulRangeA.getUpper()==1 && mulRangeB.getUpper()==1) {
	    	map_1to1_Association2KeyRelationship(classA, nameA, classB, nameB);
	    }
		else if(mulRangeA.getUpper()==-1 && mulRangeB.getUpper()==1) {
	    	map_1toN_Association2KeyRelationship(classB, nameB, classA, nameA);
	    }
		else if(mulRangeA.getUpper()==1 && mulRangeB.getUpper()==-1) {
	    	map_1toN_Association2KeyRelationship(classA, nameA, classB, nameB);
	    }
	    else if(mulRangeA.getUpper()==-1 && mulRangeB.getUpper()==-1) {
	    	map_MtoN_Association2KeyRelationship(classA, nameA, classB, nameB, assTableName);
	    }
	}

	private void map_1to1_Association2KeyRelationship(UmlClass classA, String nameA, UmlClass classB, String nameB) throws InvalidModelException {
		/** TRACING **/
		trace(TraceType.MAPPING, "Mapping 1 to 1 Association between " + classA.getName() + " and " + 
				classB.getName() + " by putting a Foreign Key to the PrimaryKey of each side into the opposite site");

		/** INITIALISATION PHASE **/
		Table tA = classname2table.get(classA.getName());
		Table tB = classname2table.get(classB.getName());
		PrimaryKey pkA = query_pkForTable(tA);
		PrimaryKey pkB = query_pkForTable(tB);
		
		/** MAPPING PHASE**/
		create_FK(pkA, nameA, classB);
		create_FK(pkB, nameB, classA);
	}

	private void map_1toN_Association2KeyRelationship(UmlClass classA, String nameA, UmlClass classB, String nameB) throws InvalidModelException {
		/** TRACING **/
		trace(TraceType.MAPPING, "Mapping 1 to N Association between " + classA.getName() + " and " + 
				classB.getName() + " by putting a Foreign Key to the PrimaryKey of the persistent table of" +
						" UmlClass " + classA.getName() + " in the persistent table of " + classB.getName());

		/** INITIALISATION PHASE **/
		Table tA = classname2table.get(classA.getName());
		PrimaryKey pkA = query_pkForTable(tA);
		Table tB = classname2table.get(classB.getName());
		PrimaryKey pkB = query_pkForTable(tB);
		
		/** MAPPING PHASE**/
		create_FK(pkA, nameA, classB);
	}
	
	private void map_MtoN_Association2KeyRelationship(UmlClass classA, String nameA, UmlClass classB, String nameB, String assTableName) throws InvalidModelException {
		/** TRACING **/
		trace(TraceType.MAPPING, "Mapping M to N Association between " + classA.getName() + " and " + 
				classB.getName() + " by putting creating an association table that holds ForeignKeys to "
				+ " the both PrimaryKeys of the persistent tables for the UmlClasses " + classA.getName() +" and " +classB.getName() );

		/** INITIALISATION PHASE **/
		Table tA = classname2table.get(classA.getName());
		PrimaryKey pkA = query_pkForTable(tA);
		Table tB = classname2table.get(classB.getName());
		PrimaryKey pkB = query_pkForTable(tB);
		
		/** MAPPING PHASE**/
		Table asTable = elementFactory.createObject(
											Table.class,
											new Field[] {
												new Field("Name", assTableName)
											});
		create_FK(pkA, nameA, asTable);
		create_FK(pkB, nameB, asTable);
	}

	
	private ForeignKey create_FK(PrimaryKey associatedPK, String foreignName, Table destTable) {
		String fkname = conf.get(FK_PREFIX.getKey()) + foreignName;
		
		ForeignKey fk = elementFactory.createObject(
											ForeignKey.class,
											new Field[] {
												new Field("Name", fkname),
												new Field("Namespace", destTable),
												new Field("UniqueKey", associatedPK)
											});
		
		Column fkCol = elementFactory.createObject(
											Column.class,
											new Field[] {
												new Field("Name",fkname),
												new Field("Owner", destTable)
											});
		
		elementFactory.addAssociation("KeysIndexes", KeyRelationshipFeatures.class, fkCol, fk);
		return fk;
	}


	private void create_FK(PrimaryKey associatedPK, String foreignName, UmlClass destClass) {
		/** TRACING **/
		/** INITIALISATION PHASE **/
				Table destTable = classname2table.get(destClass.getName());
				ForeignKey fk = create_FK(associatedPK, foreignName, destTable);
				classname2fknames.get(destClass.getName()).add(fk.getName());
			}
			
			
	

	
	/** typed MAPPING **/
	
	private void map_class2table_typed(UmlClass umlCls) {
		/** INITIALISATION PHASE **/
		String classname =  umlCls.getName();
		String tablename = conf.get(TABLE_PREFIX.getKey()) + classname;
		String pkname = conf.get(PRIMARY_KEY_PREFIX.getKey()) + classname;
		String typeColumName = "type";
		Set<UmlClass> superClasses = query_superclassesForClass(umlCls, true);
		boolean hasSuperClasses = superClasses.size() > 0;
		Set<UmlClass> subClasses = query_subclassesForClass(umlCls);
		boolean hasSubClasses = subClasses.size() > 0;
		
		/** MAPPING PHASE**/
		if(!hasSuperClasses) { // if generalisation root is found the persitent table can be created
			
			trace.addTrace(TraceType.MAPPING, "Mapping UmlClass " + classname + " to persitent table " + tablename +
							" because it is the root of a generalisation hierarchy.");
			
			Table table = elementFactory.createObject(
											Table.class,
											new Field[]{
												new Field("Name", tablename)
											});

			classname2table.put(classname, table);
			
			Column pk_col = elementFactory.createObject(
											Column.class,
											new Field[] {
												new Field("Name", pkname),
												new Field("Owner", table)
											}
											);
			
			if (hasSubClasses) { // if subclasses exists the must be a column to distinct the different object types
				trace.addTrace(TraceType.MAPPING, "Creating a type column to discint subtypes of UmlClass " + classname + 
								"in persistent table " + tablename + "." );
				
				SqlsimpleType datatype = elementFactory.createObject(
						SqlsimpleType.class,
						new Field[] {
							new Field("Name", "String")
						});
			
				Column type_col = elementFactory.createObject(
												Column.class,
												new Field[] {
													new Field("Name", typeColumName),
													new Field("Owner", table),
													new Field("Type", datatype)
												}
												);
			}
			
			PrimaryKey pk = elementFactory.createObject(
											PrimaryKey.class,
											new Field[] {
												new Field("Name", pkname),
												new Field("Namespace",table)
											}
											);
			
			elementFactory.addAssociation("KeysIndexes", UniqueFeature.class, pk_col, pk);
									
			// all subclasses will be mapped as a view to the same persitent table
			for(UmlClass subClass : subClasses) {
				trace.addTrace(TraceType.MAPPING, "Set persistent table " + tablename + " as storage table for UmlClass " + subClass.getName() );
				classname2table.put(subClass.getName(), table);
			}		
					
		}
		
	}

	private void map_class2view_typed(UmlClass umlCls) throws InvalidModelException {
		Set<UmlClass> superClasses = query_superclassesForClass(umlCls, true);
		boolean hasSuperclasses = superClasses.size() > 0;
		String classname =  umlCls.getName();
		String viewname = conf.get(OBJECT_VIEW_PREFIX.getKey()) + classname;
		String pkname = query_pkNameForClass(umlCls);
		Set<String> persistentTables = new HashSet<String>();
		SortedSet<String> columns = new TreeSet<String>();
		SortedSet<String> pks = new TreeSet<String>();
		SortedSet<String> fks = new TreeSet<String>();
	
		// also consider the actual class when constructing the view
		superClasses.add(umlCls);
		
		pks.add(pkname);
		
		// The view contains all attributes and associations of supeclasses and the actual class and
		// the primary key of the generalisation root.
		for (UmlClass clazz : superClasses) {
			Table table = classname2table.get(classname);
			String tableName = table.getName();
			for(Attribute a : query_attributesForClass(clazz)){
				// add attributes of superclasses
				columns.add(tableName + "." + a.getName() + " as " + a.getName());
			}
			for (String fk : classname2fknames.get(clazz.getName())) {
				// add fks for associations and attributes that refer to internal types
				fks.add(fk);
			}
			
			// add pk of generalisation root
			persistentTables.add(tableName);
		}
		
							
		/** TRACING **/
		trace(TraceType.MAPPING, "Create an object view "+ viewname + " for UmlClass " + umlCls.getName());

		List<String> allColumns = new ArrayList<String>();
		allColumns.addAll(pks);
		allColumns.addAll(columns);
		allColumns.addAll(fks);
		
		Template body = templateFactory.getTemplate("selectStmt");
		
		String columnstring = StringHelper.getValuesCommaSeperated(allColumns);
		String tableString = StringHelper.getValuesCommaSeperated(persistentTables);
		
		body.setAttribute("columns", columnstring);
		body.setAttribute("tables", tableString);
		if (hasSuperclasses) {
			body.setAttribute("where", "type=\""+umlCls.getName()+"\"");
		}
		
		QueryExpression qE = elementFactory.createObject(
												"DataTypes",
												QueryExpression.class,
												new Field[]{
													new Field("Body", body.toString())
												});				
		
		View scView = elementFactory.createObject(
											View.class,
											new Field[] {
												new Field("Name", viewname),
												new Field("QueryExpression",qE)
											}
											);
		

	}

	/** vertical MAPPING *
	 * @throws InvalidModelException */

	private void map_class2table_vertical(UmlClass umlCls) throws InvalidModelException {
		/** INITIALISATION PHASE **/
		String classname =  umlCls.getName();
		String tablename = conf.get(TABLE_PREFIX.getKey()) + classname;
		String pkname = query_pkNameForClass(umlCls);
		Set<UmlClass> directSuperClass = query_superclassesForClass(umlCls, false);
		boolean hasDirectSuperClass = directSuperClass.size() > 0;
		
		/** MAPPING PHASE**/
		if (classname2table.get(classname) == null) {
			trace.addTrace(TraceType.MAPPING, "Mapping UmlClass " + classname + " to persitent table " + tablename);
			
			Table table = elementFactory.createObject(
											Table.class,
											new Field[]{
												new Field("Name", tablename)
											});
	
			classname2table.put(classname, table);
			
			Column pk_col = elementFactory.createObject(
											Column.class,
											new Field[] {
												new Field("Name", pkname),
												new Field("Owner", table)
											}
											);
			
			PrimaryKey pk = elementFactory.createObject(
											PrimaryKey.class,
											new Field[] {
												new Field("Name", pkname),
												new Field("Namespace",table)
											}
											);
			
			elementFactory.addAssociation("KeysIndexes", UniqueFeature.class, pk_col, pk);
		
			if(hasDirectSuperClass) {
				if (directSuperClass.size() > 1) {
					throw new InvalidModelException(ONLY_SINGE_INHERIT + umlCls.getName(), this.model_in, this);
				}
				else {
					UmlClass superClass = (UmlClass) directSuperClass.toArray()[0];
					map_class2table_vertical(superClass);
					Table st = classname2table.get(superClass.getName());
					PrimaryKey stpk = query_pkForTable(st);
					//String generalisationFK = "GK_" + stpk.getName();
					ForeignKey fk = elementFactory.createObject(
														ForeignKey.class,
														new Field[] {
															new Field("Name", pkname),
															new Field("Namespace", table),
															new Field("UniqueKey", stpk)
														});
					
//					Column fkCol = elementFactory.createObject(
//							Column.class,
//							new Field[] {
//								new Field("Name", generalisationFK),
//								new Field("Owner", table)
//							});
//
					elementFactory.addAssociation("KeysIndexes", KeyRelationshipFeatures.class, pk_col, fk);
				
				}
			}
		}
			
		
		
		
	}
	
	private void map_class2view_vertical(UmlClass umlCls) throws InvalidModelException {

		Set<UmlClass> directSuperClass = query_superclassesForClass(umlCls, false);
		boolean hasDirectSuperclass = directSuperClass.size() > 0;
		String classname =  umlCls.getName();
		String viewname = conf.get(OBJECT_VIEW_PREFIX.getKey()) + classname;
		
		Set<String> persistentTables = new HashSet<String>();
		SortedSet<String> columns = new TreeSet<String>();
		SortedSet<String> pks = new TreeSet<String>();
		SortedSet<String> fks = new TreeSet<String>();

		Table tab = classname2table.get(umlCls.getName());
		PrimaryKey pk = query_pkForTable(tab);
		pks.add(tab.getName() + "." + pk.getName());
		
		// also consider the actual class when constructing the view
		Table subClassTable = classname2table.get(umlCls.getName());
		String subClassTableName = subClassTable.getName();
		for(Attribute a : query_attributesForClass(umlCls)){
			// add attributes
			columns.add(subClassTableName + "." + a.getName() + " as " + a.getName());
		}
		for (String fk : classname2fknames.get(umlCls.getName())) {
			// add fks for associations
			fks.add(subClassTableName + "." + fk + " as " + fk);
		}
		persistentTables.add(subClassTableName);

		
		// The view contains all attributes and associations of supeclasses and
		// the primary key of the generalisation root.
		String whereString = "";
		while(directSuperClass.size() > 0) {
			if (directSuperClass.size()>1) {
				throw new InvalidModelException(ONLY_SINGE_INHERIT + umlCls.getName(), this.model_in, this);
			}
			else {
				UmlClass superClass = (UmlClass) directSuperClass.toArray()[0];
				Table table = classname2table.get(superClass.getName());
				String tableName = table.getName();
				for(Attribute a : query_attributesForClass(superClass)){
					// add attributes of superclasses
					columns.add(tableName + "." + a.getName() + " as " + a.getName());
				}
				for (String fk : classname2fknames.get(superClass.getName())) {
					// add fks for associations of superclass
					fks.add(tableName + "." + fk + " as " + fk);
				}
				// add where statement to join this class with its superclass over the generalisation key
				String pkname = query_pkForTable(table).getName();
				String ref_name = pkname;
				
				whereString += subClassTableName+"." + ref_name + "=" + tableName + "." + pkname;
				persistentTables.add(tableName);
				
				// query next superclass
				directSuperClass = query_superclassesForClass(superClass, false);
				subClassTableName = tableName;
				if(directSuperClass.size()>0) {
					whereString += " AND ";
				}
			}
		}
		
		/** TRACING **/
		trace(TraceType.MAPPING, "Create an object view "+ viewname + " for UmlClass " + umlCls.getName());

		List<String> allColumns = new ArrayList<String>();
		allColumns.addAll(pks);
		allColumns.addAll(columns);
		allColumns.addAll(fks);
		
		Template body = templateFactory.getTemplate("selectStmt");
		String columnstring = StringHelper.getValuesCommaSeperated(allColumns);
		String tableString = StringHelper.getValuesCommaSeperated(persistentTables);
		body.setAttribute("columns", columnstring);
		body.setAttribute("tables", tableString);
		if (hasDirectSuperclass) {
			body.setAttribute("where", whereString);
		}
		
		QueryExpression qE = elementFactory.createObject(
												"DataTypes",
												QueryExpression.class,
												new Field[]{
													new Field("Body", body.toString())
												});				
		
		View scView = elementFactory.createObject(
											View.class,
											new Field[] {
												new Field("Name", viewname),
												new Field("QueryExpression",qE)
											}
											);
		

	}

}
