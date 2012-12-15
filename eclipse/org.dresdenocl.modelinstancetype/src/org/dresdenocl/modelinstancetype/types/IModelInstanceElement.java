/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
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
 */
package org.dresdenocl.modelinstancetype.types;

import org.dresdenocl.modelinstancetype.exception.AsTypeCastException;
import org.dresdenocl.modelinstancetype.exception.CopyForAtPreException;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Represents an instance of a domain specific type in an <code>IModel</code>.
 * </p>
 * 
 * @author Ronny Brandt: Developed the first version.
 * @author Claas Wilke: Did refactoring and added Javadoc.
 */
public interface IModelInstanceElement {

	/**
	 * <p>
	 * Copies this wrapper and sets the {@link #getTypes()}' {@link Type} to the
	 * given {@link Type} if an cast to the given {@link Type} is possible.
	 * </p>
	 * 
	 * <p>
	 * <strong>Please note, that the cast is performed in way of the OCL
	 * semantic.</strong>
	 * </p>
	 * <p>
	 * E.g., having two {@link Type}s <code>A</code> and <code>B</code>.
	 * <code>A</code> defines a method <code>A.a()</code>. <code>B</code> extends
	 * <code>A</code> and overwrites the method <code>A.a()</code> with
	 * <code>B.a()</code> and introduces a new method <code>B.b()</code>.
	 * </p>
	 * <p>
	 * If an instance of <code>B</code> (called <code>aB</code>) is casted to
	 * <code>A</code>, the operation <code>aB.a()</code> is still available but
	 * all calls will be delegated to <code>A.a()</code> instead. The operation
	 * <code>aB.b()</code> will not be available anymore.
	 * </p>
	 * 
	 * <p>
	 * {@link IModelInstancePrimitiveType}s can only be casted to other
	 * {@link IModelInstancePrimitiveType}s and only in some states.
	 * {@link IModelInstanceEnumerationLiteral} can only be casted to itself.
	 * {@link IModelInstanceCollection}s can always be caste to other
	 * {@link IModelInstanceCollection}s.
	 * </p>
	 * 
	 * @param type
	 *          the {@link Type} to cast this {@link IModelInstanceElement} to
	 * @return the new {@link IModelInstanceElement} that has the given type
	 * @throws AsTypeCastException
	 *           Thrown if this operation is invoked with a {@link Type} to that
	 *           this {@link IModelInstanceElement} cannot be casted.
	 */
	IModelInstanceElement asType(Type type) throws AsTypeCastException;

	/**
	 * <p>
	 * Performs a copy of the adapted element of this
	 * {@link IModelInstanceElement} that can be used to store it as a @pre value
	 * of a postcondition's expression. The method should copy the adapted object
	 * and all its references that are expected to change during the methods
	 * execution the postcondition is defined on.
	 * </p>
	 * 
	 * <p>
	 * For primitive types, the method should copy the object, for collection
	 * types it should only copy the collection but not its content.
	 * </p>
	 * 
	 * @return A copy of the adapted element.
	 * @throws CopyForAtPreException
	 *           Thrown, if an error during the copy process occurs.
	 */
	IModelInstanceElement copyForAtPre() throws CopyForAtPreException;

	/**
	 * <p>
	 * Returns the name or ID of this {@link IModelInstanceElement}. Otherwhise it
	 * returns the {@link Type}s' names.
	 * </p>
	 * 
	 * @return The name of the {@link IModelInstanceElement}.
	 */
	String getName();

	/**
	 * <p>
	 * Returns the {@link Type} of that this IModelInstanceElement is an instance.
	 * </p>
	 * 
	 * <p>
	 * <strong>Note:</strong> Only directly implemented types are returned; not
	 * their super types!
	 * </p>
	 * 
	 * @return The {@link Type} of which this IModelInstanceElement is an
	 *         instance. </p>
	 */
	Type getType();

	/**
	 * <p>
	 * Evaluates to <code>true</code> if the type of self conforms to the given
	 * {@link Type aType}. That is, self is of type aType or a subtype of aType.
	 * </p>
	 * 
	 * @param aType
	 *          The {@link Type} which shall be checked.
	 * @return <code>true</code>, if this {@link IModelInstanceElement} is kind of
	 *         the given {@link Type}.
	 */
	boolean isKindOf(Type aType);

	/**
	 * <p>
	 * Evaluates to <code>true</code> if self is of the {@link Type aType} but not
	 * a subtype of aType.
	 * </p>
	 * 
	 * @param aType
	 *          The {@link Type} which shall be checked.
	 * @return <code>true</code>, if this {@link IModelInstanceElement} is type of
	 *         the given {@link Type}.
	 */
	boolean isTypeOf(Type aType);

	/**
	 * <p>
	 * Returns true, if this {@link IModelInstanceElement} is undefined, meaning
	 * its adapted {@link Object} is <code>null</code>.
	 * </p>
	 * 
	 * @return True, if this {@link IModelInstanceElement} is undefined.
	 */
	boolean isUndefined();
}