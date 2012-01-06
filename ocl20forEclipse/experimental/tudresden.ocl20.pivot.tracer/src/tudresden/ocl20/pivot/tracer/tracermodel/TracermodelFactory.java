/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.tracer.tracermodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage
 * @generated
 */
public interface TracermodelFactory extends EFactory {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright =
			"Copyright (C) 2011 by Lars Schütze (lschuetze@gmx.net)\n\nThis file is part of the OCL 2 Interpreter of Dresden OCL2 for Eclipse.\n\nDresden OCL2 for Eclipse is free software: you can redistribute it and/or modify \nit under the terms of the GNU Lesser General Public License as published by the \nFree Software Foundation, either version 3 of the License, or (at your option)\nany later version.\n\nDresden OCL2 for Eclipse is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY \nor FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License \nfor more details.\n\nYou should have received a copy of the GNU Lesser General Public License along \nwith Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>."; //$NON-NLS-1$
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	TracermodelFactory eINSTANCE =
			tudresden.ocl20.pivot.tracer.tracermodel.impl.TracermodelFactoryImpl
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
