// FILE: a:/royloy/Transaction.java

package tudresden.ocl.test.royloy;
import java.util.*;
import tudresden.ocl.lib.*;


public class Transaction extends RLObject {

  // Attributes
  public int points;

  public Date date;

  // Associations
  /**
 *
 */
    public CustomerCard card;
  /**
 *
 */
    public Service myService;
  /**
 *
 */
    public LoyaltyAccount myLoyaltyAccount;

  // Operations

  public LoyaltyProgram program() {
    LoyaltyProgram ret=myLoyaltyAccount.myMembership.program;

    // post: result=self.card.membership.program
    OclRoot result=Ocl.getFor(ret);
    OclAnyImpl self=(OclAnyImpl)Ocl.getFor(this);
    OclBoolean p1=result.isEqualTo(
      self.getFeature("card").getFeature("membership").getFeature("program")
    );

    if (p1.isUndefined() || !p1.isTrue()) {
      System.out.println("LoyaltyProgram.program() postcondition violated: "+p1);
    }

    return ret;
  }

  public boolean assert() {
    return true;
  }

} /* end class Transaction */

