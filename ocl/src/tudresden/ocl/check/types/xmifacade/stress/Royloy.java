/*
Copyright (C) 2000  Ralf Wiebicke

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package tudresden.ocl.check.types.xmifacade.stress;

import tudresden.ocl.check.types.Basic;
import tudresden.ocl.check.types.Type;
import tudresden.ocl.check.types.xmifacade.*;
import java.util.*;

public class Royloy
{
  public static Model getModel()
  {
    return (new Royloy()).model;
  }

  public static String STANDARD     ="1..1";
  public static String ZERO_TO_INF  ="0..*";
  public static String ONE_TO_INF   ="1..*";
  public static String ZERO_OR_ONE  ="0..1";

  public ModelClass addClass(String name)
  {
    ModelClass modelclass=new ModelClass(pP, name);
    model.putClassifier(modelclass);
    return modelclass;
  }

  public void attr(ModelClass modelclass, String attrname, Type attrtype)
  {
    modelclass.addAttribute(new ModelAttribute(attrname, attrtype));
  }

  public void assoend(String assoname, ModelClass modelclass, String multiplicity)
  {
    assoend(assoname, modelclass, multiplicity, null);
  }

  public void assoend(String assoname, ModelClass modelclass, String multiplicity, Type[] qualifiers)
  {
    asso.addEnd(new ModelAssociationEnd(assoname, modelclass, multiplicity, false, qualifiers));
  }

  public void assoClear()
  {
    assoClear(null);
  }

  public void assoClear(ModelClass associationclass)
  {
    asso=new ModelAssociation(associationclass);
  }

  public void assoBurn()
  {
    asso.dissolve(model);
  }

  public void oper(ModelClass modelclass, String opername, Type returntype)
  {
    oper(modelclass, opername, returntype, paramsEmpty);
  }

  public void oper(ModelClass modelclass, String opername, Type returntype, Type[] params)
  {
    modelclass.addOperation(new ModelOperation(opername, params, returntype, false));
  }

  Model model;
  Type[] paramsEmpty=new Type[0];
  ModelAssociation asso;
  List pP=new LinkedList();

  private Royloy()
  {
    System.out.println("creating royloy test model (tudresden.ocl.check.types.xmifacade.Royloy)");

    model=new Model("created by "+getClass());

    Type[] paramsString =new Type[] { Basic.STRING };
    Type[] paramsInteger=new Type[] { Basic.INTEGER };
    Type[] paramsReal  =new Type[] { Basic.REAL };
    Type[] qualifierInteger=paramsInteger;

    pP.add("royloy");

    ModelClass date          =addClass("Date");
    Type[] paramsDate    =new Type[] { date };

    ModelClass person =addClass("Person");
    ModelClass company=addClass("Company");
    ModelClass bank   =addClass("Bank");
    ModelClass account=addClass("Account");

    attr(person, "age", Basic.INTEGER);
    attr(person, "name", Basic.STRING);
    attr(person, "isMarried", Basic.BOOLEAN);
    attr(person, "isUnemployed", Basic.BOOLEAN);
    oper(person, "getIncomeAfterTax", Basic.REAL, paramsReal);

    attr(company, "numberOfEmployees", Basic.INTEGER);
    oper(company, "getOldestEmployee", person);
    oper(company, "getOldestEmployeeAge", Basic.INTEGER);

    attr(account, "created", date);

    assoClear();
    assoend("employers", company, ZERO_TO_INF);
    assoend("employees", person, ZERO_TO_INF);
    assoBurn();

    assoClear();
    assoend("managedCompanies", company, ZERO_TO_INF);
    assoend("manager", person, STANDARD);
    assoBurn();

    assoClear();
    assoend("wife", person, ZERO_OR_ONE);
    assoend("husband", person, ZERO_OR_ONE);
    assoBurn();

    assoClear(account);
    assoend(null, bank, STANDARD, qualifierInteger);
    assoend("customer", person, ZERO_OR_ONE);
    assoBurn();


    // Day - DayType - Model

    ModelClass day    =addClass("Day");
    ModelClass dayType=addClass("DayType");
    Type[] paramsDayType=new Type[] { dayType };
    attr(dayType, "monday", dayType);
    attr(dayType, "tuesday", dayType);
    attr(dayType, "wednesday", dayType);
    attr(dayType, "thursday", dayType);
    attr(dayType, "friday", dayType);
    attr(dayType, "saturday", dayType);
    attr(dayType, "sunday", dayType);
    oper(dayType, "getFollowingDay", dayType, paramsDayType);
    attr(day, "type", dayType);
    attr(day, "isWeekend", Basic.BOOLEAN);
    oper(day, "setType", Model.VOID, paramsDayType);

    // Royals and Loyals

    ModelClass loyaltyProgram=addClass("LoyaltyProgram");
    ModelClass customer      =addClass("Customer");
    ModelClass membership    =addClass("Membership");
    ModelClass customerCard  =addClass("CustomerCard");
    ModelClass serviceLevel  =addClass("ServiceLevel");
    ModelClass programPartner=addClass("ProgramPartner");
    ModelClass service       =addClass("Service");
    ModelClass loyaltyAccount=addClass("LoyaltyAccount");
    ModelClass transaction   =addClass("Transaction");
    ModelClass burning       =addClass("Burning");
    ModelClass earning       =addClass("Earning");
    Type[] paramsCustomer=new Type[] { customer };

    burning.addDirectSupertype(transaction);
    earning.addDirectSupertype(transaction);

    oper(loyaltyProgram, "enroll", Model.VOID, paramsCustomer);

    attr(programPartner, "numberOfCustomers", Basic.INTEGER);

    attr(serviceLevel, "name", Basic.STRING);

    attr(customer, "name", Basic.STRING);
    attr(customer, "title", Basic.STRING);
    attr(customer, "isMale", Basic.BOOLEAN);
    attr(customer, "dateOfBirth", date);

    attr(customerCard, "valid", Basic.BOOLEAN);
    attr(customerCard, "validFrom", date);
    attr(customerCard, "validThru", date);
    attr(customerCard, "color", Basic.INTEGER);
    attr(customerCard, "color_gold", Basic.INTEGER);
    attr(customerCard, "COLOR_SILVER", Basic.INTEGER);
    attr(customerCard, "printedName", Basic.STRING);

    attr(loyaltyAccount, "points", Basic.INTEGER);
    oper(loyaltyAccount, "earn", Model.VOID, paramsInteger);
    oper(loyaltyAccount, "burn", Model.VOID, paramsInteger);
    oper(loyaltyAccount, "isEmpty", Basic.BOOLEAN);

    attr(transaction, "points", Basic.INTEGER);
    attr(transaction, "date", date);
    oper(transaction, "program", loyaltyProgram);

    attr(service, "condition", Basic.BOOLEAN);
    attr(service, "pointsEarned", Basic.INTEGER);
    attr(service, "pointsBurned", Basic.INTEGER);
    attr(service, "description", Basic.STRING);

    oper(date, "isBefore", Basic.BOOLEAN, paramsDate);
    oper(date, "isAfter", Basic.BOOLEAN, paramsDate);
    oper(date, "equals", Basic.BOOLEAN, paramsDate);
    attr(date, "now", date);

    assoClear();
    assoend(null, loyaltyProgram, ONE_TO_INF);
    assoend("partners", programPartner, ONE_TO_INF);
    assoBurn();

    assoClear();
    assoend("deliveredServices", service, ZERO_TO_INF);
    assoend(null, programPartner, STANDARD);
    assoBurn();

    assoClear();
    assoend("serviceLevel", serviceLevel, ONE_TO_INF);
    assoend(null, loyaltyProgram, STANDARD);
    assoBurn();

    assoClear();
    assoend(null, customer, ZERO_TO_INF);
    assoend("program", loyaltyProgram, ZERO_TO_INF);
    assoBurn();

    assoClear();
    assoend(null, membership, ZERO_TO_INF);
    assoend("program", loyaltyProgram, STANDARD);
    assoBurn();

    assoClear();
    assoend(null, membership, ZERO_TO_INF);
    assoend(null, customer, STANDARD);
    assoBurn();

    assoClear();
    assoend("cards", customerCard, ZERO_TO_INF);
    assoend("owner", customer, STANDARD);
    assoBurn();

    assoClear();
    assoend("actualLevel", serviceLevel, STANDARD);
    assoend(null, membership, ZERO_TO_INF);
    assoBurn();

    assoClear();
    assoend(null, loyaltyAccount, ZERO_OR_ONE);
    assoend(null, membership, STANDARD);
    assoBurn();

    assoClear();
    assoend("card", customerCard, STANDARD);
    assoend(null, membership, STANDARD);
    assoBurn();

    assoClear();
    assoend("transactions", transaction, ZERO_TO_INF);
    assoend("card", customerCard, STANDARD);
    assoBurn();

    assoClear();
    assoend("transactions", transaction, ZERO_TO_INF);
    assoend(null, loyaltyAccount, STANDARD);
    assoBurn();

    assoClear();
    assoend(null, service, STANDARD);
    assoend(null, serviceLevel, STANDARD);
    assoBurn();

    assoClear();
    assoend("transactions", transaction, ZERO_TO_INF);
    assoend(null, service, STANDARD);
    assoBurn();

    model.flatten();

    model.printData();
  }
}
