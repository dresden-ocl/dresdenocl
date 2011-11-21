/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.tracer.tracermodel.impl;

import java.util.Collection;
import java.util.UUID;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Tracer Item</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.impl.TracerItemImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.impl.TracerItemImpl#getResult <em>Result</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.impl.TracerItemImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.impl.TracerItemImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.impl.TracerItemImpl#getUUID <em>UUID</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.impl.TracerItemImpl#getModelInstanceElement <em>Model Instance Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TracerItemImpl extends EObjectImpl implements TracerItem {
    /**
         * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see #getExpression()
         * @generated
         * @ordered
         */
    protected EObject expression;

    /**
         * The default value of the '{@link #getResult() <em>Result</em>}' attribute.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see #getResult()
         * @generated
         * @ordered
         */
    protected static final OclAny RESULT_EDEFAULT = null;

    /**
         * The cached value of the '{@link #getResult() <em>Result</em>}' attribute.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see #getResult()
         * @generated
         * @ordered
         */
    protected OclAny result = RESULT_EDEFAULT;

    /**
         * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see #getParent()
         * @generated
         * @ordered
         */
    protected TracerItem parent;

    /**
         * The cached value of the '{@link #getChildren() <em>Children</em>}' reference list.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see #getChildren()
         * @generated
         * @ordered
         */
    protected EList<TracerItem> children;

    /**
         * The default value of the '{@link #getUUID() <em>UUID</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getUUID()
         * @generated
         * @ordered
         */
        protected static final UUID UUID_EDEFAULT = null;

/**
         * The cached value of the '{@link #getUUID() <em>UUID</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getUUID()
         * @generated
         * @ordered
         */
        protected UUID uuid = UUID_EDEFAULT;

/**
         * The default value of the '{@link #getModelInstanceElement() <em>Model Instance Element</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getModelInstanceElement()
         * @generated
         * @ordered
         */
        protected static final IModelInstanceElement MODEL_INSTANCE_ELEMENT_EDEFAULT = null;

/**
         * The cached value of the '{@link #getModelInstanceElement() <em>Model Instance Element</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getModelInstanceElement()
         * @generated
         * @ordered
         */
        protected IModelInstanceElement modelInstanceElement = MODEL_INSTANCE_ELEMENT_EDEFAULT;

/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    protected TracerItemImpl() {
                super();
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    @Override
    protected EClass eStaticClass() {
                return TracermodelPackage.Literals.TRACER_ITEM;
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public EObject getExpression() {
                return expression;
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public void setExpression(EObject newExpression) {
                EObject oldExpression = expression;
                expression = newExpression;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TracermodelPackage.TRACER_ITEM__EXPRESSION, oldExpression, expression));
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public OclAny getResult() {
                return result;
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public void setResult(OclAny newResult) {
                OclAny oldResult = result;
                result = newResult;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TracermodelPackage.TRACER_ITEM__RESULT, oldResult, result));
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public TracerItem getParent() {
                if (parent != null && parent.eIsProxy()) {
                        InternalEObject oldParent = (InternalEObject)parent;
                        parent = (TracerItem)eResolveProxy(oldParent);
                        if (parent != oldParent) {
                                if (eNotificationRequired())
                                        eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracermodelPackage.TRACER_ITEM__PARENT, oldParent, parent));
                        }
                }
                return parent;
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public TracerItem basicGetParent() {
                return parent;
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public void setParent(TracerItem newParent) {
                TracerItem oldParent = parent;
                parent = newParent;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TracermodelPackage.TRACER_ITEM__PARENT, oldParent, parent));
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public EList<TracerItem> getChildren() {
                if (children == null) {
                        children = new EObjectResolvingEList<TracerItem>(TracerItem.class, this, TracermodelPackage.TRACER_ITEM__CHILDREN);
                }
                return children;
        }

    /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public UUID getUUID() {
                return uuid;
        }

/**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setUUID(UUID newUUID) {
                UUID oldUUID = uuid;
                uuid = newUUID;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TracermodelPackage.TRACER_ITEM__UUID, oldUUID, uuid));
        }

/**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public IModelInstanceElement getModelInstanceElement() {
                return modelInstanceElement;
        }

/**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setModelInstanceElement(IModelInstanceElement newModelInstanceElement) {
                IModelInstanceElement oldModelInstanceElement = modelInstanceElement;
                modelInstanceElement = newModelInstanceElement;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, TracermodelPackage.TRACER_ITEM__MODEL_INSTANCE_ELEMENT, oldModelInstanceElement, modelInstanceElement));
        }

/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
                switch (featureID) {
                        case TracermodelPackage.TRACER_ITEM__EXPRESSION:
                                return getExpression();
                        case TracermodelPackage.TRACER_ITEM__RESULT:
                                return getResult();
                        case TracermodelPackage.TRACER_ITEM__PARENT:
                                if (resolve) return getParent();
                                return basicGetParent();
                        case TracermodelPackage.TRACER_ITEM__CHILDREN:
                                return getChildren();
                        case TracermodelPackage.TRACER_ITEM__UUID:
                                return getUUID();
                        case TracermodelPackage.TRACER_ITEM__MODEL_INSTANCE_ELEMENT:
                                return getModelInstanceElement();
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
                        case TracermodelPackage.TRACER_ITEM__EXPRESSION:
                                setExpression((EObject)newValue);
                                return;
                        case TracermodelPackage.TRACER_ITEM__RESULT:
                                setResult((OclAny)newValue);
                                return;
                        case TracermodelPackage.TRACER_ITEM__PARENT:
                                setParent((TracerItem)newValue);
                                return;
                        case TracermodelPackage.TRACER_ITEM__CHILDREN:
                                getChildren().clear();
                                getChildren().addAll((Collection<? extends TracerItem>)newValue);
                                return;
                        case TracermodelPackage.TRACER_ITEM__UUID:
                                setUUID((UUID)newValue);
                                return;
                        case TracermodelPackage.TRACER_ITEM__MODEL_INSTANCE_ELEMENT:
                                setModelInstanceElement((IModelInstanceElement)newValue);
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
                        case TracermodelPackage.TRACER_ITEM__EXPRESSION:
                                setExpression((EObject)null);
                                return;
                        case TracermodelPackage.TRACER_ITEM__RESULT:
                                setResult(RESULT_EDEFAULT);
                                return;
                        case TracermodelPackage.TRACER_ITEM__PARENT:
                                setParent((TracerItem)null);
                                return;
                        case TracermodelPackage.TRACER_ITEM__CHILDREN:
                                getChildren().clear();
                                return;
                        case TracermodelPackage.TRACER_ITEM__UUID:
                                setUUID(UUID_EDEFAULT);
                                return;
                        case TracermodelPackage.TRACER_ITEM__MODEL_INSTANCE_ELEMENT:
                                setModelInstanceElement(MODEL_INSTANCE_ELEMENT_EDEFAULT);
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
                        case TracermodelPackage.TRACER_ITEM__EXPRESSION:
                                return expression != null;
                        case TracermodelPackage.TRACER_ITEM__RESULT:
                                return RESULT_EDEFAULT == null ? result != null : !RESULT_EDEFAULT.equals(result);
                        case TracermodelPackage.TRACER_ITEM__PARENT:
                                return parent != null;
                        case TracermodelPackage.TRACER_ITEM__CHILDREN:
                                return children != null && !children.isEmpty();
                        case TracermodelPackage.TRACER_ITEM__UUID:
                                return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
                        case TracermodelPackage.TRACER_ITEM__MODEL_INSTANCE_ELEMENT:
                                return MODEL_INSTANCE_ELEMENT_EDEFAULT == null ? modelInstanceElement != null : !MODEL_INSTANCE_ELEMENT_EDEFAULT.equals(modelInstanceElement);
                }
                return super.eIsSet(featureID);
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    @Override
    public String toString() {
                if (eIsProxy()) return super.toString();

                StringBuffer result = new StringBuffer(super.toString());
                result.append(" (result: ");
                result.append(result);
                result.append(", UUID: ");
                result.append(uuid);
                result.append(", modelInstanceElement: ");
                result.append(modelInstanceElement);
                result.append(')');
                return result.toString();
        }

} // TracerItemImpl
