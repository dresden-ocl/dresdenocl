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

public class JavaMethod extends JavaFeature
{
  public static final String WRAPPER_SUFFIX="_wrappedbyocl";
  
  /**
     The index of the end of the feature name in literal.
     Needed for getWrappedLiteral() and getNotWrappedLiteral().
       @see #getWrappedLiteral()
       @see #getNotWrappedLiteral()
       @see #literal
  */
  private int name_end;

  private String literal;

  /**
     Contains parameter names and types.
  */
  private ArrayList parameters=new ArrayList();

  /**
     Contains all names given in the "throws" clause.
  */
  private ArrayList throwables=new ArrayList();
    

  public JavaMethod(JavaClass parent, 
                    int modifiers, 
                    String type, 
                    String name, 
                    int name_end)
    throws InjectorParseException
  {
    super(parent, modifiers, type, name);
    this.name_end=name_end;
    if(parent==null)
      throw new RuntimeException();
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

  /**
     A cache for getSignature.
     @see #getSignature
  */
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
    
  public final int getAllowedModifiers()
  {
    return
      java.lang.reflect.Modifier.PUBLIC |
      java.lang.reflect.Modifier.PROTECTED |
      java.lang.reflect.Modifier.PRIVATE |
      java.lang.reflect.Modifier.FINAL |
      java.lang.reflect.Modifier.STATIC |
      java.lang.reflect.Modifier.ABSTRACT |
      java.lang.reflect.Modifier.NATIVE |
      java.lang.reflect.Modifier.SYNCHRONIZED;
  }

  public final void printMore(PrintStream o)
  {
    for(Iterator i=parameters.iterator(); i.hasNext(); )
      o.println("    parameter >"+i.next()+"< >"+i.next()+"<");
    for(Iterator i=throwables.iterator(); i.hasNext(); )
      o.println("    throwable >"+i.next()+"<");
    System.out.println("    signatr: >"+getSignature()+"<"+name_end);
    System.out.println("    literal: >"+literal+"<");
    System.out.println("    wrapped: >"+getWrappedLiteral()+"<");
    System.out.println("    notwrap: >"+getNotWrappedLiteral()+"<");
  }

}
