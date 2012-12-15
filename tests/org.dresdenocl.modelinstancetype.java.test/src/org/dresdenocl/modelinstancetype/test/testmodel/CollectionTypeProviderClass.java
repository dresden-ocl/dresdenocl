/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Java Model Instance Type Test Suite of Dresden 
OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelinstancetype.test.testmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.pivotmodel.Property;

/**
 * <p>
 * This class provides some properties that can be used to load instances of
 * {@link CollectionType}s required for testing.
 * </p>
 * 
 * @author Claas Wilke
 */
public class CollectionTypeProviderClass {

	/**
	 * A {@link Property} that returns an instance of a {@link CollectionType} of
	 * the {@link CollectionKind#BAG}.
	 */
	protected String[] bagProperty1 =
			new String[] { "First element", "Second element", "Third element" };

	/**
	 * A {@link Property} that returns an instance of a {@link CollectionType} of
	 * the {@link CollectionKind#OrderedSet}.
	 */
	protected int[] orderedSetProperty1 = new int[] { 1, 2, 3 };

	/**
	 * A {@link Property} that returns an instance of a {@link CollectionType} of
	 * the {@link CollectionKind#SEQUENCE}.
	 */
	protected List<String> sequenceProperty1 =
			new ArrayList<String>(Arrays.asList(new String[] { "1st element",
					"2nd element", "3rd element" }));

	/**
	 * A {@link Property} that returns an instance of a {@link CollectionType} of
	 * the {@link CollectionKind#SEQUENCE}.
	 */
	@SuppressWarnings("unchecked")
	protected List sequenceProperty2 =
			new ArrayList(Arrays.asList(new String[] { "1st element",
					"2nd element", "3rd element" }));

	/**
	 * A {@link Property} that returns an instance of a {@link CollectionType} of
	 * the {@link CollectionKind#SET}.
	 */
	protected Set<String> setProperty1 =
			new HashSet<String>(Arrays.asList(new String[] { "1st", "2nd", "3rd" }));
}