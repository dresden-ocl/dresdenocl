package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import junit.framework.*;

public class TestReal extends TestCase {

  private OclReal r0, r10, r12k5, r2k5, rm10;
  private OclReal rUndef=new OclReal(0,"undefined OclReal");
  
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
    assertTrue( r0.isEqualTo(r0)
      .isTrue() );
    assertTrue( r0.isEqualTo(new OclReal(0d)).isTrue());
    assertTrue( r10.isNotEqualTo(r12k5).isTrue() );
    assertTrue( rUndef.isEqualTo(rUndef).isUndefined() );
  }
  
  public void testAdd() {
    assertTrue( (r0.add(r10)).isEqualTo(r10).isTrue() );
    assertTrue( ((OclRoot)r10.add(r2k5)).isEqualTo(r12k5).isTrue() );
    assertTrue( ((OclRoot)rm10.add(r10)).isEqualTo(r0).isTrue() );
    assertTrue( r0.add(rUndef).isUndefined() );
    assertTrue( r0.subtract(r10).isEqualTo(rm10).isTrue() );
    assertTrue( r0.subtract(rm10).isEqualTo(r10).isTrue() );
  }
  
  public void testMul() {
    assertTrue( ((OclRoot)r10.multiply( new OclReal(1.25d) )).isEqualTo(r12k5).isTrue() );
    assertTrue( ((OclRoot)new OclReal(-1d).multiply(rm10)).isEqualTo(r10).isTrue() );
    assertTrue( ((OclRoot)r12k5.divide( new OclReal(1.25d) )).isEqualTo(r10).isTrue() );
    assertTrue( ((OclRoot)r0.divide(r10)).isEqualTo(r0).isTrue() );
  }
  
  public void testMisc() {
    assertTrue( rm10.abs().isEqualTo(r10.abs()).isTrue() );
    //assertTrue( new OclReal(10.06767d).floor()==OclReal.UNDEFINED );
    assertTrue( new OclReal(10.06767d).floor().isEqualTo( new OclInteger(10) )==OclBoolean.TRUE );
    assertTrue( r12k5.round().getDouble()==13d );
    assertTrue( r10.round().getDouble() == 10d );
    assertTrue( r10.max(rm10).getDouble() == 10d );
    assertTrue( r10.min(rm10).getDouble() == -10d );
    assertTrue( ((Comparable)r10.add(r2k5)).compareTo(r10) == 1);
    assertTrue( r10.isGreaterThan(r0)==OclBoolean.TRUE );
    assertTrue( r10.isLessThan(r0)==OclBoolean.FALSE );
    assertTrue( r10.isGreaterEqual(r0)==OclBoolean.TRUE );
    assertTrue( r10.isGreaterEqual(r10)==OclBoolean.TRUE );
    assertTrue( rm10.isGreaterEqual(r0)==OclBoolean.FALSE );
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
