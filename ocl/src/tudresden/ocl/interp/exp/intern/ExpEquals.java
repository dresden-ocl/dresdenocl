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

package tudresden.ocl.interp.exp.intern;

import tudresden.ocl.lib.OclBoolean;
import tudresden.ocl.lib.OclRoot;


/**
 * Checks wether two Expression evaluate to the same. Special checks for
 * null-values are done therefore no use of ExpComposite possible.
 */
public class ExpEquals extends Exp {
  Exp var1;
  Exp var2;
  boolean positive;

  public ExpEquals(Exp var1, Exp var2, boolean positive) {
    this.var1 = var1;
    this.var2 = var2;
    this.positive = positive;
  }

  /**
   * @see Exp#evaluate()
   */
  public OclRoot evaluateInternal() {
    OclRoot oclVar1 = var1.evaluate();
    OclRoot oclVar2 = var2.evaluate();
    OclBoolean var;

    if (oclVar1 != null && oclVar1.isUndefined()) {
      return new OclBoolean(0, oclVar1.getUndefinedReason());
    } else if (oclVar2 != null && oclVar2.isUndefined()) {
      return new OclBoolean(0, oclVar2.getUndefinedReason());
    }

    if (oclVar1 == null && oclVar2 == null) {
      var = OclBoolean.TRUE;
    } else if (oclVar1 == null || oclVar2 == null) {
      var = OclBoolean.FALSE;
    } else {
      var = oclVar1.isEqualTo(oclVar2);
    }

    if (positive) {
      return var;
    } else {
      return var.not();
    }
  }

  public Object[] children() {
    if (positive) {
      return new Object[] {var1, new StringBuffer("="), var2};
    } else {
      return new Object[] {var1, new StringBuffer("<>"), var2};
    }
  }
}