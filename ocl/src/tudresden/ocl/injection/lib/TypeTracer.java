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

import java.util.*;

public final class TypeTracer
{

  public static final String TRACE_TYPES="tudresden.ocl.injection.lib.TraceTypes.traceTypes";
  
  private static final HashMap element_types=new HashMap();
  private static final HashMap key_types=new HashMap();

  public static void traceTypes(String attr, Collection c)
  {
    traceTypes(attr, c.iterator(), element_types);
  }

  public static void traceTypes(String attr, Map m)
  {
    traceTypes(attr, m.values().iterator(), element_types);
    traceTypes(attr, m.keySet().iterator(), key_types);
  }

  private static void traceTypes(String attr, Iterator i, HashMap types)
  {
    TypeTracer tt=(TypeTracer)(types.get(attr));
    if(tt==null)
    {
      tt=new TypeTracer(attr);
      types.put(attr, tt);
    }
    tt.traceTypes(i);
  }

  private String attr;
  
  private TypeTracer(String attr) 
  {
    this.attr=attr;
  }

  /**
     Contains all types ever been in the collection.
  */
  private HashSet types_all=new HashSet();
  
  /**
     Contains the minimal subset of {@see #types_all},
     whose subtype extension contains all elements
     of {@see #types_all}.
  */
  private HashSet types_minima=new HashSet();
  
  private void traceTypes(Iterator iterator)
  {
    boolean usedLog=false;
    
    outerloop: 
    while(iterator.hasNext())
    {
      Class c=iterator.next().getClass();
      if(types_all.contains(c))
        continue outerloop;
      
      types_all.add(c);
      log.print('*');
      log.print(attr);
      log.print(' ');
      log.println(c.getName());
      usedLog=true;
      
      boolean gets_in=false;
      for(Iterator i=types_minima.iterator(); i.hasNext(); )
      {
        Class i_minima=(Class)i.next();
        if(i_minima.isAssignableFrom(c)) // i_minima is supertype of c
        {
          if(gets_in)
            throw new RuntimeException();
          continue outerloop;
        }
        if(c.isAssignableFrom(i_minima)) // i_minima is subtype of c
        {
          i.remove();
          log.print('-');
          log.print(attr);
          log.print(' ');
          log.println(i_minima.getName());
          gets_in=true;
        }
      }
      
      types_minima.add(c);
      log.print('+');
      log.print(attr);
      log.print(' ');
      log.println(c.getName());
    }
    if(usedLog)
      log.flush();
  }
  
  
  private static java.io.PrintStream log=null;
  
}
