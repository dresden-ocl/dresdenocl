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

public abstract class ClassFeature
{
  private String classname;

  private int modifiers;

  /**
     The return type of the method.
     Is null, if is is a constructor.
  */
  protected String type;

  protected String name;
  
  public ClassFeature(String classname, 
                      int modifiers, 
                      String type, 
                      String name)
    throws InjectorParseException
  {
    this.classname=classname;
    this.modifiers=modifiers;
    this.type=type;
    this.name=name;
    
    int over=modifiers&~getAllowedModifiers();
    if(over!=0)
      throw new InjectorParseException(
        "modifier(s) "+java.lang.reflect.Modifier.toString(over)+
        " not allowed for class feature "+name+
        " of type "+getClass().getName()+'.');
  }
  
  public final String getClassName()
  {
    return classname;
  }
  
  public final int getModifiers()
  {
    return modifiers;
  }
  
  public abstract int getAllowedModifiers();

  public final boolean isStatic()
  {
    return java.lang.reflect.Modifier.isStatic(modifiers);
  }
  
  public final String getType()
  {
    return type;
  }
  
  public final String getName()
  {
    return name;
  }

  public final void print(PrintStream o)
  {
    o.println("  "+SourceReflectionExtender.extractClassName(getClass().getName())+" ("+java.lang.reflect.Modifier.toString(modifiers)+
              ") >"+type+
              "< >"+name+"<");
    printMore(o);
  }

  public void printMore(PrintStream o)
  {
  }

}


