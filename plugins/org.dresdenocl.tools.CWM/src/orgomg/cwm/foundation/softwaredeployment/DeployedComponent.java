/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployed Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A DeployedComponent represents the deployment of a Component on a specific Machine.
 * 
 * It may represent the deployment of any type of Component. However, if the Component is part of a SoftwareSystem, the DeployedComponent should be part of a DeployedSoftwareSystem.
 * 
 * Usage dependencies may be used to document that one DeployedComponent uses another DeployedComponent.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getPathname <em>Pathname</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getMachine <em>Machine</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getDeployedSoftwareSystem <em>Deployed Software System</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getComponent <em>Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDeployedComponent()
 * @model
 * @generated
 */
public interface DeployedComponent extends orgomg.cwm.objectmodel.core.Package {
	/**
	 * Returns the value of the '<em><b>Pathname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A pathname for the DeployedComponent within the Machine’s file system.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pathname</em>' attribute.
	 * @see #setPathname(String)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDeployedComponent_Pathname()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getPathname();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getPathname <em>Pathname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pathname</em>' attribute.
	 * @see #getPathname()
	 * @generated
	 */
	void setPathname(String value);

	/**
	 * Returns the value of the '<em><b>Machine</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.Machine#getDeployedComponent <em>Deployed Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Machine on which a DeployedComponent is deployed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Machine</em>' container reference.
	 * @see #setMachine(Machine)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDeployedComponent_Machine()
	 * @see orgomg.cwm.foundation.softwaredeployment.Machine#getDeployedComponent
	 * @model opposite="deployedComponent" required="true"
	 * @generated
	 */
	Machine getMachine();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getMachine <em>Machine</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Machine</em>' container reference.
	 * @see #getMachine()
	 * @generated
	 */
	void setMachine(Machine value);

	/**
	 * Returns the value of the '<em><b>Deployed Software System</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem#getDeployedComponent <em>Deployed Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the DeployedSoftwareSystem.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deployed Software System</em>' reference list.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDeployedComponent_DeployedSoftwareSystem()
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem#getDeployedComponent
	 * @model opposite="deployedComponent"
	 * @generated
	 */
	EList<DeployedSoftwareSystem> getDeployedSoftwareSystem();

	/**
	 * Returns the value of the '<em><b>Component</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.Component#getDeployment <em>Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Component deployed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Component</em>' reference.
	 * @see #setComponent(Component)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDeployedComponent_Component()
	 * @see orgomg.cwm.foundation.softwaredeployment.Component#getDeployment
	 * @model opposite="deployment" required="true"
	 * @generated
	 */
	Component getComponent();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getComponent <em>Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(Component value);

} // DeployedComponent
