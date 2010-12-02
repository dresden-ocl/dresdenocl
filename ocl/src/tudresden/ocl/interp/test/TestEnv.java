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

package tudresden.ocl.interp.test;

import junit.framework.TestCase;

import tudresden.ocl.OclException;

import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.ModelFacade;

import tudresden.ocl.interp.core.ExpResult;
import tudresden.ocl.interp.core.Interpreter;
import tudresden.ocl.interp.types.InstanceFacade;


public abstract class TestEnv extends TestCase {
  protected InstanceFacade instanceFacade;
  protected ModelFacade modelFacade;
  protected String[] ocl = new String[200];
  protected String[] failOcl = new String[200];
  protected String[] badOcl = new String[100];
  protected String[] oclTypeExp = new String[100];
  protected String[] oclEvalExp = new String[100];
  protected String[] oclUndefExp = new String[100];

  protected TestEnv(String s) {
    super(s);
    initFacades();
    initTests();
  }

  protected abstract void initTests();

  protected void initFacades() {
    instanceFacade = SpecFacadeFactory.getInstanceFacade();
    modelFacade = SpecFacadeFactory.getModelFacade();
  }

  public void testTypes() throws Exception {
    checkTest(ocl);
    checkTest(failOcl);
  }

  public void testEvaluate() throws Exception {
    evaluateTest(ocl, true);
  }

  public void testBadEvaluate() throws Exception {
    evaluateTest(badOcl, true);
  }

  public void testFailEvaluate() throws Exception {
    evaluateTest(failOcl, false);
  }

  public void testTypeException() throws Exception {
    exceptionsTest(oclTypeExp, OclTypeException.class);
  }

  public void testEvalException() throws Exception {
    exceptionsTest(oclEvalExp, OclException.class);
  }

  public void testUndef() throws Exception {
    undefTest(oclUndefExp);
  }

  protected boolean evaluateTest(String test) throws Exception {
    return Interpreter.expEvaluate(test, modelFacade).check(instanceFacade).isTrue();
  }

  private void undefTest(String[] tests) throws Exception {
    for (int i = 0; i < tests.length; i++) {
      if (tests[i] != null) {
        try {
          ExpResult reslut = Interpreter.expEvaluate(tests[i], modelFacade).check(
                                   instanceFacade);
          assertEquals(true, reslut.isUndefined());
        } catch (Exception e) {
          System.err.println("tests[" + i + "] failed with exception");
          throw e;
        }
      }
    }
  }

  private void evaluateTest(String[] tests, boolean expected)
                     throws Exception {
    for (int i = 0; i < tests.length; i++) {
      if (tests[i] != null) {
        try {
          assertEquals("OclConstraint " + i, expected, evaluateTest(tests[i]));
        } catch (Exception e) {
          System.err.println("tests[" + i + "] failed with exception");
          throw e;
        }
      }
    }
  }

  private void checkTest(String[] tests) throws Exception {
    for (int i = 0; i < tests.length; i++) {
      if (tests[i] != null) {
        try {
          Interpreter.expEvaluate(tests[i], modelFacade);
        } catch (Exception e) {
          System.err.println("tests[" + i + "] failed with exception");
          throw e;
        }
      }
    }
  }

  private void exceptionsTest(String[] tests, Class exceptionClass)
                       throws Exception {
    for (int i = 0; i < tests.length; i++) {
      if (tests[i] != null) {
        try {
          evaluateTest(tests[i]);
        } catch (Exception e) {
          if (!(exceptionClass.isInstance(e))) {
            System.err.println("tests[" + i + 
                               "] failed with wrong exception");
            throw e;
          } else {
            System.out.println(
                  "tests[" + i + "] failed correctly with exception: " + 
                  e.getMessage());
          }
          continue;
        }
        System.err.println("tests[" + i + "] passed but should fail");
        throw new RuntimeException("tests[" + i + "] passed but should fail");
      }
    }
  }
}