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

import java.util.Iterator;
import java.util.Map;

import tudresden.ocl.interp.core.*;
import tudresden.ocl.interp.core.intern.Assert;
import tudresden.ocl.interp.core.intern.ExpResultImpl;
import tudresden.ocl.interp.types.InstanceFacade;

import tudresden.ocl.lib.Ocl;
import tudresden.ocl.lib.OclBoolean;
import tudresden.ocl.lib.OclRoot;


/**
 * This class represents a whole ocl-constraint. It iterates throgh the whole
 * context and sets the "self"-variable in the evniorment.
 */
public class ExpBody extends Exp implements ExpTree {
  String relevantType;
  Map varMap;
  Exp exp;
  InstanceFacade instanceFacade;
  String selfVar;

  public ExpBody(String relevantType, Map varMap, Exp expression, 
                 String selfVar) {
    this.relevantType = relevantType;
    this.varMap = varMap;
    this.exp = expression;
    this.selfVar = selfVar;
    Assert.assertTrue(relevantType != null);
    Assert.assertTrue(varMap != null);
    Assert.assertTrue(expression != null);
    Assert.assertTrue(selfVar != null);
  }

  public ExpResult check(InstanceFacade instanceFacade) {
    this.instanceFacade = instanceFacade;
    Assert.notNull(instanceFacade, "check", "instanceFacade");
    return new ExpResultImpl((OclBoolean)evaluate());
  }

  public OclRoot evaluateInternal() {

    // If this fails ExpBody was called by evaluate directly rather then by
    // check
    Assert.assertTrue(instanceFacade != null);

    Iterator instanceIterator = instanceFacade.getRelevantOf(relevantType);
    while (instanceIterator.hasNext()) {

      // Set the current instance to be self
      varMap.clear();
      varMap.put(selfVar, Ocl.getFor(instanceIterator.next()));

      OclRoot result = exp.evaluate();

      if (result.isUndefined()) {
        return new OclBoolean(0, result.getUndefinedReason());
      }

      if (!((OclBoolean)result).isTrue()) {
        return (OclBoolean)result;
      }
    }

    return OclBoolean.TRUE;
  }

  public Object[] children() {
    return new Object[] {
      new StringBuffer(selfVar + " = iterates over instances"), exp
    };
  }

  public String toString() {
    return super.toString() + ": " + relevantType;
  }
}