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
 * PropertyPageAdapter.java
 *
 * Created on 13. September 2000, 11:24
 */
 
package tudresden.ocl.injection.reverseeng.propertypages.events;

import tudresden.ocl.injection.reverseeng.propertypages.*;

/** 
  * Empty method body adapter of {@link PropertyPageListener}
  * @author  sz9 (Steffen Zschaler)
  * @version 1.0
  */
public class PropertyPageAdapter implements PropertyPageListener {

  /** Creates new PropertyPageAdapter */
  public PropertyPageAdapter() {
    super();
  }
  
  /** Invoked when the icon of a property page changes.
   * @param ppe {@link java.util.EventObject} describing the {@link PropertyPage} that changed.
   */
  public void onIconChanged(PropertyPageEvent ppe) {
  }
  
  /** Invoked when the title of a property page changes.
   * @param ppe {@link java.util.EventObject} describing the {@link PropertyPage} that changed.
   */
  public void onTitleChanged(PropertyPageEvent ppe) {
  }
  
  /** Invoked when the tool tip associated with a property page changes.
   * @param ppe {@link java.util.EventObject} describing the {@link PropertyPage} that changed.
   */
  public void onToolTipChanged(PropertyPageEvent ppe) {
  }
  
  /** Invoked when the component that represents a property page has changed.
   *
   * <p>This event will not be invoked, when merely the data displayed in
   * the component or subcomponents of it have changed.</p>
   * @param ppe {@link java.util.EventObject} describing the {@link PropertyPage} that changed.
   */
  public void onComponentChanged(PropertyPageEvent ppe) {
  }
  
  /** Invoked when the enabled state of a property page has changed.
   * @param ppe {@link java.util.EventObject} describing the {@link PropertyPage} that changed.
   */
  public void onEnabledChanged(PropertyPageEvent ppe) {
  }
}