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

import tudresden.ocl.codegen.JavaCodeGenerator;
import tudresden.ocl.check.types.ModelFacade;
import tudresden.ocl.NameCreator;
import tudresden.ocl.injection.lib.HashExact;
import java.util.HashMap;

final class InstrumentorConfig
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
  boolean logclass=false;


  static final int INVARIANT_SCOPE_PRIVATE  =0;
  static final int INVARIANT_SCOPE_PROTECTED=1;
  static final int INVARIANT_SCOPE_PACKAGE  =2;
  static final int INVARIANT_SCOPE_PUBLIC   =3;
  static final int INVARIANT_SCOPE_EXPLICIT =4;
  int invariantScope=INVARIANT_SCOPE_PRIVATE;
	
	public TaskConfig[] taskConfigs;
}


