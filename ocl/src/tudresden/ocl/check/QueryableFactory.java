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

package tudresden.ocl.check;

import tudresden.ocl.OclTree;
import tudresden.ocl.check.types.TypeFactory;

/** an object of this type is used to tell an <code>OclTree</code> object
 *  which implementation of <code>NameBoundQueryable</code> or
 *  <code>TypeQuerable</code> it should use
 *
 *  @see tudresden.ocl.OclTree#setQueryableFactory(QueryableFactory qf)
 */
public interface QueryableFactory {

  /** @param nqb the <CODE>NameBoundQueryable</CODE> the client that is requesting
   *             a <CODE>TypeQueryable</CODE> aquired before through
   *             <CODE>getNameBoundQueryable</CODE>; <CODE>null</CODE>, if the
   *             client did not do that
   */
  public TypeQueryable getTypeQueryable(NameBoundQueryable nbq, OclTree tree, TypeFactory tf);

  /** @param tq the <CODE>TypeQueryable</CODE> the client that is requesting
   *             a <CODE>NameBoundQueryable</CODE> aquired before through
   *             <CODE>getTypeQueryable</CODE>; <CODE>null</CODE>, if the
   *             client did not do that
   */
  public NameBoundQueryable getNameBoundQueryable(TypeQueryable tq, OclTree tree, TypeFactory tf);

}
