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

import java.lang.reflect.*;
import java.util.*;

public class SourceReflectionExtender implements tudresden.ocl.check.types.ReflectionExtender
{
  /**
     Maps from java.lang.reflect.Field to java.lang.Class.
     This is the cache for the extender query.
  */
  private HashMap elementtypes=new HashMap();
  
  /**
     Contains all classes, for which the source code has been attempted to parse.
     Serves as negative cache for source code or @elementtype tags not found.
  */
  private HashSet parsedclasses=new HashSet();
  
  public Class getElementType(Field field)
  {
    Class result=(Class)elementtypes.get(field);
    if(result!=null)
      return result;
    
    final Class fieldclass=field.getDeclaringClass();
    if(parsedclasses.contains(fieldclass))
      return null;

    System.out.println("getElementType(): "+field);
    if(!java.util.Collections.class.isAssignableFrom(fieldclass))
      System.out.println("sehr merkwuerdig.");
    
    Class declaringclass=fieldclass;
    while(fieldclass.getDeclaringClass()!=null)
      declaringclass=fieldclass.getDeclaringClass();

    return null;
  }
}  
