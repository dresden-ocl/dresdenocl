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

import org.dresdenocl.metrics.metric.ConstraintMetric;
import org.dresdenocl.metrics.metric.ConstraintMetrics;
import org.dresdenocl.metrics.metric.MetricPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.ConstraintKind;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Constraint Metrics</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricsImpl#getConstraintMetrics <em>Constraint Metrics</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricsImpl#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricsImpl#getNumberOfConstraintsByKind <em>Number Of Constraints By Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstraintMetricsImpl extends ConstraintMetricImpl implements
		ConstraintMetrics {
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
	 * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> constraints;

	/**
	 * The cached value of the '{@link #getNumberOfConstraintsByKind() <em>Number Of Constraints By Kind</em>}' attribute.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getNumberOfConstraintsByKind()
	 * @generated
	 * @ordered
	 */
	protected Map<ConstraintKind, Integer> numberOfConstraintsByKind;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstraintMetricsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetricPackage.Literals.CONSTRAINT_METRICS;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConstraintMetric> getConstraintMetrics() {
		if (constraintMetrics == null) {
			constraintMetrics = new EObjectContainmentEList<ConstraintMetric>(ConstraintMetric.class, this, MetricPackage.CONSTRAINT_METRICS__CONSTRAINT_METRICS);
		}
		return constraintMetrics;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Constraint> getConstraints() {
		if (constraints == null) {
			constraints = new EObjectResolvingEList<Constraint>(Constraint.class, this, MetricPackage.CONSTRAINT_METRICS__CONSTRAINTS);
		}
		return constraints;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Map<ConstraintKind, Integer> getNumberOfConstraintsByKind() {
		Map<ConstraintKind, Integer> result = new HashMap<ConstraintKind, Integer>();

		for (ConstraintMetric metric : this.getConstraintMetrics()) {

			Integer consCount = result.get(metric.getReferredConstraint()
					.getKind());

			if (consCount == null)
				consCount = 0;
			// no else.

			consCount++;

			result.put(metric.getReferredConstraint().getKind(), consCount);
		}
		// end for.

		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberOfConstraintsByKind(
			Map<ConstraintKind, Integer> newNumberOfConstraintsByKind) {
		Map<ConstraintKind, Integer> oldNumberOfConstraintsByKind = numberOfConstraintsByKind;
		numberOfConstraintsByKind = newNumberOfConstraintsByKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.CONSTRAINT_METRICS__NUMBER_OF_CONSTRAINTS_BY_KIND, oldNumberOfConstraintsByKind, numberOfConstraintsByKind));
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
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetricPackage.CONSTRAINT_METRICS__CONSTRAINT_METRICS:
				return ((InternalEList<?>)getConstraintMetrics()).basicRemove(otherEnd, msgs);
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
			case MetricPackage.CONSTRAINT_METRICS__CONSTRAINT_METRICS:
				return getConstraintMetrics();
			case MetricPackage.CONSTRAINT_METRICS__CONSTRAINTS:
				return getConstraints();
			case MetricPackage.CONSTRAINT_METRICS__NUMBER_OF_CONSTRAINTS_BY_KIND:
				return getNumberOfConstraintsByKind();
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
			case MetricPackage.CONSTRAINT_METRICS__CONSTRAINT_METRICS:
				getConstraintMetrics().clear();
				getConstraintMetrics().addAll((Collection<? extends ConstraintMetric>)newValue);
				return;
			case MetricPackage.CONSTRAINT_METRICS__CONSTRAINTS:
				getConstraints().clear();
				getConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case MetricPackage.CONSTRAINT_METRICS__NUMBER_OF_CONSTRAINTS_BY_KIND:
				setNumberOfConstraintsByKind((Map<ConstraintKind, Integer>)newValue);
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
			case MetricPackage.CONSTRAINT_METRICS__CONSTRAINT_METRICS:
				getConstraintMetrics().clear();
				return;
			case MetricPackage.CONSTRAINT_METRICS__CONSTRAINTS:
				getConstraints().clear();
				return;
			case MetricPackage.CONSTRAINT_METRICS__NUMBER_OF_CONSTRAINTS_BY_KIND:
				setNumberOfConstraintsByKind((Map<ConstraintKind, Integer>)null);
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
			case MetricPackage.CONSTRAINT_METRICS__CONSTRAINT_METRICS:
				return constraintMetrics != null && !constraintMetrics.isEmpty();
			case MetricPackage.CONSTRAINT_METRICS__CONSTRAINTS:
				return constraints != null && !constraints.isEmpty();
			case MetricPackage.CONSTRAINT_METRICS__NUMBER_OF_CONSTRAINTS_BY_KIND:
				return numberOfConstraintsByKind != null;
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

		resultBuffer.append("= Constraints Metrics =\n");

		resultBuffer.append("== Constraints ==\n");
		resultBuffer.append("# constraints, " + this.getConstraintCount()
				+ "\n");
		resultBuffer.append("# expressions, " + this.getExpressionCount()
				+ "\n");
		resultBuffer.append("# min. expressions / constraint, "
				+ this.getMinExpressionCount() + "\n");
		resultBuffer.append("# max. expressions / constraint, "
				+ this.getMaxExpressionCount() + "\n");
		resultBuffer.append("# avg. expressions / constraint, "
				+ this.getAvgExpressionCount() + "\n");
		resultBuffer.append("# mean expressions / constraint, "
				+ this.getMeanExpressionCount() + "\n");
		resultBuffer.append("# min. expression depth / constraint, "
				+ this.getMinExpressionDepth() + "\n");
		resultBuffer.append("# max. expression depth / constraint, "
				+ this.getMaxExpressionDepth() + "\n");
		resultBuffer.append("# avg. expression depth / constraint, "
				+ this.getAvgExpressionDepth() + "\n");
		resultBuffer.append("# Mean expression depth / constraint, "
				+ this.getMeanExpressionDepth() + "\n");

		resultBuffer.append("# If expressions, "
				+ this.getNumberOfIfExpressions() + "\n");
		resultBuffer.append("# Let expressions, "
				+ this.getNumberOfLetExpressions() + "\n");

		if (this.getUsedLiterals() != null) {
			resultBuffer.append("\n === Used Literals ===\n");

			ArrayList<String> keys = new ArrayList<String>(this
					.getUsedLiterals().keySet());
			Collections.sort(keys);

			for (String litName : keys)
				resultBuffer.append(litName + ", "
						+ this.getUsedLiterals().get(litName) + "\n");
			// end for.
		}

		if (this.getCalledProperties() != null) {
			resultBuffer.append("\n=== Called Properties ===\n");

			ArrayList<String> keys = new ArrayList<String>(this
					.getCalledProperties().keySet());
			Collections.sort(keys);

			for (String propName : keys)
				resultBuffer.append(propName + ", "
						+ this.getCalledProperties().get(propName) + "\n");
			// end for.
		}

		if (this.getCalledOperations() != null) {
			resultBuffer.append("\n=== Called Operations ===\n");

			ArrayList<String> keys = new ArrayList<String>(this
					.getCalledOperations().keySet());
			Collections.sort(keys);

			for (String opName : keys)
				resultBuffer.append(opName + ", "
						+ this.getCalledOperations().get(opName) + "\n");
			// end for.
		}

		if (this.getUsedIterators() != null) {
			resultBuffer.append("\n=== Used Iterators ===\n");

			ArrayList<String> keys = new ArrayList<String>(this
					.getUsedIterators().keySet());
			Collections.sort(keys);

			for (String itName : keys)
				resultBuffer.append(itName + ", "
						+ this.getUsedIterators().get(itName) + "\n");
			// end for.
		}

		return resultBuffer.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (numberOfConstraintsByKind: ");
		result.append(numberOfConstraintsByKind);
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getCalledProperties()
	 * 
	 * @generated NOT
	 */
	@Override
	public Map<String, Integer> getCalledProperties() {
		Map<String, Integer> result = new HashMap<String, Integer>();

		for (ConstraintMetric metric : this.getConstraintMetrics()) {

			if (metric.getCalledProperties() != null) {
				for (String propName : metric.getCalledProperties().keySet()) {
					Integer opCount = result.get(propName);

					if (opCount == null)
						opCount = 0;
					// no else.

					result.put(propName, opCount
							+ metric.getCalledProperties().get(propName));
				}
				// end for.
			}
			// no else.
		}
		// end for.

		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getNumberOfIfExpressions()
	 * 
	 * @generated NOT
	 */
	@Override
	public int getNumberOfIfExpressions() {
		int result = 0;

		for (org.dresdenocl.metrics.metric.ConstraintMetric metric : this
				.getConstraintMetrics())
			result += metric.getNumberOfIfExpressions();

		// end for.

		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getNumberOfLetExpressions()
	 * 
	 * @generated NOT
	 */
	@Override
	public int getNumberOfLetExpressions() {
		int result = 0;

		for (org.dresdenocl.metrics.metric.ConstraintMetric metric : this
				.getConstraintMetrics())
			result += metric.getNumberOfLetExpressions();

		// end for.

		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getUsedIterators()
	 * 
	 * @generated NOT
	 */
	@Override
	public Map<String, Integer> getUsedIterators() {
		Map<String, Integer> result = new HashMap<String, Integer>();

		for (ConstraintMetric metric : this.getConstraintMetrics()) {

			if (metric.getUsedIterators() != null) {
				for (String litName : metric.getUsedIterators().keySet()) {
					Integer opCount = result.get(litName);

					if (opCount == null)
						opCount = 0;
					// no else.

					result.put(litName, opCount
							+ metric.getUsedIterators().get(litName));
				}
				// end for.
			}
			// no else.
		}
		// end for.

		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getUsedLiterals()
	 * 
	 * @generated NOT
	 */
	@Override
	public Map<String, Integer> getUsedLiterals() {
		Map<String, Integer> result = new HashMap<String, Integer>();

		for (ConstraintMetric metric : this.getConstraintMetrics()) {

			if (metric.getUsedLiterals() != null) {
				for (String litName : metric.getUsedLiterals().keySet()) {
					Integer opCount = result.get(litName);

					if (opCount == null)
						opCount = 0;
					// no else.

					result.put(litName,
							opCount + metric.getUsedLiterals().get(litName));
				}
				// end for.
			}
			// no else.
		}
		// end for.

		return result;
	}
} // ConstraintMetricsImpl
