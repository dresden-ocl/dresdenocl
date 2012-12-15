/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.foundation.businessinformation.Location;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Site</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Site represents a geographical location. It provides a grouping mechanism for a group of machines at the same location.
 * 
 * Sites may be documented at different levels of granularity; containment links may be used to record hierarchical relationships between Sites.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.Site#getContainingSite <em>Containing Site</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.Site#getContainedSite <em>Contained Site</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.Site#getMachine <em>Machine</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getSite()
 * @model
 * @generated
 */
public interface Site extends Location {
	/**
	 * Returns the value of the '<em><b>Containing Site</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.softwaredeployment.Site}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.Site#getContainedSite <em>Contained Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies other Sites of which the current Site forms a part.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Containing Site</em>' reference list.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getSite_ContainingSite()
	 * @see orgomg.cwm.foundation.softwaredeployment.Site#getContainedSite
	 * @model opposite="containedSite"
	 * @generated
	 */
	EList<Site> getContainingSite();

	/**
	 * Returns the value of the '<em><b>Contained Site</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.softwaredeployment.Site}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.Site#getContainingSite <em>Containing Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies other Sites that are part of the current Site.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contained Site</em>' reference list.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getSite_ContainedSite()
	 * @see orgomg.cwm.foundation.softwaredeployment.Site#getContainingSite
	 * @model opposite="containingSite"
	 * @generated
	 */
	EList<Site> getContainedSite();

	/**
	 * Returns the value of the '<em><b>Machine</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.softwaredeployment.Machine}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.Machine#getSite <em>Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Machines located at the Site.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Machine</em>' reference list.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getSite_Machine()
	 * @see orgomg.cwm.foundation.softwaredeployment.Machine#getSite
	 * @model opposite="site"
	 * @generated
	 */
	EList<Machine> getMachine();

} // Site
