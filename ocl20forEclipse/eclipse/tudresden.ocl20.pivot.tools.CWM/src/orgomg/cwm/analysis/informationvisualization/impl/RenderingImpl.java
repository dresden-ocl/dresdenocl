/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.informationvisualization.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage;
import orgomg.cwm.analysis.informationvisualization.RenderedObject;
import orgomg.cwm.analysis.informationvisualization.RenderedObjectSet;
import orgomg.cwm.analysis.informationvisualization.Rendering;

import orgomg.cwm.foundation.expressions.ExpressionNode;

import orgomg.cwm.objectmodel.core.impl.FeatureImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rendering</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderingImpl#getFormula <em>Formula</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderingImpl#getAction <em>Action</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderingImpl#getFileName <em>File Name</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderingImpl#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderingImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderingImpl#getDefaultedRenderedObject <em>Defaulted Rendered Object</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderingImpl#getRenderedObjectSet <em>Rendered Object Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RenderingImpl extends FeatureImpl implements Rendering {
	/**
	 * The cached value of the '{@link #getFormula() <em>Formula</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormula()
	 * @generated
	 * @ordered
	 */
	protected ExpressionNode formula;

	/**
	 * The default value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected String action = ACTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
	protected String fileName = FILE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected String url = URL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDefaultedRenderedObject() <em>Defaulted Rendered Object</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultedRenderedObject()
	 * @generated
	 * @ordered
	 */
	protected EList<RenderedObject> defaultedRenderedObject;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RenderingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InformationvisualizationPackage.Literals.RENDERING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionNode getFormula() {
		return formula;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFormula(ExpressionNode newFormula, NotificationChain msgs) {
		ExpressionNode oldFormula = formula;
		formula = newFormula;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERING__FORMULA, oldFormula, newFormula);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormula(ExpressionNode newFormula) {
		if (newFormula != formula) {
			NotificationChain msgs = null;
			if (formula != null)
				msgs = ((InternalEObject)formula).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationvisualizationPackage.RENDERING__FORMULA, null, msgs);
			if (newFormula != null)
				msgs = ((InternalEObject)newFormula).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationvisualizationPackage.RENDERING__FORMULA, null, msgs);
			msgs = basicSetFormula(newFormula, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERING__FORMULA, newFormula, newFormula));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(String newAction) {
		String oldAction = action;
		action = newAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERING__ACTION, oldAction, action));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileName(String newFileName) {
		String oldFileName = fileName;
		fileName = newFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERING__FILE_NAME, oldFileName, fileName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERING__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrl(String newUrl) {
		String oldUrl = url;
		url = newUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERING__URL, oldUrl, url));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RenderedObject> getDefaultedRenderedObject() {
		if (defaultedRenderedObject == null) {
			defaultedRenderedObject = new EObjectWithInverseResolvingEList<RenderedObject>(RenderedObject.class, this, InformationvisualizationPackage.RENDERING__DEFAULTED_RENDERED_OBJECT, InformationvisualizationPackage.RENDERED_OBJECT__DEFAULT_RENDERING);
		}
		return defaultedRenderedObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderedObjectSet getRenderedObjectSet() {
		if (eContainerFeatureID() != InformationvisualizationPackage.RENDERING__RENDERED_OBJECT_SET) return null;
		return (RenderedObjectSet)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRenderedObjectSet(RenderedObjectSet newRenderedObjectSet, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRenderedObjectSet, InformationvisualizationPackage.RENDERING__RENDERED_OBJECT_SET, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRenderedObjectSet(RenderedObjectSet newRenderedObjectSet) {
		if (newRenderedObjectSet != eInternalContainer() || (eContainerFeatureID() != InformationvisualizationPackage.RENDERING__RENDERED_OBJECT_SET && newRenderedObjectSet != null)) {
			if (EcoreUtil.isAncestor(this, newRenderedObjectSet))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRenderedObjectSet != null)
				msgs = ((InternalEObject)newRenderedObjectSet).eInverseAdd(this, InformationvisualizationPackage.RENDERED_OBJECT_SET__RENDERING, RenderedObjectSet.class, msgs);
			msgs = basicSetRenderedObjectSet(newRenderedObjectSet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERING__RENDERED_OBJECT_SET, newRenderedObjectSet, newRenderedObjectSet));
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
			case InformationvisualizationPackage.RENDERING__DEFAULTED_RENDERED_OBJECT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDefaultedRenderedObject()).basicAdd(otherEnd, msgs);
			case InformationvisualizationPackage.RENDERING__RENDERED_OBJECT_SET:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRenderedObjectSet((RenderedObjectSet)otherEnd, msgs);
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
			case InformationvisualizationPackage.RENDERING__FORMULA:
				return basicSetFormula(null, msgs);
			case InformationvisualizationPackage.RENDERING__DEFAULTED_RENDERED_OBJECT:
				return ((InternalEList<?>)getDefaultedRenderedObject()).basicRemove(otherEnd, msgs);
			case InformationvisualizationPackage.RENDERING__RENDERED_OBJECT_SET:
				return basicSetRenderedObjectSet(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case InformationvisualizationPackage.RENDERING__RENDERED_OBJECT_SET:
				return eInternalContainer().eInverseRemove(this, InformationvisualizationPackage.RENDERED_OBJECT_SET__RENDERING, RenderedObjectSet.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case InformationvisualizationPackage.RENDERING__FORMULA:
				return getFormula();
			case InformationvisualizationPackage.RENDERING__ACTION:
				return getAction();
			case InformationvisualizationPackage.RENDERING__FILE_NAME:
				return getFileName();
			case InformationvisualizationPackage.RENDERING__TYPE:
				return getType();
			case InformationvisualizationPackage.RENDERING__URL:
				return getUrl();
			case InformationvisualizationPackage.RENDERING__DEFAULTED_RENDERED_OBJECT:
				return getDefaultedRenderedObject();
			case InformationvisualizationPackage.RENDERING__RENDERED_OBJECT_SET:
				return getRenderedObjectSet();
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
			case InformationvisualizationPackage.RENDERING__FORMULA:
				setFormula((ExpressionNode)newValue);
				return;
			case InformationvisualizationPackage.RENDERING__ACTION:
				setAction((String)newValue);
				return;
			case InformationvisualizationPackage.RENDERING__FILE_NAME:
				setFileName((String)newValue);
				return;
			case InformationvisualizationPackage.RENDERING__TYPE:
				setType((String)newValue);
				return;
			case InformationvisualizationPackage.RENDERING__URL:
				setUrl((String)newValue);
				return;
			case InformationvisualizationPackage.RENDERING__DEFAULTED_RENDERED_OBJECT:
				getDefaultedRenderedObject().clear();
				getDefaultedRenderedObject().addAll((Collection<? extends RenderedObject>)newValue);
				return;
			case InformationvisualizationPackage.RENDERING__RENDERED_OBJECT_SET:
				setRenderedObjectSet((RenderedObjectSet)newValue);
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
			case InformationvisualizationPackage.RENDERING__FORMULA:
				setFormula((ExpressionNode)null);
				return;
			case InformationvisualizationPackage.RENDERING__ACTION:
				setAction(ACTION_EDEFAULT);
				return;
			case InformationvisualizationPackage.RENDERING__FILE_NAME:
				setFileName(FILE_NAME_EDEFAULT);
				return;
			case InformationvisualizationPackage.RENDERING__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case InformationvisualizationPackage.RENDERING__URL:
				setUrl(URL_EDEFAULT);
				return;
			case InformationvisualizationPackage.RENDERING__DEFAULTED_RENDERED_OBJECT:
				getDefaultedRenderedObject().clear();
				return;
			case InformationvisualizationPackage.RENDERING__RENDERED_OBJECT_SET:
				setRenderedObjectSet((RenderedObjectSet)null);
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
			case InformationvisualizationPackage.RENDERING__FORMULA:
				return formula != null;
			case InformationvisualizationPackage.RENDERING__ACTION:
				return ACTION_EDEFAULT == null ? action != null : !ACTION_EDEFAULT.equals(action);
			case InformationvisualizationPackage.RENDERING__FILE_NAME:
				return FILE_NAME_EDEFAULT == null ? fileName != null : !FILE_NAME_EDEFAULT.equals(fileName);
			case InformationvisualizationPackage.RENDERING__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case InformationvisualizationPackage.RENDERING__URL:
				return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
			case InformationvisualizationPackage.RENDERING__DEFAULTED_RENDERED_OBJECT:
				return defaultedRenderedObject != null && !defaultedRenderedObject.isEmpty();
			case InformationvisualizationPackage.RENDERING__RENDERED_OBJECT_SET:
				return getRenderedObjectSet() != null;
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
		result.append(" (action: ");
		result.append(action);
		result.append(", fileName: ");
		result.append(fileName);
		result.append(", type: ");
		result.append(type);
		result.append(", url: ");
		result.append(url);
		result.append(')');
		return result.toString();
	}

} //RenderingImpl
