// FILE: d:/java/classes/de/tudresden/ocl/Person.java

package tudresden.ocl.lib.test;
import java.util.*;

/** this class is for testing only and will be removed
 */
public class Person {

  // Attributes
  /** An attribute that represents ...
   */
  public boolean isMarried;

  /** An attribute that represents ...
   */
  public boolean isUnemployed;

  /** An attribute that represents ...
   */
  public String firstName;

  public int someNumber;

  public int getFive() {
    return 5;
  }

  // Associations

  /**
   *
   */
  public Vector  managedCompanies;

  /**
   *
   */
  public Vector  employer;

  /**
   *
   */
  public Vector myJob;

  // Operations

  public String toString() {
    return "Person<"+firstName+","+someNumber+">";
  }

  public void setJob(Vector v) {
    myJob=v;
  }

} /* end class Person */

