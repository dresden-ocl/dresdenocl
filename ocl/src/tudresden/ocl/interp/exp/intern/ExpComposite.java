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

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tudresden.ocl.lib.OclAnyImpl;
import tudresden.ocl.lib.OclRoot;


/**
 * This class is a multi-purpose class. It does a kind of lazy evaluation.
 * Everything needed for the method-call is given at creation time. When
 * evaluated the method is called and the  result given back.
 */
public class ExpComposite extends Exp {
  Exp base;
  String methodName;
  Object[] params;
  Method runMethod;

  public ExpComposite(Exp base, String methodName, Object[] params) {
    this.base = base;
    this.methodName = methodName;
    this.params = params;

    this.runMethod = findMethod(base, methodName, params);
  }

  public OclRoot evaluateInternal() {
    OclRoot oclBase;
    Object[] oclParams;

    if (params == null) {
      oclParams = new Object[0];
    } else {
      oclParams = new Object[params.length];
    }

    oclBase = base.evaluate();
    for (int i = 0; i < oclParams.length; i++) {
      if (params[i] instanceof Exp) {
        oclParams[i] = ((Exp)params[i]).evaluate();
      } else {
        oclParams[i] = params[i];
      }
    }

    if (oclBase == null) {
      return new OclAnyImpl(0, "FeatureCall on a null-Object: " + methodName);
    } else {
      return call(oclBase, runMethod, oclParams);
    }
  }

  public Object[] children() {
    if (params.length == 1 && params[0] instanceof String) {
      return new Object[] {base};
    } else {
      List result = new ArrayList();
      result.add(base);
      result.addAll(Arrays.asList(params));
      return result.toArray();
    }
  }

  public String toString() {
    if (params.length == 1 && params[0] instanceof String) {
      return super.toString() + ": " + methodName + "(" + params[0] + ")";
    } else {
      return super.toString() + ": " + methodName;
    }
  }
}