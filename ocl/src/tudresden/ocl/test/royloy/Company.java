// FILE: d:/java/classes/royloy/Company.java

package tudresden.ocl.test.royloy;
import java.util.*;

public class Company extends RLObject
{
  
  public String description;
  
  public int numberOfEmployees;

  // Associations

  public Person manager;

  /**
     @element-type Person
  */
  public HashSet employees=new HashSet();
  
  public Company(String description, Person manager)
  {
    this.description=description;
    this.manager=manager;
  }

  public Person getOldestEmployee() {
  return null;
  }

  public int getOldestEmployeeAge() {
  return 0;
  }
  
  public String toString()
  {
    return super.toString()+'['+description+']';
  }
  
  public boolean assert()
  {
    return true;
  }
  
} /* end class Company */

