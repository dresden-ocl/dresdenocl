/*
 * PropertyPageContainerEvent.java
 *
 * Created on 13. September 2000, 13:56
 */
 
package tudresden.ocl.injection.reverseeng.propertypages.events;

import tudresden.ocl.injection.reverseeng.propertypages.*;

import java.util.*;

/** 
  * EventObject describing an added or removed property page.
  *
  * @author  sz9
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