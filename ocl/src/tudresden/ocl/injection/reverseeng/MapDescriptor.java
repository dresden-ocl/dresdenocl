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
 * MapDescriptor.java
 *
 * Created on 7. August 2000, 17:49
 */
 
package tudresden.ocl.injection.reverseeng;

import tudresden.ocl.injection.*;

import javax.swing.tree.*;

/** 
  * Descriptor for a class attribute of map type.
  *
  * <p>These descriptors are maintained by {@link AnalysisConsumer} in its m_lcdMaps member.</p>
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class MapDescriptor extends AbstractDescriptor {

  /** 
    * Creates new MapDescriptor 
    */
  public MapDescriptor (AnalysisConsumer acOwner,
                          JavaClass jcParent,
                          String sName,
                          String sType,
                          String sComment,
                          int nCommentID) {
    super (acOwner, jcParent, sName, sType, sComment, nCommentID);
  }
  
  public void setKeyType (String sKeyType) {
    super.setKeyType (sKeyType);
  }
 
  public String toString () {
    return "Map<" + getKeyType () + " -> " + getElementType () + "> " + getName () + " at comment ID " + getCommentID ();
  }
      
  public boolean isIncomplete() {
    return ((super.isIncomplete()) ||
             (getKeyType() == null));
  }
  
  public boolean isCollection() {
    return false;
  }
  
  public RevengTreeNode createTreeNode (DefaultTreeModel dtmModel) {
    return new MapTreeNode (dtmModel, this);
  }
}