package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import java.util.*;
import junit.framework.*;

public class TestType extends TestCase {

  Person p1;
  Person p2;
  Company c;

  OclType tInteger;
  OclType tReal;
  OclType tOclString, tString;
  OclType tBoolean;
  OclType tAny;

  OclType tPerson1;
  OclType tPerson2;
  OclType tPersonFQ;
  OclType tCompany;
  OclType tObject;

  public TestType(String s) {
    super(s);
  }

  protected void setUp() {
    p1=new Person();
    p2=new Person();
    c=new Company();

    tInteger=OclType.getOclTypeFor("Integer");
    tReal = OclType.getOclTypeFor("Real");
    tOclString = OclType.getOclTypeFor("OclString");
    tString = OclType.getOclTypeFor("String");
    tBoolean = OclType.getOclTypeFor("Boolean");
    tAny = OclType.getOclTypeFor("OclAny");

    tPerson1 = OclType.getOclTypeFor(p1, "Person");
    tPerson2 = OclType.getOclTypeFor(p2, "Person");
    tPersonFQ = OclType.getOclTypeFor("tudresden.ocl.lib.test.Person");
    tCompany = OclType.getOclTypeFor(p2, "Company"); // different class, same package
    tObject=((OclAny)Ocl.getFor(new Object())).oclType();
  }

  public void testEqual() {
    assert( tInteger.isEqualTo(tInteger).isTrue() );
    assert( tInteger.isNotEqualTo(tReal).isTrue() );
    assert( tString.isEqualTo(tOclString).isTrue() );
    assert( tPerson1.isNotEqualTo(tString).isTrue() );
    assert( tPerson1.isEqualTo(tPerson2).isTrue() );
    assert( tPersonFQ.isEqualTo(tPerson1).isTrue() );
    assert( tBoolean.isEqualTo(OclType.getOclTypeFor("OclBoolean")).isTrue() );
    assert( tCompany.isEqualTo(tPerson1).not().isTrue() );
  }

  public void testKind() {
    // tests isKindOf
    assert( ((OclAny)(Ocl.getOclRepresentationFor(6))).oclIsKindOf(tInteger).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(6))).oclIsKindOf(tReal).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(p1))).oclIsKindOf(tPersonFQ).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor("foo"))).oclIsKindOf(tOclString).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(true))).oclIsKindOf(tBoolean).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(6.0))).oclIsKindOf(tReal).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(6.0))).oclIsKindOf(tInteger).not().isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(p2))).oclIsKindOf(tCompany).not().isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(false))).oclIsKindOf(tAny).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(0.0))).oclIsKindOf(tAny).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(0))).oclIsKindOf(tAny).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(p1))).oclIsKindOf(tObject).isTrue() );
  }

  public void testType() {
    // tests isTypeOf
    /* contains the same tests cases as testKind; those with different nominal result
     * are marked appropriatly ("//differs")
     */
    assert( ((OclAny)(Ocl.getOclRepresentationFor(6))).oclIsTypeOf(tInteger).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(6))).oclIsTypeOf(tReal).not().isTrue() ); //differs
    assert( ((OclAny)(Ocl.getOclRepresentationFor(p1))).oclIsTypeOf(tPersonFQ).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor("foo"))).oclIsTypeOf(tOclString).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(true))).oclIsTypeOf(tBoolean).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(6.0))).oclIsTypeOf(tReal).isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(6.0))).oclIsTypeOf(tInteger).not().isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(p2))).oclIsTypeOf(tCompany).not().isTrue() );
    assert( ((OclAny)(Ocl.getOclRepresentationFor(false))).oclIsTypeOf(tAny).not().isTrue() ); //differs
    assert( ((OclAny)(Ocl.getOclRepresentationFor(0.0))).oclIsTypeOf(tAny).not().isTrue() ); // differs
    assert( ((OclAny)(Ocl.getOclRepresentationFor(0))).oclIsTypeOf(tAny).not().isTrue() ); // differs
    assert( ((OclAny)(Ocl.getOclRepresentationFor(p1))).oclIsTypeOf(tObject).not().isTrue() ); // differs
  }

  public void testSupertypes() {
    // tests supertypes, allSupertypes
    assert( tInteger.supertypes().includes(tReal).isTrue() );
    assert( tInteger.supertypes().includes(tAny).not().isTrue() );
    assert( tInteger.supertypes().includes(tPerson1).not().isTrue() );
    assert( tInteger.supertypes().includes(tString).not().isTrue() );
    assert( tPersonFQ.supertypes().includes(tPersonFQ).not().isTrue() );
    assert( tPersonFQ.supertypes().includes(tAny).not().isTrue() );
    assert( tPersonFQ.supertypes().includes(tReal).not().isTrue() );
    assert( tPerson1.supertypes().includes(tObject).isTrue() );
    // now the same for allSupertypes, differences denoted by "differs"
    assert( tInteger.allSupertypes().includes(tReal).isTrue() );
    assert( tInteger.allSupertypes().includes(tAny).isTrue() );
    assert( tInteger.allSupertypes().includes(tPerson1).not().isTrue() );
    assert( tInteger.allSupertypes().includes(tString).not().isTrue() );
    assert( tPersonFQ.allSupertypes().includes(tPersonFQ).not().isTrue() );
    assert( tPersonFQ.allSupertypes().includes(tAny).isTrue() );
    assert( tPersonFQ.allSupertypes().includes(tReal).not().isTrue() );
    assert( tPerson1.allSupertypes().includes(tObject).isTrue() );
  }

  public void testFields() {
    // tests name, attributes, associationEnds, operations
    assert( tInteger.name().isEqualTo(Ocl.getFor("Integer")).isTrue() );
    assert( tOclString.name().isEqualTo(Ocl.getFor("String")).isTrue() );
    assert( tPersonFQ.name().isEqualTo(Ocl.getFor("Person")).isTrue() );
    Ocl.JAVA_CLASS_NAMES=true;
    assert( tPerson1.name().isEqualTo(Ocl.getFor("tudresden.ocl.lib.test.Person")).isTrue() );
    Ocl.JAVA_CLASS_NAMES=false;
    assert( tPersonFQ.attributes().includes(Ocl.getFor("isUnemployed")).isTrue() );
    assert( tPerson1.attributes().includes(Ocl.getFor("employer")).isTrue() );
    assert( tPersonFQ.associationEnds().includes(Ocl.getFor("managedCompanies")).isTrue() );
    assert( tPersonFQ.associationEnds().includes(Ocl.getFor("isMarried")).isTrue() );
    assert( tPersonFQ.operations().includes(Ocl.getFor("managedCompanies")).not().isTrue() );
    assert( tPersonFQ.operations().includes(Ocl.getFor("firstName")).not().isTrue() );
    assert( tPersonFQ.operations().includes(Ocl.getFor("getFive")).isTrue() );
    assert( tInteger.operations().includes(Ocl.getFor("add")).isTrue() );
  }

  public static Test suite() {
    TestSuite suite=new TestSuite();
    suite.addTest( new TestType("testEqual") );
    suite.addTest( new TestType("testKind") );
    suite.addTest( new TestType("testType") );
    suite.addTest( new TestType("testSupertypes") );
    suite.addTest( new TestType("testFields") );
    return suite;
  }
}
