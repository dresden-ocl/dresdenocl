/*
 * Copyright (C) 2008-2010 by Claas Wilke (claas.wilke@tu-dresden.de)
 *
 * This file is part of the OCL2Java Code Generator of Dresden OCL.
 *
 * Dresden OCL is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * Dresden OCL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along 
 * with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.tools.codegen.ocl2java.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import tudresden.ocl20.pivot.essentialocl.expressions.UnlimitedNaturalExp;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.expressions.VariableExp;
import tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch;
import tudresden.ocl20.pivot.essentialocl.types.AnyType;
import tudresden.ocl20.pivot.essentialocl.types.BagType;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.InvalidType;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.essentialocl.types.OrderedSetType;
import tudresden.ocl20.pivot.essentialocl.types.SequenceType;
import tudresden.ocl20.pivot.essentialocl.types.SetType;
import tudresden.ocl20.pivot.essentialocl.types.TupleType;
import tudresden.ocl20.pivot.essentialocl.types.TypeType;
import tudresden.ocl20.pivot.essentialocl.types.VoidType;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelConstants;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Expression;
import tudresden.ocl20.pivot.pivotmodel.Feature;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.tools.codegen.code.ITransformedCode;
import tudresden.ocl20.pivot.tools.codegen.code.ITransformedType;
import tudresden.ocl20.pivot.tools.codegen.code.impl.TransformedCodeImpl;
import tudresden.ocl20.pivot.tools.codegen.code.impl.TransformedTypeImpl;
import tudresden.ocl20.pivot.tools.codegen.exception.Ocl2CodeException;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.IOcl2Java;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.IOcl2JavaSettings;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.code.IOcl2JavaEnvironment;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.code.impl.Ocl2JavaEnvironment;
import tudresden.ocl20.pivot.tools.template.ITemplate;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

/**
 * <p>
 * This class provides the logic to transform java code from given
 * {@link Constraint}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public final class Ocl2Java extends ExpressionsSwitch<ITransformedCode>
		implements IOcl2Java {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = Logger.getLogger(Ocl2Java.class);

	/** The paths of the String templates for the code transformation. */
	private final static String TEMPLATE_PATH = "/resources/template/java/";
	private final static String EXPRESSION_TEMPLATE_FILE = "expressions.stg";
	private final static String JAVA_TEMPLATE_FILE = "java.stg";
	private final static String INSTRUMENTATION_TEMPLATE_FILE = "instrumentations.stg";
	private final static String OPERATION_TEMPLATE_FILE = "operations.stg";
	private final static String TYPE_TEMPLATE_FILE = "types.stg";

	/**
	 * Contains operation names that must be renamed to support them in
	 * languages like Java. E.g., '=' ist renamed to equals.
	 */
	private static Map<String, String> renamedOperationNames = new HashMap<String, String>();

	/* static initialization. */
	static {
		renamedOperationNames.put("=", "equals");
		renamedOperationNames.put("<>", "notEquals");
		renamedOperationNames.put("+", "plus");
		renamedOperationNames.put("-", "minus");
		renamedOperationNames.put("*", "multiply");
		renamedOperationNames.put("/", "division");
		renamedOperationNames.put("<", "lesser");
		renamedOperationNames.put(">", "greater");
		renamedOperationNames.put("<=", "lesserEquals");
		renamedOperationNames.put(">=", "greaterEquals");
	}

	/**
	 * The environment to provide and store some values during code
	 * transformation.
	 */
	private IOcl2JavaEnvironment environment;

	/** The Settings used during code generation. */
	private IOcl2JavaSettings settings;

	/**
	 * The engine to provide all {@link ITemplate}s used for code
	 * transformation.
	 */
	private ITemplateGroup templateGroup;

	/* FIXME Claas: Move this to environment. */
	private Map<String, String> variableNames = new HashMap<String, String>();

	{
		variableNames.put("class", "clazz");
		variableNames.put("extends", "ixtends");
	}

	/**
	 * <p>
	 * Creates a new {@link Ocl2Java} instance.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *             If the initialization fails.
	 */
	public Ocl2Java() throws Ocl2CodeException {

		this.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl2Code#getSettings()
	 */
	public IOcl2JavaSettings getSettings() {

		return this.settings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl22Code#resetEnvironment()
	 */
	public void resetEnvironment() {

		this.environment = new Ocl2JavaEnvironment();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.tools.codegen.IOcl2Code#setSettings(tudresden.ocl20
	 * .pivot.tools.codegen.IOcl2CodeSettings)
	 */
	public void setSettings(IOcl2JavaSettings settings) {

		this.settings = settings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.IOcl2Code#transformFragmentCode(java.util
	 * .List)
	 */
	public List<String> transformFragmentCode(List<Constraint> constraints)
			throws Ocl2CodeException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformFragmentCode(List<Constraint>) - start");
		}
		// no else.

		List<String> result = new ArrayList<String>(constraints.size());

		for (Constraint aConstraint : constraints) {

			this.environment.resetEnvironmentForNextConstraint();

			ITransformedCode aTransformedConstraint = this
					.transformFragmentCode(aConstraint);
			String aResult = aTransformedConstraint.getCode();

			if (aResult.length() > 0) {
				aResult += "\n";
			}
			// no else.

			aResult += aTransformedConstraint.getResultExp();
			result.add(aResult);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformFragmentCode(List<Constraint>)"
					+ " - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.IOcl2Code#transformInstrumentationCode
	 * (java.util.List)
	 */
	public List<String> transformInstrumentationCode(
			List<Constraint> constraints) throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInstrumentationCode"
					+ "(List<Constraint>) - start");
		}
		// no else.

		List<String> result = new ArrayList<String>(constraints.size());

		/* Iterate through all constraints and compute their code. */
		for (Constraint aConstraint : constraints) {
			this.environment.resetEnvironmentForNextConstraint();

			result.add(this.transformInstrumentationCode(aConstraint));
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInstrumentationCode"
					+ "(List<Constraint>) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseBooleanLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp)
	 */
	public ITransformedCode caseBooleanLiteralExp(
			BooleanLiteralExp aBooleanLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseBooleanLiteralExp(BooleanLiteralExp) - start");
		}
		// no else.

		ITemplate template = this.templateGroup.getTemplate("literalExp");
		template.setAttribute("type",
				this.transformType(aBooleanLiteralExp.getType()).toString());
		template.setAttribute("value", aBooleanLiteralExp.isBooleanSymbol());

		ITransformedCode result = new TransformedCodeImpl();
		result.setResultExp(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseBooleanLiteralExp(BooleanLiteralExp)"
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
	 * #caseCollectionLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp)
	 */
	public ITransformedCode caseCollectionLiteralExp(
			CollectionLiteralExp aCollectionLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseCollectionLiteralExp(CollectionLiteralExp) - start");
		}
		// no else.

		ITransformedCode result = new TransformedCodeImpl();

		/* Compute the result type first. */
		ITransformedType resultType = this
				.transformInitializableType(aCollectionLiteralExp.getType());
		String collectionName = this.environment.getNewCollectionVarName();

		/* Prepare the template for the collection expression. */
		ITemplate template = this.templateGroup
				.getTemplate("collectionLiteralExp");
		template.setAttribute("collectionName", collectionName);
		template.setAttribute("collectionType", resultType.toString());

		/* Transform Code for all initial elements. */
		for (CollectionLiteralPart aCollectionPart : aCollectionLiteralExp
				.getPart()) {

			/* Transform initial Code for all CollectionItems. */
			if (aCollectionPart instanceof CollectionItem) {

				CollectionItem anItem = (CollectionItem) aCollectionPart;
				ITransformedCode anItemsCode = this.doSwitch(anItem.getItem());

				/* Transform code for element expression. */
				ITemplate elemTemplate = this.templateGroup
						.getTemplate("collectionLiteralExp_collectionItem");
				elemTemplate.setAttribute("collectionName", collectionName);
				elemTemplate
						.setAttribute("itemExp", anItemsCode.getResultExp());

				/* Add element code to collection code. */
				template.setAttribute("elementCodes", anItemsCode.getCode());
				template.setAttribute("elementExps", elemTemplate.toString());
			}

			/* Collection can also be initialized using a for-loop. */
			else if (aCollectionPart instanceof CollectionRange) {

				CollectionRange aRange = (CollectionRange) aCollectionPart;
				ITransformedCode firstExpCode = this
						.doSwitch(aRange.getFirst());
				ITransformedType firstExpType = this.transformType(aRange
						.getFirst().getType());

				ITransformedCode lastExpCode = this.doSwitch(aRange.getLast());

				String elementCode = firstExpCode.getCode();
				if (elementCode.length() > 0) {
					elementCode += "\n";
				}
				// no else.
				elementCode = lastExpCode.getCode();

				ITemplate elemTemplate = this.templateGroup
						.getTemplate("collectionLiteralExp_collectionRange");
				elemTemplate.setAttribute("collectionName", collectionName);
				elemTemplate.setAttribute("indexVar",
						this.environment.getNewIndexVarName());
				elemTemplate.setAttribute("indexType", firstExpType.toString());
				elemTemplate.setAttribute("firstExp",
						firstExpCode.getResultExp());
				elemTemplate
						.setAttribute("lastExp", lastExpCode.getResultExp());

				/* Add element code to collection code. */
				template.setAttribute("elementCodes", elementCode);
				template.setAttribute("elementExps", elemTemplate.toString());
			}
		}

		result.addCode(template.toString());
		result.setResultExp(collectionName);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseCollectionLiteralExp(CollectionLiteralExp)"
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
	 * #caseEnumLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp)
	 */
	public ITransformedCode caseEnumLiteralExp(EnumLiteralExp anEnumLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseEnumLiteralExp(EnumLiteralExp) - start");
		}
		// no else.

		ITransformedCode result = new TransformedCodeImpl();

		EnumerationLiteral anEnumerationLiteral = anEnumLiteralExp
				.getReferredEnumLiteral();
		anEnumerationLiteral.getEnumeration();

		Enumeration anEnumeration = anEnumerationLiteral.getEnumeration();

		/* Use a canonical name for the enumeration name. */
		String enumerationName = anEnumeration.getQualifiedName();
		enumerationName = enumerationName.replaceAll("::", ".");

		/* Probably remove the root package. */
		if (enumerationName.startsWith(ModelConstants.ROOT_PACKAGE_NAME)) {
			enumerationName = enumerationName.substring(5);
		}
		// no else.

		ITemplate template = this.templateGroup.getTemplate("enumLiteralExp");
		template.setAttribute("enumerationName", enumerationName);
		template.setAttribute("literalName", anEnumerationLiteral.getName());

		result.setResultExp(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseEnumLiteralExp(EnumLiteralExp)"
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
	public ITransformedCode caseExpressionInOcl(
			ExpressionInOcl anExpressionInOcl) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseExpressionInOcl(ExpressionInOcl) - start");
		}
		// no else.

		/* Transform bodyCode. */
		ITransformedCode result = this.doSwitch(anExpressionInOcl
				.getBodyExpression());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseExpressionInOcl(ExpressionInOcl)"
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
	 * #caseIfExp(tudresden.ocl20.pivot.essentialocl.expressions.IfExp)
	 */
	public ITransformedCode caseIfExp(IfExp anIfExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIfExp(IfExp) - start");
		}
		// no else.

		ITransformedCode result = new TransformedCodeImpl();

		/* Transform ifExp, thenExp and elseExp. */
		ITransformedCode ifCode = doSwitch(anIfExp.getCondition());
		ITransformedCode thenCode = doSwitch(anIfExp.getThenExpression());
		ITransformedCode elseCode = doSwitch(anIfExp.getElseExpression());

		/* Declare variable for result of if-then-else expression. */
		String resultVarName = this.environment.getNewIfExpResultName();
		result.setResultExp(resultVarName);

		ITransformedType resultType = this.transformType(anIfExp.getType());

		/* Transform ifExp. */
		ITemplate template = this.templateGroup.getTemplate("ifExp");
		template.setAttribute("ifCode", ifCode.getCode());
		template.setAttribute("ifExp", ifCode.getResultExp());
		template.setAttribute("thenCode", thenCode.getCode());
		template.setAttribute("thenExp", thenCode.getResultExp());
		template.setAttribute("elseCode", elseCode.getCode());
		template.setAttribute("elseExp", elseCode.getResultExp());
		template.setAttribute("resultVar", resultVarName);
		template.setAttribute("resultType", resultType.toString());

		result.addCode(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIfExp(IfExp)" + " - end - return value=" + result);
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
	public ITransformedCode caseIntegerLiteralExp(
			IntegerLiteralExp anIntegerLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIntegerLiteralExp(IntegerLiteralExp) - start");
		}
		// no else.

		ITemplate template = this.templateGroup.getTemplate("literalExp");
		template.setAttribute("type",
				this.transformType(anIntegerLiteralExp.getType()).toString());
		template.setAttribute("value", anIntegerLiteralExp.getIntegerSymbol());

		ITransformedCode result = new TransformedCodeImpl();
		result.setResultExp(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIntegerLiteralExp(IntegerLiteralExp)"
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
	 * #caseInvalidLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp)
	 */
	public ITransformedCode caseInvalidLiteralExp(
			InvalidLiteralExp anInvalidLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseInvalidLiteralExp(InvalidLiteralExp) - start");
		}
		// no else.

		ITemplate template = this.templateGroup
				.getTemplate("invalidLiteralExp");

		ITransformedCode result = new TransformedCodeImpl();
		result.setResultExp(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseInvalidLiteralExp(InvalidLiteralExp)"
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
	 * #caseIterateExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IterateExp)
	 */
	public ITransformedCode caseIterateExp(IterateExp anIterateExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIterateExp(IterateExp) - start");
		}
		// no else.

		ITransformedType resultType = this
				.transformType(anIterateExp.getType());

		ITransformedCode sourceCode = this.doSwitch(anIterateExp.getSource());
		ITransformedType sourceType = this.transformType(anIterateExp
				.getSource().getType());

		OclExpression bodyExp = anIterateExp.getBody();

		/* TODO Find a solution without modifying the ASM. */
		/*
		 * Get the first iterator variable (probaly further exist during
		 * recursive calls).
		 */
		Variable iterateVariable = anIterateExp.getIterator().remove(0);

		/*
		 * If the Expression has more than one iterator variable. Create an
		 * inner IterateExp without the first iterator variable.
		 */
		if (anIterateExp.getIterator().size() > 0) {

			/* the first iterator has been already removed. */
			bodyExp = (OclExpression) anIterateExp;
			/*
			 * The Expression will be transformed inside the transformation of
			 * the first iterator Variable later on.
			 */
		}

		/* Begin with code transformation. */
		ITransformedCode bodyCode = this.doSwitch(bodyExp);

		/* TODO Find a solution without modifying the ASM (part 2). */
		/* Add the iterator Variable to the IterateExp again. */
		anIterateExp.getIterator().add(0, iterateVariable);

		ITemplate template = this.templateGroup.getTemplate("iterateExp");

		template.setAttribute("sourceCode", sourceCode.getCode());
		template.setAttribute("sourceExp", sourceCode.getResultExp());
		template.setAttribute("itVar", iterateVariable.getName());
		template.setAttribute("bodyCode", bodyCode.getCode());
		template.setAttribute("bodyExp", bodyCode.getResultExp());

		String resultVar = anIterateExp.getResult().getName();
		ITransformedCode resultVarInitCode = this.doSwitch(anIterateExp
				.getResult().getInitExpression());

		template.setAttribute("resultVarInitCode", resultVarInitCode.getCode());
		template.setAttribute("resultVarInitExp",
				resultVarInitCode.getResultExp());
		template.setAttribute("resultVar", resultVar);
		template.setAttribute("resultType", resultType.toString());
		template.setAttribute("sourceGenericType", sourceType.getGenericType()
				.toString());

		ITransformedCode result = new TransformedCodeImpl();
		result.addCode(template.toString());
		result.setResultExp(resultVar);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIterateExp(IterateExp)"
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
	 * #caseIteratorExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp)
	 */
	public ITransformedCode caseIteratorExp(IteratorExp anIteratorExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIteratorExp(IteratorExp) - start");
		}
		// no else.

		ITransformedCode result = new TransformedCodeImpl();
		ITransformedType resultType = this
				.transformInitializableType(anIteratorExp.getType());

		/* Get the name of the iterator */
		String itName = anIteratorExp.getName();

		/* Get unique name iterateResultName and iterateVarName. */
		String resultVarName = this.environment.getNewResultVarName();

		/* TODO Find a solution without modifying the ASM (Part 1). */
		/* Get the first iterator variable. */
		Variable itVariable = anIteratorExp.getIterator().remove(0);
		String itVarName = itVariable.getName();

		/* Transform code for source of the iteratorExp. */
		ITransformedCode sourceCode = this.doSwitch(anIteratorExp.getSource());
		ITransformedType sourceType = this.transformType(anIteratorExp
				.getSource().getType());

		OclExpression bodyExp = anIteratorExp.getBody();

		/*
		 * If the Expression has more than one iterator variable. Create an
		 * inner IterateExp without the first Iterator Variable.
		 */
		if (anIteratorExp.getIterator().size() > 0) {
			/* the first iterator has been already removed. */
			bodyExp = anIteratorExp;
			/*
			 * The Expression will be transformed inside the transformation of
			 * the first iterator Variable later on.
			 */
		}
		// no else.

		/* Transform code for bodyExp of the iteratorExp. */
		ITransformedCode bodyCode = this.doSwitch(bodyExp);
		ITransformedType bodyType = this.transformType(bodyExp.getType());

		/* Needed for iterators of type 'sortedBy'. */
		ITransformedCode bodyCode2 = null;
		String itVarName2 = null;

		/*
		 * For the iterator sorted by, create a second body expression with a
		 * different iterator variable.
		 */
		if (itName.equals("sortedBy")) {

			/*
			 * This is required because the code has to contain two different
			 * variables.
			 */
			itVarName2 = this.environment.getNewIteratorVarName();

			/* Rename the variable and preserve the old name. */
			String oldVarMapping = this.variableNames.get(itVariable.getName());
			this.variableNames.put(itVariable.getName(), itVarName2);

			bodyCode2 = this.doSwitch(bodyExp);

			/* Reset the mapping. */
			if (oldVarMapping != null)
				this.variableNames.put(itVariable.getName(), oldVarMapping);
			else
				this.variableNames.remove(itVariable.getName());
		}

		/* TODO Find a solution without modifying the ASM (Part 2). */
		/* Add the iterator Variable to the model again. */
		anIteratorExp.getIterator().add(0, itVariable);

		/* Begin code transformation of IteratorExp. */
		ITemplate template = this.templateGroup.getTemplate("iteratorExp_"
				+ itName);

		if (itName.equals("any") || itName.equals("exists")
				|| itName.equals("forAll") || itName.equals("one")) {

			/* Set iterator specific template attributes. */
			template.setAttribute("itType", sourceType.getGenericType()
					.toString());
		}

		else if (itName.equals("isUnique")) {

			/* Set iterator specific template attributes. */
			template.setAttribute("itType", sourceType.getGenericType()
					.toString());
			template.setAttribute("collectionVar",
					this.environment.getNewCollectionVarName());
		}

		else if (itName.equals("collect")) {

			String addOp;

			/*
			 * Collect does not add collections to collections, thus the add
			 * operation must be computed.
			 */
			if (bodyType.isGenericType()) {
				addOp = this.templateGroup.getTemplate("addAllOperationName")
						.toString();
			} else {
				addOp = this.templateGroup.getTemplate("addOperationName")
						.toString();
			}

			/* Set iterator specific template attributes. */
			template.setAttribute("itType", sourceType.getGenericType()
					.toString());
			template.setAttribute("resultType", resultType.toString());
			template.setAttribute("addOp", addOp);
		}

		else if (itName.equals("collectNested") || itName.equals("reject")
				|| itName.equals("select")) {

			/* Set iterator specific template attributes. */
			template.setAttribute("itType", sourceType.getGenericType()
					.toString());
			template.setAttribute("resultType", resultType.toString());
		}

		else if (itName.equals("sortedBy")) {

			/* Set iterator specific template attributes. */
			template.setAttribute("itType", sourceType.getGenericType()
					.toString());
			template.setAttribute("comparatorName",
					this.environment.getNewComparatorName());
			template.setAttribute("compareResult",
					this.environment.getNewResultVarName());
			template.setAttribute("resultType", resultType.toString());

			template.setAttribute("itVar2", itVarName2);
			template.setAttribute("bodyCode2", bodyCode2.getCode());
			template.setAttribute("bodyExp2", bodyCode2.getResultExp());
		}

		/* Set template attributes which are needed for all iterators. */
		template.setAttribute("sourceCode", sourceCode.getCode());
		template.setAttribute("sourceExp", sourceCode.getResultExp());
		template.setAttribute("itVar", itVarName);
		template.setAttribute("bodyCode", bodyCode.getCode());
		template.setAttribute("bodyExp", bodyCode.getResultExp());
		template.setAttribute("resultVar", resultVarName);

		result.addCode(template.toString());
		result.setResultExp(resultVarName);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIteratorExp(IteratorExp)"
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
	 * #caseLetExp(tudresden.ocl20.pivot.essentialocl.expressions.LetExp)
	 */
	public ITransformedCode caseLetExp(LetExp aLetExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseLetExp(LetExp) - start");
		}
		// no else.

		Variable aVar = aLetExp.getVariable();
		Type aVarsType = aVar.getType();

		OclExpression initExp = aVar.getInitExpression();
		OclExpression inExp = aLetExp.getIn();

		/* Generate the code for the inExp. */
		ITransformedCode inCode = doSwitch((EObject) inExp);

		ITemplate template = this.templateGroup.getTemplate("letExp");
		template.setAttribute("varType", this.transformType(aVarsType)
				.getTypeName());

		String varName = aVar.getName();
		if (this.variableNames.containsKey(varName))
			varName = this.variableNames.get(varName);
		// no else.

		template.setAttribute("varName", varName);
		template.setAttribute("inCode", inCode.getCode());

		/* Generate the code for the initExp. */
		if (initExp != null) {
			ITransformedCode initCode = doSwitch((EObject) initExp);

			template.setAttribute("initCode", initCode.getCode());
			template.setAttribute("initExp", initCode.getResultExp());
		}
		// no else.

		ITransformedCode result = new TransformedCodeImpl();
		result.addCode(template.toString());
		result.setResultExp(inCode.getResultExp());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseLetExp(LetExp) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Generates the code for a binary {@link Operation} of an
	 * {@link OperationCallExp}.
	 * </p>
	 * 
	 * @param anOperationCallExp
	 *            The {@link OperationCallExp} the code shall be transformed
	 *            for.
	 * @param anOperation
	 *            The {@link Operation} which shall be transformed.
	 * @return The {@link ITransformedCode} for the {@link Operation} which
	 *         shall be transformed.
	 */
	public ITransformedCode caseOperationCallExp(
			OperationCallExp anOperationCallExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseOperationCallExp(OperationCallExp) - start");
		}
		// no else.

		ITransformedCode result = new TransformedCodeImpl();

		/* Transform Code for the source of the operation call. */
		OclExpression sourceExp = anOperationCallExp.getSource();
		Operation referredOperation = anOperationCallExp.getReferredOperation();

		List<Type> parameterTypes = new ArrayList<Type>();

		/* Operation can be null (@pre Operation). */
		if (referredOperation != null) {
			for (Parameter parameter : referredOperation.getInputParameter()) {
				parameterTypes.add(parameter.getType());
			}
			// end for.
		}
		// no else.

		OclLibrary oclLibrary = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary();

		/* Compute the source expression. */
		Type sourceType = sourceExp.getType();

		ITransformedCode sourceCode;

		/*
		 * Especially handle static operations since type literals are hard to
		 * handle in Java.
		 */
		if (referredOperation != null && referredOperation.isStatic()
				&& sourceExp instanceof TypeLiteralExp) {
			sourceCode = new TransformedCodeImpl();
			sourceCode.setResultExp(this.transformType(
					((TypeLiteralExp) sourceExp).getReferredType())
					.getTypeName());
		}

		else
			sourceCode = this.doSwitch(sourceExp);
		// end else.

		result.addCode(sourceCode.getCode());

		String operationName;

		/* Get the operation name and handle the special case @pre. */
		if (anOperationCallExp.getName().equals("atPre")) {
			operationName = "atPre";
		}

		else {
			operationName = anOperationCallExp.getReferredOperation().getName();
		}

		String resultExp = null;
		ITemplate template = null;

		/*
		 * Search for the template of the operation. Start with concrete types
		 * and continue with more and more abstract types.
		 */
		if (sourceType != null) {

			/* Special treatement of atPre operation. */
			if (operationName.equals("atPre")) {
				/* The variable is initialized later during instrumentation. */
				String atPreVar = this.environment.addAtPreValue(sourceCode,
						sourceType);
				resultExp = atPreVar;
			}
			// no else.

			/* Operations on BagType. */
			if (template == null && sourceType instanceof BagType) {

				/*
				 * Do not use the source type here. Otherwise the operation
				 * check will fail.
				 */
				BagType bagType = oclLibrary.getBagType(oclLibrary.getOclAny());

				/*
				 * Check if the given operation is defined and probably load its
				 * template.
				 */
				Operation operation = bagType.lookupOperation(operationName,
						parameterTypes);

				if (operation != null
						&& operation.getOwningType().equals(bagType)) {

					/* Probably rename the operation. */
					if (renamedOperationNames.containsKey(operationName)) {
						operationName = renamedOperationNames
								.get(operationName);
					}
					// no else.

					template = this.templateGroup.getTemplate(operationName
							+ "OperationOnBag");
				}
				// no else.
			}
			// no else.

			/* Operations on OrderedSetType. */
			if (template == null && sourceType instanceof OrderedSetType) {

				/*
				 * Do not use the source type here. Otherwise the operation
				 * check will fail.
				 */
				OrderedSetType orderedSetType = oclLibrary
						.getOrderedSetType(oclLibrary.getOclAny());

				/*
				 * Check if the given operation is defined and probably load its
				 * template.
				 */
				Operation operation = orderedSetType.lookupOperation(
						operationName, parameterTypes);

				if (operation != null
						&& operation.getOwningType().equals(orderedSetType)) {

					/* Probably rename the operation. */
					if (renamedOperationNames.containsKey(operationName)) {
						operationName = renamedOperationNames
								.get(operationName);
					}
					// no else.

					template = this.templateGroup.getTemplate(operationName
							+ "OperationOnOrderedSet");
				}
				// no else.
			}
			// no else.

			/* Operations on SequenceType. */
			if (template == null && sourceType instanceof SequenceType) {

				/*
				 * Do not use the source type here. Otherwise the operation
				 * check will fail.
				 */
				SequenceType sequenceType = oclLibrary
						.getSequenceType(oclLibrary.getOclAny());

				/*
				 * Check if the given operation is defined and probably load its
				 * template.
				 */
				Operation operation = sequenceType.lookupOperation(
						operationName, parameterTypes);

				if (operation != null
						&& operation.getOwningType().equals(sequenceType)) {

					/* Probably rename the operation. */
					if (renamedOperationNames.containsKey(operationName)) {
						operationName = renamedOperationNames
								.get(operationName);
					}
					// no else.

					template = this.templateGroup.getTemplate(operationName
							+ "OperationOnSequence");
				}
				// no else.
			}
			// no else.

			/* Operations on SetType. */
			if (template == null && sourceType instanceof SetType) {

				/* Especially handle the union operation. */
				if (operationName.equals("union")) {

					if (parameterTypes.size() == 1
							&& parameterTypes.get(0) instanceof BagType) {
						template = this.templateGroup
								.getTemplate("unionOperationWithBagOnSet");
					}

					else if (parameterTypes.size() == 1
							&& parameterTypes.get(0) instanceof SetType) {
						template = this.templateGroup
								.getTemplate("unionOperationWithSetOnSet");
					}
				}
				// no else.

				if (template == null) {
					/*
					 * Do not use the source type here. Otherwise the operation
					 * check will fail.
					 */
					SetType setType = oclLibrary.getSetType(oclLibrary
							.getOclAny());

					/*
					 * Check if the given operation is defined and probably load
					 * its template.
					 */
					Operation operation;
					operation = setType.lookupOperation(operationName,
							parameterTypes);

					if (operation != null
							&& operation.getOwningType().equals(setType)) {

						/* Probably rename the operation. */
						if (renamedOperationNames.containsKey(operationName)) {
							operationName = renamedOperationNames
									.get(operationName);
						}
						// no else.

						template = this.templateGroup.getTemplate(operationName
								+ "OperationOnSet");
					}
					// no else.
				}
				// no else.
			}
			// no else.

			/* Operations on CollectionType. */
			if (template == null && sourceType instanceof CollectionType) {

				/*
				 * Do not use the source type here. Otherwise the operation
				 * check will fail.
				 */
				CollectionType collectionType = oclLibrary
						.getCollectionType(oclLibrary.getOclAny());

				/*
				 * Check if the given operation is defined and probably load its
				 * template.
				 */
				Operation operation = collectionType.lookupOperation(
						operationName, parameterTypes);

				if (operation != null
						&& operation.getOwningType().equals(collectionType)) {

					/* Probably rename the operation. */
					if (renamedOperationNames.containsKey(operationName)) {
						operationName = renamedOperationNames
								.get(operationName);
					}
					// no else.

					template = this.templateGroup.getTemplate(operationName
							+ "OperationOnCollection");
				}
				// no else.

				/*
				 * Sum operation requires special argument because result must
				 * be cast to result type.
				 */
				if (template != null && operationName.equals("sum")) {
					String resultType;
					resultType = this.transformType(
							anOperationCallExp.getType()).toString();

					template.setAttribute("resultType", resultType);

					/*
					 * Set additional parameter to convert from number to result
					 * type.
					 */
					template.setAttribute("typeConversion", this.templateGroup
							.getTemplate("numberConversionTo" + resultType)
							.toString());
				}
				// no else.
			}
			// no else (OclCollection).

			/*
			 * Flatten operation requires special argument because result must
			 * be cast to result type.
			 */
			if (template != null && operationName.equals("flatten")) {
				template.setAttribute("resultType",
						this.transformType(anOperationCallExp.getType())
								.toString());
			}
			// no else.

			/* Operations on OclBoolean. */
			if (template == null
					&& referredOperation != null
					&& referredOperation.getOwner() != null
					&& referredOperation.getOwner().equals(
							oclLibrary.getOclBoolean())) {

				/* Probably rename the operation. */
				if (renamedOperationNames.containsKey(operationName)) {
					operationName = renamedOperationNames.get(operationName);
				}
				// no else.

				template = this.templateGroup.getTemplate(operationName
						+ "OperationOnBoolean");
			}
			// no else (OclBoolean).

			/* Operations on OclInteger. */
			if (template == null
					&& referredOperation != null
					&& referredOperation.getOwner() != null
					&& referredOperation.getOwner().equals(
							oclLibrary.getOclInteger())) {

				/* Probably rename the operation. */
				if (renamedOperationNames.containsKey(operationName)) {
					operationName = renamedOperationNames.get(operationName);
				}
				// no else.

				/* Especially handle the minus operations. */
				if (operationName.equals("minus")) {
					/* Decide between binary minus and ... */
					if (anOperationCallExp.getArgument().size() > 0) {
						template = this.templateGroup
								.getTemplate("minusOperationOnInteger");
					}
					/* ... unary negative. */
					else {
						template = this.templateGroup
								.getTemplate("negativeOperationOnInteger");
					}
				}

				else {
					template = this.templateGroup.getTemplate(operationName
							+ "OperationOnInteger");
				}
			}
			// no else (OclInteger).

			/* Operations on OclReal. */
			if (template == null
					&& referredOperation != null
					&& referredOperation.getOwner() != null
					&& referredOperation.getOwner().equals(
							oclLibrary.getOclReal())) {

				/* Probably rename the operation. */
				if (renamedOperationNames.containsKey(operationName)) {
					operationName = renamedOperationNames.get(operationName);
				}
				// no else.

				/* Especially handle the minus operations. */
				if (operationName.equals("minus")) {
					/* Decide between binary minus and ... */
					if (anOperationCallExp.getArgument().size() > 0) {
						template = this.templateGroup
								.getTemplate("minusOperationOnReal");
					}
					/* ... unary negative. */
					else {
						template = this.templateGroup
								.getTemplate("negativeOperationOnReal");
					}
				}

				else {
					template = this.templateGroup.getTemplate(operationName
							+ "OperationOnReal");
				}
			}
			// no else (OclReal).

			/*
			 * Especially handle equality operations on Boolean, Real or
			 * Integer.
			 */
			if (template == null
					&& sourceType instanceof PrimitiveType
					&& (((PrimitiveType) sourceType).getKind() == PrimitiveTypeKind.BOOLEAN || (((PrimitiveType) sourceType)
							.getKind() == PrimitiveTypeKind.INTEGER || (((PrimitiveType) sourceType)
							.getKind() == PrimitiveTypeKind.REAL)))) {

				/* Probably rename the operation. */
				if (renamedOperationNames.containsKey(operationName)) {
					operationName = renamedOperationNames.get(operationName);
				}
				// no else.

				if (operationName.equals("equals")) {
					template = this.templateGroup
							.getTemplate("equalsOperationOnPrimitiveType");
				}

				else if (operationName.equals("notEquals")) {
					template = this.templateGroup
							.getTemplate("notEqualsOperationOnPrimitiveType");
				}
				// no else.
			}
			// no else (special equality for primitive types).

			/* Operations on OclString. */
			/* TODO AST of characters seems to be wrong. */
			if (template == null
					&& (referredOperation != null
							&& referredOperation.getOwner() != null && referredOperation
							.getOwner().equals(oclLibrary.getOclString()))
					|| operationName.equals("characters")) {

				/* Probably rename the operation. */
				if (renamedOperationNames.containsKey(operationName)) {
					operationName = renamedOperationNames.get(operationName);
				}
				// no else.

				template = this.templateGroup.getTemplate(operationName
						+ "OperationOnString");

				/* Characters operation requires special attributes because. */
				if (template != null && operationName.equals("characters")) {
					String resultVar = this.environment.getNewResultVarName();

					template.setAttribute("sourceExp",
							sourceCode.getResultExp());
					template.setAttribute("resultVar", resultVar);
					template.setAttribute("elementName",
							this.environment.getNewIteratorVarName());

					result.addCode(template.toString());
					resultExp = resultVar;
				}
				// no else.
			}
			// no else (OclString).

			/* Operations on OclAny. */
			/* TODO AST of oclAsType seems to be wrong. */
			/* TODO AST of oclType seems to be wrong. */
			if (template == null
					&& (referredOperation != null
							&& referredOperation.getOwner() != null && referredOperation
							.getOwner().equals(oclLibrary.getOclAny()))
					|| operationName.equals("oclAsType")
					|| operationName.equals("oclType")) {

				/* Probably rename the operation. */
				if (renamedOperationNames.containsKey(operationName)) {
					operationName = renamedOperationNames.get(operationName);
				}
				// no else.

				template = this.templateGroup.getTemplate(operationName
						+ "OperationOnOclAny");

				/*
				 * oclIsNew Operation calls must be registered at the
				 * evironment.
				 */
				if (template != null && operationName.equals("oclIsNew")) {
					this.environment.addIsNewClass(this.transformType(
							sourceExp.getType()).toString());
				}

				/* oclIsInvalid Operation requires special attributes. */
				else if (template != null
						&& operationName.equals("oclIsInvalid")) {
					String resultVar = this.environment.getNewResultVarName();

					template.setAttribute("sourceExp",
							sourceCode.getResultExp());
					template.setAttribute("resultVar", resultVar);

					if (sourceExp.getType() != null) {
						template.setAttribute("sourceHasType", "true");
					}
					// no else.

					result.addCode(template.toString());
					resultExp = resultVar;
				}
				// no else.
			}
			// no else (OclAny).

			/* Operations on OclType. */
			/* TODO AST of allInstances seems to be wrong. */
			if (template == null
					&& (referredOperation != null
							&& referredOperation.getOwner() != null && referredOperation
							.getOwner().equals(oclLibrary.getOclAny()))
					|| operationName.equals("allInstances")) {

				/* Probably rename the operation. */
				if (renamedOperationNames.containsKey(operationName)) {
					operationName = renamedOperationNames.get(operationName);
				}
				// no else.

				template = this.templateGroup.getTemplate(operationName
						+ "OperationOnOclType");

				/*
				 * allInstances Operation calls must be registered at the
				 * evironment.
				 */
				if (template != null && operationName.equals("allInstances")) {

					/* Esspecially handle type literal exps. */
					if (sourceExp instanceof TypeLiteralExp) {
						sourceCode = new TransformedCodeImpl();
						sourceCode.setResultExp(this
								.transformType(
										(((TypeLiteralExp) sourceExp)
												.getReferredType()))
								.getTypeName());
					}
					// no else.

					template.setAttribute("typeName", sourceCode.getResultExp());
					this.environment.addAllInstancesClass(sourceCode
							.getResultExp());
				}
				// no else.
			}
			// no else (OclType).
		}
		// no else.

		/* Code for operations on non Ocl Types. */
		if (template == null) {
			template = this.templateGroup.getTemplate("umlOperation");
			template.setAttribute("operationName", operationName);
		}
		// no else.

		/* Probably set more attributes of the template. */
		if (template != null && resultExp == null) {

			template.setAttribute("sourceExp", sourceCode.getResultExp());

			/* Probably set code for arguments of the operation. */
			for (OclExpression anArgument : anOperationCallExp.getArgument()) {

				/* Especially handle type literals for some operations. */
				if (operationName.equals("oclAsType")
						&& anArgument instanceof TypeLiteralExp) {
					template.setAttribute(
							"argsExp",
							this.transformType(
									((TypeLiteralExp) anArgument)
											.getReferredType()).getTypeName());
				}

				else {
					ITransformedCode argCode = this
							.doSwitch((EObject) anArgument);
					result.addCode(argCode.getCode());
					template.setAttribute("argsExp", argCode.getResultExp());
				}
			}
			// end for.

			resultExp = template.toString();
		}
		// no else.

		result.setResultExp(resultExp);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseOperationCallExp(OperationCallExp)"
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
	 * #casePropertyCallExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp)
	 */
	public ITransformedCode casePropertyCallExp(PropertyCallExp aPropertyCallExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("casePropertyCallExp(PropertyCallExp) - start");
		}
		// no else.

		ITransformedCode result = new TransformedCodeImpl();

		/* Get parameters for property call code. */
		Property referredProperty = aPropertyCallExp.getReferredProperty();

		/* Transform the code for the sourceExp. */
		ITransformedCode sourceCode;

		/*
		 * Esspecially handle static properties since type literals are hard to
		 * handle in Java.
		 */
		if (referredProperty.isStatic()
				&& aPropertyCallExp.getSource() instanceof TypeLiteralExp) {
			sourceCode = new TransformedCodeImpl();
			sourceCode.setResultExp(this.transformType(
					((TypeLiteralExp) aPropertyCallExp.getSource())
							.getReferredType()).getTypeName());
		}

		else
			sourceCode = doSwitch(aPropertyCallExp.getSource());
		// end else.

		/* Add source code to result. */
		result.addCode(sourceCode.getCode());

		ITemplate template;

		/* The property calls on tuples must be adapted. */
		if (aPropertyCallExp.getSource() != null
				&& aPropertyCallExp.getSource().getType() instanceof TupleType) {
			template = this.templateGroup.getTemplate("propertyCallExpOnTuple");
		}

		else {
			template = this.templateGroup.getTemplate("propertyCallExp");
		}

		/* Set template attributes. */
		template.setAttribute("sourceExp", sourceCode.getResultExp());
		template.setAttribute("propertyName", referredProperty.getName());

		result.setResultExp(template.toString());

		/*
		 * Store the called properties in the environment (Probably needed for
		 * invariant instrumentation). Only store properties, which are
		 * properties of the constrained element. Thus their source must be a
		 * self variable.
		 */
		if (aPropertyCallExp.getSource() instanceof VariableExp) {

			VariableExp aVariableExp = (VariableExp) aPropertyCallExp
					.getSource();
			Variable aVariable = aVariableExp.getReferredVariable();

			if (aVariable.getName().equals("self")) {
				this.environment.addCalledProperty(referredProperty.getName());
			}
			// no else.
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("casePropertyCallExp(PropertyCallExp)"
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
	 * #caseRealLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp)
	 */
	public ITransformedCode caseRealLiteralExp(RealLiteralExp aRealLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseRealLiteralExp(RealLiteralExp) - start");
		}
		// no else.

		ITemplate template = this.templateGroup.getTemplate("literalExp");
		template.setAttribute("type",
				this.transformType(aRealLiteralExp.getType()).toString());
		template.setAttribute("value", aRealLiteralExp.getRealSymbol());

		ITransformedCode result = new TransformedCodeImpl();
		result.setResultExp(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseRealLiteralExp(RealLiteralExp)"
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
	 * #caseStringLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp)
	 */
	public ITransformedCode caseStringLiteralExp(
			StringLiteralExp aStringLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseStringLiteralExp(StringLiteralExp) - start");
		}
		// no else.

		ITemplate template = this.templateGroup.getTemplate("stringLiteralExp");
		template.setAttribute("value", aStringLiteralExp.getStringSymbol());

		ITransformedCode result = new TransformedCodeImpl();
		result.setResultExp(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseStringLiteralExp(StringLiteralExp)"
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
	 * #caseTupleLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp)
	 */
	public ITransformedCode caseTupleLiteralExp(TupleLiteralExp aTupleLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseTupleLiteralExp(TupleLiteralExp) - start");
		}
		// no else.

		ITransformedCode result = new TransformedCodeImpl();
		String tupleName = aTupleLiteralExp.getName();

		/* If the tuple has no name, compute a new name. */
		if (tupleName.equals("")) {
			tupleName = this.environment.getNewTupleVarName();
		}
		// no else.

		/* Get template for tupleLiteralExp. */
		ITemplate template = this.templateGroup.getTemplate("tupleLiteralExp");
		template.setAttribute("tupleName", tupleName);

		/* Transform Code for all initial elements. */
		for (TupleLiteralPart aTupleElement : aTupleLiteralExp.getPart()) {

			ITransformedCode elementCode = this
					.doSwitch((EObject) aTupleElement.getValue());

			template.setAttribute("argCodes", elementCode.getCode());
			template.setAttribute("argExps", elementCode.getResultExp());
			template.setAttribute("argNames", aTupleElement.getProperty()
					.getName());
		}

		/* Set the result. */
		result.addCode(template.toString());
		result.setResultExp(tupleName);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseTupleLiteralExp(TupleLiteralExp)"
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
	 * #caseTypeLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp)
	 */
	public ITransformedCode caseTypeLiteralExp(TypeLiteralExp aTypeLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseTypeLiteralExp(TypeLiteralExp) - start");
		}
		// no else.

		ITemplate template = this.templateGroup.getTemplate("typeLiteralExp");
		template.setAttribute("type",
				this.transformType(aTypeLiteralExp.getReferredType())
						.toString());

		ITransformedCode result = new TransformedCodeImpl();
		result.setResultExp(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseTypeLiteralExp(TypeLiteralExp)"
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
	 * #caseUndefinedLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp)
	 */
	public ITransformedCode caseUndefinedLiteralExp(
			UndefinedLiteralExp anUndefinedLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseUndefinedLiteralExp(UndefinedLiteralExp) - start");
		}
		// no else.

		ITemplate template = this.templateGroup
				.getTemplate("undefinedLiteralExp");
		ITransformedCode result = new TransformedCodeImpl();
		result.setResultExp(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseUndefinedLiteralExp(UndefinedLiteralExp)"
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
	 * #caseUnlimitedNaturalExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.UnlimitedNaturalExp)
	 */
	public ITransformedCode caseUnlimitedNaturalExp(
			UnlimitedNaturalExp anUnlimitedNaturalExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseUnlimitedNaturalExp(UnlimitedNaturalExp) - start");
		}
		// no else.

		ITransformedCode result;

		ITemplate template = this.templateGroup.getTemplate("literalExp");
		template.setAttribute("type",
				this.transformType(anUnlimitedNaturalExp.getType()).toString());
		template.setAttribute("value", anUnlimitedNaturalExp.getSymbol());

		result = new TransformedCodeImpl();
		result.setResultExp(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseUnlimitedNaturalExp(UnlimitedNaturalExp)"
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
	 * #caseVariableExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.VariableExp)
	 */
	public ITransformedCode caseVariableExp(VariableExp aVariableExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseVariableExp(VariableExp) - start");
		}
		// no else.

		Variable aVariable = aVariableExp.getReferredVariable();
		ITransformedCode result = new TransformedCodeImpl();

		ITemplate template;

		/*
		 * The keyword 'self' has to be transformed into the name of the
		 * variable which references the constrained class
		 */
		if (aVariable.getName().equals("self")) {

			template = this.templateGroup.getTemplate("selfVariable");
			result.setResultExp(template.toString());
			this.environment.setIsUsigSelfVariable(true);
		}

		/*
		 * The special OCL variable 'result' has to be transformed in their name
		 * in Java.
		 */
		else if (aVariable.getName().equals("result")) {

			template = this.templateGroup.getTemplate("resultVariable");
			result.setResultExp(template.toString());
		}

		/* All other VariableExp lead to the name of the Variable. */
		else {
			String varName = aVariable.getName();

			/* Probably map the variable to another name. */
			if (this.variableNames.containsKey(varName))
				varName = this.variableNames.get(varName);
			// no else.

			result.setResultExp(varName);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseVariableExp(VariableExp)"
					+ " - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Returns The canonical Name of a {@link NamedElement} in the
	 * {@link IModel}.
	 * </p>
	 * 
	 * @returns The canonical Name of a {@link NamedElement}.
	 */
	private String getCanonicalName(NamedElement anElement) {

		String result = "";

		if (anElement.getOwner() != null) {
			result += this.getPackagePath(anElement) + ".";
		}
		// no else.

		result += anElement.getName();

		return result;
	}

	/**
	 * <p>
	 * Returns the constraint package to a given packagePath. They are the same
	 * if no constraint directory has been set. Else the constraint directory is
	 * a sub package.
	 * </p>
	 * 
	 * @param packagePath
	 * @return The constraint package to a given packagePath.
	 */
	private String getConstraintPackage(String packagePath) {

		String result = packagePath;
		String constDirectory = this.settings.getConstraintDirectory();

		if (constDirectory.length() > 0) {
			result += "." + constDirectory.replaceAll("/", ".");
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Returns the path of the package in the {@link IModel} where the given
	 * {@link NamedElement} is located.
	 * </p>
	 * 
	 * @returns The path of the package.
	 */
	private String getPackagePath(NamedElement anElement) {

		String result;

		NamedElement anOwner = anElement.getOwner();
		result = anOwner.getName();

		while (anOwner != null) {

			anOwner = anOwner.getOwner();

			if (anOwner != null && !anOwner.getName().equals("")
					&& !anOwner.getName().equals("root")
					&& !anOwner.getName().equals("generatedTopLevelModel")) {
				result = anOwner.getName() + "." + result;
			}
			// no else
		}

		if (this.settings.getBasisPackage().length() > 0)
			result = this.settings.getBasisPackage() + "." + result;
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Initializes the code generator.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *             Thrown, if a String template for code transformation can not
	 *             be found.
	 */
	private void init() throws Ocl2CodeException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("init() - start");
		}
		// no else.

		this.environment = new Ocl2JavaEnvironment();

		/* Try to load the template engine. */
		try {
			LinkedList<URL> templatePaths = new LinkedList<URL>();

			templatePaths.add(this.getClass().getResource(
					TEMPLATE_PATH + JAVA_TEMPLATE_FILE));
			templatePaths.add(this.getClass().getResource(
					TEMPLATE_PATH + TYPE_TEMPLATE_FILE));
			templatePaths.add(this.getClass().getResource(
					TEMPLATE_PATH + OPERATION_TEMPLATE_FILE));
			templatePaths.add(this.getClass().getResource(
					TEMPLATE_PATH + EXPRESSION_TEMPLATE_FILE));
			templatePaths.add(this.getClass().getResource(
					TEMPLATE_PATH + INSTRUMENTATION_TEMPLATE_FILE));

			TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup(
					"Ocl2Java");
			this.templateGroup = TemplatePlugin.getTemplateGroupRegistry()
					.addDefaultTemplateGroup("Ocl2Java", null);

			this.templateGroup.addFiles(templatePaths);
			this.settings = new Ocl2JavaSettings();
		}

		catch (TemplateException e) {
			String msg = "The template for code transformation could not be loaded. ";
			msg += e.getMessage();

			if (LOGGER.isDebugEnabled()) {
				LOGGER.error("init() - failed", e);
			}
			// no else.

			throw new Ocl2CodeException(msg);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("init() - end");
		}
		// no else.
	}

	/**
	 * <p>
	 * Generates an Aspect for the instrumentation of body constraints.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForBody(Constraint constraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForBody(Constraint) - start");
		}
		// no else.

		String result;

		ITemplate aspectTemplate = this.templateGroup.getTemplate("aspectFile");

		/* Get the constrained operation. */
		Operation constrainedOperation = ((Operation) constraint
				.getConstrainedElement().get(0));

		/* Get the path of the package where the constraint class is located. */
		String contextPackage = this.getPackagePath(constraint);
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));

		/* Compute the aspect's file name. */
		String className = constrainedOperation.getOwner().getName();
		String aspectName = className + "_"
				+ Ocl2JavaEnvironment.BODY_ASPECT_NAME + "_"
				+ constrainedOperation.getName();

		if (this.environment.isUsedAspectName(aspectName))
			aspectName = className + "_"
					+ this.environment.getNewBodyAspectName();
		// no else.

		this.environment.addUsedAspectName(aspectName);
		aspectTemplate.setAttribute("name", aspectName);

		/* Generate the code which shall be inside the advice(s). */
		ITransformedCode constrainedCode = this
				.transformFragmentCode(constraint);

		/* Probably collect allInstances for some classes. */
		if (this.environment.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses = this.environment
					.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		NamedElement constrainedClass = constrainedOperation.getOwner();

		String operationName = constrainedOperation.getName();
		String operationResultType = this.transformType(
				constrainedOperation.getType()).toString();

		/* Create Template for the advice code. */
		ITemplate adviceTemplate = this.templateGroup
				.getTemplate("bodyInstrumentation");

		/* Set template parameters. */
		adviceTemplate.setAttribute("class", constrainedClass.getName());
		adviceTemplate.setAttribute("package", contextPackage);
		adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
		adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
		adviceTemplate.setAttribute("oclBody", constraint.getSpecification()
				.getBody().trim());
		adviceTemplate.setAttribute("method", operationName);

		/* Probably set the returnType. */
		if (!operationResultType.equals(this.templateGroup.getTemplate(
				"voidType").toString())) {
			adviceTemplate.setAttribute("returnType", operationResultType);
		}
		// no else.

		/* Probably set the arguments of the operation. */
		this.setArgumentsForInstrumentationTemplate(constraint, adviceTemplate);

		/* Probably set that the constraint operation is static. */
		if (constrainedOperation.isStatic()) {
			adviceTemplate.setAttribute("opIsStatic", "true");
		}
		// no else.

		/* Add the advice code to the aspect template. */
		aspectTemplate.setAttribute("advice", adviceTemplate.toString());

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.settings.isSaveCode()) {
			this.saveTransformedCode(result, aspectName + ".aj",
					this.getConstraintPackage(contextPackage));
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForBody(Constraint)"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Generates an Aspect for the instrumentation of definition constraints.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForDef(Constraint constraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForDef(Constraint) - start");
		}
		// no else.

		String result;

		ITemplate aspectTemplate = this.templateGroup.getTemplate("aspectFile");

		/* Get the constrained element. */
		Feature definedFeature = constraint.getDefinedFeature();

		/* Get the path of the package where the constraint class is located. */
		String contextPackage = this.getPackagePath(constraint);
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));

		/* Compute the aspect's file name. */
		String className = definedFeature.getOwner().getName();
		String aspectName = className + "_"
				+ Ocl2JavaEnvironment.DEF_ASPECT_NAME + "_"
				+ definedFeature.getName();

		if (this.environment.isUsedAspectName(aspectName))
			aspectName = className + "_"
					+ this.environment.getNewDefAspectName();
		// no else.

		this.environment.addUsedAspectName(aspectName);
		aspectTemplate.setAttribute("name", aspectName);

		/* Generate the code which shall be inside the advice(s). */
		ITransformedCode constrainedCode = this
				.transformFragmentCode(constraint);

		/* Probably collect allInstances for some classes. */
		if (this.environment.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses;

			allInstancesClasses = this.environment.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		ITemplate adviceTemplate;
		Type type = (Type) constraint.getConstrainedElement().get(0);

		/* Differentiate between defined properties and operations. */
		if (definedFeature instanceof Property) {

			/* Get the defined property. */
			Property definedProperty = (Property) definedFeature;

			String propertyName = definedProperty.getName();
			String propertyType = this.transformType(definedProperty.getType())
					.toString();

			/* Create the template for the advice code. */
			adviceTemplate = this.templateGroup
					.getTemplate("defAttributeInstrumentation");

			/* Set attribute specific template attributes. */
			adviceTemplate.setAttribute("attribute", propertyName);
			adviceTemplate.setAttribute("attributeType", propertyType);

			if (definedProperty.isStatic())
				adviceTemplate.setAttribute("isStatic", "true");
			// no else.

			/* Probably also create a new getter method for the new attribute. */
			if (this.settings.isGettersForDefinedAttributesEnabled()) {
				String getterName = "get"
						+ propertyName.substring(0, 1).toUpperCase()
						+ propertyName.substring(1, propertyName.length());
				adviceTemplate.setAttribute("getterName", getterName);
			}
			// no else.
		}

		else if (definedFeature instanceof Operation) {

			/* Get the defined operation. */
			Operation definedOperation = (Operation) definedFeature;

			String operationName = definedOperation.getName();
			String operationReturnType = this.transformType(
					definedOperation.getType()).toString();

			/* Create a template for the advice code. */
			adviceTemplate = this.templateGroup
					.getTemplate("defMethodInstrumentation");

			/* Set operation specific template parameters. */
			adviceTemplate.setAttribute("method", operationName);

			/* Probably set the returnType. */
			if (!operationReturnType.equals(this.templateGroup.getTemplate(
					"voidType").toString())) {
				adviceTemplate.setAttribute("returnType", operationReturnType);
			}
			// no else.

			/* Set all arguments of the Operation. */
			for (Parameter anArgument : definedOperation.getInputParameter()) {
				String anArgumentsName = anArgument.getName();
				String anArgumentsType = this.transformType(
						anArgument.getType()).toString();

				adviceTemplate.setAttribute("args", anArgumentsName);
				adviceTemplate.setAttribute("argTypes", anArgumentsType);
			}

			if (definedOperation.isStatic())
				adviceTemplate.setAttribute("isStatic", true);

			if (this.environment.isUsingSelfVariable())
				adviceTemplate.setAttribute("usesSelfVariable", "true");
		}

		/* No other things can be defined by definition constraints. */
		else {
			String msg;

			msg = "An def value defined for an invalid Type of Feature.";
			msg += "Expected Type was Operation or Property but was ";
			msg += definedFeature.getClass().getName();

			throw new Ocl2CodeException(msg);
		}

		/* Set advice template parameters. */
		adviceTemplate.setAttribute("class", type.getName());
		adviceTemplate.setAttribute("package", contextPackage);
		adviceTemplate.setAttribute("constFolder",
				this.settings.getConstraintDirectory());
		adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
		adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
		adviceTemplate.setAttribute("oclBody", constraint.getSpecification()
				.getBody().trim());

		/* Add the advice code to the aspect template. */
		aspectTemplate.setAttribute("advice", adviceTemplate.toString());

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.settings.isSaveCode()) {
			this.saveTransformedCode(result, aspectName + ".aj",
					this.getConstraintPackage(contextPackage));
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForDef(Constraint)"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Generates an Aspect for the instrumentation of derive constraints.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForDerive(Constraint constraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForDerive(Constraint) - start");
		}
		// no else.

		String result;

		/* Get the constrained property. */
		Property constrainedProperty = ((Property) constraint
				.getConstrainedElement().get(0));

		ITemplate aspectTemplate = this.templateGroup.getTemplate("aspectFile");

		/* Get the path of the package where the constraint class is located. */
		String contextPackage = this.getPackagePath(constraint);
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));

		/* Compute the aspect's file name. */
		String className = constrainedProperty.getOwner().getName();
		String aspectName = className + "_"
				+ Ocl2JavaEnvironment.DERIVE_ASPECT_NAME + "_"
				+ constrainedProperty.getName();

		if (this.environment.isUsedAspectName(aspectName))
			aspectName = className + "_"
					+ this.environment.getNewDeriveAspectName();
		// no else.

		this.environment.addUsedAspectName(aspectName);
		aspectTemplate.setAttribute("name", aspectName);

		/* Generate the code which shall be inside the advice(s). */
		ITransformedCode constrainedCode = this
				.transformFragmentCode(constraint);

		/* Probably collect allInstances for some classes. */
		if (this.environment.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses = this.environment
					.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		NamedElement constrainedClass;

		/*
		 * Compute the constrained class. Especially handle static defined
		 * properties.
		 */
		if (constrainedProperty.isStatic()) {
			constrainedClass = ((ExpressionInOcl) constraint.getSpecification())
					.getContext().getType();
		}

		else {
			constrainedClass = constrainedProperty.getOwner();
		}

		String propertyName = constrainedProperty.getName();
		String propertyType = this.transformType(constrainedProperty.getType())
				.toString();

		/* Create Template for the advice code. */
		ITemplate adviceTemplate = this.templateGroup
				.getTemplate("deriveInstrumentation");

		/* Set template parameters. */
		adviceTemplate.setAttribute("attribute", propertyName);
		adviceTemplate.setAttribute("attributeType", propertyType);

		adviceTemplate.setAttribute("class", constrainedClass.getName());
		adviceTemplate.setAttribute("package", contextPackage);
		adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
		adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
		adviceTemplate.setAttribute("oclBody", constraint.getSpecification()
				.getBody().trim());

		/* Probably set that the constrained attribute is static. */
		if (constrainedProperty.isStatic()) {
			adviceTemplate.setAttribute("attIsStatic", "true");
		}
		// no else.

		/* Add the advice code to the aspect template. */
		aspectTemplate.setAttribute("advice", adviceTemplate.toString());

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.settings.isSaveCode()) {
			this.saveTransformedCode(result, aspectName + ".aj",
					this.getConstraintPackage(contextPackage));
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForDerive(Constraint)"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Generates an Aspect for the instrumentation of initial constraints.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForInit(Constraint constraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForInit(Constraint) - start");
		}
		// no else.

		String result;

		/* Get the constrained property. */
		Property constrainedProperty = ((Property) constraint
				.getConstrainedElement().get(0));

		ITemplate aspectTemplate = this.templateGroup.getTemplate("aspectFile");

		/* Get the path of the package where the constraint class is located. */
		String contextPackage = this.getPackagePath(constraint);
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));

		/* Compute the aspect's file name. */
		String className = constrainedProperty.getOwner().getName();
		String aspectName = className + "_"
				+ Ocl2JavaEnvironment.INIT_ASPECT_NAME + "_"
				+ constrainedProperty.getName();

		if (this.environment.isUsedAspectName(aspectName))
			aspectName = className + "_"
					+ this.environment.getNewInitAspectName();
		// no else.

		this.environment.addUsedAspectName(aspectName);
		aspectTemplate.setAttribute("name", aspectName);

		/* Generate the code which shall be inside the advice(s). */
		ITransformedCode constrainedCode = this
				.transformFragmentCode(constraint);

		/* Probably collect allInstances for some classes. */
		if (this.environment.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses = this.environment
					.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		/*
		 * Compute the constrained class name. Especially handle static defined
		 * properties.
		 */
		if (constrainedProperty.isStatic()) {
			className = ((ExpressionInOcl) constraint.getSpecification())
					.getContext().getType().getName();
		}

		else {
			className = constrainedProperty.getOwner().getName();
		}

		ITemplate adviceTemplate;

		/* Get the template and handle static attribute specially. */
		if (constrainedProperty.isStatic()) {
			adviceTemplate = this.templateGroup
					.getTemplate("staticInitInstrumentation");
		}

		else {
			adviceTemplate = this.templateGroup
					.getTemplate("initInstrumentation");
		}

		adviceTemplate.setAttribute("class", className);
		adviceTemplate.setAttribute("package", contextPackage);
		adviceTemplate.setAttribute("attribute", constrainedProperty.getName());
		adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
		adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
		adviceTemplate.setAttribute("oclBody", constraint.getSpecification()
				.getBody().trim());

		aspectTemplate.setAttribute("advice", adviceTemplate.toString());

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.settings.isSaveCode()) {
			this.saveTransformedCode(result, aspectName + ".aj",
					this.getConstraintPackage(contextPackage));
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForInit(Constraint)"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Generates an Aspect for the instrumentation of invariants.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForInv(Constraint constraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForInv(Constraint) - start");
		}
		// no else.

		String result;

		ITemplate aspectTemplate = this.templateGroup.getTemplate("aspectFile");

		/* Get the path of the package where the constraint class is located. */
		String contextPackage = this.getPackagePath(constraint);
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));

		/* Compute the aspect's file name. */
		String className = ((Type) constraint.getConstrainedElement().get(0))
				.getName();
		String aspectName = className + "_"
				+ Ocl2JavaEnvironment.INV_ASPECT_NAME + "_"
				+ constraint.getName();

		if (constraint.getName() == null || constraint.getName().length() == 0
				|| this.environment.isUsedAspectName(aspectName))
			aspectName = className + "_"
					+ this.environment.getNewInvAspectName();
		// no else.

		this.environment.addUsedAspectName(aspectName);
		aspectTemplate.setAttribute("name", aspectName);

		/* Generate the code which shall be inside the advice(s). */
		ITransformedCode constrainedCode = this
				.transformFragmentCode(constraint);

		/* Probably collect allInstances for some classes. */
		if (this.environment.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses = this.environment
					.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		Type constrainedClass = (Type) constraint.getConstrainedElement()
				.get(0);
		ITemplate adviceTemplate;

		/*
		 * Check which type of invariant check mode shall be used and create the
		 * template for the advice code.
		 */
		switch (this.settings.getInvariantCheckMode(constraint)) {

		case IOcl2JavaSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_PUBLIC_METHOD_EXECUTION:
			adviceTemplate = this.templateGroup
					.getTemplate("invInstrumentation2");
			break;

		case IOcl2JavaSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION:
			adviceTemplate = this.templateGroup
					.getTemplate("invInstrumentation3");

			String checkOperationName = this.templateGroup.getTemplate(
					"checkInvariantsOperationName").toString();

			/* Probably declare te check method inside the aspect. */
			if (!this.environment.isMethodAlreadyDefined(checkOperationName,
					constrainedClass.getQualifiedName())) {

				adviceTemplate.setAttribute("defineCheckMethod", "true");
				this.environment.addDefinedMethod(checkOperationName,
						constrainedClass.getQualifiedName());
			}
			// no else.

			break;

		default:
			adviceTemplate = this.templateGroup
					.getTemplate("invInstrumentation1");
		}

		/* Set template parameters. */
		adviceTemplate.setAttribute("class", constrainedClass.getName());
		adviceTemplate.setAttribute("package", contextPackage);
		adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
		adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
		adviceTemplate.setAttribute("oclBody", constraint.getSpecification()
				.getBody().trim());
		adviceTemplate.setAttribute("errorCode", this.settings
				.getViolationMacro(constraint).getCode());

		/*
		 * Probably add called attributes as parameters (only if the invariant
		 * shall be verified after the change of an depending attribute).
		 */
		if (this.settings.getInvariantCheckMode(constraint) == IOcl2JavaSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE) {

			if (this.environment.hasCalledProperties()) {

				for (String aCalledProperty : this.environment
						.getCalledProperties()) {
					adviceTemplate.setAttribute("calledAttPaths",
							aCalledProperty);
					adviceTemplate.setAttribute("calledAttNames",
							aCalledProperty.replaceAll("\\.", "_"));
				}
			}
			// no else.
		}
		// no else.

		/* Probably disable the inheritance for this invariant. */
		if (this.settings.isInheritanceDisabled(constraint)) {
			adviceTemplate.setAttribute("disableInheritance", "true");
		}
		// no else.

		/* Add the advice code to the aspect template. */
		aspectTemplate.setAttribute("advice", adviceTemplate.toString());

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.settings.isSaveCode()) {
			this.saveTransformedCode(result, aspectName + ".aj",
					this.getConstraintPackage(contextPackage));
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForInv(Constraint)"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Generates an Aspect for the instrumentation of postconditions.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForPost(Constraint constraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForPost(Constraint) - start");
		}
		// no else.

		String result;

		/* Get the constrained operation. */
		Operation constrainedOperation = ((Operation) constraint
				.getConstrainedElement().get(0));

		ITemplate aspectTemplate = this.templateGroup.getTemplate("aspectFile");

		/* Get the path of the package where the constraint class is located. */
		String contextPackage = this.getPackagePath(constraint);
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));

		/* Compute the aspect's file name. */
		String className = constrainedOperation.getOwner().getName();
		String aspectName = className + "_"
				+ Ocl2JavaEnvironment.POST_ASPECT_NAME + "_"
				+ constrainedOperation.getName();

		if (this.environment.isUsedAspectName(aspectName))
			aspectName = className + "_"
					+ this.environment.getNewPostAspectName();
		// no else.

		this.environment.addUsedAspectName(aspectName);
		aspectTemplate.setAttribute("name", aspectName);

		/* Generate the code which shall be inside the advice(s). */
		ITransformedCode constrainedCode = this
				.transformFragmentCode(constraint);

		/* Probably collect allInstances for some classes. */
		if (this.environment.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses = this.environment
					.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		/* Probably collect newInstances for some classes. */
		if (this.environment.hasIsNewClasses()) {
			Set<String> isNewClasses = this.environment.getIsNewClasses();

			for (String aClassName : isNewClasses) {
				aspectTemplate.setAttribute("isNewClasses", aClassName);
			}
		}
		// no else.

		NamedElement constrainedClass = constrainedOperation.getOwner();

		String operationName = constrainedOperation.getName();
		String operationResultType = this.transformType(
				constrainedOperation.getType()).toString();
		/* Create Template for the advice code. */
		ITemplate adviceTemplate = this.templateGroup
				.getTemplate("postInstrumentation");

		/* Set template parameters. */
		adviceTemplate.setAttribute("class", constrainedClass.getName());
		adviceTemplate.setAttribute("package", contextPackage);
		adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
		adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
		adviceTemplate.setAttribute("oclBody", constraint.getSpecification()
				.getBody().trim());
		adviceTemplate.setAttribute("method", operationName);
		adviceTemplate.setAttribute("errorCode", this.settings
				.getViolationMacro(constraint).getCode());

		/* Probably set the returnType. */
		if (!operationResultType.equals(this.templateGroup.getTemplate(
				"voidType").toString())) {
			adviceTemplate.setAttribute("returnType", operationResultType);
		}
		// no else.

		/* Probably set the arguments of the operation. */
		this.setArgumentsForInstrumentationTemplate(constraint, adviceTemplate);

		/* Probably store some atPre values */
		if (this.environment.hasAtPreValues()) {
			Map<ITransformedCode, Object[]> atPreValues = this.environment
					.getAtPreValues();
			Set<ITransformedCode> valueCodes = atPreValues.keySet();
			Set<String> atPreTypes = new HashSet<String>();

			for (ITransformedCode aValueCode : valueCodes) {
				Object[] typeAndName = atPreValues.get(aValueCode);

				Type aType = (Type) typeAndName[0];
				String transformedType = this.transformInitializableType(aType)
						.toString();

				/*
				 * The atPre values for primitive and collection types are
				 * initialized different from user defined types.
				 */
				if (aType instanceof PrimitiveType
						|| aType instanceof CollectionType) {
					adviceTemplate.setAttribute("primitiveAtPreCodes",
							aValueCode.getCode());
					adviceTemplate.setAttribute("primitiveAtPreExps",
							aValueCode.getResultExp());
					adviceTemplate.setAttribute("primitiveAtPreTypes",
							transformedType);
					adviceTemplate.setAttribute("primitiveAtPreVars",
							typeAndName[1].toString());
				}

				else {
					adviceTemplate.setAttribute("atPreCodes",
							aValueCode.getCode());
					adviceTemplate.setAttribute("atPreExps",
							aValueCode.getResultExp());
					adviceTemplate.setAttribute("atPreTypes", transformedType);
					adviceTemplate.setAttribute("atPreVars",
							typeAndName[1].toString());

					/*
					 * Collect the types to provide user defined copy methods.
					 */
					atPreTypes.add(transformedType);
				}

			}
			// end for.

			/* Add initialization methods for the atPreTypes. */
			for (String aType : atPreTypes) {
				adviceTemplate.setAttribute("atPreTypeSet", aType);
			}
		}
		// no else.

		/* Probably use the special OCL operation oclIsNew. */
		if (this.environment.hasIsNewClasses()) {
			adviceTemplate.setAttribute("oclIsNewUsed", "true");
		}
		// no else.

		/* Probably disable inheritance for this post condition. */
		if (this.settings.isInheritanceDisabled(constraint)) {
			adviceTemplate.setAttribute("disableInheritance", "true");
		}
		// no else.

		/* Probably set that the constraint operation is static. */
		if (constrainedOperation.isStatic()) {
			adviceTemplate.setAttribute("opIsStatic", "true");
		}
		// no else.

		/* Probably set that the constrained operation is a constructor. */
		if (operationName.equals(constrainedClass.getName())) {
			adviceTemplate.setAttribute("opIsConstructor", "true");
		}
		// no else.

		/* Add the advice code to the aspect template. */
		aspectTemplate.setAttribute("advice", adviceTemplate.toString());

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.settings.isSaveCode()) {
			this.saveTransformedCode(result, aspectName + ".aj",
					this.getConstraintPackage(contextPackage));
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForPost(Constraint)"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Generates an Aspect for the instrumentation of preconditions.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForPre(Constraint constraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForPre(Constraint) - start");
		}
		// no else.

		String result;

		/* Get the constrained operation. */
		Operation constrainedOperation = ((Operation) constraint
				.getConstrainedElement().get(0));

		ITemplate aspectTemplate = this.templateGroup.getTemplate("aspectFile");

		/* Get the path of the package where the constraint class is located. */
		String contextPackage = this.getPackagePath(constraint);
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));

		/* Compute the aspect's file name. */
		String className = constrainedOperation.getOwner().getName();
		String aspectName = className + "_"
				+ Ocl2JavaEnvironment.PRE_ASPECT_NAME + "_"
				+ constrainedOperation.getName();

		if (this.environment.isUsedAspectName(aspectName))
			aspectName = className + "_"
					+ this.environment.getNewPreAspectName();
		// no else.

		this.environment.addUsedAspectName(aspectName);
		aspectTemplate.setAttribute("name", aspectName);

		/* Generate the code which shall be inside the advice(s). */
		ITransformedCode constrainedCode = this
				.transformFragmentCode(constraint);

		/* Probably collect allInstances for some classes. */
		if (this.environment.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses;

			allInstancesClasses = this.environment.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		NamedElement constrainedClass = constrainedOperation.getOwner();

		String operationName = constrainedOperation.getName();
		String operationResultType = this.transformType(
				constrainedOperation.getType()).toString();
		/* Create Template for the advice code. */
		ITemplate adviceTemplate = this.templateGroup
				.getTemplate("preInstrumentation");

		/* Set template parameters. */
		adviceTemplate.setAttribute("class", constrainedClass.getName());
		adviceTemplate.setAttribute("package", contextPackage);
		adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
		adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
		adviceTemplate.setAttribute("oclBody", constraint.getSpecification()
				.getBody().trim());
		adviceTemplate.setAttribute("method", operationName);
		adviceTemplate.setAttribute("errorCode", this.settings
				.getViolationMacro(constraint).getCode());

		/* Probably set the returnType. */
		if (!operationResultType.equals(this.templateGroup.getTemplate(
				"voidType").toString())) {
			adviceTemplate.setAttribute("returnType", operationResultType);
		}
		// no else.

		/* Probably set the arguments of the operation. */
		this.setArgumentsForInstrumentationTemplate(constraint, adviceTemplate);

		/* Probably disable inheritance for this post condition. */
		if (this.settings.isInheritanceDisabled(constraint)) {
			adviceTemplate.setAttribute("disableInheritance", "true");
		}
		// no else.

		/* Probably set that the constraint operation is static. */
		if (constrainedOperation.isStatic()) {
			adviceTemplate.setAttribute("opIsStatic", "true");
		}
		// no else.

		/* Probably set that the constrained operation is a constructor. */
		if (operationName.equals(constrainedClass.getName())) {
			adviceTemplate.setAttribute("opIsConstructor", "true");
		}
		// no else.

		/* Add the advice code to the aspect template. */
		aspectTemplate.setAttribute("advice", adviceTemplate.toString());

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.settings.isSaveCode()) {
			this.saveTransformedCode(result, aspectName + ".aj",
					this.getConstraintPackage(contextPackage));
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForPre(Constraint)"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method which saves a given generated code into a given file name
	 * at a given location.
	 * </p>
	 * 
	 * @param generatedCode
	 *            The code which shall be saved.
	 * @param fileName
	 *            The name of the File into which the transformed code shall be
	 *            saved.
	 * @param subFolder
	 *            The subFolder(s) into which the File shall be located
	 *            (relative) to the sourcePath.
	 * @throws Ocl2CodeException
	 *             Thrown if the given file or location can not be found.
	 */
	private void saveTransformedCode(String generatedCode, String fileName,
			String subFolder) throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("saveTransformedCode(String, String, String)");
		}
		// no else.

		try {
			/* Replace all '.' with '/'. Does not work with String#replaceAll. */
			String[] directories = subFolder.split("\\.");

			subFolder = "";
			for (int i = 0; i < directories.length; i++) {
				subFolder += directories[i] + "/";
			}

			File outputFolder = new File(this.settings.getSourceDirectory()
					+ subFolder);

			/* Check if output folder does exists. */
			if (!outputFolder.isDirectory()) {
				outputFolder.mkdirs();
			}
			// no else.

			File outputFile = new File(outputFolder.getAbsolutePath() + "/"
					+ fileName);

			/* Create the output file. */
			FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
			fileOutputStream.write(generatedCode.getBytes());
			fileOutputStream.close();
		}

		catch (FileNotFoundException e) {

			String msg;

			msg = "File for saving generated code not found. ";
			msg += "File location was ";
			msg += this.settings.getSourceDirectory() + fileName + ".";

			LOGGER.fatal(msg);

			throw new Ocl2CodeException(msg);
		}

		catch (IOException e) {

			String msg;

			msg = "Opening of File for saving failed. ";
			msg += "File location was ";
			msg += this.settings.getSourceDirectory() + fileName + ".";

			LOGGER.fatal(msg);

			throw new Ocl2CodeException(msg);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("saveTransformedCode(String, String, String) - end");
		}
		// no else.
	}

	/**
	 * <p>
	 * Sets the argument names and {@link Type}s of a given {@link Constraint}
	 * for its instrumentation template.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} for that the arguments shall be set.
	 * @param adviceTemplate
	 *            The {@link ITemplate} to set the names and {@link Type}s.
	 */
	private void setArgumentsForInstrumentationTemplate(Constraint constraint,
			ITemplate adviceTemplate) {

		for (Variable anArgument : ((ExpressionInOcl) constraint
				.getSpecification()).getParameter()) {
			String anArgumentsName;
			String anArgumentsType;

			anArgumentsName = anArgument.getName();
			anArgumentsType = this.transformType(anArgument.getType())
					.toString();

			adviceTemplate.setAttribute("args", anArgumentsName);
			adviceTemplate.setAttribute("argTypes", anArgumentsType);
		}
		// end for.
	}

	/**
	 * <p>
	 * Transforms a given {@link CollectionType} into an
	 * {@link ITransformedType}.
	 * </p>
	 * 
	 * @param aCollectionType
	 *            The {@link CollectionType} for which code shall be returned.
	 * @return The code for a given {@link CollectionType}.
	 */
	private ITransformedType transformCollectionType(
			CollectionType aCollectionType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformCollectionType(CollectionType) - start");
		}
		// no else.

		ITransformedType result = null;
		ITemplate template = null;

		if (aCollectionType instanceof BagType) {
			template = this.templateGroup.getTemplate("bagType");
		}

		else if (aCollectionType instanceof OrderedSetType) {
			template = this.templateGroup.getTemplate("orderedSetType");
		}

		else if (aCollectionType instanceof SequenceType) {
			template = this.templateGroup.getTemplate("sequenceType");
		}

		else if (aCollectionType instanceof SetType) {
			template = this.templateGroup.getTemplate("setType");
		}

		else {
			template = this.templateGroup.getTemplate("collectionType");
		}
		// no else.

		if (template != null) {
			result = new TransformedTypeImpl(template.toString());
		}
		// no else.

		/* Get the generic type of the Collection. */
		Type elementType = aCollectionType.getElementType();

		/* Set the generic type, if it is not null. */
		if (elementType != null && result != null) {
			ITransformedType elementTypeInJava;

			elementTypeInJava = this.transformType(elementType);

			if (!elementTypeInJava.equals("null")) {
				result.setGenericType(elementTypeInJava);
			}
			// no else.
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformCollectionType(CollectionType)"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * This method transforms the fragment code for a given {@link Constraint}.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} for which code shall be transformed.
	 * @return An {@link ITransformedCode} containing the Code transformed for
	 *         the given {@link Constraint}'s {@link OclExpression}.
	 * @throws Ocl2CodeException
	 */
	private ITransformedCode transformFragmentCode(Constraint aConstraint)
			throws Ocl2CodeException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformFragmentCode(Constraint) - start");
		}
		// no else.

		Expression anExpression = aConstraint.getSpecification();
		ITransformedCode result = this.doSwitch(anExpression);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformFragmentCode(Constraint)"
					+ " - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Transforms the given {@link CollectionType} into a Java class that can be
	 * initialized.
	 * </p>
	 * 
	 * @param aCollectionType
	 *            The {@link CollectionType} for which code shall be returned.
	 * @return The {@link ITransformedType} for a given {@link CollectionType}.
	 */
	private ITransformedType transformInitializableCollectionType(
			CollectionType aCollectionType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInitializableCollectionType(CollectionType) - start");
		}
		// no else.

		ITransformedType result = null;
		ITemplate template = null;

		if (aCollectionType instanceof BagType) {
			template = this.templateGroup.getTemplate("bagTypeInitialization");
		}

		else if (aCollectionType instanceof OrderedSetType) {
			template = this.templateGroup
					.getTemplate("orderedSetTypeInitialization");
		}

		else if (aCollectionType instanceof SequenceType) {
			template = this.templateGroup
					.getTemplate("sequenceTypeInitialization");
		}

		else if (aCollectionType instanceof SetType) {
			template = this.templateGroup.getTemplate("setTypeInitialization");
		}

		else {
			template = this.templateGroup
					.getTemplate("collectionTypeInitialization");
		}
		// no else.

		if (template != null) {
			result = new TransformedTypeImpl(template.toString());
		}
		// no else.

		/* Get the generic type of the Collection. */
		Type elementType = aCollectionType.getElementType();

		/* Set the generic type, if it is not null. */
		if (elementType != null && result != null) {
			ITransformedType elementTypeInJava = this
					.transformType(elementType);

			if (!elementTypeInJava.equals("null")) {
				result.setGenericType(elementTypeInJava);
			}
			// no else.
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInitializableCollectionType(CollectionType)"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Transforms the {@link Type} into a java class that can be instantiated.
	 * </p>
	 * 
	 * @param aType
	 *            The {@link Type} for which code shall be returned.
	 * @return The code for a given {@link Type}.
	 */
	private ITransformedType transformInitializableType(Type aType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInitializableType(Type) - start");
		}
		// no else.

		ITransformedType result;

		if (aType instanceof AnyType) {
			result = new TransformedTypeImpl(this.templateGroup.getTemplate(
					"anyType").toString());
		}

		else if (aType instanceof CollectionType) {
			result = this
					.transformInitializableCollectionType((CollectionType) aType);
		}

		else if (aType instanceof Enumeration) {
			result = new TransformedTypeImpl(this.templateGroup.getTemplate(
					"enumerationType").toString());
		}

		else if (aType instanceof InvalidType) {
			result = new TransformedTypeImpl(this.templateGroup.getTemplate(
					"invalidType").toString());
		}

		else if (aType instanceof PrimitiveType) {
			result = this.transformPrimitiveType((PrimitiveType) aType);
		}

		else if (aType instanceof TupleType) {
			result = new TransformedTypeImpl(this.templateGroup.getTemplate(
					"tupleType").toString());
		}

		else if (aType instanceof TypeType) {
			result = new TransformedTypeImpl(this.templateGroup.getTemplate(
					"typeType").toString());
		}

		else if (aType instanceof VoidType) {
			result = new TransformedTypeImpl(this.templateGroup.getTemplate(
					"voidType").toString());
		}

		else {
			/*
			 * For other types return their canonical name as type (these are
			 * model-defined types).
			 */
			result = new TransformedTypeImpl(this.getCanonicalName(aType));
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInitializableType(Type)"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Transforms instrumentation code for a given {@link Constraint}.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} for which the code shall be
	 *            transformed.
	 * @return The transformed Code for the given {@link Constraint}.
	 * @throws Ocl2CodeException
	 *             Thrown, if an unknown or illegal Type or Expression is found
	 *             during code transformation.
	 */
	private String transformInstrumentationCode(Constraint aConstraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInstrumentationCode(Constraint) - start");
		}
		// no else.

		String result;

		/*
		 * Each constraint code use local variables. Thus the counters for
		 * generated variables in the Environment can be reseted.
		 */
		this.environment.resetEnvironmentForNextConstraint();

		/* Detect the kind of Constraint and transform its instrumentation code. */
		switch (aConstraint.getKind()) {

		case DEFINITION:
			result = this.instrumentCodeForDef(aConstraint);
			break;

		case DERIVED:
			result = this.instrumentCodeForDerive(aConstraint);
			break;

		case INITIAL:
			result = this.instrumentCodeForInit(aConstraint);
			break;

		case BODY:
			result = this.instrumentCodeForBody(aConstraint);
			break;

		case INVARIANT:
			result = this.instrumentCodeForInv(aConstraint);
			break;

		case PRECONDITION:
			result = this.instrumentCodeForPre(aConstraint);
			break;

		case POSTCONDITION:
			result = this.instrumentCodeForPost(aConstraint);
			break;

		default:
			String msg = "An unknown or unsupported kind of Constraint was found. ";
			msg += "Found Kind was  ";
			msg += aConstraint.getKind() + ".";

			LOGGER.fatal(msg);
			throw new Ocl2CodeException(msg);
		}
		// end switch.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInstrumentationCode(Constraint"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Transforms a given {@link PrimitiveType} into an {@link ITransformedType}
	 * .
	 * </p>
	 * 
	 * @param aPrimitiveType
	 *            The {@link PrimitiveType} for which code shall be returned.
	 * @return The {@link ITransformedType} for a given {@link PrimitiveType}.
	 */
	private ITransformedType transformPrimitiveType(PrimitiveType aPrimitiveType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformPrimitiveType(PrimitiveType) - start");
		}
		// no else.

		ITransformedType result = null;
		ITemplate template = null;

		switch (aPrimitiveType.getKind()) {

		case BOOLEAN:
			template = this.templateGroup.getTemplate("booleanType");
			break;

		case INTEGER:
			template = this.templateGroup.getTemplate("integerType");
			break;

		case REAL:
			template = this.templateGroup.getTemplate("realType");
			break;

		case STRING:
			template = this.templateGroup.getTemplate("stringType");
			break;

		case VOID:
			template = this.templateGroup.getTemplate("voidType");
			break;

		default:
			template = this.templateGroup.getTemplate("unknownType");
		}
		// end switch.

		result = new TransformedTypeImpl(template.toString());

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformPrimitiveType(PrimitiveType)"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Transforms a given {@link Type} into an {@link ITransformedType}.
	 * </p>
	 * 
	 * @param aType
	 *            The {@link Type} for which code shall be returned.
	 * @return The {@link ITransformedType} for a given {@link Type}.
	 */
	private ITransformedType transformType(Type aType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformType(Type) - start");
		}
		// no else.

		ITransformedType result;

		if (aType instanceof AnyType) {
			result = new TransformedTypeImpl(this.templateGroup.getTemplate(
					"anyType").toString());
		}

		else if (aType instanceof CollectionType) {
			result = this.transformCollectionType((CollectionType) aType);
		}

		else if (aType instanceof Enumeration) {
			result = new TransformedTypeImpl(this.templateGroup.getTemplate(
					"enumerationType").toString());
		}

		else if (aType instanceof InvalidType) {
			result = new TransformedTypeImpl(this.templateGroup.getTemplate(
					"invalidType").toString());
		}

		else if (aType instanceof PrimitiveType) {
			result = this.transformPrimitiveType((PrimitiveType) aType);
		}

		else if (aType instanceof TupleType) {
			result = new TransformedTypeImpl(this.templateGroup.getTemplate(
					"tupleType").toString());
		}

		else if (aType instanceof TypeType) {
			result = new TransformedTypeImpl(this.templateGroup.getTemplate(
					"typeType").toString());
		}

		else if (aType instanceof VoidType) {
			result = new TransformedTypeImpl(this.templateGroup.getTemplate(
					"voidType").toString());
		}

		else {
			/*
			 * For other types return their canonical name as type (used to
			 * handle model-defined types).
			 */
			String typeName = this.getCanonicalName(aType);
			result = new TransformedTypeImpl(typeName);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformType(Type)" + "- end - return value="
					+ result);
		}
		// no else.

		return result;
	}
}