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
package tudresden.ocl20.pivot.interpreter;

import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclModelInstanceObject;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This interface represents environments used to store values and object during
 * the interpretation of constraints.
 * </p>
 * 
 * @author Claas Wilke (first version by Ronny Brandt)
 */
public interface IInterpretationEnvironment extends Cloneable {

    /**
     * <p>
     * Creates a new child {@link IInterpretationEnvironment}.
     * </p>
     * 
     * @return A copy of the {@link IInterpretationEnvironment}.
     */
    IInterpretationEnvironment createChildEnvironment();

    /**
     * <p>
     * Returns result for @pre values stored during a postconditions
     * preparation.
     * </p>
     * 
     * @param anOperationCallExp
     *            The {@link OperationCallExp} containing the @pre operation
     *            call whose value shall be returned.
     * 
     * @return The @pre value for the given {@link OperationCallExp}.
     */
    OclAny getAtPreValue(OperationCallExp anOperationCallExp);

    /**
     * <p>
     * Returns the {@link IModelInstance} of this
     * {@link IInterpretationEnvironment}.
     * </p>
     * 
     * @return The {@link IModelInstance} of this
     *         {@link IInterpretationEnvironment}.
     */
    IModelInstance getModelInstance();

    /**
     * <p>
     * Gets saved variables with given name.
     * </p>
     * 
     * @param identifier
     *            The identifier of the variable or simply the name (e.g.
     *            <code>self</code>).
     * 
     * @return Saved variables with given name.
     */
    OclAny getVariableValue(String identifier);

    /**
     * <p>
     * Checks whether or not a given {@link IModelInstanceObject} (represented
     * by an {@link OclModelInstanceObject}) existed before the execution of the
     * current interpreted postcondition. This method can be used to interpret
     * the <code>oclIsNew()</code> operation during the interpretation of a
     * postcondition.
     * </p>
     * 
     * @param source
     *            The {@link OclModelInstanceObject} for that the
     *            <code>oclIsNew()</code> operation shall be evaluated.
     * @return <code>true</code>, if the given {@link OclModelInstanceObject}
     *         did not exist before the operation invocation of the current
     *         interpreted postcondition.
     */
    boolean isNewInstance(OclModelInstanceObject source);

    /**
     * <p>
     * Saves value of an @pre {@link OperationCallExp} during preparation of
     * postconditions.
     * </p>
     * 
     * @param anOperationCallExp
     *            The {@link OperationCallExp} containing @pre Operation whose
     *            value shall be stored.
     * @param value
     *            The {@link OclAny} value to be stored.
     */
    public void saveAtPreValue(OperationCallExp anOperationCallExp, OclAny value);

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
     *            The {@link Type} for which the instances shall be stored.
     */
    void saveOldInstances(Type type);

    /**
     * <p>
     * Sets the {@link IModelInstance} of this
     * {@link IInterpretationEnvironment}.
     * </p>
     * 
     * @param modelInstance
     *            The {@link IModelInstance} of this
     *            {@link IInterpretationEnvironment}.
     */
    void setModelInstance(IModelInstance modelInstance);

    /**
     * <p>
     * Sets the {@link OclAny} value of a {@link Variable} visible in this
     * {@link IInterpretationEnvironment}. Since the OCL Abstract Syntax handles
     * parameter values as {@link Variable}s as well, they can be stored iusing
     * this method as well.
     * </p>
     * 
     * @param identifier
     *            The identifier of the variable or simply the name (e.g.
     *            <code>self</code>).
     * @param value
     *            The {@link Variable}'s value.
     */
    void setVariableValue(String identifier, OclAny value);
}