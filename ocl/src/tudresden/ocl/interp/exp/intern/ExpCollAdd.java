/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Interpreter                                                   *
 * Copyright (C) 2002 Nikolai Krambrock (nikk@gmx.de)                *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Construction, University Of Technology Aachen, Germany            *
 * (http://www-lufgi3.informatik.rwth-aachen.de).                    *
 * It was done in co-operation with Software & Design and Managment  *
 * Troisdorf, Germany (http://www.sdm.de)                            *
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
 * this project, please visit the project home page:                 *
 * http://dresden-ocl.sourceforge.net                                * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl.interp.exp.intern;

import java.util.Collection;
import java.util.Iterator;

import tudresden.ocl.lib.OclAny;
import tudresden.ocl.lib.OclCollection;
import tudresden.ocl.lib.OclInteger;
import tudresden.ocl.lib.OclRoot;


/**
 * This Expression takes a collection and returns a new collection  with added
 * elements (Set(1,5,7)) or with a range of elements (Set(1..127)).
 */
public class ExpCollAdd extends Exp {
  Exp coll;
  Collection addToColl;
  Exp low;
  Exp high;

  public ExpCollAdd(Exp coll, Exp low, Exp high) {
    this.coll = coll;
    this.low = low;
    this.high = high;
  }

  public ExpCollAdd(Exp coll, Collection addToColl) {
    this.coll = coll;
    this.addToColl = addToColl;
  }

  public OclRoot evaluateInternal() {
    if (addToColl != null) {
      return addEvaluate();
    } else {
      return rangeEvaluate();
    }
  }

  private OclCollection addEvaluate() {
    OclCollection oclColl = (OclCollection)coll.evaluate();
    for (Iterator i = addToColl.iterator(); i.hasNext();) {
      Exp element = (Exp)i.next();
      oclColl.setToInclude((OclAny)element.evaluate());
    }
    return oclColl;
  }

  private OclCollection rangeEvaluate() {
    OclCollection oclColl = (OclCollection)coll.evaluate();
    oclColl.setToRange((OclInteger)low.evaluate(), (OclInteger)high.evaluate());
    return oclColl;
  }

  public Object[] children() {
    if (addToColl != null) {
      return new Object[] {coll, addToColl};
    } else {
      return new Object[] {coll, low, high};
    }
  }
}