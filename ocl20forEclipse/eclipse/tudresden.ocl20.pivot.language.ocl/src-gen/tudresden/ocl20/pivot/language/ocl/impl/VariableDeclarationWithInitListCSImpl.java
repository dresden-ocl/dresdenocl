/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS;
import tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Declaration With Init List CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithInitListCSImpl#getVariableDeclarations <em>Variable Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariableDeclarationWithInitListCSImpl extends EObjectImpl implements VariableDeclarationWithInitListCS {
	/**
	 * The cached value of the '{@link #getVariableDeclarations() <em>Variable Declarations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariableDeclarations()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableDeclarationWithInitCS> variableDeclarations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationWithInitListCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.VARIABLE_DECLARATION_WITH_INIT_LIST_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariableDeclarationWithInitCS> getVariableDeclarations() {
		if (variableDeclarations == null) {
			variableDeclarations = new EObjectContainmentEList<VariableDeclarationWithInitCS>(VariableDeclarationWithInitCS.class, this, OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS);
		}
		return variableDeclarations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS:
				return ((InternalEList<?>)getVariableDeclarations()).basicRemove(otherEnd, msgs);
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
		switch (featureID) {
			case OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS:
				return getVariableDeclarations();
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
		switch (featureID) {
			case OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS:
				getVariableDeclarations().clear();
				getVariableDeclarations().addAll((Collection<? extends VariableDeclarationWithInitCS>)newValue);
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
		switch (featureID) {
			case OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS:
				getVariableDeclarations().clear();
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
		switch (featureID) {
			case OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS:
				return variableDeclarations != null && !variableDeclarations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //VariableDeclarationWithInitListCSImpl
