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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tudresden.ocl.lib.OclRoot;


/**
 * Represents a number of let-expressens that have to be  evaluated befor the
 * Expressen that gives the result back can be evaluated. (let a=person.age
 * in ...)  During evaluation all the (let-)expressions are evaluated and put
 * into the enviorment.
 */
public class ExpLet extends Exp {
  private Map varMap;
  private Exp[] expressions;
  private String[] varNames;
  private Exp runExp;

  public ExpLet(Map varMap, Exp[] expressions, String[] varNames, Exp runExp) {
    this.varMap = varMap;
    this.expressions = expressions;
    this.varNames = varNames;
    this.runExp = runExp;
  }

  /**
   * @see Exp#evaluate()
   */
  public OclRoot evaluateInternal() {
    for (int i = 0; i < expressions.length; i++) {
      OclRoot tmpResult = expressions[i].evaluate();
      varMap.put(varNames[i], tmpResult);
    }
    return runExp.evaluate();
  }

  public Object[] children() {
    List result = new ArrayList();
    result.add(runExp);
    for (int i = 0; i < varNames.length; i++) {
      result.add(varNames[i] + "= ");
      result.add(expressions[i]);
    }
    return result.toArray();
  }
}