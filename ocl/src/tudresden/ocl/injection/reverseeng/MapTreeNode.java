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
 * MapTreeNode.java
 *
 * Created on 16. August 2000, 15:42
 */
 
package tudresden.ocl.injection.reverseeng;

import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

import tudresden.ocl.injection.reverseeng.propertypages.*;

/** 
  * A node representing an individual map.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class MapTreeNode extends AbstractFeatureTreeNode {
  
  static Icon s_iOK = new javax.swing.ImageIcon (MapTreeNode.class.getResource ("resources/map.gif"));
  static Icon s_iInCompl = new javax.swing.ImageIcon (MapTreeNode.class.getResource ("resources/mapInCompl.gif"));
  
  public MapTreeNode (DefaultTreeModel dtmModel, MapDescriptor md) {
    super(dtmModel, md);
  }

  public Icon getIcon (boolean fExpanded) {
    if (getDescriptor().isIncomplete()) {
      return s_iInCompl;
    }
    else {
      return s_iOK;
    }
  }

  public List getPropertyPages() {
    List lppReturn = super.getPropertyPages();
    lppReturn.add (new DefaultPropertyPage ("Element type", new TypeEditPage (getDescriptor(), new TypeEditPage.TypeDescriptor() {
      public void onTypeSelectionChanged (String sNewSelection) {
        getDescriptor().setElementType (sNewSelection);
      }
      
      public List getProposedTypes() {
        return new LinkedList();
      }
      
      public String getCurrentType() {
        return getDescriptor().getElementType();
      }
    })));
    lppReturn.add (new DefaultPropertyPage ("Key type", new TypeEditPage (getDescriptor(), new TypeEditPage.TypeDescriptor() {
      public void onTypeSelectionChanged (String sNewSelection) {
        getDescriptor().setKeyType (sNewSelection);
      }
      
      public List getProposedTypes() {
        return new LinkedList();
      }
      
      public String getCurrentType() {
        return getDescriptor().getKeyType();
      }
    })));
    
    return lppReturn;
  }

  /**
    * Return the map descriptor held as this node's user object.
    */
  public MapDescriptor getDescriptor() {
    return (MapDescriptor) getUserObject();
  }

  public String toString () {      
    MapDescriptor md = getDescriptor();

    return md.getDisplayName (true)
            + "[" + ((md.getKeyType() != null)?(md.getKeyType()):("")) + "]"
            + ((md.getElementType() != null)?(" : " + md.getElementType()):(""));
  }
  
  public String getToolTip() {
    return "Map: " + super.getToolTip();
  }
}
