/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS;
import tudresden.ocl20.pivot.language.ocl.NavigationCallExp;
import tudresden.ocl20.pivot.language.ocl.OclExpressionCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.NavigationCallExpImpl#getSource <em>Source</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.NavigationCallExpImpl#getNavigationOperator <em>Navigation Operator</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.NavigationCallExpImpl#getFeatureCalls <em>Feature Calls</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NavigationCallExpImpl extends FeatureCallExpCSImpl implements NavigationCallExp {
	/**
   * The cached value of the '{@link #getSource() <em>Source</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
	protected OclExpressionCS source;

	/**
   * The cached value of the '{@link #getNavigationOperator() <em>Navigation Operator</em>}' attribute list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getNavigationOperator()
   * @generated
   * @ordered
   */
	protected EList<String> navigationOperator;

	/**
   * The cached value of the '{@link #getFeatureCalls() <em>Feature Calls</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getFeatureCalls()
   * @generated
   * @ordered
   */
	protected EList<ImplicitFeatureCallCS> featureCalls;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected NavigationCallExpImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.NAVIGATION_CALL_EXP;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OclExpressionCS getSource() {
    return source;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetSource(OclExpressionCS newSource, NotificationChain msgs) {
    OclExpressionCS oldSource = source;
    source = newSource;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.NAVIGATION_CALL_EXP__SOURCE, oldSource, newSource);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setSource(OclExpressionCS newSource) {
    if (newSource != source)
    {
      NotificationChain msgs = null;
      if (source != null)
        msgs = ((InternalEObject)source).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.NAVIGATION_CALL_EXP__SOURCE, null, msgs);
      if (newSource != null)
        msgs = ((InternalEObject)newSource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.NAVIGATION_CALL_EXP__SOURCE, null, msgs);
      msgs = basicSetSource(newSource, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.NAVIGATION_CALL_EXP__SOURCE, newSource, newSource));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<String> getNavigationOperator() {
    if (navigationOperator == null)
    {
      navigationOperator = new EDataTypeEList<String>(String.class, this, OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR);
    }
    return navigationOperator;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<ImplicitFeatureCallCS> getFeatureCalls() {
    if (featureCalls == null)
    {
      featureCalls = new EObjectContainmentEList<ImplicitFeatureCallCS>(ImplicitFeatureCallCS.class, this, OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS);
    }
    return featureCalls;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID)
    {
      case OclPackage.NAVIGATION_CALL_EXP__SOURCE:
        return basicSetSource(null, msgs);
      case OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS:
        return ((InternalEList<?>)getFeatureCalls()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case OclPackage.NAVIGATION_CALL_EXP__SOURCE:
        return getSource();
      case OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR:
        return getNavigationOperator();
      case OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS:
        return getFeatureCalls();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID)
    {
      case OclPackage.NAVIGATION_CALL_EXP__SOURCE:
        setSource((OclExpressionCS)newValue);
        return;
      case OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR:
        getNavigationOperator().clear();
        getNavigationOperator().addAll((Collection<? extends String>)newValue);
        return;
      case OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS:
        getFeatureCalls().clear();
        getFeatureCalls().addAll((Collection<? extends ImplicitFeatureCallCS>)newValue);
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
      case OclPackage.NAVIGATION_CALL_EXP__SOURCE:
        setSource((OclExpressionCS)null);
        return;
      case OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR:
        getNavigationOperator().clear();
        return;
      case OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS:
        getFeatureCalls().clear();
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
      case OclPackage.NAVIGATION_CALL_EXP__SOURCE:
        return source != null;
      case OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR:
        return navigationOperator != null && !navigationOperator.isEmpty();
      case OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS:
        return featureCalls != null && !featureCalls.isEmpty();
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
    result.append(" (navigationOperator: ");
    result.append(navigationOperator);
    result.append(')');
    return result.toString();
  }

} //NavigationCallExpImpl
