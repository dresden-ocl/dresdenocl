package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import junit.framework.*;

public class TestString extends TestCase {

  private OclString shallo, sHALLO, sHaLlO, sHaL, shalloHALLO;
  private OclString sUndef=new OclString(0,"undefined OclString");
  
  public TestString(String s) {
    super(s);
  }

  protected void setUp() {
    shallo=(OclString)Ocl.getOclRepresentationFor("hallo");
    sHALLO=(OclString)Ocl.getOclRepresentationFor("HALLO");
    sHaLlO=(OclString)Ocl.getOclRepresentationFor("HaLlO");
    sHaL  =(OclString)Ocl.getOclRepresentationFor("HaL");
    shalloHALLO=(OclString)Ocl.getOclRepresentationFor("halloHALLO");
  }
  
  public void testEqual() {
    assertTrue( shallo.isEqualTo(shallo).isTrue() );
    assertTrue(! sHALLO.isEqualTo(shallo).isTrue() );
    assertTrue( sHALLO.isEqualTo(new OclString("HALLO")).isTrue() );
    assertTrue( sUndef.isEqualTo(shallo).isUndefined() );
    assertTrue( sHALLO.isEqualTo(sUndef).isUndefined() );
    assertTrue( sHALLO.isNotEqualTo(shallo).isTrue() );
    assertTrue( sHALLO.equals(sHALLO) );
    assertTrue( shallo.equals( Ocl.getOclRepresentationFor("hallo") ) );
  }
  
  public void testSubstring() {
    assertTrue( 
      sHaLlO.substring(
        (OclInteger)Ocl.getOclRepresentationFor(1),
        (OclInteger)Ocl.getOclRepresentationFor(3)
      ).isEqualTo(sHaL).isTrue()
    );
    assertTrue(
      shalloHALLO.substring(
        (OclInteger)Ocl.getOclRepresentationFor(2),
        (OclInteger)Ocl.getOclRepresentationFor(5)
      ).isEqualTo(
        Ocl.getOclRepresentationFor("allo")
      ).isTrue()
    );
  }
  
  public void testConcat() {
    assertTrue( shallo.concat(sHALLO).isEqualTo(shalloHALLO).isTrue());
  }
  
  public void testUpper() {
    assertTrue( shallo.toUpper().isEqualTo(sHALLO).isTrue() );
    assertTrue( sHaLlO.toUpper().isEqualTo(sHALLO).isTrue() );
  }
  
  public void testLower() {
    assertTrue( shallo.toLower().isEqualTo(shallo).isTrue() );
    assertTrue( sHaLlO.toLower().isEqualTo(shallo).isTrue() );
  }
  
  public void testSize() {
    assertTrue( shallo.size().isEqualTo(Ocl.getOclRepresentationFor(5)).isTrue());
    assertTrue( sHaL.size().isEqualTo(Ocl.getOclRepresentationFor(3)).isTrue());
  }
  
  public void testFeature() {
    assertTrue( shallo.getFeature("bla").isUndefined() );
  }
  
  public static Test suite() {
    TestSuite suite=new TestSuite();
    suite.addTest( new TestString("testEqual") );
    suite.addTest( new TestString("testSize") );
    suite.addTest( new TestString("testConcat") );
    suite.addTest( new TestString("testUpper") );
    suite.addTest( new TestString("testLower") );
    suite.addTest( new TestString("testSubstring") );
    suite.addTest( new TestString("testFeature") );

    return suite;
  }

}
