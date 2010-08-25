/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL Interpreter of DresdenOCL.

DresdenOCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

DresdenOCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with DresdenOCL. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.interpreter.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclModelInstanceObject;
import tudresden.ocl20.pivot.interpreter.IInterpretationEnvironment;
import tudresden.ocl20.pivot.interpreter.IOclInterpreter;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This Environment is used to save data needed for interpretation. E.g., values
 * of parameters, variables, expressions, @pre values etc.
 * </p>
 * 
 * @author Claas Wilke (first version by Ronny Brandt).
 */
public class InterpretationEnvironment implements IInterpretationEnvironment {

	/** The {@link IModelInstance} of this {@link InterpretationEnvironment}. */
	protected IModelInstance modelInstance;

	/** Containes variables already computed {@link Variable}'s values. */
	protected HashMap<String, OclAny> visibleVariableValues = new HashMap<String, OclAny>();

	/**
	 * A probably existing parent {@link InterpretationEnvironment} of this
	 * {@link InterpretationEnvironment}.
	 */
	protected InterpretationEnvironment parentEnvironment = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IInterpretationEnvironment#getModelInstance
	 * ()
	 */
	public IModelInstance getModelInstance() {

		return this.modelInstance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IInterpretationEnvironment#getVariableValue
	 * (java.lang.String)
	 */
	public OclAny getVariableValue(String identifier) {

		OclAny result = visibleVariableValues.get(identifier);

		/*
		 * Probably delegate to parent environment (Variables are visible for
		 * children as well).
		 */
		if (result == null && this.parentEnvironment != null) {
			result = this.parentEnvironment.getVariableValue(identifier);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IInterpretationEnvironment#setModelInstance
	 * (tudresden.ocl20.pivot.modelinstance.IModelInstance)
	 */
	public void setModelInstance(IModelInstance modelInstance) {

		this.modelInstance = modelInstance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IInterpretationEnvironment#setVariableValue
	 * (java.lang.String,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny)
	 */
	public void setVariableValue(String identifier, OclAny oclRoot) {

		this.visibleVariableValues.put(identifier, oclRoot);
	}

	// FIXME Claas: Continue refactoring from here. //
	private int REFACTOR_ME = 0;

	/** The global instance of the {@link InterpretationEnvironment}. */
	private static IInterpretationEnvironment GLOBAL;

	/**
	 * Special values for postcondition constraints. Use
	 * {@link IModelInstanceElement}s as key instead of {@link OclAny}s to avoid
	 * 'Object schizophrenia'.
	 */
	protected HashMap<IModelInstanceElement, HashMap<OperationCallExp, OclAny>> postconditionValues;

	/**
	 * Saved instances of {@link Type}s existing before the current context's
	 * invocation (required for <code>oclIsNew()</code>).
	 */
	protected Map<Type, Set<IModelInstanceObject>> savedInstances = new WeakHashMap<Type, Set<IModelInstanceObject>>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IEnvironment#getPostconditionValue(
	 * tudresden .ocl20.pivot.essentialocl.expressions.OperationCallExp)
	 */
	public OclAny getPostconditionValue(OperationCallExp operationCallExp) {

		OclAny result;
		OclAny contextObject;

		result = null;

		/* Try to get the postcondition values for the current 'self' object. */
		if (this.postconditionValues != null) {
			HashMap<OperationCallExp, OclAny> objectSpecificValues;

			/* Get the object for which the value is stored. */
			contextObject = this
					.getVariableValue(IOclInterpreter.SELF_VARIABLE_NAME);

			objectSpecificValues = this.postconditionValues.get(contextObject
					.getModelInstanceElement());

			if (objectSpecificValues != null) {
				/* Try to get the value for the given expression. */
				result = objectSpecificValues.get(operationCallExp);
			}
			// no else.
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IInterpretationEnvironment#isNewInstance
	 * (tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclModelInstanceObject)
	 */
	public boolean isNewInstance(OclModelInstanceObject source) {

		boolean result;
		IModelInstanceObject imiObject;

		imiObject = (IModelInstanceObject) source.getModelInstanceElement();
		result = true;

		/*
		 * If any imiObject's type's instances contains the imiObject, return
		 * false. Else return true.
		 */
		if (this.savedInstances.containsKey(imiObject.getType())
				&& this.savedInstances.get(imiObject.getType()).contains(
						imiObject)) {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IInterpretationEnvironment#saveOldInstances
	 * (tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public void saveOldInstances(Type type) {

		this.savedInstances.put(type, this.modelInstance.getAllInstances(type));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IEnvironment#savePostconditionValue
	 * (tudresden .ocl20.pivot.essentialocl.expressions.OperationCallExp,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny)
	 */
	public void savePostconditionValue(OperationCallExp anOperationCallExp,
			OclAny aSource) {

		HashMap<OperationCallExp, OclAny> objectSpecificValues;
		OclAny contextObject;

		/* Check if the postcondition values have been initialized at all. */
		if (this.postconditionValues == null) {
			this.postconditionValues = new HashMap<IModelInstanceElement, HashMap<OperationCallExp, OclAny>>();
		}
		// no else.

		/* Get the object for which the value is stored. */
		contextObject = this
				.getVariableValue(IOclInterpreter.SELF_VARIABLE_NAME);

		objectSpecificValues = this.postconditionValues.get(contextObject
				.getModelInstanceElement());

		/* Probably initialize the specific values. */
		if (objectSpecificValues == null) {
			objectSpecificValues = new HashMap<OperationCallExp, OclAny>();
		}
		// no else.

		/* Store the postcondition value. */
		objectSpecificValues.put(anOperationCallExp, aSource);

		/* Store the specific values. */
		this.postconditionValues.put(contextObject.getModelInstanceElement(),
				objectSpecificValues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.interpreter.IInterpretationEnvironment#
	 * createChildEnvironment()
	 */
	public IInterpretationEnvironment createChildEnvironment() {

		InterpretationEnvironment result = new InterpretationEnvironment();
		result.parentEnvironment = this;

		result.modelInstance = this.modelInstance;

		/*
		 * Variables have not to be copied since requests are probably delegated
		 * to the parent environment.
		 */

		// TODO What about parameters?

		// TODO What about postcondition values?
		result.postconditionValues = this.postconditionValues;

		// TODO What about saved instances?
		result.savedInstances = this.savedInstances;

		return result;
	}
}