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

import java.util.ArrayList;
import java.util.Iterator;
import tudresden.ocl.codegen.CodeFragment;

public class SortedFragments
{
  ArrayList inv=new ArrayList();
  ArrayList post=new ArrayList();
  ArrayList pre=new ArrayList();
  ArrayList transfer=new ArrayList();
  ArrayList preparation=new ArrayList();
  
  private String constrainedType;
  
  SortedFragments(CodeFragment firstFragment)
  {
    constrainedType=firstFragment.getConstrainedType();
    addFragment(firstFragment);
  }
  
  public void addFragment(CodeFragment fragment)
  {
    if(!constrainedType.equals(fragment.getConstrainedType()))
      throw new IllegalArgumentException();

    switch(fragment.getKind())
    {
    case CodeFragment.INV:         inv.add(fragment);         break;
    case CodeFragment.POST:        post.add(fragment);        break;
    case CodeFragment.PRE:         pre.add(fragment);         break;
    case CodeFragment.TRANSFER:    transfer.add(fragment);    break;
    case CodeFragment.PREPARATION: preparation.add(fragment); break;
    default: 
      throw new RuntimeException();
    }
  }

  public void print(java.io.PrintStream o)
  {
    for(Iterator e=inv.iterator(); e.hasNext(); )
    {
      System.out.println("inv:");
      printFragment((CodeFragment)(e.next()), o);
    }
    for(Iterator e=post.iterator(); e.hasNext(); )
    {
      System.out.println("post:");
      printFragment((CodeFragment)(e.next()), o);
    }
    for(Iterator e=pre.iterator(); e.hasNext(); )
    {
      System.out.println("pre:");
      printFragment((CodeFragment)(e.next()), o);
    }
    for(Iterator e=transfer.iterator(); e.hasNext(); )
    {
      System.out.println("transfer:");
      printFragment((CodeFragment)(e.next()), o);
    }
    for(Iterator e=preparation.iterator(); e.hasNext(); )
    {
      System.out.println("preparation:");
      printFragment((CodeFragment)(e.next()), o);
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
