/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.emftext.commons.layout.LayoutInformation;
import org.dresdenocl.language.ocl.ContextDeclarationCS;
import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.PackageDeclarationCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Declaration CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.PackageDeclarationCSImpl#getContextDeclarations <em>Context Declarations</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.PackageDeclarationCSImpl#getLayoutInformation <em>Layout Information</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PackageDeclarationCSImpl extends EObjectImpl implements PackageDeclarationCS {
	/**
   * The cached value of the '{@link #getContextDeclarations() <em>Context Declarations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getContextDeclarations()
   * @generated
   * @ordered
   */
	protected EList<ContextDeclarationCS> contextDeclarations;

	/**
   * The cached value of the '{@link #getLayoutInformation() <em>Layout Information</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLayoutInformation()
   * @generated
   * @ordered
   */
	protected EList<LayoutInformation> layoutInformation;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PackageDeclarationCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.PACKAGE_DECLARATION_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<ContextDeclarationCS> getContextDeclarations() {
    if (contextDeclarations == null)
    {
      contextDeclarations = new EObjectContainmentEList<ContextDeclarationCS>(ContextDeclarationCS.class, this, OclPackage.PACKAGE_DECLARATION_CS__CONTEXT_DECLARATIONS);
    }
    return contextDeclarations;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<LayoutInformation> getLayoutInformation() {
    if (layoutInformation == null)
    {
      layoutInformation = new EObjectResolvingEList<LayoutInformation>(LayoutInformation.class, this, OclPackage.PACKAGE_DECLARATION_CS__LAYOUT_INFORMATION);
    }
    return layoutInformation;
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
      case OclPackage.PACKAGE_DECLARATION_CS__CONTEXT_DECLARATIONS:
        return ((InternalEList<?>)getContextDeclarations()).basicRemove(otherEnd, msgs);
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
      case OclPackage.PACKAGE_DECLARATION_CS__CONTEXT_DECLARATIONS:
        return getContextDeclarations();
      case OclPackage.PACKAGE_DECLARATION_CS__LAYOUT_INFORMATION:
        return getLayoutInformation();
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
      case OclPackage.PACKAGE_DECLARATION_CS__CONTEXT_DECLARATIONS:
        getContextDeclarations().clear();
        getContextDeclarations().addAll((Collection<? extends ContextDeclarationCS>)newValue);
        return;
      case OclPackage.PACKAGE_DECLARATION_CS__LAYOUT_INFORMATION:
        getLayoutInformation().clear();
        getLayoutInformation().addAll((Collection<? extends LayoutInformation>)newValue);
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
      case OclPackage.PACKAGE_DECLARATION_CS__CONTEXT_DECLARATIONS:
        getContextDeclarations().clear();
        return;
      case OclPackage.PACKAGE_DECLARATION_CS__LAYOUT_INFORMATION:
        getLayoutInformation().clear();
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
      case OclPackage.PACKAGE_DECLARATION_CS__CONTEXT_DECLARATIONS:
        return contextDeclarations != null && !contextDeclarations.isEmpty();
      case OclPackage.PACKAGE_DECLARATION_CS__LAYOUT_INFORMATION:
        return layoutInformation != null && !layoutInformation.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //PackageDeclarationCSImpl
