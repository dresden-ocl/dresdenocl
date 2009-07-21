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
package tudresden.ocl20.pivot.modelbus;

import java.util.Map;
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents an instance of a domain specific type in an {@link IModel}.
 * </p>
 * 
 * @author Ronny Brandt: Developed the first version.
 * @author Claas Wilke: Did refactoring and added Javadoc.
 */
public interface IModelObject {

	/**
	 * <p>
	 * Returns the {@link Type}s of which this IModelObject is an instance.
	 * </p>
	 * 
	 * @return The {@link Type}s of which this IModelObject is an instance.</p>
	 */
	Set<Type> getTypes();

	/**
	 * FIXME Claas: Refactored until here.
	 * 
	 * <p>
	 * Adds a given result to this {@link IModelObject} for a given
	 * {@link Constraint}.
	 * </p>
	 * 
	 * @param cs
	 *          The {@link Constraint} the result belongs to.
	 * @param result
	 *          The result for the given {@link Constraint}.
	 */
	void addResult(Constraint cs, OclRoot result);

	/**
	 * <p>
	 * Clears the results for this {@link IModelObject}.
	 * </p>
	 * 
	 * @return True, if successful cleared the results.
	 */
	boolean clearResults();

	/**
	 * <p>
	 * Returns the name of the {@link IModelObject}.
	 * </p>
	 * 
	 * @return The name of the {@link IModelObject}.
	 */
	String getName();

	/**
	 * <p>
	 * Returns the {@link OclRoot} representation of the {@link IModelObject}.
	 * </p>
	 * 
	 * @return An {@link OclRoot} representing the {@link IModelObject}.
	 */
	OclRoot getOclObject();

	/**
	 * <p>
	 * Returns the qualified name as of the {@link IModelObject} as {@link String}
	 * . Needed for more efficiently use in InterpreterView.
	 * </p>
	 * 
	 * @return The qualified name of the {@link IModelObject} as {@link String}.
	 */
	String getQualifiedName();

	/**
	 * <p>
	 * Returns a {@link Map} containing {@link Constraint}s and their interpreted
	 * {@link OclRoot} results.
	 * 
	 * @return the results
	 */
	Map<Constraint, OclRoot> getResults();

	/**
	 * <p>
	 * Returns true if this {@link IModelObject} is an instance of the given
	 * {@link Type} in the {@link IModel}.
	 * </p>
	 * 
	 * @param aType
	 *          The {@link Type} which shall be checked.
	 * @return True, if this {@link IModelObject} is an instance of the given
	 *         {@link Type}.
	 */
	boolean isInstanceOf(Type aType);

	/**
	 * <p>
	 * Removes the result for a given {@link Constraint} from this
	 * {@link IModelObject}.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} whose result shall be removed.
	 * 
	 * @return True, if the result was removed successful
	 */
	boolean removeResult(Constraint aConstraint);
}
