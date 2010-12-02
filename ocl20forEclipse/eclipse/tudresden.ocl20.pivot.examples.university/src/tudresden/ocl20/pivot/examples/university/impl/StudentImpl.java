/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.university.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.examples.university.Student;
import tudresden.ocl20.pivot.examples.university.UniversityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Student</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.StudentImpl#getMatNr <em>Mat Nr</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.StudentImpl#getMatDate <em>Mat Date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StudentImpl extends PersonImpl implements Student {
	/**
	 * The default value of the '{@link #getMatNr() <em>Mat Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatNr()
	 * @generated
	 * @ordered
	 */
	protected static final int MAT_NR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMatNr() <em>Mat Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatNr()
	 * @generated
	 * @ordered
	 */
	protected int matNr = MAT_NR_EDEFAULT;

	/**
	 * The default value of the '{@link #getMatDate() <em>Mat Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date MAT_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMatDate() <em>Mat Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatDate()
	 * @generated
	 * @ordered
	 */
	protected Date matDate = MAT_DATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StudentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UniversityPackage.Literals.STUDENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMatNr() {
		return matNr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMatNr(int newMatNr) {
		int oldMatNr = matNr;
		matNr = newMatNr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UniversityPackage.STUDENT__MAT_NR, oldMatNr, matNr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getMatDate() {
		return matDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMatDate(Date newMatDate) {
		Date oldMatDate = matDate;
		matDate = newMatDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UniversityPackage.STUDENT__MAT_DATE, oldMatDate, matDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UniversityPackage.STUDENT__MAT_NR:
				return getMatNr();
			case UniversityPackage.STUDENT__MAT_DATE:
				return getMatDate();
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
			case UniversityPackage.STUDENT__MAT_NR:
				setMatNr((Integer)newValue);
				return;
			case UniversityPackage.STUDENT__MAT_DATE:
				setMatDate((Date)newValue);
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
			case UniversityPackage.STUDENT__MAT_NR:
				setMatNr(MAT_NR_EDEFAULT);
				return;
			case UniversityPackage.STUDENT__MAT_DATE:
				setMatDate(MAT_DATE_EDEFAULT);
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
			case UniversityPackage.STUDENT__MAT_NR:
				return matNr != MAT_NR_EDEFAULT;
			case UniversityPackage.STUDENT__MAT_DATE:
				return MAT_DATE_EDEFAULT == null ? matDate != null : !MAT_DATE_EDEFAULT.equals(matDate);
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
		result.append("Student(");
		result.append(firstName);
		result.append(" ");
		result.append(lastName);
		result.append(')');
		return result.toString();
	}

} //StudentImpl
