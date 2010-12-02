/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.Namespace;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Machine</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Machine represents a computer. The Site at which the Machine is located and the Components deployed on the Machine may be recorded.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.Machine#getIpAddress <em>Ip Address</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.Machine#getHostName <em>Host Name</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.Machine#getMachineID <em>Machine ID</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.Machine#getDeployedComponent <em>Deployed Component</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.Machine#getSite <em>Site</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getMachine()
 * @model
 * @generated
 */
public interface Machine extends Namespace {
	/**
	 * Returns the value of the '<em><b>Ip Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A fixed IP address for the Machine.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ip Address</em>' attribute.
	 * @see #setIpAddress(String)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getMachine_IpAddress()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getIpAddress();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.Machine#getIpAddress <em>Ip Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ip Address</em>' attribute.
	 * @see #getIpAddress()
	 * @generated
	 */
	void setIpAddress(String value);

	/**
	 * Returns the value of the '<em><b>Host Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A Host Name for the Machine. This may be used to identify the Machine on the network when IP addresses are dynamically allocated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Host Name</em>' attribute.
	 * @see #setHostName(String)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getMachine_HostName()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getHostName();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.Machine#getHostName <em>Host Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host Name</em>' attribute.
	 * @see #getHostName()
	 * @generated
	 */
	void setHostName(String value);

	/**
	 * Returns the value of the '<em><b>Machine ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An identification code for the Machine.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Machine ID</em>' attribute.
	 * @see #setMachineID(String)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getMachine_MachineID()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getMachineID();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.Machine#getMachineID <em>Machine ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Machine ID</em>' attribute.
	 * @see #getMachineID()
	 * @generated
	 */
	void setMachineID(String value);

	/**
	 * Returns the value of the '<em><b>Deployed Component</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getMachine <em>Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the DeployedComponents on the Machine.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deployed Component</em>' containment reference list.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getMachine_DeployedComponent()
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getMachine
	 * @model opposite="machine" containment="true"
	 * @generated
	 */
	EList<DeployedComponent> getDeployedComponent();

	/**
	 * Returns the value of the '<em><b>Site</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.Site#getMachine <em>Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Site on which the Machine is located.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Site</em>' reference.
	 * @see #setSite(Site)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getMachine_Site()
	 * @see orgomg.cwm.foundation.softwaredeployment.Site#getMachine
	 * @model opposite="machine"
	 * @generated
	 */
	Site getSite();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.Machine#getSite <em>Site</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Site</em>' reference.
	 * @see #getSite()
	 * @generated
	 */
	void setSite(Site value);

} // Machine
