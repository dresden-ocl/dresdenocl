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

package tudresden.ocl.injection.lib;

import java.util.Collection;
import java.util.Map;

public class HashSize
{

  public static final String IDENTITY_HASH_CODE=
    "tudresden.ocl.injection.lib.HashSize.identityHashCode";
  
  public static final int identityHashCode(Collection collection)
  {
    return collection==null ? 0 : collection.size();
  }

  public static final int identityHashCode(Object[] array)
  {
    return array==null ? 0 : array.length;
  }

  public static final int identityHashCode(Map map)
  {
    return map==null ? 0 : map.size();
  }
  
}
