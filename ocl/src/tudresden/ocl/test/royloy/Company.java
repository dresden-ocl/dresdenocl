
package tudresden.ocl.test.royloy;

import java.util.*;

public class Company extends RLObject
{
  
  public String description;

  /**
     @invariant numberOfEmployees: self.numberOfEmployees=employees->size
  */
  public int numberOfEmployees=0;

  // Associations

  /**
     @invariant manager_is_employee:
        employees->iterate(
          p:Person ; b:Bag(Person)=Bag{} |
          b->including(p)
        )->includes(manager)

     @invariant manager_is_employee2:
        manager.employers->includes(self)
  
     @invariant: 
        manager.oclIsKindOf(Person)
  */
  public Person manager;

  /**
     Test TreeSet working with
     {@link tudresden.ocl.injection.lib.HashModCount}.
     @element-type Person
  */
  public TreeSet employees=new TreeSet();
  
  /**
     @element-type Person
     @invariant topTenTwenty:
        topTenEmployees->first=topTwentyEmployees->first
  */
  public List topTenEmployees=new ArrayList();
  
  public Person[] topTwentyEmployees=new Person[20];
  
  public Company(String description, Person manager)
  {
    this.description=description;
    this.manager=manager;
    employees.add(manager);
    manager.employers.add(this);
    numberOfEmployees=employees.size();
    topTenEmployees.add(manager);
    topTwentyEmployees[0]=manager;
  }

  public Person getOldestEmployee() {
  return null;
  }

  public int getOldestEmployeeAge() {
  return 0;
  }
  
  public void employ(Person p)
  {
    employees.add(p);
    p.employers.add(this);
    numberOfEmployees=employees.size();
  }
  
  public String toString()
  {
    return super.toString()+'['+description+']';
  }
  
  public boolean assertTrue()
  {
    return true;
  }
  
} /* end class Company */

