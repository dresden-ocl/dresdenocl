// FILE: d:/java/classes/royloy/Person.java

package tudresden.ocl.test.royloy;
import java.util.*;

public class Person extends RLObject
{

  // Attributes
  public String name;
  public int age=18;
  public boolean isMarried=false;
  public boolean isUnemployed=false;

  // Associations
  /**
     @element-type Company
  */
  public HashSet managedCompanies=new HashSet();

  /**
     @element-type Company
  */
  public HashSet employers=new HashSet();

  /**
 *
 */
    protected Person wife;
  /**
 *
 */
    protected Person husband;

  public Person(String name)
  {
    this.name=name;
  }
  
  // Operations
  public float getIncomeAfterTax(float tax) {
  return 0.0f;
  }
  
  public boolean assert()
  {
    return true;
  }
  
} /* end class Person */

