/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.StringLiteralExpCSImpl#getStringLiteral <em>String Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StringLiteralExpCSImpl extends PrimitiveLiteralExpCSImpl implements StringLiteralExpCS {
	/**
   * The default value of the '{@link #getStringLiteral() <em>String Literal</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getStringLiteral()
   * @generated
   * @ordered
   */
	protected static final String STRING_LITERAL_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getStringLiteral() <em>String Literal</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getStringLiteral()
   * @generated
   * @ordered
   */
	protected String stringLiteral = STRING_LITERAL_EDEFAULT;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected StringLiteralExpCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.STRING_LITERAL_EXP_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String getStringLiteral() {
    return stringLiteral;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setStringLiteral(String newStringLiteral) {
    String oldStringLiteral = stringLiteral;
    stringLiteral = newStringLiteral;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.STRING_LITERAL_EXP_CS__STRING_LITERAL, oldStringLiteral, stringLiteral));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID)
    {
      case OclPackage.STRING_LITERAL_EXP_CS__STRING_LITERAL:
        return getStringLiteral();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID)
    {
      case OclPackage.STRING_LITERAL_EXP_CS__STRING_LITERAL:
        setStringLiteral((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eUnset(int featureID) {
    switch (featureID)
    {
      case OclPackage.STRING_LITERAL_EXP_CS__STRING_LITERAL:
        setStringLiteral(STRING_LITERAL_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID)
    {
      case OclPackage.STRING_LITERAL_EXP_CS__STRING_LITERAL:
        return STRING_LITERAL_EDEFAULT == null ? stringLiteral != null : !STRING_LITERAL_EDEFAULT.equals(stringLiteral);
    }
    return super.eIsSet(featureID);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (stringLiteral: ");
    result.append(stringLiteral);
    result.append(')');
    return result.toString();
  }

} //StringLiteralExpCSImpl
