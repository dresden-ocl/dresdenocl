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
// FILE: d:/java/classes/de/tudresden/ocl/OclBag.java

package tudresden.ocl20.lib;
import java.util.*;

/** A OclBag is a unordered collection that may contain duplicates. See
 *  documentation of OclCollection for further information.
 *
 *  @see OclCollection
 *  @author Frank Finger
 */
public class OclBag extends OclUnsortedCollection {

  /** package-visible constructor for OclBag; the ordering of the argument list
   *  is not reflected by OclBag
   */
  public OclBag(List list) {
    super(list);
  }

  /** constructor for undefined OclBag
   */
  public OclBag(int dummy, String reason) {
    super(dummy, reason);
  }

  /** static factory method that returns an OclBag that contains no elements
   */
  public static OclBag getEmptyOclBag() {
    ArrayList list=new ArrayList();
    return new OclBag(list);
  }

  /** two OclBags are equal if they contain the same elements the same number
   *  of times
   */
  public OclBoolean isEqualTo(Object o) {
    if ( !(o instanceof OclBag) ) {
      System.out.println("OclBag isEqualTo() is called with a non-OclBag parameter");
      return OclBoolean.FALSE;
    }
    OclBag other=(OclBag)o;
    if(isUndefined())
      return new OclBoolean(0,getUndefinedReason());
    if(other.isUndefined()) 
      return new OclBoolean(0,other.getUndefinedReason());

    HashMap hmThis=this.getCountMap();
    HashMap hmOther=other.getCountMap();

    if (hmThis.equals(hmOther)) {
      return OclBoolean.TRUE;
    } else {
      return OclBoolean.FALSE;
    }
  }

  /** @return an instance of OclBag
   *  @see OclCollection#select(OclIterator iter, OclBooleanEvaluatable eval)
   *  @see OclCollection#selectToList(OclIterator iter, OclBooleanEvaluatable eval)
   */
  public OclCollection select(OclIterator iter, OclBooleanEvaluatable eval) {
    if(isUndefined())
      return this;
    List list=selectToList(iter, eval);
    if (list==null)
      return new OclBag(0,"error in selectToList");
    else
      return new OclBag(list);
  }

  /** @return an instance of OclBag
   *  @see OclCollection#collect(OclIterator iter, OclRootEvaluatable eval)
   *  @see OclCollection#collectToList(OclIterator iter, OclRootEvaluatable eval)
   */
  public OclCollection collect(OclIterator iter, OclRootEvaluatable eval) {
    if(isUndefined())
      return this;
    List list=collectToList(iter, eval);
    if (list==null)
      return new OclBag(0,"error in collectToList");
    else
      return new OclBag(list);
  }
  
  public OclCollection collectNested(OclIterator iter, OclRootEvaluatable eval) {
    if(isUndefined())
      return this;
    List list=collectNestedToList(iter, eval);
    if (list==null)
      return new OclBag(0,"error in collectToList");
    else
      return new OclBag(list);
  }

  /**
   *Removes one level of nesting.
   */
  public OclCollection flatten(){
    if(isUndefined())
      return this;
    List list=flattenToList();
    if (list==null)
      return new OclBag(0,"error in flattenToList");
    else
      return new OclBag(list);
  }
  
  /** This method tries to call the appropriate method of the same name (for
   *  OclSet or OclBag parameters) or, if there is no such method, handles this
   *  error by returning an undefined value.
   *
   *  @see OclCollection#union(OclCollection col)
   *  @see #union(OclBag col)
   *  @see #union(OclSet col)
   */
  public OclCollection union(OclCollection col) {
    if(isUndefined()) 
      return this;
    if (col instanceof OclSet)
      return union( (OclSet)col );
    else if (col instanceof OclBag)
      return union( (OclBag)col );
    else
      return new OclBag(0,"tried to create union of OclBag and OclSequence");
  }

  /** The union of an OclBag and an OclSet is an OclBag.
   *  This method calls OclSet.union(OclBag).
   *
   *  @see OclSet#union(OclBag bag)
   */
  public OclBag union(OclSet set) {
    if(isUndefined()) 
      return this;
    return set.union(this);
  }

  /** The union of two OclBags is again an OclBag.
   */
  public OclBag union(OclBag bag) {
    if(isUndefined())
      return this;
    if(bag.isUndefined())
      return bag;
    ArrayList list=new ArrayList(collection.size()+bag.collection.size());
    list.addAll(collection);
    list.addAll(bag.collection);
    return new OclBag(list);
  }

  /** @return an OclSet containing all elements found in both this bag
   *          (regardless of their number) and the set given as argument
   */
  public OclSet intersection(OclSet set) {
    if(isUndefined())
      return new OclSet(0,getUndefinedReason());
    if(set.isUndefined()) 
      return new OclSet(0,set.getUndefinedReason());
    HashSet ret=new HashSet(set.collection.size());
    Iterator iter=set.collection.iterator();
    while (iter.hasNext()) {
      Object next=iter.next();
      if (collection.contains(next)) {
        ret.add(next);
      }
    }
    return new OclSet(ret);
  }

  /** @return an OclBag that contains all elements found in both this OclBag
   *          and the OclBag given as argument; the number of times the element
   *          is contained in the result OclBag is the minimum of the numbers
   *          it is found in the two source OclBags
   */
  public OclBag intersection(OclBag bag) {
    if(isUndefined())
      return this;
    if(bag.isUndefined()) 
      return bag;
    HashMap hsThis=getCountMap();
    HashMap hsOther=bag.getCountMap();
    Iterator iter=hsThis.keySet().iterator();
    ArrayList ret=new ArrayList(collection.size());
    while(iter.hasNext()) {
      Object key=iter.next();
      Object vThis=hsThis.get(key);
      Object vOther=hsOther.get(key);
      if (vOther!=null) {
        int iNewValue=Math.min(
          ((Integer)vThis).intValue(),
          ((Integer)vOther).intValue()
        );
        for (int i=0; i<iNewValue; i++) {
          ret.add(key);
        }
      }
    }
    return new OclBag(ret);
  }

  /** 
   *
   *  @return an OclBag containing all elements of this bag, plus the argument
   *
   */
  public OclBag including(OclRoot obj) {
    if(isUndefined())
      return this;
    if(obj.isUndefined()) 
      return new OclBag(0,obj.getUndefinedReason());

      ArrayList list=new ArrayList(collection);
      list.add(obj);
      return new OclBag(list);
    
  }

  /** 
   *
   *  @return an OclBag containing all elements of this bag but the argument
   *
   */
  public OclBag excluding(OclRoot obj) {
    if(isUndefined())
      return this;
    if(obj.isUndefined())
      return new OclBag(0,obj.getUndefinedReason());
    boolean bCreateCopy=! (collection instanceof ArrayList);
    ArrayList list;
    if ( bCreateCopy ) {
      list=new ArrayList(collection);
    } else {
      list=(ArrayList)collection;
    }
    int i=list.indexOf(obj);
    while (i!=-1) {
      list.remove(i);
      i=list.indexOf(obj);
    }
    if (bCreateCopy) {
      return new OclBag(list);
    } else {
      return this;
    }
  }

  /** @return a HashMap with elements of this collection as keys and an Integer
   *          denoting their count as value
   */
  protected HashMap getCountMap() {
    Iterator iter=collection.iterator();
    HashMap count=new HashMap();
    while (iter.hasNext()) {
      Object key=iter.next();
      if (count.get(key)==null) {
        count.put(key, new Integer(1));
      } else {
        Integer oldvalue=(Integer)count.get(key);
        count.put(key, new Integer( oldvalue.intValue()+1 ) );
      }
    }
    return count;
  }

  public String toString() {
    return "OclBag"+super.toString();
  }

} /* end class OclBag */

