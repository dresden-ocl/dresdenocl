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

/**
 * <p>
 * The {@link Class} {@link Examination} is part of the Living Example of
 * Dresden OCL2 for Eclipse.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Examination {

	/** The mark of this {@link Examination}. */
	protected float mark;

	/** Specifies, if this {@link Examination} has been passed. */
	protected boolean passed;

	/** The group of this {@link Examination}. */
	protected int group;

	/** The {@link Course} of this {@link Examination}. */
	public Course course;

	/**
	 * <p>
	 * Creates a new {@link Examination}.
	 * </p>
	 * 
	 * @param course
	 *          The {@link Course} of this {@link Examination}.
	 */
	public Examination(Course course) {

		this.course = course;
	}

	/**
	 * <p>
	 * Sets the result of this {@link Examination}.
	 * </p>
	 * 
	 * @param mark
	 *          The result of this {@link Examination}.
	 */
	public void setResult(float mark) {

		this.mark = mark;
	}
}