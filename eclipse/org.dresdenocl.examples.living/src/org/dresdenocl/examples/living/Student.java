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

import java.util.Calendar;
import java.util.Vector;

/**
 * <p>
 * The {@link Class} {@link Student} is part of the Living Example of Dresden
 * OCL2 for Eclipse.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Student extends Person {

	/** The {@link Examination} of this {@link Student}. */
	public Examination examination;

	/** The visited {@link Course}s of this {@link Student}. */
	public Vector<Course> visitedCourses;

	/**
	 * <p>
	 * Creates a new {@link Student}.
	 * </p>
	 * 
	 * @param name
	 *          The name of the {@link Student}.
	 * @param birthday
	 *          The birthday of the {@link Student}.
	 * @param mainAccomodation
	 *          The main {@link Accomodation} of the {@link Student}.
	 */
	public Student(String name, Calendar birthday, Accomodation mainAccomodation) {

		super(name, birthday, mainAccomodation);

		this.visitedCourses = new Vector<Course>();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.examples.living.Person#toString()
	 */
	@Override
	public String toString() {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		resultBuffer.append("Student[name = ");
		resultBuffer.append(name);
		resultBuffer.append(", birthday = ");
		resultBuffer.append(birthday);
		resultBuffer.append(", accomodations = ");
		resultBuffer.append(accomodations);
		resultBuffer.append("]");

		return resultBuffer.toString();
	}

	/**
	 * <p>
	 * Returns the average mark a given group.
	 * </p>
	 * 
	 * @param group
	 */
	public void getAverageMark(int group) {

	}

	/**
	 * <p>
	 * Adds a given {@link Course} to this {@link Student}.
	 * </p>
	 * 
	 * @param course
	 *          The {@link Course} that shall be added.
	 */
	public void visitCourse(Course course) {

		this.visitedCourses.add(course);
	}

	/**
	 * <p>
	 * Lets a {@link Student} write a given {@link Examination}.
	 * </p>
	 * 
	 * @param examination
	 *          The {@link Examination} the {@link Student} should write.
	 * @return True, if the {@link Student} wrote the {@link Examination}.
	 */
	public boolean writeExamination(Examination examination) {

		return true;
	}
}