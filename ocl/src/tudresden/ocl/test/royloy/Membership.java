// FILE: a:/royloy/Membership.java

package tudresden.ocl.test.royloy;
import java.util.*;
import tudresden.ocl.lib.*;

public class Membership extends RLObject {

  // Attributes

  // Associations
  /**
 *
 */
    public ServiceLevel actualLevel;
  /**
 *
 */
    public CustomerCard card;
  /**
 *
 */
    public LoyaltyAccount myLoyaltyAccount;
  /**
 *
 */
    public LoyaltyProgram program;
  /**
 *
 */
    public Customer myCustomer;

  // Operations
  public boolean assert() {
    return true;
  }

} /* end class Membership */

