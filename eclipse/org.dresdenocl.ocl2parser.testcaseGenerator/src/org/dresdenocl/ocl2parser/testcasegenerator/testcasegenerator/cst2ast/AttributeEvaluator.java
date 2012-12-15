/*
    Copyright (C) 2008  Nils (s0006383@inf.tu-dresden.de)

    This file is part of the testcase generator for OCL parser of the Dresden OCL2 for Eclipse.

    The testcase generator is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The testcase generator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with the testcase generator.  If not, see <http://www.gnu.org/licenses/>.
.
*/
package org.dresdenocl.ocl2parser.testcasegenerator.testcasegenerator.cst2ast;


import java.util.Iterator;
import java.util.List;

import org.dresdenocl.ocl2parser.internal.NodeFactory;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IAbstractModel;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IAsSetElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IComplexElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IDeclarationContainer;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IErrorElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IEssentialOclElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IGenModelFactory;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IIntegerElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IMetamodelReference;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IModelExpression;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IModelReference;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.INullElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IOperationNewElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IPackageDeclaration;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IPropertyNewElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IRealElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IResult;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IStringElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITestSuite;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITestcase;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITestcaseElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IVariable;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.AbstractModel;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.CollectionKindElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.ConstraintKindElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.DeclarationContainer;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.EnumerationLiteralElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.ErrorElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.EssentialOclElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.IntegerElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.ListElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.MetamodelReference;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.ModelExpression;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.ModelReference;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.NamespaceElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.NullElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.OperationStaticElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.PackageDeclaration;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.PropertyStaticElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.RealElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.Result;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.StringElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.TestSuite;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.Testcase;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.TestcaseElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.TokenAS;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.TypeElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl.Variable;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.analysis.AttrEvalException;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.analysis.LAttrEvalAdapter;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TAbstractmodel;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TAtpre;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TError;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TFirstName;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TIdent;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TIntegerValue;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TNull;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TOclblock;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TPackageName;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TRealValue;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TStringLiteral;
import org.dresdenocl.ocl2parser.testcasegenerator.gen.testcasegenerator.node.Token;
import org.dresdenocl.ocl2parser.testcasegenerator.util.Heritage;

/**
 * This class builds the abstract model that is underlying the concrete grammar
 * of the testcase generator. Note that at the end of this model creation, no
 * constraint is examined. So for example: a variable can be declared twice. The
 * examination will be done in the visitors. See package <code>visitor</code>.
 * This class is almost trivial because it takes the instance that are created
 * by the parser and packs this in a new instance that is created by the parser.
 * For example: we have the rule
 * 
 * testcase_element<ITestcaseElement> = testcasename brace_open ocl_expression result brace_close;
 * 
 * The corresponding method that handle this rule is <code>computeAstFor_ATestcaseElement</code>. This
 * method get a <code>ITestcaseElement</code>, two <code>ITokenAS</code> instances and a <code>IResult</code>
 * instance. The <code>ITestcaseElement</code> is created by the parser and must only be filled with the 
 * other elements. So in the rule we see that we have a 'testcasename' (the first <code>ITokenAS</code> instance),
 * an 'ocl_expression' (the second <code>ITokenAS</code> instance) and a nonterminal 'result' that
 * has the type <code>IResult</code>.
 * 
 * Most of these methods are so trivial, but some are not. First the method <code>computeAstFor_AModelelementModelExpression</code>.
 * This rule stands for the rule
 * 
 * model_expression<IModelExpression> = {modelelement}<IModelExpression> ident openparen parameters? closeparen#nocreate
 * 
 * In this method we make the decision what element of the abstract syntax should be built. For example:
 * if 'ident' is 'asSet' then we build an {@link IAsSetElement}. If 'ident' is 'PropertyCallExp' then we
 * build a {@link IEssentialOclElement} and so on.
 * 
 * The second method that is special is <code>createNodeFor_TOclblock</code>. This rule is called whenever a token
 * 'OclBlock' is recognized. The ocl block consists of two delimiters, namely '{#' and '#}'. These
 * delimiter must be removed because the next processing steps assume that these delimiters are removed.
 * 
 * 
 * The next method that is special is <code>computeAstFor_ANewModelExpression</code>. This method
 * corresponds to the rule:
 * 
 * model_expression<IModelExpression> = {new}<IModelExpression> new ident openparen parameters closeparen#nocreate;
 * 
 * In the method we make sure that the ident is either 'Property' or 'Operation'.
 * 
 * 
 * Next the method <code>computeAstFor_AStringSimpleExpression</code>. This method corresponds to the rule:
 * 
 * simple_expression<ISimpleElement> = {string}<IStringElement> [first]:tick string_literal? [last]:tick;
 * 
 * The terminals 'tick' are ignored. The user can writer '', so 'string_literal' is null. But we need a token
 * because we don't want null reference. So in this method we create a dummy token that has invalid position
 * information.
 * 
 * @author Nils
 *
 */
public class AttributeEvaluator extends LAttrEvalAdapter {
	
	public AttributeEvaluator(IGenModelFactory factory) {
		super.setNodeFactory(factory);
	}

	@Override
	public IVariable computeAstFor_AAssignment(IVariable myAst,
			Heritage nodeHrtg, ITokenAS astIdent,
			IModelExpression astModelExpression) throws AttrEvalException {
		
		myAst.setToken(astIdent);
		myAst.setReference(astModelExpression);
		return myAst;
	}

	@Override
	public IVariable computeAstFor_AIdentModelExpression(IVariable myAst,
			Heritage nodeHrtg, ITokenAS astIdent) throws AttrEvalException {
		
		myAst.setToken(astIdent);
		return myAst;
	}

	@Override
	public IIntegerElement computeAstFor_AIntegerSimpleExpression(
			IIntegerElement myAst, Heritage nodeHrtg, ITokenAS astIntegerValue)
			throws AttrEvalException {
		
		myAst.setToken(astIntegerValue);
		return myAst;
	}

	@Override
	public IMetamodelReference computeAstFor_AMetamodelDesc(
			IMetamodelReference myAst, Heritage nodeHrtg,
			ITokenAS astPackageName) throws AttrEvalException {
		
		myAst.setToken(astPackageName);
		return myAst;
	}

	@Override
	public IDeclarationContainer computeAstFor_AMetamodelModelDeclaration(
			IDeclarationContainer myAst, Heritage nodeHrtg,
			IMetamodelReference astMetamodelDesc, IModelReference astModelDesc)
			throws AttrEvalException {
		
		myAst.setMetamodelRef(astMetamodelDesc);
		myAst.setModelRef(astModelDesc);
		return myAst;
	}

	@Override
	public IModelReference computeAstFor_AModelDesc(IModelReference myAst,
			Heritage nodeHrtg, ITokenAS astStringLiteral)
			throws AttrEvalException {
		
		myAst.setToken(astStringLiteral);
		return myAst;
	}

	@Override
	public IDeclarationContainer computeAstFor_AModelModelDeclaration(
			IDeclarationContainer myAst, Heritage nodeHrtg,
			IModelReference astModelDesc, IMetamodelReference astMetamodelDesc)
			throws AttrEvalException {
		
		myAst.setMetamodelRef(astMetamodelDesc);
		myAst.setModelRef(astModelDesc);
		return myAst;
	}

	@Override
	public IModelExpression computeAstFor_AModelelementModelExpression(
			Heritage nodeHrtg, ITokenAS astIdent,
			List astParameters,
	        ITokenAS astAtpre) throws AttrEvalException {
		
		String element = astIdent.getValue();
		IComplexElement cElement = null;
		
		if (element.equals("StaticProperty")) {
			cElement = factory.createPropertyStaticElement(astIdent);
		} else 
		
		if (element.equals("Operation")) {
			cElement = factory.createOperationElement(astIdent);
		} else 
		
		if (element.equals("Namespace")) {
			cElement = factory.createNamespaceElement(astIdent);
		} else 
		
		if (element.equals("Constraint")) {
			cElement = factory.createConstraintElement(astIdent);
		} else 
		
		if (element.equals("ConstraintKind")) {
			cElement = factory.createConstraintKindElement(astIdent);
		} else 
		
		if (element.equals("CollectionKind")) {
			cElement = factory.createCollectionKindElement(astIdent);
		} else 
		
		if (element.equals("Parameter")) {
			cElement = factory.createParameterElement(astIdent);
		} else 
		
		if (element.equals("ParameterKind")) {
			cElement = factory.createParameterKindElement(astIdent);
		} else 
		
		if (element.equals("EnumerationLiteral")) {
			cElement = factory.createEnumerationLiteralElement(astIdent);
		} else
		
		if (element.equals("Type")) {
			cElement = factory.createTypeElement(astIdent);
		} else
			
		if (element.equals("asSet")) {
			cElement = factory.crateAsSetElement(astIdent);
		} else 
		
		if (element.equals("List")) {
			cElement = factory.createListElement(astIdent);
		} else 
		
		if (element.equals("Property")) {
			cElement = factory.createPropertyElement(astIdent);
		} else
			
		if (element.equals("StaticOperation")) {
			cElement = factory.createOperationStaticElement(astIdent);
		} else 
			
		{
			cElement = factory.createEssentialOclElement(astIdent);
			if (astAtpre != null) {
				((IEssentialOclElement)cElement).setAtPre(true);
			}
			
		}
		
		if (astParameters != null) cElement.setParameter(astParameters);
		return cElement;
	}

	@Override
	public List computeAstFor_AMultipleParameters(List myAst,
			Heritage nodeHrtg, IModelExpression astParameter, List astParameters)
			throws AttrEvalException {
		
		astParameters.add(0, astParameter);
		return astParameters;
	}

	@Override
	public IModelExpression computeAstFor_ANewModelExpression(
			Heritage nodeHrtg, ITokenAS astIdent,
			List astParameters) throws AttrEvalException {
		
		if (astIdent.getValue().equals("Operation")) {
			IOperationNewElement newElement = super.factory.createOperationNewElement(astIdent);
			newElement.setParameter(astParameters);
			return newElement;
		}
		
		if (astIdent.getValue().equals("Property")) {
			IPropertyNewElement newElement = super.factory.createPropertyNewElement(astIdent);
			newElement.setParameter(astParameters);
			return newElement;
		}
		
		throw new AttrEvalException("The new operator can only be used in conjunction with" +
				" the 'Property' or the 'Operation' element. The error ocurred at line " +
				astIdent.getLine() + " and column " + astIdent.getColumn());
	}

	@Override
	public INullElement computeAstFor_ANullModelExpression(INullElement myAst,
			Heritage nodeHrtg, ITokenAS astNull) throws AttrEvalException {
		
		myAst.setToken(astNull);
		return myAst;
	}

	@Override
	public IPackageDeclaration computeAstFor_APackageDeclaration(
			IPackageDeclaration myAst, Heritage nodeHrtg,
			ITokenAS astPackageName) throws AttrEvalException {
		
		myAst.setToken(astPackageName);
		return myAst;
	}

	@Override
	public IRealElement computeAstFor_ARealSimpleExpression(IRealElement myAst,
			Heritage nodeHrtg, ITokenAS astRealValue) throws AttrEvalException {
		
		myAst.setToken(astRealValue);
		return myAst;
	}

	@Override
	public IStringElement computeAstFor_AStringSimpleExpression(
			IStringElement myAst, Heritage nodeHrtg, ITokenAS astStringLiteral)
			throws AttrEvalException {
		
		if (astStringLiteral != null) myAst.setToken(astStringLiteral);
		else {
			ITokenAS dummyToken = super.factory.createToken("", -1, -1);
			myAst.setToken(dummyToken);
		}
		return myAst;
	}

	@Override
	public ITestcaseElement computeAstFor_ATestcaseElement(
			ITestcaseElement myAst, Heritage nodeHrtg,
			ITokenAS astTestcasename, ITokenAS astOclExpression,
			IResult astResult) throws AttrEvalException {
		
		myAst.setOclExpression(astOclExpression);
		myAst.setResult(astResult);
		myAst.setToken(astTestcasename);
		return myAst;
	}

	@Override
	public ITestcase computeAstFor_ATestcasefile(ITestcase myAst,
			Heritage nodeHrtg, ITokenAS astName,
			IPackageDeclaration astPackageDeclaration,
			IDeclarationContainer astModelDeclaration,
			List astListTestcaseElement) throws AttrEvalException {
		
		myAst.setToken(astName);
		myAst.setMetamodelReference(astModelDeclaration.getMetamodelRef());
		myAst.setModelReference(astModelDeclaration.getModelRef());
		if (astPackageDeclaration != null) myAst.setPackageDeclaration(astPackageDeclaration);
		myAst.setTestcaseElements(astListTestcaseElement);
		return myAst;
	}

	@Override
	public ITestSuite computeAstFor_ATestsuite(ITestSuite myAst,
			Heritage nodeHrtg, ITokenAS astNames,
			IPackageDeclaration astPackageDeclaration, List astListSuiteElement)
			throws AttrEvalException {
		
		myAst.setToken(astNames);
		if (astPackageDeclaration != null) myAst.setPackageDeclaration(astPackageDeclaration);
		myAst.setTestElementsToken(astListSuiteElement);
		return myAst;
	}

	@Override
	public ITokenAS createNodeFor_TFirstName(TFirstName node, Heritage nodeHrtg)
			throws AttrEvalException {
		
		return createTokenFromSableCCToken(node);
	}

	@Override
	public ITokenAS createNodeFor_TIdent(TIdent node, Heritage nodeHrtg)
			throws AttrEvalException {
		
		return createTokenFromSableCCToken(node);
	}

	@Override
	public ITokenAS createNodeFor_TIntegerValue(TIntegerValue node,
			Heritage nodeHrtg) throws AttrEvalException {
		
		return createTokenFromSableCCToken(node);
	}

	@Override
	public ITokenAS createNodeFor_TNull(TNull node, Heritage nodeHrtg)
			throws AttrEvalException {
		return createTokenFromSableCCToken(node);
	}

	@Override
	public ITokenAS createNodeFor_TOclblock(TOclblock node, Heritage nodeHrtg)
			throws AttrEvalException {
		/*
		 * The ocl expression is enclosed in the delimiter '{#' and '#}'
		 * but we need to get rid of this. So we get the first index of
		 * the first delimiter and build a substring that doesn't contain
		 * this first delimiter. Secondly we get the last index of the
		 * second delimiter and we get rid of this. So finally we have
		 * the ocl expression that are freed of the delimiter.
		 */
		int firstDelimiter = node.getText().indexOf("{#");
		
		String oclExpFirst = node.getText().substring(firstDelimiter+2);
		
		int lastDelimiter = oclExpFirst.lastIndexOf("#}");
		String oclExpLast = oclExpFirst.substring(0, lastDelimiter);
		
		// Last we must set the new text in the token.
		node.setText(oclExpLast);
		return createTokenFromSableCCToken(node);
	}

	@Override
	public ITokenAS createNodeFor_TPackageName(TPackageName node,
			Heritage nodeHrtg) throws AttrEvalException {
		return createTokenFromSableCCToken(node);
	}

	@Override
	public ITokenAS createNodeFor_TRealValue(TRealValue node, Heritage nodeHrtg)
			throws AttrEvalException {
		return createTokenFromSableCCToken(node);
	}

	@Override
	public ITokenAS createNodeFor_TStringLiteral(TStringLiteral node,
			Heritage nodeHrtg) throws AttrEvalException {
		return createTokenFromSableCCToken(node);
	}

	@Override
	public IAbstractModel computeAstFor_AAbstractModel(IAbstractModel myAst,
			Heritage nodeHrtg, ITokenAS astAbstractmodel,
			ITokenAS astFirstName, IModelExpression astModelExpression,
			List astListAssignment) throws AttrEvalException {
		
		myAst.setToken(astAbstractmodel);
		
		IVariable variable = factory.createVariable(astFirstName);
		variable.setReference(astModelExpression);
		
		astListAssignment.add(0, variable);
		myAst.setVariables(astListAssignment);
		return myAst;
	}

	@Override
	public IErrorElement computeAstFor_AErrorResult(IErrorElement myAst,
			Heritage nodeHrtg, ITokenAS astError) throws AttrEvalException {
		
		myAst.setToken(astError);
		return myAst;
	}

	@Override
	public ITokenAS createNodeFor_TAbstractmodel(TAbstractmodel node,
			Heritage nodeHrtg) throws AttrEvalException {
		return createTokenFromSableCCToken(node);
	}

	@Override
	public ITokenAS createNodeFor_TError(TError node, Heritage nodeHrtg)
			throws AttrEvalException {
		return createTokenFromSableCCToken(node);
	}
	
	private ITokenAS createTokenFromSableCCToken(Token token) {
		return super.factory.createToken(token.getText(), token.getLine(), token.getPos());
	}

	@Override
	public List computeAstFor_ASingleParameters(List myAst, Heritage nodeHrtg,
			IModelExpression astParameter) throws AttrEvalException {
		
		myAst.add(astParameter);
		return myAst;
	}

	@Override
	public ITokenAS createNodeFor_TAtpre(TAtpre node, Heritage nodeHrtg)
			throws AttrEvalException {
		return createTokenFromSableCCToken(node);
	}
}
