/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part
 * of the OCL Interpreter of Dresden OCL2 for Eclipse. Dresden OCL2 for Eclipse
 * is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. Dresden OCL2 for Eclipse is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.interpreter;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.interpreter.internal.InterpretationResultImpl;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * Represents interpretation results of Ocl {@link Constraint} associated to an
 * {@link IModelInstanceElement}.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public interface IInterpretationResult {

	/**
	 * <p>
	 * Returns the {@link IModelInstanceElement} of this {@link InterpretationResultImpl}.
	 * </p>
	 * 
	 * @return The {@link IModelInstanceElement} of this {@link InterpretationResultImpl}.
	 */
	public IModelInstanceElement getModelObject();

	/**
	 * <p>
	 * Returns the result of this {@link InterpretationResultImpl} as
	 * {@link OclRoot} .
	 * </p>
	 * 
	 * @return The result of this {@link InterpretationResultImpl} as
	 *         {@link OclRoot} .
	 */
	public OclAny getResult();

	/**
	 * <p>
	 * Returns the {@link Constraint} of this {@link InterpretationResultImpl}.
	 * </p>
	 * 
	 * @return The {@link Constraint} of this {@link InterpretationResultImpl}.
	 */
	public Constraint getConstraint();
}