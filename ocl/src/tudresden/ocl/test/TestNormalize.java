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

import junit.framework.*;
import tudresden.ocl.normalize.*;
import tudresden.ocl.*;

public class TestNormalize extends TestCase {

  /** the NameCreator default prefix
   */
  static String ncPrefix="tudOcl";

  public TestNormalize(String name) {
    super(name);
  }

  public void testConstraintNaming() {
    try {
      assert( convertsTo("context Person inv: self.age>15", "context Person inv "+ncPrefix+"Inv0 : self.age>15") );
      assert( convertsTo("context Person inv inv1: self.age>15", "context Person inv inv1: self.age>15") );
    }
    catch (Exception e) {
      e.printStackTrace( System.out );
      assert( false );
    }
  }

  public void testDefaultContextInsertion() {
    try {
      assert( convertsTo("context Person inv: age>=0", "context Person inv "+ncPrefix+"Inv0 : self.age>=0") );
      assert( convertsTo("context Person inv someInv: age>=0", "context Person inv someInv : self.age>=0") );
      assert( convertsTo(
        "context Company inv: employees->forAll(p:Person|age>15)",
        "context Company inv "+ncPrefix+"Inv0: self.employees->forAll(p:Person|p.age>15)"
      ));
      assert( convertsTo(
        "context Company inv mgr: employees->includes(manager)",
        "context Company inv mgr: self.employees->includes(self.manager)"
      ));
      assert( convertsTo(
        "context Company inv mgr: employees->collect(p|employers)->includes(self)",
        "context Company inv mgr: self.employees->collect(p:Person|p.employers)->includes(self)"
      ));
      assert( convertsTo(
        "context Company inv mgr: employees->collect(p|employers)->collect(c|employees)->includesAll(employees)",
        "context Company inv mgr: self.employees->collect(p:Person|p.employers)->collect(c:Company|c.employees)->includesAll(self.employees)"
      ));
      assert( convertsTo(
        "context Company inv someInv: employees->forAll(p:Person| employers->exists(c|c=self))",
        "context Company inv someInv: self.employees->forAll(p:Person| p.employers->exists(c:Company|c=self))"
      ));
      assert( convertsTo(
        "context Company inv someInv: employees->forAll(p:Person| employers->forAll(c|numberOfEmployees>0))",
        "context Company inv someInv: self.employees->forAll(p:Person| p.employers->forAll(c:Company|c.numberOfEmployees>0))"
      ));
      assert( convertsTo(
        "context Company inv someInv: employees->forAll(p:Person| employers->forAll(c|getOldestEmployeeAge()>=p.age))",
        "context Company inv someInv: self.employees->forAll(p:Person| p.employers->forAll(c:Company|c.getOldestEmployeeAge()>=p.age))"
      ));
      assert( convertsTo(
        "context Person inv: let age=age+1 in age>=0",
        "context Person inv "+ncPrefix+"Inv0 : let age:Integer=self.age+1 in age>=0"
      ));
    }
    catch (Exception e) {
      e.printStackTrace( System.out );
      assert( false );
    }
  }

  public void testMultipleIteratorSolving() {
    try {
      assert( convertsTo(
        "context Company inv: self.employees->forAll(i, j|i<>j implies i.name<>j.name)",
        "context Company inv "+ncPrefix+"Inv0 : let "+ncPrefix+"Let0:Set(Person)=self.employees in "+
          ncPrefix+"Let0->forAll(i: Person|"+ncPrefix+"Let0->forAll(j: Person|i<>j implies i.name<>j.name))"
      ));
      assert( convertsTo(
        "context Company inv i0: manager.employers->forAll(c1, c2, c3:Company|true)",
        "context Company inv i0: "+
         "let "+ncPrefix+"Let0:Set(Company) = self . manager . employers in "+
         ncPrefix+"Let0 -> forAll ( c1 :Company|"+ncPrefix+"Let0->forAll(c2:Company|"+ncPrefix+"Let0->forAll(c3:Company|true)))"
      ));
      assert( convertsTo(
        "context Person inv i1: isMarried and not employers->forAll(x, y | false)",
        "context Person inv i1: let "+ncPrefix+"Let0:Set(Company)=self.employers in "+
         "self.isMarried and not "+ncPrefix+"Let0->forAll(x :Company| "+ncPrefix+"Let0->forAll(y: Company|false))"
      ));
      assert( convertsTo(
        "context Person inv i2: let x = managedCompanies in x->forAll(i, j | i.employees->forAll(k, l | j=k or i=j) )",
        "context Person inv i2: "+
         "let x:Set(Company) = self . managedCompanies in x -> forAll ( i: Company | x -> forAll( j: Company | "+
         "i . employees -> forAll ( k: Person | i . employees -> forAll( l: Person | j = k or i = j) ) ) )"
      ));
      assert( convertsTo(
        "context Person inv i2: let x = employers in x->forAll(i, j : Company | (i.employees)->forAll(k, l :Person | k.isMarried or l.isUnemployed) )",
        "context Person inv i2: "+
         "let x: Set(Company) = self . employers in x -> forAll ( i : Company | x -> forAll( j : Company | "+
         "( i . employees ) -> forAll ( k : Person | ( i . employees ) -> forAll( l : Person | k.isMarried or l.isUnemployed) ) ) )"
      ));
    }
    catch (Exception e) {
      e.printStackTrace( System.out );
      assert( false );
    }
  }

  public void testIteratorInsertion() {
    try {
      assert( convertsTo(
        "context Company inv: employees->forAll(age>15)",
        "context Company inv "+ncPrefix+"Inv0: self.employees->forAll("+ncPrefix+"Iter0: Person|"+ncPrefix+"Iter0.age>15)"
      ));
      assert( convertsTo(
        "context Company inv i: let maxAge=getOldestEmployeeAge() in employees->forAll(it|age<=maxAge)",
        "context Company inv i: let maxAge:Integer=self.getOldestEmployeeAge() in self.employees->forAll(it: Person|it.age<=maxAge)"
      ));
      assert( convertsTo(
        "context Company inv mgr: employees->collect(employers)->includes(self)",
        "context Company inv mgr: self.employees->collect("+ncPrefix+"Iter0: Person|"+ncPrefix+"Iter0.employers)->includes(self)"
      ));
      assert( convertsTo(
        "context Company inv mgr: employees->collect(employers)->collect(employees)->includesAll(employees)",
        "context Company inv mgr: "+
          "self.employees->collect("+ncPrefix+"Iter0: Person|"+ncPrefix+"Iter0.employers)->"+
          "collect("+ncPrefix+"Iter1:Company|"+ncPrefix+"Iter1.employees)->includesAll(self.employees)"
      ));
      assert( convertsTo(
        "context Company inv someInv: employees->forAll(employers->exists(c|c=self))",
        "context Company inv someInv: self.employees->forAll("+ncPrefix+"Iter0:Person|"+ncPrefix+"Iter0.employers->exists(c:Company|c=self))"
      ));
      assert( convertsTo(
        "context Company inv someInv: employees->forAll(employers->forAll(numberOfEmployees>0))",
        "context Company inv someInv:"+
          "self.employees->forAll("+ncPrefix+"Iter0:Person|"+ncPrefix+"Iter0.employers->"+
          "forAll("+ncPrefix+"Iter1:Company|"+ncPrefix+"Iter1.numberOfEmployees>0))"
      ));
      assert( convertsTo(
        "context Company inv someInv: employees->forAll(p|employers->forAll(getOldestEmployeeAge()>=p.age))",
        "context Company inv someInv: self.employees->"+
          "forAll(p:Person|p.employers->forAll("+ncPrefix+"Iter0:Company|"+ncPrefix+"Iter0.getOldestEmployeeAge()>=p.age))"
      ));
      assert( convertsTo(
        "context Company inv: let "+ncPrefix+"Iter0:Real=17.5 in employees->forAll(age>15)",
        "context Company inv "+ncPrefix+"Inv0: let "+ncPrefix+"Iter0:Real=17.5 in self.employees->forAll("+ncPrefix+"Iter1: Person|"+ncPrefix+"Iter1.age>15)"
      ));
    }
    catch (Exception e) {
      e.printStackTrace( System.out );
      assert( false );
    }
  }

  public void testVariableClarification() {
    try {
      assert( convertsTo(
        "context Person inv i0: let a=age in let a=a+1 in a>0",
        "context Person inv i0: let a : Integer = self.age in let tudOclLet0:Integer=a+1 in tudOclLet0>0"
      ));
      assert( convertsTo(
        "context Company inv i0: let x=manager in employees->forAll(x|x.age<=x.age)",
        "context Company inv i0: let x:Person=self.manager in self.employees->forAll(tudOclIter0:Person|tudOclIter0.age<=tudOclIter0.age)"
      ));
      assert( convertsTo(
        "context Company inv i0: employees->forAll(e1|self.employees->forAll(e1|e1<>e1 implies e1.name<>e1.name))",
        "context Company inv i0: self.employees->forAll(e1:Person|self.employees->forAll(tudOclIter0:Person|"+
          "tudOclIter0<>tudOclIter0 implies tudOclIter0.name<>tudOclIter0.name)"+
        ")"
      ));
      assert( convertsTo(
        "context Person inv i: let a=age in employers->iterate(i:Company; a:Integer=0|a+i.employees->size)>10",
        "context Person inv i: let a:Integer=self.age in self.employers->iterate("+
          "i:Company; tudOclAccu0:Integer=0|tudOclAccu0+i.employees->size"+
        ")>10"
      ));
      assert( convertsTo(
        "context Person inv a: let a=1 in let a=a+1 in let a=a+1 in a=3",
        "context Person inv a: let a:Integer=1 in let tudOclLet0:Integer=a+1 in let tudOclLet1:Integer=tudOclLet0+1 in tudOclLet1=3"
      ));
    }
    catch (Exception e) {
      e.printStackTrace( System.out );
      assert( false );
    }
  }

  /** tests if
   *      - OclExpression before is normalized to OclExpression after and
   *      - OclExpression after is normalized to OclExpression after
   */
  protected boolean convertsTo(String before, String after) throws Exception {
    System.out.println("converting "+before);
    OclTree tree=OclTree.createTree(before);
    tree.applyDefaultNormalizations();
    OclTree otherTree=OclTree.createTree(after);
    OclTree thirdTree=OclTree.createTree(after);
    thirdTree.applyDefaultNormalizations();

    boolean norm = otherTree.equalsExpression( tree );
    boolean fix  = thirdTree.equalsExpression( otherTree );
    if (! (norm && fix) ) {
      System.out.println("failure in test \n"+before+"\n to \n"+after);
    }
    if (! norm) System.out.println("  normalization failed, result is:\n"+tree.getExpression());
    if (! fix) System.out.println("  not fix, goal expression becomes:\n"+thirdTree.getExpression());
    return norm && fix;
  }

  public static Test suite() {
    TestSuite suite=new TestSuite();
    suite.addTest( new TestNormalize("testConstraintNaming") );
    suite.addTest( new TestNormalize("testDefaultContextInsertion") );
    suite.addTest( new TestNormalize("testIteratorInsertion") );
    suite.addTest( new TestNormalize("testMultipleIteratorSolving") );
    suite.addTest( new TestNormalize("testVariableClarification") );
    return suite;
  }
}
