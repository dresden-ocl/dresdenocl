// FILE: a:/royloy/LoyaltyAccount.java

package tudresden.ocl.test.royloy;
import java.util.*;
import tudresden.ocl.lib.*;

public class LoyaltyAccount extends RLObject {

  public int points;

  // Associations
  public Membership membership;

  public Vector  transactions=new Vector();

  // Operations
  public void earn(int i) {
  }

  public void burn(int i) {
  }

  public boolean isEmpty() {
  return false;
  }

  public boolean assertTrue() {
    return true;
  }
} /* end class LoyaltyAccount */

