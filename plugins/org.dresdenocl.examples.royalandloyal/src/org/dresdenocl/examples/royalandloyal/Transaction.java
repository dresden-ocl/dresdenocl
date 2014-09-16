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

/**
 * <p>
 * Represents an implementation of the class {@link Transaction} of the Loyals
 * and Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Transaction {

	protected float amount;

	protected int points;

	protected Date date;

	protected CustomerCard card;

	protected LoyaltyAccount account;

	protected Service service;

	public LoyaltyProgram getProgram() {

		LoyaltyProgram result;

		if (this.service != null && this.service.getLevel() != null) {
			result = this.service.getLevel().getProgram();
		}

		else {
			result = null;
		}

		return result;
	}

	public float getAmount() {

		return amount;
	}

	public void setAmount(float amount) {

		this.amount = amount;
	}

	public int getPoints() {

		return points;
	}

	public void setPoints(int points) {

		this.points = points;
	}

	public Date getDate() {

		return date;
	}

	public void setDate(Date date) {

		this.date = date;
	}

	public CustomerCard getCard() {

		return card;
	}

	public void setCard(CustomerCard card) {

		this.card = card;
	}

	public LoyaltyAccount getAccount() {

		return account;
	}

	public void setAccount(LoyaltyAccount account) {

		this.account = account;
	}

	public Service getService() {

		return service;
	}

	public void setService(Service service) {

		this.service = service;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [");
		if (account != null) {
			builder.append("account=");
			builder.append(account.getCustomerName());
			builder.append(", ");
		}
		builder.append("amount=");
		builder.append(amount);
		builder.append(", ");
		if (card != null) {
			builder.append("card=");
			builder.append(card.getPrintedName());
			builder.append(", ");
		}
		if (date != null) {
			builder.append("date=");
			builder.append(date);
			builder.append(", ");
		}
		builder.append("points=");
		builder.append(points);
		builder.append(", ");
		if (service != null) {
			builder.append("service=");
			builder.append(service.getServiceNr());
		}
		builder.append("]");
		return builder.toString();
	}
}