/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.multidimensional;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.Attribute;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dimensioned Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * DimensionedObject represents an attribute of Dimension.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.multidimensional.DimensionedObject#getDimension <em>Dimension</em>}</li>
 *   <li>{@link orgomg.cwm.resource.multidimensional.DimensionedObject#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getDimensionedObject()
 * @model
 * @generated
 */
public interface DimensionedObject extends Attribute {
	/**
	 * Returns the value of the '<em><b>Dimension</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.multidimensional.Dimension}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.multidimensional.Dimension#getDimensionedObject <em>Dimensioned Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dimensions referencing DimensionedObjects.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension</em>' reference list.
	 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getDimensionedObject_Dimension()
	 * @see orgomg.cwm.resource.multidimensional.Dimension#getDimensionedObject
	 * @model opposite="dimensionedObject"
	 * @generated
	 */
	EList<Dimension> getDimension();

	/**
	 * Returns the value of the '<em><b>Schema</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.multidimensional.Schema#getDimensionedObject <em>Dimensioned Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Schema owning DimensionedObjects.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Schema</em>' container reference.
	 * @see #setSchema(Schema)
	 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getDimensionedObject_Schema()
	 * @see orgomg.cwm.resource.multidimensional.Schema#getDimensionedObject
	 * @model opposite="dimensionedObject" required="true"
	 * @generated
	 */
	Schema getSchema();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.multidimensional.DimensionedObject#getSchema <em>Schema</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' container reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(Schema value);

} // DimensionedObject
