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
// FILE: d:/projekte/diplom/classes/tudresden/ocl/normalize/SelfInsertion.java

package tudresden.ocl.normalize;
import java.util.*;
import tudresden.ocl.OclTree;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.check.*;

/** This node normalization inserts the default context ("self" or some
 *  iterator variable) wherever appropriate. A prerequistite for this step
 *  is that there is a default context in every part of the expression, which
 *  means that all iterators must have names and there are no double iterators.
 *
 *  @see MultipleIteratorSolving
 *  @see IteratorInsertion
 *  @author Frank Finger
 */
public class DefaultContextInsertion implements NodeNormalizer {

  public void open(OclTree tree, NormalizerPass pass) {
    tree.requireInvariant("context ANamePathNameBegin inv : defaultContext <> '' implies boundNames -> includes ( defaultContext )  ");
    tree.requireInvariant("context AStandardDeclarator inv : declaratorTail -> isEmpty");
    tree.assureInvariant("context ANamePathNameBegin inv : boundNames -> includes ( name )");
  }

  public void close(OclTree tree, NormalizerPass pass) {
  }

  public void normalize(Node n, OclTree tree) {
    if (n instanceof APostfixExpression) {
      APostfixExpression ape=(APostfixExpression)n;
      PPrimaryExpression pPrimary=ape.getPrimaryExpression();
      if (pPrimary instanceof AFeaturePrimaryExpression) {
        AFeaturePrimaryExpression aFPrimary=(AFeaturePrimaryExpression)pPrimary;
        PPathNameBegin pPath=((APathName)aFPrimary.getPathName()).getPathNameBegin();
        if (pPath instanceof ANamePathNameBegin) { // could also have been a type name
          ANamePathNameBegin aNPath=(ANamePathNameBegin)pPath;
          String name=aNPath.getName().toString().trim();
          if ( ! tree.isNameBound(name, n) ) {
            // name is not an OCL variable or self, so here we go...

            // construct new PrimaryExpression
            ANamePathNameBegin newNPNB=new ANamePathNameBegin(new TName( tree.getDefaultContext(n) ));
            APathName newPN=new APathName(newNPNB, new LinkedList());
            AFeaturePrimaryExpression newFPE=new AFeaturePrimaryExpression();
            newFPE.setPathName(newPN);

            // construct new PostfixExpressionTail
            AFeatureCall  newFC=new AFeatureCall();
            newFC.setPathName( aFPrimary.getPathName() );
            newFC.setTimeExpression( aFPrimary.getTimeExpression() );
            newFC.setQualifiers( aFPrimary.getQualifiers() );
            newFC.setFeatureCallParameters( aFPrimary.getFeatureCallParameters() );
            APostfixExpressionTail newPET=new APostfixExpressionTail(
              new ADotPostfixExpressionTailBegin( new TDot() ),
              newFC
            );
            LinkedList list=ape.getPostfixExpressionTail();
            list.addFirst(newPET);
            ape.setPrimaryExpression(newFPE);

            tree.changeNotify(n.parent());
          }
        }
      }
    }
  }
} /* end class SelfInsertion */

