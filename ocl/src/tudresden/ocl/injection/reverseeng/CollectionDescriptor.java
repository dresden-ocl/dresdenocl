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
 * CollectionDescriptor.java
 *
 * Created on 7. August 2000, 17:20
 */
 
package tudresden.ocl.injection.reverseeng;

import javax.swing.tree.*;

/** 
  * Descriptor for a class attribute of collection type.
  *
  * <p>These descriptors are maintained by {@link AnalysisConsumer} in its m_lcdCollections member.</p>
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class CollectionDescriptor extends AbstractDescriptor {

  /** 
    * Creates new CollectionDescriptor 
    *
    * @param sName the attribute's name
    * @param sComment the associated doc comment
    * @param nCommentID the comment number of the associated doc comment
    */
  public CollectionDescriptor (AnalysisConsumer acOwner, String sName, String sComment, int nCommentID) {
    super (acOwner, sName, sComment, nCommentID);
  }
  
  public String toString () {
    return "Collection<" + getElementType () + "> " + getName () + " at comment ID " + getCommentID ();
  }
  
  public RevengTreeNode createTreeNode(DefaultTreeModel dtmModel) {
    return new CollectionTreeNode (dtmModel, this);
  }
}