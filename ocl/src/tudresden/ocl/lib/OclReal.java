/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
// FILE: d:/java/classes/de/tudresden/ocl/OclReal.java

package tudresden.ocl.lib;
import java.util.*;

/** This class represents the OCL basic type Real. To implement the the abstract
 *  mathematical definition of the OCL specification, this class uses
 *  <code>double</code> precision values to hold real numbers.
 *
 * @author Frank Finger
 */
public class OclReal extends OclAny implements
  OclSubtractable, OclMultiplyable, OclAddable,
  OclComparable, java.lang.Comparable {

  // Attributes

  /** the OclReal that is the result of an undefined OCL expression */
  public static OclReal UNDEFINED=new OclReal();

  private double dValue;
  private boolean bUndefined;

  /** Usually it's preferable to use not this constructor but
   *  the factory methods of the class Ocl.
   *
   *  @see Ocl#getOclRepresentationFor(double d)
   */
  public OclReal(double d) {
    dValue=d;
    bUndefined=false;
  }

  /** Usually it's preferable to use not this constructor but
   *  the factory methods of the class Ocl.
   *
   *  @see Ocl#getOclRepresentationFor(float f)
   */
  public OclReal(float f) {
    this( (double)f );
  }

  /** protected constructor for undefined OclReal value */
  protected OclReal() {
    bUndefined=true;
  }

  /** Two OclReals are equal if their <CODE>long</CODE> values are
   *  equal and non of them is undefined.
   */
  public OclBoolean isEqualTo(Object o) {
    OclReal or=toOclReal(o, "OclReal isEqualTo()");
    if ( isUndefined() || or.isUndefined() ) return OclBoolean.UNDEFINED;
    return Ocl.getOclRepresentationFor( getDouble()==or.getDouble() );
  }

  /** @return the negated result of isEqualTo
   *  @see #isEqualTo(Object o)
   */
  public OclBoolean isNotEqualTo(Object o) {
    return isEqualTo(o).not();
  }

  /** This method is only invoked in case of an error: OclReal's add
   *  must be called only with an OclReal parameter.
   *
   *  @see #add(OclReal or)
   */
  public OclAddable add(OclAddable a) {
    if (a instanceof OclReal) return add((OclReal)a);
    return toOclReal(a, "OclReal add()");
  }

  /** @return an instance of OclReal representing the sum of this value and
   *          the parameter
   */
  public OclReal add(OclReal or) {
    if (this.isUndefined() || or.isUndefined()) return UNDEFINED;
    return new OclReal( getDouble()+or.getDouble() );
  }

  /** This method is only invoked in case of an error: OclReal's subtract
   *  must be called only with an OclReal parameter.
   *
   *  @see #subtract(OclReal or)
   */
  public OclSubtractable subtract(OclSubtractable s) {
    if (s instanceof OclReal) return subtract((OclReal)s);
    return toOclReal(s, "OclReal subtract()");
  }

  /** @return an instance of OclReal representing the value of this
   *          OclReal multiplied with -1
   */
  public OclReal negative() {
    return new OclReal(-getDouble());
  }

  /** @return an instance of OclReal representing the result of the subtraction
   *          of the parameter from this value
   */
  public OclReal subtract(OclReal or) {
    if (this.isUndefined() || or.isUndefined()) return UNDEFINED;
    return new OclReal( getDouble()-or.getDouble() );
  }

  /** This method is only invoked in case of an error: OclReal's multiply
   *  must be called only with an OclReal parameter.
   *
   *  @see #multiply(OclReal or)
   */
  public OclMultiplyable multiply(OclMultiplyable m) {
    if (m instanceof OclReal) return multiply((OclReal)m);
    return toOclReal(m, "OclReal multiply()");
  }

  /** @return an instance of OclReal representing this value multiplied with
   *          the parameter
   */
  public OclReal multiply(OclReal or) {
    if (this.isUndefined() || or.isUndefined()) return UNDEFINED;
    return new OclReal( getDouble()*or.getDouble() );
  }

  /** This method is only invoked in case of an error: OclReal's divide
   *  must be called only with an OclReal parameter.
   *
   *  @see #divide(OclReal or)
   */
  public OclMultiplyable divide(OclMultiplyable m) {
    if (m instanceof OclReal) return divide((OclReal)m);
    return toOclReal(m, "OclReal divide()");
  }

  /** @return an instance of OclReal representing this value divided by
   *          the parameter
   */
  public OclReal divide(OclReal or) {
    if (this.isUndefined() || or.isUndefined()) return UNDEFINED;
    return new OclReal( getDouble() / or.getDouble() );
  }

  /** @return the absolute value of this value
   */
  public OclReal abs() {
    if (this.isUndefined()) return UNDEFINED;
    return new OclReal( Math.abs(getDouble()) );
  }

  /** @return the largest OclInteger value that is not greater than the
   *          argument
   */
  public OclInteger floor() {
    if (this.isUndefined()) return OclInteger.UNDEFINED;
    return new OclInteger( (long) Math.floor( getDouble() ) );
  }

  /** @return the OclInteger value that is closest to this value
   */
  public OclInteger round() {
    if (this.isUndefined()) return OclInteger.UNDEFINED;
    return new OclInteger( Math.round(getDouble()) );
  }

  /** @return an instance of OclReal representing the maximum value of
   *          this value and the parameter
   */
  public OclReal max(OclReal r) {
    if (this.isUndefined() || r.isUndefined()) return UNDEFINED;
    return new OclReal( Math.max(getDouble(), r.getDouble()) );
  }

  /** @return an instance of OclReal representing the miniumum value of
   *          this value and the parameter
   */
  public OclReal min(OclReal r) {
    if (this.isUndefined() || r.isUndefined()) return UNDEFINED;
    return new OclReal( Math.min(getDouble(), r.getDouble()) );
  }

  /** @return -1, 0 or 1
   *  @see Comparable
   *  @throws OclException if one of the values is undefined
   */
  public int compareTo(Object o) {
    OclReal or=(OclReal)o;
    if (this.isUndefined() || or.isUndefined()) {
      throw new OclException("tried to compare undefined OclReal value");
    }
    double dThis=getDouble();
    double dOther=or.getDouble();
    if (dThis>dOther)
      return 1;
    else if (dThis<dOther)
      return -1;
    else
      return 0;
  }

  public OclBoolean isLessThan(OclComparable c) {
    OclReal or=toOclReal(c, "OclReal isLessThan()");
    if (this.isUndefined() || or.isUndefined()) return OclBoolean.UNDEFINED;
    return Ocl.getOclRepresentationFor(getDouble()<or.getDouble());
  }

  public OclBoolean isGreaterThan(OclComparable c) {
    OclReal or=toOclReal(c, "OclReal isLessThan()");
    if (this.isUndefined() || or.isUndefined()) return OclBoolean.UNDEFINED;
    return Ocl.getOclRepresentationFor(getDouble()>or.getDouble());
  }

  public OclBoolean isLessEqual(OclComparable c) {
    OclReal or=toOclReal(c, "OclReal isLessThan()");
    if (this.isUndefined() || or.isUndefined()) return OclBoolean.UNDEFINED;
    return Ocl.getOclRepresentationFor(getDouble()<=or.getDouble());
  }

  public OclBoolean isGreaterEqual(OclComparable c) {
    OclReal or=toOclReal(c, "OclReal isLessThan()");
    if (this.isUndefined() || or.isUndefined()) return OclBoolean.UNDEFINED;
    return Ocl.getOclRepresentationFor(getDouble()>=or.getDouble());
  }

  public double getDouble() {
    if (isUndefined()) {
      throw new OclException("tried to read value of undefined OclReal");
    }
    return dValue;
  }

  public boolean isUndefined() {
    return bUndefined;
  }

  /** This method either throws a runtime exception or returns an undefined
   *  value, depending on OCL.STRICT_CHECKING.
   *
   *  @see Ocl#STRICT_CHECKING
   */
  public OclRoot getFeature(String name) {
    if (Ocl.STRICT_CHECKING) {
      throw new OclException("feature "+name+" of OclBoolean requested");
    }
    return UNDEFINED;
  }

  private OclReal toOclReal(Object o, String methodname) {
    try {
      OclReal ret=(OclReal) o;
      return ret;
    } catch (Exception cce) {
      if (Ocl.STRICT_CHECKING) {
        throw new OclException(methodname+" called with non-OclReal parameter");
      } else {
        return UNDEFINED;
      }
    }
  }

  /** This property is no longer present in OCL 1.3. In spite of this, the
   *  library contains this method. The problems that lead to its cancellation
   *  do not occur in this Java implementation.
   */
  public OclType oclType() {
    if (isUndefined()) return OclType.UNDEFINED;
    return OclType.typeReal;
  }


} /* end class OclReal */

