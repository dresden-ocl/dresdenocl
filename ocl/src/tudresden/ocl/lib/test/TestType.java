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
    assertTrue( tInteger.isEqualTo(tInteger).isTrue() );
    assertTrue( tInteger.isNotEqualTo(tReal).isTrue() );
    assertTrue( tString.isEqualTo(tOclString).isTrue() );
    assertTrue( tPerson1.isNotEqualTo(tString).isTrue() );
    assertTrue( tPerson1.isEqualTo(tPerson2).isTrue() );
    assertTrue( tPersonFQ.isEqualTo(tPerson1).isTrue() );
    assertTrue( tBoolean.isEqualTo(OclType.getOclTypeFor("OclBoolean")).isTrue() );
    assertTrue( tCompany.isEqualTo(tPerson1).not().isTrue() );
  }

  public void testKind() {
    // tests isKindOf
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(6))).oclIsKindOf(tInteger).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(6))).oclIsKindOf(tReal).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(p1))).oclIsKindOf(tPersonFQ).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor("foo"))).oclIsKindOf(tOclString).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(true))).oclIsKindOf(tBoolean).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(6.0))).oclIsKindOf(tReal).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(6.0))).oclIsKindOf(tInteger).not().isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(p2))).oclIsKindOf(tCompany).not().isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(false))).oclIsKindOf(tAny).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(0.0))).oclIsKindOf(tAny).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(0))).oclIsKindOf(tAny).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(p1))).oclIsKindOf(tObject).isTrue() );
  }

  public void testType() {
    // tests isTypeOf
    /* contains the same tests cases as testKind; those with different nominal result
     * are marked appropriatly ("//differs")
     */
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(6))).oclIsTypeOf(tInteger).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(6))).oclIsTypeOf(tReal).not().isTrue() ); //differs
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(p1))).oclIsTypeOf(tPersonFQ).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor("foo"))).oclIsTypeOf(tOclString).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(true))).oclIsTypeOf(tBoolean).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(6.0))).oclIsTypeOf(tReal).isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(6.0))).oclIsTypeOf(tInteger).not().isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(p2))).oclIsTypeOf(tCompany).not().isTrue() );
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(false))).oclIsTypeOf(tAny).not().isTrue() ); //differs
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(0.0))).oclIsTypeOf(tAny).not().isTrue() ); // differs
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(0))).oclIsTypeOf(tAny).not().isTrue() ); // differs
    assertTrue( ((OclAny)(Ocl.getOclRepresentationFor(p1))).oclIsTypeOf(tObject).not().isTrue() ); // differs
  }

  public void testSupertypes() {
    // tests supertypes, allSupertypes
    assertTrue( tInteger.supertypes().includes(tReal).isTrue() );
    assertTrue( tInteger.supertypes().includes(tAny).not().isTrue() );
    assertTrue( tInteger.supertypes().includes(tPerson1).not().isTrue() );
    assertTrue( tInteger.supertypes().includes(tString).not().isTrue() );
    assertTrue( tPersonFQ.supertypes().includes(tPersonFQ).not().isTrue() );
    assertTrue( tPersonFQ.supertypes().includes(tAny).not().isTrue() );
    assertTrue( tPersonFQ.supertypes().includes(tReal).not().isTrue() );
    assertTrue( tPerson1.supertypes().includes(tObject).isTrue() );
    // now the same for allSupertypes, differences denoted by "differs"
    assertTrue( tInteger.allSupertypes().includes(tReal).isTrue() );
    assertTrue( tInteger.allSupertypes().includes(tAny).isTrue() );
    assertTrue( tInteger.allSupertypes().includes(tPerson1).not().isTrue() );
    assertTrue( tInteger.allSupertypes().includes(tString).not().isTrue() );
    assertTrue( tPersonFQ.allSupertypes().includes(tPersonFQ).not().isTrue() );
    assertTrue( tPersonFQ.allSupertypes().includes(tAny).isTrue() );
    assertTrue( tPersonFQ.allSupertypes().includes(tReal).not().isTrue() );
    assertTrue( tPerson1.allSupertypes().includes(tObject).isTrue() );
  }

  public void testFields() {
    // tests name, attributes, associationEnds, operations
    assertTrue( tInteger.name().isEqualTo(Ocl.getFor("Integer")).isTrue() );
    assertTrue( tOclString.name().isEqualTo(Ocl.getFor("String")).isTrue() );
    assertTrue( tPersonFQ.name().isEqualTo(Ocl.getFor("Person")).isTrue() );
    Ocl.JAVA_CLASS_NAMES=true;
    assertTrue( tPerson1.name().isEqualTo(Ocl.getFor("tudresden.ocl.lib.test.Person")).isTrue() );
    Ocl.JAVA_CLASS_NAMES=false;
    assertTrue( tPersonFQ.attributes().includes(Ocl.getFor("isUnemployed")).isTrue() );
    assertTrue( tPerson1.attributes().includes(Ocl.getFor("employer")).isTrue() );
    assertTrue( tPersonFQ.associationEnds().includes(Ocl.getFor("managedCompanies")).isTrue() );
    assertTrue( tPersonFQ.associationEnds().includes(Ocl.getFor("isMarried")).isTrue() );
    assertTrue( tPersonFQ.operations().includes(Ocl.getFor("managedCompanies")).not().isTrue() );
    assertTrue( tPersonFQ.operations().includes(Ocl.getFor("firstName")).not().isTrue() );
    assertTrue( tPersonFQ.operations().includes(Ocl.getFor("getFive")).isTrue() );
    assertTrue( tInteger.operations().includes(Ocl.getFor("add")).isTrue() );
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
