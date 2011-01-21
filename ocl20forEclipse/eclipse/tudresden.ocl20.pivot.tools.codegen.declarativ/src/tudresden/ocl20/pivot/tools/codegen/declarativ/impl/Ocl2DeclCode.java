/*
 * Ocl2DeclCode.java
 * 
 * Copyright (c) 2010 Bjoern Freitag
 * Contact: <bjoern.freitag@mailbox.tu-dresden.de>
 *
 * This file is part of the Dresden OCL2.0 Toolkit
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.pivot.tools.codegen.declarativ.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.IfExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.PrimitiveLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.expressions.VariableExp;
import tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch;
import tudresden.ocl20.pivot.essentialocl.types.AnyType;
import tudresden.ocl20.pivot.essentialocl.types.BagType;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.SequenceType;
import tudresden.ocl20.pivot.essentialocl.types.SetType;
import tudresden.ocl20.pivot.pivotmodel.AssociationProperty;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Expression;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclCode;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.code.ISQLCode;
import tudresden.ocl20.pivot.tools.codegen.declarativ.code.SQLCode;
import tudresden.ocl20.pivot.tools.codegen.declarativ.code.SQLString;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.Guide;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedClass;
import tudresden.ocl20.pivot.tools.codegen.exception.Ocl2CodeException;
import tudresden.ocl20.pivot.tools.template.ITemplate;

/**
 * <p>
 * The DeclarativeCodeGenerator generates declarative target code for a given
 * ExpressionInOcl.
 * </p>
 * <p>
 * The OclExpressions from a UML/OCL or MOF/OCL model are transformed to the
 * target language using two different mappings:<br />
 * <ul>
 * <li>Mapping between models:<br />
 * The first mapping maps the Classifiers, its attributes and association ends
 * to a target model. The target model must implement the MappedModel interface
 * and describes the model elements by Guide objects.</li>
 * <li>Language mapping:<br />
 * The second mapping maps OCL language artefacts to fragments of the target
 * language using templates which are read by the DeclarativeTemplateEngine. The
 * templates are parametrisized by the DeclarativeCodeGenerator using target
 * model information queried via the MappedModel instance.
 * </ul>
 * 
 * @author Florian Heidenreich
 * @author Bjoern Freitag
 * 
 * @see tudresden.ocl20.codegen.decl.mapping.IMappedModel
 * @see tudresden.ocl20.codegen.decl.mapping.IMappedClass
 * @see tudresden.ocl20.codegen.decl.mapping.Guide
 */
public class Ocl2DeclCode extends ExpressionsSwitch<ISQLCode> implements
		IOcl2DeclCode {

	/** The Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(Ocl2DeclCode.class);

	/** The Settings used during code generation. */
	private IOcl2DeclSettings mySettings;

	/**
	 * This map is filled by assignGuides and maps an OclExpression to a list of
	 * Guide objects
	 */
	private Map<OclExpression, List<Guide>> navigationMap;

	/**
	 * This map mapped a variable Name to a Guide.
	 */
	private Map<String, Guide> variableMap;

	/**
	 * If the variable true, than generated no allInstances code.
	 */
	private boolean useVariable;

	private boolean closure;

	/**
	 * 
	 */
	private List<ISQLCode> commonTableExpressions;

	private String constraintName;

	/**
	 * The actualNumber of commonTable
	 */
	private int cteNumber;

	/**
	 * The number for not named constraints.
	 */
	private int uniqueConstraintNumber;

	/**
	 * <p>
	 * Creates a new instance to transform code.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *           If the initialization fails.
	 */
	public Ocl2DeclCode() throws Ocl2CodeException {

		init();
	}

	/**
	 * <p>
	 * Initializes the code generator.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown, if a String template for code transformation can not be
	 *           found.
	 */
	private void init() throws Ocl2CodeException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("init() - start");
		}
		// no else.

		resetEnvironment();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("init() - end");
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.tools.codgen.IOcl2Code#getSettings()
	 */
	public IOcl2DeclSettings getSettings() {

		return mySettings;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.tools.codgen.IOcl2Code#resetEnviornment()
	 */
	public void resetEnvironment() {

		uniqueConstraintNumber = 0;
		navigationMap = new HashMap<OclExpression, List<Guide>>();
		variableMap = new HashMap<String, Guide>();
		useVariable = false;
		commonTableExpressions = new LinkedList<ISQLCode>();
		constraintName = "";
		closure = false;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.tools.codgen.IOcl2Code#setSettings()
	 */
	public void setSettings(IOcl2DeclSettings settings) {

		mySettings = settings;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.tools.codgen.IOcl2Code#transformFragmentCode(java
	 * .util .List)
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

			String aResult = transformFragmentCode(aConstraint);

			if (aResult == null)
				continue;

			result.add(aResult);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformFragmentCode(List<Constraint>)"
					+ " - end - return value=" + result);
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
	 *          The {@link Constraint} for which code shall be transformed.
	 * @return An {@link String} containing the Code transformed for the given
	 *         {@link Constraint}'s {@link OclExpression}.
	 * @throws Ocl22CodeException
	 */
	private String transformFragmentCode(Constraint aConstraint)
			throws Ocl2CodeException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformFragmentCode(Constraint) - start");
		}
		// no else.

		reset();
		// Check of generated Constraint Types:
		if (aConstraint.getKind() != ConstraintKind.INVARIANT) {
			return null;
		}

		ITemplate template = null;
		if (aConstraint.getKind() == ConstraintKind.INVARIANT) {
			template =
					mySettings.getTemplateGroup().getTemplate("constraint_invariant");
			constraintName = aConstraint.getName();
		}

		if (constraintName == null || constraintName.equals("")) {
			constraintName = getUniqueConstraintName();
		}

		ISQLCode bodyExpression;
		Expression anExpression;

		anExpression = aConstraint.getSpecification();
		bodyExpression = doSwitch(anExpression);

		Guide classGuide = variableMap.values().iterator().next();
		classGuide.reset();

		template.setAttribute("constraint_name", constraintName);
		template.setAttribute("context", classGuide.getFrom());
		template.setAttribute("context_alias", classGuide.getAlias());
		template.setAttribute("expression", bodyExpression.getResult());

		if (commonTableExpressions.size() > 0) {
			template.setAttribute("common_table", commonTableExpressions.get(0)
					.getResult());
		}

		ITemplate comment =
				mySettings.getTemplateGroup().getTemplate("constraint_comment");
		comment.setAttribute("context", ((Type) aConstraint.getConstrainedElement()
				.get(0)).getName());
		comment.setAttribute("expression",
				anExpression.getBody().replace("\r\n ", "").replace(" \r\n", "")
						.replace("\r\n", ""));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("transformFragmentCode(Constraint)"
					+ " - end - return value=" + template.toString());
		}
		// no else.

		return comment.toString() + "\n" + template.toString();
	}

	/**
	 * Returns a unique name of a constraint.
	 * 
	 * @return the name of constraint
	 */
	private String getUniqueConstraintName() {

		uniqueConstraintNumber++;
		return "UNAMED_CONSTRAINT" + uniqueConstraintNumber;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseBooleanLiteralExp(BooleanLiteralExp)
	 */
	public ISQLCode caseBooleanLiteralExp(BooleanLiteralExp aBooleanLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseBooleanLiteralExp(BooleanLiteralExp) - start");
		}
		// no else.

		ISQLCode template;

		if (aBooleanLiteralExp.isBooleanSymbol()) {
			template =
					new SQLCode(mySettings.getTemplateGroup().getTemplate(
							"literal_boolean_true"));
		}
		else {
			template =
					new SQLCode(mySettings.getTemplateGroup().getTemplate(
							"literal_boolean_false"));
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseBooleanLiteralExp(BooleanLiteralExp)"
					+ " - end - return value=" + template.toString());
		}
		// no else.

		return template;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#
	 * caseCollectionLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp)
	 */
	public ISQLCode caseCollectionLiteralExp(
			CollectionLiteralExp anCollectionLiteralExp) {

		List<CollectionLiteralPart> parts = anCollectionLiteralExp.getPart();
		String templateName = "";
		ISQLCode template;

		// determine template to use depending on collection kind
		if (anCollectionLiteralExp.getKind() == CollectionKind.BAG) {
			templateName = "literal_collection_bag";
		}
		else if (anCollectionLiteralExp.getKind() == CollectionKind.SET) {
			templateName = "literal_collection_set";
		}
		else if (anCollectionLiteralExp.getKind() == CollectionKind.SEQUENCE) {
			templateName = "literal_collection_sequence";
		}
		else {
			assert (false) : "Unknown CollectionKind";
		}

		// get template for items in the collection
		template =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(templateName));

		// parameterize the template engine
		for (CollectionLiteralPart clp : parts) {
			template.addElement("items",
					doSwitch((OclExpression) ((CollectionItem) clp).getItem()));
		}

		return template;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseEnumLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp)
	 */
	public ISQLCode caseEnumLiteralExp(EnumLiteralExp anEnumLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseEnumLiteralExp(EnumLiteralExp) - start");
		}
		// no else.

		ISQLCode template;

		template =
				new SQLCode(mySettings.getTemplateGroup().getTemplate("literal_enum"));

		template.addElement("value", new SQLString(anEnumLiteralExp
				.getReferredEnumLiteral().getName()));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseEnumLiteralExp(EnumLiteralExp)"
					+ " - end - return value=" + template.toString());
		}
		// no else.

		return template;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseExpressionInOcl
	 * (tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl)
	 */
	public ISQLCode caseExpressionInOcl(ExpressionInOcl anExpressionInOcl) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseExpressionInOcl(ExpressionInOcl) - start");
		}
		// no else.
		Variable v = anExpressionInOcl.getContext();

		IMappedClass constrainedType =
				getSettings().getMappedModel().getClass(v.getType().getName());
		Guide classGuide =
				getSettings().getMappedModel().getClass(constrainedType.getName())
						.getClassGuide();
		classGuide.setAlias(mySettings.getMappedModel().getUniqueAlias());
		classGuide.reset();
		variableMap.put(v.getName(), classGuide);

		/* Transform bodyCode. */
		ISQLCode bodyExpression =
				doSwitch((EObject) anExpressionInOcl.getBodyExpression());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseExpressionInOcl(ExpressionInOcl)"
					+ " - end - return value=" + bodyExpression);
		}
		// no else.

		return bodyExpression;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIfExp(tudresden.ocl20.pivot.essentialocl.expressions.IfExp)
	 */
	public ISQLCode caseIfExp(IfExp anIfExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIfExp(IfExp) - start");
		}
		// no else.

		ISQLCode template;

		OclExpression ifExp;
		OclExpression thenExp;
		OclExpression elseExp;

		ISQLCode ifCode;
		ISQLCode thenCode;
		ISQLCode elseCode;

		ifExp = anIfExp.getCondition();
		thenExp = anIfExp.getThenExpression();
		elseExp = anIfExp.getElseExpression();

		/* Transform ifExp, thenExp and elseExp. */
		ifCode = doSwitch((EObject) ifExp);
		thenCode = doSwitch((EObject) thenExp);
		elseCode = doSwitch((EObject) elseExp);

		/* Transform ifExp. */
		template =
				new SQLCode(mySettings.getTemplateGroup().getTemplate("if_expression"));
		template.addElement("if_branch", ifCode);
		template.addElement("then_branch", thenCode);
		template.addElement("else_branch", elseCode);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIfExp(IfExp)" + " - end - return value="
					+ template.toString());
		}
		// no else.

		return template;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIntegerLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp)
	 */
	public ISQLCode caseIntegerLiteralExp(IntegerLiteralExp anIntegerLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIntegerLiteralExp(IntegerLiteralExp) - start");
		}
		// no else.

		ISQLCode template;

		template =
				new SQLCode(mySettings.getTemplateGroup()
						.getTemplate("literal_integer"));

		template.addElement(
				"value",
				new SQLString(new Integer(anIntegerLiteralExp.getIntegerSymbol())
						.toString()));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIntegerLiteralExp(IntegerLiteralExp)"
					+ " - end - return value=" + template.toString());
		}
		// no else.

		return template;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIteratorExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp)
	 */
	public ISQLCode caseIteratorExp(IteratorExp anIteratorExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIteratorExp(IteratorExp) - start - Iterator"
					+ anIteratorExp);
		}

		String operationName = anIteratorExp.getName();
		if (operationName.equals("iterate")) {
			throw new UnsupportedOperationException(
					"The iterate operation is not supported.");
		}
		ISQLCode result = null;

		OclExpression sourceExp = anIteratorExp.getSource();
		OclExpression bodyExp = anIteratorExp.getBody();
		// get code for source expression
		ISQLCode sourceCode = doSwitch(sourceExp);
		List<Guide> srcGuides = navigationMap.get(sourceExp);

		Guide guideSrc = srcGuides.get(0);
		guideSrc.reset();
		if (guideSrc.numberOfSteps() > 1) {
			guideSrc = new Guide(guideSrc.isNavigation(), guideSrc.getAlias());
			guideSrc.add(srcGuides.get(0).getSelect(), srcGuides.get(0).getFrom(),
					srcGuides.get(0).getWhere());
			guideSrc.reset();
		}

		if (operationName.equals("closure")) {
			result = handleIterClosure(anIteratorExp, bodyExp, guideSrc, srcGuides);
		}
		else {
			Guide varGuide = guideSrc;
			// evaluate the arguments
			for (Variable v : anIteratorExp.getIterator()) {
				variableMap.put(v.getName(), varGuide);
				if (anIteratorExp.getIterator().size() > 1) {
					varGuide =
							new Guide(guideSrc.isNavigation(), mySettings.getMappedModel()
									.getUniqueAlias());
					varGuide.add(guideSrc.getSelect(), guideSrc.getFrom(),
							guideSrc.getWhere());
					varGuide.reset();
				}
			}

			boolean saveAllInstances = useVariable;
			useVariable = false;
			ISQLCode arg = doSwitch(bodyExp);
			useVariable = saveAllInstances;

			if (operationName.equals("collect")) {
				result =
						handleIterCollect(anIteratorExp, bodyExp, arg, guideSrc, srcGuides,
								sourceCode);
			}
			else if (operationName.equals("select") || operationName.equals("reject")) {
				result =
						handleIterSelectReject(operationName, sourceCode, arg,
								anIteratorExp, bodyExp);
			}
			else if (operationName.equals("forAll") || operationName.equals("exists")) {
				result =
						handleIterForAllExits(operationName, sourceCode, arg, srcGuides,
								anIteratorExp.getIterator());
			}
		}

		for (Variable v : anIteratorExp.getIterator()) {
			if (variableMap.containsKey(v.getName()))
				variableMap.remove(v.getName());
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIteratorExp(IteratorExp)" + " - end - return value="
					+ result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#
	 * caseOperationCallExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp)
	 */
	public ISQLCode caseOperationCallExp(OperationCallExp anOperationCallExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("caseOperationCallExp(OperationCallExp) - start - Operation:"
							+ anOperationCallExp);
		}
		// no else.

		ISQLCode resultExp;

		OclExpression sourceExp;
		ISQLCode sourceCode;

		List<OclExpression> arguments = anOperationCallExp.getArgument();
		List<ISQLCode> args = new ArrayList<ISQLCode>();
		for (OclExpression oe : arguments) {
			args.add(doSwitch(oe));
		}

		/* Transform Code for the source of the operation call. */
		sourceExp = anOperationCallExp.getSource();
		sourceCode = doSwitch((EObject) sourceExp);

		navigationMap.put(anOperationCallExp, navigationMap.get(sourceExp));

		Operation anOperation = anOperationCallExp.getReferredOperation();

		resultExp =
				handleAllOperations(anOperation.getName(),
						anOperationCallExp.getType(), sourceExp, sourceCode, args,
						arguments);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseOperationCallExp(OperationCallExp)"
					+ " - end - return value=" + resultExp);
		}
		// no else.

		return resultExp;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #casePropertyCallExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp)
	 */
	public ISQLCode casePropertyCallExp(PropertyCallExp anPropertyCallExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("casePropertyCallExp(PropertyCallExp) - start");
		}

		List<Guide> guides = assignGuides(anPropertyCallExp);
		ISQLCode result;
		result =
				handlePropProperty(anPropertyCallExp.getReferredProperty(), guides);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("casePropertyCallExp(PropertyCallExp)"
					+ " - end - return value=" + result);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseRealLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp)
	 */
	public ISQLCode caseRealLiteralExp(RealLiteralExp aRealLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseRealLiteralExp(RealLiteralExp) - start");
		}
		// no else.

		ISQLCode template;

		template =
				new SQLCode(mySettings.getTemplateGroup().getTemplate("literal_real"));

		// parameterize the template engine
		template.addElement("value",
				new SQLString(new Double(aRealLiteralExp.getRealSymbol()).toString()));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseRealLiteralExp(RealLiteralExp)"
					+ " - end - return value=" + template.toString());
		}
		// no else.

		return template;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseStringLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp)
	 */
	public ISQLCode caseStringLiteralExp(StringLiteralExp aStringLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseStringLiteralExp(StringLiteralExp) - start");
		}
		// no else.

		ISQLCode template =
				new SQLCode(mySettings.getTemplateGroup().getTemplate("literal_string"));

		// parameterize the template engine
		template.addElement("value",
				new SQLString(aStringLiteralExp.getStringSymbol()));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseStringLiteralExp(StringLiteralExp)"
					+ " - end - return value=" + template.toString());
		}
		// no else.

		return template;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch#
	 * caseTypeLiteralExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp)
	 */
	public ISQLCode caseTypeLiteralExp(TypeLiteralExp anTypeLiteralExp) {

		assignClassGuide(anTypeLiteralExp, anTypeLiteralExp.getReferredType());
		return new SQLString(anTypeLiteralExp.getReferredType().getName());
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseVariableExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.VariableExp)
	 */
	public ISQLCode caseVariableExp(VariableExp aVariableExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseVariableExp(VariableExp) - start - Variable:"
					+ aVariableExp);
		}
		// no else.

		ISQLCode template =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(
						"literal_variable"));

		template.addElement("value", createObjectValue(variableMap.get(aVariableExp
				.getReferredVariable().getName())));

		if (aVariableExp.getType() instanceof Type
				&& !(aVariableExp.getType() instanceof PrimitiveType)) {
			List<Guide> guides = new LinkedList<Guide>();
			guides.add(assignClassGuide(aVariableExp, aVariableExp.getType()));
			navigationMap.put(aVariableExp, guides);
		}
		else {
			navigationMap.put(aVariableExp, assignGuides(aVariableExp));
		}

		if (aVariableExp.getReferredVariable().getName().equals("self"))
			navigationMap.get(aVariableExp).get(0).setAlias("self");

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseVariableExp(VariableExp)" + " - end - return value="
					+ template.toString());
		}
		// no else.

		return template;
	}

	/**
	 * Generates a code fragment for an arithmetic operation.
	 * 
	 * @param name
	 *          the name of the arithmetic operation (can be div, minus, mult and
	 *          plus)
	 * @param sourceCode
	 *          the code of the source expression (argument 1)
	 * @param firstArg
	 *          the code of the argument expression (argument 2)
	 * @return declarative code fragment for the arithmetic operation
	 */
	protected ISQLCode handleArithmeticOperation(String name,
			ISQLCode sourceCode, ISQLCode firstArg) {

		return createTwoOperandOperation("arithmetic_expression_" + name,
				sourceCode, firstArg);
	}

	/**
	 * Generates a code fragement for all operations.
	 * 
	 * @param operationName
	 *          the name of the operation
	 * @param type
	 *          the type of operation
	 * @param sourceExp
	 *          the source of operation
	 * @param sourceCode
	 *          the generated code of the source
	 * @param args
	 *          the generated code of the arguments
	 * @param arguments
	 *          the arguments of the operation
	 * @return the generated code
	 */
	protected ISQLCode handleAllOperations(String operationName, Type type,
			OclExpression sourceExp, ISQLCode sourceCode, List<ISQLCode> args,
			List<OclExpression> arguments) {

		ISQLCode resultExp = null;
		ISQLCode firstArg = null;
		if (args.size() > 0) {
			firstArg = args.get(0);
		}
		if (operationName.equals("=")) {
			resultExp =
					handleRelExpression("equals", sourceExp, sourceCode, firstArg);
		}
		else if (operationName.equals("<>")) {
			resultExp =
					handleRelExpression("nequals", sourceExp, sourceCode, firstArg);
		}
		else if (operationName.equals(">")) {
			resultExp =
					handleRelExpression("greater", sourceExp, sourceCode, firstArg);
		}
		else if (operationName.equals(">=")) {
			resultExp =
					handleRelExpression("greaterequal", sourceExp, sourceCode, firstArg);
		}
		else if (operationName.equals("<")) {
			resultExp =
					handleRelExpression("lesser", sourceExp, sourceCode, firstArg);
		}
		else if (operationName.equals("<=")) {
			resultExp =
					handleRelExpression("lesserequal", sourceExp, sourceCode, firstArg);
		}

		// arithmetic expressions
		else if (operationName.equals("+")) {
			resultExp = handleArithmeticOperation("plus", sourceCode, firstArg);
		}
		else if (operationName.equals("*")) {
			resultExp = handleArithmeticOperation("mult", sourceCode, firstArg);
		}
		else if (operationName.equals("/")) {
			resultExp = handleArithmeticOperation("div", sourceCode, firstArg);
		}
		else if (operationName.equals("-")) {
			if (args.isEmpty()) { // unary minus special case
				resultExp = handleUnaryMinus(sourceCode);
			}
			else {
				resultExp = handleArithmeticOperation("minus", sourceCode, firstArg);
			}
		}

		// unary expressions (unary minus special case above)
		else if (operationName.equals("not")) {
			resultExp = handleUnaryNot(sourceCode);
		}

		// logical expression
		else if (operationName.equals("and")) {
			resultExp = handleLogicalExpression("and", sourceCode, firstArg);
		}
		else if (operationName.equals("or")) {
			resultExp = handleLogicalExpression("or", sourceCode, firstArg);
		}
		else if (operationName.equals("xor")) {
			resultExp = handleLogicalExpression("xor", sourceCode, firstArg);
		}
		else if (operationName.equals("implies")) {
			resultExp = handleLogicalExpression("implies", sourceCode, firstArg);
		}

		// collection related operations - BASIC VALUE
		else if (operationName.equals("count")) {
			resultExp = handleCollCount(sourceCode, firstArg);
		}
		else if ((operationName.equals("size") && !(sourceExp.getType() instanceof PrimitiveType))) {
			resultExp = handleCollObjectChange("size", sourceCode);
		}
		else if (operationName.equals("sum")) {
			resultExp = handleCollObjectChange("sum", sourceCode);
		}

		// collection related operations - COMPLEX PREDICATE
		else if (operationName.equals("includes")) {
			resultExp = handleCollIncludes(sourceCode, firstArg);
		}
		else if (operationName.equals("excludes")) {
			resultExp = handleCollExcludes(sourceCode, firstArg);
		}
		else if (operationName.equals("includesAll")) {
			resultExp = handleCollIncludesAll(sourceCode, firstArg);
		}
		else if (operationName.equals("excludesAll")) {
			resultExp = handleCollExcludesAll(sourceCode, firstArg);
		}
		else if (operationName.equals("isEmpty")) {
			resultExp = handleCollIsEmpty(sourceCode);
		}
		else if (operationName.equals("notEmpty")) {
			resultExp = handleCollNotEmpty(sourceCode);
		}
		else if (operationName.equals("intersection")) {
			resultExp =
					handleCollIntersection(sourceCode, firstArg,
							(CollectionType) sourceExp.getType());
		}
		else if (operationName.equals("including")) {
			resultExp =
					handleCollIncluding(sourceCode, firstArg,
							(CollectionType) sourceExp.getType());
		}
		else if (operationName.equals("excluding")) {
			resultExp =
					handleCollExcluding(sourceCode, firstArg,
							(CollectionType) sourceExp.getType());
		}
		else if (operationName.equals("union")) {
			resultExp =
					handleCollUnion(sourceCode, firstArg,
							(CollectionType) sourceExp.getType(), navigationMap
									.get(sourceExp).get(0));
		}
		else if (operationName.equals("symmetricDifference")) {
			resultExp = handleCollSymmetricDifference(sourceCode, firstArg);
		}

		// collection related operations - MODEL TYPE QUERY
		else if (operationName.equals("allInstances")) {
			resultExp = handleAllInstances(navigationMap.get(sourceExp).get(0));
		}

		// BASIC TYPE - String operations
		else if (operationName.equals("size")) {
			resultExp = handleStringSize(sourceCode);
		}
		else if (operationName.equals("concat")) {
			resultExp = handleStringConcat(sourceCode, firstArg);
		}
		else if (operationName.equals("toUpperCase")) {
			resultExp = handleStringToUpper(sourceCode);
		}
		else if (operationName.equals("toLowerCase")) {
			resultExp = handleStringToLower(sourceCode);
		}
		else if (operationName.equals("substring")) {
			resultExp = handleStringSubstring(sourceCode, firstArg, args.get(1));
		}

		// BASIC TYPE - Real and Integer operations
		else if (operationName.equals("abs")) {
			resultExp = handleIntExpression("abs", sourceCode, null);
		}
		else if (operationName.equals("floor")) {
			resultExp = handleIntExpression("floor", sourceCode, null);
		}
		else if (operationName.equals("max")) {
			if (sourceExp.getType() instanceof CollectionType && firstArg == null) {
				resultExp = handleCollObjectChange("max", sourceCode);
			}
			else {
				resultExp = handleIntExpression("max", sourceCode, firstArg);
			}
		}
		else if (operationName.equals("min")) {
			if (sourceExp.getType() instanceof CollectionType && firstArg == null) {
				resultExp = handleCollObjectChange("min", sourceCode);
			}
			else {
				resultExp = handleIntExpression("min", sourceCode, firstArg);
			}
		}
		else if (operationName.equals("round")) {
			resultExp = handleIntExpression("round", sourceCode, null);
		}
		else if (operationName.equals("div")) {
			resultExp = handleIntExpression("div", sourceCode, firstArg);
		}
		else if (operationName.equals("mod")) {
			resultExp = handleIntExpression("mod", sourceCode, firstArg);
		}

		else if (operationName.equals("asSet")) {
			resultExp = sourceCode;
		}
		else if (operationName.equals("asSequence")) {
			resultExp = sourceCode;
		}
		else if (operationName.equals("oclIsTypeOf")) {
			Guide guide = navigationMap.get(arguments.get(0)).get(0);
			resultExp = handleOclIsTypeOf(guide, sourceCode);
		}
		else if (operationName.equals("oclIsKindOf")) {
			List<Type> parents = query_Supertypes(type);

			if (parents.size() != 1) {
				throw new IllegalStateException(
						"Illegal number of supertypes for type: " + (type).getName() + " !");
			}

			resultExp =
					handleOclIsKindOf(
							getSettings().getMappedModel().getClass(firstArg.getResult())
									.getClassGuide(),
							getSettings().getMappedModel().getClass(parents.get(0).getName())
									.getClassGuide());
		}

		else {
			return resultExp;
			// throw new UnsupportedOperationException("The operation " +
			// operationName + "is not supported.");
		}

		return resultExp;
	}

	/**
	 * Generates a code fragment for an allInstances operation.
	 * 
	 * @return declarative code fragment for the allInstances operation
	 */
	protected ISQLCode handleAllInstances(Guide guide) {

		useVariable = true;
		// return null;
		return createSelectStatement(Arrays.asList(guide));
	}

	/**
	 * Generates a code fragment for a count operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @param guide
	 *          the guide to the source expression
	 * @param firstArg
	 *          argument passed to the feature call which is used as reference
	 *          object
	 * @return declarative code fragment for the count operation
	 */
	protected ISQLCode handleCollCount(ISQLCode sourceCode, ISQLCode firstArg) {

		// ISQLCode select = createObjectValue(guide);
		if (sourceCode.getElement("object") != null) {
			String tempName = sourceCode.getElement("object").getTemplateName();
			if ("feature_call_count".equals(tempName)
					|| "feature_call_sum".equals(tempName)
					|| "feature_call_size".equals(tempName)) {
				sourceCode.changeElement("groupby", null);
				ISQLCode code =
						new SQLCode(mySettings.getTemplateGroup().getTemplate(
								tempName + "_select"));
				code.addElement("object",
						sourceCode.getElement("object").getElement("object"));
				sourceCode.changeElement("object", code);
			}
		}
		ISQLCode sqlCode =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(
						"feature_call_count"));
		sqlCode.addElement("object", sourceCode.getElement("object"));

		ISQLCode where1 =
				createTwoOperandOperation("relational_expression_equals_any",
						sourceCode.getElement("object"), firstArg);

		changeWhereStatement(sourceCode, where1);
		sourceCode.changeElement("object", sqlCode);
		return sourceCode;
	}

	/**
	 * Generates a code fragment for an excludes operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @param firstArg
	 *          argument passed to the feature call which is used as reference
	 *          object
	 * @return declarative code fragment for the excludes operation
	 */
	protected ISQLCode handleCollExcludes(ISQLCode sourceCode, ISQLCode firstArg) {

		ISQLCode template =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(
						"feature_call_excludes"));

		template.addElement("operand1", sourceCode);
		template.changeElement("operand2", firstArg);

		return template;
	}

	/**
	 * Generates a code fragment for an excludesAll operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @param firstArg
	 *          argument passed to the feature call which is used as reference
	 *          collection
	 * @return declarative code fragment for the excludesAll operation
	 */
	protected ISQLCode handleCollExcludesAll(ISQLCode sourceCode,
			ISQLCode firstArg) {

		return createTwoOperandOperation("feature_call_excludesall", sourceCode,
				firstArg);
	}

	/**
	 * Generates a code fragment for an excluding operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @param firstArg
	 *          argument passed to the feature call which is used as reference
	 *          object
	 * @param collType
	 *          collection type to determine the template to use (BagType,
	 *          SequenceType, SetType)
	 * @return declarative code fragment for the excluding operation
	 */
	protected ISQLCode handleCollExcluding(ISQLCode sourceCode,
			ISQLCode firstArg, CollectionType collType) {

		assert (collType != null) : "including() collType may not be null";

		ITemplate template = null;

		if (collType instanceof BagType) {
			template =
					mySettings.getTemplateGroup().getTemplate(
							"feature_call_excluding_bag");
		}
		else if (collType instanceof SequenceType) {
			template =
					mySettings.getTemplateGroup().getTemplate(
							"feature_call_excluding_sequence");
		}
		else if (collType instanceof SetType) {
			template =
					mySettings.getTemplateGroup().getTemplate(
							"feature_call_excluding_set");
		}
		else {
			throw new IllegalStateException(
					"Unhandled collection type for excluding operation!");
		}

		ISQLCode returnValue = new SQLCode(template);
		Guide guide =
				mySettings.getMappedModel()
						.getClass(collType.getElementType().getName()).getClassGuide();
		guide.reset();
		returnValue.addElement("operand2", firstArg);
		returnValue.addElement("element", new SQLString(guide.getSelect()));
		returnValue.addElement("operand1", sourceCode);

		return returnValue;
	}

	/**
	 * Generates a code fragment for an includes operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @param firstArg
	 *          argument passed to the feature call which is used as reference
	 *          object
	 * @return declarative code fragment for the includes operation
	 */
	protected ISQLCode handleCollIncludes(ISQLCode sourceCode, ISQLCode firstArg) {

		ISQLCode template =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(
						"feature_call_includes"));

		template.addElement("operand1", sourceCode);
		template.addElement("operand2", firstArg);

		return template;
	}

	/**
	 * Generates a code fragment for an includesAll operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @param firstArg
	 *          argument passed to the feature call which is used as reference
	 *          collection
	 * @return declarative code fragment for the includesAll operation
	 */
	protected ISQLCode handleCollIncludesAll(ISQLCode sourceCode,
			ISQLCode firstArg) {

		return createTwoOperandOperation("feature_call_includesall", sourceCode,
				firstArg);
	}

	/**
	 * Generates a code fragment for an including operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @param firstArg
	 *          argument passed to the feature call which is used as reference
	 *          object
	 * @param collType
	 *          collection type to determine the template to use (BagType,
	 *          SequenceType, SetType)
	 * @return declarative code fragment for the including operation
	 */
	protected ISQLCode handleCollIncluding(ISQLCode sourceCode,
			ISQLCode firstArg, CollectionType collType) {

		assert (collType != null) : "including() collType may not be null";

		ITemplate template = null;

		if (collType instanceof BagType) {
			template =
					mySettings.getTemplateGroup().getTemplate(
							"feature_call_including_bag");
		}
		else if (collType instanceof SequenceType) {
			template =
					mySettings.getTemplateGroup().getTemplate(
							"feature_call_including_sequence");
		}
		else if (collType instanceof SetType) {
			template =
					mySettings.getTemplateGroup().getTemplate(
							"feature_call_including_set");
		}
		else {
			throw new IllegalStateException(
					"Unhandled collection type for including operation!");
		}

		ISQLCode result = new SQLCode(template);

		result.addElement("operand2", firstArg);
		result.addElement("operand1", sourceCode);

		return result;
	}

	/**
	 * Generates a code fragment for a intersection operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @param firstArg
	 *          argument passed to the feature call which is used as reference
	 *          collection
	 * @param collType
	 *          collection type to determine the template to use (BagType,
	 *          SetType)
	 * @return declarative code fragment for the intersection operation
	 */
	protected ISQLCode handleCollIntersection(ISQLCode sourceCode,
			ISQLCode firstArg, CollectionType collType) {

		assert (collType != null) : "intersection() collType may not be null";

		String template = null;

		if (collType instanceof BagType) {
			template = "feature_call_intersection_bag";
		}
		else if (collType instanceof SetType) {
			template = "feature_call_intersection_set";
		}
		else {
			throw new IllegalStateException(
					"Unhandled collection type for intesection operation!");
		}

		return createTwoOperandOperation(template, sourceCode, firstArg);
	}

	/**
	 * Generates a code fragment for a isEmpty operation
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @return declarative code fragment for the isEmpty operation
	 */
	protected ISQLCode handleCollIsEmpty(ISQLCode sourceCode) {

		return createOperandOperation("feature_call_isempty", sourceCode);
	}

	/**
	 * Generates a code fragment for a notEmpty operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @return declarative code fragment for the notEmpty operation
	 */
	protected ISQLCode handleCollNotEmpty(ISQLCode sourceCode) {

		return createOperandOperation("feature_call_notempty", sourceCode);
	}

	/**
	 * Generates a code fragment for a collection operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @return declarative code fragment for the collection operation
	 */
	protected ISQLCode handleCollObjectChange(String name, ISQLCode sourceCode) {

		assert (sourceCode != null) : "handleCollObjectChange(): sourceCode may not be null";

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("handleCollObjectChange(String name, ISQLCode sourceCode) - start - Type:"
							+ name + ", Code:" + sourceCode);
		}

		if (sourceCode.getElement("object") != null) {
			String tempName = sourceCode.getElement("object").getTemplateName();
			if ((name.equals("sum") || name.equals("size"))
					&& ("feature_call_count".equals(tempName)
							|| "feature_call_sum".equals(tempName) || "feature_call_size"
							.equals(tempName))) {
				ISQLCode code =
						new SQLCode(mySettings.getTemplateGroup().getTemplate(
								tempName + "_select"));
				code.addElement("object",
						sourceCode.getElement("object").getElement("object"));
				sourceCode.changeElement("object", code);
			}
		}
		if (sourceCode.getElement("object") != null) {
			changeObjectVariable("feature_call_" + name, sourceCode);
			return sourceCode;
		}
		else if (sourceCode.getElement("operand") != null) {
			return createOperandOperation("feature_call_" + name, sourceCode);
		}
		else {
			ISQLCode code =
					new SQLCode(mySettings.getTemplateGroup().getTemplate(
							"feature_call_" + name));
			code.addElement("object", sourceCode);
			return code;
		}

	}

	/**
	 * Generates a code fragment for a symmetricDifference operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @param firstArg
	 *          the declarative code fragment representing the collection passed
	 *          to the feature call which is used to build the symmetric
	 *          difference
	 * @return declarative code fragment for the symmetricDifference operation
	 */
	protected ISQLCode handleCollSymmetricDifference(ISQLCode sourceCode,
			ISQLCode firstArg) {

		return createTwoOperandOperation("feature_call_symmetricdifference",
				sourceCode, firstArg);
	}

	/**
	 * Generates a declarative code fragment for an union operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @param firstArg
	 *          the declarative code fragment representing the collection passed
	 *          to the feature call which is used to build the union of the two
	 *          collections
	 * @param collType
	 *          collection type to determine the template to use (BagType,
	 *          SequenceType, SetType)
	 * @return declarative code fragment for the union operation
	 */
	protected ISQLCode handleCollUnion(ISQLCode sourceCode, ISQLCode firstArg,
			CollectionType collType, Guide guide) {

		assert (collType != null) : "including() collType may not be null";

		guide.reset();

		String template = "feature_call_union_";

		if (collType instanceof BagType) {
			template += "bag";
		}
		else if (collType instanceof SequenceType) {
			template += "sequence";
		}
		else if (collType instanceof SetType) {
			template += "set";
		}
		else {
			throw new IllegalStateException(
					"Unhandled collection type for union operation!");
		}

		ISQLCode result = createTwoOperandOperation(template, sourceCode, firstArg);
		ISQLCode object = createObjectValue(guide);
		result.addElement("object", object);

		return result;
	}

	/**
	 * Generate for int operations
	 * 
	 * @param name
	 *          the last part of template name
	 * @param sourceCode
	 *          the first code operand
	 * @param firstArg
	 *          the second code operand
	 * @return generated code for the int operations.
	 */
	protected ISQLCode handleIntExpression(String name, ISQLCode sourceCode,
			ISQLCode firstArg) {

		return createTwoOperandOperation("feature_call_int_" + name, sourceCode,
				firstArg);
	}

	/**
	 * Generates a declarative code fragment for a collect iterator.
	 * 
	 * @param anIteratorExp
	 *          the iterator expression
	 * @param bodyExp
	 *          the body expression
	 * @param arg
	 *          the generated body code
	 * @param guideSrc
	 *          the source guide
	 * @param srcGuides
	 *          the source guides
	 * @param sourceCode
	 *          the generated source code
	 * @return declarative code fragment for the collect iterator
	 */
	protected ISQLCode handleIterCollect(IteratorExp anIteratorExp,
			OclExpression bodyExp, ISQLCode arg, Guide guideSrc,
			List<Guide> srcGuides, ISQLCode sourceCode) {

		// TODO: optimize collect for the optimize way
		List<Guide> nav = new LinkedList<Guide>();
		nav.addAll(navigationMap.get(bodyExp));
		nav.addAll(srcGuides);
		navigationMap.put(anIteratorExp, nav);
		Guide guideBody = navigationMap.get(bodyExp).get(0);
		guideBody.reset();
		guideSrc.reset();
		if (srcGuides.size() == 1 && !guideSrc.isNavigation()
				&& guideSrc.getSelect().equals(guideSrc.getWhere())) {
			sourceCode = null;
		}
		boolean temp =
				Boolean.valueOf(mySettings.getTemplateGroup()
						.getTemplate("check_database_references").toString());
		if (temp && guideSrc.getAlias().equals(guideBody.getAlias())
				&& !guideSrc.getFrom().equals(guideBody.getFrom())) {
			guideBody.setAlias(mySettings.getMappedModel().getUniqueAlias());
			arg = createSelectStatement(navigationMap.get(bodyExp));
			arg.changeElement("object", doSwitch(bodyExp));
		}
		else if (!temp) {
			guideSrc = srcGuides.get(0);
		}
		return createCollectStatement(arg, sourceCode, guideSrc, bodyExp);
	}

	/**
	 * Generates a declarative code fragment for a forAll or exists iterator.
	 * 
	 * @param operationName
	 *          the name of the operation
	 * @param srcCode
	 *          the generated source code
	 * @param where
	 *          the generated body code
	 * @param srcGuides
	 *          the source guides
	 * @param variables
	 *          all iteratpr variables
	 * @return declarative code fragment for the collect iterator
	 */
	protected ISQLCode handleIterForAllExits(String operationName,
			ISQLCode srcCode, ISQLCode where, List<Guide> srcGuides,
			List<Variable> variables) {

		where = handleIterOperation(operationName + "_where", where);

		ISQLCode join = srcCode.getElement("join");
		if (variables.size() > 1) {
			for (int i = 1; i < variables.size(); i++) {
				if (join != null) {
					ISQLCode newJoin =
							createJoinStatement(variableMap.get(variables.get(i).getName()),
									srcGuides.get(1));
					newJoin.changeElement("join", join.getElement("join"));
					join.changeElement("join", newJoin);
					join = newJoin;
				}
				else {	
					ISQLCode newJoin;
					if (srcGuides.size() > 1) {
					newJoin = createJoinStatement(variableMap.get(variables.get(i).getName()),
								srcGuides.get(1));
					} else {
						newJoin = createJoinStatement(variableMap.get(variables.get(i).getName()),
								null);
					}
							
					srcCode.changeElement("join", newJoin);
					join = newJoin;
				}
			}
		}
		changeWhereStatement(srcCode, where);
		return handleIterOperation(operationName, srcCode);
	}

	/**
	 * Generates a declarative code fragment for a select or reject iterator.
	 * 
	 * @param operationName
	 *          the name of the operation
	 * @param sourceCode
	 *          the generation source code
	 * @param where
	 *          the generated body code
	 * @param anIteratorExp
	 *          the iterator expression
	 * @param bodyExp
	 *          the body expression
	 * @return declarative code fragment for the collect iterator
	 */
	protected ISQLCode handleIterSelectReject(String operationName,
			ISQLCode sourceCode, ISQLCode where, IteratorExp anIteratorExp,
			OclExpression bodyExp) {

		ISQLCode expression = handleIterOperation(operationName, where);
		navigationMap.put(anIteratorExp, navigationMap.get(bodyExp));
		changeWhereStatement(sourceCode, expression);
		return sourceCode;
	}

	/**
	 * Generates a declarative code fragment for a closure iterator.
	 * 
	 * @param anIteratorExp
	 *          the iterator expression
	 * @param bodyExp
	 *          the body expression
	 * @param guideSrc
	 *          the source guide
	 * @param srcGuides
	 *          the source guides
	 * @return declarative code fragment for the collect iterator
	 */
	protected ISQLCode handleIterClosure(IteratorExp anIteratorExp,
			OclExpression bodyExp, Guide guideSrc, List<Guide> srcGuides) {

		String alias = guideSrc.getAlias();
		guideSrc.setAlias(mySettings.getMappedModel().getUniqueAlias());

		closure = true;
		ISQLCode code =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(
						"common_table_expression"));
		commonTableExpressions.add(code);
		ISQLCode cteName = new SQLString(constraintName + "_" + cteNumber++);
		Guide closureGuide =
				new Guide(false, mySettings.getMappedModel().getUniqueAlias());
		closureGuide.add("variable2", cteName.getResult(), "variable1");
		closureGuide.reset();

		boolean saveAllInstances = useVariable;
		useVariable = true;
		Map<OclExpression, List<Guide>> navMap =
				new HashMap<OclExpression, List<Guide>>();
		navMap.putAll(navigationMap);
		for (Variable v : anIteratorExp.getIterator()) {
			variableMap.put(v.getName(), guideSrc);
		}
		ISQLCode arg1 = doSwitch(bodyExp);
		if (arg1.getElement("object") == null) {
			arg1 = createSelectStatement(Arrays.asList(guideSrc));
		}
		arg1.changeElement("object",
				createObjectValue(navigationMap.get(bodyExp).get(0), guideSrc));
		navigationMap.clear();
		navigationMap.putAll(navMap);
		for (Variable v : anIteratorExp.getIterator()) {
			variableMap.remove(v.getName());
		}
		for (Variable v : anIteratorExp.getIterator()) {
			variableMap.put(v.getName(), closureGuide);
		}
		ISQLCode arg2 = doSwitch(bodyExp);
		closureGuide.reset();
		Guide closureGuide1 = new Guide(false, closureGuide.getAlias());
		closureGuide1.add("variable1", cteName.getResult(), "variable2");
		closureGuide1.reset();
		arg2.changeElement("object", this.createObjectValue(
				navigationMap.get(bodyExp).get(0), closureGuide1));
		useVariable = saveAllInstances;
		guideSrc.setAlias(alias);
		navMap.clear();
		code.addElement("constraint_name", cteName);
		code.addElement("recursive", arg2);
		code.addElement("non_recursive", arg1);

		LinkedList<Guide> guides = new LinkedList<Guide>();
		guides.add(closureGuide);
		guides.addAll(srcGuides);
		closure = false;
		navigationMap.put(anIteratorExp, guides);
		return createSelectStatement(navigationMap.get(anIteratorExp));
	}

	/**
	 * Generates a declarative code fragment for a select iterator
	 * 
	 * @param name
	 *          the name of the iter operation
	 * @param guide
	 *          the Guide to the source expression
	 * @param arg
	 *          argument passed to the iterator
	 * @return declarative code fragment for the select iterator
	 */
	protected ISQLCode handleIterOperation(String name, ISQLCode arg) {

		return createOperandOperation("feature_call_" + name, arg);
	}

	/**
	 * Generates a declarative code fragment for a logical expression.
	 * 
	 * @param name
	 *          name of the logical expression (and, or, xor, implies)
	 * @param sourceCode
	 *          declarative code fragment of the first expression
	 * @param firstArg
	 *          declarative code fragment of the second expression
	 * @return declarative code fragment for the logical expression
	 */
	protected ISQLCode handleLogicalExpression(String name, ISQLCode sourceCode,
			ISQLCode firstArg) {

		return createTwoOperandOperation("logical_expression_" + name, sourceCode,
				firstArg);
	}

	/**
	 * Generates a declarative code fragment for a oclIsKindOf feature call.
	 * 
	 * @param guide
	 *          the guide to the classifier type
	 * @return declarative code fragment for the oclIsKindOf feature call
	 */
	protected ISQLCode handleOclIsTypeOf(Guide guide, ISQLCode sourceCode) {

		guide.reset();

		ISQLCode template =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(
						"feature_call_oclistypeof"));

		template.addElement("from", new SQLString(guide.getFrom()));
		template.addElement("object", new SQLString(guide.getSelect()));
		template.addElement("object2", sourceCode);
		template.addElement("alias", new SQLString(guide.getAlias()));

		return template;
	}

	/**
	 * Generates a declarative code fragment for a oclIsTypeOf feature call.
	 * 
	 * @param typeGuide
	 *          the guide to the classifier type
	 * @param supertypeGuide
	 *          the guide to the classifiers supertype
	 * @return declarative code fragment for the oclIsTypeOf feature call
	 */
	protected ISQLCode handleOclIsKindOf(Guide typeGuide, Guide supertypeGuide) {

		typeGuide.reset();
		if (supertypeGuide != null)
			supertypeGuide.reset();

		ISQLCode template =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(
						"feature_call_ocliskindof"));

		template.addElement("from", new SQLString(typeGuide.getFrom()));
		template.addElement("object", new SQLString(typeGuide.getSelect()));
		template.addElement("alias", new SQLString(typeGuide.getAlias()));
		template.addElement("from2", (supertypeGuide != null) ? new SQLString(
				supertypeGuide.getFrom()) : null);

		return template;
	}

	/**
	 * Generates a declarative code fragment for a relational expression.
	 * 
	 * @param name
	 *          name of the relational expression (equal, nequal, greater, lesser,
	 *          greaterequal, lesserequal)
	 * @param srcExp
	 *          the OclExpression representing the first argument used to
	 *          determine the operand types
	 * @param operand1
	 *          declarative code fragment of the first operand
	 * @param operand2
	 *          declarative code fragment of the second operand
	 * @return declarative code fragment for the equals operation
	 */
	protected ISQLCode handleRelExpression(String name, OclExpression srcExp,
			ISQLCode operand1, ISQLCode operand2) {

		StringBuffer templateName = new StringBuffer("relational_expression_");
		Type attrType = null;

		templateName.append(name);
		templateName.append("_");

		if (srcExp instanceof OperationCallExp) {
			attrType = srcExp.getType();
		}
		else if (srcExp instanceof PropertyCallExp) {
			attrType = ((PropertyCallExp) srcExp).getReferredProperty().getType();
		}
		else if (srcExp instanceof PrimitiveLiteralExp) {
			attrType = ((PrimitiveLiteralExp) srcExp).getType();
		}
		else if (srcExp instanceof VariableExp) {
			attrType = ((VariableExp) srcExp).getType();
		}
		else {
			throw new IllegalStateException(
					"Unhandled attribute type for relational expression: "
							+ srcExp.getClass().getName() + "!");
		}

		if (attrType instanceof PrimitiveType) {
			if (((PrimitiveType) attrType).getKind() == PrimitiveTypeKind.BOOLEAN) {
				templateName.append("boolean");
			}
			else if (((PrimitiveType) attrType).getKind() == PrimitiveTypeKind.INTEGER) {
				templateName.append("integer");
			}
			else if (((PrimitiveType) attrType).getKind() == PrimitiveTypeKind.REAL) {
				templateName.append("real");
			}
			else if (((PrimitiveType) attrType).getKind() == PrimitiveTypeKind.STRING) {
				templateName.append("string");
			}
			else {
				throw new IllegalStateException(
						"Unhandled primitive type for relational expression!");
			}
		}
		/*
		 * No different between CollectionTypes and any else if (attrType instanceof
		 * CollectionType) { if (attrType instanceof SetType) {
		 * templateName.append("set"); } else if (attrType instanceof SequenceType)
		 * { templateName.append("sequence"); } else if (attrType instanceof
		 * BagType) { templateName.append("bag"); } else if (attrType instanceof
		 * OrderedSetType) { templateName.append("orderedset"); } else { throw new
		 * IllegalStateException(
		 * "Unhandled collection type for relational expression!"); } }
		 */
		else {
			templateName.append("any");
		}

		return createTwoOperandOperation(templateName.toString(), operand1,
				operand2);
	}

	/**
	 * Generates a declarative code fragment for a concat operation on strings.
	 * 
	 * @param sourceCode
	 *          declarative code fragment of the first argument (usually a simple
	 *          string)
	 * @param firstArg
	 *          declarative code fragment of the second argument (usually a simple
	 *          string)
	 * @return declarative code fragment for the concat operation
	 */
	protected ISQLCode handleStringConcat(ISQLCode sourceCode, ISQLCode firstArg) {

		return createTwoOperandOperation("feature_call_string_concat", sourceCode,
				firstArg);
	}

	/**
	 * Generates a declarative code fragment for a size operation on strings.
	 * 
	 * @param sourceCode
	 *          declarative code fragment of the operand (the string to get the
	 *          size from)
	 * @return declarative code fragment for the size operation
	 */
	protected ISQLCode handleStringSize(ISQLCode sourceCode) {

		return createOperandOperation("feature_call_string_size", sourceCode);
	}

	/**
	 * Generates a declarative code fragment for a substring operation.
	 * 
	 * @param sourceCode
	 *          declarative code fragment of the operand (the string to build the
	 *          substring from)
	 * @param firstArg
	 *          the start of the substring
	 * @param isqlCode
	 *          the end of the substring
	 * @return declarative code fragment for the substring operation
	 */
	protected ISQLCode handleStringSubstring(ISQLCode sourceCode,
			ISQLCode firstArg, ISQLCode isqlCode) {

		ISQLCode template =
				createOperandOperation("feature_call_string_substring", sourceCode);
		template.addElement("start", firstArg);
		template.addElement("end", isqlCode);

		return template;
	}

	/**
	 * Generates a declarative code fragment for a toLower operation on strings.
	 * 
	 * @param sourceCode
	 *          declarative code fragment of the operand
	 * @return declarative code fragment for the toLower operation
	 */
	protected ISQLCode handleStringToLower(ISQLCode sourceCode) {

		return createOperandOperation("feature_call_string_tolower", sourceCode);
	}

	/**
	 * Generates a declarative code fragment for a toUpper operation on strings.
	 * 
	 * @param sourceCode
	 *          declarative code fragment of the operand
	 * @return declarative code fragment for the toUpper operation
	 */
	protected ISQLCode handleStringToUpper(ISQLCode sourceCode) {

		return createOperandOperation("feature_call_string_toupper", sourceCode);
	}

	/**
	 * Generates a declarative code fragment for a unary minus operation.
	 * 
	 * @param sourceCode
	 *          declarative code fragment of the operand
	 * @return declarative code fragment for the unary minus operation
	 */
	protected ISQLCode handleUnaryMinus(ISQLCode sourceCode) {

		return createOperandOperation("unary_expression_minus", sourceCode);
	}

	/**
	 * Generates a declarative code fragment for a unary not operation.
	 * 
	 * @param sourceCode
	 *          declarative code fragment of the operand
	 * @return declarative code fragment for the unary not operation
	 */
	protected ISQLCode handleUnaryNot(ISQLCode sourceCode) {

		return createOperandOperation("unary_expression_not", sourceCode);
	}

	/**
	 * Generate code fragment for property
	 * 
	 * @param property
	 *          the property
	 * @param guides
	 *          the guides for the property
	 * @return the generated code
	 */
	protected ISQLCode handlePropProperty(Property property, List<Guide> guides) {

		Guide guide = guides.get(0);
		guide.reset();
		// attribute access without navigation
		if (guides.size() == 1) {
			ISQLCode template = createObjectValue(guide);

			// special case for Boolean attributes: e.g. expand 'attribute' to
			// 'attribute = 1' in SQL
			if (property.getType().getName().equals("Boolean")) {
				ISQLCode attr = template;
				template =
						new SQLCode(mySettings.getTemplateGroup().getTemplate(
								"feature_call_attribute_boolean"));
				template.addElement("attribute", attr);
			}

			return template;
		}
		// attribute access with navigation
		else {
			return createSelectStatement(guides);
		}
	}

	/**
	 * Resets the DeclarativeCodeGenerator to initial values. Must be used, if
	 * multiple invariants will be translated by one code generator object.
	 */
	public void reset() {

		mySettings.getMappedModel().resetUniqueAlias();
		navigationMap.clear();
		variableMap.clear();
		useVariable = false;
		commonTableExpressions.clear();
		constraintName = "";
		cteNumber = 1;
		closure = false;
	}

	/**
	 * Returns all super types of the type
	 * 
	 * @param type
	 *          the type
	 * @return a list of all supertypes
	 */
	private List<Type> query_Supertypes(Type type) {

		List<Type> parents = new ArrayList<Type>();
		for (Type t : type.getSuperType()) {
			if (t instanceof AnyType)
				continue;
			parents.add(t);
			parents.addAll(query_Supertypes(t));
		}
		return parents;
	}

	/**
	 * Assigns a class guide to the given Type which are used during the code
	 * generation process.
	 * 
	 * @param exp
	 *          the OclExpression to reaccess the generated guide
	 * @param type
	 *          the type to assign the Guide
	 * @returm the class guide
	 */
	protected Guide assignClassGuide(OclExpression exp, Type type) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("assignClassGuide(OclExpression exp, Type type) - start - Expression:"
							+ exp + ", Type:" + type);
		}

		Guide guide;
		if (type instanceof CollectionType) {
			guide =
					mySettings.getMappedModel()
							.getClass(((CollectionType) type).getElementType().getName())
							.getClassGuide();
		}
		else {
			guide =
					mySettings.getMappedModel().getClass(type.getName()).getClassGuide();
		}
		String saveAlias = null;
		try {
			saveAlias = guide.getAlias();
		} catch (NullPointerException e) {
		}
		guide.setAlias(mySettings.getMappedModel().getUniqueAlias());
		Guide guide1 = new Guide(guide);
		guide.setAlias(saveAlias);
		List<Guide> guides = new LinkedList<Guide>();
		guides.add(guide1);
		navigationMap.put(exp, guides);
		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("assignClassGuide(OclExpression exp, Type type) - end - Guide:"
							+ guide1);
		}
		return guide1;
	}

	/**
	 * Change the where part of the select code
	 * 
	 * @param select
	 *          the select code
	 * @param where
	 *          the extra where code
	 */
	protected void changeWhereStatement(ISQLCode select, ISQLCode where) {

		select.changeElement("where",
				combinedWhereStatement(where, select.getElement("where")));
	}

	/**
	 * Generate a where statement
	 * 
	 * @param guide1
	 *          the first argument
	 * @param guide2
	 *          the second argument
	 * @return the where statement
	 */
	protected ISQLCode createWhereStatement(Guide guide1, Guide guide2) {

		ISQLCode where =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(
						"feature_call_navigation_where"));
		where.addElement("alias1", new SQLString(guide2.getAlias()));
		where.addElement("where", new SQLString(guide2.getWhere()));
		where.addElement("alias2", new SQLString(guide1.getAlias()));
		where.addElement("object", new SQLString(guide1.getSelect()));
		return where;
	}

	/**
	 * Generates a where clause with two operands.
	 * 
	 * @param where1
	 *          the new operand
	 * @param where2
	 *          the operand which will be exists
	 * @return the new where clause
	 */
	protected ISQLCode combinedWhereStatement(ISQLCode where1, ISQLCode where2) {

		if (where2 != null) {
			return handleLogicalExpression("and", where2, where1);
		}
		else {
			return where1;
		}
	}

	/**
	 * Generate a join statement.
	 * 
	 * @param from
	 *          the guide for the new view
	 * @param where
	 *          the guide to bind the two tables
	 * @return the join statement
	 */
	protected ISQLCode createJoinStatement(Guide from, Guide where) {

		ISQLCode template =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(
						"feature_call_navigation_join"));
		ISQLCode fromCode =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(
						"feature_call_navigation_from"));
		fromCode.addElement("from", new SQLString(from.getFrom()));
		fromCode.addElement("object", new SQLString(from.getSelect()));
		if (!from.getWhere().equals(from.getSelect())) {
			fromCode.addElement("where", new SQLString(from.getWhere()));
		}
		template.addElement("alias", new SQLString(from.getAlias()));
		template.addElement("from", fromCode);
		if (where != null) template.addElement("where", createWhereStatement(from, where));
		return template;
	}

	/**
	 * Generate a join statement with sub joins for the guides
	 * 
	 * @param guides
	 *          the guides
	 * @return the join statement from guides.get(0) with the subjoins
	 */
	protected ISQLCode createJoinStatements(List<Guide> guides) {

		ISQLCode joinTemplate = null;
		for (int i = guides.size() - 1; i > 0; i--) {
			Guide guide = guides.get(i);
			guide.reset();
			ISQLCode template = createJoinStatement(guide, guides.get(i - 1));
			if (joinTemplate != null) {
				template.addElement("join", joinTemplate);
			}
			joinTemplate = template;
		}
		return joinTemplate;
	}

	/**
	 * Generate the object value.
	 * 
	 * @param guide
	 *          the guide for object
	 * @return the code fragment.
	 */
	protected ISQLCode createObjectValue(Guide guide) {

		guide.reset();
		ISQLCode select =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(
						"feature_call_attribute_context"));
		select.addElement("alias", new SQLString(guide.getAlias()));
		select.addElement("attribute", new SQLString(guide.getSelect()));
		return select;
	}

	/**
	 * Generate the object value.
	 * 
	 * @param guide1
	 *          the guide for object
	 * @param guide2
	 *          the guide for object
	 * @return the code fragment.
	 */
	protected ISQLCode createObjectValue(Guide guide1, Guide guide2) {

		guide1.reset();
		ISQLCode select = createObjectValue(guide2);
		select.addElement("alias2", new SQLString(guide1.getAlias()));
		select.addElement("attribute2", new SQLString(guide1.getSelect()));
		return select;
	}

	/**
	 * Creates a declarative code fragment for a navigation described by the Guide
	 * objects in the guides parameter.
	 * 
	 * @param guides
	 *          the guides which are describing the navigation
	 * @return declarative code fragment for a navigation in the target model
	 */
	protected ISQLCode createSelectStatement(List<Guide> guides) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createNavigation(List<Guide> guides) - start - Guides:"
					+ guides);
		}

		ISQLCode template;

		LinkedList<Guide> steps = new LinkedList<Guide>();
		LinkedList<String> aliase = new LinkedList<String>();
		aliase.add(guides.get(0).getAlias());
		if (guides.size() > 1
				&& aliase.contains(guides.get(guides.size() - 1).getAlias())) {
			guides.get(guides.size() - 1).setAlias(
					mySettings.getMappedModel().getUniqueAlias());
		}
		aliase.add(guides.get(guides.size() - 1).getAlias());
		for (Guide guide : guides) {
			guide.reset();
			if (aliase.contains(guide.getAlias()) && guides.indexOf(guide) != 0
					&& guides.indexOf(guide) != guides.size() - 1) {
				guide.setAlias(mySettings.getMappedModel().getUniqueAlias());
			}
			aliase.add(guide.getAlias());
			for (int k = 0; k < guide.numberOfSteps(); k++, guide.next()) {

				Guide newGuide = new Guide(guide.isNavigation(), guide.getAlias());
				newGuide.add(guide.getSelect(), guide.getFrom(), guide.getWhere());
				newGuide.reset();
				steps.add(newGuide);
			}
		}

		Guide guide = steps.get(0);
		guide.reset();
		LinkedList<Guide> joinGuides = new LinkedList<Guide>();
		joinGuides.addAll(steps);
		if (!closure)
			joinGuides.removeLast();
		template =
				new SQLCode(mySettings.getTemplateGroup().getTemplate(
						"feature_call_navigation_select"));
		template.addElement("object", createObjectValue(guide));
		template.addElement("alias", new SQLString(guide.getAlias()));
		template.addElement("join", createJoinStatements(joinGuides));
		template.addElement("from", new SQLString(guide.getFrom()));
		if (!useVariable && steps.size() >= 2) {
			template.addElement(
					"where",
					createWhereStatement(steps.get(steps.size() - 1),
							steps.get(steps.size() - 2)));
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createNavigation(List<Guide> guides) - end - Navigation:"
					+ template);
		}

		return template;
	}

	/**
	 * Assigns guides to the given OclExpression which are used during the code
	 * generation process.
	 * 
	 * @param exp
	 *          the OclExpression to assign the Guides for.
	 */
	protected List<Guide> assignGuides(OclExpression exp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("assignGuides(OclExpression exp) - start - Expression:"
					+ exp);
		}

		if (navigationMap.containsKey(exp)) {
			return navigationMap.get(exp);
		}

		Guide guide;
		LinkedList<Guide> guides = new LinkedList<Guide>();
		String featureName; // used for attribute/association end names

		if (exp instanceof PropertyCallExp) {
			PropertyCallExp propertyExp = (PropertyCallExp) exp;
			featureName = propertyExp.getReferredProperty().getName();
			OclExpression sourceExp = propertyExp.getSource();
			Type sourceType = propertyExp.getSourceType();
			IMappedClass mc =
					mySettings.getMappedModel().getClass(sourceType.getName());
			boolean findAssociation = false;
			if (propertyExp.getReferredProperty() instanceof AssociationProperty) {
				findAssociation = true;
			}
			else if (propertyExp.getReferredProperty().getType() instanceof CollectionType) {
				findAssociation =
						mySettings.getMappedModel().isClass(
								((CollectionType) propertyExp.getReferredProperty().getType())
										.getElementType().getName());
			}
			else {
				findAssociation =
						mySettings.getMappedModel().isClass(
								propertyExp.getReferredProperty().getType().getName());
			}
			if (findAssociation) {
				guide = mc.getAssociationEndGuide(featureName);
			}
			else {
				guide = mc.getAttributeGuide(featureName);
			}
			guide.setAlias("");
			guide = new Guide(guide);
			guides.add(guide);
			guides.addAll(assignGuides(sourceExp));
			if (sourceExp instanceof VariableExp) {
				/*
				 * if (!guide.isNavigation()) { guide.reset(); Guide varGuide =
				 * guides.getLast(); varGuide.reset();
				 * guide.setAlias(mySettings.getMappedModel().getUniqueAlias());
				 * //guides.getLast().reset();
				 * //guide.setAlias(guides.removeLast().getAlias()); } else {
				 */
				guide.reset();
				Guide varGuide = guides.getLast();
				varGuide.reset();
				if (guide.getWhere().equals(varGuide.getSelect())
						&& guide.getWhere().equals(varGuide.getWhere())) {
					guide.setAlias(guides.removeLast().getAlias());
				}
				else {
					guide.setAlias(mySettings.getMappedModel().getUniqueAlias());
				}
				// }
			}
			else {
				guide.setAlias(mySettings.getMappedModel().getUniqueAlias());
			}
		}
		else if (exp instanceof VariableExp) {
			guides.add(variableMap.get(((VariableExp) exp).getReferredVariable()
					.getName()));
		}

		navigationMap.put(exp, guides);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("assignGuides(OclExpression exp) - end - Guides:" + guides);
		}

		return guides;
	}

	/**
	 * Generate the fragment code of a two operand operation.
	 * 
	 * @param templateName
	 *          the name of the template
	 * @param operand1
	 *          the first argument
	 * @param operand2
	 *          the second argument
	 * @return the fragment code
	 */
	private ISQLCode createTwoOperandOperation(String templateName,
			ISQLCode operand1, ISQLCode operand2) {

		ITemplate template =
				mySettings.getTemplateGroup().getTemplate(templateName);
		ISQLCode code = new SQLCode(template);
		code.addElement("operand1", operand1);
		if (operand2 != null) {
			code.addElement("operand2", operand2);
		}
		return code;
	}

	/**
	 * Generate the fragment code of a single operand operation.
	 * 
	 * @param templateName
	 *          the name of the template
	 * @param operand
	 *          the argument
	 * @return the fragment code
	 */
	private ISQLCode createOperandOperation(String templateName, ISQLCode operand) {

		ITemplate template =
				mySettings.getTemplateGroup().getTemplate(templateName);
		ISQLCode code = new SQLCode(template);
		code.addElement("operand", operand);
		return code;
	}

	/**
	 * Generate a Select statement for the operation collect.
	 * 
	 * @param sourceCode
	 *          the sourceCode of the statement
	 * @param bodyCode
	 *          the body of the collect statement
	 * @return the fragment code
	 */
	private ISQLCode createCollectStatement(ISQLCode sourceCode,
			ISQLCode bodyCode, Guide srcGuide, Guide bodyGuide) {

		ISQLCode result = null;
		boolean temp =
				Boolean.valueOf(mySettings.getTemplateGroup()
						.getTemplate("check_database_references").toString());

		if (bodyCode != null && bodyCode.getElement("from") != null) {
			result = bodyCode;
			ISQLCode join = result;
			while (join.getElement("join") != null) {
				join = join.getElement("join");
			}
			if (result.getElement("where") != null) {
				if (result.getElement("where").getElement("operand1") != null) {
					result.changeElement("where",
							result.getElement("where").getElement("operand2"));
				}
				else {
					result.changeElement("where", null);
				}
			}
			if (sourceCode != null) {
				srcGuide.reset();
				bodyGuide.reset();
				if (temp) {
					if (srcGuide.equals(bodyGuide)) {
						join.changeElement("join", createJoinStatement(srcGuide, bodyGuide));
						join = join.getElement("join");
					}
					else {
						if (sourceCode.getElement("join") != null
								&& srcGuide.getSelect().equals(bodyGuide.getSelect())) {
							sourceCode.getElement("join").getElement("where")
									.changeElement("alias1", join.getElement("alias"));
						}
						else {
							join.changeElement("join",
									createJoinStatement(srcGuide, bodyGuide));
							join = join.getElement("join");
						}
					}
				}
				else {
					if (!srcGuide.getAlias().equals(bodyGuide.getAlias())
							&& srcGuide.numberOfSteps() > 1) {
						while (bodyGuide.hasNext()) {
							bodyGuide.next();
						}
						join.changeElement("join", createJoinStatement(srcGuide, bodyGuide));
						join = join.getElement("join");
					}
					else {
						sourceCode.getElement("join").getElement("where")
								.changeElement("alias1", join.getElement("alias"));
					}
				}
				join.changeElement("join", sourceCode.getElement("join"));
				changeWhereStatement(result, sourceCode.getElement("where"));
			}
		}
		else {
			sourceCode.changeElement("object", bodyCode);
			result = sourceCode;
		}

		return result;
	}

	/**
	 * Generate a new object value of the code
	 * 
	 * @param templateName
	 *          the name of the template for editing the object
	 * @param code
	 *          the select code with the object
	 */
	private void changeObjectVariable(String templateName, ISQLCode code) {

		ISQLCode sqlCode;
		if (templateName != null) {
			sqlCode =
					new SQLCode(mySettings.getTemplateGroup().getTemplate(templateName));
			sqlCode.addElement("object", code.getElement("object"));
		}
		else {
			sqlCode = code;
		}
		code.changeElement("object", sqlCode);
	}

	/**
	 * Generate a new select statement
	 * 
	 * @param arg
	 *          the body code of the collect statement
	 * @param sourceCode
	 *          the source code of the collect statement
	 * @param guideSrc
	 *          the guide of the source
	 * @param bodyExp
	 *          the body expression
	 * @return the fragment code
	 */
	protected ISQLCode createCollectStatement(ISQLCode arg, ISQLCode sourceCode,
			Guide guideSrc, OclExpression bodyExp) {

		ISQLCode result = null;
		Guide guideBody;

		if (navigationMap.get(bodyExp).size() >= 2) {
			guideBody =
					navigationMap.get(bodyExp).get(navigationMap.get(bodyExp).size() - 2);
		}
		else {
			guideBody = navigationMap.get(bodyExp).get(0);
		}
		result = createCollectStatement(sourceCode, arg, guideSrc, guideBody);
		return result;
	}

}