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

package tudresden.ocl.injection.ocl;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.lang.reflect.Modifier;
import tudresden.ocl.injection.*;
import tudresden.ocl.OclTree;
import tudresden.ocl.NameCreator;
import tudresden.ocl.lib.NameAdapter;
import tudresden.ocl.lib.SimpleNameAdapter;
import tudresden.ocl.lib.ArgoNameAdapter;
import tudresden.ocl.parser.OclParserException;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.codegen.CodeFragment;
import tudresden.ocl.codegen.JavaCodeGenerator;
import tudresden.ocl.injection.lib.Invariant;

public final class OclInstrumentor implements TaskInstrumentor
{
	private OclConfig config;
	
	/**
	 * Type names are keys, SortedFragments values.
	 */
	private HashMap codefragments;
	
	private String violationmacro;
	
	private boolean tracechecking;
	
	public OclInstrumentor(final OclConfig config)
	{
		this.config = config;
		this.codefragments = config.codefragments;
		this.violationmacro = config.violationmacro;
		this.tracechecking = config.tracechecking;
	}
	
	public void onDocComment(JavaClass jc, String doccomment)
	{
		if(!jc.isInterface())
		{
			addInvariant    (jc, Injector.extractDocParagraphs(doccomment, "invariant"));
			addPrecondition (jc, Injector.extractDocParagraphs(doccomment, "precondition"));
			addPostcondition(jc, Injector.extractDocParagraphs(doccomment, "postcondition"));
		}
	}
	
	private final void addInvariant(JavaClass jc, String[] text)
	{
		if(text==null) return;
		for(int i=0; i<text.length; i++)
			config.makeConstraint(text[i], "inv", jc.getName());
	}
	
	private final void addPrecondition(JavaClass jc, String[] text)
	{
		if(text==null) return;
		for(int i=0; i<text.length; i++)
			config.makeConstraint(text[i], "pre", jc.getName());
	}
	
	private final void addPostcondition(JavaClass jc, String[] text)
	{
		if(text==null) return;
		for(int i=0; i<text.length; i++)
			config.makeConstraint(text[i], "post", jc.getName());
	}
	
	public void onAttributeChanged(Writer o, JavaAttribute ja, boolean is_weakly_typed)
	throws IOException
	{
		//o.write("      System.out.println(\"notify invariants for attribute '");
		//o.write(ja.getName());
		//o.write("' on object '\"+this+'\\'');\n");
		o.write("      ");
		o.write(Invariant.NOTIFY_OBSERVING_INVARIANTS);
		o.write('(');
		o.write(ja.getName());
		o.write(Invariant.OBSERVER_SUFFIX);
		o.write(");\n");
	}
	
	public String getMutex()
	{
		return Invariant.CHECKING_FLAG;
	}
	
	public void onWrapperConstructor(Writer o, JavaConstructor jc)
	throws IOException
	{
		o.write("    ");
		o.write(Invariant.CHECKING_FLAG);
		o.write("=true;\n");
		if(hasInvariantScope(jc))
			writeInvariantCall(o);
		o.write("    ");
		o.write(Invariant.CHECKING_FLAG);
		o.write("=false;\n");
	}
	
	public void onWrapperDefaultConstructor(Writer o, JavaClass jc)
	throws IOException
	{
		o.write("    ");
		o.write(Invariant.CHECKING_FLAG);
		o.write("=true;\n");
		if(hasInvariantScope(jc))
			writeInvariantCall(o);
		o.write("    ");
		o.write(Invariant.CHECKING_FLAG);
		o.write("=false;\n");
	}
	
	public void	onWrapperPre(Writer o, JavaMethod jm)
	throws IOException
	{
		o.write("      ");
		o.write(Invariant.CHECKING_FLAG);
		o.write("=true;\n");
		if(hasInvariantScope(jm))
			writeInvariantCall(o);
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
	}
	
	public void	onWrapperPost(Writer o, JavaMethod jm)
	throws IOException
	{
		o.write("      ");
		o.write(Invariant.CHECKING_FLAG);
		o.write("=true;\n");
		if(hasInvariantScope(jm))
			writeInvariantCall(o);
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
		o.write("=false;\n");
	}
	
	public void onClassEndPerFeature(Writer o, JavaFeature jf)
	throws IOException
	{
		writeObserver(o, jf);
	}
	
	public void onClassEnd(Writer o, JavaClass jc)
	throws IOException
	{
		writeInvariants(o, jc.getName());
	}
	
	private final void writeInvariants(Writer o, String classname)
	throws IOException
	{
		SortedFragments sf=
		codefragments!=null ? (SortedFragments)(codefragments.get(classname)) : null;
		if(sf!=null)
		{
			java.util.Collection v=sf.inv;
			for(Iterator e=v.iterator(); e.hasNext(); )
				writeInvariant(o, classname, (CodeFragment)(e.next()));
		}
	}
	
	private final void writeInvariant(Writer o, String classname, CodeFragment cf) throws IOException
	{
		o.write("/**\n    An object representing ocl invariant ");
		o.write(cf.getName());
		o.write(" on this object.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
		o.write(Instrumentor.OCL_AUTHOR);
		o.write("\n  */private final tudresden.ocl.injection.lib.Invariant ");
		o.write(Invariant.INVARIANT_OBJECT);
		o.write(cf.getName());
		o.write("=new tudresden.ocl.injection.lib.Invariant(\"");
		o.write(cf.getName());
		o.write("\", this);");
		o.write("/**\n    Checks ocl invariant ");
		o.write(cf.getName());
		o.write(" on this object.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
		o.write(Instrumentor.OCL_AUTHOR);
		o.write("\n  */public final void ");
		o.write(Invariant.INVARIANT_METHOD);
		o.write(cf.getName());
		o.write("()\n  {\n");
		if(tracechecking)
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
	
	private final void writeInvariantCall(Writer o)
	throws IOException
	{
		o.write("      ");
		o.write(Invariant.CHECKING_OPERATION);
		o.write("();\n");
	}
	
	private final void writeObserver(Writer o, JavaFeature jf)
	throws IOException
	{
		o.write("/**\n    Contains observers for modifications of this feature.\n    Generated automatically, DO NOT CHANGE!\n    @author ");
		o.write(Instrumentor.OCL_AUTHOR);
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
	
	private final int getInvariantScope(int modifier)
	{
		modifier&=
		Modifier.PRIVATE|
		Modifier.PROTECTED|
		Modifier.PUBLIC;
		
		switch(modifier)
		{
			case Modifier.PRIVATE:   return config.INVARIANT_SCOPE_PRIVATE;
			case Modifier.PROTECTED: return config.INVARIANT_SCOPE_PROTECTED;
			case 0:                  return config.INVARIANT_SCOPE_PACKAGE;
			case Modifier.PUBLIC:    return config.INVARIANT_SCOPE_PUBLIC;
			default:
				throw new RuntimeException();
		}
	}
	
	private final boolean hasInvariantScope(JavaFeature jf)
	{
		return
		(getInvariantScope(jf.getModifiers()) >= config.invariantScope);
	}
	
}

