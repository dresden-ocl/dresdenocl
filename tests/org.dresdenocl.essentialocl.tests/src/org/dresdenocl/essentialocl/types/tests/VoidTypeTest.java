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
package org.dresdenocl.essentialocl.types.tests;

import junit.framework.TestCase;
import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.types.VoidType;
import org.dresdenocl.pivotmodel.PivotModelFactory;
import org.dresdenocl.pivotmodel.Type;

/**
 * Tests the VoidType implementation.
 * 
 * @generated NOT
 */
public class VoidTypeTest extends TestCase {

	/**
	 * Test the operation {@link Type#conformsTo(Type)} for {@link VoidType}.
	 * 
	 * @generated NOT
	 */
	public void testConformsTo01() {

		Type voidType =
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclVoid();

		assertTrue(voidType.conformsTo(voidType));

		/* OclVoid does not conform to OclInvalid. */
		assertFalse(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclInvalid()));

		/* OclVoid does conform to other library types. */
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclAny()));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getBagType(voidType)));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getCollectionType(voidType)));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclBag()));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclBoolean()));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclCollection()));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclInteger()));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclOrderedSet()));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclReal()));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclSequence()));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclSet()));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclString()));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclType()));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOrderedSetType(voidType)));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getSequenceType(voidType)));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getSetType(voidType)));
		assertTrue(voidType.conformsTo(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getTypeType(voidType)));

		/* OclVoid does conform to model defined types. */
		Type aType = PivotModelFactory.eINSTANCE.createType();
		aType.setName("SomeModelType");
		assertTrue(voidType.conformsTo(aType));
	}
} // VoidTypeTest
