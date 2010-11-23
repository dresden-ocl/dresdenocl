/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.university.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.examples.university.Person;
import tudresden.ocl20.pivot.examples.university.UniversityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.PersonImpl#getFirstName <em>First Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.PersonImpl#getLastName <em>Last Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.PersonImpl#isIsMarried <em>Is Married</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.PersonImpl#getBirthDate <em>Birth Date</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.PersonImpl#getAge <em>Age</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.PersonImpl#getSalaries <em>Salaries</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.PersonImpl#getSupervisor <em>Supervisor</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.university.impl.PersonImpl#getSupervised <em>Supervised</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PersonImpl extends EObjectImpl implements Person {
	/**
	 * The default value of the '{@link #getFirstName() <em>First Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstName()
	 * @generated
	 * @ordered
	 */
	protected static final String FIRST_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFirstName() <em>First Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstName()
	 * @generated
	 * @ordered
	 */
	protected String firstName = FIRST_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastName() <em>Last Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastName()
	 * @generated
	 * @ordered
	 */
	protected static final String LAST_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastName() <em>Last Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastName()
	 * @generated
	 * @ordered
	 */
	protected String lastName = LAST_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsMarried() <em>Is Married</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMarried()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_MARRIED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsMarried() <em>Is Married</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMarried()
	 * @generated
	 * @ordered
	 */
	protected boolean isMarried = IS_MARRIED_EDEFAULT;

	/**
	 * The default value of the '{@link #getBirthDate() <em>Birth Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBirthDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date BIRTH_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBirthDate() <em>Birth Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBirthDate()
	 * @generated
	 * @ordered
	 */
	protected Date birthDate = BIRTH_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getAge() <em>Age</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAge()
	 * @generated
	 * @ordered
	 */
	protected static final int AGE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAge() <em>Age</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAge()
	 * @generated
	 * @ordered
	 */
	protected int age = AGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSalaries() <em>Salaries</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSalaries()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> salaries;

	/**
	 * The cached value of the '{@link #getSupervisor() <em>Supervisor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupervisor()
	 * @generated
	 * @ordered
	 */
	protected Person supervisor;

	/**
	 * The cached value of the '{@link #getSupervised() <em>Supervised</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupervised()
	 * @generated
	 * @ordered
	 */
	protected EList<Person> supervised;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UniversityPackage.Literals.PERSON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirstName(String newFirstName) {
		String oldFirstName = firstName;
		firstName = newFirstName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UniversityPackage.PERSON__FIRST_NAME, oldFirstName, firstName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastName(String newLastName) {
		String oldLastName = lastName;
		lastName = newLastName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UniversityPackage.PERSON__LAST_NAME, oldLastName, lastName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsMarried() {
		return isMarried;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsMarried(boolean newIsMarried) {
		boolean oldIsMarried = isMarried;
		isMarried = newIsMarried;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UniversityPackage.PERSON__IS_MARRIED, oldIsMarried, isMarried));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBirthDate(Date newBirthDate) {
		Date oldBirthDate = birthDate;
		birthDate = newBirthDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UniversityPackage.PERSON__BIRTH_DATE, oldBirthDate, birthDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getAge() {
		return age;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAge(int newAge) {
		int oldAge = age;
		age = newAge;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UniversityPackage.PERSON__AGE, oldAge, age));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getSalaries() {
		if (salaries == null) {
			salaries = new EDataTypeUniqueEList<Integer>(Integer.class, this, UniversityPackage.PERSON__SALARIES);
		}
		return salaries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Person getSupervisor() {
		if (supervisor != null && supervisor.eIsProxy()) {
			InternalEObject oldSupervisor = (InternalEObject)supervisor;
			supervisor = (Person)eResolveProxy(oldSupervisor);
			if (supervisor != oldSupervisor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UniversityPackage.PERSON__SUPERVISOR, oldSupervisor, supervisor));
			}
		}
		return supervisor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Person basicGetSupervisor() {
		return supervisor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSupervisor(Person newSupervisor, NotificationChain msgs) {
		Person oldSupervisor = supervisor;
		supervisor = newSupervisor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UniversityPackage.PERSON__SUPERVISOR, oldSupervisor, newSupervisor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupervisor(Person newSupervisor) {
		if (newSupervisor != supervisor) {
			NotificationChain msgs = null;
			if (supervisor != null)
				msgs = ((InternalEObject)supervisor).eInverseRemove(this, UniversityPackage.PERSON__SUPERVISED, Person.class, msgs);
			if (newSupervisor != null)
				msgs = ((InternalEObject)newSupervisor).eInverseAdd(this, UniversityPackage.PERSON__SUPERVISED, Person.class, msgs);
			msgs = basicSetSupervisor(newSupervisor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UniversityPackage.PERSON__SUPERVISOR, newSupervisor, newSupervisor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Person> getSupervised() {
		if (supervised == null) {
			supervised = new EObjectWithInverseResolvingEList<Person>(Person.class, this, UniversityPackage.PERSON__SUPERVISED, UniversityPackage.PERSON__SUPERVISOR);
		}
		return supervised;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UniversityPackage.PERSON__SUPERVISOR:
				if (supervisor != null)
					msgs = ((InternalEObject)supervisor).eInverseRemove(this, UniversityPackage.PERSON__SUPERVISED, Person.class, msgs);
				return basicSetSupervisor((Person)otherEnd, msgs);
			case UniversityPackage.PERSON__SUPERVISED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSupervised()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UniversityPackage.PERSON__SUPERVISOR:
				return basicSetSupervisor(null, msgs);
			case UniversityPackage.PERSON__SUPERVISED:
				return ((InternalEList<?>)getSupervised()).basicRemove(otherEnd, msgs);
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
			case UniversityPackage.PERSON__FIRST_NAME:
				return getFirstName();
			case UniversityPackage.PERSON__LAST_NAME:
				return getLastName();
			case UniversityPackage.PERSON__IS_MARRIED:
				return isIsMarried();
			case UniversityPackage.PERSON__BIRTH_DATE:
				return getBirthDate();
			case UniversityPackage.PERSON__AGE:
				return getAge();
			case UniversityPackage.PERSON__SALARIES:
				return getSalaries();
			case UniversityPackage.PERSON__SUPERVISOR:
				if (resolve) return getSupervisor();
				return basicGetSupervisor();
			case UniversityPackage.PERSON__SUPERVISED:
				return getSupervised();
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
			case UniversityPackage.PERSON__FIRST_NAME:
				setFirstName((String)newValue);
				return;
			case UniversityPackage.PERSON__LAST_NAME:
				setLastName((String)newValue);
				return;
			case UniversityPackage.PERSON__IS_MARRIED:
				setIsMarried((Boolean)newValue);
				return;
			case UniversityPackage.PERSON__BIRTH_DATE:
				setBirthDate((Date)newValue);
				return;
			case UniversityPackage.PERSON__AGE:
				setAge((Integer)newValue);
				return;
			case UniversityPackage.PERSON__SALARIES:
				getSalaries().clear();
				getSalaries().addAll((Collection<? extends Integer>)newValue);
				return;
			case UniversityPackage.PERSON__SUPERVISOR:
				setSupervisor((Person)newValue);
				return;
			case UniversityPackage.PERSON__SUPERVISED:
				getSupervised().clear();
				getSupervised().addAll((Collection<? extends Person>)newValue);
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
			case UniversityPackage.PERSON__FIRST_NAME:
				setFirstName(FIRST_NAME_EDEFAULT);
				return;
			case UniversityPackage.PERSON__LAST_NAME:
				setLastName(LAST_NAME_EDEFAULT);
				return;
			case UniversityPackage.PERSON__IS_MARRIED:
				setIsMarried(IS_MARRIED_EDEFAULT);
				return;
			case UniversityPackage.PERSON__BIRTH_DATE:
				setBirthDate(BIRTH_DATE_EDEFAULT);
				return;
			case UniversityPackage.PERSON__AGE:
				setAge(AGE_EDEFAULT);
				return;
			case UniversityPackage.PERSON__SALARIES:
				getSalaries().clear();
				return;
			case UniversityPackage.PERSON__SUPERVISOR:
				setSupervisor((Person)null);
				return;
			case UniversityPackage.PERSON__SUPERVISED:
				getSupervised().clear();
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
			case UniversityPackage.PERSON__FIRST_NAME:
				return FIRST_NAME_EDEFAULT == null ? firstName != null : !FIRST_NAME_EDEFAULT.equals(firstName);
			case UniversityPackage.PERSON__LAST_NAME:
				return LAST_NAME_EDEFAULT == null ? lastName != null : !LAST_NAME_EDEFAULT.equals(lastName);
			case UniversityPackage.PERSON__IS_MARRIED:
				return isMarried != IS_MARRIED_EDEFAULT;
			case UniversityPackage.PERSON__BIRTH_DATE:
				return BIRTH_DATE_EDEFAULT == null ? birthDate != null : !BIRTH_DATE_EDEFAULT.equals(birthDate);
			case UniversityPackage.PERSON__AGE:
				return age != AGE_EDEFAULT;
			case UniversityPackage.PERSON__SALARIES:
				return salaries != null && !salaries.isEmpty();
			case UniversityPackage.PERSON__SUPERVISOR:
				return supervisor != null;
			case UniversityPackage.PERSON__SUPERVISED:
				return supervised != null && !supervised.isEmpty();
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
		result.append("Person(");
		result.append(firstName);
		result.append(" ");
		result.append(lastName);
		result.append(')');
		return result.toString();
	}

} //PersonImpl
