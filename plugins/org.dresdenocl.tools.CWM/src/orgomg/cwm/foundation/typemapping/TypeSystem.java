/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.typemapping;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.foundation.softwaredeployment.SoftwareSystem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Instances of the TypeSystem class collect together the data types (subclasses of Classifier) defined by a software system and the TypeMapping instances defining how they are mapped to data types in other TypeSystems. TypeMapping instances collected by a TypeSystem instance include both those in which the software system’s data types act as sources and as targets of mappings. Classifiers and TapeMappings are
 * maintained in a single collection via the ElementOwnership association but can be distinguished by their respective types.
 * 
 * Because it is a Package, a TypeSystem can also serve to collect together the Classifier instances for a particular software system.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.typemapping.TypeSystem#getSupportingSystem <em>Supporting System</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.typemapping.TypeSystem#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.typemapping.TypemappingPackage#getTypeSystem()
 * @model
 * @generated
 */
public interface TypeSystem extends orgomg.cwm.objectmodel.core.Package {
	/**
	 * Returns the value of the '<em><b>Supporting System</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getTypespace <em>Typespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a SoftwareSystem that supports the datatypes defined by the TypeSystem.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Supporting System</em>' reference list.
	 * @see orgomg.cwm.foundation.typemapping.TypemappingPackage#getTypeSystem_SupportingSystem()
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getTypespace
	 * @model opposite="typespace"
	 * @generated
	 */
	EList<SoftwareSystem> getSupportingSystem();

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A string describing the version identification of the type system represented.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see orgomg.cwm.foundation.typemapping.TypemappingPackage#getTypeSystem_Version()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.typemapping.TypeSystem#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

} // TypeSystem
