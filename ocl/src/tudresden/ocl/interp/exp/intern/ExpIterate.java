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

import java.util.Map;

import tudresden.ocl.lib.OclCollection;
import tudresden.ocl.lib.OclContainer;
import tudresden.ocl.lib.OclIterator;
import tudresden.ocl.lib.OclRoot;
import tudresden.ocl.lib.OclRootEvaluatable;


/**
 * Represents an coll->iterate(). For every element of the collection the
 * expression is called. The variable oclIter is used to access the current
 * element. oclAcc is used to be the result of the evaluation (and to save
 * results to the next element). See the Ocl-Spec for detailed examples.
 */
public class ExpIterate extends Exp {
  Exp appliedToVar;
  Exp expression;
  Exp declerator;
  String oclIter;
  String oclAcc;
  Map varMap;

  public ExpIterate(Exp appliedToVar, Exp expression, Exp declerator, 
                    String oclIter, String oclAcc, Map varMap) {
    this.appliedToVar = appliedToVar;
    this.expression = expression;
    this.declerator = declerator;
    this.oclIter = oclIter;
    this.oclAcc = oclAcc;
    this.varMap = varMap;
  }

  public OclRoot evaluateInternal() {
    varMap.put(oclAcc, declerator.evaluate());

    final OclIterator javaIterInter = ((OclCollection)appliedToVar.evaluate()).getIterator();
    final OclContainer javaContInterpret = new OclContainer(declerator.evaluate());

    OclRootEvaluatable javaEvalNameInterpret = new OclRootEvaluatable() {
      public OclRoot evaluate() {
        varMap.put(oclIter, javaIterInter.getValue());
        OclRoot tmpResult = expression.evaluate();
        varMap.put(oclAcc, tmpResult);
        return tmpResult;
      }
    };

    OclCollection appliedToColl = (OclCollection)appliedToVar.evaluate();

    return appliedToColl.iterate(javaIterInter, javaContInterpret, 
                                 javaEvalNameInterpret);
  }

  public Object[] children() {
    return new Object[] {oclIter, oclAcc, appliedToVar, expression, declerator};
  }
}