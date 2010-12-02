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

import java.util.Map;

import tudresden.ocl.interp.core.intern.Assert;

import tudresden.ocl.lib.OclBoolean;
import tudresden.ocl.lib.OclBooleanEvaluatable;
import tudresden.ocl.lib.OclCollection;
import tudresden.ocl.lib.OclComparableEvaluatable;
import tudresden.ocl.lib.OclIterator;
import tudresden.ocl.lib.OclRoot;
import tudresden.ocl.lib.OclRootEvaluatable;


/**
 * This method evaluates an iterating method (coll->forAll())  One evaluation
 * the expression is called for every element of the expression with
 * appropiate setting for the enviorment.  Importent part of the work is done
 * in the implementation of  OclCollection and it's subtypes.
 */
public class ExpCollOp extends Exp {
  String functionName;
  String iterName;
  Exp coll;
  Exp expression;
  Map varMap;
  Method runMethod;

  public ExpCollOp(String functionName, String iterName, Exp coll, 
                   Exp expression, Map varMap) {
    this.functionName = functionName;
    this.iterName = iterName;
    this.coll = coll;
    this.expression = expression;
    this.varMap = varMap;

    String javaEvalClassName = getEvaluatableTypes(functionName)[0];
    Class javaEvalClass = null;

    if (javaEvalClassName.equals("OclRootEvaluatable")) {
      javaEvalClass = OclRootEvaluatable.class;
    } else if (javaEvalClassName.equals("OclBooleanEvaluatable")) {
      javaEvalClass = OclBooleanEvaluatable.class;
    } else {
      Assert.assertTrue(javaEvalClassName.equals("OclComparableEvaluatable"));
      javaEvalClass = OclComparableEvaluatable.class;
    }

    Object[] params = new Object[] {OclIterator.class, javaEvalClass};
    this.runMethod = findMethod(coll, functionName, params);
  }

  public OclRoot evaluateInternal() {
    OclCollection oclColl = (OclCollection)coll.evaluate();
    final OclIterator javaIterInter = oclColl.getIterator();

    Object javaEvalNameInter;
    String javaEvalType = getEvaluatableTypes(functionName)[0];

    if (javaEvalType.equals("OclRootEvaluatable")) {
      javaEvalNameInter = new OclRootEvaluatable() {
        public OclRoot evaluate() {
          varMap.put(iterName, javaIterInter.getValue());
          return expression.evaluate();
        }
      };
    } else if (javaEvalType.equals("OclBooleanEvaluatable")) {
      javaEvalNameInter = new OclBooleanEvaluatable() {
        public OclBoolean evaluate() {
          varMap.put(iterName, javaIterInter.getValue());
          return (OclBoolean)expression.evaluate();
        }
      };
    } else {
      Assert.assertTrue(javaEvalType.equals("OclComparableEvaluatable"));
      javaEvalNameInter = new OclComparableEvaluatable() {
        public Comparable evaluate() {
          varMap.put(iterName, javaIterInter.getValue());
          return (Comparable)expression.evaluate();
        }
      };
    }

    Object[] params = new Object[] {javaIterInter, javaEvalNameInter};
    return call(oclColl, runMethod, params);
  }

  /**
   * @param featureName one of the iterating method names, excluding iterate
   * @return an String array containing the types connected to the iterating
   *         method with the given name; the returned array has length 3,
   *         with the name of the evaluatable interface at index 0, the name
   *         of the <CODE>evaluate()</CODE> methods return type at index 1,
   *         and the return type of the iterating method at index 2.
   */
  protected String[] getEvaluatableTypes(String featureName) {
    String[] ret = new String[3];
    if (featureName.equals("collect")) {
      ret[0] = "OclRootEvaluatable";
      ret[1] = "OclRoot";
      ret[2] = "OclCollection";
    } else if (featureName.equals("isUnique")) {
      ret[0] = "OclRootEvaluatable";
      ret[1] = "OclRoot";
      ret[2] = "OclBoolean";
    } else if (featureName.equals("sortedBy")) {
      ret[0] = "OclComparableEvaluatable";
      ret[1] = "java.lang.Comparable";
      ret[2] = "OclSequence";
    } else if (featureName.equals("exists")) {
      ret[0] = "OclBooleanEvaluatable";
      ret[1] = "OclBoolean";
      ret[2] = "OclBoolean";
    } else if (featureName.equals("forAll")) {
      ret[0] = "OclBooleanEvaluatable";
      ret[1] = "OclBoolean";
      ret[2] = "OclBoolean";
    } else if (featureName.equals("reject")) {
      ret[0] = "OclBooleanEvaluatable";
      ret[1] = "OclBoolean";
      ret[2] = "OclCollection";
    } else {
      Assert.assertTrue(featureName.equals("select"));
      ret[0] = "OclBooleanEvaluatable";
      ret[1] = "OclBoolean";
      ret[2] = "OclCollection";
    }
    return ret;
  }

  public Object[] children() {
    return new Object[] {iterName + "= iterates over ", coll, expression};
  }

  public String toString() {
    return super.toString() + ": " + functionName;
  }
}