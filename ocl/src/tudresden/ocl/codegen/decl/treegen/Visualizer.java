/*
Copyright (C) 1999, 2000 Frank Finger (frank@finger.org)
(Original code. See tudresden.ocl.Visualizer.)
Copyright (C) 2002  Sten Loecher 
(Adaptation for the purposes of the SQL tree structure.)

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/
package tudresden.ocl.codegen.decl.treegen;

import tudresden.ocl.codegen.decl.treegen.analysis.*;
import tudresden.ocl.codegen.decl.treegen.node.*;
import javax.swing.tree.*;
import javax.swing.*;

public class Visualizer extends DepthFirstAdapter {

  Start root;

  public Visualizer() {
  	
  }

  public void inStart(Start node) {
    root=node;
    defaultIn(node);
  }

  public void defaultIn(Node node) {
    String text=node.getClass().toString();
    text=text.substring(text.lastIndexOf(".")+1);
    if (text.startsWith("A")) text=text.substring(1);
    text = text+" - "+node.toString();
    DefaultMutableTreeNode dmtn=new DefaultMutableTreeNode(text);
    setIn(node, dmtn);
  }

  public void defaultOut(Node node) {
    Node parent=node.parent();
    if (parent!=null) {
      DefaultMutableTreeNode dmtn=(DefaultMutableTreeNode)getIn(parent);
      dmtn.add((DefaultMutableTreeNode)getIn(node));
    }
  }

  public void defaultCase(Node node) {
    // called for Tokens only -> this is a leaf node
    String text=node.getClass().toString();
    text=text.substring(text.lastIndexOf(".")+1);
    if (text.startsWith("T")) text=text.substring(1);
    text = text+" - "+node.toString();
    DefaultMutableTreeNode dmtn=new DefaultMutableTreeNode(text);
    ((DefaultMutableTreeNode)getIn(node.parent())).add(dmtn);
  }

  public DefaultMutableTreeNode getRootNode() {
    return (DefaultMutableTreeNode) getIn(root);
  }
}
