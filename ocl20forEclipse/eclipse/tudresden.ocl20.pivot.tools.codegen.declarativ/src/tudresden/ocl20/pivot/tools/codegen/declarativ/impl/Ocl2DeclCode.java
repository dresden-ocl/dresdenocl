/*
 * DeclarativeCodeGenerator.java
 * 
 * Copyright (c) 2006 Florian Heidenreich
 * Contact: <mail@fheidenreich.de>
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp;
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
import tudresden.ocl20.pivot.essentialocl.expressions.VariableExp;
import tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch;
import tudresden.ocl20.pivot.essentialocl.types.AnyType;
import tudresden.ocl20.pivot.essentialocl.types.BagType;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.OrderedSetType;
import tudresden.ocl20.pivot.essentialocl.types.SequenceType;
import tudresden.ocl20.pivot.essentialocl.types.SetType;
import tudresden.ocl20.pivot.pivotmodel.AssociationProperty;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.tools.codegen.code.ITransformedCode;
import tudresden.ocl20.pivot.tools.codegen.code.ITransformedType;
import tudresden.ocl20.pivot.tools.codegen.code.impl.TransformedCodeImpl;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclCode;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
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
 * The OclExpressions from a UML/OCL or MOF/OCL model are transformed to the target
 * language using two different mappings:<br />
 * <ul>
 * <li>Mapping between models:<br />
 * The first mapping maps the Classifiers, its attributes and association ends to 
 * a target model. The target model must implement the MappedModel interface and
 * describes the model elements by Guide objects.</li>
 * <li>Language mapping:<br />
 * The second mapping maps OCL language artefacts to fragments of the target language
 * using templates which are read by the DeclarativeTemplateEngine. The templates are
 * parametrisized by the DeclarativeCodeGenerator using target model information queried
 * via the MappedModel instance.
 * </ul>
 * 
 * @author Florian Heidenreich
 * @author Bjoern Freitag
 * 
 * @see tudresden.ocl20.codegen.decl.mapping.IMappedModel
 * @see tudresden.ocl20.codegen.decl.mapping.IMappedClass
 * @see tudresden.ocl20.codegen.decl.mapping.Guide

 */
public class Ocl2DeclCode extends ExpressionsSwitch<ITransformedCode> implements IOcl2DeclCode {

	/** The Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(Ocl2DeclCode.class);
	
	
	/** The Settings used during code generation. */
	private IOcl2DeclSettings mySettings;
	
	/**
	 * This map is filled by assignGuides and maps an OclExpression to a lis of Guide objects
	 */
	private Map<OclExpression,List<Guide>> navigationMap;
		
	private LinkedList<String> aliasList;
	private LinkedList<String> contextList;
	
	
	/**
	 * <p>
	 * Creates a new instance to transform code.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *             If the initialization fails.
	 */
	public Ocl2DeclCode() throws Ocl2CodeException {

		this.init();
	}
	
	/**
	 * <p>
	 * Initializes the code generator.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *             Thrown, if a String template for code transformation can not
	 *             be found.
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
	 * 
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl2Code#getSettings()
	 */
	public IOcl2DeclSettings getSettings() {
		return mySettings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl2Code#resetEnviornment()
	 */
	public void resetEnvironment() {
		this.aliasList = new LinkedList<String>();
		this.contextList = new LinkedList<String>();
		this.navigationMap = new HashMap<OclExpression, List<Guide>>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl2Code#setSettings()
	 */
	public void setSettings(IOcl2DeclSettings settings) {
		mySettings = settings;
		
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
		aliasList.add(mySettings.getMappedModel().getUniqueAlias());
		List<String> result;

		result = new ArrayList<String>();

		for (Constraint aConstraint : constraints) {

			ITransformedCode aTransformedConstraint;
			String aResult;

			aTransformedConstraint = this.transformFragmentCode(aConstraint);

			aResult = aTransformedConstraint.getResultExp();

			if (aResult.length() > 0) {
				aResult += "\n";
			}
			// no else.

			//aResult += aTransformedConstraint.getResultExp();

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
	 *            The {@link Constraint} for which code shall be transformed.
	 * @return An {@link ITransformedCode} containing the Code transformed for
	 *         the given {@link Constraint}'s {@link OclExpression}.
	 * @throws Ocl22CodeException
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

	@Override
	public ITransformedCode caseBooleanLiteralExp(BooleanLiteralExp aBooleanLiteralExp) {
		// TODO Auto-generated method stub
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseBooleanLiteralExp(BooleanLiteralExp) - start");
		}
		// no else.

		ITransformedCode result;

		ITemplate template;
		
		if (aBooleanLiteralExp.isBooleanSymbol()) {
			template = mySettings.getTemplateGroup().getTemplate("literal_boolean_true");
		} else {
			template = mySettings.getTemplateGroup().getTemplate("literal_boolean_false");
		}
		result = new TransformedCodeImpl();
		result.setResultExp(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseBooleanLiteralExp(BooleanLiteralExp)"
					+ " - end - return value=" + result);
		}
		// no else.

		return result;
	}

	@Override
	public ITransformedCode caseCollectionLiteralExp(CollectionLiteralExp object) {
		// TODO Auto-generated method stub
		return super.caseCollectionLiteralExp(object);
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

		ITransformedCode result;
		ITemplate template;

		result = new TransformedCodeImpl();

		template = mySettings.getTemplateGroup().getTemplate("literal_enum");
		
		template.setAttribute("value", anEnumLiteralExp.getReferredEnumLiteral().getName());
		
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
		IMappedClass constrainedType = this.getSettings().getMappedModel().getClass(anExpressionInOcl.getContext().getType().getName());
		Guide classGuide = this.getSettings().getMappedModel().getClass(constrainedType.getName()).getClassGuide();
		classGuide.reset();
		contextList.add(classGuide.getWhere());
		String constraintName;
		
		if (anExpressionInOcl.getConstraint() != null) {
			constraintName = anExpressionInOcl.getConstraint().getName();
		} else {
			constraintName = "UNAMED CONSTRAINT";
		}

		ITransformedCode result;
		
		/* Transform bodyCode. */
		String bodyExpression = this.doSwitch((EObject) anExpressionInOcl.getBodyExpression()).getResultExp();

		ITemplate template = mySettings.getTemplateGroup().getTemplate("constraint_body");
		template.setAttribute("constraint_name", constraintName);
		template.setAttribute("context", classGuide.getFrom());
		template.setAttribute("context_alias", aliasList.getLast());
		template.setAttribute("expression", bodyExpression);

		result = new TransformedCodeImpl();
		result.setResultExp(template.toString());

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

		ITransformedCode result;

		ITemplate template;

		OclExpression ifExp;
		OclExpression thenExp;
		OclExpression elseExp;

		ITransformedCode ifCode;
		ITransformedCode thenCode;
		ITransformedCode elseCode;

		ifExp = anIfExp.getCondition();
		thenExp = anIfExp.getThenExpression();
		elseExp = anIfExp.getElseExpression();

		result = new TransformedCodeImpl();

		/* Transform ifExp, thenExp and elseExp. */
		ifCode = doSwitch((EObject) ifExp);
		thenCode = doSwitch((EObject) thenExp);
		elseCode = doSwitch((EObject) elseExp);

		/* Transform ifExp. */
		template = mySettings.getTemplateGroup().getTemplate("if_expression");
		template.setAttribute("if_branch", ifCode.getResultExp());
		template.setAttribute("then_branch", thenCode.getResultExp());
		template.setAttribute("else_branch", elseCode.getResultExp());

		result.addCode(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("caseIfExp(IfExp)" + " - end - return value="
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

		template = mySettings.getTemplateGroup().getTemplate("literal_integer");
		
		// parameterize the template engine
		template.setAttribute("value", new Integer(anIntegerLiteralExp.getIntegerSymbol()).toString());
		

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
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.expressions.util.ExpressionsSwitch
	 * #caseIteratorExp
	 * (tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp)
	 */
	public ITransformedCode caseIteratorExp(IteratorExp anIteratorExp) {

		//TODO: final TransformedCode
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIteratorExp(IteratorExp) - start");
		}
		// no else.

		ITransformedCode result;
		ITransformedType resultType;

		ITemplate template = null;

		OclExpression sourceExp = anIteratorExp.getSource();
		ITransformedCode sourceCode = doSwitch((EObject)sourceExp);
		ITransformedType sourceType;
		
		// add new alias for iterator to the end of the alias list
		aliasList.addLast(this.getSettings().getMappedModel().getUniqueAlias());

		
		// evaluate the arguments
		List<ITransformedCode> args = new ArrayList<ITransformedCode>();
		args.add(doSwitch((EObject)anIteratorExp.getBody()));

		result = new TransformedCodeImpl();

		/* Get the name of the iterator */
		String itName = anIteratorExp.getName();

		if (itName.equals("any") || itName.equals("exists")
				|| itName.equals("forAll") || itName.equals("one")) {

			/* Set iterator specific template attributes. */
			//template.setAttribute("itType", sourceType.getGenericType().toString());
		}

		else if (itName.equals("collect")) {
			
			Guide guideSrc = navigationMap.get(sourceExp).get(0); guideSrc.reset();
			Guide guideArg = navigationMap.get(anIteratorExp.getBody()).get(0); guideArg.reset();
			navigationMap.put(anIteratorExp, navigationMap.get(anIteratorExp.getBody()));
			
			List<Guide> nav = new LinkedList<Guide>();
			nav.add(guideArg);
			nav.add(guideSrc);
			
			//TODO: handleIterCollect()			
		}


		/* Set template attributes which are needed for all iterators. */
		template.setAttribute("sourceCode", sourceCode.getResultExp());
		template.setAttribute("sourceExp", sourceCode.getResultExp());

		result.addCode(template.toString());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseIteratorExp(IteratorExp)"
					+ " - end - return value=" + result);
		}
		// no else.

		return result;
	}
	
	/**
	 * Generates a declarative code fragment for a relational expression.
	 * 
	 * @param name name of the relational expression (equal, nequal, greater, lesser, greaterequal, lesserequal)
	 * @param srcExp the OclExpression representing the first argument used to determine the operand types
	 * @param operand1 declarative code fragment of the first operand
	 * @param operand2 declarative code fragment of the second operand
	 * @return declarative code fragment for the equals operation
	 */
	protected String handleRelExpression(String name, OclExpression srcExp, ITransformedCode operand1, ITransformedCode operand2) {
		StringBuffer templateName = new StringBuffer("relational_expression_");
		Type attrType = null;
		
		templateName.append(name);
		templateName.append("_");
		
		if (srcExp instanceof OperationCallExp) {
			attrType = srcExp.getType();
		} else if (srcExp instanceof PropertyCallExp) {
			attrType = ((PropertyCallExp)srcExp).getReferredProperty().getType();
		} else if (srcExp instanceof PrimitiveLiteralExp) {
			attrType = ((PrimitiveLiteralExp)srcExp).getType();
		} else {
			throw new IllegalStateException("Unhandled attribute type for relational expression: " + srcExp.getClass().getName() + "!");
		}	
		
		if (attrType instanceof PrimitiveType) {
			if (attrType.getName().equals("Boolean")) {
				templateName.append("boolean"); 
			} else if (attrType.getName().equals("Enumeration")) {
				templateName.append("enumeration");
			} else if (attrType.getName().equals("Integer")) {
				templateName.append("integer");
			} else if (attrType.getName().equals("Real")) {
				templateName.append("real");
			} else if (attrType.getName().equals("String")) {
				templateName.append("string");
			} else {
				throw new IllegalStateException("Unhandled primitive type for relational expression!");
			}
		} else if (attrType instanceof CollectionType) {
			if(attrType instanceof SetType) {
				templateName.append("set");
            } else if(attrType instanceof SequenceType) {
            	templateName.append("sequence");
            } else if(attrType instanceof BagType) {
            	templateName.append("bag");
            } else if(attrType instanceof OrderedSetType) {
            	templateName.append("orderedset");
            } else {
				throw new IllegalStateException("Unhandled collection type for relational expression!");
			}
		} else {
			templateName.append("any");
		}
		
		ITemplate template = this.getSettings().getTemplateGroup().getTemplate(templateName.toString());
		template.setAttribute("operand1", operand1.getResultExp());
		template.setAttribute("operand2", operand2.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for an arithmetic operation.
	 * 
	 * @param name the name of the arithmentic operation (can be div, minus, mult and plus)
	 * @param sourceCode the code of the source expression (argument 1)
	 * @param firstArg the code of the argument expression (argument 2)
	 * @return declarative code fragment for the arithmetic operation
	 */
	protected String handleArithmeticOperation(String name, ITransformedCode sourceCode, ITransformedCode firstArg) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("arithmetic_expression_" + name);
		
		template.setAttribute("operand1", sourceCode.getResultExp());
		template.setAttribute("operand2", firstArg.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for an allInstances operation.
	 * 
	 * @param guide the guide to the classifier representation
	 * @return declarative code fragment for the allInstances operation
	 */
	protected String handleAllInstances(Guide guide) {
		guide.reset();
		
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_allinstances");
		
		template.setAttribute("object", guide.getSelect());
		template.setAttribute("source", guide.getFrom());
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for a count operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param guide the guide to the source expression
	 * @param firstArg argument passed to the feature call which is used as reference object
	 * @return declarative code fragment for the count operation
	 */
	protected String handleCollCount(ITransformedCode sourceCode, Guide guide, ITransformedCode firstArg) {
		assert(guide != null) : "count(): guide may not be null";
		guide.reset();
		
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_count");
		
		template.setAttribute("source", guide.getFrom());
		template.setAttribute("element", guide.getSelect());
		template.setAttribute("collection", sourceCode.getResultExp());
		template.setAttribute("object", firstArg.getResultExp());
		
		return template.toString();	
	}
	
	/**
	 * Generates a code fragment for an excludes operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param firstArg argument passed to the feature call which is used as reference object
	 * @return declarative code fragment for the excludes operation
	 */
	protected String handleCollExcludes(ITransformedCode sourceCode, ITransformedCode firstArg) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_excludes");
		
		template.setAttribute("collection", sourceCode.getResultExp());
		template.setAttribute("object", firstArg.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for an excludesAll operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param firstArg argument passed to the feature call which is used as reference collection
	 * @return declarative code fragment for the excludesAll operation
	 */
	protected String handleCollExcludesAll(ITransformedCode sourceCode, ITransformedCode firstArg) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_excludesall");
		
		template.setAttribute("collection", sourceCode.getResultExp());
		template.setAttribute("collection2", firstArg.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for an excluding operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param firstArg argument passed to the feature call which is used as reference object
	 * @param collType collection type to determine the template to use (BagType, SequenceType, SetType)
	 * @return declarative code fragment for the excluding operation
	 */
	protected String handleCollExcluding(ITransformedCode sourceCode, ITransformedCode firstArg, CollectionType collType) {
		assert(collType != null): "including() collType may not be null";
		
		ITemplate template = null; 
		
		if (collType instanceof BagType) {
			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_excluding_bag");
		} else if (collType instanceof SequenceType) {
			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_excluding_sequence");
		} else if (collType instanceof SetType) {
			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_excluding_set");
		} else {
			throw new IllegalStateException("Unhandled collection type for excluding operation!"); 
		}
		
		template.setAttribute("object", firstArg.getResultExp());
		template.setAttribute("element", this.mySettings.getMappedModel().getClass(collType.getElementType().getName()).getClassGuide().getSelect());
		template.setAttribute("collection", sourceCode.getResultExp());
		
		return template.toString();		
	}
	
	/**
	 * Generates a code fragment for an exists operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param guide the guide to the source expression
	 * @param firstArg argument passed to the feature call
	 * @return declarative code fragment for the exists operation
	 */
	protected String handleCollExists(ITransformedCode sourceCode, Guide guide, ITransformedCode firstArg) {
		assert(guide != null) : "exists(): guide may not be null";
		guide.reset();

		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_exists");
		
		template.setAttribute("source", guide.getFrom());
        template.setAttribute("object", guide.getSelect());
        template.setAttribute("collection", sourceCode.getResultExp());
        template.setAttribute("expression", firstArg.getResultExp());
        
        return template.toString();
	}
	
	/**
	 * Generates a code fragment for an includes operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param firstArg argument passed to the feature call which is used as reference object
	 * @return declarative code fragment for the includes operation
	 */
	protected String handleCollIncludes(ITransformedCode sourceCode, ITransformedCode firstArg) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_includes");
		
		template.setAttribute("collection", sourceCode.getResultExp());
		template.setAttribute("object", firstArg.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for an includesAll operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param firstArg argument passed to the feature call which is used as reference collection
	 * @return declarative code fragment for the includesAll operation
	 */
	protected String handleCollIncludesAll(ITransformedCode sourceCode, ITransformedCode firstArg) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_includesall");
		
		template.setAttribute("collection", sourceCode.getResultExp());
		template.setAttribute("collection2", firstArg.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for an including operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param firstArg argument passed to the feature call which is used as reference object
	 * @param collType collection type to determine the template to use (BagType, SequenceType, SetType) 
	 * @return declarative code fragment for the including operation
	 */
	protected String handleCollIncluding(ITransformedCode sourceCode, ITransformedCode firstArg, CollectionType collType) {
		assert(collType != null): "including() collType may not be null";
		
		ITemplate template = null; 
		
		if (collType instanceof BagType) {
			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_including_bag");
		} else if (collType instanceof SequenceType) {
			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_including_sequence");
		} else if (collType instanceof SetType) {
			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_including_set");
		} else {
			throw new IllegalStateException("Unhandled collection type for including operation!"); 
		}
		
		template.setAttribute("object", firstArg.getResultExp());
		template.setAttribute("collection", sourceCode.getResultExp());
		
		return template.toString();		
	}
	
	/**
	 * Generates a code fragment for a intersection operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param firstArg argument passed to the feature call which is used as reference collection
	 * @param collType collection type to determine the template to use (BagType, SetType)
	 * @return declarative code fragment for the intersection operation
	 */
	protected String handleCollIntersection(ITransformedCode sourceCode, ITransformedCode firstArg, CollectionType collType) {
		assert(collType != null): "intersection() collType may not be null";
		
		ITemplate template = null;
		
		if (collType instanceof BagType) {
			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_intersection_bag");
		} else if (collType instanceof SetType) {
			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_intersection_set");
		} else {
			throw new IllegalStateException("Unhandled collection type for intesection operation!");
		}
		
		template.setAttribute("collection", sourceCode.getResultExp());
		template.setAttribute("collection2", firstArg.getResultExp());
		
		return template.toString();		
	}
	
	/**
	 * Generates a code fragment for a isEmpty operation
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @return declarative code fragment for the isEmpty operation
	 */
	protected String handleCollIsEmpty(ITransformedCode sourceCode) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_isempty");
		
		template.setAttribute("collection", sourceCode.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for a notEmpty operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @return declarative code fragment for the notEmpty operation
	 */
	protected String handleCollNotEmpty(ITransformedCode sourceCode) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_notempty");
		
		template.setAttribute("collection", sourceCode.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for a size operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param guide the guide to the source expression
	 * @return declarative code fragment for the size operation
	 */
	protected String handleCollSize(ITransformedCode sourceCode, Guide guide) {
		assert(guide != null) : "size(): guide may not be null";
		guide.reset();

		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_size");
		
		template.setAttribute("source", guide.getFrom());
        template.setAttribute("element", guide.getSelect());
        template.setAttribute("collection", sourceCode.getResultExp());
        
        return template.toString();
	}
	
	/**
	 * Generates a code fragment for a sum operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param guide the guide to the source expression
	 * @return declarative code fragment for the sum operation
	 */
	protected String handleCollSum(ITransformedCode sourceCode, Guide guide) {
		assert(guide != null) : "sum(): guide may not be null";
		guide.reset();
		
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_sum");
		
		template.setAttribute("source", guide.getFrom());
        template.setAttribute("element", guide.getSelect());
        template.setAttribute("collection", sourceCode.getResultExp());
        
        return template.toString();
	}
	
	/**
	 * Generates a code fragment for a symmetricDifference operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param firstArg the declarative code fragment representing the collection passed to the feature call which is used to build the symmetric difference
	 * @return declarative code fragment for the symmetricDifference operation
	 */
	protected String handleCollSymmetricDifference(ITransformedCode sourceCode, ITransformedCode firstArg) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_symmetricdifference");
		
		template.setAttribute("collection", firstArg.getResultExp());
		template.setAttribute("collection2", sourceCode.getResultExp());
		
		return template.toString();		
	}
	
	/**
	 * Generates a declarative code fragment for an union operation.
	 * 
	 * @param sourceCode the declarative code fragment representing the source collection
	 * @param firstArg the declarative code fragment representing the collection passed to the feature call which is used to build the union of the two collections
	 * @param collType collection type to determine the template to use (BagType, SequenceType, SetType)
	 * @return declarative code fragment for the union operation
	 */
	protected String handleCollUnion(ITransformedCode sourceCode, ITransformedCode firstArg, CollectionType collType) {
		assert(collType != null): "including() collType may not be null";
		
		ITemplate template = null; 
		
		if (collType instanceof BagType) {
			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_union_bag");
		} else if (collType instanceof SequenceType) {
			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_union_sequence");
		} else if (collType instanceof SetType) {
			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_union_set");
		} else {
			throw new IllegalStateException("Unhandled collection type for union operation!"); 
		}
		
		template.setAttribute("element", mySettings.getMappedModel().getClass(collType.getElementType().getName()).getClassGuide().getSelect());
		template.setAttribute("collection", sourceCode.getResultExp());
		template.setAttribute("collection2", firstArg.getResultExp());
		
		return template.toString();		
	}
	
	/**
	 * Generates a declarative code fragment for a abs operation on Integer/Real.
	 * 
	 * @param sourceCode declarative code fragment for the operand to the abs operation
	 * @return declarative code fragment for the abs operation
	 */
	protected String handleIntAbs(ITransformedCode sourceCode) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_int_abs");
		
		template.setAttribute("operand", sourceCode.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a div operation on Integer/Real.
	 * 
	 * @param sourceCode declarative code fragment for the first operand of the div operation
	 * @param firstArg declarative code fragment for the second operand of the div operation
	 * @return declarative code fragment for the div operation 
	 */
	protected String handleIntDiv(ITransformedCode sourceCode, ITransformedCode firstArg) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_int_div");
		
		template.setAttribute("operand1", sourceCode.getResultExp());
		template.setAttribute("operand2", firstArg.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a floor operation on Integer/Real.
	 * 
	 * @param sourceCode declarative code fragment for the operand of the floor operation
	 * @return declarative code fragment for the floor operation
	 */
	protected String handleIntFloor(ITransformedCode sourceCode) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_int_floor");
		
		template.setAttribute("operand", sourceCode.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a max operation on Integer/Real.
	 * 
	 * @param sourceCode declarative code fragment for the first operand of the max operation
	 * @param firstArg declarative code fragment for the second operand of the max operation
	 * @return declarative code fragment for the max operation
	 */
	protected String handleIntMax(ITransformedCode sourceCode, ITransformedCode firstArg) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_int_max");
		
		template.setAttribute("operand1", sourceCode.getResultExp());
		template.setAttribute("operand2", firstArg.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a min operation on Integer/Real.
	 * 
	 * @param sourceCode declarative code fragment for the first operand of the min operation
	 * @param firstArg declarative code fragment for the second operand of the min operation
	 * @return declarative code fragment for the min operation 
	 */
	protected String handleIntMin(ITransformedCode sourceCode, ITransformedCode firstArg) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_int_min");
		
		template.setAttribute("operand1", sourceCode.getResultExp());
		template.setAttribute("operand2", firstArg.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a mod operation on Integer/Real.
	 * 
	 * @param sourceCode declarative code fragment for the first operand of the mod operation
	 * @param firstArg declarative code fragment for the second operand of the div operation
	 * @return declarative code fragment for the mod operation 
	 */
	protected String handleIntMod(ITransformedCode sourceCode, ITransformedCode firstArg) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_int_mod");
		
		template.setAttribute("operand1", sourceCode.getResultExp());
		template.setAttribute("operand2", firstArg.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a round operation on Integer/Real.
	 * 
	 * @param sourceCode declarative code fragment for the operand of the round operation
	 * @return declarative code fragment for the round operation
	 */
	protected String handleIntRound(ITransformedCode sourceCode) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_int_round");
		
		template.setAttribute("operand", sourceCode.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a collect iterator.
	 * 
	 * @param codeSrcExp the code of the source expression
	 * @return declarative code fragment for the collect iterator
	 */
	protected String handleIterCollect(String codeSrcExp) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_collect");
		
		template.setAttribute("expression", codeSrcExp);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a forAll iterator.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param guide the Guide to the source expression
	 * @param expression argument passed to the iterator
	 * @return declarative code fragment for the forAll iterator
	 */
	protected String handleIterForAll(String collection, Guide guide, String expression) {
		guide.reset();
		
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_forall");
		
		template.setAttribute("source", guide.getFrom());
		template.setAttribute("alias", aliasList.getLast());
		template.setAttribute("object", guide.getSelect());
		template.setAttribute("collection", collection);
		template.setAttribute("expression", expression);

		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a reject iterator.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param guide the Guide to the source expression
	 * @param expression argument passed to the iterator
	 * @return declarative code fragment for the reject iterator
	 */
	protected String handleIterReject(String collection, Guide guide, String expression) {
		guide.reset();
		
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_reject");
		
		template.setAttribute("source", guide.getFrom());
		template.setAttribute("alias", aliasList.getLast());
		template.setAttribute("object", guide.getSelect());
		template.setAttribute("collection", collection);
		template.setAttribute("expression", expression);

		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a select iterator
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param guide the Guide to the source expression
	 * @param expression argument passed to the iterator
	 * @return declarative code fragment for the select iterator
	 */
	protected String handleIterSelect(String collection, Guide guide, String expression) {
		guide.reset();
			
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_select");
		
		template.setAttribute("source", guide.getFrom());
		template.setAttribute("alias", aliasList.getLast());
		template.setAttribute("object", guide.getSelect());
		template.setAttribute("collection", collection);
		template.setAttribute("expression", expression);

		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a logical expression.
	 * 
	 * @param name name of the logical expression (and, or, xor, implies) 
	 * @param sourceCode declarative code fragment of the first expression
	 * @param firstArg declarative code fragment of the second expression
	 * @return declarative code fragment for the logical expression
	 */
	protected String handleLogicalExpression(String name, ITransformedCode sourceCode, ITransformedCode firstArg) {
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("logical_expression_" + name);
		
		template.setAttribute("expression1", sourceCode.getResultExp());
		template.setAttribute("expression2", firstArg.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a oclIsKindOf feature call.
	 * 
	 * @param guide the guide to the classifier type
	 * @return declarative code fragment for the oclIsKindOf feature call
	 */
	protected String handleOclIsKindOf(Guide guide) {
		guide.reset();
		
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_ocliskindof");
		
		template.setAttribute("source", guide.getFrom());
		template.setAttribute("object", guide.getSelect());
		template.setAttribute("alias", aliasList.getLast());
		
		return template.toString();		
	}
	
	/**
	 * Generates a declarative code fragment for a oclIsTypeOf feature call.
	 * 
	 * @param typeGuide the guide to the classifier type
	 * @param supertypeGuide the guide to the classifiers supertype
	 * @return declarative code fragment for the oclIsTypeOf feature call
	 */
	protected String handleOclIsTypeOf(Guide typeGuide, Guide supertypeGuide) {
		typeGuide.reset();
		if (supertypeGuide != null) supertypeGuide.reset();
		
		ITemplate template = this.mySettings.getTemplateGroup().getTemplate("feature_call_oclistypeof");
		
		template.setAttribute("source", typeGuide.getFrom());
		template.setAttribute("object", typeGuide.getSelect());
		template.setAttribute("alias", aliasList.getLast());
		template.setAttribute("source2", (supertypeGuide != null) ? supertypeGuide.getFrom() : null);
		
		return template.toString();		
	}
	
	/**
	 * Generates a declarative code fragment for a concat operation on strings.
	 * 
	 * @param sourceCode declarative code fragment of the first argument (usually a simple string)
	 * @param firstArg declarative code fragment of the second argument (usually a simple string)
	 * @return declarative code fragment for the concat operation
	 */
	protected String handleStringConcat(ITransformedCode sourceCode, ITransformedCode firstArg) {
		ITemplate template = mySettings.getTemplateGroup().getTemplate("feature_call_string_concat");
		
		template.setAttribute("operand1", sourceCode.getResultExp());
		template.setAttribute("operand2", firstArg.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a size operation on strings.
	 * 
	 * @param sourceCode declarative code fragment of the operand (the string to get the size from) 
	 * @return declarative code fragment for the size operation
	 */
	protected String handleStringSize(ITransformedCode sourceCode) {
		ITemplate template = mySettings.getTemplateGroup().getTemplate("feature_call_string_size");
		
		template.setAttribute("operand", sourceCode.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a substring operation.
	 * 
	 * @param sourceCode declarative code fragment of the operand (the string to build the substring from) 
	 * @param start the start of the substring
	 * @param iTransformedCode the end of the substring
	 * @return declarative code fragment for the substring operation
	 */
	protected String handleStringSubstring(ITransformedCode sourceCode, ITransformedCode start, ITransformedCode end) {
		ITemplate template = mySettings.getTemplateGroup().getTemplate("feature_call_string_substring");
		
		template.setAttribute("operand", sourceCode.getResultExp());
		template.setAttribute("start", start.getResultExp());
		template.setAttribute("end", end.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a toLower operation on strings.
	 * 
	 * @param sourceCode declarative code fragment of the operand
	 * @return declarative code fragment for the toLower operation
	 */
	protected String handleStringToLower(ITransformedCode sourceCode) {
		ITemplate template = mySettings.getTemplateGroup().getTemplate("feature_call_string_tolower");
		
		template.setAttribute("operand", sourceCode.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a toUpper operation on strings.
	 * 
	 * @param sourceCode declarative code fragment of the operand
	 * @return declarative code fragment for the toUpper operation
	 */
	protected String handleStringToUpper(ITransformedCode sourceCode) {
		ITemplate template = mySettings.getTemplateGroup().getTemplate("feature_call_string_toupper");
		
		template.setAttribute("operand", sourceCode.getResultExp());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a unary minus operation.
	 * 
	 * @param sourceCode declarative code fragment of the operand
	 * @return declarative code fragment for the unary minus operation
	 */
	protected String handleUnaryMinus(ITransformedCode sourceCode) {
		ITemplate template = mySettings.getTemplateGroup().getTemplate("unary_expression_minus");
		
		template.setAttribute("operand", sourceCode.getResultExp());
		
		return template.toString();
		
	}
	
	/**
	 * Generates a declarative code fragment for a unary not operation.
	 * 
	 * @param sourceCode declarative code fragment of the operand
	 * @return declarative code fragment for the unary not operation
	 */
	protected String handleUnaryNot(ITransformedCode sourceCode) {
		ITemplate template = mySettings.getTemplateGroup().getTemplate("unary_expression_not");
		
		template.setAttribute("operand", sourceCode.getResultExp());
		
		return template.toString();		
	}
	
	/**
	 * <p>
	 * Generates the code for a binary {@link Operation} of an
	 * {@link OperationCallExp}.
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

		//TODO: OperationCall/OclOperationWithTypeArgExp
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
		
		List<OclExpression> arguments = anOperationCallExp.getArgument();
		ITransformedCode firstArg = null;
		List<ITransformedCode> args = new ArrayList<ITransformedCode>();
        for(int i = 0; i < arguments.size(); i++) {
			args.add(doSwitch((OclExpression) arguments.get(i)));
        }
        if (args.size() > 0) {
        	firstArg = args.get(0);
        }

		String operationName;

		result = new TransformedCodeImpl();

		/* Transform Code for the source of the operation call. */
		sourceExp = anOperationCallExp.getSource();

		/* Check if the referred operation is static. */
		if (anOperationCallExp.getReferredOperation() != null
				&& anOperationCallExp.getReferredOperation().isStatic()) {
			ITransformedType transformedSourceType;

			/* The type of the source becomes the source expression. */
			sourceType = anOperationCallExp.getSourceType();
			
			sourceCode = new TransformedCodeImpl();

		}

		else {
			sourceType = sourceExp.getType();
			sourceCode = this.doSwitch((EObject) sourceExp);
			result.addCode(sourceCode.getResultExp());
		}

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

		if (operationName.equals("=")) {
			resultExp = (handleRelExpression("equals", sourceExp, sourceCode, firstArg));						
		} else if (operationName.equals("<>")) {
			resultExp = (handleRelExpression("nequals", sourceExp, sourceCode, firstArg));						
		} else if (operationName.equals(">")) {
			resultExp = (handleRelExpression("greater", sourceExp, sourceCode, firstArg));
		} else if (operationName.equals(">=")) {
			resultExp = (handleRelExpression("greaterequal", sourceExp, sourceCode, firstArg));
		} else if (operationName.equals("<")) {
			resultExp = (handleRelExpression("lesser", sourceExp, sourceCode, firstArg));
		} else if (operationName.equals("<=")) {
			resultExp = (handleRelExpression("lesserequal", sourceExp, sourceCode, firstArg));
		}
		
		// arithmetic expressions
		else if (operationName.equals("+")) {
			resultExp = (handleArithmeticOperation("plus", sourceCode, firstArg));
		} else if (operationName.equals("*")) {
			resultExp = (handleArithmeticOperation("mult", sourceCode, firstArg));
		} else if (operationName.equals("/")) {
			resultExp = (handleArithmeticOperation("div", sourceCode, firstArg));
		} else if (operationName.equals("-")) {
			if (args.isEmpty()) { // unary minus special case
				resultExp = (handleUnaryMinus(sourceCode));
			} else {
				resultExp = (handleArithmeticOperation("minus", sourceCode, firstArg));
			}
		}

		// unary expressions (unary minus special case above)
		else if (operationName.equals("not")) {
			resultExp = (handleUnaryNot(sourceCode));
		}

		// logical expression
		else if (operationName.equals("and")) {
			resultExp = (handleLogicalExpression("and", sourceCode, firstArg));
		} else if (operationName.equals("or")) {
			resultExp = (handleLogicalExpression("or", sourceCode, firstArg));
		} else if (operationName.equals("xor")) {
			resultExp = (handleLogicalExpression("xor", sourceCode, firstArg));
		} else if (operationName.equals("implies")) {
			resultExp = (handleLogicalExpression("implies", sourceCode, firstArg));
		}
		
		
		// collection related operations - BASIC VALUE
		else if (operationName.equals("count")) {
			resultExp = (handleCollCount(sourceCode, navigationMap.get(sourceExp).get(0), firstArg));
		} else if (operationName.equals("size") && !(sourceExp.getType() instanceof PrimitiveType)) {
			resultExp = (handleCollSize(sourceCode, navigationMap.get(sourceExp).get(0)));
		} else if (operationName.equals("sum")) {
			List<Guide> guides;
			if (sourceExp instanceof OperationCallExp && ((OperationCallExp)sourceExp).getReferredOperation().getName().equals("asSet")) {
				guides = navigationMap.get(((OperationCallExp)sourceExp).getSource());
			} else {
				guides = navigationMap.get(sourceExp);
			}
			resultExp = (handleCollSum(sourceCode, guides.get(0)));
		}
		
		// collection related operations - COMPLEX PREDICATE
		else if (operationName.equals("includes")) {
			resultExp = (handleCollIncludes(sourceCode, firstArg));
		} else if (operationName.equals("excludes")) {
			resultExp = (handleCollExcludes(sourceCode, firstArg));
		} else if (operationName.equals("includesAll")) {
			resultExp = (handleCollIncludesAll(sourceCode, firstArg));
		} else if (operationName.equals("excludesAll")) {
			resultExp = (handleCollExcludesAll(sourceCode, firstArg));
		} else if (operationName.equals("isEmpty")) {
			resultExp = (handleCollIsEmpty(sourceCode));
		} else if (operationName.equals("notEmpty")) {
			resultExp = (handleCollNotEmpty(sourceCode));
		} else if (operationName.equals("exists")) {
			resultExp = (handleCollExists(sourceCode, navigationMap.get(sourceExp).get(0), firstArg));
		} else if (operationName.equals("intersection")) {
			resultExp = (handleCollIntersection(sourceCode, firstArg, (CollectionType)sourceExp.getType()));
		} else if (operationName.equals("including")) {
			resultExp = (handleCollIncluding(sourceCode, firstArg, (CollectionType)sourceExp.getType()));
		} else if (operationName.equals("excluding")) {
			resultExp = (handleCollExcluding(sourceCode, firstArg, (CollectionType)sourceExp.getType()));
		} else if (operationName.equals("union")) {
			resultExp = (handleCollUnion(sourceCode, firstArg, (CollectionType)sourceExp.getType()));
		} else if (operationName.equals("symmetricDifference")) {
			resultExp = (handleCollSymmetricDifference(sourceCode, firstArg));
		}
		
		// collection related operations - MODEL TYPE QUERY
		else if (operationName.equals("allInstances")) {
			List<Guide> guides = assignClassGuide(anOperationCallExp, anOperationCallExp.getSourceType()); // create Guide object to the return value of allInstances for later use in the parent of this expression
			resultExp = (handleAllInstances(guides.get(0)));
		}
		
		// BASIC TYPE - String operations
		else if (operationName.equals("size")) {
			resultExp = (handleStringSize(sourceCode));
		} else if (operationName.equals("concat")) {
			resultExp = (handleStringConcat(sourceCode, firstArg));
		} else if (operationName.equals("toUpper")) {
			resultExp = (handleStringToUpper(sourceCode));
		} else if (operationName.equals("toLower")) {
			resultExp = (handleStringToLower(sourceCode));
		} else if (operationName.equals("substring")) {
			resultExp = (handleStringSubstring(sourceCode, firstArg, args.get(1)));
		}
		
		// BASIC TYPE - Real and Integer operations
		else if (operationName.equals("abs")) {
			resultExp = (handleIntAbs(sourceCode));
		} else if (operationName.equals("floor")) {
			resultExp = (handleIntFloor(sourceCode));
		} else if (operationName.equals("max")) {
			resultExp = (handleIntMax(sourceCode, firstArg));
		} else if (operationName.equals("min")) {
			resultExp = (handleIntMin(sourceCode, firstArg));
		} else if (operationName.equals("round")) {
			resultExp = (handleIntRound(sourceCode));
		} else if (operationName.equals("div")) {
			resultExp = (handleIntDiv(sourceCode, firstArg));
		} else if (operationName.equals("mod")) {
			resultExp = (handleIntMod(sourceCode, firstArg));
		}
		
		else if (operationName.equals("asSet")) {
			resultExp = "";
			navigationMap.put(anOperationCallExp, navigationMap.get(anOperationCallExp.getSource()));
				
		} else if (operationName.equals("oclIsTypeOf")) {
			resultExp = (handleOclIsKindOf(this.getSettings().getMappedModel().getClass(anOperationCallExp.getType().getName()).getClassGuide()));
		} else if (operationName.equals("oclIsKindOf")) {
			List<Type> parents = query_Supertypes(anOperationCallExp.getType());
			
			if (parents.size() != 1) {
	    		throw new IllegalStateException("Illegal number of supertypes for type: " + (anOperationCallExp.getType()).getName() + " !");
	    	}
			
			resultExp = (handleOclIsTypeOf(this.getSettings().getMappedModel().getClass(anOperationCallExp.getType().getName()).getClassGuide(),
					this.getSettings().getMappedModel().getClass(parents.get(0).getName()).getClassGuide()));
		}

		else {
			throw new UnsupportedOperationException("The operation " + operationName + "is not supported.");
		}

		result.setResultExp(resultExp);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseOperationCallExp(OperationCallExp)"
					+ " - end - return value=" + result);
		}
		// no else.

		return result;
	}
	
	/**
	 * Assigns a class guide to the given OclExpression which are used during the
	 * code generation process.
	 * 
	 * @param exp the OclExpression to assign the Guide for.
	 */
	protected List<Guide> assignClassGuide(OclExpression exp, Type type) {
		List<Guide> guides = new ArrayList<Guide>();
		guides.add(mySettings.getMappedModel().getClass(type.getName()).getClassGuide());
		navigationMap.put(exp, guides);
		return guides;
	}
	
	/**
	 * Creates a declarative code fragment for a navigation described by the Guide objects
	 * in the guides parameter.
	 * 
	 * @param guides the guides which are describing the navigation
	 * @return declarative code fragment for a navigation in the target model
	 */
	protected ITransformedCode createNavigation(List<Guide> guides) {
		String navigationExpression = "";
		String targetCodeArtifact = "";
	    String placeHolder = "##_dt_##";
	    
	    Guide guide;
	    ITemplate template;
	    
	    for (int i = 0; i < guides.size(); i++) {
	    	guide = guides.get(i); 
	    	guide.reset();
						
	    	for (int k = 0; k < guide.numberOfSteps(); k++, guide.next()) {

	    		// last step must be treated in a special way
	    		if ((i == (guides.size() - 1))  && (k == (guide.numberOfSteps() - 1))) {
	    			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_navigation_context");
	    			template.setAttribute("context_alias", guide.getAlias());
	    			template.setAttribute("context_object", contextList.getLast());
	    		} else {
	    			template = this.mySettings.getTemplateGroup().getTemplate("feature_call_navigation");
	    			template.setAttribute("context2", placeHolder);
	    		}
                                
	    		template.setAttribute("object", guide.getSelect());
	    		template.setAttribute("context1", guide.getFrom());
	    		template.setAttribute("ref_object", guide.getWhere());
                
	    		targetCodeArtifact = template.toString();
               
	    		if (navigationExpression.length() == 0) {
	    			navigationExpression = targetCodeArtifact;
	    		} else {
	    			navigationExpression = navigationExpression.replaceAll(placeHolder, targetCodeArtifact);
                }
   			}
	    }
	    ITransformedCode tc = new TransformedCodeImpl();
	    tc.setResultExp(navigationExpression);
	    return tc;
    }
	
	/**
	 * Assigns guides to the given OclExpression which are used during the
	 * code generation process.
	 * 
	 * @param exp the OclExpression to assign the Guides for.
	 */
	protected List<Guide> assignGuides(OclExpression exp) {
		Guide guide;
		List<Guide> guides = new ArrayList<Guide>();
		OclExpression next = exp;
		OclExpression tmpNext;
		String featureName;	// used for attribute/association end names
		String startType;	// used for attribute/association end types
		

		if (navigationMap.containsKey(exp)) {
			return navigationMap.get(exp);
		}

		while (next != null) {
			if (next instanceof PropertyCallExp) {
				PropertyCallExp ac = (PropertyCallExp)next;
				tmpNext = ac.getSource();
				featureName = ac.getReferredProperty().getName();
				startType = tmpNext.getType().getName();
				IMappedClass ic = this.mySettings.getMappedModel().getClass(startType);
				if (ac.getReferredProperty() instanceof AssociationProperty) { 
					//featureName = this.mySettings.getForeignKeyPrefix() + featureName;
					guide = this.mySettings.getMappedModel().getClass(startType).getAssociationEndGuide(featureName);
				} else guide = this.mySettings.getMappedModel().getClass(startType).getAttributeGuide(featureName);
			} else {
				break;
			}
			
			// assign alias to last element
			if (tmpNext == null || tmpNext instanceof VariableExp) {
				guide.setAlias(aliasList.getLast());
			}
			
			guides.add(guide);
			next = tmpNext;
		}
		
		navigationMap.put(exp, guides);
		
		return guides;
	}
	
	@Override
	public ITransformedCode casePropertyCallExp(PropertyCallExp object) {
		ITransformedCode transformCode = new TransformedCodeImpl();
		List<Guide> guides = assignGuides(object);;
		Guide guide = guides.get(0);
        guide.reset();
	
        // attribute access without navigation
		if (guides.size() == 1) {
			ITemplate template = mySettings.getTemplateGroup().getTemplate("feature_call_attribute_context");
			template.setAttribute("context_alias", guide.getAlias());
			template.setAttribute("attribute", guide.getSelect());
			
			// special case for Boolean attributes: e.g. expand 'attribute' to 'attribute = 1' in SQL
			if (object.getReferredProperty().getType().getName().equals("Boolean")) {
				String attr = template.toString();
				template = mySettings.getTemplateGroup().getTemplate("feature_call_attribute_boolean");
				template.setAttribute("attribute", attr);
			}
			transformCode.setResultExp(template.toString());
			return transformCode;
		}
		// attribute access with navigation
		else {	
			return createNavigation(guides);
		}     
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

		ITransformedCode result;

		ITemplate template;

		template = mySettings.getTemplateGroup().getTemplate("literal_real");
		
		// parameterize the template engine
		template.setAttribute("value", new Double(aRealLiteralExp.getRealSymbol()).toString());
	

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

		ITransformedCode result;

		ITemplate template = mySettings.getTemplateGroup().getTemplate("literal_string");
		
		// parameterize the template engine
		template.setAttribute("value", aStringLiteralExp.getStringSymbol());

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

		ITransformedCode result = new TransformedCodeImpl();

		ITemplate template = mySettings.getTemplateGroup().getTemplate("literal_variable");
		
		// parameterize the template engine
		template.setAttribute("value", aVariableExp.getName());
		
		result.setResultExp(template.toString());


		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caseVariableExp(VariableExp)"
					+ " - end - return value=" + result);
		}
		// no else.

		return result;
	}
	
	/**
	 *  Resets the DeclarativeCodeGenerator to initial values.
     *  Must be used, if multiple invariants will be translated 
     *  by one code generator object.
	 */
	public void reset() {
		String firstAlias = aliasList.getFirst();
		aliasList.clear();
		aliasList.add(firstAlias);
		contextList.remove();
		navigationMap.clear();
	}
	
	private List<Type> query_Supertypes(Type type) {
		List<Type> parents = new ArrayList<Type>();
		for (Type t : type.getSuperType()) {
			if (t instanceof AnyType) continue;
			parents.add(t);
			parents.addAll(query_Supertypes(t));
		}
		return parents;
	}

}