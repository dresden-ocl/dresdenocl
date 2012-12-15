/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import orgomg.cwm.management.warehouseprocess.events.*;

import orgomg.cwm.objectmodel.behavioral.Event;

import orgomg.cwm.objectmodel.core.Element;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Namespace;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage
 * @generated
 */
public class EventsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EventsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = EventsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventsSwitch<Adapter> modelSwitch =
		new EventsSwitch<Adapter>() {
			@Override
			public Adapter caseWarehouseEvent(WarehouseEvent object) {
				return createWarehouseEventAdapter();
			}
			@Override
			public Adapter caseScheduleEvent(ScheduleEvent object) {
				return createScheduleEventAdapter();
			}
			@Override
			public Adapter casePointInTimeEvent(PointInTimeEvent object) {
				return createPointInTimeEventAdapter();
			}
			@Override
			public Adapter caseCustomCalendarEvent(CustomCalendarEvent object) {
				return createCustomCalendarEventAdapter();
			}
			@Override
			public Adapter caseCustomCalendar(CustomCalendar object) {
				return createCustomCalendarAdapter();
			}
			@Override
			public Adapter caseCalendarDate(CalendarDate object) {
				return createCalendarDateAdapter();
			}
			@Override
			public Adapter caseIntervalEvent(IntervalEvent object) {
				return createIntervalEventAdapter();
			}
			@Override
			public Adapter caseExternalEvent(ExternalEvent object) {
				return createExternalEventAdapter();
			}
			@Override
			public Adapter caseInternalEvent(InternalEvent object) {
				return createInternalEventAdapter();
			}
			@Override
			public Adapter caseCascadeEvent(CascadeEvent object) {
				return createCascadeEventAdapter();
			}
			@Override
			public Adapter caseRetryEvent(RetryEvent object) {
				return createRetryEventAdapter();
			}
			@Override
			public Adapter caseRecurringPointInTimeEvent(RecurringPointInTimeEvent object) {
				return createRecurringPointInTimeEventAdapter();
			}
			@Override
			public Adapter caseElement(Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter caseModelElement(ModelElement object) {
				return createModelElementAdapter();
			}
			@Override
			public Adapter caseEvent(Event object) {
				return createEventAdapter();
			}
			@Override
			public Adapter caseNamespace(Namespace object) {
				return createNamespaceAdapter();
			}
			@Override
			public Adapter casePackage(orgomg.cwm.objectmodel.core.Package object) {
				return createPackageAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.management.warehouseprocess.events.WarehouseEvent <em>Warehouse Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.management.warehouseprocess.events.WarehouseEvent
	 * @generated
	 */
	public Adapter createWarehouseEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.management.warehouseprocess.events.ScheduleEvent <em>Schedule Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.management.warehouseprocess.events.ScheduleEvent
	 * @generated
	 */
	public Adapter createScheduleEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.management.warehouseprocess.events.PointInTimeEvent <em>Point In Time Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.management.warehouseprocess.events.PointInTimeEvent
	 * @generated
	 */
	public Adapter createPointInTimeEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent <em>Custom Calendar Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent
	 * @generated
	 */
	public Adapter createCustomCalendarEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.management.warehouseprocess.events.CustomCalendar <em>Custom Calendar</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.management.warehouseprocess.events.CustomCalendar
	 * @generated
	 */
	public Adapter createCustomCalendarAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.management.warehouseprocess.events.CalendarDate <em>Calendar Date</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.management.warehouseprocess.events.CalendarDate
	 * @generated
	 */
	public Adapter createCalendarDateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.management.warehouseprocess.events.IntervalEvent <em>Interval Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.management.warehouseprocess.events.IntervalEvent
	 * @generated
	 */
	public Adapter createIntervalEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.management.warehouseprocess.events.ExternalEvent <em>External Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.management.warehouseprocess.events.ExternalEvent
	 * @generated
	 */
	public Adapter createExternalEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.management.warehouseprocess.events.InternalEvent <em>Internal Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.management.warehouseprocess.events.InternalEvent
	 * @generated
	 */
	public Adapter createInternalEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.management.warehouseprocess.events.CascadeEvent <em>Cascade Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.management.warehouseprocess.events.CascadeEvent
	 * @generated
	 */
	public Adapter createCascadeEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.management.warehouseprocess.events.RetryEvent <em>Retry Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.management.warehouseprocess.events.RetryEvent
	 * @generated
	 */
	public Adapter createRetryEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent <em>Recurring Point In Time Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent
	 * @generated
	 */
	public Adapter createRecurringPointInTimeEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.objectmodel.core.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.objectmodel.core.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.objectmodel.core.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.objectmodel.core.ModelElement
	 * @generated
	 */
	public Adapter createModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.objectmodel.behavioral.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.objectmodel.behavioral.Event
	 * @generated
	 */
	public Adapter createEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.objectmodel.core.Namespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.objectmodel.core.Namespace
	 * @generated
	 */
	public Adapter createNamespaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link orgomg.cwm.objectmodel.core.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see orgomg.cwm.objectmodel.core.Package
	 * @generated
	 */
	public Adapter createPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //EventsAdapterFactory
