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
import java.lang.reflect.Field;

/**
   Yes, I know, it's a hack.
*/

public class HashModCount
{

  public static final int identityHashCode(Collection collection)
  {
    return (collection==null || collection.isEmpty()) ? -1 : getModCount(collection);
  }

  public static final int identityHashCode(Object[] array)
  {
    return array==null ? 0 : HashExact.identityHashCode(array);
  }

  public static final int identityHashCode(Map map)
  {
    return (map==null || map.isEmpty()) ? -1 : getModCount(map);
  }

  private static final int getModCount(Object o)
  {
    for(Class c=o.getClass(); c!=java.lang.Object.class; c=c.getSuperclass())
    {
      try
      {
        Field f=c.getDeclaredField("modCount");
        f.setAccessible(true);
        Integer i=(Integer)(f.get(o));
        return i.intValue();
      }
      catch(NoSuchFieldException e) {}
      catch(IllegalAccessException e) { throw new RuntimeException(e.toString()); };
    }
    throw new RuntimeException("Fuck!");
  }

}
