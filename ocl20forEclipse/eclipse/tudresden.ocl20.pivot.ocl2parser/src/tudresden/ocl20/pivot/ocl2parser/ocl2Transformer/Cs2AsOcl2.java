/*
    Copyright (C) 2008  Nils (s0006383@inf.tu-dresden.de)

    This file is part of the OCL parser of the Dresden OCL2 for Eclipse.

    The OCL parser is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The OCL parser is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with the OCL parser.  If not, see <http://www.gnu.org/licenses/>.
.
*/

package tudresden.ocl20.pivot.ocl2parser.ocl2Transformer;

import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.AttrOrAssocContextAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.BooleanLiteralExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.ClassifierConstraintAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.ClassifierContextAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.CollectionItemAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.CollectionLiteralExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.CollectionLiteralPartAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.CollectionRangeAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.CollectionTypeAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.ConstraintAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.DefAttributeConstraintAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.DefOperationConstraintAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.DeriveAttrOrAssocConstraintAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.IfExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.InitAttrOrAssocConstraintAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.IntegerLiteralExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.InvClassifierConstraintAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.IterateExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.IteratorExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.LetExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.List;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.OclExpressionAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.OclFileAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.OperationCallExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.OperationConstraintAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.OperationContextAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.OperationSignatureAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.PackagedConstraintAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.PathNameAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.PropertyCallExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.RealLiteralExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.StringLiteralExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.TokenAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.TupleLiteralExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.TupleLiteralPartAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.TupleTypeAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.TypeAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.VariableAS;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.VariableExpAS;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.analysis.AttrEvalException;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.analysis.LAttrEvalAdapter;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TAop;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TAtpre;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TBag;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TBody;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TCollection;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TCollectionOperation;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TEquals;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TFalse;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TImplies;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TIntegerLiteral;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TIteratorOperation;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TMop;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TNot;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TOclAsType;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TOclIsKindOf;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TOclIsTypeOf;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TOrderedset;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TPost;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TPre;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TRealLiteral;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TRelop;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TSequence;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TSet;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TSimpleLog;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TSimpleName;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TStringLiteral;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.TTrue;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.Token;
import tudresden.ocl20.pivot.ocl2parser.internal.Heritage;

public class Cs2AsOcl2 extends LAttrEvalAdapter {

	@Override
	public List computeAstFor_AActualParameterListCs(List myAst,
			Heritage nodeHrtg, OclExpressionAS astActualParameterListElementCs,
			java.util.List astListActualParameterEnumCs)
			throws AttrEvalException {
		myAst.addChild(astActualParameterListElementCs);
		
		if (astListActualParameterEnumCs != null) {
			java.util.Iterator<OclExpressionAS> it = astListActualParameterEnumCs.iterator();
			while (it.hasNext()) {
				myAst.add(it.next());
			}
		}
		
		return myAst;
	}

	@Override
	public OperationCallExpAS computeAstFor_AAdditiveAdditiveExpCs(
			OperationCallExpAS myAst, Heritage nodeHrtg,
			OclExpressionAS astAdditiveExpCs, TokenAS astAop,
			OclExpressionAS astMultiplicativeExpCs) throws AttrEvalException {
		
		return fillOperationCallExpAS(myAst, astAdditiveExpCs, astAop, astMultiplicativeExpCs);
	}

	@Override
	public IterateExpAS computeAstFor_AArrowrightIteratePostfixExpCs(
			IterateExpAS myAst, Heritage nodeHrtg,
			OclExpressionAS astPostfixExpCs, VariableAS astIterateVarCs,
			VariableAS astInitializedVariableCs,
			OclExpressionAS astOclExpressionCs) throws AttrEvalException {
		
		myAst.setSource(astPostfixExpCs);
		myAst.setBody(astOclExpressionCs);
		
		if (astIterateVarCs != null) myAst.setIterator(astIterateVarCs);
		myAst.setResult(astInitializedVariableCs);
		return myAst;
	}

	@Override
	public IteratorExpAS computeAstFor_AArrowrightIteratorPostfixExpCs(
			IteratorExpAS myAst, Heritage nodeHrtg,
			OclExpressionAS astPostfixExpCs, TokenAS astIteratorOperation,
			List astIteratorVarCs, OclExpressionAS astOclExpressionCs)
			throws AttrEvalException {
		
		myAst.setSource(astPostfixExpCs);
		myAst.setOperation(astIteratorOperation);
		if (astIteratorVarCs != null) myAst.setIteratorsList(astIteratorVarCs);
		myAst.setBody(astOclExpressionCs);
		return myAst;
	}

	@Override
	public OperationCallExpAS computeAstFor_AArrowrightOperationCallPostfixExpCs(
			OperationCallExpAS myAst, Heritage nodeHrtg,
			OclExpressionAS astPostfixExpCs, TokenAS astCollectionOperation,
			List astActualParameterListCs) throws AttrEvalException {
		myAst.setSource(astPostfixExpCs);
		
		tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.List nameList = new tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.List();
		nameList.add(astCollectionOperation);
		
		PathNameAS operationName = new PathNameAS();
		operationName.setTokenASList(nameList);
		
		myAst.setName(operationName);
		myAst.setArrowRightExpression(true);
		if (astActualParameterListCs != null) myAst.setArgumentList(astActualParameterListCs);
		return myAst;
	}

	@Override
	public PropertyCallExpAS computeAstFor_AAssociationPropertyCallExpCs(
			PropertyCallExpAS myAst, Heritage nodeHrtg,
			PathNameAS astPathNameCs, List astActualParameterListCs,
			TokenAS astAtpre) throws AttrEvalException {
		
		myAst.setPathName(astPathNameCs);
		myAst.setQualifierList(astActualParameterListCs);
		if (astAtpre != null) myAst.setAtPre(astAtpre);
		return myAst;
	}

	@Override
	public Object computeAstFor_ACaretMsgOperatorCs(Object myAst,
			Heritage nodeHrtg) throws AttrEvalException {
		throw new RuntimeException("Not implemented yet, because the pivot model doesn't provide messages");
	}

	@Override
	public List computeAstFor_ACollectionLiteralPartsCs(List myAst,
			Heritage nodeHrtg,
			CollectionLiteralPartAS astCollectionLiteralPartCs,
			java.util.List astListCollectionLiteralPartEnumCs)
			throws AttrEvalException {
		
		myAst.add(astCollectionLiteralPartCs);
		if (astListCollectionLiteralPartEnumCs != null) {
			java.util.Iterator<CollectionLiteralPartAS> it = astListCollectionLiteralPartEnumCs.iterator();
			while(it.hasNext()) {
				myAst.add(it.next());
			}
		}
		
		return myAst;
	}

	@Override
	public CollectionRangeAS computeAstFor_ACollectionRangeCs(
			CollectionRangeAS myAst, Heritage nodeHrtg,
			OclExpressionAS astLeft, OclExpressionAS astRight)
			throws AttrEvalException {
		
		myAst.setFirst(astLeft);
		myAst.setLast(astRight);
		return myAst;
	}

	@Override
	public Object computeAstFor_ADblcaretMsgOperatorCs(Object myAst,
			Heritage nodeHrtg) throws AttrEvalException {
		throw new RuntimeException("Not implemented yet, because the pivot model doesn't provide messages");
	}

	@Override
	public DeriveAttrOrAssocConstraintAS computeAstFor_ADeriveInitOrDerValueCs(
			DeriveAttrOrAssocConstraintAS myAst, Heritage nodeHrtg,
			OclExpressionAS astOclExpressionCs) throws AttrEvalException {
		
		myAst.setExpression(astOclExpressionCs);
		return myAst;
	}

	@Override
	public OperationCallExpAS computeAstFor_ADotOperationCallPostfixExpCs(
			OperationCallExpAS myAst, Heritage nodeHrtg,
			OclExpressionAS astPostfixExpCs, TokenAS astPathNameCs,
			TokenAS astAtpre, List astActualParameterListCs)
			throws AttrEvalException {
		
		myAst.setSource(astPostfixExpCs);
		myAst.setArrowRightExpression(false);
		myAst.setDotExpression(true);
		List pathNameList = new List();
		pathNameList.add(astPathNameCs);
		PathNameAS pathName = new PathNameAS();
		pathName.setTokenASList(pathNameList);
		myAst.setName(pathName);
		if (astAtpre != null) myAst.setAtPre(astAtpre);
		if (astActualParameterListCs != null) myAst.setArgumentList(astActualParameterListCs);
		return myAst;
	}

	@Override
	public PropertyCallExpAS computeAstFor_ADotPropertyAssocCallPostfixExpCs(
			PropertyCallExpAS myAst, Heritage nodeHrtg,
			OclExpressionAS astPostfixExpCs, TokenAS astPathNameCs,
			List astActualParameterListCs, TokenAS astAtpre)
			throws AttrEvalException {
		
		myAst.setSource(astPostfixExpCs);
		List pathNameList = new List();
		pathNameList.add(astPathNameCs);
		PathNameAS pathName = new PathNameAS();
		pathName.setTokenASList(pathNameList);
		myAst.setPathName(pathName);
		myAst.setQualifierList(astActualParameterListCs);
		if (astAtpre != null) myAst.setAtPre(astAtpre);
		return myAst;
	}

	@Override
	public PropertyCallExpAS computeAstFor_ADotPropertyCallPostfixExpCs(
			PropertyCallExpAS myAst, Heritage nodeHrtg,
			OclExpressionAS astPostfixExpCs, TokenAS astPathNameCs,
			TokenAS astAtpre) throws AttrEvalException {
		
		myAst.setSource(astPostfixExpCs);
		List pathNameList = new List();
		pathNameList.add(astPathNameCs);
		PathNameAS pathName = new PathNameAS();
		pathName.setTokenASList(pathNameList);
		myAst.setPathName(pathName);
		if (astAtpre != null) myAst.setAtPre(astAtpre);
		return myAst;
	}

	@Override
	public Object computeAstFor_AExpressionParameterMessageArgCs(Object myAst,
			Heritage nodeHrtg, OclExpressionAS astOclExpressionCs)
			throws AttrEvalException {
		throw new RuntimeException("Not implemented yet, because the pivot model doesn't provide messages");
	}

	@Override
	public BooleanLiteralExpAS computeAstFor_AFalseBooleanLiteralExpCs(
			BooleanLiteralExpAS myAst, Heritage nodeHrtg, TokenAS astFalse)
			throws AttrEvalException {
		myAst.setValue(astFalse);
		return myAst;
	}

	@Override
	public VariableAS computeAstFor_AFormalInitializedParameterCs(
			Heritage nodeHrtg, VariableExpAS astFormalParameterCs)
			throws AttrEvalException {
		
		return astFormalParameterCs.getVariable();
	}

	@Override
	public VariableExpAS computeAstFor_AFormalParameterCs(VariableExpAS myAst,
			Heritage nodeHrtg, TokenAS astSimpleName,
			TypeAS astFormalParameterTypeSpecifier) throws AttrEvalException {
		tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.VariableAS variable = new tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.VariableAS();
		variable.setName(astSimpleName);
		variable.setType(astFormalParameterTypeSpecifier);
		myAst.setVariable(variable);
		return myAst;
	}

	@Override
	public List computeAstFor_AFormalParameterListCs(List myAst,
			Heritage nodeHrtg, VariableExpAS astFormalParameterCs,
			java.util.List astListFormalParameterEnumCs)
			throws AttrEvalException {
		
		myAst.add(astFormalParameterCs);
		if (astListFormalParameterEnumCs != null) {
			java.util.Iterator<VariableExpAS> it = astListFormalParameterEnumCs.iterator();
			while(it.hasNext()) {
				myAst.add(it.next());
			}
		}
		
		return myAst;
	}

	@Override
	public IfExpAS computeAstFor_AIfExpCs(IfExpAS myAst, Heritage nodeHrtg,
			OclExpressionAS astLogicalExpCs, OclExpressionAS astBody,
			OclExpressionAS astElsebody) throws AttrEvalException {
		
		myAst.setCondition(astLogicalExpCs);
		myAst.setThenExpression(astBody);
		myAst.setElseExpression(astElsebody);
		return myAst;
	}

	@Override
	public InitAttrOrAssocConstraintAS computeAstFor_AInitInitOrDerValueCs(
			InitAttrOrAssocConstraintAS myAst, Heritage nodeHrtg,
			OclExpressionAS astOclExpressionCs) throws AttrEvalException {
		
		myAst.setExpression(astOclExpressionCs);
		return myAst;
	}

	@Override
	public List computeAstFor_AInitializedVariableListCs(List myAst,
			Heritage nodeHrtg, VariableAS astInitializedVariableCs,
			java.util.List astListInitializedVariableEnumCs)
			throws AttrEvalException {
		myAst.add(astInitializedVariableCs);
		if (astListInitializedVariableEnumCs != null) {
			java.util.Iterator<VariableAS> it = astListInitializedVariableEnumCs.iterator();
			while(it.hasNext()) {
				myAst.add(it.next());
			}
		}
		
		
		return myAst;
	}

	@Override
	public InvClassifierConstraintAS computeAstFor_AInvClassifierConstraintCs(
			InvClassifierConstraintAS myAst, Heritage nodeHrtg,
			TokenAS astSimpleName, OclExpressionAS astOclExpressionCs)
			throws AttrEvalException {
		if (astSimpleName != null) myAst.setName(astSimpleName);
		myAst.setExpression(astOclExpressionCs);
		return myAst;
	}

	@Override
	public LetExpAS computeAstFor_ALetExpCs(LetExpAS myAst, Heritage nodeHrtg,
			tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.List astInitializedVariableListCs,
			OclExpressionAS astOclExpressionCs) throws AttrEvalException {
		myAst.setVariableList(astInitializedVariableListCs);
		myAst.setIn(astOclExpressionCs);
		return myAst;
	}

	@Override
	public OperationCallExpAS computeAstFor_ALogicalLogicalExpCs(
			OperationCallExpAS myAst, Heritage nodeHrtg,
			OclExpressionAS astLogicalExpCs, TokenAS astImplies,
			OclExpressionAS astSimpleLogicalExpCs) throws AttrEvalException {
		
		return fillOperationCallExpAS(myAst, astLogicalExpCs, astImplies, astSimpleLogicalExpCs);
	}

	@Override
	public Object computeAstFor_AMessageArgEnumCs(Object myAst,
			Heritage nodeHrtg, Object astMessageArgCs) throws AttrEvalException {
		
		throw new RuntimeException("Not implemented yet, because the pivot model doesn't provide messages");
	}

	@Override
	public Object computeAstFor_AMessageArgumentListCs(Object myAst,
			Heritage nodeHrtg, Object astMessageArgCs,
			java.util.List astListMessageArgEnumCs) throws AttrEvalException {
		
		throw new RuntimeException("Not implemented yet, because the pivot model doesn't provide messages");
	}

	@Override
	public OclExpressionAS computeAstFor_AMsgPostfixExpCs(
			OclExpressionAS myAst, Heritage nodeHrtg,
			OclExpressionAS astPostfixExpCs, Object astMsgOperatorCs,
			Object astSignalSpecExpCs) throws AttrEvalException {
		

		throw new RuntimeException("Not implemented yet, because the pivot model doesn't provide messages");
	}

	@Override
	public OperationCallExpAS computeAstFor_AMultiplicativeMultiplicativeExpCs(
			OperationCallExpAS myAst, Heritage nodeHrtg,
			OclExpressionAS astMultiplicativeExpCs, TokenAS astMop,
			OclExpressionAS astUnaryExpCs) throws AttrEvalException {
		
		return fillOperationCallExpAS(myAst, astMultiplicativeExpCs, astMop, astUnaryExpCs);
	}

	@Override
	public OperationSignatureAS computeAstFor_AOperationSignatureCs(
			OperationSignatureAS myAst, Heritage nodeHrtg,
			List astFormalParameterListCs,
			TypeAS astOperationReturnTypeSpecifierCs) throws AttrEvalException {
		
		if (astOperationReturnTypeSpecifierCs != null) myAst.setType(astOperationReturnTypeSpecifierCs);
		if (astFormalParameterListCs != null)  {
			tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.List variableList = transformListVariableExp(astFormalParameterListCs);
			myAst.setVariableASList(variableList);
		}
		return myAst;
	}

	@Override
	public OperationCallExpAS computeAstFor_AParameterPropertyCallExpCs(
			OperationCallExpAS myAst, Heritage nodeHrtg,
			PathNameAS astPathNameCs, TokenAS astAtpre,
			List astActualParameterListCs) throws AttrEvalException {
		
		myAst.setName(astPathNameCs);
		myAst.setArrowRightExpression(false);
		if (astAtpre != null) myAst.setAtPre(astAtpre);
		if (astActualParameterListCs != null) myAst.setArgumentList(astActualParameterListCs);
		return myAst;
	}

	@Override
	public PathNameAS computeAstFor_APathNameCs(PathNameAS myAst,
			Heritage nodeHrtg, TokenAS astIdentifierCs,
			java.util.List astListIdentifierEnumCs) throws AttrEvalException {
		
		myAst.addTokenAS(astIdentifierCs);
		if (astListIdentifierEnumCs != null) {
			java.util.Iterator<TokenAS> it = astListIdentifierEnumCs.iterator();
			while(it.hasNext()) {
				myAst.addTokenAS(it.next());
			}
		}
		
		return myAst;
	}

	@Override
	public Object computeAstFor_AQuestionmarkParameterMessageArgCs(
			Object myAst, Heritage nodeHrtg,
			TypeAS astFormalParameterTypeSpecifier) throws AttrEvalException {
		
		throw new RuntimeException("Not implemented yet, because the pivot model doesn't provide messages");
	}

	@Override
	public OperationCallExpAS computeAstFor_ARelationalRelationalExpCs(
			OperationCallExpAS myAst, Heritage nodeHrtg,
			OclExpressionAS astRelationalExpCs, TokenAS astRelationalOperator,
			OclExpressionAS astAdditiveExpCs) throws AttrEvalException {
		
		return fillOperationCallExpAS(myAst, astRelationalExpCs, astRelationalOperator, astAdditiveExpCs);
	}

	@Override
	public Object computeAstFor_ASignalSpecExpCs(Object myAst,
			Heritage nodeHrtg, TokenAS astSimpleName,
			Object astMessageArgumentListCs) throws AttrEvalException {
		throw new RuntimeException("Not implemented yet, because the pivot model doesn't provide messages");
	}

	@Override
	public VariableAS computeAstFor_ASimpleInitializedParameterCs(
			VariableAS myAst, Heritage nodeHrtg, TokenAS astSimpleName)
			throws AttrEvalException {
		
		myAst.setName(astSimpleName);
		return myAst;
	}

	@Override
	public OperationCallExpAS computeAstFor_ASimpleLogicalSimpleLogicalExpCs(
			OperationCallExpAS myAst, Heritage nodeHrtg,
			OclExpressionAS astSimpleLogicalExpCs, TokenAS astSimpleLog,
			OclExpressionAS astRelationalExpCs) throws AttrEvalException {
		
		return fillOperationCallExpAS(myAst, astSimpleLogicalExpCs, astSimpleLog, astRelationalExpCs);
	}

	@Override
	public PropertyCallExpAS computeAstFor_ASimplePropertyCallExpCs(
			PropertyCallExpAS myAst, Heritage nodeHrtg,
			PathNameAS astPathNameCs, TokenAS astAtpre)
			throws AttrEvalException {
		
		if (astAtpre != null) myAst.setAtPre(astAtpre);
		myAst.setPathName(astPathNameCs);
		return myAst;
	}

	@Override
	public CollectionItemAS computeAstFor_ASingleExpCollectionLiteralPartCs(
			CollectionItemAS myAst, Heritage nodeHrtg,
			OclExpressionAS astOclExpressionCs) throws AttrEvalException {
		
		myAst.setItem(astOclExpressionCs);
		return myAst;
	}

	@Override
	public BooleanLiteralExpAS computeAstFor_ATrueBooleanLiteralExpCs(
			BooleanLiteralExpAS myAst, Heritage nodeHrtg, TokenAS astTrue)
			throws AttrEvalException {
		
		myAst.setValue(astTrue);
		return myAst;
	}

	@Override
	public TupleLiteralExpAS computeAstFor_ATupleTupleLiteralExpCs(
			TupleLiteralExpAS myAst, Heritage nodeHrtg,
			List astInitializedVariableListCs) throws AttrEvalException {
		
		int variableNumber = astInitializedVariableListCs.getNumChild();
		List tuplePartList = new List();
		for(int i = 0; i < variableNumber; i++) {
			VariableAS variable = (VariableAS)astInitializedVariableListCs.getChild(i);
			TupleLiteralPartAS tuplePart = new TupleLiteralPartAS();
			tuplePart.setExpression(variable.getExpression());
			tuplePart.setVariable(variable);
			tuplePartList.add(tuplePart);
			
		}
		myAst.setPartsList(tuplePartList);
		return myAst;
	}

	@Override
	public TupleTypeAS computeAstFor_ATupleTypeSpecifierCs(TupleTypeAS myAst,
			Heritage nodeHrtg, List astFormalParameterListCs)
			throws AttrEvalException {
		
		if (astFormalParameterListCs != null) {
			tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.List variableList = transformListVariableExp(astFormalParameterListCs);
			myAst.setVariableASList(variableList);
		}
		return myAst;
	}

	@Override
	public OperationCallExpAS computeAstFor_AUnaryUnaryExpCs(
			OperationCallExpAS myAst, Heritage nodeHrtg,
			TokenAS astUnaryOperator, OclExpressionAS astPostfixExpCs)
			throws AttrEvalException {
		
		return fillOperationCallExpAS(myAst, astPostfixExpCs, astUnaryOperator, null);
	}

	@Override
	public TokenAS createNodeFor_TAop(TAop node, Heritage nodeHrtg)
			throws AttrEvalException {
		
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TAtpre(TAtpre node, Heritage nodeHrtg)
			throws AttrEvalException {
		
		TokenAS token = fillTokenAS(node);
		//System.out.println("AtPre Token detected Line : " + token.getLine() + " Column: " + token.getColumn() + " Text: " + token.getValue());
		return token;
	}

	@Override
	public TokenAS createNodeFor_TBag(TBag node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TBody(TBody node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TCollection(TCollection node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TCollectionOperation(
			TCollectionOperation node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TFalse(TFalse node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TImplies(TImplies node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TIteratorOperation(TIteratorOperation node,
			Heritage nodeHrtg) throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TMop(TMop node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TNot(TNot node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TOclAsType(TOclAsType node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TOclIsKindOf(TOclIsKindOf node,
			Heritage nodeHrtg) throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TOclIsTypeOf(TOclIsTypeOf node,
			Heritage nodeHrtg) throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TOrderedset(TOrderedset node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TPost(TPost node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TPre(TPre node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TRelop(TRelop node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TSequence(TSequence node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TSet(TSet node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TSimpleLog(TSimpleLog node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TSimpleName(TSimpleName node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TTrue(TTrue node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public DefAttributeConstraintAS computeAstFor_ADefAttributeClassifierConstraintCs(
			DefAttributeConstraintAS myAst, Heritage nodeHrtg, TokenAS astName,
			TokenAS astAttributeName, TypeAS astTypeSpecifier,
			TokenAS astEquals, OclExpressionAS astOclExpressionCs)
			throws AttrEvalException {
		
		if (astName != null) myAst.setName(astName);
		myAst.setAttributeName(astAttributeName);
		myAst.setAttributeType(astTypeSpecifier);
		myAst.setExpression(astOclExpressionCs);
		return myAst;
	}

	@Override
	public DefOperationConstraintAS computeAstFor_ADefOperationClassifierConstraintCs(
			DefOperationConstraintAS myAst, Heritage nodeHrtg, TokenAS astName,
			TokenAS astOperationName,
			OperationSignatureAS astOperationSignatureCs, TokenAS astEquals,
			OclExpressionAS astOclExpressionCs) throws AttrEvalException {
		
		if (astName != null) myAst.setName(astName);
		myAst.setMethodName(astOperationName);
		myAst.setSignature(astOperationSignatureCs);
		myAst.setExpression(astOclExpressionCs);
		return myAst;
	}

	@Override
	public VariableAS computeAstFor_AInitializedVariableCs(Heritage nodeHrtg,
			VariableAS astInitializedParameterCs, TokenAS astEquals,
			OclExpressionAS astOclExpressionCs) throws AttrEvalException {
		
		astInitializedParameterCs.setExpression(astOclExpressionCs);
		return astInitializedParameterCs;
	}

	@Override
	public TokenAS createNodeFor_TEquals(TEquals node, Heritage nodeHrtg)
			throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public VariableAS computeAstFor_AFormalParameterParameterCs(
			VariableAS myAst, Heritage nodeHrtg,
			VariableExpAS astFormalParameterCs) throws AttrEvalException {
		return astFormalParameterCs.getVariable();
	}

	@Override
	public List computeAstFor_AParameterListCs(List myAst, Heritage nodeHrtg,
			VariableAS astParameterCs, java.util.List astListParameterEnum)
			throws AttrEvalException {
		
		myAst.add(astParameterCs);
		
		if (astListParameterEnum != null) {
			java.util.Iterator<VariableAS> it = astListParameterEnum.iterator();
			while(it.hasNext()) {
				myAst.add(it.next());
			}
		}
		
		return myAst;
	}

	@Override
	public VariableAS computeAstFor_ASimpleNameParameterCs(VariableAS myAst,
			Heritage nodeHrtg, TokenAS astSimpleName) throws AttrEvalException {
		myAst.setName(astSimpleName);
		return myAst;
	}

	@Override
	public CollectionLiteralExpAS computeAstFor_ACollectionLiteralExpCs(
			CollectionLiteralExpAS myAst, Heritage nodeHrtg,
			TokenAS astCollectionTypeIdentifierCs,
			List astCollectionLiteralPartsCs) throws AttrEvalException {
		
		if (astCollectionLiteralPartsCs != null) myAst.setCollectionLiteralPartASList(astCollectionLiteralPartsCs);
		myAst.setName(astCollectionTypeIdentifierCs);
		return myAst;
	}

	@Override
	public CollectionTypeAS computeAstFor_ACollectionTypeSpecifierCs(
			CollectionTypeAS myAst, Heritage nodeHrtg,
			TokenAS astCollectionTypeIdentifierCs, TypeAS astTypeSpecifier)
			throws AttrEvalException {
		
		myAst.setName(astCollectionTypeIdentifierCs);
		myAst.setType(astTypeSpecifier);
		return myAst;
	}

	@Override
	public OperationConstraintAS computeAstFor_AFullOperationConstraintCs(
			OperationConstraintAS myAst, Heritage nodeHrtg,
			TokenAS astOpConstraintStereotypeCs, TokenAS astSimpleName,
			OclExpressionAS astOclExpressionCs) throws AttrEvalException {
		
		//System.out.println("In the parsing process, AOperationConstraintCs, OperationStereoType: " + astOpConstraintStereotypeCs);
		myAst.setOperationStereotype(astOpConstraintStereotypeCs);
		if (astSimpleName != null) myAst.setName(astSimpleName);
		if (astOclExpressionCs != null) myAst.setExpression(astOclExpressionCs);
		return myAst;
	}

	@Override
	public IntegerLiteralExpAS computeAstFor_AIntegerNumericLiteralExpCs(
			IntegerLiteralExpAS myAst, Heritage nodeHrtg,
			TokenAS astIntegerLiteral) throws AttrEvalException {
		
		myAst.setValue(astIntegerLiteral);
		return myAst;
	}

	@Override
	public RealLiteralExpAS computeAstFor_ARealNumericLiteralExpCs(
			RealLiteralExpAS myAst, Heritage nodeHrtg, TokenAS astRealLiteral)
			throws AttrEvalException {
		
		myAst.setValue(astRealLiteral);
		return myAst;
	}

	@Override
	public StringLiteralExpAS computeAstFor_AStringLiteralExpCs(
			StringLiteralExpAS myAst, Heritage nodeHrtg,
			TokenAS astStringLiteral) throws AttrEvalException {
		
		myAst.setValue(astStringLiteral);
		return myAst;
	}

	@Override
	public VariableAS computeAstFor_AIterateVarCs(Heritage nodeHrtg,
			VariableExpAS astFormalParameterCs) throws AttrEvalException {
		
		return astFormalParameterCs.getVariable();
	}

	@Override
	public TokenAS createNodeFor_TIntegerLiteral(TIntegerLiteral node,
			Heritage nodeHrtg) throws AttrEvalException {
		
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TRealLiteral(TRealLiteral node,
			Heritage nodeHrtg) throws AttrEvalException {
		return fillTokenAS(node);
	}

	@Override
	public TokenAS createNodeFor_TStringLiteral(TStringLiteral node,
			Heritage nodeHrtg) throws AttrEvalException {
		return fillTokenAS(node);
	}
	
	/**
	 * This method is used to form the mathematical expression. The name is transform to an pathName and the both arguments are
	 * added to an argument list.
	 * @param exp the expression to be changed
	 * @param leftSide the left side of the mathematical expression
	 * @param name the name of the operation
	 * @param rightSide the right side of the mathematical expression
	 * @return the operation call expression filled out
	 */
	protected OperationCallExpAS fillOperationCallExpAS(OperationCallExpAS exp, OclExpressionAS leftSide, TokenAS name, OclExpressionAS rightSide) {
		PathNameAS operationName = new PathNameAS();
		operationName.addTokenAS(name);
		
		tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.List argumentList = new tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.List();
		if (leftSide != null) exp.setSource(leftSide);
		if (rightSide != null) argumentList.add(rightSide);
		
		exp.setArgumentList(argumentList);
		exp.setName(operationName);
		return exp;
	}
	
	/**
	 * The method transforms a list of VariableExpAS in a list of VariableAS
	 * @param list the with VariableExpAS
	 * @return a list with VariableAS
	 */
	protected List transformListVariableExp(List list) {
		tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.List variableList = new tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.List();
		for(int i = 0; i < list.getNumChild(); i++) {
			VariableExpAS variableExp = (VariableExpAS) list.getChild(i);
			variableList.add(variableExp.getVariable());
		}
		
		return variableList;
	}
	
	
	
	/**
	 * Fills out a token with the information of the node (line, column and text).
	 * @param node the node that has the information to be filled in
	 * @return token with the information of the node
	 */
	protected TokenAS fillTokenAS(Token node) {
		TokenAS token = new TokenAS();
		token.setColumn(node.getPos());
		token.setLine(node.getLine());
		token.setValue(node.getText());
		return token;
	}

	@Override
	public OclFileAS computeAstFor_AOclFile(OclFileAS myAst, Heritage nodeHrtg,
			java.util.List astListPackagedConstraintListCs)
			throws AttrEvalException {
		
		List packageList = new List();
		if (astListPackagedConstraintListCs != null) {
			java.util.Iterator<PackagedConstraintAS> it = astListPackagedConstraintListCs.iterator();
			while(it.hasNext()) {
				packageList.add(it.next());
			}
			
		}
		myAst.setPackagedConstraintASList(packageList);
		
		return myAst;
	}

	@Override
	public PackagedConstraintAS computeAstFor_APackagedConstraintListCs(
			PackagedConstraintAS myAst, Heritage nodeHrtg,
			PathNameAS astPathNameCs, java.util.List astListContextDeclarationCs)
			throws AttrEvalException {
		
		myAst.setName(astPathNameCs);
		List contextList = new List();
		if (astListContextDeclarationCs != null)
		{
			java.util.Iterator<ConstraintAS> it = astListContextDeclarationCs.iterator();
			while(it.hasNext()) {
				contextList.add(it.next());
			}
			myAst.setContextList(contextList);
		}
		
		return myAst;
	}

	@Override
	public ClassifierContextAS computeAstFor_AClassifierContextDeclarationCs(
			ClassifierContextAS myAst, Heritage nodeHrtg,
			PathNameAS astPathNameCs,
			java.util.List astListClassifierConstraintCs)
			throws AttrEvalException {
		
		myAst.setName(astPathNameCs);
								
		List constraintList = new List();
		java.util.Iterator<ClassifierConstraintAS> it = astListClassifierConstraintCs.iterator();
		while(it.hasNext()) {
			constraintList.add(it.next());
		}
		myAst.setClassifierConstraintASList(constraintList);
		return myAst;
	}

	@Override
	public OperationContextAS computeAstFor_AOperationContextDeclarationCs(
			OperationContextAS myAst, Heritage nodeHrtg,
			PathNameAS astPathNameCs,
			OperationSignatureAS astOperationSignatureCs,
			java.util.List astListOperationConstraintCs)
			throws AttrEvalException {
		
		java.util.Iterator<OperationConstraintAS> iterator = astListOperationConstraintCs.iterator();
		boolean hasValidConstraint = false;
		while (iterator.hasNext()) {
			if (iterator.next() != null) {
				hasValidConstraint = true;
				break;
			}
		}
		
		if (!hasValidConstraint) {
			TokenAS token = astPathNameCs.getTokenAS(0);
			
			throw new AttrEvalException("The operation context has no relevant constraint. Line: " + token.getLine()
					+ " Column: " + token.getColumn() + " Text: " + token.getValue());
		}
			
		
		
		myAst.setName(astPathNameCs);
		List constraintList = new List();
		java.util.Iterator<OperationConstraintAS> it = astListOperationConstraintCs.iterator();
		while(it.hasNext()) {
			OperationConstraintAS constraint = it.next();
			if (constraint != null) constraintList.add(constraint);
		}
		
		myAst.setOperationSignatureAS(astOperationSignatureCs);
		myAst.setOperationConstraintASList(constraintList);
		return myAst;
	}

	@Override
	public AttrOrAssocContextAS computeAstFor_AAttrOrAssocContextDeclarationCs(
			AttrOrAssocContextAS myAst, Heritage nodeHrtg,
			PathNameAS astPathNameCs, TypeAS astTypeSpecifier,
			java.util.List astListInitOrDerValueCs) throws AttrEvalException {
		
		myAst.setName(astPathNameCs);
		if (astTypeSpecifier != null) myAst.setType(astTypeSpecifier);
			
		List constraintList = new List();
		java.util.Iterator<OperationConstraintAS> it = astListInitOrDerValueCs.iterator();
		while(it.hasNext()) {
			constraintList.add(it.next());
		}
		
		myAst.setAttrOrAssocConstraintASList(constraintList);
		return myAst;
	}

	/**
	 * This is the case where a null method constraint is used for readability
	 * without content (especially without an ocl expression). So this empty
	 * constraint must not appear in the abstract model.
	 */
	@Override
	public OperationConstraintAS computeAstFor_AEmptyOperationConstraintCs(
			OperationConstraintAS myAst, Heritage nodeHrtg,
			TokenAS astOpConstraintStereotypeCs) throws AttrEvalException {
		
		return null;
	}


}
