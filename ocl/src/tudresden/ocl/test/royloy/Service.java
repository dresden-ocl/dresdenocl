// FILE: a:/royloy/Service.java

package tudresden.ocl.test.royloy;
import java.util.*;
import tudresden.ocl.lib.*;


public class Service extends RLObject {

  // Attributes
  public boolean condition;

  public int pointsEarned;

  public int pointsBurned;

  public String description;

  // Associations
  /**
 *
 */
    public ProgramPartner myProgramPartner;
  /**
 *
 */
    public ServiceLevel myServiceLevel;
  /**
 *
 */
    public Vector  transactions=new Vector();

  // Operations
  public boolean assert() {
    return true;
  }

} /* end class Service */

