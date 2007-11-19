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

import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;

/**
 * 
 *
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public interface IEnvironment extends Cloneable {

	/**
	 * Gets saved variables with given name.
	 * 
	 * @param path the path of the variable or simply the name (e.g. "self")
	 * 
	 * @return the saved variable
	 */
	OclRoot getVar(String path);
	
	/**
	 * Saves variable to environment.
	 * 
	 * @param path the path of the variable or simply the name (e.g. "self")
	 * @param o the variable to be saved
	 */
	void addVar(String path, OclRoot o);
	
	/**
	 * Gets the model instance for current interpretation.
	 * 
	 * @return the actual model instance
	 */
	IModelInstance getModelInstance();
	
	/**
	 * Sets the model instance to be used during interpretation.
	 * 
	 * @param mi the new model instance
	 */
	void setModelInstance(IModelInstance mi);
	
	/**
	 * Saves the constraint for the given path. Used for OCL-constraint types
	 * body, def, initial and derive.
	 * 
	 * @param path the path of the attribute/operation described by the constraint
	 * @param constraint the constraint describing the attribute/operation
	 */
	void addConstraint(String path, Constraint constraint);

	/**
	 * Gets saved constraint describing attribute/operation with given path.
	 * 
	 * @param path the path of the attribute/operation described by the constraint
	 * 
	 * @return the constraint describing the attribute/operation
	 */
	Constraint getConstraint(String path);
	
	/**
	 * Clone an environment. Used for creating local environment.
	 * 
	 * @return a copy of the environment
	 */
	IEnvironment clone();
	
	/**
	 * Caches results for a NamedElement (e.g. an expression). Used for 
	 * performance enhancement.
	 * 
	 * @param elem the NamedElement the result belongs to 
	 * @param result the result for the NamedElement
	 */
	void cacheResult(NamedElement elem, OclRoot result);
	
	/**
	 * Gets the cached result for a NamedElement.
	 * 
	 * @param elem the NamedElement the result is asked for.
	 * 
	 * @return the cached result
	 */
	OclRoot getCachedResult(NamedElement elem);
	
	/**
	 * Remove all results from cache.
	 */
	void clearCache();

	/**
	 * Saves result of atPre() and oclIsNew() for preparation of postconditions.
	 * 
	 * @param object the OperationCallExp containing operation atPre() or oclIsNew()
	 * @param source the result for that operation
	 */
	void savePostconditionValue(OperationCallExp object, OclRoot source);

	/**
	 * Returns result for operations atPre() and oclIsNew().
	 * 
	 * @param object the OperationCallExp containing operation atPre() or oclIsNew()
	 * 
	 * @return the postcondition result for that operation
	 */
	OclRoot getPostconditionValue(OperationCallExp object);
}
