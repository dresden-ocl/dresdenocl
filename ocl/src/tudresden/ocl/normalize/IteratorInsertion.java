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
// FILE: d:/projekte/diplom/classes/tudresden/ocl/normalize/IteratorInsertion.java

package tudresden.ocl.normalize;

import java.util.*;
import tudresden.ocl.*;
import tudresden.ocl.parser.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.check.TypeChecker;

/** This node normalizer inserts an iterator in every iterating method
 *  that does not already have one (or more). This is a prerequisite to
 *  the normalization step &quot;DefaultContextInsertion&quot; but breaks to results
 *  of &quot;TypeInformationInsertion&quot;.
 *
 *  @see DefaultContextInsertion
 *  @author Frank Finger
 */
public class IteratorInsertion implements NodeNormalizer {

  public void open(OclTree tree, NormalizerPass pass) {
    tree.assureInvariant(
      "context AFeatureCall inv : "+
      "let iteratingMethodNames : Set ( String ) = Set { 'collect', 'exists' , 'forAll' , 'isUnique' , 'reject' , 'select' , 'sortedBy' } in "+
      "iteratingMethodNames -> includes ( self . pathName . toString ( ) ) implies "+
      "( self . featureCallParameters . declarator -> size = 1 "+
      "and self . featureCallParameters . declarator . oclType = AStandardDeclarator )"
    );
    tree.assureInvariant("context ANamePathNameBegin inv : defaultContext <> '' implies boundNames -> includes ( defaultContext )");
  }

  public void close(OclTree tree, NormalizerPass pass) {
  }

  public void normalize(Node n, OclTree tree) {
    if (n instanceof AFeatureCall) {
      AFeatureCall fc=(AFeatureCall)n;
      PFeatureCallParameters pFcp= fc.getFeatureCallParameters();
      if (  pFcp!=null &&
            TypeChecker.setOfIteratingMethodNames.contains(
              fc.getPathName().toString().trim()
            )
      ) {
        AFeatureCallParameters aFcp=(AFeatureCallParameters)pFcp;
        PDeclarator decl=aFcp.getDeclarator();
        if (decl==null) {
          AStandardDeclarator asd=new AStandardDeclarator();
          asd.setName( new TName( tree.getNameCreator().getUniqueName("Iter") ) );
          asd.setBar( new TBar() );
          aFcp.setDeclarator( asd );
          tree.changeNotify( n );
        }
      }
    }
  }

} /* end class IteratorInsertion */

