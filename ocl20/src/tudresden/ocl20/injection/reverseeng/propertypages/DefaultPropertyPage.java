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
 * DefaultPropertyPage.java
 *
 * Created on 13. September 2000, 11:30
 */
 
package tudresden.ocl20.injection.reverseeng.propertypages;

import tudresden.ocl20.injection.reverseeng.propertypages.events.*;

import javax.swing.*;
import javax.swing.event.*; // For EventListenerList

/** 
  * Default implementation of the property page interface.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 1.0
  */
public class DefaultPropertyPage extends Object implements PropertyPage {

  private String m_sTitle;
  private String m_sToolTip;
  private Icon m_iIcon;
  private JComponent m_jcComponent;
  private boolean m_fEnabled;
  
  protected EventListenerList m_ellListeners = new EventListenerList();
  
  /**
    * Creates new DefaultPropertyPage. The default property page displays a disabled label
    * saying "<No Properties>" under a title of "No Properties".
    */
  public DefaultPropertyPage() {
    this ("No Properties", null, null, new JLabel ("<No properties>"), false);
  }
  
  /**
    * Creates new enabled DefaultPropertyPage with a component, but no title, icon or tool tip.
    *
    * @param jcComponent the component to be displayed as the property page.
    */
  public DefaultPropertyPage (JComponent jcComponent) {
    this (null, null, null, jcComponent, true);
  }

  /**
    * Creates new DefaultPropertyPage with a component, but no title, icon or tool tip.
    *
    * @param jcComponent the component to be displayed as the property page.
    * @param fEnabled if false, the property page will be initially disabled.
    */
  public DefaultPropertyPage (JComponent jcComponent, boolean fEnabled) {
    this (null, null, null, jcComponent, fEnabled);
  }
  
  /**
    * Creates new enabled DefaultPropertyPage with a component and title, but no icon or tool tip.
    *
    * @param sTitle the title of the property page.
    * @param jcComponent the component to be displayed as the property page.
    */
  public DefaultPropertyPage (String sTitle, JComponent jcComponent) {
    this (sTitle, null, null, jcComponent, true);
  }

  /**
    * Creates new DefaultPropertyPage with a component and title, but no icon or tool tip.
    *
    * @param sTitle the title of the property page.
    * @param jcComponent the component to be displayed as the property page.
    * @param fEnabled if false, the property page will be initially disabled.
    */
  public DefaultPropertyPage (String sTitle, JComponent jcComponent, boolean fEnabled) {
    this (sTitle, null, null, jcComponent, fEnabled);
  }
  
  /**
    * Creates new enabled DefaultPropertyPage with a component, title and icon, but no tool tip.
    *
    * @param sTitle the title of the property page.
    * @param iIcon the icon to be associated with the property page.
    * @param jcComponent the component to be displayed as the property page.
    */
  public DefaultPropertyPage (String sTitle, Icon iIcon, JComponent jcComponent) {
    this (sTitle, null, iIcon, jcComponent, true);
  }

  /**
    * Creates new DefaultPropertyPage with a component, title and icon, but no tool tip.
    *
    * @param sTitle the title of the property page.
    * @param iIcon the icon to be associated with the property page.
    * @param jcComponent the component to be displayed as the property page.
    * @param fEnabled if false, the property page will be initially disabled.
    */
  public DefaultPropertyPage (String sTitle, Icon iIcon, JComponent jcComponent, boolean fEnabled) {
    this (sTitle, null, iIcon, jcComponent, fEnabled);
  }

  /**
    * Creates new enabled DefaultPropertyPage with a component, title, icon and tool tip.
    *
    * @param sTitle the title of the property page.
    * @param sToolTip the tool tip to be associated with the property page.
    * @param iIcon the icon to be associated with the property page.
    * @param jcComponent the component to be displayed as the property page.
    */
  public DefaultPropertyPage (String sTitle, String sToolTip, Icon iIcon, JComponent jcComponent) {
    this (sTitle, sToolTip, iIcon, jcComponent, true);
  }

  /**
    * Creates new DefaultPropertyPage with a component, title and icon, and tool tip.
    *
    * @param sTitle the title of the property page.
    * @param sToolTip the tool tip to be associated with the property page.
    * @param iIcon the icon to be associated with the property page.
    * @param jcComponent the component to be displayed as the property page.
    * @param fEnabled if false, the property page will be initially disabled.
    */
  public DefaultPropertyPage (String sTitle, String sToolTip, Icon iIcon, JComponent jcComponent, boolean fEnabled) {
    super();
    
    setTitle (sTitle);
    setToolTip (sToolTip);
    setIcon (iIcon);
    setComponent (jcComponent);
    setEnabled (fEnabled);
  }
  
  /** 
    * Return the icon to be displayed in the property page's tab.
    */
  public Icon getIcon() {
    return m_iIcon;
  }
  
  /**
    * Set the icon to be associated with this property page.
    */
  public void setIcon (Icon iNewIcon) {
    m_iIcon = iNewIcon;
    
    fireIconChanged();
  }
  
  /** 
    * Return the text to be displayed in the property page's tab.
    */
  public String getTitle() {
    return m_sTitle;
  }
  
  /**
    * Set the title to be associated with this property page.
    */
  public void setTitle (String sNewTitle) {
    m_sTitle = sNewTitle;
    
    fireTitleChanged();
  }
  
  /** 
    * Return the tool tip text to be associated with the property page.
    */
  public String getToolTip() {
    return m_sToolTip;
  }
  
  /**
    * Set the tool tip text to be associated with this property page.
    */
  public void setToolTip (String sNewToolTip) {
    m_sToolTip = sNewToolTip;
    
    fireToolTipChanged();
  }
  
  /**
    * Return the component to be displayed as the property page contents.
    */
  public JComponent getComponent() {
    return m_jcComponent;
  }
  
  /**
    * Set the component to represent this property page.
    */
  public void setComponent (JComponent jcNewComponent) {
    m_jcComponent = jcNewComponent;
    
    fireComponentChanged();
  }
  
  /** 
    * Is the property page enabled?
    */
  public boolean isEnabled() {
    return m_fEnabled;
  }
  
  /**
    * Set the enabled state of this property page.
    */
  public void setEnabled (boolean fEnabled) {
    m_fEnabled = fEnabled;
    
    fireEnabledChanged();
  }
  
  /** 
    * Start notifying the specified listener of events regarding this property page.
    * 
    * @param ppl The property page listener to receive the events.
    */
  public void addPropertyPageListener(PropertyPageListener ppl) {
    m_ellListeners.add (PropertyPageListener.class, ppl);
  }
  
  /** 
    * Stop notifying the specified listener of events regarding this property page.
    *
    * @param ppl The property page listener to be removed from the list of evcent receivers.
    */
  public void removePropertyPageListener(PropertyPageListener ppl) {
    m_ellListeners.remove (PropertyPageListener.class, ppl);
  }
  
  /**
    * Only one event is needed per PropertyPage.
    */
  private final PropertyPageEvent m_ppeTheEvent = new PropertyPageEvent (this);
  
  protected void fireComponentChanged() {
    Object[] listeners = m_ellListeners.getListenerList();
    
    // Process the listeners last to first, notifying
    // those that are interested in this event
    for (int i = listeners.length - 2; i >= 0; i -= 2) {
      if (listeners[i] == PropertyPageListener.class) {
        try {
          ((PropertyPageListener) listeners[i + 1]).onComponentChanged (m_ppeTheEvent);
        }
        catch (Throwable t) {
          System.err.println ("Exception occurred while dispatching PropertyPageEvent: \n");
          t.printStackTrace();
        }
      }
    }
  }

  protected void fireEnabledChanged() {
    Object[] listeners = m_ellListeners.getListenerList();
    
    // Process the listeners last to first, notifying
    // those that are interested in this event
    for (int i = listeners.length - 2; i >= 0; i -= 2) {
      if (listeners[i] == PropertyPageListener.class) {
        try {
          ((PropertyPageListener) listeners[i + 1]).onEnabledChanged (m_ppeTheEvent);
        }
        catch (Throwable t) {
          System.err.println ("Exception occurred while dispatching PropertyPageEvent: \n");
          t.printStackTrace();
        }
      }
    }
  }
  
  protected void fireIconChanged() {
    Object[] listeners = m_ellListeners.getListenerList();
    
    // Process the listeners last to first, notifying
    // those that are interested in this event
    for (int i = listeners.length - 2; i >= 0; i -= 2) {
      if (listeners[i] == PropertyPageListener.class) {
        try {
          ((PropertyPageListener) listeners[i + 1]).onIconChanged (m_ppeTheEvent);
        }
        catch (Throwable t) {
          System.err.println ("Exception occurred while dispatching PropertyPageEvent: \n");
          t.printStackTrace();
        }
      }
    }
  }
  
  protected void fireTitleChanged() {
    Object[] listeners = m_ellListeners.getListenerList();
    
    // Process the listeners last to first, notifying
    // those that are interested in this event
    for (int i = listeners.length - 2; i >= 0; i -= 2) {
      if (listeners[i] == PropertyPageListener.class) {
        try {
          ((PropertyPageListener) listeners[i + 1]).onTitleChanged (m_ppeTheEvent);
        }
        catch (Throwable t) {
          System.err.println ("Exception occurred while dispatching PropertyPageEvent: \n");
          t.printStackTrace();
        }
      }
    }
  }
  
  protected void fireToolTipChanged() {
    Object[] listeners = m_ellListeners.getListenerList();
    
    // Process the listeners last to first, notifying
    // those that are interested in this event
    for (int i = listeners.length - 2; i >= 0; i -= 2) {
      if (listeners[i] == PropertyPageListener.class) {
        try {
          ((PropertyPageListener) listeners[i + 1]).onToolTipChanged (m_ppeTheEvent);
        }
        catch (Throwable t) {
          System.err.println ("Exception occurred while dispatching PropertyPageEvent: \n");
          t.printStackTrace();
        }
      }
    }
  }
  /** Invoked by the property page container to indicate removal of the page from the container.
   */
  public void onPropertyPageRemoved(PropertyPageContainer ppcParent) {
  }
}