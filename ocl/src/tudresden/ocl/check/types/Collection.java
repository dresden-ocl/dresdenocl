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

import tudresden.ocl.check.OclTypeException;

/** This class is the representation of OCL collection types. The kind of the
 *  collection (Bag, Set, Sequence, Collection) is determined by an
 *  <code>int</code> constant. An <code>Collection</code> object needs to know
 *  the element type of the represented OCL collection, otherwise iterating
 *  methods without declarator type declaration can not be type-checked.
 *
 *  @author Frank Finger
 */
public class Collection implements Type {

  protected int collectionKind;
  protected Any elementType;

  public static final int COLLECTION=23;
  public static final int SET=47;
  public static final int BAG=97;
  public static final int SEQUENCE=690;

  public Collection(int collectionKind, Type elementType) throws OclTypeException {
    if ( elementType!=null && ! (elementType instanceof Any) ) {
      throw new OclTypeException("can not create collection of collections");
    }
    this.collectionKind=collectionKind;
    this.elementType=(Any)elementType;
  }

  /** returns <code>null</code> for unrestricted collections */
  public Any getElementType() {
    return elementType;
  }

  public int getCollectionKind() {
    return collectionKind;
  }

  public Type navigateQualified(String name, Type[] qualifiers) {
    if (qualifiers!=null && qualifiers.length>0) {
      throw new OclTypeException(
        "feature \""+name+"\" of type "+this+" cannot be accessed with qualifier"
      );
    }
    //System.out.println("Collection.navigateQualified:"+this+" "+name);
    Type ret;
    switch (collectionKind) {
      case COLLECTION:
        ret=null;
        break;
      case SET:
        ret=navigateSet(name);
        break;
      case BAG:
        ret=navigateBag(name);
        break;
      case SEQUENCE:
        ret=navigateSequence(name);
        break;
      default:
        throw new RuntimeException("illegal collection kind");
    }
    if (ret==null) {
      ret=navigateCollection(name);
    }
    if (ret==null) {
      throw new OclTypeException(
        "nonexistent feature \""+name+"\" of type "+this+" accessed"
      );
    }
    return ret;
  }

  public Type navigateParameterized(String name, Type[] params) {
    Type ret;
    switch (collectionKind) {
      case COLLECTION:
        ret=null;
        break;
      case SET:
        ret=navigateSet(name, params);
        break;
      case BAG:
        ret=navigateBag(name, params);
        break;
      case SEQUENCE:
        ret=navigateSequence(name, params);
        break;
      default:
        throw new RuntimeException("illegal collection kind");
    }
    if (ret==null) {
      ret=navigateCollection(name, params);
    }
    if (ret==null) {
      throw new OclTypeException(
        "nonexistent feature \"" + Basic.signatureString (name, params) + "\" of type "+this+" accessed"
      );
    }
    return ret;
  }


  public boolean hasState(String stateName) {
    return false;
  }

  public boolean conformsTo(Type t) {
    if (t instanceof Collection) {
      Collection otherColl=(Collection)t;
      Any otherElement=otherColl.getElementType();
      return conformsKind(otherColl.getCollectionKind()) && conformsElement(otherElement);
    } else {
      return false;
    }
  }

  protected boolean conformsKind(int otherKind) {
    if (otherKind==COLLECTION) {
      return true;
    } else {
      return collectionKind==otherKind;
    }
  }

  public boolean equals(Object o) {
    if (o instanceof Collection) {
      Collection c=(Collection)o;
      boolean result=(collectionKind==c.collectionKind);
      if (elementType==null || c.elementType==null) {
        result = result && (c.elementType==null) && (elementType==null);
      } else {
        result = result && elementType.equals(c.elementType);
      }
      return result;
    } else {
      return false;
    }
  }

  protected boolean conformsElement(Any otherElementType) {
    if (otherElementType==null) {
      return true;
    } else if (elementType==null) {
      return false;
    } else {
      return elementType.conformsTo(otherElementType);
    }
  }

  public String toString() {
    String name;
    switch (collectionKind) {
      case COLLECTION: name="Collection"; break;
      case SET:        name="Set";        break;
      case BAG:        name="Bag";        break;
      case SEQUENCE:   name="Sequence";   break;
      default:         name="ERRORCOLL";  break;
    }
    if (elementType==null) {
      return name;
    } else {
      return name+"("+elementType.toString()+")";
    }
  }

  protected Type navigateCollection(String name) {
    if (name.equals("size")) {
      return Basic.INTEGER;
    } else if (name.equals("isEmpty")) {
      return Basic.BOOLEAN;
    } else if (name.equals("notEmpty")) {
      return Basic.BOOLEAN;
    } else if (name.equals("sum")) {
      return getElementType();
    } else {
      return null;
    }
  }

  protected Type navigateCollection(String name, Type[] params) {
    if (name.equals("includes") && params.length==1) {
      return Basic.BOOLEAN;
    } else if (name.equals("excludes") && params.length==1) {
      return Basic.BOOLEAN;
    } else if (name.equals("count") && params.length==1) {
      return Basic.INTEGER;
    } else if (name.equals("includesAll") && params.length==1 && (params[0] instanceof Collection) ) {
      return Basic.BOOLEAN;
    } else if (name.equals("excludesAll") && params.length==1 && (params[0] instanceof Collection) ) {
      return Basic.BOOLEAN;
    } else if (name.equals("exists") && params.length==1 && params[0]==Basic.BOOLEAN) {
      return Basic.BOOLEAN;
    } else if (name.equals("forAll") && params.length==1 && params[0]==Basic.BOOLEAN) {
      return Basic.BOOLEAN;
    } else if (name.equals("isUnique") && params.length==1) {
      return Basic.BOOLEAN;
    } else if (name.equals("sortedBy") && params.length==1) {
      return new Collection(SEQUENCE, getElementType());
    } else {
      return null;
    }
  }

  protected Type navigateSet(String name) {
    if (name.equals("asBag")) {
      return new Collection(BAG, getElementType());
    } else if (name.equals("asSequence")) {
      return new Collection(SEQUENCE, getElementType());
    } else {
      return null;
    }
  }

  protected Type navigateSet(String name, Type[] params) {
    if (name.equals("union") && params.length==1 && (params[0] instanceof Collection) ) {
      Collection c=(Collection)params[0];
      if (c.getCollectionKind()==SET || c.getCollectionKind()==BAG) {
        Type elementType=getCommonType(c.getElementType(), getElementType());
        return new Collection(c.getCollectionKind(), elementType);
      } else {
        return null;
      }
    } else if (name.equals("intersection") && params.length==1 && (params[0] instanceof Collection) ) {
      Collection c=(Collection)params[0];
      if (c.getCollectionKind()==SET || c.getCollectionKind()==BAG) {
        Type elementType=getCommonType(c.getElementType(), getElementType());
        return new Collection(SET, elementType);
      } else {
        return null;
      }
    } else if (name.equals("including") && params.length==1) {
      Type elementType=getCommonType(getElementType(), params[0]);
      return new Collection(SET, elementType);
    } else if (name.equals("excluding") && params.length==1) {
      return new Collection(SET, getElementType());
    } else if (name.equals("symmetricDifference") && params.length==1 && (params[0] instanceof Collection)) {
      Collection c=(Collection)params[0];
      if (c.getCollectionKind()==SET) {
        Type elementType=getCommonType(c.getElementType(), getElementType());
        return new Collection(SET, elementType);
      } else {
        return null;
      }
    } else if (name.equals("select") && params.length==1 && params[0]==Basic.BOOLEAN) {
      return new Collection(SET, getElementType());
    } else if (name.equals("reject") && params.length==1 && params[0]==Basic.BOOLEAN) {
      return new Collection(SET, getElementType());
    } else if (name.equals("collect") && params.length==1) {
      Type paramType=params[0];
      if (paramType instanceof Collection) paramType=((Collection)paramType).getElementType();
      return new Collection(BAG, paramType);
    } else if (name.equals("count") && params.length==1) {
      // checking the element type would lead to problems with empty collections / collections
      // with unknown element type
      return Basic.INTEGER;
    } else {
      return null;
    }
  }

  protected Type navigateBag(String name) {
    if (name.equals("asSet")) {
      return new Collection(SET, getElementType());
    } else if (name.equals("asSequence")) {
      return new Collection(SEQUENCE, getElementType());
    } else {
      return null;
    }
  }

  protected Type navigateBag(String name, Type[] params) {
    if (name.equals("union") && params.length==1 && (params[0] instanceof Collection)) {
      Collection c=(Collection)params[0];
      if (c.getCollectionKind()==SET || c.getCollectionKind()==BAG) {
        Type elementType=getCommonType(c.getElementType(), getElementType());
        return new Collection(BAG, elementType);
      } else {
        return null;
      }
    } else if (name.equals("intersection") && params.length==1 && (params[0] instanceof Collection)) {
      Collection c=(Collection)params[0];
      if (c.getCollectionKind()==SET || c.getCollectionKind()==BAG) {
        Type elementType=getCommonType(c.getElementType(), getElementType());
        return new Collection(c.getCollectionKind(), elementType);
      } else {
        return null;
      }
    } else if (name.equals("including") && params.length==1) {
      Type elementType=getCommonType(getElementType(), params[0]);
      return new Collection(BAG, elementType);
    } else if (name.equals("excluding") && params.length==1) {
      return new Collection(BAG, getElementType());
    } else if (name.equals("select") && params.length==1 && params[0]==Basic.BOOLEAN) {
      return new Collection(BAG, getElementType());
    } else if (name.equals("reject") && params.length==1 && params[0]==Basic.BOOLEAN) {
      return new Collection(BAG, getElementType());
    } else if (name.equals("collect") && params.length==1) {
      Type paramType=params[0];
      if (paramType instanceof Collection) paramType=((Collection)paramType).getElementType();
      return new Collection(BAG, paramType);
    } else if (name.equals("count") && params.length==1) {
      // checking the element type would lead to problems with empty collections / collections
      // with unknown element type
      return Basic.INTEGER;
    } else {
      return null;
    }
  }

  protected Type navigateSequence(String name) {
    if (name.equals("first")) {
      if (getElementType()==null) {
        throw new OclTypeException(
          "element type of sequence not known (is necessary for \"first\" property)"
        );
      }
      return getElementType();
    } else if (name.equals("last")) {
      if (getElementType()==null) {
        throw new OclTypeException(
          "element type of sequence not known (is necessary for \"last\" property)"
        );
      }
      return getElementType();
    } else if (name.equals("asBag")) {
      return new Collection(BAG, getElementType());
    } else if (name.equals("asSet")) {
      return new Collection(SET, getElementType());
    } else {
      return null;
    }
  }

  protected Type navigateSequence(String name, Type[] params) {
    if (name.equals("count") && params.length==1) {
      // checking the element type would lead to problems with empty collections / collections
      // with unknown element type
      return Basic.INTEGER;
    } else if (name.equals("union") && params.length==1 && params[0].conformsTo(new Collection(SEQUENCE, null))) {
      Collection c=(Collection)params[0];
      Type paramType=getCommonType(c.getElementType(), getElementType());
      return new Collection(SEQUENCE, paramType);
    } else if (name.equals("append") && params.length==1) {
      Type paramType=getCommonType(params[0], getElementType());
      return new Collection(SEQUENCE, paramType);
    } else if (name.equals("prepend") && params.length==1) {
      Type paramType=getCommonType(params[0], getElementType());
      return new Collection(SEQUENCE, paramType);
    } else if (name.equals("subSequence") && params.length==2 &&
               params[0]==Basic.INTEGER && params[1]==Basic.INTEGER) {
      return new Collection(SEQUENCE, getElementType());
    } else if (name.equals("at") && params.length==1 && params[0]==Basic.INTEGER) {
      return getElementType();
    } else if (name.equals("including") && params.length==1) {
      return navigateSequence("append", params);
    } else if (name.equals("excluding") && params.length==1) {
      // checking the element type would lead to problems with empty collections / collections
      // with unknown element type
      return new Collection(SEQUENCE, getElementType());
    } else if (name.equals("select") && params.length==1 && params[0]==Basic.BOOLEAN) {
      return new Collection(SEQUENCE, getElementType());
    } else if (name.equals("reject") && params.length==1 && params[0]==Basic.BOOLEAN) {
      return new Collection(SEQUENCE, getElementType());
    } else if (name.equals("collect") && params.length==1) {
      Type paramType=params[0];
      if (paramType instanceof Collection) paramType=((Collection)paramType).getElementType();
      return new Collection(SEQUENCE, paramType);
    } else if (name.equals("asBag") && params.length==0) {
      return new Collection(BAG, getElementType());
    } else if (name.equals("asSet") && params.length==0) {
      return new Collection(SET, getElementType());
    } else {
      return null;
    }
  }


  protected Type getCommonType(Type t1, Type t2) {
      // checking the element type would lead to problems with empty collections / collections
      // with unknown element type
    Type ret;
    if (t1==null) {
      ret=t2;
    } else if (t2==null) {
      ret=t1;
    } else if (t1.conformsTo(t2)) {
      ret=t2;
    } else {
      ret=t1;
    }
    return ret;
  }


} /* end class Collection */

