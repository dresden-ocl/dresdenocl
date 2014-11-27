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

package org.dresdenocl.tools.codegen.declarativ.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import org.dresdenocl.essentialocl.expressions.BooleanLiteralExp;
import org.dresdenocl.essentialocl.expressions.CollectionItem;
import org.dresdenocl.essentialocl.expressions.CollectionKind;
import org.dresdenocl.essentialocl.expressions.CollectionLiteralExp;
import org.dresdenocl.essentialocl.expressions.CollectionLiteralPart;
import org.dresdenocl.essentialocl.expressions.EnumLiteralExp;
import org.dresdenocl.essentialocl.expressions.ExpressionInOcl;
import org.dresdenocl.essentialocl.expressions.IfExp;
import org.dresdenocl.essentialocl.expressions.IntegerLiteralExp;
import org.dresdenocl.essentialocl.expressions.IteratorExp;
import org.dresdenocl.essentialocl.expressions.OclExpression;
import org.dresdenocl.essentialocl.expressions.OperationCallExp;
import org.dresdenocl.essentialocl.expressions.PrimitiveLiteralExp;
import org.dresdenocl.essentialocl.expressions.PropertyCallExp;
import org.dresdenocl.essentialocl.expressions.RealLiteralExp;
import org.dresdenocl.essentialocl.expressions.StringLiteralExp;
import org.dresdenocl.essentialocl.expressions.TypeLiteralExp;
import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.essentialocl.expressions.VariableExp;
import org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch;
import org.dresdenocl.essentialocl.types.AnyType;
import org.dresdenocl.essentialocl.types.BagType;
import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.essentialocl.types.SequenceType;
import org.dresdenocl.essentialocl.types.SetType;
import org.dresdenocl.pivotmodel.AssociationProperty;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.ConstraintKind;
import org.dresdenocl.pivotmodel.Expression;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.PrimitiveTypeKind;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclCode;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.codegen.declarativ.code.ICode;
import org.dresdenocl.tools.codegen.declarativ.code.IComplexCode;
import org.dresdenocl.tools.codegen.declarativ.code.impl.Code;
import org.dresdenocl.tools.codegen.declarativ.code.impl.CodeString;
import org.dresdenocl.tools.codegen.declarativ.mapping.Guide;
import org.dresdenocl.tools.codegen.declarativ.mapping.IMappedClass;
import org.dresdenocl.tools.codegen.exception.Ocl2CodeException;
import org.dresdenocl.tools.template.ITemplate;

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
 * @see org.dresdenocl.codegen.decl.mapping.IMappedModel
 * @see org.dresdenocl.codegen.decl.mapping.IMappedClass
 * @see org.dresdenocl.codegen.decl.mapping.Guide
 */
public class Ocl2DeclCode extends ExpressionsSwitch<ICode> implements
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
	
	private int uniqueAlias;

	/**
	 * If the variable true, than generated no allInstances code.
	 */
	private boolean useVariable;

	private boolean closure;

	/**
	 * 
	 */
	private List<ICode> commonTableExpressions;

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
	 * @see org.dresdenocl.tools.codgen.IOcl2Code#getSettings()
	 */
	public IOcl2DeclSettings getSettings() {

		return mySettings;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.tools.codgen.IOcl2Code#resetEnviornment()
	 */
	public void resetEnvironment() {

		uniqueConstraintNumber = 0;
		navigationMap = new HashMap<OclExpression, List<Guide>>();
		variableMap = new HashMap<String, Guide>();
		useVariable = false;
		commonTableExpressions = new LinkedList<ICode>();
		constraintName = "";
		closure = false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.tools.codgen.IOcl2Code#setSettings()
	 */
	public void setSettings(IOcl2DeclSettings settings) {

		mySettings = settings;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.tools.codgen.IOcl2Code#transformFragmentCode(java
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
		ITemplate template = null;
		if (aConstraint.getKind() == ConstraintKind.INVARIANT) {
			template =
					mySettings.getTemplateGroup().getTemplate("constraint_invariant");
			constraintName = aConstraint.getName();
		} else if (aConstraint.getKind() == ConstraintKind.DEFINITION && aConstraint.getDefinedFeature() instanceof Property) {
			template =
					mySettings.getTemplateGroup().getTemplate("constraint_definition");
			template.setAttribute("def_name", aConstraint.getDefinedFeature().getName());
		} else {
			return null;
		}


		ICode bodyExpression;
		Expression anExpression;

		anExpression = aConstraint.getSpecification();
		bodyExpression = doSwitch(anExpression);

		Guide classGuide = variableMap.values().iterator().next();
		classGuide.reset();
		
		if (aConstraint.getKind() == ConstraintKind.DEFINITION) {
			template.setAttribute("attribute", classGuide.getSelect());
			constraintName = classGuide.getFrom()+"_"+aConstraint.getDefinedFeature().getName();
		}
		
		if (constraintName == null || constraintName.equals("")) {
			constraintName = getUniqueConstraintName();
		}

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
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseBooleanLiteralExp(BooleanLiteralExp)
	 */
	public ICode caseBooleanLiteralExp(BooleanLiteralExp aBooleanLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseBooleanLiteralExp(BooleanLiteralExp) - start");
		}
		// no else.

		IComplexCode template;

		if (aBooleanLiteralExp.isBooleanSymbol()) {
			template =
					new Code("literal_boolean_true",mySettings);
		}
		else {
			template =
					new Code("literal_boolean_false",mySettings);
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
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch#
	 * caseCollectionLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.CollectionLiteralExp)
	 */
	public ICode caseCollectionLiteralExp(
			CollectionLiteralExp anCollectionLiteralExp) {

		List<CollectionLiteralPart> parts = anCollectionLiteralExp.getPart();
		String templateName = "";
		IComplexCode template;

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
				new Code(templateName,mySettings);

		// parameterize the template engine
		for (CollectionLiteralPart clp : parts) {
			template.addCode("items",
					doSwitch((OclExpression) ((CollectionItem) clp).getItem()));
		}

		return template;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseEnumLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.EnumLiteralExp)
	 */
	public ICode caseEnumLiteralExp(EnumLiteralExp anEnumLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseEnumLiteralExp(EnumLiteralExp) - start");
		}
		// no else.

		IComplexCode template;

		template =
				new Code("literal_enum",mySettings);

		template.addCode("value", new CodeString(anEnumLiteralExp
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
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseExpressionInOcl
	 * (org.dresdenocl.essentialocl.expressions.ExpressionInOcl)
	 */
	public ICode caseExpressionInOcl(ExpressionInOcl anExpressionInOcl) {

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
		classGuide.setAlias(getUniqueAlias());
		classGuide.reset();
		variableMap.put(v.getName(), classGuide);

		/* Transform bodyCode. */
		ICode bodyExpression =
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
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIfExp(org.dresdenocl.essentialocl.expressions.IfExp)
	 */
	public ICode caseIfExp(IfExp anIfExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIfExp(IfExp) - start");
		}
		// no else.

		IComplexCode template;

		OclExpression ifExp;
		OclExpression thenExp;
		OclExpression elseExp;

		ICode ifCode;
		ICode thenCode;
		ICode elseCode;

		ifExp = anIfExp.getCondition();
		thenExp = anIfExp.getThenExpression();
		elseExp = anIfExp.getElseExpression();

		/* Transform ifExp, thenExp and elseExp. */
		ifCode = doSwitch((EObject) ifExp);
		thenCode = doSwitch((EObject) thenExp);
		elseCode = doSwitch((EObject) elseExp);

		/* Transform ifExp. */
		template =
				new Code("if_expression",mySettings);
		template.addCode("if_branch", ifCode);
		template.addCode("then_branch", thenCode);
		template.addCode("else_branch", elseCode);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIfExp(IfExp)" + " - end - return value="
					+ template.toString());
		}
		// no else.

		return template;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIntegerLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.IntegerLiteralExp)
	 */
	public ICode caseIntegerLiteralExp(IntegerLiteralExp anIntegerLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIntegerLiteralExp(IntegerLiteralExp) - start");
		}
		// no else.

		IComplexCode template;

		template =	new Code("literal_integer",mySettings);

		template.addCode(
				"value",
				new CodeString(new Integer(anIntegerLiteralExp.getIntegerSymbol())
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
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIteratorExp
	 * (org.dresdenocl.essentialocl.expressions.IteratorExp)
	 */
	public ICode caseIteratorExp(IteratorExp anIteratorExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIteratorExp(IteratorExp) - start - Iterator"
					+ anIteratorExp);
		}

		String operationName = anIteratorExp.getName();
		if (operationName.equals("iterate")) {
			throw new UnsupportedOperationException(
					"The iterate operation is not supported.");
		}
		ICode result = null;

		OclExpression sourceExp = anIteratorExp.getSource();
		OclExpression bodyExp = anIteratorExp.getBody();
		// get code for source expression
		IComplexCode sourceCode = (IComplexCode) doSwitch(sourceExp);
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
							new Guide(guideSrc.isNavigation(), getUniqueAlias());
					varGuide.add(guideSrc.getSelect(), guideSrc.getFrom(),
							guideSrc.getWhere());
					varGuide.reset();
				}
			}

			boolean saveAllInstances = useVariable;
			useVariable = false;
			IComplexCode arg = (IComplexCode) doSwitch(bodyExp);
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
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch#
	 * caseOperationCallExp
	 * (org.dresdenocl.essentialocl.expressions.OperationCallExp)
	 */
	public ICode caseOperationCallExp(OperationCallExp anOperationCallExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("caseOperationCallExp(OperationCallExp) - start - Operation:"
							+ anOperationCallExp);
		}
		// no else.

		ICode resultExp;

		OclExpression sourceExp;
		ICode sourceCode;

		List<OclExpression> arguments = anOperationCallExp.getArgument();
		List<ICode> args = new ArrayList<ICode>();
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
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch
	 * #casePropertyCallExp
	 * (org.dresdenocl.essentialocl.expressions.PropertyCallExp)
	 */
	public ICode casePropertyCallExp(PropertyCallExp anPropertyCallExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("casePropertyCallExp(PropertyCallExp) - start");
		}

		List<Guide> guides = assignGuides(anPropertyCallExp);
		ICode result;
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
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseRealLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.RealLiteralExp)
	 */
	public ICode caseRealLiteralExp(RealLiteralExp aRealLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseRealLiteralExp(RealLiteralExp) - start");
		}
		// no else.

		IComplexCode template;

		template =
				new Code("literal_real",mySettings);
		// parameterize the template engine
		template.addCode("value",
				new CodeString(new Float(aRealLiteralExp.getRealSymbol()).toString()));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseRealLiteralExp(RealLiteralExp)"
					+ " - end - return value=" + template.toString());
		}
		// no else.

		return template;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseStringLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.StringLiteralExp)
	 */
	public ICode caseStringLiteralExp(StringLiteralExp aStringLiteralExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseStringLiteralExp(StringLiteralExp) - start");
		}
		// no else.

		IComplexCode template =
				new Code("literal_string",mySettings);

		// parameterize the template engine
		template.addCode("value",
				new CodeString(aStringLiteralExp.getStringSymbol()));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseStringLiteralExp(StringLiteralExp)"
					+ " - end - return value=" + template.toString());
		}
		// no else.

		return template;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch#
	 * caseTypeLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.TypeLiteralExp)
	 */
	public ICode caseTypeLiteralExp(TypeLiteralExp anTypeLiteralExp) {

		assignClassGuide(anTypeLiteralExp, anTypeLiteralExp.getReferredType());
		return new CodeString(anTypeLiteralExp.getReferredType().getName());
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseVariableExp
	 * (org.dresdenocl.essentialocl.expressions.VariableExp)
	 */
	public ICode caseVariableExp(VariableExp aVariableExp) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseVariableExp(VariableExp) - start - Variable:"
					+ aVariableExp);
		}
		// no else.

		IComplexCode template =
				new Code("literal_variable",mySettings);

		template.addCode("value", createObjectValue(variableMap.get(aVariableExp
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
	protected ICode handleArithmeticOperation(String name,
			ICode sourceCode, ICode firstArg) {

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
	protected ICode handleAllOperations(String operationName, Type type,
			OclExpression sourceExp, ICode sourceCode, List<ICode> args,
			List<OclExpression> arguments) {

		ICode resultExp = null;
		ICode firstArg = null;
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
			resultExp = handleCollCount((IComplexCode) sourceCode, firstArg);
		}
		else if ((operationName.equals("size") && !(sourceExp.getType() instanceof PrimitiveType))) {
			resultExp = handleCollObjectChange("size", (IComplexCode) sourceCode);
		}
		else if (operationName.equals("sum")) {
			resultExp = handleCollObjectChange("sum", (IComplexCode) sourceCode);
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
		else if (operationName.equals("matches")) {
			resultExp = handleStringMatches(sourceCode, firstArg);
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
				resultExp = handleCollObjectChange("max", (IComplexCode) sourceCode);
			}
			else {
				resultExp = handleIntExpression("max", sourceCode, firstArg);
			}
		}
		else if (operationName.equals("min")) {
			if (sourceExp.getType() instanceof CollectionType && firstArg == null) {
				resultExp = handleCollObjectChange("min", (IComplexCode) sourceCode);
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
	protected ICode handleAllInstances(Guide guide) {

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
	protected ICode handleCollCount(IComplexCode sourceCode, ICode firstArg) {

		// ICode select = createObjectValue(guide);
		if (sourceCode.getCode("select") != null) {
			String tempName = sourceCode.getComplexCode("select").getTemplateName();
			if ("feature_call_count".equals(tempName)
					|| "feature_call_sum".equals(tempName)
					|| "feature_call_size".equals(tempName)) {
				sourceCode.changeCode("groupby", null);
				IComplexCode code =
						new Code(tempName + "_select",mySettings);
				code.addCode("select",
						sourceCode.getComplexCode("select").getCode("select"));
				sourceCode.changeCode("select", code);
			}
		}
		IComplexCode sqlCode =
				new Code("feature_call_count",mySettings);
		sqlCode.addCode("select", sourceCode.getCode("select"));

		ICode where1 =
				createTwoOperandOperation("relational_expression_equals_any",
						sourceCode.getCode("select"), firstArg);

		changeWhereStatement(sourceCode, where1);
		sourceCode.changeCode("select", sqlCode);
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
	protected ICode handleCollExcludes(ICode sourceCode, ICode firstArg) {

		IComplexCode template =
				new Code("feature_call_excludes",mySettings);

		template.addCode("operand1", sourceCode);
		template.changeCode("operand2", firstArg);

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
	protected ICode handleCollExcludesAll(ICode sourceCode,
			ICode firstArg) {

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
	protected ICode handleCollExcluding(ICode sourceCode,
			ICode firstArg, CollectionType collType) {

		assert (collType != null) : "including() collType may not be null";

		String template = null;

		if (collType instanceof BagType) {
			template = "bag";
		}
		else if (collType instanceof SequenceType) {
			template = "sequence";
		}
		else if (collType instanceof SetType) {
			template = "set";
		}
		else {
			throw new IllegalStateException(
					"Unhandled collection type for excluding operation!");
		}

		IComplexCode returnValue = new Code("feature_call_excluding_"+template,mySettings);
		Guide guide =
				mySettings.getMappedModel()
						.getClass(collType.getElementType().getName()).getClassGuide();
		guide.reset();
		returnValue.addCode("operand2", firstArg);
		returnValue.addCode("element", new CodeString(guide.getSelect().get(0)));
		returnValue.addCode("operand1", sourceCode);

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
	protected ICode handleCollIncludes(ICode sourceCode, ICode firstArg) {

		IComplexCode template =
				new Code("feature_call_includes",mySettings);

		template.addCode("operand1", sourceCode);
		template.addCode("operand2", firstArg);

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
	protected ICode handleCollIncludesAll(ICode sourceCode,
			ICode firstArg) {

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
	protected ICode handleCollIncluding(ICode sourceCode,
			ICode firstArg, CollectionType collType) {

		assert (collType != null) : "including() collType may not be null";

		String template = null;

		if (collType instanceof BagType) {
			template ="bag";
		}
		else if (collType instanceof SequenceType) {
			template ="sequence";
		}
		else if (collType instanceof SetType) {
			template ="set";
		}
		else {
			throw new IllegalStateException(
					"Unhandled collection type for including operation!");
		}

		IComplexCode result = new Code("feature_call_including_"+template,mySettings);

		result.addCode("operand2", firstArg);
		result.addCode("operand1", sourceCode);

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
	protected ICode handleCollIntersection(ICode sourceCode,
			ICode firstArg, CollectionType collType) {

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
	protected ICode handleCollIsEmpty(ICode sourceCode) {

		return createOperandOperation("feature_call_isempty", sourceCode);
	}

	/**
	 * Generates a code fragment for a notEmpty operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @return declarative code fragment for the notEmpty operation
	 */
	protected ICode handleCollNotEmpty(ICode sourceCode) {

		return createOperandOperation("feature_call_notempty", sourceCode);
	}

	/**
	 * Generates a code fragment for a collection operation.
	 * 
	 * @param sourceCode
	 *          the declarative code fragment representing the source collection
	 * @return declarative code fragment for the collection operation
	 */
	protected ICode handleCollObjectChange(String name, IComplexCode sourceCode) {

		assert (sourceCode != null) : "handleCollObjectChange(): sourceCode may not be null";

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("handleCollObjectChange(String name, ICode sourceCode) - start - Type:"
							+ name + ", Code:" + sourceCode);
		}

		if (sourceCode.getCode("select") != null) {
			String tempName = sourceCode.getComplexCode("select").getTemplateName();
			if ((name.equals("sum") || name.equals("size"))
					&& ("feature_call_count".equals(tempName)
							|| "feature_call_sum".equals(tempName) || "feature_call_size"
							.equals(tempName))) {
				IComplexCode code =
						new Code(tempName + "_select",mySettings);
				code.addCode("select",
						sourceCode.getComplexCode("select").getCode("select"));
				sourceCode.changeCode("select", code);
			}
		}
		if (sourceCode.getCode("select") != null) {
			changeObjectVariable("feature_call_" + name, sourceCode);
			return sourceCode;
		}
		else if (sourceCode.getCode("operand") != null) {
			return createOperandOperation("feature_call_" + name, sourceCode);
		}
		else {
			IComplexCode code =
					new Code(	"feature_call_" + name,mySettings);
			code.addCode("select", sourceCode);
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
	protected ICode handleCollSymmetricDifference(ICode sourceCode,
			ICode firstArg) {

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
	protected ICode handleCollUnion(ICode sourceCode, ICode firstArg,
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

		IComplexCode result = createTwoOperandOperation(template, sourceCode, firstArg);
		ICode object = createObjectValue(guide);
		result.addCode("select", object);

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
	protected ICode handleIntExpression(String name, ICode sourceCode,
			ICode firstArg) {

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
	protected ICode handleIterCollect(IteratorExp anIteratorExp,
			OclExpression bodyExp, IComplexCode arg, Guide guideSrc,
			List<Guide> srcGuides, IComplexCode sourceCode) {

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
			guideBody.setAlias(getUniqueAlias());
			arg = createSelectStatement(navigationMap.get(bodyExp));
			arg.changeCode("select", doSwitch(bodyExp));
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
	protected ICode handleIterForAllExits(String operationName,
			IComplexCode srcCode, ICode where, List<Guide> srcGuides,
			List<Variable> variables) {

		where = handleIterOperation(operationName + "_where", where);

		IComplexCode join = srcCode.getComplexCode("join");
		if (variables.size() > 1) {
			for (int i = 1; i < variables.size(); i++) {
				if (join != null) {
					IComplexCode newJoin =
							createJoinStatement(variableMap.get(variables.get(i).getName()),
									srcGuides.get(1));
					newJoin.changeCode("join", join.getCode("join"));
					join.changeCode("join", newJoin);
					join = newJoin;
				}
				else {	
					IComplexCode newJoin;
					if (srcGuides.size() > 1) {
					newJoin = createJoinStatement(variableMap.get(variables.get(i).getName()),
								srcGuides.get(1));
					} else {
						newJoin = createJoinStatement(variableMap.get(variables.get(i).getName()),
								null);
					}
							
					srcCode.changeCode("join", newJoin);
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
	protected ICode handleIterSelectReject(String operationName,
			IComplexCode sourceCode, ICode where, IteratorExp anIteratorExp,
			OclExpression bodyExp) {

		ICode expression = handleIterOperation(operationName, where);
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
	protected ICode handleIterClosure(IteratorExp anIteratorExp,
			OclExpression bodyExp, Guide guideSrc, List<Guide> srcGuides) {

		String alias = guideSrc.getAlias();
		guideSrc.setAlias(getUniqueAlias());

		closure = true;
		IComplexCode code =
				new Code("common_table_expression",mySettings);
		commonTableExpressions.add(code);
		ICode cteName = new CodeString(constraintName + "_" + cteNumber++);
		Guide closureGuide =
				new Guide(false, getUniqueAlias());
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
		IComplexCode arg1 = (IComplexCode)doSwitch(bodyExp);
		if (arg1.getCode("select") == null) {
			arg1 = createSelectStatement(Arrays.asList(guideSrc));
		}
		arg1.changeCode("select",
				createObjectValue(navigationMap.get(bodyExp).get(0), guideSrc));
		navigationMap.clear();
		navigationMap.putAll(navMap);
		for (Variable v : anIteratorExp.getIterator()) {
			variableMap.remove(v.getName());
		}
		for (Variable v : anIteratorExp.getIterator()) {
			variableMap.put(v.getName(), closureGuide);
		}
		IComplexCode arg2 = (IComplexCode) doSwitch(bodyExp);
		closureGuide.reset();
		Guide closureGuide1 = new Guide(false, closureGuide.getAlias());
		closureGuide1.add("variable1", cteName.getResult(), "variable2");
		closureGuide1.reset();
		arg2.changeCode("select", this.createObjectValue(
				navigationMap.get(bodyExp).get(0), closureGuide1));
		useVariable = saveAllInstances;
		guideSrc.setAlias(alias);
		navMap.clear();
		code.addCode("constraint_name", cteName);
		code.addCode("recursive", arg2);
		code.addCode("non_recursive", arg1);

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
	protected ICode handleIterOperation(String name, ICode arg) {

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
	protected ICode handleLogicalExpression(String name, ICode sourceCode,
			ICode firstArg) {

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
	protected ICode handleOclIsTypeOf(Guide guide, ICode sourceCode) {

		guide.reset();

		IComplexCode template =
				new Code("feature_call_oclistypeof",mySettings);

		template.addCode("from", new CodeString(guide.getFrom()));

		template.addCode("select", new CodeString(guide.getSelect().get(0)));
		template.addCode("object2", sourceCode);
		template.addCode("alias", new CodeString(guide.getAlias()));

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
	protected ICode handleOclIsKindOf(Guide typeGuide, Guide supertypeGuide) {

		typeGuide.reset();
		if (supertypeGuide != null)
			supertypeGuide.reset();

		IComplexCode template =
				new Code("feature_call_ocliskindof",mySettings);

		template.addCode("from", new CodeString(typeGuide.getFrom()));
		template.addCode("select", new CodeString(typeGuide.getSelect().get(0)));;
		template.addCode("alias", new CodeString(typeGuide.getAlias()));
		template.addCode("from2", (supertypeGuide != null) ? new CodeString(
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
	protected ICode handleRelExpression(String name, OclExpression srcExp,
			ICode operand1, ICode operand2) {

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
			templateName.append(((PrimitiveType) attrType).getKind().getName().toLowerCase());
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
	protected ICode handleStringConcat(ICode sourceCode, ICode firstArg) {

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
	protected ICode handleStringSize(ICode sourceCode) {

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
	 * @param ICode
	 *          the end of the substring
	 * @return declarative code fragment for the substring operation
	 */
	protected ICode handleStringSubstring(ICode sourceCode,
			ICode firstArg, ICode ICode) {

		IComplexCode template =
				createOperandOperation("feature_call_string_substring", sourceCode);
		template.addCode("start", firstArg);
		template.addCode("end", ICode);

		return template;
	}
	
	/**
	 * Generates a declarative code fragment for a matches operation.
	 * 
	 * @param sourceCode
	 *          declarative code fragment of the operand (the string to build the
	 *          substring from)
	 * @param firstArg
	 *          the regular Expression
	 * @return declarative code fragment for the substring operation
	 */
	protected ICode handleStringMatches(ICode sourceCode,
			ICode firstArg) {

		return this.createTwoOperandOperation("feature_call_string_matches", sourceCode, firstArg);
	}

	/**
	 * Generates a declarative code fragment for a toLower operation on strings.
	 * 
	 * @param sourceCode
	 *          declarative code fragment of the operand
	 * @return declarative code fragment for the toLower operation
	 */
	protected ICode handleStringToLower(ICode sourceCode) {

		return createOperandOperation("feature_call_string_tolower", sourceCode);
	}

	/**
	 * Generates a declarative code fragment for a toUpper operation on strings.
	 * 
	 * @param sourceCode
	 *          declarative code fragment of the operand
	 * @return declarative code fragment for the toUpper operation
	 */
	protected ICode handleStringToUpper(ICode sourceCode) {

		return createOperandOperation("feature_call_string_toupper", sourceCode);
	}

	/**
	 * Generates a declarative code fragment for a unary minus operation.
	 * 
	 * @param sourceCode
	 *          declarative code fragment of the operand
	 * @return declarative code fragment for the unary minus operation
	 */
	protected ICode handleUnaryMinus(ICode sourceCode) {

		return createOperandOperation("unary_expression_minus", sourceCode);
	}

	/**
	 * Generates a declarative code fragment for a unary not operation.
	 * 
	 * @param sourceCode
	 *          declarative code fragment of the operand
	 * @return declarative code fragment for the unary not operation
	 */
	protected ICode handleUnaryNot(ICode sourceCode) {

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
	protected ICode handlePropProperty(Property property, List<Guide> guides) {

		Guide guide = guides.get(0);
		guide.reset();
		// attribute access without navigation
		if (guides.size() == 1) {
			IComplexCode template = createObjectValue(guide);

			// special case for Boolean attributes: e.g. expand 'attribute' to
			// 'attribute = 1' in SQL
			if (property.getType() instanceof PrimitiveType && ((PrimitiveType) property.getType()).getKind() == PrimitiveTypeKind.BOOLEAN) {
				ICode attr = template;
				template =
						new Code("feature_call_attribute_boolean",mySettings);
				template.addCode("attribute", attr);
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

		resetUniqueAlias();
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
		guide.setAlias(getUniqueAlias());
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
	protected void changeWhereStatement(IComplexCode select, ICode where) {

		select.changeCode("where",
				combinedWhereStatement(where, select.getCode("where")));
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
	protected ICode createWhereStatement(Guide guide1, Guide guide2) {
		IComplexCode where = null;
		if (guide2.getWhere().size()== 1 && guide1.getSelect().size() == 1) {
		 where =new Code("feature_call_navigation_where",mySettings);
			where.addCode("alias", new CodeString(guide2.getAlias()));
			where.addCode("alias2", new CodeString(guide1.getAlias()));
		
			where.addCode("where", new CodeString(guide2.getWhere().get(0)));
			where.addCode("select", new CodeString(guide1.getSelect().get(0)));
		} else {
			for (String s : guide2.getWhere()) {
				if (guide1.getSelect().contains(s)) {
					IComplexCode where2 =
							new Code("feature_call_navigation_where",mySettings);
					where2.addCode("alias", new CodeString(guide2.getAlias()));
					where2.addCode("alias2", new CodeString(guide1.getAlias()));
				
					where2.addCode("where", new CodeString(s));
					where2.addCode("select", new CodeString(s));
					if (where == null) where = where2;
					else combinedWhereStatement(where,where2);
				}
			}
		}

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
	protected ICode combinedWhereStatement(ICode where1, ICode where2) {

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
	protected IComplexCode createJoinStatement(Guide from, Guide where) {

		IComplexCode template =
				new Code("feature_call_navigation_join",mySettings);
		IComplexCode fromCode =
				new Code("feature_call_navigation_from",mySettings);
		fromCode.addCode("from", new CodeString(from.getFrom()));
		fromCode.addCode("select", new CodeString(from.getSelect().get(0)));
		if (!from.getWhere().equals(from.getSelect())) {
			fromCode.addCode("where", new CodeString(from.getWhere().get(0)));
		}
		template.addCode("alias", new CodeString(from.getAlias()));
		template.addCode("from", fromCode);
		if (where != null) template.addCode("where", createWhereStatement(from, where));
		return template;
	}

	/**
	 * Generate a join statement with sub joins for the guides
	 * 
	 * @param guides
	 *          the guides
	 * @return the join statement from guides.get(0) with the subjoins
	 */
	protected ICode createJoinStatements(List<Guide> guides) {

		ICode joinTemplate = null;
		for (int i = guides.size() - 1; i > 0; i--) {
			Guide guide = guides.get(i);
			guide.reset();
			IComplexCode template = createJoinStatement(guide, guides.get(i - 1));
			if (joinTemplate != null) {
				template.addCode("join", joinTemplate);
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
	protected IComplexCode createObjectValue(Guide guide) {

		guide.reset();
		IComplexCode select =
				new Code("feature_call_attribute_context",mySettings);
		select.addCode("alias", new CodeString(guide.getAlias()));
		select.addCode("attribute", new CodeString(guide.getSelect().get(0)));
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
	protected ICode createObjectValue(Guide guide1, Guide guide2) {

		guide1.reset();
		IComplexCode select = createObjectValue(guide2);
		select.addCode("alias2", new CodeString(guide1.getAlias()));
		select.addCode("attribute2", new CodeString(guide1.getSelect().get(0)));
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
	protected IComplexCode createSelectStatement(List<Guide> guides) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createNavigation(List<Guide> guides) - start - Guides:"
					+ guides);
		}

		IComplexCode template;

		LinkedList<Guide> steps = new LinkedList<Guide>();
		LinkedList<String> aliase = new LinkedList<String>();
		aliase.add(guides.get(0).getAlias());
		if (guides.size() > 1
				&& aliase.contains(guides.get(guides.size() - 1).getAlias())) {
			guides.get(guides.size() - 1).setAlias(
					getUniqueAlias());
		}
		aliase.add(guides.get(guides.size() - 1).getAlias());
		for (Guide guide : guides) {
			guide.reset();
			if (aliase.contains(guide.getAlias()) && guides.indexOf(guide) != 0
					&& guides.indexOf(guide) != guides.size() - 1) {
				guide.setAlias(getUniqueAlias());
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
				new Code("feature_call_navigation_select",mySettings);
		template.addCode("select", createObjectValue(guide));
		template.addCode("alias", new CodeString(guide.getAlias()));
		template.addCode("join", createJoinStatements(joinGuides));
		template.addCode("from", new CodeString(guide.getFrom()));
		if (!useVariable && steps.size() >= 2) {
			template.addCode(
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
				 * guide.setAlias(getUniqueAlias());
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
					guide.setAlias(getUniqueAlias());
				}
				// }
			}
			else {
				guide.setAlias(getUniqueAlias());
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
	private IComplexCode createTwoOperandOperation(String templateName,
			ICode operand1, ICode operand2) {

		IComplexCode code = new Code(templateName,mySettings);
		code.addCode("operand1", operand1);
		if (operand2 != null) {
			code.addCode("operand2", operand2);
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
	private IComplexCode createOperandOperation(String templateName, ICode operand) {

		IComplexCode code = new Code(templateName,mySettings);
		code.addCode("operand", operand);
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
	private ICode createCollectStatement(IComplexCode sourceCode,
			IComplexCode bodyCode, Guide srcGuide, Guide bodyGuide) {

		IComplexCode result = null;
		boolean temp =
				Boolean.valueOf(mySettings.getTemplateGroup()
						.getTemplate("check_database_references").toString());

		if (bodyCode != null && bodyCode.getCode("from") != null) {
			result = bodyCode;
			IComplexCode join = result;
			while (join.getComplexCode("join") != null) {
				join = join.getComplexCode("join");
			}
			if (result.getComplexCode("where") != null) {
				if (result.getComplexCode("where").getCode("operand1") != null) {
					result.changeCode("where",
							result.getComplexCode("where").getCode("operand2"));
				}
				else {
					result.changeCode("where", null);
				}
			}
			if (sourceCode != null) {
				srcGuide.reset();
				bodyGuide.reset();
				if (temp) {
					if (srcGuide.equals(bodyGuide)) {
						join.changeCode("join", createJoinStatement(srcGuide, bodyGuide));
						join = join.getComplexCode("join");
					}
					else {
						if (sourceCode.getComplexCode("join") != null
								&& srcGuide.getSelect().equals(bodyGuide.getSelect())) {
							sourceCode.getComplexCode("join").getComplexCode("where")
									.changeCode("alias", join.getCode("alias"));
						}
						else {
							join.changeCode("join",
									createJoinStatement(srcGuide, bodyGuide));
							join = join.getComplexCode("join");
						}
					}
				}
				else {
					if (!srcGuide.getAlias().equals(bodyGuide.getAlias())
							&& srcGuide.numberOfSteps() > 1) {
						while (bodyGuide.hasNext()) {
							bodyGuide.next();
						}
						join.changeCode("join", createJoinStatement(srcGuide, bodyGuide));
						join = join.getComplexCode("join");
					}
					else {
						sourceCode.getComplexCode("join").getComplexCode("where")
								.changeCode("alias", join.getCode("alias"));
					}
				}
				join.changeCode("join", sourceCode.getCode("join"));
				changeWhereStatement(result, sourceCode.getCode("where"));
			}
		}
		else {
			sourceCode.changeCode("select", bodyCode);
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
	private void changeObjectVariable(String templateName, IComplexCode code) {

		IComplexCode sqlCode;
		if (templateName != null) {
			sqlCode =
					new Code(templateName,mySettings);
			sqlCode.addCode("select", code.getCode("select"));
		}
		else {
			sqlCode = code;
		}
		code.changeCode("select", sqlCode);
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
	protected ICode createCollectStatement(IComplexCode arg, IComplexCode sourceCode,
			Guide guideSrc, OclExpression bodyExp) {

		ICode result = null;
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
	
	/**
	 * Returns a unique alias which may be used in the declarative target language
	 * 
	 * @return unique alias which may be used in the declarative target language
	 */
	public String getUniqueAlias() {
		
		uniqueAlias++;
		if (uniqueAlias == 1) {
			return "self";
		}
		return "alias" + uniqueAlias;
	}

	public void resetUniqueAlias() {
		uniqueAlias = 0;
	}

}