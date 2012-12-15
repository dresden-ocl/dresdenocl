/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.pml.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import tudresden.ocl20.pivot.examples.pml.ExtensionPoint;
import tudresden.ocl20.pivot.examples.pml.JavaType;
import tudresden.ocl20.pivot.examples.pml.Plugin;
import tudresden.ocl20.pivot.examples.pml.PmlPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Extension Point</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.impl.ExtensionPointImpl#getId <em>Id</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.impl.ExtensionPointImpl#getPlugin <em>Plugin</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.impl.ExtensionPointImpl#getInterface <em>Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtensionPointImpl extends EObjectImpl implements ExtensionPoint {

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
	 * The cached value of the '{@link #getInterface() <em>Interface</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getInterface()
	 * @generated
	 * @ordered
	 */
	protected JavaType interface_;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtensionPointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PmlPackage.Literals.EXTENSION_POINT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PmlPackage.EXTENSION_POINT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Plugin getPlugin() {
		if (eContainerFeatureID() != PmlPackage.EXTENSION_POINT__PLUGIN) return null;
		return (Plugin)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPlugin(Plugin newPlugin,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPlugin, PmlPackage.EXTENSION_POINT__PLUGIN, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPlugin(Plugin newPlugin) {
		if (newPlugin != eInternalContainer() || (eContainerFeatureID() != PmlPackage.EXTENSION_POINT__PLUGIN && newPlugin != null)) {
			if (EcoreUtil.isAncestor(this, newPlugin))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPlugin != null)
				msgs = ((InternalEObject)newPlugin).eInverseAdd(this, PmlPackage.PLUGIN__EXTENSION_POINTS, Plugin.class, msgs);
			msgs = basicSetPlugin(newPlugin, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PmlPackage.EXTENSION_POINT__PLUGIN, newPlugin, newPlugin));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public JavaType getInterface() {
		if (interface_ != null && interface_.eIsProxy()) {
			InternalEObject oldInterface = (InternalEObject)interface_;
			interface_ = (JavaType)eResolveProxy(oldInterface);
			if (interface_ != oldInterface) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PmlPackage.EXTENSION_POINT__INTERFACE, oldInterface, interface_));
			}
		}
		return interface_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public JavaType basicGetInterface() {
		return interface_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterface(JavaType newInterface) {
		JavaType oldInterface = interface_;
		interface_ = newInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PmlPackage.EXTENSION_POINT__INTERFACE, oldInterface, interface_));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
			NotificationChain msgs) {
		switch (featureID) {
			case PmlPackage.EXTENSION_POINT__PLUGIN:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPlugin((Plugin)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PmlPackage.EXTENSION_POINT__PLUGIN:
				return basicSetPlugin(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case PmlPackage.EXTENSION_POINT__PLUGIN:
				return eInternalContainer().eInverseRemove(this, PmlPackage.PLUGIN__EXTENSION_POINTS, Plugin.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PmlPackage.EXTENSION_POINT__ID:
				return getId();
			case PmlPackage.EXTENSION_POINT__PLUGIN:
				return getPlugin();
			case PmlPackage.EXTENSION_POINT__INTERFACE:
				if (resolve) return getInterface();
				return basicGetInterface();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PmlPackage.EXTENSION_POINT__ID:
				setId((String)newValue);
				return;
			case PmlPackage.EXTENSION_POINT__PLUGIN:
				setPlugin((Plugin)newValue);
				return;
			case PmlPackage.EXTENSION_POINT__INTERFACE:
				setInterface((JavaType)newValue);
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
			case PmlPackage.EXTENSION_POINT__ID:
				setId(ID_EDEFAULT);
				return;
			case PmlPackage.EXTENSION_POINT__PLUGIN:
				setPlugin((Plugin)null);
				return;
			case PmlPackage.EXTENSION_POINT__INTERFACE:
				setInterface((JavaType)null);
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
			case PmlPackage.EXTENSION_POINT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case PmlPackage.EXTENSION_POINT__PLUGIN:
				return getPlugin() != null;
			case PmlPackage.EXTENSION_POINT__INTERFACE:
				return interface_ != null;
		}
		return super.eIsSet(featureID);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#toString()
	 * @generated NOT
	 */
	public String toString() {

		String result;

		result = this.getClass().getSimpleName();
		result += "[";
		result += "id = " + id;

		if (interface_ != null) {
			result += ", interface = " + interface_.getFullyQualifiedName();
		}
		// no else.
		result += "]";

		return result;
	}

} // ExtensionPointImpl
