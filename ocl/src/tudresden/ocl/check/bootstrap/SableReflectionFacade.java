/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package tudresden.ocl.check.bootstrap;

import tudresden.ocl.check.types.*;
import tudresden.ocl.lib.NameAdapter;

public class SableReflectionFacade extends ReflectionFacade {

  public SableReflectionFacade(String[] packageNames, ReflectionAdapter reflAdapter, NameAdapter nameAdapter) {
    super(packageNames, reflAdapter, nameAdapter);
  }

  protected ClassAny getClassAny(Class c) {
      ClassAny ret=(ClassAny)classAnyInstances.get(c);
      if (ret==null) {
        ret=new ReflectionClassAny(c);
        classAnyInstances.put(c, ret);
      }
      return ret;
  }

  class ReflectionClassAny extends ClassAny {

    protected ReflectionClassAny(Class c) {
      super(c, SableReflectionFacade.this);
    }

    public Type navigateQualified(String name, Type[] qualifiers) {
      if (qualifiers==null || qualifiers.length==0) {
        if (name.equals("boundNames")) {
          return new Collection(Collection.SET, Basic.STRING);
        }
        if (name.equals("subnodes") || name.equals("supernodes")) {
          return new Collection(
            Collection.SET,
            getClassAny(tudresden.ocl.parser.node.Node.class)
          );
        }
      }
      return super.navigateQualified(name, qualifiers);
    }

    public boolean hasState(String stateName) {
      return false;
    }
  }

}
