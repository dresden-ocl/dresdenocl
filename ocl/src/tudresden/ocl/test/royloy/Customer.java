// FILE: a:/royloy/Customer.java

package tudresden.ocl.test.royloy;

import java.util.*;
import tudresden.ocl.lib.*;

public class Customer extends RLObject {


  public String name;

  /**
     @invariant title_gender:
        title=(if isMale=true then 'Mr' else 'Ms' endif)
  */
  public String title;

  public boolean isMale;

  public Date dateOfBirth;

    public Vector  program=new Vector();

  /**
     @element-type CustomerCard
  */
  public Vector  cards=new Vector();

  public Vector  membership=new Vector();
    
  public Customer(String name, boolean isMale)
  {
    this.name=name;
    this.title=isMale?"Mr":"Ms";
    this.isMale=isMale;
  }

  public String toString()
  {
    return super.toString()+'['+name+']';
  }
  
  public boolean assert() {
    return true;
  }

} /* end class Customer */

