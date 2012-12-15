/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.Classifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Component represents a physical piece of implementation of a system, including software code (source, binary or executable) or equivalents such as scripts or command files. A Component is a subtype of Classifier, and so may have its own Features, such as Attributes and Operations.
 * 
 * Deployment of a Component on a specific Machine is represented as a DeployedComponent.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.Component#getDeployment <em>Deployment</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getComponent()
 * @model
 * @generated
 */
public interface Component extends Classifier {
	/**
	 * Returns the value of the '<em><b>Deployment</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the DeployedComponent.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deployment</em>' reference list.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getComponent_Deployment()
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getComponent
	 * @model opposite="component"
	 * @generated
	 */
	EList<DeployedComponent> getDeployment();

} // Component
