package tudresden.ocl.check.bootstrap;

import tudresden.ocl.lib.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.parser.analysis.*;

public class GeneratedTests extends DepthFirstAdapter {

  public boolean reportProgress=false;

  public void inAPostfixExpressionTail(APostfixExpressionTail node) {
    reportProgress("starting tudOclCheckMethod2");
    tudOclCheckMethod2(node);
    reportProgress("starting tudOclCheckMethod8");
    tudOclCheckMethod8(node);
  }

  public void inAOperationContext(AOperationContext node) {
    reportProgress("starting tudOclCheckMethod5");
    tudOclCheckMethod5(node);
  }

  public void inAConstraint(AConstraint node) {
    reportProgress("starting tudOclCheckMethod0");
    tudOclCheckMethod0(node);
    reportProgress("starting tudOclCheckMethod1");
    tudOclCheckMethod1(node);
  }

  public void inAStandardDeclarator(AStandardDeclarator node) {
    reportProgress("starting tudOclCheckMethod3");
    tudOclCheckMethod3(node);
    reportProgress("starting tudOclCheckMethod6");
    tudOclCheckMethod6(node);
  }

  public void inAConstraintBody(AConstraintBody node) {
    reportProgress("starting tudOclCheckMethod9");
    tudOclCheckMethod9(node);
  }

  public void inAFeatureCallParameters(AFeatureCallParameters node) {
    reportProgress("starting tudOclCheckMethod4");
    tudOclCheckMethod4(node);
  }

  public void inAIterateDeclarator(AIterateDeclarator node) {
    reportProgress("starting tudOclCheckMethod7");
    tudOclCheckMethod7(node);
  }

  protected void reportProgress(String msg) {
    if (reportProgress) System.out.println(msg);
  }

protected void tudOclCheckMethod0(final AConstraint node) {
  /* context AConstraint inv tudOclInv0 : self . contextDeclaration . oclAsType ( AContextDeclaration ) . contextBody . oclIsTypeOf ( AOperationContextBody ) = self . constraintBody -> collect ( c : AConstraintBody | c . stereotype ) -> forAll ( s : PStereotype | s . oclIsTypeOf ( APreStereotype ) or s . oclIsTypeOf ( APostStereotype ) ) inv tudOclInv1 : self . contextDeclaration . oclAsType ( AContextDeclaration ) . contextBody . oclIsTypeOf ( AClassifierContextBody ) = self . constraintBody -> collect ( tudOclIter0 : AConstraintBody | tudOclIter0 . stereotype ) -> forAll ( tudOclIter1 : PStereotype | tudOclIter1 . oclIsTypeOf ( AInvStereotype ) )   */
final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(node) );
final OclAnyImpl tudOclNode1=Ocl.toOclAnyImpl(tudOclNode0.getFeature("contextDeclaration"));
final OclType tudOclNode2=OclType.getOclTypeFor(node, "AContextDeclaration");
final OclAnyImpl tudOclNode3=Ocl.toOclAnyImpl(tudOclNode1.oclAsType(tudOclNode2));
final OclAnyImpl tudOclNode4=Ocl.toOclAnyImpl(tudOclNode3.getFeature("contextBody"));
final OclType tudOclNode5=OclType.getOclTypeFor(node, "AOperationContextBody");
final OclBoolean tudOclNode6=tudOclNode4.oclIsTypeOf(tudOclNode5);
final OclSequence tudOclNode7=Ocl.toOclSequence(tudOclNode0.getFeature("constraintBody"));
final OclIterator tudOclIter2=tudOclNode7.getIterator();
final OclRootEvaluatable tudOclEval0=new OclRootEvaluatable() {
  public OclRoot evaluate() {
    final OclAnyImpl tudOclNode8=Ocl.toOclAnyImpl(Ocl.toOclAnyImpl(tudOclIter2.getValue()).getFeature("stereotype"));
    return tudOclNode8;
  }
};
final OclSequence tudOclNode9=Ocl.toOclSequence(tudOclNode7.collect(tudOclIter2, tudOclEval0));
final OclIterator tudOclIter3=tudOclNode9.getIterator();
final OclBooleanEvaluatable tudOclEval1=new OclBooleanEvaluatable() {
  public OclBoolean evaluate() {
    final OclType tudOclNode10=OclType.getOclTypeFor(node, "APreStereotype");
    final OclBoolean tudOclNode11=Ocl.toOclAnyImpl(tudOclIter3.getValue()).oclIsTypeOf(tudOclNode10);
    final OclType tudOclNode12=OclType.getOclTypeFor(node, "APostStereotype");
    final OclBoolean tudOclNode13=Ocl.toOclAnyImpl(tudOclIter3.getValue()).oclIsTypeOf(tudOclNode12);
    final OclBoolean tudOclNode14=tudOclNode11.or(tudOclNode13);
    return tudOclNode14;
  }
};
final OclBoolean tudOclNode15=tudOclNode9.forAll(tudOclIter3, tudOclEval1);
final OclBoolean tudOclNode16=tudOclNode6.isEqualTo(tudOclNode15);
  if ( !tudOclNode16.isTrue() ) {
    throw new GeneratedTestFailedException(
      "generated test failed:\ncontext AConstraint inv tudOclInv0 : self . contextDeclaration . oclAsType ( AContextDeclaration ) . contextBody . oclIsTypeOf ( AOperationContextBody ) = self . constraintBody -> collect ( c : AConstraintBody | c . stereotype ) -> forAll ( s : PStereotype | s . oclIsTypeOf ( APreStereotype ) or s . oclIsTypeOf ( APostStereotype ) ) inv tudOclInv1 : self . contextDeclaration . oclAsType ( AContextDeclaration ) . contextBody . oclIsTypeOf ( AClassifierContextBody ) = self . constraintBody -> collect ( tudOclIter0 : AConstraintBody | tudOclIter0 . stereotype ) -> forAll ( tudOclIter1 : PStereotype | tudOclIter1 . oclIsTypeOf ( AInvStereotype ) )  \n"+
      "violating node:\n"+node
    );
  }
}

protected void tudOclCheckMethod1(final AConstraint node) {
  /* context AConstraint inv tudOclInv0 : self . contextDeclaration . oclAsType ( AContextDeclaration ) . contextBody . oclIsTypeOf ( AOperationContextBody ) = self . constraintBody -> collect ( c : AConstraintBody | c . stereotype ) -> forAll ( s : PStereotype | s . oclIsTypeOf ( APreStereotype ) or s . oclIsTypeOf ( APostStereotype ) ) inv tudOclInv1 : self . contextDeclaration . oclAsType ( AContextDeclaration ) . contextBody . oclIsTypeOf ( AClassifierContextBody ) = self . constraintBody -> collect ( tudOclIter0 : AConstraintBody | tudOclIter0 . stereotype ) -> forAll ( tudOclIter1 : PStereotype | tudOclIter1 . oclIsTypeOf ( AInvStereotype ) )   */
final OclAnyImpl tudOclNode17=Ocl.toOclAnyImpl( Ocl.getFor(node) );
final OclAnyImpl tudOclNode18=Ocl.toOclAnyImpl(tudOclNode17.getFeature("contextDeclaration"));
final OclType tudOclNode19=OclType.getOclTypeFor(node, "AContextDeclaration");
final OclAnyImpl tudOclNode20=Ocl.toOclAnyImpl(tudOclNode18.oclAsType(tudOclNode19));
final OclAnyImpl tudOclNode21=Ocl.toOclAnyImpl(tudOclNode20.getFeature("contextBody"));
final OclType tudOclNode22=OclType.getOclTypeFor(node, "AClassifierContextBody");
final OclBoolean tudOclNode23=tudOclNode21.oclIsTypeOf(tudOclNode22);
final OclSequence tudOclNode24=Ocl.toOclSequence(tudOclNode17.getFeature("constraintBody"));
final OclIterator tudOclIter0=tudOclNode24.getIterator();
final OclRootEvaluatable tudOclEval2=new OclRootEvaluatable() {
  public OclRoot evaluate() {
    final OclAnyImpl tudOclNode25=Ocl.toOclAnyImpl(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature("stereotype"));
    return tudOclNode25;
  }
};
final OclSequence tudOclNode26=Ocl.toOclSequence(tudOclNode24.collect(tudOclIter0, tudOclEval2));
final OclIterator tudOclIter1=tudOclNode26.getIterator();
final OclBooleanEvaluatable tudOclEval3=new OclBooleanEvaluatable() {
  public OclBoolean evaluate() {
    final OclType tudOclNode27=OclType.getOclTypeFor(node, "AInvStereotype");
    final OclBoolean tudOclNode28=Ocl.toOclAnyImpl(tudOclIter1.getValue()).oclIsTypeOf(tudOclNode27);
    return tudOclNode28;
  }
};
final OclBoolean tudOclNode29=tudOclNode26.forAll(tudOclIter1, tudOclEval3);
final OclBoolean tudOclNode30=tudOclNode23.isEqualTo(tudOclNode29);
  if ( !tudOclNode30.isTrue() ) {
    throw new GeneratedTestFailedException(
      "generated test failed:\ncontext AConstraint inv tudOclInv0 : self . contextDeclaration . oclAsType ( AContextDeclaration ) . contextBody . oclIsTypeOf ( AOperationContextBody ) = self . constraintBody -> collect ( c : AConstraintBody | c . stereotype ) -> forAll ( s : PStereotype | s . oclIsTypeOf ( APreStereotype ) or s . oclIsTypeOf ( APostStereotype ) ) inv tudOclInv1 : self . contextDeclaration . oclAsType ( AContextDeclaration ) . contextBody . oclIsTypeOf ( AClassifierContextBody ) = self . constraintBody -> collect ( tudOclIter0 : AConstraintBody | tudOclIter0 . stereotype ) -> forAll ( tudOclIter1 : PStereotype | tudOclIter1 . oclIsTypeOf ( AInvStereotype ) )  \n"+
      "violating node:\n"+node
    );
  }
}

protected void tudOclCheckMethod2(final APostfixExpressionTail node) {
  /* context APostfixExpressionTail inv tudOclInv0 : let iteratingMethodNames : Set ( String ) = Set { 'iterate' , 'forAll' , 'isUnique' , 'exists' , 'collect' , 'select' , 'reject' , 'sortedBy' } in if ( self . postfixExpressionTailBegin . oclIsTypeOf ( AArrowPostfixExpressionTailBegin ) and iteratingMethodNames -> includes ( self . featureCall . oclAsType ( AFeatureCall ) . pathName . toString ( ) ) ) then ( self . featureCall . oclAsType ( AFeatureCall ) . featureCallParameters . oclAsType ( AFeatureCallParameters ) . declarator -> size > 0 implies self . featureCall . oclAsType ( AFeatureCall ) . featureCallParameters . oclAsType ( AFeatureCallParameters ) . declarator . oclIsTypeOf ( if ( self . featureCall . oclAsType ( AFeatureCall ) . pathName . toString ( ) = 'iterate' ) then AIterateDeclarator else AStandardDeclarator endif ) ) else ( self . featureCall . oclAsType ( AFeatureCall ) . featureCallParameters -> size > 0 implies self . featureCall . oclAsType ( AFeatureCall ) . featureCallParameters . oclAsType ( AFeatureCallParameters ) . declarator -> size = 0 ) endif   */
final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(node) );
final OclSet tudOclNode1=OclSet.getEmptyOclSet();
final OclString tudOclNode2=new OclString("iterate");
final OclString tudOclNode3=new OclString("forAll");
final OclString tudOclNode4=new OclString("isUnique");
final OclString tudOclNode5=new OclString("exists");
final OclString tudOclNode6=new OclString("collect");
final OclString tudOclNode7=new OclString("select");
final OclString tudOclNode8=new OclString("reject");
final OclString tudOclNode9=new OclString("sortedBy");
tudOclNode1.setToInclude(tudOclNode2);
tudOclNode1.setToInclude(tudOclNode3);
tudOclNode1.setToInclude(tudOclNode4);
tudOclNode1.setToInclude(tudOclNode5);
tudOclNode1.setToInclude(tudOclNode6);
tudOclNode1.setToInclude(tudOclNode7);
tudOclNode1.setToInclude(tudOclNode8);
tudOclNode1.setToInclude(tudOclNode9);
final OclAnyImpl tudOclNode10=Ocl.toOclAnyImpl(tudOclNode0.getFeature("postfixExpressionTailBegin"));
final OclType tudOclNode11=OclType.getOclTypeFor(node, "AArrowPostfixExpressionTailBegin");
final OclBoolean tudOclNode12=tudOclNode10.oclIsTypeOf(tudOclNode11);
final OclAnyImpl tudOclNode13=Ocl.toOclAnyImpl(tudOclNode0.getFeature("featureCall"));
final OclType tudOclNode14=OclType.getOclTypeFor(node, "AFeatureCall");
final OclAnyImpl tudOclNode15=Ocl.toOclAnyImpl(tudOclNode13.oclAsType(tudOclNode14));
final OclAnyImpl tudOclNode16=Ocl.toOclAnyImpl(tudOclNode15.getFeature("pathName"));
final OclString tudOclNode17=Ocl.toOclString(tudOclNode16.getFeature("toString", null));
final OclBoolean tudOclNode18=tudOclNode1.includes(tudOclNode17);
final OclBoolean tudOclNode19=tudOclNode12.and(tudOclNode18);
final OclAnyImpl tudOclNode20=Ocl.toOclAnyImpl(tudOclNode0.getFeature("featureCall"));
final OclType tudOclNode21=OclType.getOclTypeFor(node, "AFeatureCall");
final OclAnyImpl tudOclNode22=Ocl.toOclAnyImpl(tudOclNode20.oclAsType(tudOclNode21));
final OclAnyImpl tudOclNode23=Ocl.toOclAnyImpl(tudOclNode22.getFeature("featureCallParameters"));
final OclType tudOclNode24=OclType.getOclTypeFor(node, "AFeatureCallParameters");
final OclAnyImpl tudOclNode25=Ocl.toOclAnyImpl(tudOclNode23.oclAsType(tudOclNode24));
final OclAnyImpl tudOclNode26=Ocl.toOclAnyImpl(tudOclNode25.getFeature("declarator"));
final OclSet tudOclSet0=OclSet.getEmptyOclSet();
tudOclSet0.setToInclude(tudOclNode26);
final OclInteger tudOclNode27=tudOclSet0.size();
final OclInteger tudOclNode28=new OclInteger(0);
final OclBoolean tudOclNode29=tudOclNode27.isGreaterThan(tudOclNode28);
final OclAnyImpl tudOclNode30=Ocl.toOclAnyImpl(tudOclNode0.getFeature("featureCall"));
final OclType tudOclNode31=OclType.getOclTypeFor(node, "AFeatureCall");
final OclAnyImpl tudOclNode32=Ocl.toOclAnyImpl(tudOclNode30.oclAsType(tudOclNode31));
final OclAnyImpl tudOclNode33=Ocl.toOclAnyImpl(tudOclNode32.getFeature("featureCallParameters"));
final OclType tudOclNode34=OclType.getOclTypeFor(node, "AFeatureCallParameters");
final OclAnyImpl tudOclNode35=Ocl.toOclAnyImpl(tudOclNode33.oclAsType(tudOclNode34));
final OclAnyImpl tudOclNode36=Ocl.toOclAnyImpl(tudOclNode35.getFeature("declarator"));
final OclAnyImpl tudOclNode37=Ocl.toOclAnyImpl(tudOclNode0.getFeature("featureCall"));
final OclType tudOclNode38=OclType.getOclTypeFor(node, "AFeatureCall");
final OclAnyImpl tudOclNode39=Ocl.toOclAnyImpl(tudOclNode37.oclAsType(tudOclNode38));
final OclAnyImpl tudOclNode40=Ocl.toOclAnyImpl(tudOclNode39.getFeature("pathName"));
final OclString tudOclNode41=Ocl.toOclString(tudOclNode40.getFeature("toString", null));
final OclString tudOclNode42=new OclString("iterate");
final OclBoolean tudOclNode43=tudOclNode41.isEqualTo(tudOclNode42);
final OclType tudOclNode44=OclType.getOclTypeFor(node, "AIterateDeclarator");
final OclType tudOclNode45=OclType.getOclTypeFor(node, "AStandardDeclarator");
final OclType tudOclNode46=(tudOclNode43.isTrue()) ? (tudOclNode44) : (tudOclNode45);
final OclBoolean tudOclNode47=tudOclNode36.oclIsTypeOf(tudOclNode46);
final OclBoolean tudOclNode48=tudOclNode29.implies(tudOclNode47);
final OclAnyImpl tudOclNode49=Ocl.toOclAnyImpl(tudOclNode0.getFeature("featureCall"));
final OclType tudOclNode50=OclType.getOclTypeFor(node, "AFeatureCall");
final OclAnyImpl tudOclNode51=Ocl.toOclAnyImpl(tudOclNode49.oclAsType(tudOclNode50));
final OclAnyImpl tudOclNode52=Ocl.toOclAnyImpl(tudOclNode51.getFeature("featureCallParameters"));
final OclSet tudOclSet1=OclSet.getEmptyOclSet();
tudOclSet1.setToInclude(tudOclNode52);
final OclInteger tudOclNode53=tudOclSet1.size();
final OclInteger tudOclNode54=new OclInteger(0);
final OclBoolean tudOclNode55=tudOclNode53.isGreaterThan(tudOclNode54);
final OclAnyImpl tudOclNode56=Ocl.toOclAnyImpl(tudOclNode0.getFeature("featureCall"));
final OclType tudOclNode57=OclType.getOclTypeFor(node, "AFeatureCall");
final OclAnyImpl tudOclNode58=Ocl.toOclAnyImpl(tudOclNode56.oclAsType(tudOclNode57));
final OclAnyImpl tudOclNode59=Ocl.toOclAnyImpl(tudOclNode58.getFeature("featureCallParameters"));
final OclType tudOclNode60=OclType.getOclTypeFor(node, "AFeatureCallParameters");
final OclAnyImpl tudOclNode61=Ocl.toOclAnyImpl(tudOclNode59.oclAsType(tudOclNode60));
final OclAnyImpl tudOclNode62=Ocl.toOclAnyImpl(tudOclNode61.getFeature("declarator"));
final OclSet tudOclSet2=OclSet.getEmptyOclSet();
tudOclSet2.setToInclude(tudOclNode62);
final OclInteger tudOclNode63=tudOclSet2.size();
final OclInteger tudOclNode64=new OclInteger(0);
final OclBoolean tudOclNode65=tudOclNode63.isEqualTo(tudOclNode64);
final OclBoolean tudOclNode66=tudOclNode55.implies(tudOclNode65);
final OclBoolean tudOclNode67=(tudOclNode19.isTrue()) ? (tudOclNode48) : (tudOclNode66);
  if ( !tudOclNode67.isTrue() ) {
    throw new GeneratedTestFailedException(
      "generated test failed:\ncontext APostfixExpressionTail inv tudOclInv0 : let iteratingMethodNames : Set ( String ) = Set { 'iterate' , 'forAll' , 'isUnique' , 'exists' , 'collect' , 'select' , 'reject' , 'sortedBy' } in if ( self . postfixExpressionTailBegin . oclIsTypeOf ( AArrowPostfixExpressionTailBegin ) and iteratingMethodNames -> includes ( self . featureCall . oclAsType ( AFeatureCall ) . pathName . toString ( ) ) ) then ( self . featureCall . oclAsType ( AFeatureCall ) . featureCallParameters . oclAsType ( AFeatureCallParameters ) . declarator -> size > 0 implies self . featureCall . oclAsType ( AFeatureCall ) . featureCallParameters . oclAsType ( AFeatureCallParameters ) . declarator . oclIsTypeOf ( if ( self . featureCall . oclAsType ( AFeatureCall ) . pathName . toString ( ) = 'iterate' ) then AIterateDeclarator else AStandardDeclarator endif ) ) else ( self . featureCall . oclAsType ( AFeatureCall ) . featureCallParameters -> size > 0 implies self . featureCall . oclAsType ( AFeatureCall ) . featureCallParameters . oclAsType ( AFeatureCallParameters ) . declarator -> size = 0 ) endif  \n"+
      "violating node:\n"+node
    );
  }
}

protected void tudOclCheckMethod3(final AStandardDeclarator node) {
  /* context AStandardDeclarator inv tudOclInv0 : let parentPathName : String = if self . parent . parent . oclIsTypeOf ( AFeatureCall ) then self . parent . parent . oclAsType ( AFeatureCall ) . pathName . toString ( ) else self . parent . parent . oclAsType ( AFeaturePrimaryExpression ) . pathName . toString ( ) endif in ( not self . declaratorTail -> isEmpty ) implies 'forAll' = parentPathName   */
final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(node) );
final OclAnyImpl tudOclNode1=Ocl.toOclAnyImpl(tudOclNode0.getFeature("parent"));
final OclAnyImpl tudOclNode2=Ocl.toOclAnyImpl(tudOclNode1.getFeature("parent"));
final OclType tudOclNode3=OclType.getOclTypeFor(node, "AFeatureCall");
final OclBoolean tudOclNode4=tudOclNode2.oclIsTypeOf(tudOclNode3);
final OclAnyImpl tudOclNode5=Ocl.toOclAnyImpl(tudOclNode0.getFeature("parent"));
final OclAnyImpl tudOclNode6=Ocl.toOclAnyImpl(tudOclNode5.getFeature("parent"));
final OclType tudOclNode7=OclType.getOclTypeFor(node, "AFeatureCall");
final OclAnyImpl tudOclNode8=Ocl.toOclAnyImpl(tudOclNode6.oclAsType(tudOclNode7));
final OclAnyImpl tudOclNode9=Ocl.toOclAnyImpl(tudOclNode8.getFeature("pathName"));
final OclString tudOclNode10=Ocl.toOclString(tudOclNode9.getFeature("toString", null));
final OclAnyImpl tudOclNode11=Ocl.toOclAnyImpl(tudOclNode0.getFeature("parent"));
final OclAnyImpl tudOclNode12=Ocl.toOclAnyImpl(tudOclNode11.getFeature("parent"));
final OclType tudOclNode13=OclType.getOclTypeFor(node, "AFeaturePrimaryExpression");
final OclAnyImpl tudOclNode14=Ocl.toOclAnyImpl(tudOclNode12.oclAsType(tudOclNode13));
final OclAnyImpl tudOclNode15=Ocl.toOclAnyImpl(tudOclNode14.getFeature("pathName"));
final OclString tudOclNode16=Ocl.toOclString(tudOclNode15.getFeature("toString", null));
final OclString tudOclNode17=(tudOclNode4.isTrue()) ? (tudOclNode10) : (tudOclNode16);
final OclSequence tudOclNode18=Ocl.toOclSequence(tudOclNode0.getFeature("declaratorTail"));
final OclBoolean tudOclNode19=tudOclNode18.isEmpty();
final OclBoolean tudOclNode20=tudOclNode19.not();
final OclString tudOclNode21=new OclString("forAll");
final OclBoolean tudOclNode22=tudOclNode21.isEqualTo(tudOclNode17);
final OclBoolean tudOclNode23=tudOclNode20.implies(tudOclNode22);
  if ( !tudOclNode23.isTrue() ) {
    throw new GeneratedTestFailedException(
      "generated test failed:\ncontext AStandardDeclarator inv tudOclInv0 : let parentPathName : String = if self . parent . parent . oclIsTypeOf ( AFeatureCall ) then self . parent . parent . oclAsType ( AFeatureCall ) . pathName . toString ( ) else self . parent . parent . oclAsType ( AFeaturePrimaryExpression ) . pathName . toString ( ) endif in ( not self . declaratorTail -> isEmpty ) implies 'forAll' = parentPathName  \n"+
      "violating node:\n"+node
    );
  }
}

protected void tudOclCheckMethod4(final AFeatureCallParameters node) {
  /* context AFeatureCallParameters inv tudOclInv0 : ( self . declarator -> size > 0 and self . declarator . oclIsTypeOf ( AStandardDeclarator ) and self . declarator . oclAsType ( AStandardDeclarator ) . declaratorTail -> size > 0 ) implies self . actualParameterList . subnodes -> select ( n : Node | n . oclIsTypeOf ( AFeaturePrimaryExpression ) ) -> forAll ( tudOclIter0 : Node | ( tudOclIter0 . oclAsType ( AFeaturePrimaryExpression ) . pathName . oclAsType ( APathName ) . pathNameBegin . oclIsTypeOf ( ANamePathNameBegin ) and tudOclIter0 . oclAsType ( AFeaturePrimaryExpression ) . featureCallParameters -> isEmpty ) implies tudOclIter0 . boundNames -> includes ( tudOclIter0 . toString ( ) ) )   */
final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(node) );
final OclAnyImpl tudOclNode1=Ocl.toOclAnyImpl(tudOclNode0.getFeature("declarator"));
final OclSet tudOclSet0=OclSet.getEmptyOclSet();
tudOclSet0.setToInclude(tudOclNode1);
final OclInteger tudOclNode2=tudOclSet0.size();
final OclInteger tudOclNode3=new OclInteger(0);
final OclBoolean tudOclNode4=tudOclNode2.isGreaterThan(tudOclNode3);
final OclAnyImpl tudOclNode5=Ocl.toOclAnyImpl(tudOclNode0.getFeature("declarator"));
final OclType tudOclNode6=OclType.getOclTypeFor(node, "AStandardDeclarator");
final OclBoolean tudOclNode7=tudOclNode5.oclIsTypeOf(tudOclNode6);
final OclAnyImpl tudOclNode8=Ocl.toOclAnyImpl(tudOclNode0.getFeature("declarator"));
final OclType tudOclNode9=OclType.getOclTypeFor(node, "AStandardDeclarator");
final OclAnyImpl tudOclNode10=Ocl.toOclAnyImpl(tudOclNode8.oclAsType(tudOclNode9));
final OclSequence tudOclNode11=Ocl.toOclSequence(tudOclNode10.getFeature("declaratorTail"));
final OclInteger tudOclNode12=tudOclNode11.size();
final OclInteger tudOclNode13=new OclInteger(0);
final OclBoolean tudOclNode14=tudOclNode12.isGreaterThan(tudOclNode13);
final OclBoolean tudOclNode15=tudOclNode4.and(tudOclNode7).and(tudOclNode14);
final OclAnyImpl tudOclNode16=Ocl.toOclAnyImpl(tudOclNode0.getFeature("actualParameterList"));
final OclSet tudOclNode17=Ocl.toOclSet(tudOclNode16.getFeature("subnodes"));
final OclIterator tudOclIter1=tudOclNode17.getIterator();
final OclBooleanEvaluatable tudOclEval0=new OclBooleanEvaluatable() {
  public OclBoolean evaluate() {
    final OclType tudOclNode18=OclType.getOclTypeFor(node, "AFeaturePrimaryExpression");
    final OclBoolean tudOclNode19=Ocl.toOclAnyImpl(tudOclIter1.getValue()).oclIsTypeOf(tudOclNode18);
    return tudOclNode19;
  }
};
final OclSet tudOclNode20=Ocl.toOclSet(tudOclNode17.select(tudOclIter1, tudOclEval0));
final OclIterator tudOclIter0=tudOclNode20.getIterator();
final OclBooleanEvaluatable tudOclEval1=new OclBooleanEvaluatable() {
  public OclBoolean evaluate() {
    final OclType tudOclNode21=OclType.getOclTypeFor(node, "AFeaturePrimaryExpression");
    final OclAnyImpl tudOclNode22=Ocl.toOclAnyImpl(Ocl.toOclAnyImpl(tudOclIter0.getValue()).oclAsType(tudOclNode21));
    final OclAnyImpl tudOclNode23=Ocl.toOclAnyImpl(tudOclNode22.getFeature("pathName"));
    final OclType tudOclNode24=OclType.getOclTypeFor(node, "APathName");
    final OclAnyImpl tudOclNode25=Ocl.toOclAnyImpl(tudOclNode23.oclAsType(tudOclNode24));
    final OclAnyImpl tudOclNode26=Ocl.toOclAnyImpl(tudOclNode25.getFeature("pathNameBegin"));
    final OclType tudOclNode27=OclType.getOclTypeFor(node, "ANamePathNameBegin");
    final OclBoolean tudOclNode28=tudOclNode26.oclIsTypeOf(tudOclNode27);
    final OclType tudOclNode29=OclType.getOclTypeFor(node, "AFeaturePrimaryExpression");
    final OclAnyImpl tudOclNode30=Ocl.toOclAnyImpl(Ocl.toOclAnyImpl(tudOclIter0.getValue()).oclAsType(tudOclNode29));
    final OclAnyImpl tudOclNode31=Ocl.toOclAnyImpl(tudOclNode30.getFeature("featureCallParameters"));
    final OclSet tudOclSet1=OclSet.getEmptyOclSet();
    tudOclSet1.setToInclude(tudOclNode31);
    final OclBoolean tudOclNode32=tudOclSet1.isEmpty();
    final OclBoolean tudOclNode33=tudOclNode28.and(tudOclNode32);
    final OclSet tudOclNode34=Ocl.toOclSet(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature("boundNames"));
    final OclString tudOclNode35=Ocl.toOclString(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature("toString", null));
    final OclBoolean tudOclNode36=tudOclNode34.includes(tudOclNode35);
    final OclBoolean tudOclNode37=tudOclNode33.implies(tudOclNode36);
    return tudOclNode37;
  }
};
final OclBoolean tudOclNode38=tudOclNode20.forAll(tudOclIter0, tudOclEval1);
final OclBoolean tudOclNode39=tudOclNode15.implies(tudOclNode38);
  if ( !tudOclNode39.isTrue() ) {
    throw new GeneratedTestFailedException(
      "generated test failed:\ncontext AFeatureCallParameters inv tudOclInv0 : ( self . declarator -> size > 0 and self . declarator . oclIsTypeOf ( AStandardDeclarator ) and self . declarator . oclAsType ( AStandardDeclarator ) . declaratorTail -> size > 0 ) implies self . actualParameterList . subnodes -> select ( n : Node | n . oclIsTypeOf ( AFeaturePrimaryExpression ) ) -> forAll ( tudOclIter0 : Node | ( tudOclIter0 . oclAsType ( AFeaturePrimaryExpression ) . pathName . oclAsType ( APathName ) . pathNameBegin . oclIsTypeOf ( ANamePathNameBegin ) and tudOclIter0 . oclAsType ( AFeaturePrimaryExpression ) . featureCallParameters -> isEmpty ) implies tudOclIter0 . boundNames -> includes ( tudOclIter0 . toString ( ) ) )  \n"+
      "violating node:\n"+node
    );
  }
}

protected void tudOclCheckMethod5(final AOperationContext node) {
  /* context AOperationContext inv tudOclInv0 : self . formalParameterList -> size > 0 implies ( self . formalParameterList . oclAsType ( AFormalParameterList ) . formalParameterListTail -> collect ( f : AFormalParameterListTail | f . formalParameter ) -> including ( self . formalParameterList . oclAsType ( AFormalParameterList ) . formalParameter ) -> forAll ( fp : PFormalParameter | fp . oclAsType ( AFormalParameter ) . name . toString ( ) <> 'self' ) )   */
final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(node) );
final OclAnyImpl tudOclNode1=Ocl.toOclAnyImpl(tudOclNode0.getFeature("formalParameterList"));
final OclSet tudOclSet0=OclSet.getEmptyOclSet();
tudOclSet0.setToInclude(tudOclNode1);
final OclInteger tudOclNode2=tudOclSet0.size();
final OclInteger tudOclNode3=new OclInteger(0);
final OclBoolean tudOclNode4=tudOclNode2.isGreaterThan(tudOclNode3);
final OclAnyImpl tudOclNode5=Ocl.toOclAnyImpl(tudOclNode0.getFeature("formalParameterList"));
final OclType tudOclNode6=OclType.getOclTypeFor(node, "AFormalParameterList");
final OclAnyImpl tudOclNode7=Ocl.toOclAnyImpl(tudOclNode5.oclAsType(tudOclNode6));
final OclSequence tudOclNode8=Ocl.toOclSequence(tudOclNode7.getFeature("formalParameterListTail"));
final OclIterator tudOclIter0=tudOclNode8.getIterator();
final OclRootEvaluatable tudOclEval0=new OclRootEvaluatable() {
  public OclRoot evaluate() {
    final OclAnyImpl tudOclNode9=Ocl.toOclAnyImpl(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature("formalParameter"));
    return tudOclNode9;
  }
};
final OclSequence tudOclNode10=Ocl.toOclSequence(tudOclNode8.collect(tudOclIter0, tudOclEval0));
final OclAnyImpl tudOclNode11=Ocl.toOclAnyImpl(tudOclNode0.getFeature("formalParameterList"));
final OclType tudOclNode12=OclType.getOclTypeFor(node, "AFormalParameterList");
final OclAnyImpl tudOclNode13=Ocl.toOclAnyImpl(tudOclNode11.oclAsType(tudOclNode12));
final OclAnyImpl tudOclNode14=Ocl.toOclAnyImpl(tudOclNode13.getFeature("formalParameter"));
final OclSequence tudOclNode15=Ocl.toOclSequence(tudOclNode10.including(tudOclNode14));
final OclIterator tudOclIter1=tudOclNode15.getIterator();
final OclBooleanEvaluatable tudOclEval1=new OclBooleanEvaluatable() {
  public OclBoolean evaluate() {
    final OclType tudOclNode16=OclType.getOclTypeFor(node, "AFormalParameter");
    final OclAnyImpl tudOclNode17=Ocl.toOclAnyImpl(Ocl.toOclAnyImpl(tudOclIter1.getValue()).oclAsType(tudOclNode16));
    final OclAnyImpl tudOclNode18=Ocl.toOclAnyImpl(tudOclNode17.getFeature("name"));
    final OclString tudOclNode19=Ocl.toOclString(tudOclNode18.getFeature("toString", null));
    final OclString tudOclNode20=new OclString("self");
    final OclBoolean tudOclNode21=tudOclNode19.isNotEqualTo(tudOclNode20);
    return tudOclNode21;
  }
};
final OclBoolean tudOclNode22=tudOclNode15.forAll(tudOclIter1, tudOclEval1);
final OclBoolean tudOclNode23=tudOclNode4.implies(tudOclNode22);
  if ( !tudOclNode23.isTrue() ) {
    throw new GeneratedTestFailedException(
      "generated test failed:\ncontext AOperationContext inv tudOclInv0 : self . formalParameterList -> size > 0 implies ( self . formalParameterList . oclAsType ( AFormalParameterList ) . formalParameterListTail -> collect ( f : AFormalParameterListTail | f . formalParameter ) -> including ( self . formalParameterList . oclAsType ( AFormalParameterList ) . formalParameter ) -> forAll ( fp : PFormalParameter | fp . oclAsType ( AFormalParameter ) . name . toString ( ) <> 'self' ) )  \n"+
      "violating node:\n"+node
    );
  }
}

protected void tudOclCheckMethod6(final AStandardDeclarator node) {
  /* context AStandardDeclarator inv tudOclInv0 : self . name . toString ( ) <> 'self' and self . declaratorTail -> collect ( d : ADeclaratorTail | d . name ) -> forAll ( n : TName | n . toString ( ) <> 'self' )   */
final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(node) );
final OclAnyImpl tudOclNode1=Ocl.toOclAnyImpl(tudOclNode0.getFeature("name"));
final OclString tudOclNode2=Ocl.toOclString(tudOclNode1.getFeature("toString", null));
final OclString tudOclNode3=new OclString("self");
final OclBoolean tudOclNode4=tudOclNode2.isNotEqualTo(tudOclNode3);
final OclSequence tudOclNode5=Ocl.toOclSequence(tudOclNode0.getFeature("declaratorTail"));
final OclIterator tudOclIter0=tudOclNode5.getIterator();
final OclRootEvaluatable tudOclEval0=new OclRootEvaluatable() {
  public OclRoot evaluate() {
    final OclAnyImpl tudOclNode6=Ocl.toOclAnyImpl(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature("name"));
    return tudOclNode6;
  }
};
final OclSequence tudOclNode7=Ocl.toOclSequence(tudOclNode5.collect(tudOclIter0, tudOclEval0));
final OclIterator tudOclIter1=tudOclNode7.getIterator();
final OclBooleanEvaluatable tudOclEval1=new OclBooleanEvaluatable() {
  public OclBoolean evaluate() {
    final OclString tudOclNode8=Ocl.toOclString(Ocl.toOclAnyImpl(tudOclIter1.getValue()).getFeature("toString", null));
    final OclString tudOclNode9=new OclString("self");
    final OclBoolean tudOclNode10=tudOclNode8.isNotEqualTo(tudOclNode9);
    return tudOclNode10;
  }
};
final OclBoolean tudOclNode11=tudOclNode7.forAll(tudOclIter1, tudOclEval1);
final OclBoolean tudOclNode12=tudOclNode4.and(tudOclNode11);
  if ( !tudOclNode12.isTrue() ) {
    throw new GeneratedTestFailedException(
      "generated test failed:\ncontext AStandardDeclarator inv tudOclInv0 : self . name . toString ( ) <> 'self' and self . declaratorTail -> collect ( d : ADeclaratorTail | d . name ) -> forAll ( n : TName | n . toString ( ) <> 'self' )  \n"+
      "violating node:\n"+node
    );
  }
}

protected void tudOclCheckMethod7(final AIterateDeclarator node) {
  /* context AIterateDeclarator inv tudOclInv0 : self . iterator . toString ( ) <> 'self' and self . accumulator . toString ( ) <> 'self'   */
final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(node) );
final OclAnyImpl tudOclNode1=Ocl.toOclAnyImpl(tudOclNode0.getFeature("iterator"));
final OclString tudOclNode2=Ocl.toOclString(tudOclNode1.getFeature("toString", null));
final OclString tudOclNode3=new OclString("self");
final OclBoolean tudOclNode4=tudOclNode2.isNotEqualTo(tudOclNode3);
final OclAnyImpl tudOclNode5=Ocl.toOclAnyImpl(tudOclNode0.getFeature("accumulator"));
final OclString tudOclNode6=Ocl.toOclString(tudOclNode5.getFeature("toString", null));
final OclString tudOclNode7=new OclString("self");
final OclBoolean tudOclNode8=tudOclNode6.isNotEqualTo(tudOclNode7);
final OclBoolean tudOclNode9=tudOclNode4.and(tudOclNode8);
  if ( !tudOclNode9.isTrue() ) {
    throw new GeneratedTestFailedException(
      "generated test failed:\ncontext AIterateDeclarator inv tudOclInv0 : self . iterator . toString ( ) <> 'self' and self . accumulator . toString ( ) <> 'self'  \n"+
      "violating node:\n"+node
    );
  }
}

protected void tudOclCheckMethod8(final APostfixExpressionTail node) {
  /* context APostfixExpressionTail inv tudOclInv0 : let fc : AFeatureCall = self . featureCall . oclAsType ( AFeatureCall ) in ( self . postfixExpressionTailBegin . oclIsTypeOf ( AArrowPostfixExpressionTailBegin ) and Set { 'forAll' , 'exists' , 'isUnique' , 'sortedBy' , 'iterate' , 'select' , 'reject' , 'collect' } -> includes ( fc . pathName . toString ( ) ) ) implies ( fc . featureCallParameters -> notEmpty and fc . featureCallParameters . oclAsType ( AFeatureCallParameters ) . actualParameterList -> notEmpty and fc . featureCallParameters . oclAsType ( AFeatureCallParameters ) . actualParameterList . oclAsType ( AActualParameterList ) . actualParameterListTail -> isEmpty )   */
final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(node) );
final OclAnyImpl tudOclNode1=Ocl.toOclAnyImpl(tudOclNode0.getFeature("featureCall"));
final OclType tudOclNode2=OclType.getOclTypeFor(node, "AFeatureCall");
final OclAnyImpl tudOclNode3=Ocl.toOclAnyImpl(tudOclNode1.oclAsType(tudOclNode2));
final OclAnyImpl tudOclNode4=Ocl.toOclAnyImpl(tudOclNode0.getFeature("postfixExpressionTailBegin"));
final OclType tudOclNode5=OclType.getOclTypeFor(node, "AArrowPostfixExpressionTailBegin");
final OclBoolean tudOclNode6=tudOclNode4.oclIsTypeOf(tudOclNode5);
final OclSet tudOclNode7=OclSet.getEmptyOclSet();
final OclString tudOclNode8=new OclString("forAll");
final OclString tudOclNode9=new OclString("exists");
final OclString tudOclNode10=new OclString("isUnique");
final OclString tudOclNode11=new OclString("sortedBy");
final OclString tudOclNode12=new OclString("iterate");
final OclString tudOclNode13=new OclString("select");
final OclString tudOclNode14=new OclString("reject");
final OclString tudOclNode15=new OclString("collect");
tudOclNode7.setToInclude(tudOclNode8);
tudOclNode7.setToInclude(tudOclNode9);
tudOclNode7.setToInclude(tudOclNode10);
tudOclNode7.setToInclude(tudOclNode11);
tudOclNode7.setToInclude(tudOclNode12);
tudOclNode7.setToInclude(tudOclNode13);
tudOclNode7.setToInclude(tudOclNode14);
tudOclNode7.setToInclude(tudOclNode15);
final OclAnyImpl tudOclNode16=Ocl.toOclAnyImpl(tudOclNode3.getFeature("pathName"));
final OclString tudOclNode17=Ocl.toOclString(tudOclNode16.getFeature("toString", null));
final OclBoolean tudOclNode18=tudOclNode7.includes(tudOclNode17);
final OclBoolean tudOclNode19=tudOclNode6.and(tudOclNode18);
final OclAnyImpl tudOclNode20=Ocl.toOclAnyImpl(tudOclNode3.getFeature("featureCallParameters"));
final OclSet tudOclSet0=OclSet.getEmptyOclSet();
tudOclSet0.setToInclude(tudOclNode20);
final OclBoolean tudOclNode21=tudOclSet0.notEmpty();
final OclAnyImpl tudOclNode22=Ocl.toOclAnyImpl(tudOclNode3.getFeature("featureCallParameters"));
final OclType tudOclNode23=OclType.getOclTypeFor(node, "AFeatureCallParameters");
final OclAnyImpl tudOclNode24=Ocl.toOclAnyImpl(tudOclNode22.oclAsType(tudOclNode23));
final OclAnyImpl tudOclNode25=Ocl.toOclAnyImpl(tudOclNode24.getFeature("actualParameterList"));
final OclSet tudOclSet1=OclSet.getEmptyOclSet();
tudOclSet1.setToInclude(tudOclNode25);
final OclBoolean tudOclNode26=tudOclSet1.notEmpty();
final OclAnyImpl tudOclNode27=Ocl.toOclAnyImpl(tudOclNode3.getFeature("featureCallParameters"));
final OclType tudOclNode28=OclType.getOclTypeFor(node, "AFeatureCallParameters");
final OclAnyImpl tudOclNode29=Ocl.toOclAnyImpl(tudOclNode27.oclAsType(tudOclNode28));
final OclAnyImpl tudOclNode30=Ocl.toOclAnyImpl(tudOclNode29.getFeature("actualParameterList"));
final OclType tudOclNode31=OclType.getOclTypeFor(node, "AActualParameterList");
final OclAnyImpl tudOclNode32=Ocl.toOclAnyImpl(tudOclNode30.oclAsType(tudOclNode31));
final OclSequence tudOclNode33=Ocl.toOclSequence(tudOclNode32.getFeature("actualParameterListTail"));
final OclBoolean tudOclNode34=tudOclNode33.isEmpty();
final OclBoolean tudOclNode35=tudOclNode21.and(tudOclNode26).and(tudOclNode34);
final OclBoolean tudOclNode36=tudOclNode19.implies(tudOclNode35);
  if ( !tudOclNode36.isTrue() ) {
    throw new GeneratedTestFailedException(
      "generated test failed:\ncontext APostfixExpressionTail inv tudOclInv0 : let fc : AFeatureCall = self . featureCall . oclAsType ( AFeatureCall ) in ( self . postfixExpressionTailBegin . oclIsTypeOf ( AArrowPostfixExpressionTailBegin ) and Set { 'forAll' , 'exists' , 'isUnique' , 'sortedBy' , 'iterate' , 'select' , 'reject' , 'collect' } -> includes ( fc . pathName . toString ( ) ) ) implies ( fc . featureCallParameters -> notEmpty and fc . featureCallParameters . oclAsType ( AFeatureCallParameters ) . actualParameterList -> notEmpty and fc . featureCallParameters . oclAsType ( AFeatureCallParameters ) . actualParameterList . oclAsType ( AActualParameterList ) . actualParameterListTail -> isEmpty )  \n"+
      "violating node:\n"+node
    );
  }
}

protected void tudOclCheckMethod9(final AConstraintBody node) {
  /* context AConstraintBody inv tudOclInv0 : ( not self . stereotype . oclIsTypeOf ( APostStereotype ) ) implies ( self . subnodes -> select ( tudOclIter0 : Node | tudOclIter0 . oclIsTypeOf ( ATimeExpression ) ) -> isEmpty and self . subnodes -> select ( tudOclIter1 : Node | tudOclIter1 . oclIsTypeOf ( AFeaturePrimaryExpression ) ) -> select ( n : Node | n . oclAsType ( AFeaturePrimaryExpression ) . pathName . toString ( ) = 'oclIsNew' ) -> isEmpty and self . subnodes -> select ( tudOclIter2 : Node | tudOclIter2 . oclIsTypeOf ( AFeatureCall ) ) -> select ( tudOclIter3 : Node | tudOclIter3 . oclAsType ( AFeatureCall ) . pathName . toString ( ) = 'oclIsNew' ) -> isEmpty )   */
final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(node) );
final OclAnyImpl tudOclNode1=Ocl.toOclAnyImpl(tudOclNode0.getFeature("stereotype"));
final OclType tudOclNode2=OclType.getOclTypeFor(node, "APostStereotype");
final OclBoolean tudOclNode3=tudOclNode1.oclIsTypeOf(tudOclNode2);
final OclBoolean tudOclNode4=tudOclNode3.not();
final OclSet tudOclNode5=Ocl.toOclSet(tudOclNode0.getFeature("subnodes"));
final OclIterator tudOclIter0=tudOclNode5.getIterator();
final OclBooleanEvaluatable tudOclEval0=new OclBooleanEvaluatable() {
  public OclBoolean evaluate() {
    final OclType tudOclNode6=OclType.getOclTypeFor(node, "ATimeExpression");
    final OclBoolean tudOclNode7=Ocl.toOclAnyImpl(tudOclIter0.getValue()).oclIsTypeOf(tudOclNode6);
    return tudOclNode7;
  }
};
final OclSet tudOclNode8=Ocl.toOclSet(tudOclNode5.select(tudOclIter0, tudOclEval0));
final OclBoolean tudOclNode9=tudOclNode8.isEmpty();
final OclSet tudOclNode10=Ocl.toOclSet(tudOclNode0.getFeature("subnodes"));
final OclIterator tudOclIter1=tudOclNode10.getIterator();
final OclBooleanEvaluatable tudOclEval1=new OclBooleanEvaluatable() {
  public OclBoolean evaluate() {
    final OclType tudOclNode11=OclType.getOclTypeFor(node, "AFeaturePrimaryExpression");
    final OclBoolean tudOclNode12=Ocl.toOclAnyImpl(tudOclIter1.getValue()).oclIsTypeOf(tudOclNode11);
    return tudOclNode12;
  }
};
final OclSet tudOclNode13=Ocl.toOclSet(tudOclNode10.select(tudOclIter1, tudOclEval1));
final OclIterator tudOclIter4=tudOclNode13.getIterator();
final OclBooleanEvaluatable tudOclEval2=new OclBooleanEvaluatable() {
  public OclBoolean evaluate() {
    final OclType tudOclNode14=OclType.getOclTypeFor(node, "AFeaturePrimaryExpression");
    final OclAnyImpl tudOclNode15=Ocl.toOclAnyImpl(Ocl.toOclAnyImpl(tudOclIter4.getValue()).oclAsType(tudOclNode14));
    final OclAnyImpl tudOclNode16=Ocl.toOclAnyImpl(tudOclNode15.getFeature("pathName"));
    final OclString tudOclNode17=Ocl.toOclString(tudOclNode16.getFeature("toString", null));
    final OclString tudOclNode18=new OclString("oclIsNew");
    final OclBoolean tudOclNode19=tudOclNode17.isEqualTo(tudOclNode18);
    return tudOclNode19;
  }
};
final OclSet tudOclNode20=Ocl.toOclSet(tudOclNode13.select(tudOclIter4, tudOclEval2));
final OclBoolean tudOclNode21=tudOclNode20.isEmpty();
final OclSet tudOclNode22=Ocl.toOclSet(tudOclNode0.getFeature("subnodes"));
final OclIterator tudOclIter2=tudOclNode22.getIterator();
final OclBooleanEvaluatable tudOclEval3=new OclBooleanEvaluatable() {
  public OclBoolean evaluate() {
    final OclType tudOclNode23=OclType.getOclTypeFor(node, "AFeatureCall");
    final OclBoolean tudOclNode24=Ocl.toOclAnyImpl(tudOclIter2.getValue()).oclIsTypeOf(tudOclNode23);
    return tudOclNode24;
  }
};
final OclSet tudOclNode25=Ocl.toOclSet(tudOclNode22.select(tudOclIter2, tudOclEval3));
final OclIterator tudOclIter3=tudOclNode25.getIterator();
final OclBooleanEvaluatable tudOclEval4=new OclBooleanEvaluatable() {
  public OclBoolean evaluate() {
    final OclType tudOclNode26=OclType.getOclTypeFor(node, "AFeatureCall");
    final OclAnyImpl tudOclNode27=Ocl.toOclAnyImpl(Ocl.toOclAnyImpl(tudOclIter3.getValue()).oclAsType(tudOclNode26));
    final OclAnyImpl tudOclNode28=Ocl.toOclAnyImpl(tudOclNode27.getFeature("pathName"));
    final OclString tudOclNode29=Ocl.toOclString(tudOclNode28.getFeature("toString", null));
    final OclString tudOclNode30=new OclString("oclIsNew");
    final OclBoolean tudOclNode31=tudOclNode29.isEqualTo(tudOclNode30);
    return tudOclNode31;
  }
};
final OclSet tudOclNode32=Ocl.toOclSet(tudOclNode25.select(tudOclIter3, tudOclEval4));
final OclBoolean tudOclNode33=tudOclNode32.isEmpty();
final OclBoolean tudOclNode34=tudOclNode9.and(tudOclNode21).and(tudOclNode33);
final OclBoolean tudOclNode35=tudOclNode4.implies(tudOclNode34);
  if ( !tudOclNode35.isTrue() ) {
    throw new GeneratedTestFailedException(
      "generated test failed:\ncontext AConstraintBody inv tudOclInv0 : ( not self . stereotype . oclIsTypeOf ( APostStereotype ) ) implies ( self . subnodes -> select ( tudOclIter0 : Node | tudOclIter0 . oclIsTypeOf ( ATimeExpression ) ) -> isEmpty and self . subnodes -> select ( tudOclIter1 : Node | tudOclIter1 . oclIsTypeOf ( AFeaturePrimaryExpression ) ) -> select ( n : Node | n . oclAsType ( AFeaturePrimaryExpression ) . pathName . toString ( ) = 'oclIsNew' ) -> isEmpty and self . subnodes -> select ( tudOclIter2 : Node | tudOclIter2 . oclIsTypeOf ( AFeatureCall ) ) -> select ( tudOclIter3 : Node | tudOclIter3 . oclAsType ( AFeatureCall ) . pathName . toString ( ) = 'oclIsNew' ) -> isEmpty )  \n"+
      "violating node:\n"+node
    );
  }
}

}

class GeneratedTestFailedException extends tudresden.ocl.OclException {

  GeneratedTestFailedException(String msg) {
    super(msg);
  }
}

