/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Interpreter                                                   *
 * Copyright (C) 2002 Nikolai Krambrock (nikk@gmx.de)                *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Construction, University Of Technology Aachen, Germany            *
 * (http://www-lufgi3.informatik.rwth-aachen.de).                    *
 * It was done in co-operation with Software & Design and Managment  *
 * Troisdorf, Germany (http://www.sdm.de)                            *
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
 * this project, please visit the project home page:                 *
 * http://dresden-ocl.sourceforge.net                                * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl.interp.core.intern;

import java.util.*;

import tudresden.ocl.OclTree;

import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.TypeChecker;
import tudresden.ocl.check.types.Basic;
import tudresden.ocl.check.types.Collection;
import tudresden.ocl.check.types.ModelFacade;
import tudresden.ocl.check.types.Type;

import tudresden.ocl.interp.exp.intern.*;

import tudresden.ocl.lib.OclBoolean;
import tudresden.ocl.lib.OclSet;

import tudresden.ocl.parser.analysis.DepthFirstAdapter;
import tudresden.ocl.parser.node.*;


/**
 * OclConstraint represented by the OclTree is
 * fullfilled.<br>
 * 
 * The class has three types of methods:
 * <ul>
 * <li>methods for the tree traversal
 * <li>protected or private helper methods
 * <li>starter methods (currently only getExp)
 * </ul>
 * 
 * Most of the methods for tree traversal are documented by the part of an OCL-
 * Constraint that causes the method call. Look into the grammer of the parser 
 * for better understanding.
 */
public class InterpreterVisitor extends DepthFirstAdapter {

  /** set of all OclAny property names */
  protected static java.util.Set oclAnyOperations;

  static {
    oclAnyOperations = new java.util.HashSet();
    oclAnyOperations.add("oclIsKindOf");
    oclAnyOperations.add("oclIsTypeOf");
    oclAnyOperations.add("oclAsType");
    oclAnyOperations.add("oclInState");
    oclAnyOperations.add("oclIsNew");
  }

  /**
   * varMap is the enviorment for the execution of the expression tree. During
   * execution it contains results for variable names. <br>
   * String->OclRoot
   */
  protected Map varMap = new HashMap();

  /**
   * nodeMap gives a node the expression of the expression tree that belongs
   * to it as a value. The question is which node contains the result for this
   * part of the ocl-constraint.
   * Node->Exp
   */
  protected Map nodeMap = new HashMap();
  protected OclTree tree;
  protected Exp globalResult;
  private ModelFacade modelFacade;
  private String relevantType;

  public InterpreterVisitor(ModelFacade modelFacade) {
    this.modelFacade = modelFacade;
  }

  protected void requireTreeInvariants() {

    // unique variables
    tree.requireInvariant(
          "context AExpression inv : letExpression -> forAll ( le | not self . parent . boundNames -> includes ( le . name ) )  ");
    tree.requireInvariant(
          "context AStandardDeclarator : not parent . parent . boundNames -> includes ( name.toString() )");
    tree.requireInvariant(
          "context AIterateDeclarator : let ppb = parent . parent . boundNames in not ( ppb -> includes ( iterator.toString() ) or ppb -> includes( accumulator.toString() ) )");

    // no multiple iterators
    tree.requireInvariant(
          "context AStandardDeclarator inv : declaratorTail -> isEmpty");

    // explicitly qualified names
    tree.requireInvariant(
          "context ANamePathNameBegin inv : boundNames -> includes ( name )");

    // iterators where possible
    tree.requireInvariant(
          "context AFeatureCall inv : " + 
          "let iteratingMethodNames : Set ( String ) = Set { 'collect', 'exists' , 'forAll' , 'isUnique' , 'reject' , 'select' , 'sortedBy' } in " + 
          "iteratingMethodNames -> includes ( self . pathName . toString ( ) ) implies " + 
          "( self . featureCallParameters . declarator -> size = 1 " + 
          "and self . featureCallParameters . declarator . oclType = AStandardDeclarator )");
    tree.requireInvariant(
          "context ANamePathNameBegin inv : defaultContext <> '' implies boundNames -> includes ( defaultContext )");

    // named constraints
    tree.requireInvariant("context AConstraintBody inv : name -> size = 1");
  }

  // ---------- tree traversal: -----------------------------
  public void outAClassifierContext(AClassifierContext cc) {
    relevantType = cc.getPathTypeName().toString().trim();
  }

  public void outAConstraintBody(AConstraintBody node) {
    if (!"inv".equals(node.getStereotype().toString().trim())) {
      String msg = "The only Sterotype allowed in the interpreter is inv";
      throw new OclTypeException(msg);
    }

    // Get the variable that the current element is saved in, usually it is
    // not given and set to self by default
    String selfVar;
    AConstraint c = (AConstraint)node.parent();
    AContextDeclaration cd = (AContextDeclaration)c.getContextDeclaration();
    AClassifierContextBody ccb = (AClassifierContextBody)cd.getContextBody();
    AClassifierContext cc = (AClassifierContext)ccb.getClassifierContext();
    if (cc.getClassifierHead() == null) {
      selfVar = "self";
    } else {
      selfVar = cc.getClassifierHead().toString().replace(':', ' ').trim();
    }

    globalResult = new ExpBody(relevantType, varMap, 
                               getVar(node.getExpression()), selfVar);
    globalResult.setReturnType(OclBoolean.class);
  }

  /**
   * Going out of an expression we need to consider all let expressions that
   * have been done before.
   */
  public void outAExpression(AExpression e) {
    Exp result = getVar(e.getLogicalExpression());
    Iterator i = e.getLetExpression().iterator();

    if (i.hasNext()) {
      java.util.Collection names = new ArrayList();
      java.util.Collection letExps = new ArrayList();

      while (i.hasNext()) {
        ALetExpression le = (ALetExpression)i.next();
        letExps.add(getVar(le.getExpression()));
        names.add(le.getName().toString().trim());
      }
      result = new ExpLet(varMap, 
                          (Exp[])letExps.toArray(new Exp[letExps.size()]), 
                          (String[])names.toArray(new String[names.size()]), 
                          getVar(e.getLogicalExpression()));
    }
    setVar(e, result);
  }

  /**
   * if a then b else c endif
   * <p>Ocl? result=(a.isTrue()) ? (b) : (c);</p>
   * <p>For each if expression a new variable is generated.</p>
   */
  public void outAIfExpression(AIfExpression ie) {
    Exp result = new ExpIf(getVar(ie.getIfBranch()), getVar(ie.getThenBranch()), 
                           getVar(ie.getElseBranch()));
    setVar(ie, result);
  }

  /**
   * <CODE>and</CODE>, <CODE>or</CODE> and <CODE>xor</CODE> have precedence
   * over <CODE>implies</CODE> (<code>and</code> binds as strongly as
   * <code>or</code>)
   * 
   * <p>a and b or c</p>
   * <p>OclBoolean result = a.and(b).or(c);</p>
   * 
   * <p>A variable is generated for each ALogicalExpression with tail (not for 
   * a ALogicalExpressionTail).</p>
   */
  public void outALogicalExpression(ALogicalExpression le) {
    if (le.getLogicalExpressionTail().isEmpty()) {
      reachThrough(le, le.getRelationalExpression());
    } else {
      LinkedList tail = le.getLogicalExpressionTail();
      int tailSize = tail.size();

      Exp result = null;
      Exp[] relExprs = new Exp[tailSize + 1];
      String[] logOps = new String[tailSize];

      Iterator iter = tail.iterator();
      relExprs[0] = getVar(le.getRelationalExpression());
      for (int i = 0; i < tailSize; i++) {
        ALogicalExpressionTail let = (ALogicalExpressionTail)iter.next();
        logOps[i] = getOperator(let.getLogicalOperator());
        relExprs[i + 1] = getVar(let.getRelationalExpression());
      }

      int lastNonImplies = 0;// index of operand after last implies

      for (int i = 0; i < tailSize; i++) {
        if ("implies".equals(logOps[i])) {
          int from = lastNonImplies;
          int to = i;
          result = buildAndOrExp(result, relExprs, logOps, from, to);
          lastNonImplies = to + 1;
        }
      }

      result = buildAndOrExp(result, relExprs, logOps, lastNonImplies, tailSize);
      setVar(le, result);

    }
  }

  protected Exp buildAndOrExp(Exp oldResult, Exp[] relExprs, String[] logOps, 
                              int from, int to) {
    Exp result = relExprs[from];
    for (int i = from + 1; i <= to; i++) {
      result = buildComposite(result, logOps[i - 1], relExprs[i]);
      result.setReturnType(OclBoolean.class);
    }
    if (oldResult == null) {
      return result;
    } else {
      return buildComposite(oldResult, "implies", result);
    }
  }

  /**
   * a&lt;b = c&gt;d is not allowed by the OCL grammar, therefore we need not
   * care about precedence
   * 
   * <p>a = b<br>
   * OclBoolean result=a.isEqualTo(b);</p>
   * 
   * <p>a > b<br>
   * OclBoolean
   * result=a.isGreaterThan(b);
   * </p>
   */
  public void outARelationalExpression(ARelationalExpression re) {
    if (re.getRelationalExpressionTail() == null) {
      reachThrough(re, re.getAdditiveExpression());
    } else {
      ARelationalExpressionTail ret = (ARelationalExpressionTail)re.getRelationalExpressionTail();
      PRelationalOperator relOp = ret.getRelationalOperator();
      boolean comparison = true;
      if (relOp instanceof AEqualRelationalOperator || 
          relOp instanceof ANEqualRelationalOperator) {
        comparison = false;
      }

      String operator = (String)getOperator(ret.getRelationalOperator());
      Exp var1 = getVar(re.getAdditiveExpression());
      Exp var2 = getVar(ret.getAdditiveExpression());
      Exp var;

      if (comparison) {
        var = buildComposite(var1, operator, var2);
      }// now we have an equals-relation 
      else {
        if (operator.equals("isEqualTo")) {
          var = new ExpEquals(var1, var2, true);
        } else {
          Assert.assertTrue(operator.equals("isNotEqualTo"));
          var = new ExpEquals(var1, var2, false);
        }
      }
      setVar(re, var);
    }
  }

  /**
   * <p>a + b<br>
   * Ocl? result=Ocl.toOcl?(a).add(Ocl.toOcl?(b));</p>
   * 
   * <p>with Ocl? = OclReal | OclInteger | OclSet</p>
   */
  public void outAAdditiveExpression(AAdditiveExpression ae) {
    if (ae.getAdditiveExpressionTail().isEmpty()) {
      reachThrough(ae, ae.getMultiplicativeExpression());
    } else {
      Iterator iter = ae.getAdditiveExpressionTail().iterator();

      Exp result = getVar(ae.getMultiplicativeExpression());

      iter = ae.getAdditiveExpressionTail().iterator();
      while (iter.hasNext()) {
        AAdditiveExpressionTail aet = (AAdditiveExpressionTail)iter.next();
        Exp oclTmp = getVar(aet.getMultiplicativeExpression());
        result = buildComposite(result, getOperator(aet.getAddOperator()), 
                                oclTmp);
      }

      setVar(ae, result);
    }
  }

  /**
   * <p>a * b<br>
   * Ocl? result=Ocl.toOcl?(a).multiply(Ocl.toOcl?(b));</p>
   * 
   * <p>with Ocl? = OclReal | OclInteger</p>
   */
  public void outAMultiplicativeExpression(AMultiplicativeExpression me) {
    if (me.getMultiplicativeExpressionTail().isEmpty()) {
      reachThrough(me, me.getUnaryExpression());
    } else {
      Iterator iter = me.getMultiplicativeExpressionTail().iterator();

      Exp result = getVar(me.getUnaryExpression());

      iter = me.getMultiplicativeExpressionTail().iterator();
      while (iter.hasNext()) {
        AMultiplicativeExpressionTail met = (AMultiplicativeExpressionTail)iter.next();
        Exp oclTmp = getVar(met.getUnaryExpression());
        result = buildComposite(result, getOperator(met.getMultiplyOperator()), 
                                oclTmp);
      }
      setVar(me, result);
    }
  }

  /**
   * not a<br>
   * OclBoolean result=a.not();
   */
  public void outAUnaryUnaryExpression(AUnaryUnaryExpression uue) {
    Exp result = buildComposite(getVar(uue.getPostfixExpression()), 
                                getOperator(uue.getUnaryOperator()), null);

    setVar(uue, result);
  }

  public void outAPostfixUnaryExpression(APostfixUnaryExpression pue) {
    reachThrough(pue, pue.getPostfixExpression());
  }

  public void outAPostfixExpression(APostfixExpression pe) {
    if (pe.getPostfixExpressionTail().isEmpty()) {
      reachThrough(pe, pe.getPrimaryExpression());
    } else {
      APostfixExpressionTail last = (APostfixExpressionTail)pe.getPostfixExpressionTail()
        .getLast();
      reachThrough(pe, last);
    }
  }

  public void outAFeaturePrimaryExpression(AFeaturePrimaryExpression pe) {
    Type oclType = tree.getNodeType(pe);
    String varName = pe.toString().trim();

    /*
    if (varName.indexOf("::") != -1) {
      String msg = "Colon (::) used in a variable name.";
      throw new OclTypeException(msg);
    }
    */
    Exp oclVar = new ExpVar(varMap, varName, 
                            oclType instanceof tudresden.ocl.check.types.OclType, 
                            modelFacade);
    setVar(pe, oclVar);
  }

  /**
   * This method breaks the usual pattern of generating Java code postfix
   * since the collection must be declared and instanciated prior to setting
   * its contents (in <CODE>outAExpressionListOrRange</CODE>).
   */
  public void inALitColPrimaryExpression(ALitColPrimaryExpression lcpe) {
    int colKind = ((Collection)tree.getNodeType(lcpe)).getCollectionKind();
    setVar(lcpe, new ExpCollEmpty(colKind));
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

  /**
   * Filling a collection like:<br>
   *   Set{1,2,4}<br>
   *   or<br>
   *   Set{1..100}<br>
   */
  public void outAExpressionListOrRange(AExpressionListOrRange elor) {
    PExpressionListOrRangeTail tail = elor.getExpressionListOrRangeTail();

    Exp collectionVar = getVar(elor.parent().parent());
    java.util.Collection tmpColl = new ArrayList();
    Exp result;

    if (tail == null) {
      tmpColl.add(getVar(elor.getExpression()));
      result = new ExpCollAdd(collectionVar, tmpColl);
    } else if (tail instanceof AListExpressionListOrRangeTail) {
      tmpColl.add(getVar(elor.getExpression()));

      AListExpressionListOrRangeTail list = (AListExpressionListOrRangeTail)tail;
      Iterator iter = list.getExpressionListTail().iterator();
      while (iter.hasNext()) {
        AExpressionListTail next = (AExpressionListTail)iter.next();
        tmpColl.add(getVar(next.getExpression()));
      }
      result = new ExpCollAdd(collectionVar, tmpColl);
    } else {
      ARangeExpressionListOrRangeTail range = (ARangeExpressionListOrRangeTail)tail;
      result = new ExpCollAdd(collectionVar, getVar(elor.getExpression()), 
                              getVar(range.getExpression()));
    }
    setVar(elor.parent().parent(), result);
  }

  /**
   * PostfixExpression is one of:
   * <ul>
   * <li>basic:                  var.equals
   * <li>arrow for collections:  coll->size
   * <li>dotOp:                  person.age or person.getAge()
   * </ul>
   * the method calls the appropiate handling for the different cases
   */
  public void outAPostfixExpressionTail(APostfixExpressionTail pet) {
    Node appliedTo = getPreviousNode(pet);
    Type appliedType = tree.getNodeType(appliedTo);
    AFeatureCall fc = (AFeatureCall)pet.getFeatureCall();
    String featureName = fc.getPathName().toString().trim();

    if (featureName.indexOf("::") != -1) {
      String msg = "Colon (::) used in a featureName or incorrect detection " + 
                   "an enumeration type.";
      throw new OclTypeException(msg);
    }

    if (pet.getPostfixExpressionTailBegin() instanceof AArrowPostfixExpressionTailBegin) {
      buidPostfixArrowOp(pet, appliedTo, appliedType);
    } else if (appliedType instanceof Basic) {
      buildPostfixBasic(pet, appliedTo, (Basic)appliedType);
    } else if (appliedType instanceof tudresden.ocl.check.types.OclType) {
      buildPostfixBasic(pet, appliedTo, appliedType);
    } else {
      if (oclAnyOperations.contains(featureName)) {

        // property of OclAny, handled like basic types
        buildPostfixBasic(pet, appliedTo, appliedType);
      } else {
        buildPostfixDotOp(pet, appliedTo, appliedType);
      }
    }
  }

  protected void buildPostfixBasic(APostfixExpressionTail pet, Node appliedTo, 
                                   Type appliedType) {
    AFeatureCall fc = (AFeatureCall)pet.getFeatureCall();
    String featureName = fc.getPathName().toString().trim();
    if (featureName.equals("oclIsNew")) {
      throw new OclTypeException("Ocl-Interpreter does not support the OclAny property \"oclIsNew\"");
    }

    Object[] params;

    if (fc.getFeatureCallParameters() == null) {
      params = new Object[0];
    } else {
      AFeatureCallParameters fcp = (AFeatureCallParameters)fc.getFeatureCallParameters();
      AActualParameterList apl = (AActualParameterList)fcp.getActualParameterList();
      params = parameterListAsArray(apl);
    }

    setVar(pet, buildComposite(getVar(appliedTo), featureName, params));
  }

  /**
   * The APostfixExpressionTail begins with '->' and is therefore a collection
   * operation applied either to a collection or to a single, possible
   * undefined element (as in <CODE>context Person inv: ... husband->isEmpty
   * ...</CODE>).
   */
  protected void buidPostfixArrowOp(APostfixExpressionTail pet, Node appliedTo, 
                                    Type appliedType) {
    Exp result;
    Exp appliedToVar;
    AActualParameterList apl;

    AFeatureCall fc = (AFeatureCall)pet.getFeatureCall();
    String featureName = fc.getPathName().toString().trim();
    AFeatureCallParameters fcp = (AFeatureCallParameters)fc.getFeatureCallParameters();

    if (fcp != null) {
      apl = (AActualParameterList)fcp.getActualParameterList();
    } else {
      apl = null;
    }

    if (appliedType instanceof Collection) {
      appliedToVar = getVar(appliedTo);
    } else {
      appliedToVar = convertToCollection(appliedTo);
    }

    if (featureName.equals("iterate")) {
      AIterateDeclarator decl = (AIterateDeclarator)fcp.getDeclarator();
      result = new ExpIterate(appliedToVar, getVar(apl.getExpression()), 
                              getVar(decl.getExpression()), 
                              decl.getIterator().getText().trim(), 
                              decl.getAccumulator().getText().trim(), varMap);

    } else if (TypeChecker.setOfIteratingMethodNames.contains(featureName)) {
      AStandardDeclarator decl = (AStandardDeclarator)fcp.getDeclarator();
      result = new ExpCollOp(featureName, decl.getName().toString().trim(), 
                             appliedToVar, getVar(apl.getExpression()), varMap);

    } else {
      Object[] params = parameterListAsArray(apl);
      result = buildComposite(appliedToVar, featureName, params);
    }
    setVar(pet, result);
  }

  /**
   * The APostfixExpressionTail has a '.' begin and is not applied to basic
   * type. Hence it must be a simple feature call on an application object or
   * the shorthand for collect.
   */
  protected void buildPostfixDotOp(APostfixExpressionTail pet, Node appliedTo, 
                                   Type appliedType) {
    AFeatureCall fc = (AFeatureCall)pet.getFeatureCall();
    String fName = fc.getPathName().toString().trim();
    Exp exp = getVar(appliedTo);
    Assert.assertTrue(exp != null);

    Exp result;

    AFeatureCallParameters afc = (AFeatureCallParameters)fc.getFeatureCallParameters();

    if (afc != null) {
      Exp[] params = parameterListAsArray(
                           (AActualParameterList)afc.getActualParameterList());
      result = new ExpFeatureCall(exp, fName, params);
    } else {
      result = buildComposite(exp, "getFeature", fName);
    }

    setVar(pet, result);
  }

  /**
   * Create a Collection with the value of the node beeing the only element.
   */
  protected Exp convertToCollection(Node appliedTo) {
    Exp varSet = new ExpCollEmpty(Collection.SET);
    varSet.setReturnType(OclSet.class);
    java.util.Collection coll = new ArrayList();
    coll.add(getVar(appliedTo));
    Exp result = new ExpCollAdd(varSet, coll);
    result.setReturnType(OclSet.class);
    return result;
  }

  public void outALetExpression(ALetExpression le) {
    reachThrough(le, le.getExpression());
  }

  public void outAStringLiteral(AStringLiteral sl) {
    String lit = sl.toString().trim();
    lit = lit.substring(1, lit.length() - 1);// remove "'"

    Exp var = new ExpBase(lit);
    setVar(sl, var);
  }

  public void outARealLiteral(ARealLiteral rl) {
    String lit = rl.toString().trim();

    Exp var = new ExpBase(new Double(lit));
    setVar(rl, var);
  }

  public void outAIntegerLiteral(AIntegerLiteral il) {
    String lit = il.toString().trim();

    Exp var = new ExpBase(new Integer(lit));
    setVar(il, var);
  }

  public void outABooleanLiteral(ABooleanLiteral bl) {
    String lit = bl.toString().trim();

    Exp var = new ExpBase(new Boolean(lit));
    setVar(bl, var);
  }

  public void outAEnumLiteral(AEnumLiteral el) {
    throw new OclTypeException("Ocl4Java can not handle Enumeration type");
  }

  // ---------- helper methods: -----------------------------


  /**
   * Creates a ExpComposite
   */
  private static Exp buildComposite(Exp o, String methodName, Object param) {
    return buildComposite(o, methodName, new Object[] {param});
  }

  /**
   * Creates a ExpComposite
   */
  private static Exp buildComposite(Exp o, String methodName, Object[] params) {
    return new ExpComposite(o, methodName, params);
  }

  /**
   * Make an array out of a ParameterList
   */
  protected Exp[] parameterListAsArray(AActualParameterList apl) {
    List params = new ArrayList();

    if (apl != null) {
      params.add(getVar(apl.getExpression()));
      Iterator iter = apl.getActualParameterListTail().iterator();
      while (iter.hasNext()) {
        AActualParameterListTail aplt = (AActualParameterListTail)iter.next();
        params.add(getVar(aplt.getExpression()));
      }
    }

    return (Exp[])params.toArray(new Exp[params.size()]);
  }

  /**
   * @return the node that the APostfixExpressionTail is applied to, which
   *         must be either an PPrimaryExpression or another
   *         APostfixExpressionTail node
   */
  protected Node getPreviousNode(APostfixExpressionTail pet) {
    Node ret;
    APostfixExpression pe = (APostfixExpression)pet.parent();
    ListIterator iter = pe.getPostfixExpressionTail().listIterator();
    while (iter.next() != pet){}
    iter.previous();
    if (iter.hasPrevious()) {
      ret = (APostfixExpressionTail)iter.previous();
    } else {
      ret = pe.getPrimaryExpression();
    }
    return ret;
  }

  /**
   * Get the Name of an operator form the node that represents it. This is the
   * name of the method to call. (e.g. The one in front of  the operator is
   * called with the name of the operation and the  second object as a
   * parameter)
   */
  protected static String getOperator(Node node) {
    if (node instanceof AAndLogicalOperator) {
      return "and";
    } else if (node instanceof AOrLogicalOperator) {
      return "or";
    } else if (node instanceof AXorLogicalOperator) {
      return "xor";
    } else if (node instanceof AImpliesLogicalOperator) {
      return "implies";
    } else if (node instanceof AEqualRelationalOperator) {
      return "isEqualTo";
    } else if (node instanceof ANEqualRelationalOperator) {
      return "isNotEqualTo";
    } else if (node instanceof AGtRelationalOperator) {
      return "isGreaterThan";
    } else if (node instanceof ALtRelationalOperator) {
      return "isLessThan";
    } else if (node instanceof AGteqRelationalOperator) {
      return "isGreaterEqual";
    } else if (node instanceof ALteqRelationalOperator) {
      return "isLessEqual";
    } else if (node instanceof APlusAddOperator) {
      return "add";
    } else if (node instanceof AMinusAddOperator) {
      return "subtract";
    } else if (node instanceof AMultMultiplyOperator) {
      return "multiply";
    } else if (node instanceof ADivMultiplyOperator) {
      return "divide";
    } else if (node instanceof AMinusUnaryOperator) {
      return "negative";
    } else {
      Assert.assertTrue(node instanceof ANotUnaryOperator);
      return "not";
    }
  }

  /**
   * Give the from node the value of the to node.
   * 
   * @see #getVar(Node)
   * @see #setVar(Node, Exp)
   */
  protected void reachThrough(Node from, Node to) {
    setVar(from, getVar(to));
  }

  /**
   * Get the value of the node as an expression.
   * 
   * <p>This is done to get expressions we need in order to create new
   * expressions.</p>
   * 
   * <p>E.g. we need the if-brance-expression and the else-brance-expression to
   * build an ExpIf.</p>
   * 
   * @param n must be set with setVar before requesting it
   */
  protected Exp getVar(Node n) {
    Assert.assertTrue(nodeMap.containsKey(n));
    return (Exp)nodeMap.get(n);
  }

  /**
   * Assesioate a Node n with an Expression exp.
   * 
   * <p>This is done at the end of every "out"-method to save the resulting
   * expression to the current node.</p>
   */
  protected void setVar(Node n, Exp exp) {
    if (exp.getReturnType() == null) {
      exp.setReturnType(tree.getNodeType(n));
    }
    nodeMap.put(n, exp);
  }

  /**
   * Startup method from outside. Uses this vistor on the given tree. Before 
   * that some preconditions on the tree are checked.
   * 
   * @return the tree to evaluate the OCL-Expression given by tree
   */
  public Exp getExp(OclTree tree) {
    this.tree = tree;
    requireTreeInvariants();
    tree.apply(this);
    return globalResult;
  }
}