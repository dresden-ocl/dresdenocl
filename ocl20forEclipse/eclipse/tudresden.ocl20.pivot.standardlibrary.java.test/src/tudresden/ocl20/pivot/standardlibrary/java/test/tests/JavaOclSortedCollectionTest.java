package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.UniqueEList;
import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.essentialocl.types.TypeConstants;

/**
 * Test for sorted collections in OCL.
 * 
 * @author Michael Thiele
 * 
 */
public class JavaOclSortedCollectionTest {

	private final IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private final OclSortedCollection<OclAny> emptyOrderedSet =
			myStandardLibraryFactory.createOclOrderedSet(new UniqueEList<OclAny>(),
					TypeConstants.ANY);
	private final OclSortedCollection<OclAny> emptySequence =
			myStandardLibraryFactory.createOclSequence(new ArrayList<OclAny>(),
					TypeConstants.ANY);

	private OclSortedCollection<OclReal> oclOrderedSet;
	private OclSortedCollection<OclReal> oclSequence;

	private OclSortedCollection<OclReal> oclOrderedSet2;
	private OclSortedCollection<OclReal> oclSequence2;

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

		UniqueEList<OclReal> realOrderedSet = new UniqueEList<OclReal>();
		realOrderedSet.add(oclReal0_5);
		oclOrderedSet =
				myStandardLibraryFactory.createOclOrderedSet(realOrderedSet,
						TypeConstants.REAL);

		List<OclReal> realSequence = new ArrayList<OclReal>();
		realSequence.add(oclReal0_5);
		oclSequence =
				myStandardLibraryFactory.createOclSequence(realSequence,
						TypeConstants.REAL);

		realOrderedSet.add(oclReal1_5);
		realOrderedSet.add(undefined);

		oclOrderedSet2 =
				myStandardLibraryFactory.createOclOrderedSet(realOrderedSet,
						TypeConstants.REAL);

		realSequence.add(oclReal1_5);
		realSequence.add(undefined);
		oclSequence2 =
				myStandardLibraryFactory.createOclSequence(realSequence,
						TypeConstants.REAL);
	}

	@Test
	public void testAt() {

		assertTrue(oclOrderedSet.at(integer1).isEqualTo(oclReal0_5).isTrue());
		assertTrue(oclSequence.at(integer1).isEqualTo(oclReal0_5).isTrue());

		assertTrue(oclOrderedSet2.at(integer1).isEqualTo(oclReal0_5).isTrue());
		assertTrue(oclOrderedSet2.at(integer2).isEqualTo(oclReal1_5).isTrue());
		assertTrue(oclOrderedSet2.at(integer3).isEqualTo(undefined).isTrue());
		assertTrue(oclSequence2.at(integer1).isEqualTo(oclReal0_5).isTrue());
		assertTrue(oclSequence2.at(integer2).isEqualTo(oclReal1_5).isTrue());
		assertTrue(oclSequence2.at(integer3).isEqualTo(undefined).isTrue());

		assertTrue(oclOrderedSet2.at(integer4).oclIsInvalid().isTrue());
		assertTrue(oclSequence2.at(integer4).oclIsInvalid().isTrue());

		assertTrue(emptyOrderedSet.at(integer1).oclIsInvalid().isTrue());
		assertTrue(emptySequence.at(integer1).oclIsInvalid().isTrue());
		assertTrue(oclOrderedSet.at(integer0).oclIsInvalid().isTrue());
		assertTrue(oclSequence.at(integer0).oclIsInvalid().isTrue());
	}

	@Test
	public void testFirst() {

		assertTrue(oclOrderedSet.first().isEqualTo(oclOrderedSet2.first()).isTrue());
		assertTrue(oclSequence.first().isEqualTo(oclSequence2.first()).isTrue());

		assertTrue(emptyOrderedSet.first().oclIsInvalid().isTrue());
		assertTrue(emptySequence.first().oclIsInvalid().isTrue());
	}

	@Test
	public void testLast() {

		assertTrue(oclOrderedSet.last().isEqualTo(oclReal0_5).isTrue());
		assertTrue(oclSequence.last().isEqualTo(oclReal0_5).isTrue());

		assertTrue(oclOrderedSet2.last().isEqualTo(undefined).isTrue());
		assertTrue(oclSequence2.last().isEqualTo(undefined).isTrue());

		assertTrue(emptyOrderedSet.last().oclIsInvalid().isTrue());
		assertTrue(emptySequence.last().oclIsInvalid().isTrue());
	}

	@Test
	public void testIndexOf() {

		assertTrue(oclOrderedSet.indexOf(oclReal0_5).isEqualTo(integer1).isTrue());
		assertTrue(oclSequence.indexOf(oclReal0_5).isEqualTo(integer1).isTrue());

		assertTrue(oclOrderedSet2.indexOf(oclReal1_5).isEqualTo(integer2).isTrue());
		assertTrue(oclSequence2.indexOf(oclReal1_5).isEqualTo(integer2).isTrue());

		// TODO Michael: see Wiki
		assertTrue(oclOrderedSet.indexOf(oclReal1_5).isEqualTo(integer0).isTrue());
		assertTrue(oclSequence.indexOf(oclReal1_5).isEqualTo(integer0).isTrue());
	}
}
