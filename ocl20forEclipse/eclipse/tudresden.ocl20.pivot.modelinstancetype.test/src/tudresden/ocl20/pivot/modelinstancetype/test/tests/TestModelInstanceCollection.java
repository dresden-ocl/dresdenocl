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
package tudresden.ocl20.pivot.modelinstancetype.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.osgi.util.NLS;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.TypeConstants;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestPlugin;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestServices;
import tudresden.ocl20.pivot.modelinstancetype.test.msg.ModelInstanceTypeTestSuiteMessages;
import tudresden.ocl20.pivot.modelinstancetype.test.testmodel.TestModelTypesNames;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases to test the implementation of
 * {@link tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection}
 * .
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestModelInstanceCollection {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelInstanceTypeTestPlugin.getLogger(TestModelInstanceCollection.class);

	/** A {@link String} used to display and log messages and warnings. */
	private static String msg;

	/** A {@link Type} used in this test class. */
	private static Type type_CollectionTypeProviderClass;

	/** A {@link Type} used in this test class. */
	private static CollectionType type_bag;

	/** A {@link Type} used in this test class. */
	private static CollectionType type_orderedset;

	/** A {@link Type} used in this test class. */
	private static CollectionType type_sequence;

	/** A {@link Type} used in this test class. */
	private static CollectionType type_set;

	/** All instances of the <code>CollectionTypeProviderClass</code>. */
	private static Set<IModelInstanceObject> instances_CollectionTypeProviderClass;

	/** All instances of the {@link CollectionKind#BAG}. */
	private static Set<IModelInstanceCollection<?>> instances_bag;

	/** All instances of the {@link CollectionKind#ORDERED_SET}. */
	private static Set<IModelInstanceCollection<?>> instances_orderedset;

	/** All instances of the {@link CollectionKind#SEQUENCE}. */
	private static Set<IModelInstanceCollection<?>> instances_sequence;

	/** All instances of the {@link CollectionKind#SET}. */
	private static Set<IModelInstanceCollection<?>> instances_set;

	/** All instances of {@link CollectionType}s. */
	private static Set<IModelInstanceCollection<?>> instances_allCollections;

	/**
	 * <p>
	 * Loads some objects required during the tests.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		/* Get a collection type from the model. */
		type_bag = TypeConstants.BAG;

		/* Get a collection type from the model. */
		type_orderedset = TypeConstants.ORDERED_SET;

		/* Get a collection type from the model. */
		type_sequence = TypeConstants.SEQUENCE;

		/* Get a collection type from the model. */
		type_set = TypeConstants.SET;

		/* Get the CollectionType's provider class from the model. */
		type_CollectionTypeProviderClass =
				ModelInstanceTypeTestServices.getInstance().getModelType(
						TestModelTypesNames.TYPE_NAME_COLLECTION_TYPE_PROVIDER_CLASS);

		/* Load all instances of the type from the model instance. */
		instances_CollectionTypeProviderClass =
				ModelInstanceTypeTestServices.getInstance()
						.getModelInstanceObjectsOfType(type_CollectionTypeProviderClass);

		/* Check if any provider class instance has been found. */
		if (instances_CollectionTypeProviderClass.size() != 0) {

			List<Property> bagProperties;
			List<Property> orderedSetProperties;
			List<Property> sequenceProperties;
			List<Property> setProperties;

			bagProperties = new ArrayList<Property>();
			orderedSetProperties = new ArrayList<Property>();
			sequenceProperties = new ArrayList<Property>();
			setProperties = new ArrayList<Property>();

			/* Get the properties from the provider class. */
			for (Property aProperty : type_CollectionTypeProviderClass
					.getOwnedProperty()) {

				if (aProperty.getName().startsWith("bag")) {
					bagProperties.add(aProperty);
				}
				// no else.

				else if (aProperty.getName().startsWith("orderedSet")) {
					orderedSetProperties.add(aProperty);
				}
				// no else.

				else if (aProperty.getName().startsWith("sequence")) {
					sequenceProperties.add(aProperty);
				}
				// no else.

				else if (aProperty.getName().startsWith("set")) {
					setProperties.add(aProperty);
				}
				// no else.
			}

			instances_bag = new HashSet<IModelInstanceCollection<?>>();
			instances_orderedset = new HashSet<IModelInstanceCollection<?>>();
			instances_sequence = new HashSet<IModelInstanceCollection<?>>();
			instances_set = new HashSet<IModelInstanceCollection<?>>();

			/* Get the property values from all provider class instances. */
			for (IModelInstanceObject aProviderInstance : instances_CollectionTypeProviderClass) {

				for (Property aBagProperty : bagProperties) {
					IModelInstanceElement aBagResult;

					try {
						aBagResult = aProviderInstance.getProperty(aBagProperty);

						if (aBagResult != null
								&& aBagResult instanceof IModelInstanceCollection<?>) {
							instances_bag.add((IModelInstanceCollection<?>) aBagResult);
						}
						// no else.
					}

					catch (PropertyAccessException e) {
						/* Do nothing. */
					}

					catch (PropertyNotFoundException e) {
						/* Do nothing. */
					}
				}
				// end for.

				for (Property anOrderedSetProperty : orderedSetProperties) {
					IModelInstanceElement anOrderedSetResult;

					try {
						anOrderedSetResult =
								aProviderInstance.getProperty(anOrderedSetProperty);

						if (anOrderedSetResult != null
								&& anOrderedSetResult instanceof IModelInstanceCollection<?>) {
							instances_orderedset
									.add((IModelInstanceCollection<?>) anOrderedSetResult);
						}
						// no else.
					}

					catch (PropertyAccessException e) {
						/* Do nothing. */
					}

					catch (PropertyNotFoundException e) {
						/* Do nothing. */
					}
				}
				// end for.

				for (Property aSequenceProperty : sequenceProperties) {
					IModelInstanceElement aSequenceResult;

					try {
						aSequenceResult = aProviderInstance.getProperty(aSequenceProperty);

						if (aSequenceResult != null
								&& aSequenceResult instanceof IModelInstanceCollection<?>) {
							instances_sequence
									.add((IModelInstanceCollection<?>) aSequenceResult);
						}
						// no else.
					}

					catch (PropertyAccessException e) {
						/* Do nothing. */
					}

					catch (PropertyNotFoundException e) {
						/* Do nothing. */
					}
				}
				// end for.

				for (Property aSetProperty : setProperties) {
					IModelInstanceElement aBagResult;

					try {
						aBagResult = aProviderInstance.getProperty(aSetProperty);

						if (aBagResult != null
								&& aBagResult instanceof IModelInstanceCollection<?>) {
							instances_set.add((IModelInstanceCollection<?>) aBagResult);
						}
						// no else.
					}

					catch (PropertyAccessException e) {
						/* Do nothing. */
					}

					catch (PropertyNotFoundException e) {
						/* Do nothing. */
					}
				}
				// end for.
			}
			// end for.

			if (instances_bag.size() == 0 && LOGGER.isDebugEnabled()) {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_NoBagInstanceFound;

				LOGGER.warn(msg);
			}
			// no else.

			if (instances_orderedset.size() == 0 && LOGGER.isDebugEnabled()) {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_NoOrderedSetInstanceFound;

				LOGGER.warn(msg);
			}
			// no else.

			if (instances_sequence.size() == 0 && LOGGER.isDebugEnabled()) {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_NoSequenceInstanceFound;

				LOGGER.warn(msg);
			}
			// no else.

			if (instances_set.size() == 0 && LOGGER.isDebugEnabled()) {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_NoSetInstanceFound;

				LOGGER.warn(msg);
			}
			// no else.
		}

		/* Else print a warning. */
		else {
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_NoProviderClassInstanceFound;

			LOGGER.warn(msg);
		}

		instances_allCollections = new HashSet<IModelInstanceCollection<?>>();
		instances_allCollections.addAll(instances_bag);
		instances_allCollections.addAll(instances_orderedset);
		instances_allCollections.addAll(instances_sequence);
		instances_allCollections.addAll(instances_set);
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#asType(Type)}.
	 * </p>
	 */
	@Test
	public void testAsType01() {

		/* Check as type with all types possible to cast. */
		for (IModelInstanceCollection<?> aCollection : instances_allCollections) {

			IModelInstanceElement aBag;
			IModelInstanceElement anOrderedSet;
			IModelInstanceElement aSequence;
			IModelInstanceElement aSet;

			/* Test as type with bag type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_AsTypeIsWrong;
				msg = NLS.bind(msg, type_bag);

				aBag = aCollection.asType(type_bag);

				/* The casted element should be a Collection. */
				assertTrue(msg, aBag instanceof IModelInstanceCollection<?>);

				assertNotNull(msg, aBag.getType());

				assertTrue(msg, aBag.getType() instanceof CollectionType);
				assertEquals(msg, CollectionKind.BAG, ((CollectionType) aBag.getType())
						.getKind());

				/* The bag should not be ordered and not unique. */
				assertFalse(msg, ((IModelInstanceCollection<?>) aBag).isOrdered());
				assertFalse(msg, ((IModelInstanceCollection<?>) aBag).isUnique());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Test as type with OrderedSet type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_AsTypeIsWrong;
				msg = NLS.bind(msg, type_orderedset);

				anOrderedSet = aCollection.asType(type_orderedset);

				/* The casted element should be a Collection. */
				assertTrue(msg, anOrderedSet instanceof IModelInstanceCollection<?>);

				/* The OrderedSet should have one type and the type OrderedSet. */
				assertNotNull(msg, anOrderedSet.getType());

				assertTrue(msg, anOrderedSet.getType() instanceof CollectionType);
				assertEquals(msg, CollectionKind.ORDERED_SET,
						((CollectionType) anOrderedSet.getType()).getKind());

				/* The OrderedSet should be ordered and unique. */
				assertTrue(msg, ((IModelInstanceCollection<?>) anOrderedSet)
						.isOrdered());
				assertTrue(msg, ((IModelInstanceCollection<?>) anOrderedSet).isUnique());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Test as type with Sequence type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_AsTypeIsWrong;
				msg = NLS.bind(msg, type_sequence);

				aSequence = aCollection.asType(type_sequence);

				/* The casted element should be a Collection. */
				assertTrue(msg, aSequence instanceof IModelInstanceCollection<?>);

				/* The Sequence should have one type and the type Sequence. */
				assertNotNull(msg, aSequence.getType());

				assertTrue(msg, aSequence.getType() instanceof CollectionType);
				assertEquals(msg, CollectionKind.SEQUENCE, ((CollectionType) aSequence
						.getType()).getKind());

				/* The Sequence should be ordered but not unique. */
				assertTrue(msg, ((IModelInstanceCollection<?>) aSequence).isOrdered());
				assertFalse(msg, ((IModelInstanceCollection<?>) aSequence).isUnique());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Test as type with Set type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_AsTypeIsWrong;
				msg = NLS.bind(msg, type_set);

				aSet = aCollection.asType(type_set);

				/* The casted element should be a Collection. */
				assertTrue(msg, aSet instanceof IModelInstanceCollection<?>);

				/* The Set should have one type and the type Set. */
				assertNotNull(msg, aSet.getType());

				assertTrue(msg, aSet.getType() instanceof CollectionType);
				assertEquals(msg, CollectionKind.SET, ((CollectionType) aSet.getType())
						.getKind());

				/* The Set should not be ordered but unique. */
				assertFalse(msg, ((IModelInstanceCollection<?>) aSet).isOrdered());
				assertTrue(msg, ((IModelInstanceCollection<?>) aSet).isUnique());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#asType(Type)} with illegal
	 * arguments.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAsType02() throws AsTypeCastException {

		/* Check as type with all types possible to cast. */
		for (IModelInstanceCollection<?> aCollection : instances_allCollections) {

			aCollection.asType(null);
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#copyForAtPre()}.
	 * </p>
	 */
	@Test
	public void testCopyForAtPre() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_CopyForAtPreIsWrong;

		/* A Collection should be copy-able. */
		for (IModelInstanceCollection<?> aCollection : instances_allCollections) {

			try {
				assertNotNull(msg, aCollection.copyForAtPre());
			}

			catch (CopyForAtPreException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_EqualsIsWrong;

		/* An CopyableClass instance should be copy-able. */
		for (IModelInstanceCollection<?> aCollection : instances_allCollections) {

			for (IModelInstanceCollection<?> anotherCollection : instances_allCollections) {

				if (aCollection.isOrdered() == anotherCollection.isOrdered()
						&& aCollection.isUnique() == anotherCollection.isUnique()
						&& aCollection.getType() != null
						&& anotherCollection.getType() != null
						&& (aCollection.getType().conformsTo(anotherCollection.getType()) || anotherCollection
								.getType().conformsTo(aCollection.getType()))
						&& aCollection.getCollection().equals(
								anotherCollection.getCollection())) {

					assertTrue(msg, aCollection.equals(anotherCollection));
				}

				else {
					assertFalse(msg, aCollection.equals(anotherCollection));
				}
				// end else.

				/* No collection should be equal to null. */
				assertFalse(msg, aCollection.equals(null));
			}
			// end for.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#getCollection()}.
	 * </p>
	 */
	@Test
	public void testGetCollection1() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_GetCollectionIsWrong;
		msg = NLS.bind(msg, type_bag, List.class.getCanonicalName());

		/* The method should return a List or should be undefined. */
		for (IModelInstanceCollection<?> aCollection : instances_bag) {

			if (aCollection.isUndefined()) {
				assertNull(msg, aCollection.getCollection());
			}

			else {
				assertNotNull(msg, aCollection.getCollection());
				assertTrue(msg, aCollection.getCollection() instanceof List<?>);
			}
		}
		// end for.
	}

	// /**
	// * <p>
	// * Tests the method {@link IModelInstanceBoolean#asType(Type)}.
	// * </p>
	// */
	// @Test
	// public void testAsType() {
	//
	// msg =
	// ModelInstanceTypeTestSuiteMessages.TestModelInstanceBoolean_AsTypeIsWrong;
	// msg = NLS.bind(msg, type_string);
	//
	// /* Check as type with all types possible to cast. */
	// for (IModelInstanceElement anElement : instances_boolean) {
	//
	// IModelInstanceBoolean aBoolean;
	// IModelInstanceElement aString;
	//
	// aBoolean = (IModelInstanceBoolean) anElement;
	//
	// /* Test as type with string type. */
	// try {
	// aString = aBoolean.asType(type_string);
	//
	// /* The casted element should be a String. */
	// assertTrue(msg, aString instanceof IModelInstanceString);
	//
	// /* The value should depend on the boolean value. */
	// if (aBoolean.getBoolean()) {
	// assertEquals(msg, "true", ((IModelInstanceString) aString)
	// .getString());
	// }
	//
	// else {
	// assertEquals(msg, "false", ((IModelInstanceString) aString)
	// .getString());
	// }
	// }
	//
	// catch (AsTypeCastException e) {
	// fail(msg);
	// }
	//
	// }
	// // end for.
	// }
	//
	// /**
	// * <p>
	// * Tests the method {@link IModelInstanceBoolean#copyForAtPre()}.
	// * </p>
	// */
	// @Test
	// public void testCopyForAtPre() {
	//
	// msg =
	// ModelInstanceTypeTestSuiteMessages.TestModelInstanceBoolean_CopyForAtPreIsWrong;
	//
	// /* A boolean should be copy-able. */
	// for (IModelInstanceElement anElement : instances_boolean) {
	//
	// IModelInstanceBoolean aBoolean;
	// aBoolean = (IModelInstanceBoolean) anElement;
	//
	// try {
	// aBoolean.copyForAtPre();
	// }
	//
	// catch (CopyForAtPreException e) {
	// fail(msg);
	// }
	// }
	// // end for.
	// }

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#getCollection()}.
	 * </p>
	 */
	@Test
	public void testGetCollection2() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_GetCollectionIsWrong;
		msg = NLS.bind(msg, type_orderedset, UniqueEList.class.getCanonicalName());

		/* The method should return a List or should be undefined. */
		for (IModelInstanceCollection<?> aCollection : instances_orderedset) {

			if (aCollection.isUndefined()) {
				assertNull(msg, aCollection.getCollection());
			}

			else {
				assertNotNull(msg, aCollection.getCollection());
				assertTrue(msg, aCollection.getCollection() instanceof UniqueEList<?>);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#getCollection()}.
	 * </p>
	 */
	@Test
	public void testGetCollection3() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_GetCollectionIsWrong;
		msg = NLS.bind(msg, type_sequence, List.class.getCanonicalName());

		/* The method should return a List or should be undefined. */
		for (IModelInstanceCollection<?> aCollection : instances_sequence) {

			if (aCollection.isUndefined()) {
				assertNull(msg, aCollection.getCollection());
			}

			else {
				assertNotNull(msg, aCollection.getCollection());
				assertTrue(msg, aCollection.getCollection() instanceof List<?>);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#getCollection()}.
	 * </p>
	 */
	@Test
	public void testGetCollection4() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_GetCollectionIsWrong;
		msg = NLS.bind(msg, type_set, Set.class.getCanonicalName());

		/* The method should return a Set or should be undefined. */
		for (IModelInstanceCollection<?> aCollection : instances_set) {

			if (aCollection.isUndefined()) {
				assertNull(msg, aCollection.getCollection());
			}

			else {
				assertNotNull(msg, aCollection.getCollection());
				assertTrue(msg, aCollection.getCollection() instanceof Set<?>);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType1() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_GetTypesIsWrong;
		msg = NLS.bind(msg, "Bag", CollectionKind.BAG.toString());

		for (IModelInstanceCollection<?> aCollection : instances_bag) {

			assertNotNull(msg, aCollection.getType());

			/* A Bag should have the CollectionType of the kind Bag. */
			assertTrue(msg, aCollection.getType() instanceof CollectionType);
			assertEquals(msg, CollectionKind.BAG, ((CollectionType) aCollection
					.getType()).getKind());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType2() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_GetTypesIsWrong;
		msg = NLS.bind(msg, "OrderedSet", CollectionKind.ORDERED_SET.toString());

		for (IModelInstanceCollection<?> aCollection : instances_orderedset) {

			assertNotNull(msg, aCollection.getType());

			/* An OrderedSet should have the CollectionType of the kind OrderedSet. */
			assertTrue(msg, aCollection.getType() instanceof CollectionType);
			assertEquals(msg, CollectionKind.ORDERED_SET,
					((CollectionType) aCollection.getType()).getKind());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType3() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_GetTypesIsWrong;
		msg = NLS.bind(msg, "Sequence", CollectionKind.SEQUENCE.toString());

		for (IModelInstanceCollection<?> aCollection : instances_sequence) {

			assertNotNull(msg, aCollection.getType());

			/* An Sequence should have the CollectionType of the kind OrderedSet. */
			assertTrue(msg, aCollection.getType() instanceof CollectionType);
			assertEquals(msg, CollectionKind.SEQUENCE, ((CollectionType) aCollection
					.getType()).getKind());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType4() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_GetTypesIsWrong;
		msg = NLS.bind(msg, "Set", CollectionKind.SET.toString());

		for (IModelInstanceCollection<?> aCollection : instances_set) {

			assertNotNull(msg, aCollection.getType());

			/* An Set should have the CollectionType of the kind OrderedSet. */
			assertTrue(msg, aCollection.getType() instanceof CollectionType);
			assertEquals(msg, CollectionKind.SET, ((CollectionType) aCollection
					.getType()).getKind());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered1() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_IsOrderedIsWrong;
		msg = NLS.bind(msg, type_bag, "not ");

		/* A Bag should not be ordered. */
		for (IModelInstanceCollection<?> aCollection : instances_bag) {

			assertFalse(msg, aCollection.isOrdered());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered2() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_IsOrderedIsWrong;
		msg = NLS.bind(msg, type_orderedset, "");

		/* An OrderedSet should be ordered. */
		for (IModelInstanceCollection<?> aCollection : instances_orderedset) {

			assertTrue(msg, aCollection.isOrdered());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered3() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_IsOrderedIsWrong;
		msg = NLS.bind(msg, type_sequence, "");

		/* A Sequence should be ordered. */
		for (IModelInstanceCollection<?> aCollection : instances_sequence) {

			assertTrue(msg, aCollection.isOrdered());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered4() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_IsOrderedIsWrong;
		msg = NLS.bind(msg, type_set, "not ");

		/* A Set should not be ordered. */
		for (IModelInstanceCollection<?> aCollection : instances_set) {

			assertFalse(msg, aCollection.isOrdered());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_IsUndefinedIsWrong;

		/* The method should return null if the collection is undefined. */
		for (IModelInstanceCollection<?> aCollection : instances_allCollections) {

			if (aCollection.isUndefined()) {
				assertNull(msg, aCollection.getCollection());
			}

			else {
				assertNotNull(msg, aCollection.getCollection());
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique1() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_IsUniqueIsWrong;
		msg = NLS.bind(msg, type_bag, "not ");

		/* The bag should not be unique. */
		for (IModelInstanceCollection<?> aCollection : instances_bag) {

			assertFalse(msg, aCollection.isUnique());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique2() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_IsUniqueIsWrong;
		msg = NLS.bind(msg, type_orderedset, "");

		/* The OrderedSet should be unique. */
		for (IModelInstanceCollection<?> aCollection : instances_orderedset) {

			assertTrue(msg, aCollection.isUnique());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique3() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_IsUniqueIsWrong;
		msg = NLS.bind(msg, type_sequence, "not ");

		/* The Sequence should not be unique. */
		for (IModelInstanceCollection<?> aCollection : instances_sequence) {

			assertFalse(msg, aCollection.isUnique());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceCollection#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique4() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceCollection_IsUniqueIsWrong;
		msg = NLS.bind(msg, type_set, "");

		/* The Set should be unique. */
		for (IModelInstanceCollection<?> aCollection : instances_set) {

			assertTrue(msg, aCollection.isUnique());
		}
		// end for.
	}
}