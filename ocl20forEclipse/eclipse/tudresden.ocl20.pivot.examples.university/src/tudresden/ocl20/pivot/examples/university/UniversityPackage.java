/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.university;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.examples.university.UniversityFactory
 * @model kind="package"
 * @generated
 */
public interface UniversityPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "university";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.emftext.org/language/university";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "university";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UniversityPackage eINSTANCE = tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl.init();

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.examples.university.impl.UniversityImpl <em>University</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityImpl
	 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl#getUniversity()
	 * @generated
	 */
	int UNIVERSITY = 0;

	/**
	 * The feature id for the '<em><b>Students</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIVERSITY__STUDENTS = 0;

	/**
	 * The feature id for the '<em><b>Employees</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIVERSITY__EMPLOYEES = 1;

	/**
	 * The number of structural features of the '<em>University</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIVERSITY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.examples.university.impl.PersonImpl <em>Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.examples.university.impl.PersonImpl
	 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl#getPerson()
	 * @generated
	 */
	int PERSON = 1;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__FIRST_NAME = 0;

	/**
	 * The feature id for the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__LAST_NAME = 1;

	/**
	 * The feature id for the '<em><b>Is Married</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__IS_MARRIED = 2;

	/**
	 * The feature id for the '<em><b>Birth Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__BIRTH_DATE = 3;

	/**
	 * The feature id for the '<em><b>Age</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__AGE = 4;

	/**
	 * The feature id for the '<em><b>Salaries</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__SALARIES = 5;

	/**
	 * The feature id for the '<em><b>Supervisor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__SUPERVISOR = 6;

	/**
	 * The feature id for the '<em><b>Supervised</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__SUPERVISED = 7;

	/**
	 * The number of structural features of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.examples.university.impl.StudentImpl <em>Student</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.examples.university.impl.StudentImpl
	 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl#getStudent()
	 * @generated
	 */
	int STUDENT = 2;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__FIRST_NAME = PERSON__FIRST_NAME;

	/**
	 * The feature id for the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__LAST_NAME = PERSON__LAST_NAME;

	/**
	 * The feature id for the '<em><b>Is Married</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__IS_MARRIED = PERSON__IS_MARRIED;

	/**
	 * The feature id for the '<em><b>Birth Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__BIRTH_DATE = PERSON__BIRTH_DATE;

	/**
	 * The feature id for the '<em><b>Age</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__AGE = PERSON__AGE;

	/**
	 * The feature id for the '<em><b>Salaries</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__SALARIES = PERSON__SALARIES;

	/**
	 * The feature id for the '<em><b>Supervisor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__SUPERVISOR = PERSON__SUPERVISOR;

	/**
	 * The feature id for the '<em><b>Supervised</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__SUPERVISED = PERSON__SUPERVISED;

	/**
	 * The feature id for the '<em><b>Mat Nr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__MAT_NR = PERSON_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Mat Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__MAT_DATE = PERSON_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Student</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_FEATURE_COUNT = PERSON_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.examples.university.impl.EmployeeImpl <em>Employee</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.examples.university.impl.EmployeeImpl
	 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl#getEmployee()
	 * @generated
	 */
	int EMPLOYEE = 3;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE__FIRST_NAME = PERSON__FIRST_NAME;

	/**
	 * The feature id for the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE__LAST_NAME = PERSON__LAST_NAME;

	/**
	 * The feature id for the '<em><b>Is Married</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE__IS_MARRIED = PERSON__IS_MARRIED;

	/**
	 * The feature id for the '<em><b>Birth Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE__BIRTH_DATE = PERSON__BIRTH_DATE;

	/**
	 * The feature id for the '<em><b>Age</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE__AGE = PERSON__AGE;

	/**
	 * The feature id for the '<em><b>Salaries</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE__SALARIES = PERSON__SALARIES;

	/**
	 * The feature id for the '<em><b>Supervisor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE__SUPERVISOR = PERSON__SUPERVISOR;

	/**
	 * The feature id for the '<em><b>Supervised</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE__SUPERVISED = PERSON__SUPERVISED;

	/**
	 * The feature id for the '<em><b>So Sec Nr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE__SO_SEC_NR = PERSON_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tax Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE__TAX_CLASS = PERSON_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Wage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE__WAGE = PERSON_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Employee</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE_FEATURE_COUNT = PERSON_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.examples.university.impl.PhDStudentImpl <em>Ph DStudent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.examples.university.impl.PhDStudentImpl
	 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl#getPhDStudent()
	 * @generated
	 */
	int PH_DSTUDENT = 4;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT__FIRST_NAME = EMPLOYEE__FIRST_NAME;

	/**
	 * The feature id for the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT__LAST_NAME = EMPLOYEE__LAST_NAME;

	/**
	 * The feature id for the '<em><b>Is Married</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT__IS_MARRIED = EMPLOYEE__IS_MARRIED;

	/**
	 * The feature id for the '<em><b>Birth Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT__BIRTH_DATE = EMPLOYEE__BIRTH_DATE;

	/**
	 * The feature id for the '<em><b>Age</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT__AGE = EMPLOYEE__AGE;

	/**
	 * The feature id for the '<em><b>Salaries</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT__SALARIES = EMPLOYEE__SALARIES;

	/**
	 * The feature id for the '<em><b>Supervisor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT__SUPERVISOR = EMPLOYEE__SUPERVISOR;

	/**
	 * The feature id for the '<em><b>Supervised</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT__SUPERVISED = EMPLOYEE__SUPERVISED;

	/**
	 * The feature id for the '<em><b>So Sec Nr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT__SO_SEC_NR = EMPLOYEE__SO_SEC_NR;

	/**
	 * The feature id for the '<em><b>Tax Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT__TAX_CLASS = EMPLOYEE__TAX_CLASS;

	/**
	 * The feature id for the '<em><b>Wage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT__WAGE = EMPLOYEE__WAGE;

	/**
	 * The feature id for the '<em><b>Diss Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT__DISS_SUBJECT = EMPLOYEE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ph DStudent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PH_DSTUDENT_FEATURE_COUNT = EMPLOYEE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '<em>Date</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Date
	 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl#getDate()
	 * @generated
	 */
	int DATE = 5;


	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.examples.university.University <em>University</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>University</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.University
	 * @generated
	 */
	EClass getUniversity();

	/**
	 * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.examples.university.University#getStudents <em>Students</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Students</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.University#getStudents()
	 * @see #getUniversity()
	 * @generated
	 */
	EReference getUniversity_Students();

	/**
	 * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.examples.university.University#getEmployees <em>Employees</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Employees</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.University#getEmployees()
	 * @see #getUniversity()
	 * @generated
	 */
	EReference getUniversity_Employees();

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.examples.university.Person <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Person
	 * @generated
	 */
	EClass getPerson();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.university.Person#getFirstName <em>First Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Name</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Person#getFirstName()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_FirstName();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.university.Person#getLastName <em>Last Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Name</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Person#getLastName()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_LastName();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.university.Person#isIsMarried <em>Is Married</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Married</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Person#isIsMarried()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_IsMarried();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.university.Person#getBirthDate <em>Birth Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Birth Date</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Person#getBirthDate()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_BirthDate();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.university.Person#getAge <em>Age</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Age</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Person#getAge()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_Age();

	/**
	 * Returns the meta object for the attribute list '{@link tudresden.ocl20.pivot.examples.university.Person#getSalaries <em>Salaries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Salaries</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Person#getSalaries()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_Salaries();

	/**
	 * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.examples.university.Person#getSupervisor <em>Supervisor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Supervisor</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Person#getSupervisor()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Supervisor();

	/**
	 * Returns the meta object for the reference list '{@link tudresden.ocl20.pivot.examples.university.Person#getSupervised <em>Supervised</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Supervised</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Person#getSupervised()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Supervised();

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.examples.university.Student <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Student</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Student
	 * @generated
	 */
	EClass getStudent();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.university.Student#getMatNr <em>Mat Nr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mat Nr</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Student#getMatNr()
	 * @see #getStudent()
	 * @generated
	 */
	EAttribute getStudent_MatNr();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.university.Student#getMatDate <em>Mat Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mat Date</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Student#getMatDate()
	 * @see #getStudent()
	 * @generated
	 */
	EAttribute getStudent_MatDate();

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.examples.university.Employee <em>Employee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Employee</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Employee
	 * @generated
	 */
	EClass getEmployee();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.university.Employee#getSoSecNr <em>So Sec Nr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>So Sec Nr</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Employee#getSoSecNr()
	 * @see #getEmployee()
	 * @generated
	 */
	EAttribute getEmployee_SoSecNr();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.university.Employee#getTaxClass <em>Tax Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tax Class</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Employee#getTaxClass()
	 * @see #getEmployee()
	 * @generated
	 */
	EAttribute getEmployee_TaxClass();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.university.Employee#getWage <em>Wage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wage</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.Employee#getWage()
	 * @see #getEmployee()
	 * @generated
	 */
	EAttribute getEmployee_Wage();

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.examples.university.PhDStudent <em>Ph DStudent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ph DStudent</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.PhDStudent
	 * @generated
	 */
	EClass getPhDStudent();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.university.PhDStudent#getDissSubject <em>Diss Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Diss Subject</em>'.
	 * @see tudresden.ocl20.pivot.examples.university.PhDStudent#getDissSubject()
	 * @see #getPhDStudent()
	 * @generated
	 */
	EAttribute getPhDStudent_DissSubject();

	/**
	 * Returns the meta object for data type '{@link java.util.Date <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Date</em>'.
	 * @see java.util.Date
	 * @model instanceClass="java.util.Date"
	 * @generated
	 */
	EDataType getDate();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UniversityFactory getUniversityFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.examples.university.impl.UniversityImpl <em>University</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityImpl
		 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl#getUniversity()
		 * @generated
		 */
		EClass UNIVERSITY = eINSTANCE.getUniversity();

		/**
		 * The meta object literal for the '<em><b>Students</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNIVERSITY__STUDENTS = eINSTANCE.getUniversity_Students();

		/**
		 * The meta object literal for the '<em><b>Employees</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNIVERSITY__EMPLOYEES = eINSTANCE.getUniversity_Employees();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.examples.university.impl.PersonImpl <em>Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.examples.university.impl.PersonImpl
		 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl#getPerson()
		 * @generated
		 */
		EClass PERSON = eINSTANCE.getPerson();

		/**
		 * The meta object literal for the '<em><b>First Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__FIRST_NAME = eINSTANCE.getPerson_FirstName();

		/**
		 * The meta object literal for the '<em><b>Last Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__LAST_NAME = eINSTANCE.getPerson_LastName();

		/**
		 * The meta object literal for the '<em><b>Is Married</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__IS_MARRIED = eINSTANCE.getPerson_IsMarried();

		/**
		 * The meta object literal for the '<em><b>Birth Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__BIRTH_DATE = eINSTANCE.getPerson_BirthDate();

		/**
		 * The meta object literal for the '<em><b>Age</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__AGE = eINSTANCE.getPerson_Age();

		/**
		 * The meta object literal for the '<em><b>Salaries</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__SALARIES = eINSTANCE.getPerson_Salaries();

		/**
		 * The meta object literal for the '<em><b>Supervisor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__SUPERVISOR = eINSTANCE.getPerson_Supervisor();

		/**
		 * The meta object literal for the '<em><b>Supervised</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__SUPERVISED = eINSTANCE.getPerson_Supervised();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.examples.university.impl.StudentImpl <em>Student</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.examples.university.impl.StudentImpl
		 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl#getStudent()
		 * @generated
		 */
		EClass STUDENT = eINSTANCE.getStudent();

		/**
		 * The meta object literal for the '<em><b>Mat Nr</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT__MAT_NR = eINSTANCE.getStudent_MatNr();

		/**
		 * The meta object literal for the '<em><b>Mat Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT__MAT_DATE = eINSTANCE.getStudent_MatDate();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.examples.university.impl.EmployeeImpl <em>Employee</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.examples.university.impl.EmployeeImpl
		 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl#getEmployee()
		 * @generated
		 */
		EClass EMPLOYEE = eINSTANCE.getEmployee();

		/**
		 * The meta object literal for the '<em><b>So Sec Nr</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMPLOYEE__SO_SEC_NR = eINSTANCE.getEmployee_SoSecNr();

		/**
		 * The meta object literal for the '<em><b>Tax Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMPLOYEE__TAX_CLASS = eINSTANCE.getEmployee_TaxClass();

		/**
		 * The meta object literal for the '<em><b>Wage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMPLOYEE__WAGE = eINSTANCE.getEmployee_Wage();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.examples.university.impl.PhDStudentImpl <em>Ph DStudent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.examples.university.impl.PhDStudentImpl
		 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl#getPhDStudent()
		 * @generated
		 */
		EClass PH_DSTUDENT = eINSTANCE.getPhDStudent();

		/**
		 * The meta object literal for the '<em><b>Diss Subject</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PH_DSTUDENT__DISS_SUBJECT = eINSTANCE.getPhDStudent_DissSubject();

		/**
		 * The meta object literal for the '<em>Date</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Date
		 * @see tudresden.ocl20.pivot.examples.university.impl.UniversityPackageImpl#getDate()
		 * @generated
		 */
		EDataType DATE = eINSTANCE.getDate();

	}

} //UniversityPackage
