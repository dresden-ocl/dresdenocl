/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.tracer.tracermodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Tracer Root</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.tracer.tracermodel.TracerRoot#getRootItems <em>Root Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.tracer.tracermodel.TracermodelPackage#getTracerRoot()
 * @model
 * @generated
 */
public interface TracerRoot extends EObject {

	/**
	 * Returns the value of the '<em><b>Root Items</b></em>' reference list. The
	 * list contents are of type
	 * {@link org.dresdenocl.tracer.tracermodel.TracerItem}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Items</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Root Items</em>' reference list.
	 * @see org.dresdenocl.tracer.tracermodel.TracermodelPackage#getTracerRoot_RootItems()
	 * @model
	 * @generated
	 */
	EList<TracerItem> getRootItems();

} // TracerRoot
