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
package tudresden.ocl.check;

import tudresden.ocl.parser.analysis.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.check.types.*;
import tudresden.ocl.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class TypeChecker extends DepthFirstAdapter implements NameBoundQueryable, TypeQueryable {

  NodeTypeMap ntm;
  TypeFactory types;

  NodeEnvironmentMap environs;

  /* maps Nodes to Strings denoting the defaultContext (e.g. "self") for the Node */
  HashMap defaultContexts;

  OclTree tree;

  /** contains the name of all OCL collection operations that change the
   *  default context
   */
  public static java.util.HashSet setOfIteratingMethodNames;

  static {
    setOfIteratingMethodNames=new java.util.HashSet();
    setOfIteratingMethodNames.add("select");
    setOfIteratingMethodNames.add("collect");
    setOfIteratingMethodNames.add("reject");
    setOfIteratingMethodNames.add("forAll");
    setOfIteratingMethodNames.add("exists");
    setOfIteratingMethodNames.add("isUnique");
    setOfIteratingMethodNames.add("sortedBy");
  }

  public TypeChecker(OclTree tree, TypeFactory tf) {
    types=tf;
    this.tree=tree;
  }

  public void inStart(Start s) {
    ntm=new NodeTypeMap();
    defaultContexts=new HashMap();
    environs=new NodeEnvironmentMap();
  }

  public void inAConstraint(AConstraint c) {
    TypeEnvironment env=getEnvironmentCopy(c);
    PContextBody cb=((AContextDeclaration) c.getContextDeclaration()).getContextBody();
    String constrainedTypeName;
    Type constrainedType;
    if (cb instanceof AClassifierContextBody) {
      constrainedTypeName=
        ((AClassifierContext)((AClassifierContextBody)cb).getClassifierContext())
        .getPathTypeName().toString().trim();
      constrainedType=types.get(constrainedTypeName);
    } else {
      AOperationContext oc=((AOperationContext)((AOperationContextBody)cb).getOperationContext());
      constrainedTypeName=oc.getPathTypeName().toString().trim();
      String constrainedOperationName=oc.getName().toString().trim();
      LinkedList formalParams=new LinkedList();
      Type[] operationParameters;
      if (oc.getFormalParameterList()!=null) {
        AFormalParameterList fpl=(AFormalParameterList)oc.getFormalParameterList();
        formalParams.add( fpl.getFormalParameter() );
        Iterator iter=fpl.getFormalParameterListTail().iterator();
        int numberOfParameters=1;
        while (iter.hasNext()) {
          formalParams.add( ((AFormalParameterListTail)iter.next()).getFormalParameter() );
          numberOfParameters++;
        }
        operationParameters=new Type[numberOfParameters];
        iter=formalParams.iterator();
        int index=0;
        while (iter.hasNext()) {
          AFormalParameter nextParam=(AFormalParameter)iter.next();
          String paramName=nextParam.getName().toString().trim();
          String paramTypeName=nextParam.getPathTypeName().toString().trim();
          Type paramType=types.get(paramTypeName);
          operationParameters[index]=paramType;
          env.put(paramName, paramType);
					index++;
        }
      } else {
        operationParameters=new Type[0];
      }
      constrainedType=types.get(constrainedTypeName);
      Type constrainedOperationType=constrainedType.navigateParameterized(
        constrainedOperationName,
        operationParameters
      );
      if (oc.getReturnTypeDeclaration()!=null) {
        AReturnTypeDeclaration rtd=(AReturnTypeDeclaration)oc.getReturnTypeDeclaration();
        String returnTypeName=rtd.getPathTypeName().toString().trim();
        Type returnType=types.get(returnTypeName);
        types.assertTrue( returnType, constrainedOperationType, c);
      }
      env.put("result", constrainedOperationType);
    }
    env.put("self", constrainedType);
    environs.put(c, env);
    defaultContexts.put(c, "self");
  }

  public void outAConstraintBody(AConstraintBody cb) {
    types.assertTrue( ntm.get(cb.getExpression()), types.getBoolean(), cb.getExpression() );
  }

  public void caseAExpression(AExpression e) {
    LinkedList lets=e.getLetExpression();
    if (! lets.isEmpty() ) {
      Iterator iter=lets.iterator();
      Iterator iter2=lets.iterator();
      iter2.next();
      while (iter.hasNext()) {
        ALetExpression nextLet=(ALetExpression)iter.next();
        nextLet.apply(this);
        Node update; // the node whose environment is changed
        if (iter2.hasNext()) {
          update=(ALetExpression)iter2.next();
        } else {
          update=((AExpression)nextLet.parent()).getLogicalExpression();
        }
        TypeEnvironment updateEnv=getEnvironmentCopy(update);
        nextLet.getExpression().apply(this);
        String name= nextLet.getName().toString().trim();
        Type exprType=ntm.get(nextLet.getExpression());
        Type formalType;
        if (nextLet.getLetExpressionTypeDeclaration()!=null) {
          ALetExpressionTypeDeclaration letd=(ALetExpressionTypeDeclaration)
            nextLet.getLetExpressionTypeDeclaration();
          formalType=(
            (OclType) ntm.get( letd.getPathTypeName() )
          ).getType();
          types.assertTrue( exprType, formalType, nextLet );
        } else {
          formalType=exprType;
        }
        updateEnv.put(
          name,
          formalType
        );
        environs.put(update, updateEnv);
      }
    }
    e.getLogicalExpression().apply(this);
    ntm.put( e, ntm.get(e.getLogicalExpression()) );
  }

  public void outAIfExpression(AIfExpression ie) {
    Type typeIf=ntm.get( ie.getIfBranch() );
    Type typeThen=ntm.get( ie.getThenBranch() );
    Type typeElse=ntm.get( ie.getElseBranch() );
    types.assertTrue( typeIf, types.getBoolean(), ie );
    if (types.conforms(typeThen, typeElse)) {
      ntm.put( ie, typeElse );
    } else if (types.conforms(typeElse, typeThen)) {
      ntm.put( ie, typeThen );
    } else {
      throw new OclTypeException("non-conformant then- and else-branches in "+ie);
    }
  }

  public void outALogicalExpression(ALogicalExpression le) {
    if (! le.getLogicalExpressionTail().isEmpty() ) {
      // all logical operators require two Boolean operands and result in a Boolean
      types.assertTrue(ntm.get( le.getRelationalExpression() ), types.getBoolean(), le);
      Iterator iter=le.getLogicalExpressionTail().iterator();
      while (iter.hasNext()) {
        ALogicalExpressionTail logTail=(ALogicalExpressionTail)iter.next();
        types.assertTrue(ntm.get( logTail.getRelationalExpression() ), types.getBoolean(), logTail);
      }
      ntm.put(le, types.getBoolean());
    } else {
      ntm.put(le, ntm.get(le.getRelationalExpression()) );
    }
  }

  public void outARelationalExpression(ARelationalExpression re) {
    if (re.getRelationalExpressionTail()==null) {
      ntm.put( re, ntm.get(re.getAdditiveExpression()) );
    } else {
      AAdditiveExpression leftOperand =  (AAdditiveExpression)re.getAdditiveExpression();
      AAdditiveExpression rightOperand = (AAdditiveExpression)
        ((ARelationalExpressionTail)re.getRelationalExpressionTail()).getAdditiveExpression();
      PRelationalOperator operator=
        ((ARelationalExpressionTail)re.getRelationalExpressionTail())
        .getRelationalOperator();
      if (operator instanceof AEqualRelationalOperator ||
          operator instanceof ANEqualRelationalOperator) {
        if ( ntm.get(leftOperand) instanceof Collection !=
             ntm.get(rightOperand) instanceof Collection) {
          throw new OclTypeException(
            "the operators \"=\" and \"<>\" can not be used to compare elements "+
            "and collections, as in \""+re.toString()+"\""
          );
        }
        ntm.put( re, types.getBoolean() );
      } else if (operator instanceof AGtRelationalOperator ||
                 operator instanceof ALtRelationalOperator ||
                 operator instanceof AGteqRelationalOperator ||
                 operator instanceof ALteqRelationalOperator) {
        types.assertTrue( ntm.get(leftOperand), types.getReal(), re);
        types.assertTrue( ntm.get(rightOperand), types.getReal(), re);
        ntm.put( re, types.getBoolean() );
      }
    }
  }

  /** case-method overwritten since type of first operand required for
   *  analysis ( '-' is also defined for Sets)
   */
  public void caseAAdditiveExpression(AAdditiveExpression ae) {
    ae.getMultiplicativeExpression().apply(this);
    LinkedList tail=ae.getAdditiveExpressionTail();
    Type firstOperandType=ntm.get( ae.getMultiplicativeExpression() );
    if (tail.isEmpty()) {
      ntm.put( ae, firstOperandType );
    } else {
      boolean firstOperandIsSet=types.conforms (firstOperandType, types.getSet());
      boolean allOperandsAreInteger=types.conforms (firstOperandType, types.getInteger());
      if ( (! firstOperandIsSet) && (! allOperandsAreInteger) ) {
        types.assertTrue( firstOperandType, types.getReal(), ae );
      }
      Iterator iter=tail.iterator();
      while (iter.hasNext()) {
        AAdditiveExpressionTail next=(AAdditiveExpressionTail) iter.next();
        AMultiplicativeExpression nextOperand=
          (AMultiplicativeExpression) next.getMultiplicativeExpression();
        if ( firstOperandIsSet ) {
          // '-' operator on Sets
          if (next.getAddOperator() instanceof APlusAddOperator) {
            types.assertTrue( firstOperandType, types.getReal(), ae ); // this will fail
          }
          nextOperand.apply(this); // type-check subexpression
          // next line will fail in e.g. "Set(Circle)-Set(Shape)"
          types.assertTrue( ntm.get(nextOperand) , firstOperandType, ae );
        } else {
          // '-' or '+' on Real / Integer
          nextOperand.apply(this); // type-check subexpression
          Type nextOperandType=ntm.get(nextOperand);
          types.assertTrue( nextOperandType, types.getReal(), ae);
          if (allOperandsAreInteger) {
            allOperandsAreInteger=types.conforms(nextOperandType, types.getInteger());
          }
        }
      } // end while
      if (allOperandsAreInteger) {
        ntm.put( ae, types.getInteger() );
      } else if (firstOperandIsSet) {
        ntm.put( ae, firstOperandType ); // might be some special set type
      } else {
        ntm.put( ae, types.getReal() );
      }
    } // end if(tail.isEmpty())
  }

  public void outAMultiplicativeExpression(AMultiplicativeExpression me) {
    LinkedList tail=me.getMultiplicativeExpressionTail();
    if (tail.isEmpty()) {
      ntm.put( me, ntm.get(me.getUnaryExpression()) );
    } else {
      // '*' or '/' on Real, resulting type is Real or Integer
      types.assertTrue( ntm.get(me.getUnaryExpression()), types.getReal(), me );
      boolean resultIsInteger=types.conforms(
        ntm.get(me.getUnaryExpression()),
        types.getInteger()
      );
      Iterator iter=tail.iterator();
      while(iter.hasNext()) {
        AMultiplicativeExpressionTail next=(AMultiplicativeExpressionTail)iter.next();
        types.assertTrue( ntm.get(next.getUnaryExpression()), types.getReal(), next );
        if (resultIsInteger) {
          resultIsInteger=
            next.getMultiplyOperator() instanceof AMultMultiplyOperator &&
            types.conforms( ntm.get(next.getUnaryExpression()), types.getReal() );
        }
      }
      if (resultIsInteger) {
        ntm.put( me, types.getInteger() );
      } else {
        ntm.put( me, types.getReal() );
      }
    }
  }

  public void outAPostfixUnaryExpression(APostfixUnaryExpression ue) {
    ntm.put( ue, ntm.get(ue.getPostfixExpression()) );
  }

  public void outAUnaryUnaryExpression(AUnaryUnaryExpression ue) {
    PUnaryOperator uop=ue.getUnaryOperator();
    Type operandType=ntm.get( ue.getPostfixExpression() );
    if (uop instanceof AMinusUnaryOperator) {
      types.assertTrue( operandType, types.getReal(), ue );
    } else {
      // uop instanceof ANotUnaryOperator
      types.assertTrue( operandType, types.getBoolean(), ue );
    }
    ntm.put(ue, operandType);
  }

  public void caseAPostfixExpression(APostfixExpression poex) {
    PPrimaryExpression prex=poex.getPrimaryExpression();
    prex.apply(this);
    Type prexType=ntm.get(prex);
    Type navigation=prexType;
    Iterator iter=poex.getPostfixExpressionTail().iterator();
    while (iter.hasNext()) {
      APostfixExpressionTail next=(APostfixExpressionTail) iter.next();
      PPostfixExpressionTailBegin begin=next.getPostfixExpressionTailBegin();
      AFeatureCall fcall=(AFeatureCall)next.getFeatureCall();
      String pathName=fcall.getPathName().toString().trim();
      boolean isStateMachineAccess= (
          pathName.equals("oclInState") &&
          (begin instanceof ADotPostfixExpressionTailBegin) &&
          ! (navigation instanceof Collection) &&
          fcall.getFeatureCallParameters()!=null &&
          ((AFeatureCallParameters)fcall.getFeatureCallParameters()).getActualParameterList()!=null &&
          ((AActualParameterList)
            ((AFeatureCallParameters)fcall.getFeatureCallParameters()).getActualParameterList()
          ).getActualParameterListTail().isEmpty()
      );
      if (! isStateMachineAccess) {
        fcall.apply(this);
      }
      if (fcall.getFeatureCallParameters()==null) {
        if (types.conforms(navigation, types.getCollection()) &&
            begin instanceof ADotPostfixExpressionTailBegin) {
          // shorthand for collect
          Collection oldColl=(Collection)navigation;
          assertElementType(oldColl, "postfix expression \""+poex.toString()+"\"");
          navigation=navigateCollect(
            oldColl,
            oldColl.getElementType().navigateQualified(pathName, getQualifierTypes(fcall.getQualifiers()))
          );
        } else if (!types.conforms(navigation, types.getCollection()) &&
                   begin instanceof AArrowPostfixExpressionTailBegin) {
          // to-one-assoc. accessed as collection
          navigation=new Collection(Collection.SET, navigation).navigateQualified(
            pathName,
            getQualifierTypes(fcall.getQualifiers())
          );
        } else {
          // perfectly normal navigation
          navigation=navigation.navigateQualified(pathName, getQualifierTypes(fcall.getQualifiers()));
        }
      } else {
        AFeatureCallParameters fcp=(AFeatureCallParameters)fcall.getFeatureCallParameters();
        if (fcp.getDeclarator()!=null) fcp.getDeclarator().apply(this);
        AActualParameterList apl=(AActualParameterList)fcp.getActualParameterList();
        ArrayList paramTypes=new ArrayList();
        if (apl!=null && ! isStateMachineAccess) {
          addParamTypesToList(apl, paramTypes, true);
        }
        if (begin instanceof AArrowPostfixExpressionTailBegin) {
          if (!types.conforms(navigation, types.getCollection())) {
            // to-one-assoc. accessed as collection
            navigation=new Collection(Collection.SET, navigation);
          }
          if (pathName.equals("collect")) {
            types.assertTrue(navigation, types.getCollection(), poex);
            navigation=navigation.navigateParameterized(
              pathName,
              (Type[]) paramTypes.toArray( new Type[paramTypes.size()] )
            ); // ex navigateCollect
          } else if (pathName.equals("iterate")) {
            navigation=ntm.get(fcall);
          } else {
            navigation = (navigation instanceof Type2)?
                         (((Type2) navigation).navigateParameterizedQuery (
                             pathName,
                             (Type[]) paramTypes.toArray (new Type[paramTypes.size()])
                           )
                         ):
                         (navigation.navigateParameterized (
                             pathName,
                             (Type[]) paramTypes.toArray (new Type[paramTypes.size()])
                           )
                         );
          }
        } else {
          // begin instanceof ADotPostfixExpressionTailBegin
          if ( types.conforms(navigation, types.getCollection()) ) {
            // shorthand for collect
            Collection oldColl=(Collection)navigation;
            assertElementType(oldColl, "postfix expression \""+poex.toString()+"\"");
            navigation=navigateCollect(
              oldColl,
              oldColl.getElementType().navigateParameterized(
                pathName,
                (Type[]) paramTypes.toArray( new Type[paramTypes.size()] )
              )
            );
          } else {
            if (isStateMachineAccess) {
              // state machine access
              OnlyNameFinder onf=new OnlyNameFinder(true);
              apl.getExpression().apply( onf );
              String stateName=onf.getPathName().toString().trim();
              if ( navigation.hasState(stateName) ) {
                ntm.put(apl.getExpression(), new OclState());
                navigation=Basic.BOOLEAN;
              } else {
                throw new OclTypeException(""+navigation+" has no state \""+stateName+"\"");
              }
            } else {
              // perfectly normal navigation
              navigation = (navigation instanceof Type2)?
                           (((Type2) navigation).navigateParameterizedQuery (
                               pathName,
                               (Type[]) paramTypes.toArray (new Type[paramTypes.size()])
                             )
                           ):
                           (navigation.navigateParameterized (
                               pathName,
                               (Type[]) paramTypes.toArray (new Type[paramTypes.size()])
                             )
                           );
            }
          }
        }
      }
      ntm.put(next, navigation);
    }
    ntm.put(poex, navigation);
  }


  protected Collection navigateCollect(Collection oldCollection, Type paramType) {
    int newColKind=oldCollection.getCollectionKind();
    if (newColKind==Collection.SET) newColKind=Collection.BAG;
    if (paramType instanceof Collection) paramType=((Collection)paramType).getElementType();
    return new Collection(newColKind, paramType);
  }

  /** This method is implemented to update the default context for the
   *  FeatureCall's FeatureCallParameter node if the feature call is a
   *  call to an iterating method.
   */
  public void inAFeatureCall(AFeatureCall fc) {
    APostfixExpressionTail pet=(APostfixExpressionTail)fc.parent();
    String pathName=fc.getPathName().toString().trim();
    if (
      pet.getPostfixExpressionTailBegin() instanceof AArrowPostfixExpressionTailBegin &&
      (setOfIteratingMethodNames.contains(pathName) || pathName.equals("iterate"))
    ) {
      inIteratingFeatureCall(fc);
    }
  }

  protected void inIteratingFeatureCall(AFeatureCall fc) {
    ArrayList iteratorNames=new ArrayList();
    TypeEnvironment env=getEnvironmentCopy(fc.getFeatureCallParameters());
    Type iteratorType=null;
    if ( setOfIteratingMethodNames.contains( fc.getPathName().toString().trim() ) ) {
      String iteratorName;
      AFeatureCallParameters afcp=(AFeatureCallParameters)fc.getFeatureCallParameters();
      if ( afcp==null ) {
        throw new tudresden.ocl.parser.OclParserException(
          "iterating method without FeatureCallParameters: "+fc.toString()
        );
      }
      if (afcp.getDeclarator() != null) {
        if (afcp.getDeclarator() instanceof AStandardDeclarator) {
          AStandardDeclarator asd=(AStandardDeclarator) afcp.getDeclarator();
          if (asd.getDeclaratorTypeDeclaration()!=null) {
            // the application in the next line is unnecessarily done twice, since
            // the call is in a "in" method
            asd.getDeclaratorTypeDeclaration().apply(this);
            iteratorType=((OclType)ntm.get(
              ((ADeclaratorTypeDeclaration)asd.getDeclaratorTypeDeclaration())
                .getSimpleTypeSpecifier()
            )).getType();
          }
          if (asd.getDeclaratorTail()==null || asd.getDeclaratorTail().isEmpty()) {
            // single iterator variable
            iteratorName=asd.getName().toString().trim();
            iteratorNames.add(iteratorName);
          } else {
            // multiple iterators -> no default context
            iteratorName=null;
            addIteratorNamesToList(asd, iteratorNames);
          }
        } else {
          throw new tudresden.ocl.parser.OclParserException(
            "iterating method with wrong Declarator type: "+fc.toString()
          );
        }
      } else {
        // no declarator -> default context has no name -> choose name for implicit iterator
        // (default context is the non-existent iterator variable)
        iteratorName=tree.getNameCreator().getUniqueName("ImplicitIterator");
        iteratorNames.add(iteratorName);
      }
      defaultContexts.put( fc.getFeatureCallParameters(), iteratorName );
    } else if ( fc.getPathName().toString().trim().equals("iterate") ) {
      AFeatureCallParameters afcp=(AFeatureCallParameters)fc.getFeatureCallParameters();
      if (afcp.getDeclarator() instanceof AIterateDeclarator) {
        AIterateDeclarator decl=(AIterateDeclarator) afcp.getDeclarator();
        String iterator=decl.getIterator().toString().trim();
        // uncomment the following line if you think that the default context
        // in "iterate" is the iterator
        // iteratorName=iterator;
        iteratorNames.add(iterator);
        String accumulator=decl.getAccumulator().toString().trim();
        // the applications in the next lines are unnecessarily done twice, since
        // the call is in a "in" method
        decl.getIterType().apply(this);
        decl.getAccType().apply(this);
        decl.getExpression().apply(this);
        iteratorType=((OclType)ntm.get(
          ((ADeclaratorTypeDeclaration)decl.getIterType()).getSimpleTypeSpecifier()
        )).getType();
        Type accType=((OclType)ntm.get(
          ((ADeclaratorTypeDeclaration)decl.getAccType()).getSimpleTypeSpecifier()
        )).getType();
        env.put(accumulator, accType);
        ntm.put(fc, accType);
      } else {
        throw new tudresden.ocl.parser.OclParserException(
            "call to \"iterate\" with wrong Declarator type: "+fc.toString()
        );
      }
    }
    Type toppee=computeTypeOfPreviousPostfixExpressionElement(fc);

    types.assertTrue( toppee, types.getCollection(), fc.parent() );
    Collection coll=(Collection) toppee;
    if (iteratorType==null) {
      iteratorType=coll.getElementType();
    } else {
      // is a iterator type is declared and the collection's elements type is
      // known (depends on implementation of ModelFacade), it should be the
      // collections element type
      if (coll.getElementType()!=null) {
        if ( ! iteratorType.equals(coll.getElementType()) ) {
          throw new OclTypeException(
            "iterator type ("+iteratorType+
            ") does not match collection element type ("+
            coll.getElementType()+")"
          );
        }
      }
    }
    if (iteratorType==null) {
      throw new OclTypeException(
        "cannot find out iterator type in feature call \""+fc.toString()+"\""+
        "(collection element type not set)"
      );
    }

    Iterator iterators=iteratorNames.iterator();
    while (iterators.hasNext()) {
      env.put( (String)iterators.next(), iteratorType );
    }
    environs.put( fc.getFeatureCallParameters(), env );

  }

  protected Type computeTypeOfPreviousPostfixExpressionElement(AFeatureCall fc) {
    APostfixExpressionTail pet=(APostfixExpressionTail) fc.parent();
    APostfixExpression pe=(APostfixExpression) pet.parent();
    Type typeOfPreviousPEElement;
    if (pe.getPostfixExpressionTail().getFirst()==pet) {
      typeOfPreviousPEElement=ntm.get( pe.getPrimaryExpression() );
    } else {
      ListIterator lIter=pe.getPostfixExpressionTail().listIterator();
      Object prevTailElem=null;
      while (true) {
        Object next=lIter.next();
        if (next==pet) break; else prevTailElem=next;
      }
      APostfixExpressionTail prev = (APostfixExpressionTail) prevTailElem;
      typeOfPreviousPEElement=ntm.get( prev );
    }
    return typeOfPreviousPEElement;
  }

  protected Type[] getQualifierTypes(PQualifiers qual) {
    if (qual==null) {
      return null;
    } else {
      AActualParameterList apl=(AActualParameterList)
          ((AQualifiers) qual).getActualParameterList();
      LinkedList tail=apl.getActualParameterListTail();
      int size=1+tail.size();
      Type[] result=new Type[size];
      result[0]=ntm.get(apl.getExpression());
      int i=1;
      Iterator iter=tail.iterator();
      while (iter.hasNext()) {
        AActualParameterListTail next=(AActualParameterListTail)iter.next();
        result[i]=ntm.get(next.getExpression());
        i++;
      }
      return result;
    }
  }

  protected void addIteratorNamesToList(AStandardDeclarator asd, ArrayList names) {
    names.add(asd.getName().toString().trim());
    Iterator iter=asd.getDeclaratorTail().iterator();
    while (iter.hasNext()) {
      ADeclaratorTail dt=(ADeclaratorTail) iter.next();
      names.add(dt.getName().toString().trim());
    }
  }

  public void outALitColPrimaryExpression(ALitColPrimaryExpression lce) {
    ntm.put( lce, ntm.get(lce.getLiteralCollection()) );
  }

  public void outALiteralPrimaryExpression(ALiteralPrimaryExpression lpe) {
    ntm.put( lpe, ntm.get(lpe.getLiteral()) );
  }

  public void caseAFeaturePrimaryExpression(AFeaturePrimaryExpression fpe) {
    boolean isStateMachineAccess= (
      fpe.getPathName().toString().trim().equals("oclInState") &&
      fpe.getFeatureCallParameters()!=null &&
      ((AFeatureCallParameters)fpe.getFeatureCallParameters()).getActualParameterList()!=null &&
      ((AActualParameterList)
        ((AFeatureCallParameters)fpe.getFeatureCallParameters()).getActualParameterList()
      ).getActualParameterListTail().isEmpty()
    );
    if ( ! isStateMachineAccess ) {
      super.caseAFeaturePrimaryExpression(fpe); // recursive descent
    }
    Type pnType=getTypeOfPathName( (APathName)fpe.getPathName(), fpe);
    if ( pnType!=null ) {
      ntm.put( fpe, pnType );
    } else {
      String text=fpe.getPathName().toString().trim();
      if (isNameBound(text, fpe) && fpe.getFeatureCallParameters()==null) {
        // bound name
        TypeEnvironment tenv=getEnvironmentFor(fpe);
        ntm.put( fpe, tenv.get(text) );
      } else {
        // navigation from standard context
        Type start=getEnvironmentFor(fpe).get( getDefaultContext(fpe) );
        if (fpe.getFeatureCallParameters()==null) {
          if (start==null) {
            throw new OclTypeException(
              "names must be qualified explicitly in postfix expression \""+fpe.parent().toString()+"\", but \""+text+"\" is no bound name"
            );
          }
          ntm.put( fpe, start.navigateQualified(text, getQualifierTypes(fpe.getQualifiers())) );
        } else {
          AFeatureCallParameters params=(AFeatureCallParameters) fpe.getFeatureCallParameters();
          ArrayList list=new ArrayList();
          if (params.getActualParameterList()!=null && ! isStateMachineAccess) {
            addParamTypesToList( (AActualParameterList)params.getActualParameterList(), list, false );
          }
          if (isStateMachineAccess) {
            // state machine access
            PExpression stateExpr=((AActualParameterList)params.getActualParameterList()).getExpression();
            OnlyNameFinder onf=new OnlyNameFinder(true);
            stateExpr.apply( onf );
            String stateName=onf.getPathName().toString().trim();
            if ( start.hasState(stateName) ) {
              ntm.put(
                stateExpr, new OclState()
              );
              ntm.put( fpe, Basic.BOOLEAN );
            } else {
              throw new OclTypeException(""+start+" has no state \""+stateName+"\"");
            }
          } else {
            ntm.put (
                fpe,
                (start instanceof Type2)?
                (((Type2) start).navigateParameterizedQuery (
                    text,
                    (Type[]) list.toArray (new Type[list.size()])
                  )
                ):
                (start.navigateParameterized (
                    text,
                    (Type[]) list.toArray (new Type[list.size()])
                  )
                )
              );
          }
        }
      }
    }
  }

  /** @return the result type of the path name if the path name contains a type
   *          name; otherwise, return null
   */
  protected Type getTypeOfPathName(APathName pn, AFeaturePrimaryExpression fpe) {
    LinkedList tail=pn.getPathNameTail();
    PPathNameBegin begin=pn.getPathNameBegin();
    if ( tail.isEmpty() ) {
      if (begin instanceof ATypeNamePathNameBegin) {
        return ntm.get( begin );
      } else {
        return null;
      }
    } else {
      PPathNameEnd lastTailElem=((APathNameTail)tail.getLast()).getPathNameEnd();
      if (lastTailElem instanceof ATypeNamePathNameEnd) {
        // simple package and type name
        Node pathName = pn;
        return new OclType(
          types.get( getFullPath(pn) )
        );
      } else {
        // lastTailElem instanceof ANamePathNameEnd
        // access to static member
        String complete=pn.toString().trim();
        int divide=complete.lastIndexOf("::");
        String typeName=complete.substring(0, divide).trim();
        String memberName=complete.substring(divide+2).trim();
				
        Type type=types.get(typeName);
        Type result;
        if (fpe.getFeatureCallParameters()==null) {
          result=type.navigateQualified(memberName, getQualifierTypes(fpe.getQualifiers()));
        } else {
          AFeatureCallParameters params=(AFeatureCallParameters) fpe.getFeatureCallParameters();
          ArrayList list=new ArrayList();
          if (params.getActualParameterList()!=null) {
            addParamTypesToList( (AActualParameterList)params.getActualParameterList(), list, false );
          }
          result = (type instanceof Type2)?
                   (((Type2) type).navigateParameterizedQuery (
                       memberName,
                       (Type[]) list.toArray (new Type[list.size()])
                     )
                   ):
                   (type.navigateParameterized (
                       memberName,
                       (Type[]) list.toArray (new Type[list.size()])
                     )
                   );
        }
        return result;
      }
    }
  }

  public void outAParenthesesPrimaryExpression(AParenthesesPrimaryExpression ppe) {
    ntm.put(ppe, ntm.get(ppe.getExpression()) );
  }

  public void outAIfPrimaryExpression(AIfPrimaryExpression ipe) {
    ntm.put( ipe, ntm.get(ipe.getIfExpression()) );
  }

  public void outAStringLiteral(AStringLiteral sl) {
    ntm.put( sl, types.getString() );
  }

  public void outARealLiteral(ARealLiteral rl) {
    ntm.put( rl, types.getReal() );
  }

  public void outAIntegerLiteral(AIntegerLiteral il) {
    ntm.put( il, types.getInteger() );
  }

  public void outABooleanLiteral(ABooleanLiteral bl) {
    ntm.put( bl, types.getBoolean() );
  }

  public void outAEnumLiteral(AEnumLiteral el) {
    ntm.put( el, types.getEnumerationElement() );
  }

  public void outALiteralCollection(ALiteralCollection lc) {
    Type col;
    if (lc.getExpressionListOrRange()==null) {
      if (lc.getCollectionKind() instanceof ASetCollectionKind) {
        col=types.getSet();
      } else if (lc.getCollectionKind() instanceof ABagCollectionKind) {
        col=types.getBag();
      } else if (lc.getCollectionKind() instanceof ASequenceCollectionKind) {
        col=types.getSequence();
      } else {
        // lc.getCollectionKind() instanceof ACollectionCollectionKind
        col=types.getCollection();
      }
    } else {
      PExpressionListOrRange exor=lc.getExpressionListOrRange();
      Type param=ntm.get(exor);
      if (lc.getCollectionKind() instanceof ASetCollectionKind) {
        col=types.getSet(param);
      } else if (lc.getCollectionKind() instanceof ABagCollectionKind) {
        col=types.getBag(param);
      } else if (lc.getCollectionKind() instanceof ASequenceCollectionKind) {
        col=types.getSequence(param);
      } else {
        // lc.getCollectionKind() instanceof ACollectionCollectionKind
        col=types.getCollection(param);
      }
    }
    ntm.put(lc, col);
  }

  public void outAExpressionListOrRange(AExpressionListOrRange elor) {
    //  this implementation allows further expressions only to be
    //  of subtypes of the first expression's type
    Type firstType=ntm.get( elor.getExpression() );
    if (elor.getExpressionListOrRangeTail()!=null) {
      PExpressionListOrRangeTail tail=elor.getExpressionListOrRangeTail();
      if (tail instanceof AListExpressionListOrRangeTail) {
        AListExpressionListOrRangeTail listTail=(AListExpressionListOrRangeTail)tail;
        Iterator iter=listTail.getExpressionListTail().iterator();
        while (iter.hasNext()) {
          AExpressionListTail elt=(AExpressionListTail)iter.next();
          types.assertTrue( ntm.get(elt.getExpression()), firstType, elt );
        }
      } else {
        // tail instanceof ARangeExpressionListOrRangeTail
        ARangeExpressionListOrRangeTail rangeTail=(ARangeExpressionListOrRangeTail)tail;
        types.assertTrue( firstType, types.getInteger(), rangeTail );
        types.assertTrue( ntm.get( rangeTail.getExpression() ), types.getInteger(), rangeTail );
      }
    }
    ntm.put( elor, firstType );
  }

  public void outAPathSimpleTypeSpecifier(APathSimpleTypeSpecifier psts) {
    ntm.put( psts, ntm.get( psts.getPathTypeName() ) );
  }

  public void outAPathTypeName(APathTypeName ptn) {
    ntm.put(ptn, ntm.get( ptn.getTypeName() ) );
  }

  public void inACollectionTypeName(ACollectionTypeName ctn) {
    String colName=ctn.getCollectionType().toString().trim();
    String elemTypeName=ctn.getSimpleTypeName().toString().trim();
    Type elemType=types.get(elemTypeName);
    Type colType;
    if (colName.equals("Set")) {
      colType=types.getSet(elemType);
    } else if (colName.equals("Bag")) {
      colType=types.getBag(elemType);
    } else if (colName.equals("Sequence")) {
      colType=types.getSequence(elemType);
    } else {
      colType=types.getCollection(elemType);
    }
    ntm.put( ctn, types.getOclType(colType) );
  }

  public void inANonCollectionTypeName(ANonCollectionTypeName nctn) {
    ntm.put(
      nctn,
      types.getOclType(types.get(getFullPath(nctn)))
    );
  }

  public void outATypeNamePathNameBegin(ATypeNamePathNameBegin tnpnb) {
    ntm.put( tnpnb, ntm.get( tnpnb.getTypeName() ) );
  }


  /** @return a copy of the surrounding type environment of the given node
   */
  protected TypeEnvironment getEnvironmentCopy(Node n) {
    /*
    Node parent=n.parent();
    while (parent!=null && ! environs.containsKey(parent) ) {
      parent=parent.parent();
    }
    if (parent==null)
      return new TypeEnvironment();
    else
      return new TypeEnvironment( environs.get(parent) );
    */
    Node parent=getParentEnvNode(n);
    TypeEnvironment te=getEnvironmentFor(parent);
    if (te==null)
      return new TypeEnvironment();
    else
      return new TypeEnvironment( te );

  }

  /** @return the Node that defines the parent environment for
   *          the Node given as parameter
   */
  protected Node getParentEnvNode(Node n) {
    Node ret=null;
    if (n instanceof ALetExpression) {
      ALetExpression let=(ALetExpression) n;
      AExpression expr=(AExpression) let.parent();
      LinkedList list=expr.getLetExpression();
      Iterator iter=list.iterator();
      if (list.getFirst()==let) {
        ret=expr;
      } else {
        while (true) {
          Object nxt=iter.next();
          if (nxt==let) break;
          ret=(ALetExpression)nxt;
        }
      }
    } else if (n instanceof ALogicalExpression) {
      ALogicalExpression log=(ALogicalExpression)n;
      LinkedList lets=((AExpression)log.parent()).getLetExpression();
      if (lets.isEmpty()) {
        ret=n.parent();
      } else {
        ret=(Node)lets.getLast();
      }
    } else {
      ret=n.parent();
    }
    return ret;
  }

  protected TypeEnvironment getEnvironmentFor(Node n) {
    Node parent=n;
    while (parent!=null && ! environs.containsKey(parent) ) {
      parent=getParentEnvNode(parent);
    }
    return environs.get(parent);
  }

  /** adds all expression types of an AActualParameterList to a List
   *
   *  @param recurse <code>apply(this)</code> is called for all these expressions
   */
  protected void addParamTypesToList(AActualParameterList apl, List list, boolean recurse) {
    PExpression expr=apl.getExpression();
    expr.apply(this);
    list.add( ntm.get(expr) );
    Iterator iter=apl.getActualParameterListTail().iterator();
    while (iter.hasNext()) {
      AActualParameterListTail next=(AActualParameterListTail) iter.next();
      expr=next.getExpression();
      expr.apply(this);
      list.add( ntm.get(expr) );
    }
  }

  protected void assertElementType(Collection coll, String msg) {
    if (coll.getElementType()==null) {
      throw new OclTypeException(
        "cannot find out element type of collection in "+msg
      );
    }
  }

  // NameBoundQueryable methods :

  public boolean isNameBound(String name, Node node) {
    boolean result = (getTypeFor(name, node) != null);
    return result;
  }

  public HashSet getBoundNames(Node n) {
    HashSet result=new HashSet();
    TypeEnvironment env=getEnvironmentFor(n);
    result.addAll( env.map.keySet() );
    return result;
  }

  public String getDefaultContext(Node n) {
    Node parent=n;
    while (parent!=null && !defaultContexts.containsKey(parent) ) {
      parent=parent.parent();
    }
    String ret=(String) defaultContexts.get(parent);
    if (! ret.equals(ret.trim()) ) throw new RuntimeException("untrimmed default context");
    return ret;
  }

  public void changeNotify(Node n) {
    n.apply(this);
  }

  // TypeQueryable methods

  public Type getNodeType(Node n) {
    return ntm.get(n);
  }

  /** @return the type of the variable bound to the given name, or <code>null</code>
   *          if the name is not bound
   */
  public Type getTypeFor(String name, Node node) {
    // next line is to save implementors of endless seraching for nasty bugs
    if (! name.equals(name.trim()) ) throw new RuntimeException("untrimmed name");
    TypeEnvironment env=getEnvironmentFor(node);
    Type ret=env.get(name);
    return ret;
  }

	/**
	 * This returns the full class name of a node that descibes a class name.
	 * It uses the knowlege that the class-name starts with an upcase letter
	 * while static references and package declerators start with lowcase
	 * letters.
	 */
  private String getFullPath(Node n) {
  	while (!(n instanceof APathName) && !(n instanceof APathTypeName)) {
  		n = n.parent();
  	}

  	String fullPath = n.toString().trim(); 	
  	if (fullPath.indexOf("::") != -1) {                 // For efficency 
	  	String cutPath = "";
	  	StringTokenizer pathTokenizer = new StringTokenizer(fullPath,":",true);
	  	
	  	while (pathTokenizer.hasMoreElements()) {
	  		String token = pathTokenizer.nextToken().trim();
	  		cutPath = cutPath + token;
	  		if (!(token.equals(":")) && 
	  				token.substring(0,1).toUpperCase().equals(token.substring(0,1))) {
	  		  break;
	  		}
	  	}
	  	fullPath = cutPath;
  	}  	
  	return fullPath;
  }

}

class NodeTypeMap {

  HashMap map;

  NodeTypeMap() {
    map=new HashMap();
  }

  public Type put(Node n, Type c) {
    return (Type) map.put(n, c);
  }

  public Type get(Node n) {
    return (Type) map.get(n);
  }
}

class NodeEnvironmentMap {

  HashMap map=new HashMap();

  public TypeEnvironment put(Node n, TypeEnvironment te) {
    return (TypeEnvironment) map.put(n, te);
  }

  public TypeEnvironment get(Node n) {
    return (TypeEnvironment) map.get(n);
  }

  public boolean containsKey(Node n) {
    return map.containsKey(n);
  }
}

class TypeEnvironment {

  HashMap map;

  TypeEnvironment() {
    map=new HashMap();
  }

  TypeEnvironment(TypeEnvironment te) {
    map=new HashMap(te.map);
  }

  public Type put(String var, Type t) {
    if (! var.equals(var.trim()) ) throw new RuntimeException("untrimmed variable name");
    return (Type) map.put(var, t);
  }

  public Type get(String var) {
    return (Type) map.get(var);
  }
}


