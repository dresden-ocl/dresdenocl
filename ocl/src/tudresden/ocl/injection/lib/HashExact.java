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
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

/**
   Provides static methods for calculating hash codes.
   These methods emulate the <code>hashCode()</code>
   methods of the corresponding classes, but calls
   <code>System.identityHashCode(Object)</code>
   for each contained object.
*/
public class HashExact
{

  public static final String IDENTITY_HASH_CODE=
    "tudresden.ocl.injection.lib.HashExact.identityHashCode";
  
  /**
     Calculates the hash code as defined in 
     {@link List#hashCode()}, but calls 
     <code>System.identityHashCode(Object)</code>
     for each contained object.

     For null arguments it returns the same value as for
     empty lists.
     <p>
     Is also responsible for <code>java.util.Collection</code>.
     There it's not known, whether order and duplicates
     matter, so we are on the save side, if we assume so.
  */
  public static final int identityHashCode(Collection list)
  {
    int hashCode=1;

    if(list==null)
      return hashCode;
    
    for(Iterator i=list.iterator(); i.hasNext(); ) 
    {
      Object obj=i.next();
      hashCode=31*hashCode+(obj==null ? 0 : System.identityHashCode(obj));
    }
    return hashCode;
  }

  /**
     Calculates the hash code equivalent to
     {@link #identityHashCode(List)}, 
     but for arrays instead of lists.

     For null arguments it returns the same value as for
     empty arrays.
  */
  public static final int identityHashCode(Object[] array)
  {
    int hashCode=1;

    if(array==null)
      return hashCode;
    
    for(int i=0; i<array.length; i++) 
    {
      Object obj=array[i];
      hashCode=31*hashCode+(obj==null ? 0 : System.identityHashCode(obj));
    }
    return hashCode;
  }

  /**
     Calculates the hash code as defined in 
     {@link Set#hashCode()}, but calls 
     <code>System.identityHashCode(Object)</code>
     for each contained object.
  
     For null arguments it returns the same value as for
     empty sets.
  */
  public static final int identityHashCode(Set set)
  {
    int hashCode=0;

    if(set==null)
      return hashCode;

    for(Iterator i=set.iterator(); i.hasNext(); ) 
    {
      Object obj=i.next();
      hashCode+=(obj==null ? 0 : System.identityHashCode(obj));
    }
    return hashCode;
  }

  /**
     Calculates the hash code as defined in 
     {@link Map#hashCode()}, but calls 
     <code>System.identityHashCode(Object)</code>
     for each contained object.
     @see Map.Entry#hashCode()
  
     For null arguments it returns the same value as for
     empty maps.
  */
  public static final int identityHashCode(Map map)
  {
    /*
      Implementation note:

      This method promises to be equal to Map.hashCode()
      except of the call to identityHashCode.
    
      For this implementation this is only fulfilled,
      if map.keySet().iterator() and map.values().iterator()
      enumerate the maps elements in the same order.
    
      As far as I understand the specification of java.util.Map,
      this is guaranteed. If not, a RuntimeException is thrown.
    
      If you are desperatly looking for runtime efficiency, you
      may disable the checks below.
    */
    int hashCode=0;

    if(map==null)
      return hashCode;

    Iterator ikey=map.keySet().iterator();
    Iterator ival=map.values().iterator();
    while(ikey.hasNext()) 
    {
      Object key=ikey.next();
      Object val=ival.next();
      hashCode+=
        (key==null ? 0 : System.identityHashCode(key)) ^
        (val==null ? 0 : System.identityHashCode(val));
      if(map.get(key)!=val) throw new RuntimeException();
    }
    if(ival.hasNext()) throw new RuntimeException();
    return hashCode;
  }

}
