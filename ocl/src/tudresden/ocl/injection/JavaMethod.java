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
import java.io.PrintStream;

/**
   Represents a method of a class parsed by the java parser.
   @see Injector
*/
public class JavaMethod extends JavaBehaviour
{
  public static final String WRAPPER_SUFFIX="_wrappedbyocl";
  
  /**
     The index of the end of the feature name in literal.
     Needed for getWrappedLiteral() and getNotWrappedLiteral(),
     if this is not a constructor.
       @see #getWrappedLiteral()
       @see #getNotWrappedLiteral()
       @see #literal
  */
  private int name_end;
  
  
  public JavaMethod(JavaClass parent, 
                    int modifiers, 
                    String type, 
                    String name, 
                    int name_end)
    throws InjectorParseException
  {
    // parent must not be null
    super(parent, modifiers, type, 
      (name.endsWith(WRAPPER_SUFFIX)) ?
      name.substring(0, name.length()-WRAPPER_SUFFIX.length()):
      name
      );

    if(type==null)
      throw new RuntimeException();

    this.name_end=name_end;
  }

  public final boolean isConstructor()
  {
    return false;
  }
  
  public final void setLiteral(String literal)
  {
    int wsl=WRAPPER_SUFFIX.length();
    if(WRAPPER_SUFFIX.equals(literal.substring(name_end-wsl, name_end)))
    {
      literal=
        literal.substring(0, name_end-wsl)+
        literal.substring(name_end, literal.length());
      name_end-=wsl;
    }
    super.setLiteral(literal);
  }

  public final String getWrappedName()
  {
    return name+WRAPPER_SUFFIX;
  }

  public final String getNotWrappedName()
  {
    return name;
  }
  
  public final String getWrappedLiteral()
  {
    return
        literal.substring(0, name_end)+
        WRAPPER_SUFFIX+
        literal.substring(name_end, literal.length());
  }

  public final String getNotWrappedLiteral()
  {
    return literal;
  }

  /**
     A cache for getSignature.
     @see #getSignature
  */
  private String signature;

  /**
     Returns the signature of this method.
     Is compatible to 
     {@see tudresden.ocl.codegen.CodeFragment#getConstrainedOperation()}.
  */
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
    super.printMore(o);
    System.out.println("    signatr: >"+getSignature()+"<("+name_end+')');
  }

}
