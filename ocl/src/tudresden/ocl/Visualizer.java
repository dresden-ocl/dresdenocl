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
package tudresden.ocl;

import tudresden.ocl.parser.analysis.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.check.TypeQueryable;
import tudresden.ocl.OclException;
import javax.swing.tree.*;
import javax.swing.*;

public class Visualizer extends DepthFirstAdapter {

  Start root;
  String errorMessage;
  RuntimeException exception;

  TypeQueryable tq;
  boolean catchExceptions;

  public Visualizer(boolean catchExceptions) {
    this(catchExceptions, null);
  }

  public Visualizer(boolean catchExceptions, TypeQueryable tq) {
    this.tq=tq;
    this.catchExceptions=catchExceptions;
  }

  public void inStart(Start node) {
    root=node;
    defaultIn(node);
  }

  public void outStart(Start s) {
    if (exception!=null) {
      throw exception;
    }
  }

  public void defaultIn(Node node) {
    String text=node.getClass().toString();
    text=text.substring(text.lastIndexOf(".")+1);
    if (text.startsWith("A")) text=text.substring(1);
    text = text+" - "+node.toString();
    if (tq!=null) {
      try {
        Object o=tq.getNodeType(node);
        if (o==null) {
          text=text+" - no type";
        } else {
          text = text+" - "+o;
        }
      } catch (RuntimeException e) {
        text = text + " - ERROR " + e.getMessage();
        errorMessage=e.getMessage();
        if (catchExceptions) {
          e.printStackTrace(System.out);
        } else {
          if (exception==null) exception=e;
        }
      }
    }
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

  public String getErrorMessage() {
    return errorMessage;
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
