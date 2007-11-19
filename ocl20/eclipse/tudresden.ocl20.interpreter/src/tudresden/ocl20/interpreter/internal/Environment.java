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
package tudresden.ocl20.interpreter.internal;

import java.util.HashMap;

import tudresden.ocl20.interpreter.IEnvironment;
import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;

/**
 * The Environment is used to save data needed for interpretation.
 *
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class Environment implements IEnvironment {

	// the global instance of the environment
	private static IEnvironment GLOBAL;

	// the actual model instance
	protected IModelInstance mi;

	// saved constraints for body, def, initial und derive
	protected HashMap<String, Constraint> constraints;

	// saved variables
	protected HashMap<String, OclRoot> vars;

	// cached results
	private HashMap<NamedElement, OclRoot> cachedResults;

	// special values for postcondition constraints
	protected HashMap<OclRoot, HashMap<OperationCallExp, OclRoot>> postconditionValues;

	/**
	 * Gets the global environment.
	 * 
	 * @return the global instance of the environment
	 */
	public static IEnvironment getGlobalEnvironment() {
		if (GLOBAL == null)
			GLOBAL = new Environment();
		return GLOBAL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IEnvironment#getModelInstance()
	 */
	public IModelInstance getModelInstance() {
		return mi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IEnvironment#getVar(java.lang.String)
	 */
	public OclRoot getVar(String path) {
		if (vars != null)
			return vars.get(path);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IEnvironment#setModelInstance(tudresden.ocl20.pivot.modelbus.IModelInstance)
	 */
	public void setModelInstance(IModelInstance mi) {
		this.mi = mi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IEnvironment#addVar(java.lang.String,
	 *      tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public void addVar(String path, OclRoot oclRoot) {
		if (vars == null)
			vars = new HashMap<String, OclRoot>();

		vars.put(path, oclRoot);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IEnvironment#addConstraint(java.lang.String,
	 *      tudresden.ocl20.pivot.pivotmodel.Constraint)
	 */
	public void addConstraint(String path, Constraint constraint) {
		if (constraints == null)
			constraints = new HashMap<String, Constraint>();
		constraints.put(path, constraint);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IEnvironment#getConstraint(java.lang.String)
	 */
	public Constraint getConstraint(String path) {
		if (constraints != null)
			return constraints.get(path);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public IEnvironment clone() {
		Environment result = new Environment();

		result.constraints = this.constraints;
		result.mi = this.mi;
		result.vars = this.vars;
		result.cachedResults = this.cachedResults;

		return result;
	}

	/**
	 * Gets the new local environment which is a copy of the global environment.
	 * 
	 * @return the new local environment
	 */
	public static IEnvironment getNewLocalEnvironment() {
		return GLOBAL.clone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IEnvironment#cacheResult(tudresden.ocl20.pivot.pivotmodel.NamedElement,
	 *      tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public void cacheResult(NamedElement elem, OclRoot result) {
		if (cachedResults == null)
			cachedResults = new HashMap<NamedElement, OclRoot>();
		cachedResults.put(elem, result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IEnvironment#getCachedResult(tudresden.ocl20.pivot.pivotmodel.NamedElement)
	 */
	public OclRoot getCachedResult(NamedElement elem) {
		if (cachedResults != null)
			return cachedResults.get(elem);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IEnvironment#clearCache()
	 */
	public void clearCache() {
		if (cachedResults != null) {
			cachedResults.clear();
			cachedResults = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IEnvironment#savePostconditionValue(tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp,
	 *      tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public void savePostconditionValue(OperationCallExp object, OclRoot source) {
		if (postconditionValues == null)
			postconditionValues = new HashMap<OclRoot, HashMap<OperationCallExp, OclRoot>>();
		HashMap<OperationCallExp, OclRoot> specificValues = postconditionValues
				.get(getVar("self"));
		if (specificValues == null)
			specificValues = new HashMap<OperationCallExp, OclRoot>();
		specificValues.put(object, source);
		postconditionValues.put(getVar("self"), specificValues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IEnvironment#getPostconditionValue(tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp)
	 */
	public OclRoot getPostconditionValue(OperationCallExp object) {
		if (postconditionValues != null) {
			HashMap<OperationCallExp, OclRoot> specificValues = postconditionValues
					.get(getVar("self"));
			if (specificValues != null)
				return specificValues.get(object);
		}
		return mi.getUndefined();
	}
}
