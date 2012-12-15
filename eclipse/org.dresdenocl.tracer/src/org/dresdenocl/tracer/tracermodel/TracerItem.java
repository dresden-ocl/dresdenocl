/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.tracer.tracermodel;

import java.util.UUID;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Tracer Item</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.tracer.tracermodel.TracerItem#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.dresdenocl.tracer.tracermodel.TracerItem#getResult <em>Result</em>}</li>
 *   <li>{@link org.dresdenocl.tracer.tracermodel.TracerItem#getParent <em>Parent</em>}</li>
 *   <li>{@link org.dresdenocl.tracer.tracermodel.TracerItem#getChildren <em>Children</em>}</li>
 *   <li>{@link org.dresdenocl.tracer.tracermodel.TracerItem#getUUID <em>UUID</em>}</li>
 *   <li>{@link org.dresdenocl.tracer.tracermodel.TracerItem#getModelInstanceElement <em>Model Instance Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.tracer.tracermodel.TracermodelPackage#getTracerItem()
 * @model
 * @generated
 */
public interface TracerItem extends EObject {

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
	 * @see org.dresdenocl.tracer.tracermodel.TracermodelPackage#getTracerItem_Expression()
	 * @model
	 * @generated
	 */
	EObject getExpression();

	/**
	 * Sets the value of the '{@link org.dresdenocl.tracer.tracermodel.TracerItem#getExpression <em>Expression</em>}' reference.
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
	 * @see org.dresdenocl.tracer.tracermodel.TracermodelPackage#getTracerItem_Result()
	 * @model dataType="org.dresdenocl.tracer.tracermodel.OclAny"
	 * @generated
	 */
	OclAny getResult();

	/**
	 * Sets the value of the '{@link org.dresdenocl.tracer.tracermodel.TracerItem#getResult <em>Result</em>}' attribute.
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
	 * @see org.dresdenocl.tracer.tracermodel.TracermodelPackage#getTracerItem_Parent()
	 * @model
	 * @generated
	 */
	TracerItem getParent();

	/**
	 * Sets the value of the '{@link org.dresdenocl.tracer.tracermodel.TracerItem#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(TracerItem value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference list. The
	 * list contents are of type
	 * {@link org.dresdenocl.tracer.tracermodel.TracerItem}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' reference list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see org.dresdenocl.tracer.tracermodel.TracermodelPackage#getTracerItem_Children()
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
	 * @see org.dresdenocl.tracer.tracermodel.TracermodelPackage#getTracerItem_UUID()
	 * @model dataType="org.dresdenocl.tracer.tracermodel.UUID"
	 * @generated
	 */
	UUID getUUID();

	/**
	 * Sets the value of the '{@link org.dresdenocl.tracer.tracermodel.TracerItem#getUUID <em>UUID</em>}' attribute.
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
	 * @see org.dresdenocl.tracer.tracermodel.TracermodelPackage#getTracerItem_ModelInstanceElement()
	 * @model dataType="org.dresdenocl.tracer.tracermodel.IModelInstanceElement"
	 * @generated
	 */
	IModelInstanceElement getModelInstanceElement();

	/**
	 * Sets the value of the '{@link org.dresdenocl.tracer.tracermodel.TracerItem#getModelInstanceElement <em>Model Instance Element</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Model Instance Element</em>' attribute.
	 * @see #getModelInstanceElement()
	 * @generated
	 */
	void setModelInstanceElement(IModelInstanceElement value);

} // TracerItem
