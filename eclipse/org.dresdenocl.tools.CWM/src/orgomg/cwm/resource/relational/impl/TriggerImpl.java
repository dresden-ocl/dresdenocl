/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.objectmodel.core.BooleanExpression;
import orgomg.cwm.objectmodel.core.ProcedureExpression;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

import orgomg.cwm.resource.relational.NamedColumnSet;
import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.relational.Table;
import orgomg.cwm.resource.relational.Trigger;

import orgomg.cwm.resource.relational.enumerations.ActionOrientationType;
import orgomg.cwm.resource.relational.enumerations.ConditionTimingType;
import orgomg.cwm.resource.relational.enumerations.EventManipulationType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TriggerImpl#getEventManipulation <em>Event Manipulation</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TriggerImpl#getActionCondition <em>Action Condition</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TriggerImpl#getActionStatement <em>Action Statement</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TriggerImpl#getActionOrientation <em>Action Orientation</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TriggerImpl#getConditionTiming <em>Condition Timing</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TriggerImpl#getConditionReferenceNewTable <em>Condition Reference New Table</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TriggerImpl#getConditionReferenceOldTable <em>Condition Reference Old Table</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TriggerImpl#getUsedColumnSet <em>Used Column Set</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TriggerImpl#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TriggerImpl extends ModelElementImpl implements Trigger {
	/**
	 * The default value of the '{@link #getEventManipulation() <em>Event Manipulation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventManipulation()
	 * @generated
	 * @ordered
	 */
	protected static final EventManipulationType EVENT_MANIPULATION_EDEFAULT = EventManipulationType.INSERT;

	/**
	 * The cached value of the '{@link #getEventManipulation() <em>Event Manipulation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventManipulation()
	 * @generated
	 * @ordered
	 */
	protected EventManipulationType eventManipulation = EVENT_MANIPULATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getActionCondition() <em>Action Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionCondition()
	 * @generated
	 * @ordered
	 */
	protected BooleanExpression actionCondition;

	/**
	 * The cached value of the '{@link #getActionStatement() <em>Action Statement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionStatement()
	 * @generated
	 * @ordered
	 */
	protected ProcedureExpression actionStatement;

	/**
	 * The default value of the '{@link #getActionOrientation() <em>Action Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionOrientation()
	 * @generated
	 * @ordered
	 */
	protected static final ActionOrientationType ACTION_ORIENTATION_EDEFAULT = ActionOrientationType.ROW;

	/**
	 * The cached value of the '{@link #getActionOrientation() <em>Action Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionOrientation()
	 * @generated
	 * @ordered
	 */
	protected ActionOrientationType actionOrientation = ACTION_ORIENTATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getConditionTiming() <em>Condition Timing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionTiming()
	 * @generated
	 * @ordered
	 */
	protected static final ConditionTimingType CONDITION_TIMING_EDEFAULT = ConditionTimingType.BEFORE;

	/**
	 * The cached value of the '{@link #getConditionTiming() <em>Condition Timing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionTiming()
	 * @generated
	 * @ordered
	 */
	protected ConditionTimingType conditionTiming = CONDITION_TIMING_EDEFAULT;

	/**
	 * The default value of the '{@link #getConditionReferenceNewTable() <em>Condition Reference New Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionReferenceNewTable()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_REFERENCE_NEW_TABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConditionReferenceNewTable() <em>Condition Reference New Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionReferenceNewTable()
	 * @generated
	 * @ordered
	 */
	protected String conditionReferenceNewTable = CONDITION_REFERENCE_NEW_TABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getConditionReferenceOldTable() <em>Condition Reference Old Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionReferenceOldTable()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_REFERENCE_OLD_TABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConditionReferenceOldTable() <em>Condition Reference Old Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionReferenceOldTable()
	 * @generated
	 * @ordered
	 */
	protected String conditionReferenceOldTable = CONDITION_REFERENCE_OLD_TABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getUsedColumnSet() <em>Used Column Set</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedColumnSet()
	 * @generated
	 * @ordered
	 */
	protected EList<NamedColumnSet> usedColumnSet;

	/**
	 * The cached value of the '{@link #getTable() <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTable()
	 * @generated
	 * @ordered
	 */
	protected Table table;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelationalPackage.Literals.TRIGGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventManipulationType getEventManipulation() {
		return eventManipulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventManipulation(EventManipulationType newEventManipulation) {
		EventManipulationType oldEventManipulation = eventManipulation;
		eventManipulation = newEventManipulation == null ? EVENT_MANIPULATION_EDEFAULT : newEventManipulation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.TRIGGER__EVENT_MANIPULATION, oldEventManipulation, eventManipulation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanExpression getActionCondition() {
		return actionCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActionCondition(BooleanExpression newActionCondition, NotificationChain msgs) {
		BooleanExpression oldActionCondition = actionCondition;
		actionCondition = newActionCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelationalPackage.TRIGGER__ACTION_CONDITION, oldActionCondition, newActionCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionCondition(BooleanExpression newActionCondition) {
		if (newActionCondition != actionCondition) {
			NotificationChain msgs = null;
			if (actionCondition != null)
				msgs = ((InternalEObject)actionCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelationalPackage.TRIGGER__ACTION_CONDITION, null, msgs);
			if (newActionCondition != null)
				msgs = ((InternalEObject)newActionCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelationalPackage.TRIGGER__ACTION_CONDITION, null, msgs);
			msgs = basicSetActionCondition(newActionCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.TRIGGER__ACTION_CONDITION, newActionCondition, newActionCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcedureExpression getActionStatement() {
		return actionStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActionStatement(ProcedureExpression newActionStatement, NotificationChain msgs) {
		ProcedureExpression oldActionStatement = actionStatement;
		actionStatement = newActionStatement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelationalPackage.TRIGGER__ACTION_STATEMENT, oldActionStatement, newActionStatement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionStatement(ProcedureExpression newActionStatement) {
		if (newActionStatement != actionStatement) {
			NotificationChain msgs = null;
			if (actionStatement != null)
				msgs = ((InternalEObject)actionStatement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelationalPackage.TRIGGER__ACTION_STATEMENT, null, msgs);
			if (newActionStatement != null)
				msgs = ((InternalEObject)newActionStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelationalPackage.TRIGGER__ACTION_STATEMENT, null, msgs);
			msgs = basicSetActionStatement(newActionStatement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.TRIGGER__ACTION_STATEMENT, newActionStatement, newActionStatement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionOrientationType getActionOrientation() {
		return actionOrientation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionOrientation(ActionOrientationType newActionOrientation) {
		ActionOrientationType oldActionOrientation = actionOrientation;
		actionOrientation = newActionOrientation == null ? ACTION_ORIENTATION_EDEFAULT : newActionOrientation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.TRIGGER__ACTION_ORIENTATION, oldActionOrientation, actionOrientation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionTimingType getConditionTiming() {
		return conditionTiming;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConditionTiming(ConditionTimingType newConditionTiming) {
		ConditionTimingType oldConditionTiming = conditionTiming;
		conditionTiming = newConditionTiming == null ? CONDITION_TIMING_EDEFAULT : newConditionTiming;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.TRIGGER__CONDITION_TIMING, oldConditionTiming, conditionTiming));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConditionReferenceNewTable() {
		return conditionReferenceNewTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConditionReferenceNewTable(String newConditionReferenceNewTable) {
		String oldConditionReferenceNewTable = conditionReferenceNewTable;
		conditionReferenceNewTable = newConditionReferenceNewTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.TRIGGER__CONDITION_REFERENCE_NEW_TABLE, oldConditionReferenceNewTable, conditionReferenceNewTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConditionReferenceOldTable() {
		return conditionReferenceOldTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConditionReferenceOldTable(String newConditionReferenceOldTable) {
		String oldConditionReferenceOldTable = conditionReferenceOldTable;
		conditionReferenceOldTable = newConditionReferenceOldTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.TRIGGER__CONDITION_REFERENCE_OLD_TABLE, oldConditionReferenceOldTable, conditionReferenceOldTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedColumnSet> getUsedColumnSet() {
		if (usedColumnSet == null) {
			usedColumnSet = new EObjectWithInverseResolvingEList.ManyInverse<NamedColumnSet>(NamedColumnSet.class, this, RelationalPackage.TRIGGER__USED_COLUMN_SET, RelationalPackage.NAMED_COLUMN_SET__USING_TRIGGER);
		}
		return usedColumnSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getTable() {
		if (table != null && table.eIsProxy()) {
			InternalEObject oldTable = (InternalEObject)table;
			table = (Table)eResolveProxy(oldTable);
			if (table != oldTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelationalPackage.TRIGGER__TABLE, oldTable, table));
			}
		}
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table basicGetTable() {
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTable(Table newTable, NotificationChain msgs) {
		Table oldTable = table;
		table = newTable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelationalPackage.TRIGGER__TABLE, oldTable, newTable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(Table newTable) {
		if (newTable != table) {
			NotificationChain msgs = null;
			if (table != null)
				msgs = ((InternalEObject)table).eInverseRemove(this, RelationalPackage.TABLE__TRIGGER, Table.class, msgs);
			if (newTable != null)
				msgs = ((InternalEObject)newTable).eInverseAdd(this, RelationalPackage.TABLE__TRIGGER, Table.class, msgs);
			msgs = basicSetTable(newTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.TRIGGER__TABLE, newTable, newTable));
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
			case RelationalPackage.TRIGGER__USED_COLUMN_SET:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsedColumnSet()).basicAdd(otherEnd, msgs);
			case RelationalPackage.TRIGGER__TABLE:
				if (table != null)
					msgs = ((InternalEObject)table).eInverseRemove(this, RelationalPackage.TABLE__TRIGGER, Table.class, msgs);
				return basicSetTable((Table)otherEnd, msgs);
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
			case RelationalPackage.TRIGGER__ACTION_CONDITION:
				return basicSetActionCondition(null, msgs);
			case RelationalPackage.TRIGGER__ACTION_STATEMENT:
				return basicSetActionStatement(null, msgs);
			case RelationalPackage.TRIGGER__USED_COLUMN_SET:
				return ((InternalEList<?>)getUsedColumnSet()).basicRemove(otherEnd, msgs);
			case RelationalPackage.TRIGGER__TABLE:
				return basicSetTable(null, msgs);
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
			case RelationalPackage.TRIGGER__EVENT_MANIPULATION:
				return getEventManipulation();
			case RelationalPackage.TRIGGER__ACTION_CONDITION:
				return getActionCondition();
			case RelationalPackage.TRIGGER__ACTION_STATEMENT:
				return getActionStatement();
			case RelationalPackage.TRIGGER__ACTION_ORIENTATION:
				return getActionOrientation();
			case RelationalPackage.TRIGGER__CONDITION_TIMING:
				return getConditionTiming();
			case RelationalPackage.TRIGGER__CONDITION_REFERENCE_NEW_TABLE:
				return getConditionReferenceNewTable();
			case RelationalPackage.TRIGGER__CONDITION_REFERENCE_OLD_TABLE:
				return getConditionReferenceOldTable();
			case RelationalPackage.TRIGGER__USED_COLUMN_SET:
				return getUsedColumnSet();
			case RelationalPackage.TRIGGER__TABLE:
				if (resolve) return getTable();
				return basicGetTable();
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
			case RelationalPackage.TRIGGER__EVENT_MANIPULATION:
				setEventManipulation((EventManipulationType)newValue);
				return;
			case RelationalPackage.TRIGGER__ACTION_CONDITION:
				setActionCondition((BooleanExpression)newValue);
				return;
			case RelationalPackage.TRIGGER__ACTION_STATEMENT:
				setActionStatement((ProcedureExpression)newValue);
				return;
			case RelationalPackage.TRIGGER__ACTION_ORIENTATION:
				setActionOrientation((ActionOrientationType)newValue);
				return;
			case RelationalPackage.TRIGGER__CONDITION_TIMING:
				setConditionTiming((ConditionTimingType)newValue);
				return;
			case RelationalPackage.TRIGGER__CONDITION_REFERENCE_NEW_TABLE:
				setConditionReferenceNewTable((String)newValue);
				return;
			case RelationalPackage.TRIGGER__CONDITION_REFERENCE_OLD_TABLE:
				setConditionReferenceOldTable((String)newValue);
				return;
			case RelationalPackage.TRIGGER__USED_COLUMN_SET:
				getUsedColumnSet().clear();
				getUsedColumnSet().addAll((Collection<? extends NamedColumnSet>)newValue);
				return;
			case RelationalPackage.TRIGGER__TABLE:
				setTable((Table)newValue);
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
			case RelationalPackage.TRIGGER__EVENT_MANIPULATION:
				setEventManipulation(EVENT_MANIPULATION_EDEFAULT);
				return;
			case RelationalPackage.TRIGGER__ACTION_CONDITION:
				setActionCondition((BooleanExpression)null);
				return;
			case RelationalPackage.TRIGGER__ACTION_STATEMENT:
				setActionStatement((ProcedureExpression)null);
				return;
			case RelationalPackage.TRIGGER__ACTION_ORIENTATION:
				setActionOrientation(ACTION_ORIENTATION_EDEFAULT);
				return;
			case RelationalPackage.TRIGGER__CONDITION_TIMING:
				setConditionTiming(CONDITION_TIMING_EDEFAULT);
				return;
			case RelationalPackage.TRIGGER__CONDITION_REFERENCE_NEW_TABLE:
				setConditionReferenceNewTable(CONDITION_REFERENCE_NEW_TABLE_EDEFAULT);
				return;
			case RelationalPackage.TRIGGER__CONDITION_REFERENCE_OLD_TABLE:
				setConditionReferenceOldTable(CONDITION_REFERENCE_OLD_TABLE_EDEFAULT);
				return;
			case RelationalPackage.TRIGGER__USED_COLUMN_SET:
				getUsedColumnSet().clear();
				return;
			case RelationalPackage.TRIGGER__TABLE:
				setTable((Table)null);
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
			case RelationalPackage.TRIGGER__EVENT_MANIPULATION:
				return eventManipulation != EVENT_MANIPULATION_EDEFAULT;
			case RelationalPackage.TRIGGER__ACTION_CONDITION:
				return actionCondition != null;
			case RelationalPackage.TRIGGER__ACTION_STATEMENT:
				return actionStatement != null;
			case RelationalPackage.TRIGGER__ACTION_ORIENTATION:
				return actionOrientation != ACTION_ORIENTATION_EDEFAULT;
			case RelationalPackage.TRIGGER__CONDITION_TIMING:
				return conditionTiming != CONDITION_TIMING_EDEFAULT;
			case RelationalPackage.TRIGGER__CONDITION_REFERENCE_NEW_TABLE:
				return CONDITION_REFERENCE_NEW_TABLE_EDEFAULT == null ? conditionReferenceNewTable != null : !CONDITION_REFERENCE_NEW_TABLE_EDEFAULT.equals(conditionReferenceNewTable);
			case RelationalPackage.TRIGGER__CONDITION_REFERENCE_OLD_TABLE:
				return CONDITION_REFERENCE_OLD_TABLE_EDEFAULT == null ? conditionReferenceOldTable != null : !CONDITION_REFERENCE_OLD_TABLE_EDEFAULT.equals(conditionReferenceOldTable);
			case RelationalPackage.TRIGGER__USED_COLUMN_SET:
				return usedColumnSet != null && !usedColumnSet.isEmpty();
			case RelationalPackage.TRIGGER__TABLE:
				return table != null;
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
		result.append(" (eventManipulation: ");
		result.append(eventManipulation);
		result.append(", actionOrientation: ");
		result.append(actionOrientation);
		result.append(", conditionTiming: ");
		result.append(conditionTiming);
		result.append(", conditionReferenceNewTable: ");
		result.append(conditionReferenceNewTable);
		result.append(", conditionReferenceOldTable: ");
		result.append(conditionReferenceOldTable);
		result.append(')');
		return result.toString();
	}

} //TriggerImpl
