/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Multiplicity Range</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * In the metamodel a MultiplicityRange defines a range of integers. The upper bound of the range cannot be below the lower bound. The lower bound must be a nonnegative integer. The upper bound must be a nonnegative integer or the special value unlimited, which indicates there is no upper bound on the range. 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.MultiplicityRange#getLower <em>Lower</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.MultiplicityRange#getUpper <em>Upper</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.MultiplicityRange#getMultiplicity <em>Multiplicity</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getMultiplicityRange()
 * @model
 * @generated
 */
public interface MultiplicityRange extends Element {
	/**
	 * Returns the value of the '<em><b>Lower</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the positive integer lower bound of the range.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lower</em>' attribute.
	 * @see #setLower(long)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getMultiplicityRange_Lower()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getLower();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.MultiplicityRange#getLower <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower</em>' attribute.
	 * @see #getLower()
	 * @generated
	 */
	void setLower(long value);

	/**
	 * Returns the value of the '<em><b>Upper</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the upper bound of the range, which is a positive integer or the special value ’unlimited’ indicating no upper bound is defined.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Upper</em>' attribute.
	 * @see #setUpper(long)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getMultiplicityRange_Upper()
	 * @model dataType="orgomg.cwm.objectmodel.core.UnlimitedInteger"
	 * @generated
	 */
	long getUpper();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.MultiplicityRange#getUpper <em>Upper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper</em>' attribute.
	 * @see #getUpper()
	 * @generated
	 */
	void setUpper(long value);

	/**
	 * Returns the value of the '<em><b>Multiplicity</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Multiplicity#getRange <em>Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Multiplicity instance that owns the MultiplicityRange.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Multiplicity</em>' container reference.
	 * @see #setMultiplicity(Multiplicity)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getMultiplicityRange_Multiplicity()
	 * @see orgomg.cwm.objectmodel.core.Multiplicity#getRange
	 * @model opposite="range" required="true"
	 * @generated
	 */
	Multiplicity getMultiplicity();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.MultiplicityRange#getMultiplicity <em>Multiplicity</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiplicity</em>' container reference.
	 * @see #getMultiplicity()
	 * @generated
	 */
	void setMultiplicity(Multiplicity value);

} // MultiplicityRange
