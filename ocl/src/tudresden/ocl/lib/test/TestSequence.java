package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import java.util.*;
import junit.framework.*;

public class TestSequence extends TestCase {

  private OclString sa, sb, sc;

  private OclAny p1, p2, p3;

  private OclSequence sempty, s1, s12, s123, s1231;
  private OclSequence a, ab, abc, abca, aa, aabc;

  private OclSet set1, set12, set123;

  public TestSequence(String s) {
    super(s);
  }

  protected void setUp() {
    sa=(OclString)Ocl.getOclRepresentationFor("a");
    sb=(OclString)Ocl.getOclRepresentationFor("b");
    sc=(OclString)Ocl.getOclRepresentationFor("c");

    Person person1, person2, person3;
    person1=new Person();
    person1.firstName="a";
    person1.someNumber=1;
    person2=new Person();
    person2.firstName="b";
    person2.someNumber=2;
    person3=new Person();
    person3.firstName="c";
    person3.someNumber=3;

    p1=(OclAny)Ocl.getOclRepresentationFor(person1);
    p2=(OclAny)Ocl.getOclRepresentationFor(person2);
    p3=(OclAny)Ocl.getOclRepresentationFor(person3);

    List list=new ArrayList();
    sempty=new OclSequence(new ArrayList(list));
    list.add(p1);
    s1=new OclSequence(new ArrayList(list));
    list.add(p2);
    s12=new OclSequence(new ArrayList(list));
    list.add(p3);
    s123=new OclSequence(new ArrayList(list));
    list.add(p1);
    s1231=new OclSequence(new ArrayList(list));

    list=new LinkedList();
    list.add(sa);
    list.add(sb);
    ab=new OclSequence(new ArrayList(list));
    list.add(sc);
    abc=new OclSequence(new ArrayList(list));
    list.add(sa);
    abca=new OclSequence(new ArrayList(list));
    list=new LinkedList();
    list.add(sa);
    a=new OclSequence(new LinkedList(list));
    list.add(sa);
    aa=new OclSequence(new ArrayList(list));
    list.add(sb);
    list.add(sc);
    aabc=new OclSequence(new LinkedList(list));

    HashSet set=new HashSet();
    set.add(person1);
    set1=(OclSet)Ocl.getOclRepresentationFor(set);
    set.add(person2);
    set12=(OclSet)Ocl.getOclRepresentationFor(set);
    set.add(person3);
    set123=(OclSet)Ocl.getOclRepresentationFor(set);
  }

  public void testEqual() {
    assert( s123.isEqualTo(s123).isTrue() );
    assert( aabc.isNotEqualTo(abca).isTrue() );
    assert( a.isNotEqualTo(aa).isTrue() );
    assert( a.isNotEqualTo(s1).isTrue() );
    assert( ab.isNotEqualTo(abc).isTrue() );
    assert( abc.isNotEqualTo(ab).isTrue() );
    assert( abc.isEqualTo(ab.append(sc)).isTrue() );
  }

  public void testFeature() {
    //getFeature, collect
    assert( s123.getFeature("firstName").isEqualTo(abc).isTrue() );
    final OclIterator iter;
    assert(
      s123.collect(
        iter=s123.getIterator(),
        new OclRootEvaluatable() {
          public OclRoot evaluate() {
            return iter.getValue().getFeature("firstName");
          }
        } // end inner class
      ).isEqualTo(s123.getFeature("firstName")).isTrue()
    );

    assert( s1231.getFeature("firstName").isEqualTo(abca).isTrue() );
  }

  public void testExists() {
    // exists, forAll

    // OCL: s1231->exists(p|p.someNumber=1)
    final OclIterator iter1;
    assert(
      s1231.exists(
        iter1=s1231.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return iter1.getValue().getFeature("someNumber").isEqualTo(
              Ocl.getOclRepresentationFor(1)
            );
          }
        } // end inner class
      ).isTrue()
    );

    // OCL: abca->forAll(s|s.size=1)
    final OclIterator iter2;
    assert(
      abca.forAll(
        iter2=abca.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return ((OclSizable)iter2.getValue()).size().isEqualTo(
              Ocl.getOclRepresentationFor(1)
            );
          }
        } // end inner class
      ).isTrue()
    );
  }

  public void testUnique() {
    // isUnique, sortedBy

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

    // OCL not s1231->isUnique(p|p.someNumber)
    final OclIterator iter2;
    assert(
      s1231.isUnique(
        iter2=s1231.getIterator(),
        new OclRootEvaluatable() {
          public OclRoot evaluate() {
            return iter2.getValue().getFeature("someNumber");
          }
        }
      ).not().isTrue()
    );

    // OCL: s123->sortedBy(p|p.someNumber) = s123
    final OclIterator iter3;
    assert(
      s123.sortedBy(
        iter3=s123.getIterator(),
        new OclComparableEvaluatable() {
          public java.lang.Comparable evaluate() {
            return (java.lang.Comparable) iter3.getValue().getFeature("someNumber");
          }
        } // end inner class
      ).isEqualTo(s123).isTrue()
    );

    // s1231->sortedBy(p|p.someNumber) = ( s123.prepend(p1) )
    final OclIterator iter4;
    assert(
      s1231.sortedBy(
        iter4=s1231.getIterator(),
        new OclComparableEvaluatable() {
          public java.lang.Comparable evaluate() {
            return (java.lang.Comparable) iter4.getValue().getFeature("someNumber");
          }
        } // end inner class
      ).isEqualTo(
        s123.prepend(p1)
      ).isTrue()
    );
  }

  public void testIterate() {
    // select, reject, iterate

    // OCL: abca->select(s|s=a)=aa
    final OclIterator iter1;
    assert(
      abca.select(
        iter1=abca.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return iter1.getValue().isEqualTo(sa);
          }
        } // end inner class
      )
      .isEqualTo(aa).isTrue()
    );

    // OCL: s1231->reject(p|p.someNumber<2) = s123->excluding(p1)
    final OclIterator iter2;
    assert(
      s1231.reject(
        iter2=s1231.getIterator(),
        new OclBooleanEvaluatable() {
          public OclBoolean evaluate() {
            return ((OclComparable)iter2.getValue().getFeature("someNumber")).isLessThan(
              (OclComparable)Ocl.getOclRepresentationFor(2)
            );
          }
        } // end inner class
      ).isEqualTo(
        s123.excluding(p1)
      ).isTrue()
    );

    // OCL: abca->iterate(s; string=""| string.concat(s)) = "abca"
    final OclIterator iter3;
    final OclContainer acc;
    assert(
      abca.iterate(
        iter3= abca.getIterator(),
        acc  = new OclContainer(Ocl.getOclRepresentationFor("")),
        new OclRootEvaluatable() {
          public OclRoot evaluate() {
            return ((OclString)acc.getValue()).concat((OclString)iter3.getValue());
          }
        } // end inner class
      ).isEqualTo(
        Ocl.getOclRepresentationFor("abca")
      ).isTrue()
    );
  }

  public void testMisc() {
    // size, count, includes, excludes, includesAll, isEmpty, notEmpty, sum, union
    // including, excluding
    OclInteger i1=(OclInteger)Ocl.getOclRepresentationFor(1);
    OclInteger i2=(OclInteger)Ocl.getOclRepresentationFor(2);
    OclInteger i3=(OclInteger)Ocl.getOclRepresentationFor(3);
    OclInteger i4=(OclInteger)Ocl.getOclRepresentationFor(4);
    OclInteger i7=(OclInteger)Ocl.getOclRepresentationFor(7);

    assert( s123.size().isEqualTo(i3).isTrue() );
    assert( s1231.size().isEqualTo(i4).isTrue() );
    assert( abca.size().isEqualTo(i4).isTrue() );
    assert( s1231.count(p1).isEqualTo(i2).isTrue() );
    assert( abc.count(sa).isEqualTo(i1).isTrue() );
    assert( abc.includes(p1).not().isTrue() );
    assert( aabc.includes(sa).isTrue() );
    assert( s123.excludes(sa).isTrue() );
    assert( s12.excludes(p1).not().isTrue() );
    assert( abca.includesAll(abc).isTrue() );
    assert( s123.includesAll(set12).isTrue() );
    assert( aa.excluding(sa).isEmpty().isTrue() );
    assert( aabc.notEmpty().isTrue() );
    assert( ((OclCollection)s1231.getFeature("someNumber")).sum().isEqualTo(i7).isTrue() );
    assert( a.union(abc).isEqualTo(aabc).isTrue() );
    assert( s123.union(s1).isEqualTo(s1231).isTrue() );
    assert( s123.including(p1).isEqualTo(s1231).isTrue() );
    assert( a.including(sa).isEqualTo(aa).isTrue() );
    assert( s123.excluding(p2).excluding(p3).isEqualTo(s1).isTrue() );
    assert( aabc.excluding(sa).isEqualTo(abc.excluding(sa)).isTrue() );
  }

  public void testSpecial() {
    // test methods of OclSequence not inherited from OclCollection
    // append, prepend, subSequence, at, first, last

    OclInteger i1=(OclInteger)Ocl.getOclRepresentationFor(1);
    OclInteger i2=(OclInteger)Ocl.getOclRepresentationFor(2);
    OclInteger i3=(OclInteger)Ocl.getOclRepresentationFor(3);
    OclInteger i4=(OclInteger)Ocl.getOclRepresentationFor(4);
    OclInteger i5=(OclInteger)Ocl.getOclRepresentationFor(5);


    assert( abc.append(sa).isEqualTo(abca).isTrue() );
    assert( a.prepend(sc).prepend(sb).prepend(sa).isEqualTo(abca).isTrue() );
    assert( abca.union(abca).subSequence(i4, i5).isEqualTo(aa).isTrue() );
    assert( abc.subSequence(i1, i1).isEqualTo(a).isTrue() );
    assert( s1231.subSequence(i4, i4).isEqualTo(s1).isTrue() );
    assert( s123.at(i2).isEqualTo(p2).isTrue() );
    assert( abca.at(i1).isEqualTo( abca.at(i4) ).isTrue() );
    assert( sempty.first().isUndefined() );
    assert( s123.first().isEqualTo(p1).isTrue() );
    assert( s123.last().isEqualTo(p3).isTrue() );
  }

  public static Test suite() {
    TestSuite suite=new TestSuite();
    suite.addTest( new TestSequence("testEqual") );
    suite.addTest( new TestSequence("testFeature") );
    suite.addTest( new TestSequence("testExists") );
    suite.addTest( new TestSequence("testUnique") );
    suite.addTest( new TestSequence("testIterate") );
    suite.addTest( new TestSequence("testMisc") );
    suite.addTest( new TestSequence("testSpecial") );
    return suite;
  }
}
