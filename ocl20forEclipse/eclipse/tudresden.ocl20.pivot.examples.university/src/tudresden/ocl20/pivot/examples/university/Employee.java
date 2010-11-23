/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.university;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Employee</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Employee#getSoSecNr <em>So Sec Nr</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Employee#getTaxClass <em>Tax Class</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Employee#getWage <em>Wage</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getEmployee()
 * @model
 * @generated
 */
public interface Employee extends Person {
	/**
	 * Returns the value of the '<em><b>So Sec Nr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>So Sec Nr</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>So Sec Nr</em>' attribute.
	 * @see #setSoSecNr(String)
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getEmployee_SoSecNr()
	 * @model required="true"
	 * @generated
	 */
	String getSoSecNr();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.university.Employee#getSoSecNr <em>So Sec Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>So Sec Nr</em>' attribute.
	 * @see #getSoSecNr()
	 * @generated
	 */
	void setSoSecNr(String value);

	/**
	 * Returns the value of the '<em><b>Tax Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tax Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tax Class</em>' attribute.
	 * @see #setTaxClass(String)
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getEmployee_TaxClass()
	 * @model required="true"
	 * @generated
	 */
	String getTaxClass();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.university.Employee#getTaxClass <em>Tax Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tax Class</em>' attribute.
	 * @see #getTaxClass()
	 * @generated
	 */
	void setTaxClass(String value);

	/**
	 * Returns the value of the '<em><b>Wage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wage</em>' attribute.
	 * @see #setWage(int)
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getEmployee_Wage()
	 * @model required="true"
	 * @generated
	 */
	int getWage();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.university.Employee#getWage <em>Wage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wage</em>' attribute.
	 * @see #getWage()
	 * @generated
	 */
	void setWage(int value);

} // Employee
