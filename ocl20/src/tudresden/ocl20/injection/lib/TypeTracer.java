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

import java.util.*;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

/**
   Contains the log of object types found in a collection.

   Additionally provides static methods 
   {@link #traceTypes(String,Collection)} and
   {@link #traceTypes(String,Map)} to be called from generated code.

   These logs are continuesly written to a log file,
   see {@link #log} for the file format.
*/
public final class TypeTracer
{

  public static final String TRACE_TYPES="tudresden.ocl20.injection.lib.TypeTracer.traceTypes";
  
  private static final HashMap element_types=new HashMap();
  private static final HashMap key_types=new HashMap();

  public static void traceTypes(String attr, Collection c)
  {
    if(c!=null)
      traceTypes(attr, c.iterator(), element_types, "element-type");
  }

  public static void traceTypes(String attr, Map m)
  {
    if(m!=null)
    {
      traceTypes(attr, m.values().iterator(), element_types, "element-type");
      traceTypes(attr, m.keySet().iterator(), key_types, "key-type");
    }
  }

  private static void traceTypes(String attr, Iterator i, HashMap types, String kind)
  {
    TypeTracer tt=(TypeTracer)(types.get(attr));
    if(tt==null)
    {
      tt=new TypeTracer(attr);
      types.put(attr, tt);
    }
    tt.traceTypes(i, kind);
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
     Contains the minimal subset of {@link #types_all},
     whose subtype extension contains all elements
     of {@link #types_all}.
  */
  private HashSet types_minima=new HashSet();
  
  /**
     A tabulator.
     Used for the file format written to {@link #log}.
  */
  public static final char SEPARATOR='\t';
  
  /**
     An askeriks ('*').
     Used for the file format written to {@link #log}.
  */
  public static final char ADD_ALL='*';
  
  /**
     The plus character.
     Used for the file format written to {@link #log}.
  */
  public static final char ADD_MINIMA='+';
  
  /**
     The minus character.
     Used for the file format written to {@link #log}.
  */
  public static final char REMOVE_MINIMA='-';
  
  /**
     The # character.
     Used for the file format written to {@link #log}.
  */
  public static final char COMMENT='#';
     
  
  private void traceTypes(Iterator iterator, String kind)
  {
    boolean usedLog=false;
    
    outerloop: 
    while(iterator.hasNext())
    {
      Class c=iterator.next().getClass();
      if(types_all.contains(c))
        continue outerloop;
      
      types_all.add(c);
      log.print(ADD_ALL);
      log.print(attr);
      log.print(SEPARATOR);
      log.print(kind);
      log.print(SEPARATOR);
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
          log.print(REMOVE_MINIMA);
          log.print(attr);
          log.print(SEPARATOR);
          log.print(kind);
          log.print(SEPARATOR);
          log.println(i_minima.getName());
          gets_in=true;
        }
      }
      
      types_minima.add(c);
      log.print(ADD_MINIMA);
      log.print(attr);
      log.print(SEPARATOR);
      log.print(kind);
      log.print(SEPARATOR);
      log.println(c.getName());
    }
    if(usedLog)
      log.flush();
  }
  

  /**
     The information gathered by all type tracers is 
     written to this stream.
  
     Is set to the file referred by the property 
     tudresden.ocl20.injection.lib.TypeTracer.log if this 
     property is defined, otherwise points to
     <code>System.out</code>.
  
     May also be set by the user code.
  
  
     A description of the file format follows:
     <ul>
     <li>
       A line starts with a single character having 
       the following meanings:
       <dl>
       <dt>{@link #ADD_ALL}<dt>      <dd>a type was added to {@link #types_all}.</dd>
       <dt>{@link #ADD_MINIMA}<dt>   <dd>a type was added to {@link #types_minima}.</dd>
       <dt>{@link #REMOVE_MINIMA}<dt><dd>a type was removed from {@link #types_minima}.</dd>
       <dt>{@link #COMMENT}<dt>      <dd>the line should be ignored.</dd>
       </dl>
     </li>
     <li>
       This character is followed by the name of the attribute, as produced 
       by {@link tudresden.ocl20.injection.JavaAttribute#getFullDocName()}.
       The name is terminated by a {@link #SEPARATOR}.
     </li>
     <li>
       The next item is the kind of types, which is either 
       &quot;element-type&quot; or &quot;key-type&quot;.
       The kind of types is terminated by a {@link #SEPARATOR} again.
     </li>
     <li>
       The last item on line is the fully qualified name of the
       element type encountered, as produced by 
       {@link java.lang.Class#getName()}.
       The element type is terminated by the line end.
     </li>
     </ul>
  */
  private static PrintStream log=System.out;
      
  static
  {
    String logfile=
      System.getProperty("tudresden.ocl20.injection.lib.TypeTracer.log");
    if(logfile!=null)
    {
      try
      {
        log=new PrintStream(new FileOutputStream(logfile));
      }
      catch(FileNotFoundException e)
      {
        System.out.println("could not open type tracer log: "+e);
      }
    }
  }
  
}
