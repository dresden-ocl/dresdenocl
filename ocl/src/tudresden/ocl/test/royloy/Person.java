// FILE: d:/java/classes/royloy/Person.java

package tudresden.ocl.test.royloy;
import java.util.*;

public class Person {

  // Attributes
  public String name;
  public int age;
  public boolean isMarried;
  public boolean isUnemployed;

  // Associations
  /**
 *
 */
    protected Vector  managedCompanies;
  /**
 *
 */
    protected Vector  employers;
  /**
 *
 */
    protected Person wife;
  /**
 *
 */
    protected Person husband;

  // Operations
  public float getIncomeAfterTax(float tax) {
  return 0.0f;
  }
} /* end class Person */

