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
// FILE: d:/java/classes/de/tudresden/ocl/OclSequence.java

package tudresden.ocl.lib;
import java.util.*;

/** A OclSequence is a ordered collection of elements that may contain
 *  duplicates. See documentation of OclCollection for more information.
 *
 *  <p>Indexing within an OclSequence begins with 1 for the first element.
 *
 *  @see OclCollection
 *  @author Frank Finger
 */
public class OclSequence extends OclCollection {

  /** the OclSequence that is the result of an undefined OCL expression
   */
  public static OclSequence UNDEFINED=new OclSequence();

  /** public constructor for valid OclSequences; it should be considered to use
   *  Ocl.getOclSequenceFor(Object o) instead of calling this constructor
   *  directly
   *
   *  @see Ocl#getOclSequenceFor(Object o)
   */
  public OclSequence(List list) {
    super(list);
  }

  /** private constructor for undefined OclSequence
   */
  private OclSequence() {
    bUndefined=true;
  }

  /** static factory method for an OclSequence containg no elements
   */
  public static OclSequence getEmptyOclSequence() {
    return new OclSequence(new ArrayList());
  }

  /** two OclSequences are equal if they contain the same elements in the
   *  same order
   *
   *  @throws OclClassCastException if the parameter o is not of type
   *          OclAny and Ocl.STRICT_CHECKING is <CODE>true</CODE>
   */
  public OclBoolean isEqualTo(Object o) {
    if ( !(o instanceof OclSequence) ) {
      if (Ocl.STRICT_CHECKING) {
        throw new OclClassCastException(
          "OclSequence isEqualTo() is called with a non-OclSequence parameter"
        );
      } else {
        return OclBoolean.FALSE;
      }
    }
    OclSequence other=(OclSequence)o;
    if (this.isUndefined() || other.isUndefined()) return OclBoolean.UNDEFINED;
    Iterator i1=this.collection.iterator();
    Iterator i2=other.collection.iterator();
    while (i1.hasNext() && i2.hasNext()) {
      if ( ! i1.next().equals(i2.next()) ) {
        return OclBoolean.FALSE;
      }
    }
    if (i1.hasNext() || i2.hasNext())
      return OclBoolean.FALSE;
    else
      return OclBoolean.TRUE;
  }

  /** @return an OclSequence according to the shorthand notation for <CODE>collect</CODE>
   *
   *  @see OclCollection#getFeature(String name)
   */
  public OclRoot getFeature(final String name)  {
    if (isUndefined()) return UNDEFINED;
    final OclIterator iter;
    return (OclSequence) this.collect(
      iter=this.getIterator(),
      new OclRootEvaluatable() {
        public OclRoot evaluate() {
          return iter.getValue().getFeature(name);
        }
      }
    );
  }

  /** @return an instance of OclSequence
   *  @see OclCollection#select(OclIterator iter, OclBooleanEvaluatable eval)
   *  @see OclCollection#selectToList(OclIterator iter, OclBooleanEvaluatable eval)
   */
  public OclCollection select(OclIterator iter, OclBooleanEvaluatable eval) {
    if (isUndefined()) return UNDEFINED;
    List l=selectToList(iter, eval);
    if (l==null)
      return UNDEFINED;
    else
      return new OclSequence(l);
  }

  /** @return an instance of OclSequence
   *  @see OclCollection#collect(OclIterator iter, OclRootEvaluatable eval)
   *  @see OclCollection#collectToList(OclIterator iter, OclRootEvaluatable eval)
   */
  public OclCollection collect(OclIterator iter, OclRootEvaluatable eval) {
    if (isUndefined()) return UNDEFINED;
    List l=collectToList(iter, eval);
    if (l==null)
      return UNDEFINED;
    else
      return new OclSequence(l);
  }

  /** This method calls <CODE>union(OclSequence seq)</CODE> if the argument
   *  <CODE>col</CODE> is of type <CODE>OclSequence</CODE>, or starts error
   *  handling according to Ocl.STRICT_CHECKING.
   *
   *  @see Ocl#STRICT_CHECKING
   *  @see #union(OclSequence seq)
   */
  public OclCollection union(OclCollection col) {
    if (isUndefined() || col.isUndefined()) return UNDEFINED;
    if (col instanceof OclSequence)
      return union((OclSequence)col);
    else if (Ocl.STRICT_CHECKING)
      throw new OclClassCastException(
        "OclSequence union() called with non-OclSequence argument"
      );
    else
      return UNDEFINED;
  }

  /** @return the OclSequence consisting of all elements of this OclSequence,
   *          followed by all elements of the OclSequence given as argument
   */
  public OclSequence union(OclSequence seq) {
    if (isUndefined() || seq.isUndefined()) return UNDEFINED;
    ArrayList list=new ArrayList(collection);
    list.addAll(seq.collection);
    return new OclSequence(list);
  }

  /** Ocl.STRICT_VALUE_TYPES determines whether the returned OclSequence is
   *  a newly constructed one, or if this OclSequence is changed appropriately
   *  and then returned. If the <CODE>java.util.Collection</CODE> backing this OclSequence
   *  is not a <CODE>java.util.List</CODE>, a new OclSequence is created
   *  independent of Ocl.STRICT_VALUE_TYPES.
   *
   *  @return the OclSequence consisting of all elements of this OclSequence,
   *          followes by the object given as argument
   */
  public OclSequence append(OclRoot obj) {
    if (isUndefined() || obj.isUndefined()) return UNDEFINED;
    if (Ocl.STRICT_VALUE_TYPES || !(collection instanceof List)) {
      ArrayList list=new ArrayList(collection);
      list.add(obj);
      return new OclSequence(list);
    } else {
      collection.add(obj);
      return this;
    }
  }

  /** Ocl.STRICT_VALUE_TYPES determines whether the returned OclSequence is
   *  a newly constructed one, or if this OclSequence is changed appropriately
   *  and then returned. If the <CODE>java.util.Collection</CODE> backing this OclSequence
   *  is not a <CODE>java.util.List</CODE>, a new OclSequence is created
   *  independent of Ocl.STRICT_VALUE_TYPES.
   *
   *  @return the OclSequence consisting of the object given as argument followed
   *          by all elements of this OclSequence
   */
  public OclSequence prepend(OclRoot obj) {
    if (isUndefined() || obj.isUndefined()) return UNDEFINED;
    if (Ocl.STRICT_VALUE_TYPES || !(collection instanceof List)) {
      ArrayList list=new ArrayList(collection.size()+1);
      list.add(obj);
      list.addAll(collection);
      return new OclSequence(list);
    } else {
      ((List)collection).add(0, obj);
      return this;
    }
  }

  /** Ocl.STRICT_VALUE_TYPES determines whether the resulting OclSequence will
   *  be backed by the same java.lang.Collection as this OclSequence
   *
   *  @return the sub-sequence of this OclSequence starting at <CODE>lower</CODE>,
   *          up to and including <CODE>upper</CODE>; the first element of this
   *          sequence has the number 1
   *
   *  @param lower needs to be greater than or equal to 1 and less than or
   *         equal to <CODE>upper</CODE>
   *  @param upper needs to be less than or equal to the OclSequences
   *         <CODE>size()</CODE>
   *
   *  @see OclCollection#size()
   */
  public OclSequence subSequence(OclInteger lower, OclInteger upper) {
    if (isUndefined() || lower.isUndefined() || upper.isUndefined())
      return UNDEFINED;
    try {
      List oldlist;
      if ( !(collection instanceof List) )
        oldlist=new ArrayList(collection);
      else
        oldlist=(List)collection;
      List newlist=oldlist.subList(lower.getInt()-1, upper.getInt());
      if (Ocl.STRICT_VALUE_TYPES)
        return new OclSequence(new ArrayList(newlist));
      else
        return new OclSequence(newlist);
    } catch (IndexOutOfBoundsException e) {
      if (Ocl.STRICT_CHECKING)
        throw new OclException("illegal index in OclSequence substring("+
          lower+", "+upper+")");
      else
        return UNDEFINED;
    }
  }

  /** @return the OclRoot object with the index <CODE>index</CODE>
   *
   *  @param index needs to be greater than 0 and less than or equal to
   *         <code>size()</code>
   */
  public OclRoot at(OclInteger index) {
    if (isUndefined() || index.isUndefined()) return OclAny.UNDEFINED;
    if (! (collection instanceof List) ) collection=new ArrayList(collection);
    try {
      return (OclRoot) ((List)collection).get(index.getInt()-1);
    } catch (IndexOutOfBoundsException e) {
      if (Ocl.STRICT_CHECKING)
        throw new OclException("illegal index "+index+" in OclSequence at()");
      else
        return OclAny.UNDEFINED;
    }
  }

  /** @return the first element of this OclSequence; this method is implemented to
   *          call <CODE>at( OclInteger<1> )</CODE>
   */
  public OclRoot first() {
    return at(new OclInteger(1));
  }

  /** @return the last element of this OclSequence; this method is implemented to
   *          call <CODE>at( size() )</CODE>
   */
  public OclRoot last() {
    return at( size() );
  }

  /** @return an OclSequence containing all elements of this OclSequence,
   *          followed by the object given as parameter; this method is
   *          implemented to call <CODE>append(obj)</CODE>
   */
  public OclCollection including(OclRoot obj) {
    return append(obj);
  }

  /** @return an OclSequence containing all elements of this OclSequence that
   *          are not equal to <CODE>obj</CODE>, in
   *          the same order as in this OclSequence
   */
  public OclCollection excluding(OclRoot obj) {
    if (isUndefined() || obj.isUndefined()) return UNDEFINED;
    ArrayList list=new ArrayList(collection.size());
    Iterator iter=collection.iterator();
    while (iter.hasNext()) {
      Object next=iter.next();
      try {
        if (! obj.isEqualTo(next).isTrue() ) {
          list.add(next);
        }
      } catch (OclException e) {
        // isEqualTo raised exception bcause obj is not of same type as next
        list.add(next);
      }
    }
    return new OclSequence(list);
  }

  public String toString() {
    return "OclSequence"+super.toString();
  }
} /* end class OclSequence */

