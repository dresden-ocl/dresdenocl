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
 * A representation of the model object '<em><b>Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Schema contains all elements comprising a Multidimensional database.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.multidimensional.Schema#getDimensionedObject <em>Dimensioned Object</em>}</li>
 *   <li>{@link orgomg.cwm.resource.multidimensional.Schema#getDimension <em>Dimension</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getSchema()
 * @model
 * @generated
 */
public interface Schema extends orgomg.cwm.objectmodel.core.Package {
	/**
	 * Returns the value of the '<em><b>Dimensioned Object</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.multidimensional.DimensionedObject}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.multidimensional.DimensionedObject#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * DimensionedObjects owned by a Schema.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimensioned Object</em>' containment reference list.
	 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getSchema_DimensionedObject()
	 * @see orgomg.cwm.resource.multidimensional.DimensionedObject#getSchema
	 * @model opposite="schema" containment="true"
	 * @generated
	 */
	EList<DimensionedObject> getDimensionedObject();

	/**
	 * Returns the value of the '<em><b>Dimension</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.multidimensional.Dimension}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.multidimensional.Dimension#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dimensions owned by a Schema.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension</em>' containment reference list.
	 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getSchema_Dimension()
	 * @see orgomg.cwm.resource.multidimensional.Dimension#getSchema
	 * @model opposite="schema" containment="true"
	 * @generated
	 */
	EList<Dimension> getDimension();

} // Schema
