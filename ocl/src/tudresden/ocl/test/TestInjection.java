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

package tudresden.ocl.test;

import java.util.*;
import tudresden.ocl.injection.ocl.lib.Invariant;
import tudresden.ocl.test.royloy.RLObject;

abstract class TestInjection
{

  private boolean strict;
  
  protected TestInjection(boolean strict)
  {
    this.strict=strict;
    Invariant.allInvariants=new HashSet();
  }
  
  abstract protected void doTest();

  /**
     All objects.
     Has to be a List, so that assertAll() always tests objects
     in the same order.
  */
  private ArrayList allobjects=new ArrayList();
  
  protected final void add(Object o)
  {
    if(allobjects.contains(o))
      throw new RuntimeException();
    allobjects.add(o);
  }

  private void ensureAllViolations()
  {
    if(!strict) return;

    if(!ev.isEmpty())
    {
      StringBuffer buf=new StringBuffer();
      buf.append("expected violations not encountered in phase ");
      if(ev==ev1)
        buf.append('1');
      else if(ev==ev2)
        buf.append('2');
      else
        throw new RuntimeException();
      buf.append(":\n");
      for(Iterator i=ev.iterator(); i.hasNext(); )
      {
        buf.append("    ");
        buf.append((String)i.next());
        buf.append('\n');
      }
      error(buf.toString());
      ev.clear();
    }
  }

  protected final void assertAll()
  {
    // phase 1: checking invariants lazily.
    ev=ev1;
    for(Iterator i=allobjects.iterator(); i.hasNext(); )
      ((RLObject)i.next()).assertTrue();
    for(Iterator i=allobjects.iterator(); i.hasNext(); )
      ((RLObject)i.next()).assertTrue();
    ensureAllViolations();
    ev=null;

    if(!strict) return;

    // phase 2: checking all invariants.
    // must encounter exactly the same violations,
    // as in phase 1.
    ev=ev2;
    Invariant.checking_flag=true;
    for(Iterator i=Invariant.allInvariants.iterator(); i.hasNext(); )
      ((Invariant)i.next()).invoke();
    Invariant.checking_flag=false;
    ensureAllViolations();
    ev=null;
  }

  /**
     Contains all expected constraint violations.
     @element-type String
  */
  private HashSet ev1=new HashSet();
  private HashSet ev2=new HashSet();
  private HashSet ev=null;
  
  public void onViolation(String message)
  {
    if(!strict) throw new RuntimeException();

    String m=stripId(message);
    
    //System.out.println("violation :"+m);
    
    if(ev==null)
    {
      StringBuffer buf=new StringBuffer();
      buf.append("unexpected violation outside phases :\n    encountered: >");
      buf.append(m);
      buf.append("<\n");
      error(buf.toString());
    }
    else if(ev.contains(m))
    {
      ev.remove(m);
    }
    else
    {
      StringBuffer buf=new StringBuffer();
      buf.append("unexpected violation in phase ");
      if(ev==ev1)
        buf.append('1');
      else if(ev==ev2)
        buf.append('2');
      else 
        throw new RuntimeException();
      buf.append(":\n    encountered: >");
      buf.append(m);
      buf.append("<\n");
      for(Iterator i=ev.iterator(); i.hasNext(); )
      {
        buf.append("    expected:    >");
        buf.append((String)i.next());
        buf.append("<\n");
      }
      error(buf.toString());
    }
  }
  
  protected final void expectViolation(String m)
  {
    if(!strict) return;
    
    //System.out.println("expection :"+m);
    if(ev!=null) throw new RuntimeException();
    if(ev1.contains(m)) throw new RuntimeException();
    ev1.add(m);
    if(ev2.contains(m)) throw new RuntimeException();
    ev2.add(m);
  }
  
  private void error(String m)
  {
    System.out.println(m);
    //throw new RuntimeException();
  }
  
  private final String stripId(String s)
  {
    int from=s.indexOf('@');
    if(from<0) 
      throw new RuntimeException();
    int to=s.indexOf('[', from);;
    if(to<0) 
      throw new RuntimeException();
    return s.substring(0, from)+s.substring(to, s.length());
  }
  
}
