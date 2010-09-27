/*
Copyright (C) 2008-2010 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of the OCL 2 Java Code Generator of DresdenOCL.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
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
import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
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

	/** The Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(Ocl2Java.class);

	/** The paths of the String templates for the code transformation. */
	private final static String TEMPLATE_PATH = "/resources/template/java/";
	private final static String EXPRESSION_TEMPLATE_FILE = "expressions.stg";
	private final static String JAVA_TEMPLATE_FILE = "java.stg";
	private final static String INSTRUMENTATION_TEMPLATE_FILE =
			"instrumentations.stg";
	private final static String OPERATION_TEMPLATE_FILE = "operations.stg";
	private final static String TYPE_TEMPLATE_FILE = "types.stg";

	/** The prefix of the name of new created super classes. */
	private final static String EXTENDEC_CLASS_NAME_PREFIX = "Extended";

	/**
	 * Contains operation names that must be renamed to support them in languages
	 * like Java. E.g., '=' ist renamed to equals.
	 */
	private static Map<String, String> renamedOperationNames =
			new HashMap<String, String>();

	/* static initialization. */
	static {
		renamedOperationNames.put("=", "equals");
		renamedOperationNames.put("<>", "notEquals");
		renamedOperationNames.put("-", "minus");
	}

	/**
	 * The engine to provide all {@link ITemplate}s used for code transformation.
	 */
	private ITemplateGroup myTemplateGroup;

	/**
	 * The environment to provide and store some values during code
	 * transformation.
	 */
	private IOcl2JavaEnvironment myCodeTransEnv;

	/** The Settings used during code generation. */
	private IOcl2JavaSettings mySettings;

	/**
	 * <p>
	 * Creates a new instance to transform code.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *           If the initialization fails.
	 */
	public Ocl2Java() throws Ocl2CodeException {

		this.init();
	}

	/**
	 * <p>
	 * Initializes the code generator.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *           Thrown, if a String template for code transformation can not be
	 *           found.
	 */
	private void init() throws Ocl2CodeException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("init() - start");
		}
		// no else.

		this.myCodeTransEnv = new Ocl2JavaEnvironment();

		/* Try to load the template engine. */
		try {
			LinkedList<URL> templatePaths;

			templatePaths = new LinkedList<URL>();

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

			TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup("Ocl2Java");
			this.myTemplateGroup =
					TemplatePlugin.getTemplateGroupRegistry().addDefaultTemplateGroup(
							"Ocl2Java", null);

			this.myTemplateGroup.addFiles(templatePaths);

			this.mySettings = new Ocl2JavaSettings();
		}

		catch (TemplateException e) {
			String msg;

			msg = "The template for code transformation could not be loaded. ";
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

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl2Code#getSettings()
	 */
	public IOcl2JavaSettings getSettings() {

		return this.mySettings;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl22Code#resetEnvironment()
	 */
	public void resetEnvironment() {

		this.myCodeTransEnv = new Ocl2JavaEnvironment();
	}

	/**
	 * <p>
	 * This method transforms the fragment code for a given {@link Constraint}.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} for which code shall be transformed.
	 * @return An {@link ITransformedCode} containing the Code transformed for the
	 *         given {@link Constraint}'s {@link OclExpression}.
	 * @throws Ocl2CodeException
	 */
	private ITransformedCode transformFragmentCode(Constraint aConstraint)
			throws Ocl2CodeException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformFragmentCode(Constraint) - start");
		}
		// no else.

		ITransformedCode result;
		EObject anExpression;

		anExpression = (EObject) aConstraint.getSpecification();
		result = this.doSwitch(anExpression);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformFragmentCode(Constraint)"
					+ " - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl2Code#transformInstrumentationCode
	 * (java.util.List)
	 */
	public List<String> transformInstrumentationCode(List<Constraint> constraints)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInstrumentationCode"
					+ "(List<Constraint>) - start");
		}
		// no else.

		List<String> result;

		result = new ArrayList<String>();

		/* Iterate through all constraints and compute their code. */
		for (Constraint aConstraint : constraints) {
			this.myCodeTransEnv.resetEnvironmentForNextConstraint();

			result.add(this.transformInstrumentationCode(aConstraint));
		}

		/* Probably save all new transformed super classes as files. */
		if (this.mySettings.isSaveCode()) {
			this.saveExtendedClasses();
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInstrumentationCode"
					+ "(List<Constraint>) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Transforms code for a given {@link Constraint} and its instrumentation
	 * code.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} for which the code shall be transformed.
	 * @return The transformed Code for the given {@link Constraint}.
	 * @throws Ocl2CodeException
	 *           Thrown, if an unknown or illegal Type or Expression is found
	 *           during code transformation.
	 */
	private String transformInstrumentationCode(Constraint aConstraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInstrumentationCode(Constraint) - start");
		}
		// no else.

		String result;

		ConstraintKind constraintKind;

		/*
		 * Each constraint code use local variables. Thus the counters for generated
		 * variables in the Environment can be reseted.
		 */
		this.myCodeTransEnv.resetEnvironmentForNextConstraint();

		constraintKind = aConstraint.getKind();

		/* Detect the kind of Constraint and transform its instrumentation code. */
		if (constraintKind == ConstraintKind.DEFINITION) {
			result = this.instrumentCodeForDef(aConstraint);
		}

		else if (constraintKind == ConstraintKind.DERIVED) {
			result = this.instrumentCodeForDerive(aConstraint);
		}

		else if (constraintKind == ConstraintKind.INITIAL) {
			result = this.instrumentCodeForInit(aConstraint);
		}

		else if (constraintKind == ConstraintKind.BODY) {
			result = this.instrumentCodeForBody(aConstraint);
		}

		else if (constraintKind == ConstraintKind.INVARIANT) {
			result = this.instrumentCodeForInv(aConstraint);
		}

		else if (constraintKind == ConstraintKind.PRECONDITION) {
			result = this.instrumentCodeForPre(aConstraint);
		}

		else if (constraintKind == ConstraintKind.POSTCONDITION) {
			result = this.instrumentCodeForPost(aConstraint);
		}

		else {
			String msg;

			msg = "An unknown or unsupported kind of Constraint was found. ";
			msg += "Found Kind was  ";
			msg += constraintKind + ".";

			LOGGER.fatal(msg);

			throw new Ocl2CodeException(msg);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInstrumentationCode(Constraint"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseBooleanLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp)
	 */
	public ITransformedCode caseBooleanLiteralExp(
			BooleanLiteralExp aBooleanLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseBooleanLiteralExp(BooleanLiteralExp) - start");
		}
		// no else.

		ITransformedCode result;

		ITemplate template;
		String literalValue;

		template = this.myTemplateGroup.getTemplate("literalExp");

		literalValue = "";
		literalValue += aBooleanLiteralExp.isBooleanSymbol();

		template.setAttribute("type",
				this.transformType(aBooleanLiteralExp.getType()).toString());
		template.setAttribute("value", literalValue);

		result = new TransformedCodeImpl();
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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseCollectionLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp)
	 */
	public ITransformedCode caseCollectionLiteralExp(
			CollectionLiteralExp aCollectionLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseCollectionLiteralExp(CollectionLiteralExp) - start");
		}
		// no else.

		ITransformedCode result;
		ITransformedType resultType;

		ITemplate template;

		List<CollectionLiteralPart> elements;
		String collectionName;

		result = new TransformedCodeImpl();

		/* Compute the result type first. */
		resultType =
				this.transformInitializableType(aCollectionLiteralExp.getType());

		collectionName = this.myCodeTransEnv.getNewCollectionVarName();

		/* Prepare the template for the collection expression. */
		template = this.myTemplateGroup.getTemplate("collectionLiteralExp");
		template.setAttribute("collectionName", collectionName);
		template.setAttribute("collectionType", resultType.toString());

		/* Transform Code for all initial elements. */
		elements = aCollectionLiteralExp.getPart();

		for (CollectionLiteralPart aCollectionPart : elements) {

			ITemplate elemTemplate;

			/* Transform initial Code for all CollectionItems. */
			if (aCollectionPart instanceof CollectionItem) {

				CollectionItem anItem;
				OclExpression anItemsExp;
				ITransformedCode anItemsCode;

				anItem = (CollectionItem) aCollectionPart;
				anItemsExp = anItem.getItem();
				anItemsCode = this.doSwitch((EObject) anItemsExp);

				/* Transform code for element expression. */
				elemTemplate =
						this.myTemplateGroup
								.getTemplate("collectionLiteralExp_collectionItem");
				elemTemplate.setAttribute("collectionName", collectionName);
				elemTemplate.setAttribute("itemExp", anItemsCode.getResultExp());

				/* Add element code to collection code. */
				template.setAttribute("elementCodes", anItemsCode.getCode());
				template.setAttribute("elementExps", elemTemplate.toString());
			}

			/* Collection can also be initialized using a for-loop. */
			else if (aCollectionPart instanceof CollectionRange) {

				CollectionRange aRange;

				OclExpression firstExp;
				ITransformedCode firstExpCode;
				ITransformedType firstExpType;

				OclExpression lastExp;
				ITransformedCode lastExpCode;

				String elementCode;

				aRange = (CollectionRange) aCollectionPart;

				firstExp = aRange.getFirst();
				firstExpCode = this.doSwitch((EObject) firstExp);
				firstExpType = this.transformType(firstExp.getType());

				lastExp = aRange.getLast();
				lastExpCode = this.doSwitch((EObject) lastExp);

				elementCode = firstExpCode.getCode();
				if (elementCode.length() > 0) {
					elementCode += "\n";
				}
				// no else.
				elementCode = lastExpCode.getCode();

				elemTemplate =
						this.myTemplateGroup
								.getTemplate("collectionLiteralExp_collectionRange");
				elemTemplate.setAttribute("collectionName", collectionName);
				elemTemplate.setAttribute("indexVar",
						this.myCodeTransEnv.getNewIndexVarName());
				elemTemplate.setAttribute("indexType", firstExpType.toString());
				elemTemplate.setAttribute("firstExp", firstExpCode.getResultExp());
				elemTemplate.setAttribute("lastExp", lastExpCode.getResultExp());

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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseEnumLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp)
	 */
	public ITransformedCode caseEnumLiteralExp(EnumLiteralExp anEnumLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseEnumLiteralExp(EnumLiteralExp) - start");
		}
		// no else.

		ITransformedCode result;
		ITemplate template;

		Enumeration anEnumeration;
		EnumerationLiteral anEnumerationLiteral;

		String enumerationName;

		result = new TransformedCodeImpl();

		anEnumerationLiteral = anEnumLiteralExp.getReferredEnumLiteral();
		anEnumerationLiteral.getEnumeration();

		anEnumeration = anEnumerationLiteral.getEnumeration();

		/* Use a canonical name for the enumeration name. */
		enumerationName = anEnumeration.getQualifiedName();
		enumerationName = enumerationName.replaceAll("::", ".");

		/* Probably remove the root package. */
		if (enumerationName.startsWith("root.")) {
			enumerationName = enumerationName.substring(5);
		}
		// no else.

		template = this.myTemplateGroup.getTemplate("enumLiteralExp");
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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseExpressionInOcl
	 * (tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl)
	 */
	public ITransformedCode caseExpressionInOcl(ExpressionInOcl anExpressionInOcl) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseExpressionInOcl(ExpressionInOcl) - start");
		}
		// no else.

		ITransformedCode result;

		OclExpression bodyExpression;

		bodyExpression = anExpressionInOcl.getBodyExpression();

		/* Transform bodyCode. */
		result = this.doSwitch((EObject) bodyExpression);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseExpressionInOcl(ExpressionInOcl)"
					+ " - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIfExp(tudresden.ocl20.pivot.essentialocl.expressions.IfExp)
	 */
	public ITransformedCode caseIfExp(IfExp anIfExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIfExp(IfExp) - start");
		}
		// no else.

		ITransformedCode result;

		ITemplate template;

		OclExpression ifExp;
		OclExpression thenExp;
		OclExpression elseExp;

		ITransformedCode ifCode;
		ITransformedCode thenCode;
		ITransformedCode elseCode;

		ITransformedType resultType;
		String resultVarName;

		ifExp = anIfExp.getCondition();
		thenExp = anIfExp.getThenExpression();
		elseExp = anIfExp.getElseExpression();

		result = new TransformedCodeImpl();

		/* Transform ifExp, thenExp and elseExp. */
		ifCode = doSwitch((EObject) ifExp);
		thenCode = doSwitch((EObject) thenExp);
		elseCode = doSwitch((EObject) elseExp);

		/* Declare variable for result of if-then-else expression. */
		resultVarName = this.myCodeTransEnv.getNewIfExpResultName();
		result.setResultExp(resultVarName);

		resultType = this.transformType(thenExp.getType());

		/* Transform ifExp. */
		template = this.myTemplateGroup.getTemplate("ifExp");
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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIntegerLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp)
	 */
	public ITransformedCode caseIntegerLiteralExp(
			IntegerLiteralExp anIntegerLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIntegerLiteralExp(IntegerLiteralExp) - start");
		}
		// no else.

		ITransformedCode result;

		ITemplate template;
		String literalValue;

		template = this.myTemplateGroup.getTemplate("literalExp");

		literalValue = "";
		literalValue += anIntegerLiteralExp.getIntegerSymbol();

		template.setAttribute("type",
				this.transformType(anIntegerLiteralExp.getType()).toString());
		template.setAttribute("value", literalValue);

		result = new TransformedCodeImpl();
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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseInvalidLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp)
	 */
	public ITransformedCode caseInvalidLiteralExp(
			InvalidLiteralExp anInvalidLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseInvalidLiteralExp(InvalidLiteralExp) - start");
		}
		// no else.

		ITransformedCode result;
		ITemplate template;

		result = new TransformedCodeImpl();
		template = this.myTemplateGroup.getTemplate("invalidLiteralExp");

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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIterateExp (tudresden.ocl20.pivot.essentialocl.expressions.IterateExp)
	 */
	public ITransformedCode caseIterateExp(IterateExp anIterateExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIterateExp(IterateExp) - start");
		}
		// no else.

		ITransformedCode result;
		ITransformedType resultType;
		ITemplate template;

		OclExpression sourceExp;
		ITransformedCode sourceCode;
		ITransformedType sourceType;

		OclExpression resultVarInitExp;
		ITransformedCode resultVarInitCode;

		OclExpression bodyExp;
		ITransformedCode bodyCode;

		List<Variable> iteratorList;
		Variable iterateVariable;

		String resultVar;
		String sourceGenericType;

		resultType = this.transformType(anIterateExp.getType());

		sourceExp = anIterateExp.getSource();
		sourceCode = this.doSwitch((EObject) sourceExp);
		sourceType = this.transformType(sourceExp.getType());

		bodyExp = anIterateExp.getBody();

		resultVar = anIterateExp.getResult().getName();
		resultVarInitExp = anIterateExp.getResult().getInitExpression();
		resultVarInitCode = this.doSwitch((EObject) resultVarInitExp);

		/* Get the list with all iterator variables. */
		iteratorList = anIterateExp.getIterator();

		/*
		 * Get the first iterator variable (probaly further exist during recursive
		 * calls).
		 */
		iterateVariable = iteratorList.remove(0);

		/*
		 * If the Expression has more than one iterator variable. Create an inner
		 * IterateExp without the first iterator variable.
		 */
		if (iteratorList.size() > 0) {
			/* the first iterator has been already removed. */
			bodyExp = anIterateExp;
			/*
			 * The Expression will be transformed inside the transformation of the
			 * first iterator Variable later on.
			 */
		}

		/* Begin with code transformation. */
		bodyCode = this.doSwitch((EObject) bodyExp);

		result = new TransformedCodeImpl();

		template = this.myTemplateGroup.getTemplate("iterateExp");

		template.setAttribute("sourceCode", sourceCode.getCode());
		template.setAttribute("sourceExp", sourceCode.getResultExp());
		template.setAttribute("itVar", iterateVariable.getName());
		template.setAttribute("bodyCode", bodyCode.getCode());
		template.setAttribute("bodyExp", bodyCode.getResultExp());
		template.setAttribute("resultVarInitCode", resultVarInitCode.getCode());
		template.setAttribute("resultVarInitExp", resultVarInitCode.getResultExp());
		/* FIXME Claas: Use initializable type here. */
		template.setAttribute("resultType", resultType.toString());
		template.setAttribute("resultVar", resultVar);

		sourceGenericType = sourceType.getGenericType().toString();
		template.setAttribute("sourceGenericType", sourceGenericType);

		result.addCode(template.toString());
		result.setResultExp(resultVar);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIterateExp(IterateExp)" + " - end - return value="
					+ result);
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
	public ITransformedCode caseIteratorExp(IteratorExp anIteratorExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIteratorExp(IteratorExp) - start");
		}
		// no else.

		ITransformedCode result;
		ITransformedType resultType;

		ITemplate template;

		OclExpression sourceExp;
		ITransformedCode sourceCode;
		ITransformedType sourceType;

		OclExpression bodyExp;
		ITransformedCode bodyCode;
		ITransformedType bodyType;

		/* Needed for iterators of type 'sortedBy'. */
		ITransformedCode bodyCode2;

		List<Variable> itVarList;
		Variable itVariable;

		String itName;
		String itVarName;
		String itVarName2;
		String resultVarName;

		result = new TransformedCodeImpl();
		resultType = this.transformInitializableType(anIteratorExp.getType());

		/* Get the name of the iterator */
		itName = anIteratorExp.getName();

		/* Get the list with all iterator variables. */
		itVarList = anIteratorExp.getIterator();

		/* Get unique name iterateResultName and iterateVarName. */
		itVarName = this.myCodeTransEnv.getNewIteratorVarName();
		itVarName2 = null;
		resultVarName = this.myCodeTransEnv.getNewResultVarName();

		/* Get the first iterator variable. */
		itVariable = itVarList.remove(0);
		itVarName = itVariable.getName();

		/* Transform code for source of the iteratorExp. */
		sourceExp = anIteratorExp.getSource();
		sourceCode = this.doSwitch((EObject) sourceExp);
		sourceType = this.transformType(sourceExp.getType());

		bodyExp = anIteratorExp.getBody();

		/*
		 * If the Expression has more than one iterator variable. Create an inner
		 * IterateExp without the first Iterator Variable.
		 */
		if (itVarList.size() > 0) {
			/* the first iterator has been already removed. */
			bodyExp = anIteratorExp;
			/*
			 * The Expression will be transformed inside the transformation of the
			 * first iterator Variable later on.
			 */
		}
		// no else.

		/* Transform code for bodyExp of the iteratorExp. */
		bodyCode = this.doSwitch((EObject) bodyExp);
		bodyType = this.transformType(bodyExp.getType());

		bodyCode2 = null;

		/*
		 * For the iterator sorted by, create a second body expression with a
		 * different iterator variable.
		 */
		if (itName.equals("sortedBy")) {

			itVarName2 = this.myCodeTransEnv.getNewIteratorVarName();
			itVariable.setName(itVarName2);

			bodyCode2 = this.doSwitch((EObject) bodyExp);
		}

		/* Add the iterator Variable to the model again. */
		itVarList.add(0, itVariable);

		/* Begin code transformation of IteratorExp. */
		template = this.myTemplateGroup.getTemplate("iteratorExp_" + itName);

		if (itName.equals("any") || itName.equals("exists")
				|| itName.equals("forAll") || itName.equals("one")) {

			/* Set iterator specific template attributes. */
			template.setAttribute("itType", sourceType.getGenericType().toString());
		}

		else if (itName.equals("isUnique")) {

			/* Set iterator specific template attributes. */
			template.setAttribute("itType", sourceType.getGenericType().toString());
			template.setAttribute("collectionVar",
					this.myCodeTransEnv.getNewCollectionVarName());
		}

		else if (itName.equals("collect")) {

			String addOp;

			/*
			 * Collect does not add collections to collections, thus the add operation
			 * must be computed.
			 */
			if (bodyType.isGenericType()) {
				addOp =
						this.myTemplateGroup.getTemplate("addAllOperationName").toString();
			}
			else {
				addOp = this.myTemplateGroup.getTemplate("addOperationName").toString();
			}

			/* Set iterator specific template attributes. */
			template.setAttribute("itType", sourceType.getGenericType().toString());
			template.setAttribute("resultType", resultType.toString());
			template.setAttribute("addOp", addOp);
		}

		else if (itName.equals("collectNested") || itName.equals("reject")
				|| itName.equals("select")) {

			/* Set iterator specific template attributes. */
			template.setAttribute("itType", sourceType.getGenericType().toString());
			template.setAttribute("resultType", resultType.toString());
		}

		else if (itName.equals("sortedBy")) {

			/* Set iterator specific template attributes. */
			template.setAttribute("itType", sourceType.getGenericType().toString());
			template.setAttribute("comparatorName",
					this.myCodeTransEnv.getNewComparatorName());
			template.setAttribute("compareResult",
					this.myCodeTransEnv.getNewResultVarName());
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
			LOGGER.debug("caseIteratorExp(IteratorExp)" + " - end - return value="
					+ result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseLetExp(tudresden.ocl20.pivot.essentialocl.expressions.LetExp)
	 */
	public ITransformedCode caseLetExp(LetExp aLetExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseLetExp(LetExp) - start");
		}
		// no else.

		ITransformedCode result;
		ITemplate template;

		/* The Variable defined by the LetExp. */
		Variable aVar;
		Type aVarsType;

		/* The OclExpression to initialize the Variable. */
		OclExpression initExp;

		/* The OclExpression in which the LetExp is valid. */
		OclExpression inExp;
		ITransformedCode inCode;

		aVar = aLetExp.getVariable();
		aVarsType = aVar.getType();

		initExp = aVar.getInitExpression();
		inExp = aLetExp.getIn();

		/* Generate the code for the inExp. */
		inCode = doSwitch((EObject) inExp);

		template = this.myTemplateGroup.getTemplate("letExp");
		template.setAttribute("varType", this.transformType(aVarsType)
				.getTypeName());
		template.setAttribute("varName", aVar.getName());
		template.setAttribute("inCode", inCode.getCode());

		/* Generate the code for the initExp. */
		if (initExp != null) {
			ITransformedCode initCode;

			initCode = doSwitch((EObject) initExp);

			template.setAttribute("initCode", initCode.getCode());
			template.setAttribute("initExp", initCode.getResultExp());
		}
		// no else.

		result = new TransformedCodeImpl();
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
	 * 
	 * @param anOperationCallExp
	 *          The {@link OperationCallExp} the code shall be transformed for.
	 * @param anOperation
	 *          The {@link Operation} which shall be transformed.
	 * @return The {@link ITransformedCode} for the {@link Operation} which shall
	 *         be transformed.
	 */
	public ITransformedCode caseOperationCallExp(
			OperationCallExp anOperationCallExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseOperationCallExp(OperationCallExp) - start");
		}
		// no else.

		ITransformedCode result;
		String resultExp;

		ITemplate template;

		OclExpression sourceExp;
		Type sourceType;
		ITransformedCode sourceCode;

		String operationName;

		result = new TransformedCodeImpl();

		/* Transform Code for the source of the operation call. */
		sourceExp = anOperationCallExp.getSource();

		Operation referredOperation;
		referredOperation = anOperationCallExp.getReferredOperation();

		List<Type> parameterTypes;
		parameterTypes = new ArrayList<Type>();

		/* Operation can be null (@pre Operation). */
		if (referredOperation != null) {
			for (Parameter parameter : referredOperation.getInputParameter()) {
				parameterTypes.add(parameter.getType());
			}
			// end for.
		}
		// no else.

		OclLibrary oclLibrary;
		oclLibrary = EssentialOclPlugin.getOclLibraryProvider().getOclLibrary();

		/* Compute the source expression. */
		sourceType = sourceExp.getType();
		sourceCode = this.doSwitch((EObject) sourceExp);
		result.addCode(sourceCode.getCode());

		/* Get the operation name and handle the special case @pre. */
		if (anOperationCallExp.getName().equals("atPre")) {
			operationName = "atPre";
		}

		else {
			Operation anOperation;

			anOperation = anOperationCallExp.getReferredOperation();
			operationName = anOperation.getName();
		}

		resultExp = null;
		template = null;

		/*
		 * Search for the template of the operation. Start with concrete types and
		 * continue with more and more abstract types.
		 */
		if (sourceType != null) {

			/* Operations on BagType. */
			if (template == null && sourceType instanceof BagType) {

				/*
				 * Do not use the source type here. Otherwise the operation check will
				 * fail.
				 */
				BagType bagType;
				bagType = oclLibrary.getBagType(oclLibrary.getOclAny());

				/*
				 * Check if the given operation is defined and probably load its
				 * template.
				 */
				Operation operation;
				operation = bagType.lookupOperation(operationName, parameterTypes);

				if (operation != null && operation.getOwningType().equals(bagType)) {

					/* Probably rename the operation. */
					if (renamedOperationNames.containsKey(operationName)) {
						operationName = renamedOperationNames.get(operationName);
					}
					// no else.

					template =
							this.myTemplateGroup
									.getTemplate(operationName + "OperationOnBag");
				}
				// no else.
			}
			// no else.

			/* Operations on OrderedSetType. */
			if (template == null && sourceType instanceof OrderedSetType) {

				/*
				 * Do not use the source type here. Otherwise the operation check will
				 * fail.
				 */
				OrderedSetType orderedSetType;
				orderedSetType = oclLibrary.getOrderedSetType(oclLibrary.getOclAny());

				/*
				 * Check if the given operation is defined and probably load its
				 * template.
				 */
				Operation operation;
				operation =
						orderedSetType.lookupOperation(operationName, parameterTypes);

				if (operation != null
						&& operation.getOwningType().equals(orderedSetType)) {

					/* Probably rename the operation. */
					if (renamedOperationNames.containsKey(operationName)) {
						operationName = renamedOperationNames.get(operationName);
					}
					// no else.

					template =
							this.myTemplateGroup.getTemplate(operationName
									+ "OperationOnOrderedSet");
				}
				// no else.
			}
			// no else.

			/* Operations on SequenceType. */
			if (template == null && sourceType instanceof SequenceType) {

				/*
				 * Do not use the source type here. Otherwise the operation check will
				 * fail.
				 */
				SequenceType sequenceType;
				sequenceType = oclLibrary.getSequenceType(oclLibrary.getOclAny());

				/*
				 * Check if the given operation is defined and probably load its
				 * template.
				 */
				Operation operation;
				operation = sequenceType.lookupOperation(operationName, parameterTypes);

				if (operation != null && operation.getOwningType().equals(sequenceType)) {

					/* Probably rename the operation. */
					if (renamedOperationNames.containsKey(operationName)) {
						operationName = renamedOperationNames.get(operationName);
					}
					// no else.

					template =
							this.myTemplateGroup.getTemplate(operationName
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
						template =
								this.myTemplateGroup.getTemplate("unionOperationWithBagOnSet");
					}

					else if (parameterTypes.size() == 1
							&& parameterTypes.get(0) instanceof SetType) {
						template =
								this.myTemplateGroup.getTemplate("unionOperationWithSetOnSet");
					}
				}
				// no else.

				if (template == null) {
					/*
					 * Do not use the source type here. Otherwise the operation check will
					 * fail.
					 */
					SetType setType;
					setType = oclLibrary.getSetType(oclLibrary.getOclAny());

					/*
					 * Check if the given operation is defined and probably load its
					 * template.
					 */
					Operation operation;
					operation = setType.lookupOperation(operationName, parameterTypes);

					if (operation != null && operation.getOwningType().equals(setType)) {

						/* Probably rename the operation. */
						if (renamedOperationNames.containsKey(operationName)) {
							operationName = renamedOperationNames.get(operationName);
						}
						// no else.

						template =
								this.myTemplateGroup.getTemplate(operationName
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
				 * Do not use the source type here. Otherwise the operation check will
				 * fail.
				 */
				CollectionType collectionType;
				collectionType = oclLibrary.getCollectionType(oclLibrary.getOclAny());

				/*
				 * Check if the given operation is defined and probably load its
				 * template.
				 */
				Operation operation;
				operation =
						collectionType.lookupOperation(operationName, parameterTypes);

				if (operation != null
						&& operation.getOwningType().equals(collectionType)) {

					/* Probably rename the operation. */
					if (renamedOperationNames.containsKey(operationName)) {
						operationName = renamedOperationNames.get(operationName);
					}
					// no else.

					template =
							this.myTemplateGroup.getTemplate(operationName
									+ "OperationOnCollection");
				}
				// no else.

				/*
				 * Sum operation requires special argument because result must be cast
				 * to result type.
				 */
				if (template != null && operationName.equals("sum")) {
					String resultType;
					resultType =
							this.transformType(anOperationCallExp.getType()).toString();

					template.setAttribute("resultType", resultType);

					/*
					 * Set additional parameter to convert from number to result type.
					 */
					template.setAttribute("typeConversion", this.myTemplateGroup
							.getTemplate("numberConversionTo" + resultType).toString());
				}
				// no else.
			}
			// no else.

			/*
			 * Flatten operation requires special argument because result must be cast
			 * to result type.
			 */
			if (template != null && operationName.equals("flatten")) {
				template.setAttribute("resultType",
						this.transformType(anOperationCallExp.getType()).toString());
			}
			// no else.

			/* FIXME Probably rename the operation. */
			if (renamedOperationNames.containsKey(operationName)) {
				operationName = renamedOperationNames.get(operationName);
			}
			// no else.

			if (template == null && sourceType instanceof Type) {

				if (operationName.equals("oclIsNew")) {
					template = this.myTemplateGroup.getTemplate("oclIsNewOperation");
					this.myCodeTransEnv.addIsNewClass(this.transformType(
							sourceExp.getType()).toString());
				}

				else if (operationName.equals("oclIsUndefined")) {
					template =
							this.myTemplateGroup.getTemplate("oclIsUndefinedOperation");
				}

				else if (operationName.equals("oclIsInvalid")) {
					String resultVar;
					resultVar = this.myCodeTransEnv.getNewResultVarName();

					template = this.myTemplateGroup.getTemplate("oclIsInvalidOperation");
					template.setAttribute("sourceExp", sourceCode.getResultExp());
					template.setAttribute("resultVar", resultVar);

					if (sourceExp.getType() != null) {
						template.setAttribute("sourceHasType", "true");
					}
					// no else.

					result.addCode(template.toString());
					resultExp = resultVar;
				}

				else if (operationName.equals("allInstances")) {
					String instanceType;

					instanceType = sourceCode.getResultExp();

					template = this.myTemplateGroup.getTemplate("allInstancesOperation");
					template.setAttribute("typeName", instanceType);
					this.myCodeTransEnv.addAllInstancesClass(instanceType);
				}

				else if (operationName.equals("equals")) {
					template = this.myTemplateGroup.getTemplate("equalsOperation");
				}

				else if (operationName.equals("notEquals")) {
					template = this.myTemplateGroup.getTemplate("notEqualsOperation");
				}

				else if (operationName.equals("oclAsType")) {
					template = this.myTemplateGroup.getTemplate("oclAsTypeOperation");
				}

				else if (operationName.equals("oclIsTypeOf")) {
					template = this.myTemplateGroup.getTemplate("oclIsTypeOfOperation");
				}

				else if (operationName.equals("oclIsKindOf")) {
					template = this.myTemplateGroup.getTemplate("oclIsKindOfOperation");
				}

				else if (operationName.equals("oclType")) {
					template = this.myTemplateGroup.getTemplate("oclTypeOperation");
				}

				else if (operationName.equals("atPre")) {
					/* Store the value in a variable and return its name. */
					String atPreVar;

					/* The variable is initialized later during instrumentation. */
					atPreVar = this.myCodeTransEnv.addAtPreValue(sourceCode, sourceType);

					resultExp = atPreVar;
				}
			}

			/* Operations on PrimitiveTypes. */
			if (sourceType instanceof PrimitiveType) {

				PrimitiveType primitiveSourceType;
				PrimitiveTypeKind sourceTypeKind;

				primitiveSourceType = (PrimitiveType) sourceType;
				sourceTypeKind = primitiveSourceType.getKind();

				/* Operations on Boolean. */
				if (sourceTypeKind.compareTo(PrimitiveTypeKind.BOOLEAN) == 0) {

					if (operationName.equals("not")) {
						template = this.myTemplateGroup.getTemplate("notOperation");
					}

					else if (operationName.equals("or")) {
						template = this.myTemplateGroup.getTemplate("orOperation");
					}

					else if (operationName.equals("xor")) {
						template = this.myTemplateGroup.getTemplate("xorOperation");
					}

					else if (operationName.equals("and")) {
						template = this.myTemplateGroup.getTemplate("andOperation");
					}

					else if (operationName.equals("implies")) {
						template = this.myTemplateGroup.getTemplate("impliesOperation");
					}
					// no else.

				}
				// no else.

				/* Operations on String. */
				if (sourceTypeKind.compareTo(PrimitiveTypeKind.STRING) == 0) {

					if (operationName.equals("+")) {
						template =
								this.myTemplateGroup.getTemplate("plusOnStringOperation");
					}

					else if (operationName.equals("at")) {
						template = this.myTemplateGroup.getTemplate("atOnStringOperation");
					}

					else if (operationName.equals("characters")) {
						String resultVar;
						resultVar = this.myCodeTransEnv.getNewResultVarName();

						template = this.myTemplateGroup.getTemplate("charactersOperation");

						template.setAttribute("sourceExp", sourceCode.getResultExp());
						template.setAttribute("resultVar", resultVar);
						template.setAttribute("elementName",
								this.myCodeTransEnv.getNewIteratorVarName());

						result.addCode(template.toString());
						resultExp = resultVar;
					}

					else if (operationName.equals("concat")) {
						template = this.myTemplateGroup.getTemplate("concatOperation");
					}

					else if (operationName.equals("indexOf")) {
						template =
								this.myTemplateGroup.getTemplate("indexOfOnStringOperation");
					}

					else if (operationName.equals("size")) {
						template =
								this.myTemplateGroup.getTemplate("sizeOnStringOperation");
					}

					else if (operationName.equals("toBoolean")) {
						template = this.myTemplateGroup.getTemplate("toBooleanOperation");
					}

					else if (operationName.equals("toInteger")) {
						template = this.myTemplateGroup.getTemplate("toIntegerOperation");
					}

					else if (operationName.equals("toReal")) {
						template = this.myTemplateGroup.getTemplate("toRealOperation");
					}

					else if (operationName.equals("substring")) {
						template = this.myTemplateGroup.getTemplate("substringOperation");
					}
					// no else.
				}
				// no else.

				/* Operations on Boolean, Real or Integer. */
				if (sourceTypeKind.equals(PrimitiveTypeKind.BOOLEAN)
						|| sourceTypeKind.equals(PrimitiveTypeKind.INTEGER)
						|| sourceTypeKind.equals(PrimitiveTypeKind.REAL)) {
					if (operationName.equals("equals")) {
						template =
								this.myTemplateGroup
										.getTemplate("equalsOperationOnPrimitiveType");
					}

					else if (operationName.equals("notEquals")) {
						template =
								this.myTemplateGroup
										.getTemplate("notEqualsOperationOnPrimitiveType");
					}
					// no else.
				}
				// no else.

				/* Operations on Real or Integer. */
				if (sourceTypeKind.compareTo(PrimitiveTypeKind.REAL) == 0
						|| sourceTypeKind.compareTo(PrimitiveTypeKind.INTEGER) == 0) {

					if (operationName.equals("abs")) {
						template = this.myTemplateGroup.getTemplate("absOperation");
					}

					else if (operationName.equals("round")) {
						template = this.myTemplateGroup.getTemplate("roundOperation");
					}

					else if (operationName.equals("floor")) {
						template = this.myTemplateGroup.getTemplate("floorOperation");
					}

					else if (operationName.equals("minus")) {
						/* Decide between binary minus and ... */
						if (anOperationCallExp.getArgument().size() > 0) {
							template = this.myTemplateGroup.getTemplate("minusOperation");
						}
						/* ... unary negative. */
						else {
							template = this.myTemplateGroup.getTemplate("negativeOperation");
						}
					}

					else if (operationName.equals("+")) {
						template = this.myTemplateGroup.getTemplate("plusOperation");
					}

					else if (operationName.equals("*")) {
						template = this.myTemplateGroup.getTemplate("multiplyOperation");
					}

					else if (operationName.equals("max")) {
						template = this.myTemplateGroup.getTemplate("maxOperation");
					}

					else if (operationName.equals("min")) {
						template = this.myTemplateGroup.getTemplate("minOperation");
					}

					else if (operationName.equals("<")) {
						template = this.myTemplateGroup.getTemplate("lesserOperation");
					}

					else if (operationName.equals(">")) {
						template = this.myTemplateGroup.getTemplate("greaterOperation");
					}

					else if (operationName.equals("<=")) {
						template =
								this.myTemplateGroup.getTemplate("lesserEqualsOperation");
					}

					else if (operationName.equals(">=")) {
						template =
								this.myTemplateGroup.getTemplate("greaterEqualsOperation");
					}
					// no else.
				}
				// no else.

				/* Operations on Real */
				if (sourceTypeKind.compareTo(PrimitiveTypeKind.REAL) == 0) {
					if (operationName.equals("/")) {
						template = this.myTemplateGroup.getTemplate("divisionOperation");
					}
					// no else.
				}
				// no else.

				/* Operations on Real */
				if (sourceTypeKind.compareTo(PrimitiveTypeKind.INTEGER) == 0) {
					if (operationName.equals("/")) {
						template =
								this.myTemplateGroup.getTemplate("divisionOnIntegerOperation");
					}

					else if (operationName.equals("div")) {
						template = this.myTemplateGroup.getTemplate("divOperation");
					}

					else if (operationName.equals("mod")) {
						template = this.myTemplateGroup.getTemplate("modOperation");
					}
					// no else.
				}
				// no else.

			}

		}

		/* Code for operations on non OclTypes. */
		if (template == null) {
			template = this.myTemplateGroup.getTemplate("umlOperation");
			template.setAttribute("operationName", operationName);
		}

		/* Probably set more attributes of the template. */
		if (template != null && resultExp == null) {

			List<OclExpression> argumentList;

			template.setAttribute("sourceExp", sourceCode.getResultExp());

			argumentList = anOperationCallExp.getArgument();

			/* Probably set code for arguments of the operation. */
			for (OclExpression anArgument : argumentList) {

				ITransformedCode argCode;

				argCode = this.doSwitch((EObject) anArgument);

				result.addCode(argCode.getCode());

				template.setAttribute("argsExp", argCode.getResultExp());
			}

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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #casePropertyCallExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp)
	 */
	public ITransformedCode casePropertyCallExp(PropertyCallExp aPropertyCallExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("casePropertyCallExp(PropertyCallExp) - start");
		}
		// no else.

		ITransformedCode result;
		ITemplate template;

		OclExpression sourceExp;
		ITransformedCode sourceCode;
		Property referredProperty;

		String refPropertyName;

		result = new TransformedCodeImpl();

		/* Get parameters for property call code. */
		referredProperty = aPropertyCallExp.getReferredProperty();
		refPropertyName = referredProperty.getName();

		/* Transform the code for the sourceExp. */
		sourceExp = aPropertyCallExp.getSource();
		sourceCode = doSwitch((EObject) sourceExp);

		/* Add source code to result. */
		result.addCode(sourceCode.getCode());

		/* Transform code for property call. */

		/* The property calls on tuples must be adapted. */
		if (sourceExp != null && sourceExp.getType() instanceof TupleType) {
			template = this.myTemplateGroup.getTemplate("propertyCallExpOnTuple");
		}

		else {
			template = this.myTemplateGroup.getTemplate("propertyCallExp");
		}

		/* Set template attributes. */
		template.setAttribute("sourceExp", sourceCode.getResultExp());
		template.setAttribute("propertyName", refPropertyName);

		result.setResultExp(template.toString());

		/*
		 * Store the called properties in the environment (Probably needed for
		 * invariant instrumentation). Only store properties, which are properties
		 * of the constrained element. Thus their source must be a self variable.
		 */
		if (sourceExp instanceof VariableExp) {

			VariableExp aVariableExp;
			Variable aVariable;

			aVariableExp = (VariableExp) sourceExp;

			aVariable = aVariableExp.getReferredVariable();

			if (aVariable.getName().equals("self")) {
				this.myCodeTransEnv.addCalledProperty(refPropertyName);
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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseRealLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp)
	 */
	public ITransformedCode caseRealLiteralExp(RealLiteralExp aRealLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseRealLiteralExp(RealLiteralExp) - start");
		}
		// no else.

		ITransformedCode result;

		ITemplate template;
		String literalValue;

		template = this.myTemplateGroup.getTemplate("literalExp");

		literalValue = "";
		literalValue += aRealLiteralExp.getRealSymbol();

		template.setAttribute("type", this.transformType(aRealLiteralExp.getType())
				.toString());
		template.setAttribute("value", literalValue);

		result = new TransformedCodeImpl();
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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseStringLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp)
	 */
	public ITransformedCode caseStringLiteralExp(
			StringLiteralExp aStringLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseStringLiteralExp(StringLiteralExp) - start");
		}
		// no else.

		ITransformedCode result;

		ITemplate template;
		String literalValue;

		template = this.myTemplateGroup.getTemplate("stringLiteralExp");
		literalValue = "";
		literalValue += aStringLiteralExp.getStringSymbol();
		template.setAttribute("value", literalValue);

		result = new TransformedCodeImpl();
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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseTupleLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp)
	 */
	public ITransformedCode caseTupleLiteralExp(TupleLiteralExp aTupleLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseTupleLiteralExp(TupleLiteralExp) - start");
		}
		// no else.

		ITransformedCode result;

		ITemplate template;

		List<TupleLiteralPart> tupleElements;
		String tupleName;

		result = new TransformedCodeImpl();

		tupleName = aTupleLiteralExp.getName();

		/* If the tuple has no name, compute a new name. */
		if (tupleName.equals("")) {
			tupleName = this.myCodeTransEnv.getNewTupleVarName();
			aTupleLiteralExp.setName(tupleName);
		}
		// no else.

		/* Get template for tupleLiteralExp. */
		template = this.myTemplateGroup.getTemplate("tupleLiteralExp");
		template.setAttribute("tupleName", tupleName);

		tupleElements = aTupleLiteralExp.getPart();

		/* Transform Code for all initial elements. */
		for (TupleLiteralPart aTupleElement : tupleElements) {

			ITransformedCode elementCode;

			elementCode = this.doSwitch((EObject) aTupleElement.getValue());

			template.setAttribute("argCodes", elementCode.getCode());
			template.setAttribute("argExps", elementCode.getResultExp());
			template.setAttribute("argNames", aTupleElement.getProperty().getName());
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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseTypeLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp)
	 */
	public ITransformedCode caseTypeLiteralExp(TypeLiteralExp aTypeLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseTypeLiteralExp(TypeLiteralExp) - start");
		}
		// no else.

		ITransformedCode result;
		Type referredType;

		result = new TransformedCodeImpl();
		referredType = aTypeLiteralExp.getReferredType();

		ITemplate template = this.myTemplateGroup.getTemplate("typeLiteralExp");
		template.setAttribute("type", this.transformType(referredType).toString());

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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseUndefinedLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp)
	 */
	public ITransformedCode caseUndefinedLiteralExp(
			UndefinedLiteralExp anUndefinedLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseUndefinedLiteralExp(UndefinedLiteralExp) - start");
		}
		// no else.

		ITransformedCode result;
		ITemplate template;

		template = this.myTemplateGroup.getTemplate("undefinedLiteralExp");

		result = new TransformedCodeImpl();
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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
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

		ITemplate template;
		String literalValue;

		template = this.myTemplateGroup.getTemplate("literalExp");

		literalValue = "";
		literalValue += anUnlimitedNaturalExp.getSymbol();

		template.setAttribute("type",
				this.transformType(anUnlimitedNaturalExp.getType()).toString());
		template.setAttribute("value", literalValue);

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
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseVariableExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.VariableExp)
	 */
	public ITransformedCode caseVariableExp(VariableExp aVariableExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseVariableExp(VariableExp) - start");
		}
		// no else.

		ITransformedCode result;

		ITemplate template;
		Variable aVariable;

		aVariable = aVariableExp.getReferredVariable();
		result = new TransformedCodeImpl();

		/*
		 * The keyword 'self' has to be transformed into the name of the variable
		 * which references the constrained class
		 */
		if (aVariable.getName().equals("self")) {

			template = this.myTemplateGroup.getTemplate("selfVariable");
			result.setResultExp(template.toString());
		}

		/*
		 * The special OCL variable 'result' has to be transformed in their name in
		 * Java.
		 */
		else if (aVariable.getName().equals("result")) {

			template = this.myTemplateGroup.getTemplate("resultVariable");
			result.setResultExp(template.toString());
		}

		/* All other VariableExp lead to the name of the Variable. */
		else {
			result.setResultExp(aVariable.getName());
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseVariableExp(VariableExp)" + " - end - return value="
					+ result);
		}
		// no else.

		return result;
	}

	/**
	 * @param anAnyType
	 *          The {@link AnyType} for which code shall be returned.
	 * @return The code for a given {@link AnyType}.
	 */
	private ITransformedType transformAnyType(AnyType anAnyType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformAnyType(AnyType) - start");
		}
		// no else.

		ITransformedType result;
		ITemplate template;

		template = this.myTemplateGroup.getTemplate("anyType");
		result = new TransformedTypeImpl(template.toString());

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformAnyType(AnyType)" + "- end - return value="
					+ result);
		}
		// no else.

		return result;
	}

	/**
	 * @param aCollectionType
	 *          The {@link CollectionType} for which code shall be returned.
	 * @return The code for a given {@link CollectionType}.
	 */
	private ITransformedType transformCollectionType(
			CollectionType aCollectionType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformCollectionType(CollectionType) - start");
		}
		// no else.

		ITransformedType result;
		ITemplate template;

		Type elementType;

		result = null;
		template = null;

		if (aCollectionType instanceof BagType) {
			template = this.myTemplateGroup.getTemplate("bagType");
		}

		else if (aCollectionType instanceof OrderedSetType) {
			template = this.myTemplateGroup.getTemplate("orderedSetType");
		}

		else if (aCollectionType instanceof SequenceType) {
			template = this.myTemplateGroup.getTemplate("sequenceType");
		}

		else if (aCollectionType instanceof SetType) {
			template = this.myTemplateGroup.getTemplate("setType");
		}

		else {
			template = this.myTemplateGroup.getTemplate("collectionType");
		}
		// no else.

		if (template != null) {
			result = new TransformedTypeImpl(template.toString());
		}
		// no else.

		/* Get the generic type of the Collection. */
		elementType = aCollectionType.getElementType();

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
	 * @param anEnumeration
	 *          The {@link Enumeration} for which code shall be returned.
	 * @return The code for a given {@link Enumeration}.
	 */
	private ITransformedType transformEnumerationType(Enumeration anEnumeration) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformEnumerationType(Enumeration) - start");
		}
		// no else.

		ITransformedType result;
		ITemplate template;

		template = this.myTemplateGroup.getTemplate("enumerationType");
		result = new TransformedTypeImpl(template.toString());

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformEnumerationType(Enumeration)"
					+ "- end - return value=" + result);
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
	 *          The {@link CollectionType} for which code shall be returned.
	 * @return The code for a given {@link CollectionType}.
	 */
	private ITransformedType transformInitializableCollectionType(
			CollectionType aCollectionType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("transformInitializableCollectionType(CollectionType) - start");
		}
		// no else.

		ITransformedType result;
		ITemplate template;

		Type elementType;

		result = null;
		template = null;

		if (aCollectionType instanceof BagType) {
			template = this.myTemplateGroup.getTemplate("bagTypeInitialization");
		}

		else if (aCollectionType instanceof OrderedSetType) {
			template =
					this.myTemplateGroup.getTemplate("orderedSetTypeInitialization");
		}

		else if (aCollectionType instanceof SequenceType) {
			template = this.myTemplateGroup.getTemplate("sequenceTypeInitialization");
		}

		else if (aCollectionType instanceof SetType) {
			template = this.myTemplateGroup.getTemplate("setTypeInitialization");
		}

		else {
			template =
					this.myTemplateGroup.getTemplate("collectionTypeInitialization");
		}
		// no else.

		if (template != null) {
			result = new TransformedTypeImpl(template.toString());
		}
		// no else.

		/* Get the generic type of the Collection. */
		elementType = aCollectionType.getElementType();

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
	 *          The {@link Type} for which code shall be returned.
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
			AnyType anAnyType;

			anAnyType = (AnyType) aType;

			result = this.transformAnyType(anAnyType);
		}

		else if (aType instanceof CollectionType) {
			CollectionType aCollectionType;

			aCollectionType = (CollectionType) aType;

			result = this.transformInitializableCollectionType(aCollectionType);
		}

		else if (aType instanceof Enumeration) {
			Enumeration anEnumeration;

			anEnumeration = (Enumeration) aType;

			result = this.transformEnumerationType(anEnumeration);
		}

		else if (aType instanceof InvalidType) {
			/* Unreachable code. Invalid expressions are caught by the parser. */
			result = null;
		}

		else if (aType instanceof PrimitiveType) {
			PrimitiveType aPrimitiveType;

			aPrimitiveType = (PrimitiveType) aType;

			result = this.transformPrimitiveType(aPrimitiveType);
		}

		else if (aType instanceof TupleType) {
			TupleType aTupleType;

			aTupleType = (TupleType) aType;

			result = this.transformTupleType(aTupleType);
		}

		else if (aType instanceof TypeType) {
			TypeType aTypeType;

			aTypeType = (TypeType) aType;

			result = this.transformTypeType(aTypeType);
		}

		else if (aType instanceof VoidType) {
			VoidType aVoidType;

			aVoidType = (VoidType) aType;

			result = this.transformVoidType(aVoidType);
		}

		else {
			/* For other types return their canonical name as type. */
			String typeName;

			typeName = this.getCanonicalName(aType);

			result = new TransformedTypeImpl(typeName);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInitializableType(Type)" + "- end - return value="
					+ result);
		}
		// no else.

		return result;
	}

	/**
	 * @param invalidType
	 *          The {@link InvalidType} for which code shall be returned.
	 * @return The code for a given {@link InvalidType}.
	 */
	private ITransformedType transformInvalidType(InvalidType invalidType) {
	
		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInvalidType(InvalidType) - start");
		}
		// no else.
	
		ITransformedType result;
		ITemplate template;
	
		template = this.myTemplateGroup.getTemplate("invalidType");
		result = new TransformedTypeImpl(template.toString());
	
		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformInvalidType(InvalidType)" + "- end - return value="
					+ result);
		}
		// no else.
	
		return result;
	}

	/**
	 * @param aPrimitiveType
	 *          The {@link PrimitiveType} for which code shall be returned.
	 * @return The code for a given {@link PrimitiveType}.
	 */
	private ITransformedType transformPrimitiveType(PrimitiveType aPrimitiveType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformPrimitiveType(PrimitiveType) - start");
		}
		// no else.

		TransformedTypeImpl result;
		ITemplate template;

		PrimitiveTypeKind aKind;
		int aKindValue;

		result = null;
		template = null;

		aKind = aPrimitiveType.getKind();
		aKindValue = aKind.getValue();

		if (aKindValue == PrimitiveTypeKind.BOOLEAN_VALUE) {
			template = this.myTemplateGroup.getTemplate("booleanType");
		}

		else if (aKindValue == PrimitiveTypeKind.INTEGER_VALUE) {
			template = this.myTemplateGroup.getTemplate("integerType");
		}

		else if (aKindValue == PrimitiveTypeKind.REAL_VALUE) {
			template = this.myTemplateGroup.getTemplate("realType");
		}

		else if (aKindValue == PrimitiveTypeKind.STRING_VALUE) {
			template = this.myTemplateGroup.getTemplate("stringType");
		}

		else if (aKindValue == PrimitiveTypeKind.VOID_VALUE) {
			template = this.myTemplateGroup.getTemplate("voidType");
		}

		else if (aKindValue == PrimitiveTypeKind.UNKNOWN_VALUE) {
			template = this.myTemplateGroup.getTemplate("unknownType");
		}
		// no else.

		if (template != null) {
			result = new TransformedTypeImpl(template.toString());
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformPrimitiveType(PrimitiveType)"
					+ "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * @param aTupleType
	 *          The {@link TupleType} for which code shall be returned.
	 * @return The code for a given {@link TupleType}.
	 */
	private ITransformedType transformTupleType(TupleType aTupleType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformTupleType(TupleType) - start");
		}
		// no else.

		ITransformedType result;
		ITemplate template;

		template = this.myTemplateGroup.getTemplate("tupleType");
		result = new TransformedTypeImpl(template.toString());

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformTupleType(TupleType)" + "- end - return value="
					+ result);
		}
		// no else.

		return result;
	}

	/**
	 * @param aType
	 *          The {@link Type} for which code shall be returned.
	 * @return The code for a given {@link Type}.
	 */
	private ITransformedType transformType(Type aType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformType(Type) - start");
		}
		// no else.

		ITransformedType result;

		if (aType instanceof AnyType) {
			AnyType anAnyType;

			anAnyType = (AnyType) aType;

			result = this.transformAnyType(anAnyType);
		}

		else if (aType instanceof CollectionType) {
			CollectionType aCollectionType;

			aCollectionType = (CollectionType) aType;

			result = this.transformCollectionType(aCollectionType);
		}

		else if (aType instanceof Enumeration) {
			Enumeration anEnumeration;

			anEnumeration = (Enumeration) aType;

			result = this.transformEnumerationType(anEnumeration);
		}

		else if (aType instanceof InvalidType) {
			InvalidType invalidType = (InvalidType) aType;
			result = this.transformInvalidType(invalidType);
		}

		else if (aType instanceof PrimitiveType) {
			PrimitiveType aPrimitiveType;

			aPrimitiveType = (PrimitiveType) aType;

			result = this.transformPrimitiveType(aPrimitiveType);
		}

		else if (aType instanceof TupleType) {
			TupleType aTupleType;

			aTupleType = (TupleType) aType;

			result = this.transformTupleType(aTupleType);
		}

		else if (aType instanceof TypeType) {
			TypeType aTypeType;

			aTypeType = (TypeType) aType;

			result = this.transformTypeType(aTypeType);
		}

		else if (aType instanceof VoidType) {
			VoidType aVoidType;

			aVoidType = (VoidType) aType;

			result = this.transformVoidType(aVoidType);
		}

		else {
			/* For other types return their canonical name as type. */
			String typeName;

			typeName = this.getCanonicalName(aType);

			result = new TransformedTypeImpl(typeName);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformType(Type)" + "- end - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * @param aTypeType
	 *          The {@link TypeType} for which code shall be returned.
	 * @return The code for a given {@link TypeType}.
	 */
	private ITransformedType transformTypeType(TypeType aTypeType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformTypeType(TypeType) - start");
		}
		// no else.

		ITransformedType result;
		ITemplate template;

		template = this.myTemplateGroup.getTemplate("typeType");
		result = new TransformedTypeImpl(template.toString());

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformTypeType(TypeType)" + "- end - return value="
					+ result);
		}
		// no else.

		return result;
	}

	/**
	 * @param aVoidType
	 *          The {@link VoidType} for which code shall be returned.
	 * @return The code for a given {@link VoidType}.
	 */
	private ITransformedType transformVoidType(VoidType aVoidType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformVoidType(VoidType) - start");
		}
		// no else.

		ITransformedType result;
		ITemplate template;

		template = this.myTemplateGroup.getTemplate("voidType");
		result = new TransformedTypeImpl(template.toString());

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformVoidType(VoidType)" + "- end - return value="
					+ result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Generates an Aspect for the instrumentation of body constraints.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForBody(Constraint aConstraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForBody(Constraint) - start");
		}
		// no else.

		String result;

		ITemplate aspectTemplate;
		ITemplate adviceTemplate;

		/* A List of Properties whose body-Expressions shall be instrumented. */
		List<ConstrainableElement> constrainedElems;

		/* The code which shall be instrumented. */
		ITransformedCode constrainedCode;

		/* The name of the generated aspect. */
		String aspectName;
		/* The package of the context java file */
		String contextPackage;

		/* Get a new aspect file name. */
		aspectName = this.myCodeTransEnv.getNewBodyAspectName();

		/* Generate the code which shall be inside the advice(s). */
		constrainedCode = this.transformFragmentCode(aConstraint);

		/* Get the path of the package where the constraint class is located. */
		contextPackage = this.getPackagePath(aConstraint);

		aspectTemplate = this.myTemplateGroup.getTemplate("aspectFile");
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));
		aspectTemplate.setAttribute("name", aspectName);

		/* Probably collect allInstances for some classes. */
		if (this.myCodeTransEnv.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses;

			allInstancesClasses = this.myCodeTransEnv.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		constrainedElems = aConstraint.getConstrainedElement();

		/*
		 * Iterate through all ConstrainableElements and generate their point cuts
		 * and advice.
		 */
		for (ConstrainableElement aConstrainedElem : constrainedElems) {

			/* body constraints can only define the body of Operations. */
			if (aConstrainedElem instanceof Operation) {
				Operation anOperation;
				NamedElement constrainedClass;

				String operationName;
				String operationResultType;

				anOperation = (Operation) aConstrainedElem;

				constrainedClass = anOperation.getOwner();

				operationName = anOperation.getName();
				operationResultType =
						this.transformType(anOperation.getType()).toString();
				/* Create Template for the advice code. */
				adviceTemplate =
						this.myTemplateGroup.getTemplate("bodyInstrumentation");

				/* Set template parameters. */
				adviceTemplate.setAttribute("class", constrainedClass.getName());
				adviceTemplate.setAttribute("package", contextPackage);
				adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
				adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
				adviceTemplate.setAttribute("oclBody", aConstraint.getSpecification()
						.getBody().trim());
				adviceTemplate.setAttribute("method", operationName);

				/* Probably set the returnType. */
				if (!operationResultType.equals(this.myTemplateGroup.getTemplate(
						"voidType").toString())) {
					adviceTemplate.setAttribute("returnType", operationResultType);
				}
				// no else.

				/* Probably set the arguments of the operation. */
				this.setArgumentsForInstrumentationTemplate(aConstraint, adviceTemplate);

				/* Probably set that the constraint operation is static. */
				if (anOperation.isStatic()) {
					adviceTemplate.setAttribute("opIsStatic", "true");
				}
				// no else.

				/* Add the advice code to the aspect template. */
				aspectTemplate.setAttribute("advice", adviceTemplate.toString());
			}

			else {
				String msg;

				msg = "An body constraint defined for an Property of an ";
				msg += "invalid Type of ConstrainableElement.";
				msg += "Expected Type was Operation but was ";
				msg += aConstrainedElem.getClass().getName();

				throw new Ocl2CodeException(msg);
			}
		}
		// end for.

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.mySettings.isSaveCode()) {
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
	 * @param aConstraint
	 *          The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForDef(Constraint aConstraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForDef(Constraint) - start");
		}
		// no else.

		String result;

		ITemplate aspectTemplate;
		ITemplate adviceTemplate;

		/* The code which shall be instrumented. */
		ITransformedCode constrainedCode;

		/* The package of the context java file */
		String contextPackage;
		/* The name of the generated aspect. */
		String aspectName;

		/* FIXME Claas: Implement a solution without a super class. */
		String superClassName;

		/*
		 * A List of ConstrainableElements whose definition expressions shall be
		 * instrumented.
		 */
		List<ConstrainableElement> constrainedElems;

		/* Generate the code which shall be inside the advice(s). */
		constrainedCode = this.transformFragmentCode(aConstraint);

		aspectName = this.myCodeTransEnv.getNewDefAspectName();
		contextPackage = this.getPackagePath(aConstraint);

		/* Begin with aspect specification. */
		aspectTemplate = this.myTemplateGroup.getTemplate("aspectFile");
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));
		aspectTemplate.setAttribute("name", aspectName);

		/* Probably collect allInstances for some classes. */
		if (this.myCodeTransEnv.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses;

			allInstancesClasses = this.myCodeTransEnv.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		constrainedElems = aConstraint.getConstrainedElement();

		/*
		 * Iterate through all ConstrainableElements and generate their point cuts
		 * and advice.
		 */
		for (ConstrainableElement aConstrainedElem : constrainedElems) {

			/* Definition constraints are be defined on Types. */
			if (aConstrainedElem instanceof Type) {

				Type aType;
				Feature definedFeature;

				ITemplate classTemplate;

				aType = (Type) aConstrainedElem;

				definedFeature = aConstraint.getDefinedFeature();

				/* Differentiate between defined properties and operations. */
				if (definedFeature instanceof Property) {

					Property definedProperty;

					String propertyName;
					String propertyType;

					ITemplate attributeTemplate;
					String superClassCanonicalName;

					/* Get the defined property. */
					definedProperty = (Property) definedFeature;

					propertyName = definedProperty.getName();
					propertyType =
							this.transformType(definedProperty.getType()).toString();

					/* DEFINITION OF NEW DEFINED ATTRIBUTE */
					/*
					 * Try to create an extended class and define the new attribute.
					 */
					superClassName = "Extended" + aType.getName();
					superClassCanonicalName =
							this.getConstraintPackage(contextPackage) + "." + superClassName;

					/* Check if the SuperClass has already been defined. */
					if (this.myCodeTransEnv.existsClassTemplate(superClassCanonicalName)) {
						classTemplate =
								this.myCodeTransEnv
										.getSuperClassTemplate(superClassCanonicalName);
					}

					/* Else get the already defined template. */
					else {
						classTemplate =
								this.createExtendedClassTemplate(contextPackage, aType);
					}

					/* Define the new attribute. */
					attributeTemplate = this.myTemplateGroup.getTemplate("javaAttribute");

					attributeTemplate.setAttribute("name", propertyName);
					attributeTemplate.setAttribute("type", propertyType);

					/* Add the attribute to the class' template. */
					classTemplate
							.setAttribute("attributes", attributeTemplate.toString());

					/*
					 * Probably also create a new getter method for the new attribute.
					 */
					if (this.mySettings.isGettersForDefinedAttributesEnabled()) {
						attributeTemplate =
								this.myTemplateGroup.getTemplate("javaAttributeGetter");

						attributeTemplate.setAttribute("name", propertyName);
						attributeTemplate.setAttribute(
								"ucaseName",
								propertyName.substring(0, 1).toUpperCase()
										+ propertyName.substring(1));
						attributeTemplate.setAttribute("type", propertyType);

						classTemplate.setAttribute("methods", attributeTemplate.toString());
					}
					// no else.

					/* INITIALIZATION OF NEW DEFINED ATTRIBUTE */
					/* Create the template for the advice code. */
					adviceTemplate =
							this.myTemplateGroup.getTemplate("defAttributeInstrumentation");

					/* Set attribute specific template attributes. */
					adviceTemplate.setAttribute("attribute", propertyName);
					adviceTemplate.setAttribute("attributeType", propertyType);
				}

				else if (definedFeature instanceof Operation) {

					Operation definedOperation;

					String operationName;
					String operationReturnType;

					String anArgumentsName;
					String anArgumentsType;

					ITemplate methodTemplate;
					String superClassCanonicalName;

					/* Get the defined operation. */
					definedOperation = (Operation) definedFeature;

					operationName = definedOperation.getName();
					operationReturnType =
							this.transformType(definedOperation.getType()).toString();

					/* DEFINITION OF NEW DEFINED OPERATION. */
					/*
					 * Try to create an extended class and define the new operation.
					 */
					superClassName = "Extended" + aType.getName();
					superClassCanonicalName =
							this.getConstraintPackage(contextPackage) + "." + superClassName;

					/* Check if the SuperClass has already been defined. */
					if (this.myCodeTransEnv.existsClassTemplate(superClassCanonicalName)) {
						classTemplate =
								this.myCodeTransEnv
										.getSuperClassTemplate(superClassCanonicalName);
					}

					/* Else get the template of the already defined class. */
					else {
						classTemplate =
								this.createExtendedClassTemplate(contextPackage, aType);
					}

					/* Define the operation. */
					methodTemplate = this.myTemplateGroup.getTemplate("javaMethod");

					methodTemplate.setAttribute("name", operationName);
					methodTemplate.setAttribute("returnType", operationReturnType);

					/* Probably set the arguments of the operation. */
					for (Parameter anArgument : definedOperation.getInputParameter()) {
						anArgumentsName = anArgument.getName();
						anArgumentsType =
								this.transformType(anArgument.getType()).toString();

						methodTemplate.setAttribute("args", anArgumentsName);
						methodTemplate.setAttribute("argTypes", anArgumentsType);
					}

					/* Add the operation to the class' template. */
					classTemplate.setAttribute("methods", methodTemplate.toString());

					/* IMPLEMENTATION OF NEW DEFINED OPERATION. */
					/* Create a template for the advice code. */
					adviceTemplate =
							this.myTemplateGroup.getTemplate("defMethodInstrumentation");

					/* Set operation specific template parameters. */
					adviceTemplate.setAttribute("method", operationName);

					/* Probably set the returnType. */
					if (!operationReturnType.equals(this.myTemplateGroup.getTemplate(
							"voidType").toString())) {
						adviceTemplate.setAttribute("returnType", operationReturnType);
					}
					// no else.

					/* Set all arguments of the Operation. */
					for (Parameter anArgument : definedOperation.getInputParameter()) {
						anArgumentsName = anArgument.getName();
						anArgumentsType =
								this.transformType(anArgument.getType()).toString();

						adviceTemplate.setAttribute("args", anArgumentsName);
						adviceTemplate.setAttribute("argTypes", anArgumentsType);
					}
				}

				/* No other things can be defined by definition constraints. */
				else {
					String msg;

					msg = "An def value defined for an invalid Type of Feature.";
					msg += "Expected Type was Operation or Property but was ";
					msg += aConstrainedElem.getClass().getName();

					throw new Ocl2CodeException(msg);
				}

				/* Set advice template parameters. */
				adviceTemplate.setAttribute("class", aType.getName());
				adviceTemplate.setAttribute("package", contextPackage);
				adviceTemplate.setAttribute("superClass", superClassName);
				adviceTemplate.setAttribute("constFolder",
						this.mySettings.getConstraintDirectory());
				adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
				adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
				adviceTemplate.setAttribute("oclBody", aConstraint.getSpecification()
						.getBody().trim());

				/* Add the advice code to the aspect template. */
				aspectTemplate.setAttribute("advice", adviceTemplate.toString());
			}

			else {
				String msg;

				msg = "An def value defined for an invalid Type ";
				msg += "of ConstrainableElement.";
				msg += "Expected Type was Type but was ";
				msg += aConstrainedElem.getClass().getName();

				throw new Ocl2CodeException(msg);
			}
		}
		// end for.

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.mySettings.isSaveCode()) {
			this.saveTransformedCode(result, aspectName + ".aj",
					this.getConstraintPackage(contextPackage));
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForDef(Constraint)" + "- end - return value="
					+ result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Generates an Aspect for the instrumentation of derive constraints.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForDerive(Constraint aConstraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForDerive(Constraint) - start");
		}
		// no else.

		String result;

		ITemplate aspectTemplate;
		ITemplate adviceTemplate;

		/* A List of Properties whose derive-Expressions shall be instrumented. */
		List<ConstrainableElement> constrainedElems;

		/* The code which shall be instrumented. */
		ITransformedCode constrainedCode;

		/* The name of the generated aspect. */
		String aspectName;
		/* The package of the context java file */
		String contextPackage;

		/* Generate the code which shall be inside the advice(s). */
		constrainedCode = this.transformFragmentCode(aConstraint);

		aspectName = this.myCodeTransEnv.getNewDeriveAspectName();

		/* Get the path of the package where the constraint class is located. */
		contextPackage = this.getPackagePath(aConstraint);

		aspectTemplate = this.myTemplateGroup.getTemplate("aspectFile");
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));
		aspectTemplate.setAttribute("name", aspectName);

		/* Probably collect allInstances for some classes. */
		if (this.myCodeTransEnv.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses;

			allInstancesClasses = this.myCodeTransEnv.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		constrainedElems = aConstraint.getConstrainedElement();

		/*
		 * Iterate through all ConstrainableElements and generate their point cuts
		 * and advice.
		 */
		for (ConstrainableElement aConstrainedElem : constrainedElems) {

			/* Derived values can only define the values of Properties. */
			if (aConstrainedElem instanceof Property) {
				Property aProperty;
				NamedElement constrainedClass;

				String propertyName;
				String propertyType;

				aProperty = (Property) aConstrainedElem;

				/*
				 * Compute the constrained class. Especially handle static defined
				 * properties.
				 */
				if (aProperty.isStatic()) {
					constrainedClass =
							((ExpressionInOcl) aConstraint.getSpecification()).getContext()
									.getType();
				}

				else {
					constrainedClass = aProperty.getOwner();
				}

				propertyName = aProperty.getName();
				propertyType = this.transformType(aProperty.getType()).toString();

				/* Create Template for the advice code. */
				adviceTemplate =
						this.myTemplateGroup.getTemplate("deriveInstrumentation");

				/* Set template parameters. */
				adviceTemplate.setAttribute("attribute", propertyName);
				adviceTemplate.setAttribute("attributeType", propertyType);

				adviceTemplate.setAttribute("class", constrainedClass.getName());
				adviceTemplate.setAttribute("package", contextPackage);
				adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
				adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
				adviceTemplate.setAttribute("oclBody", aConstraint.getSpecification()
						.getBody().trim());

				/* Probably set that the constrained attribute is static. */
				if (aProperty.isStatic()) {
					adviceTemplate.setAttribute("attIsStatic", "true");
				}
				// no else.

				/* Add the advice code to the aspect template. */
				aspectTemplate.setAttribute("advice", adviceTemplate.toString());
			}

			else {
				String msg;

				msg = "An derived value defined for an Property of an invalid Type ";
				msg += "of ConstrainableElement.";
				msg += "Expected Type was Property but was ";
				msg += aConstrainedElem.getClass().getName();

				throw new Ocl2CodeException(msg);
			}
		}
		// end for.

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.mySettings.isSaveCode()) {
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
	 * @param aConstraint
	 *          The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForInit(Constraint aConstraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForInit(Constraint) - start");
		}
		// no else.

		String result;

		ITemplate aspectTemplate;
		ITemplate adviceTemplate;

		/* A List of Properties which initial expressions shall be instrumented. */
		List<ConstrainableElement> constrainedElems;

		/* The code which shall be instrumented. */
		ITransformedCode constrainedCode;

		/* The name of the generated aspect. */
		String aspectName;
		/* The name of the instrumented class. */
		String className;
		/* The package of the context java file */
		String contextPackage;

		/* Generate the code which shall be inside the advice(s). */
		constrainedCode = this.transformFragmentCode(aConstraint);

		/* Get a new name for an initial aspect. */
		aspectName = this.myCodeTransEnv.getNewInitAspectName();

		/* Get the path of the package where the constraint class is located. */
		contextPackage = this.getPackagePath(aConstraint);

		aspectTemplate = this.myTemplateGroup.getTemplate("aspectFile");
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));
		aspectTemplate.setAttribute("name", aspectName);

		/* Probably collect allInstances for some classes. */
		if (this.myCodeTransEnv.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses;

			allInstancesClasses = this.myCodeTransEnv.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		constrainedElems = aConstraint.getConstrainedElement();

		/*
		 * Iterate through all ConstrainableElements and generate their point cuts
		 * and advice.
		 */
		for (ConstrainableElement aConstrainedElem : constrainedElems) {

			/* Initial values can only initialize Properties of Types. */
			if (aConstrainedElem instanceof Property) {
				Property aProperty;

				aProperty = (Property) aConstrainedElem;

				/*
				 * Compute the constrained class name. Especially handle static defined
				 * properties.
				 */
				if (aProperty.isStatic()) {
					className =
							((ExpressionInOcl) aConstraint.getSpecification()).getContext()
									.getType().getName();
				}

				else {
					className = aProperty.getOwner().getName();
				}

				/* Get the template and handle static attribute specially. */
				if (aProperty.isStatic()) {
					adviceTemplate =
							this.myTemplateGroup.getTemplate("staticInitInstrumentation");
				}

				else {
					adviceTemplate =
							this.myTemplateGroup.getTemplate("initInstrumentation");
				}

				adviceTemplate.setAttribute("class", className);
				adviceTemplate.setAttribute("package", contextPackage);
				adviceTemplate.setAttribute("attribute", aProperty.getName());
				adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
				adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
				adviceTemplate.setAttribute("oclBody", aConstraint.getSpecification()
						.getBody().trim());

				aspectTemplate.setAttribute("advice", adviceTemplate.toString());
			}

			else {
				String msg;

				msg = "An init value defined for an Property of an invalid Type ";
				msg += "of ConstrainableElement.";
				msg += "Expected Type was Property but was ";
				msg += aConstrainedElem.getClass().getName();

				throw new Ocl2CodeException(msg);
			}
		}
		// end for.

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.mySettings.isSaveCode()) {
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
	 * @param aConstraint
	 *          The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForInv(Constraint aConstraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForInv(Constraint) - start");
		}
		// no else.

		String result;

		ITemplate aspectTemplate;
		ITemplate adviceTemplate;

		/* A List of Properties whose invariants shall be instrumented. */
		List<ConstrainableElement> constrainedElems;

		/* The code which shall be instrumented. */
		ITransformedCode constrainedCode;

		/* The name of the generated aspect. */
		String aspectName;
		/* The package of the context java file */
		String contextPackage;

		/* Generate the code which shall be inside the advice(s). */
		constrainedCode = this.transformFragmentCode(aConstraint);

		/* Get a new name for an invariant aspect. */
		aspectName = this.myCodeTransEnv.getNewInvAspectName();

		/* Get the path of the package where the constraint class is located. */
		contextPackage = this.getPackagePath(aConstraint);

		aspectTemplate = this.myTemplateGroup.getTemplate("aspectFile");
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));
		aspectTemplate.setAttribute("name", aspectName);

		/* Probably collect allInstances for some classes. */
		if (this.myCodeTransEnv.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses;

			allInstancesClasses = this.myCodeTransEnv.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		constrainedElems = aConstraint.getConstrainedElement();

		/*
		 * Iterate through all ConstrainableElements and generate their point cuts
		 * and advice.
		 */
		for (ConstrainableElement aConstrainedElem : constrainedElems) {

			/* Invariants can only define the values of Type instances. */
			if (aConstrainedElem instanceof Type) {

				NamedElement constrainedClass = (Type) aConstrainedElem;

				/*
				 * Check which type of invariant check mode shall be used and create the
				 * template for the advice code.
				 */
				switch (this.mySettings.getInvariantCheckMode(aConstraint)) {

				case IOcl2JavaSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_PUBLIC_METHOD_EXECUTION:
					adviceTemplate =
							this.myTemplateGroup.getTemplate("invInstrumentation2");
					break;

				case IOcl2JavaSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION:
					adviceTemplate =
							this.myTemplateGroup.getTemplate("invInstrumentation3");
					break;

				default:
					adviceTemplate =
							this.myTemplateGroup.getTemplate("invInstrumentation1");
				}

				/* Set template parameters. */
				adviceTemplate.setAttribute("class", constrainedClass.getName());
				adviceTemplate.setAttribute("package", contextPackage);
				adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
				adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
				adviceTemplate.setAttribute("oclBody", aConstraint.getSpecification()
						.getBody().trim());
				adviceTemplate.setAttribute("errorCode", this.mySettings
						.getViolationMacro(aConstraint).getCode());

				/*
				 * Probably add called attributes as parameters (only if the invariant
				 * shall be verified after the change of an depending attribute).
				 */
				if (this.mySettings.getInvariantCheckMode(aConstraint) == IOcl2JavaSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE) {

					if (this.myCodeTransEnv.hasCalledProperties()) {

						for (String aCalledProperty : this.myCodeTransEnv
								.getCalledProperties()) {
							adviceTemplate.setAttribute("calledAttPaths", aCalledProperty);
							adviceTemplate.setAttribute("calledAttNames",
									aCalledProperty.replaceAll("\\.", "_"));
						}
					}
					// no else.
				}
				// no else.

				/*
				 * Probably add super class for method definition (only if the invariant
				 * shall be verified after special method invocation).
				 */
				if (this.mySettings.getInvariantCheckMode(aConstraint) == IOcl2JavaSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION) {

					ITemplate classTemplate;
					ITemplate methodTemplate;

					/* FIXME Claas: Implement a solution without a super class. */
					String superClassName;
					String superClassCanonicalName;

					String operationName;

					/* Create an extended class and define the new method. */
					superClassName = "Extended" + constrainedClass.getName();
					superClassCanonicalName =
							this.getConstraintPackage(contextPackage) + "." + superClassName;

					/* Check if the SuperClass has already been defined. */
					if (this.myCodeTransEnv.existsClassTemplate(superClassCanonicalName)) {
						classTemplate =
								this.myCodeTransEnv
										.getSuperClassTemplate(superClassCanonicalName);
					}

					else {
						classTemplate =
								this.createExtendedClassTemplate(contextPackage,
										(Type) aConstrainedElem);
					}

					operationName =
							this.myTemplateGroup.getTemplate("checkInvariantsOperationName")
									.toString();

					/* Check if the Method has already been defined. */
					if (!classTemplate.toString().contains(operationName)) {

						/* Else Define the method. */
						methodTemplate = this.myTemplateGroup.getTemplate("javaMethod");

						methodTemplate.setAttribute("name", operationName);

						classTemplate.setAttribute("methods", methodTemplate.toString());
					}

					adviceTemplate.setAttribute("constFolder",
							mySettings.getConstraintDirectory());
					adviceTemplate.setAttribute("superClass", superClassName);
				}
				// no else.

				/* Probably disable the inheritance for this invariant. */
				if (this.mySettings.isInheritanceDisabled(aConstraint)) {
					adviceTemplate.setAttribute("disableInheritance", "true");
				}
				// no else.

				/* Add the advice code to the aspect template. */
				aspectTemplate.setAttribute("advice", adviceTemplate.toString());
			}

			else {
				String msg;

				msg = "An invariant defined for an Property of an ";
				msg += "invalid Type of ConstrainableElement.";
				msg += "Expected Type was Type but was ";
				msg += aConstrainedElem.getClass().getName();

				throw new Ocl2CodeException(msg);
			}
		}
		// end for.

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.mySettings.isSaveCode()) {
			this.saveTransformedCode(result, aspectName + ".aj",
					this.getConstraintPackage(contextPackage));
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForInv(Constraint)" + "- end - return value="
					+ result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Generates an Aspect for the instrumentation of postconditions.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForPost(Constraint aConstraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForPost(Constraint) - start");
		}
		// no else.

		String result;

		ITemplate aspectTemplate;
		ITemplate adviceTemplate;

		/* A List of properties which preconditions shall be instrumented. */
		List<ConstrainableElement> constrainedElems;

		/* The code which shall be instrumented. */
		ITransformedCode constrainedCode;

		/* The name of the generated aspect. */
		String aspectName;
		/* The package of the context java file */
		String contextPackage;

		/* Generate the code which shall be inside the advice(s). */
		constrainedCode = this.transformFragmentCode(aConstraint);

		/* Get a new name for a postcondition aspect. */
		aspectName = this.myCodeTransEnv.getNewPostAspectName();

		/* Get the path of the package where the constraint class is located. */
		contextPackage = this.getPackagePath(aConstraint);

		aspectTemplate = this.myTemplateGroup.getTemplate("aspectFile");
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));
		aspectTemplate.setAttribute("name", aspectName);

		/* Probably collect allInstances for some classes. */
		if (this.myCodeTransEnv.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses;

			allInstancesClasses = this.myCodeTransEnv.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		/* Probably collect newInstances for some classes. */
		if (this.myCodeTransEnv.hasIsNewClasses()) {
			Set<String> isNewClasses;

			isNewClasses = this.myCodeTransEnv.getIsNewClasses();

			for (String aClassName : isNewClasses) {
				aspectTemplate.setAttribute("isNewClasses", aClassName);
			}
		}
		// no else.

		constrainedElems = aConstraint.getConstrainedElement();

		/*
		 * Iterate through all ConstrainableElements and generate their point cuts
		 * and advice.
		 */
		for (ConstrainableElement aConstrainedElem : constrainedElems) {

			/* Postconditions can be only defined on the body of Operations. */
			if (aConstrainedElem instanceof Operation) {
				Operation anOperation;
				NamedElement constrainedClass;

				String operationName;
				String operationResultType;

				anOperation = (Operation) aConstrainedElem;

				constrainedClass = anOperation.getOwner();

				operationName = anOperation.getName();
				operationResultType =
						this.transformType(anOperation.getType()).toString();
				/* Create Template for the advice code. */
				adviceTemplate =
						this.myTemplateGroup.getTemplate("postInstrumentation");

				/* Set template parameters. */
				adviceTemplate.setAttribute("class", constrainedClass.getName());
				adviceTemplate.setAttribute("package", contextPackage);
				adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
				adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
				adviceTemplate.setAttribute("oclBody", aConstraint.getSpecification()
						.getBody().trim());
				adviceTemplate.setAttribute("method", operationName);
				adviceTemplate.setAttribute("errorCode", this.mySettings
						.getViolationMacro(aConstraint).getCode());

				/* Probably set the returnType. */
				if (!operationResultType.equals(this.myTemplateGroup.getTemplate(
						"voidType").toString())) {
					adviceTemplate.setAttribute("returnType", operationResultType);
				}
				// no else.

				/* Probably set the arguments of the operation. */
				this.setArgumentsForInstrumentationTemplate(aConstraint, adviceTemplate);

				/* Probably store some atPre values */
				if (this.myCodeTransEnv.hasAtPreValues()) {
					Map<ITransformedCode, Object[]> atPreValues;
					Set<ITransformedCode> valueCodes;
					Set<String> atPreTypes;

					atPreValues = this.myCodeTransEnv.getAtPreValues();
					valueCodes = atPreValues.keySet();
					atPreTypes = new HashSet<String>();

					for (ITransformedCode aValueCode : valueCodes) {
						Object[] typeAndName;

						Type aType;
						String transformedType;

						typeAndName = atPreValues.get(aValueCode);

						aType = (Type) typeAndName[0];
						transformedType = this.transformInitializableType(aType).toString();

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
							adviceTemplate.setAttribute("atPreCodes", aValueCode.getCode());
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
				if (this.myCodeTransEnv.hasIsNewClasses()) {
					adviceTemplate.setAttribute("oclIsNewUsed", "true");
				}
				// no else.

				/* Probably disable inheritance for this post condition. */
				if (this.mySettings.isInheritanceDisabled(aConstraint)) {
					adviceTemplate.setAttribute("disableInheritance", "true");
				}
				// no else.

				/* Probably set that the constraint operation is static. */
				if (anOperation.isStatic()) {
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
			}

			else {
				String msg;

				msg = "An precondition defined for an Property of an ";
				msg += "invalid Type of ConstrainableElement.";
				msg += "Expected Type was Operation but was ";
				msg += aConstrainedElem.getClass().getName();

				throw new Ocl2CodeException(msg);
			}
		}
		// end for.

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.mySettings.isSaveCode()) {
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
	 * @param aConstraint
	 *          The {@link Constraint} which shall be instrumented.
	 */
	private String instrumentCodeForPre(Constraint aConstraint)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForPre(Constraint) - start");
		}
		// no else.

		String result;

		ITemplate aspectTemplate;
		ITemplate adviceTemplate;

		/* A List of Properties on which the precondition shall be instrumented. */
		List<ConstrainableElement> constrainedElems;

		/* The code which shall be instrumented. */
		ITransformedCode constrainedCode;

		/* The name of the generated aspect. */
		String aspectName;
		/* The package of the context java file */
		String contextPackage;

		/* Generate the code which shall be inside the advice(s). */
		constrainedCode = this.transformFragmentCode(aConstraint);

		/* Get a new name for a preconditon. */
		aspectName = this.myCodeTransEnv.getNewPreAspectName();

		/* Get the path of the package where the constraint class is located. */
		contextPackage = this.getPackagePath(aConstraint);

		aspectTemplate = this.myTemplateGroup.getTemplate("aspectFile");
		aspectTemplate.setAttribute("package",
				this.getConstraintPackage(contextPackage));
		aspectTemplate.setAttribute("name", aspectName);

		/* Probably collect allInstances for some classes. */
		if (this.myCodeTransEnv.hasAllInstancesClasses()) {
			Set<String> allInstancesClasses;

			allInstancesClasses = this.myCodeTransEnv.getAllInstancesClasses();

			for (String aClassName : allInstancesClasses) {
				aspectTemplate.setAttribute("allInstanceClasses", aClassName);
			}
		}
		// no else.

		constrainedElems = aConstraint.getConstrainedElement();

		/*
		 * Iterate through all ConstrainableElements and generate their point cuts
		 * and advice.
		 */
		for (ConstrainableElement aConstrainedElem : constrainedElems) {

			/* Preconditions can be only defined on the body of operations. */
			if (aConstrainedElem instanceof Operation) {
				Operation anOperation;
				NamedElement constrainedClass;

				String operationName;
				String operationResultType;

				anOperation = (Operation) aConstrainedElem;

				constrainedClass = anOperation.getOwner();

				operationName = anOperation.getName();
				operationResultType =
						this.transformType(anOperation.getType()).toString();
				/* Create Template for the advice code. */
				adviceTemplate = this.myTemplateGroup.getTemplate("preInstrumentation");

				/* Set template parameters. */
				adviceTemplate.setAttribute("class", constrainedClass.getName());
				adviceTemplate.setAttribute("package", contextPackage);
				adviceTemplate.setAttribute("constCode", constrainedCode.getCode());
				adviceTemplate.setAttribute("constExp", constrainedCode.getResultExp());
				adviceTemplate.setAttribute("oclBody", aConstraint.getSpecification()
						.getBody().trim());
				adviceTemplate.setAttribute("method", operationName);
				adviceTemplate.setAttribute("errorCode", this.mySettings
						.getViolationMacro(aConstraint).getCode());

				/* Probably set the returnType. */
				if (!operationResultType.equals(this.myTemplateGroup.getTemplate(
						"voidType").toString())) {
					adviceTemplate.setAttribute("returnType", operationResultType);
				}
				// no else.

				/* Probably set the arguments of the operation. */
				this.setArgumentsForInstrumentationTemplate(aConstraint, adviceTemplate);

				/* Probably disable inheritance for this post condition. */
				if (this.mySettings.isInheritanceDisabled(aConstraint)) {
					adviceTemplate.setAttribute("disableInheritance", "true");
				}
				// no else.

				/* Probably set that the constraint operation is static. */
				if (anOperation.isStatic()) {
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
			}

			else {
				String msg;

				msg = "An pre condition defined for an Property of an ";
				msg += "invalid Type of ConstrainableElement.";
				msg += "Expected Type was Operation but was ";
				msg += aConstrainedElem.getClass().getName();

				throw new Ocl2CodeException(msg);
			}
		}
		// end for.

		result = aspectTemplate.toString();

		/* Probably save the generated Code. */
		if (this.mySettings.isSaveCode()) {
			this.saveTransformedCode(result, aspectName + ".aj",
					this.getConstraintPackage(contextPackage));
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("instrumentCodeForPre(Constraint)" + "- end - return value="
					+ result);
		}
		// no else.

		return result;
	}

	/**
	 * @returns The canonical Name of a {@link NamedElement} in the {@link IModel}
	 *          instance.
	 */
	private String getCanonicalName(NamedElement anElement) {

		String result;

		result = "";

		if (anElement.getOwner() != null) {
			result += this.getPackagePath(anElement) + ".";
		}
		// no else.

		result += anElement.getName();

		return result;
	}

	/**
	 * @returns The path of the package in the {@link IModel} instance where the
	 *          given {@link NamedElement} is located.
	 */
	private String getPackagePath(NamedElement anElement) {

		String result;
		NamedElement anOwner;

		anOwner = anElement.getOwner();
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

		return result;
	}

	/**
	 * @param packagePath
	 * @return The constraint package to a given packagePath. They are the same if
	 *         no constraint directory has been set. Else the constraint directory
	 *         is a sub package.
	 */
	private String getConstraintPackage(String packagePath) {

		String result;
		String constDirectory;

		result = packagePath;

		constDirectory = this.mySettings.getConstraintDirectory();

		if (constDirectory.length() > 0) {
			result += "." + constDirectory.replaceAll("/", ".");
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method which returns a {@link ITemplate} containing the code for a
	 * new defined Class as new super class to a given {@link Type} .
	 * </p>
	 * 
	 * @param contextPackage
	 *          The package where the given {@link Type} is located.
	 * @param aType
	 *          The {@link Type} which new superclass shall be created.
	 * @return A {@link ITemplate} containing the code for a new defined Class as
	 *         new super class to a given {@link Type}.
	 */
	private ITemplate createExtendedClassTemplate(String contextPackage,
			Type aType) {

		ITemplate classTemplate;
		String canoncicalSuperType;
		String className;
		String packagePath;

		className = EXTENDEC_CLASS_NAME_PREFIX + aType.getName();
		packagePath = this.getConstraintPackage(this.getPackagePath(aType));

		classTemplate = this.myTemplateGroup.getTemplate("javaFile");

		classTemplate.setAttribute("package", packagePath);
		classTemplate.setAttribute("name", aType.getName());

		/* Probably set a super type of the class. */
		if (aType.getSuperType().size() > 0) {
			canoncicalSuperType =
					this.transformType(aType.getSuperType().get(0)).toString();
			classTemplate.setAttribute("superType", canoncicalSuperType);
		}
		// no else.

		/* Store superClass in the environment. */
		this.myCodeTransEnv.addSuperClassTemplate(packagePath + "." + className,
				classTemplate);

		return classTemplate;
	}

	/**
	 * <p>
	 * A helper method which saves all new generated super classes to define new
	 * attributes and/or methods.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *           Ocl2CodeException Thrown if the given file or location can not be
	 *           found.
	 */
	private void saveExtendedClasses() throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("saveExtendedClasses() - start");
		}
		// no else.

		Set<String> classNames;
		Map<String, ITemplate> classTemplates;
		ITemplate aClassTemplate;

		/*
		 * Get the templates for all newly creates super classes and their class
		 * names.
		 */
		classTemplates = this.myCodeTransEnv.getSuperClassTemplates();
		classNames = classTemplates.keySet();

		/* Iterate through the templates and save their code. */
		for (String aCanonicalClassName : classNames) {

			String packagePath;
			String fileName;
			int nameBegin;

			/* Get the index where the simple class name begins. */
			nameBegin = aCanonicalClassName.lastIndexOf(".");

			packagePath = aCanonicalClassName.substring(0, nameBegin);

			fileName = aCanonicalClassName.substring(nameBegin + 1);
			fileName += ".java";

			aClassTemplate = classTemplates.get(aCanonicalClassName);

			this.saveTransformedCode(aClassTemplate.toString(), fileName, packagePath);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("saveExtendedClasses() - end");
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method which saves a given generated code into a given file name
	 * at a given location.
	 * </p>
	 * 
	 * @param generatedCode
	 *          The code which shall be saved.
	 * @param fileName
	 *          The name of the File into which the transformed code shall be
	 *          saved.
	 * @param subFolder
	 *          The subFolder(s) into which the File shall be located (relative)
	 *          to the sourcePath.
	 * @throws Ocl2CodeException
	 *           Thrown if the given file or location can not be found.
	 */
	private void saveTransformedCode(String generatedCode, String fileName,
			String subFolder) throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("saveGeneratedCode(String, String, String)");
		}
		// no else.

		File outputFolder;
		File outputFile;
		FileOutputStream fileOutputStream;

		try {
			String[] directories;

			/* Replace all '.' with '/'. Does not work with String#replaceAll. */
			directories = subFolder.split("\\.");

			subFolder = "";
			for (int i = 0; i < directories.length; i++) {
				subFolder += directories[i] + "/";
			}

			outputFolder = new File(this.mySettings.getSourceDirectory() + subFolder);

			/* Check if output folder does exists. */
			if (!outputFolder.isDirectory()) {
				outputFolder.mkdirs();
			}
			// no else.

			outputFile = new File(outputFolder.getAbsolutePath() + "/" + fileName);

			/* Create the output file. */
			fileOutputStream = new FileOutputStream(outputFile);
			fileOutputStream.write(generatedCode.getBytes());
			fileOutputStream.close();
		}

		catch (FileNotFoundException e) {

			String msg;

			msg = "File for saving generated code not found. ";
			msg += "File location was ";
			msg += this.mySettings.getSourceDirectory() + fileName + ".";

			LOGGER.fatal(msg);

			throw new Ocl2CodeException(msg);
		}

		catch (IOException e) {

			String msg;

			msg = "Opening of File for saving failed. ";
			msg += "File location was ";
			msg += this.mySettings.getSourceDirectory() + fileName + ".";

			LOGGER.fatal(msg);

			throw new Ocl2CodeException(msg);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("saveGeneratedCode(String, String, String) - end");
		}
		// no else.
	}

	/**
	 * <p>
	 * Sets the argument names and {@link Type}s of a given {@link Constraint} for
	 * its instrumentation template.
	 * </p>
	 * 
	 * @param constraint
	 *          The {@link Constraint} for that the arguments shall be set.
	 * @param adviceTemplate
	 *          The {@link ITemplate} to set the names and {@link Type}s.
	 */
	private void setArgumentsForInstrumentationTemplate(Constraint constraint,
			ITemplate adviceTemplate) {

		for (Variable anArgument : ((ExpressionInOcl) constraint.getSpecification())
				.getParameter()) {
			String anArgumentsName;
			String anArgumentsType;

			anArgumentsName = anArgument.getName();
			anArgumentsType = this.transformType(anArgument.getType()).toString();

			adviceTemplate.setAttribute("args", anArgumentsName);
			adviceTemplate.setAttribute("argTypes", anArgumentsType);
		}
		// end for.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.tools.codegen.IOcl2Code#setSettings(tudresden.ocl20
	 * .pivot.tools.codegen.IOcl2CodeSettings)
	 */
	public void setSettings(IOcl2JavaSettings settings) {

		this.mySettings = settings;
	}

	/*
	 * (non-Javadoc)
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

		List<String> result;

		result = new ArrayList<String>();

		for (Constraint aConstraint : constraints) {

			ITransformedCode aTransformedConstraint;
			String aResult;

			this.myCodeTransEnv.resetEnvironmentForNextConstraint();

			aTransformedConstraint = this.transformFragmentCode(aConstraint);

			aResult = aTransformedConstraint.getCode();

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
}