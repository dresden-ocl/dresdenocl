/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.examples.pml;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plugin</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.examples.pml.Plugin#getId <em>Id</em>}</li>
 *   <li>{@link org.dresdenocl.examples.pml.Plugin#getName <em>Name</em>}</li>
 *   <li>{@link org.dresdenocl.examples.pml.Plugin#getVersion <em>Version</em>}</li>
 *   <li>{@link org.dresdenocl.examples.pml.Plugin#getProvider <em>Provider</em>}</li>
 *   <li>{@link org.dresdenocl.examples.pml.Plugin#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.dresdenocl.examples.pml.Plugin#getExtensionPoints <em>Extension Points</em>}</li>
 *   <li>{@link org.dresdenocl.examples.pml.Plugin#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.dresdenocl.examples.pml.Plugin#getActivator <em>Activator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.examples.pml.PmlPackage#getPlugin()
 * @model
 * @generated
 */
public interface Plugin extends EObject {
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
	 * @see org.dresdenocl.examples.pml.PmlPackage#getPlugin_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.dresdenocl.examples.pml.Plugin#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.dresdenocl.examples.pml.PmlPackage#getPlugin_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.dresdenocl.examples.pml.Plugin#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.dresdenocl.examples.pml.PmlPackage#getPlugin_Version()
	 * @model
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.dresdenocl.examples.pml.Plugin#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Provider</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provider</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provider</em>' attribute.
	 * @see #setProvider(String)
	 * @see org.dresdenocl.examples.pml.PmlPackage#getPlugin_Provider()
	 * @model
	 * @generated
	 */
	String getProvider();

	/**
	 * Sets the value of the '{@link org.dresdenocl.examples.pml.Plugin#getProvider <em>Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provider</em>' attribute.
	 * @see #getProvider()
	 * @generated
	 */
	void setProvider(String value);

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.dresdenocl.examples.pml.Feature#getPlugins <em>Plugins</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature</em>' container reference.
	 * @see #setFeature(Feature)
	 * @see org.dresdenocl.examples.pml.PmlPackage#getPlugin_Feature()
	 * @see org.dresdenocl.examples.pml.Feature#getPlugins
	 * @model opposite="plugins" transient="false"
	 * @generated
	 */
	Feature getFeature();

	/**
	 * Sets the value of the '{@link org.dresdenocl.examples.pml.Plugin#getFeature <em>Feature</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature</em>' container reference.
	 * @see #getFeature()
	 * @generated
	 */
	void setFeature(Feature value);

	/**
	 * Returns the value of the '<em><b>Extension Points</b></em>' containment reference list.
	 * The list contents are of type {@link org.dresdenocl.examples.pml.ExtensionPoint}.
	 * It is bidirectional and its opposite is '{@link org.dresdenocl.examples.pml.ExtensionPoint#getPlugin <em>Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension Points</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extension Points</em>' containment reference list.
	 * @see org.dresdenocl.examples.pml.PmlPackage#getPlugin_ExtensionPoints()
	 * @see org.dresdenocl.examples.pml.ExtensionPoint#getPlugin
	 * @model opposite="plugin" containment="true"
	 * @generated
	 */
	EList<ExtensionPoint> getExtensionPoints();

	/**
	 * Returns the value of the '<em><b>Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.dresdenocl.examples.pml.Extension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extensions</em>' containment reference list.
	 * @see org.dresdenocl.examples.pml.PmlPackage#getPlugin_Extensions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Extension> getExtensions();

	/**
	 * Returns the value of the '<em><b>Activator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activator</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activator</em>' reference.
	 * @see #setActivator(JavaType)
	 * @see org.dresdenocl.examples.pml.PmlPackage#getPlugin_Activator()
	 * @model required="true"
	 * @generated
	 */
	JavaType getActivator();

	/**
	 * Sets the value of the '{@link org.dresdenocl.examples.pml.Plugin#getActivator <em>Activator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activator</em>' reference.
	 * @see #getActivator()
	 * @generated
	 */
	void setActivator(JavaType value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getNameOf(Feature feature);

} // Plugin
