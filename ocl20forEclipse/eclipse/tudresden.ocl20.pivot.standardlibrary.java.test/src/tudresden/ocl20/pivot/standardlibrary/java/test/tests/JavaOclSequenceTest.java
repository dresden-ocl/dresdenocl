package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;

/**
 * Tests for sequences in OCL.
 * 
 * @author Michael Thiele
 * 
 */
public class JavaOclSequenceTest {

	private final IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private final OclSequence<OclReal> emptySequence =
			myStandardLibraryFactory.createOclSequence(new ArrayList<OclReal>(),
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getOclAny());

	/**
	 * Sequence { 0.5 }
	 */
	private OclSequence<OclReal> oclSequence;

	/**
	 * Sequence { 0.5, 1.5, null }
	 */
	private OclSequence<OclReal> oclSequence2;

	/**
	 * Sequence { 0.5, 0.5, 1.5, null }
	 */
	private OclSequence<OclReal> oclSequence3;

	private final OclReal oclReal0_5 =
			myStandardLibraryFactory.createOclReal(0.5);
	private final OclReal oclReal1_5 =
			myStandardLibraryFactory.createOclReal(1.5);

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
			(OclReal) myStandardLibraryFactory.createOclUndefined(oclReal0_5
					.getModelInstanceReal().getType(), "undefined value");

	@Before
	public void init() {

		List<OclReal> realSequence = new ArrayList<OclReal>();
		realSequence.add(oclReal0_5);
		oclSequence =
				myStandardLibraryFactory.createOclSequence(realSequence,
						EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

		realSequence.add(oclReal1_5);
		realSequence.add(undefined);
		oclSequence2 =
				myStandardLibraryFactory.createOclSequence(realSequence,
						EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());

		List<OclReal> realSequence2 = new ArrayList<OclReal>();
		realSequence2.add(oclReal0_5);
		realSequence2.add(oclReal0_5);
		realSequence2.add(oclReal1_5);
		realSequence2.add(undefined);

		oclSequence3 =
				myStandardLibraryFactory.createOclSequence(realSequence2,
						EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal());
	}

	@Test
	public void testAppend() {

		assertTrue(emptySequence.append(oclReal0_5).isEqualTo(oclSequence).isTrue());
		assertTrue(emptySequence.isEmpty().isTrue());

		assertTrue(oclSequence.append(oclReal1_5).append(undefined).isEqualTo(
				oclSequence2).isTrue());
		assertTrue(oclSequence.size().isEqualTo(integer1).isTrue());
	}

	@Test
	public void testExcluding() {

		assertTrue(oclSequence.excluding(oclReal0_5).isEqualTo(emptySequence)
				.isTrue());
		assertTrue(oclSequence.size().isEqualTo(integer1).isTrue());

		assertTrue(oclSequence.excluding(oclReal1_5).isEqualTo(oclSequence)
				.isTrue());

		assertTrue(oclSequence3.excluding(oclReal0_5).excluding(undefined)
				.excluding(oclReal1_5).isEqualTo(emptySequence).isTrue());
		assertTrue(oclSequence3.size().isEqualTo(integer4).isTrue());
	}

	@Test
	public void testIncluding() {

		assertTrue(emptySequence.including(oclReal0_5).isEqualTo(oclSequence)
				.isTrue());
		assertTrue(emptySequence.isEmpty().isTrue());

		assertTrue(oclSequence.including(oclReal1_5).including(undefined)
				.isEqualTo(oclSequence2).isTrue());
		assertTrue(oclSequence.size().isEqualTo(integer1).isTrue());
	}

	@Test
	public void testInsertAt() {

		assertTrue(emptySequence.insertAt(integer1, oclReal0_5).isEqualTo(
				oclSequence).isTrue());
		assertTrue(emptySequence.isEmpty().isTrue());
		assertTrue(emptySequence.insertAt(integer0, oclReal0_5).oclIsInvalid()
				.isTrue());
		assertTrue(emptySequence.isEmpty().isTrue());
		assertTrue(oclSequence.insertAt(integer2, undefined).insertAt(integer2,
				oclReal1_5).insertAt(integer2, oclReal0_5).isEqualTo(oclSequence3)
				.isTrue());
		assertTrue(oclSequence.size().isEqualTo(integer1).isTrue());
		assertTrue(oclSequence.insertAt(integer1, oclReal0_5).insertAt(integer4,
				oclReal1_5).oclIsInvalid().isTrue());
	}

	@Test
	public void testPrepend() {

		assertTrue(emptySequence.prepend(oclReal0_5).isEqualTo(oclSequence)
				.isTrue());
		assertTrue(emptySequence.isEmpty().isTrue());
		assertTrue(oclSequence2.prepend(oclReal0_5).isEqualTo(oclSequence3)
				.isTrue());
		assertTrue(oclSequence2.size().isEqualTo(integer3).isTrue());
	}

	@Test
	public void testSubSequence() {

		assertTrue(emptySequence.subSequence(integer1, integer1).oclIsInvalid()
				.isTrue());
		assertTrue(oclSequence.subSequence(integer1, integer1).isEqualTo(
				oclSequence).isTrue());
		assertTrue(oclSequence.subSequence(integer0, integer1).oclIsInvalid()
				.isTrue());
		assertTrue(oclSequence3.subSequence(integer2, integer4).isEqualTo(
				oclSequence2).isTrue());
		assertTrue(oclSequence3.subSequence(integer2, integer1).oclIsInvalid()
				.isTrue());
	}

	@Test
	public void testUnion() {

		assertTrue(oclSequence.union(oclSequence2).isEqualTo(oclSequence3).isTrue());
		assertTrue(oclSequence.size().isEqualTo(integer1).isTrue());
		assertTrue(oclSequence2.size().isEqualTo(integer3).isTrue());

		assertTrue(emptySequence.union(emptySequence).isEqualTo(emptySequence)
				.isTrue());
		assertTrue(emptySequence.union(oclSequence).isEqualTo(oclSequence).isTrue());
		assertTrue(emptySequence.isEmpty().isTrue());
		assertTrue(oclSequence.size().isEqualTo(integer1).isTrue());

		assertTrue(oclSequence.union(emptySequence).isEqualTo(oclSequence).isTrue());
		assertTrue(emptySequence.isEmpty().isTrue());
		assertTrue(oclSequence.size().isEqualTo(integer1).isTrue());
	}

}
