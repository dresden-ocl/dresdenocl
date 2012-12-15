/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events.impl;

import org.eclipse.emf.ecore.EClass;

import orgomg.cwm.management.warehouseprocess.events.EventsPackage;
import orgomg.cwm.management.warehouseprocess.events.ScheduleEvent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Schedule Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class ScheduleEventImpl extends WarehouseEventImpl implements ScheduleEvent {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScheduleEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EventsPackage.Literals.SCHEDULE_EVENT;
	}

} //ScheduleEventImpl
