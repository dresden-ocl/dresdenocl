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
 * A representation of the model object '<em><b>Value Based Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * ValueBasedHierarchy is a subclass of Hierarchy that ranks Dimension members according to their relative distance from the root.  Each member of a ValueBasedHierarchy has a specific "metric" or "value" associated with it.
 * ValueBasedHierarchy is used to model pure "linked node" hierarchies (e.g., parent-child tables).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.ValueBasedHierarchy#getDimensionDeployment <em>Dimension Deployment</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getValueBasedHierarchy()
 * @model
 * @generated
 */
public interface ValueBasedHierarchy extends Hierarchy {
	/**
	 * Returns the value of the '<em><b>Dimension Deployment</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.DimensionDeployment}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getValueBasedHierarchy <em>Value Based Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The DimensionDepolyments owned by a ValueBasedHierarchy.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension Deployment</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getValueBasedHierarchy_DimensionDeployment()
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment#getValueBasedHierarchy
	 * @model opposite="valueBasedHierarchy" containment="true"
	 * @generated
	 */
	EList<DimensionDeployment> getDimensionDeployment();

} // ValueBasedHierarchy
