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

import tudresden.ocl.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.lib.*;
import java.util.*;

public class SableOclFactory extends DefaultOclFactory {

  OclTree tree;

  public SableOclFactory(OclTree tree) {
    this.tree=tree;
  }

  public OclRoot getOclRepresentationFor(Object o) {
    if (o instanceof List) {
      List original=(List)o;
      ArrayList list=new ArrayList( original.size() );
      Iterator iter=original.iterator();
      while (iter.hasNext()) {
        list.add( getOclRepresentationFor( iter.next() ) );
      }
      OclSequence seq=new OclSequence(list);
      return seq;
    } else if (o instanceof Node) {
      return new SableOclAnyImpl((Node)o, tree);
    } else {
      return super.getOclRepresentationFor(o);
    }
  }
}
