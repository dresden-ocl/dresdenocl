/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.tracer.tracermodel.util;

import java.util.Map;
import java.util.UUID;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.tracer.tracermodel.*;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage
 * @generated
 */
public class TracermodelAdapterFactory extends AdapterFactoryImpl {

	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static TracermodelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public TracermodelAdapterFactory() {

		if (modelPackage == null) {
			modelPackage = TracermodelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object. <!--
	 * begin-user-doc --> This implementation returns <code>true</code> if the
	 * object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {

		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TracermodelSwitch<Adapter> modelSwitch =
			new TracermodelSwitch<Adapter>() {

				@Override
				public Adapter caseTracerItem(TracerItem object) {

					return createTracerItemAdapter();
				}

				@Override
				public Adapter caseTracerRoot(TracerRoot object) {

					return createTracerRootAdapter();
				}

				@Override
				public Adapter defaultCase(EObject object) {

					return createEObjectAdapter();
				}
			};

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param target
	 *          the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {

		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem
	 * <em>Tracer Item</em>}'. <!-- begin-user-doc --> This default implementation
	 * returns null so that we can easily ignore cases; it's useful to ignore a
	 * case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracerItem
	 * @generated
	 */
	public Adapter createTracerItemAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot
	 * <em>Tracer Root</em>}'. <!-- begin-user-doc --> This default implementation
	 * returns null so that we can easily ignore cases; it's useful to ignore a
	 * case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot
	 * @generated
	 */
	public Adapter createTracerRootAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {

		return null;
	}

} // TracermodelAdapterFactory
