/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package tudresden.ocl.codegen;

import tudresden.ocl.OclTree;
import tudresden.ocl.NameCreator;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.check.types.*;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.HashMap;
import tudresden.ocl.OclException;

public class JavaCodeGenerator extends ProceduralCodeGenerator {

  String instanceName;
  String javaResult;

  /** maps Nodes for operators (ALogicalOperator, ARelationalOperator...) to
   *  the String containing their Java representation
   */
  NodeNameMap operatorCode=new NodeNameMap();
  StringStringMap varMap=new StringStringMap();

  /** set of all OclAny property names
   */
  static java.util.HashSet oclAnyOperations;

  static {
    oclAnyOperations=new java.util.HashSet();
    oclAnyOperations.add("oclIsKindOf");
    oclAnyOperations.add("oclIsTypeOf");
    oclAnyOperations.add("oclAsType");
    oclAnyOperations.add("oclInState");
    oclAnyOperations.add("oclIsNew");
  }

  /** @param instanceName a Java expression that will be evaluated to the
   *                      instance that is checked for constraint conformance
   *  @param resultName   a Java expression that will be evaluated to the
   *                      result of constrained operation (can be
   *                      <code>null</code> for other constraints than post
   *                      conditions
   */
  public JavaCodeGenerator(String instanceName, String resultName) {
    this.instanceName=instanceName;
    this.javaResult=resultName;
  }


  /** @param instanceName a Java expression that will be evaluated to the
   *                      instance that is checked for constraint conformance
   */
  public JavaCodeGenerator(String instanceName) {
    this(instanceName, null);
  }

  public JavaCodeGenerator() {
    this("this");
  }

  // ---- abstract methods from ProceduralCodeGenerator: ----

  protected String importPreVariable(String var, String type) {
    StringBuffer ret=new StringBuffer();
    ret.append("final "+type);
    ret.append(" "+var);
    ret.append("=this."+var+";\n");
    return ret.toString();
  }

  protected String exportPreVariable(String var, String type) {
    return "this."+var+"="+var+";\n";
  }

  protected String getTransferCode(String var, String type) {
    StringBuffer ret=new StringBuffer();
    ret.append(type+" "+var+";\n");
    return ret.toString();
  }

  protected void requireTreeInvariants() {
    // unique variables
    tree.requireInvariant(
      "context AExpression inv : letExpression -> forAll ( le | not self . parent . boundNames -> includes ( le . name ) )  "
    );
    tree.requireInvariant(
      "context AStandardDeclarator : not parent . parent . boundNames -> includes ( name.toString() )"
    );
    tree.requireInvariant(
      "context AIterateDeclarator : let ppb = parent . parent . boundNames in not ( ppb -> includes ( iterator.toString() ) or ppb -> includes( accumulator.toString() ) )"
    );
    // no multiple iterators
    tree.requireInvariant(
      "context AStandardDeclarator inv : declaratorTail -> isEmpty"
    );
    // explicitly qualified names
    tree.requireInvariant(
      "context ANamePathNameBegin inv : boundNames -> includes ( name )"
    );
    // iterators where possible
    tree.requireInvariant(
      "context AFeatureCall inv : "+
      "let iteratingMethodNames : Set ( String ) = Set { 'collect', 'exists' , 'forAll' , 'isUnique' , 'reject' , 'select' , 'sortedBy' } in "+
      "iteratingMethodNames -> includes ( self . pathName . toString ( ) ) implies "+
      "( self . featureCallParameters . declarator -> size = 1 "+
      "and self . featureCallParameters . declarator . oclType = AStandardDeclarator )"
    );
    tree.requireInvariant(
      "context ANamePathNameBegin inv : defaultContext <> '' implies boundNames -> includes ( defaultContext )"
    );
    // named constraints
    tree.requireInvariant(
      "context AConstraintBody inv : name -> size = 1"
    );
  }

  // ---------- tree traversal: -----------------------------

  /** the variable for the AConstraintBody node is the variable for &quot;self&quot;
   */
  public void inAConstraintBody(AConstraintBody cb) {
    varMap.clear();
    appendCode("final OclAnyImpl "+getVariable(cb)+"=Ocl.toOclAnyImpl( Ocl.getFor("+instanceName+") );\n" );
    varMap.put("self", getVariable(cb));
    if (parameters!=null) {
      for (int i=0; i<parameters.length; i++) {
        Type oclParamType=tree.getTypeFor(parameters[i][0], cb);
        String javaParamType=getJavaType(oclParamType);
        String javaParamName=tree.getNameCreator().getUniqueName("OpPar");
        appendCode("final "+javaParamType+" "+javaParamName+"="+
          "Ocl.to"+javaParamType+"( Ocl.getFor("+parameters[i][0]+") );\n");
        varMap.put(parameters[i][0], javaParamName);
      }
    }
    if ( topOfStack.getKind()==CodeFragment.POST ) {
      Type oclReturnType=tree.getTypeFor("result", cb.getExpression());
      if (oclReturnType!=null) {
        if (javaResult==null) {
          throw new OclException("tried to generate code for post condition without setting Java code for result");
        }
        String javaType=getJavaType(oclReturnType);
        String result=tree.getNameCreator().getUniqueName("Result");
        varMap.put("result", result);
        writeToStandardCodeOnly();
        appendCode("final "+javaType+" "+result+"=Ocl.to"+javaType+"( Ocl.getFor("+javaResult+") );\n");
        writeToPreCodeOnly();
        appendCode("final "+javaType+" "+result+"="+javaType+".UNDEFINED;\n");
        writeToBothCodes();
      }
    }
  }

  public void outAExpression(AExpression e) {
    reachThrough(e, e.getLogicalExpression());
  }

  /** if a then b else c endif   <br>
   *  Ocl? result=(a.isTrue()) ? (b) : (c);
   *  <p>
   *  For each if expression a new variable is generated.
   */
  public void outAIfExpression(AIfExpression ie) {
    String javaType=getJavaType(tree.getNodeType(ie));
    appendCode("final "+javaType+" "+getVariable(ie)+"=("+getVariable(ie.getIfBranch())+".isTrue()) ? (");
    appendCode(getVariable(ie.getThenBranch())+") : ("+getVariable(ie.getElseBranch())+");\n");
  }

  /** <CODE>and</CODE>, <CODE>or</CODE> and <CODE>xor</CODE> have precedence
   *  over <CODE>implies</CODE> (<code>and</code> binds as strongly as
   *  <code>or</code>)
   *  <p>
   *  a and b or c   <br>
   *  OclBoolean result = a.and(b).or(c);
   *  <p>
   *  A variable is generated for each ALogicalExpression with tail (not for a
   *  ALogicalExpressionTail).
   */
  public void outALogicalExpression(ALogicalExpression le) {
    if (le.getLogicalExpressionTail().isEmpty()) {
      reachThrough( le, le.getRelationalExpression() );
    } else {
      int tailElements=le.getLogicalExpressionTail().size();
      PRelationalExpression[] relExprs=new PRelationalExpression[tailElements+1];
      PLogicalOperator[] logOps=new PLogicalOperator[tailElements];
      boolean[] opIsImplies=new boolean[tailElements];
      // logOps[i] is the operator AFTER relExprs[i]
      Iterator iter=le.getLogicalExpressionTail().iterator();
      int index=0;
      relExprs[0]=le.getRelationalExpression();
      while (iter.hasNext()) {
        ALogicalExpressionTail let=(ALogicalExpressionTail)iter.next();
        logOps[index]=let.getLogicalOperator();
        opIsImplies[index]=(logOps[index] instanceof AImpliesLogicalOperator);
        index++;
        relExprs[index]=let.getRelationalExpression();
      }

      appendCode("final OclBoolean "+getVariable(le)+"=");
      int lastNonImplies=0; // index of operand after last implies
      boolean foundImplies=false;
      for (int i=0; i<tailElements; i++) {
        if (opIsImplies[i]) {
          int from=lastNonImplies;
          int to=i;
          appendAndOrLogExpr(relExprs, logOps, from, to);
          lastNonImplies=to+1;
          if (foundImplies) {
            appendCode(")");
          }
          appendCode(".implies(");
          foundImplies=true;
        }
      }
      appendAndOrLogExpr(relExprs, logOps, lastNonImplies, tailElements);
      if (foundImplies) {
        appendCode(")");
      }
      appendCode(";\n");
    }
  }

  /** Append code for the part of a logical expression between the indexes of
   *  relational subexpression given as parameters <code>from</code> and
   *  <code>to</code>, both relational expressions included. The part must not
   *  contain an <code>implies</code> operator. <CODE>from</CODE> may be equal
   *  to <CODE>to</CODE>.
   */
  protected void appendAndOrLogExpr(
      PRelationalExpression[] relExprs, PLogicalOperator[] logOps,
      int from, int to ) {
    appendCode( getVariable(relExprs[from]) );
    for (int i=from+1; i<=to; i++) {
      appendCode( "." );
      appendCode( operatorCode.get(logOps[i-1]) ); // "and" or "or"
      appendCode( "("+getVariable(relExprs[i])+")" );
    }
  }

  /** a&lt;b = c&gt;d is not allowed by the OCL grammar, therefore we need not
   *  care about precedence
   *  <p>
   *  a = b  <br>
   *  OclBoolean result=a.isEqualTo(b); <br>
   *  a > b <br>
   *  OclBoolean result=Ocl.toOclComparable(a).isGreaterThan(Ocl.toOclComparable(b));
   *  <p>
   *  A variable is generated for each ARelationalExpression if it has
   *  a ARelationalExpressionTail subnode.
   *
   */
  public void outARelationalExpression(ARelationalExpression re) {
    if (re.getRelationalExpressionTail()==null) {
      reachThrough(re, re.getAdditiveExpression());
    } else {
      ARelationalExpressionTail ret=
        (ARelationalExpressionTail) re.getRelationalExpressionTail();
      PRelationalOperator relOp=ret.getRelationalOperator();
      boolean comparison=true;
      if (relOp instanceof AEqualRelationalOperator ||
          relOp instanceof ANEqualRelationalOperator) {
        comparison=false;
      }
      appendCode( "final OclBoolean "+getVariable(re)+"=" );
      //if (comparison) appendCode("Ocl.toOclComparable(");
      appendCode( getVariable(re.getAdditiveExpression()) );
      //if (comparison) appendCode(")");
      appendCode( "."+operatorCode.get(ret.getRelationalOperator())+"(" );
      //if (comparison) appendCode("Ocl.toOclComparable(");
      appendCode( getVariable(ret.getAdditiveExpression()) );
      //if (comparison) appendCode(")");
      appendCode(");\n");
    }
  }

  /** a + b <br>
   *  Ocl? result=Ocl.toOcl?(a).add(Ocl.toOcl?(b)); <br>
   *  with Ocl? = OclReal | OclInteger | OclSet
   *  <p>
   *  A variable is generated for each AAdditiveExpression with tail.
   */
  public void outAAdditiveExpression(AAdditiveExpression ae) {
    if (ae.getAdditiveExpressionTail().isEmpty()) {
      reachThrough(ae, ae.getMultiplicativeExpression());
    } else {
      Type nodeType=tree.getNodeType(ae);
      String javaType=getJavaType(nodeType);
      appendCode("final "+javaType+" "+getVariable(ae)+"=");
      appendCode( getVariable(ae.getMultiplicativeExpression()) );
      Iterator iter=ae.getAdditiveExpressionTail().iterator();
      while (iter.hasNext()) {
        AAdditiveExpressionTail aet = (AAdditiveExpressionTail) iter.next();
        appendCode( "."+operatorCode.get(aet.getAddOperator())+"(" );
        appendCode( getVariable(aet.getMultiplicativeExpression()) );
        appendCode( ")" );
      }
      appendCode(";\n");
    }
  }

  /** a * b <br>
   *  Ocl? result=Ocl.toOcl?(a).multiply(Ocl.toOcl?(b)); <br>
   *  with Ocl? = OclReal | OclInteger
   *  <p>
   *  A variable is generated for each AMultiplicativeExpression with tail.
   */
  public void outAMultiplicativeExpression(AMultiplicativeExpression me) {
    if (me.getMultiplicativeExpressionTail().isEmpty()) {
      reachThrough(me, me.getUnaryExpression());
    } else {
      String javaType=getJavaType( tree.getNodeType(me) );
      appendCode("final "+javaType+" "+getVariable(me)+"=");
      appendCode("Ocl.to"+getJavaType( tree.getNodeType(me.getUnaryExpression()) )+"(");
      appendCode( getVariable(me.getUnaryExpression()) );
      appendCode(")");
      Iterator iter=me.getMultiplicativeExpressionTail().iterator();
      while (iter.hasNext()) {
        AMultiplicativeExpressionTail met=(AMultiplicativeExpressionTail)iter.next();
        javaType=getJavaType( tree.getNodeType(met.getUnaryExpression()) );
        appendCode("."+operatorCode.get(met.getMultiplyOperator())+"(");
        appendCode(getVariable(met.getUnaryExpression())+")");
      }
      appendCode(";\n");
    }
  }

  /** not a <br>
   *  OclBoolean result=a.not();
   *  <p>
   *  A variable is generated for each AUnaryUnaryExpression.
   */
  public void outAUnaryUnaryExpression(AUnaryUnaryExpression uue) {
    Type oclType=tree.getNodeType(uue);
    String javaType=getJavaType( oclType );
    appendCode("final "+javaType+" "+getVariable(uue)+"=");
    if (oclType==Basic.INTEGER) appendCode("Ocl.toOclInteger(");
    appendCode(getVariable(uue.getPostfixExpression())+"."+
      operatorCode.get( uue.getUnaryOperator() )+"()");
    if (oclType==Basic.INTEGER) appendCode(")");
    appendCode(";\n");
  }

  public void outAPostfixUnaryExpression(APostfixUnaryExpression pue) {
    reachThrough(pue, pue.getPostfixExpression());
  }

  public void outAPostfixExpression(APostfixExpression pe) {
    if (pe.getPostfixExpressionTail().isEmpty()) {
      reachThrough(pe, pe.getPrimaryExpression());
    } else {
      APostfixExpressionTail last = (APostfixExpressionTail)
        pe.getPostfixExpressionTail().getLast();
      reachThrough(pe, last);
    }
  }

  public void outAFeaturePrimaryExpression(AFeaturePrimaryExpression pe) {
    Type oclType=tree.getNodeType(pe);
    if (oclType instanceof OclType) {
      // type name
      appendCode("final OclType "+getVariable(pe)+"=OclType.getOclTypeFor("+
        instanceName+", \""+pe.toString().trim()+"\");\n");
    } else {
      setVariable(pe, varMap.get( pe.toString().trim() ));
    }
  }

  /** This method breaks the usual pattern of generating Java code postfix
   *  since the collection must be declared and instanciated prior to setting
   *  its contents (in <CODE>outAExpressionListOrRange</CODE>).
   */
  public void inALitColPrimaryExpression(ALitColPrimaryExpression lcpe) {
    String javaType=getJavaType( tree.getNodeType(lcpe) );
    appendCode("final "+javaType+" "+getVariable(lcpe)+"="+javaType+".getEmpty"+javaType+"();\n");
  }

  public void outALiteralPrimaryExpression(ALiteralPrimaryExpression lpe) {
    reachThrough(lpe, lpe.getLiteral());
  }

  public void outAParenthesesPrimaryExpression(AParenthesesPrimaryExpression lpe) {
    reachThrough(lpe, lpe.getExpression());
  }

  public void outAIfPrimaryExpression(AIfPrimaryExpression ipe) {
    reachThrough(ipe, ipe.getIfExpression());
  }

  public void outAExpressionListOrRange(AExpressionListOrRange elor) {
    String collectionVar=getVariable(elor.parent().parent());;
    PExpressionListOrRangeTail tail=elor.getExpressionListOrRangeTail();
    if (tail==null) {
      addExpressionToCollection(elor.getExpression(), collectionVar);
    } else if (tail instanceof AListExpressionListOrRangeTail) {
      AListExpressionListOrRangeTail list=(AListExpressionListOrRangeTail)tail;
      addExpressionToCollection(elor.getExpression(), collectionVar);
      Iterator iter=list.getExpressionListTail().iterator();
      while (iter.hasNext()) {
        AExpressionListTail next=(AExpressionListTail)iter.next();
        addExpressionToCollection(next.getExpression(), collectionVar);
      }
    } else {
      // tail instanceof ARangeExpressionListOrRangeTail
      ARangeExpressionListOrRangeTail range=(ARangeExpressionListOrRangeTail)tail;
      appendCode(collectionVar+".setToRange("+getVariable(elor.getExpression())+
        ", "+getVariable(range.getExpression())+");\n");
    }
  }

  /** Iterating methods parameters must be inserted into an inner class,
   *  therefore it is not possible to translate APostfixExpressionTail
   *  postfix. Hence, the case method is overwritten.
   */
  public void caseAPostfixExpressionTail(APostfixExpressionTail pet) {
    Node appliedTo=getPreviousNode(pet);
    Type appliedType=tree.getNodeType(appliedTo);
    AFeatureCall fc=(AFeatureCall)pet.getFeatureCall();
    if (fc.getTimeExpression()!=null) {
      assurePreCode();
      writeToPreCodeOnly();
    }
    if (appliedType instanceof Basic) {
      appendPostfixBasic(pet, appliedTo, (Basic)appliedType);
    } else if ( pet.getPostfixExpressionTailBegin() instanceof
                AArrowPostfixExpressionTailBegin ) {
      appendPostfixArrowOp(pet, appliedTo, appliedType);
    } else {
      String featurePathName=fc.getPathName().toString().trim();
      if (oclAnyOperations.contains(featurePathName)) {
        // property of OclAny, handled like basic types
        appendPostfixBasic(pet, appliedTo, appliedType);
      } else {
        appendPostfixDotOp(pet, appliedTo, appliedType);
      }
    }
    if (fc.getTimeExpression()!=null) {
      addPreVariable( getVariable(pet), getJavaType( tree.getNodeType(pet) ) );
      writeToBothCodes();
    }
  }

  protected void appendPostfixBasic(APostfixExpressionTail pet, Node appliedTo, Type appliedType) {
    super.caseAPostfixExpressionTail(pet);  // recursive descent
    Type oclType=tree.getNodeType(pet);
    String javaType=getJavaType( oclType );
    AFeatureCall fc=(AFeatureCall) pet.getFeatureCall();
    String featureName=fc.getPathName().toString().trim();
    if (featureName.equals("oclIsNew")) {
      throw new OclException(
        "JavaCodeGenerator does not support the OclAny property \"oclIsNew\""
      );
    }
    appendCode("final "+javaType+" "+getVariable(pet)+"=");
    boolean insertCastToInteger=featureName.equals("abs") && (oclType==Basic.INTEGER);
    boolean insertCastToOclAnyImpl=featureName.equals("oclAsType") && (javaType.equals("OclAnyImpl"));
    if (insertCastToInteger) appendCode("Ocl.toOclInteger(");
    if (insertCastToOclAnyImpl) appendCode("Ocl.toOclAnyImpl(");
    appendCode(getVariable(appliedTo)+"."+featureName+"(");
    if (fc.getFeatureCallParameters()!=null) {
      AFeatureCallParameters fcp = (AFeatureCallParameters) fc.getFeatureCallParameters();
      appendActualParameterList((AActualParameterList)fcp.getActualParameterList());
    }
    if (insertCastToInteger || insertCastToOclAnyImpl) appendCode(")");
    appendCode(");\n");
  }


  /** The APostfixExpressionTail begins with '->' and is therefore a collection
   *  operation applied either to a collection or to a single, possible undefined
   *  element (as in <CODE>context Person inv: ... husband->isEmpty ...</CODE>).
   */
  protected void appendPostfixArrowOp(APostfixExpressionTail pet, Node appliedTo, Type appliedType) {
    String javaType=getJavaType( tree.getNodeType(pet) );
    AFeatureCall fc=(AFeatureCall)pet.getFeatureCall();
    String featureName=fc.getPathName().toString().trim();
    AFeatureCallParameters fcp=(AFeatureCallParameters)fc.getFeatureCallParameters();
    AActualParameterList apl=null;
    if (fcp!=null) {
      apl=(AActualParameterList)fcp.getActualParameterList();
    }

    if (featureName.equals("iterate")) {

      String appliedToVariable;
      if (appliedType instanceof Collection) {
        appliedToVariable=getVariable(appliedTo);
      } else {
        appliedToVariable=convertToCollection(appliedTo);
      }
      AIterateDeclarator decl = (AIterateDeclarator) fcp.getDeclarator();
      String oclIter=decl.getIterator().toString().trim();
      String oclAccum=decl.getAccumulator().toString().trim();
      String javaIter;
      if (oclIter.startsWith(tree.getNameCreator().getPrefix())) {
        javaIter=oclIter;
      } else {
        javaIter=tree.getNameCreator().getUniqueName("Iter");
      }
      String javaEvalName=tree.getNameCreator().getUniqueName("Eval");
      String javaCont=tree.getNameCreator().getUniqueName("Accu");
      String javaIterType=getJavaType(tree.getTypeFor(oclIter, apl.getExpression()));
      String javaContType=getJavaType(tree.getTypeFor(oclAccum, apl.getExpression()));
      varMap.put(oclIter, "Ocl.to"+javaIterType+"("+javaIter+".getValue())");
      varMap.put(oclAccum, "Ocl.to"+javaContType+"("+javaCont+".getValue())");

      decl.getExpression().apply(this); // insert code for accumulator initialization

      appendCode("final OclIterator "+javaIter+"="+getVariable(appliedTo)+".getIterator();\n");
      appendCode("final OclContainer "+javaCont+"=new OclContainer("+getVariable(decl.getExpression())+");\n");
      appendCode("final OclRootEvaluatable "+javaEvalName+"=new OclRootEvaluatable() {\n");
      appendCode("  public OclRoot evaluate() {\n");
      increaseIndent(4);
      apl.apply(this);
      decreaseIndent(4);
      appendCode("    return "+getVariable(apl.getExpression())+";\n");
      appendCode("  }\n");
      appendCode("};\n");
      appendCode("final "+javaType+" "+getVariable(pet)+"=");
      appendCode("Ocl.to"+javaType+"(");
      appendCode(appliedToVariable+".iterate("+javaIter+", "+javaCont+", "+javaEvalName+"));\n");

    } else if (tudresden.ocl.check.TypeChecker.setOfIteratingMethodNames.contains(featureName)) {

      String appliedVariable;
      if (appliedType instanceof Collection) {
        appliedVariable=getVariable(appliedTo);
      } else {
        appliedVariable=convertToCollection(appliedTo);
      }
      AStandardDeclarator decl = (AStandardDeclarator) fcp.getDeclarator();
      String oclIter=decl.getName().toString().trim();
      String javaIter;
      if (oclIter.startsWith(tree.getNameCreator().getPrefix())) {
        javaIter=oclIter;
      } else {
        javaIter=tree.getNameCreator().getUniqueName("Iter");
      }
      String javaEvalName=tree.getNameCreator().getUniqueName("Eval");
      String[] types=getEvaluatableTypes(featureName);
      String javaIterType=getJavaType(tree.getTypeFor(oclIter, apl.getExpression()));
      String javaEvalType=types[0];
      String javaEvalReturn=types[1];
      String javaIterReturn=types[2];
      appendCode("final OclIterator "+javaIter+"="+getVariable(appliedTo)+".getIterator();\n");
      appendCode("final "+javaEvalType+" "+javaEvalName+"=new "+javaEvalType+"() {\n");
      appendCode("  public "+javaEvalReturn+" evaluate() {\n");
      varMap.put(oclIter, "Ocl.to"+javaIterType+"("+javaIter+".getValue())");
      increaseIndent(4);
      super.caseAPostfixExpressionTail(pet);  // recursive decent
      decreaseIndent(4);
      appendCode("    return "+getVariable(apl.getExpression())+";\n");
      appendCode("  }\n");
      appendCode("};\n");
      appendCode("final "+javaType+" "+getVariable(pet)+"=");
      if (javaType!=javaIterReturn) {
        appendCode("Ocl.to"+javaType+"(");
      }
      appendCode(appliedVariable+"."+featureName+"("+javaIter+", "+javaEvalName+")");
      if (javaType!=javaIterReturn) {
        appendCode(")");
      }
      appendCode(";\n");
    } else {
      super.caseAPostfixExpressionTail(pet);  // recursive descent
      String appliedVariable;
      if (appliedType instanceof Collection) {
        appliedVariable=getVariable(appliedTo);
      } else {
        appliedVariable=convertToCollection(appliedTo);
      }
      boolean insertCast=false;
      if (
          featureName.equals("first") || featureName.equals("last") ||
          featureName.equals("sum") || featureName.equals("at") ||
          featureName.equals("including") ) {
        insertCast=true;
      }
      appendCode("final "+javaType+" "+getVariable(pet)+"=");
      if (insertCast) appendCode("Ocl.to"+javaType+"(");
      appendCode(appliedVariable+"."+featureName+"(");
      appendActualParameterList(apl);
      if (insertCast) appendCode(")");
      appendCode(");\n");
    }
  }

  protected String convertToCollection(Node appliedTo) {
    String name=tree.getNameCreator().getUniqueName("Set");
    appendCode( "final OclSet "+name+"=OclSet.getEmptyOclSet();\n" );
    appendCode( name+".setToInclude("+getVariable(appliedTo)+");\n" );
    return name;
  }

  /** @param  featureName one of the iterating method names, excluding iterate
   *
   *  @return an String array containing the types connected to the iterating
   *          method with the given name; the returned array has length 3, with
   *          the name of the evaluatable interface at index 0, the name of the
   *          <CODE>evaluate()</CODE> methods return type at index 1, and the
   *          return type of the iterating method at index 2.
   */
  protected String[] getEvaluatableTypes(String featureName) {
    String[] ret=new String[3];
    if (featureName.equals("collect")) {
      ret[0]="OclRootEvaluatable";
      ret[1]="OclRoot";
      ret[2]="OclCollection";
    } else if (featureName.equals("isUnique")) {
      ret[0]="OclRootEvaluatable";
      ret[1]="OclRoot";
      ret[2]="OclBoolean";
    } else if (featureName.equals("sortedBy")) {
      ret[0]="OclComparableEvaluatable";
      ret[1]="Comparable";
      ret[2]="OclSequence";
    } else if (featureName.equals("exists")) {
      ret[0]="OclBooleanEvaluatable";
      ret[1]="OclBoolean";
      ret[2]="OclBoolean";
    } else if (featureName.equals("forAll")) {
      ret[0]="OclBooleanEvaluatable";
      ret[1]="OclBoolean";
      ret[2]="OclBoolean";
    } else if (featureName.equals("reject")) {
      ret[0]="OclBooleanEvaluatable";
      ret[1]="OclBoolean";
      ret[2]="OclCollection";
    } else if (featureName.equals("select")) {
      ret[0]="OclBooleanEvaluatable";
      ret[1]="OclBoolean";
      ret[2]="OclCollection";
    } else {
      throw new RuntimeException("Illegal iterating method name");
    }
    return ret;
  }

  /** The APostfixExpressionTail has a '.' begin and is not applied to basic type.
   *  Hence it must be a simple feature call on an application object or the shorthand for
   *  collect.
   */
  protected void appendPostfixDotOp(APostfixExpressionTail pet, Node appliedTo, Type appliedType) {
    super.caseAPostfixExpressionTail(pet); // recursive descent
    String appliedVar=getVariable(appliedTo);
    String javaType=getJavaType( tree.getNodeType(pet) );
    String paramVar=null;
    AFeatureCall fc=(AFeatureCall)pet.getFeatureCall();
    if (fc.getFeatureCallParameters()!=null) {
      // access to operation of application type
      AFeatureCallParameters fcp=(AFeatureCallParameters)fc.getFeatureCallParameters();
      AActualParameterList apl=(AActualParameterList)fcp.getActualParameterList();
      if (apl==null) {
        // operation without parameters
        paramVar="null";
      } else {
        // operation with parameters
        paramVar=tree.getNameCreator().getUniqueName("Param");
        java.util.ArrayList paramExpressions=new java.util.ArrayList();
        paramExpressions.add(apl.getExpression());
        Iterator iter=apl.getActualParameterListTail().iterator();
        while (iter.hasNext()) {
          AActualParameterListTail aplt=(AActualParameterListTail) iter.next();
          paramExpressions.add(aplt.getExpression());
        }
        appendCode("Object[] "+paramVar+"=new Object["+paramExpressions.size()+"];\n");
        for (int i=0; i<paramExpressions.size(); i++) {
          // the "null" in the next statement is the point where reconversion information
          // could be added
          appendCode(paramVar+"["+i+"]=Ocl.reconvert("+
            "null, "+getVariable((AExpression)paramExpressions.get(i))+");\n");
        }
      }
    }
    appendCode("final "+javaType+" "+getVariable(pet)+"="+
      "Ocl.to"+javaType+"("+
      appliedVar+".getFeature(\""+
      fc.getPathName().toString().trim()+
      "\""
    );
    if (paramVar!=null) {
      appendCode(", "+paramVar);
    }
    appendCode("));\n");
  }

  /** append the Java code for an AActualParameterList, excluding the
   *  parentheses
   *
   *  @param apl may be null, then nothing is done
   */
  protected void appendActualParameterList(AActualParameterList apl) {
    if (apl!=null) {
      appendCode( getVariable(apl.getExpression()) );
      Iterator iter=apl.getActualParameterListTail().iterator();
      while (iter.hasNext()) {
        AActualParameterListTail aplt=(AActualParameterListTail)iter.next();
        appendCode(
          ", "+
          getVariable(aplt.getExpression())
        );
      }
    }
  }

  public void outALetExpression(ALetExpression le) {
    reachThrough(le, le.getExpression());
    varMap.put(le.getName().toString().trim(), getVariable(le));
  }

  public void outAStringLiteral(AStringLiteral sl) {
    String lit=sl.toString().trim();
    lit=lit.substring(1, lit.length()-1); // remove "'"
    appendCode("final OclString "+getVariable(sl)+"=new OclString(\""+lit+"\");\n");
  }

  public void outARealLiteral(ARealLiteral rl) {
    String lit=rl.toString().trim();
    appendCode("final OclReal "+getVariable(rl)+"=new OclReal("+lit+");\n");
  }

  public void outAIntegerLiteral(AIntegerLiteral il) {
    String lit=il.toString().trim();
    appendCode("final OclInteger "+getVariable(il)+"=new OclInteger("+lit+");\n");
  }

  public void outABooleanLiteral(ABooleanLiteral bl) {
    String jc;
    if (bl.toString().trim().equals("true")) {
      jc="OclBoolean.TRUE";
    } else {
      jc="OclBoolean.FALSE";
    }
    appendCode("final OclBoolean "+getVariable(bl)+"="+jc+";\n");
  }

  public void outAEnumLiteral(AEnumLiteral el) {
    throw new OclException("JavaCodeGenerator can not handle Enumeration type");
  }



  /** @return the node that the APostfixExpressionTail is applied to, which must
   *          be either an PPrimaryExpression or another APostfixExpressionTail
   *          node
   */
  protected Node getPreviousNode(APostfixExpressionTail pet) {
    Node ret;
    APostfixExpression pe=(APostfixExpression)pet.parent();
    ListIterator iter=pe.getPostfixExpressionTail().listIterator();
    while (iter.next()!=pet);
    iter.previous();
    if (iter.hasPrevious()) {
      ret=(APostfixExpressionTail) iter.previous();
    } else {
      ret=pe.getPrimaryExpression();
    }
    return ret;
  }

  protected void addExpressionToCollection(PExpression e, String collVar) {
    appendCode(collVar+".setToInclude("+getVariable(e)+");\n");
  }

  protected String getJavaType(Type t) {
    if (t instanceof Basic) {
      if (t==Basic.INTEGER) return "OclInteger";
      else if (t==Basic.REAL) return "OclReal";
      else if (t==Basic.BOOLEAN) return "OclBoolean";
      else if (t==Basic.STRING) return "OclString";
      else throw new RuntimeException("illegal basic type");
    } else if (t instanceof Collection) {
      Collection c=(Collection) t;
      switch (c.getCollectionKind()) {
        case Collection.SET: return "OclSet";
        case Collection.BAG: return "OclBag";
        case Collection.SEQUENCE: return "OclSequence";
        case Collection.COLLECTION: return "OclCollection";
        default: throw new RuntimeException("illegal collection type");
      }
    } else if (t instanceof OclType) {
      return "OclType";
    } else {
      // application type
      return "OclAnyImpl";
    }
  }


  public void inAAndLogicalOperator(AAndLogicalOperator lo) {
    operatorCode.put( lo, "and" );
  }

  public void inAOrLogicalOperator(AOrLogicalOperator lo) {
    operatorCode.put( lo, "or" );
  }

  public void inAXorLogicalOperator(AXorLogicalOperator lo) {
    operatorCode.put( lo, "xor" );
  }

  public void inAImpliesLogicalOperator(AImpliesLogicalOperator lo) {
    operatorCode.put( lo, "implies" );
  }

  public void inAEqualRelationalOperator(AEqualRelationalOperator ero) {
    operatorCode.put( ero, "isEqualTo" );
  }

  public void inANEqualRelationalOperator(ANEqualRelationalOperator nero) {
    operatorCode.put( nero, "isNotEqualTo" );
  }

  public void inAGtRelationalOperator(AGtRelationalOperator ro) {
    operatorCode.put( ro, "isGreaterThan" );
  }

  public void inALtRelationalOperator(ALtRelationalOperator ro) {
    operatorCode.put( ro, "isLessThan" );
  }

  public void inAGteqRelationalOperator(AGteqRelationalOperator ro) {
    operatorCode.put( ro, "isGreaterEqual" );
  }

  public void inALteqRelationalOperator(ALteqRelationalOperator ro) {
    operatorCode.put( ro, "isLessEqual" );
  }

  public void inAPlusAddOperator(APlusAddOperator pao) {
    operatorCode.put( pao, "add" );
  }

  public void inAMinusAddOperator(AMinusAddOperator mao) {
    operatorCode.put( mao, "subtract" );
  }

  public void inAMultMultiplyOperator(AMultMultiplyOperator mmo) {
    operatorCode.put( mmo, "multiply" );
  }

  public void inADivMultiplyOperator(ADivMultiplyOperator dmo) {
    operatorCode.put( dmo, "divide" );
  }

  public void inAMinusUnaryOperator(AMinusUnaryOperator muo) {
    operatorCode.put( muo, "negative" );
  }

  public void inANotUnaryOperator(ANotUnaryOperator nuo) {
    operatorCode.put( nuo, "not" );
  }
}

class StringStringMap {

  HashMap map=new HashMap();

  public void put(String key, String value) {
    map.put(key, value);
  }

  public String get(String key) {
    if (!map.containsKey(key)) {
      throw new RuntimeException(
        "StringStringMap does not contain key: "+key
      );
    }
    return (String) map.get(key);
  }

  public void clear() {
    map.clear();
  }
}
