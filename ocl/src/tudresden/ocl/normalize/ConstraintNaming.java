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
package tudresden.ocl.normalize;

import java.util.*;
import tudresden.ocl.*;
import tudresden.ocl.parser.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.check.*;

/** assures that constraints have a name
 *
 * @author Frank Finger
 */
public class ConstraintNaming implements NodeNormalizer {

  public void normalize(Node n, OclTree tree) {
    if (n instanceof AConstraintBody) {
      AConstraintBody cbody=(AConstraintBody) n;
      if (cbody.getName()==null) {
        String name=cbody.getStereotype().toString().trim(); // inv, pre or post
        name=name.substring(0,1).toUpperCase()+name.substring(1); // Inv, Pre or Post
        cbody.setName(
          new TName( tree.getNameCreator().getUniqueName(name) )
        );
      }
    }
  }

  public void open(OclTree tree, NormalizerPass pass) {
    tree.assureInvariant("context AConstraintBody inv : name -> size = 1");
  }

  public void close(OclTree tree, NormalizerPass pass) {
  }

} /* end class ConstraintNaming */

