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
import java.util.HashSet;
import java.util.TreeSet;
import java.lang.reflect.Field;

/**
   Yes, I know, it's a hack.
*/

public class HashModCount
{

  public static final int identityHashCode(Collection collection)
  {
    return (collection==null || collection.isEmpty()) ? -1 : getModCountHash(collection);
  }

  public static final int identityHashCode(Object[] array)
  {
    return array==null ? 0 : HashExact.identityHashCode(array);
  }

  public static final int identityHashCode(Map map)
  {
    return (map==null || map.isEmpty()) ? -1 : getModCountHash(map);
  }

  private static final int getModCountHash(Object o)
  {
    // Both HashSet and TreeSet are implemented with a backing
    // map of the corresponding type. So the modification counter
    // has to be looked up in the backing map.
    if(o instanceof HashSet)
    {
      try
      {
        Field f=HashSet.class.getDeclaredField("map");
        f.setAccessible(true);
        o=f.get(o);
      }
      catch(NoSuchFieldException e)   { throw new RuntimeException(e.toString()); }
      catch(IllegalAccessException e) { throw new RuntimeException(e.toString()); };
    }
    else if(o instanceof TreeSet)
    {
      try
      {
        Field f=TreeSet.class.getDeclaredField("m");
        f.setAccessible(true);
        o=f.get(o);
      }
      catch(NoSuchFieldException e)   { throw new RuntimeException(e.toString()); }
      catch(IllegalAccessException e) { throw new RuntimeException(e.toString()); };
    }

    for(Class c=o.getClass(); c!=java.lang.Object.class; c=c.getSuperclass())
    {
      try
      {
        Field f=c.getDeclaredField("modCount");
        f.setAccessible(true);
        Integer i=(Integer)(f.get(o));
        return i.intValue() ^ System.identityHashCode(o);
      }
      catch(NoSuchFieldException e) {}
      catch(IllegalAccessException e) { throw new RuntimeException(e.toString()); };
    }
    throw new RuntimeException("No Modification Counter found in object >"+o+"< of class >"+o.getClass()+"<. Cannot use --modcount-hash.");
  }

}
