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
 * PropertyPage.java
 *
 * Created on 13. September 2000, 11:07
 */
 
package tudresden.ocl.injection.reverseeng.propertypages;

import tudresden.ocl.injection.reverseeng.propertypages.events.*;

import javax.swing.*;

/** 
  * Features describing a property page.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 1.0
  */
public interface PropertyPage {

  /**
    * Return the icon to be displayed in the property page's tab.
    */
  public Icon getIcon();
  
  /**
    * Return the text to be displayed in the property page's tab.
    */
  public String getTitle();
  
  /**
    * Return the tool tip text to be associated with the property page.
    */
  public String getToolTip();
  
  /**
    * Return the component to be displayed as the property page contents.
    */
  public JComponent getComponent();
  
  /**
    * Is the property page enabled?
    */
  public boolean isEnabled();
  
  /**
    * Start notifying the specified listener of events regarding this property page.
    * 
    * @param ppl The property page listener to receive the events.
    */
  public void addPropertyPageListener (PropertyPageListener ppl);

  /**
    * Stop notifying the specified listener of events regarding this property page.
    * 
    * @param ppl The property page listener to be removed from the list of evcent receivers.
    */
  public void removePropertyPageListener (PropertyPageListener ppl);
}
