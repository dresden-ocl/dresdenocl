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

import java.util.Set;

/**
 * <p>
 * A {@link Class} that provides fields containing all instances that shall be
 * used for testing.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ContainerClass {

	/** A field providing instances of {@link PrimitiveTypeProviderClass}. */
	public Set<PrimitiveTypeProviderClass> primitiveTypeProviderClassInstances;

	/** A field providing instances of {@link CollectionTypeProviderClass}. */
	public Set<CollectionTypeProviderClass> collectionTypeProviderClassInstances;

	/** A field providing instances of {@link EnumerationLiteralProviderClass}. */
	public Set<EnumerationLiteralProviderClass> enumerationLiteralProviderClassInstances;

	/** A field providing instances of {@link Class1}. */
	public Set<Class1> class1Instances;

	/** A field providing instances of {@link Class2}. */
	public Set<Class2> class2Instances;

	/** A field providing instances of {@link StaticPropertyAndOperationClass}. */
	public Set<StaticPropertyAndOperationClass> staticPropertyAndOperationClassInstances;

	/** A field providing instances of {@link Interface1}. */
	public Set<Interface1> interface1Instances;

	/** A field providing instances of {@link Interface2}. */
	public Set<Interface2> interface2Instances;

	/** A field providing instances of {@link Interface3}. */
	public Set<Interface3> interface3Instances;

	/** A field providing instances of {@link CopyableClass}. */
	public Set<CopyableClass> copyableClassInstances;

	/** A field providing instances of {@link NonCopyableClass}. */
	public Set<NonCopyableClass> nonCopyableClassInstances;
}