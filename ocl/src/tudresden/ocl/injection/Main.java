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

package tudresden.ocl.injection;

import java.io.*;
import java.util.*;
import tudresden.ocl.OclTree;
import tudresden.ocl.NameCreator;
import tudresden.ocl.parser.OclParserException;
import tudresden.ocl.check.types.*;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.xmifacade.XmiParser;
import tudresden.ocl.codegen.CodeFragment;
import tudresden.ocl.codegen.JavaCodeGenerator;

class OclInjector implements InjectionConsumer
{
  private Writer output;
  private HashMap codefragments;
  private boolean delayinsertions;

  /**
     Collects all methods (ClassFeature) of the current class, except automatically generated methods.
     Is used only, if delayinsertions is true. Otherwise methods is null.
     @see #delayinsertions
     @see ClassFeature
  */
  private ArrayList methods;
  
  /**
     Collects all attributes (ClassFeature) of the current class, which have element-type set.
  */
  private ArrayList typedAttributes;

  OclInjector(Writer output, HashMap codefragments, boolean insertimmediatly)
  {
    this.output=output;
    this.codefragments=codefragments;
    this.delayinsertions=!insertimmediatly;
    methods=(delayinsertions ? new ArrayList() : null);
    typedAttributes=new ArrayList();
  }
  
  private String packagename;
  
  public void onPackage(String packagename) throws InjectorParseException
  {
    if(this.packagename!=null)
      throw new InjectorParseException("more than one package statement.");
    this.packagename=packagename;
  }
  
  public void onImport(String importname)
  {
  }
    
  private boolean discardnextfeature=false;

  public void onClass(String classname)
  {
    discardnextfeature=false;
    if(delayinsertions)
      methods.clear();
    typedAttributes.clear();
  }

  public void onClassEnd(String classname) throws IOException
  {
    if(delayinsertions)
    {
      for(Iterator i=methods.iterator(); i.hasNext(); )
        writeWrapper((ClassFeature)i.next());
      methods.clear();
    }

    writeInvariants(classname);
    typedAttributes.clear();
  }
  
  private String last_element_type=null;

  public void onClassFeature(ClassFeature cf) throws IOException
  {
    if(cf.isMethod()&&!cf.isConstructor()&&!discardnextfeature)
    {
      if(delayinsertions)
        methods.add(cf);
      else
        writeWrapper(cf);
    }
    if(last_element_type!=null)
    {
      if(!cf.isMethod())
      {
        cf.setElementType(last_element_type);
        typedAttributes.add(cf);
      }
      last_element_type=null;
    }
    discardnextfeature=false;
  }

  public boolean onComment(String comment)
  {
    if(comment.startsWith("/**"))
    {
      if(OCL_AUTHOR.equals(Injector.findDocTag(comment, "author")))
      {
        discardnextfeature=true;
        return false;
      }
      else
      {
        last_element_type=Injector.findDocTag(comment, "element-type");
        return true;
      }
    }
    else
      return true;
  }

  public static final String INV_METHOD="checkOclInvariants";
  public static final String violationMakro="System.out.println";

  public final void writeInvariants(String classname) throws IOException
  {
    Writer o=output;
    o.write("/**\n    A method for checking ocl invariants.\n    Generated automatically on ");
    o.write((new Date()).toString());
    o.write(", DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n  */private final void ");
    o.write(INV_METHOD);
    o.write("()\n  {\n");
    for(Iterator i=typedAttributes.iterator(); i.hasNext(); )
      writeElementChecker((ClassFeature)i.next());
    SortedFragments sf=
      codefragments!=null ? (SortedFragments)(codefragments.get(classname)) : null;
    if(sf!=null)
    {
      java.util.Collection v=sf.inv;
      for(Iterator e=v.iterator(); e.hasNext(); )
      {
        CodeFragment cf=(CodeFragment)(e.next());
        o.write("    {\n");
        o.write(cf.getCode());
        o.write("      if(!");
        o.write(cf.getResultVariable());
        o.write(".isTrue())\n        ");
        o.write(violationMakro);
        o.write("(\"ocl invariant ");
        o.write(cf.getName());
        o.write(" violated\");\n    }\n");
      }
    }
    o.write("}");
  }
  
  /**
     All generated class features get this string as author.
     Must not contain spaces, line breaks or askerics.
     @see Injector#findDocTag
  */
  public static final String OCL_AUTHOR="ocl_injector";

  public final void writeWrapperInvariant(ClassFeature cf) throws IOException
  {
    if(!java.lang.reflect.Modifier.isStatic(cf.getModifiers()))
    {
      output.write("    ");
      output.write(INV_METHOD);
      output.write("();\n");
    }
  }

  public final void writeWrapper(ClassFeature cf) throws IOException
  {
    Writer o=output;
    o.write("/**\n    A wrapper for checking ocl constraints.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n    @see #");
    o.write(cf.getWrappedName());
    o.write('(');
    for(Iterator i=cf.getParameters(); i.hasNext(); )
    {
      o.write((String)(i.next()));
      i.next();
      if(i.hasNext()) o.write(", ");
    }
    o.write(")\n  */");
    String modifierString=
      java.lang.reflect.Modifier.toString(
        cf.getModifiers()&~java.lang.reflect.Modifier.ABSTRACT);
    if(modifierString.length()>0)
    {
      o.write(modifierString);
      o.write(' ');
    }
    if(cf.getType()!=null)
    {
      o.write(cf.getType());
      o.write(' ');
    }
    o.write(cf.getNotWrappedName());
    o.write('(');
    for(Iterator i=cf.getParameters(); i.hasNext(); )
    {
      o.write((String)i.next());
      o.write(' ');
      o.write((String)i.next());
      if(i.hasNext()) o.write(", ");
    }
    o.write(')');
    Iterator throwables=cf.getThrowables();
    if(throwables.hasNext())
    {
      o.write(" throws ");
      while(throwables.hasNext())
      {
        o.write((String)throwables.next());
        if(throwables.hasNext()) o.write(", ");
      }
    }
    o.write("\n  {\n");
    if(!cf.isConstructor())
    {
      writeWrapperInvariant(cf);
      if(codefragments!=null)
      {
        SortedFragments sf=(SortedFragments)codefragments.get(cf.getClassName());
        if(sf!=null)
        {
          for(Iterator i=sf.pre.iterator(); i.hasNext(); )
          {
            CodeFragment frag=(CodeFragment)i.next();
            if(cf.getSignature().equals(frag.getConstrainedOperation()))
            {
              o.write("    {\n");
              o.write(frag.getCode());
              o.write("      if(!");
              o.write(frag.getResultVariable());
              o.write(".isTrue())\n        ");
              o.write(violationMakro);
              o.write("(\"ocl precondition ");
              o.write(frag.getName());
              o.write(" violated\");\n    }\n");
            }
          }
          for(Iterator i=sf.transfer.iterator(); i.hasNext(); )
          {
            CodeFragment frag=(CodeFragment)i.next();
            if(cf.getSignature().equals(frag.getConstrainedOperation()))
              o.write(frag.getCode());
          }
          for(Iterator i=sf.preparation.iterator(); i.hasNext(); )
          {
            CodeFragment frag=(CodeFragment)i.next();
            if(cf.getSignature().equals(frag.getConstrainedOperation()))
            {
              o.write("    {\n");
              o.write(frag.getCode());
              o.write("    }\n");
            }
          }
        }
      }
    }
    o.write("    ");
    if(!cf.isConstructor() && !"void".equals(cf.getType()))
    {
      o.write(cf.getType());
      o.write(" result=");
    }
    o.write(cf.getWrappedName());
    o.write('(');
    for(Iterator i=cf.getParameters(); i.hasNext(); )
    {
      i.next();
      o.write((String)i.next());
      if(i.hasNext()) o.write(", ");
    }
    o.write(");\n");
    writeWrapperInvariant(cf);
    if(codefragments!=null)
    {
      SortedFragments sf=(SortedFragments)codefragments.get(cf.getClassName());
      if(sf!=null)
        for(Iterator i=sf.post.iterator(); i.hasNext(); )
        {
          CodeFragment frag=(CodeFragment)i.next();
          if(cf.getSignature().equals(frag.getConstrainedOperation()))
          {
            o.write("    {\n");
            o.write(frag.getCode());
            o.write("      if(!");
            o.write(frag.getResultVariable());
            o.write(".isTrue())\n        ");
            o.write(violationMakro);
            o.write("(\"ocl postcondition ");
            o.write(frag.getName());
            o.write(" violated\");\n    }\n");
          }
        }
    }
    if(!cf.isConstructor() && !"void".equals(cf.getType()))
      o.write("    return result;\n");
    o.write("  }");
  }

  public void writeElementChecker(ClassFeature cf) throws IOException
  {
    Writer o=output;
    o.write("    for(Iterator i=");
    o.write(cf.getName());
    o.write(".iterator(); i.hasNext(); )\n      if(!(i.next() instanceof ");
    o.write(cf.getElementType());
    o.write("))\n        ");
    o.write(violationMakro);
    o.write("(\"element checker failed.\");\n");
  }

}
  
public class Main
{
  public static HashMap makeCode(File constraintfile,  ModelFacade modelfacade)
    throws OclParserException, OclTypeException, IOException
  {
    BufferedReader br=new BufferedReader(new FileReader(constraintfile));
    NameCreator namecreator=new NameCreator();
    JavaCodeGenerator jcg=new JavaCodeGenerator("this", "result");
    HashMap constrainedTypes=new HashMap(); // type names are keys, SortedFragments values

    String nextLine;
    String nextConstraint="";
    do
    {
      nextLine=br.readLine();
      if(nextLine==null || nextLine.trim().equals(""))
      {
        if(!nextConstraint.equals(""))
        {
          String constraintString=nextConstraint;
          System.out.println("Loaded constraint:");
          System.out.println(constraintString);
          System.out.println("Parsing constraint.");
          OclTree constraintTree=OclTree.createTree(constraintString, modelfacade);
          constraintTree.setNameCreator(namecreator);
          System.out.println("Type checking constraint.");
          constraintTree.assureTypes();
          System.out.println("Normalizing.");
          constraintTree.applyDefaultNormalizations();
          System.out.println("Generating Code.");
          CodeFragment[] frags=jcg.getCode(constraintTree);
          for (int j=0; j<frags.length; j++)
          {
            String ct=frags[j].getConstrainedType();
            SortedFragments sf=(SortedFragments)(constrainedTypes.get(ct));
            if(sf==null)
              constrainedTypes.put(ct, new SortedFragments(frags[j]));
            else
              sf.addFragment(frags[j]);
          }
        }
        nextConstraint="";
      }
      else
        nextConstraint=nextConstraint+"\n"+nextLine;
    }
    while (nextLine!=null);
    
    /*
    for(Iterator iter=constrainedTypes.keySet().iterator(); iter.hasNext(); )
    {
      String nexttype=(String)iter.next();
      System.out.println("generated code for "+nexttype+":");
      ((SortedFragments)(constrainedTypes.get(nexttype))).print(System.out);
    }
    */

    return constrainedTypes;
  }

  public static void inject(File inputfile, 
      File outputfile, 
      HashMap codefragments, 
      boolean insertimmediatly)
    throws IOException, InjectorParseException
  {
    //System.out.println("injecting from "+inputfile+" to "+outputfile);

    if(outputfile.exists())
    {
      if(inputfile.getCanonicalPath().equals(outputfile.getCanonicalPath()))
        throw new RuntimeException("error: input file and output file are the same.");
      if(!outputfile.isFile())
        throw new RuntimeException("error: output file is not a regular file.");
    }

    Reader input=null;
    Writer output=null;
    try
    {
      input =new InputStreamReader (new FileInputStream (inputfile));
      output=new OutputStreamWriter(new FileOutputStream(outputfile));
      (new Injector(input, output, new OclInjector(output, codefragments, insertimmediatly))).parseFile();
      input.close();
      output.close();
    }
    catch(InjectorParseException e)
    {
      input.close();
      output.close();
      outputfile.delete();
      throw e;
    }
    catch(IOException e)
    {
      if(input!=null)  input.close();
      if(output!=null) output.close();
      outputfile.delete();
      throw e;
    }
  }

  public static final String TEMPFILE_SUFFIX=".temp_oclinjection";

  public static void inject(File tobemodifiedfile, HashMap codefragments, boolean insertimmediatly)
    throws IOException, InjectorParseException
  {
    File outputfile=new File(tobemodifiedfile.getPath()+TEMPFILE_SUFFIX);
    inject(tobemodifiedfile, outputfile, codefragments, insertimmediatly);
    if(!tobemodifiedfile.delete())
      System.out.println("warning: deleting "+tobemodifiedfile+" failed.");
    if(!outputfile.renameTo(tobemodifiedfile))
      System.out.println("warning: renaming "+outputfile+" to "+tobemodifiedfile+" failed.");
  }

  public static void main (String args[])
  {
    String usage="usage:\n   java tudresden.ocl.injection.Main [options] tobemodified1.java ...\n      --xmi-model model.xmi\n      --constraint-file constraints.txt\n      --reflection-model modelpackage\n      -m : modify files";
    String constraintfile=null;
    String xmimodel=null;
    ArrayList reflectionmodel=new ArrayList();
    boolean modify=false;
    boolean insertimmediatly=false;
    try
    {
      for(int i=0; i<args.length; i++)
      {
        if("--constraint-file".equals(args[i]))
        {
          if(constraintfile!=null)
          {
            System.out.println("can use only one constraint file.");
            System.out.println(usage);
            return;
          }
          i++;
          if(i>=args.length)
          {
            System.out.println("constraint file not given.");
            System.out.println(usage);
            return;
          }
          constraintfile=args[i];
        }
        else if("--xmi-model".equals(args[i]))
        {
          if(xmimodel!=null)
          {
            System.out.println("can use only one xmi model.");
            System.out.println(usage);
            return;
          }
          i++;
          if(i>=args.length)
          {
            System.out.println("xmi file not given.");
            System.out.println(usage);
            return;
          }
          xmimodel=args[i];
        }
        else if("--reflection-model".equals(args[i]))
        {
          i++;
          if(i>=args.length)
          {
            System.out.println("reflection package not given.");
            System.out.println(usage);
            return;
          }
          reflectionmodel.add(args[i]);
        }
        else if("-m".equals(args[i]))
          modify=true;
        else if("--insert-immediatly".equals(args[i]))
          insertimmediatly=true;
        else if(args[i].startsWith("-"))
        {
          System.out.println("unknown option: "+args[i]);
          System.out.println(usage);
          return;
        }
        else
        {
          HashMap codefragments=null;
          if(constraintfile!=null)
          {
            if((xmimodel==null) == (reflectionmodel==null))
            {
              System.out.println("There must be exaxtly one of --xmi-model and --reflect-model");
              System.out.println(usage);
              return;
            }
            ModelFacade modelfacade;
            if(xmimodel!=null)
              modelfacade=tudresden.ocl.check.types.xmifacade.XmiParser.getModel(xmimodel);
            else
              modelfacade=new ReflectionFacade
              (
                (String[])(reflectionmodel.toArray(new String[0])),
                new DefaultReflectionAdapter(),
                new tudresden.ocl.lib.SimpleNameAdapter(),
                new SourceReflectionExtender()
              );
            codefragments=makeCode(new File(constraintfile), modelfacade);
          }
          else
            System.out.println("no constraints given, generating code for @element-type only.");
            
          for(int j=i; j<args.length; j++)
          {
            if(modify)
              inject(new File(args[j]), codefragments, insertimmediatly);
            else
              inject(new File(args[j]), new File(args[j]+".injected"), codefragments, insertimmediatly);
          }
          return;
        }
      }
      System.out.println("nothing to do.");
      System.out.println(usage);
      return;
    }
    catch(InjectorParseException e){System.out.println(e);}
    catch(org.xml.sax.SAXException e){System.out.println(e);}
    catch(IOException e){System.out.println(e);}
  }

}



