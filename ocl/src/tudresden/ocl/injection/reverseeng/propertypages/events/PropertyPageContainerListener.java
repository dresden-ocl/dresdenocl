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
 * PropertyPageContainerListener.java
 *
 * Created on 13. September 2000, 13:51
 */
 
package tudresden.ocl.injection.reverseeng.propertypages.events;

import tudresden.ocl.injection.reverseeng.propertypages.*;

import java.util.*;

/** 
  * Event interface sourced by {@link PropertyPageContainer}.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 1.0
  */
public interface PropertyPageContainerListener extends EventListener {
  /**
    * Invoked when a page was added to the property page container.
    *
    * @ppe {@link EventObject} specifiying the property page added.
    */
  public void onPropertyPageAdded (PropertyPageContainerEvent ppce);

  /**
    * Invoked when a page was removed from the property page container.
    *
    * @ppe {@link EventObject} specifiying the property page removed.
    */
  public void onPropertyPageRemoved (PropertyPageContainerEvent ppce);
}
