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

import java.util.GregorianCalendar;

/**
 * <p>
 * Represents an implementation of the {@link Date} of the Loyals and Royals
 * example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Date {

	protected GregorianCalendar myDate;

	public static Date now = now();

	public static String nowString = nowAsString();

	private Date() {

		this.myDate = new GregorianCalendar();
	}

	public Date(int year, int month, int day) {

		this.myDate = new GregorianCalendar();

		this.myDate.set(year, month, day);
	}

	public boolean isAfter(Date aDate) {

		return this.myDate.after(aDate.myDate);
	}

	public boolean isBefore(Date aDate) {

		boolean result;

		result = true;

		if (aDate != null) {
			result = this.myDate.before(aDate.myDate);
		}
		// no else.

		return result;
	}

	public static Date now() {

		return new Date();
	}

	public static String nowAsString() {

		return now().toString();
	}

	public String toString() {

		String result;

		result = "";
		result += this.myDate.get(GregorianCalendar.DAY_OF_MONTH);
		result += "-";
		result += this.myDate.get(GregorianCalendar.MONTH);
		result += "-";
		result += this.myDate.get(GregorianCalendar.YEAR);

		return result;
	}
}