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
// FILE: d:/projekte/diplom/classes/tudresden/ocl/normalize/MultipleIteratorSolving.java

package tudresden.ocl.normalize;

import tudresden.ocl.*;
import tudresden.ocl.parser.*;
import tudresden.ocl.parser.analysis.*;
import tudresden.ocl.parser.node.*;
import java.util.*;

/** prerequiste: every feature call must have an explicitly named context (-> the
 *  FeatureCallParameters object must not be direct subnode of a
 *  PrimaryExpression node)
 *  ->after DefaultContextInsertion
 *
 *  @see DefaultContextInsertion
 *  @author Frank Finger
 */
public class MultipleIteratorSolving extends DepthFirstAdapter implements TreeNormalizer {

  OclTree tree;
  boolean isChanged;

  /** the expression that a inserted let expression will be added to (that
   *  is always the expression subnode of the last traversed constraint body
   *  node)
   */
  AExpression topExpression;

  public void normalize(OclTree tree) {
    this.tree=tree;
    do {
      isChanged=false;
      tree.apply(this);
    }
    while (isChanged);
    tree.assureInvariant("context AStandardDeclarator inv : declaratorTail -> isEmpty");
  }

  public void inAConstraintBody(AConstraintBody cb) {
    topExpression=(AExpression)cb.getExpression();
  }

  public void inAStandardDeclarator(AStandardDeclarator decl)  {
    if ( ! isChanged &&
          decl.getDeclaratorTail()!=null &&
          !decl.getDeclaratorTail().isEmpty() ) {
      // multiple iterators found
      isChanged=true;
      LinkedList tail=decl.getDeclaratorTail();
      AFeatureCall featureCall=(AFeatureCall)decl.parent().parent();

      int numberOfIterators=1+tail.size(); // >=2
      TName[] iterators=new TName[numberOfIterators];
      iterators[0]=decl.getName();
      Iterator iter=tail.iterator();
      for (int i=1; i<numberOfIterators; i++) {
        iterators[i]=( (ADeclaratorTail)iter.next() ).getName();
      }
      PDeclaratorTypeDeclaration typeDecl=decl.getDeclaratorTypeDeclaration();
      AExpression oldParameterExpression = (AExpression)
        (
          (AActualParameterList)(
            (AFeatureCallParameters) featureCall.getFeatureCallParameters()
          ).getActualParameterList()
        ).getExpression();
      PPathName featureCallPathName=featureCall.getPathName();
      PTimeExpression featureCallTimeExpression=featureCall.getTimeExpression();
      APostfixExpressionTail poExTailElem=(APostfixExpressionTail)featureCall.parent();
      APostfixExpression poEx=(APostfixExpression)poExTailElem.parent();
      boolean makeLetExpression= ! exprContainsBoundNames(poEx.getPrimaryExpression());

      // the part of the list before the element that becomes expanded
      LinkedList poExTailBeforeTailElem=new LinkedList( poEx.getPostfixExpressionTail() );
      // remove all elements after/including poExTailElem
      while (poExTailBeforeTailElem.removeLast()!=poExTailElem);

      // the part of the list after the element that becomes expanded
      LinkedList poExTailAfterTailElem=new LinkedList( poEx.getPostfixExpressionTail() );
      // remove all elements before/including poExTailElem
      while (poExTailAfterTailElem.removeFirst()!=poExTailElem);

      // from here on, the tree is changed

      String letName=null;
      if (makeLetExpression) {
        // add new let expression to topExpression
        letName=tree.getNameCreator().getUniqueName("Let");
        if (topExpression.getLetExpression()==null) {
          topExpression.setLetExpression( new LinkedList() );
        }
        ALetExpression letExpr=new ALetExpression();
        letExpr.setTLet( new TTLet() );
        letExpr.setName( new TName( letName ) );
        letExpr.setEqual( new TEqual() );
        letExpr.setTIn( new TTIn() );
        letExpr.setExpression(
          new AExpression(
            new LinkedList(),
            new ALogicalExpression(
              new ARelationalExpression(
                new AAdditiveExpression(
                  new AMultiplicativeExpression(
                    new APostfixUnaryExpression(
                      new APostfixExpression(
                        (PPrimaryExpression)poEx.getPrimaryExpression().clone(),
                        poExTailBeforeTailElem
                      )
                    ),
                    new LinkedList()
                  ),
                  new LinkedList()
                ),
                null
              ),
              new LinkedList()
            )
          )
        );
        topExpression.getLetExpression().addLast( letExpr );
      }

      //change poEx

      APostfixExpression nextPoEx=poEx;

      for (int iterIndex=0; iterIndex<numberOfIterators; iterIndex++) {
        nextPoEx.setPrimaryExpression(
          (
            (makeLetExpression) ?
            new AFeaturePrimaryExpression(
              new APathName(
                new ANamePathNameBegin(
                  new TName( letName )
                ),
                new LinkedList()
              ),
              null, // no time expression
              null, // no qualifiers
              null  // no feature call parameters
            ) :
            (PPrimaryExpression)poEx.getPrimaryExpression().clone()
          )
        );

        nextPoEx.getPostfixExpressionTail().clear();
        if (!makeLetExpression) {
          Iterator tailElemIter=poExTailBeforeTailElem.iterator();
          while (tailElemIter.hasNext()) {
            nextPoEx.getPostfixExpressionTail().addLast(
              (APostfixExpressionTail) ((APostfixExpressionTail)tailElemIter.next()).clone()
            );
          }
        }
        /* the PostfixExpressionTail that will become part of what is now
         * called nextPoEx but will then be called prevPoEx */
        LinkedList nextPoExTail;
        if (nextPoEx==poEx) {
          nextPoExTail=poExTailAfterTailElem;
        } else {
          nextPoExTail=new LinkedList();
        }
        APostfixExpression prevPoEx=nextPoEx;
        nextPoEx=new APostfixExpression();

        AExpression parameterExpression;
        if (iterIndex<numberOfIterators-1) {
          // parameterExpression is call to nextPoEx
          parameterExpression = new AExpression(
            new LinkedList(), // no let expressions
            new ALogicalExpression(
              new ARelationalExpression(
                new AAdditiveExpression(
                  new AMultiplicativeExpression(
                    new APostfixUnaryExpression(
                      nextPoEx
                    ),
                    new LinkedList()
                  ),
                  new LinkedList()
                ),
                null
              ),
              new LinkedList()
            )
          );
        } else {
          // parameterExpression is old expression
          parameterExpression = (AExpression) oldParameterExpression.clone();
        }

        APostfixExpressionTail create=new APostfixExpressionTail(
            new AArrowPostfixExpressionTailBegin( new TArrow() ),
            new AFeatureCall(
              (PPathName) featureCallPathName.clone(),
              null, // no time expression
              null, // no qualifiers
              new AFeatureCallParameters(
                new TLPar(),
                new AStandardDeclarator(
                  (TName) iterators[iterIndex].clone(),
                  new LinkedList(),
                  (typeDecl==null)?typeDecl:(PDeclaratorTypeDeclaration) typeDecl.clone(),
                  new TBar()
                ),
                new AActualParameterList(
                  parameterExpression,
                  new LinkedList()
                ),
                new TRPar()
              )
            ) // end constructor AFeatureCall
          );
        nextPoExTail.addFirst(
          create
        ); // end nextPoExTail.addFirst
        prevPoEx.setPostfixExpressionTail( nextPoExTail );
      }
      tree.changeNotify();
    } // end if
  } // end method normalize


  /** @return true if the given primary expression contains any bound names
   *          other than self and result
   */
  protected boolean exprContainsBoundNames(final PPrimaryExpression pPrEx) {
    BoundNameFinder bnf=new BoundNameFinder(pPrEx, tree);
    pPrEx.apply(bnf);
    return bnf.isBound;
  }

  class BoundNameFinder extends DepthFirstAdapter {

    boolean isBound=false;
    PPrimaryExpression pPrEx;
    OclTree oclTree;

    BoundNameFinder(PPrimaryExpression pPrEx, OclTree oclTree) {
      this.pPrEx=pPrEx;
      this.oclTree=oclTree;
    }

    public void inANamePathNameBegin(ANamePathNameBegin npnb) {
      String name=npnb.toString().trim();
      if ( !name.equals("self") && !name.equals("result") && tree.isNameBound( name, pPrEx ) ) {
        isBound=true;
      }
    }
  }

} /* end class MultipleIteratorSolving */

