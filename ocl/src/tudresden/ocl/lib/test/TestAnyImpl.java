package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import junit.framework.*;

public class TestAnyImpl extends TestCase {

  private OclAnyImpl aEmpl, aEmpl2, aUempl;
  
  public TestAnyImpl(String s) {
    super(s);
  }

  protected void setUp() {
    Person employed=new Person();
    Person unemployed=new Person();
    unemployed.isUnemployed=true;
    aEmpl=(OclAnyImpl)Ocl.getOclRepresentationFor(employed);
    aEmpl2=(OclAnyImpl)Ocl.getOclRepresentationFor(employed);
    aUempl=(OclAnyImpl)Ocl.getOclRepresentationFor(unemployed);
  }
  
  public void testEqual() {
    assertTrue( aEmpl.isEqualTo(aEmpl2).isTrue() );
    assertTrue( aEmpl.isEqualTo(aEmpl).isTrue() );
    assertTrue(! aEmpl.isEqualTo(aUempl).isTrue() );
  }
  
  public void testFeature() {
    assertTrue(! ((OclBoolean)aEmpl.getFeature("isUnemployed")).isTrue() );
    assertTrue( ((OclBoolean)aUempl.getFeature("isUnemployed")).isTrue() );
    assertTrue( aEmpl.getFeature("getFive", null).isEqualTo( Ocl.getOclRepresentationFor(5) ).isTrue() );
  }
  
  public static Test suite() {
    TestSuite suite=new TestSuite();
    suite.addTest( new TestAnyImpl("testEqual") );
    suite.addTest( new TestAnyImpl("testFeature") );
    
    return suite;
  }
}

