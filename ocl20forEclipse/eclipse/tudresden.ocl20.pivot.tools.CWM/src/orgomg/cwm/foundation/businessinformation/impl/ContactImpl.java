/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.businessinformation.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.foundation.businessinformation.BusinessinformationPackage;
import orgomg.cwm.foundation.businessinformation.Contact;
import orgomg.cwm.foundation.businessinformation.Email;
import orgomg.cwm.foundation.businessinformation.Location;
import orgomg.cwm.foundation.businessinformation.ResourceLocator;
import orgomg.cwm.foundation.businessinformation.Telephone;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contact</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.impl.ContactImpl#getEmail <em>Email</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.impl.ContactImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.impl.ContactImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.impl.ContactImpl#getTelephone <em>Telephone</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContactImpl extends ModelElementImpl implements Contact {
	/**
	 * The cached value of the '{@link #getEmail() <em>Email</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmail()
	 * @generated
	 * @ordered
	 */
	protected EList<Email> email;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected EList<Location> location;

	/**
	 * The cached value of the '{@link #getUrl() <em>Url</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceLocator> url;

	/**
	 * The cached value of the '{@link #getTelephone() <em>Telephone</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTelephone()
	 * @generated
	 * @ordered
	 */
	protected EList<Telephone> telephone;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContactImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BusinessinformationPackage.Literals.CONTACT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Email> getEmail() {
		if (email == null) {
			email = new EObjectWithInverseResolvingEList.ManyInverse<Email>(Email.class, this, BusinessinformationPackage.CONTACT__EMAIL, BusinessinformationPackage.EMAIL__CONTACT);
		}
		return email;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Location> getLocation() {
		if (location == null) {
			location = new EObjectWithInverseResolvingEList.ManyInverse<Location>(Location.class, this, BusinessinformationPackage.CONTACT__LOCATION, BusinessinformationPackage.LOCATION__CONTACT);
		}
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceLocator> getUrl() {
		if (url == null) {
			url = new EObjectWithInverseResolvingEList.ManyInverse<ResourceLocator>(ResourceLocator.class, this, BusinessinformationPackage.CONTACT__URL, BusinessinformationPackage.RESOURCE_LOCATOR__CONTACT);
		}
		return url;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Telephone> getTelephone() {
		if (telephone == null) {
			telephone = new EObjectWithInverseResolvingEList.ManyInverse<Telephone>(Telephone.class, this, BusinessinformationPackage.CONTACT__TELEPHONE, BusinessinformationPackage.TELEPHONE__CONTACT);
		}
		return telephone;
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
			case BusinessinformationPackage.CONTACT__EMAIL:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEmail()).basicAdd(otherEnd, msgs);
			case BusinessinformationPackage.CONTACT__LOCATION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLocation()).basicAdd(otherEnd, msgs);
			case BusinessinformationPackage.CONTACT__URL:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUrl()).basicAdd(otherEnd, msgs);
			case BusinessinformationPackage.CONTACT__TELEPHONE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTelephone()).basicAdd(otherEnd, msgs);
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
			case BusinessinformationPackage.CONTACT__EMAIL:
				return ((InternalEList<?>)getEmail()).basicRemove(otherEnd, msgs);
			case BusinessinformationPackage.CONTACT__LOCATION:
				return ((InternalEList<?>)getLocation()).basicRemove(otherEnd, msgs);
			case BusinessinformationPackage.CONTACT__URL:
				return ((InternalEList<?>)getUrl()).basicRemove(otherEnd, msgs);
			case BusinessinformationPackage.CONTACT__TELEPHONE:
				return ((InternalEList<?>)getTelephone()).basicRemove(otherEnd, msgs);
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
			case BusinessinformationPackage.CONTACT__EMAIL:
				return getEmail();
			case BusinessinformationPackage.CONTACT__LOCATION:
				return getLocation();
			case BusinessinformationPackage.CONTACT__URL:
				return getUrl();
			case BusinessinformationPackage.CONTACT__TELEPHONE:
				return getTelephone();
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
			case BusinessinformationPackage.CONTACT__EMAIL:
				getEmail().clear();
				getEmail().addAll((Collection<? extends Email>)newValue);
				return;
			case BusinessinformationPackage.CONTACT__LOCATION:
				getLocation().clear();
				getLocation().addAll((Collection<? extends Location>)newValue);
				return;
			case BusinessinformationPackage.CONTACT__URL:
				getUrl().clear();
				getUrl().addAll((Collection<? extends ResourceLocator>)newValue);
				return;
			case BusinessinformationPackage.CONTACT__TELEPHONE:
				getTelephone().clear();
				getTelephone().addAll((Collection<? extends Telephone>)newValue);
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
			case BusinessinformationPackage.CONTACT__EMAIL:
				getEmail().clear();
				return;
			case BusinessinformationPackage.CONTACT__LOCATION:
				getLocation().clear();
				return;
			case BusinessinformationPackage.CONTACT__URL:
				getUrl().clear();
				return;
			case BusinessinformationPackage.CONTACT__TELEPHONE:
				getTelephone().clear();
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
			case BusinessinformationPackage.CONTACT__EMAIL:
				return email != null && !email.isEmpty();
			case BusinessinformationPackage.CONTACT__LOCATION:
				return location != null && !location.isEmpty();
			case BusinessinformationPackage.CONTACT__URL:
				return url != null && !url.isEmpty();
			case BusinessinformationPackage.CONTACT__TELEPHONE:
				return telephone != null && !telephone.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ContactImpl
