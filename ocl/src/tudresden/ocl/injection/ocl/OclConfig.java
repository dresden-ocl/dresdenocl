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
import tudresden.ocl.injection.TaskConfig;
import tudresden.ocl.injection.TaskInstrumentor;
import tudresden.ocl.NameCreator;
import tudresden.ocl.codegen.JavaCodeGenerator;
import tudresden.ocl.check.types.ModelFacade;

public final class OclConfig implements TaskConfig
{
	/**
	 * Type names are keys, SortedFragments values.
	 */
	public HashMap codefragments = new HashMap();
	
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
	public NameCreator namecreator=new NameCreator();
	
	public TaskInstrumentor createTaskInstrumentor()
	{
		return new OclInstrumentor(this);
	}
	
}


