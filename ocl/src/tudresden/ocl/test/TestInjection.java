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
import tudresden.ocl.test.royloy.*;
import tudresden.ocl.injection.lib.Invariant;

public class TestInjection
{
  private boolean strict;
  
  private TestInjection(boolean strict)
  {
    this.strict=strict;
  }
  
  private void doTest() 
  {
    tudresden.ocl.lib.Ocl.TOLERATE_NONEXISTENT_FIELDS=false;
    tudresden.ocl.lib.Ocl.setNameAdapter(new tudresden.ocl.lib.ArgoNameAdapter());
    
    Person p1=new Person("Person1"); add(p1);
    Person p2=new Person("Person2"); add(p2);
    Person p3=new Person("Person3"); add(p3);
    Person p4=new Person("Person4"); add(p4);
    Person p5=new Person("Person5"); add(p5);
    p1.marry(p2);

    Company c1=new Company("Company1", p3); add(c1);
    Company c2=new Company("Company2", p3); add(c2);
    c1.employ(p1);
    c2.employ(p2);
    c2.employ(p3);

    Bank b1=new Bank("Bank1"); add(b1);
    p1.age=1; b1.addCustomer(0, p1);
    p2.age=2; b1.addCustomer(1, p2);
    p3.age=3; b1.addCustomer(2, p3);
    p4.age=4; b1.addCustomer(3, p4);
    p5.age=1;
    
    LoyaltyProgram lp=new LoyaltyProgram(); add(lp);
    Customer cm1=new Customer("Customer1", true);  add(cm1);
    Customer cm2=new Customer("Customer2", false); add(cm2);
    Customer cm3=new Customer("Customer3", true);  add(cm3);
    Customer cm4=new Customer("Customer4", false); add(cm4);
    
    assertAll();
    {
      String x1=p1.name;
      String x2=p2.name;
      p1.name=x1.toLowerCase();
      p2.name=x2.toLowerCase();
      expectViolation("violated ocl invariant 'nameUpperCase' on object 'tudresden.ocl.test.royloy.Person[person1]'.");
      expectViolation("violated ocl invariant 'nameUpperCase' on object 'tudresden.ocl.test.royloy.Person[person2]'.");
      assertAll();
      p1.name=x1;
      p2.name=x2;
    }
    assertAll();
    {
      Person p=c2.manager;
      c2.manager=p1;
      expectViolation("violated ocl invariant 'manager_is_employee' on object 'tudresden.ocl.test.royloy.Company[Company2]'.");
      expectViolation("violated ocl invariant 'manager_is_employee2' on object 'tudresden.ocl.test.royloy.Company[Company2]'.");
      assertAll();
      c2.manager=p;
    }
    assertAll();
    {
      c2.employees.remove(c2.manager);
      c2.manager.employers.remove(c2);
      expectViolation("violated ocl invariant 'numberOfEmployees' on object 'tudresden.ocl.test.royloy.Company[Company2]'.");
      expectViolation("violated ocl invariant 'manager_is_employee' on object 'tudresden.ocl.test.royloy.Company[Company2]'.");
      expectViolation("violated ocl invariant 'manager_is_employee2' on object 'tudresden.ocl.test.royloy.Company[Company2]'.");
      assertAll();
      c2.employees.add(c2.manager);
      c2.manager.employers.add(c2);
    }
    assertAll();
    {
      int age=p3.age;
      p3.age=-3;
      expectViolation("violated ocl invariant 'age_greater_zero' on object 'tudresden.ocl.test.royloy.Person[Person1]'.");
      expectViolation("violated ocl invariant 'age_greater_zero' on object 'tudresden.ocl.test.royloy.Person[Person2]'.");
      expectViolation("violated ocl invariant 'age_greater_zero' on object 'tudresden.ocl.test.royloy.Person[Person3]'.");
      expectViolation("violated ocl invariant 'age_positive' on object 'tudresden.ocl.test.royloy.Person[Person3]'.");
      expectViolation("violated ocl invariant 'age0to199' on object 'tudresden.ocl.test.royloy.Person[Person3]'.");
      expectViolation("violated ocl invariant 'customers_ordered_by_age' on object 'tudresden.ocl.test.royloy.Bank[Bank1]'.");
      expectViolation("violated ocl invariant 'bank_customer2_age' on object 'tudresden.ocl.test.royloy.Bank[Bank1]'.");
      assertAll();
      p3.age=age;
    }
    assertAll();
    {
      b1.customer.put(new Integer(4), p5);
      
      // use the following statement to test the check for 
      // unique map values in 
      // tudresden.ocl.lib.DefaultOclFactory#getRepresentationFor(Object)
      //b1.customer.put(new Integer(5), p5);
      
      expectViolation("violated ocl invariant 'customers_ordered_by_age' on object 'tudresden.ocl.test.royloy.Bank[Bank1]'.");
      assertAll();
      b1.customer.remove(new Integer(4));
    }
    assertAll();
    {
      boolean x=cm3.isMale;
      cm3.isMale=false;
      expectViolation("violated ocl invariant 'title_gender' on object 'tudresden.ocl.test.royloy.Customer[Customer3]'.");
      assertAll();
      cm3.isMale=x;
    }
    assertAll();
    {
      Person x=c2.topTwentyEmployees[0];
      c2.topTwentyEmployees[0]=p5;
      expectViolation("violated ocl invariant 'topTenTwenty' on object 'tudresden.ocl.test.royloy.Company[Company2]'.");
      assertAll();
      c2.topTwentyEmployees[0]=x;
    }
    assertAll();
    
  }
  
  /**
     All objects.
     Has to be a List, so that assertAll() always tests objects
     in the same order.
  */
  private ArrayList allobjects=new ArrayList();
  
  private void add(Object o)
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

  private void assertAll()
  {
    // phase 1: checking invariants lazily.
    ev=ev1;
    for(Iterator i=allobjects.iterator(); i.hasNext(); )
      ((RLObject)i.next()).assert();
    for(Iterator i=allobjects.iterator(); i.hasNext(); )
      ((RLObject)i.next()).assert();
    ensureAllViolations();
    ev=null;

    if(!strict) return;
    
    // phase 2: checking all invariants.
    // must encounter exactly the same violations, 
    // as in phase 1.
    ev=ev2;
    for(Iterator i=Invariant.allInvariants.iterator(); i.hasNext(); )
      ((Invariant)i.next()).invoke();
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
    
    if(ev.contains(m))
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
  
  private void expectViolation(String m)
  {
    if(!strict) return;
    
    //System.out.println("expection :"+m);
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
    
  
  static public TestInjection theInstance;
  
  static public void main(String[] args)
  {
    boolean strict=false;
    if(args.length>0 && args[0].equals("strict"))
      strict=true;
    
    (theInstance=new TestInjection(strict)).doTest();
  }

}
