/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.transformation.ClassifierFeatureMap;
import orgomg.cwm.analysis.transformation.ClassifierMap;
import orgomg.cwm.analysis.transformation.TransformationPackage;

import orgomg.cwm.foundation.datatypes.DatatypesPackage;
import orgomg.cwm.foundation.datatypes.TypeAlias;

import orgomg.cwm.foundation.expressions.ExpressionNode;
import orgomg.cwm.foundation.expressions.ExpressionsPackage;

import orgomg.cwm.foundation.typemapping.TypeMapping;
import orgomg.cwm.foundation.typemapping.TypemappingPackage;

import orgomg.cwm.objectmodel.behavioral.BehavioralPackage;
import orgomg.cwm.objectmodel.behavioral.Parameter;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.Feature;
import orgomg.cwm.objectmodel.core.StructuralFeature;

import orgomg.cwm.objectmodel.instance.Instance;
import orgomg.cwm.objectmodel.instance.InstancePackage;

import orgomg.cwm.objectmodel.relationships.Generalization;
import orgomg.cwm.objectmodel.relationships.RelationshipsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#getStructuralFeature <em>Structural Feature</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#getGeneralization <em>Generalization</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#getSpecialization <em>Specialization</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#getInstance <em>Instance</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#getAlias <em>Alias</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#getExpressionNode <em>Expression Node</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#getMappingFrom <em>Mapping From</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#getMappingTo <em>Mapping To</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#getClassifierMap <em>Classifier Map</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ClassifierImpl#getCfMap <em>Cf Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ClassifierImpl extends NamespaceImpl implements Classifier {
	/**
	 * The default value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean isAbstract = IS_ABSTRACT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFeature() <em>Feature</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeature()
	 * @generated
	 * @ordered
	 */
	protected EList<Feature> feature;

	/**
	 * The cached value of the '{@link #getStructuralFeature() <em>Structural Feature</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStructuralFeature()
	 * @generated
	 * @ordered
	 */
	protected EList<StructuralFeature> structuralFeature;

	/**
	 * The cached value of the '{@link #getParameter() <em>Parameter</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> parameter;

	/**
	 * The cached value of the '{@link #getGeneralization() <em>Generalization</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneralization()
	 * @generated
	 * @ordered
	 */
	protected EList<Generalization> generalization;

	/**
	 * The cached value of the '{@link #getSpecialization() <em>Specialization</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecialization()
	 * @generated
	 * @ordered
	 */
	protected EList<Generalization> specialization;

	/**
	 * The cached value of the '{@link #getInstance() <em>Instance</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstance()
	 * @generated
	 * @ordered
	 */
	protected EList<Instance> instance;

	/**
	 * The cached value of the '{@link #getAlias() <em>Alias</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeAlias> alias;

	/**
	 * The cached value of the '{@link #getExpressionNode() <em>Expression Node</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionNode()
	 * @generated
	 * @ordered
	 */
	protected EList<ExpressionNode> expressionNode;

	/**
	 * The cached value of the '{@link #getMappingFrom() <em>Mapping From</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappingFrom()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeMapping> mappingFrom;

	/**
	 * The cached value of the '{@link #getMappingTo() <em>Mapping To</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappingTo()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeMapping> mappingTo;

	/**
	 * The cached value of the '{@link #getClassifierMap() <em>Classifier Map</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassifierMap()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassifierMap> classifierMap;

	/**
	 * The cached value of the '{@link #getCfMap() <em>Cf Map</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCfMap()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassifierFeatureMap> cfMap;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassifierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.CLASSIFIER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsAbstract() {
		return isAbstract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsAbstract(boolean newIsAbstract) {
		boolean oldIsAbstract = isAbstract;
		isAbstract = newIsAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CLASSIFIER__IS_ABSTRACT, oldIsAbstract, isAbstract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Feature> getFeature() {
		if (feature == null) {
			feature = new EObjectContainmentWithInverseEList<Feature>(Feature.class, this, CorePackage.CLASSIFIER__FEATURE, CorePackage.FEATURE__OWNER);
		}
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StructuralFeature> getStructuralFeature() {
		if (structuralFeature == null) {
			structuralFeature = new EObjectWithInverseResolvingEList<StructuralFeature>(StructuralFeature.class, this, CorePackage.CLASSIFIER__STRUCTURAL_FEATURE, CorePackage.STRUCTURAL_FEATURE__TYPE);
		}
		return structuralFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Parameter> getParameter() {
		if (parameter == null) {
			parameter = new EObjectWithInverseResolvingEList<Parameter>(Parameter.class, this, CorePackage.CLASSIFIER__PARAMETER, BehavioralPackage.PARAMETER__TYPE);
		}
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Generalization> getGeneralization() {
		if (generalization == null) {
			generalization = new EObjectWithInverseResolvingEList<Generalization>(Generalization.class, this, CorePackage.CLASSIFIER__GENERALIZATION, RelationshipsPackage.GENERALIZATION__CHILD);
		}
		return generalization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Generalization> getSpecialization() {
		if (specialization == null) {
			specialization = new EObjectWithInverseResolvingEList<Generalization>(Generalization.class, this, CorePackage.CLASSIFIER__SPECIALIZATION, RelationshipsPackage.GENERALIZATION__PARENT);
		}
		return specialization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Instance> getInstance() {
		if (instance == null) {
			instance = new EObjectWithInverseResolvingEList<Instance>(Instance.class, this, CorePackage.CLASSIFIER__INSTANCE, InstancePackage.INSTANCE__CLASSIFIER);
		}
		return instance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeAlias> getAlias() {
		if (alias == null) {
			alias = new EObjectWithInverseResolvingEList<TypeAlias>(TypeAlias.class, this, CorePackage.CLASSIFIER__ALIAS, DatatypesPackage.TYPE_ALIAS__TYPE);
		}
		return alias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExpressionNode> getExpressionNode() {
		if (expressionNode == null) {
			expressionNode = new EObjectWithInverseResolvingEList<ExpressionNode>(ExpressionNode.class, this, CorePackage.CLASSIFIER__EXPRESSION_NODE, ExpressionsPackage.EXPRESSION_NODE__TYPE);
		}
		return expressionNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeMapping> getMappingFrom() {
		if (mappingFrom == null) {
			mappingFrom = new EObjectWithInverseResolvingEList<TypeMapping>(TypeMapping.class, this, CorePackage.CLASSIFIER__MAPPING_FROM, TypemappingPackage.TYPE_MAPPING__SOURCE_TYPE);
		}
		return mappingFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeMapping> getMappingTo() {
		if (mappingTo == null) {
			mappingTo = new EObjectWithInverseResolvingEList<TypeMapping>(TypeMapping.class, this, CorePackage.CLASSIFIER__MAPPING_TO, TypemappingPackage.TYPE_MAPPING__TARGET_TYPE);
		}
		return mappingTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassifierMap> getClassifierMap() {
		if (classifierMap == null) {
			classifierMap = new EObjectWithInverseResolvingEList.ManyInverse<ClassifierMap>(ClassifierMap.class, this, CorePackage.CLASSIFIER__CLASSIFIER_MAP, TransformationPackage.CLASSIFIER_MAP__SOURCE);
		}
		return classifierMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassifierFeatureMap> getCfMap() {
		if (cfMap == null) {
			cfMap = new EObjectWithInverseResolvingEList.ManyInverse<ClassifierFeatureMap>(ClassifierFeatureMap.class, this, CorePackage.CLASSIFIER__CF_MAP, TransformationPackage.CLASSIFIER_FEATURE_MAP__CLASSIFIER);
		}
		return cfMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.CLASSIFIER__FEATURE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFeature()).basicAdd(otherEnd, msgs);
			case CorePackage.CLASSIFIER__STRUCTURAL_FEATURE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStructuralFeature()).basicAdd(otherEnd, msgs);
			case CorePackage.CLASSIFIER__PARAMETER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParameter()).basicAdd(otherEnd, msgs);
			case CorePackage.CLASSIFIER__GENERALIZATION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGeneralization()).basicAdd(otherEnd, msgs);
			case CorePackage.CLASSIFIER__SPECIALIZATION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSpecialization()).basicAdd(otherEnd, msgs);
			case CorePackage.CLASSIFIER__INSTANCE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInstance()).basicAdd(otherEnd, msgs);
			case CorePackage.CLASSIFIER__ALIAS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAlias()).basicAdd(otherEnd, msgs);
			case CorePackage.CLASSIFIER__EXPRESSION_NODE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExpressionNode()).basicAdd(otherEnd, msgs);
			case CorePackage.CLASSIFIER__MAPPING_FROM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMappingFrom()).basicAdd(otherEnd, msgs);
			case CorePackage.CLASSIFIER__MAPPING_TO:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMappingTo()).basicAdd(otherEnd, msgs);
			case CorePackage.CLASSIFIER__CLASSIFIER_MAP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getClassifierMap()).basicAdd(otherEnd, msgs);
			case CorePackage.CLASSIFIER__CF_MAP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCfMap()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.CLASSIFIER__FEATURE:
				return ((InternalEList<?>)getFeature()).basicRemove(otherEnd, msgs);
			case CorePackage.CLASSIFIER__STRUCTURAL_FEATURE:
				return ((InternalEList<?>)getStructuralFeature()).basicRemove(otherEnd, msgs);
			case CorePackage.CLASSIFIER__PARAMETER:
				return ((InternalEList<?>)getParameter()).basicRemove(otherEnd, msgs);
			case CorePackage.CLASSIFIER__GENERALIZATION:
				return ((InternalEList<?>)getGeneralization()).basicRemove(otherEnd, msgs);
			case CorePackage.CLASSIFIER__SPECIALIZATION:
				return ((InternalEList<?>)getSpecialization()).basicRemove(otherEnd, msgs);
			case CorePackage.CLASSIFIER__INSTANCE:
				return ((InternalEList<?>)getInstance()).basicRemove(otherEnd, msgs);
			case CorePackage.CLASSIFIER__ALIAS:
				return ((InternalEList<?>)getAlias()).basicRemove(otherEnd, msgs);
			case CorePackage.CLASSIFIER__EXPRESSION_NODE:
				return ((InternalEList<?>)getExpressionNode()).basicRemove(otherEnd, msgs);
			case CorePackage.CLASSIFIER__MAPPING_FROM:
				return ((InternalEList<?>)getMappingFrom()).basicRemove(otherEnd, msgs);
			case CorePackage.CLASSIFIER__MAPPING_TO:
				return ((InternalEList<?>)getMappingTo()).basicRemove(otherEnd, msgs);
			case CorePackage.CLASSIFIER__CLASSIFIER_MAP:
				return ((InternalEList<?>)getClassifierMap()).basicRemove(otherEnd, msgs);
			case CorePackage.CLASSIFIER__CF_MAP:
				return ((InternalEList<?>)getCfMap()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.CLASSIFIER__IS_ABSTRACT:
				return isIsAbstract();
			case CorePackage.CLASSIFIER__FEATURE:
				return getFeature();
			case CorePackage.CLASSIFIER__STRUCTURAL_FEATURE:
				return getStructuralFeature();
			case CorePackage.CLASSIFIER__PARAMETER:
				return getParameter();
			case CorePackage.CLASSIFIER__GENERALIZATION:
				return getGeneralization();
			case CorePackage.CLASSIFIER__SPECIALIZATION:
				return getSpecialization();
			case CorePackage.CLASSIFIER__INSTANCE:
				return getInstance();
			case CorePackage.CLASSIFIER__ALIAS:
				return getAlias();
			case CorePackage.CLASSIFIER__EXPRESSION_NODE:
				return getExpressionNode();
			case CorePackage.CLASSIFIER__MAPPING_FROM:
				return getMappingFrom();
			case CorePackage.CLASSIFIER__MAPPING_TO:
				return getMappingTo();
			case CorePackage.CLASSIFIER__CLASSIFIER_MAP:
				return getClassifierMap();
			case CorePackage.CLASSIFIER__CF_MAP:
				return getCfMap();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CorePackage.CLASSIFIER__IS_ABSTRACT:
				setIsAbstract((Boolean)newValue);
				return;
			case CorePackage.CLASSIFIER__FEATURE:
				getFeature().clear();
				getFeature().addAll((Collection<? extends Feature>)newValue);
				return;
			case CorePackage.CLASSIFIER__STRUCTURAL_FEATURE:
				getStructuralFeature().clear();
				getStructuralFeature().addAll((Collection<? extends StructuralFeature>)newValue);
				return;
			case CorePackage.CLASSIFIER__PARAMETER:
				getParameter().clear();
				getParameter().addAll((Collection<? extends Parameter>)newValue);
				return;
			case CorePackage.CLASSIFIER__GENERALIZATION:
				getGeneralization().clear();
				getGeneralization().addAll((Collection<? extends Generalization>)newValue);
				return;
			case CorePackage.CLASSIFIER__SPECIALIZATION:
				getSpecialization().clear();
				getSpecialization().addAll((Collection<? extends Generalization>)newValue);
				return;
			case CorePackage.CLASSIFIER__INSTANCE:
				getInstance().clear();
				getInstance().addAll((Collection<? extends Instance>)newValue);
				return;
			case CorePackage.CLASSIFIER__ALIAS:
				getAlias().clear();
				getAlias().addAll((Collection<? extends TypeAlias>)newValue);
				return;
			case CorePackage.CLASSIFIER__EXPRESSION_NODE:
				getExpressionNode().clear();
				getExpressionNode().addAll((Collection<? extends ExpressionNode>)newValue);
				return;
			case CorePackage.CLASSIFIER__MAPPING_FROM:
				getMappingFrom().clear();
				getMappingFrom().addAll((Collection<? extends TypeMapping>)newValue);
				return;
			case CorePackage.CLASSIFIER__MAPPING_TO:
				getMappingTo().clear();
				getMappingTo().addAll((Collection<? extends TypeMapping>)newValue);
				return;
			case CorePackage.CLASSIFIER__CLASSIFIER_MAP:
				getClassifierMap().clear();
				getClassifierMap().addAll((Collection<? extends ClassifierMap>)newValue);
				return;
			case CorePackage.CLASSIFIER__CF_MAP:
				getCfMap().clear();
				getCfMap().addAll((Collection<? extends ClassifierFeatureMap>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CorePackage.CLASSIFIER__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case CorePackage.CLASSIFIER__FEATURE:
				getFeature().clear();
				return;
			case CorePackage.CLASSIFIER__STRUCTURAL_FEATURE:
				getStructuralFeature().clear();
				return;
			case CorePackage.CLASSIFIER__PARAMETER:
				getParameter().clear();
				return;
			case CorePackage.CLASSIFIER__GENERALIZATION:
				getGeneralization().clear();
				return;
			case CorePackage.CLASSIFIER__SPECIALIZATION:
				getSpecialization().clear();
				return;
			case CorePackage.CLASSIFIER__INSTANCE:
				getInstance().clear();
				return;
			case CorePackage.CLASSIFIER__ALIAS:
				getAlias().clear();
				return;
			case CorePackage.CLASSIFIER__EXPRESSION_NODE:
				getExpressionNode().clear();
				return;
			case CorePackage.CLASSIFIER__MAPPING_FROM:
				getMappingFrom().clear();
				return;
			case CorePackage.CLASSIFIER__MAPPING_TO:
				getMappingTo().clear();
				return;
			case CorePackage.CLASSIFIER__CLASSIFIER_MAP:
				getClassifierMap().clear();
				return;
			case CorePackage.CLASSIFIER__CF_MAP:
				getCfMap().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CorePackage.CLASSIFIER__IS_ABSTRACT:
				return isAbstract != IS_ABSTRACT_EDEFAULT;
			case CorePackage.CLASSIFIER__FEATURE:
				return feature != null && !feature.isEmpty();
			case CorePackage.CLASSIFIER__STRUCTURAL_FEATURE:
				return structuralFeature != null && !structuralFeature.isEmpty();
			case CorePackage.CLASSIFIER__PARAMETER:
				return parameter != null && !parameter.isEmpty();
			case CorePackage.CLASSIFIER__GENERALIZATION:
				return generalization != null && !generalization.isEmpty();
			case CorePackage.CLASSIFIER__SPECIALIZATION:
				return specialization != null && !specialization.isEmpty();
			case CorePackage.CLASSIFIER__INSTANCE:
				return instance != null && !instance.isEmpty();
			case CorePackage.CLASSIFIER__ALIAS:
				return alias != null && !alias.isEmpty();
			case CorePackage.CLASSIFIER__EXPRESSION_NODE:
				return expressionNode != null && !expressionNode.isEmpty();
			case CorePackage.CLASSIFIER__MAPPING_FROM:
				return mappingFrom != null && !mappingFrom.isEmpty();
			case CorePackage.CLASSIFIER__MAPPING_TO:
				return mappingTo != null && !mappingTo.isEmpty();
			case CorePackage.CLASSIFIER__CLASSIFIER_MAP:
				return classifierMap != null && !classifierMap.isEmpty();
			case CorePackage.CLASSIFIER__CF_MAP:
				return cfMap != null && !cfMap.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isAbstract: ");
		result.append(isAbstract);
		result.append(')');
		return result.toString();
	}

} //ClassifierImpl
