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

package tudresden.ocl.check.types;

public class DefaultReflectionAdapter implements ReflectionAdapter {

  public Type getTypeForClass(Class c) {
    if (c==String.class) {
      return tudresden.ocl.check.types.Basic.STRING;
    } else if (c==Integer.class || c==Integer.TYPE) {
      return tudresden.ocl.check.types.Basic.INTEGER;
    } else if (c==Float.class || c==Float.TYPE || c==Double.class || c==Double.TYPE) {
      return tudresden.ocl.check.types.Basic.REAL;
    } else if (c==Boolean.class || c==Boolean.TYPE) {
      return tudresden.ocl.check.types.Basic.BOOLEAN;
    } else if(java.util.Collection.class.isAssignableFrom(c)) 
    {
      int collectionKind;
      if(java.util.Set.class.isAssignableFrom(c) || (tudresden.ocl.lib.Ocl.TAKE_VECTORS_AS_SET && java.util.Vector.class.isAssignableFrom(c)))
        collectionKind=tudresden.ocl.check.types.Collection.SET;
      else if(java.util.List.class.isAssignableFrom(c))
        collectionKind=tudresden.ocl.check.types.Collection.SEQUENCE;
      else
        throw new RuntimeException("encountered a java.util.Collection, which is neither Set or List.");

      return new tudresden.ocl.check.types.Collection(collectionKind, null);
    }
    return null;
  }
}
