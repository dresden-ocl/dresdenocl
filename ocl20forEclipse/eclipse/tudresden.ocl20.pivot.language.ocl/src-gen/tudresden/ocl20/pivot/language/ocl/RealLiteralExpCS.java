/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Real Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS#getIntValue <em>Int Value</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS#getRealValue <em>Real Value</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS#getNavigationOperator <em>Navigation Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getRealLiteralExpCS()
 * @model
 * @generated
 */
public interface RealLiteralExpCS extends PrimitiveLiteralExpCS {
	/**
	 * Returns the value of the '<em><b>Int Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Int Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Int Value</em>' attribute.
	 * @see #setIntValue(int)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getRealLiteralExpCS_IntValue()
	 * @model required="true"
	 * @generated
	 */
	int getIntValue();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS#getIntValue <em>Int Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Int Value</em>' attribute.
	 * @see #getIntValue()
	 * @generated
	 */
	void setIntValue(int value);

	/**
	 * Returns the value of the '<em><b>Real Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Real Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Real Value</em>' attribute.
	 * @see #setRealValue(String)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getRealLiteralExpCS_RealValue()
	 * @model required="true"
	 * @generated
	 */
	String getRealValue();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS#getRealValue <em>Real Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Real Value</em>' attribute.
	 * @see #getRealValue()
	 * @generated
	 */
	void setRealValue(String value);

	/**
	 * Returns the value of the '<em><b>Navigation Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Navigation Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Navigation Operator</em>' attribute.
	 * @see #setNavigationOperator(String)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getRealLiteralExpCS_NavigationOperator()
	 * @model required="true"
	 * @generated
	 */
	String getNavigationOperator();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS#getNavigationOperator <em>Navigation Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Navigation Operator</em>' attribute.
	 * @see #getNavigationOperator()
	 * @generated
	 */
	void setNavigationOperator(String value);

} // RealLiteralExpCS
