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
 * PropertyPane.java
 *
 * Created on 11. September 2000, 16:02
 */ 
package tudresden.ocl.injection.reverseeng;

import javax.swing.*;

/** 
  * Information about a page that can be displayed in the right-hand-side property pane container.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class PropertyPage {
  
  protected Icon m_iIcon;
  protected String m_sTitle;
  protected String m_sToolTip;
  protected JComponent m_jcComponent;
  
  private boolean m_fEnabled = true;
  
  public PropertyPage (JComponent jcComponent) {
    this (null, jcComponent.getName(), null, jcComponent);
  }
  
  public PropertyPage (String sTitle, JComponent jcComponent) {
    this (null, sTitle, null, jcComponent);
  }
  
  public PropertyPage (Icon iIcon, String sTitle, JComponent jcComponent) {
    this (iIcon, sTitle, null, jcComponent);
  }
  
  public PropertyPage (Icon iIcon, String sTitle, String sToolTip, JComponent jcComponent) {
    super();
    
    m_iIcon = iIcon;
    m_sTitle = sTitle;
    m_sToolTip = sToolTip;
    m_jcComponent = jcComponent;
  }
  
  /**
    * Return an icon identifying the page. May be null.
    */
  public Icon getIcon() {
    return m_iIcon;
  }
  
  /**
    * Return a title for the page. May be null.
    */
  public String getTitle() {
    return m_sTitle;
  }
  
  /**
    * Return a tooltip for the page. May be null.
    */
  public String getToolTip() {
    return ((m_sToolTip != null)?
             (m_sToolTip):
             (m_sTitle));
  }
  
  /**
    * Return the component representing the page. Must not be null.
    */
  public JComponent getComponent() {
    return m_jcComponent;
  }
  
  public void setEnabled (boolean fEnabled) {
    m_fEnabled = fEnabled;
    getComponent().setEnabled (m_fEnabled);
  }
}