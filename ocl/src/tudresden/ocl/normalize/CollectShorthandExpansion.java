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
// FILE: d:/projekte/diplom/classes/tudresden/ocl/normalize/CollectShorthandExpansion.java

package tudresden.ocl.normalize;

import tudresden.ocl.check.types.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.parser.*;
import tudresden.ocl.OclTree;
import java.util.Iterator;
import java.util.LinkedList;

/** breaks default context insertion and iterator insertion
 *
 *
 */
public class CollectShorthandExpansion implements NodeNormalizer {

  public void normalize(Node n, OclTree tree) {
    if (n instanceof APostfixExpression) {
      APostfixExpression pe = (APostfixExpression) n;
      Iterator iter=pe.getPostfixExpressionTail().iterator();
      Type lastType=tree.getNodeType( pe.getPrimaryExpression() );
      while (iter.hasNext()) {
        APostfixExpressionTail pet=(APostfixExpressionTail) iter.next();
        PPostfixExpressionTailBegin petb = pet.getPostfixExpressionTailBegin();
        if ( petb instanceof ADotPostfixExpressionTailBegin &&
             lastType instanceof Collection ) {
          expandCollect(pe, pet, tree);
        }
        lastType=tree.getNodeType(pet);
      }
    }
  }

  protected void expandCollect(APostfixExpression pe, APostfixExpressionTail pet, OclTree tree) {
    AFeatureCall fc = (AFeatureCall) pet.getFeatureCall();

    // construct parameter expression for collect()
    AFeaturePrimaryExpression primary=new AFeaturePrimaryExpression();
    primary.setPathName( fc.getPathName() );
    primary.setTimeExpression( fc.getTimeExpression() );
    primary.setQualifiers( fc.getQualifiers() );
    primary.setFeatureCallParameters( fc.getFeatureCallParameters() );
    APostfixExpression postfix=new APostfixExpression();
    postfix.setPrimaryExpression(primary);
    APostfixUnaryExpression unary=new APostfixUnaryExpression(postfix);
    AMultiplicativeExpression mult=new AMultiplicativeExpression();
    mult.setUnaryExpression(unary);
    AAdditiveExpression additive=new AAdditiveExpression();
    additive.setMultiplicativeExpression(mult);
    ARelationalExpression relat=new ARelationalExpression(additive, null);
    ALogicalExpression logical=new ALogicalExpression();
    logical.setRelationalExpression(relat);
    AExpression paramExpr=new AExpression();
    paramExpr.setLogicalExpression(logical);

    // change existing pet to new collect()
    pet.setPostfixExpressionTailBegin( new AArrowPostfixExpressionTailBegin( new TArrow() ) );
    pet.setFeatureCall(
      new AFeatureCall(
        new APathName(
          new ANamePathNameBegin( new TName("collect") ),
          new LinkedList() // path name tail
        ),
        null, // time expression
        null, // qualifiers
        new AFeatureCallParameters(
          new TLPar(),
          null, // declarator
          new AActualParameterList(
            paramExpr,
            new LinkedList() // actual param list tail
          ),
          new TRPar()
        )
      )
    );

    tree.changeNotify(pet);
  }

  public void open(OclTree tree, NormalizerPass pass) {
    tree.breakInvariant("context ANamePathNameBegin inv : boundNames -> includes ( name )");
    tree.breakInvariant(
      "context AFeatureCall inv : "+
      "let iteratingMethodNames : Set ( String ) = Set { 'forAll' , 'exists' , 'collect' , 'isUnique' , 'select' , 'reject' } in "+
      "iteratingMethodNames -> includes ( self . pathName . toString ( ) ) implies "+
      "( self . featureCallParameters . declarator -> size = 1 "+
      "and self . featureCallParameters . declarator . oclType = AStandardDeclarator )"
    );
    tree.breakInvariant("context ANamePathNameBegin inv : defaultContext <> '' implies boundNames -> includes ( defaultContext )");
    tree.assureInvariant(
      "context APostfixExpression inv : "+
      "Set { 1 .. ( self . postfixExpressionTail -> size ) } -> forAll ( i : Integer | "+
        "Set { Collection , Set , Bag , Sequence } -> includes ( "+
          "if i = 1 then self . primaryExpression . oclType else self . postfixExpressionTail -> at ( i - 1 ) . oclType endif "+
        ") implies self . postfixExpressionTail -> at ( i ) . postfixExpressionTailBegin . oclIsTypeOf ( "+
          "AArrowPostfixExpressionTailBegin "+
        ") "+
      ")"
    );
  }

  public void close(OclTree tree, NormalizerPass pass) {
  }

} /* end class CollectShorthandExpansion */

