/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import orgomg.cwm.management.warehouseprocess.events.CalendarDate;
import orgomg.cwm.management.warehouseprocess.events.EventsPackage;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Calendar Date</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.impl.CalendarDateImpl#getSpecificDate <em>Specific Date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CalendarDateImpl extends ModelElementImpl implements CalendarDate {
	/**
	 * The default value of the '{@link #getSpecificDate() <em>Specific Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecificDate()
	 * @generated
	 * @ordered
	 */
	protected static final String SPECIFIC_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSpecificDate() <em>Specific Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecificDate()
	 * @generated
	 * @ordered
	 */
	protected String specificDate = SPECIFIC_DATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CalendarDateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EventsPackage.Literals.CALENDAR_DATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpecificDate() {
		return specificDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpecificDate(String newSpecificDate) {
		String oldSpecificDate = specificDate;
		specificDate = newSpecificDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EventsPackage.CALENDAR_DATE__SPECIFIC_DATE, oldSpecificDate, specificDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EventsPackage.CALENDAR_DATE__SPECIFIC_DATE:
				return getSpecificDate();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EventsPackage.CALENDAR_DATE__SPECIFIC_DATE:
				setSpecificDate((String)newValue);
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
			case EventsPackage.CALENDAR_DATE__SPECIFIC_DATE:
				setSpecificDate(SPECIFIC_DATE_EDEFAULT);
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
			case EventsPackage.CALENDAR_DATE__SPECIFIC_DATE:
				return SPECIFIC_DATE_EDEFAULT == null ? specificDate != null : !SPECIFIC_DATE_EDEFAULT.equals(specificDate);
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
		result.append(" (specificDate: ");
		result.append(specificDate);
		result.append(')');
		return result.toString();
	}

} //CalendarDateImpl
