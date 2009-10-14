/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.pml.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.examples.pml.Extension;
import tudresden.ocl20.pivot.examples.pml.ExtensionPoint;
import tudresden.ocl20.pivot.examples.pml.Feature;
import tudresden.ocl20.pivot.examples.pml.JavaType;
import tudresden.ocl20.pivot.examples.pml.Operation;
import tudresden.ocl20.pivot.examples.pml.Plugin;
import tudresden.ocl20.pivot.examples.pml.PmlPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Plugin</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link tudresden.ocl20.pivot.examples.pml.impl.PluginImpl#getId <em>Id
 * </em>}</li>
 * <li>{@link tudresden.ocl20.pivot.examples.pml.impl.PluginImpl#getName <em>
 * Name</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.examples.pml.impl.PluginImpl#getVersion <em>
 * Version</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.examples.pml.impl.PluginImpl#getProvider
 * <em>Provider</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.examples.pml.impl.PluginImpl#getFeature <em>
 * Feature</em>}</li>
 * <li>
 * {@link tudresden.ocl20.pivot.examples.pml.impl.PluginImpl#getExtensionPoints
 * <em>Extension Points</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.examples.pml.impl.PluginImpl#getExtensions
 * <em>Extensions</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.examples.pml.impl.PluginImpl#getServices
 * <em>Services</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.examples.pml.impl.PluginImpl#getActivator
 * <em>Activator</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PluginImpl extends EObjectImpl implements Plugin {

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getProvider() <em>Provider</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProvider()
	 * @generated
	 * @ordered
	 */
	protected static final String PROVIDER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProvider() <em>Provider</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProvider()
	 * @generated
	 * @ordered
	 */
	protected String provider = PROVIDER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExtensionPoints()
	 * <em>Extension Points</em>}' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getExtensionPoints()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtensionPoint> extensionPoints;

	/**
	 * The cached value of the '{@link #getExtensions() <em>Extensions</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> extensions;

	/**
	 * The cached value of the '{@link #getServices() <em>Services</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getServices()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> services;

	/**
	 * The cached value of the '{@link #getActivator() <em>Activator</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getActivator()
	 * @generated
	 * @ordered
	 */
	protected JavaType activator;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PluginImpl() {

		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return PmlPackage.Literals.PLUGIN;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getId() {

		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setId(String newId) {

		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PmlPackage.PLUGIN__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName() {

		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String newName) {

		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PmlPackage.PLUGIN__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getVersion() {

		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersion(String newVersion) {

		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PmlPackage.PLUGIN__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getProvider() {

		return provider;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setProvider(String newProvider) {

		String oldProvider = provider;
		provider = newProvider;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PmlPackage.PLUGIN__PROVIDER, oldProvider, provider));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Feature getFeature() {

		if (eContainerFeatureID() != PmlPackage.PLUGIN__FEATURE)
			return null;
		return (Feature) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetFeature(Feature newFeature,
			NotificationChain msgs) {

		msgs =
				eBasicSetContainer((InternalEObject) newFeature,
						PmlPackage.PLUGIN__FEATURE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFeature(Feature newFeature) {

		if (newFeature != eInternalContainer()
				|| (eContainerFeatureID() != PmlPackage.PLUGIN__FEATURE && newFeature != null)) {
			if (EcoreUtil.isAncestor(this, newFeature))
				throw new IllegalArgumentException(
						"Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFeature != null)
				msgs =
						((InternalEObject) newFeature).eInverseAdd(this,
								PmlPackage.FEATURE__PLUGINS, Feature.class, msgs);
			msgs = basicSetFeature(newFeature, msgs);
			if (msgs != null)
				msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PmlPackage.PLUGIN__FEATURE, newFeature, newFeature));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<ExtensionPoint> getExtensionPoints() {

		if (extensionPoints == null) {
			extensionPoints =
					new EObjectContainmentWithInverseEList<ExtensionPoint>(
							ExtensionPoint.class, this, PmlPackage.PLUGIN__EXTENSION_POINTS,
							PmlPackage.EXTENSION_POINT__PLUGIN);
		}
		return extensionPoints;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Extension> getExtensions() {

		if (extensions == null) {
			extensions =
					new EObjectContainmentEList<Extension>(Extension.class, this,
							PmlPackage.PLUGIN__EXTENSIONS);
		}
		return extensions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Operation> getServices() {

		if (services == null) {
			services =
					new EObjectContainmentEList<Operation>(Operation.class, this,
							PmlPackage.PLUGIN__SERVICES);
		}
		return services;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public JavaType getActivator() {

		if (activator != null && activator.eIsProxy()) {
			InternalEObject oldActivator = (InternalEObject) activator;
			activator = (JavaType) eResolveProxy(oldActivator);
			if (activator != oldActivator) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							PmlPackage.PLUGIN__ACTIVATOR, oldActivator, activator));
			}
		}
		return activator;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public JavaType basicGetActivator() {

		return activator;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setActivator(JavaType newActivator) {

		JavaType oldActivator = activator;
		activator = newActivator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PmlPackage.PLUGIN__ACTIVATOR, oldActivator, activator));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getNameOf(Feature feature) {

		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
			NotificationChain msgs) {

		switch (featureID) {
		case PmlPackage.PLUGIN__FEATURE:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetFeature((Feature) otherEnd, msgs);
		case PmlPackage.PLUGIN__EXTENSION_POINTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getExtensionPoints())
					.basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {

		switch (featureID) {
		case PmlPackage.PLUGIN__FEATURE:
			return basicSetFeature(null, msgs);
		case PmlPackage.PLUGIN__EXTENSION_POINTS:
			return ((InternalEList<?>) getExtensionPoints()).basicRemove(otherEnd,
					msgs);
		case PmlPackage.PLUGIN__EXTENSIONS:
			return ((InternalEList<?>) getExtensions()).basicRemove(otherEnd, msgs);
		case PmlPackage.PLUGIN__SERVICES:
			return ((InternalEList<?>) getServices()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {

		switch (eContainerFeatureID()) {
		case PmlPackage.PLUGIN__FEATURE:
			return eInternalContainer().eInverseRemove(this,
					PmlPackage.FEATURE__PLUGINS, Feature.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {

		switch (featureID) {
		case PmlPackage.PLUGIN__ID:
			return getId();
		case PmlPackage.PLUGIN__NAME:
			return getName();
		case PmlPackage.PLUGIN__VERSION:
			return getVersion();
		case PmlPackage.PLUGIN__PROVIDER:
			return getProvider();
		case PmlPackage.PLUGIN__FEATURE:
			return getFeature();
		case PmlPackage.PLUGIN__EXTENSION_POINTS:
			return getExtensionPoints();
		case PmlPackage.PLUGIN__EXTENSIONS:
			return getExtensions();
		case PmlPackage.PLUGIN__SERVICES:
			return getServices();
		case PmlPackage.PLUGIN__ACTIVATOR:
			if (resolve)
				return getActivator();
			return basicGetActivator();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {

		switch (featureID) {
		case PmlPackage.PLUGIN__ID:
			setId((String) newValue);
			return;
		case PmlPackage.PLUGIN__NAME:
			setName((String) newValue);
			return;
		case PmlPackage.PLUGIN__VERSION:
			setVersion((String) newValue);
			return;
		case PmlPackage.PLUGIN__PROVIDER:
			setProvider((String) newValue);
			return;
		case PmlPackage.PLUGIN__FEATURE:
			setFeature((Feature) newValue);
			return;
		case PmlPackage.PLUGIN__EXTENSION_POINTS:
			getExtensionPoints().clear();
			getExtensionPoints().addAll(
					(Collection<? extends ExtensionPoint>) newValue);
			return;
		case PmlPackage.PLUGIN__EXTENSIONS:
			getExtensions().clear();
			getExtensions().addAll((Collection<? extends Extension>) newValue);
			return;
		case PmlPackage.PLUGIN__SERVICES:
			getServices().clear();
			getServices().addAll((Collection<? extends Operation>) newValue);
			return;
		case PmlPackage.PLUGIN__ACTIVATOR:
			setActivator((JavaType) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {

		switch (featureID) {
		case PmlPackage.PLUGIN__ID:
			setId(ID_EDEFAULT);
			return;
		case PmlPackage.PLUGIN__NAME:
			setName(NAME_EDEFAULT);
			return;
		case PmlPackage.PLUGIN__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case PmlPackage.PLUGIN__PROVIDER:
			setProvider(PROVIDER_EDEFAULT);
			return;
		case PmlPackage.PLUGIN__FEATURE:
			setFeature((Feature) null);
			return;
		case PmlPackage.PLUGIN__EXTENSION_POINTS:
			getExtensionPoints().clear();
			return;
		case PmlPackage.PLUGIN__EXTENSIONS:
			getExtensions().clear();
			return;
		case PmlPackage.PLUGIN__SERVICES:
			getServices().clear();
			return;
		case PmlPackage.PLUGIN__ACTIVATOR:
			setActivator((JavaType) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {

		switch (featureID) {
		case PmlPackage.PLUGIN__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case PmlPackage.PLUGIN__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case PmlPackage.PLUGIN__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT
					.equals(version);
		case PmlPackage.PLUGIN__PROVIDER:
			return PROVIDER_EDEFAULT == null ? provider != null : !PROVIDER_EDEFAULT
					.equals(provider);
		case PmlPackage.PLUGIN__FEATURE:
			return getFeature() != null;
		case PmlPackage.PLUGIN__EXTENSION_POINTS:
			return extensionPoints != null && !extensionPoints.isEmpty();
		case PmlPackage.PLUGIN__EXTENSIONS:
			return extensions != null && !extensions.isEmpty();
		case PmlPackage.PLUGIN__SERVICES:
			return services != null && !services.isEmpty();
		case PmlPackage.PLUGIN__ACTIVATOR:
			return activator != null;
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
		result += "[";
		result += "id = " + id;
		result += ", name = " + name;
		result += ", version = " + version;

		if (extensionPoints != null) {
			result += "extensionPoints = [";
			for (int index = 0; index < extensionPoints.size(); index++) {

				if (index > 0) {
					result += ", ";
				}
				// no else.

				result += extensionPoints.get(index).getId();
			}
			result += "]";
		}
		// no else.

		if (extensions != null) {
			result += "extensions = [";
			for (int index = 0; index < extensions.size(); index++) {

				if (index > 0) {
					result += ", ";
				}
				// no else.

				result += extensions.get(index).getExtensionPoint().getId();
			}
			result += "]";
		}
		// no else.

		result += "]";

		return result;
	}

} // PluginImpl
