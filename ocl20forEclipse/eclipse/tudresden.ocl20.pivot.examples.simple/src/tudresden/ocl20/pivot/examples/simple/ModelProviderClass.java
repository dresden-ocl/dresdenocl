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
package tudresden.ocl20.pivot.examples.simple;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Provider Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.simple.ModelProviderClass#getPersons <em>Persons</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.simple.ModelProviderClass#getProfessors <em>Professors</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.simple.ModelProviderClass#getStudents <em>Students</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.examples.simple.SimplePackage#getModelProviderClass()
 * @model
 * @generated
 */
public interface ModelProviderClass extends EObject {
	/**
	 * Returns the value of the '<em><b>Persons</b></em>' containment reference list.
	 * The list contents are of type {@link tudresden.ocl20.pivot.examples.simple.Person}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Persons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persons</em>' containment reference list.
	 * @see tudresden.ocl20.pivot.examples.simple.SimplePackage#getModelProviderClass_Persons()
	 * @model containment="true"
	 * @generated
	 */
	EList<Person> getPersons();

	/**
	 * Returns the value of the '<em><b>Professors</b></em>' containment reference list.
	 * The list contents are of type {@link tudresden.ocl20.pivot.examples.simple.Professor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Professors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Professors</em>' containment reference list.
	 * @see tudresden.ocl20.pivot.examples.simple.SimplePackage#getModelProviderClass_Professors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Professor> getProfessors();

	/**
	 * Returns the value of the '<em><b>Students</b></em>' containment reference list.
	 * The list contents are of type {@link tudresden.ocl20.pivot.examples.simple.Student}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Students</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Students</em>' containment reference list.
	 * @see tudresden.ocl20.pivot.examples.simple.SimplePackage#getModelProviderClass_Students()
	 * @model containment="true"
	 * @generated
	 */
	EList<Student> getStudents();

} // ModelProviderClass
