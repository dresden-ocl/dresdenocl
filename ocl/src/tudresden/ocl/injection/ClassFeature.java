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

public class ClassFeature
{
  public static final String WRAPPER_SUFFIX="_wrappedbyocl";

  private int modifiers;

  /**
     The return type of the method.
     Is null, if is is a constructor.
  */
  private String type;

  private String name;
  private int name_end;

  /**
     Contains parameter types. Is null for attributes.
     Has the same size as paramnames.
       @see paramnames
  */
  private Vector paramtypes=null;

  /**
     Contains parameter names. Is null for attributes.
     Has the same size as paramtypes.
       @see paramtypes
  */
  private Vector paramnames=null;

  /**
     Contains all names given in the "throws" clause.
     Is null for attributes.
  */
  private Vector throwables=null;

  String literal;

  private String element_type=null;

  public ClassFeature(int modifiers, String type, String name, int name_end)
  {
    this.modifiers=modifiers;
    this.type=type;
    this.name=name;
    this.name_end=name_end;
  }

  public void setMethod()
  {
    paramtypes=new Vector();
    paramnames=new Vector();
    throwables=new Vector();
  }

  public boolean isMethod()
  {
    return paramtypes!=null;
  }

  public final void addParameter(String paramtype, String paramname)
  {
    paramtypes.addElement(paramtype);
    paramnames.addElement(paramname);
  }

  public final void addThrowable(String throwable)
  {
    throwables.addElement(throwable);
  }

  public final void setLiteral(String literal)
  {
    if(this.literal!=null)
      throw new IllegalArgumentException();
    this.literal=literal;
  }

  public final String getLiteral()
  {
    return literal;
  }

  public final void setElementType(String element_type)
  {
    if(this.element_type!=null)
      throw new IllegalArgumentException();
    this.element_type=element_type;
  }

  public final boolean isConstructor()
  {
    return type==null;
  }

  public final String getWrappedName()
  {
    if(name.endsWith(WRAPPER_SUFFIX))
      return name;
    else
      return name+WRAPPER_SUFFIX;
  }

  public final String getNotWrappedName()
  {
    if(name.endsWith(WRAPPER_SUFFIX))
      return name.substring(0, name.length()-WRAPPER_SUFFIX.length());
    else
      return name;
  }

  public final String getWrappedLiteral()
  {
    if(name.endsWith(WRAPPER_SUFFIX))
      return literal;
    else
      return
        literal.substring(0, name_end)+
        WRAPPER_SUFFIX+
        literal.substring(name_end, literal.length());
  }

  public final String getNotWrappedLiteral()
  {
    if(name.endsWith(WRAPPER_SUFFIX))
      return
        literal.substring(0, name_end-name.length())+
        literal.substring(name_end, literal.length());
    else
      return literal;
  }

  public static final String OCL_AUTHOR="ocl injector";

  public final void writeWrapper(Writer o) throws IOException
  {
    o.write("/**\n    A wrapper for checking ocl constraints.\n    Generated automatically, DO NOT CHANGE!\n      @author ");
    o.write(OCL_AUTHOR);
    o.write("\n      @see #");
    o.write(getWrappedName());
    o.write('(');
    for(int i=0; i<paramtypes.size(); i++)
    {
      if(i>0) o.write(", ");
      o.write((String)(paramtypes.elementAt(i)));
    }
    o.write(")\n  */");
    String modifierString=
      java.lang.reflect.Modifier.toString(
        modifiers&~java.lang.reflect.Modifier.ABSTRACT);
    if(modifierString.length()>0)
    {
      o.write(modifierString);
      o.write(' ');
    }
    if(type!=null)
    {
      o.write(type);
      o.write(' ');
    }
    o.write(getNotWrappedName());
    o.write('(');
    for(int i=0; i<paramtypes.size(); i++)
    {
      if(i>0) o.write(", ");
      o.write(paramtypes.elementAt(i).toString());
      o.write(' ');
      o.write((String)(paramnames.elementAt(i)));
    }
    o.write(')');
    if(throwables.size()>0)
    {
      o.write(" throws ");
      for(int i=0; i<throwables.size(); i++)
      {
        if(i>0) o.write(", ");
        o.write(throwables.elementAt(i).toString());
      }
    }
    o.write("\n  {\n");
    if(!isConstructor())
    {
      o.write("    ");
      o.write(Injector.INV_METHOD);
      o.write("();\n");
      o.write("    // some codefragments pre\n");
    }
    o.write("    ");
    if(!isConstructor() && !"void".equals(type))
    {
      o.write(type);
      o.write(" result=");
    }
    o.write(getWrappedName());
    o.write('(');
    for(int i=0; i<paramnames.size(); i++)
    {
      if(i>0) o.write(", ");
      o.write((String)(paramnames.elementAt(i)));
    }
    o.write(");\n");
    o.write("    ");
    o.write(Injector.INV_METHOD);
    o.write("();\n");
    o.write("    // some codefragments post\n");
    if(!isConstructor() && !"void".equals(type))
      o.write("    return result;\n");
    o.write("  }");
  }

  public void writeElementChecker(Writer o) throws IOException
  {
    o.write("    for(int i=0; i<");
    o.write(name);
    o.write(".size(); i++)\n      if(!(");
    o.write(name);
    o.write(".elementAt(i) instanceof ");
    o.write(element_type);
    o.write("))\n        throw new RuntimeException();\n");
  }

  public final void print(PrintStream o)
  {
    o.println("  "+(isMethod()?"method":"attribute")+" ("+java.lang.reflect.Modifier.toString(modifiers)+") >"+type+"< >"+name+"<");
    if(isMethod())
    {
      for(int i=0; i<paramtypes.size(); i++)
        o.println("    parameter >"+paramtypes.elementAt(i)+"< >"+paramnames.elementAt(i)+"<");
      for(int i=0; i<throwables.size(); i++)
        o.println("    throwable >"+throwables.elementAt(i)+"<");
    }
    System.out.println("    literal: >"+literal+"<"+name_end);
    System.out.println("    wrapped: >"+getWrappedLiteral()+"<");
    System.out.println("    notwrap: >"+getNotWrappedLiteral()+"<");
  }
}


