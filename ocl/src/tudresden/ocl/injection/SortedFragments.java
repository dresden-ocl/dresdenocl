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

import java.util.Vector;
import java.util.Enumeration;
import tudresden.ocl.codegen.CodeFragment;

public class SortedFragments
{
  Vector inv=new Vector();
  Vector post=new Vector();
  Vector pre=new Vector();
  Vector transfer=new Vector();
  Vector preparation=new Vector();

  public void print(java.io.PrintStream o)
  {
    for(Enumeration e=inv.elements(); e.hasMoreElements(); )
    {
      System.out.println("inv:");
      printFragment((CodeFragment)(e.nextElement()), o);
    }
    for(Enumeration e=post.elements(); e.hasMoreElements(); )
    {
      System.out.println("post:");
      printFragment((CodeFragment)(e.nextElement()), o);
    }
    for(Enumeration e=pre.elements(); e.hasMoreElements(); )
    {
      System.out.println("pre:");
      printFragment((CodeFragment)(e.nextElement()), o);
    }
    for(Enumeration e=transfer.elements(); e.hasMoreElements(); )
    {
      System.out.println("transfer:");
      printFragment((CodeFragment)(e.nextElement()), o);
    }
    for(Enumeration e=preparation.elements(); e.hasMoreElements(); )
    {
      System.out.println("preparation:");
      printFragment((CodeFragment)(e.nextElement()), o);
    }
  }

  public static void printFragment(CodeFragment f, java.io.PrintStream o)
  {
    o.print("getConstrainedType() ");
    o.println(f.getConstrainedType());
    o.print("getConstrainedOperation() ");
    o.println(f.getConstrainedOperation());
    o.print("getName() ");
    o.println(f.getName());
    o.print("getKind() ");
    o.println(f.getKind());
    o.print("getResultVariable()" );
    o.println(f.getResultVariable());
    o.println("getCode()");
    o.println(f.getCode());
  }

}
