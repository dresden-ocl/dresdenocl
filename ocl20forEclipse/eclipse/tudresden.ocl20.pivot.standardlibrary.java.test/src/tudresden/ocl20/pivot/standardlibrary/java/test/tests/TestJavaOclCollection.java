package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.UniqueEList;
import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;

public class TestJavaOclCollection {

	private IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private OclCollection<OclAny> emptySet =
			myStandardLibraryFactory.createOclSet(new HashSet<OclAny>());
	private OclCollection<OclAny> emptyBag =
			myStandardLibraryFactory.createOclBag(new ArrayList<OclAny>());
	private OclCollection<OclAny> emptyOrderedSet =
			myStandardLibraryFactory.createOclOrderedSet(new UniqueEList<OclAny>());
	private OclCollection<OclAny> emptySequence =
			myStandardLibraryFactory.createOclSequence(new ArrayList<OclAny>());

	private OclCollection<OclReal> oclSet;
	private OclCollection<OclReal> oclBag;
	private OclCollection<OclReal> oclOrderedSet;
	private OclCollection<OclReal> oclSequence;

	@Before
	public void init() {

		OclReal oclReal = myStandardLibraryFactory.createOclReal(0.5);
		
		Set<OclReal> realSet = new HashSet<OclReal>();
		realSet.add(oclReal);
		oclSet = myStandardLibraryFactory.createOclSet(realSet);
		
		List<OclReal> realBag = new ArrayList<OclReal>();
		realBag.add(oclReal);
		oclBag = myStandardLibraryFactory.createOclBag(realBag);
		
		UniqueEList<OclReal> realOrderedSet = new UniqueEList<OclReal>();
		realOrderedSet.add(oclReal);
		oclOrderedSet = myStandardLibraryFactory.createOclOrderedSet(realOrderedSet);
		
		oclSequence = myStandardLibraryFactory.createOclSequence(realBag);
	}

	@Test
	public void testAsSet() throws TestException {

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
	public void testAsBag() throws TestException {

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
	public void testAsOrderedSet() throws TestException {

		assertTrue(emptySet.asOrderedSet().isEqualTo(emptyOrderedSet).isTrue());
		assertTrue(emptyOrderedSet.asOrderedSet() != emptyOrderedSet);
		assertTrue(emptyOrderedSet.asOrderedSet().isEqualTo(emptyOrderedSet).isTrue());
		assertTrue(emptyOrderedSet.asOrderedSet().isEqualTo(emptyOrderedSet).isTrue());
		assertTrue(emptySequence.asOrderedSet().isEqualTo(emptyOrderedSet).isTrue());
		assertTrue(oclSet.asOrderedSet().isEqualTo(oclOrderedSet).isTrue());
		assertTrue(oclOrderedSet.asOrderedSet() != oclOrderedSet);
		assertTrue(oclOrderedSet.asOrderedSet().isEqualTo(oclOrderedSet).isTrue());
		assertTrue(oclOrderedSet.asOrderedSet().isEqualTo(oclOrderedSet).isTrue());
		assertTrue(oclSequence.asOrderedSet().isEqualTo(oclOrderedSet).isTrue());
	}
	
	@Test
	public void testAsSequence() throws TestException {

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

}
