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

package tudresden.ocl.normalize;

import tudresden.ocl.*;
import tudresden.ocl.parser.*;
import tudresden.ocl.parser.analysis.*;
import tudresden.ocl.parser.node.*;
import java.util.*;

/** make variable names unique
 *
 *  @author Frank Finger
 */
public class VariableClarification extends DepthFirstAdapter implements TreeNormalizer {

  OclTree tree;
  boolean isChanged;

  /** maps variable names to <code>null</code> if they can remain unchanged or
   *  to the variable name they are replaced by
   */
  HashMap replacements=new HashMap();

  /** contains a superset of the key set of <code>replacements</code>
   */
  HashSet usedVariables=new HashSet();;

  public void normalize(OclTree tree) {
    this.tree=tree;
    tree.apply(this);
    tree.assureInvariant(
      "context AExpression inv : letExpression -> forAll ( le | not self . parent . boundNames -> includes ( le . name ) )  "
    );
    tree.assureInvariant(
      "context AStandardDeclarator : not parent . parent . boundNames -> includes ( name.toString() )"
    );
    tree.assureInvariant(
      "context AIterateDeclarator : let ppb = parent . parent . boundNames in not ( ppb -> includes ( iterator.toString() ) or ppb -> includes( accumulator.toString() ) )"
    );
  }

  public void inAConstraint(AConstraint c) {
    isChanged=false;
  }

  public void outAConstraint(AConstraint c) {
    if (isChanged) {
      tree.changeNotify(c);
    }
  }

  public void caseAExpression(AExpression e) {
    HashMap oldReplacements=new HashMap(replacements);

    Iterator iter=e.getLetExpression().iterator();
    while (iter.hasNext()) {
      ALetExpression le=(ALetExpression) iter.next();
      le.apply(this); // recursive decent
      String oldName=le.getName().toString().trim();
      String newName;
      if (usedVariables.contains(oldName)) {
        newName=tree.getNameCreator().getUniqueName("Let");
        le.setName(new TName(newName));
        isChanged=true;
      } else {
        newName=null;
        usedVariables.add(oldName);
      }
      replacements.put(oldName, newName);
    }
    e.getLogicalExpression().apply(this); // recursive decent

    replacements=oldReplacements;
  }


  public void caseAFeatureCallParameters(AFeatureCallParameters fcp) {
    PDeclarator decl=fcp.getDeclarator();
    if (decl!=null) {
      String oldIterName;
      String oldIterReplacement;
      String newIterName;
      String oldAccuName;
      String oldAccuReplacement;
      String newAccuName;
      decl.apply(this);
      AStandardDeclarator sdecl=null;
      AIterateDeclarator idecl=null;
      if (decl instanceof AStandardDeclarator) {
        sdecl=(AStandardDeclarator) decl;
        oldIterName=sdecl.getName().toString().trim();
        oldAccuName=null;
      } else {
        // decl instanceof AIterateDeclarator
        idecl=(AIterateDeclarator) decl;
        oldIterName=idecl.getIterator().toString().trim();
        oldAccuName=idecl.getAccumulator().toString().trim();
      }
      if (usedVariables.contains(oldIterName)) {
        newIterName=tree.getNameCreator().getUniqueName("Iter");
        isChanged=true;
        oldIterReplacement=(String) replacements.put(oldIterName, newIterName);
      } else {
        usedVariables.add(oldIterName);
        newIterName=null;
        oldIterReplacement=null;
      }
      if (oldAccuName!=null && usedVariables.contains(oldAccuName)) {
        newAccuName=tree.getNameCreator().getUniqueName("Accu");
        isChanged=true;
        oldAccuReplacement=(String) replacements.put(oldAccuName, newAccuName);
      } else {
        usedVariables.add(oldAccuName);
        newAccuName=null;
        oldAccuReplacement=null;
      }
      if (newIterName!=null) {
        if (sdecl != null) {
          sdecl.setName( new TName(newIterName) );
        } else {
          // decl instanceof AIterateDeclarator
          idecl.setIterator( new TName(newIterName) );
        }
      }
      if (newAccuName!=null) {
        idecl.setAccumulator( new TName(newAccuName) );
      }
      fcp.getActualParameterList().apply(this);
      if (oldIterReplacement!=null) replacements.put(oldIterName, oldIterReplacement);
      if (oldAccuReplacement!=null) replacements.put(oldAccuName, oldAccuReplacement);
    } else {
      if (fcp.getActualParameterList()!=null) {
        fcp.getActualParameterList().apply(this);
      }
    }
  }

  public void inAFeaturePrimaryExpression(AFeaturePrimaryExpression fpe) {
    String s=fpe.toString().trim();
    if (replacements.keySet().contains(s) && replacements.get(s)!=null) {
      String repl=(String)replacements.get(s);
      fpe.setPathName(
        new APathName(
          new ANamePathNameBegin(new TName(repl)),
          new LinkedList()
        )
      );
    }
  }

}
