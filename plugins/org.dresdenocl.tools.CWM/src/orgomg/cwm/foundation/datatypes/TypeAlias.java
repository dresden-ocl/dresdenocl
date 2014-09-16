/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.datatypes;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.DataType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Alias</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The TypeAlias class is intended to provide a renaming capability for Classifier instances. This class is required to support situations in which creation of an alias for a class effectively creates a new class. For example, CORBA IDL type aliases have different typeCodes than their base types and are therefore treated as distinct types.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.datatypes.TypeAlias#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getTypeAlias()
 * @model
 * @generated
 */
public interface TypeAlias extends DataType {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Classifier#getAlias <em>Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Classifier instance for which this TypeAlias instance acts as an alias.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Classifier)
	 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getTypeAlias_Type()
	 * @see orgomg.cwm.objectmodel.core.Classifier#getAlias
	 * @model opposite="alias" required="true"
	 * @generated
	 */
	Classifier getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.datatypes.TypeAlias#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Classifier value);

} // TypeAlias
