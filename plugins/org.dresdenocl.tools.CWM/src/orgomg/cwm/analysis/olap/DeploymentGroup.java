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
 * A representation of the model object '<em><b>Deployment Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * DeploymentGroup represents a logical grouping of model elements defining a single, complete deployment of an instance of Olap Schema (i.e., CubeDeployments and DimensionDeployments).
 * 
 * The usage of DeploymentGroup is as follows: A user may specify a particular DeploymentGroup as the session-wide, default deployment for all metadata queries performed throughout the session. Alternatively, for queries involving some particular deployed object (e.g., a Cube or a Dimension), the user may be asked to choose from a list of deployments.  This either becomes the default deployment for the remainder of the session, or the user may continue to be asked to specify a deployment for each  subsequent query against deployed objects.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.DeploymentGroup#getSchema <em>Schema</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.DeploymentGroup#getCubeDeployment <em>Cube Deployment</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.DeploymentGroup#getDimensionDeployment <em>Dimension Deployment</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getDeploymentGroup()
 * @model
 * @generated
 */
public interface DeploymentGroup extends orgomg.cwm.objectmodel.core.Package {
	/**
	 * Returns the value of the '<em><b>Schema</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Schema#getDeploymentGroup <em>Deployment Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Schema owning a DeploymentGroup.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Schema</em>' container reference.
	 * @see #setSchema(Schema)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDeploymentGroup_Schema()
	 * @see orgomg.cwm.analysis.olap.Schema#getDeploymentGroup
	 * @model opposite="deploymentGroup" required="true"
	 * @generated
	 */
	Schema getSchema();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.DeploymentGroup#getSchema <em>Schema</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' container reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(Schema value);

	/**
	 * Returns the value of the '<em><b>Cube Deployment</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.CubeDeployment}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.CubeDeployment#getDeploymentGroup <em>Deployment Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The CubeDeployments referenced by a DeploymentGroup.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cube Deployment</em>' reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDeploymentGroup_CubeDeployment()
	 * @see orgomg.cwm.analysis.olap.CubeDeployment#getDeploymentGroup
	 * @model opposite="deploymentGroup"
	 * @generated
	 */
	EList<CubeDeployment> getCubeDeployment();

	/**
	 * Returns the value of the '<em><b>Dimension Deployment</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.DimensionDeployment}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getDeploymentGroup <em>Deployment Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The DimensionDeployments referenced by a DeploymentGroup.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension Deployment</em>' reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDeploymentGroup_DimensionDeployment()
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment#getDeploymentGroup
	 * @model opposite="deploymentGroup"
	 * @generated
	 */
	EList<DimensionDeployment> getDimensionDeployment();

} // DeploymentGroup
