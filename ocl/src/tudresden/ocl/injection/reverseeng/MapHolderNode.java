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
 * MapHolderNode.java
 *
 * Created on 16. August 2000, 15:39
 */
 
package tudresden.ocl.injection.reverseeng;

import java.util.*;

import javax.swing.tree.*;

/** 
  * A node holding MapTreeNodes.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class MapHolderNode extends HolderTreeNode {

  /**
    * Creates a new map holder node.
    */
  public MapHolderNode (DefaultTreeModel dtmModel, List lmdMaps) {
    super (dtmModel, "Maps", lmdMaps);
  }

  public void fill() {
    List lmdMaps = (List) getUserObject ();

    if (lmdMaps.size() == 0) {
      setAllowsChildren (false);

      nodeChanged();
    }
    else {
      for (Iterator i = lmdMaps.iterator(); i.hasNext();) {
        add (new MapTreeNode (getModel(), (MapDescriptor) i.next ()));
      }

      nodeStructureChanged();
    }
  }
}