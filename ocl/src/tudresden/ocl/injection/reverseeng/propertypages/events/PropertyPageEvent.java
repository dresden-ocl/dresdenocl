/*
 * PropertyPageEvent.java
 *
 * Created on 13. September 2000, 11:26
 */
 
package tudresden.ocl.injection.reverseeng.propertypages.events;

import tudresden.ocl.injection.reverseeng.propertypages.*;

import java.util.*;

/** 
  * Event issued by {@link PropertyPage property pages} to indicate changes in their state.
  *
  * @author  sz9
  * @version 1.0
  */
public class PropertyPageEvent extends EventObject {

  /** 
    * Create a new PropertyPageEvent.
    *
    * @param ppSource the source property page.
    */
  public PropertyPageEvent (PropertyPage ppSource) {    
    super (ppSource);
  }
  
  /** Get the property page that issued this event.
    * @return the property page that issued this event.
    */
  public PropertyPage getSourcePage() {
    return (PropertyPage) getSource();
  }
}