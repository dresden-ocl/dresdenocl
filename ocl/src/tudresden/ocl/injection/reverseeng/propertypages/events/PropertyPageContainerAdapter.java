/*
 * PropertyPageContainerAdapter.java
 *
 * Created on 13. September 2000, 13:55
 */
 
package tudresden.ocl.injection.reverseeng.propertypages.events;

import tudresden.ocl.injection.reverseeng.propertypages.*;

/** 
  * Empty method body implementation of the PropertyPageContainerListener interface.
  *
  * @author  sz9
  * @version 1.0
  */
public class PropertyPageContainerAdapter implements PropertyPageContainerListener {

  /** Creates new PropertyPageContainerAdapter */
  public PropertyPageContainerAdapter() {
  }
  
  /** Invoked when a page was added to the property page container.
   *
   * @ppe {@link EventObject} specifiying the property page added.
   */
  public void onPropertyPageAdded(PropertyPageContainerEvent ppce) {
  }
  
  /** Invoked when a page was removed from the property page container.
   *
   * @ppe {@link EventObject} specifiying the property page removed.
   */
  public void onPropertyPageRemoved(PropertyPageContainerEvent ppce) {
  }
}