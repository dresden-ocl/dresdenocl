/*
Copyright (C) 2000  Sten Loecher

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
package tudresden.ocl.codegen.decl;

/**
 *  A test class that implements ORMappingScheme.
 *
 *  @author Sten Loecher
 */
public class ORTestScheme implements ORMappingScheme {

	MappedClass person, company, transaction, burning, earning, customer,
                    customerCard, loyaltyAccount, service, serviceLevel,
                    programPartner, loyaltyProgram, membership;

	public ORTestScheme() {
		Table t;
		Guide g;

		// the mapping the classes
		person = new MappedClass("Person");
		t = new Table("PERSON");
		t.addColumn("", "PID", true);
		t.addColumn("name", "NAME", false);
		t.addColumn("age", "AGE", false);
		t.addColumn("isMarried", "ISMARRIED", false);
		t.addColumn("isUnemployed", "ISUNEMPLOYED", false);
		person.addTable(t);

		company = new MappedClass("Company");
		t = new Table("COMPANY");
		t.addColumn("", "CID", true);
		t.addColumn("numberOfEmployees", "NUMBEROFEMPLOYEES", false);
		t.addColumn("", "MANAGER", false);
		company.addTable(t);

                customer = new MappedClass("Customer");
                t = new Table("CUSTOMER");
                t.addColumn("", "CUID", true);
                t.addColumn("name", "NAME", false);
                t.addColumn("title", "TITLE", false);
                t.addColumn("isMale", "ISMALE", false);
                customer.addTable(t);

                customerCard = new MappedClass("CustomerCard");
                t = new Table("CUSTOMERCARD");
                t.addColumn("", "CCID", true);
                t.addColumn("valid", "VALID", false);
                t.addColumn("color", "COLOR", false);
                t.addColumn("printedName", "PRINTEDNAME", false);
                customerCard.addTable(t);

                transaction = new MappedClass("Transaction");
                t = new Table("TRANSACTION");
                t.addColumn("", "TID", true);
                t.addColumn("points", "POINTS", false);
                t.addColumn("", "CCID", false);
                t.addColumn("", "LAID", false);
                t.addColumn("", "SID", false);
                transaction.addTable(t);

                burning = new MappedClass("Burning");
                t = new Table("BURNING");
                t.addColumn("", "TID", true);
                burning.addTable(t);
                burning.addSuperclass("Transaction", transaction);

                earning = new MappedClass("Earning");
                t = new Table("EARNING");
                t.addColumn("", "TID", true);
                earning.addTable(t);
                earning.addSuperclass("Transaction", transaction);

                loyaltyAccount = new MappedClass("LoyaltyAccount");
                t = new Table("LOYALTYACCOUNT");
                t.addColumn("", "LAID", true);
                t.addColumn("points", "POINTS", false);
                t.addColumn("", "MSID", false);
                loyaltyAccount.addTable(t);
                loyaltyAccount.addQuery("isEmpty");

                service = new MappedClass("Service");
                t = new Table("SERVICE");
                t.addColumn("", "SID", true);
                t.addColumn("condition", "CONDITION", false);
                t.addColumn("pointsEarned", "POINTSEARNED", false);
                t.addColumn("pointsBurned", "POINTSBURNED", false);
                t.addColumn("description", "DESCRIPTION", false);
                t.addColumn("", "SLID", false);
                t.addColumn("", "PPID", false);
                service.addTable(t);

                serviceLevel = new MappedClass("ServiceLevel");
                t = new Table("SERVICELEVEL");
                t.addColumn("", "SLID", true);
                t.addColumn("name", "NAME", false);
                t.addColumn("", "SID", false);
                t.addColumn("", "LPID", false);
                serviceLevel.addTable(t);

                programPartner = new MappedClass("ProgramPartner");
                t = new Table("PROGRAMPARTNER");
                t.addColumn("", "PPID", true);
                t.addColumn("numberOfCustomers", "NUMBEROFCUSTOMERS", false);
                programPartner.addTable(t);

                membership = new MappedClass("Membership");
                t = new Table("MEMBERSHIP");
                t.addColumn("", "MSID", true);
                t.addColumn("", "CCID", false);
                t.addColumn("", "LAID", false);
                t.addColumn("", "SLID", false);
                t.addColumn("", "LPID", false);
                t.addColumn("", "CUID", false);
                membership.addTable(t);

                loyaltyProgram = new MappedClass("LoyaltyProgram");
                t = new Table("LOYALTYPROGRAM");
                loyaltyProgram.addTable(t);

		// the mapping of association ends
		person.addAssociationEnd("managedCompanies", company);
		person.addAssociationEnd("employers", company);

		company.addAssociationEnd("manager", person);
		company.addAssociationEnd("employees", person);

                customer.addAssociationEnd("cards", customerCard);
                customer.addAssociationEnd("membership", membership);
                customer.addAssociationEnd("loyaltyProgram", loyaltyProgram);

                customerCard.addAssociationEnd("membership", membership);
                customerCard.addAssociationEnd("transactions", transaction);
                customerCard.addAssociationEnd("owner", customer);

                transaction.addAssociationEnd("card", customerCard);
                transaction.addAssociationEnd("loyaltyAccount", loyaltyAccount);
                transaction.addAssociationEnd("service", service);

                loyaltyAccount.addAssociationEnd("membership", membership);
                loyaltyAccount.addAssociationEnd("transaction", transaction);

                service.addAssociationEnd("transactions", transaction);
                service.addAssociationEnd("serviceLevel", serviceLevel);
                service.addAssociationEnd("programPartner", programPartner);

                serviceLevel.addAssociationEnd("service", service);
                serviceLevel.addAssociationEnd("membership", membership);
                serviceLevel.addAssociationEnd("loyaltyProgram", loyaltyProgram);

                programPartner.addAssociationEnd("deliveredService", service);
                programPartner.addAssociationEnd("loyaltyProgram", loyaltyProgram);

                loyaltyProgram.addAssociationEnd("partners", programPartner);
                loyaltyProgram.addAssociationEnd("membership", membership);
                loyaltyProgram.addAssociationEnd("serviceLevel", serviceLevel);
                loyaltyProgram.addAssociationEnd("customer", customer);

                membership.addAssociationEnd("customer", customer);
                membership.addAssociationEnd("card", customerCard);
                membership.addAssociationEnd("loyaltyAccount", loyaltyAccount);
                membership.addAssociationEnd("actualLevel", serviceLevel);
                membership.addAssociationEnd("program", loyaltyProgram);

		g = new Guide(true);
		g.add("CID", "COMPANY", "CID");
		g.add("CID", "EMPLOYMENT", "PID");
		g.add("PID", "PERSON", "PID");
		person.addJoinGuide("employers", g);

		g = new Guide(true);
		g.add("CID", "COMPANY", "PID");
		person.addJoinGuide("managedCompanies", g);

		g = new Guide(true);
		g.add("PID", "PERSON", "PID");
		g.add("PID", "EMPLOYMENT", "CID");
		g.add("CID", "COMPANY", "CID");
		company.addJoinGuide("employees", g);

		g = new Guide(true);
		g.add("PID", "COMPANY", "PID");
		company.addJoinGuide("manager", g);

                g = new Guide(true);
                g.add("CCID", "CUSTOMERCARD", "CUID");
                customer.addJoinGuide("cards", g);

                g = new Guide(true);
                g.add("MSID", "MEMBERSHIP", "CUID");
                customer.addJoinGuide("membership", g);

                g = new Guide(true);
                g.add("MSID", "CUSTOMERCARD", "CCID");
                customerCard.addJoinGuide("membership", g);

                g = new Guide(true);
                g.add("LPID", "LOYALTYPROGRAM", "LPID");
                g.add("LPID", "MEMBERSHIP", "CUID");
                g.add("CUID", "CUSTOMER", "CUID");
                customerCard.addJoinGuide("loyaltyProgram", g);

                g = new Guide(true);
                g.add("TID", "TRANSACTION", "CCID");
                customerCard.addJoinGuide("transactions", g);

                g = new Guide(true);
                g.add("CUID", "CUSTOMERCARD", "CCID");
                customerCard.addJoinGuide("owner", g);

                g = new Guide(true);
                g.add("CCID", "TRANSACTION", "TID");
                transaction.addJoinGuide("card", g);

                g = new Guide(true);
                g.add("LAID", "TRANSACTION", "TID");
                transaction.addJoinGuide("loyaltyAccount", g);

                g = new Guide(true);
                g.add("SID", "TRANSACTION", "TID");
                transaction.addJoinGuide("service", g);

                g = new Guide(true);
                g.add("MSID", "MEMBERSHIP", "LAID");
                loyaltyAccount.addJoinGuide("membership", g);

                g = new Guide(true);
                g.add("TID", "TRANSACTION", "LAID");
                loyaltyAccount.addJoinGuide("transaction", g);

                g = new Guide(true);
                g.add("TID", "TRANSACTIONS", "SID");
                service.addJoinGuide("transactions", g);

                g = new Guide(true);
                g.add("SLID", "SERVICELEVEL", "SID");
                service.addJoinGuide("serviceLevel", g);

                g = new Guide(true);
                g.add("PPID", "SERVICE", "SID");
                service.addJoinGuide("programPartner", g);

                g = new Guide(true);
                g.add("SID", "SERVICELEVEL", "SLID");
                serviceLevel.addJoinGuide("service", g);

                g = new Guide(true);
                g.add("MSID", "MEMBERSHIP", "SLID");
                serviceLevel.addJoinGuide("membership", g);

                g = new Guide(true);
                g.add("LPID", "SERVICELEVEL", "SLID");
                serviceLevel.addJoinGuide("loyaltyProgram", g);

                g = new Guide(true);
                g.add("SID", "SERVICE", "PPID");
                programPartner.addJoinGuide("deliveredService", g);

                g = new Guide(true);
                g.add("LPID", "LOYALTYPROGRAM", "LPID");
                g.add("LPID", "LP_PP", "PPID");
                g.add("PPID", "PROGRAMPARTNER", "PPID");
                programPartner.addJoinGuide("loyaltyProgram", g);

                g = new Guide(true);
                g.add("PPID", "PROGRAMPARTNERS", "PPID");
                g.add("PPID", "LP_PP", "LPID");
                g.add("LPID", "LOYALTYPROGRAM", "LPID");
                loyaltyProgram.addJoinGuide("partners", g);

                g = new Guide(true);
                g.add("MSID", "MEMBERSHIP", "LPID");
                loyaltyProgram.addJoinGuide("membership", g);

                g = new Guide(true);
                g.add("SLID", "SERVICELEVEL", "LPID");
                loyaltyProgram.addJoinGuide("serviceLevel", g);

                g = new Guide(true);
                g.add("CUID", "CUSTOMER", "CUID");
                g.add("CUID", "MEMBERSHIP", "LPID");
                g.add("LPID", "LOYALTYPROGRAM", "LPID");
                loyaltyProgram.addJoinGuide("customer", g);

                g = new Guide(true);
                g.add("CUID", "MEMBERSHIP", "MSID");
                membership.addJoinGuide("customer", g);

                g = new Guide(true);
                g.add("CCID", "CUSTOMERCARD", "MSID");
                membership.addJoinGuide("card", g);

                g = new Guide(true);
                g.add("LAID", "LOYALTYACCOUNT", "MSID");
                membership.addJoinGuide("loyaltyAccount", g);

                g = new Guide(true);
                g.add("SLID", "MEMBERSHIP", "MSID");
                membership.addJoinGuide("actualLevel", g);

                g = new Guide(true);
                g.add("LPID", "MEMBERSHIP", "MSID");
                membership.addJoinGuide("program", g);
	}

	public MappedClass getMappedClass(String name) {
		if (name.equals("Person")) {
			return person;
		} else if (name.equals("Company")) {
			return company;
		} else if (name.equals("Customer")) {
			return customer;
		} else if (name.equals("CustomerCard")) {
			return customerCard;
		} else if (name.equals("Transaction")) {
			return transaction;
		} else if (name.equals("LoyaltyAccount")) {
			return loyaltyAccount;
		} else if (name.equals("Service")) {
			return service;
		} else if (name.equals("Burning")) {
			return burning;
		} else if (name.equals("Earning")) {
			return earning;
		} else if (name.equals("ProgramPartner")) {
			return programPartner;
		} else if (name.equals("ServiceLevel")) {
			return serviceLevel;
		} else if (name.equals("Membership")) {
			return membership;
		} else if (name.equals("LoyaltyProgram")) {
			return loyaltyProgram;
		} else {
			return null;
		}
	}
}
