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
package tudresden.ocl;

import java.util.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.parser.analysis.*;

/** A class that provides unique names for the processing of OCL expressions.
 *  Such names are necessary for normalization and for generating Java code.
 *  A generated name consist of three parts: a prefix which is usually
 *  "tuddOcl", a part called infix that can be set by the caller (might be
 *  something like "Var" or "Inv", and a postfix part chosen by NameCreator to
 *  make the name unique.
 *
 *  A special instance of this class can be accessed through the getInstance
 *  method, but a strict singleton pattern is not enforced to allow several
 *  name spaces.
 *
 *  @author Frank Finger
 */
public class NameCreator {

  public static String defaultPrefix="tudOcl";

  protected static NameCreator theInstance=new NameCreator();

  /** contains all registered names
   */
  protected HashSet names;
  /** maps infixes (Strings) to Integers that will become postfixes
   */
  protected HashMap fixmap;

  /** the prefix String, by default "tuddOcl" */
  protected String prefix;

  /** creates a new NameCreator with the given prefix String; consider using the
   *  getInstance method if you don't need seperate name spaces*/
  public NameCreator(String prefix) {
    names=new HashSet();
    fixmap=new HashMap();
    this.prefix=prefix;
  }

  /** creates a new NameCreator with the default prefix String, "tuddOcl";
   *  consider using the getInstance method if you don't need seperate
   *  name spaces */
  public NameCreator() {
    this(defaultPrefix);
  }

  /** @return the special name creator object that is intended to be used
   *          for a single name space
   */
  public static NameCreator getInstance() {
    return theInstance;
  }

  /** changes the prefix to the given String
   */
  public void setPrefix(String prefix) {
    this.prefix=prefix;
  }

  /** @return this NameCreators prefix
   */
  public String getPrefix() {
    return prefix;
  }

  /** @return an unique name with empty infix part
   */
  public String getUniqueName() {
    return getUniqueName("");
  }

  /** create a new unique name (unique wrt. the names registered with this
   *  name creator) with the given String as infix part
   */
  public String getUniqueName(String infix) {
    if ( ! fixmap.containsKey(infix) ) {
      fixmap.put(infix,  new Integer(0));
    }
    String newName;
    do {
      Integer iPostfix=(Integer)fixmap.get(infix);
      newName=prefix+infix+iPostfix.toString();
      fixmap.put(infix, new Integer( iPostfix.intValue()+1 ));
    }
    while ( names.contains(newName) );
    names.add(newName);
    return newName;
  }

  /** register the name given as parameter as reserved; this name will not be
   *  returned as a new unique name in further calls to getUniqueName
   *
   *  @return true if the reservation was successful, i.e. if the name was not
   *          already reserved
   */
  public boolean reserveName(String s) {
    if (s==null) return false;
    return names.add(s);
    /* HashSet::add returns true if the HashSet did not already contain the
     * parameter object */
  }

  /** Register all names used as variables in the OCL constraint represented
   *  by the given OclTree object as reserved, including <code>self</code>
   *  and, if the constraint contains a post condition, <code>result</code>.
   *  This method does not report an error if one of the names was reserved
   *  before.
   */
  public void reserveAllNames(OclTree tree) {
    NameReserver nr=new NameReserver();
    tree.apply(nr);
  }

  /** re-initialize this name creator, that means delete all registered names
   *  and restart postfix count
   */
  public void clear() {
    names.clear();
    fixmap.clear();
  }

  /** This inner class is used by the method reserveAllNames.
   *  The results of calls to reserveName from within NameReserver are
   *  not evaluated because several variables of the same name may be declared
   *  within one OCL constraint, which will reserveName make return
   *  <code>false</code>.
   */
  class NameReserver extends DepthFirstAdapter {

    public void inStart(Start s) {
      reserveName("self");
    }

    public void inAPostStereotype(APostStereotype ps) {
      reserveName("result");
    }

    public void inALetExpression(ALetExpression le) {
      String name=le.getName().toString().trim();
      reserveName(name);
    }

    public void inAStandardDeclarator(AStandardDeclarator sd) {
      String iterName=sd.getName().toString().trim();
      reserveName(iterName);
      Iterator iter=sd.getDeclaratorTail().iterator();
      while (iter.hasNext()) {
        ADeclaratorTail dt=(ADeclaratorTail) iter.next();
        String anotherIterName=dt.getName().toString().trim();
        reserveName(anotherIterName);
      }
    }

    public void inAIterateDeclarator(AIterateDeclarator id) {
      String iterName=id.getIterator().toString().trim();
      String accuName=id.getAccumulator().toString().trim();
      reserveName(iterName);
      reserveName(accuName);
    }
  }
} /* end class NameCreator */

