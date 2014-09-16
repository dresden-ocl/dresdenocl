/**
 * Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)
 * 
 * This file is part of the Royal and Loyal Example of Dresden OCL2 for Eclipse.
 * 
 * Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along 
 * with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.examples.royalandloyal.ocl22javacode;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Represents an implementation of the class {@link CustomerCard} of the Loyals
 * and Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class CustomerCard {

	protected Color color;

	protected String printedName;

	protected boolean valid;

	protected Date validFrom;

	protected Date validThru;

	protected Customer owner;

	protected Membership membership;

	protected Set<Transaction> transactions = new HashSet<Transaction>();

	public CustomerCard() {

		this.validFrom = new Date(2008, 1, 1);

		this.validThru = new Date(2009, 1, 1);
	}

	public Color getColor() {

		return color;
	}

	public void setColor(Color color) {

		this.color = color;
	}

	public String getPrintedName() {

		return printedName;
	}

	public void setPrintedName(String printedName) {

		this.printedName = printedName;
	}

	public boolean isValid() {

		return valid;
	}

	public void setValid(boolean valid) {

		this.valid = valid;
	}

	public Date getValidFrom() {

		return validFrom;
	}

	public void setValidFrom(Date validFrom) {

		this.validFrom = validFrom;
	}

	public Date getValidThru() {

		return validThru;
	}

	public void setValidThru(Date validThru) {

		this.validThru = validThru;
	}

	public Customer getOwner() {

		return owner;
	}

	public void setOwner(Customer owner) {

		this.owner = owner;
	}

	public Set<Transaction> getTransactions() {

		return transactions;
	}

	public void addTransaction(Transaction aTransaction) {

		this.transactions.add(aTransaction);
	}

	public Membership getMembership() {

		return membership;
	}

	public void setMembership(Membership membership) {

		this.membership = membership;
	}
}