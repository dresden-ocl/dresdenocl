/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package tudresden.ocl.check.types.testfacade;

import java.util.HashMap;
import tudresden.ocl.check.types.*;
import tudresden.ocl.check.OclTypeException;

public class TestModelFacade implements ModelFacade {

  TestClassifier person, company;
  Any bank;
  TestClassifier loyaltyProgram, customer, membership, customerCard, serviceLevel,
    programPartner, service, loyaltyAccount, transaction, burning, earning, date;
  TestClassifier day, dayType;

  public TestModelFacade() {
    // Person - Company - Model

    person = new TestClassifier("Person");
    company = new TestClassifier("Company");
    bank = new BankClassifier(person);

    person.features.put("age", new TestSFeature("age", Basic.INTEGER));
    person.features.put("name", new TestSFeature("age", Basic.STRING));
    person.features.put("isMarried", new TestSFeature("isMarried", Basic.BOOLEAN));
    person.features.put("isUnemployed", new TestSFeature("isUnemployed", Basic.BOOLEAN));
    person.features.put("getIncomeAfterTax", new TestBFeature("getIncomeAfterTax", Basic.REAL, Basic.REAL));

    company.features.put("numberOfEmployees", new TestSFeature("numberOfEmployees", Basic.INTEGER));
    company.features.put("getOldestEmployee", new TestBFeature("getOldestEmployee", person));
    company.features.put("getOldestEmployeeAge", new TestBFeature("getOldestEmployeeAge", Basic.INTEGER));

    person.assocEnds.put("employers", new TestAssocEnd("employers", company, Multiplicity.ZERO_TO_INF));
    person.assocEnds.put("managedCompanies", new TestAssocEnd("managedCompanies", company, Multiplicity.ZERO_TO_INF));
    person.assocEnds.put("wife", new TestAssocEnd("wife", person, Multiplicity.ZERO_OR_ONE));
    person.assocEnds.put("husband", new TestAssocEnd("husband", person, Multiplicity.ZERO_OR_ONE));

    company.assocEnds.put("employees", new TestAssocEnd("employees", person, Multiplicity.ZERO_TO_INF));
    company.assocEnds.put("manager", new TestAssocEnd("manager", person, Multiplicity.STANDARD));

    // Day - DayType - Model

    day=new TestClassifier("Day");
    dayType=new TestClassifier("DayType");
    dayType.features.put("monday", new TestSFeature("monday", dayType));
    dayType.features.put("tuesday", new TestSFeature("tuesday", dayType));
    dayType.features.put("wednesday", new TestSFeature("wednesday", dayType));
    dayType.features.put("thursday", new TestSFeature("thursday", dayType));
    dayType.features.put("friday", new TestSFeature("friday", dayType));
    dayType.features.put("saturday", new TestSFeature("saturday", dayType));
    dayType.features.put("sunday", new TestSFeature("sunday", dayType));
    dayType.features.put("getFollowingDay", new TestBFeature("getFollowingDay", dayType, dayType));
    day.features.put("type", new TestSFeature("type", dayType));
    day.features.put("isWeekend", new TestSFeature("isWeekend", Basic.BOOLEAN));
    day.features.put("setType", new TestBFeature("setType", null, dayType));

    // Royals and Loyals

    loyaltyProgram=new TestClassifier("LoyaltyProgram");
    customer=new TestClassifier("Customer");
    membership=new TestClassifier("Membership");
    customerCard=new TestClassifier("CustomerCard");
    serviceLevel=new TestClassifier("ServiceLevel");
    programPartner=new TestClassifier("ProgramPartner");
    service=new TestClassifier("Service");
    loyaltyAccount=new TestClassifier("LoyaltyAccount");
    transaction=new TestClassifier("Transaction");
    burning=new TestClassifier("Burning");
    earning=new TestClassifier("Earning");
    date=new TestClassifier("Date");

    loyaltyProgram.features.put("enroll", new TestBFeature("enroll", null));
    programPartner.features.put("numberOfCustomers", new TestSFeature("numberOfCustomers", Basic.INTEGER));
    serviceLevel.features.put("name", new TestSFeature("name", Basic.STRING));
    customer.features.put("name", new TestSFeature("name", Basic.STRING));
    customer.features.put("title", new TestSFeature("title", Basic.STRING));
    customer.features.put("isMale", new TestSFeature("isMale", Basic.BOOLEAN));
    customer.features.put("dateOfBirth", new TestSFeature("dateOfBirth", date));
    customerCard.features.put("valid", new TestSFeature("valid", Basic.BOOLEAN));
    customerCard.features.put("validFrom", new TestSFeature("validFrom", date));
    customerCard.features.put("validThru", new TestSFeature("validThru", date));
    customerCard.features.put("color", new TestSFeature("color", Basic.INTEGER));
    customerCard.features.put("color_gold", new TestSFeature("COLOR_GOLD", Basic.INTEGER));
    customerCard.features.put("color_silver", new TestSFeature("COLOR_SILVER", Basic.INTEGER));
    customerCard.features.put("printedName", new TestSFeature("printedName", Basic.STRING));
    loyaltyAccount.features.put("points", new TestSFeature("points", Basic.INTEGER));
    loyaltyAccount.features.put("earn", new TestBFeature("earn", null, Basic.INTEGER));
    loyaltyAccount.features.put("burn", new TestBFeature("burn", null, Basic.INTEGER));
    loyaltyAccount.features.put("isEmpty", new TestBFeature("isEmpty", Basic.BOOLEAN));
    transaction.features.put("points", new TestSFeature("points", Basic.INTEGER));
    transaction.features.put("date", new TestSFeature("date", date));
    transaction.features.put("program", new TestBFeature("program", loyaltyProgram));
    service.features.put("condition", new TestSFeature("condition", Basic.BOOLEAN));
    service.features.put("pointsEarned", new TestSFeature("pointsEarned", Basic.INTEGER));
    service.features.put("pointsBurned", new TestSFeature("pointsBurned", Basic.INTEGER));
    service.features.put("description", new TestSFeature("description", Basic.STRING));
    date.features.put("isBefore", new TestBFeature("isBefore", Basic.BOOLEAN, date));
    date.features.put("isAfter", new TestBFeature("isAfter", Basic.BOOLEAN, date));
    date.features.put("equals", new TestBFeature("equals", Basic.BOOLEAN, date));
    date.features.put("now", new TestSFeature("now", date));

    programPartner.assocEnds.put("loyaltyProgram", new TestAssocEnd("loyaltyProgram", loyaltyProgram, Multiplicity.ONE_TO_INF));
    programPartner.assocEnds.put("deliveredServices", new TestAssocEnd("deliveredServices", service, Multiplicity.ZERO_TO_INF));
    loyaltyProgram.assocEnds.put("partners", new TestAssocEnd("partners", programPartner, Multiplicity.ONE_TO_INF));
    loyaltyProgram.assocEnds.put("serviceLevel", new TestAssocEnd("serviceLevel", serviceLevel, Multiplicity.ONE_TO_INF));
    loyaltyProgram.assocEnds.put("customer", new TestAssocEnd("customer", customer, Multiplicity.ZERO_TO_INF));
    loyaltyProgram.assocEnds.put("membership", new TestAssocEnd("membership", membership, Multiplicity.ZERO_TO_INF));
    customer.assocEnds.put("program", new TestAssocEnd("program", loyaltyProgram, Multiplicity.ZERO_TO_INF));
    customer.assocEnds.put("membership", new TestAssocEnd("membership", membership, Multiplicity.ZERO_TO_INF));
    customer.assocEnds.put("cards", new TestAssocEnd("cards", customerCard, Multiplicity.ZERO_TO_INF));
    membership.assocEnds.put("program", new TestAssocEnd("program", loyaltyProgram, Multiplicity.STANDARD));
    membership.assocEnds.put("actualLevel", new TestAssocEnd("actualLevel", serviceLevel, Multiplicity.STANDARD));
    membership.assocEnds.put("loyaltyAccount", new TestAssocEnd("loyaltyAccount", loyaltyAccount, Multiplicity.ZERO_OR_ONE));
    membership.assocEnds.put("card", new TestAssocEnd("card", customerCard, Multiplicity.STANDARD));
    membership.assocEnds.put("customer", new TestAssocEnd("customer", customer, Multiplicity.STANDARD));
    customerCard.assocEnds.put("owner", new TestAssocEnd("owner", customer, Multiplicity.STANDARD));
    customerCard.assocEnds.put("membership", new TestAssocEnd("membership", membership, Multiplicity.STANDARD));
    customerCard.assocEnds.put("transactions", new TestAssocEnd("transactions", transaction, Multiplicity.ZERO_TO_INF));
    loyaltyAccount.assocEnds.put("transactions", new TestAssocEnd("transactions", transaction, Multiplicity.ZERO_TO_INF));
    loyaltyAccount.assocEnds.put("membership", new TestAssocEnd("membership", membership, Multiplicity.STANDARD));
    serviceLevel.assocEnds.put("service", new TestAssocEnd("service", service, Multiplicity.STANDARD));
    serviceLevel.assocEnds.put("membership", new TestAssocEnd("membership", membership, Multiplicity.ZERO_TO_INF));
    serviceLevel.assocEnds.put("loyaltyProgram", new TestAssocEnd("loyaltyProgram", loyaltyProgram, Multiplicity.STANDARD));
    service.assocEnds.put("serviceLevel", new TestAssocEnd("serviceLevel", serviceLevel, Multiplicity.STANDARD));
    service.assocEnds.put("programPartner", new TestAssocEnd("programPartner", programPartner, Multiplicity.STANDARD));
    service.assocEnds.put("transactions", new TestAssocEnd("transactions", transaction, Multiplicity.ZERO_TO_INF));
    transaction.assocEnds.put("card", new TestAssocEnd("card", customerCard, Multiplicity.STANDARD));
    transaction.assocEnds.put("loyaltyAccount", new TestAssocEnd("loyaltyAccount", loyaltyAccount, Multiplicity.STANDARD));
    transaction.assocEnds.put("service", new TestAssocEnd("service", service, Multiplicity.STANDARD));

  }

  public Any getClassifier(String name) {
    if (name.indexOf("::")!=-1) {
      name=name.substring(name.lastIndexOf("::")+2).trim();
    }
    if (name.equals("Person")) {
      return person;
    } if (name.equals("Company")) {
      return company;
    } if (name.equals("LoyaltyProgram")) {
      return loyaltyProgram;
    } if (name.equals("Customer")) {
      return customer;
    } if (name.equals("Membership")) {
      return membership;
    } if (name.equals("CustomerCard")) {
      return customerCard;
    } if (name.equals("ProgramPartner")) {
      return programPartner;
    } if (name.equals("ServiceLevel")) {
      return serviceLevel;
    } if (name.equals("Service")) {
      return service;
    } if (name.equals("LoyaltyAccount")) {
      return loyaltyAccount;
    } if (name.equals("Transaction")) {
      return transaction;
    } if (name.equals("Burning")) {
      return burning;
    } if (name.equals("Earning")) {
      return earning;
    } if (name.equals("Date")) {
      return date;
    } else if (name.equals("Day")) {
      return day;
    } else if (name.equals("DayType")) {
      return dayType;
    } else if (name.equals("Bank")) {
      return bank;
    } else {
      throw new OclTypeException("can not find classifier \""+name+"\"");
    }
  }

  public String toString()
  {
    return getClass().getName();
  }
}

class TestClassifier implements Any {

  String name;
  HashMap features, assocEnds;

  TestClassifier(String n) {
    name=n;
    features=new HashMap();
    assocEnds=new HashMap();
  }

  public String getName() {
    return name;
  }

  public TestFeature getFeature(String feature) {
    return (TestFeature) features.get(feature);
  }

  public boolean conformsTo(Type t) {
    return t==this;
  }

  public TestBFeature getBehavioralFeature(String name, Type[] params) {
    return (TestBFeature) features.get(name);
  }

  public TestSFeature getStructuralFeature(String name) {
    return (TestSFeature) features.get(name);
  }

  public TestAssocEnd getAssociationEnd(String assocEnd) {
    return (TestAssocEnd) assocEnds.get(assocEnd);
  }

  public Any[] getSupertypes() {
    return new Any[0];
  }

  public Type navigateQualified(String name, Type[] qualifiers) {
    if (qualifiers!=null && qualifiers.length>0) {
      throw new OclTypeException(
        "feature \""+name+"\" of type "+this+" cannot be accessed with qualifier"
      );
    }
    Type ret=null;
    ret=Basic.navigateAnyQualified(name, this, qualifiers);
    if (ret!=null) return ret;
    if (getFeature(name)!=null)
      ret=getFeature(name).getType();
    else if (getAssociationEnd(name)!=null) {
      ret=getAssociationEnd(name).getType();
      if (getAssociationEnd(name).getMultiplicity().getMax()!=1) {
        // add distincion between ordered and unordered assocs. here
        ret=new Collection(Collection.SET, ret);
      }
    }
    if (ret==null) {
      throw new OclTypeException(this.name+" has no feature "+name);
    }
    return ret;
  }

  public Type navigateParameterized(String name, Type[] params) {
    Type ret=null;
    // add handling of polymorphism in parameters here
    TestFeature f=(TestFeature)features.get(name);
    if (f!=null && f instanceof TestBFeature) {
      TestBFeature tbf=(TestBFeature)f;
      Type[] featureParams=tbf.getParameters();
      boolean fits=(params.length==featureParams.length);
      for (int i=0; fits && i<params.length; i++) {
        if (!params[i].conformsTo(featureParams[i]))
          fits=false;
      }
      if (fits) ret=tbf.getType();
    }
    if (ret==null) {
      if (name.equals("oclIsKindOf") && params.length==1 && params[0] instanceof OclType) {
        ret=Basic.BOOLEAN;
      } else if (name.equals("oclIsTypeOf") && params.length==1 && params[0] instanceof OclType) {
        ret=Basic.BOOLEAN;
      } else if (name.equals("oclAsType") && params.length==1 && params[0] instanceof OclType) {
        ret=((OclType)params[0]).getType();
      }
    }
    if (ret==null) {
      throw new OclTypeException(this.name+" has no feature \""+name+"("+DefaultTypeFactory.toString(params)+")\"");
    }
    return ret;
  }

  public boolean hasState(String stateName) {
    return false;
  }

  public String toString() {
    return name;
  }

}

abstract class TestFeature {
  abstract Type getType();
}

class TestBFeature extends TestFeature {

  String name;
  Type[] params;
  Type ret;

  public TestBFeature(String n, Type r) {
    name=n;
    ret=r;
    params=new Type[0];
  }

  public TestBFeature(String n, Type r, Type p) {
    name=n;
    ret=r;
    params=new Type[1];
    params[0]=p;
  }

  public TestBFeature(String n, Type r, Type[] ps) {
    name=n;
    ret=r;
    params=ps;
  }

  public String getName() {
    return name;
  }

  public Type getType() {
    return ret;
  }

  public boolean isQuery() {
    return true;
  }

  public Type[] getParameters() {
    return params;
  }
}

class TestSFeature extends TestFeature {

  String name;
  Type type;

  public TestSFeature(String n, Type t) {
    name=n;
    type=t;
  }

  public String getName() {
    return name;
  }

  public Type getType() {
    return type;
  }
}

class TestAssocEnd {
  String name;
  Multiplicity mult;
  Any type;

  TestAssocEnd(String n, Any c, Multiplicity m) {
    name=n;
    mult=m;
    type=c;
  }

  public String getName() {
    return name;
  }

  public Multiplicity getMultiplicity() {
    return mult;
  }

  public boolean isOrdered() {
    return false;
  }

  public Any getType() {
    return type;
  }
}

class Multiplicity {

  /** constant to be used as return value for the method getMax()
   *
   *  @see #getMax()
   */
  public static int INFINITE = -10;

  /** multiplicity "1" */
  public static Multiplicity STANDARD     = new Multiplicity(1,1);
  /** multiplicity "0 .. *" */
  public static Multiplicity ZERO_TO_INF  = new Multiplicity(0,INFINITE);
  /** multiplicity "1 .. *" */
  public static Multiplicity ONE_TO_INF   = new Multiplicity(1,INFINITE);
  /** multiplicity "0 .. 1" */
  public static Multiplicity ZERO_OR_ONE  = new Multiplicity(0,1);


  protected int min, max;


  /** create a new Multiplicity objects representing the given minumum and
   *  maximum multiplicity of an association end
   *
   *  @param max the maximum multiplicity, possibly INFINITE
   *
   *  @throws IllegalArgumentException if not
   *          <code>0 <= min and max<>0 and (min<=max or max=INFINITE)</code>
   *
   *  @see #INFINITE
   */
  public Multiplicity(int min, int max) {
    if ( min<0 )
      throw new IllegalArgumentException("minimum multiplicity must be at least 0, is "+min);
    if ( max==0 )
      throw new IllegalArgumentException("maximum multiplicity must not be 0");
    if ( !(min<=max  || max==INFINITE))
      throw new IllegalArgumentException(
        "minimum multiplicity ("+min+") must not be greater than maximum multiplicity ("+max+")"
      );
    this.min=min;
    this.max=max;
  }

  /** @return the maximum multiplicity of the association end, possibly
   *          INFINITE
   *  @see #INFINITE
   */
  public int getMax() {
    return max;
  }

  /** @return the minumum multiplicity of the association end, usually 0 or 1
   */
  public int getMin() {
    return min;
  }
} /* end class Muliplicity */

class BankClassifier implements Any {

  TestClassifier person;

  BankClassifier(TestClassifier person) {
    this.person=person;
  }

  public Type navigateQualified(String name, Type[] qualifiers) {
    if (name.equals("customer")) {
      if (qualifiers==null || qualifiers.length==0) {
        return new Collection(Collection.SET, person);
      }
      if (qualifiers.length==1 && qualifiers[0]==Basic.INTEGER) {
        return person;
      }
    }
    throw new OclTypeException("Bank has no feature "+name);
  }

  public Type navigateParameterized(String name, Type[] params) {
    throw new OclTypeException("Bank has no operation "+name);
  }

  public boolean hasState(String name) {
    return "bankrupt".equals(name) || "notBankrupt".equals(name);
  }

  public boolean conformsTo(Type t) {
    if (t instanceof BankClassifier) {
      return true;
    } else {
      return false;
    }
  }

  public String toString() {
    return "Bank";
  }
}

