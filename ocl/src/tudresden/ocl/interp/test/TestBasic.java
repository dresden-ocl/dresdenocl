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
 * The Basic Types out of the OCL-Secification 1.4 are checked here.
 * Tests are gouped by the types and for every kind of method there is
 * a test. 
 */
public class TestBasic extends TestEnv {
  public TestBasic(String s) {
    super(s);
  }

  public void initTests() {

    // -- All the tests that do not work

    // -- Check all the basic functions for Integer
    ocl[0] = "context Company inv: 1 = 1";
    ocl[1] = "context Company inv: 2 + 1 = 3";
    ocl[2] = "context Company inv: 2 - (1) = 1";
    ocl[3] = "context Company inv: 2 - 1 = 1";
    ocl[4] = "context Company inv: 2 * -2 = -4";
    ocl[5] = "context Company inv: 4/2 = 2";
    ocl[6] = "context Company inv: (-2).abs = 2";
    ocl[7] = "context Company inv: (-4).div(2)= -2";
    ocl[8] = "context Company inv: (4).mod(3)= 1";
    ocl[9] = "context Company inv: (2).max(3) = 3";
    ocl[10] = "context Company inv: (2).min(3) = 2";

    // -- Check all the basic functions for Real
    ocl[20] = "context Company inv: 1.5 = 1.5";
    ocl[21] = "context Company inv: 1.5 <> 2";
    ocl[22] = "context Company inv: 2.5 + 0.5 = 3";
    ocl[23] = "context Company inv: 2.5 - 0.5 = 2";
    ocl[24] = "context Company inv: 1.5 * 2 = 3";
    ocl[25] = "context Company inv: 1.5/-0.5 = -3";
    ocl[26] = "context Company inv: (-2.6).abs = 2.6";
    ocl[27] = "context Company inv: (2.6).floor = 2";
    ocl[28] = "context Company inv: (2.6).round = 3";
    ocl[29] = "context Company inv: (2.6).max(-2.3) = 2.6";
    ocl[30] = "context Company inv: (2.6).min(2.3) = 2.3";
    ocl[31] = "context Company inv: 2.6 > -2.3";
    ocl[32] = "context Company inv: 2.6 < 2.8";
    ocl[33] = "context Company inv: 2.6 <= 2.6";
    ocl[34] = "context Company inv: 2.6 >= 2.6";

    // -- Check all basic functions for String
    ocl[50] = "context Company inv: 'Hello' = 'Hello'";
    ocl[51] = "context Company inv: ('Hello').size = 5";
    ocl[52] = "context Company inv: 'HellO'.concat(' World') = 'HellO World'";
    ocl[53] = "context Company inv: 'HellO'.toUpper = 'HELLO'";
    ocl[54] = "context Company inv: 'HellO'.toLower = 'hello'";
    ocl[55] = "context Company inv: 'HellO'.substring(2,3) = 'el'";

    // -- Check all basic functions for Boolean
    ocl[70] = "context Company inv: false = false";
    ocl[71] = "context Company inv: (false or true) and not (false or false)";
    ocl[72] = "context Company inv: (true xor false) and not (true xor true)";
    ocl[73] = "context Company inv: not (true and false) and (true and true)";
    ocl[74] = "context Company inv: not false";
    ocl[75] = "context Company inv: (false implies true) and (true implies true)";
    ocl[76] = "context Company inv: not (true and false) and (true and true)";
    ocl[77] = "context Company inv: if false then 3 else 4 endif = 4";
    ocl[78] = "context Company inv: if true then 3 else 4 endif = 3";
    ocl[79] = "context Company inv: not (true and false and true)";
    ocl[80] = "context Company inv: false or true or false";
    ocl[81] = "context Company inv: (true and false) implies (false and true)";
    ocl[82] = "context Company inv: false and true implies true and false";

    // -- Check all basic functions for Collection
    ocl[90] = "context Company inv: Set{-1,3,5}->size = 3";
    ocl[91] = "context Company inv: Set{1,3,5}->includes(5)";
    ocl[92] = "context Company inv: Set{1,3,5}->excludes(4)";
    ocl[93] = "context Company inv: Set{1,3,5}->count(1) = 1";
    ocl[94] = "context Company inv: Set{1,3,5}->includesAll(Bag{1,3})";
    ocl[95] = "context Company inv: Set{1,3,5}->excludesAll(Sequence{2,4,6,6})";
    ocl[96] = "context Company inv: not Set{1,3,5}->isEmpty and (Set{}->isEmpty)";
    ocl[97] = "context Company inv: Set{1,3,5}->notEmpty and (not Set{}->notEmpty)";
    ocl[98] = "context Company inv: Set{1,3,5}->sum = 9";
    ocl[99] = "context Company inv: Set{1,-3,5}->exists(abs = 3)";
    ocl[100] = "context Company inv: Set{1,3,5}->forAll(abs > 0)";
    ocl[101] = "context Company inv: not Set{1,3,5}->forAll(abs > 100)";
    ocl[102] = "context Company inv: Set{1,3,5}->isUnique(abs)";
    ocl[103] = "context Company inv: not Set{-1,1,-3,5}->isUnique(abs)";
    ocl[104] = "context Company inv: Set{-3,1,5}->sortedBy(abs) = Sequence{1,-3,5}";
    ocl[105] = "context Company inv: Set{1,3,5}->iterate(i:Integer;acc:Boolean=true|acc and i.abs > 0)";
    ocl[106] = "context Company inv: not Set{1,3,5}->iterate(i:Integer;acc:Boolean=false|acc or i.abs < 0)";
    ocl[107] = "context Company inv: Set{1,3,5}->iterate(i:Integer;acc:Integer=0|acc+i) = Set{1,3,5}->sum";
    ocl[108] = "context Company inv: Set{1 .. 5} = Set{1,2,3,5,4}";

    // -- Check all basic functions for Set
    ocl[116] = "context Company inv: Set{1,3,5}->union(Set{5,7}) = Set{1,3,5,7}";
    ocl[117] = "context Company inv: Set{1,3,5}->union(Bag{5,7})->count(5) = 2";
    ocl[118] = "context Company inv: Set{1,3,5} = Set{1,5,3}";
    ocl[119] = "context Company inv: Set{1,3,5}->intersection(Set{3,5,7}) = Set{5,3}";
    ocl[120] = "context Company inv: Set{1,3,5}->intersection(Bag{3,3,5,7}) = Set{5,3}";
    ocl[121] = "context Company inv: Set{1,3,5} - Set{5} = Set{3,1}";
    ocl[122] = "context Company inv: Set{1,3,5}->symmetricDifference(Set{5,7}) = Set{1,3,7}";
    ocl[123] = "context Company inv: Set{1,3,5}->asSequence->asSet = Set{1,3,5}";
    ocl[124] = "context Company inv: Set{1,3,5}->asBag->asSet = Set{1,3,5}";

    // -- Check all basic functions for Bag
    ocl[130] = "context Company inv: Bag{1,3,3} = Bag{3,3,1}";
    ocl[131] = "context Company inv: Bag{1,3,3}->union(Bag{3}) = Bag{3,3,3,1}";
    ocl[132] = "context Company inv: Bag{1,3,3}->union(Set{3}) = Bag{3,3,3,1}";
    ocl[133] = "context Company inv: Bag{1,3,3,3}->intersection(Bag{3,3,1}) = Bag{1,3,3}";
    ocl[134] = "context Company inv: Bag{1,3,3,3}->intersection(Set{3,3,1}) = Set{1,3}";
    ocl[135] = "context Company inv: Bag{1,3,3}->asSet->asBag = Bag{1,3}";
    ocl[135] = "context Company inv: Bag{1,3,3}->asSequence->asBag = Bag{1,3,3}";

    // -- Check all basic functions for Sequence
    ocl[150] = "context Company inv: Sequence{1,3,3,5}->count(3) = 2";
    ocl[151] = "context Company inv: Sequence{1,3,3,5} = Sequence{1,3,3,5}";
    ocl[152] = "context Company inv: not (Sequence{1,3,3,5} = Sequence{3,1,3,5})";
    ocl[153] = "context Company inv: Sequence{1,3,3,5}->union(Sequence{3,5}) = Sequence{1,3,3,5,3,5}";
    ocl[154] = "context Company inv: Sequence{1,3,3,5}->append(5) = Sequence{1,3,3,5,5}";
    ocl[155] = "context Company inv: Sequence{1,3,3,5}->prepend(5) = Sequence{5,1,3,3,5}";
    ocl[156] = "context Company inv: Sequence{1,3,3,5}->subSequence(2,3) = Sequence{3,3}";
    ocl[157] = "context Company inv: Sequence{1,3,3,5}->at(2)= 3";
    ocl[158] = "context Company inv: Sequence{1,3,3,5}->first = 1";
    ocl[159] = "context Company inv: Sequence{1,3,3,5}->last = 5";
    ocl[160] = "context Company inv: Sequence{1,3,3,5}->excluding(3) = Sequence{1,5}";
    ocl[161] = "context Company inv: Sequence{1,3,3,5}->asBag = Bag{1,3,3,5}";
    ocl[162] = "context Company inv: Sequence{1,3,3,5}->asSet = Set{1,3,5}";

    failOcl[0] = "context Company inv: 1 = 2";
    failOcl[1] = "context Company inv: 2 + 1 = 4";
    failOcl[2] = "context Company inv: 2 - (1) = 0";
    failOcl[3] = "context Company inv: 2 - 1 = 0";
    failOcl[4] = "context Company inv: 2 * -2 = 4";
    failOcl[5] = "context Company inv: 4/2 = 9";
    failOcl[6] = "context Company inv: (-2).abs = -2";
    failOcl[7] = "context Company inv: (-4).div(2)= 0";
    failOcl[8] = "context Company inv: (4).mod(3)= 0";
    failOcl[9] = "context Company inv: (2).max(3) = 2";
    failOcl[10] = "context Company inv: (2).min(3) = 3";

    // -- Check all the basic functions for Real
    failOcl[20] = "context Company inv: 1.5 = 1.51";
    failOcl[21] = "context Company inv: 1.5 <> 1.5";
    failOcl[22] = "context Company inv: 2.5 + 0.5 = 3.5";
    failOcl[23] = "context Company inv: 2.5 - 0.5 = 2.5";
    failOcl[24] = "context Company inv: 1.5 * 2 = 4";
    failOcl[25] = "context Company inv: 1.5/-0.5 = 3";
    failOcl[26] = "context Company inv: (-2.6).abs = -2.6";
    failOcl[27] = "context Company inv: (2.6).floor = 3";
    failOcl[28] = "context Company inv: (2.6).round = 2";
    failOcl[29] = "context Company inv: (2.6).max(-2.3) = 2.3";
    failOcl[30] = "context Company inv: (2.6).min(2.3) = 2.6";
    failOcl[31] = "context Company inv: 2.6 < -2.3";
    failOcl[32] = "context Company inv: 2.6 > 2.8";
    failOcl[33] = "context Company inv: 2.6 < 2.6";
    failOcl[34] = "context Company inv: 2.6 > 2.6";

    // -- Check all basic functions for String
    failOcl[50] = "context Company inv: 'Hello' = 'Helloc'";
    failOcl[51] = "context Company inv: ('Hello').size = 51";
    failOcl[52] = "context Company inv: 'HellO'.concat('World') = 'HellO World'";
    failOcl[53] = "context Company inv: 'HellO'.toUpper = 'HEbLLO'";
    failOcl[54] = "context Company inv: 'HellO'.toLower = 'hebllo'";
    failOcl[55] = "context Company inv: 'HebllO'.substring(2,3) = 'el'";

    // -- Check all basic functions for Boolean
    failOcl[70] = "context Company inv: false = true";
    failOcl[71] = "context Company inv: (false or false) and not (false or false)";
    failOcl[72] = "context Company inv: (true xor true) and not (true xor true)";
    failOcl[73] = "context Company inv: (true and false) and (true and true)";
    failOcl[74] = "context Company inv: not true";
    failOcl[75] = "context Company inv: (false implies true) and (true implies false)";
    failOcl[76] = "context Company inv: (true and false) and (true and true)";
    failOcl[77] = "context Company inv: if false then 3 else 4 endif = 3";
    failOcl[78] = "context Company inv: if true then 3 else 4 endif = 4";

    // -- Check all basic functions for Collection
    failOcl[90] = "context Company inv: Set{1,3,5,5}->size = 4";
    failOcl[91] = "context Company inv: Set{1,3,5}->includes(2)";
    failOcl[92] = "context Company inv: Set{1,3,5}->excludes(3)";
    failOcl[93] = "context Company inv: Set{1,3,5,2,1}->count(1) = 2";
    failOcl[94] = "context Company inv: Set{1,3,5}->includesAll(Bag{1,3,6})";
    failOcl[95] = "context Company inv: Set{1,3,5}->excludesAll(Sequence{1,3,3,3})";
    failOcl[96] = "context Company inv: not Set{1,3,5}->isEmpty and (Set{1}->isEmpty)";
    failOcl[97] = "context Company inv: Set{}->notEmpty and (not Set{1}->notEmpty)";
    failOcl[98] = "context Company inv: Set{1,3,5}->sum = 10";
    failOcl[99] = "context Company inv: Set{1,-3,5}->exists(abs = -3)";
    failOcl[100] = "context Company inv: Set{1,3,5}->forAll(abs < 0)";
    failOcl[101] = "context Company inv: Set{1,3,5}->forAll(abs > 100)";
    failOcl[102] = "context Company inv: not Bag{1,3,5}->isUnique(abs)";
    failOcl[103] = "context Company inv: Set{-1,1,-3,5}->isUnique(abs)";
    failOcl[104] = "context Company inv: Set{-3,1,5}->sortedBy(abs) = Sequence{-3,1,5}";
    failOcl[105] = "context Company inv: not Set{1,3,5}->iterate(i:Integer;acc:Boolean=true|acc and i.abs > 0)";
    failOcl[106] = "context Company inv: Set{1,3,5}->iterate(i:Integer;acc:Boolean=false|acc or i.abs < 0)";
    failOcl[107] = "context Company inv: Set{1,3,5}->iterate(i:Integer;acc:Integer=0|acc+i) = Set{1,2,3,5}->sum";

    // -- Check all basic functions for Set
    failOcl[116] = "context Company inv: Set{1,3,5}->union(Set{5,7}) = Set{1,3,5,7,8}";
    failOcl[117] = "context Company inv: Set{1,3,5}->union(Bag{5,7})->count(5) = 1";
    failOcl[118] = "context Company inv: Sequence{1,3,5} = Sequence{1,5,3}";
    failOcl[119] = "context Company inv: Set{1,3,5}->intersection(Set{3,5,7}) = Set{1,5,3}";
    failOcl[120] = "context Company inv: Set{1,3,5}->intersection(Bag{3,3,5,7}) = Set{1,5,3}";
    failOcl[121] = "context Company inv: Set{1,3,5} - Set{5} = Set{3,1,5}";
    failOcl[122] = "context Company inv: Set{1,3,5}->symmetricDifference(Set{5,7}) = Set{1,3,7,5}";
    failOcl[123] = "context Company inv: Set{1,3,5,5}->asSequence->asSet = Set{1,3}";
    failOcl[124] = "context Company inv: Set{1,3,5}->asBag->asSet = Set{1,5}";

    // -- Check all basic functions for Bag
    failOcl[130] = "context Company inv: Bag{1,3,3} = Bag{3,3,1,1}";
    failOcl[131] = "context Company inv: Bag{1,3,3}->union(Bag{3}) = Bag{3,3,1}";
    failOcl[132] = "context Company inv: Bag{1,3,3}->union(Set{3}) = Bag{3,3,1}";
    failOcl[133] = "context Company inv: Bag{1,3,3,3}->intersection(Bag{3,3,1}) = Bag{1,3,3,3}";
    failOcl[134] = "context Company inv: Bag{1,3,3,3}->intersection(Set{3,3,1}) = Set{1,2,3}";
    failOcl[135] = "context Company inv: Bag{1,3,3}->asSet->asBag = Bag{1,3,3}";
    failOcl[135] = "context Company inv: Bag{1,3,3}->asSequence->asBag = Bag{1,3,3,3}";

    // -- Check all basic functions for Sequence
    failOcl[150] = "context Company inv: Sequence{1,3,3,3,5}->count(3) = 2";
    failOcl[151] = "context Company inv: Sequence{1,3,3,5} = Sequence{3,1,3,5}";
    failOcl[152] = "context Company inv: Sequence{1,3,3,5} = Sequence{3,1,3,5}";
    failOcl[153] = "context Company inv: Sequence{1,3,3,5}->union(Sequence{3,5}) = Sequence{5,3,1,3,3,5}";
    failOcl[154] = "context Company inv: Sequence{1,3,3,5}->append(5) = Sequence{5,1,3,3,5}";
    failOcl[155] = "context Company inv: Sequence{1,3,3,5}->prepend(5) = Sequence{1,3,3,5,5}";
    failOcl[156] = "context Company inv: Sequence{1,3,3,5}->subSequence(3,4) = Sequence{3,3}";
    failOcl[157] = "context Company inv: Sequence{1,3,3,5}->at(1)= 3";
    failOcl[158] = "context Company inv: Sequence{1,3,3,5}->first = 3";
    failOcl[159] = "context Company inv: Sequence{1,3,3,5}->last = 3";
    failOcl[160] = "context Company inv: Sequence{1,3,3,5}->excluding(3) = Sequence{1,3,5}";
    failOcl[161] = "context Company inv: Sequence{1,3,3,5}->asBag = Bag{1,3,3,5,6}";
    failOcl[162] = "context Company inv: Sequence{1,3,3,5}->asSet = Set{1,3,5,6}";

    // -- Give Expressions that should fail because of type errors
    oclTypeExp[0] = "context Company inv: 'Hallo'.foo = 1";
    oclTypeExp[1] = "context Company inv: (1).foo = 1";
    oclTypeExp[2] = "context Company inv: true.foo = 1";
    oclTypeExp[3] = "context Company inv: Set{1,3,5}.foo = 1";
    oclTypeExp[4] = "context Company inv: Bag{1,3,3}.foo = 1";
    oclTypeExp[5] = "context Company inv: Sequence{1,3,3,5}.foo = 1";
    oclTypeExp[6] = "context Company inv: Sequence{}->at(1) = 1";

    // -- Give Expressions that should fail because they are not evaluateable
    oclEvalExp[0] = "context Company inv: 1/0 = 1";
    oclEvalExp[1] = "context Company inv: (1).div(0) = 1";
    oclEvalExp[2] = "context Company inv: 'Hallo'.substring(100,1001) = ''";
    oclEvalExp[3] = "context Company inv: Sequence{1,3,3,5}->at(7) = 1";
    oclEvalExp[4] = "context Company inv: Sequence{1,3,3,5}->at(-1) = 1";
    oclEvalExp[5] = "context Company inv: Sequence{1,3,3,5}->at(0) = 1";
  }

  public static Test suite() {
    return new TestSuite(TestBasic.class);
  }

  public void setUp() throws Exception {
    super.setUp();
  }

  public void tearDown() throws Exception {
    super.tearDown();
  }

  public void testOne() throws Exception {
    int i = 82;
    assertEquals("OclConstraint " + i, true, evaluateTest(ocl[i]));
  }
}