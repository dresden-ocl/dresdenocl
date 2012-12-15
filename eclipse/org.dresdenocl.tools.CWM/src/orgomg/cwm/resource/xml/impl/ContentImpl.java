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

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

import orgomg.cwm.resource.xml.Content;
import orgomg.cwm.resource.xml.ContentType;
import orgomg.cwm.resource.xml.ElementType;
import orgomg.cwm.resource.xml.ElementTypeReference;
import orgomg.cwm.resource.xml.OccurrenceType;
import orgomg.cwm.resource.xml.XmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.xml.impl.ContentImpl#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.resource.xml.impl.ContentImpl#getOccurrence <em>Occurrence</em>}</li>
 *   <li>{@link orgomg.cwm.resource.xml.impl.ContentImpl#getElementType <em>Element Type</em>}</li>
 *   <li>{@link orgomg.cwm.resource.xml.impl.ContentImpl#getOwnedElementType <em>Owned Element Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContentImpl extends ModelElementImpl implements Content {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final ContentType TYPE_EDEFAULT = ContentType.XML_EMPTY;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ContentType type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOccurrence() <em>Occurrence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOccurrence()
	 * @generated
	 * @ordered
	 */
	protected static final OccurrenceType OCCURRENCE_EDEFAULT = OccurrenceType.XML_ONE;

	/**
	 * The cached value of the '{@link #getOccurrence() <em>Occurrence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOccurrence()
	 * @generated
	 * @ordered
	 */
	protected OccurrenceType occurrence = OCCURRENCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getElementType() <em>Element Type</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementType()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementType> elementType;

	/**
	 * The cached value of the '{@link #getOwnedElementType() <em>Owned Element Type</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedElementType()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementTypeReference> ownedElementType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XmlPackage.Literals.CONTENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContentType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(ContentType newType) {
		ContentType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XmlPackage.CONTENT__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OccurrenceType getOccurrence() {
		return occurrence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOccurrence(OccurrenceType newOccurrence) {
		OccurrenceType oldOccurrence = occurrence;
		occurrence = newOccurrence == null ? OCCURRENCE_EDEFAULT : newOccurrence;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XmlPackage.CONTENT__OCCURRENCE, oldOccurrence, occurrence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ElementType> getElementType() {
		if (elementType == null) {
			elementType = new EObjectWithInverseResolvingEList<ElementType>(ElementType.class, this, XmlPackage.CONTENT__ELEMENT_TYPE, XmlPackage.ELEMENT_TYPE__CONTENT);
		}
		return elementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ElementTypeReference> getOwnedElementType() {
		if (ownedElementType == null) {
			ownedElementType = new EObjectWithInverseResolvingEList.ManyInverse<ElementTypeReference>(ElementTypeReference.class, this, XmlPackage.CONTENT__OWNED_ELEMENT_TYPE, XmlPackage.ELEMENT_TYPE_REFERENCE__OWNER_CONTENT);
		}
		return ownedElementType;
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
			case XmlPackage.CONTENT__ELEMENT_TYPE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getElementType()).basicAdd(otherEnd, msgs);
			case XmlPackage.CONTENT__OWNED_ELEMENT_TYPE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedElementType()).basicAdd(otherEnd, msgs);
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
			case XmlPackage.CONTENT__ELEMENT_TYPE:
				return ((InternalEList<?>)getElementType()).basicRemove(otherEnd, msgs);
			case XmlPackage.CONTENT__OWNED_ELEMENT_TYPE:
				return ((InternalEList<?>)getOwnedElementType()).basicRemove(otherEnd, msgs);
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
			case XmlPackage.CONTENT__TYPE:
				return getType();
			case XmlPackage.CONTENT__OCCURRENCE:
				return getOccurrence();
			case XmlPackage.CONTENT__ELEMENT_TYPE:
				return getElementType();
			case XmlPackage.CONTENT__OWNED_ELEMENT_TYPE:
				return getOwnedElementType();
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
			case XmlPackage.CONTENT__TYPE:
				setType((ContentType)newValue);
				return;
			case XmlPackage.CONTENT__OCCURRENCE:
				setOccurrence((OccurrenceType)newValue);
				return;
			case XmlPackage.CONTENT__ELEMENT_TYPE:
				getElementType().clear();
				getElementType().addAll((Collection<? extends ElementType>)newValue);
				return;
			case XmlPackage.CONTENT__OWNED_ELEMENT_TYPE:
				getOwnedElementType().clear();
				getOwnedElementType().addAll((Collection<? extends ElementTypeReference>)newValue);
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
			case XmlPackage.CONTENT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case XmlPackage.CONTENT__OCCURRENCE:
				setOccurrence(OCCURRENCE_EDEFAULT);
				return;
			case XmlPackage.CONTENT__ELEMENT_TYPE:
				getElementType().clear();
				return;
			case XmlPackage.CONTENT__OWNED_ELEMENT_TYPE:
				getOwnedElementType().clear();
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
			case XmlPackage.CONTENT__TYPE:
				return type != TYPE_EDEFAULT;
			case XmlPackage.CONTENT__OCCURRENCE:
				return occurrence != OCCURRENCE_EDEFAULT;
			case XmlPackage.CONTENT__ELEMENT_TYPE:
				return elementType != null && !elementType.isEmpty();
			case XmlPackage.CONTENT__OWNED_ELEMENT_TYPE:
				return ownedElementType != null && !ownedElementType.isEmpty();
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
		result.append(" (type: ");
		result.append(type);
		result.append(", occurrence: ");
		result.append(occurrence);
		result.append(')');
		return result.toString();
	}

} //ContentImpl
