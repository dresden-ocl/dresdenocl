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
import java.lang.reflect.Modifier;
import tudresden.ocl.OclTree;
import tudresden.ocl.NameCreator;
import tudresden.ocl.lib.NameAdapter;
import tudresden.ocl.lib.SimpleNameAdapter;
import tudresden.ocl.lib.ArgoNameAdapter;
import tudresden.ocl.parser.OclParserException;
import tudresden.ocl.check.types.*;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.xmifacade.XmiParser;
import tudresden.ocl.codegen.CodeFragment;
import tudresden.ocl.codegen.JavaCodeGenerator;
import tudresden.ocl.injection.lib.Invariant;

final class OclInjector implements InjectionConsumer
{
  private Writer output;
  private HashMap codefragments;
  private boolean delayinsertions;
  private boolean clean;
  private String violationmakro;
  private OclInjectorConfig config;

  /**
     Collects all methods of the current class, except automatically generated methods.
     Is used only, if delayinsertions is true. Otherwise methods is null.
     @see #delayinsertions
     @see JavaMethod
  */
  private ArrayList methods=null;

  /**
     Collects the method attributes of outer classes,
     when operating on a inner class.
     @see #methods
  */
  private ArrayList methods_stack=new ArrayList();
  
  /**
     Collects all attributes of the current class, which have element-type set.
     @see JavaAttribute
  */
  private ArrayList typedAttributes=null;

  /**
     Collects the typedAttribute attributes of outer classes,
     when operating on a inner class.
     @see #typedAttributes
  */
  private ArrayList typedAttributes_stack=new ArrayList();

  /**
     Collects all features of the current class, which should be observed.
     @see JavaFeature
  */
  private ArrayList observedFeatures=null;

  /**
     Collects the observedFeature attributes of outer classes,
     when operating on a inner class.
     @see #observedFeatures
  */
  private ArrayList observedFeatures_stack=new ArrayList();

  
  OclInjector(Writer output, OclInjectorConfig config)
  {
    this.output=output;
    this.codefragments=config.codefragments;
    this.delayinsertions=!config.insertimmediatly;
    this.clean=config.clean;
    this.violationmakro=config.violationmakro;
    this.config=config;
  }
  
  public void onPackage(JavaFile javafile) throws InjectorParseException
  {
  }
  
  public void onImport(String importname)
  {
  }
    
  private boolean discardnextfeature=false;

  public void onClass(JavaClass cc)
  {
    discardnextfeature=false;

    if(clean) return;
    
    methods_stack.add(methods);
    methods=(delayinsertions ? new ArrayList() : null);
    typedAttributes_stack.add(typedAttributes);
    typedAttributes=new ArrayList();
    observedFeatures_stack.add(observedFeatures);
    observedFeatures=new ArrayList();
  }

  public void onClassEnd(JavaClass cc) throws IOException
  {
    if(clean) return;

    if(delayinsertions)
      for(Iterator i=methods.iterator(); i.hasNext(); )
        writeWrapper((JavaMethod)i.next());
    for(Iterator i=observedFeatures.iterator(); i.hasNext(); )
      writeObserver((JavaFeature)i.next());
    writeChangedChecker();
    writeInvariants(cc.getName());

    methods=(ArrayList)
      (methods_stack.remove(methods_stack.size()-1));
    typedAttributes=(ArrayList)
      (typedAttributes_stack.remove(typedAttributes_stack.size()-1));
    observedFeatures=(ArrayList)
      (observedFeatures_stack.remove(observedFeatures_stack.size()-1));
  }
  
  public void onMethodHeader(JavaMethod cf) 
    throws java.io.IOException
  {
    if(clean || cf.isConstructor() || cf.isStatic())
      output.write(cf.getNotWrappedLiteral());
    else
      output.write(cf.getWrappedLiteral());
  }

  private String last_element_type=null;
  private String last_key_type=null;

  public void onClassFeature(JavaFeature cf) 
    throws IOException, InjectorParseException
  {
    if(!clean)
    {
      if(cf instanceof JavaAttribute &&
         !Modifier.isFinal(cf.getModifiers()) &&
         !discardnextfeature)
        observedFeatures.add(cf);
      
      if( cf instanceof JavaMethod && 
          !((JavaMethod)cf).isConstructor() && 
          !cf.isStatic() && 
          !discardnextfeature)
      {
        if(delayinsertions)
          methods.add(cf);
        else
          writeWrapper((JavaMethod)cf);
        
        //if(!"void".equals(cf.getType()))
          //observedFeatures.add(cf);
      }
      boolean notYetAddedToTypedAttributes=true;
      if(last_element_type!=null)
      {
        if(cf instanceof JavaAttribute)
        {
          ((JavaAttribute)cf).setElementType(last_element_type);
          typedAttributes.add(cf);
          notYetAddedToTypedAttributes=false;
        }
        else 
          throw new InjectorParseException("encountered @element-type tag on non-attribute");
        last_element_type=null;
      }
      if(last_key_type!=null)
      {
        if(cf instanceof JavaAttribute)
        {
          ((JavaAttribute)cf).setKeyType(last_key_type);
          if(notYetAddedToTypedAttributes)
            typedAttributes.add(cf);
        }
        else 
          throw new InjectorParseException("encountered @key-type tag on non-attribute");
        last_key_type=null;
      }
    }
    discardnextfeature=false;
  }
  
  public boolean onComment(String comment) throws IOException
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
        last_key_type=Injector.findDocTag(comment, "key-type");
        output.write(comment);
        return true;
      }
    }
    else
    {
      output.write(comment);
      return true;
    }
  }

  public void onFileEnd()
  {
    if(!methods_stack.isEmpty() || 
       !typedAttributes_stack.isEmpty() ||
       !observedFeatures_stack.isEmpty())
      throw new RuntimeException();
  }

  public final void writeInvariants(String classname) throws IOException
  {
    SortedFragments sf=
      codefragments!=null ? (SortedFragments)(codefragments.get(classname)) : null;
    if(sf!=null)
    {
      java.util.Collection v=sf.inv;
      for(Iterator e=v.iterator(); e.hasNext(); )
        writeInvariant(classname, (CodeFragment)(e.next()));
    }
  }
  

  
  public final void writeInvariant(String classname, CodeFragment cf) throws IOException
  {
    Writer o=output;

    o.write("/**\n    An object representing ocl invariant ");
    o.write(cf.getName());
    o.write(" on this object.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n  */private final tudresden.ocl.injection.lib.Invariant ");
    o.write(Invariant.INVARIANT_OBJECT);
    o.write(cf.getName());
    o.write("=new tudresden.ocl.injection.lib.Invariant(\"");
    o.write(cf.getName());
    o.write("\", this);");
    o.write("/**\n    Checks ocl invariant ");
    o.write(cf.getName());
    o.write(" on this object.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n  */public final void ");
    o.write(Invariant.INVARIANT_METHOD);
    o.write(cf.getName());
    o.write("()\n  {\n");
    o.write("    tudresden.ocl.lib.OclAnyImpl.setFeatureListener(");
    o.write(Invariant.INVARIANT_OBJECT);
    o.write(cf.getName());
    o.write(");\n");
    o.write(cf.getCode());
    o.write("    tudresden.ocl.lib.OclAnyImpl.clearFeatureListener();\n");
    o.write("    if(!");
    o.write(cf.getResultVariable());
    o.write(".isTrue()) ");
    o.write(violationmakro);
    o.write("(\"violated ocl invariant '");
    o.write(cf.getName());
    o.write("' on object '\"+this+\"'.\");\n");
    o.write("  }");
  }
  
  /**
     All generated class features get this string as author.
     Must not contain spaces, line breaks or askerics.
     @see Injector#findDocTag
  */
  public static final String OCL_AUTHOR="ocl_injector";

  public final void writeWrapperInvariant() throws IOException
  {
    output.write("      ");
    output.write(Invariant.CHECKING_OPERATION);
    output.write("();\n");
  }
  
  void writeCall(JavaMethod cf) throws IOException
  {
    Writer o=output;
    o.write("      ");
    if(!cf.isConstructor() && !"void".equals(cf.getType()))
      o.write("result=");
    o.write(cf.getWrappedName());
    o.write('(');
    for(Iterator i=cf.getParameters(); i.hasNext(); )
    {
      i.next();
      o.write((String)i.next());
      if(i.hasNext()) o.write(", ");
    }
    o.write(");\n");
  }

  /**
     Returns, whether the type of the given java feature
     is a collection or not.
     May cause problems, as described in findType's 
     documentation.
     @see JavaFile#findType(String)
  */
  private final boolean isCollection(JavaFeature jf)
  {
    Class jftype=jf.getFile().findType(jf.getType());
    return 
      jftype!=null &&
      (
        java.util.Collection.class.isAssignableFrom(jftype) ||
        java.util.Map.class.isAssignableFrom(jftype)
      );
  }
  
  
  public final void writeObserver(JavaFeature cf) throws IOException
  {
    Writer o=output;
    boolean is_collection=isCollection(cf);
    o.write("/**\n    A backup for detecting modifications.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n    @see #");
    o.write(cf.getName());
    o.write("\n  */");
    o.write(Modifier.toString(
        (cf.getModifiers()&Modifier.STATIC)|Modifier.PRIVATE));
    o.write(' ');
    if(is_collection)
      o.write("int");
    else
      o.write(cf.getType());
    o.write(' ');
    o.write(cf.getNotWrappedName());
    o.write(Invariant.BACKUP_SUFFIX);
    o.write('=');
    if(is_collection)
      o.write('0');
    else
      o.write(cf.getNotWrappedName());
    o.write(";/**\n    Contains observers for modifications of this feature.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n    @see #");
    o.write(cf.getName());
    o.write("\n  */");
    o.write(Modifier.toString(
        (cf.getModifiers()&Modifier.STATIC)|Modifier.PUBLIC|Modifier.FINAL));
    o.write(" java.util.HashSet ");
    o.write(cf.getNotWrappedName());
    o.write(Invariant.OBSERVER_SUFFIX);
    o.write("=new java.util.HashSet();");
  }
  

  public static final String CHANGED_CHECKER="checkForChangedFeatures";
  
  public final void writeChangedCheckerCall() throws IOException
  {
    Writer o=output;
    o.write("      ");
    o.write(CHANGED_CHECKER);
    o.write("();\n");
  }

  public final void writeChangedChecker() throws IOException
  {
    Writer o=output;
    o.write("/**\n    Checks object features, whether they have changed.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n  */private void ");
    o.write(CHANGED_CHECKER);
    o.write("()\n  {\n");
    for(Iterator i=observedFeatures.iterator(); i.hasNext(); )
    {
      JavaFeature jf=(JavaFeature)i.next();
      boolean is_collection=isCollection(jf);
      o.write("    if(");
      if(is_collection)
      {
        o.write(Invariant.IDENTITY_HASH_CODE);
        o.write('(');
        o.write(jf.getNotWrappedName());
        o.write(')');
      }
      else
        o.write(jf.getNotWrappedName());
      o.write("!=");
      o.write(jf.getNotWrappedName());
      o.write(Invariant.BACKUP_SUFFIX);
      o.write(")\n    {\n      ");
      o.write(jf.getNotWrappedName());
      o.write(Invariant.BACKUP_SUFFIX);
      o.write('=');
      if(is_collection)
      {
        o.write(Invariant.IDENTITY_HASH_CODE);
        o.write('(');
        o.write(jf.getNotWrappedName());
        o.write(')');
      }
      else
        o.write(jf.getNotWrappedName());
      o.write(";\n");
      
      //o.write("      System.out.println(\"notify invariants for attribute '");
      //o.write(jf.getNotWrappedName());
      //o.write("' on object '\"+this+'\\'');\n");
      
      if(jf instanceof JavaAttribute)
        writeTypeChecker((JavaAttribute)jf);
      
      o.write("      ");
      o.write(Invariant.NOTIFY_OBSERVING_INVARIANTS);
      o.write('(');
      o.write(jf.getNotWrappedName());
      o.write(Invariant.OBSERVER_SUFFIX);
      o.write(");\n    }\n");
    }
    o.write("  }");
  }
  

  private final int getInvariantScope(int modifier)
  {
    modifier&=
      Modifier.PRIVATE|
      Modifier.PROTECTED|
      Modifier.PUBLIC;
    
    switch(modifier)
    {
      case Modifier.PRIVATE:   return OclInjectorConfig.INVARIANT_SCOPE_PRIVATE;
      case Modifier.PROTECTED: return OclInjectorConfig.INVARIANT_SCOPE_PROTECTED;
      case 0:                  return OclInjectorConfig.INVARIANT_SCOPE_PACKAGE;
      case Modifier.PUBLIC:    return OclInjectorConfig.INVARIANT_SCOPE_PUBLIC;
      default:
        throw new RuntimeException();
    }
  }
  
  private final boolean hasInvariantScope(JavaMethod jm)
  {
    return
      (getInvariantScope(jm.getModifiers()) >= config.invariantScope);
  }
  
  public final void writeWrapper(JavaMethod cf) throws IOException
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
      Modifier.toString(cf.getModifiers()&~Modifier.ABSTRACT);
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
    if(!cf.isConstructor() && !"void".equals(cf.getType()))
    {
      o.write("    ");
      o.write(cf.getType());
      o.write(" result;\n");
    }
    o.write("    if(");
    o.write(Invariant.CHECKING_FLAG);
    o.write(")\n");
    writeCall(cf);
    o.write("    else\n    {\n      ");
    o.write(Invariant.CHECKING_FLAG);
    o.write("=true;\n");
    if(!cf.isConstructor())
    {
      writeChangedCheckerCall();
      if(hasInvariantScope(cf))
        writeWrapperInvariant();
      if(codefragments!=null)
      {
        SortedFragments sf=(SortedFragments)codefragments.get(cf.getParent().getName());
        if(sf!=null)
        {
          for(Iterator i=sf.transfer.iterator(); i.hasNext(); )
          {
            CodeFragment frag=(CodeFragment)i.next();
            if(cf.getSignature().equals(frag.getConstrainedOperation()))
              o.write(frag.getCode());
          }
          for(Iterator i=sf.pre.iterator(); i.hasNext(); )
          {
            CodeFragment frag=(CodeFragment)i.next();
            if(cf.getSignature().equals(frag.getConstrainedOperation()))
            {
              o.write("      {\n");
              o.write(frag.getCode());
              o.write("        if(!");
              o.write(frag.getResultVariable());
              o.write(".isTrue())\n          ");
              o.write(violationmakro);
              o.write("(\"violated ocl precondition '");
              o.write(frag.getName());
              o.write("' on object '\"+this+\"' operation '");
              o.write(frag.getConstrainedOperation());
              o.write("'.\");\n      }\n");
            }
          }
          for(Iterator i=sf.preparation.iterator(); i.hasNext(); )
          {
            CodeFragment frag=(CodeFragment)i.next();
            if(cf.getSignature().equals(frag.getConstrainedOperation()))
            {
              o.write("      {\n");
              o.write(frag.getCode());
              o.write("      }\n");
            }
          }
        }
      }
    }
    o.write("      ");
    o.write(Invariant.CHECKING_FLAG);
    o.write("=false;\n");
    writeCall(cf);
    o.write("      ");
    o.write(Invariant.CHECKING_FLAG);
    o.write("=true;\n");
    writeChangedCheckerCall();
    if(hasInvariantScope(cf))
      writeWrapperInvariant();
    if(codefragments!=null)
    {
      SortedFragments sf=(SortedFragments)codefragments.get(cf.getParent().getName());
      if(sf!=null)
        for(Iterator i=sf.post.iterator(); i.hasNext(); )
        {
          CodeFragment frag=(CodeFragment)i.next();
          if(cf.getSignature().equals(frag.getConstrainedOperation()))
          {
            o.write("      {\n");
            o.write(frag.getCode());
            o.write("        if(!");
            o.write(frag.getResultVariable());
            o.write(".isTrue())\n          ");
            o.write(violationmakro);
            o.write("(\"violated ocl postcondition '");
            o.write(frag.getName());
            o.write("' on object '\"+this+\"' operation '");
            o.write(frag.getConstrainedOperation());
            o.write("'.\");\n      }\n");
          }
        }
    }
    o.write("      ");
    o.write(Invariant.CHECKING_FLAG);
    o.write("=false;\n    }\n");
    if(!cf.isConstructor() && !"void".equals(cf.getType()))
      o.write("    return result;\n");
    o.write("  }");
  }

  private final void writeTypeChecker(JavaAttribute jf) throws IOException
  {
    writeTypeChecker(jf, Invariant.CHECK_ELEMENT_TYPES, jf.getElementType());
    writeTypeChecker(jf, Invariant.CHECK_KEY_TYPES,     jf.getKeyType());
  }

  private final void writeTypeChecker(JavaAttribute jf, String kind, String type) 
    throws IOException
  {
    if(type!=null)
    {
      Writer o=output;
      o.write("      if(!");
      o.write(kind);
      o.write('(');
      o.write(jf.getName());
      o.write(',');
      o.write(type);
      o.write(".class)) ");
      o.write(violationmakro);
      o.write("(\"");
      if(kind==Invariant.CHECK_ELEMENT_TYPES)
        o.write("element");
      else if(kind==Invariant.CHECK_KEY_TYPES)
        o.write("key");
      else
        throw new RuntimeException();
      o.write(" type checker failed at feature '");
      o.write(jf.getName());
      o.write("' of object \"+this+\".\");\n");
    }
  }

}
  
final class OclInjectorConfig
{
  HashMap codefragments=null;
  boolean insertimmediatly=false;
  boolean clean=false;
  String violationmakro=null;

  static final int INVARIANT_SCOPE_PRIVATE  =0;
  static final int INVARIANT_SCOPE_PROTECTED=1;
  static final int INVARIANT_SCOPE_PACKAGE  =2;
  static final int INVARIANT_SCOPE_PUBLIC   =3;
  static final int INVARIANT_SCOPE_EXPLICIT =4;
  int invariantScope=INVARIANT_SCOPE_PRIVATE;
}

public class Main
{
  public static HashMap makeCode(File constraintfile,  ModelFacade modelfacade)
    throws OclParserException, OclTypeException, IOException
  {
    BufferedReader br=new BufferedReader(new FileReader(constraintfile));
    NameCreator namecreator=new NameCreator();
    JavaCodeGenerator jcg=new JavaCodeGenerator("this", "result");
    jcg.setInitialIndent(8);
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
          //System.out.println("Loaded constraint:");
          //System.out.println(constraintString);
          //System.out.println("Parsing constraint.");
          OclTree constraintTree=OclTree.createTree(constraintString, modelfacade);
          constraintTree.setNameCreator(namecreator);
          //System.out.println("Type checking constraint.");
          constraintTree.assureTypes();
          //System.out.println("Normalizing.");
          constraintTree.applyDefaultNormalizations();
          //System.out.println("Generating Code.");
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

  public static void inject(File inputfile, File outputfile, OclInjectorConfig conf)
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
      (new Injector(input, output, new OclInjector(output, conf))).parseFile();
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

  public static void inject(File tobemodifiedfile, OclInjectorConfig conf)
    throws IOException, InjectorParseException
  {
    File outputfile=new File(tobemodifiedfile.getPath()+TEMPFILE_SUFFIX);
    inject(tobemodifiedfile, outputfile, conf);
    if(!tobemodifiedfile.delete())
      System.out.println("warning: deleting "+tobemodifiedfile+" failed.");
    if(!outputfile.renameTo(tobemodifiedfile))
      System.out.println("warning: renaming "+outputfile+" to "+tobemodifiedfile+" failed.");
  }

  public static void main (String args[])
  {
    String usage=
      "usage:\n"+
      "java tudresden.ocl.injection.Main [options] tobemodified1.java ...\n"+
      "  -f  --constraint-file constraints.txt\n"+
      "  -x  --xmi-model model.xmi\n"+
      "      the model given as xmi file\n"+
      "  -r  --reflection-model modelpackage\n"+
      "      the model given by reflection\n"+
      "  -n  --name-adapter [simple|argo]\n"+
      "      the nameadapter\n"+
      "  -is --invariant-scope [all|private|protected|package|public|explicit]\n"+
      "      the scope of invariants\n"+
      "  -vm --violation-makro makro\n"+
      "      what to to, if a constraint fails.\n"+
      "  -c  --clean\n"+
      "      clean files\n"+
      "  -m  --modify\n"+
      "      modify files";
    String constraintfile=null;
    String xmimodel=null;
    ArrayList reflectionmodel=new ArrayList();
    NameAdapter nameadapter=null;
    boolean modify=false;
    ArrayList sourcefiles=new ArrayList();
    OclInjectorConfig conf=new OclInjectorConfig();
    try
    {
      for(int i=0; i<args.length; i++)
      {
        if("--constraint-file".equals(args[i])||"-f".equals(args[i]))
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
        else if("--xmi-model".equals(args[i])||"-x".equals(args[i]))
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
        else if("--reflection-model".equals(args[i])||"-r".equals(args[i]))
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
        else if("--name-adapter".equals(args[i])||"-n".equals(args[i]))
        {
          if(nameadapter!=null)
          {
            System.out.println("can use only one name adapter.");
            System.out.println(usage);
            return;
          }
          i++;
          if(i>=args.length)
          {
            System.out.println("name adapter not given.");
            System.out.println(usage);
            return;
          }
          if("simple".equals(args[i]))
            nameadapter=new SimpleNameAdapter();
          else if("argo".equals(args[i]))
            nameadapter=new ArgoNameAdapter();
          else
          {
            System.out.println("name adapter must be 'simple' or 'argo'.");
            System.out.println(usage);
            return;
          }
        }
        else if("--invariant-scope".equals(args[i])||"-is".equals(args[i]))
        {
          i++;
          if(i>=args.length)
          {
            System.out.println("invariant scope not given.");
            System.out.println(usage);
            return;
          }
          if("private".equals(args[i])||"all".equals(args[i]))
            conf.invariantScope=conf.INVARIANT_SCOPE_PRIVATE;
          else if("protected".equals(args[i]))
            conf.invariantScope=conf.INVARIANT_SCOPE_PROTECTED;
          else if("package".equals(args[i]))
            conf.invariantScope=conf.INVARIANT_SCOPE_PACKAGE;
          else if("public".equals(args[i]))
            conf.invariantScope=conf.INVARIANT_SCOPE_PUBLIC;
          else if("explicit".equals(args[i]))
            conf.invariantScope=conf.INVARIANT_SCOPE_EXPLICIT;
          else
          {
            System.out.println("invariant scope must be 'all', 'private', 'protected', 'package', 'public' or 'explicit'.");
            System.out.println(usage);
            return;
          }
        }
        else if("--violation-makro".equals(args[i])||"-vm".equals(args[i]))
        {
          if(conf.violationmakro!=null)
          {
            System.out.println("can use only one violation makro.");
            System.out.println(usage);
            return;
          }
          i++;
          if(i>=args.length)
          {
            System.out.println("violation makro not given.");
            System.out.println(usage);
            return;
          }
          conf.violationmakro=args[i];
        }
        else if("--modify".equals(args[i])||"-m".equals(args[i]))
          modify=true;
        else if("--clean".equals(args[i])||"-c".equals(args[i]))
          conf.clean=true;
        else if("--insert-immediatly".equals(args[i]))
          conf.insertimmediatly=true;
        else if(args[i].startsWith("-"))
        {
          System.out.println("unknown option: "+args[i]);
          System.out.println(usage);
          return;
        }
        else
        {
          for(; i<args.length; i++)
            sourcefiles.add(args[i]);
        }
      }
    
      if(sourcefiles.isEmpty())
      {
        System.out.println("nothing to do.");
        System.out.println(usage);
        return;
      }

      if(conf.violationmakro==null)
        conf.violationmakro="System.out.println";

      if(conf.clean)
        System.out.println("cleaning code.");
      else if(constraintfile==null)
        System.out.println("no constraints given, generating code for @element-type only.");
      else
      {
        if((xmimodel==null) == (reflectionmodel==null))
        {
          System.out.println("There must be exaxtly one of --xmi-model and --reflect-model");
          System.out.println(usage);
          return;
        }
        ModelFacade modelfacade;
        if(xmimodel!=null)
          modelfacade=tudresden.ocl.check.types.xmifacade.XmiParser.getModel(xmimodel,xmimodel);
        else
        {
          if(nameadapter==null)
            nameadapter=new SimpleNameAdapter();
          modelfacade=new ReflectionFacade
          (
            (String[])(reflectionmodel.toArray(new String[0])),
            new DefaultReflectionAdapter(),
            nameadapter,
            new SourceReflectionExtender()
          );
        }
        conf.codefragments=makeCode(new File(constraintfile), modelfacade);
      }
            
      for(Iterator i=sourcefiles.iterator(); i.hasNext(); )
      {
        String s=(String)i.next();
        if(modify)
          inject(new File(s), conf);
        else
          inject(new File(s), new File(s+".injected"), conf);
      }
    }
    catch(InjectorParseException e){System.out.println(e);}
    catch(org.xml.sax.SAXException e){System.out.println(e);}
    catch(IOException e){System.out.println(e);}
  }

}



