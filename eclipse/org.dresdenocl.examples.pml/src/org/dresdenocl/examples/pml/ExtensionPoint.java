/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.pml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extension Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.ExtensionPoint#getId <em>Id</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.ExtensionPoint#getPlugin <em>Plugin</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.ExtensionPoint#getInterface <em>Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.examples.pml.PmlPackage#getExtensionPoint()
 * @model
 * @generated
 */
public interface ExtensionPoint extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see tudresden.ocl20.pivot.examples.pml.PmlPackage#getExtensionPoint_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.pml.ExtensionPoint#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Plugin</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link tudresden.ocl20.pivot.examples.pml.Plugin#getExtensionPoints <em>Extension Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plugin</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin</em>' container reference.
	 * @see #setPlugin(Plugin)
	 * @see tudresden.ocl20.pivot.examples.pml.PmlPackage#getExtensionPoint_Plugin()
	 * @see tudresden.ocl20.pivot.examples.pml.Plugin#getExtensionPoints
	 * @model opposite="extensionPoints" required="true"
	 * @generated
	 */
	Plugin getPlugin();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.pml.ExtensionPoint#getPlugin <em>Plugin</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plugin</em>' container reference.
	 * @see #getPlugin()
	 * @generated
	 */
	void setPlugin(Plugin value);

	/**
	 * Returns the value of the '<em><b>Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' reference.
	 * @see #setInterface(JavaType)
	 * @see tudresden.ocl20.pivot.examples.pml.PmlPackage#getExtensionPoint_Interface()
	 * @model required="true"
	 * @generated
	 */
	JavaType getInterface();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.pml.ExtensionPoint#getInterface <em>Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' reference.
	 * @see #getInterface()
	 * @generated
	 */
	void setInterface(JavaType value);

} // ExtensionPoint
