package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import test.framework.*;

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
    assert( shallo.isEqualTo(shallo).isTrue() );
    assert(! sHALLO.isEqualTo(shallo).isTrue() );
    assert( sHALLO.isEqualTo(new OclString("HALLO")).isTrue() );
    assert( sUndef.isEqualTo(shallo).isUndefined() );
    assert( sHALLO.isEqualTo(sUndef).isUndefined() );
    assert( sHALLO.isNotEqualTo(shallo).isTrue() );
    assert( sHALLO.equals(sHALLO) );
    assert( shallo.equals( Ocl.getOclRepresentationFor("hallo") ) );
  }
  
  public void testSubstring() {
    assert( 
      sHaLlO.substring(
        (OclInteger)Ocl.getOclRepresentationFor(1),
        (OclInteger)Ocl.getOclRepresentationFor(3)
      ).isEqualTo(sHaL).isTrue()
    );
    assert(
      shalloHALLO.substring(
        (OclInteger)Ocl.getOclRepresentationFor(2),
        (OclInteger)Ocl.getOclRepresentationFor(5)
      ).isEqualTo(
        Ocl.getOclRepresentationFor("allo")
      ).isTrue()
    );
  }
  
  public void testConcat() {
    assert( shallo.concat(sHALLO).isEqualTo(shalloHALLO).isTrue());
  }
  
  public void testUpper() {
    assert( shallo.toUpper().isEqualTo(sHALLO).isTrue() );
    assert( sHaLlO.toUpper().isEqualTo(sHALLO).isTrue() );
  }
  
  public void testLower() {
    assert( shallo.toLower().isEqualTo(shallo).isTrue() );
    assert( sHaLlO.toLower().isEqualTo(shallo).isTrue() );
  }
  
  public void testSize() {
    assert( shallo.size().isEqualTo(Ocl.getOclRepresentationFor(5)).isTrue());
    assert( sHaL.size().isEqualTo(Ocl.getOclRepresentationFor(3)).isTrue());
  }
  
  public void testFeature() {
    assert( shallo.getFeature("bla").isUndefined() );
  }
  
  public void testNull() {
    assert( Ocl.getFor(null).isEqualTo(Ocl.getFor(null)).isTrue() );
    assert( Ocl.getFor(null).isNotEqualTo(Ocl.getFor("")).isTrue() );
    assert(! ((OclString)Ocl.getFor(null)).isUndefined() );
    assert( ((OclString)Ocl.getFor(null)).substring(
      (OclInteger)Ocl.getFor(1), (OclInteger)Ocl.getFor(2)).isUndefined() );
    assert( ((OclSizable)Ocl.getFor(null)).size().isEqualTo(Ocl.getFor(0)).isTrue() );
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
    suite.addTest( new TestString("testNull") );
    
    return suite;
  }

}
