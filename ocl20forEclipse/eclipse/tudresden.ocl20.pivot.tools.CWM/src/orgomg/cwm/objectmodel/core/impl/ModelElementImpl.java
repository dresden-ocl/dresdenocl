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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage;
import orgomg.cwm.analysis.businessnomenclature.VocabularyElement;

import orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage;
import orgomg.cwm.analysis.informationvisualization.RenderedObject;

import orgomg.cwm.analysis.transformation.DataObjectSet;
import orgomg.cwm.analysis.transformation.TransformationPackage;

import orgomg.cwm.foundation.businessinformation.BusinessinformationPackage;
import orgomg.cwm.foundation.businessinformation.Description;
import orgomg.cwm.foundation.businessinformation.Document;
import orgomg.cwm.foundation.businessinformation.ResponsibleParty;

import orgomg.cwm.foundation.expressions.ElementNode;
import orgomg.cwm.foundation.expressions.ExpressionsPackage;

import orgomg.cwm.management.warehouseoperation.ChangeRequest;
import orgomg.cwm.management.warehouseoperation.Measurement;
import orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage;

import orgomg.cwm.objectmodel.core.Constraint;
import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.Dependency;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Namespace;
import orgomg.cwm.objectmodel.core.Stereotype;
import orgomg.cwm.objectmodel.core.TaggedValue;
import orgomg.cwm.objectmodel.core.VisibilityKind;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getClientDependency <em>Client Dependency</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getSupplierDependency <em>Supplier Dependency</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getImporter <em>Importer</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getStereotype <em>Stereotype</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getTaggedValue <em>Tagged Value</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getDocument <em>Document</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getResponsibleParty <em>Responsible Party</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getElementNode <em>Element Node</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getSet <em>Set</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getRenderedObject <em>Rendered Object</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getVocabularyElement <em>Vocabulary Element</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getMeasurement <em>Measurement</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ModelElementImpl#getChangeRequest <em>Change Request</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ModelElementImpl extends ElementImpl implements ModelElement {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected static final VisibilityKind VISIBILITY_EDEFAULT = VisibilityKind.VK_PUBLIC;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected VisibilityKind visibility = VISIBILITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getClientDependency() <em>Client Dependency</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientDependency()
	 * @generated
	 * @ordered
	 */
	protected EList<Dependency> clientDependency;

	/**
	 * The cached value of the '{@link #getSupplierDependency() <em>Supplier Dependency</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupplierDependency()
	 * @generated
	 * @ordered
	 */
	protected EList<Dependency> supplierDependency;

	/**
	 * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraint()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> constraint;

	/**
	 * The cached value of the '{@link #getImporter() <em>Importer</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImporter()
	 * @generated
	 * @ordered
	 */
	protected EList<orgomg.cwm.objectmodel.core.Package> importer;

	/**
	 * The cached value of the '{@link #getStereotype() <em>Stereotype</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotype()
	 * @generated
	 * @ordered
	 */
	protected Stereotype stereotype;

	/**
	 * The cached value of the '{@link #getTaggedValue() <em>Tagged Value</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaggedValue()
	 * @generated
	 * @ordered
	 */
	protected EList<TaggedValue> taggedValue;

	/**
	 * The cached value of the '{@link #getDocument() <em>Document</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocument()
	 * @generated
	 * @ordered
	 */
	protected EList<Document> document;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected EList<Description> description;

	/**
	 * The cached value of the '{@link #getResponsibleParty() <em>Responsible Party</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponsibleParty()
	 * @generated
	 * @ordered
	 */
	protected EList<ResponsibleParty> responsibleParty;

	/**
	 * The cached value of the '{@link #getElementNode() <em>Element Node</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementNode()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementNode> elementNode;

	/**
	 * The cached value of the '{@link #getSet() <em>Set</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSet()
	 * @generated
	 * @ordered
	 */
	protected EList<DataObjectSet> set;

	/**
	 * The cached value of the '{@link #getRenderedObject() <em>Rendered Object</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRenderedObject()
	 * @generated
	 * @ordered
	 */
	protected EList<RenderedObject> renderedObject;

	/**
	 * The cached value of the '{@link #getVocabularyElement() <em>Vocabulary Element</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVocabularyElement()
	 * @generated
	 * @ordered
	 */
	protected EList<VocabularyElement> vocabularyElement;

	/**
	 * The cached value of the '{@link #getMeasurement() <em>Measurement</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasurement()
	 * @generated
	 * @ordered
	 */
	protected EList<Measurement> measurement;

	/**
	 * The cached value of the '{@link #getChangeRequest() <em>Change Request</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeRequest()
	 * @generated
	 * @ordered
	 */
	protected EList<ChangeRequest> changeRequest;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.MODEL_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.MODEL_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisibilityKind getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(VisibilityKind newVisibility) {
		VisibilityKind oldVisibility = visibility;
		visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.MODEL_ELEMENT__VISIBILITY, oldVisibility, visibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Dependency> getClientDependency() {
		if (clientDependency == null) {
			clientDependency = new EObjectWithInverseResolvingEList.ManyInverse<Dependency>(Dependency.class, this, CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY, CorePackage.DEPENDENCY__CLIENT);
		}
		return clientDependency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Dependency> getSupplierDependency() {
		if (supplierDependency == null) {
			supplierDependency = new EObjectWithInverseResolvingEList.ManyInverse<Dependency>(Dependency.class, this, CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY, CorePackage.DEPENDENCY__SUPPLIER);
		}
		return supplierDependency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Constraint> getConstraint() {
		if (constraint == null) {
			constraint = new EObjectWithInverseResolvingEList.ManyInverse<Constraint>(Constraint.class, this, CorePackage.MODEL_ELEMENT__CONSTRAINT, CorePackage.CONSTRAINT__CONSTRAINED_ELEMENT);
		}
		return constraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Namespace getNamespace() {
		if (eContainerFeatureID() != CorePackage.MODEL_ELEMENT__NAMESPACE) return null;
		return (Namespace)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNamespace(Namespace newNamespace, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newNamespace, CorePackage.MODEL_ELEMENT__NAMESPACE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNamespace(Namespace newNamespace) {
		if (newNamespace != eInternalContainer() || (eContainerFeatureID() != CorePackage.MODEL_ELEMENT__NAMESPACE && newNamespace != null)) {
			if (EcoreUtil.isAncestor(this, newNamespace))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newNamespace != null)
				msgs = ((InternalEObject)newNamespace).eInverseAdd(this, CorePackage.NAMESPACE__OWNED_ELEMENT, Namespace.class, msgs);
			msgs = basicSetNamespace(newNamespace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.MODEL_ELEMENT__NAMESPACE, newNamespace, newNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<orgomg.cwm.objectmodel.core.Package> getImporter() {
		if (importer == null) {
			importer = new EObjectWithInverseResolvingEList.ManyInverse<orgomg.cwm.objectmodel.core.Package>(orgomg.cwm.objectmodel.core.Package.class, this, CorePackage.MODEL_ELEMENT__IMPORTER, CorePackage.PACKAGE__IMPORTED_ELEMENT);
		}
		return importer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stereotype getStereotype() {
		if (stereotype != null && stereotype.eIsProxy()) {
			InternalEObject oldStereotype = (InternalEObject)stereotype;
			stereotype = (Stereotype)eResolveProxy(oldStereotype);
			if (stereotype != oldStereotype) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.MODEL_ELEMENT__STEREOTYPE, oldStereotype, stereotype));
			}
		}
		return stereotype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stereotype basicGetStereotype() {
		return stereotype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStereotype(Stereotype newStereotype, NotificationChain msgs) {
		Stereotype oldStereotype = stereotype;
		stereotype = newStereotype;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.MODEL_ELEMENT__STEREOTYPE, oldStereotype, newStereotype);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStereotype(Stereotype newStereotype) {
		if (newStereotype != stereotype) {
			NotificationChain msgs = null;
			if (stereotype != null)
				msgs = ((InternalEObject)stereotype).eInverseRemove(this, CorePackage.STEREOTYPE__EXTENDED_ELEMENT, Stereotype.class, msgs);
			if (newStereotype != null)
				msgs = ((InternalEObject)newStereotype).eInverseAdd(this, CorePackage.STEREOTYPE__EXTENDED_ELEMENT, Stereotype.class, msgs);
			msgs = basicSetStereotype(newStereotype, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.MODEL_ELEMENT__STEREOTYPE, newStereotype, newStereotype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TaggedValue> getTaggedValue() {
		if (taggedValue == null) {
			taggedValue = new EObjectContainmentWithInverseEList<TaggedValue>(TaggedValue.class, this, CorePackage.MODEL_ELEMENT__TAGGED_VALUE, CorePackage.TAGGED_VALUE__MODEL_ELEMENT);
		}
		return taggedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Document> getDocument() {
		if (document == null) {
			document = new EObjectWithInverseResolvingEList.ManyInverse<Document>(Document.class, this, CorePackage.MODEL_ELEMENT__DOCUMENT, BusinessinformationPackage.DOCUMENT__MODEL_ELEMENT);
		}
		return document;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Description> getDescription() {
		if (description == null) {
			description = new EObjectWithInverseResolvingEList.ManyInverse<Description>(Description.class, this, CorePackage.MODEL_ELEMENT__DESCRIPTION, BusinessinformationPackage.DESCRIPTION__MODEL_ELEMENT);
		}
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResponsibleParty> getResponsibleParty() {
		if (responsibleParty == null) {
			responsibleParty = new EObjectWithInverseResolvingEList.ManyInverse<ResponsibleParty>(ResponsibleParty.class, this, CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY, BusinessinformationPackage.RESPONSIBLE_PARTY__MODEL_ELEMENT);
		}
		return responsibleParty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ElementNode> getElementNode() {
		if (elementNode == null) {
			elementNode = new EObjectWithInverseResolvingEList<ElementNode>(ElementNode.class, this, CorePackage.MODEL_ELEMENT__ELEMENT_NODE, ExpressionsPackage.ELEMENT_NODE__MODEL_ELEMENT);
		}
		return elementNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataObjectSet> getSet() {
		if (set == null) {
			set = new EObjectWithInverseResolvingEList.ManyInverse<DataObjectSet>(DataObjectSet.class, this, CorePackage.MODEL_ELEMENT__SET, TransformationPackage.DATA_OBJECT_SET__ELEMENT);
		}
		return set;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RenderedObject> getRenderedObject() {
		if (renderedObject == null) {
			renderedObject = new EObjectWithInverseResolvingEList<RenderedObject>(RenderedObject.class, this, CorePackage.MODEL_ELEMENT__RENDERED_OBJECT, InformationvisualizationPackage.RENDERED_OBJECT__MODEL_ELEMENT);
		}
		return renderedObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VocabularyElement> getVocabularyElement() {
		if (vocabularyElement == null) {
			vocabularyElement = new EObjectWithInverseResolvingEList.ManyInverse<VocabularyElement>(VocabularyElement.class, this, CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT, BusinessnomenclaturePackage.VOCABULARY_ELEMENT__MODEL_ELEMENT);
		}
		return vocabularyElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Measurement> getMeasurement() {
		if (measurement == null) {
			measurement = new EObjectWithInverseResolvingEList<Measurement>(Measurement.class, this, CorePackage.MODEL_ELEMENT__MEASUREMENT, WarehouseoperationPackage.MEASUREMENT__MODEL_ELEMENT);
		}
		return measurement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ChangeRequest> getChangeRequest() {
		if (changeRequest == null) {
			changeRequest = new EObjectWithInverseResolvingEList.ManyInverse<ChangeRequest>(ChangeRequest.class, this, CorePackage.MODEL_ELEMENT__CHANGE_REQUEST, WarehouseoperationPackage.CHANGE_REQUEST__MODEL_ELEMENT);
		}
		return changeRequest;
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
			case CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getClientDependency()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSupplierDependency()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__CONSTRAINT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConstraint()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__NAMESPACE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetNamespace((Namespace)otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__IMPORTER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getImporter()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__STEREOTYPE:
				if (stereotype != null)
					msgs = ((InternalEObject)stereotype).eInverseRemove(this, CorePackage.STEREOTYPE__EXTENDED_ELEMENT, Stereotype.class, msgs);
				return basicSetStereotype((Stereotype)otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__TAGGED_VALUE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTaggedValue()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__DOCUMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDocument()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__DESCRIPTION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDescription()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResponsibleParty()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__ELEMENT_NODE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getElementNode()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__SET:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSet()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__RENDERED_OBJECT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRenderedObject()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getVocabularyElement()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__MEASUREMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMeasurement()).basicAdd(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__CHANGE_REQUEST:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChangeRequest()).basicAdd(otherEnd, msgs);
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
			case CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY:
				return ((InternalEList<?>)getClientDependency()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY:
				return ((InternalEList<?>)getSupplierDependency()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__CONSTRAINT:
				return ((InternalEList<?>)getConstraint()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__NAMESPACE:
				return basicSetNamespace(null, msgs);
			case CorePackage.MODEL_ELEMENT__IMPORTER:
				return ((InternalEList<?>)getImporter()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__STEREOTYPE:
				return basicSetStereotype(null, msgs);
			case CorePackage.MODEL_ELEMENT__TAGGED_VALUE:
				return ((InternalEList<?>)getTaggedValue()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__DOCUMENT:
				return ((InternalEList<?>)getDocument()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__DESCRIPTION:
				return ((InternalEList<?>)getDescription()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY:
				return ((InternalEList<?>)getResponsibleParty()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__ELEMENT_NODE:
				return ((InternalEList<?>)getElementNode()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__SET:
				return ((InternalEList<?>)getSet()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__RENDERED_OBJECT:
				return ((InternalEList<?>)getRenderedObject()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT:
				return ((InternalEList<?>)getVocabularyElement()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__MEASUREMENT:
				return ((InternalEList<?>)getMeasurement()).basicRemove(otherEnd, msgs);
			case CorePackage.MODEL_ELEMENT__CHANGE_REQUEST:
				return ((InternalEList<?>)getChangeRequest()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case CorePackage.MODEL_ELEMENT__NAMESPACE:
				return eInternalContainer().eInverseRemove(this, CorePackage.NAMESPACE__OWNED_ELEMENT, Namespace.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.MODEL_ELEMENT__NAME:
				return getName();
			case CorePackage.MODEL_ELEMENT__VISIBILITY:
				return getVisibility();
			case CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY:
				return getClientDependency();
			case CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY:
				return getSupplierDependency();
			case CorePackage.MODEL_ELEMENT__CONSTRAINT:
				return getConstraint();
			case CorePackage.MODEL_ELEMENT__NAMESPACE:
				return getNamespace();
			case CorePackage.MODEL_ELEMENT__IMPORTER:
				return getImporter();
			case CorePackage.MODEL_ELEMENT__STEREOTYPE:
				if (resolve) return getStereotype();
				return basicGetStereotype();
			case CorePackage.MODEL_ELEMENT__TAGGED_VALUE:
				return getTaggedValue();
			case CorePackage.MODEL_ELEMENT__DOCUMENT:
				return getDocument();
			case CorePackage.MODEL_ELEMENT__DESCRIPTION:
				return getDescription();
			case CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY:
				return getResponsibleParty();
			case CorePackage.MODEL_ELEMENT__ELEMENT_NODE:
				return getElementNode();
			case CorePackage.MODEL_ELEMENT__SET:
				return getSet();
			case CorePackage.MODEL_ELEMENT__RENDERED_OBJECT:
				return getRenderedObject();
			case CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT:
				return getVocabularyElement();
			case CorePackage.MODEL_ELEMENT__MEASUREMENT:
				return getMeasurement();
			case CorePackage.MODEL_ELEMENT__CHANGE_REQUEST:
				return getChangeRequest();
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
			case CorePackage.MODEL_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__VISIBILITY:
				setVisibility((VisibilityKind)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY:
				getClientDependency().clear();
				getClientDependency().addAll((Collection<? extends Dependency>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY:
				getSupplierDependency().clear();
				getSupplierDependency().addAll((Collection<? extends Dependency>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__CONSTRAINT:
				getConstraint().clear();
				getConstraint().addAll((Collection<? extends Constraint>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__NAMESPACE:
				setNamespace((Namespace)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__IMPORTER:
				getImporter().clear();
				getImporter().addAll((Collection<? extends orgomg.cwm.objectmodel.core.Package>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__STEREOTYPE:
				setStereotype((Stereotype)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__TAGGED_VALUE:
				getTaggedValue().clear();
				getTaggedValue().addAll((Collection<? extends TaggedValue>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__DOCUMENT:
				getDocument().clear();
				getDocument().addAll((Collection<? extends Document>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends Description>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY:
				getResponsibleParty().clear();
				getResponsibleParty().addAll((Collection<? extends ResponsibleParty>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__ELEMENT_NODE:
				getElementNode().clear();
				getElementNode().addAll((Collection<? extends ElementNode>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__SET:
				getSet().clear();
				getSet().addAll((Collection<? extends DataObjectSet>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__RENDERED_OBJECT:
				getRenderedObject().clear();
				getRenderedObject().addAll((Collection<? extends RenderedObject>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT:
				getVocabularyElement().clear();
				getVocabularyElement().addAll((Collection<? extends VocabularyElement>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__MEASUREMENT:
				getMeasurement().clear();
				getMeasurement().addAll((Collection<? extends Measurement>)newValue);
				return;
			case CorePackage.MODEL_ELEMENT__CHANGE_REQUEST:
				getChangeRequest().clear();
				getChangeRequest().addAll((Collection<? extends ChangeRequest>)newValue);
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
			case CorePackage.MODEL_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CorePackage.MODEL_ELEMENT__VISIBILITY:
				setVisibility(VISIBILITY_EDEFAULT);
				return;
			case CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY:
				getClientDependency().clear();
				return;
			case CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY:
				getSupplierDependency().clear();
				return;
			case CorePackage.MODEL_ELEMENT__CONSTRAINT:
				getConstraint().clear();
				return;
			case CorePackage.MODEL_ELEMENT__NAMESPACE:
				setNamespace((Namespace)null);
				return;
			case CorePackage.MODEL_ELEMENT__IMPORTER:
				getImporter().clear();
				return;
			case CorePackage.MODEL_ELEMENT__STEREOTYPE:
				setStereotype((Stereotype)null);
				return;
			case CorePackage.MODEL_ELEMENT__TAGGED_VALUE:
				getTaggedValue().clear();
				return;
			case CorePackage.MODEL_ELEMENT__DOCUMENT:
				getDocument().clear();
				return;
			case CorePackage.MODEL_ELEMENT__DESCRIPTION:
				getDescription().clear();
				return;
			case CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY:
				getResponsibleParty().clear();
				return;
			case CorePackage.MODEL_ELEMENT__ELEMENT_NODE:
				getElementNode().clear();
				return;
			case CorePackage.MODEL_ELEMENT__SET:
				getSet().clear();
				return;
			case CorePackage.MODEL_ELEMENT__RENDERED_OBJECT:
				getRenderedObject().clear();
				return;
			case CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT:
				getVocabularyElement().clear();
				return;
			case CorePackage.MODEL_ELEMENT__MEASUREMENT:
				getMeasurement().clear();
				return;
			case CorePackage.MODEL_ELEMENT__CHANGE_REQUEST:
				getChangeRequest().clear();
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
			case CorePackage.MODEL_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CorePackage.MODEL_ELEMENT__VISIBILITY:
				return visibility != VISIBILITY_EDEFAULT;
			case CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY:
				return clientDependency != null && !clientDependency.isEmpty();
			case CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY:
				return supplierDependency != null && !supplierDependency.isEmpty();
			case CorePackage.MODEL_ELEMENT__CONSTRAINT:
				return constraint != null && !constraint.isEmpty();
			case CorePackage.MODEL_ELEMENT__NAMESPACE:
				return getNamespace() != null;
			case CorePackage.MODEL_ELEMENT__IMPORTER:
				return importer != null && !importer.isEmpty();
			case CorePackage.MODEL_ELEMENT__STEREOTYPE:
				return stereotype != null;
			case CorePackage.MODEL_ELEMENT__TAGGED_VALUE:
				return taggedValue != null && !taggedValue.isEmpty();
			case CorePackage.MODEL_ELEMENT__DOCUMENT:
				return document != null && !document.isEmpty();
			case CorePackage.MODEL_ELEMENT__DESCRIPTION:
				return description != null && !description.isEmpty();
			case CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY:
				return responsibleParty != null && !responsibleParty.isEmpty();
			case CorePackage.MODEL_ELEMENT__ELEMENT_NODE:
				return elementNode != null && !elementNode.isEmpty();
			case CorePackage.MODEL_ELEMENT__SET:
				return set != null && !set.isEmpty();
			case CorePackage.MODEL_ELEMENT__RENDERED_OBJECT:
				return renderedObject != null && !renderedObject.isEmpty();
			case CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT:
				return vocabularyElement != null && !vocabularyElement.isEmpty();
			case CorePackage.MODEL_ELEMENT__MEASUREMENT:
				return measurement != null && !measurement.isEmpty();
			case CorePackage.MODEL_ELEMENT__CHANGE_REQUEST:
				return changeRequest != null && !changeRequest.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", visibility: ");
		result.append(visibility);
		result.append(')');
		return result.toString();
	}

} //ModelElementImpl
