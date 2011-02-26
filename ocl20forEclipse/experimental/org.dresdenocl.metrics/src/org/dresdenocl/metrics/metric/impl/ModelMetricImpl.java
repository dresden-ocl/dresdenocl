/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.metrics.metric.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dresdenocl.metrics.metric.ConstraintCount;
import org.dresdenocl.metrics.metric.ConstraintMetric;
import org.dresdenocl.metrics.metric.MetricPackage;
import org.dresdenocl.metrics.metric.ModelMetric;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Model Metric</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.dresdenocl.metrics.metric.impl.ModelMetricImpl#getReferredModelId
 * <em>Referred Model Id</em>}</li>
 * <li>
 * {@link org.dresdenocl.metrics.metric.impl.ModelMetricImpl#getConstraintCountPerKind
 * <em>Constraint Count Per Kind</em>}</li>
 * <li>
 * {@link org.dresdenocl.metrics.metric.impl.ModelMetricImpl#getConstraintMetrics
 * <em>Constraint Metrics</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ModelMetricImpl extends ConstraintMetricImpl implements
		ModelMetric {
	/**
	 * The default value of the '{@link #getReferredModelId()
	 * <em>Referred Model Id</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getReferredModelId()
	 * @generated
	 * @ordered
	 */
	protected static final String REFERRED_MODEL_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReferredModelId()
	 * <em>Referred Model Id</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getReferredModelId()
	 * @generated
	 * @ordered
	 */
	protected String referredModelId = REFERRED_MODEL_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConstraintCountPerKind()
	 * <em>Constraint Count Per Kind</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getConstraintCountPerKind()
	 * @generated
	 * @ordered
	 */
	protected ConstraintCount constraintCountPerKind;

	/**
	 * The cached value of the '{@link #getConstraintMetrics()
	 * <em>Constraint Metrics</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getConstraintMetrics()
	 * @generated
	 * @ordered
	 */
	protected EList<ConstraintMetric> constraintMetrics;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ModelMetricImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetricPackage.Literals.MODEL_METRIC;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getReferredModelId() {
		return referredModelId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setReferredModelId(String newReferredModelId) {
		String oldReferredModelId = referredModelId;
		referredModelId = newReferredModelId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					MetricPackage.MODEL_METRIC__REFERRED_MODEL_ID,
					oldReferredModelId, referredModelId));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ConstraintCount getConstraintCountPerKind() {
		return constraintCountPerKind;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetConstraintCountPerKind(
			ConstraintCount newConstraintCountPerKind, NotificationChain msgs) {
		ConstraintCount oldConstraintCountPerKind = constraintCountPerKind;
		constraintCountPerKind = newConstraintCountPerKind;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					MetricPackage.MODEL_METRIC__CONSTRAINT_COUNT_PER_KIND,
					oldConstraintCountPerKind, newConstraintCountPerKind);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setConstraintCountPerKind(
			ConstraintCount newConstraintCountPerKind) {
		if (newConstraintCountPerKind != constraintCountPerKind) {
			NotificationChain msgs = null;
			if (constraintCountPerKind != null)
				msgs = ((InternalEObject) constraintCountPerKind)
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- MetricPackage.MODEL_METRIC__CONSTRAINT_COUNT_PER_KIND,
								null, msgs);
			if (newConstraintCountPerKind != null)
				msgs = ((InternalEObject) newConstraintCountPerKind)
						.eInverseAdd(
								this,
								EOPPOSITE_FEATURE_BASE
										- MetricPackage.MODEL_METRIC__CONSTRAINT_COUNT_PER_KIND,
								null, msgs);
			msgs = basicSetConstraintCountPerKind(newConstraintCountPerKind,
					msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					MetricPackage.MODEL_METRIC__CONSTRAINT_COUNT_PER_KIND,
					newConstraintCountPerKind, newConstraintCountPerKind));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<ConstraintMetric> getConstraintMetrics() {
		if (constraintMetrics == null) {
			constraintMetrics = new EObjectContainmentEList<ConstraintMetric>(
					ConstraintMetric.class, this,
					MetricPackage.MODEL_METRIC__CONSTRAINT_METRICS);
		}
		return constraintMetrics;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getConstraintCount() {

		return this.getConstraintMetrics().size();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getExpressionCount() {

		int result = 0;

		for (org.dresdenocl.metrics.metric.ConstraintMetric metric : this
				.getConstraintMetrics())
			result += metric.getExpressionCount();

		// end for.

		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public float getAvgExpressionCount() {

		return (float) this.getExpressionCount()
				/ (float) this.getConstraintCount();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public float getAvgExpressionDepth() {

		int totalDepth = 0;

		for (org.dresdenocl.metrics.metric.ConstraintMetric metric : this
				.getConstraintMetrics())
			totalDepth += metric.getExpressionDepth();

		// end for.

		return (float) totalDepth / (float) this.getConstraintCount();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getMinExpressionCount() {
		Integer result = null;

		for (org.dresdenocl.metrics.metric.ConstraintMetric metric : this
				.getConstraintMetrics())

			if (result == null)
				result = metric.getExpressionCount();
			else
				result = Math.min(result, metric.getExpressionCount());

		// end for.

		if (result == null)
			result = 0;
		// no else.

		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getMinExpressionDepth() {
		Integer result = null;

		for (org.dresdenocl.metrics.metric.ConstraintMetric metric : this
				.getConstraintMetrics())
			if (result == null)
				result = metric.getExpressionDepth();
			else
				result = Math.min(result, metric.getExpressionDepth());
		// end for.

		if (result == null)
			result = 0;
		// no else.

		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getMaxExpressionCount() {
		Integer result = 0;

		for (org.dresdenocl.metrics.metric.ConstraintMetric metric : this
				.getConstraintMetrics())
			result = Math.max(result, metric.getExpressionCount());
		// end for.

		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getMaxExpressionDepth() {
		Integer result = 0;

		for (org.dresdenocl.metrics.metric.ConstraintMetric metric : this
				.getConstraintMetrics())
			result = Math.max(result, metric.getExpressionDepth());
		// end for.

		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getMeanExpressionCount() {
		List<Integer> counts = new ArrayList<Integer>();

		for (org.dresdenocl.metrics.metric.ConstraintMetric metric : this
				.getConstraintMetrics())
			counts.add(metric.getExpressionCount());
		// end for.

		if (counts.size() > 0) {
			Collections.sort(counts);
			return counts.get(counts.size() / 2);
		} else
			return 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getMeanExpressionDepth() {
		List<Integer> depths = new ArrayList<Integer>();

		for (org.dresdenocl.metrics.metric.ConstraintMetric metric : this
				.getConstraintMetrics())
			depths.add(metric.getExpressionDepth());
		// end for.

		if (depths.size() > 0) {
			Collections.sort(depths);
			return depths.get(depths.size() / 2);
		} else
			return 0;
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
		case MetricPackage.MODEL_METRIC__CONSTRAINT_COUNT_PER_KIND:
			return basicSetConstraintCountPerKind(null, msgs);
		case MetricPackage.MODEL_METRIC__CONSTRAINT_METRICS:
			return ((InternalEList<?>) getConstraintMetrics()).basicRemove(
					otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case MetricPackage.MODEL_METRIC__REFERRED_MODEL_ID:
			return getReferredModelId();
		case MetricPackage.MODEL_METRIC__CONSTRAINT_COUNT_PER_KIND:
			return getConstraintCountPerKind();
		case MetricPackage.MODEL_METRIC__CONSTRAINT_METRICS:
			return getConstraintMetrics();
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
		case MetricPackage.MODEL_METRIC__REFERRED_MODEL_ID:
			setReferredModelId((String) newValue);
			return;
		case MetricPackage.MODEL_METRIC__CONSTRAINT_COUNT_PER_KIND:
			setConstraintCountPerKind((ConstraintCount) newValue);
			return;
		case MetricPackage.MODEL_METRIC__CONSTRAINT_METRICS:
			getConstraintMetrics().clear();
			getConstraintMetrics().addAll(
					(Collection<? extends ConstraintMetric>) newValue);
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
		case MetricPackage.MODEL_METRIC__REFERRED_MODEL_ID:
			setReferredModelId(REFERRED_MODEL_ID_EDEFAULT);
			return;
		case MetricPackage.MODEL_METRIC__CONSTRAINT_COUNT_PER_KIND:
			setConstraintCountPerKind((ConstraintCount) null);
			return;
		case MetricPackage.MODEL_METRIC__CONSTRAINT_METRICS:
			getConstraintMetrics().clear();
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
		case MetricPackage.MODEL_METRIC__REFERRED_MODEL_ID:
			return REFERRED_MODEL_ID_EDEFAULT == null ? referredModelId != null
					: !REFERRED_MODEL_ID_EDEFAULT.equals(referredModelId);
		case MetricPackage.MODEL_METRIC__CONSTRAINT_COUNT_PER_KIND:
			return constraintCountPerKind != null;
		case MetricPackage.MODEL_METRIC__CONSTRAINT_METRICS:
			return constraintMetrics != null && !constraintMetrics.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.metrics.metric.impl.MetricImpl#getReport()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getReport() {

		StringBuffer resultBuffer = new StringBuffer();

		resultBuffer.append("= Model Metric =\n");
		resultBuffer.append("model: " + this.getReferredModelId() + "\n");
		resultBuffer.append("\n");

		resultBuffer.append("== Constraints ==\n");
		resultBuffer.append("# constraints: " + this.getConstraintCount()
				+ "\n");
		resultBuffer.append("# expressions: " + this.getExpressionCount()
				+ "\n");
		resultBuffer.append("# min. expressions / constraint: "
				+ this.getMinExpressionCount() + "\n");
		resultBuffer.append("# max. expressions / constraint: "
				+ this.getMaxExpressionCount() + "\n");
		resultBuffer.append("# avg. expressions / constraint: "
				+ this.getAvgExpressionCount() + "\n");
		resultBuffer.append("# mean expressions / constraint: "
				+ this.getMeanExpressionCount() + "\n");
		resultBuffer.append("# min. expression depth / constraint: "
				+ this.getMinExpressionDepth() + "\n");
		resultBuffer.append("# max. expression depth / constraint: "
				+ this.getMaxExpressionDepth() + "\n");
		resultBuffer.append("# avg. expression depth / constraint: "
				+ this.getAvgExpressionDepth() + "\n");
		resultBuffer.append("# Mean expression depth / constraint: "
				+ this.getMeanExpressionDepth() + "\n");
		resultBuffer.append("\n");

		resultBuffer.append("=== Called Operations ===\n");

		for (String opName : this.getCalledOperations().keySet())
			resultBuffer.append(opName + ", "
					+ this.getCalledOperations().get(opName) + "\n");
		// end for.

		return resultBuffer.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (referredModelId: ");
		result.append(referredModelId);
		result.append(')');
		return result.toString();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getCalledOperations()
	 * 
	 * @generated NOT
	 */
	@Override
	public Map<String, Integer> getCalledOperations() {
		Map<String, Integer> result = new HashMap<String, Integer>();

		for (ConstraintMetric metric : this.getConstraintMetrics()) {

			if (metric.getCalledOperations() != null) {
				for (String opName : metric.getCalledOperations().keySet()) {
					Integer opCount = result.get(opName);

					if (opCount == null)
						opCount = 0;
					// no else.

					result.put(opName, opCount
							+ metric.getCalledOperations().get(opName));
				}
				// end for.
			}
			// no else.
		}
		// end for.

		return result;
	}
} // ModelMetricImpl
