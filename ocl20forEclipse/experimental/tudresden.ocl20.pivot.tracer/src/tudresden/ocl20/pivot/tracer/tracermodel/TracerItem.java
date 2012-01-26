/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.tracer.tracermodel;

import java.util.UUID;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Expression;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Tracer Item</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem#getExpression <em>Expression</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem#getResult <em>Result</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem#getParent <em>Parent</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem#getChildren <em>Children</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem#getUUID <em>UUID</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem#getModelInstanceElement <em>Model Instance Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage#getTracerItem()
 * @model
 * @generated
 */
public interface TracerItem extends EObject {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (C) 2011 by Lars Schütze (lschuetze@gmx.net)\n\nThis file is part of the OCL 2 Interpreter of Dresden OCL2 for Eclipse.\n\nDresden OCL2 for Eclipse is free software: you can redistribute it and/or modify \nit under the terms of the GNU Lesser General Public License as published by the \nFree Software Foundation, either version 3 of the License, or (at your option)\nany later version.\n\nDresden OCL2 for Eclipse is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY \nor FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License \nfor more details.\n\nYou should have received a copy of the GNU Lesser General Public License along \nwith Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>."; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' reference.
	 * @see #setExpression(EObject)
	 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage#getTracerItem_Expression()
	 * @model
	 * @generated
	 */
	EObject getExpression();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem#getExpression <em>Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(EObject value);

	/**
	 * Returns the value of the '<em><b>Result</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Result</em>' attribute.
	 * @see #setResult(OclAny)
	 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage#getTracerItem_Result()
	 * @model dataType="tudresden.ocl20.pivot.tracer.tracermodel.OclAny"
	 * @generated
	 */
	OclAny getResult();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem#getResult <em>Result</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result</em>' attribute.
	 * @see #getResult()
	 * @generated
	 */
	void setResult(OclAny value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(TracerItem)
	 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage#getTracerItem_Parent()
	 * @model
	 * @generated
	 */
	TracerItem getParent();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(TracerItem value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference list. The
	 * list contents are of type
	 * {@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' reference list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage#getTracerItem_Children()
	 * @model ordered="false"
	 * @generated
	 */
	EList<TracerItem> getChildren();

	/**
	 * Returns the value of the '<em><b>UUID</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>UUID</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>UUID</em>' attribute.
	 * @see #setUUID(UUID)
	 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage#getTracerItem_UUID()
	 * @model dataType="tudresden.ocl20.pivot.tracer.tracermodel.UUID"
	 * @generated
	 */
	UUID getUUID();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem#getUUID <em>UUID</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>UUID</em>' attribute.
	 * @see #getUUID()
	 * @generated
	 */
	void setUUID(UUID value);

	/**
	 * Returns the value of the '<em><b>Model Instance Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Instance Element</em>' attribute isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Instance Element</em>' attribute.
	 * @see #setModelInstanceElement(IModelInstanceElement)
	 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage#getTracerItem_ModelInstanceElement()
	 * @model dataType="tudresden.ocl20.pivot.tracer.tracermodel.IModelInstanceElement"
	 * @generated
	 */
	IModelInstanceElement getModelInstanceElement();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem#getModelInstanceElement <em>Model Instance Element</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Model Instance Element</em>' attribute.
	 * @see #getModelInstanceElement()
	 * @generated
	 */
	void setModelInstanceElement(IModelInstanceElement value);

} // TracerItem
