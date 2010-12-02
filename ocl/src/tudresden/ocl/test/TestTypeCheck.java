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

import java.io.IOException;
import junit.framework.TestCase;

import tudresden.ocl.OclTree;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.testfacade.TestModelFacade;

/**
 *
 * @author  frank
 * @version $Revision: 1.1 $
 */
public class TestTypeCheck extends TestCase {
   
   OclTree tree;
   
   public TestTypeCheck(String name) {
      super(name);
   }
   
   public void testTestFacade() throws IOException {
      tree = OclTree.createTree( "context Person inv: true", new TestModelFacade() );
      tree.assureTypes();
      
      try {
         tree = OclTree.createTree( "context Person inv: 6", new TestModelFacade() );
         tree.assureTypes();
         fail();
      }
      catch (OclTypeException e) {
      }
      
      tree = OclTree.createTree( "context Company inv: employees->size <= 7", new TestModelFacade() );
      tree.assureTypes();
      
      tree = OclTree.createTree( "context Company inv: employees->oclAsType( Collection(Person) )->size <= 7", new TestModelFacade() );
      try {
         tree.assureTypes();
         fail( "oclAsType not defined for Collections" );
      }
      catch (OclTypeException e) {
      }     
      
      // here, oclAsType is the shorthand for collect
      tree = OclTree.createTree( "context Company inv: employees.oclAsType( Person )->size <= 7", new TestModelFacade() );
      tree.assureTypes();      
   }
   
   
   
}
/**
 *	$Log: TestTypeCheck.java,v $
 *	Revision 1.1  2002/05/06 20:47:39  ff3
 *	tests against result variable in void post fragments, for oclAsType with Collections
 *
 */