/**
 * Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)
 * 
 * This file is part of the Simple Examples of Dresden OCL2 for Eclipse.
 * 
 * Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along 
 * with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.simple.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.examples.simple.ModelProviderClass;
import tudresden.ocl20.pivot.examples.simple.Person;
import tudresden.ocl20.pivot.examples.simple.Professor;
import tudresden.ocl20.pivot.examples.simple.SimplePackage;
import tudresden.ocl20.pivot.examples.simple.Student;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Provider Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.simple.impl.ModelProviderClassImpl#getPersons <em>Persons</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.simple.impl.ModelProviderClassImpl#getProfessors <em>Professors</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.simple.impl.ModelProviderClassImpl#getStudents <em>Students</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelProviderClassImpl extends EObjectImpl implements ModelProviderClass {
	/**
	 * The cached value of the '{@link #getPersons() <em>Persons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersons()
	 * @generated
	 * @ordered
	 */
	protected EList<Person> persons;

	/**
	 * The cached value of the '{@link #getProfessors() <em>Professors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfessors()
	 * @generated
	 * @ordered
	 */
	protected EList<Professor> professors;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelProviderClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimplePackage.Literals.MODEL_PROVIDER_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Person> getPersons() {
		if (persons == null) {
			persons = new EObjectContainmentEList<Person>(Person.class, this, SimplePackage.MODEL_PROVIDER_CLASS__PERSONS);
		}
		return persons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Professor> getProfessors() {
		if (professors == null) {
			professors = new EObjectContainmentEList<Professor>(Professor.class, this, SimplePackage.MODEL_PROVIDER_CLASS__PROFESSORS);
		}
		return professors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Student> getStudents() {
		if (students == null) {
			students = new EObjectContainmentEList<Student>(Student.class, this, SimplePackage.MODEL_PROVIDER_CLASS__STUDENTS);
		}
		return students;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimplePackage.MODEL_PROVIDER_CLASS__PERSONS:
				return ((InternalEList<?>)getPersons()).basicRemove(otherEnd, msgs);
			case SimplePackage.MODEL_PROVIDER_CLASS__PROFESSORS:
				return ((InternalEList<?>)getProfessors()).basicRemove(otherEnd, msgs);
			case SimplePackage.MODEL_PROVIDER_CLASS__STUDENTS:
				return ((InternalEList<?>)getStudents()).basicRemove(otherEnd, msgs);
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
			case SimplePackage.MODEL_PROVIDER_CLASS__PERSONS:
				return getPersons();
			case SimplePackage.MODEL_PROVIDER_CLASS__PROFESSORS:
				return getProfessors();
			case SimplePackage.MODEL_PROVIDER_CLASS__STUDENTS:
				return getStudents();
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
			case SimplePackage.MODEL_PROVIDER_CLASS__PERSONS:
				getPersons().clear();
				getPersons().addAll((Collection<? extends Person>)newValue);
				return;
			case SimplePackage.MODEL_PROVIDER_CLASS__PROFESSORS:
				getProfessors().clear();
				getProfessors().addAll((Collection<? extends Professor>)newValue);
				return;
			case SimplePackage.MODEL_PROVIDER_CLASS__STUDENTS:
				getStudents().clear();
				getStudents().addAll((Collection<? extends Student>)newValue);
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
			case SimplePackage.MODEL_PROVIDER_CLASS__PERSONS:
				getPersons().clear();
				return;
			case SimplePackage.MODEL_PROVIDER_CLASS__PROFESSORS:
				getProfessors().clear();
				return;
			case SimplePackage.MODEL_PROVIDER_CLASS__STUDENTS:
				getStudents().clear();
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
			case SimplePackage.MODEL_PROVIDER_CLASS__PERSONS:
				return persons != null && !persons.isEmpty();
			case SimplePackage.MODEL_PROVIDER_CLASS__PROFESSORS:
				return professors != null && !professors.isEmpty();
			case SimplePackage.MODEL_PROVIDER_CLASS__STUDENTS:
				return students != null && !students.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ModelProviderClassImpl
