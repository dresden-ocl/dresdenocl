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

import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

import tudresden.ocl.injection.reverseeng.propertypages.*;

/** 
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class CollectionTreeNode extends AbstractFeatureTreeNode {
  
  static Icon s_iOK = new javax.swing.ImageIcon (CollectionTreeNode.class.getResource ("resources/collection.gif"));
  static Icon s_iInCompl = new javax.swing.ImageIcon (CollectionTreeNode.class.getResource ("resources/collectionInCompl.gif"));
  
  public CollectionTreeNode (DefaultTreeModel dtmModel, CollectionDescriptor cd) {
    super (dtmModel, cd);
  }

  public Icon getIcon (boolean fExpanded) {
    if (getDescriptor().isIncomplete()) {
      return s_iInCompl;
    }
    else {
      return s_iOK;
    }
  }

  public CollectionDescriptor getDescriptor() {
    return (CollectionDescriptor) getUserObject();
  }

  public List getPropertyPages() {
    List lppReturn = super.getPropertyPages();
    lppReturn.add (new DefaultPropertyPage ("Element type", new TypeEditPage (getDescriptor(), new TypeEditPage.TypeDescriptor() {
      public void onTypeSelectionChanged (String sNewSelection) {
        getDescriptor().setElementType (sNewSelection);
      }
      
      public List getProposedTypes() {
        List lReturn = new LinkedList();
        List lMinima = new LinkedList (RevengGUI.getTheApp().getElementTypeMinima (getDescriptor()));
        
        for (Iterator i = lMinima.iterator(); i.hasNext();) {
          lReturn.add (new TypeEditPage.ProposedType ((String) i.next(),
                                                         new String[] {"Identified as minimal type during runtime type tracing."}));
        }
        
        List lAllTypes = new LinkedList (RevengGUI.getTheApp().getAllElementTypes (getDescriptor()));
        for (Iterator i = lAllTypes.iterator(); i.hasNext();) {
          String sCurrent = (String) i.next();
          int nIdx = lMinima.indexOf (sCurrent);
          
          if (nIdx > -1) {
            ((TypeEditPage.ProposedType) lReturn.get (nIdx)).addReason ("Identified as actual type during runtime type tracing.");
          }
          else {
            lReturn.add (new TypeEditPage.ProposedType (sCurrent,
                                                           new String[] {"Identified as actual type during runtime type tracing."}));
          }
        }

        return lReturn;
      }
      
      public String getCurrentType() {
        return getDescriptor().getElementType();
      }
    })));
    
    return lppReturn;
  }

  public String toString () {      
    CollectionDescriptor cd = getDescriptor();

    return cd.getDisplayName (true) + 
            ((cd.getElementType() != null)?(" : " + cd.getElementType()):(""));
  }
  
  public String getToolTip() {
    return "Collection: " + super.getToolTip();
  }
}