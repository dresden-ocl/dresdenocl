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
package org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Represents an implementation of the class {@link LoyaltyAccount} of the
 * Loyals and Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class LoyaltyAccount {

	protected int points;

	protected int totalPointsEarned;

	protected int number;

	protected Membership membership;

	protected Set<Transaction> transactions = new HashSet<Transaction>();

	public void burn(int points) {

		this.points -= points;
	}

	public void earn(int points) {

		this.points += points;
	}

	public Boolean isEmpty() {

		return transactions.isEmpty();
	}

	public String getCustomerName() {

		// Implemented by BodyAspect 2.
		return null;
	}

	public int getPoints() {

		return points;
	}

	public void setPoints(int points) {

		this.points = points;
	}

	public int getTotalPointsEarned() {

		return totalPointsEarned;
	}

	public void setTotalPointsEarned(Integer totalPointsEarned) {

		this.totalPointsEarned = totalPointsEarned;
	}

	public int getNumber() {

		return number;
	}

	public void setNumber(int number) {

		this.number = number;
	}

	public Membership getMembership() {

		return membership;
	}

	public void setMembership(Membership membership) {

		this.membership = membership;
	}

	public Set<Transaction> getTransactions() {

		return transactions;
	}

	public void addTransaction(Transaction aTransaction) {

		this.transactions.add(aTransaction);
	}
}