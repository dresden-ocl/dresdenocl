/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Living Examples of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.examples.living;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

/**
 * <p>
 * The {@link Class} {@link Person} is part of the Living Example of Dresden
 * OCL2 for Eclipse.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Person {

	/** The {@link Accomodation}s of this {@link Person}. */
	protected List<Accomodation> accomodations;

	/** The age of this {@link Person}. */
	protected int age = -1;

	/** The birthday of this {@link Person}. */
	protected Calendar birthday;

	/** The name of this {@link Person}. */
	protected String name;

	/** The {@link Insurance}s of this {@link Person}. */
	public Vector<Insurance> insurances;

	/**
	 * <p>
	 * Creates a new {@link Person}.
	 * </p>
	 * 
	 * @param name
	 *          The name of the {@link Person}.
	 * @param birthday
	 *          The name of the {@link Person}.
	 * @param mainAccomodation
	 *          The main {@link Accomodation} of the {@link Person}.
	 */
	public Person(String name, Calendar birthday, Accomodation mainAccomodation) {

		this.name = name;
		this.birthday = birthday;

		accomodations = new ArrayList<Accomodation>();
		accomodations.add(mainAccomodation);

		insurances = new Vector<Insurance>();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		resultBuffer.append("Person[name = ");
		resultBuffer.append(name);
		resultBuffer.append(", birthday = ");
		resultBuffer.append(birthday);
		resultBuffer.append(", accomodations = ");
		resultBuffer.append(accomodations.toString());
		resultBuffer.append("]");

		return resultBuffer.toString();
	}

	/**
	 * <p>
	 * Adds a given {@link Accomodation} to this {@link Person}.
	 * </p>
	 * 
	 * @param accomodation
	 *          The {@link Accomodation} that shall be added.
	 */
	public void addAccomodation(Accomodation accomodation) {

		this.accomodations.add(accomodation);
	}

	/**
	 * <p>
	 * Returns the age of this {@link Person}.
	 * </p>
	 * 
	 * @return The age of this {@link Person}.
	 */
	public int getAge() {

		if (this.age == -1) {
			calculateAge();
		}
		// no else.

		return this.age;
	}

	/**
	 * <p>
	 * Returns the birthday of this {@link Person}.
	 * </p>
	 * 
	 * @return The birthday of this {@link Person}.
	 */
	public String getBirthday() {
	
		return birthday.get(Calendar.DAY_OF_MONTH) + "."
				+ (birthday.get(Calendar.MONTH) + 1) + "."
				+ birthday.get(Calendar.YEAR);
	}

	/**
	 * <p>
	 * Removes a given {@link Accomodation} from this {@link Person}.
	 * </p>
	 * 
	 * @param accomodation
	 *          The {@link Accomodation} that shall be removed.
	 */
	public void removeAccomodation(Accomodation accomodation) {
	
		if (accomodations.contains(accomodation)) {
			accomodations.remove(accomodation);
		}
		// no else.
	}

	/**
	 * <p>
	 * Calculares the age of this {@link Person}.
	 * </p>
	 */
	protected void calculateAge() {

		this.age =
				(Calendar.getInstance().get(Calendar.YEAR) - birthday
						.get(Calendar.YEAR));
	}
}