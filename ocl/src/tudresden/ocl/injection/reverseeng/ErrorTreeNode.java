/*
Copyright (C) 2000  Steffen Zschaler

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

/*
 * ErrorTreeNode.java
 *
 * Created on 16. August 2000, 15:29
 */
 
package tudresden.ocl.injection.reverseeng;

import tudresden.ocl.injection.reverseeng.propertypages.*;

import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

/** 
  * Node that displays an error message.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class ErrorTreeNode extends RevengTreeNode {

  /**
    * The icon used for the error node.
    */
  static Icon s_iIcon;
  static {
    s_iIcon = new javax.swing.ImageIcon (ErrorTreeNode.class.getResource ("resources/error.gif"));
  }
 
  /**
    * Create a new error tree node with the specified message.
    *
    * @param dtmModel the TreeModel which this node is part of.
    * @param sMessage the message to be displayed.
    */
  public ErrorTreeNode (DefaultTreeModel dtmModel, String sMessage) {
    super (dtmModel);
      
    setUserObject (sMessage);
    setAllowsChildren (false);
  }
    
  /**
    * Return the error icon.
    */
  public Icon getIcon (boolean fExpanded) {
    return s_iIcon;
  }
    
  /**
    * Ignored.
    */
  public void fill() { }
  
  public List getPropertyPages() {
    List lppPages = super.getPropertyPages();
    
    JTextArea jta = new JTextArea ((String) getUserObject());
    jta.setEditable (false);
    jta.setBackground (java.awt.Color.lightGray);
    
    JScrollPane jsp = new JScrollPane (jta);
    
    lppPages.add (new DefaultPropertyPage ("Error", jsp));
    
    return lppPages;
  }
}