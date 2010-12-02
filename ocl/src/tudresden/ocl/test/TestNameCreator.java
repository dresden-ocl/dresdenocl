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

public class TestNameCreator extends TestCase {

  public TestNameCreator(String s) {
    super(s);
  }

  public void testNames() {
    NameCreator na=new NameCreator();
    na.setPrefix("test");
    assertTrue( na.reserveName("testVar") );
    assertTrue( na.getUniqueName().equals("test0") );
    assertTrue( na.getUniqueName().equals("test1") );
    assertTrue( na.getUniqueName().equals("test2") );
    assertTrue( ! na.reserveName("test2") );
    assertTrue( ! na.reserveName("testVar") );
    assertTrue( na.reserveName("testVar2") );
    assertTrue( na.getUniqueName("Var").equals("testVar0") );
    assertTrue( na.getUniqueName("Var").equals("testVar1") );
    // "testVar2" already reserved
    assertTrue( na.getUniqueName("Var").equals("testVar3") );
    assertTrue( na.getUniqueName("Var").equals("testVar4") );
    na.setPrefix(NameCreator.defaultPrefix);
  }

  public void testReserveAllNames() {
    try {
      OclTree tree=DocCheck.createTree("context Test inv: let var0=4 in var1->forAll(var2, var3 | expression)");
      NameCreator nc=new NameCreator();
      nc.reserveAllNames(tree);
      assertTrue( ! nc.reserveName("self") );
      assertTrue( nc.reserveName("result") );
      assertTrue( ! nc.reserveName("var0") );
      assertTrue( nc.reserveName("var1") );
      assertTrue( ! nc.reserveName("var2") );
      assertTrue( ! nc.reserveName("var3") );
      nc.clear();
      tree=DocCheck.createTree("context Test::method() post: test->iterate(iter:Integer ; acc:Integer=0 | iter+acc) = 5");
      nc.reserveAllNames(tree);
      assertTrue( ! nc.reserveName("self") );
      assertTrue( ! nc.reserveName("result") );
      assertTrue( ! nc.reserveName("iter") );
      assertTrue( ! nc.reserveName("acc") );
    }
    catch (Exception e) {
      e.printStackTrace(System.out);
      assertTrue(false);
    }
  }

  public static Test suite() {
    TestSuite suite=new TestSuite();
    suite.addTest( new TestNameCreator("testNames") );
    suite.addTest( new TestNameCreator("testReserveAllNames") );
    return suite;
  }
}
