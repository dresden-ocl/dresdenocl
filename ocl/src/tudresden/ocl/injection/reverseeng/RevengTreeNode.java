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

import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

/** 
  * Abstract super class for each node in the treeview.
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public abstract class RevengTreeNode extends DefaultMutableTreeNode {
  
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
    * Get the property panes to be shown in the right hand panel of the GUI.
    */ 
  public Iterator getPropertyPages() {
    return new Iterator() {
      private boolean m_fHasNext = true;

      public boolean hasNext() {
        return m_fHasNext;
      }

      public Object next() {
        if (m_fHasNext) {
          m_fHasNext = false;
          
          PropertyPage ppReturn = new PropertyPage ("No properties...", new JLabel ("No properties for current selection!"));
          ppReturn.setEnabled (false);
          return ppReturn;
        }
        else {
          throw new NoSuchElementException();
        }
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
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
  
  /**
    * Return the tool tip to be associated with this node. Defaults to toString().
    */
  public String getToolTip() {
    return toString();
  }
  
  /**
    * Return true, if the node is part of a file that needs to be saved. As a default returns
    * the result of sending isDirty to this node's parent, if a parent exists. Otherwise returns
    * false.
    */
  public boolean isDirty() {
    if (getParent() != null) {
      return ((RevengTreeNode) getParent()).isDirty();
    }
    else {
      return false;
    }
  }
  
  /**
    * Start to call rguiObserver's onDirtyChanged method whenever the dirty state of this node changes.
    */
  public void startDirtyChangeNotification (RevengGUI rguiObserver) {
    if (getParent() != null) {
      ((RevengTreeNode) getParent()).startDirtyChangeNotification (rguiObserver);
    }
  }
  
  /**
    * Stop calling rguiObserver's onDirtyChanged method whenever the dirty state of this node changes.
    */
  public void stopDirtyChangeNotification() {
    if (getParent() != null) {
      ((RevengTreeNode) getParent()).stopDirtyChangeNotification();
    }
  }
  
  /**
    * Save the file to which this node belongs.
    */
  public void save()
    throws IOException {
    if (getParent() != null) {
      ((RevengTreeNode) getParent()).save();
    }
  }
}