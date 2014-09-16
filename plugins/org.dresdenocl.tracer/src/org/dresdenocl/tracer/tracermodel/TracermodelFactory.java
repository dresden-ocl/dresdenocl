/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.tracer.tracermodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * @see org.dresdenocl.tracer.tracermodel.TracermodelPackage
 * @generated
 */
public interface TracermodelFactory extends EFactory {

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	TracermodelFactory eINSTANCE =
			org.dresdenocl.tracer.tracermodel.impl.TracermodelFactoryImpl
					.init();

	/**
	 * Returns a new object of class '<em>Tracer Item</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Tracer Item</em>'.
	 * @generated
	 */
	TracerItem createTracerItem();

	/**
	 * Returns a new object of class '<em>Tracer Root</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Tracer Root</em>'.
	 * @generated
	 */
	TracerRoot createTracerRoot();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TracermodelPackage getTracermodelPackage();

} // TracermodelFactory
