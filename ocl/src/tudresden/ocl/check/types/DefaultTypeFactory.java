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
// FILE: d:/projekte/diplom/classes/tudresden/ocl/check/types/TypeFactory.java

package tudresden.ocl.check.types;

import java.util.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.check.OclTypeException;

public class DefaultTypeFactory implements TypeFactory {

  static HashSet predefinedTypeNames;

  static {
    predefinedTypeNames=new HashSet();
    predefinedTypeNames.add("Integer");
    predefinedTypeNames.add("Real");
    predefinedTypeNames.add("Boolean");
    predefinedTypeNames.add("String");
    predefinedTypeNames.add("Bag");
    predefinedTypeNames.add("Set");
    predefinedTypeNames.add("Sequence");
    predefinedTypeNames.add("Collection");
    predefinedTypeNames.add("Enumeration");
    predefinedTypeNames.add("OclAny");
  }

  protected ModelFacade myModelFacade;

  public DefaultTypeFactory(ModelFacade mf) {
    myModelFacade=mf;
  }

  public Type getBoolean() {
    return Basic.BOOLEAN;
  }

  public Type getInteger() {
    return Basic.INTEGER;
  }

  public Type getReal() {
    return Basic.REAL;
  }

  public Type getString() {
    return Basic.STRING;
  }

  public Type getSet() {
    return getSet(null);
  }

  public Type getSet(Type param) {
    return new Collection(Collection.SET, param);
  }

  public Type getBag() {
    return getBag(null);
  }

  public Type getBag(Type param) {
    return new Collection(Collection.BAG, param);
  }

  public Type getSequence() {
    return getSequence(null);
  }

  public Type getSequence(Type param) {
    return new Collection(Collection.SEQUENCE, param);
  }

  public Type getCollection() {
    return getCollection(null);
  }

  public Type getCollection(Type param) {
    return new Collection(Collection.COLLECTION, param);
  }

  public Type getEnumerationElement() {
    return Basic.ENUMERATION;
  }

  public Type getOclType(Type t) {
    return new OclType(t);
  }

  public Type getOclAny() {
    return Basic.OCLANY;
  }

  public Type get(String name) {
    if (predefinedTypeNames.contains(name)) {
      if (name.equals("Boolean")) return getBoolean();
      else if (name.equals("Integer")) return getInteger();
      else if (name.equals("Real")) return getReal();
      else if (name.equals("String")) return getString();
      else if (name.equals("Collection")) return getCollection();
      else if (name.equals("Set")) return getSet();
      else if (name.equals("Bag")) return getBag();
      else if (name.equals("Sequence")) return getSequence();
      else if (name.equals("Enumeration")) return getEnumerationElement();
      else if (name.equals("OclAny")) return getOclAny();
      else return null;
    } else {
      return myModelFacade.getClassifier(name);
    }
  }

  public boolean conforms(Type found, Type expected) {
    return found.conformsTo(expected);
  }

  public void assertTrue(Type found, Type expected, Node where) {
    if (!conforms (found, expected)) {
      throw new OclTypeException("type error in \""+where+"\" :expected "+expected+", found "+found);
    }
  }

  public static String toString(Type[] array) {
    StringBuffer sbuf=new StringBuffer();
    for (int i=0; i<array.length; i++) {
      if (i!=0) sbuf.append(",");
      sbuf.append(array[i]);
    }
    return sbuf.toString();
  }
} /* end class TypeFactory */

