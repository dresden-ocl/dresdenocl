// FILE: a:/royloy/Membership.java

package tudresden.ocl.test.royloy;
import java.util.*;
import tudresden.ocl.lib.*;

public class Membership extends RLObject {

  // Attributes

  // Associations
  /**
     @invariant actualLevel:
        program.serviceLevel->includes(actualLevel)
  */
  public ServiceLevel actualLevel;
  
  public CustomerCard card;

  /**
     @invariant: 
        loyaltyAccount.points>=0 or loyaltyAccount->isEmpty
  */
  public LoyaltyAccount loyaltyAccount;
  
  public LoyaltyProgram program;

  /**
     @invariant membership_back:
        customer.cards.membership->includes(self)
  */
  public Customer customer;

  // Operations
  public boolean assertTrue() {
    return true;
  }

} /* end class Membership */

