/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap;

import orgomg.cwm.foundation.expressions.ExpressionNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Coded Level</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * CodedLevel is a subclass of Level that assigns a unique encoding, or label, to each of its Dimension members.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.CodedLevel#getEncoding <em>Encoding</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getCodedLevel()
 * @model
 * @generated
 */
public interface CodedLevel extends Level {
	/**
	 * Returns the value of the '<em><b>Encoding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Encoding is an expression that generates a unique encoding, or label, for each member of a CodedLevel.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Encoding</em>' containment reference.
	 * @see #setEncoding(ExpressionNode)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCodedLevel_Encoding()
	 * @model containment="true"
	 * @generated
	 */
	ExpressionNode getEncoding();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.CodedLevel#getEncoding <em>Encoding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Encoding</em>' containment reference.
	 * @see #getEncoding()
	 * @generated
	 */
	void setEncoding(ExpressionNode value);

} // CodedLevel
