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
// FILE: d:/java/classes/de/tudresden/ocl/OclIterator.java

package tudresden.ocl.lib;
import java.util.*;

/** The OclIterator is needed to transform OCL <i>iterating methods</i> to
 *  Java. An OclIterator can be obtained from an OclCollection and is given
 *  back to the OclCollection as an argument of the iterating method.
 *
 *  <p>This class is a wrapper class around an <CODE>java.util.Iterator</CODE>.
 *  It differs from <CODE>Iterator</CODE> in the ability to set the pointer to
 *  the next element once and then read this element arbitrarily often. In
 *  contrast, <CODE>Iterator</CODE> moves the pointer every time the element
 *  is read.
 *
 *  <p>When constructed, the <CODE>OclIterator</CODE>s pointer is before the first element.
 *  Calling <CODE>getValue()</CODE> would yield <CODE>null</CODE>.
 *
 *  @see OclCollection
 *  @author Frank Finger
 */
public class OclIterator {

  // Attributes
  private Iterator iter;
  private OclRoot value;

  protected OclIterator(Collection c) {
    iter=c.iterator();
  }

  protected OclIterator(Iterator i) {
    iter=i;
  }

  /** This operation is the only one that is called from out of the OCL
   *  class library. It returns the <CODE>OclRoot</CODE> element that the
   *  <CODE>OclIterator</CODE> is pointing to.
   */
  public OclRoot getValue() {
    return value;
  }

  /** @return <code>true</code> if the iteration has more elements
   */
  boolean hasNext() {
    return iter.hasNext();
  }

  /** moves the <CODE>OclIterator</CODE>s pointer to the next element
   *
   * @throws OclClassCastException if the next element that the backing
   *         <CODE>java.lang.Iterator</CODE> returns is not of type
   *         <CODE>OclRoot</CODE>; this means propably that the OclCollection
   *         that returned this OclIterator contains non-OclRoot elements
   */
  void next() {
    try {
      value=(OclRoot)iter.next();
    } catch (ClassCastException cce) {
      throw new OclClassCastException(
        "OclIterator ran into non-OclRoot element - this means propably that"+
        " the OclCollection that returned this OclIterator contains"
        +" non-OclRoot elements"
      );
    }
  }
} /* end class OclIterator */

