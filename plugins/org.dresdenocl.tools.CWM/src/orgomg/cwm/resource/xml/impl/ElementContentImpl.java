/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.xml.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.resource.xml.ElementContent;
import orgomg.cwm.resource.xml.ElementOrderType;
import orgomg.cwm.resource.xml.XmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.xml.impl.ElementContentImpl#getOrder <em>Order</em>}</li>
 *   <li>{@link orgomg.cwm.resource.xml.impl.ElementContentImpl#getOwnerContent <em>Owner Content</em>}</li>
 *   <li>{@link orgomg.cwm.resource.xml.impl.ElementContentImpl#getOwnedContent <em>Owned Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementContentImpl extends ContentImpl implements ElementContent {
	/**
	 * The default value of the '{@link #getOrder() <em>Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrder()
	 * @generated
	 * @ordered
	 */
	protected static final ElementOrderType ORDER_EDEFAULT = ElementOrderType.XML_CHOICE;

	/**
	 * The cached value of the '{@link #getOrder() <em>Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrder()
	 * @generated
	 * @ordered
	 */
	protected ElementOrderType order = ORDER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnerContent() <em>Owner Content</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnerContent()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementContent> ownerContent;

	/**
	 * The cached value of the '{@link #getOwnedContent() <em>Owned Content</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedContent()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementContent> ownedContent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XmlPackage.Literals.ELEMENT_CONTENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementOrderType getOrder() {
		return order;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrder(ElementOrderType newOrder) {
		ElementOrderType oldOrder = order;
		order = newOrder == null ? ORDER_EDEFAULT : newOrder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XmlPackage.ELEMENT_CONTENT__ORDER, oldOrder, order));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ElementContent> getOwnerContent() {
		if (ownerContent == null) {
			ownerContent = new EObjectWithInverseResolvingEList.ManyInverse<ElementContent>(ElementContent.class, this, XmlPackage.ELEMENT_CONTENT__OWNER_CONTENT, XmlPackage.ELEMENT_CONTENT__OWNED_CONTENT);
		}
		return ownerContent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ElementContent> getOwnedContent() {
		if (ownedContent == null) {
			ownedContent = new EObjectWithInverseResolvingEList.ManyInverse<ElementContent>(ElementContent.class, this, XmlPackage.ELEMENT_CONTENT__OWNED_CONTENT, XmlPackage.ELEMENT_CONTENT__OWNER_CONTENT);
		}
		return ownedContent;
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
			case XmlPackage.ELEMENT_CONTENT__OWNER_CONTENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnerContent()).basicAdd(otherEnd, msgs);
			case XmlPackage.ELEMENT_CONTENT__OWNED_CONTENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedContent()).basicAdd(otherEnd, msgs);
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
			case XmlPackage.ELEMENT_CONTENT__OWNER_CONTENT:
				return ((InternalEList<?>)getOwnerContent()).basicRemove(otherEnd, msgs);
			case XmlPackage.ELEMENT_CONTENT__OWNED_CONTENT:
				return ((InternalEList<?>)getOwnedContent()).basicRemove(otherEnd, msgs);
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
			case XmlPackage.ELEMENT_CONTENT__ORDER:
				return getOrder();
			case XmlPackage.ELEMENT_CONTENT__OWNER_CONTENT:
				return getOwnerContent();
			case XmlPackage.ELEMENT_CONTENT__OWNED_CONTENT:
				return getOwnedContent();
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
			case XmlPackage.ELEMENT_CONTENT__ORDER:
				setOrder((ElementOrderType)newValue);
				return;
			case XmlPackage.ELEMENT_CONTENT__OWNER_CONTENT:
				getOwnerContent().clear();
				getOwnerContent().addAll((Collection<? extends ElementContent>)newValue);
				return;
			case XmlPackage.ELEMENT_CONTENT__OWNED_CONTENT:
				getOwnedContent().clear();
				getOwnedContent().addAll((Collection<? extends ElementContent>)newValue);
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
			case XmlPackage.ELEMENT_CONTENT__ORDER:
				setOrder(ORDER_EDEFAULT);
				return;
			case XmlPackage.ELEMENT_CONTENT__OWNER_CONTENT:
				getOwnerContent().clear();
				return;
			case XmlPackage.ELEMENT_CONTENT__OWNED_CONTENT:
				getOwnedContent().clear();
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
			case XmlPackage.ELEMENT_CONTENT__ORDER:
				return order != ORDER_EDEFAULT;
			case XmlPackage.ELEMENT_CONTENT__OWNER_CONTENT:
				return ownerContent != null && !ownerContent.isEmpty();
			case XmlPackage.ELEMENT_CONTENT__OWNED_CONTENT:
				return ownedContent != null && !ownedContent.isEmpty();
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
		result.append(" (order: ");
		result.append(order);
		result.append(')');
		return result.toString();
	}

} //ElementContentImpl
