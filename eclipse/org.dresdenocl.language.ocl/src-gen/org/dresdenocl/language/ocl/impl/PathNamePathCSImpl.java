/**
 */
package org.dresdenocl.language.ocl.impl;

import java.util.Collection;

import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.PathNamePathCS;
import org.dresdenocl.language.ocl.UnreservedSimpleNameCS;
import org.dresdenocl.pivotmodel.NamedElement;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Path Name Path CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.PathNamePathCSImpl#getPathName <em>Path Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PathNamePathCSImpl extends PathNameCSImpl implements PathNamePathCS {
	/**
   * The cached value of the '{@link #getPathName() <em>Path Name</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPathName()
   * @generated
   * @ordered
   */
	protected EList<UnreservedSimpleNameCS> pathName;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PathNamePathCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.PATH_NAME_PATH_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<UnreservedSimpleNameCS> getPathName() {
    if (pathName == null)
    {
      pathName = new EObjectContainmentEList<UnreservedSimpleNameCS>(UnreservedSimpleNameCS.class, this, OclPackage.PATH_NAME_PATH_CS__PATH_NAME);
    }
    return pathName;
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
      case OclPackage.PATH_NAME_PATH_CS__PATH_NAME:
        return ((InternalEList<?>)getPathName()).basicRemove(otherEnd, msgs);
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
      case OclPackage.PATH_NAME_PATH_CS__PATH_NAME:
        return getPathName();
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
      case OclPackage.PATH_NAME_PATH_CS__PATH_NAME:
        getPathName().clear();
        getPathName().addAll((Collection<? extends UnreservedSimpleNameCS>)newValue);
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
      case OclPackage.PATH_NAME_PATH_CS__PATH_NAME:
        getPathName().clear();
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
      case OclPackage.PATH_NAME_PATH_CS__PATH_NAME:
        return pathName != null && !pathName.isEmpty();
    }
    return super.eIsSet(featureID);
  }


	@Override
	public NamedElement getNamedElement() {
		return this.pathName.get(pathName.size()-1).getNamedElement();
	}
	
} //PathNamePathCSImpl
