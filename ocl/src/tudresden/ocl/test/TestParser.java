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
import tudresden.ocl.*;
import tudresden.ocl.parser.*;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.*;
import java.io.*;
import java.util.*;
import java.util.StringTokenizer;
import java.net.URL;

public class TestParser extends TestCase {

  String[] oclExpressions;
  boolean[] syntaxOK;
  boolean[] typesOK;

  URL testFileName;
  ModelFacade facade;

  public TestParser(String s, URL testFile, ModelFacade facade) {
    super(s);
    this.testFileName=testFile;
    this.facade=facade;
  }

  public void testFromFile() {
    System.out.println("starting testFromFile, using "+facade);
    try {
      String testfile=readTestFile();
      getExpression(testfile);
      int size=oclExpressions.length;
      for (int i=0; i<size; i++) {
        try {
          OclTree ot=getTree(oclExpressions[i]);
          if (!syntaxOK[i])
            System.out.println("incorrectly parsed successfully:\n"+oclExpressions[i]);
          ot.assureTypes();
          ot.applyGeneratedTests();
          if (!typesOK[i])
            System.out.println("incorrectly type-checked successfully:\n"+oclExpressions[i]);
          assert( syntaxOK[i] && typesOK[i] );
        }
        catch (OclTypeException e) {
          if (typesOK[i])
          {
            System.out.println("failed to type-check:\n"+oclExpressions[i]);
            throw new RuntimeException(e.toString()+oclExpressions[i]);
            //assert( !typesOK[i] );
          }
        }
        catch (OclParserException e) {
          if (syntaxOK[i])
            System.out.println("failed to parse:\n"+oclExpressions[i]);
          assert( !syntaxOK[i] );
        }
        catch (IOException e) {
          if (syntaxOK[i])
            System.out.println("failed to parse:\n"+oclExpressions[i]);
          assert( !syntaxOK[i] );
        }
        catch (tudresden.ocl.OclException e) {
          if (typesOK[i])
          {
            System.out.println("generated tests failed:\n"+oclExpressions[i]);
            throw new RuntimeException(e.toString()+oclExpressions[i]);
            //assert( !typesOK[i] );
          }
        }
      }
    }
    catch(IOException e) {
      e.printStackTrace(System.out);
      assert(false);
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
      new tudresden.ocl.check.types.testfacade.TestModelFacade()
    ) );
    String[] reflectpackages=new String[1];
    reflectpackages[0]="tudresden.ocl.test.royloy";
    suite.addTest( new TestParser(
      "testFromFile",
      TestParser.class.getResource("oclexpressions.argo"),
      new tudresden.ocl.check.types.ReflectionFacade(
        reflectpackages,
        new tudresden.ocl.check.types.DefaultReflectionAdapter(),
        new tudresden.ocl.lib.ArgoNameAdapter(),
        new tudresden.ocl.injection.SourceReflectionExtender()
      )
    ) );
    suite.addTest( new TestParser(
      "testFromFile",
      TestParser.class.getResource("oclexpressions.xmi"),
      tudresden.ocl.check.types.xmifacade.stress.Royloy.getModel()
    ) );
    try {
      String argoXmi=
        TestParser.class.getResource("xmi/argo07/royloy.xmi").getFile();
      String roseXmi=
        TestParser.class.getResource("xmi/rose/royloy.xml").getFile();
      suite.addTest( new TestParser(
        "testFromFile",
        TestParser.class.getResource("oclexpressions.argo"),
        tudresden.ocl.check.types.xmifacade.XmiParser.getModel(argoXmi)
      ) );
      suite.addTest( new TestParser(
        "testFromFile",
        TestParser.class.getResource("oclexpressions.xmi"),
        tudresden.ocl.check.types.xmifacade.XmiParser.getModel(roseXmi,true)
      ) );
    }
    catch (Exception e) {
      System.out.println("error while creating XMI model facade:");
      e.printStackTrace(System.out);
    }
    return suite;
  }
}

