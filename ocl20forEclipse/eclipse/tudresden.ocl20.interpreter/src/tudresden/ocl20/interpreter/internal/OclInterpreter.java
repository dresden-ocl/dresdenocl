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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.interpreter.IEnvironment;
import tudresden.ocl20.interpreter.IOclInterpreter;
import tudresden.ocl20.interpreter.InterpreterPlugin;
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
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollectionType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclComparable;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclPrimitiveType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTupleType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclVoid;
import tudresden.ocl20.pivot.essentialocl.types.BagType;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.OrderedSetType;
import tudresden.ocl20.pivot.essentialocl.types.SequenceType;
import tudresden.ocl20.pivot.essentialocl.types.SetType;
import tudresden.ocl20.pivot.essentialocl.types.TupleType;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Type;

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
 * @author Ronny Brandt
 */
public class OclInterpreter extends ExpressionsSwitch<OclRoot> implements
		IOclInterpreter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OclInterpreter.class);

	/** The environment to be used to store variables etc. */
	private IEnvironment env;

	/** Specifies if caching caching shall be used. */
	private boolean useCache = true;

	/** Specifies if model access was needed to interpret result. */
	private boolean modelAccessNeeded = false;

	/** Specifies if this is a preparation run. */
	private boolean preparation = false;

	/** The current model object to be interpreted. */
	private IModelObject constrainedModelObject;

	/**
	 * <p>
	 * Instantiates a new {@link OclInterpreter}.
	 * </p>
	 * 
	 * @param env
	 *            The {@link IEnvironment} to be used during interpretation.
	 */
	public OclInterpreter(IEnvironment env) {
		this.env = env;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#clearResults(java.util.List)
	 */
	public void clearResults(List<IModelObject> modelObjects) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("clearResults(List<IModelObject>) - start");
		}
		// no else.

		/* Iterate through the modelObjects and delete their results. */
		for (IModelObject object : modelObjects) {

			if (object.clearResults()) {
				InterpreterPlugin.getInterpreterRegistry()
						.fireInterpretationFinished(null, object);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("clearResults(List<IModelObject>) - end");
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#interpret(tudresden.ocl20
	 * .pivot.pivotmodel.Constraint,
	 * tudresden.ocl20.pivot.modelbus.IModelObject)
	 */
	public OclRoot interpret(Constraint constraint, IModelObject modelObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("interpret(Constraint, IModelObject) - start");
		}
		// no else.

		OclRoot result;
		OclRoot oclModelObject;

		this.modelAccessNeeded = false;
		this.preparation = false;

		this.constrainedModelObject = modelObject;

		/* Try to get the modelObject as Ocl object. */
		if (modelObject != null) {
			oclModelObject = modelObject.getOclObject();
		}

		else {
			oclModelObject = null;
		}

		/* Add self variable to environment. */
		this.env.addVar("self", oclModelObject);

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(constraint) != null) {
			result = env.getCachedResult(constraint);
		}

		/* Else compute the result. */
		else {
			EObject constraintSpecification;

			constraintSpecification = (EObject) constraint.getSpecification();

			result = doSwitch((EObject) constraintSpecification);
		}

		/* Eventually cache the result. */
		if (useCache && !modelAccessNeeded) {
			env.cacheResult(constraint, result);
		}
		// no else.

		modelObject.addResult(constraint, result);
		InterpreterPlugin.getInterpreterRegistry().fireInterpretationFinished(
				constraint, modelObject);

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("interpret(Constraint, IModelObject) - end - return value="
							+ result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#isModelAccessNeeded()
	 */
	public boolean isModelAccessNeeded() {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("isModelAccessNeeded() - start");
		}
		// no else.

		boolean result;

		result = this.modelAccessNeeded;

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("isModelAccessNeeded() - end - return value="
							+ result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#isUseCache()
	 */
	public boolean isUseCache() {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("isUseCache() - start");
		}
		// no else.

		boolean result;

		result = this.useCache;

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("isUseCache() - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#prepare(tudresden.ocl20.pivot
	 * .pivotmodel.Constraint)
	 */
	public void prepare(Constraint aConstraint, IModelObject modelObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("prepare(Constraint) - start");
		}
		// no else.

		ConstraintKind aKind;

		String constrainedElemName;

		this.preparation = true;

		aKind = aConstraint.getKind();

		constrainedElemName = ((NamedElement) aConstraint
				.getConstrainedElement().get(0)).getQualifiedName();

		/* Check if a body expressions shall be prepared */
		if (aKind.equals(ConstraintKind.BODY)) {
			/* Add the constraint to the environment. */
			this.env.addConstraint(constrainedElemName, aConstraint);
		}

		/* Else check if a definition shall be prepared. */
		else if (aKind.equals(ConstraintKind.DEFINITION)) {

			String featureName;

			featureName = aConstraint.getDefinedFeature().getQualifiedName();

			/*
			 * Add the defined feature to the global environment.
			 */
			this.env.addConstraint(featureName, aConstraint);
		}

		/* Else check if a derived value shall be prepared */
		else if (aKind.equals(ConstraintKind.DERIVED)) {
			/* Add the constraint to the environment. */
			this.env
					.addConstraint(constrainedElemName + "-derive", aConstraint);
		}

		/* Else check if a initial value shall be prepared */
		else if (aKind.equals(ConstraintKind.INITIAL)) {
			/* Add the constraint to the environment. */
			this.env.addConstraint(constrainedElemName + "-initial",
					aConstraint);
		}

		else {
			OclRoot oclModelObject;
			EObject constraintSpecification;

			this.modelAccessNeeded = false;

			this.constrainedModelObject = modelObject;

			/* Try to get the modelObject as OCL object. */
			if (modelObject != null) {
				oclModelObject = modelObject.getOclObject();
			}

			else {
				oclModelObject = null;
			}

			/* Add self variable to environment. */
			this.env.addVar("self", oclModelObject);

			/* Prepare the constraintSpecification. */
			constraintSpecification = (EObject) aConstraint.getSpecification();
			doSwitch((EObject) constraintSpecification);
		}

		this.preparation = false;

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("prepare(Constraint) - end");
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.interpreter.IOclInterpreter#removeResults(java.util.List,
	 * java.util.List)
	 */
	public void removeResults(List<IModelObject> modelObjects,
			List<Constraint> constraints) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("removeResults(List<IModelObject>, List<Constraint>) - start");
		}
		// no else.

		/*
		 * Iterate through the modelObjects and remove their results to the
		 * given constraints.
		 */
		for (IModelObject object : modelObjects) {
			for (Constraint constraint : constraints) {

				if (object.removeResult(constraint)) {
					InterpreterPlugin.getInterpreterRegistry()
							.fireInterpretationFinished(constraint, object);
				}
				// no else.
			}
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("removeResults(List<IModelObject>, List<Constraint>) - end");
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#setUseCache(boolean)
	 */
	public void setUseCache(boolean useCache) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("setUseCache(boolean) - start");
		}
		// no else.

		this.useCache = useCache;

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("setUseCache(boolean) - end");
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
	public OclRoot caseBooleanLiteralExp(BooleanLiteralExp object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseBooleanLiteralExp(BooleanLiteralExp) - start");
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(object) != null) {
			result = env.getCachedResult(object);
		}

		/* Else compute the result. */
		else {
			result = env.getModelInstance().getFactory().createOclBoolean(
					object.isBooleanSymbol());

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(object, result);
			}
			// no else.
		}

		/* Eventually log the exit from this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseBooleanLiteralExp(BooleanLiteralExp) - end - return value="
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
	 * #caseCollectionItem
	 * (tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem)
	 */
	@Override
	public OclRoot caseCollectionItem(CollectionItem object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseCollectionItem(CollectionItem) - start");
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(object) != null) {
			result = env.getCachedResult(object);
		}

		/* Else compute the result. */
		else {

			result = doSwitch((EObject) object.getItem());

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(object, result);
			}
			// no else
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseCollectionItem(CollectionItem) - end - return value="
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
	 * #caseCollectionLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp)
	 */
	@Override
	public OclRoot caseCollectionLiteralExp(CollectionLiteralExp object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseCollectionLiteralExp(CollectionLiteralExp) - start");
		}

		OclRoot result;
		String collectionKind;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(object) != null) {
			result = env.getCachedResult(object);
		}

		else {

			Iterator<CollectionLiteralPart> literalPartIt;
			List<OclRoot> resultParts;

			literalPartIt = object.getPart().iterator();
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

					OclRoot currentElement;
					OclRoot lastElement;

					/*
					 * Get the first and the last element of the collection
					 * range.
					 */
					currentElement = doSwitch((EObject) ((CollectionRange) aPart)
							.getFirst());
					lastElement = doSwitch((EObject) ((CollectionRange) aPart)
							.getLast());

					/*
					 * Try to generate all parts by invoking the increment
					 * operation.
					 */
					try {
						while (((OclBoolean) currentElement.invokeOperation(
								"isLessEqual", new OclRoot[] { lastElement }))
								.isTrue()) {

							resultParts.add(currentElement);
							currentElement = currentElement.invokeOperation(
									"increment", new OclRoot[] {});
						}
						// end while.
					}

					catch (NoSuchMethodException e) {

						String msg;

						msg = "caseCollectionLiteralExp(CollectionLiteralExp)";

						logger.error(msg, e);

						resultParts = null;
					}
				}
			}

			if (resultParts != null) {

				collectionKind = object.getKind().getName();

				/* Create the result depending on the kind of given collection. */
				if (collectionKind.equals("Set")) {

					ArrayList<OclRoot> tempList;

					tempList = new ArrayList<OclRoot>();

					for (OclRoot anElement : resultParts) {
						if (!tempList.contains(anElement)) {
							tempList.add(anElement);
						}
						// no else.
					}

					result = env.getModelInstance().getFactory().createOclSet(
							tempList.toArray(new OclRoot[0]));
				}

				else if (collectionKind.equals("OrderedSet")) {

					ArrayList<OclRoot> tempList;

					tempList = new ArrayList<OclRoot>();

					for (OclRoot anElement : resultParts) {
						if (!tempList.contains(anElement)) {
							tempList.add(anElement);
						}
						// no else.
					}

					result = env.getModelInstance().getFactory()
							.createOclOrderedSet(
									tempList.toArray(new OclRoot[0]));
				}

				else if (collectionKind.equals("Sequence")) {
					result = env.getModelInstance().getFactory()
							.createOclSequence(
									resultParts.toArray(new OclRoot[0]));
				}

				else if (collectionKind.equals("Bag")) {
					result = env.getModelInstance().getFactory().createOclBag(
							resultParts.toArray(new OclRoot[0]));
				}

				else {
					result = null;
				}
			}

			else {
				result = null;
			}

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(object, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseCollectionLiteralExp(CollectionLiteralExp) - end - return value="
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
	 * #caseEnumLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp)
	 */
	@Override
	public OclRoot caseEnumLiteralExp(EnumLiteralExp anEnumLiteralExp) {

		if (logger.isDebugEnabled()) {
			logger.debug("caseEnumLiteralExp(EnumLiteralExp) - start");
		}
		// no else.

		OclRoot result = null;

		EnumerationLiteral referredEnumLiteral;
		String literalName;
		List<String> literalPath;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(anEnumLiteralExp) != null) {
			result = env.getCachedResult(anEnumLiteralExp);
		}

		/* Else compute the result. */
		else {

			referredEnumLiteral = anEnumLiteralExp.getReferredEnumLiteral();

			literalName = referredEnumLiteral.getQualifiedName();
			literalPath = Arrays.asList(literalName.split("::"));

			result = env.getModelInstance().findEnumLiteral(literalPath);

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(anEnumLiteralExp, result);
			}
			// no else.
		}

		if (logger.isDebugEnabled()) {
			logger.debug("caseEnumLiteralExp(EnumLiteralExp)"
					+ " - end - return value=" + result);
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
	public OclRoot caseExpressionInOcl(ExpressionInOcl object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseExpressionInOcl(ExpressionInOcl) - start");
		}
		// no else.

		OclRoot result = doSwitch((EObject) object.getBodyExpression());

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseExpressionInOcl(ExpressionInOcl) - end - return value="
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
	 * #caseIfExp(tudresden.ocl20.pivot.essentialocl.expressions.IfExp)
	 */
	@Override
	public OclRoot caseIfExp(IfExp object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseIfExp(IfExp) - start");
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(object) != null) {
			result = env.getCachedResult(object);
		}

		/* Else compute the result. */
		else {
			OclRoot condition;
			OclRoot thenStatement;
			OclRoot elseStatement;

			condition = doSwitch((EObject) object.getCondition());

			thenStatement = doSwitch((EObject) object.getThenExpression());

			elseStatement = doSwitch((EObject) object.getElseExpression());

			if (condition instanceof OclBoolean) {
				result = ((OclBoolean) condition).ifThenElse(thenStatement,
						elseStatement);
			}
			// no else.

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(object, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseIfExp(IfExp) - end - return value=" + result);
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
	public OclRoot caseIntegerLiteralExp(IntegerLiteralExp intLiteralExp) {

		if (logger.isDebugEnabled()) {
			logger.debug("caseIntegerLiteralExp(IntegerLiteralExp) - start");
		}

		OclRoot result = null;

		/* Eventually use the cache to compute the result. */
		if (useCache && env.getCachedResult(intLiteralExp) != null) {

			result = env.getCachedResult(intLiteralExp);
		}

		/* Else interpret the result. */
		else {

			int intValue;

			intValue = intLiteralExp.getIntegerSymbol();

			result = env.getModelInstance().getFactory().createOclInteger(
					intValue);

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(intLiteralExp, result);
			}
			// no else.

		}
		// end else.

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseIntegerLiteralExp(IntegerLiteralExp) - end - return value="
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
	 * #caseInvalidLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp)
	 */
	@Override
	public OclRoot caseInvalidLiteralExp(InvalidLiteralExp object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseInvalidLiteralExp(InvalidLiteralExp) - start");
		}
		// no else.

		OclRoot result = null;

		result = env.getModelInstance().getInvalid();

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseInvalidLiteralExp(InvalidLiteralExp) - end - return value="
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
	 * #caseIterateExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IterateExp)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public OclRoot caseIterateExp(IterateExp object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseIterateExp(IterateExp) - start");
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(object) != null) {
			result = env.getCachedResult(object);
		}

		/* Else compute the result. */
		else {
			OclRoot source;
			OclCollection<OclRoot> col;

			source = doSwitch((EObject) object.getSource());

			if (source instanceof OclCollection) {
				col = (OclCollection<OclRoot>) source;
			}

			else {
				col = source.asSet();
			}

			/* Reset the accumulator variable in the environment. */
			env.addVar(object.getResult().getQualifiedName(), null);

			result = evaluateIterate(object.getBody(), col, object
					.getIterator(), col.getIterator(), object.getResult()
					.getQualifiedName());

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(object, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseIterateExp(IterateExp) - end - return value="
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
	 * #caseIteratorExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp)
	 */
	@SuppressWarnings("unchecked")
	public OclRoot caseIteratorExp(IteratorExp anIteratorExp) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseIteratorExp(IteratorExp) - start");
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(anIteratorExp) != null) {
			result = env.getCachedResult(anIteratorExp);
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

			sourceIterator = (OclIterator<OclRoot>) sourceCollection
					.getIterator();

			body = anIteratorExp.getBody();

			iteratorName = anIteratorExp.getName();
			resultType = anIteratorExp.getType();

			/* Check which type of iterator shall be used. */
			if (iteratorName.equals("exists")) {
				result = evaluateExists(body, sourceCollection, allIterators,
						sourceIterator);
			}

			else if (iteratorName.equals("select")) {

				/* Select can only use one iterator variable. */
				if (allIterators.size() > 1) {
					System.err
							.println("select() may have at most one iterator variable");
					result = env.getModelInstance().getInvalid();
				}

				else {
					result = evaluateSelect(body, sourceCollection,
							allIterators.get(0), resultType);
				}
			}

			else if (anIteratorExp.getName().equals("forAll")) {
				result = evaluateForAll(body, sourceCollection, allIterators,
						sourceIterator);

			} else if (anIteratorExp.getName().equals("isUnique")) {

				/* IsUnique can only use one iterator variable. */
				if (allIterators.size() > 1) {
					System.err
							.println("isUnique() may have at most one iterator variable");
					result = env.getModelInstance().getInvalid();
				}

				else {
					result = evaluateIsUnique(body, sourceCollection,
							allIterators.get(0));
				}
			}

			else if (anIteratorExp.getName().equals("any")) {

				/* Any can only use one iterator variable. */
				if (allIterators.size() > 1) {
					System.err
							.println("any() may have at most one iterator variable");
					result = env.getModelInstance().getInvalid();
				}

				else {
					result = evaluateAny(body, sourceCollection, allIterators
							.get(0));
				}
			}

			else if (anIteratorExp.getName().equals("one")) {

				/* One can only use one iterator variable. */
				if (allIterators.size() > 1) {
					System.err
							.println("one() may have at most one iterator variable");
					result = env.getModelInstance().getInvalid();
				}

				else {
					result = evaluateOne(body, sourceCollection, allIterators
							.get(0));
				}
			}

			else if (anIteratorExp.getName().equals("reject")) {

				/* Reject can only use one iterator variable. */
				if (allIterators.size() > 1) {
					System.err
							.println("reject() may have at most one iterator variable");
					result = env.getModelInstance().getInvalid();
				}

				else {
					result = evaluateReject(body, sourceCollection,
							allIterators.get(0), resultType);
				}
			}

			else if (anIteratorExp.getName().equals("sortedBy")) {

				/* SortedBy can only use one iterator variable. */
				if (allIterators.size() > 1) {
					System.err
							.println("sortedBy() may have at most one iterator variable");
					result = env.getModelInstance().getInvalid();
				}

				else {
					result = evaluateSortedBy(body, sourceCollection,
							allIterators.get(0), resultType);
				}
			}

			else if (anIteratorExp.getName().equals("collectNested")) {

				/* CollectNested can only use one iterator variable. */
				if (allIterators.size() > 1) {
					System.err
							.println("collectNested() may have at most one iterator variable");
					result = env.getModelInstance().getInvalid();
				}

				else {
					result = evaluateCollectNested(body, sourceCollection,
							allIterators.get(0), resultType);
				}
			}

			else if (anIteratorExp.getName().equals("collect")) {

				/* Collect can only use one iterator variable. */
				if (allIterators.size() > 1) {
					System.err
							.println("collect() may have at most one iterator variable");
					result = env.getModelInstance().getInvalid();
				}

				else {
					result = evaluateCollectNested(body, sourceCollection,
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
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(anIteratorExp, result);
			}
			// no else.
		}

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseIteratorExp(IteratorExp) - end - return value="
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
	 * #caseLetExp(tudresden.ocl20.pivot.essentialocl.expressions.LetExp)
	 */
	@Override
	public OclRoot caseLetExp(LetExp object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseLetExp(LetExp) - start");
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(object) != null) {
			result = env.getCachedResult(object);
		}

		/* Else compute the result. */
		else {
			result = doSwitch((EObject) object.getIn());

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(object, result);
			}
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseLetExp(LetExp) - end - return value=" + result);
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
	public OclRoot caseOperationCallExp(OperationCallExp object) {

		OclRoot result;

		if (logger.isDebugEnabled()) {
			String log;

			log = "caseOperationCallExp(OperationCallExp) - start";
			logger.debug(log);
		}

		result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(object) != null) {
			result = env.getCachedResult(object);
		}

		/* Else compute the result. */
		else {
			OclRoot[] parameters;
			OclRoot source;

			Constraint body;
			IEnvironment localEnv;

			Operation referredOperation;
			String opName;

			ListIterator<OclExpression> argIterator;
			List<Parameter> opParams;

			parameters = new OclRoot[object.getArgument().size()];

			referredOperation = object.getReferredOperation();
			opName = env.getModelInstance().getOperationName(
					referredOperation.getName(), parameters.length + 1);

			/*
			 * Transform the operation name for all operations which are
			 * implemented with a different name than in OCL.
			 */
			if (opName.equals("=")) {
				opName = "isEqualTo";
			} else if (opName.equals("<>")) {
				opName = "isNotEqualTo";
			} else if (opName.equals(">=")) {
				opName = "isGreaterEqual";
			} else if (opName.equals(">")) {
				opName = "isGreaterThan";
			} else if (opName.equals("<=")) {
				opName = "isLessEqual";
			} else if (opName.equals("<")) {
				opName = "isLessThan";
			} else if (opName.equals("+")) {
				opName = "add";
			} else if (opName.equals("-")) {
				/* Decide between unary and binary operation. */
				if (parameters.length > 0) {
					opName = "subtract";
				} else {
					opName = "negative";
				}
			} else if (opName.equals("*")) {
				opName = "multiply";
			} else if (opName.equals("/")) {
				opName = "divide";
			} else if (opName.equals("oclIsUndefined")) {
				opName = "isOclUndefined";
			}
			// no else.

			source = null;

			/* Get the source type for static operations. */
			if (object.getReferredOperation().isStatic()) {
				List<String> pathName;
				String typeName;

				typeName = object.getSourceType().getQualifiedName();

				/*
				 * Convert the type name into a list containing the package
				 * names.
				 */
				if (StringUtils.isEmpty(typeName)) {
					pathName = Collections.emptyList();
				} else {
					pathName = Arrays.asList(typeName.split("::"));
				}

				/* Find the source type. */
				source = env.getModelInstance().findType(pathName);
			}

			/* Compute the source type for non-static operations. */
			else {
				source = doSwitch((EObject) object.getSource());
			}

			localEnv = null;

			/*
			 * Operations can be implemented by a body expression or a
			 * definition. Try to get such an expression if it has been defined.
			 */
			body = env.getConstraint(object.getReferredOperation()
					.getQualifiedName());

			/*
			 * If a body expression or definition has been defined, create a new
			 * local environment.
			 */
			if (body != null) {
				localEnv = Environment.getNewLocalEnvironment();
			}
			// no else.

			argIterator = object.getArgument().listIterator();

			opParams = referredOperation.getInputParameter();

			/* Iterate through the arguments and compute the parameter values. */
			while (argIterator.hasNext()) {
				
				OclExpression exp;
				OclRoot param;
				String parameterName;

				exp = argIterator.next();
				param = doSwitch((EObject) exp);
				parameters[argIterator.previousIndex()] = param;

				parameterName = opParams
						.get(argIterator.previousIndex()).getName();

				/*
				 * Eventually add the variables to the local environment if a
				 * body expression or definition has been defined.
				 */
				if (localEnv != null) {
					localEnv.addVar(parameterName, param);
				}
				// no else.
			}

			/* Handle special operations. */

			/* The operation atPre has to store the atPre value. */
			if (opName.equals("atPre")) {

				if (preparation) {
					env.savePostconditionValue(object, source);
					result = env.getModelInstance().getUndefined();
				}

				else {
					result = env.getPostconditionValue(object);
				}
			}

			/*
			 * The operation oclIsNew has to store some values. To compute all
			 * new values.
			 */
			else if (opName.equals("oclIsNew")) {

				if (preparation) {
					env.savePostconditionValue(object, env.getModelInstance()
							.getFactory().createOclBoolean(
									source instanceof OclVoid));
					result = env.getModelInstance().getUndefined();
				}

				else {
					result = env.getPostconditionValue(object);
				}
			}

			/*
			 * If allInstances for some reason is not possible for standard
			 * library null is returned. In that case
			 * modelInstance.getObjectsOfKind() is used.
			 */
			else if (opName.equals("allInstances")) {
				List<String> typePath;
				String typeName;

				List<IModelObject> objectInstances;
				Iterator<IModelObject> objectsIt;

				List<OclRoot> instances;

				typeName = object.getSourceType().getQualifiedName();

				/*
				 * Split the canonical name of the source type into its
				 * packages.
				 */
				if (StringUtils.isEmpty(typeName)) {
					typePath = Collections.emptyList();
				} else {
					typePath = Arrays.asList(typeName.split("::"));
				}

				objectInstances = new ArrayList<IModelObject>(env
						.getModelInstance().getObjectsOfKind(typePath));
				objectsIt = objectInstances.iterator();

				instances = new ArrayList<OclRoot>(objectInstances.size());

				/*
				 * Iterate through the instances and add them to the instances
				 * list.
				 */
				while (objectsIt.hasNext()) {
					instances.add(objectsIt.next().getOclObject());
				}

				result = env.getModelInstance().getFactory().createOclSet(
						instances.toArray(new OclRoot[instances.size()]));
			}

			/* The standard case. Invoke the operation and compute the result. */
			else {

				if (body == null) {

					try {
						result = source.invokeOperation(opName, parameters);
					}

					catch (NoSuchMethodException e) {

						if (source instanceof OclVoid) {
							result = source;
						}

						else {
							String log;

							log = "caseOperationCallExp(OperationCallExp)";
							logger.error(log, e);
						}
					}
				}

				else {
					IOclInterpreter interp;

					interp = new OclInterpreter(localEnv);
					interp.setUseCache(useCache);
					result = interp.interpret(body, constrainedModelObject);
					modelAccessNeeded = interp.isModelAccessNeeded();
				}
			}
			// end else.

			/*
			 * If the result or its source is an OclObject, the result cannot be
			 * cached.
			 */
			if ((result instanceof OclObject) || (source instanceof OclObject)) {
				modelAccessNeeded = true;
			}

		}
		// end else.

		/* Eventually cache the result. */
		if (useCache && !modelAccessNeeded) {
			env.cacheResult(object, result);
		}
		// no else.

		if (logger.isDebugEnabled()) {
			String log;

			log = "caseOperationCallExp(OperationCallExp) - end - ";
			log += "return value=" + result;
			logger.debug(log);
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
	public OclRoot casePropertyCallExp(PropertyCallExp aProperty) {

		OclRoot result;
		IEnvironment localEnv;

		if (logger.isDebugEnabled()) {
			logger.debug("casePropertyCallExp(PropertyCallExp) - start");
		}

		result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(aProperty) != null) {
			result = env.getCachedResult(aProperty);
		}

		/* Else interpret the result. */
		else {

			OclRoot sourceObject;

			String qualifiedPropertyName;
			String propertyName;

			sourceObject = null;

			/* Handle static properties. */
			if (aProperty.getReferredProperty().isStatic()) {
				List<String> pathName;
				String typeName;

				typeName = aProperty.getSourceType().getQualifiedName();

				/* Convert the canonical name into a List of packages. */
				if (StringUtils.isEmpty(typeName)) {
					pathName = Collections.emptyList();
				} else {
					pathName = Arrays.asList(typeName.split("::"));
				}

				/* For static properties the source is their Type. */
				sourceObject = env.getModelInstance().findType(pathName);
			}

			/* Else interpret the sourceExp. */
			else {
				sourceObject = doSwitch((EObject) aProperty.getSource());
			}

			localEnv = null;

			qualifiedPropertyName = aProperty.getReferredProperty()
					.getQualifiedName();
			propertyName = aProperty.getReferredProperty().getName();

			/* Check if the property is a derived one. */
			if (env.getConstraint(qualifiedPropertyName + "-derive") != null) {

				Constraint derivedC;
				IOclInterpreter interp;

				derivedC = env.getConstraint(qualifiedPropertyName + "-derive");

				localEnv = Environment.getNewLocalEnvironment();

				interp = new OclInterpreter(localEnv);
				interp.setUseCache(useCache);

				result = interp.interpret(derivedC, constrainedModelObject);
				modelAccessNeeded = interp.isModelAccessNeeded();
			}

			/* Else check if the property has a initial expression. */
			else if (env.getConstraint(qualifiedPropertyName + "-initial") != null) {

				Constraint initC;
				IOclInterpreter interp;

				initC = env.getConstraint(qualifiedPropertyName + "-initial");

				localEnv = Environment.getNewLocalEnvironment();

				interp = new OclInterpreter(localEnv);
				interp.setUseCache(useCache);

				result = interp.interpret(initC, constrainedModelObject);

				modelAccessNeeded = interp.isModelAccessNeeded();
			}

			/* Else check if the property has a body expression. */
			else if (env.getConstraint(qualifiedPropertyName) != null) {

				Constraint body;
				IOclInterpreter interp;

				body = env.getConstraint(qualifiedPropertyName);

				localEnv = Environment.getNewLocalEnvironment();

				interp = new OclInterpreter(localEnv);
				interp.setUseCache(useCache);

				result = interp.interpret(body, constrainedModelObject);
				modelAccessNeeded = interp.isModelAccessNeeded();
			}

			/* Else the property value must be set in the model instance. */
			else {
				try {
					Type propertyType;

					propertyType = aProperty.getType();

					if (propertyType instanceof OrderedSetType) {
						result = sourceObject
								.getPropertyValueAsOrderedSet(propertyName);
					}

					else if (propertyType instanceof SequenceType) {
						result = sourceObject
								.getPropertyValueAsSequence(propertyName);
					}

					else if (propertyType instanceof SetType) {
						result = sourceObject
								.getPropertyValueAsSet(propertyName);
					}

					else if (propertyType instanceof BagType) {
						result = sourceObject
								.getPropertyValueAsBag(propertyName);
					}

					else {
						result = sourceObject.getPropertyValue(propertyName);
					}
				}

				catch (NoSuchFieldException e) {
					logger.error("casePropertyCallExp(PropertyCallExp)", e);

					e.printStackTrace();

					result = env.getModelInstance().getInvalid();
				}

				catch (IllegalAccessException e) {
					logger.error("casePropertyCallExp(PropertyCallExp)", e);

					e.printStackTrace();

					result = env.getModelInstance().getInvalid();
				}
			}

			/* If the result is an OclObject, the result can not be cached. */
			if (result instanceof OclObject) {
				modelAccessNeeded = true;
			}
			// no else.

			// Eventually cache the result.
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(aProperty, result);
			}
			// no else.
		}
		// end else.

		if (logger.isDebugEnabled()) {
			logger
					.debug("casePropertyCallExp(PropertyCallExp) - end - return value="
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
	 * #caseRealLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp)
	 */
	@Override
	public OclRoot caseRealLiteralExp(RealLiteralExp object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseRealLiteralExp(RealLiteralExp) - start");
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(object) != null) {
			result = env.getCachedResult(object);
		}

		/* Else compute the result. */
		else {
			result = env.getModelInstance().getFactory().createOclReal(
					object.getRealSymbol());

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(object, result);
			}
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseRealLiteralExp(RealLiteralExp) - end - return value="
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
	public OclRoot caseStringLiteralExp(StringLiteralExp aStringLiteralExp) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseStringLiteralExp(StringLiteralExp) - start");
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(aStringLiteralExp) != null) {
			result = env.getCachedResult(aStringLiteralExp);
		}

		/* Else compute the result. */
		else {
			result = env.getModelInstance().getFactory().createOclString(
					aStringLiteralExp.getStringSymbol());

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(aStringLiteralExp, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseStringLiteralExp(StringLiteralExp) - end - return value="
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
	 * #caseTypeLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp)
	 */
	@Override
	public OclRoot caseTypeLiteralExp(TypeLiteralExp object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseTypeLiteralExp(TypeLiteralExp) - start");
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(object) != null) {
			result = env.getCachedResult(object);
		}

		/* Else compute the result. */
		else {

			List<String> pathName;
			String typeName;

			Type referredType;

			referredType = object.getReferredType();

			/*
			 * Check if the referred type is a type of the OCL standard library
			 * and try to adapt it.
			 */
			if (referredType instanceof CollectionType) {
				result = (OclRoot) Platform.getAdapterManager().getAdapter(
						referredType, OclCollectionType.class);
			}

			else if (referredType instanceof Enumeration) {
				result = (OclRoot) Platform.getAdapterManager().getAdapter(
						referredType, OclEnumType.class);
			}

			else if (referredType instanceof PrimitiveType) {
				result = (OclRoot) Platform.getAdapterManager().getAdapter(
						referredType, OclPrimitiveType.class);
			}

			else if (referredType instanceof TupleType) {
				result = (OclRoot) Platform.getAdapterManager().getAdapter(
						referredType, OclTupleType.class);
			}

			/* Else try to get a user defined type from the model. */
			else {
				typeName = referredType.getQualifiedName();

				/* Split the path name into a list of package names. */
				if (StringUtils.isEmpty(typeName)) {
					pathName = Collections.emptyList();
				} else {
					pathName = Arrays.asList(typeName.split("::"));
				}

				/* Try to find the type. */
				result = env.getModelInstance().findType(pathName);
			}

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(object, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseTypeLiteralExp(TypeLiteralExp) - end - return value="
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
	 * #caseTupleLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp)
	 */
	@Override
	public OclRoot caseTupleLiteralExp(TupleLiteralExp aTupleLiteralExp) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseTupleLiteralExp(TupleLiteralExp) - start");
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(aTupleLiteralExp) != null) {
			result = env.getCachedResult(aTupleLiteralExp);
		}

		/* Else compute the result. */
		else {

			List<String> partNames;
			List<OclRoot> partValues;

			partNames = new ArrayList<String>(aTupleLiteralExp.getPart().size());
			partValues = new ArrayList<OclRoot>(aTupleLiteralExp.getPart()
					.size());

			for (TupleLiteralPart aLiteralPart : aTupleLiteralExp.getPart()) {
				partNames.add(aLiteralPart.getProperty().getName());
				partValues.add(doSwitch((EObject) aLiteralPart));
			}

			result = env.getModelInstance().getFactory().createOclTuple(
					partNames.toArray(new String[0]),
					partValues.toArray(new OclRoot[0]));

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(aTupleLiteralExp, result);
			}
		}

		/* Eventually log the exit of this method. */

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseTupleLiteralExp(TupleLiteralExp) - end - return value="
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
	public OclRoot caseTupleLiteralPart(TupleLiteralPart object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseTupleLiteralPart(TupleLiteralPart) - start");
		}

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(object) != null) {
			result = env.getCachedResult(object);
		}

		/* Else compute the result. */
		else {
			result = doSwitch((EObject) object.getValue());

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(object, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseTupleLiteralPart(TupleLiteralPart) - end - return value="
							+ result);
		}
		// no else;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseVariable(tudresden.ocl20.pivot.essentialocl.expressions.Variable)
	 */
	public OclRoot caseVariable(Variable aVariable) {

		OclRoot result;

		if (logger.isDebugEnabled()) {
			logger.debug("caseVariable(Variable) - start");
		}
		// no else.

		result = null;

		/* Eventually use a caches result. */
		if (useCache && env.getCachedResult(aVariable) != null) {
			result = env.getCachedResult(aVariable);
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
			// no else.

			/*
			 * Eventually get the value of the Variable from the environment.
			 * For example if the variable was prepared like the variables
			 * 'self' or 'result'.
			 */
			result = env.getVar(aVariable.getName());

			/*
			 * Else initialized the Variable and add the Variable to the
			 * environment.
			 */
			if (result == null) {

				if (initValue != null) {
					result = initValue;
					env.addVar(aVariable.getQualifiedName(), result);
				}

				else if (aVariable.getName().equals("self")) {
					result = this.constrainedModelObject.getOclObject();
				}

				/* FIXME Handle the special variable result. */
				else if (aVariable.getName().equals("result")) {
					System.out
							.println("Problem for 'result' variables not solved yet.");
				}

				else {
					System.out.println("Unknown Variable");
					System.out.println("Variable name: " + aVariable.getName());
					result = env.getModelInstance().getUndefined();
				}
			}

			/* OclObject can't be cached. */
			if (result instanceof OclObject) {
				modelAccessNeeded = true;
			}
			// no else.

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(aVariable, result);
			}
			// no else.
		}

		if (logger.isDebugEnabled()) {
			logger.debug("caseVariable(Variable) - end - return value="
					+ result);
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
	public OclRoot caseVariableExp(VariableExp object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseVariableExp(VariableExp) - start");
		}
		// no else.

		OclRoot result;

		result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(object) != null) {
			result = env.getCachedResult(object);
		}

		/* Else compute the result. */
		else {
			result = doSwitch((EObject) object.getReferredVariable());

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(object, result);
			}
			// no else.
		}

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("caseVariableExp(VariableExp) - end - return value="
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
	public OclRoot caseUndefinedLiteralExp(UndefinedLiteralExp object) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseUndefinedLiteralExp(UndefinedLiteralExp) - start");
		}
		// no else.

		OclRoot result = null;

		/* Eventually use a cached result. */
		if (useCache && env.getCachedResult(object) != null) {

			result = env.getCachedResult(object);
		}

		/* Else compute the result. */
		else {
			result = env.getModelInstance().getUndefined();

			/* Eventually cache the result. */
			if (useCache && !modelAccessNeeded) {
				env.cacheResult(object, result);
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseUndefinedLiteralExp(UndefinedLiteralExp) - end - return value="
							+ result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Evaluates the general iterate method. Will be invoked recursively for
	 * every iterator of the iteration.
	 * </p>
	 * 
	 * @param body
	 *            the body expression of the iteration
	 * @param source
	 *            the collection representing the source expression of the
	 *            iteration
	 * @param iterators
	 *            the iterators
	 * @param it
	 *            the current iterator for the source collection
	 * @param resultVarName
	 *            the name of the result variable
	 * 
	 * @return the result of the iteration
	 */
	@SuppressWarnings("unchecked")
	private OclRoot evaluateIterate(OclExpression body,
			OclCollection<OclRoot> source, List<Variable> iterators,
			OclIterator<OclRoot> it, String resultVarName) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateIterate(OclExpression, OclCollection<OclRoot>,";
			msg += " List<Variable>, OclIterator<OclRoot>, String) - begin";

			logger.debug(msg);
		}
		// no else.

		OclRoot result = null;

		/* Iterate through the iterator. */
		while (it.hasNext().isTrue()) {

			OclRoot activeElement;

			/* Get the next element. */
			activeElement = it.next();

			/* Add the elements variable to the environment. */
			env.addVar(iterators.get(0).getQualifiedName(), activeElement);

			/*
			 * If more than one iterators are used, remove the first iterator
			 * and recall this method recursively.
			 */
			if (iterators.size() > 1) {
				List<Variable> allIterators;
				OclIterator<OclRoot> nextIt;

				allIterators = new ArrayList<Variable>(iterators);
				allIterators.remove(0);

				nextIt = (OclIterator<OclRoot>) source.getIterator();

				result = evaluateIterate(body, source, allIterators, nextIt,
						resultVarName);
			}

			/* Else compute the result. */
			else {
				result = doSwitch((EObject) body);
			}

			/* Add the result to the environment. */
			env.addVar(resultVarName, result);
		}

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateIterate(OclExpression, OclCollection<OclRoot>,";
			msg += " List<Variable>, OclIterator<OclRoot>, String) - end";
			msg += " - return value=" + result;

			logger.debug(msg);
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
	 * 
	 * @return the result of the iteration
	 */
	@SuppressWarnings("unchecked")
	private OclRoot evaluateAny(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateAny(OclExpression, OclCollection<OclRoot>";
			msg += ", Variable) - start";

			logger.debug(msg);
		}
		// no else.

		OclRoot result;

		/* By default, the result is undefined. */
		result = env.getModelInstance().getUndefined();

		OclIterator<OclRoot> sourceIt = source.getIterator();

		/*
		 * Iterate over the source and evaluate the body expression for all
		 * elements. If it is true for any element, the result the specific
		 * element.
		 */
		while (sourceIt.hasNext().isTrue()) {

			OclRoot anElement;
			OclBoolean bodyResult;

			/* Add an element to the environment. */
			anElement = sourceIt.next();
			env.addVar(iterator.getQualifiedName(), anElement);

			/* Compute the body result. */
			bodyResult = (OclBoolean) doSwitch((EObject) body);

			if (bodyResult.isTrue()) {
				result = anElement;
				break;
			}
			// no else.
		}
		// end while.

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateAny(OclExpression, OclCollection<OclRoot>";
			msg += ", Variable) - end - return value=" + result;

			logger.debug(msg);
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
	@SuppressWarnings("unchecked")
	private OclRoot evaluateCollectNested(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateCollectNested(OclExpression, OclCollection";
			msg += "<OclRoot>, Variable, Type) - start";

			logger.debug(msg);
		}
		// no else.

		OclRoot result;

		List<OclRoot> resultList;
		OclIterator<OclRoot> sourceIt;

		resultList = new ArrayList<OclRoot>();
		sourceIt = source.getIterator();

		/*
		 * Iterate over the source and collect the body expression for all
		 * elements.
		 */
		while (sourceIt.hasNext().isTrue()) {

			OclRoot anElement;
			OclRoot bodyResult;

			/* Get the next element and add it to the environment. */
			anElement = sourceIt.next();
			env.addVar(iterator.getQualifiedName(), anElement);

			/* Compute the body expression for an element. */
			bodyResult = doSwitch((EObject) body);

			resultList.add(bodyResult);
		}

		/* Compute the result type depending on the given result type. */
		if (resultType instanceof BagType) {
			result = env.getModelInstance().getFactory().createOclBag(
					resultList.toArray(new OclRoot[resultList.size()]));
		}

		else if (resultType instanceof SequenceType) {
			result = env.getModelInstance().getFactory().createOclSequence(
					resultList.toArray(new OclRoot[resultList.size()]));
		}

		else {
			result = env.getModelInstance().getInvalid();
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateCollectNested(OclExpression, OclCollection";
			msg += "<OclRoot>, Variable, Type) - end - result = " + result;

			logger.debug(msg);
		}
		// no else.

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
	@SuppressWarnings("unchecked")
	private OclRoot evaluateExists(OclExpression body,
			OclCollection<OclRoot> source, List<Variable> iterators,
			OclIterator<OclRoot> it) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateExists(OclExpression, OclCollection<OclRoot>";
			msg += ", List<Variable>, OclIterator<OclRoot>) - start";

			logger.debug(msg);
		}
		// no else.

		OclRoot result;

		/* By default the result is false. */
		result = env.getModelInstance().getFactory().createOclBoolean(false);

		/*
		 * Iterate over the collection and check if at least one element
		 * fulfills the body expression.
		 */
		while (it.hasNext().isTrue()) {

			OclRoot anElement;
			OclBoolean bodyResult;

			/* Add an element to the environment... */
			anElement = it.next();
			env.addVar(iterators.get(0).getQualifiedName(), anElement);

			/* ...and compute its body expression. */
			bodyResult = null;

			/*
			 * Eventually recall this method recursively for more iterator
			 * variables.
			 */
			if (iterators.size() > 1) {

				List<Variable> tempItList;
				OclIterator<OclRoot> nextIt;

				/*
				 * Remove the firs iterator variable and recall recursively this
				 * method for all other iterator variables.
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

			/* If the body result is true, result in true. */
			if (bodyResult.isTrue()) {
				result = env.getModelInstance().getFactory().createOclBoolean(
						true);
				break;
			}
			// no else.
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateExists(OclExpression, OclCollection<OclRoot>";
			msg += ", List<Variable>, OclIterator<OclRoot>) - end - result = ";
			msg += result;

			logger.debug(msg);
		}
		// no else.

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
	@SuppressWarnings("unchecked")
	private OclRoot evaluateForAll(OclExpression body,
			OclCollection<OclRoot> source, List<Variable> iterators,
			OclIterator<OclRoot> it) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateForAll(OclExpression, OclCollection<OclRoot>";
			msg += ", List<Variable>, OclIterator<OclRoot>) - start";

			logger.debug(msg);
		}
		// no else.

		OclRoot result;

		/* By default the result is true. */
		result = env.getModelInstance().getFactory().createOclBoolean(true);

		/* Iterate through the collection. */
		while (it.hasNext().isTrue()) {

			OclRoot anItVariable;
			OclBoolean bodyResult;

			/* Get an iterator variable and add it to the environment. */
			anItVariable = it.next();
			env.addVar(iterators.get(0).getQualifiedName(), anItVariable);

			bodyResult = null;

			/*
			 * Check if more than this iterator variables are available and
			 * eventually add them to the environment and compute the result
			 * recursively.
			 */
			if (iterators.size() > 1) {
				List<Variable> subIteratorList;
				OclIterator<OclRoot> nextIt;

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

			/* If the body result is false, return false. */
			if (!bodyResult.isTrue()) {
				result = env.getModelInstance().getFactory().createOclBoolean(
						false);
				break;
			}
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateForAll(OclExpression, OclCollection<OclRoot>";
			msg += ", List<Variable>, OclIterator<OclRoot>) - end - result = ";
			msg += result;

			logger.debug(msg);
		}
		// no else.

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
	@SuppressWarnings("unchecked")
	private OclRoot evaluateIsUnique(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateIsUnique(OclExpression, OclCollection";
			msg += "<OclRoot>, Variable) - start";

			logger.debug(msg);
		}
		// no else.

		OclRoot result;
		List<OclRoot> resultList;

		OclIterator<OclRoot> sourceIt;

		sourceIt = source.getIterator();
		resultList = new ArrayList<OclRoot>();

		/* By default, the result is true. */
		result = env.getModelInstance().getFactory().createOclBoolean(true);

		/* Iterate over the collection and check if every element is unique. */
		while (sourceIt.hasNext().isTrue()) {

			OclRoot anElement;
			OclRoot bodyResult;

			anElement = sourceIt.next();

			/* Add the element to the environment. */
			env.addVar(iterator.getQualifiedName(), anElement);

			/* Compute the body for the set environment. */
			bodyResult = doSwitch((EObject) body);

			/* Check if the result is unique. */
			if (!resultList.contains(bodyResult)) {
				resultList.add(bodyResult);
			}

			else {
				result = env.getModelInstance().getFactory().createOclBoolean(
						false);
				break;
			}
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateIsUnique(OclExpression, OclCollection<OclRoot>";
			msg += ", Variable) - end - return value=" + result;

			logger.debug(msg);
		}
		// no else.

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
	@SuppressWarnings("unchecked")
	private OclRoot evaluateOne(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateOne(OclExpression, OclCollection<OclRoot>";
			msg += ", Variable) - start";

			logger.debug(msg);
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
		 * Iterate through the source and check if exactly one element fulfills
		 * the body condition.
		 */
		while (sourceIt.hasNext().isTrue()) {

			OclRoot anElement;
			OclRoot bodyResult;
			OclBoolean bodyBooleanResult;

			/* Add the element to the environment and compute the body result. */
			anElement = sourceIt.next();
			env.addVar(iterator.getQualifiedName(), anElement);

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
			result = env.getModelInstance().getFactory().createOclBoolean(
					oneFoundElement);
		}
		// no else.

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateOne(OclExpression, OclCollection<OclRoot>";
			msg += ", Variable) - end - result = " + result;

			logger.debug(msg);
		}
		// no else.
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
	@SuppressWarnings("unchecked")
	private OclRoot evaluateReject(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateReject(OclExpression, OclCollection";
			msg += "<OclRoot>, Variable, Type) - start";

			logger.debug(msg);
		}
		// no else.

		OclRoot result;

		List<OclRoot> resultList = new ArrayList<OclRoot>();
		OclIterator<OclRoot> it = source.getIterator();

		/*
		 * Iterate over the collection and add all elements to the result list
		 * which do not fulfill the body condition.
		 */
		while (it.hasNext().isTrue()) {

			OclRoot anElement;
			OclBoolean bodyResult;

			/* Add the actual element to the environment. */
			anElement = it.next();
			env.addVar(iterator.getQualifiedName(), anElement);

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
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateReject(OclExpression, OclCollection";
			msg += "<OclRoot>, Variable, Type) - end - return value=" + result;

			logger.debug(msg);
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
	@SuppressWarnings("unchecked")
	private OclRoot evaluateSelect(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateSelect(OclExpression, OclCollection<OclRoot>, Variable, Type) - start");
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
			env.addVar(iterator.getQualifiedName(), anElement);

			/* Compute the body expression for an element. */
			bodyResult = (OclBoolean) doSwitch((EObject) body);

			/* Add the element to the result list if the body result is true. */
			if (bodyResult.isTrue()) {
				resultList.add(anElement);
			}
			// no else.
		}
		// end while.

		/* Convert the result list into a collection. */
		result = this.getResultListAsCollection(resultList, resultType);

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateSelect(OclExpression, OclCollection";
			msg += "<OclRoot>, Variable, Type) - end - return value=" + result;

			logger.debug(msg);
		}
		// no else.

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
	@SuppressWarnings("unchecked")
	private OclRoot evaluateSortedBy(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateSortedBy(OclExpression, OclCollection";
			msg += "<OclRoot>, Variable, Type) - start";

			logger.debug(msg);
		}
		// no else.

		/* Create comparator for the major part of the sort algorithm. */
		Comparator<OclComparable> comp = new Comparator<OclComparable>() {

			public int compare(OclComparable object1, OclComparable object2) {

				/* Eventually log the entry of this method. */
				if (logger.isDebugEnabled()) {
					logger
							.debug("compare(OclComparable, OclComparable) - start");
				}
				// no else.

				int result;
				OclInteger oclResult;

				/* Compare the two objects. */
				oclResult = object1.compareTo(object2);

				if (oclResult.isEqualTo(
						env.getModelInstance().getFactory()
								.createOclInteger(-1)).isTrue()) {
					result = -1;
				}

				else if (oclResult
						.isEqualTo(
								env.getModelInstance().getFactory()
										.createOclInteger(1)).isTrue()) {
					result = 1;
				}

				else {
					result = 0;
				}

				/* Eventually log the exit of this method. */
				if (logger.isDebugEnabled()) {
					logger
							.debug("compare(OclComparable, OclComparable) - end - return value=" + 0);
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
			env.addVar(iterator.getQualifiedName(), activeElement);

			/* Compute the body for the actual set element. */
			bodyResult = doSwitch((EObject) body);

			if (bodyResult instanceof OclComparable) {
				results.put((OclComparable) bodyResult, activeElement);
			}

			else {
				result = env.getModelInstance().getInvalid();
			}
		}
		// end while.

		resultList = new ArrayList<OclRoot>(results.values());

		/* Check which type of collection the result shall have. */
		if (resultType instanceof SequenceType) {
			result = env.getModelInstance().getFactory().createOclSequence(
					resultList.toArray(new OclRoot[resultList.size()]));
		}

		else if (resultType instanceof OrderedSetType) {
			result = env.getModelInstance().getFactory().createOclOrderedSet(
					resultList.toArray(new OclRoot[resultList.size()]));
		}

		else {
			result = env.getModelInstance().getInvalid();
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {

			String msg;

			msg = "evaluateSortedBy(OclExpression, OclCollection";
			msg += "<OclRoot>, Variable, Type) - end - return value=" + result;

			logger.debug(msg);
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
	 *            The list which shall be returned as collection.
	 * @param resultType
	 *            The {@link Type} of the collection which shall be returned.
	 * @return Returns a given List as an instance of a given collection type.
	 */
	private OclRoot getResultListAsCollection(List<OclRoot> resultList,
			Type resultType) {

		OclRoot result;

		/* Check which type of collection shall be returned. */
		if (resultType instanceof SetType) {
			result = env.getModelInstance().getFactory().createOclSet(
					resultList.toArray(new OclRoot[resultList.size()]));
		}

		else if (resultType instanceof BagType) {
			result = env.getModelInstance().getFactory().createOclBag(
					resultList.toArray(new OclRoot[resultList.size()]));
		}

		else if (resultType instanceof SequenceType) {
			result = env.getModelInstance().getFactory().createOclSequence(
					resultList.toArray(new OclRoot[resultList.size()]));
		}

		else if (resultType instanceof OrderedSetType) {
			result = env.getModelInstance().getFactory().createOclOrderedSet(
					resultList.toArray(new OclRoot[resultList.size()]));
		}

		else {
			result = env.getModelInstance().getInvalid();
		}

		return result;
	}
}