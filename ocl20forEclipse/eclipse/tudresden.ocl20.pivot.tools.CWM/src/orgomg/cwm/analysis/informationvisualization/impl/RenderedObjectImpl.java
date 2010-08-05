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
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage;
import orgomg.cwm.analysis.informationvisualization.RenderedObject;
import orgomg.cwm.analysis.informationvisualization.Rendering;

import orgomg.cwm.foundation.expressions.ExpressionNode;

import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.ModelElement;

import orgomg.cwm.objectmodel.core.impl.ClassifierImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rendered Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl#getFormula <em>Formula</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl#getAction <em>Action</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl#getFileName <em>File Name</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl#getComposite <em>Composite</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl#getDefaultRendering <em>Default Rendering</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl#getModelElement <em>Model Element</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl#getNeighbor <em>Neighbor</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl#getReferencingNeighbor <em>Referencing Neighbor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RenderedObjectImpl extends ClassifierImpl implements RenderedObject {
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
	 * The cached value of the '{@link #getComposite() <em>Composite</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComposite()
	 * @generated
	 * @ordered
	 */
	protected EList<RenderedObject> composite;

	/**
	 * The cached value of the '{@link #getComponent() <em>Component</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected EList<RenderedObject> component;

	/**
	 * The cached value of the '{@link #getDefaultRendering() <em>Default Rendering</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultRendering()
	 * @generated
	 * @ordered
	 */
	protected Rendering defaultRendering;

	/**
	 * The cached value of the '{@link #getModelElement() <em>Model Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelElement()
	 * @generated
	 * @ordered
	 */
	protected ModelElement modelElement;

	/**
	 * The cached value of the '{@link #getNeighbor() <em>Neighbor</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNeighbor()
	 * @generated
	 * @ordered
	 */
	protected EList<RenderedObject> neighbor;

	/**
	 * The cached value of the '{@link #getReferencingNeighbor() <em>Referencing Neighbor</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencingNeighbor()
	 * @generated
	 * @ordered
	 */
	protected EList<RenderedObject> referencingNeighbor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RenderedObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InformationvisualizationPackage.Literals.RENDERED_OBJECT;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERED_OBJECT__FORMULA, oldFormula, newFormula);
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
				msgs = ((InternalEObject)formula).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationvisualizationPackage.RENDERED_OBJECT__FORMULA, null, msgs);
			if (newFormula != null)
				msgs = ((InternalEObject)newFormula).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationvisualizationPackage.RENDERED_OBJECT__FORMULA, null, msgs);
			msgs = basicSetFormula(newFormula, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERED_OBJECT__FORMULA, newFormula, newFormula));
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
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERED_OBJECT__ACTION, oldAction, action));
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
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERED_OBJECT__FILE_NAME, oldFileName, fileName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERED_OBJECT__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERED_OBJECT__URL, oldUrl, url));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RenderedObject> getComposite() {
		if (composite == null) {
			composite = new EObjectWithInverseResolvingEList.ManyInverse<RenderedObject>(RenderedObject.class, this, InformationvisualizationPackage.RENDERED_OBJECT__COMPOSITE, InformationvisualizationPackage.RENDERED_OBJECT__COMPONENT);
		}
		return composite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RenderedObject> getComponent() {
		if (component == null) {
			component = new EObjectWithInverseResolvingEList.ManyInverse<RenderedObject>(RenderedObject.class, this, InformationvisualizationPackage.RENDERED_OBJECT__COMPONENT, InformationvisualizationPackage.RENDERED_OBJECT__COMPOSITE);
		}
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rendering getDefaultRendering() {
		if (defaultRendering != null && defaultRendering.eIsProxy()) {
			InternalEObject oldDefaultRendering = (InternalEObject)defaultRendering;
			defaultRendering = (Rendering)eResolveProxy(oldDefaultRendering);
			if (defaultRendering != oldDefaultRendering) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InformationvisualizationPackage.RENDERED_OBJECT__DEFAULT_RENDERING, oldDefaultRendering, defaultRendering));
			}
		}
		return defaultRendering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rendering basicGetDefaultRendering() {
		return defaultRendering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultRendering(Rendering newDefaultRendering, NotificationChain msgs) {
		Rendering oldDefaultRendering = defaultRendering;
		defaultRendering = newDefaultRendering;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERED_OBJECT__DEFAULT_RENDERING, oldDefaultRendering, newDefaultRendering);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultRendering(Rendering newDefaultRendering) {
		if (newDefaultRendering != defaultRendering) {
			NotificationChain msgs = null;
			if (defaultRendering != null)
				msgs = ((InternalEObject)defaultRendering).eInverseRemove(this, InformationvisualizationPackage.RENDERING__DEFAULTED_RENDERED_OBJECT, Rendering.class, msgs);
			if (newDefaultRendering != null)
				msgs = ((InternalEObject)newDefaultRendering).eInverseAdd(this, InformationvisualizationPackage.RENDERING__DEFAULTED_RENDERED_OBJECT, Rendering.class, msgs);
			msgs = basicSetDefaultRendering(newDefaultRendering, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERED_OBJECT__DEFAULT_RENDERING, newDefaultRendering, newDefaultRendering));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelElement getModelElement() {
		if (modelElement != null && modelElement.eIsProxy()) {
			InternalEObject oldModelElement = (InternalEObject)modelElement;
			modelElement = (ModelElement)eResolveProxy(oldModelElement);
			if (modelElement != oldModelElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InformationvisualizationPackage.RENDERED_OBJECT__MODEL_ELEMENT, oldModelElement, modelElement));
			}
		}
		return modelElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelElement basicGetModelElement() {
		return modelElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModelElement(ModelElement newModelElement, NotificationChain msgs) {
		ModelElement oldModelElement = modelElement;
		modelElement = newModelElement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERED_OBJECT__MODEL_ELEMENT, oldModelElement, newModelElement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelElement(ModelElement newModelElement) {
		if (newModelElement != modelElement) {
			NotificationChain msgs = null;
			if (modelElement != null)
				msgs = ((InternalEObject)modelElement).eInverseRemove(this, CorePackage.MODEL_ELEMENT__RENDERED_OBJECT, ModelElement.class, msgs);
			if (newModelElement != null)
				msgs = ((InternalEObject)newModelElement).eInverseAdd(this, CorePackage.MODEL_ELEMENT__RENDERED_OBJECT, ModelElement.class, msgs);
			msgs = basicSetModelElement(newModelElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationvisualizationPackage.RENDERED_OBJECT__MODEL_ELEMENT, newModelElement, newModelElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RenderedObject> getNeighbor() {
		if (neighbor == null) {
			neighbor = new EObjectWithInverseResolvingEList.ManyInverse<RenderedObject>(RenderedObject.class, this, InformationvisualizationPackage.RENDERED_OBJECT__NEIGHBOR, InformationvisualizationPackage.RENDERED_OBJECT__REFERENCING_NEIGHBOR);
		}
		return neighbor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RenderedObject> getReferencingNeighbor() {
		if (referencingNeighbor == null) {
			referencingNeighbor = new EObjectWithInverseResolvingEList.ManyInverse<RenderedObject>(RenderedObject.class, this, InformationvisualizationPackage.RENDERED_OBJECT__REFERENCING_NEIGHBOR, InformationvisualizationPackage.RENDERED_OBJECT__NEIGHBOR);
		}
		return referencingNeighbor;
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
			case InformationvisualizationPackage.RENDERED_OBJECT__COMPOSITE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getComposite()).basicAdd(otherEnd, msgs);
			case InformationvisualizationPackage.RENDERED_OBJECT__COMPONENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getComponent()).basicAdd(otherEnd, msgs);
			case InformationvisualizationPackage.RENDERED_OBJECT__DEFAULT_RENDERING:
				if (defaultRendering != null)
					msgs = ((InternalEObject)defaultRendering).eInverseRemove(this, InformationvisualizationPackage.RENDERING__DEFAULTED_RENDERED_OBJECT, Rendering.class, msgs);
				return basicSetDefaultRendering((Rendering)otherEnd, msgs);
			case InformationvisualizationPackage.RENDERED_OBJECT__MODEL_ELEMENT:
				if (modelElement != null)
					msgs = ((InternalEObject)modelElement).eInverseRemove(this, CorePackage.MODEL_ELEMENT__RENDERED_OBJECT, ModelElement.class, msgs);
				return basicSetModelElement((ModelElement)otherEnd, msgs);
			case InformationvisualizationPackage.RENDERED_OBJECT__NEIGHBOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getNeighbor()).basicAdd(otherEnd, msgs);
			case InformationvisualizationPackage.RENDERED_OBJECT__REFERENCING_NEIGHBOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferencingNeighbor()).basicAdd(otherEnd, msgs);
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
			case InformationvisualizationPackage.RENDERED_OBJECT__FORMULA:
				return basicSetFormula(null, msgs);
			case InformationvisualizationPackage.RENDERED_OBJECT__COMPOSITE:
				return ((InternalEList<?>)getComposite()).basicRemove(otherEnd, msgs);
			case InformationvisualizationPackage.RENDERED_OBJECT__COMPONENT:
				return ((InternalEList<?>)getComponent()).basicRemove(otherEnd, msgs);
			case InformationvisualizationPackage.RENDERED_OBJECT__DEFAULT_RENDERING:
				return basicSetDefaultRendering(null, msgs);
			case InformationvisualizationPackage.RENDERED_OBJECT__MODEL_ELEMENT:
				return basicSetModelElement(null, msgs);
			case InformationvisualizationPackage.RENDERED_OBJECT__NEIGHBOR:
				return ((InternalEList<?>)getNeighbor()).basicRemove(otherEnd, msgs);
			case InformationvisualizationPackage.RENDERED_OBJECT__REFERENCING_NEIGHBOR:
				return ((InternalEList<?>)getReferencingNeighbor()).basicRemove(otherEnd, msgs);
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
			case InformationvisualizationPackage.RENDERED_OBJECT__FORMULA:
				return getFormula();
			case InformationvisualizationPackage.RENDERED_OBJECT__ACTION:
				return getAction();
			case InformationvisualizationPackage.RENDERED_OBJECT__FILE_NAME:
				return getFileName();
			case InformationvisualizationPackage.RENDERED_OBJECT__TYPE:
				return getType();
			case InformationvisualizationPackage.RENDERED_OBJECT__URL:
				return getUrl();
			case InformationvisualizationPackage.RENDERED_OBJECT__COMPOSITE:
				return getComposite();
			case InformationvisualizationPackage.RENDERED_OBJECT__COMPONENT:
				return getComponent();
			case InformationvisualizationPackage.RENDERED_OBJECT__DEFAULT_RENDERING:
				if (resolve) return getDefaultRendering();
				return basicGetDefaultRendering();
			case InformationvisualizationPackage.RENDERED_OBJECT__MODEL_ELEMENT:
				if (resolve) return getModelElement();
				return basicGetModelElement();
			case InformationvisualizationPackage.RENDERED_OBJECT__NEIGHBOR:
				return getNeighbor();
			case InformationvisualizationPackage.RENDERED_OBJECT__REFERENCING_NEIGHBOR:
				return getReferencingNeighbor();
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
			case InformationvisualizationPackage.RENDERED_OBJECT__FORMULA:
				setFormula((ExpressionNode)newValue);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__ACTION:
				setAction((String)newValue);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__FILE_NAME:
				setFileName((String)newValue);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__TYPE:
				setType((String)newValue);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__URL:
				setUrl((String)newValue);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__COMPOSITE:
				getComposite().clear();
				getComposite().addAll((Collection<? extends RenderedObject>)newValue);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__COMPONENT:
				getComponent().clear();
				getComponent().addAll((Collection<? extends RenderedObject>)newValue);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__DEFAULT_RENDERING:
				setDefaultRendering((Rendering)newValue);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__MODEL_ELEMENT:
				setModelElement((ModelElement)newValue);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__NEIGHBOR:
				getNeighbor().clear();
				getNeighbor().addAll((Collection<? extends RenderedObject>)newValue);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__REFERENCING_NEIGHBOR:
				getReferencingNeighbor().clear();
				getReferencingNeighbor().addAll((Collection<? extends RenderedObject>)newValue);
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
			case InformationvisualizationPackage.RENDERED_OBJECT__FORMULA:
				setFormula((ExpressionNode)null);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__ACTION:
				setAction(ACTION_EDEFAULT);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__FILE_NAME:
				setFileName(FILE_NAME_EDEFAULT);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__URL:
				setUrl(URL_EDEFAULT);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__COMPOSITE:
				getComposite().clear();
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__COMPONENT:
				getComponent().clear();
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__DEFAULT_RENDERING:
				setDefaultRendering((Rendering)null);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__MODEL_ELEMENT:
				setModelElement((ModelElement)null);
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__NEIGHBOR:
				getNeighbor().clear();
				return;
			case InformationvisualizationPackage.RENDERED_OBJECT__REFERENCING_NEIGHBOR:
				getReferencingNeighbor().clear();
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
			case InformationvisualizationPackage.RENDERED_OBJECT__FORMULA:
				return formula != null;
			case InformationvisualizationPackage.RENDERED_OBJECT__ACTION:
				return ACTION_EDEFAULT == null ? action != null : !ACTION_EDEFAULT.equals(action);
			case InformationvisualizationPackage.RENDERED_OBJECT__FILE_NAME:
				return FILE_NAME_EDEFAULT == null ? fileName != null : !FILE_NAME_EDEFAULT.equals(fileName);
			case InformationvisualizationPackage.RENDERED_OBJECT__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case InformationvisualizationPackage.RENDERED_OBJECT__URL:
				return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
			case InformationvisualizationPackage.RENDERED_OBJECT__COMPOSITE:
				return composite != null && !composite.isEmpty();
			case InformationvisualizationPackage.RENDERED_OBJECT__COMPONENT:
				return component != null && !component.isEmpty();
			case InformationvisualizationPackage.RENDERED_OBJECT__DEFAULT_RENDERING:
				return defaultRendering != null;
			case InformationvisualizationPackage.RENDERED_OBJECT__MODEL_ELEMENT:
				return modelElement != null;
			case InformationvisualizationPackage.RENDERED_OBJECT__NEIGHBOR:
				return neighbor != null && !neighbor.isEmpty();
			case InformationvisualizationPackage.RENDERED_OBJECT__REFERENCING_NEIGHBOR:
				return referencingNeighbor != null && !referencingNeighbor.isEmpty();
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

} //RenderedObjectImpl
