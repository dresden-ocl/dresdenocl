/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.university.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.examples.university.Employee;
import tudresden.ocl20.pivot.examples.university.Student;
import tudresden.ocl20.pivot.examples.university.University;
import tudresden.ocl20.pivot.examples.university.UniversityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>University</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.UniversityImpl#getStudents <em>Students</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.UniversityImpl#getEmployees <em>Employees</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UniversityImpl extends EObjectImpl implements University {
	/**
	 * The cached value of the '{@link #getStudents() <em>Students</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStudents()
	 * @generated
	 * @ordered
	 */
	protected EList<Student> students;

	/**
	 * The cached value of the '{@link #getEmployees() <em>Employees</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmployees()
	 * @generated
	 * @ordered
	 */
	protected EList<Employee> employees;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UniversityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UniversityPackage.Literals.UNIVERSITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Student> getStudents() {
		if (students == null) {
			students = new EObjectContainmentEList<Student>(Student.class, this, UniversityPackage.UNIVERSITY__STUDENTS);
		}
		return students;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Employee> getEmployees() {
		if (employees == null) {
			employees = new EObjectContainmentEList<Employee>(Employee.class, this, UniversityPackage.UNIVERSITY__EMPLOYEES);
		}
		return employees;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UniversityPackage.UNIVERSITY__STUDENTS:
				return ((InternalEList<?>)getStudents()).basicRemove(otherEnd, msgs);
			case UniversityPackage.UNIVERSITY__EMPLOYEES:
				return ((InternalEList<?>)getEmployees()).basicRemove(otherEnd, msgs);
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
			case UniversityPackage.UNIVERSITY__STUDENTS:
				return getStudents();
			case UniversityPackage.UNIVERSITY__EMPLOYEES:
				return getEmployees();
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
			case UniversityPackage.UNIVERSITY__STUDENTS:
				getStudents().clear();
				getStudents().addAll((Collection<? extends Student>)newValue);
				return;
			case UniversityPackage.UNIVERSITY__EMPLOYEES:
				getEmployees().clear();
				getEmployees().addAll((Collection<? extends Employee>)newValue);
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
			case UniversityPackage.UNIVERSITY__STUDENTS:
				getStudents().clear();
				return;
			case UniversityPackage.UNIVERSITY__EMPLOYEES:
				getEmployees().clear();
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
			case UniversityPackage.UNIVERSITY__STUDENTS:
				return students != null && !students.isEmpty();
			case UniversityPackage.UNIVERSITY__EMPLOYEES:
				return employees != null && !employees.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UniversityImpl
