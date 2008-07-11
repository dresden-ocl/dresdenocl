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

import java.util.List;
import java.util.Map;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * A wrapper for domain specific object
 *
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public interface IModelObject {

	/**
	 * Gets the OCL representation of the object.
	 * 
	 * @return an {@link OclRoot} representing the user object
	 */
	OclRoot getOclObject();
	
	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	String getName();
	
	/**
	 * Gets the qualified name.
	 * 
	 * @return the qualified name
	 */
	List<String> getQualifiedName();
	
	/**
	 * Gets the qualified name as string. Needed for more efficiently use in InterpreterView.
	 * 
	 * @return the qualified name as string
	 */
	String getQualifiedNameString();
	
	/**
	 * Adds the result for given {@link Constraint}.
	 * 
	 * @param cs the {@link Constraint} the result belongs to
	 * @param result the result for the given {@link Constraint}
	 */
	void addResult(Constraint cs, OclRoot result);
	
	/**
	 * Gets the results for the {@link Constraint}.
	 * 
	 * @return the results
	 */
	Map<Constraint, OclRoot> getResults();
	
	/**
	 * Clear results.
	 * 
	 * @return true, if successful
	 */
	boolean clearResults();
	
	/**
	 * Removes result for given {@link Constraint}.
	 * 
	 * @param cs the {@link Constraint} the result shall be removed for
	 * 
	 * @return true, if successful
	 */
	boolean removeResult(Constraint cs);
}
