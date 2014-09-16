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
 * A representation of the model object '<em><b>Deployed Software System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A DeployedSoftwareSystem represents a deployment of a SoftwareSystem.
 * 
 * Its associated DeployedComponents identify the individual Component deployments that constitute the DeployedSoftwareSystem. These DeployedComponents are not necessarily all deployed on the same Machine.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem#getSoftwareSystem <em>Software System</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem#getDeployedComponent <em>Deployed Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDeployedSoftwareSystem()
 * @model
 * @generated
 */
public interface DeployedSoftwareSystem extends orgomg.cwm.objectmodel.core.Package {
	/**
	 * Returns the value of the '<em><b>Software System</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getDeployment <em>Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the SoftwareSystem deployed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Software System</em>' reference.
	 * @see #setSoftwareSystem(SoftwareSystem)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDeployedSoftwareSystem_SoftwareSystem()
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getDeployment
	 * @model opposite="deployment" required="true"
	 * @generated
	 */
	SoftwareSystem getSoftwareSystem();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem#getSoftwareSystem <em>Software System</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Software System</em>' reference.
	 * @see #getSoftwareSystem()
	 * @generated
	 */
	void setSoftwareSystem(SoftwareSystem value);

	/**
	 * Returns the value of the '<em><b>Deployed Component</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getDeployedSoftwareSystem <em>Deployed Software System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the DeployedComponent.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deployed Component</em>' reference list.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDeployedSoftwareSystem_DeployedComponent()
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getDeployedSoftwareSystem
	 * @model opposite="deployedSoftwareSystem"
	 * @generated
	 */
	EList<DeployedComponent> getDeployedComponent();

} // DeployedSoftwareSystem
