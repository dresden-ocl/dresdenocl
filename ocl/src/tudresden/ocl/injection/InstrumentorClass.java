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

import java.util.ArrayList;

final class InstrumentorClass
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
	
	TaskInstrumentor[] taskInstrumentors;

  InstrumentorClass(JavaClass javaclass, TaskConfig[] taskConfigs, boolean delayinsertions)
  {
    this.javaclass=javaclass;
    if(delayinsertions)
      behaviours=new ArrayList();
		
		taskInstrumentors=new TaskInstrumentor[taskConfigs.length];
		for(int i=0; i<taskConfigs.length; i++)
			taskInstrumentors[i]=taskConfigs[i].createTaskInstrumentor();
  }
  
}


