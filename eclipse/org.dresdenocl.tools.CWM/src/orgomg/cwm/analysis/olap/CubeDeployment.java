/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cube Deployment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * CubeDeployment represents a particular implementation strategy for the data portions of an OLAP model.  It does so by organizing a collection of ContentMaps, which in turn define a mapping to an implementation model.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.CubeDeployment#getCubeRegion <em>Cube Region</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.CubeDeployment#getDeploymentGroup <em>Deployment Group</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.CubeDeployment#getContentMap <em>Content Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeDeployment()
 * @model
 * @generated
 */
public interface CubeDeployment extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Cube Region</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.CubeRegion#getCubeDeployment <em>Cube Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The CubeRegion owning a CubeDeployment.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cube Region</em>' container reference.
	 * @see #setCubeRegion(CubeRegion)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeDeployment_CubeRegion()
	 * @see orgomg.cwm.analysis.olap.CubeRegion#getCubeDeployment
	 * @model opposite="cubeDeployment" required="true"
	 * @generated
	 */
	CubeRegion getCubeRegion();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.CubeDeployment#getCubeRegion <em>Cube Region</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cube Region</em>' container reference.
	 * @see #getCubeRegion()
	 * @generated
	 */
	void setCubeRegion(CubeRegion value);

	/**
	 * Returns the value of the '<em><b>Deployment Group</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.DeploymentGroup#getCubeDeployment <em>Cube Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The DeploymentGroups referencing a CubeDeployment.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deployment Group</em>' reference.
	 * @see #setDeploymentGroup(DeploymentGroup)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeDeployment_DeploymentGroup()
	 * @see orgomg.cwm.analysis.olap.DeploymentGroup#getCubeDeployment
	 * @model opposite="cubeDeployment" required="true"
	 * @generated
	 */
	DeploymentGroup getDeploymentGroup();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.CubeDeployment#getDeploymentGroup <em>Deployment Group</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deployment Group</em>' reference.
	 * @see #getDeploymentGroup()
	 * @generated
	 */
	void setDeploymentGroup(DeploymentGroup value);

	/**
	 * Returns the value of the '<em><b>Content Map</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.ContentMap}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.ContentMap#getCubeDeployment <em>Cube Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection of ContentMaps owned by a CubeDeployment.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Content Map</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeDeployment_ContentMap()
	 * @see orgomg.cwm.analysis.olap.ContentMap#getCubeDeployment
	 * @model opposite="cubeDeployment" containment="true"
	 * @generated
	 */
	EList<ContentMap> getContentMap();

} // CubeDeployment
