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

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.objectmodel.core.BooleanExpression;
import orgomg.cwm.objectmodel.core.Constraint;
import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Stereotype;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ConstraintImpl#getBody <em>Body</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ConstraintImpl#getConstrainedElement <em>Constrained Element</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.ConstraintImpl#getConstrainedStereotype <em>Constrained Stereotype</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstraintImpl extends ModelElementImpl implements Constraint {
	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected BooleanExpression body;

	/**
	 * The cached value of the '{@link #getConstrainedElement() <em>Constrained Element</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstrainedElement()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelElement> constrainedElement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanExpression getBody() {
		return body;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(BooleanExpression newBody, NotificationChain msgs) {
		BooleanExpression oldBody = body;
		body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.CONSTRAINT__BODY, oldBody, newBody);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBody(BooleanExpression newBody) {
		if (newBody != body) {
			NotificationChain msgs = null;
			if (body != null)
				msgs = ((InternalEObject)body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.CONSTRAINT__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.CONSTRAINT__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CONSTRAINT__BODY, newBody, newBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelElement> getConstrainedElement() {
		if (constrainedElement == null) {
			constrainedElement = new EObjectWithInverseResolvingEList.ManyInverse<ModelElement>(ModelElement.class, this, CorePackage.CONSTRAINT__CONSTRAINED_ELEMENT, CorePackage.MODEL_ELEMENT__CONSTRAINT);
		}
		return constrainedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stereotype getConstrainedStereotype() {
		if (eContainerFeatureID() != CorePackage.CONSTRAINT__CONSTRAINED_STEREOTYPE) return null;
		return (Stereotype)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConstrainedStereotype(Stereotype newConstrainedStereotype, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newConstrainedStereotype, CorePackage.CONSTRAINT__CONSTRAINED_STEREOTYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstrainedStereotype(Stereotype newConstrainedStereotype) {
		if (newConstrainedStereotype != eInternalContainer() || (eContainerFeatureID() != CorePackage.CONSTRAINT__CONSTRAINED_STEREOTYPE && newConstrainedStereotype != null)) {
			if (EcoreUtil.isAncestor(this, newConstrainedStereotype))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newConstrainedStereotype != null)
				msgs = ((InternalEObject)newConstrainedStereotype).eInverseAdd(this, CorePackage.STEREOTYPE__STEREOTYPE_CONSTRAINT, Stereotype.class, msgs);
			msgs = basicSetConstrainedStereotype(newConstrainedStereotype, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CONSTRAINT__CONSTRAINED_STEREOTYPE, newConstrainedStereotype, newConstrainedStereotype));
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
			case CorePackage.CONSTRAINT__CONSTRAINED_ELEMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConstrainedElement()).basicAdd(otherEnd, msgs);
			case CorePackage.CONSTRAINT__CONSTRAINED_STEREOTYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetConstrainedStereotype((Stereotype)otherEnd, msgs);
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
			case CorePackage.CONSTRAINT__BODY:
				return basicSetBody(null, msgs);
			case CorePackage.CONSTRAINT__CONSTRAINED_ELEMENT:
				return ((InternalEList<?>)getConstrainedElement()).basicRemove(otherEnd, msgs);
			case CorePackage.CONSTRAINT__CONSTRAINED_STEREOTYPE:
				return basicSetConstrainedStereotype(null, msgs);
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
			case CorePackage.CONSTRAINT__CONSTRAINED_STEREOTYPE:
				return eInternalContainer().eInverseRemove(this, CorePackage.STEREOTYPE__STEREOTYPE_CONSTRAINT, Stereotype.class, msgs);
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
			case CorePackage.CONSTRAINT__BODY:
				return getBody();
			case CorePackage.CONSTRAINT__CONSTRAINED_ELEMENT:
				return getConstrainedElement();
			case CorePackage.CONSTRAINT__CONSTRAINED_STEREOTYPE:
				return getConstrainedStereotype();
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
			case CorePackage.CONSTRAINT__BODY:
				setBody((BooleanExpression)newValue);
				return;
			case CorePackage.CONSTRAINT__CONSTRAINED_ELEMENT:
				getConstrainedElement().clear();
				getConstrainedElement().addAll((Collection<? extends ModelElement>)newValue);
				return;
			case CorePackage.CONSTRAINT__CONSTRAINED_STEREOTYPE:
				setConstrainedStereotype((Stereotype)newValue);
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
			case CorePackage.CONSTRAINT__BODY:
				setBody((BooleanExpression)null);
				return;
			case CorePackage.CONSTRAINT__CONSTRAINED_ELEMENT:
				getConstrainedElement().clear();
				return;
			case CorePackage.CONSTRAINT__CONSTRAINED_STEREOTYPE:
				setConstrainedStereotype((Stereotype)null);
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
			case CorePackage.CONSTRAINT__BODY:
				return body != null;
			case CorePackage.CONSTRAINT__CONSTRAINED_ELEMENT:
				return constrainedElement != null && !constrainedElement.isEmpty();
			case CorePackage.CONSTRAINT__CONSTRAINED_STEREOTYPE:
				return getConstrainedStereotype() != null;
		}
		return super.eIsSet(featureID);
	}

} //ConstraintImpl
