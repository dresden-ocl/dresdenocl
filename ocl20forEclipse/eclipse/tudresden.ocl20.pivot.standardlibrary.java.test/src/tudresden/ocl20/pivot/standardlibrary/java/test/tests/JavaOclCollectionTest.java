package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.UniqueEList;
import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * Test for collections in OCL.
 * 
 * @author Michael Thiele
 */
public class JavaOclCollectionTest {

	private final IStandardLibraryFactory myStandardLibraryFactory = TestPerformer
			.getInstance().getSLFactory();

	private final OclCollection<OclAny> emptySet = myStandardLibraryFactory
			.createOclSet(new HashSet<Object>(), EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclAny());
	private final OclCollection<OclAny> emptyBag = myStandardLibraryFactory
			.createOclBag(new ArrayList<Object>(), EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclAny());
	private final OclCollection<OclAny> emptyOrderedSet = myStandardLibraryFactory
			.createOclOrderedSet(new UniqueEList<Object>(), EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclAny());
	private final OclCollection<OclAny> emptySequence = myStandardLibraryFactory
			.createOclSequence(new ArrayList<Object>(), EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclAny());

	/**
	 * Set { 0.5 }
	 */
	private OclCollection<OclReal> oclSet;

	/**
	 * Bag { 0.5 }
	 */
	private OclCollection<OclReal> oclBag;

	/**
	 * OrderedSet { 0.5 }
	 */
	private OclCollection<OclReal> oclOrderedSet;

	/**
	 * Sequence { 0.5 }
	 */
	private OclCollection<OclReal> oclSequence;

	/**
	 * Set { 0.5, 1.5, null, null }
	 */
	private OclCollection<OclReal> oclSet2;

	/**
	 * Bag { 0.5, 1.5, null, null }
	 */
	private OclCollection<OclReal> oclBag2;

	/**
	 * OrderedSet { 0.5, 1.5, null, null }
	 */
	private OclCollection<OclReal> oclOrderedSet2;

	/**
	 * Sequence { 0.5, 1.5, null, null }
	 */
	private OclCollection<OclReal> oclSequence2;

	private final OclReal oclReal0_5 = myStandardLibraryFactory
			.createOclReal(0.5);
	private final OclReal oclReal1_5 = myStandardLibraryFactory
			.createOclReal(1.5);

	private final OclInteger integer0 = myStandardLibraryFactory
			.createOclInteger(0L);
	private final OclInteger integer1 = myStandardLibraryFactory
			.createOclInteger(1L);
	private final OclInteger integer2 = myStandardLibraryFactory
			.createOclInteger(2L);
	private final OclInteger integer3 = myStandardLibraryFactory
			.createOclInteger(3L);
	private final OclInteger integer4 = myStandardLibraryFactory
			.createOclInteger(4L);

	private final OclReal undefined = myStandardLibraryFactory
			.createOclUndefined(EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getOclReal(), "undefined value");
	private final OclReal undefined2 = myStandardLibraryFactory
			.createOclUndefined(EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getOclReal(), "undefined value2");

	@Before
	public void init() {

		Set<OclReal> realSet = new HashSet<OclReal>();
		realSet.add(oclReal0_5);
		oclSet = myStandardLibraryFactory.createOclSet(realSet,
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal());

		List<OclReal> realBag = new ArrayList<OclReal>();
		realBag.add(oclReal0_5);
		oclBag = myStandardLibraryFactory.createOclBag(realBag,
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal());

		UniqueEList<OclReal> realOrderedSet = new UniqueEList<OclReal>();
		realOrderedSet.add(oclReal0_5);
		oclOrderedSet = myStandardLibraryFactory.createOclOrderedSet(
				realOrderedSet, EssentialOclPlugin.getOclLibraryProvider()
						.getOclLibrary().getOclReal());

		oclSequence = myStandardLibraryFactory.createOclSequence(realBag,
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal());

		realSet.add(oclReal1_5);
		realSet.add(undefined);
		realSet.add(undefined2);
		// realSet.add(invalid);
		oclSet2 = myStandardLibraryFactory.createOclSet(realSet,
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal());

		realBag.add(oclReal1_5);
		realBag.add(undefined);
		realBag.add(undefined2);
		// realBag.add(invalid);
		oclBag2 = myStandardLibraryFactory.createOclBag(realBag,
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal());

		realOrderedSet.add(oclReal1_5);
		realOrderedSet.add(undefined);
		realOrderedSet.add(undefined2);
		// realOrderedSet.add(invalid);
		oclOrderedSet2 = myStandardLibraryFactory.createOclOrderedSet(
				realOrderedSet, EssentialOclPlugin.getOclLibraryProvider()
						.getOclLibrary().getOclReal());

		oclSequence2 = myStandardLibraryFactory.createOclSequence(realBag,
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal());

	}

	@Test
	public void testAsSet() {

		assertTrue(emptySet.asSet().isEqualTo(emptySet).isTrue());
		assertTrue(emptySet.asSet() != emptySet);
		assertTrue(emptyBag.asSet().isEqualTo(emptySet).isTrue());
		assertTrue(emptyOrderedSet.asSet().isEqualTo(emptySet).isTrue());
		assertTrue(emptySequence.asSet().isEqualTo(emptySet).isTrue());
		assertTrue(oclSet.asSet().isEqualTo(oclSet).isTrue());
		assertTrue(oclSet.asSet() != oclSet);
		assertTrue(oclBag.asSet().isEqualTo(oclSet).isTrue());
		assertTrue(oclOrderedSet.asSet().isEqualTo(oclSet).isTrue());
		assertTrue(oclSequence.asSet().isEqualTo(oclSet).isTrue());
	}

	@Test
	public void testAsBag() {

		assertTrue(emptySet.asBag().isEqualTo(emptyBag).isTrue());
		assertTrue(emptyBag.asBag() != emptyBag);
		assertTrue(emptyBag.asBag().isEqualTo(emptyBag).isTrue());
		assertTrue(emptyOrderedSet.asBag().isEqualTo(emptyBag).isTrue());
		assertTrue(emptySequence.asBag().isEqualTo(emptyBag).isTrue());
		assertTrue(oclSet.asBag().isEqualTo(oclBag).isTrue());
		assertTrue(oclBag.asBag() != oclBag);
		assertTrue(oclBag.asBag().isEqualTo(oclBag).isTrue());
		assertTrue(oclOrderedSet.asBag().isEqualTo(oclBag).isTrue());
		assertTrue(oclSequence.asBag().isEqualTo(oclBag).isTrue());
	}

	@Test
	public void testAsOrderedSet() {

		assertTrue(emptySet.asOrderedSet().isEqualTo(emptyOrderedSet).isTrue());
		assertTrue(emptyOrderedSet.asOrderedSet() != emptyOrderedSet);
		assertTrue(emptyOrderedSet.asOrderedSet().isEqualTo(emptyOrderedSet)
				.isTrue());
		assertTrue(emptyOrderedSet.asOrderedSet().isEqualTo(emptyOrderedSet)
				.isTrue());
		assertTrue(emptySequence.asOrderedSet().isEqualTo(emptyOrderedSet)
				.isTrue());
		assertTrue(oclSet.asOrderedSet().isEqualTo(oclOrderedSet).isTrue());
		assertTrue(oclOrderedSet.asOrderedSet() != oclOrderedSet);
		assertTrue(oclOrderedSet.asOrderedSet().isEqualTo(oclOrderedSet)
				.isTrue());
		assertTrue(oclOrderedSet.asOrderedSet().isEqualTo(oclOrderedSet)
				.isTrue());
		assertTrue(oclSequence.asOrderedSet().isEqualTo(oclOrderedSet).isTrue());
	}

	@Test
	public void testAsSequence() {

		assertTrue(emptySet.asSequence().isEqualTo(emptySequence).isTrue());
		assertTrue(emptySequence.asSequence() != emptySequence);
		assertTrue(emptySequence.asSequence().isEqualTo(emptySequence).isTrue());
		assertTrue(emptyOrderedSet.asSequence().isEqualTo(emptySequence)
				.isTrue());
		assertTrue(emptySequence.asSequence().isEqualTo(emptySequence).isTrue());
		assertTrue(oclSet.asSequence().isEqualTo(oclSequence).isTrue());
		assertTrue(oclSequence.asSequence() != oclSequence);
		assertTrue(oclSequence.asSequence().isEqualTo(oclSequence).isTrue());
		assertTrue(oclOrderedSet.asSequence().isEqualTo(oclSequence).isTrue());
		assertTrue(oclSequence.asSequence().isEqualTo(oclSequence).isTrue());
	}

	@Test
	public void testCount() {

		assertTrue(emptySet.count(oclReal0_5).isEqualTo(integer0).isTrue());
		assertTrue(emptyOrderedSet.count(oclReal0_5).isEqualTo(integer0)
				.isTrue());
		assertTrue(emptyBag.count(oclReal0_5).isEqualTo(integer0).isTrue());
		assertTrue(emptySequence.count(oclReal0_5).isEqualTo(integer0).isTrue());

		assertTrue(oclSet.count(oclReal0_5).isEqualTo(integer1).isTrue());
		assertTrue(oclOrderedSet.count(oclReal0_5).isEqualTo(integer1).isTrue());
		assertTrue(oclBag.count(oclReal0_5).isEqualTo(integer1).isTrue());
		assertTrue(oclSequence.count(oclReal0_5).isEqualTo(integer1).isTrue());

		assertTrue(oclSet.count(oclReal1_5).isEqualTo(integer0).isTrue());
		assertTrue(oclOrderedSet.count(oclReal1_5).isEqualTo(integer0).isTrue());
		assertTrue(oclBag.count(oclReal1_5).isEqualTo(integer0).isTrue());
		assertTrue(oclSequence.count(oclReal1_5).isEqualTo(integer0).isTrue());

		assertTrue(oclSet2.count(oclReal0_5).isEqualTo(integer1).isTrue());
		assertTrue(oclOrderedSet2.count(oclReal0_5).isEqualTo(integer1)
				.isTrue());
		assertTrue(oclBag2.count(oclReal0_5).isEqualTo(integer1).isTrue());
		assertTrue(oclSequence2.count(oclReal0_5).isEqualTo(integer1).isTrue());

		/*
		 * see standard, page 195 (non-strict evaluation)
		 */
		assertTrue(oclSet2.count(undefined).isEqualTo(integer1).isTrue());
		assertTrue(oclOrderedSet2.count(undefined).isEqualTo(integer1).isTrue());
		assertTrue(oclBag2.count(undefined).isEqualTo(integer2).isTrue());
		assertTrue(oclSequence2.count(undefined).isEqualTo(integer2).isTrue());
	}

	@Test
	public void testExcludes() {

		assertTrue(emptySet.excludes(oclReal0_5).isTrue());
		assertTrue(emptyOrderedSet.excludes(oclReal0_5).isTrue());
		assertTrue(emptyBag.excludes(oclReal0_5).isTrue());
		assertTrue(emptySequence.excludes(oclReal0_5).isTrue());

		assertTrue(oclSet.excludes(oclReal1_5).isTrue());
		assertTrue(oclOrderedSet.excludes(oclReal1_5).isTrue());
		assertTrue(oclBag.excludes(oclReal1_5).isTrue());
		assertTrue(oclSequence.excludes(oclReal1_5).isTrue());

		assertFalse(oclSet.excludes(oclReal0_5).isTrue());
		assertFalse(oclOrderedSet.excludes(oclReal0_5).isTrue());
		assertFalse(oclBag.excludes(oclReal0_5).isTrue());
		assertFalse(oclSequence.excludes(oclReal0_5).isTrue());

		/*
		 * see standard, page 195 (non-strict evaluation)
		 */
		assertFalse(oclSet2.excludes(undefined).isTrue());
		assertFalse(oclOrderedSet2.excludes(undefined).isTrue());
		assertFalse(oclBag2.excludes(undefined).isTrue());
		assertFalse(oclSequence2.excludes(undefined).isTrue());
	}

	@Test
	public void testExcludesAll() {

		assertTrue(emptySet.excludesAll(emptySet).isTrue());
		assertTrue(emptyOrderedSet.excludesAll(emptySet).isTrue());
		assertTrue(emptyBag.excludesAll(emptySet).isTrue());
		assertTrue(emptySequence.excludesAll(emptySet).isTrue());

		assertFalse(oclSet2.excludesAll(oclSet).isTrue());
		assertFalse(oclSet2.excludesAll(oclOrderedSet).isTrue());
		assertFalse(oclSet2.excludesAll(oclBag).isTrue());
		assertFalse(oclSet2.excludesAll(oclSequence).isTrue());

		assertFalse(oclSet2.excludesAll(oclSet2).isTrue());
		assertFalse(oclOrderedSet2.excludesAll(oclOrderedSet2).isTrue());
		assertFalse(oclBag2.excludesAll(oclBag2).isTrue());
		assertFalse(oclSequence2.excludesAll(oclSequence2).isTrue());
	}

	@Test
	public void testIncludes() {

		assertFalse(emptySet.includes(oclReal0_5).isTrue());
		assertFalse(emptyOrderedSet.includes(oclReal0_5).isTrue());
		assertFalse(emptyBag.includes(oclReal0_5).isTrue());
		assertFalse(emptySequence.includes(oclReal0_5).isTrue());

		assertFalse(oclSet.includes(oclReal1_5).isTrue());
		assertFalse(oclOrderedSet.includes(oclReal1_5).isTrue());
		assertFalse(oclBag.includes(oclReal1_5).isTrue());
		assertFalse(oclSequence.includes(oclReal1_5).isTrue());

		assertTrue(oclSet2.includes(oclReal0_5).isTrue());
		assertTrue(oclOrderedSet2.includes(oclReal0_5).isTrue());
		assertTrue(oclBag2.includes(oclReal0_5).isTrue());
		assertTrue(oclSequence2.includes(oclReal0_5).isTrue());
	}

	@Test
	public void testIncludesAll() {

		assertTrue(emptySet.includesAll(emptySet).isTrue());
		assertTrue(emptyOrderedSet.includesAll(emptySet).isTrue());
		assertTrue(emptyBag.includesAll(emptySet).isTrue());
		assertTrue(emptySequence.includesAll(emptySet).isTrue());

		assertTrue(oclSet2.includesAll(oclSet).isTrue());
		assertTrue(oclSet2.includesAll(oclOrderedSet).isTrue());
		assertTrue(oclSet2.includesAll(oclBag).isTrue());
		assertTrue(oclSet2.includesAll(oclSequence).isTrue());

		assertTrue(oclSet2.includesAll(oclSet2).isTrue());
		assertTrue(oclOrderedSet2.includesAll(oclOrderedSet2).isTrue());
		assertTrue(oclBag2.includesAll(oclBag2).isTrue());
		assertTrue(oclSequence2.includesAll(oclSequence2).isTrue());

		assertFalse(oclSet.includesAll(oclSet2).isTrue());
		assertFalse(oclOrderedSet.includesAll(oclOrderedSet2).isTrue());
		assertFalse(oclBag.includesAll(oclBag2).isTrue());
		assertFalse(oclSequence.includesAll(oclSequence2).isTrue());
	}

	@Test
	public void testIsEmpty() {

		assertTrue(emptySet.isEmpty().isTrue());
		assertTrue(emptyOrderedSet.isEmpty().isTrue());
		assertTrue(emptyBag.isEmpty().isTrue());
		assertTrue(emptySequence.isEmpty().isTrue());

		assertFalse(oclSet.isEmpty().isTrue());
		assertFalse(oclOrderedSet.isEmpty().isTrue());
		assertFalse(oclBag.isEmpty().isTrue());
		assertFalse(oclSequence.isEmpty().isTrue());
	}

	@Test
	public void testIsNotEmpty() {

		assertFalse(emptySet.notEmpty().isTrue());
		assertFalse(emptyOrderedSet.notEmpty().isTrue());
		assertFalse(emptyBag.notEmpty().isTrue());
		assertFalse(emptySequence.notEmpty().isTrue());

		assertTrue(oclSet.notEmpty().isTrue());
		assertTrue(oclOrderedSet.notEmpty().isTrue());
		assertTrue(oclBag.notEmpty().isTrue());
		assertTrue(oclSequence.notEmpty().isTrue());
	}

	@Test
	public void testProduct() {

		fail("Method is not yet implemented.");

		// /*
		// * Product on empty collections result in an empty set (OCL 2.2 Spec.
		// p.
		// * 149).
		// */
		// assertTrue(emptySet.product(emptySet).isEqualTo(emptySet).isTrue());
		// assertTrue(emptyOrderedSet.product(emptyOrderedSet).isEqualTo(emptySet)
		// .isTrue());
		// assertTrue(emptyBag.product(emptyBag).isEqualTo(emptySet).isTrue());
		// assertTrue(emptySequence.product(emptySequence).isEqualTo(emptySet)
		// .isTrue());
		//
		// /* Hack to create tuple type. */
		// RealLiteralExp realExp01 = ExpressionsFactory.INSTANCE
		// .createRealLiteralExp();
		// realExp01.setRealSymbol(new Float(0.5));
		// realExp01.setOclLibrary(EssentialOclPlugin.getOclLibraryProvider()
		// .getOclLibrary());
		//
		// Variable variable = ExpressionsFactory.INSTANCE.createVariable();
		// variable.setName("first");
		// variable.setInitExpression(realExp01);
		//
		// Type expectedType = EssentialOclPlugin.getOclLibraryProvider()
		// .getOclLibrary().makeTupleType(
		// Arrays.asList(new Property[] { variable.asProperty(),
		// variable.asProperty() }));
		//
		// /* Build Set(Tuple(first -> 0.5, second -> 0.5)) */
		// List<String> names = new ArrayList<String>();
		// names.add("first");
		// names.add("second");
		//
		// List<Object> values = new ArrayList<Object>();
		// values.add(0.5);
		// values.add(0.5);
		//
		// OclTuple expectedTuple =
		// myStandardLibraryFactory.createOclTupleObject(
		// names, values, expectedType);
		//
		// Set<OclTuple> elements = new HashSet<OclTuple>();
		// elements.add(expectedTuple);
		//
		// OclSet<OclTuple> expectedResult = myStandardLibraryFactory
		// .createOclSet(elements, expectedType);
		// assertTrue(oclSet.product(oclSet).isEqualTo(expectedResult).isTrue());
	}

	@Test
	public void testSize() {

		assertTrue(emptySet.size().isEqualTo(integer0).isTrue());
		assertTrue(emptyOrderedSet.size().isEqualTo(integer0).isTrue());
		assertTrue(emptyBag.size().isEqualTo(integer0).isTrue());
		assertTrue(emptySequence.size().isEqualTo(integer0).isTrue());

		assertTrue(oclSet.size().isEqualTo(integer1).isTrue());
		assertTrue(oclOrderedSet.size().isEqualTo(integer1).isTrue());
		assertTrue(oclBag.size().isEqualTo(integer1).isTrue());
		assertTrue(oclSequence.size().isEqualTo(integer1).isTrue());

		assertTrue(oclSet2.size().isEqualTo(integer3).isTrue());
		assertTrue(oclOrderedSet2.size().isEqualTo(integer3).isTrue());
		assertTrue(oclBag2.size().isEqualTo(integer4).isTrue());
		assertTrue(oclSequence2.size().isEqualTo(integer4).isTrue());
	}

	@Test
	public void testSum() {

		assertTrue(emptySet.sum().isEqualTo(integer0).isTrue());
		assertTrue(emptyOrderedSet.sum().isEqualTo(integer0).isTrue());
		assertTrue(emptyBag.sum().isEqualTo(integer0).isTrue());
		assertTrue(emptySequence.sum().isEqualTo(integer0).isTrue());

		assertTrue(oclSet2.sum().isEqualTo(integer2).isTrue());
		assertTrue(oclOrderedSet2.sum().isEqualTo(integer2).isTrue());
		assertTrue(oclBag2.sum().isEqualTo(integer2).isTrue());
		assertTrue(oclSequence2.sum().isEqualTo(integer2).isTrue());
	}

	@Test
	public void testFlatten() {

		/*
		 * Set { Set { 0.5, null }, Set { 0.5, 1.5 } }->flatten() == Set { 0.5,
		 * 1.5, null }
		 */
		Set<OclReal> realSet1 = new HashSet<OclReal>();
		realSet1.add(oclReal0_5);
		realSet1.add(undefined);

		Set<OclReal> realSet2 = new HashSet<OclReal>();
		realSet2.add(oclReal0_5);
		realSet2.add(oclReal1_5);

		Set<Set<OclReal>> setOfRealSets = new HashSet<Set<OclReal>>();
		setOfRealSets.add(realSet1);
		setOfRealSets.add(realSet2);
		OclSet<OclSet<OclAny>> oclSetofSets = myStandardLibraryFactory
				.createOclSet(
						setOfRealSets,
						EssentialOclPlugin
								.getOclLibraryProvider()
								.getOclLibrary()
								.getSetType(
										EssentialOclPlugin
												.getOclLibraryProvider()
												.getOclLibrary().getOclReal()));

		final OclSet<OclAny> flattenedSetOfSets = oclSetofSets.flatten();
		assertTrue(flattenedSetOfSets.isEqualTo(oclSet2).isTrue());
		assertTrue(flattenedSetOfSets.getGenericType().equals(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal()));

		/*
		 * Set { Set { Set { 0.5, null}, Set { 0.5, 1.5 } }, Set { Set { 2.5 } }
		 * }->flatten() == Set { 0.5, 1.5, 2.5, null }
		 */
		OclReal oclReal2_5 = myStandardLibraryFactory.createOclReal(2.5);

		realSet1.add(oclReal1_5);
		realSet1.add(oclReal2_5);
		OclSet<OclReal> oclResultSet = myStandardLibraryFactory.createOclSet(
				realSet1, EssentialOclPlugin.getOclLibraryProvider()
						.getOclLibrary().getOclReal());

		Set<OclReal> realSet3 = new HashSet<OclReal>();
		realSet3.add(oclReal2_5);

		Set<Set<OclReal>> setOfRealSets2 = new HashSet<Set<OclReal>>();
		setOfRealSets2.add(realSet3);

		Set<Set<Set<OclReal>>> setOfSetsOfRealSets = new HashSet<Set<Set<OclReal>>>();
		setOfSetsOfRealSets.add(setOfRealSets);
		setOfSetsOfRealSets.add(setOfRealSets2);
		OclSet<OclSet<OclSet<OclReal>>> oclSetOfSetsOfSets = myStandardLibraryFactory
				.createOclSet(
						setOfSetsOfRealSets,
						EssentialOclPlugin
								.getOclLibraryProvider()
								.getOclLibrary()
								.getSetType(
										EssentialOclPlugin
												.getOclLibraryProvider()
												.getOclLibrary()
												.getSetType(
														EssentialOclPlugin
																.getOclLibraryProvider()
																.getOclLibrary()
																.getOclReal())));

		OclSet<OclReal> flattenedSetOfSetOfSets = oclSetOfSetsOfSets.flatten();
		assertTrue(flattenedSetOfSetOfSets.isEqualTo(oclResultSet).isTrue());
		assertTrue(flattenedSetOfSets.getGenericType().equals(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal()));

		/*
		 * Bag { Bag { 0.5, null }, Bag { 0.5, 1.5 } }->flatten() == Bag { 0.5,
		 * 0.5, 1.5, null }
		 */
		List<OclReal> realBag1 = new ArrayList<OclReal>();
		realBag1.add(oclReal0_5);
		realBag1.add(undefined);

		List<OclReal> realBag2 = new ArrayList<OclReal>();
		realBag2.add(oclReal0_5);
		realBag2.add(oclReal1_5);

		List<OclReal> realBag3 = new ArrayList<OclReal>();
		realBag3.addAll(realBag1);
		realBag3.addAll(realBag2);
		OclBag<OclReal> oclResultBag = myStandardLibraryFactory.createOclBag(
				realBag3, EssentialOclPlugin.getOclLibraryProvider()
						.getOclLibrary().getOclReal());

		List<List<OclReal>> BagOfRealBags = new ArrayList<List<OclReal>>();
		BagOfRealBags.add(realBag1);
		BagOfRealBags.add(realBag2);
		OclBag<OclBag<OclAny>> oclBagofBags = myStandardLibraryFactory
				.createOclBag(
						BagOfRealBags,
						EssentialOclPlugin
								.getOclLibraryProvider()
								.getOclLibrary()
								.getBagType(
										EssentialOclPlugin
												.getOclLibraryProvider()
												.getOclLibrary().getOclReal()));
		assertTrue(oclBagofBags.flatten().isEqualTo(oclResultBag).isTrue());

		/*
		 * Bag { Bag { Bag { 0.5, null}, Bag { 0.5, 1.5 } }, Bag { Bag { 2.5 } }
		 * }->flatten() == Bag { 0.5, null, 0.5, 1.5, 2.5 }
		 */
		List<OclReal> realBag5 = new ArrayList<OclReal>();
		realBag3.add(oclReal2_5);

		List<OclReal> realBag4 = new ArrayList<OclReal>();
		realBag4.addAll(realBag1);
		realBag4.addAll(realBag2);
		realBag4.addAll(realBag5);
		OclBag<OclReal> oclResultBag2 = myStandardLibraryFactory.createOclBag(
				realBag4, EssentialOclPlugin.getOclLibraryProvider()
						.getOclLibrary().getOclReal());

		List<List<OclReal>> BagOfRealBags2 = new ArrayList<List<OclReal>>();
		BagOfRealBags2.add(realBag5);

		List<List<List<OclReal>>> BagOfBagsOfRealBags = new ArrayList<List<List<OclReal>>>();
		BagOfBagsOfRealBags.add(BagOfRealBags);
		BagOfBagsOfRealBags.add(BagOfRealBags2);
		OclBag<OclBag<OclBag<OclReal>>> oclBagOfBagsOfBags = myStandardLibraryFactory
				.createOclBag(
						BagOfBagsOfRealBags,
						EssentialOclPlugin
								.getOclLibraryProvider()
								.getOclLibrary()
								.getBagType(
										EssentialOclPlugin
												.getOclLibraryProvider()
												.getOclLibrary()
												.getBagType(
														EssentialOclPlugin
																.getOclLibraryProvider()
																.getOclLibrary()
																.getOclReal())));

		final OclBag<OclAny> flatBag = oclBagOfBagsOfBags.flatten();
		assertTrue(flatBag.isEqualTo(oclResultBag2).isTrue());
		/*
		 * The generic/element type of the Bag should be Real.
		 */
		Type genericType = flatBag.getGenericType();
		assertTrue(genericType.equals(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclReal()));

		/*
		 * OrderedSet { OrderedSet { 0.5, 1.5 }, OrderedSet { 0.5, null }
		 * }->flatten() == OrderedSet { 0.5, 1.5, null }
		 */
		List<OclReal> realOrderedSet1 = new UniqueEList<OclReal>();
		realOrderedSet1.add(oclReal0_5);
		realOrderedSet1.add(oclReal1_5);

		List<OclReal> realOrderedSet2 = new UniqueEList<OclReal>();
		realOrderedSet2.add(oclReal0_5);
		realOrderedSet2.add(undefined);

		List<List<OclReal>> OrderedSetOfRealOrderedSets = new UniqueEList<List<OclReal>>();
		OrderedSetOfRealOrderedSets.add(realOrderedSet1);
		OrderedSetOfRealOrderedSets.add(realOrderedSet2);
		OclOrderedSet<OclOrderedSet<OclAny>> oclOrderedSetofOrderedSets = myStandardLibraryFactory
				.createOclOrderedSet(
						OrderedSetOfRealOrderedSets,
						EssentialOclPlugin
								.getOclLibraryProvider()
								.getOclLibrary()
								.getOrderedSetType(
										EssentialOclPlugin
												.getOclLibraryProvider()
												.getOclLibrary().getOclReal()));

		final OclOrderedSet<OclAny> flattenedOrderedSetOfOrderedSets = oclOrderedSetofOrderedSets
				.flatten();
		assertTrue(flattenedOrderedSetOfOrderedSets.isEqualTo(oclOrderedSet2)
				.isTrue());
		assertTrue(flattenedOrderedSetOfOrderedSets.getGenericType().equals(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal()));

		/*
		 * OrderedSet { OrderedSet { OrderedSet { 0.5, null}, OrderedSet { 0.5,
		 * 1.5 } }, OrderedSet { OrderedSet { 2.5 } } }->flatten() == OrderedSet
		 * { 0.5, 1.5, 2.5, null }
		 */
		realOrderedSet1.add(oclReal1_5);
		realOrderedSet1.add(oclReal2_5);
		realOrderedSet1.add(undefined2);
		
		OclOrderedSet<OclReal> oclResultOrderedSet = myStandardLibraryFactory
				.createOclOrderedSet(realOrderedSet1, EssentialOclPlugin
						.getOclLibraryProvider().getOclLibrary().getOclReal());

		List<OclReal> realOrderedSet3 = new UniqueEList<OclReal>();
		realOrderedSet3.add(oclReal2_5);

		List<List<OclReal>> OrderedSetOfRealOrderedSets2 = new UniqueEList<List<OclReal>>();
		OrderedSetOfRealOrderedSets2.add(realOrderedSet3);

		List<List<List<OclReal>>> OrderedSetOfOrderedSetsOfRealOrderedSets = new UniqueEList<List<List<OclReal>>>();
		OrderedSetOfOrderedSetsOfRealOrderedSets
				.add(OrderedSetOfRealOrderedSets);
		OrderedSetOfOrderedSetsOfRealOrderedSets
				.add(OrderedSetOfRealOrderedSets2);
		OclOrderedSet<OclOrderedSet<OclOrderedSet<OclReal>>> oclOrderedSetOfOrderedSetsOfOrderedSets = myStandardLibraryFactory
				.createOclOrderedSet(
						OrderedSetOfOrderedSetsOfRealOrderedSets,
						EssentialOclPlugin
								.getOclLibraryProvider()
								.getOclLibrary()
								.getOrderedSetType(
										EssentialOclPlugin
												.getOclLibraryProvider()
												.getOclLibrary()
												.getOrderedSetType(
														EssentialOclPlugin
																.getOclLibraryProvider()
																.getOclLibrary()
																.getOclReal())));

		OclOrderedSet<OclReal> flattenedOrderedSetOfOrderedSetOfOrderedSets = oclOrderedSetOfOrderedSetsOfOrderedSets
				.flatten();
		assertTrue(flattenedOrderedSetOfOrderedSetOfOrderedSets.isEqualTo(
				oclResultOrderedSet).isTrue());
		assertTrue(flattenedOrderedSetOfOrderedSets.getGenericType().equals(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal()));

		/*
		 * Sequence { Sequence { 0.5, null }, Sequence { 0.5, 1.5 } }->flatten()
		 * == Sequence { 0.5, 0.5, 1.5, null }
		 */
		List<OclReal> realSequence1 = new ArrayList<OclReal>();
		realSequence1.add(oclReal0_5);
		realSequence1.add(undefined);

		List<OclReal> realSequence2 = new ArrayList<OclReal>();
		realSequence2.add(oclReal0_5);
		realSequence2.add(oclReal1_5);

		List<OclReal> realSequence3 = new ArrayList<OclReal>();
		realSequence3.addAll(realSequence1);
		realSequence3.addAll(realSequence2);
		OclSequence<OclReal> oclResultSequence = myStandardLibraryFactory
				.createOclSequence(realSequence3, EssentialOclPlugin
						.getOclLibraryProvider().getOclLibrary().getOclReal());

		List<List<OclReal>> sequenceOfRealSequences = new ArrayList<List<OclReal>>();
		sequenceOfRealSequences.add(realSequence1);
		sequenceOfRealSequences.add(realSequence2);
		OclSequence<OclSequence<OclAny>> oclSequenceofSequences = myStandardLibraryFactory
				.createOclSequence(
						sequenceOfRealSequences,
						EssentialOclPlugin
								.getOclLibraryProvider()
								.getOclLibrary()
								.getSequenceType(
										EssentialOclPlugin
												.getOclLibraryProvider()
												.getOclLibrary().getOclReal()));
		assertTrue(oclSequenceofSequences.flatten()
				.isEqualTo(oclResultSequence).isTrue());

		/*
		 * Sequence { Sequence { Sequence { 0.5, null}, Sequence { 0.5, 1.5 } },
		 * Sequence { Sequence { 2.5 } } }->flatten() == Sequence { 0.5, null,
		 * 0.5, 1.5, 2.5 }
		 */
		List<OclReal> realSequence5 = new ArrayList<OclReal>();
		realSequence3.add(oclReal2_5);

		List<OclReal> realSequence4 = new ArrayList<OclReal>();
		realSequence4.addAll(realSequence1);
		realSequence4.addAll(realSequence2);
		realSequence4.addAll(realSequence5);
		OclSequence<OclReal> oclResultSequence2 = myStandardLibraryFactory
				.createOclSequence(realSequence4, EssentialOclPlugin
						.getOclLibraryProvider().getOclLibrary().getOclReal());

		List<List<OclReal>> sequenceOfRealSequences2 = new ArrayList<List<OclReal>>();
		sequenceOfRealSequences2.add(realSequence5);

		List<List<List<OclReal>>> SequenceOfSequencesOfRealSequences = new ArrayList<List<List<OclReal>>>();
		SequenceOfSequencesOfRealSequences.add(sequenceOfRealSequences);
		SequenceOfSequencesOfRealSequences.add(sequenceOfRealSequences2);
		OclSequence<OclSequence<OclSequence<OclReal>>> oclSequenceOfSequencesOfSequences = myStandardLibraryFactory
				.createOclSequence(
						SequenceOfSequencesOfRealSequences,
						EssentialOclPlugin
								.getOclLibraryProvider()
								.getOclLibrary()
								.getSequenceType(
										EssentialOclPlugin
												.getOclLibraryProvider()
												.getOclLibrary()
												.getSequenceType(
														EssentialOclPlugin
																.getOclLibraryProvider()
																.getOclLibrary()
																.getOclReal())));

		final OclSequence<OclAny> flatSequence = oclSequenceOfSequencesOfSequences
				.flatten();
		assertTrue(flatSequence.isEqualTo(oclResultSequence2).isTrue());
		/*
		 * The generic/element type of the Sequence should be Real.
		 */
		genericType = flatSequence.getGenericType();
		assertTrue(genericType.equals(EssentialOclPlugin
				.getOclLibraryProvider().getOclLibrary().getOclReal()));
	}

	@Test
	public void testGetIterator() {

		assertFalse(emptySet.getIterator().hasNext().isTrue());
		assertFalse(emptyOrderedSet.getIterator().hasNext().isTrue());
		assertFalse(emptyBag.getIterator().hasNext().isTrue());
		assertFalse(emptySequence.getIterator().hasNext().isTrue());

		final OclIterator<OclReal> setIterator = oclSet2.getIterator();
		final OclIterator<OclReal> orderedSetIterator = oclOrderedSet2
				.getIterator();
		final OclIterator<OclReal> bagIterator = oclBag2.getIterator();
		final OclIterator<OclReal> sequenceIterator = oclSequence2
				.getIterator();

		assertTrue(setIterator.hasNext().isTrue());
		assertTrue(orderedSetIterator.hasNext().isTrue());
		assertTrue(bagIterator.hasNext().isTrue());
		assertTrue(sequenceIterator.hasNext().isTrue());

		/*
		 * Sets and Bags make no claims about element order.
		 */
		OclReal setIteratorNext = setIterator.next();
		OclReal bagIteratorNext = bagIterator.next();

		assertTrue(setIteratorNext.isEqualTo(oclReal0_5).isTrue()
				|| setIteratorNext.isEqualTo(oclReal1_5).isTrue()
				|| setIteratorNext.isEqualTo(undefined).isTrue());
		assertTrue(orderedSetIterator.next().isEqualTo(oclReal0_5).isTrue());
		assertTrue(bagIteratorNext.isEqualTo(oclReal0_5).isTrue()
				|| bagIteratorNext.isEqualTo(oclReal1_5).isTrue()
				|| bagIteratorNext.isEqualTo(undefined).isTrue());
		assertTrue(sequenceIterator.next().isEqualTo(oclReal0_5).isTrue());

		setIteratorNext = setIterator.next();
		bagIteratorNext = bagIterator.next();

		assertTrue(setIteratorNext.isEqualTo(oclReal0_5).isTrue()
				|| setIteratorNext.isEqualTo(oclReal1_5).isTrue()
				|| setIteratorNext.isEqualTo(undefined).isTrue());
		assertTrue(orderedSetIterator.next().isEqualTo(oclReal1_5).isTrue());
		assertTrue(bagIteratorNext.isEqualTo(oclReal1_5).isTrue()
				|| bagIteratorNext.isEqualTo(oclReal1_5).isTrue()
				|| bagIteratorNext.isEqualTo(undefined).isTrue());
		assertTrue(sequenceIterator.next().isEqualTo(oclReal1_5).isTrue());

		setIteratorNext = setIterator.next();
		bagIteratorNext = bagIterator.next();

		assertTrue(setIteratorNext.isEqualTo(oclReal0_5).isTrue()
				|| setIteratorNext.isEqualTo(oclReal1_5).isTrue()
				|| setIteratorNext.isEqualTo(undefined).isTrue());
		assertTrue(orderedSetIterator.next().isEqualTo(undefined).isTrue());
		assertTrue(bagIteratorNext.isEqualTo(undefined).isTrue()
				|| bagIteratorNext.isEqualTo(oclReal1_5).isTrue()
				|| bagIteratorNext.isEqualTo(undefined).isTrue());
		assertTrue(sequenceIterator.next().isEqualTo(undefined).isTrue());

		assertFalse(setIterator.hasNext().isTrue());
		assertFalse(orderedSetIterator.hasNext().isTrue());
		/*
		 * There is definitely another element in the Bag, but not certain which
		 * one. The Sequence's last element is definitely an undefined value.
		 */
		assertTrue(bagIterator.hasNext().isTrue());
		assertTrue(sequenceIterator.next().isEqualTo(undefined).isTrue());
	}

	@Test
	public void testMax() {

		assertTrue(emptySet.max().isEqualTo(integer0).isTrue());
		assertTrue(emptyOrderedSet.max().isEqualTo(integer0).isTrue());
		assertTrue(emptyBag.max().isEqualTo(integer0).isTrue());
		assertTrue(emptySequence.max().isEqualTo(integer0).isTrue());

		assertTrue(oclSet2.max().isEqualTo(oclReal1_5).isTrue());
		assertTrue(oclOrderedSet2.max().isEqualTo(oclReal1_5).isTrue());
		assertTrue(oclBag2.max().isEqualTo(oclReal1_5).isTrue());
		assertTrue(oclSequence2.max().isEqualTo(oclReal1_5).isTrue());
	}

	@Test
	public void testMin() {

		assertTrue(emptySet.min().isEqualTo(integer0).isTrue());
		assertTrue(emptyOrderedSet.min().isEqualTo(integer0).isTrue());
		assertTrue(emptyBag.min().isEqualTo(integer0).isTrue());
		assertTrue(emptySequence.min().isEqualTo(integer0).isTrue());

		assertTrue(oclSet2.min().isEqualTo(oclReal0_5).isTrue());
		assertTrue(oclOrderedSet2.min().isEqualTo(oclReal0_5).isTrue());
		assertTrue(oclBag2.min().isEqualTo(oclReal0_5).isTrue());
		assertTrue(oclSequence2.min().isEqualTo(oclReal0_5).isTrue());
	}
}
