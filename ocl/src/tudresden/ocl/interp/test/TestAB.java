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

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.ModelFacade;

import tudresden.ocl.interp.core.Interpreter;
import tudresden.ocl.interp.types.InstanceFacade;


public class TestAB extends TestCase {
  private InstanceFacade instanceFacade;
  private ModelFacade modelFacade;
  private final String ocl1 = "context A inv: r1.c > 0";
  private final String ocl2 = "context A inv: r1.r3 = self";
  private final String ocl3 = "context A inv: r2->forAll(c>1)";
  private final String ocl3a = "context A inv: r2->exists(c>1)";
  private final String ocl3b = "context A inv: r2->exists(r3=self)";
  private final String ocl4 = "context A inv: r1.c > 100";
  private final String ocl5 = "context A inv: r2->forAll(r3=self)";

  public TestAB(String s) {
    super(s);
  }

  public static Test suite() {
    return new TestSuite(TestAB.class);
  }

  protected void setUp() throws Exception {
    super.setUp();
    instanceFacade = new ABDummyFacade();
    modelFacade = (ModelFacade)instanceFacade;
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testTypeCheck() throws OclTypeException, IOException {
    Interpreter.expEvaluate(ocl1, modelFacade);
    Interpreter.expEvaluate(ocl2, modelFacade);
    Interpreter.expEvaluate(ocl3, modelFacade);
    Interpreter.expEvaluate(ocl4, modelFacade);
    Interpreter.expEvaluate(ocl5, modelFacade);

    try {
      Interpreter.expEvaluate("context A inv: r1.c", modelFacade);
      throw new RuntimeException();
    } catch (OclTypeException e){}

    try {
      Interpreter.expEvaluate("context A inv: r1.c > ''", modelFacade);
      throw new RuntimeException();
    } catch (OclTypeException e){}

  }

  protected boolean evaluateTest(String test) throws IOException {
    return Interpreter.expEvaluate(test, modelFacade).check(instanceFacade).isTrue();
  }

  public void testSimpleEvaluate() throws OclTypeException, IOException {
    assertEquals(true, evaluateTest("context A inv: d > 0"));
    assertEquals(true, evaluateTest("context A inv: d < 100"));
    assertEquals(true, evaluateTest("context A inv: d = 1"));
    assertEquals(false, evaluateTest("context A inv: d < 0"));
    assertEquals(false, evaluateTest("context A inv: d > 100"));
  }

  public void testEvaluate() throws OclTypeException, IOException {
    assertEquals(true, evaluateTest(ocl1));
    assertEquals(true, evaluateTest(ocl2));
    assertEquals(true, evaluateTest(ocl3));
    assertEquals(true, evaluateTest(ocl3a));
    assertEquals(true, evaluateTest(ocl3b));

    assertEquals(false, evaluateTest(ocl4));
    assertEquals(false, evaluateTest(ocl5));
  }
}