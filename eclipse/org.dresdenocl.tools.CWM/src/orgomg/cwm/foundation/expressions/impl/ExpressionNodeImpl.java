/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.expressions.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import orgomg.cwm.foundation.expressions.ExpressionNode;
import orgomg.cwm.foundation.expressions.ExpressionsPackage;
import orgomg.cwm.foundation.expressions.FeatureNode;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.Expression;

import orgomg.cwm.objectmodel.core.impl.ElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.expressions.impl.ExpressionNodeImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.expressions.impl.ExpressionNodeImpl#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.expressions.impl.ExpressionNodeImpl#getFeatureNode <em>Feature Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExpressionNodeImpl extends ElementImpl implements ExpressionNode {
	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Classifier type;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpressionNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackage.Literals.EXPRESSION_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
		Expression oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.EXPRESSION_NODE__EXPRESSION, oldExpression, newExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		if (newExpression != expression) {
			NotificationChain msgs = null;
			if (expression != null)
				msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.EXPRESSION_NODE__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.EXPRESSION_NODE__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.EXPRESSION_NODE__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (Classifier)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExpressionsPackage.EXPRESSION_NODE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(Classifier newType, NotificationChain msgs) {
		Classifier oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.EXPRESSION_NODE__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Classifier newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, CorePackage.CLASSIFIER__EXPRESSION_NODE, Classifier.class, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, CorePackage.CLASSIFIER__EXPRESSION_NODE, Classifier.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.EXPRESSION_NODE__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureNode getFeatureNode() {
		if (eContainerFeatureID() != ExpressionsPackage.EXPRESSION_NODE__FEATURE_NODE) return null;
		return (FeatureNode)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFeatureNode(FeatureNode newFeatureNode, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFeatureNode, ExpressionsPackage.EXPRESSION_NODE__FEATURE_NODE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFeatureNode(FeatureNode newFeatureNode) {
		if (newFeatureNode != eInternalContainer() || (eContainerFeatureID() != ExpressionsPackage.EXPRESSION_NODE__FEATURE_NODE && newFeatureNode != null)) {
			if (EcoreUtil.isAncestor(this, newFeatureNode))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFeatureNode != null)
				msgs = ((InternalEObject)newFeatureNode).eInverseAdd(this, ExpressionsPackage.FEATURE_NODE__ARGUMENT, FeatureNode.class, msgs);
			msgs = basicSetFeatureNode(newFeatureNode, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.EXPRESSION_NODE__FEATURE_NODE, newFeatureNode, newFeatureNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExpressionsPackage.EXPRESSION_NODE__TYPE:
				if (type != null)
					msgs = ((InternalEObject)type).eInverseRemove(this, CorePackage.CLASSIFIER__EXPRESSION_NODE, Classifier.class, msgs);
				return basicSetType((Classifier)otherEnd, msgs);
			case ExpressionsPackage.EXPRESSION_NODE__FEATURE_NODE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFeatureNode((FeatureNode)otherEnd, msgs);
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
			case ExpressionsPackage.EXPRESSION_NODE__EXPRESSION:
				return basicSetExpression(null, msgs);
			case ExpressionsPackage.EXPRESSION_NODE__TYPE:
				return basicSetType(null, msgs);
			case ExpressionsPackage.EXPRESSION_NODE__FEATURE_NODE:
				return basicSetFeatureNode(null, msgs);
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
			case ExpressionsPackage.EXPRESSION_NODE__FEATURE_NODE:
				return eInternalContainer().eInverseRemove(this, ExpressionsPackage.FEATURE_NODE__ARGUMENT, FeatureNode.class, msgs);
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
			case ExpressionsPackage.EXPRESSION_NODE__EXPRESSION:
				return getExpression();
			case ExpressionsPackage.EXPRESSION_NODE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case ExpressionsPackage.EXPRESSION_NODE__FEATURE_NODE:
				return getFeatureNode();
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
			case ExpressionsPackage.EXPRESSION_NODE__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case ExpressionsPackage.EXPRESSION_NODE__TYPE:
				setType((Classifier)newValue);
				return;
			case ExpressionsPackage.EXPRESSION_NODE__FEATURE_NODE:
				setFeatureNode((FeatureNode)newValue);
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
			case ExpressionsPackage.EXPRESSION_NODE__EXPRESSION:
				setExpression((Expression)null);
				return;
			case ExpressionsPackage.EXPRESSION_NODE__TYPE:
				setType((Classifier)null);
				return;
			case ExpressionsPackage.EXPRESSION_NODE__FEATURE_NODE:
				setFeatureNode((FeatureNode)null);
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
			case ExpressionsPackage.EXPRESSION_NODE__EXPRESSION:
				return expression != null;
			case ExpressionsPackage.EXPRESSION_NODE__TYPE:
				return type != null;
			case ExpressionsPackage.EXPRESSION_NODE__FEATURE_NODE:
				return getFeatureNode() != null;
		}
		return super.eIsSet(featureID);
	}

} //ExpressionNodeImpl
