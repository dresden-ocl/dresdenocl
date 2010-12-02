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

import java.util.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.parser.analysis.*;

/** This class supplies name binding information as defined by the interface
 *  NameBoundQueryable. It is not used in the standard compiler configuration.
 *
 * @author Frank Finger
 */
public class NameBinder extends DepthFirstAdapter implements NameBoundQueryable {

  /** the AST that's name binding is of interest, represented by it's root node
   */
  protected Start ast;

  /** maps AST nodes to HashSets containing NameSpaceEntries; if a node doesn't
   *  change it's parent's environment there is NO entry in this map for
   *  the node
   */
  protected HashMap nodes;

  /**
   */
  protected HashMap defaultContexts;

  /** create a new NameBinder for the AST given through it's root node; the
   *  tree will be examined instantly
   */
  public NameBinder(Start s) {
    ast=s;
    update(ast);
  }

  protected void update(Node n) {
    // start tree examination at node n
    n.apply(this);
  }

  // DepthFirstAdapter methods

  public void inStart(Start s) {
    nodes=new HashMap();
    defaultContexts=new HashMap();
    nodes.put(s, new HashSet());
  }

  public void inAConstraint(AConstraint c) {
    HashSet set=getCopy(c);
    set.add(new NameSpaceEntry("self"));
    if (
      ((AContextDeclaration)c.getContextDeclaration()).getContextBody()
      instanceof AOperationContextBody
    ) {
      set.add(new NameSpaceEntry("result"));
      AOperationContextBody ocb=(AOperationContextBody)
        ((AContextDeclaration)c.getContextDeclaration()).getContextBody();
      AOperationContext oc=(AOperationContext)ocb.getOperationContext();
      if (oc.getFormalParameterList()!=null) {
        AFormalParameterList fpl=(AFormalParameterList)oc.getFormalParameterList();
        set.add(new NameSpaceEntry(
          ((AFormalParameter)fpl.getFormalParameter()).getName().toString().trim()
        ) );
        Iterator iter=fpl.getFormalParameterListTail().iterator();
        while (iter.hasNext()) {
          AFormalParameter fp=(AFormalParameter)
            ((AFormalParameterListTail)iter.next()).getFormalParameter();
          set.add(new NameSpaceEntry(
            fp.getName().toString().trim()
          ) );
        }
      }
    }
    nodes.put(c, set);
    defaultContexts.put(c, "self");
  }

  /** This method is implemented to update the default context for the
   *  FeatureCall's FeatureCallParameter node if the feature call is a
   *  call to an iterating method.
   */
  public void inAFeatureCall(AFeatureCall fc) {
    if ( TypeChecker.setOfIteratingMethodNames.contains( fc.getPathName().toString().trim() ) ) {
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
          if (asd.getDeclaratorTail()==null || asd.getDeclaratorTail().isEmpty()) {
            // single iterator variable
            iteratorName=asd.getName().toString().trim();
          } else {
            // multiple iterators -> no default context
            iteratorName=null;
          }
        } else {
          throw new tudresden.ocl.parser.OclParserException(
            "iterating method with wrong Declarator type: "+fc.toString()
          );
        }
      } else {
        // no declarator -> default context has no name
        // (default context is the non-existent iterator variable)
        iteratorName=null;
      }
      defaultContexts.put( fc.getFeatureCallParameters(), iteratorName );
    }
  }

  /** This method adds iterator variables to the node's set of bound names.
   */
  public void inAFeatureCallParameters(AFeatureCallParameters fcp) {
    PDeclarator pd=fcp.getDeclarator();
    if (pd!=null) {
      HashSet newNames=getCopy(fcp);
      if (pd instanceof AStandardDeclarator) {
        AStandardDeclarator asd=(AStandardDeclarator)pd;
        String name=asd.getName().toString().trim();
        newNames.add(new NameSpaceEntry(name));
        Iterator iter=asd.getDeclaratorTail().iterator();
        while (iter.hasNext()) {
          name= ( (ADeclaratorTail)iter.next() ).getName().toString().trim();
          newNames.add(new NameSpaceEntry(name));
        }
      } else if (pd instanceof AIterateDeclarator) {
        AIterateDeclarator aid=(AIterateDeclarator)pd;
        String nameIterator=aid.getIterator().toString().trim();
        String nameAccum=aid.getAccumulator().toString().trim();
        newNames.add( new NameSpaceEntry(nameIterator) );
        newNames.add( new NameSpaceEntry(nameAccum) );
      }
      PActualParameterList params=fcp.getActualParameterList();
      if (params!=null) {
        nodes.put(params, newNames);
      }
    }
  }

  public void inAExpression(AExpression e) {
    LinkedList lets=e.getLetExpression();
    if (! lets.isEmpty() ) {
      HashSet set=getCopy(e);
      Iterator iter=lets.iterator();
      while (iter.hasNext()) {
        String name= ( (ALetExpression)iter.next() ).getName().toString().trim();
        set.add(new NameSpaceEntry(name));
      }
      nodes.put(e, set);
    }
  }

  /** @return a copy of the surrounding environment of node n
   */
  protected HashSet getCopy(Node n) {
    Node parent=n.parent();
    while ( parent!=null && !nodes.containsKey(parent) ) {
      parent=parent.parent();
    }
    return new HashSet( (HashSet)nodes.get(parent) );
  }

  /** may return null
   */
  protected HashSet getEnvironment(Node n) {
    Node parent=n;
    while ( !nodes.containsKey(parent) ) {
      parent=parent.parent();
    }
    return (HashSet) nodes.get(parent);
  }

  public boolean isNameBound(String name, Node node) {
    HashSet environ=getEnvironment(node);
    if (environ==null) {
      return false;
    }
    return environ.contains(new NameSpaceEntry(name.trim()));
  }

  public HashSet getBoundNames(Node n) {
    HashSet result=new HashSet();
    HashSet env=getEnvironment(n);
    Iterator iter=env.iterator();
    while (iter.hasNext()) {
      NameSpaceEntry nse=(NameSpaceEntry)iter.next();
      result.add(nse.name);
    }
    return result;
  }

  public String getDefaultContext(Node n) {
    Node parent=n;
    while (parent!=null && !defaultContexts.containsKey(parent) ) {
      parent=parent.parent();
    }
    return (String) defaultContexts.get(parent);
  }

  /** An operation that does ...
   *
   * @param firstParamName  a description of this parameter
   */
  public void changeNotify(Node subtree) {
    update(subtree);
  }
} /* end class NameBinder */

class NameSpaceEntry {

  String name;

  NameSpaceEntry(String name) {
    this.name=name;
  }

  public String toString() {
    return "<"+name+">";
  }

  public boolean equals(Object o) {
    if (o instanceof NameSpaceEntry) {
      NameSpaceEntry nse=(NameSpaceEntry)o;
      return nse.name.equals(this.name);
    } else {
      return false;
    }
  }

  public int hashCode() {
    return name.hashCode();
  }
}






