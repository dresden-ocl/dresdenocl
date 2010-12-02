/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Interpreter                                                   *
 * Copyright (C) 2002 Nikolai Krambrock (nikk@gmx.de)                *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Construction, University Of Technology Aachen, Germany            *
 * (http://www-lufgi3.informatik.rwth-aachen.de).                    *
 * It was done in co-operation with Software & Design and Managment  *
 * Troisdorf, Germany (http://www.sdm.de)                            *
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
 * this project, please visit the project home page:                 *
 * http://dresden-ocl.sourceforge.net                                * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl.interp.core.intern;

import tudresden.ocl.OclException;

import tudresden.ocl.interp.core.ExpResult;

import tudresden.ocl.lib.OclBoolean;


/**
 * Implementation for the ExpResult interface that takes an OclBoolean and 
 * hides most of the functionality away. Unfortionality it was not possible
 * to let OclBoolean implement the interface as we do not want to change the
 * original code.<br>
 * All the methods just call the same methods on OclBoolean.
 * 
 * @see OclBoolean
 */
public class ExpResultImpl implements ExpResult {
  private OclBoolean oclBoolean;

  public ExpResultImpl(OclBoolean oclBoolean) {
    Assert.assertTrue(oclBoolean != null);
    this.oclBoolean = oclBoolean;
  }

  public String getUndefinedReason() throws OclException {

    // This is a bad hack (but we can not do it another way as the
    // OclBoolean throws the RuntimeException)
    try {
      return oclBoolean.getUndefinedReason();
    } catch (RuntimeException e) {
      throw new OclException(e.getMessage());
    }
  }

  public boolean isTrue() throws OclException {
    if (isUndefined()) {
      throw new OclException("tried to get undefined ExpResult value: " + 
                             getUndefinedReason());
    }
    return oclBoolean.isTrue();
  }

  public boolean isUndefined() {
    return oclBoolean.isUndefined();
  }

  public String toString() {
    if (isUndefined()) {
      return "ExpResult<UNDEFINED:" + getUndefinedReason() + ">";
    } else {
      return "ExpResult<" + (isTrue() ? "TRUE>" : "FALSE>");
    }
  }
}