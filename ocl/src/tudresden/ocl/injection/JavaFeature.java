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

import java.io.*;
import java.util.*;
import tudresden.ocl.codegen.CodeFragment;

/**
   Represents a java feature.
   May be a class (even an inner class), an attribute or
   a method.
*/
public abstract class JavaFeature
{
  /**
     The java file, which contains this feature.
     Must not be null.
  */
  private JavaFile file;
  
  /**
     The class, which contains this feature.
     Is null for top-level (not inner) classes.
  */
  private JavaClass parent;

  /**
     The modifiers of this feature.
     @see java.lang.reflect.Modifier
  */
  private int modifiers;

  /**
     The return type of the method.
     Is null, if it is a constructor, or a class.
  */
  protected String type;

  protected String name;
  
  public JavaFeature(JavaFile file,
                     JavaClass parent, 
                     int modifiers, 
                     String type, 
                     String name)
    throws InjectorParseException
  {
    this.file=file;
    this.parent=parent;
    this.modifiers=modifiers;
    this.type=type;
    this.name=name;
    
    if(file==null)
      throw new RuntimeException();
    
    if(parent!=null && file!=parent.getFile()) // JavaFile objects are flyweight
      throw new RuntimeException();
    
    int over=modifiers&~getAllowedModifiers();
    if(over!=0)
      throw new InjectorParseException(
        "modifier(s) "+java.lang.reflect.Modifier.toString(over)+
        " not allowed for class feature "+name+
        " of type "+getClass().getName()+'.');
  }

  /**
     Returns the java file, which contains this feature.
     Is never null.
  */
  public final JavaFile getFile()
  {
    return file;
  }

  /**
     Returns the package of the file containing this feature.
  */
  public final String getPackageName()
  {
    return file.getPackageName();
  }

  /**
     Returns the class, which contains this feature.
     Is null for top-level (not inner) classes.
  */
  public final JavaClass getParent()
  {
    return parent;
  }
  
  /**
     Returns the modifiers of this feature.
     @see java.lang.reflect.Modifier
  */
  public final int getModifiers()
  {
    return modifiers;
  }
  
  /**
     Subclasses use this method to specify,
     which modifiers are allowed for the specific kind
     of feature.
  */
  public abstract int getAllowedModifiers();

  public final boolean isStatic()
  {
    return java.lang.reflect.Modifier.isStatic(modifiers);
  }
  
  /**
     The return type of the method.
     Is null, if it is a constructor, or a class.
  */
  public final String getType()
  {
    return type;
  }
  
  public final String getName()
  {
    return name;
  }
  
  public String getNotWrappedName()
  {
    return name;
  }

  public final void print(PrintStream o)
  {
    o.println("  "+JavaFile.extractClassName(getClass().getName())+
              " ("+java.lang.reflect.Modifier.toString(modifiers)+
              ") >"+type+
              "< >"+name+
              "< in >"+(parent==null?"none":parent.getName())+"<");
    printMore(o);
  }

  public void printMore(PrintStream o)
  {
  }

}


