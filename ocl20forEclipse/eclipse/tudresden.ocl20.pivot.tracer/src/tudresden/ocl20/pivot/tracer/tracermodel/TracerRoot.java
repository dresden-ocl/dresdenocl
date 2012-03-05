/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.tracer.tracermodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Tracer Root</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot#getRootItems <em>Root Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage#getTracerRoot()
 * @model
 * @generated
 */
public interface TracerRoot extends EObject {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (C) 2011 by Lars Schütze (lschuetze@gmx.net)\n\nThis file is part of the OCL 2 Interpreter of Dresden OCL2 for Eclipse.\n\nDresden OCL2 for Eclipse is free software: you can redistribute it and/or modify \nit under the terms of the GNU Lesser General Public License as published by the \nFree Software Foundation, either version 3 of the License, or (at your option)\nany later version.\n\nDresden OCL2 for Eclipse is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY \nor FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License \nfor more details.\n\nYou should have received a copy of the GNU Lesser General Public License along \nwith Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>."; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Root Items</b></em>' reference list. The
	 * list contents are of type
	 * {@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Items</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Root Items</em>' reference list.
	 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage#getTracerRoot_RootItems()
	 * @model
	 * @generated
	 */
	EList<TracerItem> getRootItems();

} // TracerRoot
