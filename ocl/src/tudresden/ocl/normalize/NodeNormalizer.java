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
import tudresden.ocl.OclTree;
import tudresden.ocl.parser.node.*;

/** By implementing this interface, normalizations on tree nodes
 *  are possible. More complex normalization steps might need to be implemented
 *  as TreeNormalizers.
 *
 *  The class NormalizerPass, objects of which usually invoke NodeNormalizer
 *  objects, traverses an AST left-recursive, and calls the method normalize
 *  with each tree node <em>before</em> descending further "down" the tree (the
 *  trees root is on top, of course...). Modifications to the node will take
 *  effect immediatle, i.e. they will effect the further recursion.
 *
 *  For token nodes, which are leaf nodes, the method normalize is not called.
 *
 *  @see TreeNormalizer
 *
 *  @author Frank Finger
 */
public interface NodeNormalizer {

  /** normalize a single node
   */
  public void normalize(Node n, OclTree tree);

  /** allows the NodeNormalizers to initialize itself. In this method,
   *  a NodeNormalizer typically makes sure that the AST fulfills the
   *  preconditions it requires and then registers its own postconditions.
   */
  public void open(OclTree tree, NormalizerPass pass);

  /** This method notifies the NodeNormalizer of the end of a tree
   *  normalization. Here new invariants on the tree can be registered that
   *  were not registered in <code>open()</code> already
   */
  public void close(OclTree tree, NormalizerPass pass);

} /* end interface NodeNormalizer */

