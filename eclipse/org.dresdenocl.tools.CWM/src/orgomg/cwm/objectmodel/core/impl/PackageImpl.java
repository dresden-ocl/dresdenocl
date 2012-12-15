/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.foundation.softwaredeployment.DataManager;
import orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage;

import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.PackageImpl#getImportedElement <em>Imported Element</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.PackageImpl#getDataManager <em>Data Manager</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageImpl extends NamespaceImpl implements orgomg.cwm.objectmodel.core.Package {
	/**
	 * The cached value of the '{@link #getImportedElement() <em>Imported Element</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportedElement()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelElement> importedElement;

	/**
	 * The cached value of the '{@link #getDataManager() <em>Data Manager</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataManager()
	 * @generated
	 * @ordered
	 */
	protected EList<DataManager> dataManager;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelElement> getImportedElement() {
		if (importedElement == null) {
			importedElement = new EObjectWithInverseResolvingEList.ManyInverse<ModelElement>(ModelElement.class, this, CorePackage.PACKAGE__IMPORTED_ELEMENT, CorePackage.MODEL_ELEMENT__IMPORTER);
		}
		return importedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataManager> getDataManager() {
		if (dataManager == null) {
			dataManager = new EObjectWithInverseResolvingEList.ManyInverse<DataManager>(DataManager.class, this, CorePackage.PACKAGE__DATA_MANAGER, SoftwaredeploymentPackage.DATA_MANAGER__DATA_PACKAGE);
		}
		return dataManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.PACKAGE__IMPORTED_ELEMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getImportedElement()).basicAdd(otherEnd, msgs);
			case CorePackage.PACKAGE__DATA_MANAGER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDataManager()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.PACKAGE__IMPORTED_ELEMENT:
				return ((InternalEList<?>)getImportedElement()).basicRemove(otherEnd, msgs);
			case CorePackage.PACKAGE__DATA_MANAGER:
				return ((InternalEList<?>)getDataManager()).basicRemove(otherEnd, msgs);
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
			case CorePackage.PACKAGE__IMPORTED_ELEMENT:
				return getImportedElement();
			case CorePackage.PACKAGE__DATA_MANAGER:
				return getDataManager();
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
			case CorePackage.PACKAGE__IMPORTED_ELEMENT:
				getImportedElement().clear();
				getImportedElement().addAll((Collection<? extends ModelElement>)newValue);
				return;
			case CorePackage.PACKAGE__DATA_MANAGER:
				getDataManager().clear();
				getDataManager().addAll((Collection<? extends DataManager>)newValue);
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
			case CorePackage.PACKAGE__IMPORTED_ELEMENT:
				getImportedElement().clear();
				return;
			case CorePackage.PACKAGE__DATA_MANAGER:
				getDataManager().clear();
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
			case CorePackage.PACKAGE__IMPORTED_ELEMENT:
				return importedElement != null && !importedElement.isEmpty();
			case CorePackage.PACKAGE__DATA_MANAGER:
				return dataManager != null && !dataManager.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PackageImpl
