// FILE: d:/java/classes/royloy/Company.java

package tudresden.ocl.test.royloy;
import java.util.*;

public class Company {

  public int numberOfEmployees;

  // Associations
  /**
 *
 */
    protected Person manager;

  /**
     @element-type Person
  */
  protected HashSet  employees;

  public Person getOldestEmployee() {
  return null;
  }

  public int getOldestEmployeeAge() {
  return 0;
  }
} /* end class Company */

