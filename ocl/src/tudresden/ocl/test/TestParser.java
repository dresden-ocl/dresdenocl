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
import tudresden.ocl.*;
import tudresden.ocl.parser.*;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.*;
import tudresden.ocl.check.types.xmifacade.*;
import java.io.*;
import java.util.*;
import java.util.StringTokenizer;
import java.net.URL;

public class TestParser extends TestCase {

  String[] oclExpressions;
  boolean[] syntaxOK;
  boolean[] typesOK;

  URL testFileName;
  ModelFacadeFactory facadeFactory;
  ModelFacade facade;

  public TestParser(final String s, final URL testFile, final ModelFacadeFactory facadeFactory) {
    super(s);
    this.testFileName=testFile;
    this.facadeFactory=facadeFactory;
  }

  public void testFromFile() throws Exception{
    facade=facadeFactory.makeModelFacade();
    try {
      String testfile=readTestFile();
      getExpression(testfile);
      int size=oclExpressions.length;
      for (int i=0; i<size; i++) {
        try {
          OclTree ot=getTree(oclExpressions[i]);
          assertTrue( "incorrectly parsed successfully:\n"+oclExpressions[i], syntaxOK[i] );
          ot.assureTypes();
          ot.applyGeneratedTests();
          assertTrue( "incorrectly type-checked successfully:\n"+oclExpressions[i], typesOK[i] );
        }
        catch (OclTypeException e) {
          assertTrue("failed to type-check:\n"+oclExpressions[i], !typesOK[i] );
        }
        catch (OclParserException e) {
          assertTrue("failed to parse:\n"+oclExpressions[i], !syntaxOK[i] );
        }
        catch (IOException e) {
          assertTrue("failed to parse:\n"+oclExpressions[i], !syntaxOK[i] );
        }
        catch (tudresden.ocl.OclException e) {
          assertTrue("generated tests failed:\n"+oclExpressions[i], !typesOK[i] );
        }
      }
    }
    catch(IOException e) {
      e.printStackTrace(System.out);
      assertTrue(false);
    }
  }

  protected OclTree getTree(String oclExpr) throws OclException, IOException {
    return OclTree.createTree(oclExpr, facade);
  }

  protected String readTestFile() throws IOException {
    String filename=testFileName.getFile();
    BufferedReader br=new BufferedReader(new FileReader(filename));
    StringBuffer sbuf=new StringBuffer();
    String next;
    do {
      next=br.readLine();
      if (next!=null) sbuf.append(next+"\n");
    }
    while (next!=null);
    br.close();
    return sbuf.toString();
  }

  protected void getExpression(String text) {
    ArrayList exprs=new ArrayList();
    ArrayList syn=new ArrayList();
    ArrayList types=new ArrayList();
    int index=text.indexOf("<ocl");
    while(index>=0) {
      int endIndex=text.indexOf(">", index);
      String descr=text.substring(index, endIndex);
      StringTokenizer stok=new StringTokenizer(descr);
      stok.nextToken(); // read "<ocl"
      boolean nextSyn=true;
      boolean nextTypes=true;
      while (stok.hasMoreTokens()) {
        String next=stok.nextToken();
        if (next.startsWith("syntax=0")) {
          nextSyn=false;
        } else if (next.startsWith("types=0")) {
          nextTypes=false;
        }
      }
      syn.add(new Boolean(nextSyn));
      types.add(new Boolean(nextTypes));
      index=text.indexOf("<ocl", endIndex);
      String nextExpr;
      if (index>0) {
        nextExpr=text.substring(endIndex+1, index);
      } else {
        nextExpr=text.substring(endIndex+1);
      }
      exprs.add(nextExpr);
    }

    int size=exprs.size();
    oclExpressions=(String[])exprs.toArray(new String[size]);
    syntaxOK=new boolean[size];
    typesOK=new boolean[size];
    Iterator iSyn=syn.iterator();
    Iterator iTypes=types.iterator();
    for (int i=0; i<size; i++) {
      syntaxOK[i]=( (Boolean)iSyn.next() ).booleanValue();
      typesOK[i]= ( (Boolean)iTypes.next() ).booleanValue();
    }
  }

  public static Test suite() {
    TestSuite suite=new TestSuite();

    suite.addTest( new TestParser(
      "testFromFile",
      TestParser.class.getResource("oclexpressions"),
      new ModelFacadeFactory()
      {
        public ModelFacade makeModelFacade()
        {
          return new tudresden.ocl.check.types.testfacade.TestModelFacade();
        }
      }
    ));
    suite.addTest( new TestParser(
      "testFromFile",
      TestParser.class.getResource("oclexpressions.xmi"),
      new ModelFacadeFactory()
      {
        public ModelFacade makeModelFacade()
        {
          return new tudresden.ocl.check.types.ReflectionFacade(
            new String[] {"tudresden.ocl.test.royloy"},
            new tudresden.ocl.check.types.DefaultReflectionAdapter(),
            new tudresden.ocl.lib.ArgoNameAdapter(),
            new tudresden.ocl.injection.ocl.SourceReflectionExtender());
        }
      }
    ));
    suite.addTest( new TestParser(
      "testFromFile",
      TestParser.class.getResource("oclexpressions.xmi"),
      new ModelFacadeFactory()
      {
        public ModelFacade makeModelFacade() throws Exception
        {
          return tudresden.ocl.check.types.xmifacade.stress.Royloy.getModel();
        }
      }
    ));
    suite.addTest( new TestParser(
      "testFromFile",
      TestParser.class.getResource("oclexpressions.argo"),
      new ModelFacadeFactory()
      {
        public ModelFacade makeModelFacade() throws Exception
        {
          final File actual = new File("argo07_royloy.debug.bak");
          final String resource = "xmi/argo07/royloy.xmi";
          Model result = XmiParser.getModel(
            getClass().getResource(resource),
            resource
          );
          result.printData(new PrintStream(new FileOutputStream(actual)));
          Diff.diff(new DiffSource(getClass().getResource(resource+".debug")), new DiffSource(actual));
          return result;
        }
      }
    ));
    suite.addTest( new TestParser(
      "testFromFile",
      TestParser.class.getResource("oclexpressions.xmi"),
      new ModelFacadeFactory()
      {
        public ModelFacade makeModelFacade() throws Exception
        {
          final File actual = new File("rose_royloy.debug.bak");
          final String resource = "xmi/rose/royloy.xml";
          Model result = tudresden.ocl.check.types.xmifacade.XmiParser.getModel(
            getClass().getResource("xmi/rose/royloy.xml"),
            resource,
            true
            );
          result.printData(new PrintStream(new FileOutputStream(actual)));
          Diff.diff(new DiffSource(getClass().getResource(resource+".debug")), new DiffSource(actual));
          return result;
        }
      }
    ));
    return suite;
  }
}

