/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.university;

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Student</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Student#getMatNr <em>Mat Nr</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Student#getMatDate <em>Mat Date</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getStudent()
 * @model
 * @generated
 */
public interface Student extends Person {
	/**
	 * Returns the value of the '<em><b>Mat Nr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mat Nr</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mat Nr</em>' attribute.
	 * @see #setMatNr(int)
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getStudent_MatNr()
	 * @model required="true"
	 * @generated
	 */
	int getMatNr();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.university.Student#getMatNr <em>Mat Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mat Nr</em>' attribute.
	 * @see #getMatNr()
	 * @generated
	 */
	void setMatNr(int value);

	/**
	 * Returns the value of the '<em><b>Mat Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mat Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mat Date</em>' attribute.
	 * @see #setMatDate(Date)
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getStudent_MatDate()
	 * @model dataType="tudresden.ocl20.pivot.examples.university.Date" required="true"
	 * @generated
	 */
	Date getMatDate();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.university.Student#getMatDate <em>Mat Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mat Date</em>' attribute.
	 * @see #getMatDate()
	 * @generated
	 */
	void setMatDate(Date value);

} // Student
