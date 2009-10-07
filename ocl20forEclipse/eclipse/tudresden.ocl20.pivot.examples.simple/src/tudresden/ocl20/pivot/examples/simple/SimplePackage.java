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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see tudresden.ocl20.pivot.examples.simple.SimpleFactory
 * @model kind="package"
 * @generated
 */
public interface SimplePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "simple";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.tu-dresden.de/ocl20/pivot/examples/simple";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "simple";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SimplePackage eINSTANCE = tudresden.ocl20.pivot.examples.simple.impl.SimplePackageImpl.init();

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.examples.simple.impl.PersonImpl <em>Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.examples.simple.impl.PersonImpl
	 * @see tudresden.ocl20.pivot.examples.simple.impl.SimplePackageImpl#getPerson()
	 * @generated
	 */
	int PERSON = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__NAME = 0;

	/**
	 * The feature id for the '<em><b>Age</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__AGE = 1;

	/**
	 * The number of structural features of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.examples.simple.impl.ProfessorImpl <em>Professor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.examples.simple.impl.ProfessorImpl
	 * @see tudresden.ocl20.pivot.examples.simple.impl.SimplePackageImpl#getProfessor()
	 * @generated
	 */
	int PROFESSOR = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFESSOR__NAME = PERSON__NAME;

	/**
	 * The feature id for the '<em><b>Age</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFESSOR__AGE = PERSON__AGE;

	/**
	 * The number of structural features of the '<em>Professor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFESSOR_FEATURE_COUNT = PERSON_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.examples.simple.impl.StudentImpl <em>Student</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.examples.simple.impl.StudentImpl
	 * @see tudresden.ocl20.pivot.examples.simple.impl.SimplePackageImpl#getStudent()
	 * @generated
	 */
	int STUDENT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__NAME = PERSON__NAME;

	/**
	 * The feature id for the '<em><b>Age</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__AGE = PERSON__AGE;

	/**
	 * The number of structural features of the '<em>Student</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_FEATURE_COUNT = PERSON_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.examples.simple.impl.ModelProviderClassImpl <em>Model Provider Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.examples.simple.impl.ModelProviderClassImpl
	 * @see tudresden.ocl20.pivot.examples.simple.impl.SimplePackageImpl#getModelProviderClass()
	 * @generated
	 */
	int MODEL_PROVIDER_CLASS = 3;

	/**
	 * The feature id for the '<em><b>Persons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_PROVIDER_CLASS__PERSONS = 0;

	/**
	 * The feature id for the '<em><b>Professors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_PROVIDER_CLASS__PROFESSORS = 1;

	/**
	 * The feature id for the '<em><b>Students</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_PROVIDER_CLASS__STUDENTS = 2;

	/**
	 * The number of structural features of the '<em>Model Provider Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_PROVIDER_CLASS_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.examples.simple.Person <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person</em>'.
	 * @see tudresden.ocl20.pivot.examples.simple.Person
	 * @generated
	 */
	EClass getPerson();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.simple.Person#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see tudresden.ocl20.pivot.examples.simple.Person#getName()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_Name();

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.examples.simple.Person#getAge <em>Age</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Age</em>'.
	 * @see tudresden.ocl20.pivot.examples.simple.Person#getAge()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_Age();

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.examples.simple.Professor <em>Professor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Professor</em>'.
	 * @see tudresden.ocl20.pivot.examples.simple.Professor
	 * @generated
	 */
	EClass getProfessor();

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.examples.simple.Student <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Student</em>'.
	 * @see tudresden.ocl20.pivot.examples.simple.Student
	 * @generated
	 */
	EClass getStudent();

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.examples.simple.ModelProviderClass <em>Model Provider Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Provider Class</em>'.
	 * @see tudresden.ocl20.pivot.examples.simple.ModelProviderClass
	 * @generated
	 */
	EClass getModelProviderClass();

	/**
	 * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.examples.simple.ModelProviderClass#getPersons <em>Persons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Persons</em>'.
	 * @see tudresden.ocl20.pivot.examples.simple.ModelProviderClass#getPersons()
	 * @see #getModelProviderClass()
	 * @generated
	 */
	EReference getModelProviderClass_Persons();

	/**
	 * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.examples.simple.ModelProviderClass#getProfessors <em>Professors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Professors</em>'.
	 * @see tudresden.ocl20.pivot.examples.simple.ModelProviderClass#getProfessors()
	 * @see #getModelProviderClass()
	 * @generated
	 */
	EReference getModelProviderClass_Professors();

	/**
	 * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.examples.simple.ModelProviderClass#getStudents <em>Students</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Students</em>'.
	 * @see tudresden.ocl20.pivot.examples.simple.ModelProviderClass#getStudents()
	 * @see #getModelProviderClass()
	 * @generated
	 */
	EReference getModelProviderClass_Students();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SimpleFactory getSimpleFactory();

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
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.examples.simple.impl.PersonImpl <em>Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.examples.simple.impl.PersonImpl
		 * @see tudresden.ocl20.pivot.examples.simple.impl.SimplePackageImpl#getPerson()
		 * @generated
		 */
		EClass PERSON = eINSTANCE.getPerson();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__NAME = eINSTANCE.getPerson_Name();

		/**
		 * The meta object literal for the '<em><b>Age</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__AGE = eINSTANCE.getPerson_Age();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.examples.simple.impl.ProfessorImpl <em>Professor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.examples.simple.impl.ProfessorImpl
		 * @see tudresden.ocl20.pivot.examples.simple.impl.SimplePackageImpl#getProfessor()
		 * @generated
		 */
		EClass PROFESSOR = eINSTANCE.getProfessor();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.examples.simple.impl.StudentImpl <em>Student</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.examples.simple.impl.StudentImpl
		 * @see tudresden.ocl20.pivot.examples.simple.impl.SimplePackageImpl#getStudent()
		 * @generated
		 */
		EClass STUDENT = eINSTANCE.getStudent();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.examples.simple.impl.ModelProviderClassImpl <em>Model Provider Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.examples.simple.impl.ModelProviderClassImpl
		 * @see tudresden.ocl20.pivot.examples.simple.impl.SimplePackageImpl#getModelProviderClass()
		 * @generated
		 */
		EClass MODEL_PROVIDER_CLASS = eINSTANCE.getModelProviderClass();

		/**
		 * The meta object literal for the '<em><b>Persons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_PROVIDER_CLASS__PERSONS = eINSTANCE.getModelProviderClass_Persons();

		/**
		 * The meta object literal for the '<em><b>Professors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_PROVIDER_CLASS__PROFESSORS = eINSTANCE.getModelProviderClass_Professors();

		/**
		 * The meta object literal for the '<em><b>Students</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_PROVIDER_CLASS__STUDENTS = eINSTANCE.getModelProviderClass_Students();

	}

} //SimplePackage
