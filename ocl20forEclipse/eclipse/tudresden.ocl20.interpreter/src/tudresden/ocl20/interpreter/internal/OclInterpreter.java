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

import org.apache.log4j.Logger;

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
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclComparable;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclVoid;
import tudresden.ocl20.pivot.essentialocl.types.BagType;
import tudresden.ocl20.pivot.essentialocl.types.OrderedSetType;
import tudresden.ocl20.pivot.essentialocl.types.SequenceType;
import tudresden.ocl20.pivot.essentialocl.types.SetType;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class OclInterpreter extends ExpressionsSwitch<OclRoot> implements
		IOclInterpreter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OclInterpreter.class);

	// the environment to be used
	private IEnvironment env;

	// shall caching be used
	private boolean useCache = true;

	// was model access needed
	private boolean modelAccessNeeded = false;

	// is this a preparation run
	private boolean preparation = false;

	// the current model object to be interpreted
	private IModelObject modelObject;

	/**
	 * Instantiates a new ocl interpreter.
	 * 
	 * @param env
	 *            the {@link IEnvironment} to be used
	 */
	public OclInterpreter(IEnvironment env) {
		this.env = env;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#interpret(tudresden.ocl20.pivot.pivotmodel.Constraint,
	 *      tudresden.ocl20.pivot.modelbus.IModelObject)
	 */
	public OclRoot interpret(Constraint constraint, IModelObject modelObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("interpret(Constraint, IModelObject) - start");
		}

		modelAccessNeeded = false;
		preparation = false;

		this.modelObject = modelObject;
		env.addVar("self", modelObject != null ? modelObject.getOclObject()
				: null);

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(constraint)) != null) {
			modelObject.addResult(constraint, ret);
			InterpreterPlugin.getInterpreterRegistry()
					.fireInterpretationFinished(constraint, modelObject);

			if (logger.isDebugEnabled()) {
				logger
						.debug("interpret(Constraint, IModelObject) - end - return value="
								+ ret);
			}
			return ret;
		}

		EObject exp = (EObject) constraint.getSpecification();
		ret = doSwitch((EObject) exp);

		if (useCache && !modelAccessNeeded)
			env.cacheResult(constraint, ret);

		modelObject.addResult(constraint, ret);
		InterpreterPlugin.getInterpreterRegistry().fireInterpretationFinished(
				constraint, modelObject);

		if (logger.isDebugEnabled()) {
			logger
					.debug("interpret(Constraint, IModelObject) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#prepare(tudresden.ocl20.pivot.pivotmodel.Constraint)
	 */
	public void prepare(Constraint constraint, IModelObject modelObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("prepare(Constraint) - start");
		}

		modelAccessNeeded = false;
		preparation = true;

		this.modelObject = modelObject;
		env.addVar("self", modelObject != null ? modelObject.getOclObject()
				: null);

		EObject exp = (EObject) constraint.getSpecification();
		doSwitch((EObject) exp);

		preparation = false;

		if (logger.isDebugEnabled()) {
			logger.debug("prepare(Constraint) - end");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#removeResults(java.util.List,
	 *      java.util.List)
	 */
	public void removeResults(List<IModelObject> modelObjects,
			List<Constraint> constraints) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("removeResults(List<IModelObject>, List<Constraint>) - start");
		}

		for (IModelObject object : modelObjects) {
			for (Constraint constraint : constraints)
				if (object.removeResult(constraint))
					InterpreterPlugin.getInterpreterRegistry()
							.fireInterpretationFinished(constraint, object);
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("removeResults(List<IModelObject>, List<Constraint>) - end");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#clearResults(java.util.List)
	 */
	public void clearResults(List<IModelObject> modelObjects) {
		if (logger.isDebugEnabled()) {
			logger.debug("clearResults(List<IModelObject>) - start");
		}

		for (IModelObject object : modelObjects)
			if (object.clearResults())
				InterpreterPlugin.getInterpreterRegistry()
						.fireInterpretationFinished(null, object);

		if (logger.isDebugEnabled()) {
			logger.debug("clearResults(List<IModelObject>) - end");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#isUseCache()
	 */
	public boolean isUseCache() {
		if (logger.isDebugEnabled()) {
			logger.debug("isUseCache() - start");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("isUseCache() - end - return value=" + useCache);
		}
		return useCache;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#setUseCache(boolean)
	 */
	public void setUseCache(boolean useCache) {
		if (logger.isDebugEnabled()) {
			logger.debug("setUseCache(boolean) - start");
		}

		this.useCache = useCache;

		if (logger.isDebugEnabled()) {
			logger.debug("setUseCache(boolean) - end");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IOclInterpreter#isModelAccessNeeded()
	 */
	public boolean isModelAccessNeeded() {
		if (logger.isDebugEnabled()) {
			logger.debug("isModelAccessNeeded() - start");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("isModelAccessNeeded() - end - return value="
					+ modelAccessNeeded);
		}
		return modelAccessNeeded;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseBooleanLiteralExp(tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp)
	 */
	@Override
	public OclRoot caseBooleanLiteralExp(BooleanLiteralExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseBooleanLiteralExp(BooleanLiteralExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseBooleanLiteralExp(BooleanLiteralExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		ret = env.getModelInstance().getFactory().createOclBoolean(
				object.isBooleanSymbol());

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseBooleanLiteralExp(BooleanLiteralExp) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseCollectionLiteralExp(tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp)
	 */
	@Override
	public OclRoot caseCollectionLiteralExp(CollectionLiteralExp object) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseCollectionLiteralExp(CollectionLiteralExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseCollectionLiteralExp(CollectionLiteralExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		Iterator<CollectionLiteralPart> it = object.getPart().iterator();

		// OclRoot[] parts = new OclRoot[object.getPart().size()];
		List<OclRoot> parts = new ArrayList<OclRoot>();

		while (it.hasNext()) {
			CollectionLiteralPart part = it.next();
			if (part instanceof CollectionItem)
				parts.add(doSwitch((EObject) part));
			else if (part instanceof CollectionRange) {
				OclRoot current = doSwitch((EObject) ((CollectionRange) part)
						.getFirst());
				OclRoot last = doSwitch((EObject) ((CollectionRange) part)
						.getLast());
				try {
					while (((OclBoolean) current.invokeOperation("isLessEqual",
							new OclRoot[] { last })).isTrue()) {
						parts.add(current);
						current = current.invokeOperation("inkrement",
								new OclRoot[] {});
					}
				} catch (NoSuchMethodException e) {
					logger
							.error(
									"caseCollectionLiteralExp(CollectionLiteralExp)",
									e);

					System.err
							.println("Operation isLessEqual() or inkrement() are "
									+ "not supported by CollectionRange.first type "
									+ ((CollectionRange) part).getFirst());

					if (logger.isDebugEnabled()) {
						logger
								.debug("caseCollectionLiteralExp(CollectionLiteralExp) - end - return value="
										+ null);
					}
					return null;
				}
			}
		}

		String kind = object.getKind().getName();

		if (kind.equals("Set")) {
			ArrayList<OclRoot> temp = new ArrayList<OclRoot>();
			Iterator<OclRoot> itP = parts.iterator();
			while (itP.hasNext()) {
				OclRoot o = itP.next();
				if (!temp.contains(o))
					temp.add(o);
			}
			ret = env.getModelInstance().getFactory().createOclSet(
					temp.toArray(new OclRoot[0]));
		} else if (kind.equals("OrderedSet")) {
			ArrayList<OclRoot> temp = new ArrayList<OclRoot>();
			Iterator<OclRoot> itP = parts.iterator();
			while (itP.hasNext()) {
				OclRoot o = itP.next();
				if (!temp.contains(o))
					temp.add(o);
			}
			ret = env.getModelInstance().getFactory().createOclOrderedSet(
					temp.toArray(new OclRoot[0]));
		} else if (kind.equals("Sequence"))
			ret = env.getModelInstance().getFactory().createOclSequence(
					parts.toArray(new OclRoot[0]));
		else if (kind.equals("Bag"))
			ret = env.getModelInstance().getFactory().createOclBag(
					parts.toArray(new OclRoot[0]));

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseCollectionLiteralExp(CollectionLiteralExp) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseCollectionItem(tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem)
	 */
	@Override
	public OclRoot caseCollectionItem(CollectionItem object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseCollectionItem(CollectionItem) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseCollectionItem(CollectionItem) - end - return value="
								+ ret);
			}
			return ret;
		}

		ret = doSwitch((EObject) object.getItem());

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseCollectionItem(CollectionItem) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseExpressionInOcl(tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl)
	 */
	@Override
	public OclRoot caseExpressionInOcl(ExpressionInOcl object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseExpressionInOcl(ExpressionInOcl) - start");
		}

		OclRoot ret = doSwitch((EObject) object.getBodyExpression());

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseExpressionInOcl(ExpressionInOcl) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseIfExp(tudresden.ocl20.pivot.essentialocl.expressions.IfExp)
	 */
	@Override
	public OclRoot caseIfExp(IfExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseIfExp(IfExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("caseIfExp(IfExp) - end - return value=" + ret);
			}
			return ret;
		}

		OclRoot cond = doSwitch((EObject) object.getCondition());

		OclRoot thenStatement = doSwitch((EObject) object.getThenExpression());

		OclRoot elseStatement = doSwitch((EObject) object.getElseExpression());

		if (cond instanceof OclBoolean)
			ret = ((OclBoolean) cond).ifThenElse(thenStatement, elseStatement);

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger.debug("caseIfExp(IfExp) - end - return value=" + ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseIntegerLiteralExp(tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp)
	 */
	@Override
	public OclRoot caseIntegerLiteralExp(IntegerLiteralExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseIntegerLiteralExp(IntegerLiteralExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseIntegerLiteralExp(IntegerLiteralExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		ret = env.getModelInstance().getFactory().createOclInteger(
				object.getIntegerSymbol());

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseIntegerLiteralExp(IntegerLiteralExp) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseEnumLiteralExp(tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp)
	 */
	@Override
	public OclRoot caseEnumLiteralExp(EnumLiteralExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseEnumLiteralExp(EnumLiteralExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseEnumLiteralExp(EnumLiteralExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		ret = env.getModelInstance().findEnumLiteral(
				Arrays.asList(object.getReferredEnumLiteral()
						.getQualifiedName().split("::")));

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseEnumLiteralExp(EnumLiteralExp) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseOperationCallExp(tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp)
	 */
	@Override
	public OclRoot caseOperationCallExp(OperationCallExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseOperationCallExp(OperationCallExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseOperationCallExp(OperationCallExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		OclRoot[] parameters = new OclRoot[object.getArgument().size()];

		String opName = env.getModelInstance().getOperationName(
				object.getReferredOperation().getName(), parameters.length + 1);

		OclRoot source = null;
		if (object.getReferredOperation().isStatic()) {
			List<String> pathName;
			String typeName = object.getSourceType().getQualifiedName();

			if (StringUtils.isEmpty(typeName))
				pathName = Collections.EMPTY_LIST;
			else
				pathName = Arrays.asList(typeName.split("::"));

			source = env.getModelInstance().findType(pathName);
		} else {
			source = doSwitch((EObject) object.getSource());
		}
		Constraint body = env.getConstraint(object.getReferredOperation()
				.getQualifiedName());
		IEnvironment local = null;
		if (body != null) {
			local = Environment.getNewLocalEnvironment();
		}
		ListIterator<OclExpression> it = object.getArgument().listIterator();
		while (it.hasNext()) {
			OclExpression exp = it.next();
			OclRoot param = doSwitch((EObject) exp);
			parameters[it.previousIndex()] = param;
			if (local != null)
				local.addVar(exp.getName(), param);
		}

		if (opName.equals("atPre")) {
			if (preparation) {
				env.savePostconditionValue(object, source);
				OclRoot returnOclRoot = env.getModelInstance().getUndefined();
				if (logger.isDebugEnabled()) {
					logger
							.debug("caseOperationCallExp(OperationCallExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			} else {
				OclRoot returnOclRoot = env.getPostconditionValue(object);
				if (logger.isDebugEnabled()) {
					logger
							.debug("caseOperationCallExp(OperationCallExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
		}

		if (opName.equals("oclIsNew")) {
			if (preparation) {
				env.savePostconditionValue(object, env.getModelInstance()
						.getFactory().createOclBoolean(
								source instanceof OclVoid));
				OclRoot returnOclRoot = env.getModelInstance().getUndefined();
				if (logger.isDebugEnabled()) {
					logger
							.debug("caseOperationCallExp(OperationCallExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			} else {
				OclRoot returnOclRoot = env.getPostconditionValue(object);
				if (logger.isDebugEnabled()) {
					logger
							.debug("caseOperationCallExp(OperationCallExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
		}

		if (source instanceof OclVoid)
			ret = source;
		else {
			if (body == null) {
				try {
					ret = source.invokeOperation(opName, parameters);
				} catch (NoSuchMethodException e) {
					logger.error("caseOperationCallExp(OperationCallExp)", e);

					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				IOclInterpreter interp = new OclInterpreter(local);
				interp.setUseCache(useCache);
				ret = interp.interpret(body, modelObject);
				modelAccessNeeded = interp.isModelAccessNeeded();
			}
		}

		// If allInstances for some reason is not possible for standard library
		// null is returned. In that case modelInstance.getObjectsOfKind() is
		// used.
		if (opName.equals("allInstances") && (ret instanceof OclVoid)) {
			List<String> typePath;
			String typeName = object.getSourceType().getQualifiedName();

			if (StringUtils.isEmpty(typeName))
				typePath = Collections.EMPTY_LIST;
			else
				typePath = Arrays.asList(typeName.split("::"));

			List<IModelObject> objects = new ArrayList<IModelObject>(env
					.getModelInstance().getObjectsOfKind(typePath));
			List<OclRoot> instances = new ArrayList<OclRoot>(objects.size());
			Iterator<IModelObject> objectsIt = objects.iterator();
			while (objectsIt.hasNext())
				instances.add(objectsIt.next().getOclObject());
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclSet(
							instances.toArray(new OclRoot[instances.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseOperationCallExp(OperationCallExp) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		}

		if ((ret instanceof OclObject) || (source instanceof OclObject))
			modelAccessNeeded = true;

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseOperationCallExp(OperationCallExp) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#casePropertyCallExp(tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp)
	 */
	@Override
	public OclRoot casePropertyCallExp(PropertyCallExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("casePropertyCallExp(PropertyCallExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("casePropertyCallExp(PropertyCallExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		OclRoot sourceExp = null;

		if (object.getReferredProperty().isStatic()) {
			List<String> pathName;
			String typeName = object.getSourceType().getQualifiedName();

			if (StringUtils.isEmpty(typeName))
				pathName = Collections.EMPTY_LIST;
			else
				pathName = Arrays.asList(typeName.split("::"));

			sourceExp = env.getModelInstance().findType(pathName);
		} else {
			sourceExp = doSwitch((EObject) object.getSource());
		}

		IEnvironment local = null;

		Constraint derivedC = env.getConstraint(object.getReferredProperty()
				.getQualifiedName()
				+ "-derive");
		if (derivedC != null) {
			local = Environment.getNewLocalEnvironment();
			IOclInterpreter interp = new OclInterpreter(local);
			interp.setUseCache(useCache);
			ret = interp.interpret(derivedC, modelObject);
			modelAccessNeeded = interp.isModelAccessNeeded();
		}

		if (ret == null) {
			Constraint initC = env.getConstraint(object.getReferredProperty()
					.getQualifiedName()
					+ "-initial");
			if (initC != null) {
				local = Environment.getNewLocalEnvironment();
				IOclInterpreter interp = new OclInterpreter(local);
				interp.setUseCache(useCache);
				ret = interp.interpret(initC, modelObject);
				modelAccessNeeded = interp.isModelAccessNeeded();
			}
		}

		if (ret == null) {
			Constraint body = env.getConstraint(object.getReferredProperty()
					.getQualifiedName());
			if (body != null) {
				local = Environment.getNewLocalEnvironment();
				IOclInterpreter interp = new OclInterpreter(local);
				interp.setUseCache(useCache);
				ret = interp.interpret(body, modelObject);
				modelAccessNeeded = interp.isModelAccessNeeded();
			}
		}

		if (ret == null) {
			try {
				if (object.getType() instanceof OrderedSetType)
					ret = sourceExp.getPropertyValueAsOrderedSet(object
							.getReferredProperty().getName());
				else if (object.getType() instanceof SequenceType)
					ret = sourceExp.getPropertyValueAsSequence(object
							.getReferredProperty().getName());
				else if (object.getType() instanceof SetType)
					ret = sourceExp.getPropertyValueAsSet(object
							.getReferredProperty().getName());
				else if (object.getType() instanceof BagType)
					ret = sourceExp.getPropertyValueAsBag(object
							.getReferredProperty().getName());
				else
					ret = sourceExp.getPropertyValue(object
							.getReferredProperty().getName());
			} catch (NoSuchFieldException e) {
				logger.error("casePropertyCallExp(PropertyCallExp)", e);

				// TODO Auto-generated catch block
				e.printStackTrace();
				OclRoot returnOclRoot = env.getModelInstance().getInvalid();
				if (logger.isDebugEnabled()) {
					logger
							.debug("casePropertyCallExp(PropertyCallExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			} catch (IllegalAccessException e) {
				logger.error("casePropertyCallExp(PropertyCallExp)", e);

				// TODO Auto-generated catch block
				e.printStackTrace();
				OclRoot returnOclRoot = env.getModelInstance().getInvalid();
				if (logger.isDebugEnabled()) {
					logger
							.debug("casePropertyCallExp(PropertyCallExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
		}

		if (ret instanceof OclObject)
			modelAccessNeeded = true;

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("casePropertyCallExp(PropertyCallExp) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseRealLiteralExp(tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp)
	 */
	@Override
	public OclRoot caseRealLiteralExp(RealLiteralExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseRealLiteralExp(RealLiteralExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseRealLiteralExp(RealLiteralExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		ret = env.getModelInstance().getFactory().createOclReal(
				object.getRealSymbol());

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseRealLiteralExp(RealLiteralExp) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseStringLiteralExp(tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp)
	 */
	@Override
	public OclRoot caseStringLiteralExp(StringLiteralExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseStringLiteralExp(StringLiteralExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseStringLiteralExp(StringLiteralExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		ret = env.getModelInstance().getFactory().createOclString(
				object.getStringSymbol());

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseStringLiteralExp(StringLiteralExp) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseVariable(tudresden.ocl20.pivot.essentialocl.expressions.Variable)
	 */
	@Override
	public OclRoot caseVariable(Variable object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseVariable(Variable) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("caseVariable(Variable) - end - return value="
						+ ret);
			}
			return ret;
		}

		OclExpression initExp = object.getInitExpression();
		OclRoot init = null;

		if (initExp != null) {
			init = doSwitch((EObject) object.getInitExpression());
		}

		ret = env.getVar(object.getName());
		if (ret == null) {
			if (init != null) {
				ret = init;
				env.addVar(object.getQualifiedName(), ret);
			} else {
				System.out.println("Unknown Variable");
				ret = env.getModelInstance().getUndefined();
			}
		}

		if (ret instanceof OclObject)
			modelAccessNeeded = true;

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger.debug("caseVariable(Variable) - end - return value=" + ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseTupleLiteralExp(tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp)
	 */
	@Override
	public OclRoot caseTupleLiteralExp(TupleLiteralExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseTupleLiteralExp(TupleLiteralExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseTupleLiteralExp(TupleLiteralExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		List<String> partNames = new ArrayList<String>(object.getPart().size());
		List<OclRoot> partValues = new ArrayList<OclRoot>(object.getPart()
				.size());

		Iterator<TupleLiteralPart> partsIt = object.getPart().iterator();

		while (partsIt.hasNext()) {
			TupleLiteralPart part = partsIt.next();
			partNames.add(part.getProperty().getName());
			partValues.add(doSwitch((EObject) part));
		}

		ret = env.getModelInstance().getFactory().createOclTuple(
				partNames.toArray(new String[0]),
				partValues.toArray(new OclRoot[0]));

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseTupleLiteralExp(TupleLiteralExp) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseTupleLiteralPart(tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart)
	 */
	@Override
	public OclRoot caseTupleLiteralPart(TupleLiteralPart object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseTupleLiteralPart(TupleLiteralPart) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseTupleLiteralPart(TupleLiteralPart) - end - return value="
								+ ret);
			}
			return ret;
		}

		ret = doSwitch((EObject) object.getValue());

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseTupleLiteralPart(TupleLiteralPart) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseLetExp(tudresden.ocl20.pivot.essentialocl.expressions.LetExp)
	 */
	@Override
	public OclRoot caseLetExp(LetExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseLetExp(LetExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("caseLetExp(LetExp) - end - return value=" + ret);
			}
			return ret;
		}

		ret = doSwitch((EObject) object.getIn());

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger.debug("caseLetExp(LetExp) - end - return value=" + ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseVariableExp(tudresden.ocl20.pivot.essentialocl.expressions.VariableExp)
	 */
	@Override
	public OclRoot caseVariableExp(VariableExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseVariableExp(VariableExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseVariableExp(VariableExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		ret = doSwitch((EObject) object.getReferredVariable());

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger.debug("caseVariableExp(VariableExp) - end - return value="
					+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseTypeLiteralExp(tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp)
	 */
	@Override
	public OclRoot caseTypeLiteralExp(TypeLiteralExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseTypeLiteralExp(TypeLiteralExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseTypeLiteralExp(TypeLiteralExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		List<String> pathName;
		String typeName = object.getReferredType().getQualifiedName();

		if (StringUtils.isEmpty(typeName))
			pathName = Collections.EMPTY_LIST;
		else
			pathName = Arrays.asList(typeName.split("::"));

		ret = env.getModelInstance().findType(pathName);

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseTypeLiteralExp(TypeLiteralExp) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseInvalidLiteralExp(tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp)
	 */
	@Override
	public OclRoot caseInvalidLiteralExp(InvalidLiteralExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseInvalidLiteralExp(InvalidLiteralExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseInvalidLiteralExp(InvalidLiteralExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		ret = env.getModelInstance().getInvalid();

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseInvalidLiteralExp(InvalidLiteralExp) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseUndefinedLiteralExp(tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp)
	 */
	@Override
	public OclRoot caseUndefinedLiteralExp(UndefinedLiteralExp object) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("caseUndefinedLiteralExp(UndefinedLiteralExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseUndefinedLiteralExp(UndefinedLiteralExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		ret = env.getModelInstance().getUndefined();

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger
					.debug("caseUndefinedLiteralExp(UndefinedLiteralExp) - end - return value="
							+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseIterateExp(tudresden.ocl20.pivot.essentialocl.expressions.IterateExp)
	 */
	@Override
	public OclRoot caseIterateExp(IterateExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseIterateExp(IterateExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("caseIterateExp(IterateExp) - end - return value="
						+ ret);
			}
			return ret;
		}

		OclRoot source = doSwitch((EObject) object.getSource());

		OclCollection<OclRoot> col = null;

		if (source instanceof OclCollection) {
			col = (OclCollection<OclRoot>) source;
		} else
			col = source.asSet();

		// reset accumulator variable
		env.addVar(object.getResult().getQualifiedName(), null);

		ret = evaluateIterate(object.getBody(), col, object.getIterator(), col
				.getIterator(), object.getResult().getQualifiedName());

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger.debug("caseIterateExp(IterateExp) - end - return value="
					+ ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#caseIteratorExp(tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp)
	 */
	public OclRoot caseIteratorExp(IteratorExp object) {
		if (logger.isDebugEnabled()) {
			logger.debug("caseIteratorExp(IteratorExp) - start");
		}

		OclRoot ret = null;

		if (useCache && (ret = env.getCachedResult(object)) != null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("caseIteratorExp(IteratorExp) - end - return value="
								+ ret);
			}
			return ret;
		}

		OclRoot source = doSwitch((EObject) object.getSource());

		OclCollection<OclRoot> col = null;

		if (source instanceof OclCollection) {
			col = (OclCollection<OclRoot>) source;
		} else
			col = source.asSet();

		if (object.getName().equals("exists")) {
			ret = evaluateExists(object.getBody(), col, object.getIterator(),
					col.getIterator());
		} else if (object.getName().equals("select")) {
			if (object.getIterator().size() > 1) {
				System.err
						.println("select() may have at most one iterator variable");
				OclRoot returnOclRoot = env.getModelInstance().getInvalid();
				if (logger.isDebugEnabled()) {
					logger
							.debug("caseIteratorExp(IteratorExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
			ret = evaluateSelect(object.getBody(), col, object.getIterator()
					.get(0), object.getType());
		} else if (object.getName().equals("forAll")) {
			ret = evaluateForAll(object.getBody(), col, object.getIterator(),
					col.getIterator());
		} else if (object.getName().equals("isUnique")) {
			if (object.getIterator().size() > 1) {
				System.err
						.println("isUnique() may have at most one iterator variable");
				OclRoot returnOclRoot = env.getModelInstance().getInvalid();
				if (logger.isDebugEnabled()) {
					logger
							.debug("caseIteratorExp(IteratorExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
			ret = evaluateIsUnique(object.getBody(), col, object.getIterator()
					.get(0));
		} else if (object.getName().equals("any")) {
			if (object.getIterator().size() > 1) {
				System.err
						.println("any() may have at most one iterator variable");
				OclRoot returnOclRoot = env.getModelInstance().getInvalid();
				if (logger.isDebugEnabled()) {
					logger
							.debug("caseIteratorExp(IteratorExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
			ret = evaluateAny(object.getBody(), col, object.getIterator()
					.get(0));
		} else if (object.getName().equals("one")) {
			if (object.getIterator().size() > 1) {
				System.err
						.println("one() may have at most one iterator variable");
				OclRoot returnOclRoot = env.getModelInstance().getInvalid();
				if (logger.isDebugEnabled()) {
					logger
							.debug("caseIteratorExp(IteratorExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
			ret = evaluateOne(object.getBody(), col, object.getIterator()
					.get(0));
		} else if (object.getName().equals("reject")) {
			if (object.getIterator().size() > 1) {
				System.err
						.println("reject() may have at most one iterator variable");
				OclRoot returnOclRoot = env.getModelInstance().getInvalid();
				if (logger.isDebugEnabled()) {
					logger
							.debug("caseIteratorExp(IteratorExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
			ret = evaluateReject(object.getBody(), col, object.getIterator()
					.get(0), object.getType());
		} else if (object.getName().equals("sortedBy")) {
			if (object.getIterator().size() > 1) {
				System.err
						.println("sortedBy() may have at most one iterator variable");
				OclRoot returnOclRoot = env.getModelInstance().getInvalid();
				if (logger.isDebugEnabled()) {
					logger
							.debug("caseIteratorExp(IteratorExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
			ret = evaluateSortedBy(object.getBody(), col, object.getIterator()
					.get(0), object.getType());
		} else if (object.getName().equals("collectNested")) {
			if (object.getIterator().size() > 1) {
				System.err
						.println("collect() may have at most one iterator variable");
				OclRoot returnOclRoot = env.getModelInstance().getInvalid();
				if (logger.isDebugEnabled()) {
					logger
							.debug("caseIteratorExp(IteratorExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
			ret = evaluateCollectNested(object.getBody(), col, object
					.getIterator().get(0), object.getType());
		} else if (object.getName().equals("collect")) {
			if (object.getIterator().size() > 1) {
				System.err
						.println("collect() may have at most one iterator variable");
				OclRoot returnOclRoot = env.getModelInstance().getInvalid();
				if (logger.isDebugEnabled()) {
					logger
							.debug("caseIteratorExp(IteratorExp) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
			ret = evaluateCollectNested(object.getBody(), col, object
					.getIterator().get(0), object.getType());
			if (ret instanceof OclCollection)
				ret = ((OclCollection) ret).flatten();
		}

		if (useCache && !modelAccessNeeded)
			env.cacheResult(object, ret);

		if (logger.isDebugEnabled()) {
			logger.debug("caseIteratorExp(IteratorExp) - end - return value="
					+ ret);
		}
		return ret;
	}

	/**
	 * Evaluates general iterate method. Will be invoked recursive for every
	 * iterator of the iteration
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
	private OclRoot evaluateIterate(OclExpression body,
			OclCollection<OclRoot> source, List<Variable> iterators,
			OclIterator<OclRoot> it, String resultVarName) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateIterate(OclExpression, OclCollection<OclRoot>, List<Variable>, OclIterator<OclRoot>, String) - start");
		}

		OclRoot bodyResult = null;
		while (it.hasNext().isTrue()) {
			OclRoot activeObject = it.next();
			env.addVar(iterators.get(0).getQualifiedName(), activeObject);
			if (iterators.size() > 1) {
				List<Variable> tempIt = new ArrayList<Variable>(iterators);
				tempIt.remove(0);
				OclIterator nextIt = source.getIterator();
				bodyResult = evaluateIterate(body, source, tempIt, nextIt,
						resultVarName);
			} else {
				bodyResult = doSwitch((EObject) body);
			}
			env.addVar(resultVarName, bodyResult);
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateIterate(OclExpression, OclCollection<OclRoot>, List<Variable>, OclIterator<OclRoot>, String) - end - return value="
							+ bodyResult);
		}
		return bodyResult;
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
	private OclRoot evaluateExists(OclExpression body,
			OclCollection<OclRoot> source, List<Variable> iterators,
			OclIterator<OclRoot> it) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateExists(OclExpression, OclCollection<OclRoot>, List<Variable>, OclIterator<OclRoot>) - start");
		}

		while (it.hasNext().isTrue()) {
			OclRoot activeObject = it.next();
			env.addVar(iterators.get(0).getQualifiedName(), activeObject);
			OclBoolean bodyResult = null;
			if (iterators.size() > 1) {
				List<Variable> tempIt = new ArrayList<Variable>(iterators);
				tempIt.remove(0);
				OclIterator nextIt = source.getIterator();
				bodyResult = (OclBoolean) evaluateExists(body, source, tempIt,
						nextIt);
			} else {
				bodyResult = (OclBoolean) doSwitch((EObject) body);
			}
			if (bodyResult.isTrue()) {
				OclRoot returnOclRoot = env.getModelInstance().getFactory()
						.createOclBoolean(true);
				if (logger.isDebugEnabled()) {
					logger
							.debug("evaluateExists(OclExpression, OclCollection<OclRoot>, List<Variable>, OclIterator<OclRoot>) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
		}
		OclRoot returnOclRoot = env.getModelInstance().getFactory()
				.createOclBoolean(false);
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateExists(OclExpression, OclCollection<OclRoot>, List<Variable>, OclIterator<OclRoot>) - end - return value="
							+ returnOclRoot);
		}
		return returnOclRoot;
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
	private OclRoot evaluateForAll(OclExpression body,
			OclCollection<OclRoot> source, List<Variable> iterators,
			OclIterator<OclRoot> it) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateForAll(OclExpression, OclCollection<OclRoot>, List<Variable>, OclIterator<OclRoot>) - start");
		}

		while (it.hasNext().isTrue()) {
			OclRoot activeObject = it.next();
			env.addVar(iterators.get(0).getQualifiedName(), activeObject);
			OclBoolean bodyResult = null;
			if (iterators.size() > 1) {
				List<Variable> tempIt = new ArrayList<Variable>(iterators);
				tempIt.remove(0);
				OclIterator nextIt = source.getIterator();
				bodyResult = (OclBoolean) evaluateForAll(body, source, tempIt,
						nextIt);
			} else {
				bodyResult = (OclBoolean) doSwitch((EObject) body);
			}
			if (!bodyResult.isTrue()) {
				OclRoot returnOclRoot = env.getModelInstance().getFactory()
						.createOclBoolean(false);
				if (logger.isDebugEnabled()) {
					logger
							.debug("evaluateForAll(OclExpression, OclCollection<OclRoot>, List<Variable>, OclIterator<OclRoot>) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
		}
		OclRoot returnOclRoot = env.getModelInstance().getFactory()
				.createOclBoolean(true);
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateForAll(OclExpression, OclCollection<OclRoot>, List<Variable>, OclIterator<OclRoot>) - end - return value="
							+ returnOclRoot);
		}
		return returnOclRoot;
	}

	/**
	 * Results in true if body evaluates to a different value for each element
	 * in the source collection; otherwise, result is false.
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
	private OclRoot evaluateIsUnique(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateIsUnique(OclExpression, OclCollection<OclRoot>, Variable) - start");
		}

		OclIterator<OclRoot> it = source.getIterator();
		List<OclRoot> uniqueList = new ArrayList<OclRoot>();
		while (it.hasNext().isTrue()) {
			OclRoot activeObject = it.next();
			env.addVar(iterator.getQualifiedName(), activeObject);
			OclRoot bodyResult = doSwitch((EObject) body);
			if (!uniqueList.contains(bodyResult))
				uniqueList.add(bodyResult);
			else {
				OclRoot returnOclRoot = env.getModelInstance().getFactory()
						.createOclBoolean(false);
				if (logger.isDebugEnabled()) {
					logger
							.debug("evaluateIsUnique(OclExpression, OclCollection<OclRoot>, Variable) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
		}
		OclRoot returnOclRoot = env.getModelInstance().getFactory()
				.createOclBoolean(true);
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateIsUnique(OclExpression, OclCollection<OclRoot>, Variable) - end - return value="
							+ returnOclRoot);
		}
		return returnOclRoot;
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
	private OclRoot evaluateAny(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateAny(OclExpression, OclCollection<OclRoot>, Variable) - start");
		}

		OclIterator<OclRoot> it = source.getIterator();
		while (it.hasNext().isTrue()) {
			OclRoot activeObject = it.next();
			env.addVar(iterator.getQualifiedName(), activeObject);
			OclBoolean bodyResult = (OclBoolean) doSwitch((EObject) body);
			if (bodyResult.isTrue()) {
				if (logger.isDebugEnabled()) {
					logger
							.debug("evaluateAny(OclExpression, OclCollection<OclRoot>, Variable) - end - return value="
									+ activeObject);
				}
				return activeObject;
			}
		}
		OclRoot returnOclRoot = env.getModelInstance().getUndefined();
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateAny(OclExpression, OclCollection<OclRoot>, Variable) - end - return value="
							+ returnOclRoot);
		}
		return returnOclRoot;
	}

	/**
	 * The collection of elements which results from applying body to every
	 * member of the source set.
	 * 
	 * @param body
	 *            the body expression to be evaluated
	 * @param source
	 *            the collection representing the source expression of the
	 *            iteration
	 * @param iterator
	 *            the iterator (collectNested may have at most one iterator
	 *            variable.)
	 * @param resultType
	 *            the result type (set, sequence, bag, orderedSet)
	 * 
	 * @return the result of the iteration
	 */
	private OclRoot evaluateCollectNested(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateCollectNested(OclExpression, OclCollection<OclRoot>, Variable, Type) - start");
		}

		OclIterator<OclRoot> it = source.getIterator();
		List<OclRoot> resultList = new ArrayList<OclRoot>();
		while (it.hasNext().isTrue()) {
			OclRoot activeObject = it.next();
			env.addVar(iterator.getQualifiedName(), activeObject);
			OclRoot bodyResult = doSwitch((EObject) body);
			resultList.add(bodyResult);
		}

		if (resultType instanceof BagType) {
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclBag(
							resultList.toArray(new OclRoot[resultList.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateCollectNested(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		} else if (resultType instanceof SequenceType) {
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclSequence(
							resultList.toArray(new OclRoot[resultList.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateCollectNested(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		} else {
			OclRoot returnOclRoot = env.getModelInstance().getInvalid();
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateCollectNested(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		}
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
	private OclRoot evaluateOne(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateOne(OclExpression, OclCollection<OclRoot>, Variable) - start");
		}

		OclIterator<OclRoot> it = source.getIterator();
		boolean one = false;
		while (it.hasNext().isTrue()) {
			OclRoot activeObject = it.next();
			env.addVar(iterator.getQualifiedName(), activeObject);
			OclBoolean bodyResult = (OclBoolean) doSwitch((EObject) body);
			if (bodyResult.isTrue()) {
				if (one == true) {
					OclRoot returnOclRoot = env.getModelInstance().getFactory()
							.createOclBoolean(false);
					if (logger.isDebugEnabled()) {
						logger
								.debug("evaluateOne(OclExpression, OclCollection<OclRoot>, Variable) - end - return value="
										+ returnOclRoot);
					}
					return returnOclRoot;
				} else
					one = true;
			}
		}
		OclRoot returnOclRoot = env.getModelInstance().getFactory()
				.createOclBoolean(one);
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateOne(OclExpression, OclCollection<OclRoot>, Variable) - end - return value="
							+ returnOclRoot);
		}
		return returnOclRoot;
	}

	/**
	 * The sub collection of source for which body is true.
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
	private OclRoot evaluateSelect(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateSelect(OclExpression, OclCollection<OclRoot>, Variable, Type) - start");
		}

		OclIterator<OclRoot> it = source.getIterator();
		List<OclRoot> resultList = new ArrayList<OclRoot>();
		while (it.hasNext().isTrue()) {
			OclRoot activeObject = it.next();
			env.addVar(iterator.getQualifiedName(), activeObject);
			OclBoolean bodyResult = (OclBoolean) doSwitch((EObject) body);
			if (bodyResult.isTrue())
				resultList.add(activeObject);
		}
		if (resultType instanceof SetType) {
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclSet(
							resultList.toArray(new OclRoot[resultList.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateSelect(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		} else if (resultType instanceof BagType) {
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclBag(
							resultList.toArray(new OclRoot[resultList.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateSelect(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		} else if (resultType instanceof SequenceType) {
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclSequence(
							resultList.toArray(new OclRoot[resultList.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateSelect(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		} else if (resultType instanceof OrderedSetType) {
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclOrderedSet(
							resultList.toArray(new OclRoot[resultList.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateSelect(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		} else {
			OclRoot returnOclRoot = env.getModelInstance().getInvalid();
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateSelect(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		}
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
	private OclRoot evaluateReject(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateReject(OclExpression, OclCollection<OclRoot>, Variable, Type) - start");
		}

		OclIterator<OclRoot> it = source.getIterator();
		List<OclRoot> resultList = new ArrayList<OclRoot>();
		while (it.hasNext().isTrue()) {
			OclRoot activeObject = it.next();
			env.addVar(iterator.getQualifiedName(), activeObject);
			OclBoolean bodyResult = (OclBoolean) doSwitch((EObject) body);
			if (!bodyResult.isTrue())
				resultList.add(activeObject);
		}

		if (resultType instanceof SetType) {
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclSet(
							resultList.toArray(new OclRoot[resultList.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateReject(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		} else if (resultType instanceof BagType) {
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclBag(
							resultList.toArray(new OclRoot[resultList.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateReject(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		} else if (resultType instanceof SequenceType) {
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclSequence(
							resultList.toArray(new OclRoot[resultList.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateReject(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		} else if (resultType instanceof OrderedSetType) {
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclOrderedSet(
							resultList.toArray(new OclRoot[resultList.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateReject(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		} else {
			OclRoot returnOclRoot = env.getModelInstance().getInvalid();
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateReject(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		}
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
	private OclRoot evaluateSortedBy(OclExpression body,
			OclCollection<OclRoot> source, Variable iterator, Type resultType) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("evaluateSortedBy(OclExpression, OclCollection<OclRoot>, Variable, Type) - start");
		}

		Comparator<OclComparable> comp = new Comparator<OclComparable>() {
			public int compare(OclComparable o1, OclComparable o2) {
				if (logger.isDebugEnabled()) {
					logger
							.debug("compare(OclComparable, OclComparable) - start");
				}

				OclInteger result = o1.compareTo(o2);
				if (result
						.isEqualTo(
								env.getModelInstance().getFactory()
										.createOclInteger(1)).isTrue()) {
					if (logger.isDebugEnabled()) {
						logger
								.debug("compare(OclComparable, OclComparable) - end - return value=" + 1);
					}
					return 1;
				}
				if (result.isEqualTo(
						env.getModelInstance().getFactory()
								.createOclInteger(-1)).isTrue()) {
					int returnint = -1;
					if (logger.isDebugEnabled()) {
						logger
								.debug("compare(OclComparable, OclComparable) - end - return value="
										+ returnint);
					}
					return returnint;
				}

				if (logger.isDebugEnabled()) {
					logger
							.debug("compare(OclComparable, OclComparable) - end - return value=" + 0);
				}
				return 0;
			}
		};

		Map<OclComparable, OclRoot> results = new TreeMap<OclComparable, OclRoot>(
				comp);

		OclIterator<OclRoot> it = source.getIterator();
		while (it.hasNext().isTrue()) {
			OclRoot activeObject = it.next();
			env.addVar(iterator.getQualifiedName(), activeObject);
			OclRoot bodyResult = doSwitch((EObject) body);
			if (bodyResult instanceof OclComparable)
				results.put((OclComparable) bodyResult, activeObject);
			else {
				OclRoot returnOclRoot = env.getModelInstance().getInvalid();
				if (logger.isDebugEnabled()) {
					logger
							.debug("evaluateSortedBy(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
									+ returnOclRoot);
				}
				return returnOclRoot;
			}
		}

		List<OclRoot> resultList = new ArrayList<OclRoot>(results.values());

		if (resultType instanceof SequenceType) {
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclSequence(
							resultList.toArray(new OclRoot[resultList.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateSortedBy(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		} else if (resultType instanceof OrderedSetType) {
			OclRoot returnOclRoot = env.getModelInstance().getFactory()
					.createOclOrderedSet(
							resultList.toArray(new OclRoot[resultList.size()]));
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateSortedBy(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		} else {
			OclRoot returnOclRoot = env.getModelInstance().getInvalid();
			if (logger.isDebugEnabled()) {
				logger
						.debug("evaluateSortedBy(OclExpression, OclCollection<OclRoot>, Variable, Type) - end - return value="
								+ returnOclRoot);
			}
			return returnOclRoot;
		}
	}

}