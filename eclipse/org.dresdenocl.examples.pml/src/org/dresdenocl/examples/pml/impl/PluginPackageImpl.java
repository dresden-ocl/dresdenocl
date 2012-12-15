/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.pml.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.examples.pml.Feature;
import tudresden.ocl20.pivot.examples.pml.JavaType;
import tudresden.ocl20.pivot.examples.pml.Plugin;
import tudresden.ocl20.pivot.examples.pml.PluginPackage;
import tudresden.ocl20.pivot.examples.pml.PmlPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Plugin Package</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.impl.PluginPackageImpl#getPlugins <em>Plugins</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.impl.PluginPackageImpl#getTypes <em>Types</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.impl.PluginPackageImpl#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PluginPackageImpl extends EObjectImpl implements PluginPackage {

	/**
	 * The cached value of the '{@link #getPlugins() <em>Plugins</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPlugins()
	 * @generated
	 * @ordered
	 */
	protected EList<Plugin> plugins;

	/**
	 * The cached value of the '{@link #getTypes() <em>Types</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<JavaType> types;

	/**
	 * The cached value of the '{@link #getFeatures() <em>Features</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<Feature> features;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected PluginPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PmlPackage.Literals.PLUGIN_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Plugin> getPlugins() {
		if (plugins == null) {
			plugins = new EObjectContainmentEList<Plugin>(Plugin.class, this, PmlPackage.PLUGIN_PACKAGE__PLUGINS);
		}
		return plugins;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JavaType> getTypes() {
		if (types == null) {
			types = new EObjectContainmentEList<JavaType>(JavaType.class, this, PmlPackage.PLUGIN_PACKAGE__TYPES);
		}
		return types;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Feature> getFeatures() {
		if (features == null) {
			features = new EObjectContainmentEList<Feature>(Feature.class, this, PmlPackage.PLUGIN_PACKAGE__FEATURES);
		}
		return features;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PmlPackage.PLUGIN_PACKAGE__PLUGINS:
				return ((InternalEList<?>)getPlugins()).basicRemove(otherEnd, msgs);
			case PmlPackage.PLUGIN_PACKAGE__TYPES:
				return ((InternalEList<?>)getTypes()).basicRemove(otherEnd, msgs);
			case PmlPackage.PLUGIN_PACKAGE__FEATURES:
				return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PmlPackage.PLUGIN_PACKAGE__PLUGINS:
				return getPlugins();
			case PmlPackage.PLUGIN_PACKAGE__TYPES:
				return getTypes();
			case PmlPackage.PLUGIN_PACKAGE__FEATURES:
				return getFeatures();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PmlPackage.PLUGIN_PACKAGE__PLUGINS:
				getPlugins().clear();
				getPlugins().addAll((Collection<? extends Plugin>)newValue);
				return;
			case PmlPackage.PLUGIN_PACKAGE__TYPES:
				getTypes().clear();
				getTypes().addAll((Collection<? extends JavaType>)newValue);
				return;
			case PmlPackage.PLUGIN_PACKAGE__FEATURES:
				getFeatures().clear();
				getFeatures().addAll((Collection<? extends Feature>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PmlPackage.PLUGIN_PACKAGE__PLUGINS:
				getPlugins().clear();
				return;
			case PmlPackage.PLUGIN_PACKAGE__TYPES:
				getTypes().clear();
				return;
			case PmlPackage.PLUGIN_PACKAGE__FEATURES:
				getFeatures().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PmlPackage.PLUGIN_PACKAGE__PLUGINS:
				return plugins != null && !plugins.isEmpty();
			case PmlPackage.PLUGIN_PACKAGE__TYPES:
				return types != null && !types.isEmpty();
			case PmlPackage.PLUGIN_PACKAGE__FEATURES:
				return features != null && !features.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#toString()
	 * @generated NOT
	 */
	public String toString() {

		String result;

		result = this.getClass().getSimpleName();

		return result;
	}
} // PluginPackageImpl
