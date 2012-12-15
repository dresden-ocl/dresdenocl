/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.pml;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plugin Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.PluginPackage#getPlugins <em>Plugins</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.PluginPackage#getTypes <em>Types</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.PluginPackage#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.examples.pml.PmlPackage#getPluginPackage()
 * @model
 * @generated
 */
public interface PluginPackage extends EObject {
	/**
	 * Returns the value of the '<em><b>Plugins</b></em>' containment reference list.
	 * The list contents are of type {@link tudresden.ocl20.pivot.examples.pml.Plugin}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plugins</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugins</em>' containment reference list.
	 * @see tudresden.ocl20.pivot.examples.pml.PmlPackage#getPluginPackage_Plugins()
	 * @model containment="true"
	 * @generated
	 */
	EList<Plugin> getPlugins();

	/**
	 * Returns the value of the '<em><b>Types</b></em>' containment reference list.
	 * The list contents are of type {@link tudresden.ocl20.pivot.examples.pml.JavaType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types</em>' containment reference list.
	 * @see tudresden.ocl20.pivot.examples.pml.PmlPackage#getPluginPackage_Types()
	 * @model containment="true"
	 * @generated
	 */
	EList<JavaType> getTypes();

	/**
	 * Returns the value of the '<em><b>Features</b></em>' containment reference list.
	 * The list contents are of type {@link tudresden.ocl20.pivot.examples.pml.Feature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' containment reference list.
	 * @see tudresden.ocl20.pivot.examples.pml.PmlPackage#getPluginPackage_Features()
	 * @model containment="true"
	 * @generated
	 */
	EList<Feature> getFeatures();

} // PluginPackage
