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
 * HolderTreeNode.java
 *
 * Created on 16. August 2000, 15:33
 */
 
package tudresden.ocl.injection.reverseeng;

import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

/** 
  * Abstract superclass of TreeNodes holding a list of children.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public abstract class HolderTreeNode extends RevengTreeNode {

  /**
    * The caption of the top-level node.
    */
  private String m_sCaption;
    
  /**
    * Create a new HolderTreeNode
    *
    * @param dtmModel the model which this node is part of
    * @param sCaption the caption of the top-level node.
    * @param lData the list to be held by this node.
    */
  public HolderTreeNode (DefaultTreeModel dtmModel, String sCaption, List lData) {
    super (dtmModel);
      
    setUserObject (lData);
    setAllowsChildren (lData.size() > 0);
      
    m_sCaption = sCaption;
  }
    
  /**
    * Return an open or closed folder icon.
    */
  public Icon getIcon (boolean fExpanded) {
    if (fExpanded) {
      return javax.swing.UIManager.getIcon ("Tree.closedIcon");
    }
    else {
      return javax.swing.UIManager.getIcon ("Tree.openIcon");
    }
  }
    
  /**
    * Return the specified caption.
    */
  public String toString() {
    return m_sCaption;
  }
}