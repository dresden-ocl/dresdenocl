// FILE: a:/royloy/Customer.java

package tudresden.ocl.test.royloy;

import java.util.*;
import tudresden.ocl.lib.*;

public class Customer extends RLObject {


  public String name;

  public String title;

  public boolean isMale;

  public Date dateOfBirth;

    public Vector  program=new Vector();

  /**
     @element-type CustomerCard
  */
  public Vector  cards=new Vector();

    public Vector  myMembership=new Vector();

  public boolean assert() {
final OclAnyImpl tuddOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );
final OclSet tuddOclNode1=Ocl.toOclSet(tuddOclNode0.getFeature("cards"));
final OclIterator tuddOclIter0=tuddOclNode1.getIterator();
final OclBooleanEvaluatable tuddOclEval0=new OclBooleanEvaluatable() {
  public OclBoolean evaluate() {
    final OclString tuddOclNode2=Ocl.toOclString(Ocl.toOclAnyImpl(tuddOclIter0.getValue()).getFeature("printedName"));
    final OclString tuddOclNode3=Ocl.toOclString(tuddOclNode0.getFeature("name"));
    final OclBoolean tuddOclNode4=tuddOclNode2.isEqualTo(tuddOclNode3);
    return tuddOclNode4;
  }
};
final OclBoolean tuddOclNode5=tuddOclNode1.forAll(tuddOclIter0, tuddOclEval0);

    return tuddOclNode5.isTrue();
  }

} /* end class Customer */

