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

public final class ClassFeature
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
  
  /**
     The index of the end of the feature name in literal.
     Needed for getWrappedLiteral() and getNotWrappedLiteral().
       @see getWrappedLiteral()
       @see getNotWrappedLiteral()
       @see literal
  */
  private int name_end;

  /**
     Contains parameter names and types. Is null for attributes.
       @see paramnames
  */
  private ArrayList parameters=null;

  /**
     Contains all names given in the "throws" clause.
     Is null for attributes.
  */
  private ArrayList throwables=null;

  private String literal;

  /**
     The content of the @element-type tag in the doccomment connected to this class feature.
  */
  private String element_type=null;

  public ClassFeature(String classname, int modifiers, String type, String name, int name_end)
  {
    this.classname=classname;
    this.modifiers=modifiers;
    this.type=type;
    this.name=name;
    this.name_end=name_end;
  }
  
  public String getClassName()
  {
    return classname;
  }
  
  public int getModifiers()
  {
    return modifiers;
  }
  
  public boolean isStatic()
  {
    return java.lang.reflect.Modifier.isStatic(modifiers);
  }
  
  public String getType()
  {
    return type;
  }
  
  public String getName()
  {
    return name;
  }

  public final void setMethod()
  {
    parameters=new ArrayList();
    throwables=new ArrayList();
  }

  public final boolean isMethod()
  {
    return parameters!=null;
  }

  public final void addParameter(String paramtype, String paramname)
  {
    parameters.add(paramtype);
    parameters.add(paramname);
  }
  
  public final Iterator getParameters()
  {
    return parameters.iterator();
  }

  public final void addThrowable(String throwable)
  {
    throwables.add(throwable);
  }
  
  public final Iterator getThrowables()
  {
    return throwables.iterator();
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
  
  public final String getElementType()
  {
    return element_type;
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
        literal.substring(0, name_end-WRAPPER_SUFFIX.length())+
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
    for(Iterator i=parameters.iterator(); i.hasNext(); )
    {
      buf.append((String)i.next());
      i.next();
      if(i.hasNext()) buf.append(',');
    }
    buf.append(')');
    return buf.toString();
  }
    
  public final void print(PrintStream o)
  {
    o.println("  "+(isMethod()?"method":"attribute")+" ("+java.lang.reflect.Modifier.toString(modifiers)+") >"+type+"< >"+name+"<");
    if(isMethod())
    {
      for(Iterator i=parameters.iterator(); i.hasNext(); )
        o.println("    parameter >"+i.next()+"< >"+i.next()+"<");
      for(Iterator i=throwables.iterator(); i.hasNext(); )
        o.println("    throwable >"+i.next()+"<");
    }
    System.out.println("    signatr: >"+getSignature()+"<");
    System.out.println("    literal: >"+literal+"<"+name_end);
    System.out.println("    wrapped: >"+getWrappedLiteral()+"<");
    System.out.println("    notwrap: >"+getNotWrappedLiteral()+"<");
  }
}


