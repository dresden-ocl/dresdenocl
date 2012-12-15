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
 * A representation of the model object '<em><b>Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Schema contains all elements comprising an OLAP model.  A Schema may also contain any number of DeploymentGroups, representing the various physical deployments of the logical Schema.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.Schema#getCube <em>Cube</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Schema#getDimension <em>Dimension</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Schema#getDeploymentGroup <em>Deployment Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getSchema()
 * @model
 * @generated
 */
public interface Schema extends orgomg.cwm.objectmodel.core.Package {
	/**
	 * Returns the value of the '<em><b>Cube</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.Cube}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Cube#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Cubes owned by a Schema.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cube</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getSchema_Cube()
	 * @see orgomg.cwm.analysis.olap.Cube#getSchema
	 * @model opposite="schema" containment="true"
	 * @generated
	 */
	EList<Cube> getCube();

	/**
	 * Returns the value of the '<em><b>Dimension</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.Dimension}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Dimension#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Dimension owned by a Schema.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getSchema_Dimension()
	 * @see orgomg.cwm.analysis.olap.Dimension#getSchema
	 * @model opposite="schema" containment="true"
	 * @generated
	 */
	EList<Dimension> getDimension();

	/**
	 * Returns the value of the '<em><b>Deployment Group</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.DeploymentGroup}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.DeploymentGroup#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The DeploymentGroups owned by a Schema.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deployment Group</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getSchema_DeploymentGroup()
	 * @see orgomg.cwm.analysis.olap.DeploymentGroup#getSchema
	 * @model opposite="schema" containment="true"
	 * @generated
	 */
	EList<DeploymentGroup> getDeploymentGroup();

} // Schema
