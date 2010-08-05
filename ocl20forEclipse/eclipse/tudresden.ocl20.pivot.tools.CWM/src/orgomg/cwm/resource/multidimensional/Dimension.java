/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.multidimensional;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dimension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Dimension represents physical dimension in a multidimensional database (e.g., a dimension object defined by the programming model/API of an OLAP database server). Tool-specific extensions to the Multidimensional package will generally contain classes that derive from Dimension.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.multidimensional.Dimension#getDimensionedObject <em>Dimensioned Object</em>}</li>
 *   <li>{@link orgomg.cwm.resource.multidimensional.Dimension#getComposite <em>Composite</em>}</li>
 *   <li>{@link orgomg.cwm.resource.multidimensional.Dimension#getComponent <em>Component</em>}</li>
 *   <li>{@link orgomg.cwm.resource.multidimensional.Dimension#getSchema <em>Schema</em>}</li>
 *   <li>{@link orgomg.cwm.resource.multidimensional.Dimension#getMemberSet <em>Member Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getDimension()
 * @model
 * @generated
 */
public interface Dimension extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Dimensioned Object</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.multidimensional.DimensionedObject}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.multidimensional.DimensionedObject#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * DimensionedObjects referenced by Dimensions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimensioned Object</em>' reference list.
	 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getDimension_DimensionedObject()
	 * @see orgomg.cwm.resource.multidimensional.DimensionedObject#getDimension
	 * @model opposite="dimension"
	 * @generated
	 */
	EList<DimensionedObject> getDimensionedObject();

	/**
	 * Returns the value of the '<em><b>Composite</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.multidimensional.Dimension}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.multidimensional.Dimension#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * "Composite" Dimensions referencing "Component" Dimensions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Composite</em>' reference list.
	 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getDimension_Composite()
	 * @see orgomg.cwm.resource.multidimensional.Dimension#getComponent
	 * @model opposite="component"
	 * @generated
	 */
	EList<Dimension> getComposite();

	/**
	 * Returns the value of the '<em><b>Component</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.multidimensional.Dimension}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.multidimensional.Dimension#getComposite <em>Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * "Component" Dimensions referenced by "Composite" Dimensions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Component</em>' reference list.
	 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getDimension_Component()
	 * @see orgomg.cwm.resource.multidimensional.Dimension#getComposite
	 * @model opposite="composite"
	 * @generated
	 */
	EList<Dimension> getComponent();

	/**
	 * Returns the value of the '<em><b>Schema</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.multidimensional.Schema#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Schema owning Dimensions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Schema</em>' container reference.
	 * @see #setSchema(Schema)
	 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getDimension_Schema()
	 * @see orgomg.cwm.resource.multidimensional.Schema#getDimension
	 * @model opposite="dimension" required="true"
	 * @generated
	 */
	Schema getSchema();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.multidimensional.Dimension#getSchema <em>Schema</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' container reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(Schema value);

	/**
	 * Returns the value of the '<em><b>Member Set</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.multidimensional.MemberSet}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.multidimensional.MemberSet#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * MemberSets owned by a Dimension.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Member Set</em>' containment reference list.
	 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getDimension_MemberSet()
	 * @see orgomg.cwm.resource.multidimensional.MemberSet#getDimension
	 * @model opposite="dimension" containment="true"
	 * @generated
	 */
	EList<MemberSet> getMemberSet();

} // Dimension
