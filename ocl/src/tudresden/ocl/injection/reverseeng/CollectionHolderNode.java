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
 * CollectionHolderNode.java
 *
 * Created on 16. August 2000, 15:45
 */
 
package tudresden.ocl.injection.reverseeng;

import java.util.*;

import javax.swing.tree.*;

/** 
  * A node holding collection descriptions.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class CollectionHolderNode extends HolderTreeNode {
  
  public CollectionHolderNode (DefaultTreeModel dtmModel, List lcdCollections) {
    super (dtmModel, "Collections", lcdCollections);
  }

  public void fill() {
    List lcdCollections = (List) getUserObject ();

    if (lcdCollections.size() == 0) {
      setAllowsChildren (false);

      nodeChanged();
    }
    else {
      for (Iterator i = lcdCollections.iterator(); i.hasNext();) {
        add (new CollectionTreeNode (getModel(), (CollectionDescriptor) i.next ()));
      }

      nodeStructureChanged();
    }
  }
}