/*
Copyright (C) 2000  Ralf Wiebicke

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

package tudresden.ocl20.injection.lib;

import java.util.Collection;
import java.util.Map;
import java.util.Iterator;

public class Check
{
  
  public static final String CHECK_ELEMENT_TYPES="tudresden.ocl20.injection.lib.Check.checkElementTypes";
  public static final String CHECK_KEY_TYPES=    "tudresden.ocl20.injection.lib.Check.checkKeyTypes";
  
  public static final boolean checkElementTypes(Collection c, Class elementtype)
  {
    if(c!=null)
    {
      for(Iterator i=c.iterator(); i.hasNext(); )
        if(!elementtype.isAssignableFrom(i.next().getClass()))
          return false;
    }
    return true;
  }

  public static final boolean checkElementTypes(Map m, Class elementtype)
  {
    if(m!=null)
    {
      for(Iterator i=m.values().iterator(); i.hasNext(); )
        if(!elementtype.isAssignableFrom(i.next().getClass()))
          return false;
    }
    return true;
  }
  
  public static final boolean checkKeyTypes(Map m, Class keytype)
  {
    if(m!=null)
    {
      for(Iterator i=m.keySet().iterator(); i.hasNext(); )
        if(!keytype.isAssignableFrom(i.next().getClass()))
          return false;
    }
    return true;
  }
  
}
