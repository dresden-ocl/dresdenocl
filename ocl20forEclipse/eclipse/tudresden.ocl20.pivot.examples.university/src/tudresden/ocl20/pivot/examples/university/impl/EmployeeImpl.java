/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.university.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.examples.university.Employee;
import tudresden.ocl20.pivot.examples.university.UniversityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Employee</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.EmployeeImpl#getSoSecNr <em>So Sec Nr</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.EmployeeImpl#getTaxClass <em>Tax Class</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.EmployeeImpl#getWage <em>Wage</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EmployeeImpl extends PersonImpl implements Employee {
	/**
	 * The default value of the '{@link #getSoSecNr() <em>So Sec Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoSecNr()
	 * @generated
	 * @ordered
	 */
	protected static final String SO_SEC_NR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSoSecNr() <em>So Sec Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoSecNr()
	 * @generated
	 * @ordered
	 */
	protected String soSecNr = SO_SEC_NR_EDEFAULT;

	/**
	 * The default value of the '{@link #getTaxClass() <em>Tax Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaxClass()
	 * @generated
	 * @ordered
	 */
	protected static final String TAX_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTaxClass() <em>Tax Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaxClass()
	 * @generated
	 * @ordered
	 */
	protected String taxClass = TAX_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getWage() <em>Wage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWage()
	 * @generated
	 * @ordered
	 */
	protected static final int WAGE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getWage() <em>Wage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWage()
	 * @generated
	 * @ordered
	 */
	protected int wage = WAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EmployeeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UniversityPackage.Literals.EMPLOYEE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSoSecNr() {
		return soSecNr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSoSecNr(String newSoSecNr) {
		String oldSoSecNr = soSecNr;
		soSecNr = newSoSecNr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UniversityPackage.EMPLOYEE__SO_SEC_NR, oldSoSecNr, soSecNr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTaxClass() {
		return taxClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaxClass(String newTaxClass) {
		String oldTaxClass = taxClass;
		taxClass = newTaxClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UniversityPackage.EMPLOYEE__TAX_CLASS, oldTaxClass, taxClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWage() {
		return wage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWage(int newWage) {
		int oldWage = wage;
		wage = newWage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UniversityPackage.EMPLOYEE__WAGE, oldWage, wage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UniversityPackage.EMPLOYEE__SO_SEC_NR:
				return getSoSecNr();
			case UniversityPackage.EMPLOYEE__TAX_CLASS:
				return getTaxClass();
			case UniversityPackage.EMPLOYEE__WAGE:
				return getWage();
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
			case UniversityPackage.EMPLOYEE__SO_SEC_NR:
				setSoSecNr((String)newValue);
				return;
			case UniversityPackage.EMPLOYEE__TAX_CLASS:
				setTaxClass((String)newValue);
				return;
			case UniversityPackage.EMPLOYEE__WAGE:
				setWage((Integer)newValue);
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
			case UniversityPackage.EMPLOYEE__SO_SEC_NR:
				setSoSecNr(SO_SEC_NR_EDEFAULT);
				return;
			case UniversityPackage.EMPLOYEE__TAX_CLASS:
				setTaxClass(TAX_CLASS_EDEFAULT);
				return;
			case UniversityPackage.EMPLOYEE__WAGE:
				setWage(WAGE_EDEFAULT);
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
			case UniversityPackage.EMPLOYEE__SO_SEC_NR:
				return SO_SEC_NR_EDEFAULT == null ? soSecNr != null : !SO_SEC_NR_EDEFAULT.equals(soSecNr);
			case UniversityPackage.EMPLOYEE__TAX_CLASS:
				return TAX_CLASS_EDEFAULT == null ? taxClass != null : !TAX_CLASS_EDEFAULT.equals(taxClass);
			case UniversityPackage.EMPLOYEE__WAGE:
				return wage != WAGE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer();
		result.append("Employee(");
		result.append(firstName);
		result.append(" ");
		result.append(lastName);
		result.append(')');
		return result.toString();
	}

} //EmployeeImpl
