package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import test.framework.*;

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
    assert(! bTrue.isEqualTo(bFalse).isTrue() );
    assert(! bFalse.isEqualTo(bTrue).isTrue() );
    assert( bFalse.isEqualTo(OclBoolean.FALSE).isTrue() );
    assert( bTrue.isEqualTo(OclBoolean.TRUE).isTrue() );
  }
  
  public void testOr() {
    // tests or, xor
    assert( bTrue.or(bFalse).isTrue() );
    assert( bFalse.or(bTrue).isTrue() );
    assert( bTrue.or(bTrue).isTrue() );
    assert(! bFalse.or(bFalse).isTrue() );
    
    assert( bTrue.xor(bTrue).not().isTrue() );
    assert( bFalse.xor(Ocl.getFor(true)).isTrue() );
    assert( bTrue.xor(Ocl.getFor(false)).isTrue() );
    assert( bFalse.xor(OclBoolean.FALSE).not().isTrue() );
    try {
      OclBoolean res=bUndef.xor(bTrue);
      assert( res.isTrue() ); // must raise exception
      assert(false);
    } catch (OclException e) {
      assert(true);
    }
  }
  
  public void testAnd() {
    assert( bTrue.and(OclBoolean.TRUE).isTrue() );
    assert(! bTrue.and(OclBoolean.FALSE).isTrue() );
    assert(! OclBoolean.FALSE.and(bFalse).isTrue() );
  }
  
  public void testNot() {
    assert( bFalse.not().isTrue() );
    assert( bTrue.not().not().isTrue() );
    assert(! bTrue.not().isTrue() );
  }
  
  public void testImplies() {
    assert( bFalse.implies(bFalse).isTrue() );
    assert( bTrue.implies(bTrue).isTrue() );
    assert(! bTrue.implies(bFalse).isTrue() );
    assert( bFalse.implies(bTrue).isTrue() );
  }
  
  public void testUndefined() {
    assert( bTrue.getFeature("bla").isUndefined() );
    // assert( bUndef.isTrue() ); raises Exception
    
    // The following works only by accident.
    // Instead of the "==bUndef" there should be a ".isUndefined()".
    // The "==bUndef" may not work in future versions
    // of the ocl library.
    assert( bUndef.isEqualTo(bUndef) == bUndef );
    assert( bTrue.implies(bUndef) == bUndef );
    assert( bFalse.implies(bUndef) == bTrue );
    assert( bUndef.implies(bFalse) == bUndef );
    assert( bUndef.not() == bUndef );
    assert( bUndef.or(bTrue) == bTrue );
    assert(! bFalse.and(bUndef).isTrue() );
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
