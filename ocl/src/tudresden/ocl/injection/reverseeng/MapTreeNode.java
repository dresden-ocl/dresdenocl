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

  public Iterator getPropertyPages() {
    List l = new LinkedList();
    //l.add (new PropertyPage ("Map Properties", new MapEditor (getDescriptor())));
    l.add (new PropertyPage ("Element type", new TypeEditPage (new TypeEditPage.TypeDescriptor() {
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
    l.add (new PropertyPage ("Key type", new TypeEditPage (new TypeEditPage.TypeDescriptor() {
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

    final Iterator iStandardPages = super.getPropertyPages();
    final Iterator iNewPages = l.iterator();
    
    return new Iterator() {
      public boolean hasNext() {
        return (iStandardPages.hasNext() ||
                 iNewPages.hasNext());
      }
      
      public Object next() {
        if (iStandardPages.hasNext()) {
          return iStandardPages.next();
        }
        else {
          return iNewPages.next();
        }
      }
      
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  /**
    * Return the map descriptor held as this node's user object.
    */
  public MapDescriptor getDescriptor() {
    return (MapDescriptor) getUserObject();
  }

  public String toString () {      
    MapDescriptor md = getDescriptor();

    return md.getContainingClass() + "." + md.getName()
            + "[" + ((md.getKeyType() != null)?(md.getKeyType()):("")) + "]"
            + ((md.getElementType() != null)?(" : " + md.getElementType()):(""));
  }
  
  public String getToolTip() {
    return "Map: " + super.getToolTip();
  }
}
