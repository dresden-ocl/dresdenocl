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
 * RevengTreeNode.java
 *
 * Created on 16. August 2000, 15:19
 */
 
package tudresden.ocl.injection.reverseeng;

import javax.swing.*;
import javax.swing.tree.*;

/** 
  * Abstract super class for each node in the treeview.
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public abstract class RevengTreeNode extends DefaultMutableTreeNode {
    
  /**
    * The component associated with any node that doesn't change the default behavior.
    */
  static JLabel s_jlNoProperties = new JLabel ("No properties for current selection!");
  
  /**
    * The model which this node is part of.
    */
  private DefaultTreeModel m_dtmModel = null;
  
  /**
    * Create a new RevengTreeNode.
    *
    * @param dtmModel the model which this node is part of. Must not be null!
    */
  public RevengTreeNode (DefaultTreeModel dtmModel) {
    super();
    
    m_dtmModel = dtmModel;
    
    if (dtmModel == null) {
      throw new NullPointerException ("Treemodel must not be null!");
    }
  }
 
  /**
    * Retrieve the model to which this node belongs.
    */
  protected DefaultTreeModel getModel() {
    return m_dtmModel;
  }
  
  /**
    * Return the icon associated with the tree node.
    *
    * @param fExpanded if true, the node is currently expanded.
    */
  public abstract Icon getIcon (boolean fExpanded);
  
  /**
    * Fill in the children of this node. Called when the node was freshly expanded.
    */
  public abstract void fill();
        
  /**
    * Called to indicate that the node was just collapsed. By default does nothing.
    */
  public void collapsed() { }
    
  /**
    * Get the component to be shown in the right hand panel of the GUI.
    */ 
  public JComponent getRightComponent () {
    return s_jlNoProperties;
  }
  
  /**
    * Notification that the data underlying this node has changed.
    *
    * By default, recursively cascades upward.
    */
  public void setModified() {
    nodeChanged();
    
    if (getParent() != null) {
      ((RevengTreeNode) getParent()).setModified();
    }
  }
  
  /**
    * Thread-safe invocation of getModel().nodeChanged (this);
    */
  protected void nodeChanged() {
    SwingUtilities.invokeLater (new Runnable() {
      public void run() {
        m_dtmModel.nodeChanged (RevengTreeNode.this);
      }
    });
  }
  
  /**
    * Thread-safe invocation of getModel().nodeStructureChanged (this);
    */
  protected void nodeStructureChanged() {
    SwingUtilities.invokeLater (new Runnable() {
      public void run() {
        m_dtmModel.nodeStructureChanged (RevengTreeNode.this);
      }
    });
  }
}