// FILE: a:/royloy/ServiceLevel.java

package tudresden.ocl.test.royloy;
import java.util.*;
import tudresden.ocl.lib.*;

public class ServiceLevel extends RLObject {

  // Attributes
  public String name;

  // Associations
  /**
 *
 */
    public LoyaltyProgram myLoyaltyProgram;
  /**
 * @element-type Membership
 */
    public Vector  myMembership=new Vector();
  /**
 *
 */
    public Service myService;

  // Operations
  public boolean assert() {
    return true;
  }

} /* end class ServiceLevel */

