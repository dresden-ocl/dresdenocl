// FILE: a:/royloy/CustomerCard.java

package tudresden.ocl.test.royloy;
import java.util.*;
import tudresden.ocl.lib.*;

public class CustomerCard extends RLObject {

  // Attributes

  public boolean valid;

  public Date validFrom;

  public Date validThru;

  public int color;

  public static int COLOR_GOLD;

  public static int COLOR_SILVER;

  public String printedName;

  // Associations

  public Customer owner;

  public Membership myMembership;

  public Vector  transactions=new Vector();

  // Operations
  public boolean assert() {
    return true;
  }

} /* end class CustomerCard */

