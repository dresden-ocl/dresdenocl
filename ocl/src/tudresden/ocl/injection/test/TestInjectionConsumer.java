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

public class TestInjectionConsumer implements InjectionConsumer
{
  private Writer output;
  
  public TestInjectionConsumer(Writer output) 
  {
    this.output=output;
  }

  public void onPackage(JavaFile javafile) 
    throws InjectorParseException 
  {
    try
    {
      output.write("[onPackage]");
    }
    catch(IOException e) { System.out.println(e); };
  }

  public void onImport(String importname) 
  {
    try
    {
      output.write("[onImport]");
    }
    catch(IOException e) { System.out.println(e); };
  }

  public void onClass(JavaClass cc) 
  {
    try
    {
      output.write("[onClass]");
    }
    catch(IOException e) { System.out.println(e); };
  }

  public void onClassEnd(JavaClass cc) 
    throws java.io.IOException, InjectorParseException 
  {
    try
    {
      output.write("[onClassEnd]");
    }
    catch(IOException e) { System.out.println(e); };
  }

  public void onBehaviourHeader(JavaBehaviour jb) 
    throws java.io.IOException 
  {
    try
    {
      output.write("[onBehaviourHeader]");
      output.write(jb.getLiteral());
    }
    catch(IOException e) { System.out.println(e); };
  }

  public void onClassFeature(JavaFeature cf,String doccomment) 
    throws java.io.IOException, InjectorParseException 
  {
    try
    {
      output.write("[onClassFeature]");
    }
    catch(IOException e) { System.out.println(e); };
  }

  public boolean onDocComment(String doccomment) 
    throws java.io.IOException 
  {
    try
    {
      output.write("[onDocComment]");
      output.write(doccomment);
    }
    catch(IOException e) { System.out.println(e); };
    return true;
  }
  
  public void onFileEnd() 
  {
    try
    {
      output.write("[onFileEnd]");
    }
    catch(IOException e) { System.out.println(e); };
  }
  
  public static void main(String[] args)
  {
    String  inputfile=TestInjectionConsumer.class.getResource("Example.java").getFile();
    String outputfile="Example.java.injectiontest";
    Reader input=null;
    Writer output=null;
    try
    {
      try
      {
        input =new InputStreamReader (new  FileInputStream(inputfile));
        output=new OutputStreamWriter(new FileOutputStream(outputfile));
        (new Injector(input, output, new TestInjectionConsumer(output))).parseFile();
        input.close();
        output.close();
      }
      catch(InjectorParseException e)
      {
        input.close();
        output.close();
        System.out.println(e);
      }
      catch(IOException e)
      {
        if(input!=null)  input.close();
        if(output!=null) output.close();
        System.out.println(e);
      }
    }
    catch(IOException e) { System.out.println(e); }
  }

}
