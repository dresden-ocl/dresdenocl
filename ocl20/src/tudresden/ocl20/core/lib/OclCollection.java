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
// FILE: d:/java/classes/de/tudresden/ocl/OclCollection.java

package tudresden.ocl20.lib;
import java.util.*;

/** This class is the abstract superclass of the OCL collection classes
 *  Set, Bag and Sequence. All these classes are implemented as adapter classes
 *  around classes of the Java 2 (= JDK1.2) collection framework.
 *
 *  <P>The java.lang.Collection backing OclCollections are required to contain
 *  only objects of type <CODE>OclRoot</CODE>. That should be considered when
 *  calling the constructors of OclCollections that take java.lang.Collections.
 *
 *  <h3>Iterating methods</h3>
 *
 *  <p>A central feature of the collections defined in the OCL specification
 *  are operations that have an OCL expression as their parameter. These
 *  methods are
 *  <ul><li><CODE>exists</CODE>,
 *      <li><CODE>forAll</CODE>,
 *      <li><CODE>isUnique</CODE>,
 *      <li><CODE>sortedBy</CODE>,
 *      <li><CODE>select</CODE>,
 *      <li><CODE>reject</CODE>,
 *      <li><CODE>collect</CODE> and, the most general one,
 *      <li><CODE>iterate</CODE>.
 *  </ul>
 *  These operations are called <i>iterating methods</i> in this documentation.
 *  They are implemented using an iterator concept. <CODE>OclCollection</CODE> defines a
 *  method <CODE>getIterator</CODE> that returns an <CODE>OclIterator</CODE>.
 *  To call a iterating method, you have to
 *  get yourself an OclIterator first. This OclIterator is passed to the
 *  iterating method as the first parameter. The second parameter is an object
 *  that implements an <i>Evaluatable interface</i>. This object will usually
 *  be of an inner class only implementing the single method of the Evaluatable
 *  interface. Within this method, the value of the iterator can be reffered
 *  to using OclIterator's <CODE>getValue()</CODE> method.
 *
 *  <p>An example:
 *  <code>context Company inv:<br><br>
 *  self.employee-&gt;forAll(i|i.isUnemployed=false)<br><br></code>
 *  can be transformed to the following Java code:
 *  (given that <CODE>employee</CODE> is an <CODE>OclSet</CODE>)<br><br>
 *  <code>OclIterator iter;<br>
 *  OclBoolean constraintFulfilled=employee.forAll(<br>
 *    &nbsp;iter=employee.getIterator(),<br>
 *    &nbsp;new OclBooleanEvaluatable {<br>
 *      &nbsp;&nbsp;public OclBoolean evaluate() {<br>
 *        &nbsp;&nbsp;&nbsp;return ! ((OclBoolean)(iter.getValue().getFeature("isUnemployed"))).isTrue();<br>
 *      &nbsp;&nbsp;}<br>
 *    &nbsp;} // end of inner class<br>
 *  );<br>
 *
 *  <h3>Class Diagram</h3>
 *  <img src="doc-files/collections.gif">
 *
 *  @see OclBooleanEvaluatable
 *  @see OclRootEvaluatable
 *  @see OclComparableEvaluatable
 *  @see OclIterator
 *  @author Frank Finger
 */
public abstract class OclCollection implements OclSizable, OclRoot {

//  /** copied from Ocl.STRICT_VALUE_TYPES at creation of this collection; can
//   *  then be changed as needed
//   *
//   *  @see Ocl#STRICT_VALUE_TYPES
//   */
//  public boolean STRICT_VALUE_TYPES=Ocl.STRICT_VALUE_TYPES;

  protected Collection collection;

  /** package-visible constructor for valid collections
   */
  OclCollection(Collection c) {
    collection=c;

//// OCL2.0: flattening is always explicit
//    if (Ocl.STRICT_FLATTENING && c!=null) {
//      Iterator iter=c.iterator();
//      while (iter.hasNext()) {
//        Object o=iter.next();
//        if (o instanceof OclCollection) {
//          c.remove(o);
//          c.addAll( ((OclCollection)o).collection ); 
//          //NOTE: this is not STRICT_VALUE_TYPES!
//          //=> side effects in case of live collections!
//        }
//      }
//    }
  }

  public abstract OclBoolean isEqualTo(Object o);

  public OclBoolean isNotEqualTo(Object o) {
    return isEqualTo(o).not();
  }


  
     /** This method returns true if the OclType given as parameter is a type
     *  of the object whose method is called or a supertype of such a type.
     * According to the semantics chapter [Ocl1.6, p A-26] this is 
     * either welldefined for Collections.  
     */
    public OclBoolean oclIsKindOf(OclType t) {
        if(isUndefined()){
            return new OclBoolean(0, getUndefinedReason());
        }
        return OclBoolean.getOclRepresentationFor(t.isOfKind(this));
    }
    
    /** This method returns true if the OclType given as parameter is a "type"
     *  of this object. "Type" is to be understood in the UML/OCL sense of the
     *  word, meaning that an OclAny has exactly one type and
     *  this types supertypes are not types of the object.
     * According to the semantics chapter [Ocl1.6, p A-26] this is 
     * either welldefined for Collections.  
     */
    public OclBoolean oclIsTypeOf(OclType t) {
        if(isUndefined()){
            return new OclBoolean(0, getUndefinedReason());
        }
        return OclBoolean.getOclRepresentationFor(t.isOfType(this));
    }

//  /** If a feature of a collection is queried then this is interpreted to be the
//   *  shorthand for <CODE>collect</CODE> (e.g. <CODE>employee.name</CODE> as short
//   *  for <CODE>employee.collect(name)</CODE>). Therefore, this method is
//   *  implemented to call
//   *  <CODE>collect</CODE> appropriately.
//   *
//   *  @return an object of type OclCollection
//   */
//  public abstract OclRoot getFeature(String name);
//
//  /** Please consult the documentation of <CODE>OclRoot.getFeatureAsCollection
//   *  </CODE> for a detailed explanation.
//   *
//   *  @see OclRoot#getFeatureAsCollection(String name)
//   */
//  public OclCollection getFeatureAsCollection(String name) {
//    OclRoot or=getFeature(name);
//    if (or instanceof OclCollection) {
//      return (OclCollection) or;
//    } else {
//      HashSet set=new HashSet();
//      set.add(or);
//      return new OclSet(set);
//    }
//  }

  /** @return OclBoolean.TRUE if at least one element of this collection
   *          fulfills the condition given as second parameter
   */
  public OclBoolean exists(OclIterator iter, OclBooleanEvaluatable eval) {
    if(isUndefined()) 
      return new OclBoolean(0,getUndefinedReason());
    OclBoolean ret=OclBoolean.FALSE;
    while (iter.hasNext() && ret != OclBoolean.TRUE) { //please note that we do not abort in case of undefined!
      iter.next();
      ret = eval.evaluate().or(ret);
    }
    return ret;
  }

  /** @return OclBoolean.TRUE iff all elements of this collection
   *          fulfill the condition given as second parameter. 
   *          Yields OclBoolean.FALSE if the condition evaluates 
   *          to false in at least one case, even if there are some 
   *          undefined evaluation results for other elements.  
   *  
   */
  public OclBoolean forAll(OclIterator iter, OclBooleanEvaluatable eval) {
    if (isUndefined()) 
      return new OclBoolean(0,getUndefinedReason());

      OclBoolean ret= OclBoolean.TRUE;
      while (iter.hasNext() && ret == OclBoolean.TRUE) {
        iter.next();
        ret = eval.evaluate().and(ret); //and handles undefined properly...
      }
      return ret;
  
  }

  /** This method checks the uniqueness of a given expression, evaluated for
   *  all members of the collection. This is done by storing all evaluation
   *  results in a HashSet. If an element is added that was already contained
   *  in the HashSet, execution is stopped and FALSE returned.
   *
   *  @return OclBoolean.TRUE iff the OCL expression represented by parameter <CODE>eval</CODE>
   *          returns different results for all members of the set
   */
  public OclBoolean isUnique(OclIterator iter, OclRootEvaluatable eval) {
    if(isUndefined()) 
      return new OclBoolean(0,getUndefinedReason());
    boolean ret=true;
    HashSet hs=new HashSet(collection.size());
    while (iter.hasNext() && ret) {
      iter.next();
      ret=hs.add( eval.evaluate() );
    }
    return OclBoolean.getOclRepresentationFor(ret);
  }

  /** This methods sorts the elements of the collection according to the
   *  comparable results of the OCL expression represented by the second
   *  parameter.
   */
  public OclSequence sortedBy(OclIterator iter, OclComparableEvaluatable eval) {
    if(isUndefined()) 
      return new OclSequence(0,getUndefinedReason());

    /* problem:  a key may occur many times for different value
     * solution: store a mapping key -> set(value)
     */
    TreeMap tm=new TreeMap();
    while ( iter.hasNext() ) {
      iter.next();
      java.lang.Comparable key;
      try {
        key = eval.evaluate();
      } catch (ClassCastException e) {
        return new OclSequence(0,"ClassCastException in OclComparableEvaluatable.evaluate()");
      }
      OclRoot obj    = iter.getValue();
      if (tm.keySet().contains(key)) {
        ArrayList list=(ArrayList) tm.get(key);
        list.add(obj);
      } else {
        ArrayList list=new ArrayList();
        list.add(obj);
        tm.put(key, list);
      }
    }

    List l=new ArrayList(tm.size()*2);
    Iterator mapIter=tm.values().iterator();
    while (mapIter.hasNext()) {
      ArrayList valList=(ArrayList)mapIter.next();
      Iterator valIter=valList.iterator();
      while (valIter.hasNext()) {
        l.add(valIter.next());
      }
    }
    return new OclSequence(l);
  }

  /** This method is the most general one of the iterating methods. All others
   *  can be expressed through this.
   *
   *  @param accum the initial value of the accumulator that is updated
   *         every iteration step by the result of eval
   *  @see OclContainer
   */
  public OclRoot iterate(OclIterator iter, OclContainer accum, OclRootEvaluatable eval) {
    if(isUndefined())
      return this;
    while (iter.hasNext()) {
      iter.next();
      OclRoot root=eval.evaluate();
      accum.setValue(root);
    }
    return accum.getValue();
  }

  /** @return the biggest sub-collection of this collection where all elements
   *          fulfil the condition given as <CODE>eval</CODE>
   */
  public abstract OclCollection select(OclIterator iter, OclBooleanEvaluatable eval);

  /** This method does almost the complete work necessary to implement
   *  <CODE>select</CODE>. The only thing that has to be done in subclasses is
   *  to call this method, get the result of the select operation as
   *  a java.lang.List and wrap the result in the correct Ocl Collection.
   *
   *  @return <code>null</code> if this collection is undefined
   */
  protected List selectToList(OclIterator iter, OclBooleanEvaluatable eval) {
    if (isUndefined()) return null;
    ArrayList list=new ArrayList(collection.size());
    while (iter.hasNext()) {
      iter.next();
      OclBoolean b = eval.evaluate();
      if(b.isUndefined()){
          return null; //the result gets undefined as well
      }
      if (b.isTrue()) list.add(iter.getValue());
    }
    return list;
  }
  
  public OclBoolean one(OclIterator iter, OclBooleanEvaluatable eval){
    if (isUndefined()) return new OclBoolean(0, this.getUndefinedReason());
    ArrayList list=new ArrayList(collection.size());
    int cnt = 0;
    boolean undefined = false;
    while (iter.hasNext()) {
      iter.next();
      OclBoolean b = eval.evaluate();
      if (b == OclBoolean.TRUE) {
          cnt++;
          if(cnt >1){
            return OclBoolean.FALSE; 
            //if we have more than one occurence we always return false, 
            //regardless if there were some undefined values
          }
      } else if(b.isUndefined()) {
          undefined = true;
      }
    }
    if(undefined){
        return new OclBoolean(0, "The evaluation of the body of the 'one'-Iterator was undefined for at least one element of the collection");
    } else {
        return OclBoolean.getOclRepresentationFor(cnt==1);
    }
  }
  
  public OclRoot any(OclIterator iter, OclBooleanEvaluatable eval) {
    if (isUndefined()) return this;
    ArrayList list=new ArrayList(collection.size());
    while (iter.hasNext()) {
      iter.next();
      if (eval.evaluate()==OclBoolean.TRUE) return iter.getValue();
    }
    return new OclUndefined("No element fullfilled the condition for any().");
  }

  /** @return the biggest sub-collection of this collection where no element
   *          fulfils the condition given as <CODE>eval</CODE>
   */
  public OclCollection reject(OclIterator iter, final OclBooleanEvaluatable eval) {
    return select(
      iter,
      new OclBooleanEvaluatable() {
        public OclBoolean evaluate() {
          return eval.evaluate().not();
        }
      }
    );
  }

  /** @return the OclCollection containing <CODE>eval</CODE> evaluated for
   *          every element of this collection; the Collection will either be
   *          a Bag (for Bags and Sets) or a Sequence (for Sequences)
   */
  public abstract OclCollection collect(OclIterator iter, OclRootEvaluatable eval);

  /** This method does almost the complete work necessary to implement
   *  <CODE>collect</CODE>. The only thing that has to be done in subclasses is
   *  to call this method, get the result of the select operation as
   *  a java.lang.List and wrap the result in the correct Ocl Collection.
   *
   *  @return <code>null</code> if this collection is undefined
   */
  protected List collectToList(OclIterator iter, OclRootEvaluatable eval) {
    if (isUndefined()) return null;
    ArrayList list=new ArrayList(collection.size());
    while (iter.hasNext()) {
      iter.next();
      OclRoot or=eval.evaluate();
      if (or instanceof OclCollection) {
        Iterator i=((OclCollection)or).collection.iterator();
        while (i.hasNext()) {
          list.add( i.next() );
        }
      } else {
        list.add( or );
      }
    }
    return list;
  }
  
  /**same as collectToList, but without flattening*/
  protected List collectNestedToList(OclIterator iter, OclRootEvaluatable eval) {
    if (isUndefined()) return null;
    ArrayList list=new ArrayList(collection.size());
    while (iter.hasNext()) {
      iter.next();
      OclRoot or=eval.evaluate(); 
        list.add( or );
    }
    return list;
  }
  
  /**
   * Removes one level of nesting.
   */
  protected List flattenToList(){
    if (isUndefined()) return null;
    ArrayList list=new ArrayList(collection.size());
    
    Iterator iter=collection.iterator();
    while (iter.hasNext()) {      
      OclRoot or= (OclRoot) iter.next();
      if (or instanceof OclCollection) {
        Iterator i=((OclCollection)or).collection.iterator();
        while (i.hasNext()) {
          list.add( i.next() );
        }
      } else {
        list.add( or );
      }
    }
    return list;
  }
  

  /** @return the cardinality of this collection
   */
  public OclInteger size() {
    if(isUndefined()) 
      return new OclInteger(0,getUndefinedReason());
    return new OclInteger(collection.size());
  }

  /** checks if this collection contains the element given as argument
   *  (i.e., if this collection contains an OclRoot that is equal to
   *  the argument)
   *
   *  @see OclRoot#isEqualTo(Object o)
   */
  public OclBoolean includes(OclRoot obj) {
    if(isUndefined()) 
      return new OclBoolean(0,getUndefinedReason());
    if(obj.isUndefined()) 
      return new OclBoolean(0,obj.getUndefinedReason());
    boolean ret=false;
    Iterator iter=collection.iterator();
    while (iter.hasNext() && !ret) {
      try {
        if (obj.isEqualTo(iter.next()).isTrue()) ret=true;
      }
      catch (OclException e) {
      }
    } // end while
    return OclBoolean.getOclRepresentationFor(ret);
  }

  /** @return the negated result of <CODE>includes(OclRoot)</CODE>
   *
   *  @see #includes(OclRoot a)
   */
  public OclBoolean excludes(OclRoot obj) {
    return includes(obj).not();
  }

  /** @return an OclInteger denoting the number of objects in this collection
   *          that are equal to the argument of the operation
   *  //////@param obj either of type OclRoot, or a OCL representation is generated in
   *  //////       this method
   *  this indroduces dependencies to class Ocl and to type mapping via Java Reflection
   *  changed to 
   * @param or  of type OclRoot
   */
  public OclInteger count(OclRoot or) {
    if(isUndefined()) 
      return new OclInteger(0,getUndefinedReason());
    
    Iterator iter=collection.iterator();
    int count=0;
    while (iter.hasNext()) {
      try {
        Object next=iter.next();
        if (or.isEqualTo(next).isTrue()) count++;
      }
      catch (OclException e) {
      }
    }
    return new OclInteger(count);
  }

  public OclBoolean includesAll(OclCollection coll) {
    if(isUndefined()) 
      return new OclBoolean(0,getUndefinedReason());
    if(coll.isUndefined()) 
      return new OclBoolean(0,coll.getUndefinedReason());
    return OclBoolean.getOclRepresentationFor(collection.containsAll(coll.collection));
  }

  /** check if there are any elements in the collection; an undefined
   *  collection is not considered empty
   */
  public OclBoolean isEmpty() {
    if(isUndefined()) 
      return new OclBoolean(0,getUndefinedReason());
    return OclBoolean.getOclRepresentationFor(collection.isEmpty());
  }

  /** @return the negated result of <CODE>isEmpty</CODE>
   */
  public OclBoolean notEmpty() {
    return isEmpty().not();
  }

  /** This method sums up all elements of the collection. If any element does
   *  not implement the interface <CODE>OclAddable</CODE>, the result is undefined.
   *  If the collection is empty, an OclInteger representing 0 is returned.
   *
   *  @see OclAddable
   */
  public OclAddable sum() {
    if(isUndefined()) 
      return new OclInteger(0,getUndefinedReason());
    if (collection.isEmpty()) return new OclInteger(0l);

    try {
      Iterator iter=collection.iterator();
      OclAddable sum=(OclAddable)iter.next();
      while (iter.hasNext()) {
        OclAddable nextsum = sum.add( (OclAddable)iter.next() );
        sum=nextsum;
      }
      return sum;
    }
    catch (ClassCastException cce) {
      return new OclInteger(0,"sum() of collection with non-OclAddable element requested");
    }
  }

  /** @return an OclSet containing all elements of this collection (without
   *          duplicates, if there are any)
   */
  public OclSet asSet() {
    if(isUndefined())
      return new OclSet(0,getUndefinedReason());
    HashSet set=new HashSet(collection);
    return new OclSet(set);
  }

  /** @return an OclBag containing all elements of this collection
   */
  public OclBag asBag() {
    if(isUndefined()) 
      return new OclBag(0,getUndefinedReason());
    ArrayList list=new ArrayList(collection);
    return new OclBag(list);
  }

  /** @return an OclSequence containing all elements of this collection in no
   *          defined order
   */
  public OclSequence asSequence() {
    if(isUndefined()) 
      return new OclSequence(0,getUndefinedReason());
    ArrayList list=new ArrayList(collection);
    return new OclSequence(list);
  }

  /** an OclIterator is necessary to invoke the "iterating methods", e.g.
   *  collect, forAll, iterate
   */
  public OclIterator getIterator() {
    if (isUndefined()) {
      return null;
    }
    return new OclIterator(collection);
  }

  private OclCollection error(String msg) {
    return new OclSet(0,msg);
  }

  public boolean equals(Object o) {
    try {
      return isEqualTo(o).isTrue();
    } catch (OclException e) {
      return false;
    }
  }

  public String toString() {
    if (isUndefined()) {
      return "[UNDEFINED:"+getUndefinedReason()+"]";
    } else {
      StringBuffer sb=new StringBuffer();
      Iterator iter=collection.iterator();
      sb.append("[");
      while (iter.hasNext()) {
        sb.append(iter.next().toString());
        if (iter.hasNext()) sb.append(",");
      }
      sb.append("]");
      return sb.toString();
    }
  }

  /** Sets this collection to contain the range from <code>begin</code> to
   *  <code>end</code>. This method is not specified as operation of the OCL
   *  type but is necessary to convert range collection literals to Java. It
   *  is not implemented side-effect free as the collection operations defined
   *  in the OCL specification but changes the state of the OclCollection
   *  called. Elements that are in the collection prior to the call to this
   *  method remain there.
   *
   *  Makes this Collection undefined if begin is greater than end.
   */
  public void setToRange(OclInteger begin, OclInteger end) {
    if(isUndefined()||begin.isUndefined()||end.isUndefined())
      return;
    int iBegin = begin.getInt();
    int iEnd =   end.getInt();
    if (iBegin>iEnd) {
      becomeUndefined("lower range boundary ("+iBegin+") greater than upper range boundary ("+iEnd+") in collection literal."
      );
    }
    for (int i=iBegin; i<=iEnd; i++) {
      collection.add( new OclInteger(i) );
    }
  }

  /** Sets this collection to contain the the object given as parameter.
   *  This method is not specified as operation of the OCL type but is
   *  necessary to convert collection literals to Java. It is not implemented
   *  side-effect free but changes the called OclCollection. Elements that
   *  are in the collection prior to the call to this
   *  method remain there.
   *
   * Ocl 2.0 : Parameter changed to OclRoot to allow nested collection literals.
   */
  public void setToInclude(OclRoot root) {
    if(isUndefined())
      return;
    if (root != null && ! root.isUndefined()) {
      collection.add(root);
    }
  }

  // START of section implementing undefined values
  // this section is duplicated in all classes,
  // directly implementing the OclRoot interface,
  // excepting OclContainer.

  /**
     The reason, why this object represents an undefined value.
     Additionally, this is the tag, whether this object represents
     a undefined value.
     Is null, if and only if it is not undefined.
  */
  private String undefinedreason=null;
  
  /**
     Constructs an instance representing an undefined value.
     @parameter dummy must be 0.
  */
  protected OclCollection(int dummy, String undefinedreason)
  {
    if(dummy!=0)
      throw new RuntimeException();
    this.undefinedreason=undefinedreason;
  }
  
  public final boolean isUndefined()
  {
    return undefinedreason!=null;
  }
  
  public final String getUndefinedReason()
  {
    if(undefinedreason!=null)
      return undefinedreason;
    else
      throw new RuntimeException();
  }

  // END of section implementing undefined values

  /**
     Makes this instance represent an undefined value.
     This method is needed, since OclCollections are not immutable
     in this implementation.
     @see #setToInclude
     @see #setToRange
     @throws RuntimeException if the collection is already undefined
  */
  protected void becomeUndefined(String undefinedreason)
  {
    if(this.undefinedreason!=null)
      throw new RuntimeException();
    this.undefinedreason=undefinedreason;
  }
  
} /* end class OclCollection */

