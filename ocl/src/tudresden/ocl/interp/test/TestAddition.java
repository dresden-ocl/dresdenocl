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


/**
 * This test are added to the BasicTests and the TestSpecExample for four 
 * reasons:
 * <ul>
 * <li> Bugs that were found (in manual testing and reviewing)
 *      are now tested through this test-cases
 * <li> Parts of the code that were not tested through the other Tests needed 
 *      to be covered. This process has been done with a coverage-checker.
 * <li> Some tests on exception handling and undefined values were added. This 
 *      is not part of the specification.
 * <li> Tests were added that just seemed right to me.
 * </ul>
 */
public class TestAddition extends TestEnv {
  public TestAddition(String s) {
    super(s);
  }

  public void initTests() {

    // own tests that just fit in the context
    ocl[0] = "context Company inv: numberOfEmployees = 3 or numberOfEmployees = 4";
    ocl[1] = "context Company inv: 3 = numberOfEmployees or 4 = numberOfEmployees";
    ocl[2] = "context Person inv: wife = wife";
    ocl[3] = "context Person inv: wife->isEmpty or not (wife = husband)";
    ocl[4] = "context Bank inv: subnames->forAll(size > self.name.size)";
    ocl[5] = "context Person inv: Person.associationEnds->size = Person.attributes->size";
    ocl[6] = "context Company inv: Company.supertypes->includes(NamedEntety)";
    ocl[7] = "context Company inv: privateField = 'private'";
    failOcl[0] = "context Company inv: 3 = numberOfEmployees or 5 = numberOfEmployees";
    failOcl[1] = "context Company inv: Company.supertypes->includes(Person)";

    //tests for method invokation
    ocl[20] = "context Company inv: name = getName()";
    ocl[21] = "context Company inv: Set{1 .. 10}->forAll(self.getEmployeesMinus(abs)=self.numberOfEmployees - abs)";
    ocl[22] = "context Company inv: Set{1..10}->forAll(self.getEmployeesMinus(abs)=self.numberOfEmployees - abs)";
    failOcl[20] = "context Company inv: Set{1 .. 10}->forAll(self.getEmployeesMinus(abs)=-6)";
    failOcl[21] = "context Company inv: Set{1 .. 10}->forAll(self.getEmployeesMinus(abs)=3)";
    failOcl[22] = "context Company inv: Set{1 .. 10}->forAll(self.getEmployeesMinus(abs)>-6)";
    failOcl[23] = "context Company inv: Set{1 .. 10}->forAll(self.getEmployeesMinus(abs)<3)";

    //tests for Meta-Information
    ocl[40] = "context Manager inv: Manager.name = 'Manager'";
    ocl[41] = "context Manager inv: Manager.attributes->includes('employer') and ";
    ocl[41] = ocl[41] + 
              "Manager.attributes->includes('managedCompanies') and ";
    ocl[41] = ocl[41] + "Manager.attributes->includes('isMarried') and ";
    ocl[41] = ocl[41] + "Manager.attributes->includes('isUnemployed') and ";
    ocl[41] = ocl[41] + "Manager.attributes->includes('birthDate') and ";
    ocl[41] = ocl[41] + "Manager.attributes->includes('isMarried') and ";
    ocl[41] = ocl[41] + "Manager.attributes->includes('age') and ";
    ocl[41] = ocl[41] + "Manager.attributes->includes('firstName') and ";
    ocl[41] = ocl[41] + "Manager.attributes->includes('lastName') and ";
    ocl[41] = ocl[41] + "Manager.attributes->includes('sex') and ";
    ocl[41] = ocl[41] + "Manager.attributes->includes('employer') and ";
    ocl[41] = ocl[41] + "Manager.attributes->includes('husband') and ";
    ocl[41] = ocl[41] + "Manager.attributes->includes('wife')";
    ocl[42] = "context Manager inv: Manager.operations->includes('manage')";
    ocl[43] = "context Manager inv: Manager.supertypes->includes(Person)";
    ocl[44] = "context Manager inv: Manager.allSupertypes->includes(Object)";
    ocl[45] = "context Manager inv: Manager.allSupertypes->includes(java::lang::Object)";
    failOcl[40] = "context Manager inv: Manager.allSupertypes->includes(Class)";
    failOcl[41] = "context Manager inv: Manager.supertypes->includes(Class)";

    ocl[60] = "context Bank inv: customers->forAll(true)";
    ocl[61] = "context Bank inv: subnames->forAll(true)";
    ocl[62] = "context Company inv: employees->select(age>40)->size=1";
    ocl[63] = "context Company inv: employees->reject(age>40)->size=employees->size-1";
    ocl[64] = "context Company inv: employees->collect(age)->sum = 134 or employees->collect(age)->sum = 100";
    ocl[65] = "context tudresden::ocl::interp::test::Company inv: employees->collect(age)->sum = 134 or employees->collect(age)->sum = 100";
    ocl[66] = "context tudresden::ocl::interp::test::Manager inv: self.oclIsKindOf(tudresden::ocl::interp::test::Person)";

    // Qualifying over Maps is supported by the Ocl-Libary but does
    // not seem to get through the parser ...
    // ocl[123] = "context Bank inv: Set{1001 .. 1004}->forAll(no|customers[no].name->notEmpty)";
    // Qualifying over Arrays is not supported by the parser
    // ocl[124] = "context Bank inv: subnames[0]='name1'";

    // -- Give Expressions that should fail because of type errors
    oclTypeExp[0] = "context Company inv: Manager.foo = 'Test'";
    oclTypeExp[1] = "context Company inv: Teck.foo = 'Test'";
    oclTypeExp[2] = "context Company inv: self.oclIsTypeOf(Teck)";
    oclTypeExp[3] = "context Company inv: self.oclIsNew";
    oclTypeExp[4] = "context Company inv: throwTestRuntimeExceptionX(nul) = 1";
    oclTypeExp[5] = "context Company::throwTestException() pre: true";

    // oclTypeExp[6] = "context Company inv: self.manager = Company::nul";
    oclTypeExp[7] = "context Company inv: self.manager = Company::nix";
    oclTypeExp[8] = "context Company inv: self.manager = tudresden::ocl::interp::test::Company::nix";
    oclTypeExp[9] = "context Company inv: self::manager = self.manager";

    // -- Give Expressions that should fail because they are not evaluateable

    // -- Give Expressions that should result in a undefined value
    oclUndefExp[0] = "context Company inv: wrongManagerSet->forAll(managedCompanies->size>0)";
    oclUndefExp[1] = "context Company inv: wrongManagerSet->forAll(m|m.oclAsType(Manager).managedCompanies->size>0)";
    oclUndefExp[2] = "context Company inv: wrongManagerSet->forAll(m|m.oclAsType(Company).wrongManagerSet->size>0)";
    oclUndefExp[3] = "context Company inv: wrongManagerSet->forAll(if managedCompanies->size>0 then 2 else 3 endif = 2)";
    oclUndefExp[4] = "context Person inv: wife.age > 10";
    oclUndefExp[5] = "context Person inv: husband.age > 10";
    oclUndefExp[6] = "context Person inv: husband.age > 10";
    oclUndefExp[7] = "context Person inv: wife.sex = true";
    oclUndefExp[8] = "context Person inv: true = wife.sex";
    oclUndefExp[9] = "context Person inv: if wife.sex then true else true endif";
    oclUndefExp[10] = "context Person inv: wife.husband.name = name";
    oclUndefExp[11] = "context Company inv: personReturnNull().age > 0";
    oclUndefExp[12] = "context Company inv: throwTestException() = 1";
    oclUndefExp[13] = "context Company inv: throwTestRuntimeException(nul) = 1";
    oclUndefExp[14] = "context Company inv: Company.allInstances->size < 100";

  }

  public static Test suite() {
    return new TestSuite(TestAddition.class);
  }

  public void testOne() throws Exception {
    int i = 65;
    assertEquals("OclConstraint " + i, true, evaluateTest(ocl[i]));
  }
}