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
package tudresden.ocl20.pivot.examples.living;

import java.util.Calendar;

/**
 * <p>
 * The {@link Class} {@link Commuter} is part of the Living Example of Dresden
 * OCL2 for Eclipse.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Commuter extends Worker {

	/** The travels per month of this {@link Worker}. */
	protected int travelsPerMonth;

	/**
	 * <p>
	 * Creates a new {@link Commuter}.
	 * </p>
	 * 
	 * @param name
	 *          The name of this {@link Commuter}.
	 * @param birthday
	 *          The birthday of this {@link Commuter}.
	 * @param mainAccomodation
	 *          The main {@link Accomodation} of this {@link Commuter}.
	 */
	public Commuter(String name, Calendar birthday, Accomodation mainAccomodation) {

		super(name, birthday, mainAccomodation);
	}
}