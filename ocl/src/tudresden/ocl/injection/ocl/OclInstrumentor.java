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
import java.util.Map;
import java.util.List;
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
import tudresden.ocl.injection.ocl.lib.Invariant;

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
	
	private String lineSeparator;
	
	public void setLineSeparator(final String lineSeparator)
	{
		this.lineSeparator = lineSeparator;
	}
	
	public void onDocComment(JavaClass jc, String doccomment)
	{
	}
	
	private final void processConstraint(final Object o, final String type, final String context)
	{
		if(o instanceof String)
			config.makeConstraint((String)o, type, context);
		else
		{
			for(Iterator i = ((List)o).iterator(); i.hasNext(); )
				config.makeConstraint((String)i.next(), type, context);
		}
	}
	
	public void onClassFeature(final JavaFeature jf, final String doccomment)
	{
		if(!jf.getParent().isInterface() && doccomment!=null)
		{
			final Map map = Injector.extractDocParagraphs(doccomment);
			
			{
				final Object o = map.get("invariant");
				if(o!=null)
					processConstraint(o, "inv", jf.getParent().getName());
			}
			
			if(jf instanceof JavaMethod)
			{
				final JavaMethod jm = (JavaMethod)jf;
				String context = null;
				{
					final Object o = map.get("precondition");
					if(o!=null)
					{
						context = makeContext(jm);
						processConstraint(o, "pre", context);
					}
				}
				{
					final Object o = map.get("postcondition");
					if(o!=null)
					{
						if(context!=null)
							context = makeContext(jm);
						processConstraint(o, "post", context);
					}
				}
			}
		}
	}
	
	private final void fillInSignature(final StringBuffer buf, final JavaMethod jm)
	{
		buf.append(jm.getName());
		buf.append('(');
		for(Iterator i = jm.getParameters().iterator(); i.hasNext(); )
		{
			String type = (String)i.next();
			if("java.lang.String".equals(type))
				type="String";
			else if("byte".equals(type) || "short".equals(type) || "int".equals(type) || "long".equals(type))
				type="Integer";
			else if("float".equals(type) || "double".equals(type))
				type="Real";
			else if("boolean".equals(type))
				type="Boolean";
			
			buf.append(i.next());
			buf.append(" : ");
			buf.append(type);
			if(i.hasNext())
				buf.append(" ; ");
		}
		buf.append(')');
	}
	
	/**
	 * Returns the signature of this method.
	 * Is compatible to
	 * {@link tudresden.ocl.codegen.CodeFragment#getConstrainedOperation()}.
	 */
	private final String makeSignature(final JavaMethod jm)
	{
		final StringBuffer buf = new StringBuffer();
		fillInSignature(buf, jm);
		return buf.toString();
	}
	
	private final String makeContext(final JavaMethod jm)
	{
		final StringBuffer buf = new StringBuffer();
		buf.append(jm.getParent().getName());
		buf.append("::");
		fillInSignature(buf, jm);
		return buf.toString();
	}
	
	public void onAttributeChanged(Writer o, JavaAttribute ja, boolean is_weakly_typed)
	throws IOException
	{
		//o.write("System.out.println(\"notify invariants for attribute '");
		//o.write(ja.getName());
		//o.write("' on object '\"+this+'\\'');");
		//o.write(lineSeparator);
		o.write(Invariant.NOTIFY_OBSERVING_INVARIANTS);
		o.write('(');
		o.write(ja.getName());
		o.write(Invariant.OBSERVER_SUFFIX);
		o.write(");");
		o.write(lineSeparator);
	}
	
	public String getMutex()
	{
		return Invariant.CHECKING_FLAG;
	}
	
	public void onWrapperConstructor(Writer o, JavaConstructor jc)
	throws IOException
	{
		o.write(Invariant.CHECKING_FLAG);
		o.write("=true;");
		o.write(lineSeparator);
		if(hasInvariantScope(jc))
			writeInvariantCall(o);
		o.write(Invariant.CHECKING_FLAG);
		o.write("=false;");
		o.write(lineSeparator);
	}
	
	public void onWrapperDefaultConstructor(Writer o, JavaClass jc)
	throws IOException
	{
		o.write(Invariant.CHECKING_FLAG);
		o.write("=true;");
		o.write(lineSeparator);
		if(hasInvariantScope(jc))
			writeInvariantCall(o);
		o.write(Invariant.CHECKING_FLAG);
		o.write("=false;");
		o.write(lineSeparator);
	}
	
	public void	onWrapperPre(Writer o, JavaMethod jm)
	throws IOException
	{
		o.write(Invariant.CHECKING_FLAG);
		o.write("=true;");
		o.write(lineSeparator);
		if(hasInvariantScope(jm))
			writeInvariantCall(o);
		if(codefragments!=null)
		{
			SortedFragments sf=(SortedFragments)codefragments.get(jm.getParent().getName());
			if(sf!=null)
			{
				final String signature = makeSignature(jm);
				//System.out.println("X "+signature);
				for(Iterator i=sf.transfer.iterator(); i.hasNext(); )
				{
					CodeFragment frag=(CodeFragment)i.next();
					//System.out.println("t "+frag.getConstrainedOperation());
					if(signature.equals(frag.getConstrainedOperation()))
						o.write(frag.getCode());
				}
				for(Iterator i=sf.pre.iterator(); i.hasNext(); )
				{
					CodeFragment frag=(CodeFragment)i.next();
					//System.out.println("p "+frag.getConstrainedOperation());
					if(signature.equals(frag.getConstrainedOperation()))
					{
						o.write('{');
						o.write(frag.getCode());
						o.write("if(!");
						o.write(frag.getResultVariable());
						o.write(".isTrue())");
						o.write(violationmacro);
						o.write("(\"violated ocl precondition '");
						o.write(frag.getName());
						o.write("' on object '\"+this+\"' operation '");
						o.write(frag.getConstrainedOperation());
						o.write("'.\");}");
						o.write(lineSeparator);
					}
				}
				for(Iterator i=sf.preparation.iterator(); i.hasNext(); )
				{
					CodeFragment frag=(CodeFragment)i.next();
					//System.out.println("r "+frag.getConstrainedOperation());
					if(signature.equals(frag.getConstrainedOperation()))
					{
						o.write('{');
						o.write(frag.getCode());
						o.write('}');
						o.write(lineSeparator);
					}
				}
			}
		}
		o.write(Invariant.CHECKING_FLAG);
		o.write("=false;");
		o.write(lineSeparator);
	}
	
	public void	onWrapperPost(Writer o, JavaMethod jm)
	throws IOException
	{
		o.write(Invariant.CHECKING_FLAG);
		o.write("=true;");
		o.write(lineSeparator);
		if(hasInvariantScope(jm))
			writeInvariantCall(o);
		if(codefragments!=null)
		{
			SortedFragments sf=(SortedFragments)codefragments.get(jm.getParent().getName());
			if(sf!=null)
			{
				final String signature = makeSignature(jm);
				//System.out.println("Y "+signature);
				for(Iterator i=sf.post.iterator(); i.hasNext(); )
				{
					CodeFragment frag=(CodeFragment)i.next();
					//System.out.println("o "+frag.getConstrainedOperation());
					if(signature.equals(frag.getConstrainedOperation()))
					{
						o.write('{');
						o.write(lineSeparator);
						o.write(frag.getCode());
						o.write("if(!");
						o.write(frag.getResultVariable());
						o.write(".isTrue())");
						o.write(violationmacro);
						o.write("(\"violated ocl postcondition '");
						o.write(frag.getName());
						o.write("' on object '\"+this+\"' operation '");
						o.write(frag.getConstrainedOperation());
						o.write("'.\");}");
						o.write(lineSeparator);
					}
				}
			}
		}
		o.write(Invariant.CHECKING_FLAG);
		o.write("=false;");
		o.write(lineSeparator);
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
		o.write("/**");
		o.write(lineSeparator);
		o.write("An object representing ocl invariant ");
		o.write(cf.getName());
		o.write(" on this object. Generated automatically, DO NOT CHANGE!");
		o.write(lineSeparator);
		o.write("@author ");
		o.write(Instrumentor.OCL_AUTHOR);
		o.write(lineSeparator);
		o.write("*/private final ");
		o.write(Invariant.class.getName());
		o.write(' ');
		o.write(Invariant.INVARIANT_OBJECT);
		o.write(cf.getName());
		o.write("=new ");
		o.write(Invariant.class.getName());
		o.write("(\"");
		o.write(cf.getName());
		o.write("\", this);");
		o.write("/**");
		o.write(lineSeparator);
		o.write("Checks ocl invariant ");
		o.write(cf.getName());
		o.write(" on this object. Generated automatically, DO NOT CHANGE!");
		o.write(lineSeparator);
		o.write("@author ");
		o.write(Instrumentor.OCL_AUTHOR);
		o.write(lineSeparator);
		o.write("*/public final void ");
		o.write(Invariant.INVARIANT_METHOD);
		o.write(cf.getName());
		o.write("(){");
		o.write(lineSeparator);
		if(tracechecking)
		{
			o.write("System.out.println(\"checking ocl invariant '");
			o.write(cf.getName());
			o.write("' on object '\"+this+\"'.\");");
			o.write(lineSeparator);
		}
		o.write("tudresden.ocl.lib.OclAnyImpl.setFeatureListener(");
		o.write(Invariant.INVARIANT_OBJECT);
		o.write(cf.getName());
		o.write(");");
		o.write(lineSeparator);
		o.write(cf.getCode());
		o.write("tudresden.ocl.lib.OclAnyImpl.clearFeatureListener();");
		o.write(lineSeparator);
		o.write("if(!");
		o.write(cf.getResultVariable());
		o.write(".isTrue())");
		o.write(violationmacro);
		o.write("(\"violated ocl invariant '");
		o.write(cf.getName());
		o.write("' on object '\"+this+\"'");
		if(config.logclass)
			o.write(" of class '\"+getClass().getName()+\"'");
		o.write(".\");");
		o.write(lineSeparator);
		o.write('}');
	}
	
	private final void writeInvariantCall(Writer o)
	throws IOException
	{
		o.write(Invariant.CHECKING_OPERATION);
		o.write("();");
		o.write(lineSeparator);
	}
	
	private final void writeObserver(Writer o, JavaFeature jf)
	throws IOException
	{
		o.write("/**");
		o.write(lineSeparator);
		o.write("Contains observers for modifications of this feature. Generated automatically, DO NOT CHANGE!");
		o.write(lineSeparator);
		o.write("@author ");
		o.write(Instrumentor.OCL_AUTHOR);
		o.write(lineSeparator);
		o.write("@see #");
		o.write(jf.getName());
		o.write(lineSeparator);
		o.write("*/");
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

