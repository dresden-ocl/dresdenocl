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
// FILE: d:/projekte/diplom/classes/tudresden/ocl/check/Any.java

package tudresden.ocl.check.types;

import tudresden.ocl.check.OclTypeException;
import java.util.*;

/** This interface corresponds to the OCL type OclAny. It does not define
 *  any additional methods to those inherited from Type
 *
 *  @author Frank Finger
 */
public interface Any extends Type {

  /**
     A dummy type for Void.

     To be returned by {@link Type#navigateParameterized},
     if the return type of the requested method is void.
     This is ok for the type checker, if the method is in the
     context clause of the ocl constraint. However, in the ocl
     expression itself this is an error.

     This dummy is a don't-touch-me object,
     most methods throw an {@link IllegalArgumentException}.
  */
  public static final Any VOID=new VoidAny();

  class VoidAny implements Any
  {
    /**
       @throws IllegalArgumentException always
    */
    public Type navigateQualified(String name, Type[] qualifiers) throws OclTypeException
    {
      throw new IllegalArgumentException("called Any.VOID.navigateQualified(\""+Basic.qualifierString(name,qualifiers)+"\"), this should never happen.");
    }

    /**
       @throws IllegalArgumentException always
    */
    public Type navigateParameterized(String name, Type[] params) throws OclTypeException
    {
      throw new IllegalArgumentException("called Any.VOID.navigateParameterized(\""+Basic.signatureString(name,params)+"\"), this should never happen.");
    }

    /**
       @throws IllegalArgumentException always
    */
    public boolean hasState(String name)
    {
      throw new IllegalArgumentException("called Any.VOID.hasState(\""+name+"\"), this should never happen.");
    }


    /**
       Returns true, if and only if the argument is this.
    */
    public boolean conformsTo(Type type)
    {
      return (this==type);
    }

    public String toString()
    {
      return "void";
    }
  };



} /* end interface Any */

