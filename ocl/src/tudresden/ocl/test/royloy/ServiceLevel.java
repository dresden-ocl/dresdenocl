// FILE: a:/royloy/ServiceLevel.java

package tudresden.ocl.test.royloy;
import java.util.*;
import tudresden.ocl.lib.*;

public class ServiceLevel extends RLObject {

  // Attributes
  public String name;

  // Associations

  /**
     @invariant: 
        loyaltyProgram.partners->includes(service.programPartner)
  */
  public LoyaltyProgram loyaltyProgram;

  /**
     @element-type Membership
  */
  public Vector  membership=new Vector();

  public Service service;

  // Operations
  public boolean assertTrue() {
    return true;
  }

} /* end class ServiceLevel */

