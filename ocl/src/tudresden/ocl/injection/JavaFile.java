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

package tudresden.ocl.injection;

import java.util.*;

/**
   Represents a parsed java file.
   Manages the mapping of type names and types.
   This depends on the current package and all 
   imported packages/classes.
*/
public final class JavaFile
{
  private String packagename;

  /**
     Contains all imported packages/classes of this source file 
     in the order of occurence.
     Packages still have the trailing '.*'.
     @element-type String
  */
  private ArrayList imports=new ArrayList();
  
  /**
     Distiguishes two stages in life cycle of this object:
     getting imports via addImport and finding types via findType.
     @see #addImport
     @see #findType
  */
  private boolean buildStage=true;

  /**
     Sets the package of this file.
     Necessary, since the package is not known at construction time.
     @throws InjectorParseException if called more than once.
  */
  public final void setPackage(String packagename)
    throws InjectorParseException
  {
    if(!buildStage)
      throw new RuntimeException();
    if(this.packagename!=null)
      throw new InjectorParseException("only one package statement allowed.");

    this.packagename=packagename;
  }
  
  /**
     Gets the value of the package statement encountered
     in this java file.
     Is null, if no package statement found.
  */
  public final String getPackageName()
  {
    return packagename;
  }

  /**
     Adds the value of an import statement.
  */
  public final void addImport(String importname)
  {
    if(!buildStage)
      throw new RuntimeException();

    imports.add(importname);
  }

  /**
     Maps type names to types.
     This mapping depends on the import statements encountered
     in this java file.
     Implements Java Language Specification 7.5.3.
  
     Note, that the result depends also on the classes that are
     available (in the CLASSPATH) when running this method.
     Using this method in the ocl injector assumes, 
     that at injection time the same classes are available
     as at compile time of the modified user code.
  */
  public final Class findType(String typename)
  {
    //System.out.println("findtype: >"+typename+"<");
    
    buildStage=false;

    if(packagename==null)
      throw new RuntimeException();

    if(typename.indexOf('.')>=0)
    {
      try
      {
        return Class.forName(typename);
      }
      catch(ClassNotFoundException e) 
      { 
        throw new RuntimeException(e.toString()); 
      }
    }

    try
    {
      return Class.forName(packagename+'.'+typename);
    }
    catch(ClassNotFoundException e) {};

    try
    {
      // see Java Language Specification 7.5.3.
      return Class.forName("java.lang."+typename);
    }
    catch(ClassNotFoundException e) {};
     
    for(Iterator i=imports.iterator(); i.hasNext(); )
    {
      String importString=(String)i.next();
      if(importString.endsWith(".*"))
      {
        String full_element_type=
          importString.substring(0,importString.length()-1)+typename;
        try
        {
          return Class.forName(full_element_type);
        }
        catch(ClassNotFoundException e) {};
      }
      else
      {
        if(extractClassName(importString).equals(typename))
        {
          try
          {
            return Class.forName(importString);
          }
          catch(ClassNotFoundException e) 
          { 
            throw new RuntimeException(e.toString()); 
          }
        }
      }
    }
      
    return null;
  }
    
  /**
     Extracts the class name from a fully qualified class name
     (including package path.)
  */
  public static String extractClassName(String fullclassname)
  {
    int pos=fullclassname.lastIndexOf('.');
    if(pos>=0)
      return fullclassname.substring(pos+1, fullclassname.length());
    else
      return fullclassname;
  }
  
  /**
     Extracts the package path (without trailing dot)
     from a fully qualified class name.
  */
  public static String extractPackageName(String fullclassname)
  {
    int pos=fullclassname.lastIndexOf('.');
    if(pos>=0)
      return fullclassname.substring(0, pos);
    else
      return null;
  }
  
}
