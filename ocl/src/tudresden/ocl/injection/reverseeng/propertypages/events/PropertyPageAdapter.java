/*
 * PropertyPageAdapter.java
 *
 * Created on 13. September 2000, 11:24
 */
 
package tudresden.ocl.injection.reverseeng.propertypages.events;

import tudresden.ocl.injection.reverseeng.propertypages.*;

/** 
  * Empty method body adapter of {@link PropertyPageListener}
  * @author  sz9
  * @version 
  */
public class PropertyPageAdapter implements PropertyPageListener {

  /** Creates new PropertyPageAdapter */
  public PropertyPageAdapter() {
    super();
  }
  
  /** Invoked when the icon of a property page changes.
   * @param ppe {@link EventObject} describing the {@link PropertyPage} that changed.
   */
  public void onIconChanged(PropertyPageEvent ppe) {
  }
  
  /** Invoked when the title of a property page changes.
   * @param ppe {@link EventObject} describing the {@link PropertyPage} that changed.
   */
  public void onTitleChanged(PropertyPageEvent ppe) {
  }
  
  /** Invoked when the tool tip associated with a property page changes.
   * @param ppe {@link EventObject} describing the {@link PropertyPage} that changed.
   */
  public void onToolTipChanged(PropertyPageEvent ppe) {
  }
  
  /** Invoked when the component that represents a property page has changed.
   *
   * <p>This event will not be invoked, when merely the data displayed in
   * the component or subcomponents of it have changed.</p>
   * @param ppe {@link EventObject} describing the {@link PropertyPage} that changed.
   */
  public void onComponentChanged(PropertyPageEvent ppe) {
  }
  
  /** Invoked when the enabled state of a property page has changed.
   * @param ppe {@link EventObject} describing the {@link PropertyPage} that changed.
   */
  public void onEnabledChanged(PropertyPageEvent ppe) {
  }
}