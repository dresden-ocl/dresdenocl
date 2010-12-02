/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.university;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Person#getFirstName <em>First Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Person#getLastName <em>Last Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Person#isIsMarried <em>Is Married</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Person#getBirthDate <em>Birth Date</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Person#getAge <em>Age</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Person#getSalaries <em>Salaries</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Person#getSupervisor <em>Supervisor</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.Person#getSupervised <em>Supervised</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getPerson()
 * @model
 * @generated
 */
public interface Person extends EObject {
	/**
	 * Returns the value of the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Name</em>' attribute.
	 * @see #setFirstName(String)
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getPerson_FirstName()
	 * @model required="true"
	 * @generated
	 */
	String getFirstName();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.university.Person#getFirstName <em>First Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Name</em>' attribute.
	 * @see #getFirstName()
	 * @generated
	 */
	void setFirstName(String value);

	/**
	 * Returns the value of the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Name</em>' attribute.
	 * @see #setLastName(String)
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getPerson_LastName()
	 * @model required="true"
	 * @generated
	 */
	String getLastName();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.university.Person#getLastName <em>Last Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Name</em>' attribute.
	 * @see #getLastName()
	 * @generated
	 */
	void setLastName(String value);

	/**
	 * Returns the value of the '<em><b>Is Married</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Married</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Married</em>' attribute.
	 * @see #setIsMarried(boolean)
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getPerson_IsMarried()
	 * @model required="true"
	 * @generated
	 */
	boolean isIsMarried();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.university.Person#isIsMarried <em>Is Married</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Married</em>' attribute.
	 * @see #isIsMarried()
	 * @generated
	 */
	void setIsMarried(boolean value);

	/**
	 * Returns the value of the '<em><b>Birth Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Birth Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Birth Date</em>' attribute.
	 * @see #setBirthDate(Date)
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getPerson_BirthDate()
	 * @model dataType="tudresden.ocl20.pivot.examples.university.Date" required="true"
	 * @generated
	 */
	Date getBirthDate();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.university.Person#getBirthDate <em>Birth Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Birth Date</em>' attribute.
	 * @see #getBirthDate()
	 * @generated
	 */
	void setBirthDate(Date value);

	/**
	 * Returns the value of the '<em><b>Age</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Age</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Age</em>' attribute.
	 * @see #setAge(int)
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getPerson_Age()
	 * @model required="true"
	 * @generated
	 */
	int getAge();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.university.Person#getAge <em>Age</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Age</em>' attribute.
	 * @see #getAge()
	 * @generated
	 */
	void setAge(int value);

	/**
	 * Returns the value of the '<em><b>Salaries</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Salaries</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Salaries</em>' attribute list.
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getPerson_Salaries()
	 * @model required="true"
	 * @generated
	 */
	EList<Integer> getSalaries();

	/**
	 * Returns the value of the '<em><b>Supervisor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link tudresden.ocl20.pivot.examples.university.Person#getSupervised <em>Supervised</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supervisor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supervisor</em>' reference.
	 * @see #setSupervisor(Person)
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getPerson_Supervisor()
	 * @see tudresden.ocl20.pivot.examples.university.Person#getSupervised
	 * @model opposite="supervised"
	 * @generated
	 */
	Person getSupervisor();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.university.Person#getSupervisor <em>Supervisor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supervisor</em>' reference.
	 * @see #getSupervisor()
	 * @generated
	 */
	void setSupervisor(Person value);

	/**
	 * Returns the value of the '<em><b>Supervised</b></em>' reference list.
	 * The list contents are of type {@link tudresden.ocl20.pivot.examples.university.Person}.
	 * It is bidirectional and its opposite is '{@link tudresden.ocl20.pivot.examples.university.Person#getSupervisor <em>Supervisor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supervised</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supervised</em>' reference list.
	 * @see tudresden.ocl20.pivot.examples.university.UniversityPackage#getPerson_Supervised()
	 * @see tudresden.ocl20.pivot.examples.university.Person#getSupervisor
	 * @model opposite="supervisor"
	 * @generated
	 */
	EList<Person> getSupervised();

} // Person
