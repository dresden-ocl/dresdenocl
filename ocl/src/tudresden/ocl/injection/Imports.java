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
   Manages the mapping of type names and types.
   This depends on the current package and all 
   imported packages/classes.
*/
public class Imports
{
  private String packagename;

  /**
     Contains all imported packages/classes of this source file 
     in the order of occurence.
     Packages still have the trailing '.*'.
     @element-type String
  */
  private ArrayList imports=new ArrayList();

  public void setPackage(String packagename)
  {
    if(this.packagename!=null)
      throw new RuntimeException();
    this.packagename=packagename;
  }

  public void addImport(String importname)
  {
    imports.add(importname);
  }

  public Class findType(String typename)
  {
    //System.out.println("findtype: >"+typename+"<");

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
    
  public static String extractClassName(String fullclassname)
  {
    int pos=fullclassname.lastIndexOf('.');
    if(pos>=0)
      return fullclassname.substring(pos+1, fullclassname.length());
    else
      return fullclassname;
  }
  
  public static String extractPackageName(String fullclassname)
  {
    int pos=fullclassname.lastIndexOf('.');
    if(pos>=0)
      return fullclassname.substring(0, pos);
    else
      return null;
  }
  
}
