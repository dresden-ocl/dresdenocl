package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import junit.framework.*;

public class TestBoolean extends TestCase {

  private OclBoolean bTrue, bFalse, bUndef;
  
  public TestBoolean(String s) {
    super(s);
  }
  
  protected void setUp() {
    bTrue=OclBoolean.TRUE;
    bFalse=OclBoolean.FALSE;
    bUndef=new OclBoolean(0,"undefined OclBoolean");
  }
  
  public void testEqual() {
    assertTrue(! bTrue.isEqualTo(bFalse).isTrue() );
    assertTrue(! bFalse.isEqualTo(bTrue).isTrue() );
    assertTrue( bFalse.isEqualTo(OclBoolean.FALSE).isTrue() );
    assertTrue( bTrue.isEqualTo(OclBoolean.TRUE).isTrue() );
  }
  
  public void testOr() {
    // tests or, xor
    assertTrue( bTrue.or(bFalse).isTrue() );
    assertTrue( bFalse.or(bTrue).isTrue() );
    assertTrue( bTrue.or(bTrue).isTrue() );
    assertTrue(! bFalse.or(bFalse).isTrue() );
    
    assertTrue( bTrue.xor(bTrue).not().isTrue() );
    assertTrue( bFalse.xor(Ocl.getFor(true)).isTrue() );
    assertTrue( bTrue.xor(Ocl.getFor(false)).isTrue() );
    assertTrue( bFalse.xor(OclBoolean.FALSE).not().isTrue() );
    try {
      OclBoolean res=bUndef.xor(bTrue);
      assertTrue( res.isTrue() ); // must raise exception
      assertTrue(false);
    } catch (OclException e) {
      assertTrue(true);
    }
  }
  
  public void testAnd() {
    assertTrue( bTrue.and(OclBoolean.TRUE).isTrue() );
    assertTrue(! bTrue.and(OclBoolean.FALSE).isTrue() );
    assertTrue(! OclBoolean.FALSE.and(bFalse).isTrue() );
  }
  
  public void testNot() {
    assertTrue( bFalse.not().isTrue() );
    assertTrue( bTrue.not().not().isTrue() );
    assertTrue(! bTrue.not().isTrue() );
  }
  
  public void testImplies() {
    assertTrue( bFalse.implies(bFalse).isTrue() );
    assertTrue( bTrue.implies(bTrue).isTrue() );
    assertTrue(! bTrue.implies(bFalse).isTrue() );
    assertTrue( bFalse.implies(bTrue).isTrue() );
  }
  
  public void testUndefined() {
    assertTrue( bTrue.getFeature("bla").isUndefined() );
    // assertTrue( bUndef.isTrue() ); raises Exception
    
    // The following works only by accident.
    // Instead of the "==bUndef" there should be a ".isUndefined()".
    // The "==bUndef" may not work in future versions
    // of the ocl library.
    assertTrue( bUndef.isEqualTo(bUndef) == bUndef );
    assertTrue( bTrue.implies(bUndef) == bUndef );
    assertTrue( bFalse.implies(bUndef) == bTrue );
    assertTrue( bUndef.implies(bFalse) == bUndef );
    assertTrue( bUndef.not() == bUndef );
    assertTrue( bUndef.or(bTrue) == bTrue );
    assertTrue(! bFalse.and(bUndef).isTrue() );
  }
  
  public static Test suite() {
    TestSuite suite=new TestSuite();
    suite.addTest( new TestBoolean("testEqual") );
    suite.addTest( new TestBoolean("testOr") );
    suite.addTest( new TestBoolean("testAnd") );
    suite.addTest( new TestBoolean("testNot") );
    suite.addTest( new TestBoolean("testImplies") );
    suite.addTest( new TestBoolean("testUndefined") );
    return suite;
  }
}
