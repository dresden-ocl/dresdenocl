/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A data type is a type whose values have no identity (i.e., they are pure values). Data types include primitive built-in types (such as integer and string) as well as definable enumeration types.
 * 
 * In the metamodel, a DataType defines a special kind of Classifier in which operations are all pure functions (i.e., they can return data values but they cannot change data values, because they have no identity). For example, an "add" operation on a number with another number as an argument yields a third number as a result; the target and argument are unchanged.
 * 
 * A DataType is a special kind of Classifier whose instances are primitive values, not objects. For example, integers and strings are usually treated as primitive values. A primitive value does not have an identity, so two occurrences of the same value cannot be differentiated. Usually, DataTypes are used for specification of the type of an attribute or parameter.
 * <!-- end-model-doc -->
 *
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getDataType()
 * @model
 * @generated
 */
public interface DataType extends Classifier {
} // DataType
