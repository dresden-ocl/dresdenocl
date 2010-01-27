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

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;

// TODO Michael: nested collections
public class JavaOclCollectionTest {

	private final IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private final OclCollection<OclAny> emptySet =
			myStandardLibraryFactory.createOclSet(new HashSet<OclAny>());
	private final OclCollection<OclAny> emptyBag =
			myStandardLibraryFactory.createOclBag(new ArrayList<OclAny>());
	private final OclCollection<OclAny> emptyOrderedSet =
			myStandardLibraryFactory.createOclOrderedSet(new UniqueEList<OclAny>());
	private final OclCollection<OclAny> emptySequence =
			myStandardLibraryFactory.createOclSequence(new ArrayList<OclAny>());

	private OclCollection<OclReal> oclSet;
	private OclCollection<OclReal> oclBag;
	private OclCollection<OclReal> oclOrderedSet;
	private OclCollection<OclReal> oclSequence;

	private OclCollection<OclReal> oclSet2;
	private OclCollection<OclReal> oclBag2;
	private OclCollection<OclReal> oclOrderedSet2;
	private OclCollection<OclReal> oclSequence2;

	private final OclReal oclReal0_5 =
			myStandardLibraryFactory.createOclReal(0.5);
	private final OclReal oclReal1_5 =
			myStandardLibraryFactory.createOclReal(1.5);

	private final OclInteger integer0 =
			myStandardLibraryFactory.createOclInteger(0L);
	private final OclInteger integer1 =
			myStandardLibraryFactory.createOclInteger(1L);

//	 private final OclReal undefined =
//	 (OclReal) myStandardLibraryFactory.createOclUndefined(oclReal0_5
//	 .getModelInstanceReal().getTypes().iterator().next(),
//	 "undefined value");
//	
//	 private final OclReal invalid =
//	 (OclReal) myStandardLibraryFactory.createOclInvalid(oclReal0_5
//	 .getModelInstanceReal().getTypes().iterator().next(),
//	 new RuntimeException("invalid value"));

	@Before
	public void init() {

		Set<OclReal> realSet = new HashSet<OclReal>();
		realSet.add(oclReal0_5);
		oclSet = myStandardLibraryFactory.createOclSet(realSet);

		List<OclReal> realBag = new ArrayList<OclReal>();
		realBag.add(oclReal0_5);
		oclBag = myStandardLibraryFactory.createOclBag(realBag);

		UniqueEList<OclReal> realOrderedSet = new UniqueEList<OclReal>();
		realOrderedSet.add(oclReal0_5);
		oclOrderedSet =
				myStandardLibraryFactory.createOclOrderedSet(realOrderedSet);

		oclSequence = myStandardLibraryFactory.createOclSequence(realBag);

		realSet.add(oclReal1_5);
		// realSet.add(undefined);
		// realSet.add(invalid);
		oclSet2 = myStandardLibraryFactory.createOclSet(realSet);

		realBag.add(oclReal1_5);
		// realBag.add(undefined);
		// realBag.add(invalid);
		oclBag2 = myStandardLibraryFactory.createOclBag(realBag);

		realOrderedSet.add(oclReal1_5);
		// realOrderedSet.add(undefined);
		// realOrderedSet.add(invalid);
		oclOrderedSet2 =
				myStandardLibraryFactory.createOclOrderedSet(realOrderedSet);

		oclSequence2 = myStandardLibraryFactory.createOclSequence(realBag);

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
		assertTrue(emptySequence.asOrderedSet().isEqualTo(emptyOrderedSet).isTrue());
		assertTrue(oclSet.asOrderedSet().isEqualTo(oclOrderedSet).isTrue());
		assertTrue(oclOrderedSet.asOrderedSet() != oclOrderedSet);
		assertTrue(oclOrderedSet.asOrderedSet().isEqualTo(oclOrderedSet).isTrue());
		assertTrue(oclOrderedSet.asOrderedSet().isEqualTo(oclOrderedSet).isTrue());
		assertTrue(oclSequence.asOrderedSet().isEqualTo(oclOrderedSet).isTrue());
	}

	@Test
	public void testAsSequence() {

		assertTrue(emptySet.asSequence().isEqualTo(emptySequence).isTrue());
		assertTrue(emptySequence.asSequence() != emptySequence);
		assertTrue(emptySequence.asSequence().isEqualTo(emptySequence).isTrue());
		assertTrue(emptyOrderedSet.asSequence().isEqualTo(emptySequence).isTrue());
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
		assertTrue(emptyOrderedSet.count(oclReal0_5).isEqualTo(integer0).isTrue());
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
		assertTrue(oclOrderedSet2.count(oclReal0_5).isEqualTo(integer1).isTrue());
		assertTrue(oclBag2.count(oclReal0_5).isEqualTo(integer1).isTrue());
		assertTrue(oclSequence2.count(oclReal0_5).isEqualTo(integer1).isTrue());

		/*
		 * see standard, page 195 (non-strict evaluation), but WFR (p. 149) says
		 * that count iterates over elements and checks for equality which is
		 * non-strict
		 */
		// assertTrue(oclSet2.count(undefined).isEqualTo(integer1).isTrue());
		// assertTrue(oclOrderedSet2.count(undefined).isEqualTo(integer1).isTrue());
		// assertTrue(oclBag2.count(undefined).isEqualTo(integer1).isTrue());
		// assertTrue(oclSequence2.count(undefined).isEqualTo(integer1).isTrue());
		//
		// assertTrue(oclSet2.count(invalid).isEqualTo(integer1).isTrue());
		// assertTrue(oclOrderedSet2.count(invalid).isEqualTo(integer1).isTrue());
		// assertTrue(oclBag2.count(invalid).isEqualTo(integer1).isTrue());
		// assertTrue(oclSequence2.count(invalid).isEqualTo(integer1).isTrue());
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
		 * see standard, page 195 (non-strict evaluation), but WFR (p. 149) says
		 * that count iterates over elements and checks for equality which is
		 * non-strict; same is true for excludes()
		 */
		// assertFalse(oclSet2.excludes(undefined).isTrue());
		// assertFalse(oclOrderedSet2.excludes(undefined).isTrue());
		// assertFalse(oclBag2.excludes(undefined).isTrue());
		// assertFalse(oclSequence2.excludes(undefined).isTrue());
		//
		// assertFalse(oclSet2.excludes(invalid).isTrue());
		// assertFalse(oclOrderedSet2.excludes(invalid).isTrue());
		// assertFalse(oclBag2.excludes(invalid).isTrue());
		// assertFalse(oclSequence2.excludes(invalid).isTrue());
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
	}
	
	// @Test
	// public void testExcluding() throws TestException {
	//
	// assertTrue(emptySet.excluding(oclReal0_5).isEqualTo(emptySet).isTrue());
	// assertTrue(emptySet.excluding(oclReal0_5) != emptySet);
	// assertTrue(emptyOrderedSet.excluding(oclReal0_5).isEqualTo(emptyOrderedSet)
	// .isTrue());
	// assertTrue(emptyOrderedSet.excluding(oclReal0_5) != emptyOrderedSet);
	// assertTrue(emptyBag.excluding(oclReal0_5).isEqualTo(emptyBag).isTrue());
	// assertTrue(emptyBag.excluding(oclReal0_5) != emptyBag);
	// assertTrue(emptySequence.excluding(oclReal0_5).isEqualTo(emptySequence)
	// .isTrue());
	// assertTrue(emptySequence.excluding(oclReal0_5) != emptySequence);
	// }

}
