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
import tudresden.ocl.codegen.CodeFragment;
import tudresden.ocl.codegen.JavaCodeGenerator;
import tudresden.ocl.injection.lib.Invariant;
import tudresden.ocl.injection.lib.HashExact;
import tudresden.ocl.injection.lib.HashSize;
import tudresden.ocl.injection.lib.HashModCount;
import tudresden.ocl.injection.lib.WrapperDummy;
import tudresden.ocl.injection.lib.TypeTracer;


final class ClassState
{
  
  JavaClass javaclass;
  
  /**
     Collects all behavioral features of the current class, 
     except automatically generated features.
     Is used only, if delayinsertions is true. Otherwise it is null.
     @see OclInjector#delayinsertions
     @see JavaBehaviour
     @element-type JavaBehaviour
  */
  ArrayList behaviours=null;

  /**
     Collects all attributes of the current class, 
     which have element-type or key-type set.
     @see JavaAttribute
     @element-type JavaAttribute
  */
  ArrayList typedAttributes=new ArrayList();

  /**
     Collects all features of the current class, which should be observed.
     @see JavaFeature
     @element-type JavaFeature
  */
  ArrayList observedFeatures=new ArrayList();
    
  /**
     Whether this class has at least one constructor or not.
     Is used to decide, whether the default constructor has
     to be replaced by {@link OclInjector#writeDefaultConstructor}.
  */
  boolean has_constructors=false;

  ClassState(JavaClass javaclass, boolean delayinsertions)
  {
    this.javaclass=javaclass;
    if(delayinsertions)
      behaviours=new ArrayList();
  }
  
}

final class OclInjector implements InjectionConsumer
{
  private Writer output;
  private HashMap codefragments;
  private boolean delayinsertions;
  private boolean clean;
  private String violationmacro;
  private OclInjectorConfig config;
  private String identityhashcode;


  /**
     Holds several properties of the class currently
     worked on.
  */
  private ClassState class_state=null;

  /**
     Collects the class states of outer classes,
     when operating on a inner class.
     @see #class_state
     @element-type ClassState
  */
  private ArrayList class_state_stack=new ArrayList();


  OclInjector(Writer output, OclInjectorConfig config)
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
    class_state=new ClassState(jc, delayinsertions);
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
    class_state=(ClassState)
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
      
      if(is_weakly_typed&&config.tracetypes)
        writeTypeTracer((JavaAttribute)jf);
      
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
      case Modifier.PRIVATE:   return OclInjectorConfig.INVARIANT_SCOPE_PRIVATE;
      case Modifier.PROTECTED: return OclInjectorConfig.INVARIANT_SCOPE_PROTECTED;
      case 0:                  return OclInjectorConfig.INVARIANT_SCOPE_PACKAGE;
      case Modifier.PUBLIC:    return OclInjectorConfig.INVARIANT_SCOPE_PUBLIC;
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
    writeTypeChecker(ja, Invariant.CHECK_ELEMENT_TYPES, ja.getElementType());
    writeTypeChecker(ja, Invariant.CHECK_KEY_TYPES,     ja.getKeyType());
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
      if(kind==Invariant.CHECK_ELEMENT_TYPES)
        o.write("element");
      else if(kind==Invariant.CHECK_KEY_TYPES)
        o.write("key");
      else
        throw new RuntimeException();
      o.write(" type checker failed at feature '");
      o.write(ja.getName());
      o.write("' of object \"+this+\".\");\n");
    }
  }
  
  
  // type tracing
  
  private final void writeTypeTracer(JavaAttribute ja)
    throws IOException
  {
    Writer o=output;

    o.write("      ");
    o.write(TypeTracer.TRACE_TYPES);
    o.write("(\"");
    o.write(ja.getFullDocName());
    o.write("\", ");
    o.write(ja.getName());
    o.write(");\n");
  }

}

final class OclInjectorConfig
{
  /**
     Type names are keys, SortedFragments values.
  */
  HashMap codefragments=new HashMap();

  JavaCodeGenerator jcg=new JavaCodeGenerator("this", "result");
  {
    jcg.setInitialIndent(8);
  }

  ModelFacade modelfacade;
  NameCreator namecreator=new NameCreator();

  boolean insertimmediately=false;
  boolean tracechecking=false;
  Class hashmode=HashExact.class;
  boolean clean=false;
  String violationmacro=null;
  boolean tracetypes=false;
  boolean logclass=false;


  static final int INVARIANT_SCOPE_PRIVATE  =0;
  static final int INVARIANT_SCOPE_PROTECTED=1;
  static final int INVARIANT_SCOPE_PACKAGE  =2;
  static final int INVARIANT_SCOPE_PUBLIC   =3;
  static final int INVARIANT_SCOPE_EXPLICIT =4;
  int invariantScope=INVARIANT_SCOPE_PRIVATE;
}

public class Main
{

  public static void makeConstraint(String text,
                                    String kind,
                                    String context,
                                    OclInjectorConfig conf)
    throws OclParserException, OclTypeException
  {
    String constraintString="context "+context+' '+kind+' '+text;
    //System.out.println("found inline constraint: >"+constraintString+"<");
    try
    {
      makeConstraint(constraintString, conf);
    }
    catch(IOException e) { throw new RuntimeException(e.toString()); }
  }

  public static void makeConstraint(String constraintString, OclInjectorConfig conf)
    throws OclParserException, OclTypeException, IOException
  {
    //System.out.println("Loaded constraint:");
    //System.out.println(constraintString);
    //System.out.println("Parsing constraint.");
    OclTree constraintTree=OclTree.createTree(constraintString, conf.modelfacade);
    constraintTree.setNameCreator(conf.namecreator);
    //System.out.println("Type checking constraint.");
    constraintTree.assureTypes();
    //System.out.println("Normalizing.");
    constraintTree.applyDefaultNormalizations();
    //System.out.println("Generating Code.");
    CodeFragment[] frags=conf.jcg.getCode(constraintTree);
    for (int j=0; j<frags.length; j++)
    {
      String ct=frags[j].getConstrainedType();
      SortedFragments sf=(SortedFragments)(conf.codefragments.get(ct));
      if(sf==null)
        conf.codefragments.put(ct, new SortedFragments(frags[j]));
      else
        sf.addFragment(frags[j]);
    }
  }

  public static void makeCode(File constraintfile, OclInjectorConfig conf)
    throws OclParserException, OclTypeException, IOException
  {
    BufferedReader br=new BufferedReader(new FileReader(constraintfile));

    String nextLine;
    String nextConstraint="";
    do
    {
      nextLine=br.readLine();
      if(nextLine==null || nextLine.trim().equals(""))
      {
        if(!nextConstraint.equals(""))
        {
          makeConstraint(nextConstraint, conf);
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
      throw new InjectorParseException(inputfile+": "+e.getMessage());
    }
    catch(tudresden.ocl.check.OclTypeException e)
    {
      input.close();
      output.close();
      outputfile.delete();
      throw new tudresden.ocl.check.OclTypeException(inputfile+": "+e.getMessage());
    }
    catch(tudresden.ocl.parser.OclParserException e)
    {
      input.close();
      output.close();
      outputfile.delete();
      throw new tudresden.ocl.parser.OclParserException(inputfile+": "+e.getMessage());
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
      "  -r  --reflection-model modelpackage\n"+
      "      the model given by reflection\n"+
      "  -n  --name-adapter [none|argo]\n"+
      "      the nameadapter\n"+
      "  -is --invariant-scope [all|private|protected|package|public|explicit]\n"+
      "      the scope of invariants\n"+
      "  -vm --violation-macro macro\n"+
      "      what to to, if a constraint fails.\n"+
      "  -tt --trace-types\n"+
      "      trace types of collection elements.\n"+
      "  -c  --clean\n"+
      "      clean files\n"+
      "  -m  --modify\n"+
      "      modify files";
    String constraintfile=null;
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
          if("none".equals(args[i]))
            nameadapter=new SimpleNameAdapter();
          else if("argo".equals(args[i]))
            nameadapter=new ArgoNameAdapter();
          else
          {
            System.out.println("name adapter must be 'none' or 'argo'.");
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
        else if("--violation-macro".equals(args[i])||"-vm".equals(args[i]))
        {
          if(conf.violationmacro!=null)
          {
            System.out.println("can use only one violation macro.");
            System.out.println(usage);
            return;
          }
          i++;
          if(i>=args.length)
          {
            System.out.println("violation macro not given.");
            System.out.println(usage);
            return;
          }
          conf.violationmacro=args[i];
        }
        else if("--trace-types".equals(args[i])||"-tt".equals(args[i]))
          conf.tracetypes=true;
        else if("--modify".equals(args[i])||"-m".equals(args[i]))
          modify=true;
        else if("--clean".equals(args[i])||"-c".equals(args[i]))
          conf.clean=true;
        else if("--insert-immediately".equals(args[i]))
          conf.insertimmediately=true;
        else if("--trace-checking".equals(args[i]))
          conf.tracechecking=true;
        else if("--simple-hash".equals(args[i]))
          conf.hashmode=HashSize.class;
        else if("--modcount-hash".equals(args[i]))
          conf.hashmode=HashModCount.class;
        else if("--log-class".equals(args[i]))
          conf.logclass=true;
        else if(args[i].startsWith("-"))
        {
          System.out.println("unknown option: "+args[i]);
          System.out.println(usage);
          return;
        }
        else
        {
          for(; i<args.length; i++)
            expand(sourcefiles, args[i]);
        }
      }

      if(sourcefiles.isEmpty())
      {
        System.out.println("nothing to do.");
        System.out.println(usage);
        return;
      }

      if(conf.clean&&constraintfile!=null)
      {
        System.out.println("cannot combine --clean and --constraint-file.");
        System.out.println(usage);
        return;
      }

      if(conf.violationmacro==null)
        conf.violationmacro="System.out.println";

      if(nameadapter==null)
        nameadapter=new SimpleNameAdapter();
      conf.modelfacade=new ReflectionFacade
      (
        (String[])(reflectionmodel.toArray(new String[0])),
        new DefaultReflectionAdapter(),
        nameadapter,
        new SourceReflectionExtender()
      );

      if(constraintfile!=null)
      {
        makeCode(new File(constraintfile), conf);
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
    catch(IOException e){System.out.println(e);}
  }
	
	public static void expand(java.util.Collection files, String pattern)
	  throws IOException
	{
		if(pattern.endsWith("*.java"))
		{
			//System.out.println("expanding "+pattern);
			String directoryName = pattern.substring(0,pattern.length()-"*.java".length());
			File directory = new File(directoryName);
			if(!directory.isDirectory())
				throw new IOException(directoryName+" should be a directory");
			File[] expandedFiles = directory.listFiles(new FileFilter()
			{
				public boolean accept(File file)
				{
					return
						file.isFile() &&
						file.getName().endsWith(".java");
				}
			});
			//for(int i=0; i<expandedFiles.length; i++) System.out.println("  into "+expandedFiles[i].getPath());
			for(int i=0; i<expandedFiles.length; i++)
			  files.add(expandedFiles[i].getPath());
		}
		else
			files.add(pattern);
	}

}



