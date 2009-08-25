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
package tudresden.ocl20.pivot.modelbus.modelinstance.types;

import java.util.Set;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents an instance of a domain specific type in an {@link IModel}.
 * </p>
 * 
 * @author Ronny Brandt: Developed the first version.
 * @author Claas Wilke: Did refactoring and added Javadoc.
 */
public interface IModelInstanceElement {

	/**
	 * <p>
	 * Copies this wrapper and sets the {@link #getTypes() type} to the given type
	 * if an upcast is possible. Downcasts are not necessary, since the execution
	 * of standard library methods always returns the most specific type.
	 * </p>
	 * 
	 * @param type
	 *          the {@link Type} to cast this {@link IModelInstanceElement} to
	 * @return the new {@link IModelInstanceElement} that has the given type
	 */
	IModelInstanceElement asType(Type type);

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
	 * @return a deep copy of the adapted element
	 */
	Object copyForAtPre();

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
	 * Returns the {@link Type}s of that this IModelInstanceElement is an
	 * instance.
	 * </p>
	 * 
	 * <p>
	 * <strong>Note:</strong> Only directly implemented types are returned; not
	 * their super types!
	 * </p>
	 * 
	 * <p>
	 * <strong>Please be aware, that the {@link Type}s of an
	 * {@link IModelInstanceElement} are only set, if the
	 * {@link IModelInstanceElement} represents at least one Type in the
	 * {@link IModel}. The {@link IModelInstanceElement}s of the kinds
	 * {@link IModelInstanceCollection}, {@link IModelInstanceBoolean},
	 * {@link IModelInstanceInteger}, {@link IModelInstanceReal}, and
	 * {@link IModelInstanceString} do not have a {@link Type}.</strong>
	 * </p>
	 * 
	 * @return The {@link Type}s of which this IModelInstanceElement is an
	 *         instance. </p>
	 */
	Set<Type> getTypes();

	/**
	 * <p>
	 * Returns true if this {@link IModelInstanceElement} is an instance of the
	 * given {@link Type} in the {@link IModel}.
	 * </p>
	 * 
	 * @param aType
	 *          The {@link Type} which shall be checked.
	 * @return True, if this {@link IModelInstanceElement} is an instance of the
	 *         given {@link Type}.
	 */
	boolean isInstanceOf(Type aType);

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