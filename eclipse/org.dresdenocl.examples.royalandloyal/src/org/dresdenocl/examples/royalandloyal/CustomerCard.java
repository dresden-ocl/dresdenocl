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
package org.dresdenocl.examples.royalandloyal;

import org.dresdenocl.ocl2java.types.OclSet;

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

	protected OclSet<Transaction> transactions = new OclSet<Transaction>();

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

	public OclSet<Transaction> getTransactions() {

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

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("CustomerCard [");
		if (color != null) {
			builder.append("color=");
			builder.append(color);
			builder.append(", ");
		}
		if (owner != null) {
			builder.append("owner=");
			builder.append(owner.getName());
			builder.append(", ");
		}
		if (printedName != null) {
			builder.append("printedName=");
			builder.append(printedName);
			builder.append(", ");
		}
		if (transactions != null) {
			builder.append("transactions=");
			builder.append("[");
			for (Transaction transaction : transactions) {
				builder.append(transaction.getAmount());
				builder.append(", ");
			}
			builder.append("]");
			builder.append(", ");
		}
		builder.append("valid=");
		builder.append(valid);
		builder.append(", ");
		if (validFrom != null) {
			builder.append("validFrom=");
			builder.append(validFrom);
			builder.append(", ");
		}
		if (validThru != null) {
			builder.append("validThru=");
			builder.append(validThru);
		}
		builder.append("]");
		return builder.toString();
	}
}