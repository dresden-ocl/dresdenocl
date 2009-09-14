/*
Copyright (C) 2007-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Ecore Meta Model of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelinstancetype.ecore.internal.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <p>
 * This class provides some static methods to convert {@link EClass}es to
 * qualified names and vice versa.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public class EcoreModelInstanceTypeUtilitiy {

	/**
	 * <p>
	 * A helper method that returns the qualified name (as a {@link List} of
	 * {@link String}s) of a given {@link EClass}.
	 * </p>
	 * 
	 * @param eClass
	 *          The {@link EClass} whose qualified name shall be returned.
	 * @return The qualified name of the given {@link EClass}.
	 */
	public static List<String> toQualifiedNameList(EClass eClass) {

		List<String> result;
		EPackage ePackage;

		result = new ArrayList<String>();

		/* Add the name of the class. */
		result.add(eClass.getName());

		ePackage = eClass.getEPackage();

		/* Iterate through the packages and add their names as well. */
		while (ePackage != null) {
			result.add(0, ePackage.getName());
			ePackage = ePackage.getESuperPackage();
			
			/* FIXME Claas: Ask Micha if their is a better way to find the super packages. */
		}
		// end while.

		return result;
	}
}