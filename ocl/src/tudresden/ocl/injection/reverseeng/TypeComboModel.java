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
  
  private static String s_sUnspecified = ">>Unspecified<<";
  
  /** 
    * Creates new TypeComboModel.
    */
  public TypeComboModel() {
    super();
    
    m_lsTypes = new ArrayList();
    
    fillInStandardTypes();
  }
  
  protected void fillInStandardTypes() {
    m_lsTypes.add (s_sUnspecified);
    m_lsTypes.add ("java.lang.Boolean");
    m_lsTypes.add ("java.lang.Byte");
    m_lsTypes.add ("java.lang.Character");
    m_lsTypes.add ("java.lang.Double");
    m_lsTypes.add ("java.lang.Float");
    m_lsTypes.add ("java.lang.Integer");
    m_lsTypes.add ("java.lang.Long");
    m_lsTypes.add ("java.lang.Number");
    m_lsTypes.add ("java.lang.Object");
    m_lsTypes.add ("java.lang.Short");
    m_lsTypes.add ("java.lang.String");
    m_lsTypes.add ("java.lang.StringBuffer");
  }
  
  public void setSelectedItem (Object oSelectedItem) {
    
    if (((String) oSelectedItem) != s_sUnspecified) {
      m_sSelection = (String) oSelectedItem;
    }
    else {
      m_sSelection = null;
    }
    
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