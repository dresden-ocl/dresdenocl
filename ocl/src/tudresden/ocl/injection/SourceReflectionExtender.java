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
     This is the cache for getElementType.
     @see #getElementType
  */
  private HashMap elementtypes=new HashMap();

  /**
     Maps from java.lang.reflect.Field to java.lang.Class.
     This is the cache for getKeyType.
     @see #getKeyType
  */
  private HashMap keytypes=new HashMap();

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
    checkField(field);
    return (Class)elementtypes.get(field);
  }

  public Class getKeyType(Field field)
  {
    Class result=(Class)keytypes.get(field);
    if(result!=null)
      return result;
    checkField(field);
    return (Class)keytypes.get(field);
  }
    
  private void checkField(Field field)
  {
    final Class fieldclass=field.getDeclaringClass();
    if(parsedclasses.contains(fieldclass))
      return;

    //System.out.println("checkField("+field+"):");

    Class declaringclass=fieldclass;
    while(fieldclass.getDeclaringClass()!=null)
      declaringclass=fieldclass.getDeclaringClass();
    
    java.io.InputStream inputstream=
      declaringclass.getResourceAsStream(JavaFile.extractClassName(declaringclass.getName())+".java");
    if(inputstream==null)
    {
      System.out.println("SourceReflectionExtender: source file for "+declaringclass.getName()+" not found.");
      return;
    }
    
    try
    {
      java.io.Reader reader=new java.io.InputStreamReader(inputstream);
      try
      {
        (new Injector(reader, null, 
          new ReflectionConsumer(JavaFile.extractPackageName(declaringclass.getName())))).parseFile();
        reader.close();
      }
      catch(InjectorParseException e)
      {
        reader.close();
        System.out.println(e);
        return;
      }
    }
    catch(java.io.IOException e) { System.out.println(e); return; }
  }

  public String toString()
  {
    return getClass().getName();
  }
  
  final class ReflectionConsumer implements InjectionConsumer
  {
    /**
       The package the source file is expected to be contained in.
       Is null for the root package.
    */
    private String packagename;
    
    private JavaClass current_class=null;
    
    private Class current_classobject=null;
    
    ReflectionConsumer(String packagename)
    {
      this.packagename=packagename;
    }

    public void onPackage(JavaFile javafile) throws InjectorParseException
    {
      if(this.packagename==null)
        throw new InjectorParseException("expected root package, found "+packagename+'.');
      if(!this.packagename.equals(javafile.getPackageName()))
        throw new InjectorParseException("expected package "+this.packagename+", found "+javafile.getPackageName()+'.');
    }
    
    public void onImport(String importname)
    {
    }
  
    public void onClass(JavaClass cc)
    {
      try
      {
        String fullclassname=cc.getFullName();
        current_classobject=Class.forName(fullclassname);
        parsedclasses.add(current_classobject);
        current_class=cc;
      }
      catch(ClassNotFoundException e) { throw new RuntimeException(e.toString()); }
    }
 
    public void onClassEnd(JavaClass cc) throws java.io.IOException
    {
      if(current_class!=cc)
        throw new RuntimeException();
      current_class=current_class.getParent();
      current_classobject=current_classobject.getDeclaringClass();
    }
    
    public void onFileEnd()
    {
    }
  
    public void onBehaviourHeader(JavaBehaviour jb)
    {
    }

    public void onAttributeHeader(JavaAttribute ja)
    {
    }

    public void onClassFeature(JavaFeature cf, String doccomment)
      throws java.io.IOException, InjectorParseException
    {
      if(cf instanceof JavaAttribute)
      {
        String element_type=null;
        String key_type=null;
        if(doccomment!=null)
        {
          element_type=Injector.findDocTag(doccomment, "element-type");
          key_type=    Injector.findDocTag(doccomment, "key-type");
        }
        if(element_type!=null)
        {
          Class c=cf.getFile().findType(element_type);
          //System.out.println("findType(element):"+c);
          if(c!=null)
          {
            try
            {
              //System.out.println("getField: >"+classobject+"< >"+cf.getName()+"<");
              Field f=current_classobject.getDeclaredField(cf.getName());
              elementtypes.put(f,c);
              //System.out.println("SourceReflectionFacade: elementtypes.put("+f+","+c+")");
              if(!java.util.Collection.class.isAssignableFrom(f.getType()) &&
                 !java.util.Map.class.isAssignableFrom(f.getType()) )
                System.out.println("warning: field "+f+" of element-type "+c+" is not a collection/map.");
            }
            catch(NoSuchFieldException e)
            {
              throw new RuntimeException(e.toString());
            }
          }
        }
        if(key_type!=null)
        {
          Class c=cf.getFile().findType(key_type);
          //System.out.println("findType(key):"+c);
          if(c!=null)
          {
            try
            {
              //System.out.println("getField: >"+classobject+"< >"+cf.getName()+"<");
              Field f=current_classobject.getDeclaredField(cf.getName());
              keytypes.put(f,c);
              //System.out.println("SourceReflectionFacade: keytypes.put("+f+","+c+")");
              if(!java.util.Map.class.isAssignableFrom(f.getType()) )
                System.out.println("warning: field "+f+" of key-type "+c+" is not a map.");
            }
            catch(NoSuchFieldException e)
            {
              throw new RuntimeException(e.toString());
            }
          }
        }
      }
    }

    public boolean onDocComment(String doccomment)
    {
      return true;
    }

    public void onFileDocComment(String doccomment)
    {
    }

  }
}
