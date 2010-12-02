/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.foundation.softwaredeployment.DataManager;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A package is a grouping of model elements.
 * 
 * In the metamodel, Package is a subclass of Namespace. A Package contains ModelElements such as Packages and Classifiers. A Package may also contain Constraints and Dependencies between ModelElements of the Package.
 * 
 * The purpose of the package construct is to provide a general grouping mechanism. In fact, its only semantics is to define a namespace for its contents. The package construct can be used for organizing elements for any purpose; the criteria to use for grouping elements together into one package are not defined.
 * 
 * A package owns a set of model elements, with the implication that if the package is removed from the model, so are the elements owned by the package. Elements with names, such as classifiers, that are owned by the same package must have unique names within the package, although elements in different packages may have the same name.
 * 
 * There may be relationships between elements contained in the same package, and between an element in one package and an element in a surrounding package at any level. In other words, elements “see” all the way out through nested levels of packages.
 * 
 * Elements in peer packages, however, are encapsulated and are not a priori visible to each other. The same goes for elements in contained packages, i.e. packages do not see "inwards".
 * 
 * Elements owned by a Package can be made available to other Packages by importing
 * them. Although any ModelElement may be imported by a Package, imported
 * ModelElements are typically other Packages. When an element is imported by a
 * package it extends the namespace of that package. Thus the elements available in a
 * Package consists of its owned and imported ModelElements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.Package#getImportedElement <em>Imported Element</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Package#getDataManager <em>Data Manager</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getPackage()
 * @model
 * @generated
 */
public interface Package extends Namespace {
	/**
	 * Returns the value of the '<em><b>Imported Element</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.ModelElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getImporter <em>Importer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies ModelElements imported by a Package.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Imported Element</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getPackage_ImportedElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getImporter
	 * @model opposite="importer"
	 * @generated
	 */
	EList<ModelElement> getImportedElement();

	/**
	 * Returns the value of the '<em><b>Data Manager</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.softwaredeployment.DataManager}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.DataManager#getDataPackage <em>Data Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a DataManager that provides access to the data defined in the Package.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Manager</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getPackage_DataManager()
	 * @see orgomg.cwm.foundation.softwaredeployment.DataManager#getDataPackage
	 * @model opposite="dataPackage"
	 * @generated
	 */
	EList<DataManager> getDataManager();

} // Package
