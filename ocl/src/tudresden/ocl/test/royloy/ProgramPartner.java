// FILE: a:/royloy/ProgramPartner.java

package tudresden.ocl.test.royloy;
import java.util.*;
import tudresden.ocl.lib.*;

public class ProgramPartner extends RLObject {

  public int numberOfCustomers;

    public Vector  myLoyaltyProgram=new Vector();

  /**
     @element-type Service
  */
  public Vector  deliveredServices=new Vector();

  // Operations
  public boolean assert() {
    return true;
  }

} /* end class ProgramPartner */

