// FILE: d:/java/classes/royloy/Company.java

package tudresden.ocl.test.royloy;
import java.util.*;

public class Company extends RLObject
{

  public int numberOfEmployees;

  // Associations

  public Person manager;

  /**
     @element-type Person
  */
  public HashSet employees=new HashSet();
  
  public Company(Person manager)
  {
    this.manager=manager;
  }

  public Person getOldestEmployee() {
  return null;
  }

  public int getOldestEmployeeAge() {
  return 0;
  }
  
  public boolean assert()
  {
    return true;
  }
  
} /* end class Company */

