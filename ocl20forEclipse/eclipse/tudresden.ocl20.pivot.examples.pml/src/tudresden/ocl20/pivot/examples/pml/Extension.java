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
 * A representation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.Extension#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.Extension#getExtensionPoint <em>Extension Point</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.examples.pml.PmlPackage#getExtension()
 * @model
 * @generated
 */
public interface Extension extends EObject {
	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation</em>' reference.
	 * @see #setImplementation(JavaType)
	 * @see tudresden.ocl20.pivot.examples.pml.PmlPackage#getExtension_Implementation()
	 * @model
	 * @generated
	 */
	JavaType getImplementation();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.pml.Extension#getImplementation <em>Implementation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' reference.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(JavaType value);

	/**
	 * Returns the value of the '<em><b>Extension Point</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension Point</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extension Point</em>' reference.
	 * @see #setExtensionPoint(ExtensionPoint)
	 * @see tudresden.ocl20.pivot.examples.pml.PmlPackage#getExtension_ExtensionPoint()
	 * @model
	 * @generated
	 */
	ExtensionPoint getExtensionPoint();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.examples.pml.Extension#getExtensionPoint <em>Extension Point</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extension Point</em>' reference.
	 * @see #getExtensionPoint()
	 * @generated
	 */
	void setExtensionPoint(ExtensionPoint value);

} // Extension
