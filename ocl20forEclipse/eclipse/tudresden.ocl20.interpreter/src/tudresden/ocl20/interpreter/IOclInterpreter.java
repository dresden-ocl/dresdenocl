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
package tudresden.ocl20.interpreter;

import java.util.List;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * Provides an interface for interpreter which interpret {@link Constraint}s for
 * given {@link IModelObject}s.
 * </p>
 * 
 * @author Ronny Brandt
 */
public interface IOclInterpreter {

	/**
	 * <p>
	 * Removes all saved results for given objects.
	 * </p>
	 * 
	 * @param modelObjects
	 *            The {@link IModelObject}s for which the results shall be
	 *            removed.
	 */
	public void clearResults(List<IModelObject> modelObjects);

	/**
	 * <p>
	 * Interpret the given {@link Constraint} for the given {@link IModelObject}
	 * .
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} to be interpreted.
	 * @param aModelObject
	 *            The {@link IModelObject} representing the current object.
	 * 
	 * @return the result of the interpretation as {@link OclRoot}
	 */
	public OclRoot interpret(Constraint aConstraint, IModelObject aModelObject);

	/**
	 * <p>
	 * Checks if interpreter needed model access. Used for caching mechanism.
	 * </p>
	 * 
	 * @return True, if model access was needed.
	 */
	public boolean isModelAccessNeeded();

	/**
	 * <p>
	 * Checks if interpreter shall use cache.
	 * </p>
	 * 
	 * @return True, if the cache is used.
	 */
	public boolean isUseCache();

	/**
	 * <p>
	 * Prepare the given {@link Constraint} for the given {@link IModelObject}.
	 * Used for postconditions.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} to be prepared.
	 * @param aModelObject
	 *            The {@link IModelObject}, needed for self reference in
	 *            postconditions.
	 */
	public void prepare(Constraint aConstraint, IModelObject aModelObject);

	/**
	 * <p>
	 * Removes saved results for given constraints from given objects.
	 * </p>
	 * 
	 * @param modelObjects
	 *            The {@link IModelObject}s for which the results shall be
	 *            removed.
	 * @param constraints
	 *            The {@link Constraint}s for which the results shall be
	 *            removed.
	 */
	public void removeResults(List<IModelObject> modelObjects,
			List<Constraint> constraints);

	/**
	 * <p>
	 * Sets the {@link IOclInterpreter} to use cache.
	 * </p>
	 * 
	 * @param useCache
	 *            True, if the {@link IOclInterpreter} shall use cache.
	 */
	public void setUseCache(boolean useCache);
}
