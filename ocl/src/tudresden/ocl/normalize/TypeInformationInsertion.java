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
// FILE: d:/projekte/diplom/classes/tudresden/ocl/normalize/TypeInformationInsertion.java

package tudresden.ocl.normalize;

import tudresden.ocl.OclException;
import tudresden.ocl.check.types.*;
import tudresden.ocl.OclTree;
import tudresden.ocl.parser.node.*;
import java.util.Iterator;

/**
 * @author Frank Finger
 */
public class TypeInformationInsertion implements NodeNormalizer {

  static java.util.HashSet setOfAllIteratingMethodNames;

  static {
    setOfAllIteratingMethodNames=new java.util.HashSet( tudresden.ocl.check.TypeChecker.setOfIteratingMethodNames );
    setOfAllIteratingMethodNames.add("iterate");
  }

  public void normalize(Node n, OclTree tree) {
    if (n instanceof AStandardDeclarator) {
      AStandardDeclarator sd = (AStandardDeclarator) n;
      if (sd.getDeclaratorTypeDeclaration()==null) {
        String iteratorName = sd.getName().toString().trim();
        AFeatureCallParameters fcp = (AFeatureCallParameters) sd.parent();
        AActualParameterList apl=(AActualParameterList)fcp.getActualParameterList();
        if (apl==null) {
          throw new OclException(
            "feature call must not have a declarator but no parameter in "+fcp
          );
        }
        Type t = tree.getTypeFor(iteratorName, apl.getExpression());
        APathTypeName ptn=getPathTypeName(t);
        sd.setDeclaratorTypeDeclaration(
          new ADeclaratorTypeDeclaration(
            new TColon(),
            new APathSimpleTypeSpecifier(
              ptn
            )
          )
        );
      }
    } else if (n instanceof ALetExpression) {
      ALetExpression le=(ALetExpression) n;
      if (le.getLetExpressionTypeDeclaration()==null) {
        AExpression exp=(AExpression)le.parent();
        Iterator iter=exp.getLetExpression().iterator();
        while (iter.hasNext() && iter.next()!=le) ;
        Node next; // the node for that the let variable is bound
        if (iter.hasNext()) {
          next=(Node)iter.next(); // the following let expression
        } else {
          next=exp.getLogicalExpression(); // if no let follows, the logical expr.
        }
        String letVarName=le.getName().toString().trim();
        Type t=tree.getTypeFor(letVarName, next);
        APathTypeName ptn=getPathTypeName(t);
        le.setLetExpressionTypeDeclaration(
          new ALetExpressionTypeDeclaration(
            new TColon(),
            ptn
          )
        );
      }
    }
  }

  public APathTypeName getPathTypeName(Type t) {
    APathTypeName ptn=new APathTypeName();
    if (t instanceof Collection) {
      Collection c=(Collection)t;
      Any elementType=c.getElementType();
      PCollectionType ct;
      switch (c.getCollectionKind()) {
        case Collection.SET:        ct=new ASetCollectionType(        new TTSet("Set") );               break;
        case Collection.BAG:        ct=new ABagCollectionType(        new TTBag("Bag") );               break;
        case Collection.SEQUENCE:   ct=new ASequenceCollectionType(   new TTSequence("Sequence") );     break;
        case Collection.COLLECTION: ct=new ACollectionCollectionType( new TTCollection("Collection") ); break;
        default: throw new RuntimeException("illegal collection kind");
      }
      ptn.setTypeName(
        new ACollectionTypeName(
          ct,
          new TLPar(),
          new TSimpleTypeName( elementType.toString() ),
          new TRPar()
        )
      );
    } else {
      ptn.setTypeName(
        new ANonCollectionTypeName(
          new TSimpleTypeName(t.toString())
        )
      );
    }
    return ptn;
  }

  public void open(OclTree tree, NormalizerPass pass) {
  }

  public void close(OclTree tree, NormalizerPass pass) {
  }

} /* end class TypeInformationInsertion */

