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

public class ClassFeature
{
  public static final String WRAPPER_SUFFIX="_wrappedbyocl";
  
  private String classname;

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
  private ArrayList paramtypes=null;

  /**
     Contains parameter names. Is null for attributes.
     Has the same size as paramtypes.
       @see paramtypes
  */
  private ArrayList paramnames=null;

  /**
     Contains all names given in the "throws" clause.
     Is null for attributes.
  */
  private ArrayList throwables=null;

  String literal;

  private String element_type=null;

  public ClassFeature(String classname, int modifiers, String type, String name, int name_end)
  {
    this.classname=classname;
    this.modifiers=modifiers;
    this.type=type;
    this.name=name;
    this.name_end=name_end;
  }

  public void setMethod()
  {
    paramtypes=new ArrayList();
    paramnames=new ArrayList();
    throwables=new ArrayList();
  }

  public boolean isMethod()
  {
    return paramtypes!=null;
  }

  public final void addParameter(String paramtype, String paramname)
  {
    paramtypes.add(paramtype);
    paramnames.add(paramname);
  }

  public final void addThrowable(String throwable)
  {
    throwables.add(throwable);
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

  private String signature;
  public String getSignature()
  {
    if(signature!=null)
      return signature;
    StringBuffer buf=new StringBuffer();
    buf.append(getNotWrappedName());
    buf.append('(');
    for(Iterator i=paramtypes.iterator(); i.hasNext(); )
    {
      buf.append((String)i.next());
      if(i.hasNext()) buf.append(',');
    }
    buf.append(')');
    return buf.toString();
  }
    
  public static final String OCL_AUTHOR="ocl injector";

  public final void writeWrapperInvariant(Writer o) throws IOException
  {
    if(!java.lang.reflect.Modifier.isStatic(modifiers))
    {
      o.write("    ");
      o.write(Injector.INV_METHOD);
      o.write("();\n");
    }
  }

  public final void writeWrapper(Writer o, Hashtable codefragments) throws IOException
  {
    o.write("/**\n    A wrapper for checking ocl constraints.\n    Generated automatically, DO NOT CHANGE!\n      @author ");
    o.write(OCL_AUTHOR);
    o.write("\n      @see #");
    o.write(getWrappedName());
    o.write('(');
    for(Iterator i=paramtypes.iterator(); i.hasNext(); )
    {
      o.write((String)(i.next()));
      if(i.hasNext()) o.write(", ");
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
    Iterator pni=paramnames.iterator();
    for(Iterator i=paramtypes.iterator(); i.hasNext(); )
    {
      o.write((String)i.next());
      o.write(' ');
      o.write((String)pni.next());
      if(i.hasNext()) o.write(", ");
    }
    o.write(')');
    if(throwables.size()>0)
    {
      o.write(" throws ");
      for(Iterator i=throwables.iterator(); i.hasNext(); )
      {
        o.write((String)i.next());
        if(i.hasNext()) o.write(", ");
      }
    }
    o.write("\n  {\n");
    if(!isConstructor())
    {
      writeWrapperInvariant(o);
      if(codefragments!=null)
      {
        SortedFragments sf=(SortedFragments)codefragments.get(classname);
        if(sf!=null)
        {
          for(Iterator i=sf.pre.iterator(); i.hasNext(); )
          {
            CodeFragment cf=(CodeFragment)i.next();
            if(getSignature().equals(cf.getConstrainedOperation()))
            {
              o.write("    {\n");
              o.write(cf.getCode());
              o.write("      if(!");
              o.write(cf.getResultVariable());
              o.write(".isTrue())\n        ");
              o.write(Injector.violationMakro);
              o.write("(\"ocl precondition ");
              o.write(cf.getName());
              o.write(" violated\");\n    }\n");
            }
          }
          for(Iterator i=sf.transfer.iterator(); i.hasNext(); )
          {
            CodeFragment cf=(CodeFragment)i.next();
            if(getSignature().equals(cf.getConstrainedOperation()))
              o.write(cf.getCode());
          }
          for(Iterator i=sf.preparation.iterator(); i.hasNext(); )
          {
            CodeFragment cf=(CodeFragment)i.next();
            if(getSignature().equals(cf.getConstrainedOperation()))
            {
              o.write("    {\n");
              o.write(cf.getCode());
              o.write("    }\n");
            }
          }
        }
      }
    }
    o.write("    ");
    if(!isConstructor() && !"void".equals(type))
    {
      o.write(type);
      o.write(" result=");
    }
    o.write(getWrappedName());
    o.write('(');
    for(Iterator i=paramnames.iterator(); i.hasNext(); )
    {
      o.write((String)i.next());
      if(i.hasNext()) o.write(", ");
    }
    o.write(");\n");
    writeWrapperInvariant(o);
    if(codefragments!=null)
    {
      SortedFragments sf=(SortedFragments)codefragments.get(classname);
      if(sf!=null)
        for(Iterator i=sf.post.iterator(); i.hasNext(); )
        {
          CodeFragment cf=(CodeFragment)i.next();
          if(getSignature().equals(cf.getConstrainedOperation()))
          {
            o.write("    {\n");
            o.write(cf.getCode());
            o.write("      if(!");
            o.write(cf.getResultVariable());
            o.write(".isTrue())\n        ");
            o.write(Injector.violationMakro);
            o.write("(\"ocl postcondition ");
            o.write(cf.getName());
            o.write(" violated\");\n    }\n");
          }
        }
    }
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
    o.write("))\n        ");
    o.write(Injector.violationMakro);
    o.write("(\"element checker failed.\");\n");
  }

  public final void print(PrintStream o)
  {
    o.println("  "+(isMethod()?"method":"attribute")+" ("+java.lang.reflect.Modifier.toString(modifiers)+") >"+type+"< >"+name+"<");
    if(isMethod())
    {
      Iterator pni=paramnames.iterator();
      for(Iterator i=paramtypes.iterator(); i.hasNext(); )
        o.println("    parameter >"+i.next()+"< >"+pni.next()+"<");
      for(Iterator i=throwables.iterator(); i.hasNext(); )
        o.println("    throwable >"+i.next()+"<");
    }
    System.out.println("    signatr: >"+getSignature()+"<");
    System.out.println("    literal: >"+literal+"<"+name_end);
    System.out.println("    wrapped: >"+getWrappedLiteral()+"<");
    System.out.println("    notwrap: >"+getNotWrappedLiteral()+"<");
  }
}


