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
package tudresden.ocl20.pivot.examples.living.instance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import tudresden.ocl20.pivot.examples.living.Accomodation;
import tudresden.ocl20.pivot.examples.living.City;
import tudresden.ocl20.pivot.examples.living.Course;
import tudresden.ocl20.pivot.examples.living.Flat;
import tudresden.ocl20.pivot.examples.living.House;
import tudresden.ocl20.pivot.examples.living.Student;

/**
 * <p>
 * This class provides a provider method to load a Java {@link IModelInstance}
 * of the Living Example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelInstanceProviderClass {

	/**
	 * <p>
	 * Returns a {@link List} of {@link Object}s that are part of the
	 * {@link IModelInstance}.
	 * </p>
	 * 
	 * @return A {@link List} of {@link Object}s that are part of the
	 *         {@link IModelInstance}.
	 */
	public static List<Object> getModelObjects() {

		List<Object> result;
		result = new ArrayList<Object>();

		City dresden = new City("Dresden");
		result.add(dresden);

		House awp = new House("Somestreet", 2, "01239", dresden);
		result.add(awp);

		Flat f1 = new Flat(awp, 1205);
		f1.setCategory(Accomodation.PERMANENTRESIDENCE);
		result.add(f1);

		Flat f2 = new Flat(awp, 1204);
		result.add(f2);

		City goerlitz = new City("Görlitz");
		result.add(goerlitz);

		House js = new House("Anotherstreet", 8, "02828", goerlitz);
		result.add(js);

		Flat f3 = new Flat(js, 21);
		f3.setCategory(Accomodation.SECONDARYRESIDENCE);
		result.add(f3);

		Student s1 =
				new Student("Ronny Brandt", new GregorianCalendar(1981,
						Calendar.AUGUST, 10), f1);
		s1.addAccomodation(f3);
		result.add(s1);

		Accomodation acc = new Accomodation("Anystreet", 12, "02828", goerlitz);
		result.add(acc);

		Course c = new Course(null);
		result.add(c);

		return result;
	}
}