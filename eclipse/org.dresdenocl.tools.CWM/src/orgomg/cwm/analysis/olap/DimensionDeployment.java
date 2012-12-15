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
 * A representation of the model object '<em><b>Dimension Deployment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * DimensionDeployment represents a particular implementation strategy for the dimensional/hierarchical portions of an OLAP model.  It does so by organizing a collection of StructureMaps, which in turn define a mapping to an implementation model.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.DimensionDeployment#getHierarchyLevelAssociation <em>Hierarchy Level Association</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.DimensionDeployment#getValueBasedHierarchy <em>Value Based Hierarchy</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.DimensionDeployment#getStructureMap <em>Structure Map</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.DimensionDeployment#getListOfValues <em>List Of Values</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.DimensionDeployment#getImmediateParent <em>Immediate Parent</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.DimensionDeployment#getDeploymentGroup <em>Deployment Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimensionDeployment()
 * @model
 * @generated
 */
public interface DimensionDeployment extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Hierarchy Level Association</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getDimensionDeployment <em>Dimension Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The HierarchyLevelAssociation owning DimensionDeployments.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hierarchy Level Association</em>' container reference.
	 * @see #setHierarchyLevelAssociation(HierarchyLevelAssociation)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimensionDeployment_HierarchyLevelAssociation()
	 * @see orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getDimensionDeployment
	 * @model opposite="dimensionDeployment"
	 * @generated
	 */
	HierarchyLevelAssociation getHierarchyLevelAssociation();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getHierarchyLevelAssociation <em>Hierarchy Level Association</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hierarchy Level Association</em>' container reference.
	 * @see #getHierarchyLevelAssociation()
	 * @generated
	 */
	void setHierarchyLevelAssociation(HierarchyLevelAssociation value);

	/**
	 * Returns the value of the '<em><b>Value Based Hierarchy</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.ValueBasedHierarchy#getDimensionDeployment <em>Dimension Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The ValueBasedHierarchy owning a DimensionDeployment.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value Based Hierarchy</em>' container reference.
	 * @see #setValueBasedHierarchy(ValueBasedHierarchy)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimensionDeployment_ValueBasedHierarchy()
	 * @see orgomg.cwm.analysis.olap.ValueBasedHierarchy#getDimensionDeployment
	 * @model opposite="dimensionDeployment"
	 * @generated
	 */
	ValueBasedHierarchy getValueBasedHierarchy();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getValueBasedHierarchy <em>Value Based Hierarchy</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Based Hierarchy</em>' container reference.
	 * @see #getValueBasedHierarchy()
	 * @generated
	 */
	void setValueBasedHierarchy(ValueBasedHierarchy value);

	/**
	 * Returns the value of the '<em><b>Structure Map</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.StructureMap}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.StructureMap#getDimensionDeployment <em>Dimension Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The StructureMaps owned by a DimensionDeployment.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Structure Map</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimensionDeployment_StructureMap()
	 * @see orgomg.cwm.analysis.olap.StructureMap#getDimensionDeployment
	 * @model opposite="dimensionDeployment" containment="true"
	 * @generated
	 */
	EList<StructureMap> getStructureMap();

	/**
	 * Returns the value of the '<em><b>List Of Values</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.StructureMap#getDimensionDeploymentLV <em>Dimension Deployment LV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The StructureMap referenced by a DimensionDeployment as its "list of values" StructureMap.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>List Of Values</em>' reference.
	 * @see #setListOfValues(StructureMap)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimensionDeployment_ListOfValues()
	 * @see orgomg.cwm.analysis.olap.StructureMap#getDimensionDeploymentLV
	 * @model opposite="dimensionDeploymentLV"
	 * @generated
	 */
	StructureMap getListOfValues();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getListOfValues <em>List Of Values</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>List Of Values</em>' reference.
	 * @see #getListOfValues()
	 * @generated
	 */
	void setListOfValues(StructureMap value);

	/**
	 * Returns the value of the '<em><b>Immediate Parent</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.StructureMap#getDimensionDeploymentIP <em>Dimension Deployment IP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The StructureMap referenced by a DimensionDeployment as its "immediate parent".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Immediate Parent</em>' reference.
	 * @see #setImmediateParent(StructureMap)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimensionDeployment_ImmediateParent()
	 * @see orgomg.cwm.analysis.olap.StructureMap#getDimensionDeploymentIP
	 * @model opposite="dimensionDeploymentIP"
	 * @generated
	 */
	StructureMap getImmediateParent();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getImmediateParent <em>Immediate Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Immediate Parent</em>' reference.
	 * @see #getImmediateParent()
	 * @generated
	 */
	void setImmediateParent(StructureMap value);

	/**
	 * Returns the value of the '<em><b>Deployment Group</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.DeploymentGroup#getDimensionDeployment <em>Dimension Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The DeploymentGroups referencing a DimensionDeployment.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deployment Group</em>' reference.
	 * @see #setDeploymentGroup(DeploymentGroup)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimensionDeployment_DeploymentGroup()
	 * @see orgomg.cwm.analysis.olap.DeploymentGroup#getDimensionDeployment
	 * @model opposite="dimensionDeployment" required="true"
	 * @generated
	 */
	DeploymentGroup getDeploymentGroup();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getDeploymentGroup <em>Deployment Group</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deployment Group</em>' reference.
	 * @see #getDeploymentGroup()
	 * @generated
	 */
	void setDeploymentGroup(DeploymentGroup value);

} // DimensionDeployment
