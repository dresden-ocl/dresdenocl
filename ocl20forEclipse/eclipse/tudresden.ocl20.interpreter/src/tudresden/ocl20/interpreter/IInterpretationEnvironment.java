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
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This interface represents environments used to store values and object during
 * the interpretation of constraints.
 * </p>
 * 
 * @author Ronny Brandt
 */
public interface IInterpretationEnvironment extends Cloneable {

	/**
	 * <p>
	 * Saves the {@link Constraint} for the given path. Used for OCL-constraint
	 * types body, def, initial and derive.
	 * </p>
	 * 
	 * @param path
	 *          The path of the attribute/operation described by the
	 *          {@link Constraint}.
	 * @param aConstraint
	 *          The {@link Constraint} describing the attribute/operation.
	 */
	public void addConstraint(String path, Constraint aConstraint);

	/**
	 * <p>
	 * Saves a variable in the environment.
	 * </p>
	 * 
	 * @param path
	 *          The path of the variable or simply the name (e.g. "self")
	 * @param anOclRoot
	 *          The variable to be saved.
	 */
	public void addVar(String path, OclAny anOclRoot);

	/**
	 * <p>
	 * Caches results for a {@link NamedElement} (e.g. an expression). Used for
	 * performance enhancement.
	 * </p>
	 * 
	 * @param anElement
	 *          The {@link NamedElement} the result belongs to.
	 * @param result
	 *          The result for the {@link NamedElement}.
	 */
	public void cacheResult(NamedElement anElement, OclAny result);

	/**
	 * <p>
	 * Removes all results from cache.
	 * </p>
	 */
	public void clearCache();

	/**
	 * <p>
	 * Removes all prepared {@link Constraint}s from the
	 * {@link IInterpretationEnvironment}.
	 * </p>
	 */
	public void clearPreparedConstraints();

	/**
	 * <p>
	 * Clones an {@link IInterpretationEnvironment}. Used for creating local
	 * environments.
	 * 
	 * @return A copy of the {@link IInterpretationEnvironment}.
	 */
	public IInterpretationEnvironment clone();

	/**
	 * <p>
	 * Gets the cached result for a {@link NamedElement}.
	 * </p>
	 * 
	 * @param anElement
	 *          The {@link NamedElement} the result is asked for.
	 * 
	 * @return The cached result.
	 */
	public OclAny getCachedResult(NamedElement anElement);

	/**
	 * <p>
	 * Gets saved {@link Constraint} describing attribute/operation with given
	 * path.
	 * </p>
	 * 
	 * @param path
	 *          The path of the attribute/operation described by the
	 *          {@link Constraint}.
	 * 
	 * @return The {@link Constraint} describing the attribute/operation.
	 */
	public Constraint getConstraint(String path);

	/**
	 * <p>
	 * Gets the {@link IModelInstance} for the current interpretation.
	 * </p>
	 * 
	 * @return The actual {@link IModelInstance}.
	 */
	public IModelInstance getModelInstance();

	/**
	 * <p>
	 * Returns result for operations atPre() and oclIsNew().
	 * </p>
	 * 
	 * @param anOperationCallExp
	 *          The {@link OperationCallExp} containing operation atPre() or
	 *          oclIsNew().
	 * 
	 * @return The postcondition result for that operation.
	 */
	public OclAny getPostconditionValue(OperationCallExp anOperationCallExp);

	/**
	 * <p>
	 * Gets saved variables with given name.
	 * </p>
	 * 
	 * @param path
	 *          The path of the variable or simply the name (e.g. "self").
	 * 
	 * @return Saved variables with given name.
	 */
	public OclAny getVar(String path);

	/**
	 * <p>
	 * Saves result of atPre() and oclIsNew() for preparation of postconditions.
	 * </p>
	 * 
	 * @param anOperationCallExp
	 *          The {@link OperationCallExp} containing operation atPre() or
	 *          oclIsNew().
	 * @param source
	 *          The result for that operation.
	 */
	public void savePostconditionValue(OperationCallExp anOperationCallExp,
			OclAny source);

	/**
	 * <p>
	 * Sets the {@link IModelInstance} to be used during interpretation.
	 * </p>
	 * 
	 * @param aModelInstance
	 *          The new {@link IModelInstance}.
	 */
	public void setModelInstance(IModelInstance aModelInstance);

	/**
	 * <p>
	 * Saves a set of all instances of a given {@link Type} that existed during
	 * the preparation of a postcondition. The stored value can be used to call
	 * the method {@link IInterpretationEnvironment#isNewInstance(OclAny)} to
	 * interpret the <code>oclIsNew()</code> operation during the interpretation
	 * of a postcondition.
	 * </p>
	 * 
	 * @param type
	 *          The {@link Type} for which the instances shall be stored.
	 */
	public void saveOldInstances(Type type);

	/**
	 * <p>
	 * Checks whether or not a given {@link IModelInstanceObject} (represented by
	 * an {@link OclModelInstanceObject}) existed before the execution of the
	 * current interpreted postcondition. This method can be used to interpret the
	 * <code>oclIsNew()</code> operation during the interpretation of a
	 * postcondition.
	 * 
	 * @param source
	 *          The {@link OclModelInstanceObject} for that the
	 *          <code>oclIsNew()</code> operation shall be evaluated.
	 * @return <code>true</code>, if the given {@link OclModelInstanceObject} did
	 *         not exist before the operation invocation of the current
	 *         interpreted postcondition.
	 */
	public boolean isNewInstance(OclModelInstanceObject source);
}