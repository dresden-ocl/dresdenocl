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

import tudresden.ocl.check.*;

public class OclState implements Any {

  public Type navigateQualified(String name, Type[] qualifiers) throws OclTypeException {
    Type t=Basic.navigateAnyQualified(name, this, qualifiers);
    throw new OclTypeException("OclState has no attributes");
  }

  public Type navigateParameterized(String name, Type[] params) throws OclTypeException {
    Type t=Basic.navigateAnyParameterized(name, params);
    throw new OclTypeException("OclState has no attributes");
  }

  public boolean hasState(String name) {
    return false;
  }

  public boolean conformsTo(Type t) {
    return this.equals(t);
  }

  public boolean equals(Object o) {
    if (o instanceof OclState) {
      return true;
    } else {
      return false;
    }
  }

  public int hashCode() {
    return 17;
  }

  public String toString() {
    return "OclState";
  }

}
