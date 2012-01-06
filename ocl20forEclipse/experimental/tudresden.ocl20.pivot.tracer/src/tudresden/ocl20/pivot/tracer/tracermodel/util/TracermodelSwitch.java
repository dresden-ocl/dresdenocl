/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.tracer.tracermodel.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import tudresden.ocl20.pivot.tracer.tracermodel.*;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage
 * @generated
 */
public class TracermodelSwitch<T> extends Switch<T> {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright =
			"Copyright (C) 2011 by Lars Schütze (lschuetze@gmx.net)\n\nThis file is part of the OCL 2 Interpreter of Dresden OCL2 for Eclipse.\n\nDresden OCL2 for Eclipse is free software: you can redistribute it and/or modify \nit under the terms of the GNU Lesser General Public License as published by the \nFree Software Foundation, either version 3 of the License, or (at your option)\nany later version.\n\nDresden OCL2 for Eclipse is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY \nor FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License \nfor more details.\n\nYou should have received a copy of the GNU Lesser General Public License along \nwith Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>."; //$NON-NLS-1$
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static TracermodelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public TracermodelSwitch() {

		if (modelPackage == null) {
			modelPackage = TracermodelPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {

		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {

		switch (classifierID) {
		case TracermodelPackage.TRACER_ITEM: {
			TracerItem tracerItem = (TracerItem) theEObject;
			T result = caseTracerItem(tracerItem);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TracermodelPackage.TRACER_ROOT: {
			TracerRoot tracerRoot = (TracerRoot) theEObject;
			T result = caseTracerRoot(tracerRoot);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tracer Item</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tracer Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTracerItem(TracerItem object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tracer Root</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tracer Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTracerRoot(TracerRoot object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch, but this is
	 * the last case anyway. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {

		return null;
	}

} // TracermodelSwitch
