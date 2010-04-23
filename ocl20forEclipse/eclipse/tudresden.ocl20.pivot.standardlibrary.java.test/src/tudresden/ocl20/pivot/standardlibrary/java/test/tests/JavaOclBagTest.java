package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;

/**
 * Test for Bags in OCL.
 * 
 * @author Michael Thiele
 * 
 */
public class JavaOclBagTest {

	private final IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private final OclBag<OclReal> emptyBag =
			myStandardLibraryFactory.createOclBag(new ArrayList<OclReal>(),
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getOclAny());

	/**
	 * Bag { 0.5 }
	 */
	private OclBag<OclReal> oclBag;

	/**
	 * Bag { 0.5, 1.5, null }
	 */
	private OclBag<OclReal> oclBag2;

	/**
	 * Bag { 0.5, 0.5, 1.5, null }
	 */
	private OclBag<OclReal> oclBag3;

	/**
	 * Set { 0.5 }
	 */
	private OclSet<OclReal> oclSet;

	/**
	 * Set { 0.5, 1.5, null }
	 */
	private OclSet<OclReal> oclSet2;

	private final OclReal oclReal0_5 =
			myStandardLibraryFactory.createOclReal(0.5);
	private final OclReal oclReal1_5 =
			myStandardLibraryFactory.createOclReal(1.5);

	private final OclInteger integer0 =
			myStandardLibraryFactory.createOclInteger(0L);
	private final OclInteger integer1 =
			myStandardLibraryFactory.createOclInteger(1L);
	private final OclInteger integer3 =
			myStandardLibraryFactory.createOclInteger(3L);
	private final OclInteger integer4 =
			myStandardLibraryFactory.createOclInteger(4L);

	private final OclReal undefined =
			(OclReal) myStandardLibraryFactory.createOclUndefined(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal(),
					"undefined value");

	@Before
	public void init() {

		List<OclReal> realBag = new ArrayList<OclReal>();
		realBag.add(oclReal0_5);
		oclBag = myStandardLibraryFactory.createOclBag(realBag, EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

		realBag.add(oclReal1_5);
		realBag.add(undefined);
		oclBag2 =
				myStandardLibraryFactory.createOclBag(realBag, EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

		List<OclReal> realBag2 = new ArrayList<OclReal>();
		realBag2.add(oclReal0_5);
		realBag2.add(oclReal0_5);
		realBag2.add(oclReal1_5);
		realBag2.add(undefined);

		oclBag3 =
				myStandardLibraryFactory.createOclBag(realBag2, EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

		Set<OclReal> realSet = new HashSet<OclReal>();
		realSet.add(oclReal0_5);
		oclSet = myStandardLibraryFactory.createOclSet(realSet, EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

		realSet.add(oclReal1_5);
		realSet.add(undefined);
		oclSet2 =
				myStandardLibraryFactory.createOclSet(realSet, EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

	}

	@Test
	public void testExcluding() {

		assertTrue(oclBag.excluding(oclReal0_5).isEqualTo(emptyBag).isTrue());
		assertTrue(oclBag.size().isEqualTo(integer1).isTrue());

		assertTrue(oclBag.excluding(oclReal1_5).isEqualTo(oclBag).isTrue());

		assertTrue(oclBag3.excluding(oclReal0_5).excluding(undefined).excluding(
				oclReal1_5).isEqualTo(emptyBag).isTrue());
		assertTrue(oclBag3.size().isEqualTo(integer4).isTrue());
	}

	@Test
	public void testIncluding() {

		assertTrue(emptyBag.including(oclReal0_5).isEqualTo(oclBag).isTrue());
		assertTrue(emptyBag.isEmpty().isTrue());

		assertTrue(oclBag.including(oclReal1_5).including(undefined).isEqualTo(
				oclBag2).isTrue());
		assertTrue(oclBag.including(undefined).including(oclReal1_5).isEqualTo(
				oclBag2).isTrue());
		assertTrue(oclBag.size().isEqualTo(integer1).isTrue());
	}

	@Test
	public void testIntersection() {

		assertTrue(emptyBag.intersection(emptyBag).isEqualTo(emptyBag).isTrue());
		assertTrue(emptyBag.intersection(oclBag).isEqualTo(emptyBag).isTrue());
		assertTrue(oclBag.intersection(emptyBag).isEqualTo(emptyBag).isTrue());
		assertTrue(emptyBag.size().isEqualTo(integer0).isTrue());
		assertTrue(oclBag.size().isEqualTo(integer1).isTrue());

		assertTrue(oclBag.intersection(oclBag2).isEqualTo(oclBag).isTrue());
		assertTrue(oclBag2.intersection(oclBag).isEqualTo(oclBag).isTrue());
		assertTrue(oclBag.size().isEqualTo(integer1).isTrue());
		assertTrue(oclBag2.size().isEqualTo(integer3).isTrue());
	}

	@Test
	public void testUnion() {

		assertTrue(emptyBag.union(oclSet).isEqualTo(oclBag).isTrue());
		assertTrue(emptyBag.union(oclSet2).isEqualTo(oclBag2).isTrue());
		assertTrue(emptyBag.isEmpty().isTrue());
		assertTrue(oclSet.size().isEqualTo(integer1).isTrue());
		assertTrue(oclSet2.size().isEqualTo(integer3).isTrue());
	}
}
