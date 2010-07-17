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
package tudresden.ocl20.pivot.interpreter.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange;
import tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.IfExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IterateExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp;
import tudresden.ocl20.pivot.essentialocl.expressions.LetExp;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.expressions.VariableExp;
import tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclComparable;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclModelInstanceObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.essentialocl.types.BagType;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.OrderedSetType;
import tudresden.ocl20.pivot.essentialocl.types.SequenceType;
import tudresden.ocl20.pivot.essentialocl.types.SetType;
import tudresden.ocl20.pivot.essentialocl.types.TypeType;
import tudresden.ocl20.pivot.interpreter.IInterpretationEnvironment;
import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.interpreter.IOclInterpreter;
import tudresden.ocl20.pivot.interpreter.OclInterpreterPlugin;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelinstancetype.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Feature;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.JavaStandardlibraryPlugin;

/**
 * <p>
 * The main class of the OCL Interpreter which interprets OCL constraints.
 * </p>
 * 
 * <p>
 * This class implements {@link ExpressionsSwitch} which traverses over
 * instances of EssentialOCL.
 * </p>
 * 
 * @author Ronny Brandt: Developed the very buggy first version.
 * @author Claas Wilke: Refactored the interpreter and fixed many bugs.
 * @author Michael Thiele: Helped with refactoring and implementation logic.
 */
public class OclInterpreter extends ExpressionsSwitch<OclAny> implements
		IOclInterpreter {

	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = OclInterpreterPlugin
			.getLogger(OclInterpreter.class);

	/**
	 * FIXME Claas: in future versions, this hard-coded reference should be
	 * replaced by something dynamic (registry lookup, etc.). Ask Micha, if
	 * already possible.
	 * 
	 * The {@link IStandardLibraryFactory} of this {@link IOclInterpreter}.
	 */
	protected IStandardLibraryFactory myStandardLibraryFactory = JavaStandardlibraryPlugin
			.getStandardLibraryFactory();

	/**
	 * Specifies if the current interpretation run is used to prepare a
	 * {@link Constraint}.
	 */
	protected boolean isPreparationRun = false;

	/**
	 * The {@link InterpretationEnvironment} to be used to store
	 * {@link Variable}s etc.
	 */
	protected IInterpretationEnvironment myEnvironment = new InterpretationEnvironment();;

	/** Offset used to hierarchically shift logging messages. */
	private String logOffset = "";

	/**
	 * The Stack is used to store local {@link IInterpretationEnvironment} used
	 * during method or property call interpretation. The local
	 * {@link IInterpretationEnvironment}s can contain {@link Variable}s that
	 * are not visible globally.
	 */
	private Stack<IInterpretationEnvironment> myEnvironmentStack = new Stack<IInterpretationEnvironment>();

	/**
	 * <p>
	 * Instantiates a new {@link OclInterpreter}.
	 * </p>
	 * 
	 * @param aModelInstance
	 *            The {@link IModelInstance} used during interpretation.
	 */
	public OclInterpreter(IModelInstance aModelInstance) {

		this.myEnvironment.setModelInstance(aModelInstance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IOclInterpreter#interpretConstraint
	 * (tudresden .ocl20.pivot.pivotmodel.Constraint,
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceElement)
	 */
	public IInterpretationResult interpretConstraint(Constraint constraint,
			IModelInstanceElement modelInstanceElement) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Entry interpretConstraint(constraint = " + constraint
					+ ", modelInstanceElement = " + modelInstanceElement + ")");
			this.pushLogOffset();
		}
		// no else.

		IInterpretationResult result;

		/*
		 * Check if the IModelInstanceElement is constrained by the constraint
		 * at all.
		 */
		if (this.isConstrained(modelInstanceElement, constraint)) {

			OclAny oclResult;
			OclAny context;

			/* Try to get the modelObject as OCL object. */
			if (modelInstanceElement != null) {
				context = myStandardLibraryFactory
						.createOclAny(modelInstanceElement);
			}

			else {
				context = null;
			}

			/* Add self variable to environment. */
			this.myEnvironment.addVar("self", context);

			/* Compute the result. */
			oclResult = this.interpretConstraint(constraint, context);

			result = new InterpretationResultImpl(modelInstanceElement,
					constraint, oclResult);

			OclInterpreterPlugin.getInterpreterRegistry()
					.fireInterpretationFinished(result);
		}

		else {
			result = null;
		}

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER
					.debug("Exit interpretConstraint(Constraint, IModelInstanceElement) - Result = "
							+ result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Interpret the given {@link Constraint} for the given {@link OclAny}.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} to be interpreted.
	 * @param context
	 *            The {@link OclAny} representing the current context.
	 * 
	 * @return The result of the interpretation as {@link OclAny}
	 */
	protected OclAny interpretConstraint(Constraint constraint, OclAny context) {

		OclAny result;

		this.isPreparationRun = false;

		/* Add self variable to environment. */
		this.myEnvironment.addVar("self", context);

		/* Compute the result. */
		EObject constraintSpecification;
		constraintSpecification = (EObject) constraint.getSpecification();

		result = this.doSwitch((EObject) constraintSpecification);

		return result;
	}

	/**
	 * <p>
	 * A helper method that checks if a given {@link IModelInstanceElement} is
	 * constrained by the context of a given {@link Constraint} and thus, if the
	 * {@link Constraint} can be interpreted for this
	 * {@link IModelInstanceElement}.
	 * </p>
	 * 
	 * @param modelInstanceElement
	 *            The {@link IModelInstanceElement} that shall be checked.
	 * @param constraint
	 *            The {@link Constraint} that shall be checked.
	 * @return <code>true</code> if the {@link Constraint} can be interpreted
	 *         for the given {@link IModelInstanceElement}.
	 */
	private boolean isConstrained(IModelInstanceElement modelInstanceElement,
			Constraint constraint) {

		boolean result;
		result = false;

		if (constraint.getDefinedFeature() != null) {
			result = modelInstanceElement.isKindOf((Type) constraint
					.getDefinedFeature().getOwner());
		}

		else if (constraint.getConstrainedElement().size() > 0) {

			for (ConstrainableElement constrainedElement : constraint
					.getConstrainedElement()) {

				if (constrainedElement instanceof Feature) {
					result |= modelInstanceElement
							.isKindOf((Type) ((Feature) constrainedElement)
									.getOwner());
				}

				else if (constrainedElement instanceof Type) {
					result |= modelInstanceElement
							.isKindOf((Type) constrainedElement);
				}

				if (result) {
					break;
				}
				// no else.
			}
			// end for.
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IOclInterpreter#interpretConstraints(
	 * java.util .Collection,
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceElement)
	 */
	public List<IInterpretationResult> interpretConstraints(
			Collection<Constraint> constraints,
			IModelInstanceElement modelInstanceElement) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Entry interpretConstraints(constraints = "
					+ constraints + ", modelInstanceElement = "
					+ modelInstanceElement + ")");
			this.pushLogOffset();
		}
		// no else.

		/* Check for valid parameters. */
		if (constraints == null) {
			String msg;
			msg = "Parameter 'constraints' must not be null.";

			LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}

		else if (modelInstanceElement == null) {
			String msg;
			msg = "Parameter 'modelInstanceElement' must not be null.";

			LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}
		// no else.

		List<IInterpretationResult> result;

		result = new ArrayList<IInterpretationResult>();

		for (Constraint aConstraint : constraints) {
			result.add(this.interpretConstraint(aConstraint,
					modelInstanceElement));
		}

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER
					.debug("Exit interpretConstraint(Collection<Constraint>, IModelInstanceElement) - Result = "
							+ result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IOclInterpreter#interpretPreConditions
	 * (tudresden .ocl20.pivot.modelbus.IModelObject,
	 * tudresden.ocl20.pivot.pivotmodel.Operation,
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceElement[],
	 * java.util.Collection)
	 */
	public List<IInterpretationResult> interpretPreConditions(
			IModelInstanceElement modelInstanceElement, Operation operation,
			IModelInstanceElement[] parameterValues,
			Collection<Constraint> preConditions) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Entry interpretConstraints(modelInstanceElement = "
					+ modelInstanceElement + ", operation = " + operation
					+ ", parameterValues = " + parameterValues
					+ ", preConditions = " + preConditions + ")");
			this.pushLogOffset();
		}
		// no else.

		List<IInterpretationResult> result;
		result = new ArrayList<IInterpretationResult>();

		/* Only interpret preconditions defined on the given operation. */
		for (Constraint aConstraint : preConditions) {

			if (aConstraint.getKind().equals(ConstraintKind.PRECONDITION)
					&& aConstraint.getConstrainedElement().contains(operation)) {

				/*
				 * Add the parameters of the Operation to the environment (they
				 * can be named different for each Constraint).
				 */
				this.addParametersToEnvironment(aConstraint, parameterValues);

				result.add(this.interpretConstraint(aConstraint,
						modelInstanceElement));

				/* Remove the parameters again. */
				this.removeParametersFromEnvironment(aConstraint);

			}
			// no else.
		}
		// end for.

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER
					.debug("Exit interpretConstraints(IModelInstanceElement, Operation, IModelInstanceElement[], Collection<Constraint>) - Result = "
							+ result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IOclInterpreter#interpretPostConditions
	 * (tudresden .ocl20.pivot.modelbus.IModelObject,
	 * tudresden.ocl20.pivot.pivotmodel.Operation,
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceElement[],
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceElement,
	 * java.util.Collection)
	 */
	public List<IInterpretationResult> interpretPostConditions(
			IModelInstanceElement modelInstanceElement, Operation operation,
			IModelInstanceElement[] parameterValues,
			IModelInstanceElement resultValue,
			Collection<Constraint> postConditions) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("Entry interpretPostConditions(modelInstanceElement = "
							+ modelInstanceElement
							+ ", operation = "
							+ operation
							+ ", parameterValues = "
							+ parameterValues
							+ ", resultValue = "
							+ resultValue
							+ ", preConditions = "
							+ postConditions + ")");
			this.pushLogOffset();
		}
		// no else.

		List<IInterpretationResult> result;

		result = new ArrayList<IInterpretationResult>();

		/* Add a local environment containing the result variable. */
		this.pushLocalEnvironment();

		/* Set the result variable to null or its result. */
		if (resultValue != null) {
			OclAny oclResult;
			oclResult = myStandardLibraryFactory.createOclAny(resultValue);

			this.myEnvironment.addVar(RESULT_VARIABLE_NAME, oclResult);
		}

		else {
			/* Reset result variable. */
			this.myEnvironment.addVar(RESULT_VARIABLE_NAME, null);
		}

		/* Only interpret postconditions defined on the given operation. */
		for (Constraint aConstraint : postConditions) {

			if (aConstraint.getKind().equals(ConstraintKind.POSTCONDITION)
					&& aConstraint.getConstrainedElement().contains(operation)) {

				/*
				 * Add the parameters of the Operation to the environment (they
				 * can be named different for each Constraint).
				 */
				this.addParametersToEnvironment(aConstraint, parameterValues);

				result.add(this.interpretConstraint(aConstraint,
						modelInstanceElement));

				/* Remove the parameters again. */
				this.removeParametersFromEnvironment(aConstraint);

			}
			// no else.
		}
		// end for.

		/* Remove the local environment containing the result variable. */
		this.popLocalEnvironment();

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER
					.debug("Exit interpretPostConditions(IModelInstanceElement, Operation, IModelInstanceElement[], IModelInstanceElement, Collection<Constraint>) - Result = "
							+ result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IOclInterpreter#preparePostConditions
	 * (tudresden
	 * .ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement,
	 * tudresden.ocl20.pivot.pivotmodel.Operation,
	 * tudresden.ocl20.pivot.modelbus
	 * .modelinstance.types.IModelInstanceElement[], java.util.Collection)
	 */
	public void preparePostConditions(
			IModelInstanceElement modelInstanceElement, Operation operation,
			IModelInstanceElement[] parameterValues,
			Collection<Constraint> postConditions) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Entry preparePostConditions(modelInstanceElement = "
					+ modelInstanceElement + ", operation = " + operation
					+ ", parameterValues = " + parameterValues
					+ ", preConditions = " + postConditions + ")");
			this.pushLogOffset();
		}
		// no else.

		/* Check for valid parameters. */
		if (modelInstanceElement == null) {
			String msg;
			msg = "Parameter 'modelInstanceElement' must not be null.";

			LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}

		else if (parameterValues == null) {
			String msg;
			msg = "Parameter 'parameterValues' must not be null.";

			LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}

		else if (operation == null) {
			String msg;
			msg = "Parameter 'operation' must not be null.";

			LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}

		else if (postConditions == null) {
			String msg;
			msg = "Parameter 'postConditions' must not be null.";

			LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}
		// no else.

		/* Iterate through the constraints. */
		for (Constraint aConstraint : postConditions) {

			/*
			 * Only prepare postconditions defined on the operation of the given
			 * context.
			 */
			if (aConstraint.getKind() == ConstraintKind.POSTCONDITION
					&& aConstraint.getConstrainedElement().contains(operation)) {

				/*
				 * Add the parameters of the Operation to the environment (they
				 * can be named different for each Constraint).
				 */
				this.addParametersToEnvironment(aConstraint, parameterValues);
				this.isPreparationRun = true;

				OclAny oclModelObject;
				EObject constraintSpecification;

				/* Try to get the modelObject as OCL object. */
				if (modelInstanceElement != null) {
					oclModelObject = myStandardLibraryFactory
							.createOclAny(modelInstanceElement);
				}

				else {
					oclModelObject = null;
				}

				/* Add self variable to environment. */
				this.myEnvironment.addVar(SELF_VARIABLE_NAME, oclModelObject);

				/* Prepare the constraintSpecification. */
				constraintSpecification = (EObject) aConstraint
						.getSpecification();
				this.doSwitch((EObject) constraintSpecification);

				/* Remove the self variable from the environment again. */
				this.myEnvironment.addVar(SELF_VARIABLE_NAME, null);

				/* Remove the parameters again. */
				this.isPreparationRun = false;
				this.removeParametersFromEnvironment(aConstraint);
			}
			// no else.
		}
		// end for.

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER
					.debug("Exit preparePostConditions(IModelInstanceElement, Operation, IModelInstanceElement[], Collection<Constraint>)");
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method which adds the parameters of an {@link Operation} as
	 * context of a {@link Constraint} that shall be interpreted to the current
	 * {@link IInterpretationEnvironment}.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} for which the arguments shall be
	 *            prepared.
	 * @param parameters
	 *            The parameters (as array of {@link IModelInstanceElement})
	 *            which shall be added.
	 */
	private void addParametersToEnvironment(Constraint aConstraint,
			IModelInstanceElement[] parameters) {

		ExpressionInOcl oclExpression;
		int index;

		oclExpression = (ExpressionInOcl) aConstraint.getSpecification();
		index = 0;

		/* Probably add parameters to the environment. */
		for (Variable aVariable : oclExpression.getParameter()) {

			if (parameters[index] != null) {
				this.myEnvironment.addVar(aVariable.getName(),
						this.myStandardLibraryFactory
								.createOclAny(parameters[index]));
			}

			else {
				String msg;
				OclAny value;

				msg = "Parameter '" + aVariable.getName() + "' was undefined";

				value = myStandardLibraryFactory.createOclUndefined(aVariable
						.getType(), msg);

				this.myEnvironment.addVar(aVariable.getName(), value);
			}

			index++;
		}
		// end for.
	}

	/**
	 * <p>
	 * A helper method which removes the parameters of an {@link Operation}
	 * which is the context of a {@link Constraint} from the
	 * {@link IInterpretationEnvironment} after its execution and
	 * interpretation.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} for which the arguments shall be
	 *            removed.
	 */
	private void removeParametersFromEnvironment(Constraint aConstraint) {

		ExpressionInOcl oclExpression;
		int index;

		oclExpression = (ExpressionInOcl) aConstraint.getSpecification();
		index = 0;

		/* Probably remove parameters from the environment. */
		for (Variable aVariable : oclExpression.getParameter()) {

			this.myEnvironment.addVar(aVariable.getName(), null);
			index++;
		}
		// end for.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.interpreter.IOclInterpreter#setEnviromentVariable
	 * (java. lang.String, tudresden.ocl20.pivot.modelbus.IModelInstanceElement)
	 */
	public void setEnviromentVariable(String name, IModelInstanceElement value) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "setEnviromentVariable(";
			msg += "name = " + name;
			msg += ", value = " + value;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclAny oclValue;
		oclValue = myStandardLibraryFactory.createOclAny(value);

		this.myEnvironment.addVar(name, oclValue);

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "setEnviromentVariable() - exit ";

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseBooleanLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp)
	 */
	@Override
	public OclAny caseBooleanLiteralExp(BooleanLiteralExp booleanLiteralExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret BooleanLiteral.");
		}
		// no else.

		/* Cache is not efficient here. */
		OclAny result;
		result = myStandardLibraryFactory.createOclBoolean(booleanLiteralExp
				.isBooleanSymbol());

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset
					+ "Interpreted BooleanLiteral. Result = " + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseCollectionItem
	 * (tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem)
	 */
	@Override
	public OclAny caseCollectionItem(CollectionItem collectionItem) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret CollectionItem.");
			this.pushLogOffset();
		}
		// no else.

		/* Collection items cannot be cached. */
		OclAny result = doSwitch((EObject) collectionItem.getItem());

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER.debug(this.logOffset
					+ "Interpreted CollectionItem. Result = " + result);
		}
		// no else.

		return result;
	}

	/*
	 * FIXME Michael: check, if collectionLiteralExp return CollectionType or
	 * the generic type of the collection (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseCollectionLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp)
	 */
	@Override
	public OclAny caseCollectionLiteralExp(
			CollectionLiteralExp collectionLiteralExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret CollectionLiteral.");
			this.pushLogOffset();
		}

		/*
		 * Collection literal cannot be cached - they are only defined once in a
		 * constraint.
		 */
		OclAny result;

		List<OclAny> resultList;
		resultList = new ArrayList<OclAny>();

		/* Iterate through all literal parts and compute their results. */
		for (CollectionLiteralPart part : collectionLiteralExp.getPart()) {

			/* Check if the part is a collection item. */
			if (part instanceof CollectionItem) {
				resultList.add(doSwitch((EObject) part));
			}

			/* Else the part must be a collection range. */
			else if (part instanceof CollectionRange) {

				OclInteger currentElement;
				OclInteger lastElement;

				/*
				 * Get the first and the last element of the collection range.
				 */
				currentElement = (OclInteger) doSwitch((EObject) ((CollectionRange) part)
						.getFirst());
				lastElement = (OclInteger) doSwitch((EObject) ((CollectionRange) part)
						.getLast());

				while (currentElement.isLessEqual(lastElement).isTrue()) {
					resultList.add(currentElement);
					currentElement = currentElement
							.add(myStandardLibraryFactory.createOclInteger(1L));
				}
				// end while.
			}
		}
		// end for.

		/* Create the result depending on the kind of given collection. */
		result = this.adaptResultListAsCollection(resultList,
				collectionLiteralExp.getType());

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER.debug(this.logOffset
					+ "Interpreted CollectionLiteral. Result = " + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseEnumLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp)
	 */
	@Override
	public OclAny caseEnumLiteralExp(EnumLiteralExp enumLiteralExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret EnumerationLiteral.");
		}
		// no else.

		/* Cache is not efficient here. */
		OclAny result;
		result = myStandardLibraryFactory.createOclEnumLiteral(enumLiteralExp
				.getReferredEnumLiteral());

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset
					+ "Interpreted EnumerationLiteral. Result = " + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseExpressionInOcl
	 * (tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl)
	 */
	@Override
	public OclAny caseExpressionInOcl(ExpressionInOcl expressionInOcl) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret ExpressionInOcl "
					+ expressionInOcl.getBody());
			this.pushLogOffset();
		}
		// no else.

		OclAny result;
		result = doSwitch((EObject) expressionInOcl.getBodyExpression());

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER.debug(this.logOffset
					+ "Interpreted ExpressionInOcl. Result = " + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIfExp(tudresden.ocl20.pivot.essentialocl.expressions.IfExp)
	 */
	@Override
	public OclAny caseIfExp(IfExp ifExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret If.");
			this.pushLogOffset();
		}
		// no else.

		/* If expressions cannot be cached. */
		OclAny result;

		/* Evaluate the condition. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Evaluate condition ... ");
		}
		// no else.

		OclAny condition;
		condition = doSwitch((EObject) ifExp.getCondition());

		if (condition instanceof OclBoolean) {
			OclBoolean booleanCondition;
			booleanCondition = (OclBoolean) condition;

			if (booleanCondition.oclIsInvalid().isTrue()) {
				result = myStandardLibraryFactory.createOclInvalid(ifExp
						.getType(), new IllegalArgumentException(
						"Condition of IfExpression was invalid.",
						booleanCondition.getInvalidReason()));
			}

			else if (booleanCondition.oclIsUndefined().isTrue()) {
				result = myStandardLibraryFactory.createOclInvalid(ifExp
						.getType(), new IllegalArgumentException(
						"Condition of IfExpression was undefined: "
								+ booleanCondition.getUndefinedReason()));
			}

			else if (booleanCondition.isTrue()) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(this.logOffset
							+ "Evaluate ThenExpression ... ");
				}
				// no else.
				result = doSwitch((EObject) ifExp.getThenExpression());
			}

			else {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(this.logOffset
							+ "Evaluate ElseExpression ... ");
				}
				// no else.
				result = doSwitch((EObject) ifExp.getElseExpression());
			}
		}

		else {
			String msg;
			msg = "Condition of IfExpression was not boolean.";

			result = this.myStandardLibraryFactory.createOclInvalid(ifExp
					.getType(), new IllegalStateException(msg));
		}

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER.debug(this.logOffset + "Interpreted If. Result = " + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIntegerLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp)
	 */
	public OclAny caseIntegerLiteralExp(IntegerLiteralExp integerLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret IntegerLiteral.");
		}
		// no else.

		/* Cache is not efficient here. */
		OclAny result;
		result = myStandardLibraryFactory.createOclInteger(new Long(
				integerLiteralExp.getIntegerSymbol()));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset
					+ "Interpreted IntegerLiteral. Result = " + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseInvalidLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp)
	 */
	@Override
	public OclAny caseInvalidLiteralExp(InvalidLiteralExp invalidLiteralExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret InvalidLiteral.");
		}
		// no else.

		OclAny result = null;
		result = myStandardLibraryFactory.createOclInvalid(invalidLiteralExp
				.getType(), new IllegalArgumentException(
				"Value was declared as invalid."));

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset
					+ "Interpreted InvalidLiteral. Result = " + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIterateExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IterateExp)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public OclAny caseIterateExp(IterateExp iterateExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret Iterate.");
			this.pushLogOffset();
		}
		// no else.

		/* IterateExpressions cannot be cached. */
		OclAny result;
		OclAny source;
		OclCollection<OclAny> sourceCollection;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Evaluate Source ...");
		}
		// no else.
		source = doSwitch((EObject) iterateExp.getSource());

		if (source instanceof OclCollection) {
			sourceCollection = (OclCollection<OclAny>) source;
		}

		else {
			sourceCollection = source.asSet();
		}

		this.pushLocalEnvironment();

		/* Reset the accumulator variable in the environment. */
		myEnvironment.addVar(iterateExp.getResult().getQualifiedName(), null);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Evaluate Body ...");
		}
		// no else.
		result = evaluateIterate(iterateExp.getBody(), sourceCollection,
				iterateExp.getIterator(), sourceCollection.getIterator(),
				iterateExp.getResult());
		this.popLocalEnvironment();

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER.debug(this.logOffset + "Interpreted Iterate. Result = "
					+ result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Evaluates the general iterate method. Will be invoked recursively for
	 * every iterator variable of the iteration.
	 * </p>
	 * 
	 * @param bodyExpression
	 *            The body {@link OclExpression} of the {@link IterateExp}.
	 * @param source
	 *            The {@link OclCollection} representing the source of the
	 *            iteration.
	 * @param iteratorVariables
	 *            The variables representing the iterators of the
	 *            {@link IterateExp} .
	 * @param iterator
	 *            The current iterator on source {@link OclExpression} (is given
	 *            to support recursive call with multiple iterators).
	 * @param resultVar
	 *            The {@link Variable} containing the result of the iteration.
	 * 
	 * @return the result of the iteration.
	 */
	protected OclAny evaluateIterate(OclExpression bodyExpression,
			OclCollection<OclAny> source, List<Variable> iteratorVariables,
			OclIterator<OclAny> iterator, Variable resultVariable) {

		OclAny result;

		/* Check if iterator is undefined. */
		if (iterator.hasNext().oclIsInvalid().isTrue()) {
			result = myStandardLibraryFactory.createOclInvalid(bodyExpression
					.getType(), new IllegalArgumentException(
					"Source of iterate expression was invalid.", iterator
							.hasNext().getInvalidReason()));
		}

		/* Else compute the iteration. */
		else {
			/* First, evaluate the result variable. */
			result = this.caseVariable(resultVariable);

			/* Iterate through the iterator. */
			while (iterator.hasNext().isTrue()) {

				OclAny activeElement;

				/* Get the next element. */
				activeElement = iterator.next();

				/* Add the elements variable to the environment. */
				myEnvironment.addVar(iteratorVariables.get(0)
						.getQualifiedName(), activeElement);

				/*
				 * If more than one iterators are used, remove the first
				 * iterator and recall this method recursively.
				 */
				if (iteratorVariables.size() > 1) {
					List<Variable> allIterators;
					OclIterator<OclAny> nextIt;

					allIterators = new ArrayList<Variable>(iteratorVariables);
					allIterators.remove(0);

					nextIt = (OclIterator<OclAny>) source.getIterator();

					result = evaluateIterate(bodyExpression, source,
							allIterators, nextIt, resultVariable);
				}

				/* Else compute the result. */
				else {
					result = doSwitch((EObject) bodyExpression);
				}

				/* Add the result to the environment. */
				myEnvironment.addVar(resultVariable.getQualifiedName(), result);
			}
			// end while.
		}
		// end else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIteratorExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp)
	 */
	@SuppressWarnings("unchecked")
	public OclAny caseIteratorExp(IteratorExp iteratorExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret Iterator "
					+ iteratorExp.getName() + ".");
			this.pushLogOffset();
		}
		// no else.

		/* IteratorExpressions cannot be cached. */
		OclAny result;

		List<Variable> allIteratorVariables;
		allIteratorVariables = iteratorExp.getIterator();

		/* Compute the result of the source. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Evaluate Source ...");
		}
		// no else.
		OclAny source;
		source = doSwitch((EObject) iteratorExp.getSource());

		/* Probably convert source to a set. */
		OclCollection<OclAny> sourceCollection;

		if (source instanceof OclCollection) {
			sourceCollection = (OclCollection<OclAny>) source;
		}

		else {
			sourceCollection = source.asSet();
		}

		OclIterator<OclAny> sourceIterator;
		sourceIterator = (OclIterator<OclAny>) sourceCollection.getIterator();

		OclExpression bodyExpression;
		bodyExpression = iteratorExp.getBody();

		String iteratorName;
		iteratorName = iteratorExp.getName();

		Type resultType;
		resultType = iteratorExp.getType();

		/* Check which type of iterator shall be used. */
		if (iteratorExp.getName().equals("any")) {

			/* Any can only use one iterator variable. */
			if (allIteratorVariables.size() > 1) {
				String msg;
				msg = "Iterator any() may have at most one iterator variable.";

				if (LOGGER.isInfoEnabled()) {
					LOGGER.warn(msg);
				}
				// no else.

				result = myStandardLibraryFactory.createOclInvalid(iteratorExp
						.getType(), new IllegalArgumentException(msg));
			}

			else {
				result = this.evaluateAny(bodyExpression, sourceCollection,
						allIteratorVariables.get(0), iteratorExp.getType());
			}
		}

		else if (iteratorExp.getName().equals("collect")) {

			/* Collect can only use one iterator variable. */
			if (allIteratorVariables.size() > 1) {
				String msg;

				msg = "Iterator collect() may have at most one iterator variable.";

				if (LOGGER.isInfoEnabled()) {
					LOGGER.warn(msg);
				}
				// no else.

				result = myStandardLibraryFactory.createOclInvalid(iteratorExp
						.getType(), new IllegalArgumentException(msg));
			}

			else {
				result = this.evaluateCollectNested(bodyExpression,
						sourceCollection, allIteratorVariables.get(0),
						resultType);

				/* Flatten the result. */
				if (result instanceof OclCollection) {
					result = ((OclCollection) result).flatten();
				}

				else {
					result.asSet().flatten();
				}
			}
		}

		else if (iteratorExp.getName().equals("collectNested")) {

			/* CollectNested can only use one iterator variable. */
			if (allIteratorVariables.size() > 1) {
				String msg;
				msg = "Iterator collectNested() may have at most one iterator variable.";

				if (LOGGER.isInfoEnabled()) {
					LOGGER.warn(msg);
				}
				// no else.

				result = myStandardLibraryFactory.createOclInvalid(iteratorExp
						.getType(), new IllegalArgumentException(msg));
			}

			else {
				result = this.evaluateCollectNested(bodyExpression,
						sourceCollection, allIteratorVariables.get(0),
						resultType);
			}
		}

		else if (iteratorName.equals("exists")) {
			result = this.evaluateExists(bodyExpression, sourceCollection,
					allIteratorVariables, sourceIterator);
		}

		else if (iteratorExp.getName().equals("forAll")) {
			result = this.evaluateForAll(bodyExpression, sourceCollection,
					allIteratorVariables, sourceIterator);

		}

		else if (iteratorExp.getName().equals("isUnique")) {

			/* IsUnique can only use one iterator variable. */
			if (allIteratorVariables.size() > 1) {
				String msg;
				msg = "Iterator isUnique() may have at most one iterator variable.";

				if (LOGGER.isInfoEnabled()) {
					LOGGER.warn(msg);
				}
				// no else.

				result = myStandardLibraryFactory.createOclInvalid(iteratorExp
						.getType(), new IllegalArgumentException(msg));
			}

			else {
				result = this.evaluateIsUnique(bodyExpression,
						sourceCollection, allIteratorVariables.get(0));
			}
		}

		else if (iteratorExp.getName().equals("one")) {

			/* One can only use one iterator variable. */
			if (allIteratorVariables.size() > 1) {
				String msg;
				msg = "Iterator one() may have at most one iterator variable.";

				if (LOGGER.isInfoEnabled()) {
					LOGGER.warn(msg);
				}
				// no else.

				result = myStandardLibraryFactory.createOclInvalid(iteratorExp
						.getType(), new IllegalArgumentException(msg));
			}

			else {
				result = this.evaluateOne(bodyExpression, sourceCollection,
						allIteratorVariables.get(0));
			}
		}

		else if (iteratorExp.getName().equals("reject")) {

			/* Reject can only use one iterator variable. */
			if (allIteratorVariables.size() > 1) {
				String msg;
				msg = "Iterator reject() may have at most one iterator variable.";

				if (LOGGER.isInfoEnabled()) {
					LOGGER.warn(msg);
				}
				// no else.

				result = myStandardLibraryFactory.createOclInvalid(iteratorExp
						.getType(), new IllegalArgumentException(msg));
			}

			else {
				result = this.evaluateReject(bodyExpression, sourceCollection,
						allIteratorVariables.get(0), resultType);
			}
		}

		else if (iteratorName.equals("select")) {

			/* Select can only use one iterator variable. */
			if (allIteratorVariables.size() > 1) {
				String msg;
				msg = "Iterator select() may have at most one iterator variable.";

				if (LOGGER.isInfoEnabled()) {
					LOGGER.warn(msg);
				}
				// no else.

				result = myStandardLibraryFactory.createOclInvalid(iteratorExp
						.getType(), new IllegalArgumentException(msg));
			}

			else {
				result = this.evaluateSelect(bodyExpression, sourceCollection,
						allIteratorVariables.get(0), resultType);
			}
		}

		else if (iteratorExp.getName().equals("sortedBy")) {

			/* SortedBy can only use one iterator variable. */
			if (allIteratorVariables.size() > 1) {
				String msg;
				msg = "Iterator sortedBy() may have at most one iterator variable.";

				if (LOGGER.isInfoEnabled()) {
					LOGGER.warn(msg);
				}
				// no else.

				result = myStandardLibraryFactory.createOclInvalid(iteratorExp
						.getType(), new IllegalArgumentException(msg));
			}

			else {
				result = this.evaluateSortedBy(bodyExpression,
						sourceCollection, allIteratorVariables.get(0),
						resultType);
			}
		}

		/* Else result in invalid. */
		else {
			String msg;
			msg = "Unknown iterator " + iteratorExp.getName()
					+ ". Was not able to interpret result.";

			if (LOGGER.isInfoEnabled()) {
				LOGGER.warn(msg);
			}
			// no else.

			result = myStandardLibraryFactory.createOclInvalid(iteratorExp
					.getType(), new IllegalArgumentException(msg));
		}
		// no else.

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER.debug(this.logOffset + "Interpreted Iterator "
					+ iteratorExp.getName() + ". Result = " + result);
		}
		// no else.

		return result;
	}

	/**
	 * Returns any element in the source collection for which body evaluates to
	 * true. If there is more than one element for which body is true, one of
	 * them is returned. There must be at least one element fulfilling body,
	 * otherwise the result of this IteratorExp is null.
	 * 
	 * @param body
	 *            the body expression to be evaluated
	 * @param source
	 *            the collection representing the source expression of the
	 *            iteration
	 * @param iterator
	 *            the iterator (any may have at most one iterator variable.)
	 * @param resultType
	 *            The result {@link Type} of this any iterator expression.
	 * 
	 * @return the result of the iteration
	 */
	protected OclAny evaluateAny(OclExpression body,
			OclCollection<OclAny> source, Variable iterator, Type resultType) {

		OclAny result;
		OclIterator<OclAny> sourceIt;

		sourceIt = source.getIterator();

		/* Check if iterator is undefined. */
		if (sourceIt.hasNext().oclIsInvalid().isTrue()) {
			result = myStandardLibraryFactory.createOclInvalid(source
					.getGenericType(), new IllegalArgumentException(
					"Source of iterator any() was invalid.", sourceIt.hasNext()
							.getInvalidReason()));
		}

		/* Else compute the result. */
		else {

			result = null;

			/*
			 * Iterate over the source and evaluate the body expression for all
			 * elements. If it is true for any element, the result the specific
			 * element.
			 */
			while (sourceIt.hasNext().isTrue()) {

				OclAny anElement;
				OclBoolean bodyResult;

				/* Add an element to the environment. */
				anElement = sourceIt.next();
				this.myEnvironment.addVar(iterator.getQualifiedName(),
						anElement);

				/* Compute the body result. */
				bodyResult = (OclBoolean) doSwitch((EObject) body);

				/* Remove the variable from the environment again. */
				this.myEnvironment.addVar(iterator.getQualifiedName(), null);

				/* Probably result in invalid. */
				if (result == null && bodyResult.oclIsInvalid().isTrue()) {
					result = this.myStandardLibraryFactory
							.createOclInvalid(
									source.getGenericType(),
									new IllegalArgumentException(
											"Body-Expression of iterator any() was invalid for at least on element and no other element fulfilling the body condition could be found.",
											bodyResult.getInvalidReason()));
					/*
					 * Do not break. Probably a valid result will be found in
					 * the following.
					 */
				}

				else if (result == null && bodyResult.oclIsUndefined().isTrue()) {
					result = this.myStandardLibraryFactory
							.createOclInvalid(
									source.getGenericType(),
									new IllegalArgumentException(
											"Body-Expression of iterator any() was undefined for at least on element and no other element fulfilling the body condition could be found."));
					/*
					 * Do not break. Probably a valid result will be found in
					 * the following.
					 */
				}

				/* Probably break iteration. */
				else if (!bodyResult.oclIsInvalid().isTrue()
						&& !bodyResult.oclIsUndefined().isTrue()
						&& bodyResult.isTrue()) {
					result = anElement;
					break;
				}
				// no else.
			}
			// end while.
		}
		// end else.

		/* Probably result in undefined. */
		if (result == null) {
			String msg;

			msg = "Iterator any() resulted in undefined.";
			result = myStandardLibraryFactory.createOclUndefined(resultType,
					msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * The collection of elements which results from applying body to every
	 * member of the source set.
	 * </p>
	 * 
	 * @param body
	 *            The body expression to be evaluated.
	 * @param source
	 *            The collection representing the source expression of the
	 *            iteration.
	 * @param iterator
	 *            The iterator (collectNested may have at most one iterator
	 *            variable.).
	 * @param resultType
	 *            The result type (set, sequence, bag, orderedSet).
	 * 
	 * @return The result of the iteration.
	 */
	protected OclAny evaluateCollectNested(OclExpression body,
			OclCollection<OclAny> source, Variable iterator, Type resultType) {

		OclAny result;

		List<OclAny> resultList;
		OclIterator<OclAny> sourceIt;

		resultList = new ArrayList<OclAny>();
		sourceIt = source.getIterator();

		/* Check if iterator is undefined. */
		if (sourceIt.hasNext().oclIsInvalid().isTrue()) {
			result = myStandardLibraryFactory.createOclInvalid(source
					.getGenericType(), new IllegalArgumentException(
					"Source of iterator collectNested() was invalid.", sourceIt
							.hasNext().getInvalidReason()));
		}

		/* Else compute the result. */
		else {

			/*
			 * Iterate over the source and collect the body expression for all
			 * elements.
			 */
			while (sourceIt.hasNext().isTrue()) {

				OclAny anElement;
				OclAny bodyResult;

				/* Get the next element and add it to the environment. */
				anElement = sourceIt.next();
				myEnvironment.addVar(iterator.getQualifiedName(), anElement);

				/* Compute the body expression for an element. */
				bodyResult = doSwitch((EObject) body);

				resultList.add(bodyResult);
			}
			// end while.

			/* Compute the result type depending on the given result type. */
			if (resultType instanceof BagType) {
				result = myStandardLibraryFactory.createOclBag(resultList,
						((BagType) resultType).getElementType());
			}

			else if (resultType instanceof SequenceType) {
				result = myStandardLibraryFactory.createOclSequence(resultList,
						((SequenceType) resultType).getElementType());
			}

			else {
				String msg;
				msg = "The ResultType of a collectNested Iterator should by a Sequence or Bag.";
				msg += " But was " + resultType.getQualifiedName();

				if (LOGGER.isInfoEnabled()) {
					LOGGER.warn(msg);
				}
				// no else.

				result = myStandardLibraryFactory.createOclInvalid(resultType,
						new IllegalArgumentException(msg));
			}
			// end else.
		}
		// end else.

		return result;
	}

	/**
	 * Results in true if body evaluates to true for at least one element in the
	 * source collection.
	 * 
	 * @param body
	 *            the body expression to be evaluated
	 * @param source
	 *            the collection representing the source expression of the
	 *            iteration
	 * @param iterators
	 *            the iterators
	 * @param it
	 *            the current iterator for the source collection
	 * 
	 * @return the result of the iteration
	 */
	protected OclAny evaluateExists(OclExpression body,
			OclCollection<OclAny> source, List<Variable> iterators,
			OclIterator<OclAny> it) {

		OclBoolean result;

		/* By default the result is false. */
		result = myStandardLibraryFactory.createOclBoolean(false);

		/* Check if iterator is undefined. */
		if (it.hasNext().oclIsInvalid().isTrue()) {
			result = myStandardLibraryFactory.createOclInvalid(
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getOclBoolean(), new IllegalArgumentException(
							"Source of iterator exists() was invalid.", it
									.hasNext().getInvalidReason()));
		}

		/* Else compute the result. */
		else {
			/*
			 * Iterate over the collection and check if at least one element
			 * fulfills the body expression.
			 */
			while (it.hasNext().isTrue()) {

				OclAny anElement;
				OclBoolean bodyResult;

				/* Add an element to the environment... */
				anElement = it.next();
				myEnvironment.addVar(iterators.get(0).getQualifiedName(),
						anElement);

				/* ...and compute its body expression. */
				bodyResult = null;

				/*
				 * Probably recall this method recursively for more iterator
				 * variables.
				 */
				if (iterators.size() > 1) {

					List<Variable> tempItList;
					OclIterator<OclAny> nextIt;

					/*
					 * Remove the firs iterator variable and recall recursively
					 * this method for all other iterator variables.
					 */
					tempItList = new ArrayList<Variable>(iterators);
					tempItList.remove(0);

					nextIt = source.getIterator();
					bodyResult = (OclBoolean) evaluateExists(body, source,
							tempItList, nextIt);
				}

				else {
					bodyResult = (OclBoolean) doSwitch((EObject) body);
				}

				/* Remove the variable from the environment again. */
				this.myEnvironment.addVar(iterators.get(0).getQualifiedName(),
						null);

				result = result.or(bodyResult);

				/* Probably break iteration. */
				if (!result.oclIsInvalid().isTrue()
						&& !result.oclIsUndefined().isTrue() && result.isTrue()) {
					break;
				}
				// no else.
			}
			// end while.
		}
		// end else.

		return result;
	}

	/**
	 * Results in true if the body expression evaluates to true for each element
	 * in the source collection; otherwise, result is false.
	 * 
	 * @param body
	 *            the body expression to be evaluated
	 * @param source
	 *            the collection representing the source expression of the
	 *            iteration
	 * @param iterators
	 *            the iterators
	 * @param it
	 *            the current iterator for the source collection
	 * 
	 * @return the result of the iteration
	 */
	protected OclAny evaluateForAll(OclExpression body,
			OclCollection<OclAny> source, List<Variable> iterators,
			OclIterator<OclAny> it) {

		OclBoolean result;

		/* By default the result is true. */
		result = myStandardLibraryFactory.createOclBoolean(true);

		/* Check if iterator is undefined. */
		if (it.hasNext().oclIsInvalid().isTrue()) {
			result = myStandardLibraryFactory.createOclInvalid(
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getOclBoolean(), new IllegalArgumentException(
							"Source of iterator forAll() was invalid.", it
									.hasNext().getInvalidReason()));
		}

		else {

			/* Iterate through the collection. */
			while (it.hasNext().isTrue()) {

				OclAny anItVariable;
				OclBoolean bodyResult;

				/* Get an iterator variable and add it to the environment. */
				anItVariable = it.next();
				this.myEnvironment.addVar(iterators.get(0).getQualifiedName(),
						anItVariable);

				bodyResult = null;

				/*
				 * Check if more than this iterator variables are available and
				 * Probably add them to the environment and compute the result
				 * recursively.
				 */
				if (iterators.size() > 1) {
					List<Variable> subIteratorList;
					OclIterator<OclAny> nextIt;

					subIteratorList = new ArrayList<Variable>(iterators);

					/* Remove the actual iterator. */
					subIteratorList.remove(0);

					/* Get the next iterator and compute the result recursively. */
					nextIt = source.getIterator();
					bodyResult = (OclBoolean) evaluateForAll(body, source,
							subIteratorList, nextIt);
				}

				/*
				 * Else compute the result for this iterator variable and all
				 * iterator variables which were set recursively before.
				 */
				else {
					bodyResult = (OclBoolean) doSwitch((EObject) body);
				}

				/* Remove the variable from the environment again. */
				this.myEnvironment.addVar(iterators.get(0).getQualifiedName(),
						null);

				result = result.and(bodyResult);

				/* Probably break iteration. */
				if (result.oclIsInvalid().isTrue()
						|| result.oclIsUndefined().isTrue() || !result.isTrue()) {
					break;
				}
			}
			// end while.
		}
		// end else.

		return result;
	}

	/**
	 * <p>
	 * Results in true if body evaluates to a different value for each element
	 * in the source collection; otherwise, result is false.
	 * </p>
	 * 
	 * @param body
	 *            the body expression to be evaluated
	 * @param source
	 *            the collection representing the source expression of the
	 *            iteration
	 * @param iterator
	 *            the iterator (isUnique may have at most one iterator
	 *            variable.)
	 * 
	 * @return the result of the iteration
	 */
	protected OclAny evaluateIsUnique(OclExpression body,
			OclCollection<OclAny> source, Variable iterator) {

		OclAny result;
		OclIterator<OclAny> sourceIt;

		sourceIt = source.getIterator();

		/* Check if iterator is undefined. */
		if (sourceIt.hasNext().oclIsInvalid().isTrue()) {
			result = myStandardLibraryFactory.createOclInvalid(
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getOclBoolean(), new IllegalArgumentException(
							"Source of iterator isUnique() was invalid.",
							sourceIt.hasNext().getInvalidReason()));
		}

		else {
			/* By default, the result is true. */
			result = myStandardLibraryFactory.createOclBoolean(true);

			List<OclAny> resultList;
			resultList = new ArrayList<OclAny>();

			/* Iterate over the collection and check if every element is unique. */
			while (sourceIt.hasNext().isTrue()) {

				OclAny anElement;
				OclAny bodyResult;

				anElement = sourceIt.next();

				/* Add the element to the environment. */
				this.myEnvironment.addVar(iterator.getQualifiedName(),
						anElement);

				/* Compute the body for the set environment. */
				bodyResult = doSwitch((EObject) body);

				/* Remove the variable from the environment again. */
				this.myEnvironment.addVar(iterator.getQualifiedName(), null);

				/* Check if the result is invalid. */
				if (bodyResult.oclIsInvalid().isTrue()) {
					result = this.myStandardLibraryFactory.createOclInvalid(
							EssentialOclPlugin.getOclLibraryProvider()
									.getOclLibrary().getOclBoolean(),
							bodyResult.getInvalidReason());
					break;
				}

				/* Check if the result is undefined. */
				else if (bodyResult.oclIsUndefined().isTrue()) {
					result = this.myStandardLibraryFactory
							.createOclInvalid(
									EssentialOclPlugin.getOclLibraryProvider()
											.getOclLibrary().getOclBoolean(),
									new IllegalArgumentException(
											"Cannot determine iterator isUnique on Collection containing undefined values."));
					break;
				}

				/* Else check if the result is not unique. */
				else if (resultList.contains(bodyResult)) {
					result = myStandardLibraryFactory.createOclBoolean(false);
					break;
				}

				/* Else continue iteration. */
				else {
					resultList.add(bodyResult);
				}
			}
			// end while.
		}
		// end else.

		return result;
	}

	/**
	 * Results in true if there is exactly one element in the source collection
	 * for which body is true.
	 * 
	 * @param body
	 *            the body expression to be evaluated
	 * @param source
	 *            the collection representing the source expression of the
	 *            iteration
	 * @param iterator
	 *            the iterator (one may have at most one iterator variable.)
	 * 
	 * @return the result of the iteration
	 */
	protected OclAny evaluateOne(OclExpression body,
			OclCollection<OclAny> source, Variable iterator) {

		OclAny result;

		OclIterator<OclAny> sourceIt;

		sourceIt = source.getIterator();

		/* Check if iterator is undefined. */
		if (sourceIt.hasNext().oclIsInvalid().isTrue()) {
			result = myStandardLibraryFactory.createOclInvalid(
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getOclBoolean(), new IllegalArgumentException(
							"Source of iterator one() was invalid.", sourceIt
									.hasNext().getInvalidReason()));
		}

		/* Else compute the result. */
		else {
			OclAny failedBodyResult;
			failedBodyResult = null;
			int validElements = 0;

			result = null;

			/*
			 * Iterate through the source and check if exactly one element
			 * fulfills the body condition.
			 */
			while (sourceIt.hasNext().isTrue()) {

				OclAny anElement;
				OclBoolean bodyResult;

				/*
				 * Add the element to the environment and compute the body
				 * result.
				 */
				anElement = sourceIt.next();
				this.myEnvironment.addVar(iterator.getQualifiedName(),
						anElement);

				bodyResult = (OclBoolean) doSwitch((EObject) body);

				/* Remove the variable from the environment again. */
				this.myEnvironment.addVar(iterator.getQualifiedName(), null);

				/* Register if body is invalid. */
				if (failedBodyResult == null
						&& bodyResult.oclIsInvalid().isTrue()) {
					failedBodyResult = bodyResult;
				}

				/* Register if body is undefined. */
				else if (failedBodyResult == null
						&& bodyResult.oclIsUndefined().isTrue()) {
					failedBodyResult = bodyResult;
				}

				/* Else probably count the elements. */
				else if (!bodyResult.oclIsInvalid().isTrue()
						&& !bodyResult.oclIsUndefined().isTrue()
						&& bodyResult.isTrue()) {

					validElements++;

					/* If alreadyFoundAnElement, break and return false. */
					if (validElements > 1) {
						break;
					}
					// no else.
				}
				// no else.
			}
			// end while.

			/* Create the result. */
			if (result == null) {

				/*
				 * If more than one elements fulfilled the condition, return
				 * false.
				 */
				if (validElements > 1) {
					result = myStandardLibraryFactory.createOclBoolean(false);
				}

				/*
				 * If body failed for some elements and only one or zero
				 * elements fulfilled the condition, fail.
				 */
				else if (failedBodyResult != null) {

					if (failedBodyResult.oclIsInvalid().isTrue()) {
						result = this.myStandardLibraryFactory
								.createOclInvalid(
										EssentialOclPlugin
												.getOclLibraryProvider()
												.getOclLibrary()
												.getOclBoolean(),
										new IllegalArgumentException(
												"Cannot determine result of iterator one() if body expression is invalid for at least one element and less than two elements fulfill the body expression.",
												failedBodyResult
														.getInvalidReason()));
					}

					else {
						result = this.myStandardLibraryFactory
								.createOclInvalid(
										EssentialOclPlugin
												.getOclLibraryProvider()
												.getOclLibrary()
												.getOclBoolean(),
										new IllegalArgumentException(
												"Cannot determine result of iterator one() if body expression is undefined for at least one element and less than two elements fulfill the body expression."));
					}
				}

				/* Else check the found elements fulfilling the condition. */
				else {
					result = myStandardLibraryFactory
							.createOclBoolean(validElements == 1);
				}
				// end else.
			}
			// no else.
		}
		// end else.

		return result;
	}

	/**
	 * The sub collection of source for which body is false.
	 * 
	 * @param body
	 *            the body expression to be evaluated
	 * @param source
	 *            the collection representing the source expression of the
	 *            iteration
	 * @param iterator
	 *            the iterator (reject may have at most one iterator variable.)
	 * @param resultType
	 *            the result type (set, sequence, bag, orderedSet)
	 * 
	 * @return the result of the iteration
	 */
	protected OclAny evaluateReject(OclExpression body,
			OclCollection<OclAny> source, Variable iterator, Type resultType) {

		OclAny result;
		result = null;

		List<OclAny> resultList = new ArrayList<OclAny>();
		OclIterator<OclAny> it = source.getIterator();

		/* Check if iterator is undefined. */
		if (it.hasNext().oclIsInvalid().isTrue()) {
			result = myStandardLibraryFactory.createOclInvalid(source
					.getGenericType(), new IllegalArgumentException(
					"Source of iterator reject() was invalid.", it.hasNext()
							.getInvalidReason()));
		}

		/* Else compute the result. */
		else {
			/*
			 * Iterate over the collection and add all elements to the result
			 * list which do not fulfill the body condition.
			 */
			while (it.hasNext().isTrue()) {

				OclAny anElement;
				OclBoolean bodyResult;

				/* Add the actual element to the environment. */
				anElement = it.next();
				myEnvironment.addVar(iterator.getQualifiedName(), anElement);

				/* Compute the body expression. */
				bodyResult = (OclBoolean) doSwitch((EObject) body);

				/* Probably result in invalid. */
				if (bodyResult.oclIsInvalid().isTrue()) {
					result = this.myStandardLibraryFactory
							.createOclInvalid(
									source.getGenericType(),
									new IllegalArgumentException(
											"During reject() iteration, body expression was invalid for at least one element.",
											bodyResult.getInvalidReason()));
					break;
				}

				else if (bodyResult.oclIsUndefined().isTrue()) {
					result = this.myStandardLibraryFactory
							.createOclInvalid(
									source.getGenericType(),
									new IllegalArgumentException(
											"During reject() iteration, body expression was undefined for at least one element."));
					break;
				}

				/*
				 * Else add the element to the result list if the body result is
				 * not true.
				 */
				else if (!bodyResult.isTrue()) {
					resultList.add(anElement);
				}
				// no else.
			}
			// end while.

			/* Probably adapt the result list. */
			if (result == null) {
				result = this.adaptResultListAsCollection(resultList,
						resultType);
			}
			// no else.
		}
		// end else.

		return result;
	}

	/**
	 * <p>
	 * The sub collection of source for which body is true.
	 * </p>
	 * 
	 * @param body
	 *            the body expression to be evaluated
	 * @param source
	 *            the collection representing the source expression of the
	 *            iteration
	 * @param iterator
	 *            the iterator (select may have at most one iterator variable.)
	 * @param resultType
	 *            the result type (set, sequence, bag, orderedSet)
	 * 
	 * @return the result of the iteration
	 */
	protected OclAny evaluateSelect(OclExpression body,
			OclCollection<OclAny> source, Variable iterator, Type resultType) {

		OclAny result;
		result = null;

		OclIterator<OclAny> it = source.getIterator();
		List<OclAny> resultList = new ArrayList<OclAny>();

		/* Check if iterator is undefined. */
		if (it.hasNext().oclIsInvalid().isTrue()) {
			result = myStandardLibraryFactory.createOclInvalid(source
					.getGenericType(), new IllegalArgumentException(
					"Source of iterator select() was invalid.", it.hasNext()
							.getInvalidReason()));
		}

		/* Else compute the result. */
		else {
			/* Iterate over the collection. */
			while (it.hasNext().isTrue()) {

				OclAny anElement;
				OclBoolean bodyResult;

				/* Add an element to the environment. */
				anElement = it.next();
				myEnvironment.addVar(iterator.getQualifiedName(), anElement);

				/* Compute the body expression for an element. */
				bodyResult = (OclBoolean) doSwitch((EObject) body);

				/* Probably result in invalid. */
				if (bodyResult.oclIsInvalid().isTrue()) {
					result = this.myStandardLibraryFactory
							.createOclInvalid(
									source.getGenericType(),
									new IllegalArgumentException(
											"During select() iteration, body expression was invalid for at least one element.",
											bodyResult.getInvalidReason()));
					break;
				}

				else if (bodyResult.oclIsUndefined().isTrue()) {
					result = this.myStandardLibraryFactory
							.createOclInvalid(
									source.getGenericType(),
									new IllegalArgumentException(
											"During select() iteration, body expression was undefined for at least one element."));
					break;
				}

				/*
				 * Else add the element to the result list if the body result is
				 * true.
				 */
				else if (bodyResult.isTrue()) {
					resultList.add(anElement);
				}
				// no else.
			}
			// end while.

			/* Probably adapt the result list. */
			if (result == null) {
				result = this.adaptResultListAsCollection(resultList,
						resultType);
			}
			// no else.
		}
		// end else.

		return result;
	}

	/**
	 * Results in the sorted collection containing all elements of the source
	 * collection. The element for which body has the lowest value comes first,
	 * and so on. The type of the body expression must have the < operation
	 * defined. The < operation must return a Boolean value and must be
	 * transitive (i.e., if a < b and b < c then a < c).
	 * 
	 * @param body
	 *            the body expression to be evaluated
	 * @param source
	 *            the collection representing the source expression of the
	 *            iteration
	 * @param iterator
	 *            the iterator (sortedBy may have at most one iterator
	 *            variable.)
	 * @param resultType
	 *            the result type (set, sequence, bag, orderedSet)
	 * 
	 * @return the result of the iteration
	 */
	protected OclAny evaluateSortedBy(OclExpression body,
			OclCollection<OclAny> source, Variable iterator, Type resultType) {

		OclAny result;
		result = null;

		OclIterator<OclAny> collectionIt;
		collectionIt = source.getIterator();

		/* Check if iterator is undefined. */
		if (collectionIt.hasNext().oclIsInvalid().isTrue()) {
			result = myStandardLibraryFactory.createOclInvalid(source
					.getGenericType(), new IllegalArgumentException(
					"Source of iterator sortedBy() was invalid.", collectionIt
							.hasNext().getInvalidReason()));
		}

		/* Else compute the result. */
		else {
			List<OclAny> resultList;

			Map<OclComparable, OclAny> results;
			results = new TreeMap<OclComparable, OclAny>(
					new Comparator<OclComparable>() {

						public int compare(OclComparable first,
								OclComparable second) {

							int result;

							OclInteger oclResult;
							oclResult = first.compareTo(second);

							if (oclResult.oclIsInvalid().isTrue()
									|| oclResult.oclIsUndefined().isTrue()) {
								result = 0;
							}

							else {
								result = oclResult.getModelInstanceInteger()
										.getLong().intValue();
							}

							return result;
						}
					});

			/* Iterate over the collection. */
			while (collectionIt.hasNext().isTrue()) {

				OclAny activeElement;
				OclAny bodyResult;

				/* Add the active element to the environment. */
				activeElement = collectionIt.next();
				myEnvironment
						.addVar(iterator.getQualifiedName(), activeElement);

				/* Compute the body for the actual set element. */
				bodyResult = doSwitch((EObject) body);

				/* Probably fail. */
				if (bodyResult.oclIsInvalid().isTrue()) {
					result = this.myStandardLibraryFactory
							.createOclInvalid(
									resultType,
									new IllegalStateException(
											"Body expression was invalid for at least one element during interpretation of iterator sortedBy().",
											bodyResult.getInvalidReason()));
					break;
				}

				else if (bodyResult.oclIsUndefined().isTrue()) {
					result = this.myStandardLibraryFactory
							.createOclInvalid(
									resultType,
									new IllegalStateException(
											"Body expression was undefined for at least one element during interpretation of iterator sortedBy()."));
					break;
				}

				if (bodyResult instanceof OclComparable) {
					results.put((OclComparable) bodyResult, activeElement);
				}

				else {
					result = myStandardLibraryFactory
							.createOclInvalid(
									resultType,
									new IllegalStateException(
											"Body expression was not comparable for at least one element during interpretation of iterator sortedBy()."));
				}
				// end else.
			}
			// end while.

			/* Probably adapt the result. */
			if (result == null) {
				resultList = new ArrayList<OclAny>(results.values());

				/* Check which type of collection the result shall have. */
				if (resultType instanceof SequenceType) {
					result = myStandardLibraryFactory.createOclSequence(
							resultList, ((SequenceType) resultType)
									.getElementType());
				}

				else if (resultType instanceof OrderedSetType) {
					result = myStandardLibraryFactory.createOclOrderedSet(
							resultList, ((OrderedSetType) resultType)
									.getElementType());
				}

				else {
					String msg;
					msg = "The ResultType of the Iterator sortedBy() should be a sorted collection.";
					msg += " But was " + resultType.getQualifiedName();

					result = myStandardLibraryFactory.createOclInvalid(
							resultType, new IllegalArgumentException(msg));
				}
				// end else.
			}
			// no else.
		}
		// end else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseLetExp(tudresden.ocl20.pivot.essentialocl.expressions.LetExp)
	 */
	@Override
	public OclAny caseLetExp(LetExp letExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret Let of Variable "
					+ letExp.getVariable().getName() + ".");
			this.pushLogOffset();
		}
		// no else.

		OclAny result = null;

		/* LetExpressions cannot be cached. */

		/*
		 * LetExpressions define a set of variables. The expression itself has
		 * not to be handled by the interpreter. The references to the variable
		 * no their initialization expression and will be interpreted if
		 * required. Only pushes and pops a new environment around the
		 * in-Expressions to ensure local visibility of defined variables.
		 */

		this.pushLocalEnvironment();
		result = doSwitch((EObject) letExp.getIn());
		this.popLocalEnvironment();

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER.debug(this.logOffset + "Interpreted Let of Variable "
					+ letExp.getVariable().getName() + ". Result = " + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseOperationCallExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp)
	 */
	public OclAny caseOperationCallExp(OperationCallExp operationCallExp) {

		OclAny result;

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset
					+ "Interpret OperationCall on Operation "
					+ operationCallExp.getReferredOperation().getName() + ".");
			this.pushLogOffset();
		}
		// no else.

		/* Probably handle a static operation. */
		if (operationCallExp.getReferredOperation() != null
				&& operationCallExp.getReferredOperation().isStatic()) {

			result = this.evaluateStaticOperation(operationCallExp);
		}

		/* Else handle a non static operation. */
		else {
			result = this.evaluateNonStaticOperation(operationCallExp);
		}
		// end else (no static operation).

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER.debug(this.logOffset
					+ "Interpreted OperationCall on Operation "
					+ operationCallExp.getReferredOperation().getName()
					+ ". Result = " + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Evaluates the {@link OclAny} result of an {@link OperationCallExp}
	 * representing a non-static {@link Operation}.
	 * </p>
	 * 
	 * @param propertyCallExp
	 *            The {@link PropertyCallExp}.
	 * @return The evaluated result.
	 */
	protected OclAny evaluateNonStaticOperation(
			OperationCallExp operationCallExp) {

		OclAny result;

		Operation referredOperation;
		referredOperation = operationCallExp.getReferredOperation();

		/* Compute the source of the operation call. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Evaluate Source ...");
		}
		// no else.

		OclAny source;

		if (operationCallExp.getSource() == null) {

			/*
			 * TODO: This should not happen but the parser parses static
			 * operation's source to null. Should be a TypeLiteralExp.
			 */
			source = this.myStandardLibraryFactory
					.createOclType(operationCallExp.getSourceType());
		}

		else {
			source = doSwitch((EObject) operationCallExp.getSource());
		}

		/*
		 * Probably get the result from a special operation like @pre or
		 * oclIsNew.
		 */
		result = this.handleSpecialOperations(operationCallExp, source);

		/*
		 * The standard case. Invoke the operation and compute the result.
		 */
		if (result == null) {

			Constraint operationSemanticInOcl;
			operationSemanticInOcl = this
					.getFeatureSemanticInOcl(referredOperation);

			OclAny[] oclAnyParameters;
			oclAnyParameters = computeParameters(operationCallExp,
					operationSemanticInOcl);

			/*
			 * Probably interpret the result of a definition, derive or body
			 * constraint.
			 */
			if (operationSemanticInOcl != null) {

				if (source.oclIsInvalid().isTrue()) {
					result = this.myStandardLibraryFactory.createOclInvalid(
							operationCallExp.getType(),
							new IllegalArgumentException(
									"Source of operation was invalid.", source
											.getInvalidReason()));
				}

				else if (source.oclIsUndefined().isTrue()) {
					result = this.myStandardLibraryFactory.createOclInvalid(
							operationCallExp.getType(),
							new IllegalArgumentException(
									"Source of operation was undefined: "
											+ source.getUndefinedReason()));
				}

				else {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug(this.logOffset
								+ "Evaluate OCL-defined Operation ...");
					}
					this.pushLocalEnvironment();
					result = this.interpretConstraint(operationSemanticInOcl,
							source);
					this.popLocalEnvironment();
				}
			}

			/* Else invoke the operation. */
			else {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(this.logOffset + "Invoke Operation "
							+ referredOperation.getName() + " ...");
				}
				result = source.invokeOperation(referredOperation,
						oclAnyParameters);
			}
		}
		// end else.

		return result;
	}

	/**
	 * <p>
	 * Evaluates the {@link OclAny} result of an {@link OperationCallExp}
	 * representing a static {@link Operation}.
	 * </p>
	 * 
	 * @param propertyCallExp
	 *            The {@link PropertyCallExp}.
	 * @return The evaluated result.
	 */
	protected OclAny evaluateStaticOperation(OperationCallExp operationCallExp) {

		OclAny result;

		Operation referredOperation;
		referredOperation = operationCallExp.getReferredOperation();

		/* Compute the source of the operation call. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Evaluate Source ...");
		}
		OclAny source;

		if (operationCallExp.getSource() == null) {

			/*
			 * TODO: This should not happen but the parser parses static
			 * operation's source to null. Should be a TypeLiteralExp.
			 */
			source = this.myStandardLibraryFactory
					.createOclType(operationCallExp.getSourceType());
		}

		else {
			source = doSwitch((EObject) operationCallExp.getSource());
		}

		if (source instanceof OclType<?>) {

			OclType<?> sourceType;
			sourceType = (OclType<?>) source;

			Constraint operationSemanticInOcl;
			operationSemanticInOcl = this
					.getFeatureSemanticInOcl(referredOperation);

			OclAny[] oclAnyParameters;
			oclAnyParameters = computeParameters(operationCallExp,
					operationSemanticInOcl);

			/*
			 * Probably interpret the result of a definition, derive or body
			 * constraint.
			 */
			if (operationSemanticInOcl != null) {

				if (source.oclIsInvalid().isTrue()) {
					result = this.myStandardLibraryFactory.createOclInvalid(
							operationCallExp.getType(),
							new IllegalArgumentException(
									"Source of operation was invalid.", source
											.getInvalidReason()));
				}

				else if (source.oclIsUndefined().isTrue()) {
					result = this.myStandardLibraryFactory.createOclInvalid(
							operationCallExp.getType(),
							new IllegalArgumentException(
									"Source of operation was undefined: "
											+ source.getUndefinedReason()));
				}

				else {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug(this.logOffset
								+ "Evaluate OCL-defined Operation ...");
					}
					this.pushLocalEnvironment();
					result = this.interpretConstraint(operationSemanticInOcl,
							source);
					this.popLocalEnvironment();
				}
			}

			/* Else invoke the operation on the instance. */
			else {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(this.logOffset + "Invoke Operation "
							+ referredOperation.getName() + " ...");
				}
				result = sourceType
						.invokeStaticOperation(referredOperation,
								oclAnyParameters, this.myEnvironment
										.getModelInstance());
			}
			// end else.
		}

		/* Else result in invalid. */
		else {
			result = this.myStandardLibraryFactory.createOclInvalid(
					referredOperation.getType(), new IllegalArgumentException(
							"Cannot invoked the static operation "
									+ referredOperation
									+ " on a source that is no OclType."));
		}
		// end else.

		return result;
	}

	/**
	 * <p>
	 * Computes the parameters for a given {@link OperationCallExp}.
	 * </p>
	 * 
	 * @param anOperationCallExp
	 *            The {@link OperationCallExp} whose parameters shall be
	 *            computed.
	 * @param oclDefinedOperation
	 *            A probably existing {@link Constraint} definition of the
	 *            referred {@link Operation}.
	 */
	private OclAny[] computeParameters(OperationCallExp anOperationCallExp,
			Constraint oclDefinedOperation) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Evaluate Parameter Values ...");
		}
		OclAny[] result;
		Operation referredOperation;

		ListIterator<OclExpression> argIterator;
		List<Parameter> opParams;

		result = new OclAny[anOperationCallExp.getArgument().size()];
		referredOperation = anOperationCallExp.getReferredOperation();

		argIterator = anOperationCallExp.getArgument().listIterator();

		if (referredOperation != null) {
			opParams = referredOperation.getInputParameter();
		}

		else {
			opParams = null;
		}

		/* Iterate through the arguments and compute the parameter values. */
		while (argIterator.hasNext()) {

			OclExpression exp;
			OclAny param;
			String parameterName;

			exp = argIterator.next();
			param = doSwitch((EObject) exp);
			result[argIterator.previousIndex()] = param;

			parameterName = opParams.get(argIterator.previousIndex()).getName();

			/*
			 * Probably add the variables to the local interpreter if a body
			 * expression or definition has been defined.
			 */
			if (oclDefinedOperation != null) {
				this.myEnvironment.addVar(parameterName, param);
			}
			// no else.
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method that tries to retrieve the result for an
	 * {@link OperationCallExp} by evaluation a special operation like
	 * <code>@pre()</code>, <code>oclIsNew()</code> or
	 * <code>allInstances()</code> .
	 * </p>
	 * 
	 * @param anOperationCallExp
	 *            The {@link OperationCallExp} representing the
	 *            {@link Operation} to be called.
	 * @param source
	 *            The already interpreted source of the {@link OperationCallExp}
	 *            .
	 * @return The result of a special {@link Operation} or <code>null</code>.
	 */
	private OclAny handleSpecialOperations(OperationCallExp anOperationCallExp,
			OclAny source) {

		OclAny result;
		result = null;

		/* Probably handle the operation atPre. */
		if (anOperationCallExp.getName().equals("atPre")) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(this.logOffset + "Evaluate @pre Operation ...");
			}
			// no else.

			if (this.isPreparationRun) {
				String msg;

				this.myEnvironment.savePostconditionValue(anOperationCallExp,
						source);

				msg = "@Pre is not available during constraint preparation.";
				result = myStandardLibraryFactory.createOclUndefined(
						anOperationCallExp.getType(), msg);
			}

			else {
				result = this.myEnvironment
						.getPostconditionValue(anOperationCallExp);

				if (result == null) {
					String msg;
					msg = "@Pre value of " + anOperationCallExp
							+ " has not been found.";

					result = myStandardLibraryFactory.createOclUndefined(
							anOperationCallExp.getType(), msg);
				}
				// no else.
			}
		} else {
			final Operation referredOperation = anOperationCallExp
					.getReferredOperation();
			if (referredOperation.getName().equals("oclIsNew")) {

				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(this.logOffset
							+ "Evaluate oclIsNew() Operation ...");
				}
				// no else.

				if (this.isPreparationRun) {
					String msg;

					this.myEnvironment.saveOldInstances(anOperationCallExp
							.getSource().getType());

					msg = "oclIsNew() is not available during preparation.";
					result = myStandardLibraryFactory.createOclUndefined(
							anOperationCallExp.getType(), msg);
				}

				else {

					if (source.oclIsInvalid().isTrue()) {

						result = this.myStandardLibraryFactory
								.createOclInvalid(anOperationCallExp.getType(),
										source.getInvalidReason());
					}

					else if (source.oclIsUndefined().isTrue()) {

						result = this.myStandardLibraryFactory
								.createOclUndefined(anOperationCallExp
										.getType(), source.getUndefinedReason());
					}

					else {

						try {
							OclModelInstanceObject oclModelInstanceObject;
							oclModelInstanceObject = (OclModelInstanceObject) source;

							result = this.myStandardLibraryFactory
									.createOclBoolean(this.myEnvironment
											.isNewInstance(oclModelInstanceObject));
						}

						catch (ClassCastException e) {

							result = this.myStandardLibraryFactory
									.createOclInvalid(anOperationCallExp
											.getType(), e);
						}
						// end catch.
					}
					// end else.
				}
				// end else.
			}

			/*
			 * allInstances
			 */
			else if (source instanceof OclType<?>
					&& referredOperation.getName().equals("allInstances")
					&& referredOperation.getInputParameter().isEmpty()) {

				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(this.logOffset
							+ "Evaluate allInstances() Operation ...");
				}
				// no else.

				if (source.oclIsInvalid().isTrue())
					result = myStandardLibraryFactory.createOclInvalid(
							anOperationCallExp.getSourceType(), source
									.getInvalidReason());
				else if (source.oclIsUndefined().isTrue())
					result = myStandardLibraryFactory.createOclInvalid(
							anOperationCallExp.getSourceType(),
							new RuntimeException(
									"Tried to call allInstances() on null. Reason :"
											+ source.getUndefinedReason()));
				else {
					final Type sourceType = ((TypeType) anOperationCallExp
							.getSourceType()).getRepresentedType();

					Set<IModelInstanceObject> allInstances = myEnvironment
							.getModelInstance().getAllInstances(sourceType);

					result = myStandardLibraryFactory.createOclSet(
							allInstances, sourceType);
				}
			}

			/*
			 * Probably handle the boolean operations that have to be
			 * interpreted only partially.
			 */
			else if (source instanceof OclBoolean) {

				OclBoolean booleanSource;
				booleanSource = (OclBoolean) source;

				/* Handle and operation: False and everything is false. */
				if (referredOperation.getName().equals("and")
						&& !booleanSource.oclIsInvalid().isTrue()
						&& !booleanSource.oclIsUndefined().isTrue()
						&& !booleanSource.isTrue()) {

					if (LOGGER.isDebugEnabled()) {
						LOGGER
								.debug(this.logOffset
										+ "Source of and is false/undefined/invalid. Result in false.");
					}
					// no else.

					result = this.myStandardLibraryFactory
							.createOclBoolean(false);
				}

				/* Handle implies operation. */
				else if (referredOperation.getName().equals("implies")) {

					/* invalid implies anything = invalid. */
					if (booleanSource.oclIsInvalid().isTrue()) {
						if (LOGGER.isDebugEnabled()) {
							LOGGER
									.debug(this.logOffset
											+ "Source of implies is invalid. Result in invalid.");
						}
						// no else.

						result = this.myStandardLibraryFactory
								.createOclInvalid(EssentialOclPlugin
										.getOclLibraryProvider()
										.getOclLibrary().getOclBoolean(),
										booleanSource.getInvalidReason());
					}

					/* undefined implies anything = invalid. */
					else if (booleanSource.oclIsUndefined().isTrue()) {
						if (LOGGER.isDebugEnabled()) {
							LOGGER
									.debug(this.logOffset
											+ "Source of implies is undefined. Result in invalid.");
						}
						// no else.

						result = this.myStandardLibraryFactory
								.createOclInvalid(
										EssentialOclPlugin
												.getOclLibraryProvider()
												.getOclLibrary()
												.getOclBoolean(),
										new NullPointerException(
												"Implies on undefined is not allowed. "
														+ booleanSource
																.getUndefinedReason()));
					}

					/* false implies anything = true. */
					else if (!booleanSource.isTrue()) {
						if (LOGGER.isDebugEnabled()) {
							LOGGER
									.debug(this.logOffset
											+ "Source of implies is false. Result in true.");
						}
						// no else.

						result = this.myStandardLibraryFactory
								.createOclBoolean(true);
					}
					// end else.
				}

				/* Handle or operation: True or everything is true. */
				else if (referredOperation.getName().equals("or")
						&& !booleanSource.oclIsInvalid().isTrue()
						&& !booleanSource.oclIsUndefined().isTrue()
						&& booleanSource.isTrue()) {

					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug(this.logOffset
								+ "Source of or is true. Result in true.");
					}
					// no else.

					result = this.myStandardLibraryFactory
							.createOclBoolean(true);
				}
			}
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #casePropertyCallExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp)
	 */
	public OclAny casePropertyCallExp(PropertyCallExp propertyCallExp) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret PropertyCall on Property "
					+ propertyCallExp.getReferredProperty().getName() + ".");
			this.pushLogOffset();
		}
		// no else.

		OclAny result;

		/* Handle static properties. */
		if (propertyCallExp.getReferredProperty().isStatic()) {
			result = this.evaluateStaticProperty(propertyCallExp);
		}

		/* Else try to get a non static property. */
		else {
			result = this.evaluateNonStaticProperty(propertyCallExp);
		}
		// end else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER.debug(this.logOffset
					+ "Interpreted PropertyCall on Property "
					+ propertyCallExp.getReferredProperty().getName()
					+ ". Result = " + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Evaluates the OclAny result of a {@link PropertyCallExp} representing a
	 * non-static {@link Property}.
	 * </p>
	 * 
	 * @param propertyCallExp
	 *            The {@link PropertyCallExp}.
	 * @return The evaluated result.
	 */
	protected OclAny evaluateNonStaticProperty(PropertyCallExp propertyCallExp) {

		OclAny result;

		Property referredProperty;
		referredProperty = propertyCallExp.getReferredProperty();

		/* Interpret the source of the property call. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Evaluate Source ...");
		}
		// no else.

		OclAny source;
		source = doSwitch((EObject) propertyCallExp.getSource());

		/* Check if the source is a tuple. */
		if (source instanceof OclTuple) {
			OclTuple sourceTuple;
			sourceTuple = (OclTuple) source;

			result = sourceTuple.getPropertyValue(this.myStandardLibraryFactory
					.createOclString(referredProperty.getName()));
		}

		/* Else the source must be an OclModelInstanceObject. */
		else {
			try {
				OclModelInstanceObject sourceObject;
				sourceObject = (OclModelInstanceObject) source;

				Constraint propertySemanticInOcl;
				propertySemanticInOcl = this
						.getFeatureSemanticInOcl(referredProperty);

				/*
				 * Probably interpret the result of a definition, derive or init
				 * constraint.
				 */
				if (propertySemanticInOcl != null) {

					if (source.oclIsInvalid().isTrue()) {
						result = this.myStandardLibraryFactory
								.createOclInvalid(
										propertyCallExp.getType(),
										new IllegalArgumentException(
												"Source of property was invalid.",
												source.getInvalidReason()));
					}

					else if (source.oclIsUndefined().isTrue()) {
						result = this.myStandardLibraryFactory
								.createOclInvalid(
										propertyCallExp.getType(),
										new IllegalArgumentException(
												"Source of property was undefined: "
														+ source
																.getUndefinedReason()));
					}

					else {
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug(this.logOffset
									+ "Evaluate OCL-defined Property ...");
						}
						// no else.

						this.pushLocalEnvironment();
						result = this.interpretConstraint(
								propertySemanticInOcl, source);
						this.popLocalEnvironment();
					}
				}

				/* Else get the property from the instance. */
				else {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug(this.logOffset + "Get Property "
								+ referredProperty.getName() + " ...");
					}
					// no else.

					Constraint def = isDef(referredProperty, source);
					if (def != null)
						result = interpretConstraint(def, source);
					else
						result = sourceObject.getProperty(referredProperty);
				}
			}

			/* If not, result in invalid. */
			catch (ClassCastException e) {
				result = myStandardLibraryFactory.createOclInvalid(
						propertyCallExp.getType(), e);
			}
			// end catch.
		}
		// end else.

		return result;
	}

	// FIXME Michael: cache defs for improved performance
	/**
	 * Returns a {@link Constraint} - i.e., the def - when the property is
	 * defined by a definition.
	 */
	private Constraint isDef(Property property, OclAny source) {

		Constraint result = null;

		/* Probably Unwrap element type. */
		Type elementType = property.getType();

		while (elementType instanceof CollectionType) {
			elementType = ((CollectionType) elementType).getElementType();
		}
		// end while.

		/* Go to root name space. */
		Namespace namespace = elementType.getNamespace();

		while (namespace.getNestingNamespace() != null) {
			namespace = namespace.getNestingNamespace();
		}
		// end while.

		for (Constraint constraint : namespace.getOwnedAndNestedRules()) {
			// find the definition for the property
			Feature definedFeature = constraint.getDefinedFeature();
			if (definedFeature == property) {
				result = constraint;
				break;
			}
		}

		return result;
	}

	/**
	 * <p>
	 * Evaluates the OclAny result of a {@link PropertyCallExp} representing a
	 * static {@link Property}.
	 * </p>
	 * 
	 * @param propertyCallExp
	 *            The {@link PropertyCallExp}.
	 * @return The evaluated result.
	 */
	protected OclAny evaluateStaticProperty(PropertyCallExp propertyCallExp) {

		OclAny result;

		Property referredProperty;
		referredProperty = propertyCallExp.getReferredProperty();

		/* Interpret the source of the property call. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Evaluate Source ...");
		}
		// no else.

		OclAny source;

		if (propertyCallExp.getSource() == null) {

			/*
			 * TODO This should not happen. Anyway the parser parses the source
			 * of static properties to null instead of a TypeLiteralExp.
			 */
			source = this.myStandardLibraryFactory
					.createOclType((Type) referredProperty.getOwner());
		}

		else {
			source = this.doSwitch((EObject) propertyCallExp.getSource());
		}

		/* Check if the source is an OclType. */
		if (source instanceof OclType<?>) {
			OclType<?> sourceType;
			sourceType = (OclType<?>) source;

			Constraint propertySemanticInOcl;
			propertySemanticInOcl = this
					.getFeatureSemanticInOcl(referredProperty);

			/*
			 * Probably interpret the result of a definition, derive or init
			 * constraint.
			 */
			if (propertySemanticInOcl != null) {

				if (source.oclIsInvalid().isTrue()) {
					result = this.myStandardLibraryFactory.createOclInvalid(
							propertyCallExp.getType(),
							new IllegalArgumentException(
									"Source of property was invalid.", source
											.getInvalidReason()));
				}

				else if (source.oclIsUndefined().isTrue()) {
					result = this.myStandardLibraryFactory.createOclInvalid(
							propertyCallExp.getType(),
							new IllegalArgumentException(
									"Source of property was undefined: "
											+ source.getUndefinedReason()));
				}

				else {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug(this.logOffset
								+ "Evaluate OCL-defined Property ...");
					}
					// no else.

					this.pushLocalEnvironment();
					result = this.interpretConstraint(propertySemanticInOcl,
							source);
					this.popLocalEnvironment();
				}
			}

			/* Else get the property from the instance. */
			else {
				result = sourceType.getStaticProperty(referredProperty,
						this.myEnvironment.getModelInstance());
			}
		}

		else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(this.logOffset + "Get Property "
						+ referredProperty.getName() + " ...");
			}
			// no else.

			result = this.myStandardLibraryFactory.createOclInvalid(
					propertyCallExp.getType(), new IllegalArgumentException(
							"Cannot invoke the static property "
									+ referredProperty
									+ " on a non static source."));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseRealLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp)
	 */
	@Override
	public OclAny caseRealLiteralExp(RealLiteralExp realLiteralExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret RealLiteral.");
		}
		// no else.

		/* Cache is not efficient here. */
		OclAny result;
		result = myStandardLibraryFactory.createOclReal(realLiteralExp
				.getRealSymbol());

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpreted RealLiteral. Result = "
					+ result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseStringLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp)
	 */
	@Override
	public OclAny caseStringLiteralExp(StringLiteralExp stringLiteralExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret StringLiteral.");
		}
		// no else.

		/* Cache is not efficient here. */
		OclAny result;
		result = myStandardLibraryFactory.createOclString(stringLiteralExp
				.getStringSymbol());

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset
					+ "Interpreted StringLiteral. Result = " + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseTupleLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp)
	 */
	@Override
	public OclAny caseTupleLiteralExp(TupleLiteralExp tupleLiteralExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret TupleLiteral.");
			this.pushLogOffset();
		}
		// no else.

		/* TupleLiteralExps cannot be cached. */
		OclAny result;

		if (tupleLiteralExp.getPart().size() != tupleLiteralExp.getPart()
				.size()) {
			result = this.myStandardLibraryFactory
					.createOclInvalid(
							tupleLiteralExp.getType(),
							new IllegalStateException(
									"Sizes of keys and values in TupleLiteralExp does not match."));
		}

		else {
			List<IModelInstanceString> partNames;
			partNames = new ArrayList<IModelInstanceString>(tupleLiteralExp
					.getPart().size());

			List<IModelInstanceElement> partValues;
			partValues = new ArrayList<IModelInstanceElement>(tupleLiteralExp
					.getPart().size());

			for (TupleLiteralPart literalPart : tupleLiteralExp.getPart()) {
				partNames.add(BasisJavaModelInstanceFactory
						.createModelInstanceString(literalPart.getProperty()
								.getName()));
				partValues.add(doSwitch((EObject) literalPart)
						.getModelInstanceElement());
			}
			// end for.

			result = myStandardLibraryFactory.createOclTuple(partNames,
					partValues, tupleLiteralExp.getType());
		}
		// end else.

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER.debug(this.logOffset + "Interpreted TupleLiteral. Result = "
					+ result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseTupleLiteralPart
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart)
	 */
	@Override
	public OclAny caseTupleLiteralPart(TupleLiteralPart tupleLiteralPart) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Intepret TuplePart " + tupleLiteralPart.getName()
					+ ".");
			this.pushLogOffset();
		}

		/* TupleLiteralParts cannot be cached. */
		OclAny result = doSwitch((EObject) tupleLiteralPart.getValue());

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER.debug("Interpreted TuplePart " + tupleLiteralPart.getName()
					+ ". Result = " + result);

		}
		// no else;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseTypeLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp)
	 */
	@Override
	public OclAny caseTypeLiteralExp(TypeLiteralExp typeLiteralExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret TypeLiteral.");
		}
		// no else.

		/* Cache is not efficient here. */
		OclAny result = myStandardLibraryFactory.createOclType(typeLiteralExp
				.getReferredType());

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpreted TypeLiteral. Result = "
					+ result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseUndefinedLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp)
	 */
	@Override
	public OclAny caseUndefinedLiteralExp(
			UndefinedLiteralExp undefinedLiteralExp) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret UndefinedLiteral.");
		}
		// no else.

		/* Cache is not efficient here. */
		OclAny result;
		result = myStandardLibraryFactory.createOclUndefined(
				undefinedLiteralExp.getType(), "Value was declared as null.");

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset
					+ "Interpreted UndefinedLiteral. Result = " + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseVariable(tudresden.ocl20.pivot.essentialocl.expressions.Variable)
	 */
	public OclAny caseVariable(Variable variable) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this.logOffset + "Interpret Variable "
					+ variable.getName());
			this.pushLogOffset();
		}
		// no else.

		OclAny result;

		/*
		 * probably get the value of the Variable from the environment. For
		 * example if the variable was prepared like the variables 'self' or
		 * 'result' or the variable represents a parameter value from the
		 * constraint's context.
		 */
		if (myEnvironment.getVar(variable.getName()) != null) {
			result = myEnvironment.getVar(variable.getName());
		}

		/* Else compute the result. */
		else {
			/* Probably interpret the initExpression. */
			if (variable.getInitExpression() != null) {
				result = doSwitch((EObject) variable.getInitExpression());
			}

			/* Else the variable is undefined. */
			else {

				String msg;
				msg = "Variable " + variable.getName()
						+ " was not initialized.";

				if (!this.isPreparationRun) {
					LOGGER.warn(msg);
				}
				// no else.

				result = myStandardLibraryFactory.createOclUndefined(variable
						.getType(), msg);
			}

			/* Add var to environment to avoid re-initialization. */
			this.myEnvironment.addVar(variable.getName(), result);
		}

		if (LOGGER.isDebugEnabled()) {
			this.popLogOffset();
			LOGGER.debug(this.logOffset + "Interpreted Variable "
					+ variable.getName() + ". Result = " + result);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseVariableExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.VariableExp)
	 */
	@Override
	public OclAny caseVariableExp(VariableExp variableExp) {

		/* VariableExp cannot be cached. Only the variable self can be cached. */
		OclAny result;
		result = doSwitch((EObject) variableExp.getReferredVariable());

		return result;
	}

	/**
	 * <p>
	 * Helper method which returns a given List as an instance of a given
	 * collection type.
	 * </p>
	 * 
	 * @param resultList
	 *            The list which shall be returned as collection.
	 * @param resultType
	 *            The {@link Type} of the collection which shall be returned.
	 * @return Returns a given List as an instance of a given collection type.
	 */
	protected OclAny adaptResultListAsCollection(List<OclAny> resultList,
			Type resultType) {

		OclAny result;

		/* Check which type of collection shall be returned. */
		if (resultType instanceof SetType) {
			Set<OclAny> resultSet = new HashSet<OclAny>(resultList);
			result = myStandardLibraryFactory.createOclSet(resultSet,
					((SetType) resultType).getElementType());
		}

		else if (resultType instanceof BagType) {
			result = myStandardLibraryFactory.createOclBag(resultList,
					((BagType) resultType).getElementType());
		}

		else if (resultType instanceof SequenceType) {
			result = myStandardLibraryFactory.createOclSequence(resultList,
					((SequenceType) resultType).getElementType());
		}

		else if (resultType instanceof OrderedSetType) {
			result = myStandardLibraryFactory.createOclOrderedSet(resultList,
					((OrderedSetType) resultType).getElementType());
		}

		else {
			String msg;
			msg = "Unknown Type of Collection. Type was "
					+ resultType.getName();

			LOGGER.error(msg);
			result = myStandardLibraryFactory.createOclInvalid(resultType,
					new IllegalArgumentException(msg));
		}

		return result;
	}

	/**
	 * <p>
	 * Replaces the current {@link IInterpretationEnvironment} from the next
	 * {@link IInterpretationEnvironment} located on the stack if the stack is
	 * not empty.
	 * </p>
	 * 
	 * @see OclInterpreter#pushLocalEnvironment()
	 */
	protected void popLocalEnvironment() {

		if (this.myEnvironmentStack.size() > 0) {
			this.myEnvironment = this.myEnvironmentStack.pop();
		}
		// no else.
	}

	/**
	 * <p>
	 * Pushes the log offset by adding additional white spaces.
	 * </p>
	 */
	protected void popLogOffset() {

		if (this.logOffset.length() > 1) {
			this.logOffset = this.logOffset.substring(0, this.logOffset
					.length() - 2);
		}
		// no else.
	}

	/**
	 * <p>
	 * Creates a new local {@link IInterpretationEnvironment} and copies all
	 * required context information from the current
	 * {@link IInterpretationEnvironment} (e.g., existing variables).
	 * </p>
	 * 
	 * <p>
	 * The current {@link IInterpretationEnvironment} is pushed on a stack and
	 * can be popped using the method
	 * {@link OclInterpreter#popLocalEnvironment()} again.
	 * </p>
	 */
	protected void pushLocalEnvironment() {

		this.myEnvironmentStack.push(this.myEnvironment);
		this.myEnvironment = this.myEnvironment.clone();
	}

	/**
	 * <p>
	 * Pushes the log offset by adding additional white spaces.
	 * </p>
	 */
	protected void pushLogOffset() {

		this.logOffset += ". ";
	}

	/**
	 * <p>
	 * Checks whether or not a given {@link Feature}'s semantic is defined by an
	 * {@link Constraint} and returns this {@link Constraint} or
	 * <code>null</code> .
	 * 
	 * TODO This method would be unnecessary if {@link Feature}s would know
	 * their {@link Constraint}s if they exist.
	 * 
	 * @param feature
	 *            The {@link Feature} that shall be checked.
	 * @return The found {@link Constraint} or <code>null</code>.
	 */
	private Constraint getFeatureSemanticInOcl(Feature feature) {

		Constraint result;
		result = null;

		Type owningType;
		owningType = (Type) feature.getOwner();

		if (owningType != null && owningType.getNamespace() != null) {
			for (Constraint constraint : owningType.getNamespace()
					.getOwnedRule()) {

				/*
				 * For definitions, the features is defined by the constraint,
				 * for init, derive and body expressions, the feature is
				 * constrained by the constraint.
				 */
				if (feature.equals(constraint.getDefinedFeature())
						|| constraint.getConstrainedElement().contains(feature)) {
					result = constraint;
					break;
				}
				// no else.
			}
			// end for.
		}
		// no else.

		return result;
	}
}