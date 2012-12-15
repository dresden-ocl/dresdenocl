/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.typemapping;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * TypeMapping instances permit the creation of mappings between data types defined within different environments and are used to indicate data type compatibilities that permit direct assignment of values from one environment (the "source" type) into equivalent values in another environment (the "target" type). For example, an integer field data type in a record-oriented DBMS (the source type) might be mapped to a compatible integer data type in a relational DBMS (the target type).
 * 
 * Whereas the actual transfer of data values between environments is accomplished using the CWM’s Transformation package, TypeMapping instances can be used the identify both the permissible and preferred mappings between data types. Value interchange between a pair of data types is considered permissible if a TypeMapping instance is defined for the pair. A TypeMapping instance is considered the preferred mapping if the instance’s isBestMatch attribute has the value true.
 * 
 * Typically, there will be one TypeMapping Instance between a pair of data types that is considered the preferred mapping. To promote flexible use of this feature, there is no requirement that a preferred TypeMapping instance must be identified between a pair of data types nor are multiple preferred instances prohibited. In these latter cases, however, the precise semantics are usage-defined.
 * 
 * Interchange between data types defined by non-preferred mappings may often function as intended. However, the isLossy boolean may be set to indicate that such interchanges may be subject to validity restrictions in certain circumstances. For example, it may be valid to move data values from a 32-bit integer data type to a 16-bit integer data type as long as the actual values in the 32-bit underlying data type do not exceed the range permitted for 16-bit integers. The CWM Foundation leaves the understanding and handling of such differences to individual tools. If such differences must be modeled, consider using the CWM Transformation package to filter data values during interchange.
 * 
 * TypeMapping instances are unidirectional, so two instances are required to show that a data type pair can be mutually interchanged between environments.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.typemapping.TypeMapping#isIsBestMatch <em>Is Best Match</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.typemapping.TypeMapping#isIsLossy <em>Is Lossy</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.typemapping.TypeMapping#getSourceType <em>Source Type</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.typemapping.TypeMapping#getTargetType <em>Target Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.typemapping.TypemappingPackage#getTypeMapping()
 * @model
 * @generated
 */
public interface TypeMapping extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Is Best Match</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True if this TypeMapping instance represents the best available mapping between a pair of data types in different software systems.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Best Match</em>' attribute.
	 * @see #setIsBestMatch(boolean)
	 * @see orgomg.cwm.foundation.typemapping.TypemappingPackage#getTypeMapping_IsBestMatch()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsBestMatch();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.typemapping.TypeMapping#isIsBestMatch <em>Is Best Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Best Match</em>' attribute.
	 * @see #isIsBestMatch()
	 * @generated
	 */
	void setIsBestMatch(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Lossy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True if this TypeMapping instance may result in a data conversion error if the source data is within certain ranges. For example, storing a 32-bit unsigned integer value into a 16-bit unsigned integer container will result in a data conversion error only when the source data has a value greater than 65535.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Lossy</em>' attribute.
	 * @see #setIsLossy(boolean)
	 * @see orgomg.cwm.foundation.typemapping.TypemappingPackage#getTypeMapping_IsLossy()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsLossy();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.typemapping.TypeMapping#isIsLossy <em>Is Lossy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Lossy</em>' attribute.
	 * @see #isIsLossy()
	 * @generated
	 */
	void setIsLossy(boolean value);

	/**
	 * Returns the value of the '<em><b>Source Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Classifier#getMappingFrom <em>Mapping From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Classifier instance that is the source of information exchange.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source Type</em>' reference.
	 * @see #setSourceType(Classifier)
	 * @see orgomg.cwm.foundation.typemapping.TypemappingPackage#getTypeMapping_SourceType()
	 * @see orgomg.cwm.objectmodel.core.Classifier#getMappingFrom
	 * @model opposite="mappingFrom" required="true"
	 * @generated
	 */
	Classifier getSourceType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.typemapping.TypeMapping#getSourceType <em>Source Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Type</em>' reference.
	 * @see #getSourceType()
	 * @generated
	 */
	void setSourceType(Classifier value);

	/**
	 * Returns the value of the '<em><b>Target Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Classifier#getMappingTo <em>Mapping To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Classifier instance that is the target of information exchange.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target Type</em>' reference.
	 * @see #setTargetType(Classifier)
	 * @see orgomg.cwm.foundation.typemapping.TypemappingPackage#getTypeMapping_TargetType()
	 * @see orgomg.cwm.objectmodel.core.Classifier#getMappingTo
	 * @model opposite="mappingTo" required="true"
	 * @generated
	 */
	Classifier getTargetType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.typemapping.TypeMapping#getTargetType <em>Target Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Type</em>' reference.
	 * @see #getTargetType()
	 * @generated
	 */
	void setTargetType(Classifier value);

} // TypeMapping
