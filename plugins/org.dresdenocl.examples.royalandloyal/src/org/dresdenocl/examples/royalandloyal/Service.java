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
 * Represents an implementation of the class {@link Service} of the Loyals and
 * Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Service {

	protected boolean condition;

	protected int pointsBurned;

	protected int pointsEarned;

	protected String description;

	protected int serviceNr;

	protected ProgramPartner partner;

	protected ServiceLevel level;

	protected Transaction transaction;

	public int calcPoints() {

		return pointsEarned - pointsBurned;
	}

	public void upgradePointsEarned(Integer amount) {

		this.pointsEarned = this.pointsEarned + amount;
	}

	public boolean isCondition() {

		return condition;
	}

	public void setCondition(boolean condition) {

		this.condition = condition;
	}

	public int getPointsBurned() {

		return pointsBurned;
	}

	public void setPointsBurned(int pointsBurned) {

		this.pointsBurned = pointsBurned;
	}

	public int getPointsEarned() {

		return pointsEarned;
	}

	public void setPointsEarned(int pointsEarned) {

		this.pointsEarned = pointsEarned;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public int getServiceNr() {

		return serviceNr;
	}

	public void setServiceNr(int serviceNr) {

		this.serviceNr = serviceNr;
	}

	public ProgramPartner getPartner() {

		return partner;
	}

	public void setPartner(ProgramPartner partner) {

		this.partner = partner;
	}

	public Transaction getTransaction() {

		return transaction;
	}

	public void setTransaction(Transaction transaction) {

		this.transaction = transaction;
	}

	public ServiceLevel getLevel() {

		return level;
	}

	public void setLevel(ServiceLevel level) {

		this.level = level;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("Service [condition=");
		builder.append(condition);
		builder.append(", ");
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (level != null) {
			builder.append("level=");
			builder.append(level.getName());
			builder.append(", ");
		}
		if (partner != null) {
			builder.append("partner=");
			builder.append(partner.getName());
			builder.append(", ");
		}
		builder.append("pointsBurned=");
		builder.append(pointsBurned);
		builder.append(", pointsEarned=");
		builder.append(pointsEarned);
		builder.append(", serviceNr=");
		builder.append(serviceNr);
		builder.append(", ");
		if (transaction != null) {
			builder.append("transaction=");
			builder.append(transaction.getPoints());
		}
		builder.append("]");
		return builder.toString();
	}
}