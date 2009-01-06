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
package tudresden.ocl20.pivot.ocl2parser.testcasegenerator.testcasegenerator.cst2ast;


import java.util.Iterator;
import java.util.List;

import tudresden.ocl20.pivot.ocl2parser.internal.NodeFactory;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IAbstractModel;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IAsSetElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IComplexElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IDeclarationContainer;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IErrorElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IEssentialOclElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IGenModelFactory;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IIntegerElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IMetamodelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelExpression;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.INullElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IOperationNewElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IPackageDeclaration;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IPropertyNewElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IRealElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IResult;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IStringElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestSuite;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestcase;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestcaseElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IVariable;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.AbstractModel;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.CollectionKindElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.ConstraintKindElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.DeclarationContainer;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.EnumerationLiteralElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.ErrorElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.EssentialOclElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.IntegerElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.ListElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.MetamodelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.ModelExpression;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.ModelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.NamespaceElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.NullElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.OperationStaticElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.PackageDeclaration;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.PropertyStaticElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.RealElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.Result;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.StringElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.TestSuite;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.Testcase;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.TestcaseElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.TokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.TypeElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.Variable;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.analysis.AttrEvalException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.analysis.LAttrEvalAdapter;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TAbstractmodel;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TError;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TFirstName;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TIdent;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TIntegerValue;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TNull;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TOclblock;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TPackageName;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TRealValue;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.node.TStringLiteral;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.gen.testcasegenerator.node.Token;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.Heritage;

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
			List astParameters) throws AttrEvalException {
		
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
}
