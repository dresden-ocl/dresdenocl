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

import junit.framework.Test;
import junit.framework.TestSuite;

import tudresden.ocl.interp.types.reflect.ReflectionInstanceFacade;


/**
 * The Expamples out of the OCL specification 1.4 are implemented and asserted
 * with the correct results. The pages the tests are taken from are noted in 
 * the code.
 */
public class TestSpecExample extends TestEnv {
  public TestSpecExample(String s) {
    super(s);
  }

  protected void initFacades() {
    super.initFacades();
    modelFacade = SpecFacadeFactory.getNoPackageFacade();
    instanceFacade = SpecFacadeFactory.getNoInstanceFacade();
  }

  public void initTests() {

    // -- Examples pages 7-1 to 7-10
    ocl[0] = "context tudresden::ocl::interp::test::Company inv: self.numberOfEmployees > 2";
    ocl[1] = "context tudresden::ocl::interp::test::Company inv enoughEmployees: numberOfEmployees > 2";
    ocl[2] = "context tudresden::ocl::interp::test::Person inv: employer->forAll(numberOfEmployees > 2)";
    ocl[3] = "context tudresden::ocl::interp::test::Person inv: let person : tudresden::ocl::interp::test::Person = self in person.employer->forAll(numberOfEmployees > 2)";
    ocl[4] = "context tudresden::ocl::interp::test::Person inv: employer.numberOfEmployees->sum>0";
    ocl[5] = "context tudresden::ocl::interp::test::Person inv: let collegs : Integer = self.employer.numberOfEmployees->sum in (collegs > 2) and (collegs < 10)";
    ocl[6] = "context tudresden::ocl::interp::test::Company inv: let manager : tudresden::ocl::interp::test::Person = self.manager in manager.oclAsType(tudresden::ocl::interp::test::Manager).managedCompanies-> size > 0";
    ocl[7] = "context tudresden::ocl::interp::test::Company inv: --comment\nself.numberOfEmployees > 2";
    failOcl[0] = "context tudresden::ocl::interp::test::Company inv: numberOfEmployees > 3";
    failOcl[1] = "context tudresden::ocl::interp::test::Company inv: numberOfEmployees < 4";
    failOcl[2] = "context tudresden::ocl::interp::test::Person inv: let person : tudresden::ocl::interp::test::Person = self in person.employer->forAll(numberOfEmployees > 100)";

    // -- Type conformance for Collections
    // is checked in TestBasic

    // -- Preceden Rules
    // to be inserted some time (low priority)

    // -- Examples pages 7-11 to 7-14
    ocl[20] = "context tudresden::ocl::interp::test::Person inv: self.age>0";
    ocl[21] = "context tudresden::ocl::interp::test::Company inv: manager.isUnemployed = false inv: employees->notEmpty";
    ocl[22] = "context tudresden::ocl::interp::test::Person inv: self.employer->size < 4";
    ocl[23] = "context tudresden::ocl::interp::test::Person inv: isUnemployed implies employer->isEmpty";
    ocl[24] = "context tudresden::ocl::interp::test::Company inv: self.manager.age>40";
    ocl[25] = "context tudresden::ocl::interp::test::Person inv: wife->notEmpty implies (wife.sex = true)";
    ocl[26] = "context tudresden::ocl::interp::test::Person inv: wife->notEmpty implies wife.sex = true";
    ocl[27] = "context tudresden::ocl::interp::test::Person inv: (wife->notEmpty implies wife.age >= 18) and (husband->notEmpty implies husband.age >= 18)";
    ocl[28] = "context tudresden::ocl::interp::test::Company inv: employees->size <= 50";
    ocl[29] = "context tudresden::ocl::interp::test::Person inv: true or wife.lastName='Hallo'";
    ocl[30] = "context tudresden::ocl::interp::test::Person inv: not(wife.lastName='Hallo' and false)";

    // -- Examples page 7-17
    //  State Checks are useles whenever we come form reflection
    //  oclIsNew is not yet supported
    //  allInstances is not supported
    //  we should insert type conformance for collections
    ocl[40] = "context tudresden::ocl::interp::test::Manager inv: self.oclIsTypeOf(tudresden::ocl::interp::test::Manager)";
    ocl[41] = "context tudresden::ocl::interp::test::Manager inv: not self.oclIsTypeOf(tudresden::ocl::interp::test::Person)";
    ocl[42] = "context tudresden::ocl::interp::test::Manager inv: not self.oclIsTypeOf(tudresden::ocl::interp::test::Company)";
    ocl[43] = "context tudresden::ocl::interp::test::Manager inv: self.oclIsKindOf(tudresden::ocl::interp::test::Manager)";
    ocl[44] = "context tudresden::ocl::interp::test::Manager inv: self.oclIsKindOf(tudresden::ocl::interp::test::Person)";
    ocl[45] = "context tudresden::ocl::interp::test::Manager inv: not self.oclIsKindOf(tudresden::ocl::interp::test::Company)";
    failOcl[40] = "context tudresden::ocl::interp::test::Person inv: self.oclIsKindOf(tudresden::ocl::interp::test::Manager)";
    failOcl[41] = "context tudresden::ocl::interp::test::Person inv: self.oclIsTypeOf(tudresden::ocl::interp::test::Manager)";
  }

  public static Test suite() {
    return new TestSuite(TestSpecExample.class);
  }

  public void testOne() throws Exception {
    int i = 5;
    assertEquals("OclConstraint " + i, true, evaluateTest(ocl[i]));
  }
}