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
 * TypeComboModel.java
 *
 * Created on 14. August 2000, 15:18
 */
 
package tudresden.ocl.injection.reverseeng;

import java.util.*;
import javax.swing.*;

/** 
  * A ComboBoxModel for the type drop down boxes.
  *
  * <p>In addition to a selection of default Java Types, the list of the model can also contain a number
  * of other types that seem to be appropriate for the map or collection.</p>
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class TypeComboModel extends AbstractListModel implements ComboBoxModel {

  private String m_sSelection;
  private List m_lsTypes;
  
  /** 
    * Creates new TypeComboModel.
    */
  public TypeComboModel() {
    super();
    
    m_lsTypes = new ArrayList();
    
    fillInStandardTypes();
  }
  
  protected void fillInStandardTypes() {
    m_lsTypes.add ("boolean");
    m_lsTypes.add ("char");
    m_lsTypes.add ("int");
    m_lsTypes.add ("float");
    m_lsTypes.add ("double");
    m_lsTypes.add ("java.lang.String");
    m_lsTypes.add ("java.lang.Object");
  }
  
  public void setSelectedItem (Object oSelectedItem) {
    m_sSelection = (String) oSelectedItem;
    
    fireContentsChanged (this, 0, 0);
  }
  
  public Object getSelectedItem() {
    return m_sSelection;
  }
  
  public int getSize() {
    return m_lsTypes.size();
  }
  
  public Object getElementAt (int nIdx) {
    return m_lsTypes.get (nIdx);
  }
}