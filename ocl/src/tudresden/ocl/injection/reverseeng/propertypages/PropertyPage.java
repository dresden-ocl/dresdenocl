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
  * @author  sz9
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
