package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import junit.framework.*;

public class TestInteger extends TestCase {

  private OclInteger i0, i1, i2, im1, i10, i5;
  private OclInteger iUndef=new OclInteger(0,"undefined OclInteger");
  
  private OclReal r1k5=new OclReal(1.5d);
  
  public TestInteger(String s) {
    super(s);
  }
  
  protected void setUp() {
    i0=(OclInteger)Ocl.getOclRepresentationFor(0);
    i1=(OclInteger)Ocl.getOclRepresentationFor(1);
    i2=(OclInteger)Ocl.getOclRepresentationFor(2);
    im1=(OclInteger)Ocl.getOclRepresentationFor(-1);
    i10=(OclInteger)Ocl.getOclRepresentationFor(10);
    i5=(OclInteger)Ocl.getOclRepresentationFor(5);
  }
  
  public void testEqual() {
    assertTrue( i0.isEqualTo(i0).isTrue() );
    assertTrue( i0.isEqualTo(new OclInteger(0)).isTrue());
    assertTrue( i10.isNotEqualTo(i1).isTrue() );
    assertTrue( iUndef.isEqualTo(iUndef).isUndefined() );
    assertTrue( i5.isEqualTo(new OclReal(5.0d)).isTrue() );
  }
  
  public void testAdd() {
    assertTrue( (i0.add(i10)).isEqualTo(i10).isTrue() );
    assertTrue( i1.add(i1).isEqualTo(i2).isTrue() );
    assertTrue( im1.add(i1).isEqualTo(i0).isTrue() );
    assertTrue( i0.add(iUndef).isUndefined() );
    assertTrue( i0.subtract(i10).isEqualTo(new OclInteger(-10)).isTrue() );
    assertTrue( i0.subtract(im1).isEqualTo(i1).isTrue() );
    assertTrue( i0.add(r1k5).isEqualTo(r1k5).isTrue() );
    assertTrue( r1k5.add(i1).isEqualTo(new OclReal(2.5d)).isTrue() );
  }
  
  public void testMul() {
    assertTrue( i10.multiply( new OclReal(1.25d) ).isEqualTo( new OclReal(12.5d) ).isTrue() );
    assertTrue( im1.multiply(i10).multiply(im1).isEqualTo(i10).isTrue() );
    assertTrue( r1k5.multiply(i2).isEqualTo( new OclInteger(3) ).isTrue() );
  }
  
  public void testMisc() {
    assertTrue( im1.abs().isEqualTo(i1.abs()).isTrue() );
    assertTrue( i2.max(i1).getDouble() == 2d );
    assertTrue( i0.min(im1).getDouble() == -1d );
    assertTrue( ((Comparable)i1.add(r1k5)).compareTo(i1) == 1);
    assertTrue( i1.isGreaterThan(i0)==OclBoolean.TRUE );
    assertTrue( i1.isLessThan(i0)==OclBoolean.FALSE );
    assertTrue( i1.isGreaterEqual(i0)==OclBoolean.TRUE );
    assertTrue( i1.isGreaterEqual(i1)==OclBoolean.TRUE );
    assertTrue( im1.isGreaterEqual(i0)==OclBoolean.FALSE );
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
