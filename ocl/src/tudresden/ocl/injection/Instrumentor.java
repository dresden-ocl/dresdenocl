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

import java.io.IOException;
import java.io.Writer;
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
import tudresden.ocl.codegen.CodeFragment;
import tudresden.ocl.codegen.JavaCodeGenerator;
import tudresden.ocl.injection.lib.Invariant;
import tudresden.ocl.injection.lib.Check;
import tudresden.ocl.injection.lib.HashExact;
import tudresden.ocl.injection.lib.HashSize;
import tudresden.ocl.injection.lib.HashModCount;
import tudresden.ocl.injection.lib.WrapperDummy;

final class Instrumentor implements InjectionConsumer
{
  private Writer output;
  private HashMap codefragments;
  private boolean delayinsertions;
  private boolean clean;
  private String violationmacro;
  private InstrumentorConfig config;
  private String identityhashcode;


  /**
     Holds several properties of the class currently
     worked on.
  */
  private InstrumentorClass class_state=null;

  /**
     Collects the class states of outer classes,
     when operating on a inner class.
     @see #class_state
     @element-type InstrumentorClass
  */
  private ArrayList class_state_stack=new ArrayList();


  Instrumentor(Writer output, InstrumentorConfig config)
  {
    this.output=output;
    this.codefragments=config.codefragments;
    this.delayinsertions=!config.insertimmediately;
    this.clean=config.clean;
    this.violationmacro=config.violationmacro;
    this.config=config;
    this.identityhashcode=config.hashmode.getName()+".identityHashCode";
  }

  public void onPackage(JavaFile javafile)
    throws InjectorParseException
  {
  }

  public void onImport(String importname)
  {
  }
    
  private boolean discardnextfeature=false;

  public void onClass(JavaClass jc)
  {
    discardnextfeature=false;

    class_state_stack.add(class_state);
    class_state=new InstrumentorClass(jc, config.taskConfigs, delayinsertions);
  }

  public void onClassEnd(JavaClass jc)
    throws IOException, InjectorParseException
  {
    if(!clean && !jc.isInterface())
    {
      if(delayinsertions)
        for(Iterator i=class_state.behaviours.iterator(); i.hasNext(); )
          writeWrapper((JavaBehaviour)i.next());
      if(!class_state.has_constructors)
        writeDefaultConstructor(jc);
      for(Iterator i=class_state.observedFeatures.iterator(); i.hasNext(); )
        writeObserver((JavaFeature)i.next());
      writeChangedChecker();
      writeInvariants(jc.getName());
    }

    if(class_state.javaclass!=jc)
      throw new RuntimeException();
    class_state=(InstrumentorClass)
      (class_state_stack.remove(class_state_stack.size()-1));
  }
  
  public void onBehaviourHeader(JavaBehaviour jb)
    throws java.io.IOException
  {
    if(clean || 
       jb.isStatic() || 
       jb.isAbstract() ||
       class_state.javaclass.isInterface())
    {
      output.write(jb.getLiteral());
    }
    else
    {
      output.write(jb.getWrappedLiteral());
    }

    if(!clean)
    {
      if(jb instanceof JavaConstructor)
        class_state.has_constructors=true;
    }
  }

  public void onAttributeHeader(JavaAttribute ja)
  {
  }

  public void onClassFeature(JavaFeature jf, String doccomment) 
    throws IOException, InjectorParseException
  {
    if(!clean && !class_state.javaclass.isInterface())
    {
      if(jf instanceof JavaAttribute &&
         !Modifier.isFinal(jf.getModifiers()) &&
         !discardnextfeature)
        class_state.observedFeatures.add(jf);
      
      if(jf instanceof JavaBehaviour && 
         !jf.isStatic() &&
         !jf.isAbstract() && 
         !discardnextfeature)
      {
        if(delayinsertions)
          class_state.behaviours.add(jf);
        else
          writeWrapper((JavaBehaviour)jf);
        
        //if(!"void".equals(jf.getType()))
          //observedFeatures.add(jf);
      }

      String element_type=null;
      String key_type=null;
      if(doccomment!=null)
      {
        element_type=Injector.findDocTag(doccomment, "element-type");
        key_type=    Injector.findDocTag(doccomment, "key-type");
      }

      boolean notYetAddedToTypedAttributes=true;
      if(element_type!=null)
      {
        if(jf instanceof JavaAttribute)
        {
          ((JavaAttribute)jf).setElementType(element_type);
          class_state.typedAttributes.add(jf);
          notYetAddedToTypedAttributes=false;
        }
        else 
          throw new InjectorParseException("encountered @element-type tag on non-attribute");
      }
      if(key_type!=null)
      {
        if(jf instanceof JavaAttribute)
        {
          ((JavaAttribute)jf).setKeyType(key_type);
          if(notYetAddedToTypedAttributes)
            class_state.typedAttributes.add(jf);
        }
        else 
          throw new InjectorParseException("encountered @key-type tag on non-attribute");
      }
    }
    discardnextfeature=false;
  }

  public boolean onDocComment(String doccomment)
    throws IOException
  {
    if(OCL_AUTHOR.equals(Injector.findDocTag(doccomment, "author")))
    {
      discardnextfeature=true;
      return false;
    }
    else
    {
      output.write(doccomment);
      if(!clean && !class_state.javaclass.isInterface())
      {
        addInvariant    (Injector.extractDocParagraphs(doccomment, "invariant"));
        addPrecondition (Injector.extractDocParagraphs(doccomment, "precondition"));
        addPostcondition(Injector.extractDocParagraphs(doccomment, "postcondition"));
      }
      return true;
    }
  }

  public void onFileDocComment(String doccomment)
    throws IOException
  {
    output.write(doccomment);
  }

  public void onFileEnd()
  {
    if(!class_state_stack.isEmpty())
      throw new RuntimeException();
  }
  
  private final void addInvariant(String[] text)
  {
    if(text==null) return;
    for(int i=0; i<text.length; i++)
      Main.makeConstraint(text[i], "inv", class_state.javaclass.getName(), config);
  }

  private final void addPrecondition(String[] text)
  {
    if(text==null) return;
    for(int i=0; i<text.length; i++)
      Main.makeConstraint(text[i], "pre", class_state.javaclass.getName(), config);
  }

  private final void addPostcondition(String[] text)
  {
    if(text==null) return;
    for(int i=0; i<text.length; i++)
      Main.makeConstraint(text[i], "post", class_state.javaclass.getName(), config);
  }

  private final void writeInvariants(String classname) 
    throws IOException
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
  


  private final void writeInvariant(String classname, CodeFragment cf) throws IOException
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
    if(config.tracechecking)
    {
      o.write("    System.out.println(\"checking ocl invariant '");
      o.write(cf.getName());
      o.write("' on object '\"+this+\"'.\");\n");
    }
    o.write("    tudresden.ocl.lib.OclAnyImpl.setFeatureListener(");
    o.write(Invariant.INVARIANT_OBJECT);
    o.write(cf.getName());
    o.write(");\n");
    o.write(cf.getCode());
    o.write("    tudresden.ocl.lib.OclAnyImpl.clearFeatureListener();\n");
    o.write("    if(!");
    o.write(cf.getResultVariable());
    o.write(".isTrue()) ");
    o.write(violationmacro);
    o.write("(\"violated ocl invariant '");
    o.write(cf.getName());
    o.write("' on object '\"+this+\"'");
    if(config.logclass)
      o.write(" of class '\"+getClass().getName()+\"'");
    o.write(".\");\n");
    o.write("  }");
  }

  /**
     All generated class features get this string as author.
     Must not contain spaces, line breaks or askerics.
     @see Injector#findDocTag
  */
  public static final String OCL_AUTHOR="ocl_injector";

  private final void writeInvariantCall() 
    throws IOException
  {
    output.write("      ");
    output.write(Invariant.CHECKING_OPERATION);
    output.write("();\n");
  }
  
  private final void writeCall(JavaMethod jm) 
    throws IOException
  {
    Writer o=output;
    
    o.write("      ");
    if(!"void".equals(jm.getType()))
      o.write("result=");
    o.write(jm.getWrappedName());
    o.write('(');
    for(Iterator i=jm.getParameters(); i.hasNext(); )
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
    throws InjectorParseException
  {
    Class jftype=jf.getFile().findType(jf.getType());
    return 
      jftype!=null &&
      (
        jftype.isArray() ||
        java.util.Collection.class.isAssignableFrom(jftype) ||
        java.util.Map.class.isAssignableFrom(jftype)
      );
  }
  
  /**
     Returns, whether the type of the given java feature
     can be typed by element-type tags or not.
     Return the same as {@link #isCollection(JavaFeature)},
     except for arrays, where it returns false.
     May cause problems, as described in findType's 
     documentation.
     @see JavaFile#findType(String)
  */
  private final boolean isWeaklyTyped(JavaFeature jf)
    throws InjectorParseException
  {
    Class jftype=jf.getFile().findType(jf.getType());
    return 
      jftype!=null &&
      (
        java.util.Collection.class.isAssignableFrom(jftype) ||
        java.util.Map.class.isAssignableFrom(jftype)
      );
  }
  
  
  private final void writeObserver(JavaFeature jf) 
    throws IOException, InjectorParseException
  {
    Writer o=output;
    
    boolean is_collection=isCollection(jf);
    o.write("/**\n    A backup for detecting modifications.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n    @see #");
    o.write(jf.getName());
    o.write("\n  */");
    o.write(Modifier.toString(
        (jf.getModifiers()&Modifier.STATIC)|Modifier.PRIVATE));
    o.write(' ');
    if(is_collection)
      o.write("int");
    else
      o.write(jf.getType());
    o.write(' ');
    o.write(jf.getName());
    o.write(Invariant.BACKUP_SUFFIX);
    o.write('=');
    if(is_collection)
      o.write('0');
    else
      o.write(jf.getName());
    o.write(";/**\n    Contains observers for modifications of this feature.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n    @see #");
    o.write(jf.getName());
    o.write("\n  */");
    o.write(Modifier.toString(
        (jf.getModifiers()&Modifier.STATIC)|Modifier.PUBLIC|Modifier.FINAL));
    o.write(" java.util.HashSet ");
    o.write(jf.getName());
    o.write(Invariant.OBSERVER_SUFFIX);
    o.write("=new java.util.HashSet();");
  }
  

  public static final String CHANGED_CHECKER="checkForChangedFeatures";
  
  private final void writeChangedCheckerCall() 
    throws IOException
  {
    Writer o=output;
    
    o.write("      ");
    o.write(CHANGED_CHECKER);
    o.write("();\n");
  }

  private final void writeChangedChecker() 
    throws IOException, InjectorParseException
  {
    Writer o=output;
    
    o.write("/**\n    Checks object features, whether they have changed.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n  */private void ");
    o.write(CHANGED_CHECKER);
    o.write("()\n  {\n");
    for(Iterator i=class_state.observedFeatures.iterator(); i.hasNext(); )
    {
      JavaFeature jf=(JavaFeature)i.next();
      boolean is_collection=isCollection(jf);
      boolean is_weakly_typed=isWeaklyTyped(jf);
      o.write("    if(");
      if(is_collection)
      {
        o.write(identityhashcode);
        o.write('(');
        o.write(jf.getName());
        o.write(')');
      }
      else
        o.write(jf.getName());
      o.write("!=");
      o.write(jf.getName());
      o.write(Invariant.BACKUP_SUFFIX);
      o.write(")\n    {\n      ");
      o.write(jf.getName());
      o.write(Invariant.BACKUP_SUFFIX);
      o.write('=');
      if(is_collection)
      {
        o.write(identityhashcode);
        o.write('(');
        o.write(jf.getName());
        o.write(')');
      }
      else
        o.write(jf.getName());
      o.write(";\n");
      
      //o.write("      System.out.println(\"notify invariants for attribute '");
      //o.write(jf.getName());
      //o.write("' on object '\"+this+'\\'');\n");
      
      if(jf instanceof JavaAttribute)
        writeTypeChecker((JavaAttribute)jf);
			
			for(int j=0; j<class_state.taskInstrumentors.length; j++)
				class_state.taskInstrumentors[j].onAttributeChanged(o, (JavaAttribute)jf, is_weakly_typed);
      
      o.write("      ");
      o.write(Invariant.NOTIFY_OBSERVING_INVARIANTS);
      o.write('(');
      o.write(jf.getName());
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
      case Modifier.PRIVATE:   return InstrumentorConfig.INVARIANT_SCOPE_PRIVATE;
      case Modifier.PROTECTED: return InstrumentorConfig.INVARIANT_SCOPE_PROTECTED;
      case 0:                  return InstrumentorConfig.INVARIANT_SCOPE_PACKAGE;
      case Modifier.PUBLIC:    return InstrumentorConfig.INVARIANT_SCOPE_PUBLIC;
      default:
        throw new RuntimeException();
    }
  }

  private final boolean hasInvariantScope(JavaFeature jf)
  {
    return
      (getInvariantScope(jf.getModifiers()) >= config.invariantScope);
  }
  
  private final void writeWrapperHeader(JavaBehaviour jb)
    throws IOException
  {
    Writer o=output;
    
    String modifierString=Modifier.toString(jb.getModifiers());
    if(modifierString.length()>0)
    {
      o.write(modifierString);
      o.write(' ');
    }
    if(jb.getType()!=null)
    {
      o.write(jb.getType());
      o.write(' ');
    }
    o.write(jb.getName());
    o.write('(');
    for(Iterator i=jb.getParameters(); i.hasNext(); )
    {
      o.write((String)i.next());
      o.write(' ');
      o.write((String)i.next());
      if(i.hasNext()) o.write(", ");
    }
    o.write(')');
    Iterator throwables=jb.getThrowables();
    if(throwables.hasNext())
    {
      o.write(" throws ");
      while(throwables.hasNext())
      {
        o.write((String)throwables.next());
        if(throwables.hasNext()) o.write(", ");
      }
    }
  }

  private final void writeWrapper(JavaBehaviour jb) 
    throws IOException
  {
    if(jb instanceof JavaConstructor)
      writeWrapper((JavaConstructor)jb);
    else
      writeWrapper((JavaMethod)jb);
  }

  private final void writeWrapper(JavaConstructor jc)
    throws IOException
  {
    Writer o=output;
    
    o.write("/**\n    A wrapper for checking ocl constraints.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n    @see #");
    o.write(jc.getName());
    o.write('(');
    for(Iterator i=jc.getParameters(); i.hasNext(); )
    {
      o.write((String)(i.next()));
      i.next();
      if(i.hasNext()) o.write(", ");
    }
    if(jc.getParameters().hasNext())
      o.write(", ");
    o.write(WrapperDummy.class.getName());
    o.write(")\n  */");
    writeWrapperHeader(jc);
    o.write("\n  {\n");
    o.write("    this(");
    for(Iterator i=jc.getParameters(); i.hasNext(); )
    {
      i.next();
      o.write((String)i.next());
      o.write(", ");
    }
    o.write('(');
    o.write(WrapperDummy.class.getName());
    o.write(")null);\n");
    o.write("    ");
    o.write(Invariant.CHECKING_FLAG);
    o.write("=true;\n");
    if(hasInvariantScope(jc))
      writeInvariantCall();
    o.write("    ");
    o.write(Invariant.CHECKING_FLAG);
    o.write("=false;\n");
    o.write("  }");
  }

  private final void writeWrapper(JavaMethod jm) 
    throws IOException
  {
    Writer o=output;
    
    o.write("/**\n    A wrapper for checking ocl constraints.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n    @see #");
    o.write(jm.getWrappedName());
    o.write('(');
    for(Iterator i=jm.getParameters(); i.hasNext(); )
    {
      o.write((String)(i.next()));
      i.next();
      if(i.hasNext()) o.write(", ");
    }
    o.write(")\n  */");
    writeWrapperHeader(jm);
    o.write("\n  {\n");
    if(!"void".equals(jm.getType()))
    {
      o.write("    ");
      o.write(jm.getType());
      o.write(" result;\n");
    }
    o.write("    if(");
    o.write(Invariant.CHECKING_FLAG);
    o.write(")\n");
    writeCall(jm);
    o.write("    else\n    {\n      ");
    o.write(Invariant.CHECKING_FLAG);
    o.write("=true;\n");
    writeChangedCheckerCall();
    if(hasInvariantScope(jm))
      writeInvariantCall();
    if(codefragments!=null)
    {
      SortedFragments sf=(SortedFragments)codefragments.get(jm.getParent().getName());
      if(sf!=null)
      {
        for(Iterator i=sf.transfer.iterator(); i.hasNext(); )
        {
          CodeFragment frag=(CodeFragment)i.next();
          if(jm.getSignature().equals(frag.getConstrainedOperation()))
            o.write(frag.getCode());
        }
        for(Iterator i=sf.pre.iterator(); i.hasNext(); )
        {
          CodeFragment frag=(CodeFragment)i.next();
          if(jm.getSignature().equals(frag.getConstrainedOperation()))
          {
            o.write("      {\n");
            o.write(frag.getCode());
            o.write("        if(!");
            o.write(frag.getResultVariable());
            o.write(".isTrue())\n          ");
            o.write(violationmacro);
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
          if(jm.getSignature().equals(frag.getConstrainedOperation()))
          {
            o.write("      {\n");
            o.write(frag.getCode());
            o.write("      }\n");
          }
        }
      }
    }
    o.write("      ");
    o.write(Invariant.CHECKING_FLAG);
    o.write("=false;\n");
    writeCall(jm);
    o.write("      ");
    o.write(Invariant.CHECKING_FLAG);
    o.write("=true;\n");
    writeChangedCheckerCall();
    if(hasInvariantScope(jm))
      writeInvariantCall();
    if(codefragments!=null)
    {
      SortedFragments sf=(SortedFragments)codefragments.get(jm.getParent().getName());
      if(sf!=null)
        for(Iterator i=sf.post.iterator(); i.hasNext(); )
        {
          CodeFragment frag=(CodeFragment)i.next();
          if(jm.getSignature().equals(frag.getConstrainedOperation()))
          {
            o.write("      {\n");
            o.write(frag.getCode());
            o.write("        if(!");
            o.write(frag.getResultVariable());
            o.write(".isTrue())\n          ");
            o.write(violationmacro);
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
    if(!"void".equals(jm.getType()))
      o.write("    return result;\n");
    o.write("  }");
  }

  /**
     See Java Language Specification 8.6.7
     &quot;Default Constructor&quot
  */
  private final void writeDefaultConstructor(JavaClass jc) 
    throws IOException
  {
    Writer o=output;
    
    o.write("/**\n    A default constructor for checking ocl constraints,\n    replacing the automatically generated constructor.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
    o.write(OCL_AUTHOR);
    o.write("\n  */");
    if(Modifier.isPublic(jc.getModifiers()))
      o.write("public ");
    o.write(jc.getName());
    o.write("()\n  {\n");
    o.write("    ");
    o.write(Invariant.CHECKING_FLAG);
    o.write("=true;\n");
    if(hasInvariantScope(jc))
      writeInvariantCall();
    o.write("    ");
    o.write(Invariant.CHECKING_FLAG);
    o.write("=false;\n");
    o.write("  }");
  }
  
  
  // type checking
  
  private final void writeTypeChecker(JavaAttribute ja) 
    throws IOException
  {
    writeTypeChecker(ja, Check.CHECK_ELEMENT_TYPES, ja.getElementType());
    writeTypeChecker(ja, Check.CHECK_KEY_TYPES,     ja.getKeyType());
  }

  private final void writeTypeChecker(JavaAttribute ja, String kind, String type) 
    throws IOException
  {
    if(type!=null)
    {
      Writer o=output;
      o.write("      if(!");
      o.write(kind);
      o.write('(');
      o.write(ja.getName());
      o.write(',');
      o.write(type);
      o.write(".class)) ");
      o.write(violationmacro);
      o.write("(\"");
      if(kind==Check.CHECK_ELEMENT_TYPES)
        o.write("element");
      else if(kind==Check.CHECK_KEY_TYPES)
        o.write("key");
      else
        throw new RuntimeException();
      o.write(" type checker failed at feature '");
      o.write(ja.getName());
      o.write("' of object \"+this+\".\");\n");
    }
  }
  
}


