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

import java.io.PrintStream;

/**
   Represents a constructor of a class parsed by the java parser.
   @see Injector
*/
public final class JavaConstructor extends JavaBehaviour
{
  /**
     The index of the start of the last parameter of the 
     parameter list in {@see #literal}.
     Is the index of the last comma, if there is more than one
     parameter or otherwise the index after the opening parent.
  
     Needed for {@see #getWrappedLiteral()} and 
     {@see #getNotWrappedLiteral()}, if this IS a constructor.

     Is initialized to -1.
  */
  private int last_param_start=-1;
     
  /**
     The index of the end of the last parameter of the 
     parameter list in {@see #literal}.
     Is the index of the closing bracket of the parameter list.
  
     Needed for {@see #getWrappedLiteral()} and 
     {@see #getNotWrappedLiteral()}, if this IS a constructor.

     Is initialized to -1.
  */
  private int last_param_end=-1;
     
  private boolean is_wrapped=false;

  public JavaConstructor(JavaClass parent, 
                         int modifiers, 
                         String name) 
    throws InjectorParseException
  {
    super(parent, modifiers, null, name);
    parent.onConstructorAdded();
  }

  public final void addParameter(String paramtype, String paramname)
  {
    if(tudresden.ocl.injection.lib.WrapperDummy.class.getName().equals(paramtype))
      is_wrapped=true;
    else
      super.addParameter(paramtype, paramname);
  }

  public final String getWrappedLiteral()
  {
    String dummytype=tudresden.ocl.injection.lib.WrapperDummy.class.getName();
    int ps=parameters.size();
    int last_param=
      is_wrapped ?
      last_param_start :
      last_param_end;
    if(ps>0)
    {
      return
        literal.substring(0, last_param)+
        ", "+
        dummytype+
        " dummy"+ // parameter name to be removed
        literal.substring(last_param, literal.length());
    }
    else
    {
      return
        literal.substring(0, last_param_start)+
        dummytype+
        " dummy"+ // parameter name to be removed
        literal.substring(last_param, literal.length());
    }
  }

  public final void setLiteral(String literal)
  {
    if(is_wrapped)
    {
      literal=
        literal.substring(0, last_param_start)+
        literal.substring(last_param_end, literal.length());
    }
    super.setLiteral(literal);
  }
  
  /**
     Sets {@see #last_param_start} to the given value.
     @throws RuntimeException if pos is negative.
  */
  public final void setLastParameterStart(int pos)
  {
    if(pos<0)
      throw new RuntimeException();
    last_param_start=pos;
  }

  /**
     Sets {@see #last_param_end} to the given value.
     @throws RuntimeException if pos is negative.
     @throws RuntimeException if called more than once.
  */
  public final void setLastParameterEnd(int pos)
  {
    if(pos<0)
      throw new RuntimeException();
    if(last_param_end>=0)
      throw new RuntimeException();
    last_param_end=pos;
  }

  public final int getAllowedModifiers()
  {
    return
      java.lang.reflect.Modifier.PUBLIC |
      java.lang.reflect.Modifier.PROTECTED |
      java.lang.reflect.Modifier.PRIVATE |
      java.lang.reflect.Modifier.NATIVE |
      java.lang.reflect.Modifier.SYNCHRONIZED;
  }

  public final void printMore(PrintStream o)
  {
    super.printMore(o);
    System.out.println("    ("+last_param_start+'|'+last_param_end+')');
  }

}
