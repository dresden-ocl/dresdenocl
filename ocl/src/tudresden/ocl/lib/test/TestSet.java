package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import java.util.*;
import test.framework.*;

public class TestSet extends TestCase {

  private OclString sa, sb, sc;
  private Person p1, p2, p3;

  private OclSet abc, ab, ac, bc, a;    // sets containing OclStrings

  private OclSet sempty, s1, s12, s123; // sets containing persons

  public TestSet(String s) {
    super(s);
  }

  protected void setUp() {
    sa=(OclString)Ocl.getOclRepresentationFor("a");
    sb=(OclString)Ocl.getOclRepresentationFor("b");
    sc=(OclString)Ocl.getOclRepresentationFor("c");

    p1=new Person();
    p1.firstName="b";
    p1.someNumber=10;
    p2=new Person();
    p2.firstName="a";
    p2.someNumber=5;
    p3=new Person();
    p3.firstName="c";
    p3.someNumber=0;

    ArrayList list=new ArrayList();
    list.add("a");
    a = (OclSet)Ocl.getOclRepresentationFor(list);
    list.add("b");
    ab=(OclSet)Ocl.getOclRepresentationFor(list);
    list.add("c");
    abc = (OclSet)Ocl.getOclRepresentationFor(list);

    HashSet set=new HashSet();
    set.add("a");
    set.add("c");
    ac= (OclSet)Ocl.getOclRepresentationFor(set);

    set=new HashSet();
    set.add("b");
    set.add("c");
    bc=(OclSet)Ocl.getOclRepresentationFor(set);

    LinkedList tree=new LinkedList();
    sempty=(OclSet)Ocl.getOclRepresentationFor(tree);
    tree.add(p1);
    s1=(OclSet)Ocl.getOclRepresentationFor(tree);
    tree.add(p2);
    s12=(OclSet)Ocl.getOclRepresentationFor(tree);
    tree.add(p3);
    s123=(OclSet)Ocl.getOclRepresentationFor(tree);

  }

  public void testEqual() {
    List list=new ArrayList();
    list.add("a");
    list.add("c");
    OclRoot set=Ocl.getOclRepresentationFor(list);

    assert( set.isEqualTo(ac).isTrue() );
    assert( ac.isEqualTo(set).isTrue() );
    assert( abc.isNotEqualTo(ab).isTrue() );
    assert( abc.isNotEqualTo(s123).isTrue() );
  }

  public void testIncludes() {
    assert( abc.includes( Ocl.getOclRepresentationFor("a") ).isTrue() );
    assert( ac.includes( Ocl.getOclRepresentationFor("a") ).isTrue() );
    assert( ac.includes( Ocl.getOclRepresentationFor("c") ).isTrue() );
    assert( s123.includes( (OclAny)Ocl.getOclRepresentationFor(p2) ).isTrue() );

  }

  public void testFeature() {
    OclRoot bag=s123.getFeature("firstName");
    assert( bag.isEqualTo( abc ).not().isTrue() );
    assert( bag.isEqualTo( abc.asBag() ).isTrue() );
  }

  public void testExists() {
    // OCL: s123.exists(p|p=p2)
    final OclIterator iter;
    assert(
      s123.exists(
        iter=s123.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return iter.getValue().isEqualTo( Ocl.getOclRepresentationFor(p2) );
          }
        }
      ).isTrue()
    );

    // OCL: not s12.exists(p|p=p3)
    final OclIterator iter2;
    assert(
      s12.exists(
        iter2=s12.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return iter2.getValue().isEqualTo(p3);
          }
        }
      ).not().isTrue()
    );
  }

  public void testForAll() {
    // OCL: s123->forAll(p|p.firstName<>"d")
    final OclIterator iter1;
    assert(
      s123.forAll(
        iter1=s123.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return iter1.getValue().isNotEqualTo( Ocl.getOclRepresentationFor("d") );
          }
        }
      ).isTrue()
    );

    // OCL: s123->forAll(i2, i3| i1<>i2 -> i2.firstName<>i3.firstName)
    final OclIterator iter2;
    assert( s123.forAll(
        iter2=s123.getIterator(),
        new OclBooleanEvaluatable() {
          final OclIterator iter3=s123.getIterator();
          public OclBoolean evaluate() {
            return s123.forAll(
              iter3,
              new OclBooleanEvaluatable() {
                public OclBoolean evaluate() {
                  return iter2.getValue().isNotEqualTo(iter3.getValue()).implies(
                      iter2.getValue().getFeature("firstName").isNotEqualTo( iter3.getValue().getFeature("firstName")
                  ));
                }
              } // end inner class
            );
          }
        } // end inner class
      ).isTrue()
    );
  }

  public void testUnique() {
    // OCL: s123->isUnique(p|p.firstName)
    final OclIterator iter1;
    assert(
      s123.isUnique(
        iter1=s123.getIterator(),
        new OclRootEvaluatable() {
          public OclRoot evaluate() {
            return iter1.getValue().getFeature("firstName");
          }
        } // end inner class
      ).isTrue()
    );
  }

  public void testSortedBy() {

    Set set=new HashSet();
    set.add(new Integer(1));
    set.add(new Integer(2));
    set.add(new Integer(3));
    OclSet set123=Ocl.toOclSet(Ocl.getFor(set));

    // OCL: set123->sortedBy(i|i)->first = 1
    final OclIterator iter1=set123.getIterator();
    assert(
      set123.sortedBy(
        iter1,
        new OclComparableEvaluatable() {
          public java.lang.Comparable evaluate() {
            return (java.lang.Comparable) iter1.getValue();
          }
        }
      ).first().isEqualTo(Ocl.getFor(1)).isTrue()
    );

    // OCL: set123->excluding(1).sortedBy(i|i) = 2
    final OclIterator iter2=set123.excluding(Ocl.getFor(1)).getIterator();
    assert(
      set123.excluding(Ocl.getFor(1)).sortedBy(
        iter2,
        new OclComparableEvaluatable() {
          public java.lang.Comparable evaluate() {
            return (java.lang.Comparable) iter2.getValue();
          }
        }
      ).first().isEqualTo(Ocl.getFor(2)).isTrue()
    );

    // OCL: s123->sortedBy(someNumber)->at(1) = p3
    final OclIterator iter3=s123.getIterator();
    assert(
      s123.sortedBy(
        iter3,
        new OclComparableEvaluatable() {
          public java.lang.Comparable evaluate() {
            return (java.lang.Comparable) iter3.getValue().getFeature("someNumber");
          }
        }
      ).at((OclInteger)Ocl.getFor(1)).isEqualTo(Ocl.getFor(p3)).isTrue()
    );

    // OCL: s123->sortedBy(someNumber)->at(2) = p2
    final OclIterator iter4=s123.getIterator();
    assert(
      s123.sortedBy(
        iter4,
        new OclComparableEvaluatable() {
          public java.lang.Comparable evaluate() {
            return (java.lang.Comparable) iter4.getValue().getFeature("someNumber");
          }
        }
      ).at((OclInteger)Ocl.getFor(2)).isEqualTo(Ocl.getFor(p2)).isTrue()
    );

    // OCL: s123->sortedBy(someNumber)->at(3) = p1
    final OclIterator iter5=s123.getIterator();
    assert(
      s123.sortedBy(
        iter5,
        new OclComparableEvaluatable() {
          public java.lang.Comparable evaluate() {
            return (java.lang.Comparable) iter5.getValue().getFeature("someNumber");
          }
        }
      ).at((OclInteger)Ocl.getFor(3)).isEqualTo(Ocl.getFor(p1)).isTrue()
    );

  }

  public void testIterate() {
    // OCL: abc=(s123->iterate(p; set=Set{}| set->including(p.firstName)))
    final OclIterator iter1;
    final OclContainer acc;
    assert(
      abc.isEqualTo(
        s123.iterate(
          iter1=s123.getIterator(),
          acc=new OclContainer( OclSet.getEmptyOclSet() ),
          new OclRootEvaluatable() {
            public OclRoot evaluate() {
              return ((OclCollection)acc.getValue()).including(
                iter1.getValue().getFeature("firstName")
              );
            }
          } // end inner class
        )
      ).isTrue()
    );
  }

  public void testSelect() {
    //OCL: ( s123->select(p|p.firstName<>"c") ) = s12
    final OclIterator iter1;
    assert(
      s123.select(
        iter1=s123.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return iter1.getValue().getFeature("firstName").isNotEqualTo(
              Ocl.getOclRepresentationFor("c")
            );
          }
        } // end inner class
      ).isEqualTo(s12).isTrue()
    );
  }

  public void testReject() {
    // OCL: abc->reject(s|s.size<2)->isEmpty
    final OclIterator iter1;
    assert(
      abc.reject(
        iter1=abc.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return ((OclSizable)iter1.getValue()).size().isLessThan(
              (OclComparable)Ocl.getOclRepresentationFor(2)
            );
          }
        } // end inner class
      ).isEmpty().isTrue()
    );
  }

  public void testMisc() {
    //size, excludes, includes, includesAll, isEmpty, notEmpty
    assert( s123.size().isEqualTo( Ocl.getOclRepresentationFor(3) ).isTrue() );
    assert( s123.excludes(sa).isTrue() );
    assert( abc.includes(sa).isTrue() );
    assert( s123.includesAll(s12).isTrue() );
    assert( ab.includesAll(a).isTrue() );
    assert( s12.includesAll(sempty).isTrue() );
    assert(! s1.isEmpty().isTrue() );
    assert( sempty.isEmpty().isTrue() );
    assert(! sempty.notEmpty().isTrue() );
    assert( a.notEmpty().isTrue() );
  }


  public void testCount() {
    //count, sum
    HashSet s5785=new HashSet();
    s5785.add( new Integer(5) );
    s5785.add( new Integer(7) );
    s5785.add( new Integer(8) );
    s5785.add( new Integer(5) );
    OclSet os5785=(OclSet)Ocl.getOclRepresentationFor(s5785);
    OclInteger i1=(OclInteger)Ocl.getOclRepresentationFor(1);

    assert( os5785.count( Ocl.getOclRepresentationFor(5) ).
        isEqualTo( i1 ).isTrue() );
    assert( abc.count(sa).isEqualTo(i1).isTrue() );
    assert( os5785.sum().isEqualTo(Ocl.getOclRepresentationFor(20)).isTrue() );
  }

  public void testUnion() {
    // union, symmetricDifference, subtract, including, excluding, intersection
    assert( ab.union(ac).isEqualTo(abc).isTrue() );
    assert( s1.union(s12).isEqualTo(s12).isTrue() );
    assert( ab.symmetricDifference(bc).isEqualTo(ac).isTrue() );
    assert( abc.symmetricDifference(bc).isEqualTo(a).isTrue() );
    assert( s123.subtract(s123).isEqualTo(sempty).isTrue() );
    assert( abc.subtract(a).isEqualTo(bc).isTrue() );
    assert( ab.including(sc).isEqualTo(abc).isTrue() );
    assert( ab.including(sb).isEqualTo(ab).isTrue() );
    assert( ab.excluding(sb).isEqualTo(a).isTrue() );
    assert( abc.excluding(sc).isEqualTo(ab).isTrue() );
    assert( abc.intersection(bc).isEqualTo(bc).isTrue() );
    assert( ab.intersection(ac).isEqualTo(a).isTrue() );
    assert( s1.intersection(s12).isEqualTo(s1).isTrue() );
    assert( sempty.intersection(s123).isEmpty().isTrue() );
    assert( s123.union(abc).asBag().isEqualTo(
      s123.asBag().union(abc.asBag())
    ).isTrue() );
    assert( s123.union((OclCollection)s123.getFeature("firstName")).includesAll(abc).isTrue() );
    assert( s123.union((OclCollection)s123.getFeature("firstName")).
      isNotEqualTo(s123.union(abc)).isTrue() );
  }

  public static Test suite() {
    TestSuite suite=new TestSuite();
    suite.addTest( new TestSet("testEqual") );
    suite.addTest( new TestSet("testFeature") );
    suite.addTest( new TestSet("testExists") );
    suite.addTest( new TestSet("testForAll") );
    suite.addTest( new TestSet("testUnique") );
    suite.addTest( new TestSet("testSortedBy") );
    suite.addTest( new TestSet("testIterate") );
    suite.addTest( new TestSet("testSelect") );
    suite.addTest( new TestSet("testReject") );
    suite.addTest( new TestSet("testMisc") );
    suite.addTest( new TestSet("testCount") );
    suite.addTest( new TestSet("testUnion") );
    return suite;
  }
}
