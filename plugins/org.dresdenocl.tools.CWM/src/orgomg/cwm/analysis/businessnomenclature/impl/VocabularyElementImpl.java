/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.businessnomenclature.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage;
import orgomg.cwm.analysis.businessnomenclature.VocabularyElement;

import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.ModelElement;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vocabulary Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.VocabularyElementImpl#getDefinition <em>Definition</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.VocabularyElementImpl#getExample <em>Example</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.VocabularyElementImpl#getUsage <em>Usage</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.VocabularyElementImpl#getRelatedElement <em>Related Element</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.VocabularyElementImpl#getElement <em>Element</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.VocabularyElementImpl#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VocabularyElementImpl extends ModelElementImpl implements VocabularyElement {
	/**
	 * The default value of the '{@link #getDefinition() <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFINITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefinition() <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected String definition = DEFINITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getExample() <em>Example</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExample()
	 * @generated
	 * @ordered
	 */
	protected static final String EXAMPLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExample() <em>Example</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExample()
	 * @generated
	 * @ordered
	 */
	protected String example = EXAMPLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUsage() <em>Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsage()
	 * @generated
	 * @ordered
	 */
	protected static final String USAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUsage() <em>Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsage()
	 * @generated
	 * @ordered
	 */
	protected String usage = USAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRelatedElement() <em>Related Element</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelatedElement()
	 * @generated
	 * @ordered
	 */
	protected EList<VocabularyElement> relatedElement;

	/**
	 * The cached value of the '{@link #getElement() <em>Element</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected EList<VocabularyElement> element;

	/**
	 * The cached value of the '{@link #getModelElement() <em>Model Element</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelElement()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelElement> modelElement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VocabularyElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BusinessnomenclaturePackage.Literals.VOCABULARY_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinition(String newDefinition) {
		String oldDefinition = definition;
		definition = newDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BusinessnomenclaturePackage.VOCABULARY_ELEMENT__DEFINITION, oldDefinition, definition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExample() {
		return example;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExample(String newExample) {
		String oldExample = example;
		example = newExample;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BusinessnomenclaturePackage.VOCABULARY_ELEMENT__EXAMPLE, oldExample, example));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUsage() {
		return usage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsage(String newUsage) {
		String oldUsage = usage;
		usage = newUsage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BusinessnomenclaturePackage.VOCABULARY_ELEMENT__USAGE, oldUsage, usage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VocabularyElement> getRelatedElement() {
		if (relatedElement == null) {
			relatedElement = new EObjectWithInverseResolvingEList.ManyInverse<VocabularyElement>(VocabularyElement.class, this, BusinessnomenclaturePackage.VOCABULARY_ELEMENT__RELATED_ELEMENT, BusinessnomenclaturePackage.VOCABULARY_ELEMENT__ELEMENT);
		}
		return relatedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VocabularyElement> getElement() {
		if (element == null) {
			element = new EObjectWithInverseResolvingEList.ManyInverse<VocabularyElement>(VocabularyElement.class, this, BusinessnomenclaturePackage.VOCABULARY_ELEMENT__ELEMENT, BusinessnomenclaturePackage.VOCABULARY_ELEMENT__RELATED_ELEMENT);
		}
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelElement> getModelElement() {
		if (modelElement == null) {
			modelElement = new EObjectWithInverseResolvingEList.ManyInverse<ModelElement>(ModelElement.class, this, BusinessnomenclaturePackage.VOCABULARY_ELEMENT__MODEL_ELEMENT, CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT);
		}
		return modelElement;
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
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__RELATED_ELEMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRelatedElement()).basicAdd(otherEnd, msgs);
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__ELEMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getElement()).basicAdd(otherEnd, msgs);
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__MODEL_ELEMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getModelElement()).basicAdd(otherEnd, msgs);
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
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__RELATED_ELEMENT:
				return ((InternalEList<?>)getRelatedElement()).basicRemove(otherEnd, msgs);
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__ELEMENT:
				return ((InternalEList<?>)getElement()).basicRemove(otherEnd, msgs);
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__MODEL_ELEMENT:
				return ((InternalEList<?>)getModelElement()).basicRemove(otherEnd, msgs);
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
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__DEFINITION:
				return getDefinition();
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__EXAMPLE:
				return getExample();
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__USAGE:
				return getUsage();
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__RELATED_ELEMENT:
				return getRelatedElement();
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__ELEMENT:
				return getElement();
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__MODEL_ELEMENT:
				return getModelElement();
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
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__DEFINITION:
				setDefinition((String)newValue);
				return;
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__EXAMPLE:
				setExample((String)newValue);
				return;
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__USAGE:
				setUsage((String)newValue);
				return;
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__RELATED_ELEMENT:
				getRelatedElement().clear();
				getRelatedElement().addAll((Collection<? extends VocabularyElement>)newValue);
				return;
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__ELEMENT:
				getElement().clear();
				getElement().addAll((Collection<? extends VocabularyElement>)newValue);
				return;
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__MODEL_ELEMENT:
				getModelElement().clear();
				getModelElement().addAll((Collection<? extends ModelElement>)newValue);
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
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__DEFINITION:
				setDefinition(DEFINITION_EDEFAULT);
				return;
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__EXAMPLE:
				setExample(EXAMPLE_EDEFAULT);
				return;
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__USAGE:
				setUsage(USAGE_EDEFAULT);
				return;
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__RELATED_ELEMENT:
				getRelatedElement().clear();
				return;
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__ELEMENT:
				getElement().clear();
				return;
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__MODEL_ELEMENT:
				getModelElement().clear();
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
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__DEFINITION:
				return DEFINITION_EDEFAULT == null ? definition != null : !DEFINITION_EDEFAULT.equals(definition);
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__EXAMPLE:
				return EXAMPLE_EDEFAULT == null ? example != null : !EXAMPLE_EDEFAULT.equals(example);
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__USAGE:
				return USAGE_EDEFAULT == null ? usage != null : !USAGE_EDEFAULT.equals(usage);
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__RELATED_ELEMENT:
				return relatedElement != null && !relatedElement.isEmpty();
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__ELEMENT:
				return element != null && !element.isEmpty();
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT__MODEL_ELEMENT:
				return modelElement != null && !modelElement.isEmpty();
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
		result.append(" (definition: ");
		result.append(definition);
		result.append(", example: ");
		result.append(example);
		result.append(", usage: ");
		result.append(usage);
		result.append(')');
		return result.toString();
	}

} //VocabularyElementImpl
