/*
Copyright (C) 2000  Ralf Wiebicke

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package tudresden.ocl.injection.test;

import tudresden.ocl.injection.*;
import java.io.*;
import junit.framework.*;
import tudresden.ocl.test.*;

public class TestInjection extends TestCase
{
  Reader input=null;
  Writer output=null;
  String outputfile="TestInjectionConsumer.result";

  TestInjection(String name)
  {
    super(name);
  }

  protected void setUp() throws Exception
  {
    super.setUp();
    String  inputfile=TestInjection.class.getResource("Example.java").getFile();
    String outputfile="TestInjectionConsumer.result";
    input =new InputStreamReader (new  FileInputStream(inputfile));
    output=new OutputStreamWriter(new FileOutputStream(outputfile));
  }

  protected void tearDown() throws Exception
  {
    if(input!=null)  { input.close(); input=null; }
    if(output!=null) { output.close(); output=null; }
    super.tearDown();
  }


  public void testInjection() throws Exception
  {
    (new Injector(input, output, new TestInjectionConsumer(output))).parseFile();
    input.close();  input=null;
    output.close(); output=null;

    String expected=TestInjection.class.getResource("TestInjectionConsumer.result").getFile();
    String actual=outputfile;
    Diff.diff(new DiffSource(new File(expected)), new DiffSource(new File(actual)));
  }

  public static Test suite()
  {
    TestSuite suite=new TestSuite();
    suite.addTest(new TestInjection("testInjection"));
    return suite;
  }

}

