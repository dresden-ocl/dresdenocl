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

// FILE: d:/java/classes/de/tudresden/ocl/OclInteger.java



package tudresden.ocl20.core.lib;

import java.util.*;



/** This class represents the basic OCL type Integer. The Integer value is

 *  stored in a <code>long</code> attribute, thus restricting the possible

 *  values. Please note that OclInteger extends OclReal.

 *

 *  <p>All operations on OclIntegers return undefined values if one or more

 *  of the operands is undefined.

 *

 *  @author Frank Finger

 */

public class OclInteger extends OclReal {



  private long lValue;



  /** Usually it's preferable to use this constructor but

   *  the factory methods of the class Ocl.

   *

   *  @see Ocl#getOclRepresentationFor(int i)

   */

  public OclInteger(int i) {

    this( (long)i );

  }



  /** Usually it's preferable to use this constructor but

   *  the factory methods of the class Ocl.

   *

   *  @see Ocl#getOclRepresentationFor(long l)

   */

  public OclInteger(long l) {

    super(0);

    lValue=l;

  }



  /** constructor for undefined OclInteger value */

  public OclInteger(int dummy, String reason) {

    super(dummy, reason);

    lValue=Long.MIN_VALUE; // hopefully makes fail-fast

  }



  /** This method is only invoked in case of an error: OclInteger's add

   *  must be called with an OclInteger parameter.

   *

   *  @see #add(OclInteger or)

   *  @see OclReal#add(OclReal r)

   */

  public OclAddable add(OclAddable a) {

    OclInteger i=toOclInteger(a, "OclInteger add()");

    return add(i);

  }



  /** @return an instance of OclInteger represeting the sum of this value

   *          and the parameter

   */

  public OclInteger add(OclInteger i) {

    if(isUndefined())

      return this; // no need to create a new one

    if(i.isUndefined())

      return i; // no need to create a new one

    return new OclInteger( this.getLong() + i.getLong() );

  }



  /** @return an instance of OclReal represeting the sum of this value

   *          and the parameter

   */

  public OclReal add(OclReal i) {

    return super.add(i);

  }



  /** This method is only invoked in case of an error: OclInteger's subtract

   *  must be called with an OclInteger parameter.

   *

   *  @see #subtract(OclInteger or)

   *  @see OclReal#subtract(OclReal r)

   */

  public OclSubtractable subtract(OclSubtractable s) {

    return subtract(toOclInteger(s, "OclInteger subtract()"));

  }



  /** @return an instance of OclInteger representing the result of the subtraction

   *          of the parameter from this value

   */

  public OclInteger subtract(OclInteger oi) {

    if(isUndefined())

      return this;

    if(oi.isUndefined())

      return oi;

    return new OclInteger(this.getLong()-oi.getLong());

  }



  /** @return an instance of OclInteger representing the value of this

   *          OclInteger multiplied with -1

   */

  public OclReal negative() {

    if(isUndefined())

      return this;

    return new OclInteger(-getLong());

  }



  /** This method is only invoked in case of an error: OclInteger's multiply

   *  must be called with an OclInteger parameter.

   *

   *  @see #multiply(OclInteger or)

   *  @see OclReal#multiply(OclReal r)

   */

  public OclMultiplyable multiply(OclMultiplyable m) {

    return multiply(toOclInteger(m, "OclInteger multiply()"));

  }



  /** @return an instance of OclInteger representing the product of this value

   *          and the parameter

   */

  public OclInteger multiply(OclInteger oi) {

    if(isUndefined())

      return this;

    if(oi.isUndefined())

      return oi;

    return new OclInteger(getLong() * oi.getLong());

  }



  /** @return an instance of OclReal representing the product of this value

   *          and the parameter

   */

  public OclReal multiply(OclReal oi) {

    return super.multiply(oi);

  }



  /** This method is only invoked in case of an error: OclInteger's divide

   *  must be called with an OclInteger parameter.

   *

   *  @see #divide(OclInteger or)

   *  @see OclReal#divide(OclReal r)

   */

  public OclMultiplyable divide(OclMultiplyable m) {

    return divide(toOclInteger(m, "OclInteger multiply()"));

  }



  /** @param oi must not be zero

   *  @return this value devided by the parameter

   */

  public OclReal divide(OclInteger oi) {

    if (oi.lValue==0l) {

      return new OclInteger(0,"division by zero");

    }

    if(isUndefined())

      return this;

    if(oi.isUndefined())

      return oi;

    return new OclReal(getDouble()/oi.getDouble());

  }



  /** @return this modulo the parameter

   */

  public OclInteger mod(OclInteger i) {

    if(isUndefined())

      return this;

    if(i.isUndefined()) 

      return i;

    return new OclInteger(lValue % i.lValue);

  }



  public OclInteger max(OclInteger i) {

    if(isUndefined())

      return this;

    if(i.isUndefined()) 

      return i;

    return new OclInteger( Math.max(lValue, i.lValue) );

  }



  public OclInteger min(OclInteger i) {

    if(isUndefined())

      return this;

    if(i.isUndefined()) 

      return i;

    return new OclInteger( Math.min(lValue, i.lValue) );

  }



  /** @param i must not be zero

   *  @return the number of times that the parameter completely fits within

   *          this value

   */

  public OclInteger div(OclInteger i) {

    if (i.lValue==0l) {

      return new OclInteger(0,"division by zero");

    }

    if(isUndefined())

      return this;

    if(i.isUndefined())

      return i;

    return new OclInteger( lValue / i.lValue );

  }



  /** @return the absolute value of this OclInteger value

   */

  public OclReal abs() {

    if(isUndefined())

      return this;

    return new OclInteger( Math.abs(lValue) );

  }



  /** @return the value of this OclInteger as an <CODE>int</CODE>

   *

   *  @throws OclException if this is an undefined OclInteger value

   */

  public int getInt() {

    if(isUndefined())

      throw new OclException("tried to evaluate undefined OclInteger: "+getUndefinedReason());

    return (int)lValue;

  }



  /** @return the value of this OclInteger as an <CODE>long</CODE>

   *

   *  @throws OclException if this is an undefined OclInteger value

   */

  public long getLong() {

    if(isUndefined()) 

      throw new OclException("tried to evaluate undefined OclInteger: "+getUndefinedReason());

    return lValue;

  }



  /** @return the value of this OclInteger as an <CODE>double</CODE>

   *

   *  @throws OclException if this is an undefined OclInteger value

   */

  public double getDouble() {

    if(isUndefined()) 

      throw new OclException("tried to evaluate undefined OclInteger: "+getUndefinedReason());

    return (double)lValue;

  }



  private OclInteger toOclInteger(Object o, String methodname) {

    try {

      OclInteger ret=(OclInteger) o;

      return ret;

    } catch (ClassCastException cce) {

      return new OclInteger(0,methodname+" called with non-OclInteger parameter");

    }

  }



  public String toString() {

    return "OclInteger<"+lValue+">";

  }



  public boolean equals(Object o) {

    try {

      return isEqualTo(o).isTrue();

    } catch (OclException e) {

      return false;

    }

  }



  public int hashCode() {

    return (int) lValue;

  }

} /* end class OclInteger */



