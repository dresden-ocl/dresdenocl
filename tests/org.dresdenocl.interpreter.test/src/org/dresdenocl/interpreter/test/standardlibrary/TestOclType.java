/*
Copyright (C) 2010 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of the PAIN Case Study of Dresden OCL2 for Eclipse.

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
package org.dresdenocl.interpreter.test.standardlibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.standardlibrary.OclBoolean;
import org.dresdenocl.essentialocl.standardlibrary.OclIterator;
import org.dresdenocl.essentialocl.standardlibrary.OclSet;
import org.dresdenocl.essentialocl.standardlibrary.OclVoid;
import org.dresdenocl.essentialocl.types.OclLibrary;
import org.dresdenocl.interpreter.IInterpretationResult;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.parser.ParseException;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Contains some test cases to test Standard Library operations on
 * <code>OclInvalid</code> literals.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestOclType extends AbstractInterpreterTest {

	/** The name of the constraint directory for this test suite. */
	private static final String CONSTRAINT_DIRECTORY = "standardlibrary/ocltype";

	/**
	 * <p>
	 * Tests the operation <code>OclType.allInstances()</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testAllInstances01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/allInstances01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		IInterpretationResult result = results.get(0);
		OclLibrary oclLib = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary();
		Type booleanType = oclLib.getOclBoolean();
		Type resultType = oclLib.getSetType(booleanType);

		/* Boolean.allInstances = Set { true, false }. */
		this.assertIsCollectionOfSize(2, result);
		this.assertIsOfType(resultType, result);

		assertTrue(result.getResult() instanceof OclSet<?>);

		OclSet<OclBoolean> oclSet = (OclSet<OclBoolean>) result.getResult();
		OclIterator<OclBoolean> it = oclSet.getIterator();

		int invalid = 0;
		int undefined = 0;
		int trues = 0;
		int falses = 0;

		while (it.hasNext().isTrue()) {
			OclBoolean elem = it.next();

			if (elem.oclIsInvalid().isTrue())
				invalid++;
			else if (elem.oclIsUndefined().isTrue())
				undefined++;
			else if (elem.isTrue())
				trues++;
			else
				falses++;
		}

		assertEquals(0, invalid);
		assertEquals(0, undefined);
		assertEquals(1, trues);
		assertEquals(1, falses);
	}

	/**
	 * <p>
	 * Tests the operation <code>OclType.allInstances()</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAllInstances02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/allInstances02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		/* OclInvalid.allInstances = invalid */
		IInterpretationResult result = results.get(0);
		this.assertIsInvalid(result);
	}

	/**
	 * <p>
	 * Tests the operation <code>OclType.allInstances()</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testAllInstances03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/allInstances03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		IInterpretationResult result = results.get(0);
		OclLibrary oclLib = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary();
		Type voidType = oclLib.getOclVoid();
		Type resultType = oclLib.getSetType(voidType);

		/* OclVoid.allInstances = Set { null }. */
		this.assertIsCollectionOfSize(1, result);
		this.assertIsOfType(resultType, result);

		assertTrue(result.getResult() instanceof OclSet<?>);

		OclSet<OclVoid> oclSet = (OclSet<OclVoid>) result.getResult();
		OclIterator<OclVoid> it = oclSet.getIterator();

		int invalid = 0;
		int undefined = 0;

		while (it.hasNext().isTrue()) {
			OclVoid elem = it.next();

			if (elem.oclIsInvalid().isTrue())
				invalid++;
			else if (elem.oclIsUndefined().isTrue())
				undefined++;
			// no else.
		}

		assertEquals(0, invalid);
		assertEquals(1, undefined);
	}
}