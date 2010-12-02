/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.university;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ph DStudent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.PhDStudent#getDissSubject <em>Diss Subject</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getPhDStudent()
 * @model
 * @generated
 */
public interface PhDStudent extends Employee {
	/**
	 * Returns the value of the '<em><b>Diss Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diss Subject</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diss Subject</em>' attribute.
	 * @see #setDissSubject(String)
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getPhDStudent_DissSubject()
	 * @model required="true"
	 * @generated
	 */
	String getDissSubject();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.university.PhDStudent#getDissSubject <em>Diss Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diss Subject</em>' attribute.
	 * @see #getDissSubject()
	 * @generated
	 */
	void setDissSubject(String value);

} // PhDStudent
