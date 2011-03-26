/*
Copyright (C) 2010 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of OCL2 Intepreter Test Suite of Dresden OCL.

Dresden OCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.essentialocl.types.tests;

import junit.framework.TestCase;
import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.types.InvalidType;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * Tests the InvalidType implementation.
 * 
 * @generated NOT
 */
public class InvalidTypeTest extends TestCase {

	/**
	 * Test the operation {@link Type#conformsTo(Type)} for {@link InvalidType}.
	 * 
	 * @generated NOT
	 */
	public void testConformsTo01() {

		Type invalidType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclInvalid();
		Type elementType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclInteger();

		assertTrue(invalidType.conformsTo(invalidType));

		/* OclInvalid does conform to OclInvalid. */
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclInvalid()));

		/* OclInvalid does conform to other library types. */
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclAny()));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary()
				.getBagType(elementType)));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary()
				.getCollectionType(elementType)));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclBag()));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclBoolean()));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclCollection()));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclInteger()));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclOrderedSet()));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclReal()));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclSequence()));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclSet()));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclString()));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclType()));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary()
				.getOrderedSetType(elementType)));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary()
				.getSequenceType(elementType)));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary()
				.getSetType(elementType)));
		assertTrue(invalidType.conformsTo(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary()
				.getTypeType(elementType)));

		/* OclInvalid does conform to model defined types. */
		Type aType = PivotModelFactory.eINSTANCE.createType();
		aType.setName("SomeModelType");
		assertTrue(invalidType.conformsTo(aType));
	}
}
