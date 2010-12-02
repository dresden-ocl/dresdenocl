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
 * AbstractFeatureTreeNode.java
 *
 * Created on 11. September 2000, 16:48
 */
 
package tudresden.ocl.injection.reverseeng;

import java.util.*;
import javax.swing.tree.*;

import tudresden.ocl.injection.reverseeng.propertypages.*;

/** 
  * Abstract super class of CollectionTreeNode and MapTreeNode.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public abstract class AbstractFeatureTreeNode extends RevengTreeNode {

  /** Creates new AbstractFeatureTreeNode */
  public AbstractFeatureTreeNode (DefaultTreeModel dtmModel,
                                      AbstractDescriptor ad) {
    super (dtmModel);
    
    setUserObject (ad);
    ad.setAssociatedTreeNode (this);
    
    setAllowsChildren(false);
  }
  
  public void fill() {
    setAllowsChildren (false);

    nodeChanged();
  }
 
  /**
    * Return property pages common to both maps and collections...
    */
  public List getPropertyPages() {
    List lppReturn = new LinkedList();
    lppReturn.add (new DefaultPropertyPage ("General", 
                                                new FeatureOverviewPage ((AbstractDescriptor) getUserObject())) {
      public void onPropertyPageRemoved (PropertyPageContainer ppc) {
        ((FeatureOverviewPage) getComponent()).onFinalRemove();
      }
    });
    
    return lppReturn;
  }
}