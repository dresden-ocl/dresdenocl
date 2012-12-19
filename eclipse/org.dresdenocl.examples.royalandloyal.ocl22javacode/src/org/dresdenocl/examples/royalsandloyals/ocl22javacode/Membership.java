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
package org.dresdenocl.examples.royalsandloyals.ocl22javacode;

import java.util.HashSet;
import java.util.Set;

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

	protected Set<LoyaltyAccount> accounts = new HashSet<LoyaltyAccount>();

	protected LoyaltyProgram program;

	protected ServiceLevel currentLevel;

	public CustomerCard getCard() {

		return card;
	}

	public void setCard(CustomerCard card) {

		this.card = card;
	}

	public Set<LoyaltyAccount> getAccounts() {

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
}