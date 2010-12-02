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

import tudresden.ocl.test.royloy.*;

public class TestInjectionRoyloy extends TestInjection
{
	
	private TestInjectionRoyloy(boolean strict)
	{
		super(strict);
	}
	
	protected void doTest()
	{
		tudresden.ocl.lib.Ocl.TOLERATE_NONEXISTENT_FIELDS=false;
		
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
		
		LoyaltyProgram lp=new LoyaltyProgram("LoyaltyProgram1"); add(lp);
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
			expectViolation("violated ocl invariant 'nameUpperCase' on object 'tudresden.ocl.test.royloy.Person[Person1]'.");
			expectViolation("violated ocl invariant 'nameUpperCase' on object 'tudresden.ocl.test.royloy.Person[Person2]'.");
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
			long age=p3.age;
			p3.age=-3;
			expectViolation("violated ocl invariant 'age_greater_zero' on object 'tudresden.ocl.test.royloy.Person[Person1]'.");
			expectViolation("violated ocl invariant 'age_greater_zero' on object 'tudresden.ocl.test.royloy.Person[Person2]'.");
			expectViolation("violated ocl invariant 'age_greater_zero' on object 'tudresden.ocl.test.royloy.Person[Person3]'.");
			expectViolation("violated ocl invariant 'ageGreaterEqual0' on object 'tudresden.ocl.test.royloy.Person[Person3]'.");
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
		{
			expectViolationNow("violated ocl precondition 'index_positive' on object 'tudresden.ocl.test.royloy.Customer[Customer1]' operation 'ignoreCard(index : Integer ; c : CustomerCard ; resultat : Integer)'.");
			cm1.ignoreCard(-1, null, 1);
			assertNoViolationsNowLeft();
			expectViolationNow("violated ocl postcondition 'result_is_one' on object 'tudresden.ocl.test.royloy.Customer[Customer1]' operation 'ignoreCard(index : Integer ; c : CustomerCard ; resultat : Integer)'.");
			cm1.ignoreCard(0, null, 2);
			assertNoViolationsNowLeft();
			expectViolationNow("violated ocl invariant 'title_gender' on object 'tudresden.ocl.test.royloy.Customer[Customer1]'.");
			expectViolationNow("violated ocl postcondition 'title_unchanged' on object 'tudresden.ocl.test.royloy.Customer[Customer1]' operation 'ignoreCard(index : Integer ; c : CustomerCard ; resultat : Integer)'.");
			final String title = cm1.title;
			cm1.ignoreCard(42, null, 1);
			cm1.title = title;
			assertNoViolationsNowLeft();
		}
		assertAll();
		
	}
	
	static public TestInjectionRoyloy theInstance;
	
	static public void main(String[] args)
	{
		boolean strict=false;
		if(args.length>0 && args[0].equals("strict"))
			strict=true;
		
		(theInstance=new TestInjectionRoyloy(strict)).doTest();
	}
	
}
