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


package tudresden.ocl.check.bootstrap;

import tudresden.ocl.lib.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.parser.analysis.*;
import tudresden.ocl.*;
import java.util.*;

public class SableOclAnyImpl extends OclAnyImpl {

  OclTree tree;
  Node node;

  public SableOclAnyImpl(Node node, OclTree tree) {
    super(node);
    this.tree=tree;
    this.node=node;
  }

  public OclRoot getFeature(String name) {
    if (name.equals("boundNames")) {
      HashSet boundNames=tree.getBoundNames(node);
      HashSet result=new HashSet();
      Iterator iter=boundNames.iterator();
      while (iter.hasNext()) {
        String s=(String)iter.next();
        result.add( new OclString( (String)s ) );
      }
      return new OclSet(result);
    } else if (name.equals("subnodes")) {
      SubnodeCollector sc=new SubnodeCollector(node);
      node.apply(sc);
      return new OclSet(sc.subnodes);
    } else if (name.equals("supernodes")) {
      HashSet result=new HashSet();
      Node parent=node.parent();
      while (parent!=null) {
        result.add( new SableOclAnyImpl(parent, tree) );
        parent=parent.parent();
      }
      return new OclSet(result);
    } else {
      return super.getFeature(name);
    }
  }

  public OclRoot getFeature(String name, Object[] params) {
    if (name.equals("toString") && (params==null || params.length==0)) {
      return new OclString( node.toString().trim() );
    }
    return super.getFeature(name, params);
  }

}

class SubnodeCollector extends DepthFirstAdapter {

  HashSet subnodes=new HashSet();
  Node node;

  SubnodeCollector(Node n) {
    node=n;
  }

  public void defaultIn(Node n) {
    if (node != n) {
      subnodes.add( Ocl.getOclRepresentationFor(n) );
    }
  }
}
