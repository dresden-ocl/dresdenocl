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
package org.dresdenocl.interpreter.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.dresdenocl.essentialocl.expressions.OperationCallExp;
import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.essentialocl.standardlibrary.OclModelInstanceObject;
import org.dresdenocl.interpreter.IInterpretationEnvironment;
import org.dresdenocl.interpreter.IOclInterpreter;
import org.dresdenocl.modelinstance.IModelInstance;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceObject;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * This Environment is used to save data needed for interpretation. E.g., values
 * of parameters, variables, expressions, @pre values etc.
 * </p>
 * 
 * @author Claas Wilke (first version by Ronny Brandt).
 */
public class InterpretationEnvironment implements IInterpretationEnvironment {

	/**
	 * {@link Map} to store @pre values used during postcondition evaluation. Uses
	 * {@link IModelInstanceElement}s as key to store the values for each specific
	 * {@link IModelInstanceObject} seperately. This {@link Map} is created lazily
	 * since children {@link InterpretationEnvironment}s delegate their requests
	 * to their parent.
	 */
	protected Map<IModelInstanceElement, Map<OperationCallExp, OclAny>> atPreValues;

	/** The {@link IModelInstance} of this {@link InterpretationEnvironment}. */
	protected IModelInstance modelInstance;

	/**
	 * Saved instances of {@link Type}s existing at the latest {@link Constraint}
	 * 's preparation (required for <code>oclIsNew()</code>). This {@link Map} is
	 * initialized lazily since children {@link InterpretationEnvironment}s do not
	 * need such a {@link Map}.
	 */
	protected Map<Type, Set<IModelInstanceObject>> oldInstancesByType;

	/**
	 * A probably existing parent {@link InterpretationEnvironment} of this
	 * {@link InterpretationEnvironment}.
	 */
	protected InterpretationEnvironment parentEnvironment = null;

	/** Containes variables already computed {@link Variable}'s values. */
	protected HashMap<String, OclAny> visibleVariableValues =
			new HashMap<String, OclAny>();

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.interpreter.IInterpretationEnvironment#
	 * createChildEnvironment()
	 */
	public IInterpretationEnvironment createChildEnvironment() {

		InterpretationEnvironment result = new InterpretationEnvironment();
		result.parentEnvironment = this;

		result.modelInstance = this.modelInstance;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.interpreter.IEnvironment#getPostconditionValue(
	 * tudresden .ocl20.pivot.essentialocl.expressions.OperationCallExp)
	 */
	public OclAny getAtPreValue(OperationCallExp operationCallExp) {

		OclAny result;
		OclAny contextObject;

		result = null;

		/* Probably delegate to parent environment. */
		if (this.parentEnvironment != null) {
			this.parentEnvironment.getAtPreValue(operationCallExp);
		}

		else {
			/* Try to get the @pre values for the current 'self' object. */
			if (this.atPreValues != null) {

				Map<OperationCallExp, OclAny> instanceElementSpecificAtPreValues;

				/*
				 * Get the ModelInstanceObject for which the postcondition is currently
				 * evaluated.
				 */
				contextObject =
						this.getVariableValue(IOclInterpreter.SELF_VARIABLE_NAME);

				instanceElementSpecificAtPreValues =
						this.atPreValues.get(contextObject.getModelInstanceElement());

				/* Try to get the value for the given expression. */
				if (instanceElementSpecificAtPreValues != null) {
					result = instanceElementSpecificAtPreValues.get(operationCallExp);
				}
				// no else.
			}
			// no else.
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.interpreter.IInterpretationEnvironment#getModelInstance
	 * ()
	 */
	public IModelInstance getModelInstance() {

		return this.modelInstance;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.interpreter.IInterpretationEnvironment#getVariableValue
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
	 * @see
	 * org.dresdenocl.interpreter.IInterpretationEnvironment#isNewInstance
	 * (tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclModelInstanceObject)
	 */
	public boolean isNewInstance(OclModelInstanceObject object) {

		boolean result;
		IModelInstanceObject imiObject;

		imiObject = (IModelInstanceObject) object.getModelInstanceElement();

		/* Probably check the parent environment. */
		if (this.parentEnvironment != null) {
			result = this.parentEnvironment.isNewInstance(object);
		}

		/*
		 * If any imiObject's type's instances contains the imiObject, return false.
		 * Else return true.
		 */
		else if (this.oldInstancesByType != null
				&& this.oldInstancesByType.containsKey(imiObject.getType())) {
			result =
					!this.oldInstancesByType.get(imiObject.getType()).contains(imiObject);
		}

		else {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.interpreter.IEnvironment#savePostconditionValue
	 * (tudresden .ocl20.pivot.essentialocl.expressions.OperationCallExp,
	 * org.dresdenocl.essentialocl.standardlibrary.OclAny)
	 */
	public void saveAtPreValue(OperationCallExp anOperationCallExp, OclAny value) {

		/* Probably delegate to parent environment. */
		if (this.parentEnvironment != null) {
			this.parentEnvironment.saveAtPreValue(anOperationCallExp, value);
		}

		else {
			Map<OperationCallExp, OclAny> instanceObjectSpecificValues;
			OclAny contextObject;

			/* Check if any @pre values have been stored yet. */
			if (this.atPreValues == null) {
				this.atPreValues =
						new WeakHashMap<IModelInstanceElement, Map<OperationCallExp, OclAny>>();
			}
			// no else.

			/*
			 * Get the ModelInstanceObject for which a @pre value shall be stored.
			 */
			contextObject = this.getVariableValue(IOclInterpreter.SELF_VARIABLE_NAME);

			instanceObjectSpecificValues =
					this.atPreValues.get(contextObject.getModelInstanceElement());

			/* Probably initialize the ModelInstanceObject specific @pre values. */
			if (instanceObjectSpecificValues == null) {
				instanceObjectSpecificValues = new HashMap<OperationCallExp, OclAny>();
			}
			// no else.

			/* Store the postcondition value. */
			instanceObjectSpecificValues.put(anOperationCallExp, value);

			/* Store the specific values. */
			this.atPreValues.put(contextObject.getModelInstanceElement(),
					instanceObjectSpecificValues);
		}
		// end else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.interpreter.IInterpretationEnvironment#saveOldInstances
	 * (org.dresdenocl.pivotmodel.Type)
	 */
	public void saveOldInstances(Type type) {

		/*
		 * If possible use the parent environment to avoid duplicate entries causing
		 * memory overhead.
		 */
		if (this.parentEnvironment == null) {

			/* Lazy initialization. */
			if (this.oldInstancesByType == null) {
				this.oldInstancesByType =
						new WeakHashMap<Type, Set<IModelInstanceObject>>();
			}
			// no else.

			this.oldInstancesByType.put(type,
					this.modelInstance.getAllInstances(type));
		}

		else {
			this.parentEnvironment.saveOldInstances(type);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.interpreter.IInterpretationEnvironment#setModelInstance
	 * (org.dresdenocl.modelinstance.IModelInstance)
	 */
	public void setModelInstance(IModelInstance modelInstance) {

		this.modelInstance = modelInstance;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.interpreter.IInterpretationEnvironment#setVariableValue
	 * (java.lang.String,
	 * org.dresdenocl.essentialocl.standardlibrary.OclAny)
	 */
	public void setVariableValue(String identifier, OclAny oclRoot) {

		this.visibleVariableValues.put(identifier, oclRoot);
	}

	@Override
	public Map<String, OclAny> getStoredVariableMappings() {
		return visibleVariableValues;
	}
}