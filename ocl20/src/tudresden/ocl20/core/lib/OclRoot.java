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

// FILE: d:/java/classes/de/tudresden/ocl/OclRoot.java



package tudresden.ocl20.core.lib;

import java.util.*;



/**

 * Defines the common interface of  subtypes of OclCollection and subtypes of OclAny.

 */

public interface OclRoot extends Cloneable{

    /** Returns true if this object is equal to the object given as parameter.

     *  For definitions of equality for the different types, see the

     *  implementing methods. Generally, all basic and collection objects are

     *  considered equal if they contain the same value(s), application

     *  objects are considered equal if they are identical (<CODE>==</CODE>, not

     *  <CODE>equal</CODE>).

     */

    public abstract OclBoolean isEqualTo(Object o);

    

    /** Returns the negated result of <CODE>isEqualTo</CODE>.

     *

     *  @see #isEqualTo(Object o)

     */



     public abstract OclBoolean isNotEqualTo(Object o);

    /** Returns true if this object is the result of an undefined OCL

     *  expression.

     */

    public boolean isUndefined();

    /**

     Returns the reason, why this undefined ocl object has been created.

     @throws RuntimeException if the onject is not undefined

     */

    public String getUndefinedReason();

    

    public OclBoolean oclIsKindOf(OclType t);

    public OclBoolean oclIsTypeOf(OclType t);

    /**
     * @return the java class of this object  
     * @author: Ronny Brandt     
     */
    public Class toUmlClass();

    

}



