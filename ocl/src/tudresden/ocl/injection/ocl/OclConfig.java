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

import java.util.HashMap;
import java.io.IOException;
import tudresden.ocl.OclTree;
import tudresden.ocl.injection.TaskConfig;
import tudresden.ocl.injection.TaskInstrumentor;
import tudresden.ocl.NameCreator;
import tudresden.ocl.codegen.JavaCodeGenerator;
import tudresden.ocl.codegen.CodeFragment;
import tudresden.ocl.check.types.ModelFacade;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.parser.OclParserException;

public final class OclConfig implements TaskConfig
{
	/**
	 * Type names are keys, SortedFragments values.
	 */
	public final HashMap codefragments = new HashMap();
	
	public boolean tracechecking = false;
	
	public String violationmacro;
	
	public boolean logclass = false;
	
	
	public static final int INVARIANT_SCOPE_PRIVATE  =0;
	public static final int INVARIANT_SCOPE_PROTECTED=1;
	public static final int INVARIANT_SCOPE_PACKAGE  =2;
	public static final int INVARIANT_SCOPE_PUBLIC   =3;
	public static final int INVARIANT_SCOPE_EXPLICIT =4;
	public int invariantScope=INVARIANT_SCOPE_PRIVATE;
	
	public JavaCodeGenerator jcg=new JavaCodeGenerator("this", "result");
	{
		jcg.setInitialIndent(8);
	}
	
	public ModelFacade modelfacade;
	
	public final NameCreator namecreator = new NameCreator();
	
	public TaskInstrumentor createTaskInstrumentor()
	{
		return new OclInstrumentor(this);
	}
	
	public void makeConstraint(String text, String kind, String context)
	throws OclParserException, OclTypeException
	{
		String constraintString="context "+context+' '+kind+' '+text;
		//System.out.println("found inline constraint: >"+constraintString+"<");
		try
		{
			makeConstraint(constraintString);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e.toString());
		}
	}
	
	public void makeConstraint(String constraintString)
	throws OclParserException, OclTypeException, IOException
	{
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
			SortedFragments sf=(SortedFragments)(codefragments.get(ct));
			if(sf==null)
				codefragments.put(ct, new SortedFragments(frags[j]));
			else
				sf.addFragment(frags[j]);
		}
	}
	
}


