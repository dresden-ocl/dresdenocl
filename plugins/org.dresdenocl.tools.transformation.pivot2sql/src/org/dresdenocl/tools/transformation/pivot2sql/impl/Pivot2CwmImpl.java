/*
 * Created on 26.01.2006
 *
 */
package org.dresdenocl.tools.transformation.pivot2sql.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.pivotmodel.AssociationProperty;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.template.ITemplate;
import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.Transformation;
import org.dresdenocl.tools.transformation.exception.InvalidModelException;
import org.dresdenocl.tools.transformation.exception.TransformationException;
import org.dresdenocl.tools.transformation.pivot2sql.Pivot2SqlPlugin;
import org.dresdenocl.tools.transformation.pivot2sql.util.CwmModelAnalyser;
import org.dresdenocl.tools.transformation.pivot2sql.util.NamedElementComparator;
import org.dresdenocl.tools.transformation.pivot2sql.util.PivotModelAnalyser;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import orgomg.cwm.foundation.datatypes.QueryExpression;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.ForeignKey;
import orgomg.cwm.resource.relational.PrimaryKey;
import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.relational.SQLSimpleType;
import orgomg.cwm.resource.relational.Schema;
import orgomg.cwm.resource.relational.Table;
import orgomg.cwm.resource.relational.View;
import orgomg.cwm.resource.relational.impl.RelationalPackageImpl;

/**
 * The class Pivot2CwmImpl realises the transformation of an instance of the
 * pivotmodel into an instance of the CWM Metamodel. The Implementation contains
 * the objectrelational mapping.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 * 
 */
public class Pivot2CwmImpl extends
		Transformation<Namespace, IOcl2DeclSettings, Catalog> implements
		ITransformation<Namespace, IOcl2DeclSettings, Catalog> {

	private Logger LOGGER = Pivot2SqlPlugin.getLogger(Pivot2CwmImpl.class);

	private static final String ONE_PK =
			"A Table must contain exactly on PrimaryKey. ";
//	private static final String UNKNOWN_TYPE =
//			"While mapping an Attribute an unknown datatype was found. ";
	private static final String PART_CLASS =
			"The partitipants of an Association must be a type. ";
	private static final String ASS_ROLE =
			"The Roles at the AssociationEnds must be named.";
	private static final String ONLY_SINGE_INHERIT =
			"The model has multiple inheritance structures. ";
	
	private static final String typeColumnName = "type";

	/* Factory that is used to create model elements in the target model */
	private RelationalPackage elementPackage;

	/*
	 * Stores the Table to which the attributes and associations of a specific
	 * class will be mapped
	 */
	private Map<String, Table> typename2table;
	
	/*
	 * Stores the schema
	 */
	private Map<String, Schema> schemaname2Schema;

	/*
	 * Stores all FKs that where created for a specific class from the
	 * associations mapping
	 */
	private Map<String, List<ForeignKey>> typename2fknames;
	
	private PivotModelAnalyser pivotModelAnalyser;

	private CwmModelAnalyser cwmModelAnalyser;
	
	/**
	 * The constructor for a Uml2Cwm transformation.
	 * 
	 * @param modelInName
	 *          The name for the in model.
	 * @param modelOutName
	 *          The name for the out model.
	 * @throws ModelAccessException
	 * @throws Exception
	 */
	public Pivot2CwmImpl(String modelInName, String modelOutName) {

		super(modelInName, modelOutName);
		elementPackage = RelationalPackageImpl.init();
		this.setParameterOUT((Catalog) elementPackage.getEFactoryInstance().create(
				elementPackage.getCatalog()));
	}

	public void invoke() throws InvalidModelException, TransformationException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Starting Pivot to CWM Transformation");
			String settings = "TablePrefix:" + this.settings.getTablePrefix() + "\n";
			settings +=
					"ObjectViewPrefix:" + this.settings.getObjectViewPrefix() + "\n";
			settings +=
					"AssociationTablePrefix:" + this.settings.getAssociationTablePrefix()
							+ "\n";
			settings +=
					"PrimaryKeyPrefix:" + this.settings.getPrimaryKeyPrefix() + "\n";
			settings +=
					"ForeignKeyPrefix:" + this.settings.getForeignKeyPrefix() + "\n";
			settings +=
					"Modus:" + this.settings.getModus() + "(1 =typed, 2=vertical)";
			LOGGER.debug(settings);
		}
		this.pivotModelAnalyser = new PivotModelAnalyser(in);
		this.cwmModelAnalyser = new CwmModelAnalyser(out);

		/** CHECK SETTINGS **/
		if (!((this.settings.getModus() == IOcl2DeclSettings.MODUS_TYPED) || (this.settings
				.getModus() == IOcl2DeclSettings.MODUS_VERTICAL))) {
			throw new TransformationException("No modus set.", this);
		}
		if (this.settings.getPrimaryKeyPrefix().equals("")) {
			throw new TransformationException("No primary key prefix set.", this);
		}
		if (this.settings.getPrimaryKeyPrefix().equals(
				this.settings.getForeignKeyPrefix())) {
			throw new TransformationException(
					"Primary Key and Foreign Key prefix equals", this);
		}
		if (this.settings.getTablePrefix().equals(
				this.settings.getObjectViewPrefix())) {
			throw new TransformationException("Table and ObjectView prefix equal",
					this);
		}

		/** HELPERS **/
		schemaname2Schema = new HashMap<String,Schema>();
		typename2table = new HashMap<String, Table>();
		typename2fknames = new HashMap<String, List<ForeignKey>>();

		/** INVOKE MAPPINGS **/
		Set<Type> types = pivotModelAnalyser.getInstancesOfType(Type.class);

		SortedSet<Namespace> namespaces = new TreeSet<Namespace>(new NamedElementComparator());
		for (Type type : types) {
			if (!pivotModelAnalyser.isPrimitive(type)) namespaces.add(type.getNamespace());
		}
		for (Namespace n : namespaces) {
			create_Schema(n.getName());
		}
		
		for (Type type : types) {
			if (pivotModelAnalyser.isPrimitive(type))
				continue;
			map_type2table(type);
			typename2fknames.put(type.getName(), new ArrayList<ForeignKey>());
		}

		Set<Property> properties =
				pivotModelAnalyser.getInstancesOfType(Property.class);
		for (Property property : properties) {
			if (property.getSemantics() == null) map_property2column(property);
		}

		Set<AssociationProperty> filterAssociation =
				new HashSet<AssociationProperty>();
		Set<AssociationProperty> associations =
				pivotModelAnalyser.getInstancesOfType(AssociationProperty.class);
		for (AssociationProperty association : associations) {
			if (filterAssociation.contains(association))
				continue;
			map_associationProperty2keyRelationship(association);
			filterAssociation.addAll(association.getInverseAssociationProperties());
		}

		for (Type type : types) {
			if (!pivotModelAnalyser.isPrimitive(type)) map_type2view(type);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Finished pivot to CWM transformation");
		}
	}
	
	public void setSettings(IOcl2DeclSettings ocl2CodeSettings) {

		this.settings = ocl2CodeSettings;

	}

	private void map_type2table(Type type) throws InvalidModelException {

		if (this.settings.getModus() == IOcl2DeclSettings.MODUS_TYPED) 
			map_type2table_typed(type);
		else if (this.settings.getModus() == IOcl2DeclSettings.MODUS_VERTICAL) 
			map_type2table_vertical(type);
	}

	private void map_type2view(Type type) throws InvalidModelException {

		if (this.settings.getModus() == IOcl2DeclSettings.MODUS_TYPED) {
			map_type2view_typed(type);
		}
		if (this.settings.getModus() == IOcl2DeclSettings.MODUS_VERTICAL) {
			map_type2view_vertical(type);
		}
	}

	private void map_property2column(Property property)
			throws TransformationException, InvalidModelException {

		if (!(property instanceof AssociationProperty || isIdentifier(property)) ) {
			String name = property.getName();
			Type type = property.getType();
			Type owningType = (Type) property.getOwner();
			Table owningTable = typename2table.get(owningType.getName());

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Mapping attribute " + property.getName()
						+ " to a column of type " + type.getName() + " in Table "
						+ owningTable.getName());
			}

			if (isAssociation(property)) {
				map_property2keyRelationship(property, type, owningType);
			}
//			else if (pivotModelAnalyser.isPrimitive(type) || type instanceof CollectionType) {
//				create_TableColumn(name, owningTable, type);
//			}
			else {
				create_TableColumn(name, owningTable, type);
//				throw new TransformationException(UNKNOWN_TYPE + property.getName()
//						+ " : " + type, this);
			}
		}
	}

	private void map_property2keyRelationship(Property property, Type type,
			Type owningType) throws InvalidModelException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("Mapping association "
							+ property.getName()
							+ " between "
							+ owningType.getName()
							+ " and "
							+ type.getName()
							+ " by putting a Foreign Key to the PrimaryKey of each side into the opposite site");
		}

		String name = property.getName();
		if (type instanceof CollectionType) {
			map_MtoN_Association2KeyRelationship(
					((CollectionType) type).getElementType(), ((CollectionType) type)
							.getElementType().getName(), owningType, owningType.getName(),
					settings.getUniqueAssociationTableName(property));
		}
		else {
			map_1toN_Association2KeyRelationship(type, name, owningType, null);
		}
	}

	private void map_associationProperty2keyRelationship(
			AssociationProperty property) throws InvalidModelException {

		/** TRACING **/
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Mapping association " + property.getName()
					+ " to a KeyRelationship");
		}

		/** INITIALISATION PHASE **/

		String assTableName = settings.getUniqueAssociationTableName(property);

		Type tA = property.getType();
		String nameA = property.getName();
		Property pB =
				((AssociationProperty) property).getInverseAssociationProperties().get(
						0);
		Type tB = pB.getType();
		String nameB = pB.getName();

		if (nameA == null || nameA.equals("")) {
			throw new InvalidModelException(ASS_ROLE + "[" + assTableName + ", "
					+ tA.getName() + "]", this.in, this);
		}

		if (nameB == null || nameB.equals("")) {
			throw new InvalidModelException(ASS_ROLE + "[" + assTableName + ", "
					+ tB.getName() + "]", this.in, this);
		}

		if (pivotModelAnalyser.isMultiple(property)) {
			tA = ((CollectionType) tA).getElementType();
		}

		Type typeA = null;
		if (!(pivotModelAnalyser.instanceIsOfType(tA, Type.class))) {
			throw new InvalidModelException(PART_CLASS + "[" + assTableName + ", "
					+ tA.getName() + "]", this.in, this);
		}
		else {
			typeA = (Type) tA;
		}

		if (pivotModelAnalyser.isMultiple(pB)) {
			tB = ((CollectionType) tB).getElementType();
		}

		Type typeB = null;
		if (!(pivotModelAnalyser.instanceIsOfType(tB, Type.class))) {
			throw new InvalidModelException(PART_CLASS + "[" + assTableName + ", "
					+ tB.getName() + "]", this.in, this);
		}
		else {
			typeB = (Type) tB;
		}

		/** MAPPING PHASE **/

		if (!pivotModelAnalyser.isMultiple(property)
				&& !pivotModelAnalyser.isMultiple(pB)) {
			map_1to1_Association2KeyRelationship(typeA, nameA, typeB, nameB);
		}
		else if (pivotModelAnalyser.isMultiple(property)
				&& !pivotModelAnalyser.isMultiple(pB)) {
			map_1toN_Association2KeyRelationship(typeB, nameB, typeA, nameA);
		}
		else if (!pivotModelAnalyser.isMultiple(property)
				&& pivotModelAnalyser.isMultiple(pB)) {
			map_1toN_Association2KeyRelationship(typeA, nameA, typeB, nameB);
		}
		else if (pivotModelAnalyser.isMultiple(property)
				&& pivotModelAnalyser.isMultiple(pB)) {
			map_MtoN_Association2KeyRelationship(typeA, nameA, typeB, nameB,
					assTableName);
		}
	}

	private void map_1to1_Association2KeyRelationship(Type typeA, String nameA,
			Type typeB, String nameB) throws InvalidModelException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("Mapping 1 to 1 Association between "
							+ typeA.getName()
							+ " and "
							+ typeB.getName()
							+ " by putting a Foreign Key to the PrimaryKey of each side into the opposite site");
		}

		/** INITIALISATION PHASE **/
		Table tA = typename2table.get(typeA.getName());
		Table tB = typename2table.get(typeB.getName());
		PrimaryKey pkA = query_pkForTable(tA);
		PrimaryKey pkB = query_pkForTable(tB);

		/** MAPPING PHASE **/
		create_ForeignKey(nameA,tB,pkA);
		create_ForeignKey(nameB,tA,pkB);

	}

	private void map_1toN_Association2KeyRelationship(Type typeA, String nameA,
			Type typeB, String nameB) throws InvalidModelException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("Mapping 1 to N Association between "
							+ typeA.getName()
							+ " and "
							+ typeB.getName()
							+ " by putting a Foreign Key to the PrimaryKey of the persistent table of"
							+ " Type " + typeA.getName() + " in the persistent table of "
							+ typeB.getName());
		}

		/** INITIALISATION PHASE **/
		Table tA = typename2table.get(typeA.getName());
		Table tB = typename2table.get(typeB.getName());
		PrimaryKey pkA = query_pkForTable(tA);

		/** MAPPING PHASE **/
		create_ForeignKey(nameA,tB,pkA);
	}

	private void map_MtoN_Association2KeyRelationship(Type typeA, String nameA,
			Type typeB, String nameB, String assTableName)
			throws InvalidModelException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("Mapping M to N Association between "
							+ typeA.getName()
							+ " and "
							+ typeB.getName()
							+ " by putting creating an association table that holds ForeignKeys to "
							+ " the both PrimaryKeys of the persistent tables for the Types "
							+ typeA.getName() + " and " + typeB.getName());
		}

		/** INITIALISATION PHASE **/
		Table tA = typename2table.get(typeA.getName());
		PrimaryKey pkA = query_pkForTable(tA);
		Table tB = typename2table.get(typeB.getName());
		PrimaryKey pkB = query_pkForTable(tB);

		/** MAPPING PHASE **/
		Table asTable = create_Table(assTableName,typeA);
		create_ForeignKey(nameA,asTable,pkA);
		create_ForeignKey(nameB,asTable,pkB);
	}
	
	/** typed MAPPING **/

	private void map_type2table_typed(Type type) {

		/** INITIALISATION PHASE **/
		String typename = type.getName();
		String tablename = this.settings.getTablePrefix() + typename;
		String pkname = settings.getPrimaryKeyPrefix() + type.getName();
		
		List<Type> superTypes = query_supertypesForType(type, true);
		boolean hasSuperTypes = superTypes.size() > 0;
		Set<Type> subTypes = query_subtypesForType(type);
		boolean hasSubTypes = subTypes.size() > 0;

		/** MAPPING PHASE **/
		if (!hasSuperTypes) { // if generalisation root is found the persitent
			// table
			// can be created
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Mapping Type " + typename + " to persitent table "
						+ tablename
						+ " because it is the root of a generalisation hierarchy.");
			}
			Table table = create_Table(tablename,type);
			typename2table.put(typename, table);

			create_PrimaryKey(pkname, table,type);

			if (hasSubTypes) { // if subTypes exists the must be a column to
				// distinct
				// the different object types
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Creating a type column to discint subtypes of Type "
							+ typename + "in persistent table " + tablename + ".");
				}

				Column c = create_TableColumn(typeColumnName, table, null);
				c.setType(create_SQLSimpleType("String"));

			}

			// all subTypes will be mapped as a view to the same persitent table
			for (Type subType : subTypes) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Set persistent table " + tablename
							+ " as storage table for type " + subType.getName());
				}
				typename2table.put(subType.getName(), table);
			}

		}

	}

	private String getName(Table table) {
		ITemplate template =
				this.settings.getTemplateGroup().getTemplate("createName");
		template.setAttribute("name", table.getName());
		if ((schemaname2Schema.size() > 1) && settings.isSchemaUsing()) template.setAttribute("schema", table.getNamespace().getName());
		return template.toString();
	}
	
	private void map_type2view_typed(Type type) throws InvalidModelException {

		List<Type> superTypes = query_supertypesForType(type, true);
		boolean hasSuperTypes = superTypes.size() > 0;
		String classname = type.getName();
		String viewname = this.settings.getObjectViewPrefix() + classname;
		List<String> persistentTables = new  ArrayList<String>();
		List<String> columns = new ArrayList<String>();

		// also consider the actual class when constructing the view
		superTypes.add(type);

		// if (clazz instanceof OclAny)
		Table table = typename2table.get(classname);
		String tableName = getName(table);
		List<String> props = new ArrayList<String>();
		for (Type forType : superTypes) {
			for(Property prop : query_propertyForType(forType)) {
			if (isAssociation(prop) || isIdentifier(prop)) continue;
			props.add(prop.getName());
			}
		}
		for (Column col : query_ColumnsForTable(table)) {
			if (col.getUniqueKey().size() > 0 || col.getKeyRelationship().size() > 0) columns.add(col.getName());
			else if (props.contains(col.getName()))columns.add(create_Property(tableName, col.getName(), true));
		}
		// add pk of generalisation root
		persistentTables.add(tableName);

		
		
		
		/** TRACING **/
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Create an object view " + viewname + " for Type "
					+ type.getName());
		}

		ITemplate body = settings.getTemplateGroup().getTemplate("selectStmt");

		body.setAttribute("columns", columns);
		body.setAttribute("tables", persistentTables);
		if (hasSuperTypes) {
			ITemplate typeTemplate =
					this.settings.getTemplateGroup().getTemplate("createSubType");
			typeTemplate.setAttribute("type", type.getName());
			body.setAttribute("where", typeTemplate.toString());
		}

		create_View(viewname, body.toString(),type);

	}

	/**
	 * vertical MAPPING *
	 * 
	 * @throws InvalidModelException
	 */

	private void map_type2table_vertical(Type type) throws InvalidModelException {

		/** INITIALISATION PHASE **/
		String classname = type.getName();
		String tablename = this.settings.getTablePrefix() + classname;
		List<Type> directSuperClass = query_supertypesForType(type, false);
		String pkName = settings.getPrimaryKeyPrefix()+query_nameOfGenroot(type);
		boolean hasDirectSuperClass = directSuperClass.size() > 0;

		/** MAPPING PHASE **/
		if (typename2table.get(classname) == null) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Mapping Type " + classname + " to persitent table "
						+ tablename);
			}

			Table table = create_Table(tablename,type);
			typename2table.put(classname, table);

			create_PrimaryKey(pkName,table,type);

			if (hasDirectSuperClass) {
				if (directSuperClass.size() > 1) {
					throw new InvalidModelException(ONLY_SINGE_INHERIT + type.getName(),
							this.in, this);
				}
				else {
					Type superClass = directSuperClass.get(0);
					map_type2table_vertical(superClass);
					Table st = typename2table.get(superClass.getName());
					PrimaryKey stpk = query_pkForTable(st);
					
					create_ForeignSubKey(pkName, table, stpk);
				}
			}
		}

	}

	private void map_type2view_vertical(Type type) throws InvalidModelException {

		List<Type> directSuperType = query_supertypesForType(type, false);
		boolean hasDirectSuperType = directSuperType.size() > 0;
		String classname = type.getName();
		String viewname = this.settings.getObjectViewPrefix() + classname;

		List<String> persistentTables = new ArrayList<String>();
		List<String> columns = new ArrayList<String>();

		// also consider the actual class when constructing the view
		Table subClassTable = typename2table.get(classname);
		String subTypeTableName = getName(subClassTable);
		
		List<String> props = new ArrayList<String>();
		for(Property prop : query_propertyForType(type)) {
			if (isAssociation(prop) || isIdentifier(prop)) continue;
			props.add(prop.getName());
			}
		for (Column col : query_ColumnsForTable(subClassTable)) {
			if (col.getUniqueKey().size() > 0) columns.add(create_Property(subTypeTableName, col.getName(), false));
			else if (props.contains(col.getName()) || col.getKeyRelationship().size() > 0) columns.add(create_Property(subTypeTableName, col.getName(), true));
		}

		persistentTables.add(subTypeTableName);

		// The view contains all attributes and associations of supeTypes and
		// the primary key of the generalisation root.
		String whereString = "";
		while (directSuperType.size() > 0) {
			if (directSuperType.size() > 1) {
				throw new InvalidModelException(ONLY_SINGE_INHERIT + type.getName(),
						this.in, this);
			}
			else {
				Type superType = (Type) directSuperType.toArray()[0];
				Table table = typename2table.get(superType.getName());
				String tableName = getName(table);
				List<String> props2 = new ArrayList<String>();
				for(Property prop : query_propertyForType(superType)) {
					if (isAssociation(prop) || isIdentifier(prop)) continue;
					props2.add(prop.getName());
					}
				for (Column col : query_ColumnsForTable(table)) {
					if (col.getUniqueKey().size() > 0) continue; //columns.add(create_Property(tableName, col.getName(), false));
					else if (props2.contains(col.getName()) || col.getKeyRelationship().size() > 0) columns.add(create_Property(tableName, col.getName(), true));
				}
				// add where statement to join this class with its superclass
				// over the
				// generalisation key
				String pkname = query_pkForTable(table).getName();
				String ref_name = pkname;

				whereString +=
						create_Property(subTypeTableName, ref_name, false) + "="
								+ create_Property(tableName, pkname, false);
				persistentTables.add(tableName);

				// query next superclass
				directSuperType = query_supertypesForType(superType, false);
				subTypeTableName = tableName;
				if (directSuperType.size() > 0) {
					whereString += " AND ";
				}
			}
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Create an object view " + viewname + " for UmlClass "
					+ type.getName());
		}


		ITemplate body = settings.getTemplateGroup().getTemplate("selectStmt");
		body.setAttribute("columns", columns);
		body.setAttribute("tables", persistentTables);
		if (hasDirectSuperType) {
			body.setAttribute("where", whereString);
		}

		create_View(viewname, body.toString(),type);
	}

	private Table create_Table(String name,Type type) {

		Table table =
				(Table) elementPackage.getEFactoryInstance().create(
						elementPackage.getTable());
		table.setName(name);
		table.setNamespace(schemaname2Schema.get(type.getNamespace().getName()));
		return table;
	}

	private View create_View(String name, String body,Type type) {

		View scView =
				(View) elementPackage.getEFactoryInstance().create(
						elementPackage.getView());
		scView.setName(name);
		scView.setQueryExpression(create_QueryExpression(body));
		scView.setNamespace(schemaname2Schema.get(type.getNamespace().getName()));

		return scView;
	}
	
	private void create_Schema(String schemaName) {
		Schema schema = (Schema)elementPackage.getEFactoryInstance().create(elementPackage.getSchema());
		schema.setName(schemaName);
		schema.setNamespace(out);
		schemaname2Schema.put(schemaName, schema);
	}
	
	private Column create_Column(String columnName, Type dataType) {
		Column c = (Column) elementPackage.getEFactoryInstance().create(elementPackage.getColumn());
		c.setName(columnName);
		if (dataType instanceof PrimitiveType) c.setType(create_SQLSimpleType(((PrimitiveType) dataType).getKind().getName()));
		else if (pivotModelAnalyser.isPrimitive(dataType)) c.setType(create_SQLSimpleType(dataType.getName()));
		else if (dataType instanceof CollectionType) {
			c.setType(create_SQLSimpleType(((CollectionType) dataType).getElementType().getName()+" ARRAY"));
		} else {
			c.setType(create_SQLSimpleType("String"));
		}
		return c;
	}

	private Column create_TableColumn(String columnName, Table owningTable,
			Type dataType) {

		Column c = create_Column(columnName,dataType);
		c.setOwner(owningTable);
		return c;
	}
	
	private PrimaryKey create_PrimaryKey(String pkname, Table table,Type type) {

		PrimaryKey pk =
				(PrimaryKey) elementPackage.getEFactoryInstance().create(
						elementPackage.getPrimaryKey());
		pk.setName(pkname);
		pk.setNamespace(table);
		table.getOwnedElement().add(pk);
		for (Property property: type.getIDProperties()) {		
			pk.getFeature().add(create_TableColumn(property.getName(),table,property.getType()));
		}
		if (pk.getFeature().size() == 0) {
			pk.getFeature().add(create_TableColumn(pkname,table,null));
			pk.getFeature().get(0).setType(create_SQLSimpleType("String"));
		}
		return pk;
	}

	private ForeignKey create_ForeignKey(String fkname, Table destTable,
			PrimaryKey primaryKey) { 
		ForeignKey fk =
				(ForeignKey) elementPackage.getEFactoryInstance().create(
						elementPackage.getForeignKey());
		fk.setName(settings.getForeignKeyPrefix()+ fkname);
		fk.setNamespace(destTable);
		fk.setUniqueKey(primaryKey);
		Set<Column> columns = cwmModelAnalyser.getInstancesOfType(primaryKey.getFeature(), Column.class);
		for(Column column : columns) {
			Column c = create_TableColumn(fk.getName()+ (columns.size() > 1 ? "_"+column.getName() : ""), destTable, null);
			c.setType(column.getType());
			c.getKeyRelationship().add(fk);
		}
		return fk;
	}
	
	private ForeignKey create_ForeignSubKey(String fkname, Table destTable,
			PrimaryKey primaryKey) throws InvalidModelException {

		ForeignKey fk =
				(ForeignKey) elementPackage.getEFactoryInstance().create(
						elementPackage.getForeignKey());
		fk.setName(settings.getForeignKeyPrefix()+ fkname);
		fk.setNamespace(destTable);
		fk.setUniqueKey(primaryKey);
		Set<Column> columns = query_ColumnsForTable(destTable);
		for(Column col : columns) {
			if (col.getName().equals(fkname)) col.getKeyRelationship().add(fk);
		}
		return fk;
	}

	private QueryExpression create_QueryExpression(String body) {

		EReference ref = elementPackage.getView_QueryExpression();
		QueryExpression qE = (QueryExpression) ((EPackage) ref.getEReferenceType().eContainer()).getEFactoryInstance().create(
				ref.getEReferenceType());
		qE.setBody(body);
		return qE;
	}

	private SQLSimpleType create_SQLSimpleType(String name) {

		SQLSimpleType dataType =
				(SQLSimpleType) elementPackage.getEFactoryInstance().create(
						elementPackage.getSQLSimpleType());
		if (PivotModelAnalyser.extraPrimitives.containsKey(name)) name = PivotModelAnalyser.extraPrimitives.get(name);
		dataType.setName(name);
		return dataType;
	}

	private String create_Property(String tablename, String columnname,
			boolean asname) {

		ITemplate template =
				this.settings.getTemplateGroup().getTemplate("createProperty");
		template.setAttribute("tablename", tablename);
		template.setAttribute("columnname", columnname);
		template.setAttribute("asname", asname);
		return template.toString();
	}

	private boolean isAssociation(Property property) {

		if (property.getType() instanceof CollectionType) {
			if (typename2table.containsKey(((CollectionType) property.getType())
					.getElementType().getName())) {
				return true;
			}
		}
		else if (typename2table.containsKey(property.getType().getName())) {
			return true;
		}
		return false;
	}
	
	private boolean isIdentifier(Property property) {
		return property.isIdentifier();
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
		for (Property prop : pivotModelAnalyser.getInstancesOfType(
				type.getOwnedProperty(), Property.class)) {
			if (!(prop instanceof AssociationProperty))
				properties.add(prop);
		}
		return properties;
	}
	
	private Set<Column> query_ColumnsForTable(Table table) throws InvalidModelException {
		
		return cwmModelAnalyser.getInstancesOfType(table.getFeature(),
						Column.class);
		
	}
	
	private String query_nameOfGenroot(Type type) throws InvalidModelException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Query the name of genroot for Type: " + type.getName());
		}

		try {
			return pivotModelAnalyser.query_nameOfGenroot(type);
		} catch (InvalidModelException ime) {
			throw new InvalidModelException(ime.getMessage(), in, this);
		}

	}

	private PrimaryKey query_pkForTable(Table table) throws InvalidModelException {

		Set<PrimaryKey> pks =
				cwmModelAnalyser.getInstancesOfType(table.getOwnedElement(),
						PrimaryKey.class);
		if (pks.size() > 1) {
			throw new InvalidModelException(ONE_PK + "[" + table.getName() + "]",
					this.out, this);
		}
		return pks.iterator().next();
	}

}
