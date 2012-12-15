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
package org.dresdenocl.examples.royalsandloyals;

import org.dresdenocl.ocl2java.types.OclSet;

/**
 * <p>
 * Represents an implementation of the class {@link Membership} of the Loyals
 * and Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Membership {

	protected CustomerCard card;

	protected OclSet<LoyaltyAccount> accounts = new OclSet<LoyaltyAccount>();

	protected LoyaltyProgram program;

	protected ServiceLevel currentLevel;

	public CustomerCard getCard() {

		return card;
	}

	public void setCard(CustomerCard card) {

		this.card = card;
	}

	public OclSet<LoyaltyAccount> getAccounts() {

		return accounts;
	}

	public void addAccount(LoyaltyAccount anAccount) {

		this.accounts.add(anAccount);
	}

	public LoyaltyProgram getProgram() {

		return program;
	}

	public void setProgram(LoyaltyProgram program) {

		this.program = program;
	}

	public ServiceLevel getCurrentLevel() {

		return currentLevel;
	}

	public void setCurrentLevel(ServiceLevel currentLevel) {

		this.currentLevel = currentLevel;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("Membership [");
		if (card != null) {
			builder.append("card=");
			builder.append(card.getPrintedName());
			builder.append(", ");
		}
		if (currentLevel != null) {
			builder.append("currentLevel=");
			builder.append(currentLevel.getName());
			builder.append(", ");
		}
		if (program != null) {
			builder.append("program=");
			builder.append(program.getName());
		}
		builder.append("]");
		return builder.toString();
	}
}