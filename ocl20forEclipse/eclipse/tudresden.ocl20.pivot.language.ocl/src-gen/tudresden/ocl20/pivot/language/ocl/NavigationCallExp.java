/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.NavigationCallExp#getSource <em>Source</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.NavigationCallExp#getNavigationOperator <em>Navigation Operator</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.NavigationCallExp#getFeatureCalls <em>Feature Calls</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getNavigationCallExp()
 * @model
 * @generated
 */
public interface NavigationCallExp extends FeatureCallExpCS {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' containment reference.
	 * @see #setSource(OclExpressionCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getNavigationCallExp_Source()
	 * @model containment="true" required="true"
	 * @generated
	 */
	OclExpressionCS getSource();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.NavigationCallExp#getSource <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' containment reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(OclExpressionCS value);

	/**
	 * Returns the value of the '<em><b>Navigation Operator</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Navigation Operator</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Navigation Operator</em>' attribute list.
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getNavigationCallExp_NavigationOperator()
	 * @model unique="false" required="true"
	 * @generated
	 */
	EList<String> getNavigationOperator();

	/**
	 * Returns the value of the '<em><b>Feature Calls</b></em>' containment reference list.
	 * The list contents are of type {@link tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature Calls</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature Calls</em>' containment reference list.
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getNavigationCallExp_FeatureCalls()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<ImplicitFeatureCallCS> getFeatureCalls();

} // NavigationCallExp
