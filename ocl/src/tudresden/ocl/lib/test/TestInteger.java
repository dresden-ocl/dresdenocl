package tudresden.ocl.lib.test;

import tudresden.ocl.lib.*;
import test.framework.*;

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
    assert( i0.isEqualTo(i0).isTrue() );
    assert( i0.isEqualTo(new OclInteger(0)).isTrue());
    assert( i10.isNotEqualTo(i1).isTrue() );
    assert( iUndef.isEqualTo(iUndef).isUndefined() );
    assert( i5.isEqualTo(new OclReal(5.0d)).isTrue() );
  }
  
  public void testAdd() {
    assert( (i0.add(i10)).isEqualTo(i10).isTrue() );
    assert( i1.add(i1).isEqualTo(i2).isTrue() );
    assert( im1.add(i1).isEqualTo(i0).isTrue() );
    assert( i0.add(iUndef).isUndefined() );
    assert( i0.subtract(i10).isEqualTo(new OclInteger(-10)).isTrue() );
    assert( i0.subtract(im1).isEqualTo(i1).isTrue() );
    assert( i0.add(r1k5).isEqualTo(r1k5).isTrue() );
    assert( r1k5.add(i1).isEqualTo(new OclReal(2.5d)).isTrue() );
  }
  
  public void testMul() {
    assert( i10.multiply( new OclReal(1.25d) ).isEqualTo( new OclReal(12.5d) ).isTrue() );
    assert( im1.multiply(i10).multiply(im1).isEqualTo(i10).isTrue() );
    assert( r1k5.multiply(i2).isEqualTo( new OclInteger(3) ).isTrue() );
  }
  
  public void testMisc() {
    assert( im1.abs().isEqualTo(i1.abs()).isTrue() );
    assert( i2.max(i1).getDouble() == 2d );
    assert( i0.min(im1).getDouble() == -1d );
    assert( ((Comparable)i1.add(r1k5)).compareTo(i1) == 1);
    assert( i1.isGreaterThan(i0)==OclBoolean.TRUE );
    assert( i1.isLessThan(i0)==OclBoolean.FALSE );
    assert( i1.isGreaterEqual(i0)==OclBoolean.TRUE );
    assert( i1.isGreaterEqual(i1)==OclBoolean.TRUE );
    assert( im1.isGreaterEqual(i0)==OclBoolean.FALSE );
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
