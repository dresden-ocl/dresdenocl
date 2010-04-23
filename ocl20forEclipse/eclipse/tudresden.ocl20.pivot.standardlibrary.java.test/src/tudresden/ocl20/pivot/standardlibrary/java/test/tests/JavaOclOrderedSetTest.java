package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.emf.common.util.UniqueEList;
import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;

public class JavaOclOrderedSetTest {

	private final IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private final OclOrderedSet<OclReal> emptyOrderedSet =
			myStandardLibraryFactory.createOclOrderedSet(new UniqueEList<OclReal>(),
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclAny());

	/**
	 * OrderedSet { 0.5 }
	 */
	private OclOrderedSet<OclReal> oclOrderedSet;

	/**
	 * OrderedSet { 0.5, 1.5, null }
	 */
	private OclOrderedSet<OclReal> oclOrderedSet2;

	/**
	 * OrderedSet { 0.5, 1.5, 2.5, null }
	 */
	private OclOrderedSet<OclReal> oclOrderedSet3;

	private final OclReal oclReal0_5 =
			myStandardLibraryFactory.createOclReal(0.5);
	private final OclReal oclReal1_5 =
			myStandardLibraryFactory.createOclReal(1.5);
	private final OclReal oclReal2_5 =
			myStandardLibraryFactory.createOclReal(2.5);

	private final OclInteger integer0 =
			myStandardLibraryFactory.createOclInteger(0L);
	private final OclInteger integer1 =
			myStandardLibraryFactory.createOclInteger(1L);
	private final OclInteger integer2 =
			myStandardLibraryFactory.createOclInteger(2L);
	private final OclInteger integer3 =
			myStandardLibraryFactory.createOclInteger(3L);
	private final OclInteger integer4 =
			myStandardLibraryFactory.createOclInteger(4L);

	private final OclReal undefined =
			(OclReal) myStandardLibraryFactory.createOclUndefined(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal(),
					"undefined value");

	@Before
	public void init() {

		List<OclReal> realOrderedSet = new UniqueEList<OclReal>();
		realOrderedSet.add(oclReal0_5);
		oclOrderedSet =
				myStandardLibraryFactory.createOclOrderedSet(realOrderedSet,
						EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

		realOrderedSet.add(oclReal1_5);
		realOrderedSet.add(undefined);
		oclOrderedSet2 =
				myStandardLibraryFactory.createOclOrderedSet(realOrderedSet,
						EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

		List<OclReal> realOrderedSet2 = new UniqueEList<OclReal>();
		realOrderedSet2.add(oclReal0_5);
		realOrderedSet2.add(oclReal1_5);
		realOrderedSet2.add(oclReal2_5);
		realOrderedSet2.add(undefined);

		oclOrderedSet3 =
				myStandardLibraryFactory.createOclOrderedSet(realOrderedSet2,
						EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());
	}

	@Test
	public void testAppend() {

		assertTrue(emptyOrderedSet.append(oclReal0_5).isEqualTo(oclOrderedSet)
				.isTrue());
		assertTrue(emptyOrderedSet.isEmpty().isTrue());

		assertTrue(oclOrderedSet.append(oclReal1_5).append(undefined).isEqualTo(
				oclOrderedSet2).isTrue());
		assertTrue(oclOrderedSet.size().isEqualTo(integer1).isTrue());
		assertTrue(oclOrderedSet.append(oclReal0_5).isEqualTo(oclOrderedSet)
				.isTrue());
	}

	@Test
	public void testIncluding() {

		assertTrue(emptyOrderedSet.including(oclReal0_5).isEqualTo(oclOrderedSet)
				.isTrue());
		assertTrue(emptyOrderedSet.isEmpty().isTrue());

		assertTrue(oclOrderedSet.including(oclReal1_5).including(undefined)
				.isEqualTo(oclOrderedSet2).isTrue());
		assertTrue(oclOrderedSet.size().isEqualTo(integer1).isTrue());
		assertTrue(oclOrderedSet.including(oclReal0_5).isEqualTo(oclOrderedSet)
				.isTrue());
	}

	@Test
	public void testExluding() {

		assertTrue(emptyOrderedSet.excluding(oclReal0_5).isEqualTo(emptyOrderedSet)
				.isTrue());
		assertTrue(oclOrderedSet.excluding(oclReal1_5).isEqualTo(oclOrderedSet)
				.isTrue());
		assertTrue(oclOrderedSet.excluding(oclReal0_5).isEqualTo(emptyOrderedSet)
				.isTrue());
		assertTrue(oclOrderedSet.size().isEqualTo(integer1).isTrue());
		assertTrue(oclOrderedSet3.excluding(oclReal1_5).excluding(oclReal2_5)
				.excluding(undefined).isEqualTo(oclOrderedSet).isTrue());
		assertTrue(oclOrderedSet3.size().isEqualTo(integer4).isTrue());
	}

	@Test
	public void testInsertAt() {

		assertTrue(emptyOrderedSet.insertAt(integer1, oclReal0_5).isEqualTo(
				oclOrderedSet).isTrue());
		assertTrue(emptyOrderedSet.isEmpty().isTrue());
		assertTrue(emptyOrderedSet.insertAt(integer0, oclReal0_5).oclIsInvalid()
				.isTrue());
		assertTrue(emptyOrderedSet.isEmpty().isTrue());
		assertTrue(oclOrderedSet.insertAt(integer2, undefined).insertAt(integer2,
				oclReal1_5).insertAt(integer2, oclReal2_5).insertAt(integer2,
				oclReal0_5).isEqualTo(oclOrderedSet3).isTrue());
		assertTrue(oclOrderedSet.size().isEqualTo(integer1).isTrue());
		assertTrue(oclOrderedSet.insertAt(integer1, oclReal0_5).insertAt(integer4,
				oclReal1_5).oclIsInvalid().isTrue());
	}

	@Test
	public void testPrepend() {

		assertTrue(emptyOrderedSet.prepend(oclReal0_5).isEqualTo(oclOrderedSet)
				.isTrue());
		assertTrue(emptyOrderedSet.isEmpty().isTrue());
		assertTrue(oclOrderedSet2.prepend(oclReal0_5).prepend(undefined).prepend(
				oclReal0_5).isEqualTo(oclOrderedSet2).isTrue());
		assertTrue(oclOrderedSet2.size().isEqualTo(integer3).isTrue());
	}

	@Test
	public void testSubOrderedSet() {

		assertTrue(emptyOrderedSet.subOrderedSet(integer1, integer1).oclIsInvalid()
				.isTrue());
		assertTrue(oclOrderedSet.subOrderedSet(integer1, integer1).isEqualTo(
				oclOrderedSet).isTrue());
		assertTrue(oclOrderedSet2.subOrderedSet(integer1, integer1).isEqualTo(
				oclOrderedSet).isTrue());
		assertTrue(oclOrderedSet2.subOrderedSet(integer1, integer3).isEqualTo(
				oclOrderedSet2).isTrue());
		assertTrue(oclOrderedSet2.subOrderedSet(integer2, integer1).oclIsInvalid()
				.isTrue());
		assertTrue(oclOrderedSet2.subOrderedSet(integer1, integer4).oclIsInvalid()
				.isTrue());
	}

}
