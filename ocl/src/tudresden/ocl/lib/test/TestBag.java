package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import java.util.*;
import test.framework.*;

public class TestBag extends TestCase {

  private OclString sa, sb, sc;
  private Person p1, p2, p3, p4;

  private OclBag aabc, abc, ab, a, bc;

  private OclSet sempty, s1, s12, s123;

  public TestBag(String s) {
    super(s);
  }

  protected void setUp() {
    sa=(OclString)Ocl.getOclRepresentationFor("a");
    sb=(OclString)Ocl.getOclRepresentationFor("b");
    sc=(OclString)Ocl.getOclRepresentationFor("c");

    p1=new Person();
    p1.firstName="b"; p1.someNumber=1;
    p2=new Person();
    p2.firstName="a"; p2.someNumber=2;
    p3=new Person();
    p3.firstName="c"; p3.someNumber=3;
    p4=new Person();
    p4.firstName="a"; // p4 heisst wieder "a"

    ArrayList list=new ArrayList();
    list.add(sa);
    a = new OclBag(new ArrayList(list));
    list.add(sb);
    ab = new OclBag(new ArrayList(list));
    list.add(sc);
    abc = new OclBag(new ArrayList(list));
    list.add(sa);
    aabc = new OclBag(new ArrayList(list));

    list=new ArrayList();
    list.add(sb);
    list.add(sc);
    bc = new OclBag(list);

    LinkedList tree=new LinkedList();
    sempty=(OclSet)Ocl.getOclRepresentationFor(tree);
    tree.add(p1);
    s1=(OclSet)Ocl.getOclRepresentationFor(tree);
    tree.add(p2);
    s12=(OclSet)Ocl.getOclRepresentationFor(tree);
    tree.add(p3);
    s123=(OclSet)Ocl.getOclRepresentationFor(tree);

  }

  public void testEquals() {
    assert( abc.isEqualTo(abc).isTrue() );
    assert(! aabc.isEqualTo(abc).isTrue() );
  }

  public void testCollect() {
    // OCL: sempty->collect(i|i.feature)->isEmpty
    final OclIterator iter1;
    assert(
      sempty.collect(
        iter1=sempty.getIterator(),
        new OclRootEvaluatable() {
          public OclRoot evaluate() {
            return iter1.getValue().getFeature("feature");
          }
        } // end inner class
      ).isEmpty().isTrue()
    );

    // OCL: (s123->collect(p|p.firstName)) = abc
    final OclIterator iter2;
    assert(
      s123.collect(
        iter2=s123.getIterator(),
        new OclRootEvaluatable() {
          public OclRoot evaluate() {
            return iter2.getValue().getFeature("firstName");
          }
        } // end inner class
      ).isEqualTo(abc).isTrue()
    );
  }

  public void testFeature() {
    // OCL: s123.firstName = abc
    final OclIterator iter1;
    assert(
      s123.getFeature("firstName").isEqualTo(abc).isTrue()
    );
  }

  public void testExists() {
    // exists, forAll

    // OCL: abc->forAll(s|s.size > 0)
    final OclIterator iter1;
    assert(
      abc.forAll(
        iter1=abc.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return ((OclSizable)iter1.getValue()).size().isGreaterThan(
              (OclComparable)Ocl.getOclRepresentationFor(0)
            );
          }
        } // end inner class
      ).isTrue()
    );

    // OCL: aabc->exists(s|s="b")
    final OclIterator iter2;
    assert(
      aabc.exists(
        iter2=aabc.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return iter2.getValue().isEqualTo(
              Ocl.getOclRepresentationFor("b")
            );
          }
        } // end inner class
      ).isTrue()
    );
  }

  public void testUnique() {
    // OCL: s123.firstName->isUnique(s|s)
    final OclIterator iter1;
    OclBag bag;
    assert(
      ( bag=(OclBag)s123.getFeature("firstName") ).isUnique(
        iter1=bag.getIterator(),
        new OclRootEvaluatable() {
          public OclRoot evaluate() {
            return iter1.getValue();
          }
        } // end inner class
      ).isTrue()
    );

    // OCL: not (aabc->isUnique(s|s))
    final OclIterator iter2;
    assert(
      aabc.isUnique(
        iter2=aabc.getIterator(),
        new OclRootEvaluatable() {
          public OclRoot evaluate() {
            return iter2.getValue();
          }
        } // end inner class
      ).not().isTrue()
    );
  }

  public void testIterate() {
    // OCL: ( abc->iterate(p; set=Set{}| set->including(p.size)) ).size = 1
    final OclIterator iter1;
    final OclContainer acc1;
    assert(
      ((OclSizable)abc.iterate(
        iter1=abc.getIterator(),
        acc1=new OclContainer(OclSet.getEmptyOclSet()),
        new OclRootEvaluatable() {
          public OclRoot evaluate() {
              return ((OclCollection)acc1.getValue()).including( ((OclSizable)iter1.getValue()).size() );
          }
        } // end inner class
      )).size().isEqualTo(
        Ocl.getOclRepresentationFor(1)
      ).isTrue()
    );
  }

  public void testSelect() {
    // select, reject, size

    // OCL: aabc->select(s|s.size<5) = aabc
    final OclIterator iter1;
    assert(
      aabc.select(
        iter1=aabc.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return ((OclSizable)iter1.getValue()).size().isLessThan(
              (OclComparable)Ocl.getOclRepresentationFor(5)
            );
          }
        } // end inner class
      ).isEqualTo(aabc).isTrue()
    );

    //OCL: aabc->reject(p|aabc->count(p)>1) = bc
    final OclIterator iter2;
    assert(
      aabc.reject(
        iter2=aabc.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return aabc.count(iter2.getValue()).isGreaterThan(
              (OclComparable)Ocl.getOclRepresentationFor(1)
            );
          }
        }
      ).isEqualTo(bc).isTrue()
    );
  }

  public void testUnion() {
    // includes, excludes, includesAll, isEmpty, notEmpty, union
    //    including, excluding, sum
    final OclIterator iter;
    assert(aabc.includes(sa).isTrue());
    assert(aabc.includes(sb).isTrue());
    assert(bc.excludes(sa).isTrue());
    assert(abc.includesAll(ab).isTrue());
    assert(aabc.includesAll(abc).isTrue());
    assert(abc.includesAll(aabc).isTrue()); // !! aber die Ocl Spec will es so
    assert(OclBag.getEmptyOclBag().isEmpty().isTrue());
    assert(OclBag.getEmptyOclBag().including(
      Ocl.getOclRepresentationFor(p1)
    ).notEmpty().isTrue());
    assert(abc.union(abc).isNotEqualTo(abc).isTrue());
    assert(abc.union(a).isEqualTo(aabc).isTrue());
    assert(abc.union(s123).size().getInt()==6);
    assert(aabc.excluding(sa).isEqualTo(bc).isTrue());
    assert(
      ((OclInteger)aabc.collect(
        iter=aabc.getIterator(),
        new OclRootEvaluatable() {
          public OclRoot evaluate() {
            return ((OclSizable)iter.getValue()).size();
          }
        } // end inner class
      ).sum()).getInt()==4
    );
  }



  public static Test suite() {
    TestSuite suite=new TestSuite();
    suite.addTest(new TestBag("testEquals"));
    suite.addTest(new TestBag("testCollect"));
    suite.addTest(new TestBag("testExists"));
    suite.addTest(new TestBag("testUnique"));
    suite.addTest(new TestBag("testIterate"));
    suite.addTest(new TestBag("testSelect"));
    suite.addTest(new TestBag("testUnion"));

    return suite;
  }
}
