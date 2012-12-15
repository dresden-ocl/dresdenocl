/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * $Id$
 */
package org.dresdenocl.essentialocl.expressions;

import org.dresdenocl.pivotmodel.ConstrainableElement;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.TypedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.Variable#getRepresentedParameter <em>Represented Parameter</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.Variable#getInitExpression <em>Init Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface Variable extends TypedElement, NamedElement {

	/**
	 * Returns the value of the '<em><b>Represented Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Represented Parameter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Represented Parameter</em>' reference.
	 * @see #setRepresentedParameter(Parameter)
	 * @generated
	 */
	Parameter getRepresentedParameter();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.expressions.Variable#getRepresentedParameter <em>Represented Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Represented Parameter</em>' reference.
	 * @see #getRepresentedParameter()
	 * @generated
	 */
	void setRepresentedParameter(Parameter value);

	/**
	 * Returns the value of the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Expression</em>' containment reference.
	 * @see #setInitExpression(OclExpression)
	 * @generated
	 */
	OclExpression getInitExpression();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.expressions.Variable#getInitExpression <em>Init Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Init Expression</em>' containment reference.
	 * @see #getInitExpression()
	 * @generated
	 */
	void setInitExpression(OclExpression value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * The OCL 2.0 specification defines an additional operation that returns a
	 * corresponding {@link Parameter} for a <code>Variable</code>.
	 * 
	 * It is specified as follows:
	 * <pre>
	 * context Variable::asParameter() : Parameter
	 * post: result.name = self.name
	 * post: result.direction = ParameterDirectionKind::in
	 * post: result.type = self.type
	 * </pre>
	 * </p>
	 * <!-- end-model-doc -->
	 * @generated
	 */
	Parameter asParameter();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * The OCL 2.0 Specification defines an additional operation that returns a
	 * corresponding {@link Property} for a <code>Variable</code>.
	 * 
	 * It is specified as follows:
	 * <pre>
	 * context Variable::asProperty() : Attribute
	 * post: result.name= self.name
	 * post: result.type= self.type
	 * post: result.isMultiple = false
	 * post: result.isOrdered= true
	 * post: result.isStatic= false
	 * </pre>
	 * 
	 * Note that the OCL specification adds an additional post condition:
	 * 
	 * <pre>
	 * post: result.constraint.specification.bodyExpression = self.initExpression
	 * </pre>
	 * 
	 * However, the meta association between the Pivot Model elements
	 * {@link Constraint} and {@link ConstrainableElement} is only
	 * undirectional (in analogy to the specification in Core::Abstractions),
	 * so we cannot navigate from the property to one of its constraints.
	 * Also, this constraint is not really required because the
	 * <code>asProperty()</code> operation is only used to
	 * determine the type of {@link TupleLiteralExpression}s. There. 
	 * a conversion from <code>Variable</code> to
	 * <code>Property</code> is needed to make a corresponding
	 * <code>TupleType</code>. Thus, setting the name and the
	 * type of the created <code>Propery</code> is sufficient.
	 * </p>
	 * <!-- end-model-doc -->
	 * @generated
	 */
	Property asProperty();

	/**
	 * Ths method was added manually to support adding parameters
	 * to the ExpressionInOcl instance by the parser. If the parser trys
	 * to added the same parameter or variable to an ExpressionInOcl the
	 * first ExpressionInOcl instance will lost this parameter. So,
	 * a parameter instance can only be set only once.
	 * @return a clone of a variable
	 */
	public Variable clone();

} // Variable
