/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.tracer.tracermodel.impl;

import java.util.UUID;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UUID To Tracer Item Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.impl.UUIDToTracerItemMapImpl#getTypedKey <em>Key</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.impl.UUIDToTracerItemMapImpl#getTypedValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UUIDToTracerItemMapImpl extends EObjectImpl implements BasicEMap.Entry<UUID,TracerItem> {
        /**
         * The default value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getTypedKey()
         * @generated
         * @ordered
         */
        protected static final UUID KEY_EDEFAULT = null;

        /**
         * The cached value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getTypedKey()
         * @generated
         * @ordered
         */
        protected UUID key = KEY_EDEFAULT;

        /**
         * The cached value of the '{@link #getTypedValue() <em>Value</em>}' reference.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getTypedValue()
         * @generated
         * @ordered
         */
        protected TracerItem value;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected UUIDToTracerItemMapImpl() {
                super();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        protected EClass eStaticClass() {
                return TracermodelPackage.Literals.UUID_TO_TRACER_ITEM_MAP;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public UUID getTypedKey() {
                return key;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setTypedKey(UUID newKey) {
                UUID oldKey = key;
                key = newKey;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TracermodelPackage.UUID_TO_TRACER_ITEM_MAP__KEY, oldKey, key));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public TracerItem getTypedValue() {
                if (value != null && value.eIsProxy()) {
                        InternalEObject oldValue = (InternalEObject)value;
                        value = (TracerItem)eResolveProxy(oldValue);
                        if (value != oldValue) {
                                if (eNotificationRequired())
                                        eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracermodelPackage.UUID_TO_TRACER_ITEM_MAP__VALUE, oldValue, value));
                        }
                }
                return value;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public TracerItem basicGetTypedValue() {
                return value;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setTypedValue(TracerItem newValue) {
                TracerItem oldValue = value;
                value = newValue;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TracermodelPackage.UUID_TO_TRACER_ITEM_MAP__VALUE, oldValue, value));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public Object eGet(int featureID, boolean resolve, boolean coreType) {
                switch (featureID) {
                        case TracermodelPackage.UUID_TO_TRACER_ITEM_MAP__KEY:
                                return getTypedKey();
                        case TracermodelPackage.UUID_TO_TRACER_ITEM_MAP__VALUE:
                                if (resolve) return getTypedValue();
                                return basicGetTypedValue();
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
                switch (featureID) {
                        case TracermodelPackage.UUID_TO_TRACER_ITEM_MAP__KEY:
                                setTypedKey((UUID)newValue);
                                return;
                        case TracermodelPackage.UUID_TO_TRACER_ITEM_MAP__VALUE:
                                setTypedValue((TracerItem)newValue);
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
                        case TracermodelPackage.UUID_TO_TRACER_ITEM_MAP__KEY:
                                setTypedKey(KEY_EDEFAULT);
                                return;
                        case TracermodelPackage.UUID_TO_TRACER_ITEM_MAP__VALUE:
                                setTypedValue((TracerItem)null);
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
                        case TracermodelPackage.UUID_TO_TRACER_ITEM_MAP__KEY:
                                return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
                        case TracermodelPackage.UUID_TO_TRACER_ITEM_MAP__VALUE:
                                return value != null;
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
                result.append(" (key: ");
                result.append(key);
                result.append(')');
                return result.toString();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected int hash = -1;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public int getHash() {
                if (hash == -1) {
                        Object theKey = getKey();
                        hash = (theKey == null ? 0 : theKey.hashCode());
                }
                return hash;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setHash(int hash) {
                this.hash = hash;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public UUID getKey() {
                return getTypedKey();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setKey(UUID key) {
                setTypedKey(key);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public TracerItem getValue() {
                return getTypedValue();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public TracerItem setValue(TracerItem value) {
                TracerItem oldValue = getValue();
                setTypedValue(value);
                return oldValue;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("unchecked")
        public EMap<UUID, TracerItem> getEMap() {
                EObject container = eContainer();
                return container == null ? null : (EMap<UUID, TracerItem>)container.eGet(eContainmentFeature());
        }

} //UUIDToTracerItemMapImpl
