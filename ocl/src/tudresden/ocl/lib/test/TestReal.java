package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import test.framework.*;

public class TestReal extends TestCase {

  private OclReal r0, r10, r12k5, r2k5, rm10;
  private OclReal rUndef=OclReal.UNDEFINED;
  
  public TestReal(String s) {
    super(s);
  }
  
  protected void setUp() {
    r0=(OclReal)Ocl.getOclRepresentationFor(0.0d);
    r10=(OclReal)Ocl.getOclRepresentationFor(10d);
    r12k5=(OclReal)Ocl.getOclRepresentationFor(12.5d);
    r2k5=(OclReal)Ocl.getOclRepresentationFor(2.5d);
    rm10=(OclReal)Ocl.getOclRepresentationFor(-10.0d);
  }
  
  public void testEqual() {
    assert( r0.isEqualTo(r0)
      .isTrue() );
    assert( r0.isEqualTo(new OclReal(0d)).isTrue());
    assert( r10.isNotEqualTo(r12k5).isTrue() );
    assert( rUndef.isEqualTo(rUndef)==OclBoolean.UNDEFINED );
  }
  
  public void testAdd() {
    assert( (r0.add(r10)).isEqualTo(r10).isTrue() );
    assert( ((OclRoot)r10.add(r2k5)).isEqualTo(r12k5).isTrue() );
    assert( ((OclRoot)rm10.add(r10)).isEqualTo(r0).isTrue() );
    assert( r0.add(rUndef)==OclReal.UNDEFINED );
    assert( r0.subtract(r10).isEqualTo(rm10).isTrue() );
    assert( r0.subtract(rm10).isEqualTo(r10).isTrue() );
  }
  
  public void testMul() {
    assert( ((OclRoot)r10.multiply( new OclReal(1.25d) )).isEqualTo(r12k5).isTrue() );
    assert( ((OclRoot)new OclReal(-1d).multiply(rm10)).isEqualTo(r10).isTrue() );
    assert( ((OclRoot)r12k5.divide( new OclReal(1.25d) )).isEqualTo(r10).isTrue() );
    assert( ((OclRoot)r0.divide(r10)).isEqualTo(r0).isTrue() );
  }
  
  public void testMisc() {
    assert( rm10.abs().isEqualTo(r10.abs()).isTrue() );
    //assert( new OclReal(10.06767d).floor()==OclReal.UNDEFINED );
    assert( new OclReal(10.06767d).floor().isEqualTo( new OclInteger(10) )==OclBoolean.TRUE );
    assert( r12k5.round().getDouble()==13d );
    assert( r10.round().getDouble() == 10d );
    assert( r10.max(rm10).getDouble() == 10d );
    assert( r10.min(rm10).getDouble() == -10d );
    assert( ((Comparable)r10.add(r2k5)).compareTo(r10) == 1);
    assert( r10.isGreaterThan(r0)==OclBoolean.TRUE );
    assert( r10.isLessThan(r0)==OclBoolean.FALSE );
    assert( r10.isGreaterEqual(r0)==OclBoolean.TRUE );
    assert( r10.isGreaterEqual(r10)==OclBoolean.TRUE );
    assert( rm10.isGreaterEqual(r0)==OclBoolean.FALSE );
  }
  
  public static Test suite() {
    TestSuite suite=new TestSuite();
    suite.addTest( new TestReal("testEqual") );
    suite.addTest( new TestReal("testAdd") );
    suite.addTest( new TestReal("testMul") );
    suite.addTest( new TestReal("testMisc") );
    return suite;
  }
  
}
