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

// FILE: d:/java/classes/de/tudresden/ocl/OclContainer.java



package tudresden.ocl20.core.lib;

import java.util.*;



/** This class is a implementation of OclRoot that is backed by a "contained"

 *  OclRoot object. That contained object can be changed. <CODE>OclContainer</CODE> is

 *  necessary for the <code>iterate</code> operation on collections: The

 *  accumulator argument is initialized as an instance of <CODE>OclContainer</CODE>

 *  and can be updated with every iteration step. The method iterate is

 *  implemented to update the contained value of the <CODE>OclContainer</CODE>

 *  every step and return the contained value (NOT the OclContainer) after the

 *  iteration is complete.

 *

 *  @see OclCollection#iterate(OclIterator iter, OclContainer acc, OclRootEvaluatable eval)

 *  @author Frank Finger

 */

public class OclContainer implements OclRoot {

    

    private OclRoot containedValue;

    

    /** create a new OclContainer that contains the <CODE>OclRoot</CODE> object

     *  given as argument

     *

     *  @param o the initial value to be contained by this OclContainer

     */

    public OclContainer(OclRoot o) {

        containedValue=o;

    }

    

    /** two OclContainers are equal if their contained values are equal; usually

     *  not the OclContainers themselves should be compared, but their values

     *  @see #getValue()

     */

    public OclBoolean isEqualTo(Object o) {

        try {

            OclContainer cont=(OclContainer)o;

            return containedValue.isEqualTo(cont.containedValue);

        } catch (ClassCastException e) {

            return new OclBoolean(0,"OclContainer isEqualTo() called with non-OclContainer argument");

        }

    }

    

    /** @return the negated result of isEqualTo

     *

     *  @see #isEqualTo(Object o)

     */

    public OclBoolean isNotEqualTo(Object o) {

        return isEqualTo(o).not();

    }

    

    public OclBoolean oclIsKindOf(OclType t){

        return containedValue.oclIsKindOf(t);

    }

    public OclBoolean oclIsTypeOf(OclType t){

        return containedValue.oclIsTypeOf(t);

    }

    

    /** sets the contained value to the argument; this method is called inside

     *  the method <CODE>iterate</CODE> of <CODE>OclCollection</CODE>

     */

    protected void setValue(OclRoot o) {

        containedValue=o;

    }

    

    /** returns the contained value; this method is called inside

     *  the method <CODE>iterate</CODE> of <CODE>OclCollection</CODE> to get the

     *  OclRoot that will be returned by that method, and in

     *  (inner) classes implementing <CODE>OclRootEvaluatable</CODE> to refer to

     *  the value of the accumulator

     */

    public OclRoot getValue() {

        return containedValue;

    }

    

    /** @return the result of isUndefined() of the contained value; the

     *          OclContainer itself can not be undefined

     */

    public boolean isUndefined() {

        return containedValue.isUndefined();

    }

    

    /** @return the result of getUndefinedReason() of the contained value; the

     *          OclContainer itself can not be undefined

     */

    public final String getUndefinedReason() {

        return containedValue.getUndefinedReason();

    }

} /* end class OclContainer */



