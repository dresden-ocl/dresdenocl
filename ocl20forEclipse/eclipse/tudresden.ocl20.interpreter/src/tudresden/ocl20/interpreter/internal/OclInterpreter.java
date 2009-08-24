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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.interpreter.IInterpretationEnvironment;
import tudresden.ocl20.interpreter.IInterpretationResult;
import tudresden.ocl20.interpreter.IOclInterpreter;
import tudresden.ocl20.interpreter.OclInterpreterPlugin;
import tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
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
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclComparable;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclVoid;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IOclInstanceAdapterFactory;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.essentialocl.types.BagType;
import tudresden.ocl20.pivot.essentialocl.types.OrderedSetType;
import tudresden.ocl20.pivot.essentialocl.types.SequenceType;
import tudresden.ocl20.pivot.essentialocl.types.SetType;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTypeObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.JavaStandardlibraryPlugin;
import tudresden.ocl20.pivot.standardlibrary.java.internal.factory.JavaOclInstanceAdapterFactory;

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
 * @author Ronny Brandt: Developed the first version.
 * @author Claas Wilke: Refactored the interpreter and fixed many bugs.
 */
public class OclInterpreter extends ExpressionsSwitch<OclRoot> implements
		IOclInterpreter {

	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER =
			OclInterpreterPlugin.getLogger(OclInterpreter.class);

	/** The {@link IOclInstanceAdapterFactory} of this {@link IOclInterpreter}. */
	private IOclInstanceAdapterFactory myOclInstanceAdapterFactory =
			JavaStandardlibraryPlugin.getOclInstanceAdapterFactory();

	/** The {@link IStandardLibraryFactory} of this {@link IOclInterpreter}. */
	private IStandardLibraryFactory myStandardLibraryFactory =
			JavaStandardlibraryPlugin.getStandardLibraryFactory();

	/** Specifies if model access was needed to interpret result. */
	private boolean isModelAccessNeeded = false;

	/**
	 * Specifies if the current interpretation run is used to prepare a
	 * {@link Constraint}.
	 */
	private boolean isPreparation = false;

	/** Specifies if caching shall be used. */
	private boolean isCachingEnabled = true;

	/** The current {@link IModelInstanceElement} to be interpreted or prepared. */
	private IModelInstanceElement myCurrentModelObject;

	/** The {@link InterpretationEnvironment} to be used to store {@link Variable}s etc. */
	private IInterpretationEnvironment myEnvironment = new InterpretationEnvironment();;

	/**
	 * The Stack is used to store local {@link IInterpretationEnvironment} used during method or
	 * property call interpretation. The local {@link IInterpretationEnvironment}s can contain
	 * {@link Variable}s that are not visible globaly.
	 */
	private Stack<IInterpretationEnvironment> myEnvironmentStack = new Stack<IInterpretationEnvironment>();

	/**
	 * <p>
	 * Instantiates a new {@link OclInterpreter}.
	 * </p>
	 * 
	 * @param aModelInstance
	 *          The {@link IModelInstance} used during interpretation.
	 */
	public OclInterpreter(IModelInstance aModelInstance) {

		this.myEnvironment.setModelInstance(aModelInstance);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#getEnvironment()
	 */
	public IInterpretationEnvironment getEnvironment() {

		return this.myEnvironment;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#getStandardLibraryFactory()
	 */
	public IStandardLibraryFactory getStandardLibraryFactory() {

		return this.myStandardLibraryFactory;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#interpretConstraint(tudresden
	 * .ocl20.pivot.pivotmodel.Constraint,
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceElement)
	 */
	public IInterpretationResult interpretConstraint(Constraint aConstraint,
			IModelInstanceElement aModelObject) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "interpretConstraint(";
			msg += "aConstraint = " + aConstraint;
			msg += ", aModelObject = " + aModelObject;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		IInterpretationResult result;
		OclRoot oclRootResult;
		OclRoot oclModelObject;

		this.isModelAccessNeeded = false;
		this.isPreparation = false;

		this.myCurrentModelObject = aModelObject;

		/* Try to get the modelObject as OCL object. */
		if (aModelObject != null) {
			oclModelObject =
					JavaOclInstanceAdapterFactory.INSTANCE.createOclRoot(aModelObject);
		}

		else {
			oclModelObject = null;
		}

		/* Add self variable to environment. */
		this.myEnvironment.addVar("self", oclModelObject);

		/* Eventually use a cached result. */
		if (isCachingEnabled && myEnvironment.getCachedResult(aConstraint) != null) {
			oclRootResult = myEnvironment.getCachedResult(aConstraint);
		}

		/* Else compute the result. */
		else {
			EObject constraintSpecification;

			constraintSpecification = (EObject) aConstraint.getSpecification();

			oclRootResult = doSwitch((EObject) constraintSpecification);
		}

		/* Eventually cache the result. */
		if (isCachingEnabled && !isModelAccessNeeded) {
			myEnvironment.cacheResult(aConstraint, oclRootResult);
		}
		// no else.

		result =
				new InterpretationResultImpl(aModelObject, aConstraint, oclRootResult);

		OclInterpreterPlugin.getInterpreterRegistry().fireInterpretationFinished(
				result);

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "interpretConstraint(Constraint, IModelInstanceElement) - exit ";
			msg += "- return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#interpretConstraints(java.util
	 * .Collection, tudresden.ocl20.pivot.modelbus.IModelInstanceElement)
	 */
	public List<IInterpretationResult> interpretConstraints(
			Collection<Constraint> constraints, IModelInstanceElement aModelObject) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "interpretConstraints(";
			msg += "constraints = " + constraints;
			msg += ", aModelObject = " + aModelObject;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		List<IInterpretationResult> result;

		result = new ArrayList<IInterpretationResult>();

		for (Constraint aConstraint : constraints) {
			result.add(this.interpretConstraint(aConstraint, aModelObject));
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg =
					"interpretConstraints(Collection<Constraint>, IModelInstanceElement) - exit ";
			msg += "- return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#interpretConstraintsOfKind(
	 * java.util.Collection, tudresden.ocl20.pivot.modelbus.IModelInstanceElement,
	 * tudresden.ocl20.pivot.pivotmodel.ConstraintKind)
	 */
	public List<IInterpretationResult> interpretConstraintsOfKind(
			Collection<Constraint> constraints, IModelInstanceElement aModelObject,
			ConstraintKind aKind) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "interpretConstraintsOfKind(";
			msg += "constraints = " + constraints;
			msg += ", aModelObject = " + aModelObject;
			msg += ", aKind = " + aKind;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		List<IInterpretationResult> result;
		List<Constraint> filteredConstraints;

		filteredConstraints = new ArrayList<Constraint>();

		/* Filter the Constraints by the given ConstraintKind. */
		for (Constraint aConstraint : constraints) {
			if (aConstraint.getKind().equals(aKind)) {
				filteredConstraints.add(aConstraint);
			}
			// no else.
		}

		/* Interpret the filtered Constraints. */
		result = this.interpretConstraints(filteredConstraints, aModelObject);

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg =
					"interpretConstraints(Collection<Constraint>, IModelInstanceElement) - exit ";
			msg += "- return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#interpretPreConditions(tudresden
	 * .ocl20.pivot.modelbus.IModelObject,
	 * tudresden.ocl20.pivot.pivotmodel.Operation,
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceElement[], java.util.Collection)
	 */
	public List<IInterpretationResult> interpretPreConditions(
			IModelInstanceElement modelObject, Operation operation,
			IModelInstanceElement[] parameterValues, Collection<Constraint> preConditions) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "interpretPreConditions(";
			msg += "modelObject = " + modelObject;
			msg += ", operation = " + operation;
			msg += ", parameterValues = " + parameterValues;
			msg += ", preConditions = " + preConditions;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		List<IInterpretationResult> result;

		result = new ArrayList<IInterpretationResult>();

		/* Only interpret preconditions defined on the given operation. */
		for (Constraint aConstraint : preConditions) {

			if (aConstraint.getKind().equals(ConstraintKind.PRECONDITION)
					&& aConstraint.getConstrainedElement().contains(operation)) {

				/*
				 * Add the parameters of the Operation to the environment (they can be
				 * named different for each Constraint).
				 */
				this.addParametersToEnvironment(aConstraint, parameterValues);

				result.add(this.interpretConstraint(aConstraint, modelObject));

				/* Remove the parameters again. */
				this.removeParametersFromEnvironment(aConstraint);

			}
			// no else.
		}
		// end for.

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "interpretPreConditions(IModelInstanceElement, Operation, IModelInstanceElement[]";
			msg += ", Collection<Constraint>) - exit ";
			msg += "- return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#interpretPostConditions(tudresden
	 * .ocl20.pivot.modelbus.IModelObject,
	 * tudresden.ocl20.pivot.pivotmodel.Operation,
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceElement[],
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceElement, java.util.Collection)
	 */
	public List<IInterpretationResult> interpretPostConditions(
			IModelInstanceElement modelObject, Operation operation,
			IModelInstanceElement[] parameterValues, IModelInstanceElement resultValue,
			Collection<Constraint> postConditions) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "interpretPostConditions(";
			msg += "modelObject = " + modelObject;
			msg += ", operation = " + operation;
			msg += ", parameterValues = " + parameterValues;
			msg += ", resultValue = " + resultValue;
			msg += ", postConditions = " + postConditions;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		List<IInterpretationResult> result;

		result = new ArrayList<IInterpretationResult>();

		/* Add a local environment containing the result variable. */
		this.pushLocalEnvironment();

		/* Set the result variable to null or its result. */
		if (resultValue != null) {
			OclRoot oclResult;
			oclResult =
					JavaOclInstanceAdapterFactory.INSTANCE.createOclRoot(resultValue);

			this.setEnviromentVariable(RESULT_VARIABLE_NAME, oclResult);
		}

		else {
			this.resetEnviromentVariable(RESULT_VARIABLE_NAME);
		}

		/* Only interpret preconditions defined on the given operation. */
		for (Constraint aConstraint : postConditions) {

			if (aConstraint.getKind().equals(ConstraintKind.POSTCONDITION)
					&& aConstraint.getConstrainedElement().contains(operation)) {

				/*
				 * Add the parameters of the Operation to the environment (they can be
				 * named different for each Constraint).
				 */
				this.addParametersToEnvironment(aConstraint, parameterValues);

				result.add(this.interpretConstraint(aConstraint, modelObject));

				/* Remove the parameters again. */
				this.removeParametersFromEnvironment(aConstraint);

			}
			// no else.
		}
		// end for.

		/* Remove the local environment containing the result variable. */
		this.popLocalEnvironment();

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "interpretPostConditions(IModelInstanceElement, Operation, IModelInstanceElement[]";
			msg += ", IModelInstanceElement, Collection<Constraint>) - exit ";
			msg += "- return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#isCachingEnabled()
	 */
	public boolean isCachingEnabled() {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("isCachingEnabled()() - start");
		}
		// no else.

		boolean result;

		result = this.isCachingEnabled;

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "isCachingEnabled()() - exit ";
			msg += "- return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#isModelAccessNeeded()
	 */
	public boolean isModelAccessNeeded() {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("isModelAccessNeeded() - start");
		}
		// no else.

		boolean result;

		result = this.isModelAccessNeeded;

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "isModelAccessNeeded() - exit ";
			msg += "- return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#popLocalEnvironment()
	 */
	public void popLocalEnvironment() {

		if (this.myEnvironmentStack.size() > 0) {
			this.myEnvironment = this.myEnvironmentStack.pop();
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#pushLocalEnvironment()
	 */
	public void pushLocalEnvironment() {

		this.myEnvironmentStack.push(this.myEnvironment);
		this.myEnvironment = this.myEnvironment.clone();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#prepare(tudresden.ocl20.pivot
	 * .pivotmodel.Constraint)
	 */
	public void prepareConstraint(Constraint aConstraint, IModelInstanceElement modelObject) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "prepareConstraint(";
			msg += "aConstraint = " + aConstraint;
			msg += ", modelObject = " + modelObject;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		ConstraintKind aKind;

		this.isPreparation = true;

		aKind = aConstraint.getKind();

		/*
		 * Check if a constraints which do not need the IModelInstanceElement context shall
		 * be prepared.
		 */
		if (aKind.equals(ConstraintKind.BODY)
				|| aKind.equals(ConstraintKind.DEFINITION)
				|| aKind.equals(ConstraintKind.DERIVED)
				|| aKind.equals(ConstraintKind.INITIAL)) {

			this.prepareConstraint(aConstraint);
		}

		/* Else prepare the constraint and its context. */
		else {
			OclRoot oclModelObject;
			EObject constraintSpecification;

			this.isModelAccessNeeded = false;
			this.myCurrentModelObject = modelObject;

			/* Try to get the modelObject as OCL object. */
			if (modelObject != null) {
				oclModelObject =
						JavaOclInstanceAdapterFactory.INSTANCE.createOclRoot(modelObject);
			}

			else {
				oclModelObject = null;
			}

			/* Add self variable to environment. */
			this.myEnvironment.addVar(SELF_VARIABLE_NAME, oclModelObject);

			/* Prepare the constraintSpecification. */
			constraintSpecification = (EObject) aConstraint.getSpecification();
			this.doSwitch((EObject) constraintSpecification);
		}

		this.isPreparation = false;

		/* Remove the self variable from the environment again. */
		this.myEnvironment.addVar(SELF_VARIABLE_NAME, null);

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("prepareConstraint(Constraint) - end");
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#prepareConstraint(tudresden
	 * .ocl20.pivot.pivotmodel.Constraint)
	 */
	public void prepareConstraint(Constraint aConstraint) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "prepareConstraint(";
			msg += "aConstraint = " + aConstraint;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		ConstraintKind aKind;

		String constrainedElemName;

		this.isPreparation = true;

		aKind = aConstraint.getKind();

		constrainedElemName =
				((NamedElement) aConstraint.getConstrainedElement().get(0))
						.getQualifiedName();

		/* Check if a body expressions shall be prepared */
		if (aKind.equals(ConstraintKind.BODY)) {

			/* Add the constraint to the environment. */
			this.myEnvironment.addConstraint(constrainedElemName, aConstraint);
		}

		/* Else check if a definition shall be prepared. */
		else if (aKind.equals(ConstraintKind.DEFINITION)) {

			String featureName;

			featureName = aConstraint.getDefinedFeature().getQualifiedName();

			/* Add the defined feature to the environment. */
			this.myEnvironment.addConstraint(featureName, aConstraint);
		}

		/* Else check if a derived value shall be prepared */
		else if (aKind.equals(ConstraintKind.DERIVED)) {

			/* Add the constraint to the environment. */
			this.myEnvironment.addConstraint(constrainedElemName + "-derive",
					aConstraint);
		}

		/* Else check if a initial value shall be prepared */
		else if (aKind.equals(ConstraintKind.INITIAL)) {

			/* Add the constraint to the environment. */
			this.myEnvironment.addConstraint(constrainedElemName + "-initial",
					aConstraint);
		}

		this.isPreparation = false;

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("prepareConstraint(Constraint) - end");
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#prepareConstraints(java.util
	 * .Collection)
	 */
	public void prepareConstraints(Collection<Constraint> constraints) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "prepareConstraints(";
			msg += "constraints = " + constraints;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		/* Iterate through the given model objects and constraints. */
		for (Constraint aConstraint : constraints) {

			/* Check if the Constraint must be prepared at all. */
			if (aConstraint.getKind().equals(ConstraintKind.DEFINITION)
					|| aConstraint.getKind().equals(ConstraintKind.DERIVED)
					|| aConstraint.getKind().equals(ConstraintKind.BODY)
					|| aConstraint.getKind().equals(ConstraintKind.INITIAL)) {

				this.prepareConstraint(aConstraint);
			}
			// no else.
		}
		// end for.

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "prepareConstraints(Collection<Constraint>) - end";

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#prepareConstraints(java.util
	 * .Collection, tudresden.ocl20.pivot.modelbus.IModelInstanceElement)
	 */
	public void prepareConstraints(Collection<Constraint> constraints,
			IModelInstanceElement modelObject) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "prepareConstraints(";
			msg += "constraints = " + constraints;
			msg += ", modelObject = " + modelObject;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		for (Constraint aConstraint : constraints) {
			this.prepareConstraint(aConstraint, modelObject);
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "prepareConstraints(Collection<Constraint>, IModelInstanceElement) - end";

			LOGGER.debug(msg);
		}
		// no else.
	}

	public void preparePostConditions(IModelInstanceElement modelObject,
			Operation operation, IModelInstanceElement[] parameterValues,
			Collection<Constraint> postConditions) {

		/* Iterate through the constraints. */
		for (Constraint aConstraint : postConditions) {

			/* Only prepare postconditions. */
			if (aConstraint.getKind().equals(ConstraintKind.POSTCONDITION)) {

				/*
				 * Only prepare postconditions defined on the operation of the given
				 * context.
				 */
				if (aConstraint.getConstrainedElement().contains(operation)) {
					/*
					 * Add the parameters of the Operation to the environment (they can be
					 * named different for each Constraint).
					 */
					this.addParametersToEnvironment(aConstraint, parameterValues);

					this.prepareConstraint(aConstraint, modelObject);

					/* Remove the parameters again. */
					this.removeParametersFromEnvironment(aConstraint);
				}
				// no else.
			}
			// no else.
		}
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#setUseCache(boolean)
	 */
	public void setCachingEnabled(boolean enabled) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "setCachingEnabled(";
			msg += "enabled = " + enabled;
			msg += ") - enter";

			LOGGER.debug(msg);
		}
		// no else.

		this.isCachingEnabled = enabled;

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setCachingEnabled(boolean) - exit");
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#setEnviromentVariable(java.
	 * lang.String, tudresden.ocl20.pivot.modelbus.IModelInstanceElement)
	 */
	public void setEnviromentVariable(String name, IModelInstanceElement value) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "setEnviromentVariable(";
			msg += "name = " + name;
			msg += ", value = " + value;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot oclValue;

		oclValue = this.myOclInstanceAdapterFactory.createOclRoot(value);
		this.myEnvironment.addVar(name, oclValue);

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "setEnviromentVariable() - exit ";

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#setEnviromentVariable(java.
	 * lang.String, tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public void setEnviromentVariable(String name, OclRoot value) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "setEnviromentVariable(";
			msg += "name = " + name;
			msg += ", value = " + value;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		this.myEnvironment.addVar(name, value);

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "setEnviromentVariable() - exit ";

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#setEnviromentVariable(java.
	 * lang.String, tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public void resetEnviromentVariable(String name) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "resetEnviromentVariable(";
			msg += "name = " + name;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		this.myEnvironment.addVar(name, null);

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "resetEnviromentVariable() - exit ";

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseBooleanLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp)
	 */
	@Override
	public OclRoot caseBooleanLiteralExp(BooleanLiteralExp booleanLiteralExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseBooleanLiteralExp(";
			msg += "booleanLiteralExp = " + booleanLiteralExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(booleanLiteralExp) != null) {
			result = myEnvironment.getCachedResult(booleanLiteralExp);
		}

		/* Else compute the result. */
		else {
			result =
					this.myStandardLibraryFactory.createOclBoolean(booleanLiteralExp
							.isBooleanSymbol());

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(booleanLiteralExp, result);
			}
			// no else.
		}

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseBooleanLiteralExp(BooleanLiteralExp) - end ";
			msg += "- return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseCollectionItem
	 * (tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem)
	 */
	@Override
	public OclRoot caseCollectionItem(CollectionItem collectionItem) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseCollectionItem(";
			msg += "collectionItem = " + collectionItem;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(collectionItem) != null) {
			result = myEnvironment.getCachedResult(collectionItem);
		}

		/* Else compute the result. */
		else {

			result = doSwitch((EObject) collectionItem.getItem());

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(collectionItem, result);
			}
			// no else
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseCollectionItem(CollectionItem) - end ";
			msg += "- return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseCollectionLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp)
	 */
	@Override
	public OclRoot caseCollectionLiteralExp(
			CollectionLiteralExp collectionLiteralExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseCollectionLiteralExp(";
			msg += "collectionLiteralExp = " + collectionLiteralExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}

		OclRoot result;
		CollectionKind collectionKind;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(collectionLiteralExp) != null) {
			result = myEnvironment.getCachedResult(collectionLiteralExp);
		}

		else {

			Iterator<CollectionLiteralPart> literalPartIt;
			List<OclRoot> resultParts;

			literalPartIt = collectionLiteralExp.getPart().iterator();
			resultParts = new ArrayList<OclRoot>();

			/* Iterate through all literal parts and compute their results. */
			while (literalPartIt.hasNext()) {

				CollectionLiteralPart aPart;

				aPart = literalPartIt.next();

				/* Check if the part is a collection item. */
				if (aPart instanceof CollectionItem) {
					resultParts.add(doSwitch((EObject) aPart));
				}

				/* Else the part must be a collection range. */
				else if (aPart instanceof CollectionRange) {

					OclInteger currentElement;
					OclInteger lastElement;

					/*
					 * Get the first and the last element of the collection range.
					 */
					currentElement =
							(OclInteger) doSwitch((EObject) ((CollectionRange) aPart)
									.getFirst());
					lastElement =
							(OclInteger) doSwitch((EObject) ((CollectionRange) aPart)
									.getLast());

					while (currentElement.isLessEqual(lastElement).isTrue()) {
						resultParts.add(currentElement);
						currentElement =
								currentElement.add(this.myStandardLibraryFactory
										.createOclInteger(1));
					}
				}
			}

			collectionKind = collectionLiteralExp.getKind();

			/* Create the result depending on the kind of given collection. */
			if (collectionKind.equals(CollectionKind.SET)) {

				ArrayList<OclRoot> tempList;

				tempList = new ArrayList<OclRoot>();

				for (OclRoot anElement : resultParts) {
					if (!tempList.contains(anElement)) {
						tempList.add(anElement);
					}
					// no else.
				}

				result = this.myStandardLibraryFactory.createOclSet(tempList);
			}

			else if (collectionKind.equals(CollectionKind.ORDERED_SET)) {

				ArrayList<OclRoot> tempList;

				tempList = new ArrayList<OclRoot>();

				for (OclRoot anElement : resultParts) {
					if (!tempList.contains(anElement)) {
						tempList.add(anElement);
					}
					// no else.
				}

				result = this.myStandardLibraryFactory.createOclOrderedSet(tempList);
			}

			else if (collectionKind.equals(CollectionKind.SEQUENCE)) {
				result = this.myStandardLibraryFactory.createOclSequence(resultParts);
			}

			else if (collectionKind.equals(CollectionKind.BAG)) {
				result = this.myStandardLibraryFactory.createOclBag(resultParts);
			}

			else {
				String msg;

				msg =
						"UnknownKind of collection in CollectionLiteralExp. Kind was "
								+ collectionKind;
				result = this.myStandardLibraryFactory.createOclInvalid(msg);
			}

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(collectionLiteralExp, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseCollectionLiteralExp(CollectionLiteralExp) - end";
			msg += " - return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseEnumLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp)
	 */
	@Override
	public OclRoot caseEnumLiteralExp(EnumLiteralExp anEnumLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseEnumLiteralExp(EnumLiteralExp) - start");
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(anEnumLiteralExp) != null) {
			result = myEnvironment.getCachedResult(anEnumLiteralExp);
		}

		/* Else compute the result. */
		else {
			IModelInstanceEnumerationLiteral miEnumerationLiteral;

			miEnumerationLiteral =
					myEnvironment.getModelInstance().findEnumerationLiteral(
							anEnumLiteralExp.getReferredEnumLiteral());

			result =
					this.myOclInstanceAdapterFactory
							.createOclEnumLiteral(miEnumerationLiteral);

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(anEnumLiteralExp, result);
			}
			// no else.
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseEnumLiteralExp(EnumLiteralExp)"
					+ " - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseExpressionInOcl
	 * (tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl)
	 */
	@Override
	public OclRoot caseExpressionInOcl(ExpressionInOcl expressionInOcl) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseExpressionInOcl(";
			msg += "expressionInOcl = " + expressionInOcl;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = doSwitch((EObject) expressionInOcl.getBodyExpression());

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseExpressionInOcl(ExpressionInOcl) - end ";
			msg += "- return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIfExp(tudresden.ocl20.pivot.essentialocl.expressions.IfExp)
	 */
	@Override
	public OclRoot caseIfExp(IfExp ifExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseIfExp(";
			msg += "ifExp = " + ifExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled && myEnvironment.getCachedResult(ifExp) != null) {
			result = myEnvironment.getCachedResult(ifExp);
		}

		/* Else compute the result. */
		else {
			OclRoot condition;
			OclRoot thenStatement;
			OclRoot elseStatement;

			condition = doSwitch((EObject) ifExp.getCondition());

			thenStatement = doSwitch((EObject) ifExp.getThenExpression());

			elseStatement = doSwitch((EObject) ifExp.getElseExpression());

			if (condition instanceof OclBoolean) {
				result =
						((OclBoolean) condition).ifThenElse(thenStatement, elseStatement);
			}
			// no else.

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(ifExp, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseIfExp(IfExp) - end ";
			msg += "- return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIntegerLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp)
	 */
	public OclRoot caseIntegerLiteralExp(IntegerLiteralExp intLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseIntegerLiteralExp(";
			msg += "intLiteralExp = " + intLiteralExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}

		OclRoot result = null;

		/* Eventually use the cache to compute the result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(intLiteralExp) != null) {

			result = myEnvironment.getCachedResult(intLiteralExp);
		}

		/* Else interpret the result. */
		else {

			int intValue;

			intValue = intLiteralExp.getIntegerSymbol();

			result = this.myStandardLibraryFactory.createOclInteger(intValue);

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(intLiteralExp, result);
			}
			// no else.

		}
		// end else.

		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseIntegerLiteralExp(IntegerLiteralExp) - end - ";
			msg += "return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseInvalidLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp)
	 */
	@Override
	public OclRoot caseInvalidLiteralExp(InvalidLiteralExp invalidLiteralExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseInvalidLiteralExp(";
			msg += "InvalidLiteralExp";
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		result =
				this.myStandardLibraryFactory.createOclInvalid("InvalidLiteralExp");

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseInvalidLiteralExp(InvalidLiteralExp) - end ";
			msg += "- return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIterateExp (tudresden.ocl20.pivot.essentialocl.expressions.IterateExp)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public OclRoot caseIterateExp(IterateExp interateExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseIterateExp(";
			msg += "interateExp = " + interateExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled && myEnvironment.getCachedResult(interateExp) != null) {
			result = myEnvironment.getCachedResult(interateExp);
		}

		/* Else compute the result. */
		else {
			OclRoot source;
			OclCollection<OclRoot> col;

			source = doSwitch((EObject) interateExp.getSource());

			if (source instanceof OclCollection) {
				col = (OclCollection<OclRoot>) source;
			}

			else {
				col = source.asSet();
			}

			/* Reset the accumulator variable in the environment. */
			myEnvironment.addVar(interateExp.getResult().getQualifiedName(), null);

			result =
					evaluateIterate(interateExp.getBody(), col,
							interateExp.getIterator(), col.getIterator(), interateExp
									.getResult().getQualifiedName());

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(interateExp, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseIterateExp(IterateExp) - end ";
			msg += "- return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIteratorExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp)
	 */
	@SuppressWarnings("unchecked")
	public OclRoot caseIteratorExp(IteratorExp anIteratorExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseIteratorExp(";
			msg += "anIteratorExp = " + anIteratorExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(anIteratorExp) != null) {
			result = myEnvironment.getCachedResult(anIteratorExp);
		}

		/* Else compute the result. */
		else {

			List<Variable> allIterators;

			OclExpression body;
			OclRoot source;

			OclCollection<OclRoot> sourceCollection;
			OclIterator<OclRoot> sourceIterator;

			String iteratorName;
			Type resultType;

			allIterators = anIteratorExp.getIterator();

			/* Compute the result of the source. */
			source = doSwitch((EObject) anIteratorExp.getSource());

			if (source instanceof OclCollection) {
				sourceCollection = (OclCollection<OclRoot>) source;
			}

			else {
				sourceCollection = source.asSet();
			}

			sourceIterator = (OclIterator<OclRoot>) sourceCollection.getIterator();

			body = anIteratorExp.getBody();

			iteratorName = anIteratorExp.getName();
			resultType = anIteratorExp.getType();

			/* Check which type of iterator shall be used. */
			if (iteratorName.equals("exists")) {
				result =
						evaluateExists(body, sourceCollection, allIterators, sourceIterator);
			}

			else if (iteratorName.equals("select")) {

				/* Select can only use one iterator variable. */
				if (allIterators.size() > 1) {
					String msg;
					msg = "select() may have at most one iterator variable";

					if (LOGGER.isInfoEnabled()) {
						LOGGER.warn(msg);
					}
					// no else.

					result = this.myStandardLibraryFactory.createOclInvalid(msg);
				}

				else {
					result =
							evaluateSelect(body, sourceCollection, allIterators.get(0),
									resultType);
				}
			}

			else if (anIteratorExp.getName().equals("forAll")) {
				result =
						evaluateForAll(body, sourceCollection, allIterators, sourceIterator);

			}
			else if (anIteratorExp.getName().equals("isUnique")) {

				/* IsUnique can only use one iterator variable. */
				if (allIterators.size() > 1) {
					String msg;
					msg = "isUnique() may have at most one iterator variable";

					if (LOGGER.isInfoEnabled()) {
						LOGGER.warn(msg);
					}
					// no else.

					result = this.myStandardLibraryFactory.createOclInvalid(msg);
				}

				else {
					result =
							evaluateIsUnique(body, sourceCollection, allIterators.get(0));
				}
			}

			else if (anIteratorExp.getName().equals("any")) {

				/* Any can only use one iterator variable. */
				if (allIterators.size() > 1) {
					String msg;
					msg = "any() may have at most one iterator variable";

					if (LOGGER.isInfoEnabled()) {
						LOGGER.warn(msg);
					}
					// no else.

					result = this.myStandardLibraryFactory.createOclInvalid(msg);
				}

				else {
					result =
							evaluateAny(body, sourceCollection, allIterators.get(0),
									anIteratorExp.getType());
				}
			}

			else if (anIteratorExp.getName().equals("one")) {

				/* One can only use one iterator variable. */
				if (allIterators.size() > 1) {
					String msg;
					msg = "one() may have at most one iterator variable";

					if (LOGGER.isInfoEnabled()) {
						LOGGER.warn(msg);
					}
					// no else.

					result = this.myStandardLibraryFactory.createOclInvalid(msg);
				}

				else {
					result = evaluateOne(body, sourceCollection, allIterators.get(0));
				}
			}

			else if (anIteratorExp.getName().equals("reject")) {

				/* Reject can only use one iterator variable. */
				if (allIterators.size() > 1) {
					String msg;
					msg = "reject() may have at most one iterator variable";

					if (LOGGER.isInfoEnabled()) {
						LOGGER.warn(msg);
					}
					// no else.

					result = this.myStandardLibraryFactory.createOclInvalid(msg);
				}

				else {
					result =
							evaluateReject(body, sourceCollection, allIterators.get(0),
									resultType);
				}
			}

			else if (anIteratorExp.getName().equals("sortedBy")) {

				/* SortedBy can only use one iterator variable. */
				if (allIterators.size() > 1) {
					String msg;
					msg = "sortedBy() may have at most one iterator variable";

					if (LOGGER.isInfoEnabled()) {
						LOGGER.warn(msg);
					}
					// no else.

					result = this.myStandardLibraryFactory.createOclInvalid(msg);
				}

				else {
					result =
							evaluateSortedBy(body, sourceCollection, allIterators.get(0),
									resultType);
				}
			}

			else if (anIteratorExp.getName().equals("collectNested")) {

				/* CollectNested can only use one iterator variable. */
				if (allIterators.size() > 1) {
					String msg;
					msg = "collectNested() may have at most one iterator variable ";

					if (LOGGER.isInfoEnabled()) {
						LOGGER.warn(msg);
					}
					// no else.

					result = this.myStandardLibraryFactory.createOclInvalid(msg);
				}

				else {
					result =
							evaluateCollectNested(body, sourceCollection,
									allIterators.get(0), resultType);
				}
			}

			else if (anIteratorExp.getName().equals("collect")) {

				/* Collect can only use one iterator variable. */
				if (allIterators.size() > 1) {
					String msg;

					msg = "collect() may have at most one iterator variable";
					result = this.myStandardLibraryFactory.createOclInvalid(msg);
				}

				else {
					result =
							evaluateCollectNested(body, sourceCollection,
									allIterators.get(0), resultType);
				}

				/* Eventually flatten the result. */
				if (result instanceof OclCollection) {
					result = ((OclCollection) result).flatten();
				}
				// no else.
			}
			// no else.

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(anIteratorExp, result);
			}
			// no else.
		}

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseIteratorExp(IteratorExp) - end ";
			msg += "- return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseLetExp(tudresden.ocl20.pivot.essentialocl.expressions.LetExp)
	 */
	@Override
	public OclRoot caseLetExp(LetExp aLetExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseLetExp(";
			msg += "aLetExp = " + aLetExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (this.isCachingEnabled
				&& this.myEnvironment.getCachedResult(aLetExp) != null) {
			result = this.myEnvironment.getCachedResult(aLetExp);
		}

		/* Else compute the result. */
		else {
			result = doSwitch((EObject) aLetExp.getIn());

			/*
			 * Remove the variable from the environment because it is invalid outside
			 * the scope of the let expression.
			 */
			this.myEnvironment.addVar(aLetExp.getVariable().getQualifiedName(), null);

			/* Eventually cache the result. */
			if (this.isCachingEnabled && !this.isModelAccessNeeded) {
				this.myEnvironment.cacheResult(aLetExp, result);
			}
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseLetExp(LetExp) - end ";
			msg += "- return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseOperationCallExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp)
	 */
	public OclRoot caseOperationCallExp(OperationCallExp anOperationCallExp) {

		OclRoot result;

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseOperationCallExp(";
			msg += "anOperationCallExp = " + anOperationCallExp;
			msg += ") - enter";

			LOGGER.debug(msg);
		}
		// no else.

		result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(anOperationCallExp) != null) {
			result = myEnvironment.getCachedResult(anOperationCallExp);
		}

		/* Else compute the result. */
		else {
			OclRoot[] parameters;
			OclRoot source;

			Constraint body;

			Operation referredOperation;
			String opName;

			ListIterator<OclExpression> argIterator;
			List<Parameter> opParams;

			parameters = new OclRoot[anOperationCallExp.getArgument().size()];
			referredOperation = anOperationCallExp.getReferredOperation();

			/*
			 * If the special operation atPre is invoked, the referred operation can
			 * be null.
			 */
			if (referredOperation == null) {
				opName = anOperationCallExp.getName();
			}

			/* Else get the referred operation's name. */
			else {
				opName = referredOperation.getName();
				/*
				 * Eventually map the operation name from the name of essential OCL to
				 * the name of the standard library.
				 */
				opName =
						this.myEnvironment.getModelInstance().getOperationName(opName,
								parameters.length + 1);
			}

			source = null;

			/* Get the source type for static operations. */
			if (referredOperation != null && referredOperation.isStatic()) {

				/* Find the source type. */
				IModelInstanceTypeObject modelInstanceTypeObject;
				modelInstanceTypeObject =
						this.myEnvironment.getModelInstance().findModelTypeObject(
								anOperationCallExp.getSourceType());

				if (modelInstanceTypeObject != null) {
					source =
							this.myOclInstanceAdapterFactory
									.createOclType(modelInstanceTypeObject);
				}

				else {
					String msg;

					msg = "The Type " + anOperationCallExp.getSourceType();
					msg += " was not adapted by the IModelInstance. ";

					if (LOGGER.isInfoEnabled()) {
						LOGGER.warn(msg);
					}
					// no else.

					source =
							this.myStandardLibraryFactory.createOclUndefined(
									anOperationCallExp.getSourceType(), msg);
				}
			}

			/* Compute the source type for non-static operations. */
			else {
				source = doSwitch((EObject) anOperationCallExp.getSource());
			}

			body = null;

			/*
			 * Operations can be implemented by a body expression or a definition. Try
			 * to get such an expression if it has been defined.
			 */
			if (referredOperation != null) {
				body =
						this.myEnvironment.getConstraint(referredOperation
								.getQualifiedName());
			}
			// no else.

			/*
			 * If a body expression or definition has been defined, create a new local
			 * interpreter.
			 */
			if (body != null) {
				this.pushLocalEnvironment();
			}
			// no else.

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
				OclRoot param;
				String parameterName;

				exp = argIterator.next();
				param = doSwitch((EObject) exp);
				parameters[argIterator.previousIndex()] = param;

				parameterName = opParams.get(argIterator.previousIndex()).getName();

				/*
				 * Eventually add the variables to the local interpreter if a body
				 * expression or definition has been defined.
				 */
				if (body != null) {
					this.setEnviromentVariable(parameterName, param);
				}
				// no else.
			}

			/* Handle special operations. */

			/* The operation atPre has to store the atPre value. */
			if (opName.equals("atPre")) {

				if (this.isPreparation) {
					String msg;

					this.myEnvironment.savePostconditionValue(anOperationCallExp, source);

					msg = "@Pre is not available during constraint preparation.";
					result =
							this.myStandardLibraryFactory.createOclUndefined(
									anOperationCallExp.getType(), msg);
				}

				else {
					result = this.myEnvironment.getPostconditionValue(anOperationCallExp);

					if (result == null) {
						String msg;
						msg =
								"@Pre value of " + anOperationCallExp + " has not been found.";

						result =
								this.myStandardLibraryFactory.createOclUndefined(
										anOperationCallExp.getType(), msg);
					}
				}
			}

			/*
			 * The operation oclIsNew has to store some values. To compute all new
			 * values.
			 */
			else if (opName.equals("oclIsNew")) {

				if (this.isPreparation) {
					String msg;

					this.myEnvironment.savePostconditionValue(anOperationCallExp,
							this.myStandardLibraryFactory
									.createOclBoolean(source instanceof OclVoid));

					msg = "oclIsNew() is not available during preparation.";
					result =
							this.myStandardLibraryFactory.createOclUndefined(
									anOperationCallExp.getType(), msg);
				}

				else {
					result = this.myEnvironment.getPostconditionValue(anOperationCallExp);
				}
			}

			/*
			 * If allInstances for some reason is not possible for standard library
			 * null is returned. In that case modelInstance.getObjectsOfKind() is
			 * used.
			 */
			else if (opName.equals("allInstances")) {
				List<String> typePath;
				String typeName;

				List<IModelInstanceElement> objectInstances;

				List<OclRoot> instances;

				typeName = anOperationCallExp.getSourceType().getQualifiedName();

				/*
				 * Split the canonical name of the source type into its packages.
				 */
				if (StringUtils.isEmpty(typeName)) {
					typePath = Collections.emptyList();
				}
				else {
					typePath = Arrays.asList(typeName.split("::"));
				}

				objectInstances =
						new ArrayList<IModelInstanceElement>(this.myEnvironment.getModelInstance()
								.getObjectsOfType(typePath));

				instances = new ArrayList<OclRoot>(objectInstances.size());

				/*
				 * Iterate through the instances and add them to the instances list.
				 */
				for (IModelInstanceElement anInstance : objectInstances) {
					instances.add(this.myOclInstanceAdapterFactory
							.createOclRoot(anInstance));
				}

				result = this.myStandardLibraryFactory.createOclSet(instances);
			}

			/* The standard case. Invoke the operation and compute the result. */
			else {

				if (body == null) {

					if (source.isOclUndefined().isTrue()) {
						result =
								this.myStandardLibraryFactory.createOclUndefined(
										anOperationCallExp.getType(), source.getUndefinedreason());
					}

					else {
						try {
							result = source.invokeOperation(opName, parameters);
						}

						catch (NoSuchMethodException e) {
							String msg;

							msg = "Error during method invocation. ";
							msg += "OperationCallExp was " + anOperationCallExp;

							LOGGER.error(msg, e);

							result =
									this.myStandardLibraryFactory.createOclUndefined(
											anOperationCallExp.getType(), msg);
						}
					}
				}

				else {
					result =
							this.interpretConstraint(body, this.myCurrentModelObject)
									.getResult();

					this.popLocalEnvironment();
				}
			}
			// end else.

			/*
			 * If the result or its source is an OclObject, the result cannot be
			 * cached.
			 */
			if ((result instanceof OclObject) || (source instanceof OclObject)) {
				this.isModelAccessNeeded = true;
			}

		}
		// end else.

		/* Eventually cache the result. */
		if (this.isCachingEnabled && !isModelAccessNeeded) {
			this.myEnvironment.cacheResult(anOperationCallExp, result);
		}
		// no else.

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String log;

			log = "caseOperationCallExp(OperationCallExp) - exit - ";
			log += "return value=" + result;

			LOGGER.debug(log);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #casePropertyCallExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp)
	 */
	public OclRoot casePropertyCallExp(PropertyCallExp propertyCallExp) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "casePropertyCallExp(";
			msg += "propertyCallExp = " + propertyCallExp;
			msg += ") - enter";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result;
		result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(propertyCallExp) != null) {
			result = myEnvironment.getCachedResult(propertyCallExp);
		}

		/* Else compute the result. */
		else {

			OclRoot sourceObject;

			String qualifiedPropertyName;
			String propertyName;

			/* Switch to local environment and cache the global environment. */
			this.pushLocalEnvironment();

			sourceObject = null;

			/* Handle static properties. */
			if (propertyCallExp.getReferredProperty().isStatic()) {

				/* Find the source type. */
				IModelInstanceTypeObject modelInstanceTypeObject;
				modelInstanceTypeObject =
						this.myEnvironment.getModelInstance().findModelTypeObject(
								propertyCallExp.getSourceType());

				if (modelInstanceTypeObject != null) {
					sourceObject =
							this.myOclInstanceAdapterFactory
									.createOclType(modelInstanceTypeObject);
				}

				else {
					String msg;

					msg = "The Type " + propertyCallExp.getSourceType();
					msg += " was not adapted by the IModelInstance. ";

					if (LOGGER.isInfoEnabled()) {
						LOGGER.warn(msg);
					}
					// no else.

					sourceObject =
							this.myStandardLibraryFactory.createOclUndefined(propertyCallExp
									.getSourceType(), msg);
				}
			}

			/* Else interpret the sourceExp. */
			else {
				sourceObject = doSwitch((EObject) propertyCallExp.getSource());
			}

			qualifiedPropertyName =
					propertyCallExp.getReferredProperty().getQualifiedName();
			propertyName = propertyCallExp.getReferredProperty().getName();

			/* Check if the property is a derived one. */
			if (myEnvironment.getConstraint(qualifiedPropertyName + "-derive") != null) {

				Constraint deriveConstraint;

				deriveConstraint =
						myEnvironment.getConstraint(qualifiedPropertyName + "-derive");

				result =
						this.interpretConstraint(deriveConstraint, myCurrentModelObject)
								.getResult();
			}

			/* Else check if the property has a initial expression. */
			else if (myEnvironment.getConstraint(qualifiedPropertyName + "-initial") != null) {

				Constraint initialConstaint;

				initialConstaint =
						myEnvironment.getConstraint(qualifiedPropertyName + "-initial");

				result =
						this.interpretConstraint(initialConstaint, myCurrentModelObject)
								.getResult();
				this.isModelAccessNeeded = this.isModelAccessNeeded();
			}

			/* Else check if the property has a body expression. */
			else if (myEnvironment.getConstraint(qualifiedPropertyName) != null) {

				Constraint bodyConstraint;

				bodyConstraint = myEnvironment.getConstraint(qualifiedPropertyName);

				result =
						this.interpretConstraint(bodyConstraint, myCurrentModelObject)
								.getResult();
				this.isModelAccessNeeded = this.isModelAccessNeeded();
			}

			/* Else the property value must be set in the model instance. */
			else {
				try {
					Type propertyType;

					propertyType = propertyCallExp.getType();

					if (propertyType instanceof OrderedSetType) {
						result = sourceObject.getPropertyValueAsOrderedSet(propertyName);
					}

					else if (propertyType instanceof SequenceType) {
						result = sourceObject.getPropertyValueAsSequence(propertyName);
					}

					else if (propertyType instanceof SetType) {
						result = sourceObject.getPropertyValueAsSet(propertyName);
					}

					else if (propertyType instanceof BagType) {
						result = sourceObject.getPropertyValueAsBag(propertyName);
					}

					else {
						result = sourceObject.getPropertyValue(propertyName);
					}
				}

				catch (NoSuchFieldException e) {
					String msg;

					msg = "Field not found during interpretation of propertyCallExp. ";
					msg += "The fields name was " + propertyName + ". ";
					msg += "The source object was " + sourceObject + ". ";

					LOGGER.error(msg, e);

					result = this.myStandardLibraryFactory.createOclInvalid(msg);
				}

				catch (IllegalAccessException e) {
					String msg;

					msg =
							"Field could not accessed during interpretation of propertyCallExp. ";
					msg += "The fields name was " + propertyName + ". ";
					msg += "The source object was " + sourceObject + ". ";

					LOGGER.error(msg, e);

					result = this.myStandardLibraryFactory.createOclInvalid(msg);
				}
			}

			/* If the result is an OclObject, the result can not be cached. */
			if (result instanceof OclObject) {
				this.isModelAccessNeeded = true;
			}
			// no else.

			// Eventually cache the result.
			if (this.isCachingEnabled && !this.isModelAccessNeeded) {
				this.myEnvironment.cacheResult(propertyCallExp, result);
			}
			// no else.

			/* Switch to global environment again. */
			this.popLocalEnvironment();
		}
		// end else.

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "casePropertyCallExp(PropertyCallExp) - end ";
			msg += "- return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseRealLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp)
	 */
	@Override
	public OclRoot caseRealLiteralExp(RealLiteralExp realLiteralExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseRealLiteralExp(";
			msg += "realLiteralExp = " + realLiteralExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(realLiteralExp) != null) {
			result = myEnvironment.getCachedResult(realLiteralExp);
		}

		/* Else compute the result. */
		else {
			result =
					this.myStandardLibraryFactory.createOclReal(realLiteralExp
							.getRealSymbol());

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(realLiteralExp, result);
			}
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseRealLiteralExp(RealLiteralExp) - end ";
			msg += "- return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseStringLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp)
	 */
	@Override
	public OclRoot caseStringLiteralExp(StringLiteralExp aStringLiteralExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseStringLiteralExp(";
			msg += "aStringLiteralExp = " + aStringLiteralExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(aStringLiteralExp) != null) {
			result = myEnvironment.getCachedResult(aStringLiteralExp);
		}

		/* Else compute the result. */
		else {
			result =
					this.myStandardLibraryFactory.createOclString(aStringLiteralExp
							.getStringSymbol());

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(aStringLiteralExp, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseStringLiteralExp(StringLiteralExp) - end ";
			msg += "- return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseTupleLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp)
	 */
	@Override
	public OclRoot caseTupleLiteralExp(TupleLiteralExp aTupleLiteralExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseTupleLiteralExp(";
			msg += "aTupleLiteralExp = " + aTupleLiteralExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(aTupleLiteralExp) != null) {
			result = myEnvironment.getCachedResult(aTupleLiteralExp);
		}

		/* Else compute the result. */
		else {

			List<String> partNames;
			List<OclRoot> partValues;

			partNames = new ArrayList<String>(aTupleLiteralExp.getPart().size());
			partValues = new ArrayList<OclRoot>(aTupleLiteralExp.getPart().size());

			for (TupleLiteralPart aLiteralPart : aTupleLiteralExp.getPart()) {
				partNames.add(aLiteralPart.getProperty().getName());
				partValues.add(doSwitch((EObject) aLiteralPart));
			}

			result =
					this.myStandardLibraryFactory.createOclTuple(partNames, partValues);

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(aTupleLiteralExp, result);
			}
		}

		/* Eventually log the exit of this method. */

		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseTupleLiteralExp(TupleLiteralExp) - end ";
			msg += "- return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseTupleLiteralPart
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart)
	 */
	@Override
	public OclRoot caseTupleLiteralPart(TupleLiteralPart tupleLiteralPart) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseTupleLiteralPart(";
			msg += "tupleLiteralPart = " + tupleLiteralPart;
			msg += ") - start";

			LOGGER.debug(msg);
		}

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(tupleLiteralPart) != null) {
			result = myEnvironment.getCachedResult(tupleLiteralPart);
		}

		/* Else compute the result. */
		else {
			result = doSwitch((EObject) tupleLiteralPart.getValue());

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(tupleLiteralPart, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseTupleLiteralPart(TupleLiteralPart) - end ";
			msg += "- return value=" + result;

			LOGGER.debug(msg);
		}
		// no else;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseTypeLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp)
	 */
	@Override
	public OclRoot caseTypeLiteralExp(TypeLiteralExp typeLiteralExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseTypeLiteralExp(";
			msg += "typeLiteralExp = " + typeLiteralExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(typeLiteralExp) != null) {
			result = myEnvironment.getCachedResult(typeLiteralExp);
		}

		/* Else compute the result. */
		else {

			result =
					this.myStandardLibraryFactory.createOclType(typeLiteralExp
							.getReferredType(), this.myEnvironment.getModelInstance());

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(typeLiteralExp, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseTypeLiteralExp(TypeLiteralExp) - end ";
			msg += "- return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseVariable(tudresden.ocl20.pivot.essentialocl.expressions.Variable)
	 */
	public OclRoot caseVariable(Variable aVariable) {

		OclRoot result;

		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseVariable(";
			msg += "aVariable = " + aVariable;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		result = null;

		/* Eventually use a caches result. */
		if (isCachingEnabled && myEnvironment.getCachedResult(aVariable) != null) {
			result = myEnvironment.getCachedResult(aVariable);
		}

		else {

			OclExpression initExp;
			OclRoot initValue;

			/* Eventually interpret the initExpression. */
			initExp = aVariable.getInitExpression();
			initValue = null;

			if (initExp != null) {
				initValue = doSwitch((EObject) aVariable.getInitExpression());
			}

			/*
			 * Eventually get the value of the Variable from the environment. For
			 * example if the variable was prepared like the variables 'self' or
			 * 'result'.
			 */
			result = myEnvironment.getVar(aVariable.getName());

			/*
			 * Else initialized the Variable and add the Variable to the environment.
			 */
			if (result == null) {

				if (initValue != null) {
					result = initValue;
					myEnvironment.addVar(aVariable.getQualifiedName(), result);
				}

				else {

					String msg;

					msg = "Unknown Variable:" + aVariable.getName() + ".";

					if (!this.isPreparation) {
						LOGGER.warn(msg);
					}
					// no else.

					result =
							this.myStandardLibraryFactory.createOclUndefined(aVariable
									.getType(), msg);
				}
			}

			/* OclObject can't be cached. */
			if (result instanceof OclObject) {
				isModelAccessNeeded = true;
			}
			// no else.

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(aVariable, result);
			}
			// no else.
		}

		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseVariable(Variable) - end - ";
			msg += "return value = " + result;

			LOGGER.debug(msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseVariableExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.VariableExp)
	 */
	@Override
	public OclRoot caseVariableExp(VariableExp variableExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseVariableExp(";
			msg += "variableExp = " + variableExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result;

		result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled && myEnvironment.getCachedResult(variableExp) != null) {
			result = myEnvironment.getCachedResult(variableExp);
		}

		/* Else compute the result. */
		else {
			result = doSwitch((EObject) variableExp.getReferredVariable());

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(variableExp, result);
			}
			// no else.
		}

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseVariableExp(VariableExp) - end ";
			msg += "- return value = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseUndefinedLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp)
	 */
	@Override
	public OclRoot caseUndefinedLiteralExp(UndefinedLiteralExp undefinedLiteralExp) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseUndefinedLiteralExp(";
			msg += "undefinedLiteralExp = " + undefinedLiteralExp;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (isCachingEnabled
				&& myEnvironment.getCachedResult(undefinedLiteralExp) != null) {

			result = myEnvironment.getCachedResult(undefinedLiteralExp);
		}

		/* Else compute the result. */
		else {
			Type resultType;
			resultType = undefinedLiteralExp.getType();

			result =
					this.myStandardLibraryFactory.createOclUndefined(resultType,
							"UndefinedLiteralExp");

			/* Eventually cache the result. */
			if (isCachingEnabled && !isModelAccessNeeded) {
				myEnvironment.cacheResult(undefinedLiteralExp, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "caseUndefinedLiteralExp(UndefinedLiteralExp) - end ";
			msg += "- return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method which adds the parameters of an {@link Operation} as
	 * context of a {@link Constraint} that shall be interpreted to the current
	 * {@link IInterpretationEnvironment}.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} for which the arguments shall be prepared.
	 * @param parameters
	 *          The parameters (as array of {@link IModelInstanceElement}) which shall be
	 *          added.
	 */
	private void addParametersToEnvironment(Constraint aConstraint,
			IModelInstanceElement[] parameters) {

		ExpressionInOcl oclExpression;
		int index;

		oclExpression = (ExpressionInOcl) aConstraint.getSpecification();
		index = 0;

		/* Eventually add parameters to the environment. */
		for (Variable aVariable : oclExpression.getParameter()) {

			if (parameters[index] != null) {
				this.setEnviromentVariable(aVariable.getName(),
						this.myOclInstanceAdapterFactory.createOclRoot(parameters[index]));
			}

			else {
				String msg;
				OclRoot value;

				msg = "Parameter " + aVariable.getName() + " was undefined";

				value =
						this.myStandardLibraryFactory.createOclUndefined(aVariable
								.getType(), msg);

				this.setEnviromentVariable(aVariable.getName(), value);
			}

			index++;
		}
		// end for.
	}

	/**
	 * Returns any element in the source collection for which body evaluates to
	 * true. If there is more than one element for which body is true, one of them
	 * is returned. There must be at least one element fulfilling body, otherwise
	 * the result of this IteratorExp is null.
	 * 
	 * @param body
	 *          the body expression to be evaluated
	 * @param source
	 *          the collection representing the source expression of the iteration
	 * @param iterator
	 *          the iterator (any may have at most one iterator variable.)
	 * @param resultType
	 *          The result {@link Type} of this any interator expression.
	 * 
	 * @return the result of the iteration
	 */
	@SuppressWarnings("unchecked")
	private OclRoot evaluateAny(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "evaluateIsUnique(";
			msg += "body = " + body;
			msg += ", source = " + source;
			msg += ", iterator = " + iterator;
			msg += ", resultType = " + resultType;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result;
		OclIterator<OclRoot> sourceIt;

		result = null;
		sourceIt = source.getIterator();

		/*
		 * Iterate over the source and evaluate the body expression for all
		 * elements. If it is true for any element, the result the specific element.
		 */
		while (sourceIt.hasNext().isTrue()) {

			OclRoot anElement;
			OclBoolean bodyResult;

			/* Add an element to the environment. */
			anElement = sourceIt.next();
			myEnvironment.addVar(iterator.getQualifiedName(), anElement);

			/* Compute the body result. */
			bodyResult = (OclBoolean) doSwitch((EObject) body);

			if (bodyResult.isTrue()) {
				result = anElement;
				break;
			}
			// no else.
		}
		// end while.

		if (result == null) {
			String msg;

			msg = "Iterator any() resulted in undefined.";
			result =
					this.myStandardLibraryFactory.createOclUndefined(resultType, msg);
		}
		// no else.

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {

			String msg;

			msg = "evaluateAny(OclExpression, OclCollection<OclRoot>";
			msg += ", Variable) - end - return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * The collection of elements which results from applying body to every member
	 * of the source set.
	 * </p>
	 * 
	 * @param body
	 *          The body expression to be evaluated.
	 * @param source
	 *          The collection representing the source expression of the
	 *          iteration.
	 * @param iterator
	 *          The iterator (collectNested may have at most one iterator
	 *          variable.).
	 * @param resultType
	 *          The result type (set, sequence, bag, orderedSet).
	 * 
	 * @return The result of the iteration.
	 */
	@SuppressWarnings("unchecked")
	private OclRoot evaluateCollectNested(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "evaluateIsUnique(";
			msg += "body = " + body;
			msg += ", source = " + source;
			msg += ", iterator = " + iterator;
			msg += ", resultType = " + resultType;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result;

		List<OclRoot> resultList;
		OclIterator<OclRoot> sourceIt;

		resultList = new ArrayList<OclRoot>();
		sourceIt = source.getIterator();

		/*
		 * Iterate over the source and collect the body expression for all elements.
		 */
		while (sourceIt.hasNext().isTrue()) {

			OclRoot anElement;
			OclRoot bodyResult;

			/* Get the next element and add it to the environment. */
			anElement = sourceIt.next();
			myEnvironment.addVar(iterator.getQualifiedName(), anElement);

			/* Compute the body expression for an element. */
			bodyResult = doSwitch((EObject) body);

			resultList.add(bodyResult);
		}

		/* Compute the result type depending on the given result type. */
		if (resultType instanceof BagType) {
			result = this.myStandardLibraryFactory.createOclBag(resultList);
		}

		else if (resultType instanceof SequenceType) {
			result = this.myStandardLibraryFactory.createOclSequence(resultList);
		}

		else {
			String msg;
			msg =
					"The ResultType of a collectNested Iterator should by a Sequence or Bag.";
			msg += " But was " + resultType.getQualifiedName();

			if (LOGGER.isInfoEnabled()) {
				LOGGER.warn(msg);
			}
			// no else.

			result = this.myStandardLibraryFactory.createOclInvalid(msg);
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {

			String msg;

			msg = "evaluateCollectNested(OclExpression, OclCollection";
			msg += "<OclRoot>, Variable, Type) - end - result = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * Results in true if body evaluates to true for at least one element in the
	 * source collection.
	 * 
	 * @param body
	 *          the body expression to be evaluated
	 * @param source
	 *          the collection representing the source expression of the iteration
	 * @param iterators
	 *          the iterators
	 * @param it
	 *          the current iterator for the source collection
	 * 
	 * @return the result of the iteration
	 */
	@SuppressWarnings("unchecked")
	private OclRoot evaluateExists(OclExpression body,
			OclCollection<OclRoot> source, List<Variable> iterators,
			OclIterator<OclRoot> it) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "evaluateIsUnique(";
			msg += "body = " + body;
			msg += ", source = " + source;
			msg += ", iterators = " + iterators;
			msg += ", it = " + it;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result;

		/* By default the result is false. */
		result = this.myStandardLibraryFactory.createOclBoolean(false);

		/*
		 * Iterate over the collection and check if at least one element fulfills
		 * the body expression.
		 */
		while (it.hasNext().isTrue()) {

			OclRoot anElement;
			OclBoolean bodyResult;

			/* Add an element to the environment... */
			anElement = it.next();
			myEnvironment.addVar(iterators.get(0).getQualifiedName(), anElement);

			/* ...and compute its body expression. */
			bodyResult = null;

			/*
			 * Eventually recall this method recursively for more iterator variables.
			 */
			if (iterators.size() > 1) {

				List<Variable> tempItList;
				OclIterator<OclRoot> nextIt;

				/*
				 * Remove the firs iterator variable and recall recursively this method
				 * for all other iterator variables.
				 */
				tempItList = new ArrayList<Variable>(iterators);
				tempItList.remove(0);

				nextIt = source.getIterator();
				bodyResult =
						(OclBoolean) evaluateExists(body, source, tempItList, nextIt);
			}

			else {
				bodyResult = (OclBoolean) doSwitch((EObject) body);
			}

			/* If the body result is true, result in true. */
			if (bodyResult.isTrue()) {
				result = this.myStandardLibraryFactory.createOclBoolean(true);
				break;
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {

			String msg;

			msg = "evaluateExists(OclExpression, OclCollection<OclRoot>";
			msg += ", List<Variable>, OclIterator<OclRoot>) - end - result = ";
			msg += result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * Results in true if the body expression evaluates to true for each element
	 * in the source collection; otherwise, result is false.
	 * 
	 * @param body
	 *          the body expression to be evaluated
	 * @param source
	 *          the collection representing the source expression of the iteration
	 * @param iterators
	 *          the iterators
	 * @param it
	 *          the current iterator for the source collection
	 * 
	 * @return the result of the iteration
	 */
	@SuppressWarnings("unchecked")
	private OclRoot evaluateForAll(OclExpression body,
			OclCollection<OclRoot> source, List<Variable> iterators,
			OclIterator<OclRoot> it) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "evaluateIsUnique(";
			msg += "body = " + body;
			msg += ", source = " + source;
			msg += ", iterators = " + iterators;
			msg += ", it = " + it;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result;

		/* By default the result is true. */
		result = this.myStandardLibraryFactory.createOclBoolean(true);

		/* Iterate through the collection. */
		while (it.hasNext().isTrue()) {

			OclRoot anItVariable;
			OclBoolean bodyResult;

			/* Get an iterator variable and add it to the environment. */
			anItVariable = it.next();
			myEnvironment.addVar(iterators.get(0).getQualifiedName(), anItVariable);

			bodyResult = null;

			/*
			 * Check if more than this iterator variables are available and eventually
			 * add them to the environment and compute the result recursively.
			 */
			if (iterators.size() > 1) {
				List<Variable> subIteratorList;
				OclIterator<OclRoot> nextIt;

				subIteratorList = new ArrayList<Variable>(iterators);

				/* Remove the actual iterator. */
				subIteratorList.remove(0);

				/* Get the next iterator and compute the result recursively. */
				nextIt = source.getIterator();
				bodyResult =
						(OclBoolean) evaluateForAll(body, source, subIteratorList, nextIt);
			}

			/*
			 * Else compute the result for this iterator variable and all iterator
			 * variables which were set recursively before.
			 */
			else {
				bodyResult = (OclBoolean) doSwitch((EObject) body);
			}

			/* If the body result is false, return false. */
			if (!bodyResult.isTrue()) {
				result = this.myStandardLibraryFactory.createOclBoolean(false);
				break;
			}
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {

			String msg;

			msg = "evaluateForAll(OclExpression, OclCollection<OclRoot>";
			msg += ", List<Variable>, OclIterator<OclRoot>) - end - result = ";
			msg += result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Results in true if body evaluates to a different value for each element in
	 * the source collection; otherwise, result is false.
	 * </p>
	 * 
	 * @param body
	 *          the body expression to be evaluated
	 * @param source
	 *          the collection representing the source expression of the iteration
	 * @param iterator
	 *          the iterator (isUnique may have at most one iterator variable.)
	 * 
	 * @return the result of the iteration
	 */
	@SuppressWarnings("unchecked")
	private OclRoot evaluateIsUnique(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "evaluateIsUnique(";
			msg += "body = " + body;
			msg += ", source = " + source;
			msg += ", iterator = " + iterator;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result;
		List<OclRoot> resultList;

		OclIterator<OclRoot> sourceIt;

		sourceIt = source.getIterator();
		resultList = new ArrayList<OclRoot>();

		/* By default, the result is true. */
		result = this.myStandardLibraryFactory.createOclBoolean(true);

		/* Iterate over the collection and check if every element is unique. */
		while (sourceIt.hasNext().isTrue()) {

			OclRoot anElement;
			OclRoot bodyResult;

			anElement = sourceIt.next();

			/* Add the element to the environment. */
			myEnvironment.addVar(iterator.getQualifiedName(), anElement);

			/* Compute the body for the set environment. */
			bodyResult = doSwitch((EObject) body);

			/* Check if the result is unique. */
			if (!resultList.contains(bodyResult)) {
				resultList.add(bodyResult);
			}

			else {
				result = this.myStandardLibraryFactory.createOclBoolean(false);
				break;
			}
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {

			String msg;

			msg = "evaluateIsUnique(OclExpression, OclCollection<OclRoot>";
			msg += ", Variable) - end - return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Evaluates the general iterate method. Will be invoked recursively for every
	 * iterator of the iteration.
	 * </p>
	 * 
	 * @param body
	 *          the body expression of the iteration
	 * @param source
	 *          the collection representing the source expression of the iteration
	 * @param iterators
	 *          the iterators
	 * @param it
	 *          the current iterator for the source collection
	 * @param resultVarName
	 *          the name of the result variable
	 * 
	 * @return the result of the iteration
	 */
	@SuppressWarnings("unchecked")
	private OclRoot evaluateIterate(OclExpression body,
			OclCollection<OclRoot> source, List<Variable> iterators,
			OclIterator<OclRoot> it, String resultVarName) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "evaluateIsUnique(";
			msg += "body = " + body;
			msg += ", source = " + source;
			msg += ", iterators = " + iterators;
			msg += ", it = " + it;
			msg += ", resultVarName = " + resultVarName;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Iterate through the iterator. */
		while (it.hasNext().isTrue()) {

			OclRoot activeElement;

			/* Get the next element. */
			activeElement = it.next();

			/* Add the elements variable to the environment. */
			myEnvironment.addVar(iterators.get(0).getQualifiedName(), activeElement);

			/*
			 * If more than one iterators are used, remove the first iterator and
			 * recall this method recursively.
			 */
			if (iterators.size() > 1) {
				List<Variable> allIterators;
				OclIterator<OclRoot> nextIt;

				allIterators = new ArrayList<Variable>(iterators);
				allIterators.remove(0);

				nextIt = (OclIterator<OclRoot>) source.getIterator();

				result =
						evaluateIterate(body, source, allIterators, nextIt, resultVarName);
			}

			/* Else compute the result. */
			else {
				result = doSwitch((EObject) body);
			}

			/* Add the result to the environment. */
			myEnvironment.addVar(resultVarName, result);
		}

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {

			String msg;

			msg = "evaluateIterate(OclExpression, OclCollection<OclRoot>,";
			msg += " List<Variable>, OclIterator<OclRoot>, String) - end";
			msg += " - return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * Results in true if there is exactly one element in the source collection
	 * for which body is true.
	 * 
	 * @param body
	 *          the body expression to be evaluated
	 * @param source
	 *          the collection representing the source expression of the iteration
	 * @param iterator
	 *          the iterator (one may have at most one iterator variable.)
	 * 
	 * @return the result of the iteration
	 */
	@SuppressWarnings("unchecked")
	private OclRoot evaluateOne(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "evaluateOne(";
			msg += "body = " + body;
			msg += ", source = " + source;
			msg += ", iterator = " + iterator;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result;
		boolean oneFoundElement;

		OclIterator<OclRoot> sourceIt;

		result = null;

		sourceIt = source.getIterator();

		/* By default the result is false. */
		oneFoundElement = false;

		/*
		 * Iterate through the source and check if exactly one element fulfills the
		 * body condition.
		 */
		while (sourceIt.hasNext().isTrue()) {

			OclRoot anElement;
			OclRoot bodyResult;
			OclBoolean bodyBooleanResult;

			/* Add the element to the environment and compute the body result. */
			anElement = sourceIt.next();
			myEnvironment.addVar(iterator.getQualifiedName(), anElement);

			bodyResult = doSwitch((EObject) body);

			if (bodyResult instanceof OclBoolean) {
				bodyBooleanResult = (OclBoolean) doSwitch((EObject) body);
			}

			else {
				/* A void or undefined result has occurred. */
				result = bodyResult;
				break;
			}

			if (bodyBooleanResult.isTrue()) {

				/*
				 * If the boolean result is true, another element fulfills the
				 * condition.
				 */
				if (oneFoundElement) {
					oneFoundElement = false;
					break;
				}

				else {
					/* Search for another element. */
					oneFoundElement = true;
				}
			}
		}

		if (result == null) {
			result = this.myStandardLibraryFactory.createOclBoolean(oneFoundElement);
		}
		// no else.

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {

			String msg;

			msg = "evaluateOne(OclExpression, OclCollection<OclRoot>";
			msg += ", Variable) - end - result = " + result;

			LOGGER.debug(msg);
		}
		// no else.
		return result;
	}

	/**
	 * The sub collection of source for which body is false.
	 * 
	 * @param body
	 *          the body expression to be evaluated
	 * @param source
	 *          the collection representing the source expression of the iteration
	 * @param iterator
	 *          the iterator (reject may have at most one iterator variable.)
	 * @param resultType
	 *          the result type (set, sequence, bag, orderedSet)
	 * 
	 * @return the result of the iteration
	 */
	@SuppressWarnings("unchecked")
	private OclRoot evaluateReject(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "evaluateReject(";
			msg += "body = " + body;
			msg += ", source = " + source;
			msg += ", iterator = " + iterator;
			msg += ", resultType = " + resultType;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result;

		List<OclRoot> resultList = new ArrayList<OclRoot>();
		OclIterator<OclRoot> it = source.getIterator();

		/*
		 * Iterate over the collection and add all elements to the result list which
		 * do not fulfill the body condition.
		 */
		while (it.hasNext().isTrue()) {

			OclRoot anElement;
			OclBoolean bodyResult;

			/* Add the actual element to the environment. */
			anElement = it.next();
			myEnvironment.addVar(iterator.getQualifiedName(), anElement);

			/* Compute the body expression. */
			bodyResult = (OclBoolean) doSwitch((EObject) body);

			/* Add the element to the result list if the body result is false. */
			if (!bodyResult.isTrue()) {
				resultList.add(anElement);
			}
			// no else.
		}

		/* Convert the result list into a collection. */
		result = this.getResultListAsCollection(resultList, resultType);

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {

			String msg;

			msg = "evaluateReject(OclExpression, OclCollection";
			msg += "<OclRoot>, Variable, Type) - end - return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * The sub collection of source for which body is true.
	 * </p>
	 * 
	 * @param body
	 *          the body expression to be evaluated
	 * @param source
	 *          the collection representing the source expression of the iteration
	 * @param iterator
	 *          the iterator (select may have at most one iterator variable.)
	 * @param resultType
	 *          the result type (set, sequence, bag, orderedSet)
	 * 
	 * @return the result of the iteration
	 */
	@SuppressWarnings("unchecked")
	private OclRoot evaluateSelect(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "evaluateSelect(";
			msg += "body = " + body;
			msg += ", source = " + source;
			msg += ", iterator = " + iterator;
			msg += ", resultType = " + resultType;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		OclRoot result;

		OclIterator<OclRoot> it = source.getIterator();
		List<OclRoot> resultList = new ArrayList<OclRoot>();

		/* Iterate over the collection. */
		while (it.hasNext().isTrue()) {

			OclRoot anElement;
			OclBoolean bodyResult;

			/* Add an element to the environment. */
			anElement = it.next();
			myEnvironment.addVar(iterator.getQualifiedName(), anElement);

			/* Compute the body expression for an element. */
			bodyResult = (OclBoolean) doSwitch((EObject) body);

			/* Add the element to the result list if the body result is true. */
			if (!bodyResult.isOclUndefined().isTrue() && bodyResult.isTrue()) {
				resultList.add(anElement);
			}
			// no else.
		}
		// end while.

		/* Convert the result list into a collection. */
		result = this.getResultListAsCollection(resultList, resultType);

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {

			String msg;

			msg = "evaluateSelect(OclExpression, OclCollection";
			msg += "<OclRoot>, Variable, Type) - end - return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * Results in the sorted collection containing all elements of the source
	 * collection. The element for which body has the lowest value comes first,
	 * and so on. The type of the body expression must have the < operation
	 * defined. The < operation must return a Boolean value and must be transitive
	 * (i.e., if a < b and b < c then a < c).
	 * 
	 * @param body
	 *          the body expression to be evaluated
	 * @param source
	 *          the collection representing the source expression of the iteration
	 * @param iterator
	 *          the iterator (sortedBy may have at most one iterator variable.)
	 * @param resultType
	 *          the result type (set, sequence, bag, orderedSet)
	 * 
	 * @return the result of the iteration
	 */
	@SuppressWarnings("unchecked")
	private OclRoot evaluateSortedBy(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {

			String msg;

			msg = "evaluateSortedBy(";
			msg += "body = " + body;
			msg += ", source = " + source;
			msg += ", iterator = " + iterator;
			msg += ", resultType = " + resultType;
			msg += ") - start";

			LOGGER.debug(msg);
		}
		// no else.

		/* Create comparator for the major part of the sort algorithm. */
		Comparator<OclComparable> comp = new Comparator<OclComparable>() {

			public int compare(OclComparable oclComparable1,
					OclComparable oclComparable2) {

				/* Eventually log the entry of this method. */
				if (LOGGER.isDebugEnabled()) {
					String msg;

					msg = "compare(";
					msg += "oclComparable1 = " + oclComparable1;
					msg += ", oclComparable2 = " + oclComparable2;
					msg += ") - start";

					LOGGER.debug(msg);
				}
				// no else.

				int result;
				OclInteger oclResult;

				/* Compare the two objects. */
				oclResult = oclComparable2.compareTo(oclComparable2);

				if (oclResult.isEqualTo(myStandardLibraryFactory.createOclInteger(-1))
						.isTrue()) {
					result = -1;
				}

				else if (oclResult.isEqualTo(
						myStandardLibraryFactory.createOclInteger(1)).isTrue()) {
					result = 1;
				}

				else {
					result = 0;
				}

				/* Eventually log the exit of this method. */
				if (LOGGER.isDebugEnabled()) {
					String msg;

					msg = "compare(OclComparable, OclComparable) - end ";
					msg += "- return value = " + result;

					LOGGER.debug(msg);
				}
				// no else;

				return result;
			}
		};

		OclRoot result;
		List<OclRoot> resultList;

		Map<OclComparable, OclRoot> results;
		OclIterator<OclRoot> collectionIt;

		results = new TreeMap<OclComparable, OclRoot>(comp);
		collectionIt = source.getIterator();

		/* Iterate over the collection. */
		while (collectionIt.hasNext().isTrue()) {

			OclRoot activeElement;
			OclRoot bodyResult;

			/* Add the active element to the environment. */
			activeElement = collectionIt.next();
			myEnvironment.addVar(iterator.getQualifiedName(), activeElement);

			/* Compute the body for the actual set element. */
			bodyResult = doSwitch((EObject) body);

			if (bodyResult instanceof OclComparable) {
				results.put((OclComparable) bodyResult, activeElement);
			}

			else {
				String msg;
				msg = "The Result of the body expression in a sortedBy Iterator ";
				msg += "was not comparable.";

				if (LOGGER.isInfoEnabled()) {
					LOGGER.warn(msg);
				}
				// no else.

				result = this.myStandardLibraryFactory.createOclInvalid(msg);
			}
		}
		// end while.

		resultList = new ArrayList<OclRoot>(results.values());

		/* Check which type of collection the result shall have. */
		if (resultType instanceof SequenceType) {
			result = this.myStandardLibraryFactory.createOclSequence(resultList);
		}

		else if (resultType instanceof OrderedSetType) {
			result = this.myStandardLibraryFactory.createOclOrderedSet(resultList);
		}

		else {
			String msg;
			msg =
					"The ResultType of a sortedBy Iterator should by a sorted collection.";
			msg += " But was " + resultType.getQualifiedName();

			if (LOGGER.isInfoEnabled()) {
				LOGGER.warn(msg);
			}
			// no else.

			result = this.myStandardLibraryFactory.createOclInvalid(msg);
		}

		/* Eventually log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {

			String msg;

			msg = "evaluateSortedBy(OclExpression, OclCollection";
			msg += "<OclRoot>, Variable, Type) - end - return value=" + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Helper method which returns a given List as an instance of a given
	 * collection type.
	 * </p>
	 * 
	 * @param resultList
	 *          The list which shall be returned as collection.
	 * @param resultType
	 *          The {@link Type} of the collection which shall be returned.
	 * @return Returns a given List as an instance of a given collection type.
	 */
	private OclRoot getResultListAsCollection(List<OclRoot> resultList,
			Type resultType) {

		OclRoot result;

		/* Check which type of collection shall be returned. */
		if (resultType instanceof SetType) {
			result = this.myStandardLibraryFactory.createOclSet(resultList);
		}

		else if (resultType instanceof BagType) {
			result = this.myStandardLibraryFactory.createOclBag(resultList);
		}

		else if (resultType instanceof SequenceType) {
			result = this.myStandardLibraryFactory.createOclSequence(resultList);
		}

		else if (resultType instanceof OrderedSetType) {
			result = this.myStandardLibraryFactory.createOclOrderedSet(resultList);
		}

		else {
			String msg;

			msg = "Unknown Type of Collection. Type was " + resultType.getName();
			result = this.myStandardLibraryFactory.createOclInvalid(msg);
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method which removes the parameters of an {@link Operation} which
	 * is the context of a {@link Constraint} from the {@link IInterpretationEnvironment} after
	 * its execution and interpretation.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} for which the arguments shall be removed.
	 */
	private void removeParametersFromEnvironment(Constraint aConstraint) {

		ExpressionInOcl oclExpression;
		int index;

		oclExpression = (ExpressionInOcl) aConstraint.getSpecification();
		index = 0;

		/* Eventually remove parameters from the environment. */
		for (Variable aVariable : oclExpression.getParameter()) {

			this.resetEnviromentVariable(aVariable.getName());
			index++;
		}
		// end for.
	}
}