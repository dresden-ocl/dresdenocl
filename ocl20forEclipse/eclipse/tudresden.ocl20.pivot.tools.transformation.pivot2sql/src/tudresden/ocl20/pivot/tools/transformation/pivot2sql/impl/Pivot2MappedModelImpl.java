/*
 * Created on 06.02.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.pivotmodel.AssociationProperty;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.Guide;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.M2XTransformation;
import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.Pivot2SqlPlugin;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.mapping.MappedClassImpl;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.mapping.MappedModelImpl;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.util.PivotModelAnalyser;

/**
 * The class Uml2MappedModelImpl implements the transformation of an instance of
 * the Uml Metamodel to a MappedModel.
 * 
 * @author Christian Wende
 * 
 */
public class Pivot2MappedModelImpl extends
		M2XTransformation<Namespace, IOcl2DeclSettings, IMappedModel> implements
		ITransformation<Namespace, IOcl2DeclSettings, IMappedModel> {

	private Logger LOGGER = Pivot2SqlPlugin
			.getLogger(Pivot2MappedModelImpl.class);

	private static final String PART_CLASS =
			"The partitipants of an Association must be Types.";

	private static final String ASS_ROLE =
			"The Roles at the AssociationEnds must be named.";

	private PivotModelAnalyser pivotModelAnalyser;

	/**
	 * The constructor for a Uml2MappdedModel transformation.
	 * 
	 * @param modelInName
	 *          The name of the in model.
	 * @param outname
	 *          The name of the out model.
	 * @throws ModelAccessException
	 * @throws Exception
	 */
	public Pivot2MappedModelImpl(String modelInName, String outname) {

		super(modelInName, outname);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("init() - start");
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("init() - stop");
		}
	}

	@Override
	public void invoke() throws InvalidModelException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Starting pivot to mappedmodel transformation");
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

		this.pivotModelAnalyser = new PivotModelAnalyser(model_in);

		this.settings.setMappedModel(new MappedModelImpl());

		Set<Type> types = pivotModelAnalyser.getInstancesOfType(Type.class);
		for (Type type : types) {
			if (pivotModelAnalyser.isPrimitive(type))
				continue;
			map_Type2MappedClass(type);
		}
		Set<AssociationProperty> filterAssociation =
				new HashSet<AssociationProperty>();
		Set<AssociationProperty> associations = query_allAssociations();
		for (AssociationProperty association : associations) {
			if (filterAssociation.contains(association))
				continue;
			map_association2guide(association);
			filterAssociation.addAll(association.getInverseAssociationProperties());
		}

		this.out = this.settings.getMappedModel();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Stop Transformation");
		}

	}

	private Set<Type> query_subtypesForType(Type type) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Query all subtypes for Type: " + type.getName());
		}

		return pivotModelAnalyser.query_subtypesForType(type);
	}

	private Set<AssociationProperty> query_allAssociations() {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Query all associations of input model");
		}

		return pivotModelAnalyser.getInstancesOfType(AssociationProperty.class);
	}

	private List<Property> query_propertiesForType(Type type) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Query all attributes for Type: " + type.getName());
		}

		List<Property> properties = type.allProperties();
		return properties;
	}

	private String query_pkNameForType(Type type) throws InvalidModelException {

		String pkname =
				this.settings.getPrimaryKeyPrefix() + query_nameOfGenroot(type);
		return pkname;
	}

	private String query_nameOfGenroot(Type type) throws InvalidModelException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Query the name of genroot for Type: " + type.getName());
		}

		try {
			return pivotModelAnalyser.query_nameOfGenroot(type);
		} catch (InvalidModelException ime) {
			throw new InvalidModelException(ime.getMessage(), model_in, this);
		}

	}

	private void map_Type2MappedClass(Type type) throws InvalidModelException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Mapping of Type: " + type.getName());
		}

		String typename = type.getName();
		String pkname = query_pkNameForType(type);
		String ovname = this.settings.getObjectViewPrefix() + typename;
		List<Property> attributes = query_propertiesForType(type);
		Set<Type> subtypes = query_subtypesForType(type);

		MappedClassImpl mc = accessMappedClass(type);

		for (Property attribute : attributes) {
			if (attribute instanceof AssociationProperty)
				continue;
			Guide ag = new Guide(false);
			ag.add(attribute.getName(), ovname, pkname);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Creating Attribute Guide for Attribute: "
						+ attribute.getName() + " in MappedClass " + mc.getName() + "\n"
						+ ag);
			}

			mc.addAttributeGuide(attribute.getName(), ag);

			for (Type subType : subtypes) {
				String typename_sub = subType.getName();
				String ovname_sub = this.settings.getObjectViewPrefix() + typename_sub;

				MappedClassImpl sub = accessMappedClass(subType);

				Guide ag_sub = new Guide(false);
				ag_sub.add(attribute.getName(), ovname_sub, pkname);

				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Creating Attribute Guide for Attribute: "
							+ attribute.getName() + " in MappedClass " + sub.getName() + "\n"
							+ ag_sub);
				}

				sub.addAttributeGuide(attribute.getName(), ag_sub);

			}
		}
	}

	private MappedClassImpl accessMappedClass(Type type)
			throws InvalidModelException {

		String typename = type.getName();
		String pkname = query_pkNameForType(type);
		String ovname = this.settings.getObjectViewPrefix() + typename;

		MappedClassImpl mc;

		if (this.settings.getMappedModel().isClass(typename)) {
			mc = (MappedClassImpl) this.settings.getMappedModel().getClass(typename);
		}
		else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Creating MappedClass for Type " + type.getName());
			}

			mc = new MappedClassImpl(typename);
			Guide g = new Guide(false);
			((MappedModelImpl) this.settings.getMappedModel()).addMappedClass(
					typename, mc);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Creating Class Guide for Type " + type.getName() + "\n"
						+ g);
			}

			g.add(pkname, ovname, pkname);
			mc.addClassGuide(g);

		}
		return mc;
	}

	private void map_association2guide(AssociationProperty property)
			throws InvalidModelException {

		String assTableName = settings.getUniqueAssociationTableName(property);

		Type tA = property.getType();
		String nameA = property.getName();

		Property pB = property.getInverseAssociationProperties().get(0);
		Type tB = pB.getType();
		String nameB = pB.getName();

		if (nameA == null || nameA.equals("")) {
			throw new InvalidModelException(ASS_ROLE + "[" + assTableName + ", "
					+ tA.getName() + "]", this.model_in, this);
		}

		if (nameB == null || nameB.equals("")) {
			throw new InvalidModelException(ASS_ROLE + "[" + assTableName + ", "
					+ tB.getName() + "]", this.model_in, this);
		}

		if (pivotModelAnalyser.isMultiple(property)) {
			tA = ((CollectionType) tA).getElementType();
		}

		Type typeA = null;
		if (!(pivotModelAnalyser.instanceIsOfType(tA, Type.class))) {
			throw new InvalidModelException(PART_CLASS + "[" + assTableName + ", "
					+ tA.getName() + "]", this.model_in, this);
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
					+ tB.getName() + "]", this.model_in, this);
		}
		else {
			typeB = (Type) tB;
		}

		/** MAPPING PHASE **/

		if (!pivotModelAnalyser.isMultiple(property)
				&& !pivotModelAnalyser.isMultiple(pB)) {
			map_1to1_Association2Guide(typeA, nameA, typeB, nameB);
		}
		else if (pivotModelAnalyser.isMultiple(property)
				&& !pivotModelAnalyser.isMultiple(pB)) {
			map_1toN_Association2Guide(typeB, nameB, typeA, nameA);
		}
		else if (!pivotModelAnalyser.isMultiple(property)
				&& pivotModelAnalyser.isMultiple(pB)) {
			map_1toN_Association2Guide(typeA, nameA, typeB, nameB);
		}
		else {
			map_MtoN_Association2Guide(typeA, nameA, typeB, nameB, assTableName);
		}

	}

	private void map_1toN_Association2Guide(Type typeB, String nameB, Type typeA,
			String nameA) throws InvalidModelException {

		String pknameA = query_pkNameForType(typeA);
		String ovnameA = this.settings.getObjectViewPrefix() + typeA.getName();
		String fknameB = this.settings.getForeignKeyPrefix() + nameB;

		Set<Type> subtypesA = query_subtypesForType(typeA);
		Set<Type> subtypesB = query_subtypesForType(typeB);

		MappedClassImpl mcA = accessMappedClass(typeA);
		MappedClassImpl mcB = accessMappedClass(typeB);

		Guide a2b = new Guide(true);

		a2b.add(fknameB, ovnameA, pknameA);
		mcA.addAssociationEndGuide(nameB, a2b);
		for (Type subType : subtypesA) {
			MappedClassImpl mcA_sub = accessMappedClass(subType);

			Guide sa2b = new Guide(true);
			String ovnameA_sub =
					this.settings.getObjectViewPrefix() + subType.getName();

			sa2b.add(fknameB, ovnameA_sub, pknameA);
			mcA_sub.addAssociationEndGuide(nameB, sa2b);

		}

		Guide b2a = new Guide(true);
		b2a.add(pknameA, ovnameA, fknameB);
		mcB.addAssociationEndGuide(nameA, b2a);
		for (Type subType : subtypesB) {
			MappedClassImpl mcB_sub = accessMappedClass(subType);

			Guide sb2a = new Guide(true);

			sb2a.add(pknameA, ovnameA, fknameB);
			mcB_sub.addAssociationEndGuide(nameA, sb2a);

		}

	}

	private void map_MtoN_Association2Guide(Type typeA, String nameA, Type typeB,
			String nameB, String assTableName) throws InvalidModelException {

		String pknameA = this.settings.getPrimaryKeyPrefix() + typeA.getName();
		String pknameB = this.settings.getPrimaryKeyPrefix() + typeB.getName();
		String ovnameA = this.settings.getObjectViewPrefix() + typeA.getName();
		String ovnameB = this.settings.getObjectViewPrefix() + typeB.getName();
		String fknameA = this.settings.getForeignKeyPrefix() + nameA;
		String fknameB = this.settings.getForeignKeyPrefix() + nameB;
		Set<Type> subtypesA = query_subtypesForType(typeA);
		Set<Type> subtypesB = query_subtypesForType(typeB);

		MappedClassImpl mcA =
				(MappedClassImpl) ((MappedModelImpl) this.settings.getMappedModel())
						.getClass(typeA.getName());
		MappedClassImpl mcB =
				(MappedClassImpl) ((MappedModelImpl) this.settings.getMappedModel())
						.getClass(typeB.getName());

		Guide a2b = new Guide(true);

		a2b.add(pknameB, ovnameB, pknameB);
		a2b.add(fknameB, assTableName, fknameA);
		a2b.add(pknameA, ovnameA, pknameA);
		mcA.addAssociationEndGuide(nameB, a2b);
		for (Type subType : subtypesA) {
			MappedClassImpl mcA_sub = accessMappedClass(subType);

			Guide sa2b = new Guide(true);
			String ovnameA_sub =
					this.settings.getObjectViewPrefix() + subType.getName();

			sa2b.add(pknameB, ovnameB, pknameB);
			sa2b.add(fknameB, assTableName, fknameA);
			sa2b.add(pknameA, ovnameA_sub, pknameA);
			mcA_sub.addAssociationEndGuide(nameB, sa2b);

		}

		Guide b2a = new Guide(true);

		b2a.add(pknameA, ovnameA, pknameA);
		b2a.add(fknameA, assTableName, fknameB);
		b2a.add(pknameB, ovnameB, pknameB);
		mcB.addAssociationEndGuide(nameA, b2a);
		for (Type subType : subtypesB) {
			MappedClassImpl mcB_sub = accessMappedClass(subType);

			Guide sb2a = new Guide(true);
			String ovnameB_sub =
					this.settings.getObjectViewPrefix() + subType.getName();

			sb2a.add(fknameA, ovnameA, pknameA);
			sb2a.add(fknameA, assTableName, fknameB);
			sb2a.add(pknameB, ovnameB_sub, pknameB);
			mcB_sub.addAssociationEndGuide(nameA, sb2a);
		}
	}

	private void map_1to1_Association2Guide(Type typeA, String nameA, Type typeB,
			String nameB) throws InvalidModelException {

		String pknameA = query_pkNameForType(typeA);
		String pknameB = query_pkNameForType(typeB);
		String ovnameA = this.settings.getObjectViewPrefix() + typeA.getName();
		String ovnameB = this.settings.getObjectViewPrefix() + typeB.getName();
		String fknameA = this.settings.getForeignKeyPrefix() + nameA;
		String fknameB = this.settings.getForeignKeyPrefix() + nameB;
		Set<Type> subtypesA = query_subtypesForType(typeA);
		Set<Type> subtypesB = query_subtypesForType(typeB);

		MappedClassImpl mcA =
				(MappedClassImpl) ((MappedModelImpl) this.settings.getMappedModel())
						.getClass(typeA.getName());
		MappedClassImpl mcB =
				(MappedClassImpl) ((MappedModelImpl) this.settings.getMappedModel())
						.getClass(typeB.getName());

		Guide a2b = new Guide(true);
		a2b.add(fknameB, ovnameA, pknameA);
		mcA.addAssociationEndGuide(nameB, a2b);
		for (Type subType : subtypesA) {
			MappedClassImpl mcA_sub = accessMappedClass(subType);

			Guide sa2b = new Guide(true);

			sa2b.add(fknameB, ovnameA, pknameA);
			mcA_sub.addAssociationEndGuide(nameB, sa2b);

		}

		Guide b2a = new Guide(true);
		b2a.add(fknameA, ovnameB, pknameB);
		mcB.addAssociationEndGuide(nameA, b2a);
		for (Type subType : subtypesB) {
			MappedClassImpl mcB_sub = accessMappedClass(subType);

			Guide sb2a = new Guide(true);

			sb2a.add(fknameA, ovnameB, pknameB);
			mcB_sub.addAssociationEndGuide(nameA, sb2a);

		}

	}

}
