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

package tudresden.ocl.lib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import tudresden.ocl.lib.OclAnyImpl; // for the doc commennts only

/**
   An interface, listening the access to object attributes and
   methods by the ocl library.
   This happens in getFeature methods of OclAnyImpl only.

   @see OclAnyImpl#getFeature(String)
   @see OclAnyImpl#getFeatureQualified(String,Object)
   @see OclAnyImpl#getFeatureAsSequence(String)
   @see OclAnyImpl#getFeature(String,Object[])
*/
public interface FeatureListener
{

  /**
     Is called, when the ocl library accesses an attribute
     of an application object.

     @see OclAnyImpl#getFeature(String)
     @see OclAnyImpl#getFeatureQualified(String,Object)
     @see OclAnyImpl#getFeatureAsSequence(String)
  
     @parameter f the attribute accessed, must not be null.
     @parameter o the application object, must not be null.
  */
  public void onField(Field f, Object o);

  /**
     Is called, when the ocl library accesses a method
     of an application object.
     Note, that the ocl library should access query methods
     (side effect free methods) only, but this is not 
     enforced in any way.

     @see OclAnyImpl#getFeature(String,Object[])
  
     @parameter m the method accessed, must not be null.
     @parameter o the application object, must not be null.
  */
  public void onMethod(Method m, Object o);

}
