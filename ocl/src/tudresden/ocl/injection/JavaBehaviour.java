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
   Represents a behavioral feature of a class parsed by the java parser.
   May be either a method or a constructor.
   @see Injector
*/
public abstract class JavaBehaviour extends JavaFeature
{

  /**
     Contains subsequently parameter names and types.
  */
  protected ArrayList parameters=new ArrayList();

  /**
     Contains all names given in the &quot;throws&quot; clause.
  */
  private ArrayList throwables=new ArrayList();
    
  /**
     The method header of this method, 
     exactly as in the input stream, 
     including all typographic extra's (line breaks, comments).
  */
  protected String literal;

  public JavaBehaviour(JavaClass parent, 
                       int modifiers, 
                       String type, 
                       String name) 
    throws InjectorParseException
  {
    // parent must not be null
    super(parent.getFile(), parent, modifiers, type, name);
  }

  public void addParameter(String paramtype, String paramname)
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

  public void setLiteral(String literal)
  {
    if( this.literal!=null || literal==null )
      throw new IllegalArgumentException();
    this.literal=literal;
  }

  public final String getLiteral()
  {
    return literal;
  }
  
  public abstract String getWrappedLiteral();

  /**
     Ignores this information.
     To be overridden by classes needing this information.
     @see JavaConstructor#setLastParameterStart(int)
  */
  public void setLastParameterStart(int pos) {}

  /**
     Ignores this information.
     To be overridden by classes needing this information.
     @see JavaConstructor#setLastParameterEnd(int)
  */
  public void setLastParameterEnd(int pos) {}

  public void printMore(PrintStream o)
  {
    for(Iterator i=parameters.iterator(); i.hasNext(); )
      o.println("    parameter >"+i.next()+"< >"+i.next()+"<");
    for(Iterator i=throwables.iterator(); i.hasNext(); )
      o.println("    throwable >"+i.next()+"<");
    System.out.println("    literal: >"+literal+"<");
    System.out.println("    wrapped: >"+getWrappedLiteral()+"<");
  }

}
