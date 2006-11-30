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
 * PropertyPageContainerEvent.java
 *
 * Created on 13. September 2000, 13:56
 */
 
package tudresden.ocl20.injection.reverseeng.propertypages.events;

import tudresden.ocl20.injection.reverseeng.propertypages.*;

import java.util.*;

/** 
  * EventObject describing an added or removed property page.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 1.0
  */
public class PropertyPageContainerEvent extends EventObject {

  /**
    * The page in question.
    */
  private PropertyPage m_ppPage;
  
  /** 
    * Creates new PropertyPageContainerEvent 
    */
  public PropertyPageContainerEvent (PropertyPageContainer ppcSource,
                                          PropertyPage ppPage) {
    super (ppcSource);
    
    m_ppPage = ppPage;
  }
  
  /**
    * Return the property page container that issued this event.
    */
  public PropertyPageContainer getSourceContainer() {
    return (PropertyPageContainer) getSource();
  }
  
  /**
    * Return the property page this event is about.
    */
  public PropertyPage getAffectedPage() {
    return m_ppPage;
  }
}