/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
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
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package tudresden.ocl.test;

import test.framework.*;
import tudresden.ocl.codegen.*;
import tudresden.ocl.OclTree;
import tudresden.ocl.NameCreator;
import java.io.*;
import java.util.StringTokenizer;

public class TestJavaGenerator extends TestCase {

  static StringBuffer javaFile=new StringBuffer();
  static int index=0;

  public TestJavaGenerator(String n) {
    super(n);
  }

  public void testLet() {
    testGenerate(
      "context Person inv: let a=age in a>0",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclInteger tudOclNode1=Ocl.toOclInteger(tudOclNode0.getFeature(\"age\"));\n"+
      "final OclInteger tudOclNode2=new OclInteger(0);\n"+
      "final OclBoolean tudOclNode3=tudOclNode1.isGreaterThan(tudOclNode2);"
    );
    testGenerate(
      "context Person inv: let a=age in let b=a in b>0",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclInteger tudOclNode1=Ocl.toOclInteger(tudOclNode0.getFeature(\"age\"));\n"+
      "final OclInteger tudOclNode2=new OclInteger(0);\n"+
      "final OclBoolean tudOclNode3=tudOclNode1.isGreaterThan(tudOclNode2);"
    );
    testGenerate(
      "context Company inv: let var1=manager.age in let var2=var1+1 in var2>var1",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclAnyImpl tudOclNode1=Ocl.toOclAnyImpl(tudOclNode0.getFeature(\"manager\"));\n"+
      "final OclInteger tudOclNode2=Ocl.toOclInteger(tudOclNode1.getFeature(\"age\"));\n"+
      "final OclInteger tudOclNode3=new OclInteger(1);\n"+
      "final OclInteger tudOclNode4=tudOclNode2.add(tudOclNode3);\n"+
      "final OclBoolean tudOclNode5=tudOclNode4.isGreaterThan(tudOclNode2);\n"
    );
  }

  public void testColLiteral() {
    testGenerate(
      "context Person inv: Set{ 0 }->includes(age)",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=OclSet.getEmptyOclSet();\n"+
      "final OclInteger tudOclNode2=new OclInteger(0);\n"+
      "tudOclNode1.setToInclude(tudOclNode2);\n"+
      "final OclInteger tudOclNode3=Ocl.toOclInteger(tudOclNode0.getFeature(\"age\"));\n"+
      "final OclBoolean tudOclNode4=tudOclNode1.includes(tudOclNode3);\n"
    );
    testGenerate(
      "context Person inv: Set{ 0 .. 199 }->includes(age)",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=OclSet.getEmptyOclSet();\n"+
      "final OclInteger tudOclNode2=new OclInteger(0);\n"+
      "final OclInteger tudOclNode3=new OclInteger(199);\n"+
      "tudOclNode1.setToRange(tudOclNode2, tudOclNode3);\n"+
      "final OclInteger tudOclNode4=Ocl.toOclInteger(tudOclNode0.getFeature(\"age\"));\n"+
      "final OclBoolean tudOclNode5=tudOclNode1.includes(tudOclNode4);\n"
    );
    testGenerate(
      "context Person inv: Set{ 0, 1, 2, 3, 4 }->includes(age)",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=OclSet.getEmptyOclSet();\n"+
      "final OclInteger tudOclNode2=new OclInteger(0);\n"+
      "final OclInteger tudOclNode3=new OclInteger(1);\n"+
      "final OclInteger tudOclNode4=new OclInteger(2);\n"+
      "final OclInteger tudOclNode5=new OclInteger(3);\n"+
      "final OclInteger tudOclNode6=new OclInteger(4);\n"+
      "tudOclNode1.setToInclude(tudOclNode2);\n"+
      "tudOclNode1.setToInclude(tudOclNode3);\n"+
      "tudOclNode1.setToInclude(tudOclNode4);\n"+
      "tudOclNode1.setToInclude(tudOclNode5);\n"+
      "tudOclNode1.setToInclude(tudOclNode6);\n"+
      "final OclInteger tudOclNode7=Ocl.toOclInteger(tudOclNode0.getFeature(\"age\"));\n"+
      "final OclBoolean tudOclNode8=tudOclNode1.includes(tudOclNode7);\n"
    );
  }

  public void testPostfix() {
    // postfix property of basic types
    testGenerate(
      "context Person inv: (-1). abs=1",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclInteger tudOclNode1=new OclInteger(1);\n"+
      "final OclInteger tudOclNode2=Ocl.toOclInteger(tudOclNode1.negative());\n"+
      "final OclInteger tudOclNode3=Ocl.toOclInteger(tudOclNode2.abs());\n"+
      "final OclInteger tudOclNode4=new OclInteger(1);\n"+
      "final OclBoolean tudOclNode5=tudOclNode3.isEqualTo(tudOclNode4);\n"
    );
    // basic type, with parameters
    testGenerate(
      "context Person inv: let firstLetter=self.name.substring(1,1) in "+
      "firstLetter=firstLetter.toUpper",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclString tudOclNode1=Ocl.toOclString(tudOclNode0.getFeature(\"name\"));\n"+
      "final OclInteger tudOclNode2=new OclInteger(1);\n"+
      "final OclInteger tudOclNode3=new OclInteger(1);\n"+
      "final OclString tudOclNode4=tudOclNode1.substring(tudOclNode2, tudOclNode3);\n"+
      "final OclString tudOclNode5=tudOclNode4.toUpper();\n"+
      "final OclBoolean tudOclNode6=tudOclNode4.isEqualTo(tudOclNode5);\n"
    );
    // operation of application type, without parameters
    testGenerate(
      "context Company inv: getOldestEmployeeAge()<manager.age+20",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclInteger tudOclNode1=Ocl.toOclInteger(tudOclNode0.getFeature(\"getOldestEmployeeAge\", null));\n"+
      "final OclAnyImpl tudOclNode2=Ocl.toOclAnyImpl(tudOclNode0.getFeature(\"manager\"));\n"+
      "final OclInteger tudOclNode3=Ocl.toOclInteger(tudOclNode2.getFeature(\"age\"));\n"+
      "final OclInteger tudOclNode4=new OclInteger(20);\n"+
      "final OclInteger tudOclNode5=tudOclNode3.add(tudOclNode4);\n"+
      "final OclBoolean tudOclNode6=tudOclNode1.isLessThan(tudOclNode5);\n"
    );
    // operation of application type, with parameters
    testGenerate(
      "context Person inv: getIncomeAfterTax(0.3)<=getIncomeAfterTax(0)",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclReal tudOclNode1=new OclReal(0.3);\n"+
      "Object[] tudOclParam0=new Object[1];\n"+
      "tudOclParam0[0]=Ocl.reconvert(null, tudOclNode1);\n"+
      "final OclReal tudOclNode2=Ocl.toOclReal(tudOclNode0.getFeature(\"getIncomeAfterTax\", tudOclParam0));\n"+
      "final OclInteger tudOclNode3=new OclInteger(0);\n"+
      "Object[] tudOclParam1=new Object[1];\n"+
      "tudOclParam1[0]=Ocl.reconvert(null, tudOclNode3);\n"+
      "final OclReal tudOclNode4=Ocl.toOclReal(tudOclNode0.getFeature(\"getIncomeAfterTax\", tudOclParam1));\n"+
      "final OclBoolean tudOclNode5=tudOclNode2.isLessEqual(tudOclNode4);\n"
    );
    // iterating methods are tested in testIterate
  }

  public void testIterate() {
    // forAll
    testGenerate(
      "context Company inv: employees->forAll(not isUnemployed)",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"employees\"));\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclBooleanEvaluatable tudOclEval0=new OclBooleanEvaluatable() {\n"+
      "  public OclBoolean evaluate() {\n"+
      "    final OclBoolean tudOclNode2=Ocl.toOclBoolean(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"isUnemployed\"));\n"+
      "    final OclBoolean tudOclNode3=tudOclNode2.not();\n"+
      "    return tudOclNode3;\n"+
      "  }\n"+
      "};\n"+
      "final OclBoolean tudOclNode4=tudOclNode1.forAll(tudOclIter0, tudOclEval0);\n"
    );
    // exists
    testGenerate(
      "context Company inv: employees->exists(p|p=self.manager)",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"employees\"));\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclBooleanEvaluatable tudOclEval0=new OclBooleanEvaluatable() {\n"+
      "  public OclBoolean evaluate() {\n"+
      "    final OclAnyImpl tudOclNode2=Ocl.toOclAnyImpl(tudOclNode0.getFeature(\"manager\"));\n"+
      "    final OclBoolean tudOclNode3=Ocl.toOclAnyImpl(tudOclIter0.getValue()).isEqualTo(tudOclNode2);\n"+
      "    return tudOclNode3;\n"+
      "  }\n"+
      "};\n"+
      "final OclBoolean tudOclNode4=tudOclNode1.exists(tudOclIter0, tudOclEval0);\n"
    );
    // Iterator with type Integer
    testGenerate(
      "context Person inv: Set{1, 2, 3}->forAll(i|(i+1)>1)",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=OclSet.getEmptyOclSet();\n"+
      "final OclInteger tudOclNode2=new OclInteger(1);\n"+
      "final OclInteger tudOclNode3=new OclInteger(2);\n"+
      "final OclInteger tudOclNode4=new OclInteger(3);\n"+
      "tudOclNode1.setToInclude(tudOclNode2);\n"+
      "tudOclNode1.setToInclude(tudOclNode3);\n"+
      "tudOclNode1.setToInclude(tudOclNode4);\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclBooleanEvaluatable tudOclEval0=new OclBooleanEvaluatable() {\n"+
      "  public OclBoolean evaluate() {\n"+
      "    final OclInteger tudOclNode5=new OclInteger(1);\n"+
      "    final OclInteger tudOclNode6=Ocl.toOclInteger(tudOclIter0.getValue()).add(tudOclNode5);\n"+
      "    final OclInteger tudOclNode7=new OclInteger(1);\n"+
      "    final OclBoolean tudOclNode8=tudOclNode6.isGreaterThan(tudOclNode7);\n"+
      "    return tudOclNode8;\n"+
      "  }\n"+
      "};\n"+
      "final OclBoolean tudOclNode9=tudOclNode1.forAll(tudOclIter0, tudOclEval0);\n"
    );
    // isUnique
    testGenerate(
      "context Company inv: employees->isUnique(name)",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"employees\"));\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclRootEvaluatable tudOclEval0=new OclRootEvaluatable() {\n"+
      "  public OclRoot evaluate() {\n"+
      "    final OclString tudOclNode2=Ocl.toOclString(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"name\"));\n"+
      "    return tudOclNode2;\n"+
      "  }\n"+
      "};\n"+
      "final OclBoolean tudOclNode3=tudOclNode1.isUnique(tudOclIter0, tudOclEval0);\n"
    );
    // sortedBy
    testGenerate(
      "context Person inv: managedCompanies->sortedBy(numberOfEmployees)->first.numberOfEmployees>100",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"managedCompanies\"));\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclComparableEvaluatable tudOclEval0=new OclComparableEvaluatable() {\n"+
      "  public Comparable evaluate() {\n"+
      "    final OclInteger tudOclNode2=Ocl.toOclInteger(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"numberOfEmployees\"));\n"+
      "    return tudOclNode2;\n"+
      "  }\n"+
      "};\n"+
      "final OclSequence tudOclNode3=tudOclNode1.sortedBy(tudOclIter0, tudOclEval0);\n"+
      "final OclAnyImpl tudOclNode4=Ocl.toOclAnyImpl(tudOclNode3.first());\n"+
      "final OclInteger tudOclNode5=Ocl.toOclInteger(tudOclNode4.getFeature(\"numberOfEmployees\"));\n"+
      "final OclInteger tudOclNode6=new OclInteger(100);\n"+
      "final OclBoolean tudOclNode7=tudOclNode5.isGreaterThan(tudOclNode6);\n"
    );
    // selected
    testGenerate(
      "context Customer inv: cards->select(color=color_gold)->notEmpty implies title<>''",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"cards\"));\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclBooleanEvaluatable tudOclEval0=new OclBooleanEvaluatable() {\n"+
      "  public OclBoolean evaluate() {\n"+
      "    final OclInteger tudOclNode2=Ocl.toOclInteger(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"color\"));\n"+
      "    final OclInteger tudOclNode3=Ocl.toOclInteger(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"color_gold\"));\n"+
      "    final OclBoolean tudOclNode4=tudOclNode2.isEqualTo(tudOclNode3);\n"+
      "    return tudOclNode4;\n"+
      "  }\n"+
      "};\n"+
      "final OclSet tudOclNode5=Ocl.toOclSet(tudOclNode1.select(tudOclIter0, tudOclEval0));\n"+
      "final OclBoolean tudOclNode6=tudOclNode5.notEmpty();\n"+
      "final OclString tudOclNode7=Ocl.toOclString(tudOclNode0.getFeature(\"title\"));\n"+
      "final OclString tudOclNode8=new OclString(\"\");\n"+
      "final OclBoolean tudOclNode9=tudOclNode7.isNotEqualTo(tudOclNode8);\n"+
      "final OclBoolean tudOclNode10=tudOclNode6.implies(tudOclNode9);\n"
    );
    // reject
    testGenerate(
      "context Company inv: employees->reject(isUnemployed) = employees",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"employees\"));\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclBooleanEvaluatable tudOclEval0=new OclBooleanEvaluatable() {\n"+
      "  public OclBoolean evaluate() {\n"+
      "    final OclBoolean tudOclNode2=Ocl.toOclBoolean(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"isUnemployed\"));\n"+
      "    return tudOclNode2;\n"+
      "  }\n"+
      "};\n"+
      "final OclSet tudOclNode3=Ocl.toOclSet(tudOclNode1.reject(tudOclIter0, tudOclEval0));\n"+
      "final OclSet tudOclNode4=Ocl.toOclSet(tudOclNode0.getFeature(\"employees\"));\n"+
      "final OclBoolean tudOclNode5=tudOclNode3.isEqualTo(tudOclNode4);\n"
    );
    // collect collection
    testGenerate(
      "context Person inv: managedCompanies->collect(employees)->includes(self)",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"managedCompanies\"));\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclRootEvaluatable tudOclEval0=new OclRootEvaluatable() {\n"+
      "  public OclRoot evaluate() {\n"+
      "    final OclSet tudOclNode2=Ocl.toOclSet(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"employees\"));\n"+
      "    return tudOclNode2;\n"+
      "  }\n"+
      "};\n"+
      "final OclBag tudOclNode3=Ocl.toOclBag(tudOclNode1.collect(tudOclIter0, tudOclEval0));\n"+
      "final OclBoolean tudOclNode4=tudOclNode3.includes(tudOclNode0);\n"
    );
    // collect basic value
    testGenerate(
      "context Company inv: employees->collect(age)->sum > 30",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"employees\"));\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclRootEvaluatable tudOclEval0=new OclRootEvaluatable() {\n"+
      "  public OclRoot evaluate() {\n"+
      "    final OclInteger tudOclNode2=Ocl.toOclInteger(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"age\"));\n"+
      "    return tudOclNode2;\n"+
      "  }\n"+
      "};\n"+
      "final OclBag tudOclNode3=Ocl.toOclBag(tudOclNode1.collect(tudOclIter0, tudOclEval0));\n"+
      "final OclInteger tudOclNode4=Ocl.toOclInteger(tudOclNode3.sum());\n"+
      "final OclInteger tudOclNode5=new OclInteger(30);\n"+
      "final OclBoolean tudOclNode6=tudOclNode4.isGreaterThan(tudOclNode5);\n"
    );
    // iterate
    testGenerate(
      "context Company inv: employees->iterate(p:Person; i:Integer=0 | i+p.age)>30",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"employees\"));\n"+
      "final OclInteger tudOclNode2=new OclInteger(0);\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclContainer tudOclAccu0=new OclContainer(tudOclNode2);\n"+
      "final OclRootEvaluatable tudOclEval0=new OclRootEvaluatable() {\n"+
      "  public OclRoot evaluate() {\n"+
      "    final OclInteger tudOclNode3=Ocl.toOclInteger(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"age\"));\n"+
      "    final OclInteger tudOclNode4=Ocl.toOclInteger(tudOclAccu0.getValue()).add(tudOclNode3);\n"+
      "    return tudOclNode4;\n"+
      "  }\n"+
      "};\n"+
      "final OclInteger tudOclNode5=Ocl.toOclInteger(tudOclNode1.iterate(tudOclIter0, tudOclAccu0, tudOclEval0));\n"+
      "final OclInteger tudOclNode6=new OclInteger(30);\n"+
      "final OclBoolean tudOclNode7=tudOclNode5.isGreaterThan(tudOclNode6);\n"
    );
    // twice iterate
    testGenerate(
      "context ProgramPartner inv: \n"+
      "self.deliveredServices.transactions->iterate(\n"+
      "  t:Transaction; result:Integer=0 |\n"+
      "  if t.oclIsTypeOf(Burning) then result+t.points else result endif\n"+
      ")\n"+
      ">=\n"+
      "self.deliveredServices.transactions->iterate(\n"+
      "  t:Transaction; result:Integer=0 |\n"+
      "  if t.oclIsTypeOf(Earning) then result+t.points else result endif\n"+
      ")\n",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"deliveredServices\"));\n"+
      "final OclBag tudOclNode2=Ocl.toOclBag(tudOclNode1.getFeature(\"transactions\"));\n"+
      "final OclInteger tudOclNode3=new OclInteger(0);\n"+
      "final OclIterator tudOclIter1=tudOclNode2.getIterator();\n"+
      "final OclContainer tudOclAccu1=new OclContainer(tudOclNode3);\n"+
      "final OclRootEvaluatable tudOclEval0=new OclRootEvaluatable() {\n"+
      "  public OclRoot evaluate() {\n"+
      "    final OclType tudOclNode4=OclType.getOclTypeFor(this, \"Burning\");\n"+
      "    final OclBoolean tudOclNode5=Ocl.toOclAnyImpl(tudOclIter1.getValue()).oclIsTypeOf(tudOclNode4);\n"+
      "    final OclInteger tudOclNode6=Ocl.toOclInteger(Ocl.toOclAnyImpl(tudOclIter1.getValue()).getFeature(\"points\"));\n"+
      "    final OclInteger tudOclNode7=Ocl.toOclInteger(tudOclAccu1.getValue()).add(tudOclNode6);\n"+
      "    final OclInteger tudOclNode8=(tudOclNode5.isTrue()) ? (tudOclNode7) : (Ocl.toOclInteger(tudOclAccu1.getValue()));\n"+
      "    return tudOclNode8;\n"+
      "  }\n"+
      "};\n"+
      "final OclInteger tudOclNode9=Ocl.toOclInteger(tudOclNode2.iterate(tudOclIter1, tudOclAccu1, tudOclEval0));\n"+
      "final OclSet tudOclNode10=Ocl.toOclSet(tudOclNode0.getFeature(\"deliveredServices\"));\n"+
      "final OclBag tudOclNode11=Ocl.toOclBag(tudOclNode10.getFeature(\"transactions\"));\n"+
      "final OclInteger tudOclNode12=new OclInteger(0);\n"+
      "final OclIterator tudOclIter0=tudOclNode11.getIterator();\n"+
      "final OclContainer tudOclAccu2=new OclContainer(tudOclNode12);\n"+
      "final OclRootEvaluatable tudOclEval1=new OclRootEvaluatable() {\n"+
      "  public OclRoot evaluate() {\n"+
      "    final OclType tudOclNode13=OclType.getOclTypeFor(this, \"Earning\");\n"+
      "    final OclBoolean tudOclNode14=Ocl.toOclAnyImpl(tudOclIter0.getValue()).oclIsTypeOf(tudOclNode13);\n"+
      "    final OclInteger tudOclNode15=Ocl.toOclInteger(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"points\"));\n"+
      "    final OclInteger tudOclNode16=Ocl.toOclInteger(tudOclAccu2.getValue()).add(tudOclNode15);\n"+
      "    final OclInteger tudOclNode17=(tudOclNode14.isTrue()) ? (tudOclNode16) : (Ocl.toOclInteger(tudOclAccu2.getValue()));\n"+
      "    return tudOclNode17;\n"+
      "  }\n"+
      "};\n"+
      "final OclInteger tudOclNode18=Ocl.toOclInteger(tudOclNode11.iterate(tudOclIter0, tudOclAccu2, tudOclEval1));\n"+
      "final OclBoolean tudOclNode19=tudOclNode9.isGreaterEqual(tudOclNode18);\n"
    );

    // double: forAll, exists
    testGenerate(
      "context Person inv: self.employers->forAll(c|c.employees->exists(p|p=self)) or self.isUnemployed",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"employers\"));\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclBooleanEvaluatable tudOclEval0=new OclBooleanEvaluatable() {\n"+
      "  public OclBoolean evaluate() {\n"+
      "    final OclSet tudOclNode2=Ocl.toOclSet(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"employees\"));\n"+
      "    final OclIterator tudOclIter1=tudOclNode2.getIterator();\n"+
      "    final OclBooleanEvaluatable tudOclEval1=new OclBooleanEvaluatable() {\n"+
      "      public OclBoolean evaluate() {\n"+
      "        final OclBoolean tudOclNode3=Ocl.toOclAnyImpl(tudOclIter1.getValue()).isEqualTo(tudOclNode0);\n"+
      "        return tudOclNode3;\n"+
      "      }\n"+
      "    };\n"+
      "    final OclBoolean tudOclNode4=tudOclNode2.exists(tudOclIter1, tudOclEval1);\n"+
      "    return tudOclNode4;\n"+
      "  }\n"+
      "};\n"+
      "final OclBoolean tudOclNode5=tudOclNode1.forAll(tudOclIter0, tudOclEval0);\n"+
      "final OclBoolean tudOclNode6=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isUnemployed\"));\n"+
      "final OclBoolean tudOclNode7=tudOclNode5.or(tudOclNode6);\n"
    );
    // double: select, isUnique
    testGenerate(
      "context Company inv: self.employees->select(p:Person|p.age<18)->isUnique(name)",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"employees\"));\n"+
      "final OclIterator tudOclIter1=tudOclNode1.getIterator();\n"+
      "final OclBooleanEvaluatable tudOclEval0=new OclBooleanEvaluatable() {\n"+
      "  public OclBoolean evaluate() {\n"+
      "    final OclInteger tudOclNode2=Ocl.toOclInteger(Ocl.toOclAnyImpl(tudOclIter1.getValue()).getFeature(\"age\"));\n"+
      "    final OclInteger tudOclNode3=new OclInteger(18);\n"+
      "    final OclBoolean tudOclNode4=tudOclNode2.isLessThan(tudOclNode3);\n"+
      "    return tudOclNode4;\n"+
      "  }\n"+
      "};\n"+
      "final OclSet tudOclNode5=Ocl.toOclSet(tudOclNode1.select(tudOclIter1, tudOclEval0));\n"+
      "final OclIterator tudOclIter0=tudOclNode5.getIterator();\n"+
      "final OclRootEvaluatable tudOclEval1=new OclRootEvaluatable() {\n"+
      "  public OclRoot evaluate() {\n"+
      "    final OclString tudOclNode6=Ocl.toOclString(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"name\"));\n"+
      "    return tudOclNode6;\n"+
      "  }\n"+
      "};\n"+
      "final OclBoolean tudOclNode7=tudOclNode5.isUnique(tudOclIter0, tudOclEval1);\n"
    );
    // double: sortedBy, iterate
    testGenerate(
      "context Person inv: employers->sortedBy(numberOfEmployees)->iterate(c:Company; s:String=''| s.concat(c.manager.name)) = 'bla'",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"employers\"));\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclComparableEvaluatable tudOclEval0=new OclComparableEvaluatable() {\n"+
      "  public Comparable evaluate() {\n"+
      "    final OclInteger tudOclNode2=Ocl.toOclInteger(Ocl.toOclAnyImpl(tudOclIter0.getValue()).getFeature(\"numberOfEmployees\"));\n"+
      "    return tudOclNode2;\n"+
      "  }\n"+
      "};\n"+
      "final OclSequence tudOclNode3=tudOclNode1.sortedBy(tudOclIter0, tudOclEval0);\n"+
      "final OclString tudOclNode4=new OclString(\"\");\n"+
      "final OclIterator tudOclIter1=tudOclNode3.getIterator();\n"+
      "final OclContainer tudOclAccu0=new OclContainer(tudOclNode4);\n"+
      "final OclRootEvaluatable tudOclEval1=new OclRootEvaluatable() {\n"+
      "  public OclRoot evaluate() {\n"+
      "    final OclAnyImpl tudOclNode5=Ocl.toOclAnyImpl(Ocl.toOclAnyImpl(tudOclIter1.getValue()).getFeature(\"manager\"));\n"+
      "    final OclString tudOclNode6=Ocl.toOclString(tudOclNode5.getFeature(\"name\"));\n"+
      "    final OclString tudOclNode7=Ocl.toOclString(tudOclAccu0.getValue()).concat(tudOclNode6);\n"+
      "    return tudOclNode7;\n"+
      "  }\n"+
      "};\n"+
      "final OclString tudOclNode8=Ocl.toOclString(tudOclNode3.iterate(tudOclIter1, tudOclAccu0, tudOclEval1));\n"+
      "final OclString tudOclNode9=new OclString(\"bla\");\n"+
      "final OclBoolean tudOclNode10=tudOclNode8.isEqualTo(tudOclNode9);\n"
    );
    // select and collect
    testGenerate(
      "context LoyaltyAccount inv: transactions->select( oclIsTypeOf(Earning) )->collect(points)->sum >= 0",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclSet tudOclNode1=Ocl.toOclSet(tudOclNode0.getFeature(\"transactions\"));\n"+
      "final OclIterator tudOclIter0=tudOclNode1.getIterator();\n"+
      "final OclBooleanEvaluatable tudOclEval0=new OclBooleanEvaluatable() {\n"+
      "  public OclBoolean evaluate() {\n"+
      "    final OclType tudOclNode2=OclType.getOclTypeFor(this, \"Earning\");\n"+
      "    final OclBoolean tudOclNode3=Ocl.toOclAnyImpl(tudOclIter0.getValue()).oclIsTypeOf(tudOclNode2);\n"+
      "    return tudOclNode3;\n"+
      "  }\n"+
      "};\n"+
      "final OclSet tudOclNode4=Ocl.toOclSet(tudOclNode1.select(tudOclIter0, tudOclEval0));\n"+
      "final OclIterator tudOclIter1=tudOclNode4.getIterator();\n"+
      "final OclRootEvaluatable tudOclEval1=new OclRootEvaluatable() {\n"+
      "  public OclRoot evaluate() {\n"+
      "    final OclInteger tudOclNode5=Ocl.toOclInteger(Ocl.toOclAnyImpl(tudOclIter1.getValue()).getFeature(\"points\"));\n"+
      "    return tudOclNode5;\n"+
      "  }\n"+
      "};\n"+
      "final OclBag tudOclNode6=Ocl.toOclBag(tudOclNode4.collect(tudOclIter1, tudOclEval1));\n"+
      "final OclInteger tudOclNode7=Ocl.toOclInteger(tudOclNode6.sum());\n"+
      "final OclInteger tudOclNode8=new OclInteger(0);\n"+
      "final OclBoolean tudOclNode9=tudOclNode7.isGreaterEqual(tudOclNode8);\n"
    );
  }

  public void testLogExpressions() {
    testGenerate(
      "context Person inv: isMarried and isUnemployed",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclBoolean tudOclNode1=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode2=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isUnemployed\"));\n"+
      "final OclBoolean tudOclNode3=tudOclNode1.and(tudOclNode2);\n"
    );
    testGenerate(
      "context Person inv: isMarried and isUnemployed or isMarried",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclBoolean tudOclNode1=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode2=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isUnemployed\"));\n"+
      "final OclBoolean tudOclNode3=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode4=tudOclNode1.and(tudOclNode2).or(tudOclNode3);\n"
    );
    testGenerate(
      "context Person inv: isMarried implies isUnemployed",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclBoolean tudOclNode1=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode2=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isUnemployed\"));\n"+
      "final OclBoolean tudOclNode3=tudOclNode1.implies(tudOclNode2);\n"
    );
    testGenerate(
      "context Person inv: isMarried and isUnemployed implies isMarried",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclBoolean tudOclNode1=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode2=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isUnemployed\"));\n"+
      "final OclBoolean tudOclNode3=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode4=tudOclNode1.and(tudOclNode2).implies(tudOclNode3);\n"
    );
    testGenerate(
      "context Person inv: isMarried implies isUnemployed and isMarried",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclBoolean tudOclNode1=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode2=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isUnemployed\"));\n"+
      "final OclBoolean tudOclNode3=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode4=tudOclNode1.implies(tudOclNode2.and(tudOclNode3));\n"
    );
    testGenerate(
      "context Person inv: isMarried implies isUnemployed implies isMarried",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclBoolean tudOclNode1=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode2=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isUnemployed\"));\n"+
      "final OclBoolean tudOclNode3=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode4=tudOclNode1.implies(tudOclNode2).implies(tudOclNode3);\n"
    );
    testGenerate(
      "context Person inv: isMarried or isUnemployed implies isMarried and isUnemployed",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclBoolean tudOclNode1=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode2=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isUnemployed\"));\n"+
      "final OclBoolean tudOclNode3=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode4=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isUnemployed\"));\n"+
      "final OclBoolean tudOclNode5=tudOclNode1.or(tudOclNode2).implies(tudOclNode3.and(tudOclNode4));\n"
    );
    testGenerate(
      "context Person inv: isMarried or isUnemployed implies isMarried and isUnemployed implies isMarried",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclBoolean tudOclNode1=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode2=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isUnemployed\"));\n"+
      "final OclBoolean tudOclNode3=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode4=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isUnemployed\"));\n"+
      "final OclBoolean tudOclNode5=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclBoolean tudOclNode6=tudOclNode1.or(tudOclNode2).implies(tudOclNode3.and(tudOclNode4)).implies(tudOclNode5);\n"
    );
  }

  public void testElements() {
    testGenerate(
      "context Person inv: isMarried = ( wife->notEmpty or husband->notEmpty)",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclBoolean tudOclNode1=Ocl.toOclBoolean(tudOclNode0.getFeature(\"isMarried\"));\n"+
      "final OclAnyImpl tudOclNode2=Ocl.toOclAnyImpl(tudOclNode0.getFeature(\"wife\"));\n"+
      "final OclSet tudOclSet0=OclSet.getEmptyOclSet();\n"+
      "tudOclSet0.setToInclude(tudOclNode2);\n"+
      "final OclBoolean tudOclNode3=tudOclSet0.notEmpty();\n"+
      "final OclAnyImpl tudOclNode4=Ocl.toOclAnyImpl(tudOclNode0.getFeature(\"husband\"));\n"+
      "final OclSet tudOclSet1=OclSet.getEmptyOclSet();\n"+
      "tudOclSet1.setToInclude(tudOclNode4);\n"+
      "final OclBoolean tudOclNode5=tudOclSet1.notEmpty();\n"+
      "final OclBoolean tudOclNode6=tudOclNode3.or(tudOclNode5);\n"+
      "final OclBoolean tudOclNode7=tudOclNode1.isEqualTo(tudOclNode6);\n"
    );
    testGenerate(
      "context Company inv: manager->notEmpty",
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclAnyImpl tudOclNode1=Ocl.toOclAnyImpl(tudOclNode0.getFeature(\"manager\"));\n"+
      "final OclSet tudOclSet0=OclSet.getEmptyOclSet();\n"+
      "tudOclSet0.setToInclude(tudOclNode1);\n"+
      "final OclBoolean tudOclNode2=tudOclSet0.notEmpty();\n"
    );
  }

  public void testPre() {
    String[] target=new String[3];
    // 0 -> evaluating code; 1 -> preparation code; 2 -> transfer code
    target[0]=
      "final OclInteger tudOclNode2=this.tudOclNode2;\n"+
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclReal tudOclOpPar0=Ocl.toOclReal( Ocl.getFor(r) );\n"+
      "final OclReal tudOclResult0=Ocl.toOclReal( Ocl.getFor(result) );\n"+
      "final OclInteger tudOclNode1=Ocl.toOclInteger(tudOclNode0.getFeature(\"age\"));\n"+
      "final OclBoolean tudOclNode3=tudOclNode1.isEqualTo(tudOclNode2);\n";
    target[1]=
      "final OclAnyImpl tudOclNode0=Ocl.toOclAnyImpl( Ocl.getFor(this) );\n"+
      "final OclReal tudOclOpPar0=Ocl.toOclReal( Ocl.getFor(r) );\n"+
      "final OclReal tudOclResult0=OclReal.UNDEFINED;\n"+
      "final OclInteger tudOclNode1=Ocl.toOclInteger(tudOclNode0.getFeature(\"age\"));\n"+
      "final OclInteger tudOclNode2=Ocl.toOclInteger(tudOclNode0.getFeature(\"age\"));\n"+
      "final OclBoolean tudOclNode3=tudOclNode1.isEqualTo(tudOclNode2);\n"+
      "this.tudOclNode2=tudOclNode2;\n";
    target[2]=
      "OclInteger tudOclNode2;\n";
    testGenerate(
      "context Person::getIncomeAfterTax(r: Real):Real post: age=age@pre",
      target
    );
  }

  public void testCompile() {
    try {
      FileWriter fw=new FileWriter("TestOclCompile.java");
      fw.write("import tudresden.ocl.lib.*;\n\n");
      fw.write("class TestOclCompile {\n\n");
      fw.write(javaFile.toString());
      fw.write("}\n");
      fw.close();
    }
    catch (IOException ioe) {
      ioe.printStackTrace(System.out);
      assert(false);
    }
  }

  protected void testGenerate(String ocl, String java) {
    String[] array=new String[1];
    array[0]=java;
    testGenerate(ocl, array);
  }

  protected void testGenerate(String ocl, String[] java) {
    boolean allIsFine=true;
    OclTree tree=null;
    try {
      tree=OclTree.createTree(ocl);
      tree.applyDefaultNormalizations();
      CodeFragment[] frags=tree.getCode(new JavaCodeGenerator("this", "result"));
      if (java.length!=frags.length) System.out.println("wrong number of fragments");
      assert( java.length == frags.length );
      for (int i=0; i<java.length; i++) {
        boolean ok=java[i].trim().equals( frags[i].getCode().trim() );
        if (!ok) {
          System.out.println("OCL expression:\n"+ocl);
          System.out.print("error in fragment "+i);
          System.out.println("  (name "+frags[i].getName()+", kind "+frags[i].getKind()+")");
          StringTokenizer stTarget=new StringTokenizer(java[i], "\n");
          StringTokenizer stOutput=new StringTokenizer(frags[i].getCode(), "\n");
          if (stTarget.countTokens()!=stOutput.countTokens()) {
            System.out.println("should be:\n"+java[i]);
            System.out.println("("+stTarget.countTokens()+" lines)");
            System.out.println("but is:\n"+frags[i].getCode());
            System.out.println("("+stOutput.countTokens()+" lines)");
          } else {
            System.out.println("(T is target, O is output)");
            while (stTarget.hasMoreTokens()) {
              String lineTarget=stTarget.nextToken();
              String lineOutput=stOutput.nextToken();
              System.out.println("T "+lineTarget);
              System.out.println("O "+lineOutput);
              System.out.print("  ");
              for (int iLine=0; iLine<lineTarget.length() && iLine<lineOutput.length(); iLine++) {
                if (lineTarget.charAt(iLine)!=lineOutput.charAt(iLine)) {
                  System.out.print("x");
                } else {
                  System.out.print(" ");
                }
              }
              if (lineOutput.length()!=lineTarget.length()) System.out.print(" difference in line length");
              System.out.println();
            }
          }
          allIsFine=false;
        }
        if (frags[i].getKind()==CodeFragment.TRANSFER) {
          javaFile.append(frags[i].getCode());
        } else {
          javaFile.append(
            "void test"+(index++)+"() {\n"+
            "Object result=null;\n"+
            frags[i].getCode()+
            "}\n\n");
        }
      }
    }
    catch (Exception e) {
      allIsFine=false;
      System.out.println("exception in TestJavaGenerator: ");
      System.out.println("generating code for");
      System.out.println(ocl);
      if (tree!=null) {
        System.out.println("normalized:");
        System.out.println(tree.toString());
      }
      e.printStackTrace(System.out);
    }
    assert(allIsFine);
  }

  public static Test suite() {
    TestSuite t=new TestSuite();
    t.addTest(new TestJavaGenerator("testLet"));
    t.addTest(new TestJavaGenerator("testColLiteral"));
    t.addTest(new TestJavaGenerator("testPostfix"));
    t.addTest(new TestJavaGenerator("testIterate"));
    t.addTest(new TestJavaGenerator("testLogExpressions"));
    t.addTest(new TestJavaGenerator("testElements"));
    t.addTest(new TestJavaGenerator("testPre"));

    t.addTest(new TestJavaGenerator("testCompile"));
    return t;
  }
}
