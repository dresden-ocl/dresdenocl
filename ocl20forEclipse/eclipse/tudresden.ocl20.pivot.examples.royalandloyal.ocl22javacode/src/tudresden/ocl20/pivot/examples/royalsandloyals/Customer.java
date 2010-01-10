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
package tudresden.ocl20.pivot.examples.royalsandloyals;

import tudresden.ocl20.pivot.ocl2java.types.OclSet;

/**
 * <p>
 * Represents an implementation of the class {@link Customer} of the Loyals and
 * Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Customer {

	protected int age;

	protected Date dateOfBirth;

	protected boolean isMale;

	protected String name = "";

	protected String title = "";

	protected OclSet<CustomerCard> cards = new OclSet<CustomerCard>();

	protected OclSet<LoyaltyProgram> programs = new OclSet<LoyaltyProgram>();

	public Customer(int age) {

		this.age = age;
	}

	public void birthdayHappens() {

		this.age++;
	}

	public int getAge() {

		return age;
	}

	public void setAge(int age) {

		this.age = age;
	}

	public Date getDateOfBirth() {

		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {

		this.dateOfBirth = dateOfBirth;
	}

	public boolean isMale() {

		return isMale;
	}

	public void setMale(boolean isMale) {

		this.isMale = isMale;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public OclSet<CustomerCard> getCards() {

		return cards;
	}

	public void addCard(CustomerCard aCard) {

		this.cards.add(aCard);

		aCard.setOwner(this);
	}

	public OclSet<LoyaltyProgram> getPrograms() {

		return programs;
	}

	public void addProgram(LoyaltyProgram aProgram) {

		this.programs.add(aProgram);
	}
}