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

import tudresden.ocl20.pivot.examples.university.PhDStudent;
import tudresden.ocl20.pivot.examples.university.UniversityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ph DStudent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.PhDStudentImpl#getDissSubject <em>Diss Subject</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PhDStudentImpl extends EmployeeImpl implements PhDStudent {
	/**
	 * The default value of the '{@link #getDissSubject() <em>Diss Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDissSubject()
	 * @generated
	 * @ordered
	 */
	protected static final String DISS_SUBJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDissSubject() <em>Diss Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDissSubject()
	 * @generated
	 * @ordered
	 */
	protected String dissSubject = DISS_SUBJECT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PhDStudentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UniversityPackage.Literals.PH_DSTUDENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDissSubject() {
		return dissSubject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDissSubject(String newDissSubject) {
		String oldDissSubject = dissSubject;
		dissSubject = newDissSubject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UniversityPackage.PH_DSTUDENT__DISS_SUBJECT, oldDissSubject, dissSubject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UniversityPackage.PH_DSTUDENT__DISS_SUBJECT:
				return getDissSubject();
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
			case UniversityPackage.PH_DSTUDENT__DISS_SUBJECT:
				setDissSubject((String)newValue);
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
			case UniversityPackage.PH_DSTUDENT__DISS_SUBJECT:
				setDissSubject(DISS_SUBJECT_EDEFAULT);
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
			case UniversityPackage.PH_DSTUDENT__DISS_SUBJECT:
				return DISS_SUBJECT_EDEFAULT == null ? dissSubject != null : !DISS_SUBJECT_EDEFAULT.equals(dissSubject);
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
		result.append("PhDStudent(");
		result.append(firstName);
		result.append(" ");
		result.append(lastName);
		result.append(')');
		return result.toString();
	}

} //PhDStudentImpl
