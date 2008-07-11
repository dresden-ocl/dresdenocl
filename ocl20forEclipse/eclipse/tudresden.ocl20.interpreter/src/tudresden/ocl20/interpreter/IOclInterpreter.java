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
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public interface IOclInterpreter {

	/**
	 * Interpret the given constraint for the given object.
	 * 
	 * @param c
	 *            the {@link Constraint} to be interpreted
	 * @param mo
	 *            the {@link IModelObject} representing the current object
	 * 
	 * @return the result of the interpretation as {@link OclRoot}
	 */
	public OclRoot interpret(Constraint c, IModelObject mo);

	/**
	 * Remove saved results for given constraints from given objects.
	 * 
	 * @param modelObjects
	 *            the {@link IModelObject}s for which the results shall be
	 *            removed
	 * @param constraints
	 *            the {@link Constraint}s for which the results shall be
	 *            removed
	 */
	public void removeResults(List<IModelObject> modelObjects,
			List<Constraint> constraints);

	/**
	 * Remove all saved results for given objects.
	 * 
	 * @param modelObjects
	 *            the {@link IModelObject}s for which the results shall be
	 *            removed
	 */
	public void clearResults(List<IModelObject> modelObjects);

	/**
	 * Checks if interpreter needed model access. Used for caching mechanism.
	 * 
	 * @return true, if model access was needed
	 */
	public boolean isModelAccessNeeded();

	/**
	 * Checks if interpreter shall use cache.
	 * 
	 * @return true, if is use cache
	 */
	public boolean isUseCache();

	/**
	 * Sets the interpreter to use cache.
	 * 
	 * @param useCache
	 *            true, if interpreter shall use cache
	 */
	public void setUseCache(boolean useCache);

	/**
	 * Prepare the given {@link Constraint} for the given {@link IModelObject}.
	 * Used for postconditions.
	 * 
	 * @param c
	 *            the {@link Constraint} to be prepared.
	 * @param mo
	 *            the {@link IModelObject}, needed for self reference in
	 *            postconditions
	 */
	public void prepare(Constraint c, IModelObject mo);
}
