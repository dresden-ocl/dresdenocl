/* This file was generated by SableCC (http://www.sable.mcgill.ca/sablecc/). */

package tudresden.ocl.parser.analysis;

import tudresden.ocl.parser.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object in);
    Object getOut(Node node);
    void setOut(Node node, Object out);

    void caseStart(Start node);
    void caseAConstraint(AConstraint node);
    void caseAConstraintBody(AConstraintBody node);
    void caseAContextDeclaration(AContextDeclaration node);
    void caseAClassifierContextBody(AClassifierContextBody node);
    void caseAOperationContextBody(AOperationContextBody node);
    void caseAClassifierContext(AClassifierContext node);
    void caseAClassifierHead(AClassifierHead node);
    void caseAOperationContext(AOperationContext node);
    void caseAReturnTypeDeclaration(AReturnTypeDeclaration node);
    void caseAFormalParameterList(AFormalParameterList node);
    void caseAFormalParameterListTail(AFormalParameterListTail node);
    void caseAFormalParameter(AFormalParameter node);
    void caseAInvStereotype(AInvStereotype node);
    void caseAPreStereotype(APreStereotype node);
    void caseAPostStereotype(APostStereotype node);
    void caseAExpression(AExpression node);
    void caseAIfExpression(AIfExpression node);
    void caseALogicalExpression(ALogicalExpression node);
    void caseALogicalExpressionTail(ALogicalExpressionTail node);
    void caseARelationalExpression(ARelationalExpression node);
    void caseARelationalExpressionTail(ARelationalExpressionTail node);
    void caseAAdditiveExpression(AAdditiveExpression node);
    void caseAAdditiveExpressionTail(AAdditiveExpressionTail node);
    void caseAMultiplicativeExpression(AMultiplicativeExpression node);
    void caseAMultiplicativeExpressionTail(AMultiplicativeExpressionTail node);
    void caseAUnaryUnaryExpression(AUnaryUnaryExpression node);
    void caseAPostfixUnaryExpression(APostfixUnaryExpression node);
    void caseAPostfixExpression(APostfixExpression node);
    void caseAPostfixExpressionTail(APostfixExpressionTail node);
    void caseADotPostfixExpressionTailBegin(ADotPostfixExpressionTailBegin node);
    void caseAArrowPostfixExpressionTailBegin(AArrowPostfixExpressionTailBegin node);
    void caseALitColPrimaryExpression(ALitColPrimaryExpression node);
    void caseALiteralPrimaryExpression(ALiteralPrimaryExpression node);
    void caseAFeaturePrimaryExpression(AFeaturePrimaryExpression node);
    void caseAParenthesesPrimaryExpression(AParenthesesPrimaryExpression node);
    void caseAIfPrimaryExpression(AIfPrimaryExpression node);
    void caseAEmptyFeatureCallParameters(AEmptyFeatureCallParameters node);
    void caseAConcreteFeatureCallParameters(AConcreteFeatureCallParameters node);
    void caseAFeatureCallParameters(AFeatureCallParameters node);
    void caseACommaFcpHelper(ACommaFcpHelper node);
    void caseAColonFcpHelper(AColonFcpHelper node);
    void caseAIterateFcpHelper(AIterateFcpHelper node);
    void caseABarFcpHelper(ABarFcpHelper node);
    void caseALetExpression(ALetExpression node);
    void caseALetExpressionTypeDeclaration(ALetExpressionTypeDeclaration node);
    void caseAStringLiteral(AStringLiteral node);
    void caseARealLiteral(ARealLiteral node);
    void caseAIntegerLiteral(AIntegerLiteral node);
    void caseABooleanLiteral(ABooleanLiteral node);
    void caseAEnumLiteral(AEnumLiteral node);
    void caseAEnumerationType(AEnumerationType node);
    void caseAEnumerationTypeTail(AEnumerationTypeTail node);
    void caseAPathSimpleTypeSpecifier(APathSimpleTypeSpecifier node);
    void caseAEnumSimpleTypeSpecifier(AEnumSimpleTypeSpecifier node);
    void caseALiteralCollection(ALiteralCollection node);
    void caseAExpressionListOrRange(AExpressionListOrRange node);
    void caseAListExpressionListOrRangeTail(AListExpressionListOrRangeTail node);
    void caseARangeExpressionListOrRangeTail(ARangeExpressionListOrRangeTail node);
    void caseAExpressionListTail(AExpressionListTail node);
    void caseAFeatureCall(AFeatureCall node);
    void caseAQualifiers(AQualifiers node);
    void caseAStandardDeclarator(AStandardDeclarator node);
    void caseAIterateDeclarator(AIterateDeclarator node);
    void caseADeclaratorTail(ADeclaratorTail node);
    void caseADeclaratorTypeDeclaration(ADeclaratorTypeDeclaration node);
    void caseAPathTypeName(APathTypeName node);
    void caseAPathTypeNameTail(APathTypeNameTail node);
    void caseANonCollectionTypeName(ANonCollectionTypeName node);
    void caseACollectionTypeName(ACollectionTypeName node);
    void caseASetCollectionType(ASetCollectionType node);
    void caseABagCollectionType(ABagCollectionType node);
    void caseASequenceCollectionType(ASequenceCollectionType node);
    void caseACollectionCollectionType(ACollectionCollectionType node);
    void caseAPathName(APathName node);
    void caseATypeNamePathNameBegin(ATypeNamePathNameBegin node);
    void caseANamePathNameBegin(ANamePathNameBegin node);
    void caseAPathNameTail(APathNameTail node);
    void caseATypeNamePathNameEnd(ATypeNamePathNameEnd node);
    void caseANamePathNameEnd(ANamePathNameEnd node);
    void caseATimeExpression(ATimeExpression node);
    void caseAActualParameterList(AActualParameterList node);
    void caseAActualParameterListTail(AActualParameterListTail node);
    void caseAAndLogicalOperator(AAndLogicalOperator node);
    void caseAOrLogicalOperator(AOrLogicalOperator node);
    void caseAXorLogicalOperator(AXorLogicalOperator node);
    void caseAImpliesLogicalOperator(AImpliesLogicalOperator node);
    void caseASetCollectionKind(ASetCollectionKind node);
    void caseABagCollectionKind(ABagCollectionKind node);
    void caseASequenceCollectionKind(ASequenceCollectionKind node);
    void caseACollectionCollectionKind(ACollectionCollectionKind node);
    void caseAEqualRelationalOperator(AEqualRelationalOperator node);
    void caseANEqualRelationalOperator(ANEqualRelationalOperator node);
    void caseAGtRelationalOperator(AGtRelationalOperator node);
    void caseALtRelationalOperator(ALtRelationalOperator node);
    void caseAGteqRelationalOperator(AGteqRelationalOperator node);
    void caseALteqRelationalOperator(ALteqRelationalOperator node);
    void caseAPlusAddOperator(APlusAddOperator node);
    void caseAMinusAddOperator(AMinusAddOperator node);
    void caseAMultMultiplyOperator(AMultMultiplyOperator node);
    void caseADivMultiplyOperator(ADivMultiplyOperator node);
    void caseAMinusUnaryOperator(AMinusUnaryOperator node);
    void caseANotUnaryOperator(ANotUnaryOperator node);

    void caseTComment(TComment node);
    void caseTDot(TDot node);
    void caseTArrow(TArrow node);
    void caseTNot(TNot node);
    void caseTMult(TMult node);
    void caseTDiv(TDiv node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTContext(TContext node);
    void caseTEnum(TEnum node);
    void caseTTPre(TTPre node);
    void caseTTPost(TTPost node);
    void caseTTInv(TTInv node);
    void caseTEqual(TEqual node);
    void caseTNEqual(TNEqual node);
    void caseTLt(TLt node);
    void caseTGt(TGt node);
    void caseTLteq(TLteq node);
    void caseTGteq(TGteq node);
    void caseTAnd(TAnd node);
    void caseTOr(TOr node);
    void caseTXor(TXor node);
    void caseTImplies(TImplies node);
    void caseTLPar(TLPar node);
    void caseTRPar(TRPar node);
    void caseTLBracket(TLBracket node);
    void caseTRBracket(TRBracket node);
    void caseTLBrace(TLBrace node);
    void caseTRBrace(TRBrace node);
    void caseTSemicolon(TSemicolon node);
    void caseTDcolon(TDcolon node);
    void caseTColon(TColon node);
    void caseTComma(TComma node);
    void caseTChannel(TChannel node);
    void caseTAt(TAt node);
    void caseTBar(TBar node);
    void caseTDdot(TDdot node);
    void caseTApostroph(TApostroph node);
    void caseTTLet(TTLet node);
    void caseTTIn(TTIn node);
    void caseTTIf(TTIf node);
    void caseTTThen(TTThen node);
    void caseTTElse(TTElse node);
    void caseTEndif(TEndif node);
    void caseTTSet(TTSet node);
    void caseTTBag(TTBag node);
    void caseTTSequence(TTSequence node);
    void caseTTCollection(TTCollection node);
    void caseTBool(TBool node);
    void caseTSimpleTypeName(TSimpleTypeName node);
    void caseTName(TName node);
    void caseTNewLine(TNewLine node);
    void caseTInt(TInt node);
    void caseTReal(TReal node);
    void caseTBlank(TBlank node);
    void caseTTab(TTab node);
    void caseTStringLit(TStringLit node);
    void caseEOF(EOF node);
}

