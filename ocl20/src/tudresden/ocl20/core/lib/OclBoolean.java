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

// FILE: d:/java/classes/de/tudresden/ocl/OclBoolean.java



package tudresden.ocl20.core.lib;

import java.util.*;



/** A class that represents the basic OCL type Boolean. There is no public

 *  constructor for this class to enforce that exactly two instances of this

 *  class exist: <CODE>TRUE</CODE> and <CODE>FALSE</CODE>. This is a slightly

 *  weakened Singleton pattern.

 *

 *  <p>TRUE and FALSE (in capital letters) in this documentation page denote the

 *  class attributes of the same name.

 *

 *  @author Frank Finger

 */

public class OclBoolean extends OclAny {



  // *** attributes ***



  /** The instance of this class that represents the boolean value false.

   */

  public static final OclBoolean FALSE=new OclBoolean(false);



  /** The instance of this class that represents the boolean value true.

   */

  public static final OclBoolean TRUE=new OclBoolean(true);



  private boolean bValue;



  // *** constructors ***



  /** constructor for undefined value */

  public OclBoolean(int dummy, String undefinedreason) {

    super(dummy, undefinedreason);

  }



  /** constructor for defined values */

  private OclBoolean(boolean b) {

    bValue=b;

  }



  public static OclBoolean getOclRepresentationFor(boolean b) {

    if (b)

      return OclBoolean.TRUE;

    else

      return OclBoolean.FALSE;

  }

  

  // *** methods ***



  /** @return TRUE if the called object and the parameter are identical

   */

  public OclBoolean isEqualTo(Object o) {

    if ( !(o instanceof OclBoolean) ) {

      System.out.println("OclBoolean isEqualTo() is called with a non-OclBoolean parameter");

      return FALSE;

    }

    OclBoolean other=(OclBoolean)o;

    if(isUndefined()) 

      return this; // no need to create a new one

    else if(other.isUndefined()) 

      return other; // no need to create a new one

    if (this==o)

      return TRUE;

    else

      return FALSE;

  }



  /** @return the negateted result of isEqualTo

   *  @see #isEqualTo(Object o)

   */

  public OclBoolean isNotEqualTo(Object o) {

    return isEqualTo(o).not();

  }



  /** @return TRUE if this object is different from the one given as parameter

   */

  public OclBoolean xor(OclBoolean b) {

    return this.isNotEqualTo(b);

  }



  /** @return TRUE if this object, the operations parameter or both are TRUE,

              FALSE otherwise

   */

  public OclBoolean or(OclBoolean b) {

    if (this==TRUE)

      return TRUE;

    else if (b==TRUE)

      return TRUE;

    else if (isUndefined())

      return this;

    else if (b.isUndefined())

      return b;

    else

      return FALSE;

  }



  /** @return TRUE if this object and the operations parameter are TRUE,

              FALSE otherwise

   */

  public OclBoolean and(OclBoolean b) {

    if (this==FALSE || b==FALSE)

      return FALSE;

    else if (isUndefined())

      return this; // there is no need to create a new undefined

    else if (b.isUndefined())

      return b;

    else

      return TRUE;

  }



  /** @return TRUE if this object is FALSE, FALSE otherwise

   */

  public OclBoolean not() {

    if (this==TRUE)

      return FALSE;

    else if (this==FALSE)

      return TRUE;

    else if (isUndefined())

      return this;

    else

      throw new RuntimeException();

  }



  /** @return FALSE if this object is TRUE and the parameter of the operation

   *          is FALSE, TRUE otherwise

   */

  public OclBoolean implies(OclBoolean b) {

    return this.not().or( this.and(b) );

  }



  /** @return param1 if this OclBoolean is true, param2 otherwise;

   *          usually it's preferrable to use Javas "?:" operator

   *          since that is type preserving

   */

  public OclRoot ifThenElse(OclRoot param1, OclRoot param2) {

    if(isUndefined())

      return new OclUndefined(getUndefinedReason());      

    if (isTrue())

      return param1;

    else

      return param2;

  }



  /** @return <code>true</code> if this object is TRUE, <code>false</code>

   *          otherwise

   */

  public boolean isTrue() {

    if (isUndefined()) {

      throw new OclException("tried to evaluate undefined OclBoolean value: "+getUndefinedReason());

    }

    return bValue;

  }



  /** This method returns an undefined value.

   */

//  public OclRoot getFeature(String name) {

//    return new OclBoolean(0,"feature "+name+" of OclBoolean requested");

//  }



  /** This property is no longer present in OCL 1.3. In spite of this, the

   *  library contains this method. The problems that lead to its cancellation

   *  do not occur in this Java implementation.

   */

  public OclType oclType() {

    return OclPrimitiveType.getOclBoolean();

  }



  public String toString() {

    if (isUndefined()) {

      return "OclBoolean<UNDEFINED:"+getUndefinedReason()+">";

    } else {

      return "OclBoolean<"+ ( bValue ? "TRUE>" : "FALSE>" );

    }

  }

  /**
   * @return the java class of this object  
   * @author: Ronny Brandt     
   */
  public Class toUmlClass() {
	  return boolean.class;
  }


} /* end class OclBoolean */



