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

import java.lang.reflect.*;
import java.util.*;

public class SourceReflectionExtender implements tudresden.ocl.check.types.ReflectionExtender
{
  /**
     Maps from java.lang.reflect.Field to java.lang.Class.
     This is the cache for the extender query.
  */
  private HashMap elementtypes=new HashMap();
  
  /**
     Contains all classes, for which the source code has been attempted to parse.
     Serves as negative cache for source code or @elementtype tags not found.
  */
  private HashSet parsedclasses=new HashSet();
  
  
  public Class getElementType(Field field)
  {
    Class result=(Class)elementtypes.get(field);
    if(result!=null)
      return result;
    
    final Class fieldclass=field.getDeclaringClass();
    if(parsedclasses.contains(fieldclass))
      return null;

    //System.out.println("getElementType(): "+field);
    if(!java.util.Collection.class.isAssignableFrom(field.getType()))
      System.out.println("warning: "+field+"is field has not a collection type.");
    
    Class declaringclass=fieldclass;
    while(fieldclass.getDeclaringClass()!=null)
      declaringclass=fieldclass.getDeclaringClass();
    
    java.io.InputStream inputstream=
      declaringclass.getResourceAsStream(extractClassName(declaringclass.getName())+".java");
    if(inputstream==null)
    {
      System.out.println("SourceReflectionExtender: source file for "+declaringclass.getName()+" not found.");
      return null;
    }
    
    try
    {
      java.io.Reader reader=new java.io.InputStreamReader(inputstream);
      try
      {
        (new Injector(reader, null, 
          new ReflectionConsumer(extractPackageName(declaringclass.getName())))).parseFile();
        reader.close();
      }
      catch(InjectorParseException e)
      {
        reader.close();
        System.out.println(e);
        return null;
      }
    }
    catch(java.io.IOException e) { System.out.println(e); return null; }
    

    return (Class)elementtypes.get(field);
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
  
  class ReflectionConsumer implements InjectionConsumer
  {
    /**
       The package the source file is expected to be contained in.
       Is null for the root package.
    */
    private String packagename;
    
    /**
       Contains all imported packages of this source file in the order of declaration.
    */
    private ArrayList imports=new ArrayList();
  
    private String classname;
    
    private Class classobject;
    
    ReflectionConsumer(String packagename)
    {
      this.packagename=packagename;
      this.classname=null;
    }

    public void onPackage(String packagename) throws InjectorParseException
    {
      if(this.packagename==null)
        throw new InjectorParseException("expected root package, found "+packagename+'.');
      if(!this.packagename.equals(packagename))
        throw new InjectorParseException("expected package "+this.packagename+", found "+packagename+'.');
    }
    
    public void onImport(String importname)
    {
      imports.add(importname);
    }
  
    public void onClass(String classname)
    {
      try
      {
        String fullclassname= (packagename!=null) ? packagename+'.'+classname : classname;
        classobject=Class.forName(fullclassname);
        parsedclasses.add(classobject);
        this.classname=classname;
      }
      catch(ClassNotFoundException e) { throw new RuntimeException(e.toString()); }
    }
 
    public void onClassEnd(String classname) throws java.io.IOException
    {
      if(this.classname!=classname)
        throw new RuntimeException();
      this.classname=null;
      classobject=null;
    }
  
    private String last_element_type=null;

    private Class findElementType()
    {
      //System.out.println("findelementtype: >"+last_element_type+"<");
      if(last_element_type.indexOf('.')>=0)
      {
        try
        {
          return Class.forName(last_element_type);
        }
        catch(ClassNotFoundException e) { throw new RuntimeException(e.toString()); }
      }

      try
      {
        return Class.forName(packagename+'.'+last_element_type);
      }
      catch(ClassNotFoundException e) {};

      try
      {
        // see Java Language Specification 7.5.3.
        return Class.forName("java.lang."+last_element_type);
      }
      catch(ClassNotFoundException e) {};
      
      for(Iterator i=imports.iterator(); i.hasNext(); )
      {
        String importString=(String)i.next();
        if(importString.endsWith(".*"))
        {
          String full_element_type=
            importString.substring(0,importString.length()-1)+last_element_type;
          try
          {
            return Class.forName(full_element_type);
          }
          catch(ClassNotFoundException e) {};
        }
        else
        {
          if(extractClassName(importString).equals(last_element_type))
          try
          {
            return Class.forName(importString);
          }
          catch(ClassNotFoundException e) { throw new RuntimeException(e.toString()); }
        }
      }
      
      return null;
    }

    public void onClassFeature(ClassFeature cf) throws java.io.IOException
    {
      if(!cf.isMethod() && last_element_type!=null)
      {
        Class c=findElementType();
        //System.out.println("findElementType:"+c);
        if(c!=null)
        {
          try
          {
            //System.out.println("getField: >"+classobject+"< >"+cf.getName()+"<");
            Field f=classobject.getDeclaredField(cf.getName());
            elementtypes.put(f,c);
            //System.out.println("SourceReflectionFacade: put("+f+","+c+")");
          }
          catch(NoSuchFieldException e) { throw new RuntimeException(e.toString()); }
        }
      }
      last_element_type=null;
    }
  
    public boolean onComment(String comment)
    {
      if(comment.startsWith("/**"))
        last_element_type=Injector.findDocTag(comment, "element-type");
      return true;
    }
  
  }
}  
