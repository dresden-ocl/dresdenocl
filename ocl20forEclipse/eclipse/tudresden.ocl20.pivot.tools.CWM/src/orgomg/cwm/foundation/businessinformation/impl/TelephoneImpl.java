/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.businessinformation.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.foundation.businessinformation.BusinessinformationPackage;
import orgomg.cwm.foundation.businessinformation.Contact;
import orgomg.cwm.foundation.businessinformation.Telephone;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Telephone</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.impl.TelephoneImpl#getPhoneNumber <em>Phone Number</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.impl.TelephoneImpl#getPhoneType <em>Phone Type</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.impl.TelephoneImpl#getContact <em>Contact</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TelephoneImpl extends ModelElementImpl implements Telephone {
	/**
	 * The default value of the '{@link #getPhoneNumber() <em>Phone Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhoneNumber()
	 * @generated
	 * @ordered
	 */
	protected static final String PHONE_NUMBER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPhoneNumber() <em>Phone Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhoneNumber()
	 * @generated
	 * @ordered
	 */
	protected String phoneNumber = PHONE_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhoneType() <em>Phone Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhoneType()
	 * @generated
	 * @ordered
	 */
	protected static final String PHONE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPhoneType() <em>Phone Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhoneType()
	 * @generated
	 * @ordered
	 */
	protected String phoneType = PHONE_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContact() <em>Contact</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContact()
	 * @generated
	 * @ordered
	 */
	protected EList<Contact> contact;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TelephoneImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BusinessinformationPackage.Literals.TELEPHONE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhoneNumber(String newPhoneNumber) {
		String oldPhoneNumber = phoneNumber;
		phoneNumber = newPhoneNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BusinessinformationPackage.TELEPHONE__PHONE_NUMBER, oldPhoneNumber, phoneNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPhoneType() {
		return phoneType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhoneType(String newPhoneType) {
		String oldPhoneType = phoneType;
		phoneType = newPhoneType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BusinessinformationPackage.TELEPHONE__PHONE_TYPE, oldPhoneType, phoneType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Contact> getContact() {
		if (contact == null) {
			contact = new EObjectWithInverseResolvingEList.ManyInverse<Contact>(Contact.class, this, BusinessinformationPackage.TELEPHONE__CONTACT, BusinessinformationPackage.CONTACT__TELEPHONE);
		}
		return contact;
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
			case BusinessinformationPackage.TELEPHONE__CONTACT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getContact()).basicAdd(otherEnd, msgs);
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
			case BusinessinformationPackage.TELEPHONE__CONTACT:
				return ((InternalEList<?>)getContact()).basicRemove(otherEnd, msgs);
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
			case BusinessinformationPackage.TELEPHONE__PHONE_NUMBER:
				return getPhoneNumber();
			case BusinessinformationPackage.TELEPHONE__PHONE_TYPE:
				return getPhoneType();
			case BusinessinformationPackage.TELEPHONE__CONTACT:
				return getContact();
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
			case BusinessinformationPackage.TELEPHONE__PHONE_NUMBER:
				setPhoneNumber((String)newValue);
				return;
			case BusinessinformationPackage.TELEPHONE__PHONE_TYPE:
				setPhoneType((String)newValue);
				return;
			case BusinessinformationPackage.TELEPHONE__CONTACT:
				getContact().clear();
				getContact().addAll((Collection<? extends Contact>)newValue);
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
			case BusinessinformationPackage.TELEPHONE__PHONE_NUMBER:
				setPhoneNumber(PHONE_NUMBER_EDEFAULT);
				return;
			case BusinessinformationPackage.TELEPHONE__PHONE_TYPE:
				setPhoneType(PHONE_TYPE_EDEFAULT);
				return;
			case BusinessinformationPackage.TELEPHONE__CONTACT:
				getContact().clear();
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
			case BusinessinformationPackage.TELEPHONE__PHONE_NUMBER:
				return PHONE_NUMBER_EDEFAULT == null ? phoneNumber != null : !PHONE_NUMBER_EDEFAULT.equals(phoneNumber);
			case BusinessinformationPackage.TELEPHONE__PHONE_TYPE:
				return PHONE_TYPE_EDEFAULT == null ? phoneType != null : !PHONE_TYPE_EDEFAULT.equals(phoneType);
			case BusinessinformationPackage.TELEPHONE__CONTACT:
				return contact != null && !contact.isEmpty();
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
		result.append(" (phoneNumber: ");
		result.append(phoneNumber);
		result.append(", phoneType: ");
		result.append(phoneType);
		result.append(')');
		return result.toString();
	}

} //TelephoneImpl
