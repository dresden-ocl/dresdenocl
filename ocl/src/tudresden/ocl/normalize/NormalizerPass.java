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
import tudresden.ocl.parser.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.parser.analysis.*;
import tudresden.ocl.OclTree;

/** This class implements TreeNormalizer to traverse the abstract syntax tree
 *  and call a list of NodeNormalizers for each of its nodes.
 *
 *  @see NodeNormalizer
 *  @author Frank Finger
 */
public class NormalizerPass extends DepthFirstAdapter implements TreeNormalizer {

  protected ArrayList myNodeNormalizer;
  protected OclTree tree;

  public NormalizerPass() {
    myNodeNormalizer=new ArrayList();
  }

  /** This method normalizes the abstract syntax tree by starting to travere
   *  the tree via calling the given Start nodes apply method. In subsequent
   *  calls to this objects defaultIn method, the NodeNormalizers will be
   *  invoked.
   */
  public void normalize(OclTree t) {
    tree=t;
    openNormalizers(t);
    if (! myNodeNormalizer.isEmpty() ) {
      t.apply(this);
    }
    closeNormalizers(t);
  }

  public void defaultIn(Node n) {
    Iterator iter=iterator();
    while(iter.hasNext()) {
      NodeNormalizer nn = (NodeNormalizer) iter.next();
      nn.normalize(n, tree);
    }
  }

  public void add(NodeNormalizer nn) {
    myNodeNormalizer.add(nn);
  }

  /** @return an Iterator through the list of NodeNormalizers
   */
  public Iterator iterator() {
    return myNodeNormalizer.iterator();
  }

  protected void openNormalizers(OclTree t) {
    Iterator iter=iterator();
    while (iter.hasNext()) {
      NodeNormalizer nn=(NodeNormalizer) iter.next();
      nn.open(t, this);
    }
  }

  protected void closeNormalizers(OclTree t) {
    Iterator iter=iterator();
    while (iter.hasNext()) {
      NodeNormalizer nn=(NodeNormalizer) iter.next();
      nn.close(t, this);
    }
  }

} /* end class NormalizerPass */

