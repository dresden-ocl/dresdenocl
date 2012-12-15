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

public class JavaOclSetTest {

	private final IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private final OclSet<OclReal> emptySet =
			myStandardLibraryFactory.createOclSet(new HashSet<OclReal>(),
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getOclAny());

	/**
	 * Set { 0.5 }
	 */
	private OclSet<OclReal> oclSet;

	/**
	 * Set { 0.5, 1.5, null }
	 */
	private OclSet<OclReal> oclSet2;

	/**
	 * Set { 0.5, 1.5, 2.5, null }
	 */
	private OclSet<OclReal> oclSet3;

	/**
	 * Set { 2.5 }
	 */
	private OclSet<OclReal> oclSet4;

	private OclBag<OclReal> emptyBag =
			myStandardLibraryFactory.createOclBag(new ArrayList<OclReal>(),
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());;

	/**
	 * Bag { 0.5, 0.5 }
	 */
	private OclBag<OclReal> oclBag;

	private final OclReal oclReal0_5 =
			myStandardLibraryFactory.createOclReal(0.5);
	private final OclReal oclReal1_5 =
			myStandardLibraryFactory.createOclReal(1.5);
	private final OclReal oclReal2_5 =
			myStandardLibraryFactory.createOclReal(2.5);

	private final OclInteger integer1 =
			myStandardLibraryFactory.createOclInteger(1L);
	private final OclInteger integer4 =
			myStandardLibraryFactory.createOclInteger(4L);

	private final OclReal undefined =
			(OclReal) myStandardLibraryFactory.createOclUndefined(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal(),
					"undefined value");

	@Before
	public void init() {

		Set<OclReal> realSet = new HashSet<OclReal>();
		realSet.add(oclReal0_5);
		oclSet = myStandardLibraryFactory.createOclSet(realSet, EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

		realSet.add(oclReal1_5);
		realSet.add(undefined);
		oclSet2 =
				myStandardLibraryFactory.createOclSet(realSet, EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

		Set<OclReal> realSet2 = new HashSet<OclReal>();
		realSet2.add(oclReal0_5);
		realSet2.add(oclReal1_5);
		realSet2.add(oclReal2_5);
		realSet2.add(undefined);
		oclSet3 =
				myStandardLibraryFactory.createOclSet(realSet2, EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

		Set<OclReal> realSet3 = new HashSet<OclReal>();
		realSet3.add(oclReal2_5);
		oclSet4 =
				myStandardLibraryFactory.createOclSet(realSet3, EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

		List<OclReal> realBag = new ArrayList<OclReal>();
		realBag.add(oclReal0_5);
		realBag.add(oclReal0_5);
		oclBag = myStandardLibraryFactory.createOclBag(realBag, EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());
	}

	@Test
	public void testComplement() {

		assertTrue(emptySet.complement(emptySet).isEqualTo(emptySet).isTrue());
		assertTrue(emptySet.complement(oclSet).isEqualTo(emptySet).isTrue());
		assertTrue(oclSet.complement(emptySet).isEqualTo(oclSet).isTrue());
		assertTrue(oclSet.complement(oclSet).isEqualTo(emptySet).isTrue());
		assertTrue(oclSet.size().isEqualTo(integer1).isTrue());
		assertTrue(oclSet3.complement(oclSet2).isEqualTo(oclSet4).isTrue());
	}

	@Test
	public void testExcluding() {

		assertTrue(oclSet.excluding(oclReal0_5).isEqualTo(emptySet).isTrue());
		assertTrue(oclSet.size().isEqualTo(integer1).isTrue());

		assertTrue(oclSet.excluding(oclReal1_5).isEqualTo(oclSet).isTrue());

		assertTrue(oclSet3.excluding(oclReal0_5).excluding(undefined).excluding(
				oclReal1_5).isEqualTo(oclSet4).isTrue());
		assertTrue(oclSet3.size().isEqualTo(integer4).isTrue());
	}

	@Test
	public void testIncluding() {

		assertTrue(emptySet.including(oclReal0_5).isEqualTo(oclSet).isTrue());
		assertTrue(emptySet.isEmpty().isTrue());
		assertTrue(oclSet.including(oclReal0_5).including(oclReal1_5).including(
				oclReal2_5).including(undefined).isEqualTo(oclSet3).isTrue());
	}

	@Test
	public void testIntersection() {

		assertTrue(emptySet.intersection(emptyBag).isEqualTo(emptySet).isTrue());
		assertTrue(oclSet.intersection(oclBag).isEqualTo(oclSet).isTrue());
	}

	@Test
	public void testSymmetricDifferece() {

		assertTrue(emptySet.symmetricDifference(emptySet).isEqualTo(emptySet)
				.isTrue());
		assertTrue(oclSet.symmetricDifference(emptySet).isEqualTo(oclSet).isTrue());
		assertTrue(oclSet2.symmetricDifference(oclSet3).isEqualTo(oclSet4).isTrue());
	}

	@Test
	public void testUnion() {

		assertTrue(emptySet.union(emptySet).isEqualTo(emptySet).isTrue());
		assertTrue(oclSet.union(emptySet).isEqualTo(oclSet).isTrue());
		assertTrue(oclSet.union(oclSet2).union(oclSet3).isEqualTo(oclSet3).isTrue());
		assertTrue(oclSet.size().isEqualTo(integer1).isTrue());
	}
}
