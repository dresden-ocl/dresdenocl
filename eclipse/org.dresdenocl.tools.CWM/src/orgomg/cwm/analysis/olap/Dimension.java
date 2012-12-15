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
 * A representation of the model object '<em><b>Dimension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Dimension is an ordinate within a multidimensional structure, and consists of an ordered list of values (i.e.,  members) that share a common semantic meaning within the domain being modeled.  Each member designates a unique position along its ordinate.
 * 
 * Typical Dimensions are: Time, Product, Geography, Scenario (e.g., actual, budget, forecast), Measure (e.g., sales, quantity).
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.Dimension#isIsTime <em>Is Time</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Dimension#isIsMeasure <em>Is Measure</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Dimension#getMemberSelection <em>Member Selection</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Dimension#getCubeDimensionAssociation <em>Cube Dimension Association</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Dimension#getHierarchy <em>Hierarchy</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Dimension#getDisplayDefault <em>Display Default</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Dimension#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimension()
 * @model
 * @generated
 */
public interface Dimension extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Is Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, then this Dimension is a Time Dimension (i.e., its members collectively represent a time series).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Time</em>' attribute.
	 * @see #setIsTime(boolean)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimension_IsTime()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsTime();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.Dimension#isIsTime <em>Is Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Time</em>' attribute.
	 * @see #isIsTime()
	 * @generated
	 */
	void setIsTime(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Measure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, then this Dimension is a Measure Dimension (i.e., its members represent Measures).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Measure</em>' attribute.
	 * @see #setIsMeasure(boolean)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimension_IsMeasure()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsMeasure();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.Dimension#isIsMeasure <em>Is Measure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Measure</em>' attribute.
	 * @see #isIsMeasure()
	 * @generated
	 */
	void setIsMeasure(boolean value);

	/**
	 * Returns the value of the '<em><b>Member Selection</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.MemberSelection}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.MemberSelection#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * MemberSelections owned by the Dimension.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Member Selection</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimension_MemberSelection()
	 * @see orgomg.cwm.analysis.olap.MemberSelection#getDimension
	 * @model opposite="dimension" containment="true"
	 * @generated
	 */
	EList<MemberSelection> getMemberSelection();

	/**
	 * Returns the value of the '<em><b>Cube Dimension Association</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.CubeDimensionAssociation}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * CubeDimAssocs referencing the Dimension.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cube Dimension Association</em>' reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimension_CubeDimensionAssociation()
	 * @see orgomg.cwm.analysis.olap.CubeDimensionAssociation#getDimension
	 * @model opposite="dimension"
	 * @generated
	 */
	EList<CubeDimensionAssociation> getCubeDimensionAssociation();

	/**
	 * Returns the value of the '<em><b>Hierarchy</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.Hierarchy}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Hierarchy#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Hierarchies owned by the Dimension.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hierarchy</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimension_Hierarchy()
	 * @see orgomg.cwm.analysis.olap.Hierarchy#getDimension
	 * @model opposite="dimension" containment="true"
	 * @generated
	 */
	EList<Hierarchy> getHierarchy();

	/**
	 * Returns the value of the '<em><b>Display Default</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Hierarchy#getDefaultedDimension <em>Defaulted Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Hierarchy designated by the Dimension as its default Hierarchy for display purposes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Display Default</em>' reference.
	 * @see #setDisplayDefault(Hierarchy)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimension_DisplayDefault()
	 * @see orgomg.cwm.analysis.olap.Hierarchy#getDefaultedDimension
	 * @model opposite="defaultedDimension"
	 * @generated
	 */
	Hierarchy getDisplayDefault();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.Dimension#getDisplayDefault <em>Display Default</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Default</em>' reference.
	 * @see #getDisplayDefault()
	 * @generated
	 */
	void setDisplayDefault(Hierarchy value);

	/**
	 * Returns the value of the '<em><b>Schema</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Schema#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Schema owning a Dimension
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Schema</em>' container reference.
	 * @see #setSchema(Schema)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getDimension_Schema()
	 * @see orgomg.cwm.analysis.olap.Schema#getDimension
	 * @model opposite="dimension" required="true"
	 * @generated
	 */
	Schema getSchema();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.Dimension#getSchema <em>Schema</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' container reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(Schema value);

} // Dimension
