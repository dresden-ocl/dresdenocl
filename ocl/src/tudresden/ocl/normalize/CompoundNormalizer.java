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
// FILE: d:/projekte/diplom/classes/tudresden/ocl/normalize/CompoundNormalizer.java

package tudresden.ocl.normalize;

import java.util.*;
import tudresden.ocl.parser.*;
import tudresden.ocl.OclTree;

/** This class represents a TreeNormalizer that normalizes an abstract syntax
 *  tree by starting other TreeNormalizers in a fixed order. Usually, an object
 *  of this class will be the "top level" normalizer and contain several
 *  NormalizerPass objects.
 *
 *  @see NormalizerPass
 *
 *  @author Frank Finger
 */
public class CompoundNormalizer implements TreeNormalizer {

  protected ArrayList myTreeNormalizers;

  public CompoundNormalizer() {
    myTreeNormalizers=new ArrayList();
  }

  public void add(TreeNormalizer tn) {
    myTreeNormalizers.add(tn);
  }

  /** normalize a abstract syntax tree given by its Start node by calling
   *  the "normalize" method of all its tree normalizer
   */
  public void normalize(OclTree t) {
    Iterator iter=iterator();
    while (iter.hasNext()) {
      TreeNormalizer tn=(TreeNormalizer)iter.next();
      tn.normalize(t);
    }
  }

  /** @return an Iterator through the list of TreeNormalizers
   */
  public Iterator iterator() {
    return myTreeNormalizers.iterator();
  }
} /* end class CompoundNormalizer */

