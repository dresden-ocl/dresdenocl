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
 * CollectionTreeNode.java
 *
 * Created on 16. August 2000, 15:48
 */
 
package tudresden.ocl.injection.reverseeng;

import javax.swing.*;

import javax.swing.tree.*;

/** 
 *
 * @author  sz9
 * @version 
 */
public class CollectionTreeNode extends RevengTreeNode {
  
  static Icon s_iOK = new javax.swing.ImageIcon (CollectionTreeNode.class.getResource ("collection.gif"));
  static Icon s_iInCompl = new javax.swing.ImageIcon (CollectionTreeNode.class.getResource ("collectionInCompl.gif"));
  
  public CollectionTreeNode (DefaultTreeModel dtmModel, CollectionDescriptor cd) {
    super (dtmModel);

    setUserObject (cd);
    cd.setAssociatedTreeNode (this);

    setAllowsChildren(false);
  }

  public Icon getIcon (boolean fExpanded) {
    if (getDescriptor().isIncomplete()) {
      return s_iInCompl;
    }
    else {
      return s_iOK;
    }
  }

  public void fill() {
    setAllowsChildren (false);

    nodeChanged();
  }

  public CollectionDescriptor getDescriptor() {
    return (CollectionDescriptor) getUserObject();
  }

  public JComponent getRightComponent() {
    return new CollectionEditor (getDescriptor());
  }

  public String toString () {      
    CollectionDescriptor cd = getDescriptor();

    return cd.getName() + 
            ((cd.getElementType() != null)?(" : " + cd.getElementType()):(""));
  }
  
  public String getToolTip() {
    return "Collection: " + super.getToolTip();
  }
}