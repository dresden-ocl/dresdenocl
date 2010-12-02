package tudresden.ocl20.core.lib;
import java.util.*;

public class OclOrderedSet extends OclSortedCollection {
  
  public OclOrderedSet(List list) {
    super(list);
    List temp = new ArrayList();
    Iterator it = this.collection.iterator();
    while(it.hasNext()) {
      Object o = it.next();
      if (!temp.contains(o))
        temp.add(o);
    }
    this.collection = temp;
  }
  
  public OclOrderedSet(int dummy, String reason) {
    super(dummy, reason);
  }
  
  public static OclOrderedSet getEmptyOclOrderedSet() {
    return new OclOrderedSet(new ArrayList());
  }
  
  public OclRoot at(OclInteger index) {
    if (isUndefined())
      return new OclUndefined(getUndefinedReason());
    if (index.isUndefined())
      return new OclUndefined(index.getUndefinedReason());
    if (!(collection instanceof List))
      collection = new ArrayList(collection);
    try {
      return (OclRoot)((List)collection).get(index.getInt()-1);
    } catch(IndexOutOfBoundsException e) {
      return new OclUndefined("illegal index "+index+" in OclOrderedSet at()");
    }
  }

  public OclRoot first() {
    return at(new OclInteger(1));
  }

  public OclRoot last() {
    return at(size());
  }

  public OclInteger indexOf(OclRoot obj) {
    if (isUndefined())
    	/* Constructor changed during refactoring by Claas Wilke in July 2007. */
    	return new OclInteger(getUndefinedReason());
    if (obj.isUndefined())
    	/* Constructor changed during refactoring by Claas Wilke in July 2007. */
    	return new OclInteger(obj.getUndefinedReason());
    int index;
    if (!(collection instanceof List)) {
      ArrayList list = new ArrayList(collection);
      index = list.indexOf(obj) + 1;
    }
    else
    {
      index = ((List)collection).indexOf(obj) + 1;
    }
    if (index > 0)
      return new OclInteger(index);
    else
    	/* Constructor changed during refactoring by Claas Wilke in July 2007. */
    	return new OclInteger("object "+obj.toString()+" not found within indexOf()");
  }

  public OclBoolean isEqualTo(Object o) {
    if (!(o instanceof OclOrderedSet)) {
      System.out.println(
          "OclOrderedSet isEqualTo() is called with a non-OclOrderedSet parameter"
          );
      return OclBoolean.FALSE;
    }
    OclOrderedSet other = (OclOrderedSet)o;
    if (isUndefined())
    	/* Constructor changed during refactoring by Claas Wilke in July 2007. */
    	return new OclBoolean(getUndefinedReason());
    if (other.isUndefined())
    	/* Constructor changed during refactoring by Claas Wilke in July 2007. */
    	return new OclBoolean(other.getUndefinedReason());
    Iterator i1 = this.collection.iterator();
    Iterator i2 = other.collection.iterator();
    while (i1.hasNext() && i2.hasNext()) {
      if (!i1.next().equals(i2.next()))
        return OclBoolean.FALSE;
    }
    if (i1.hasNext() || i2.hasNext())
      return OclBoolean.FALSE;
    else
      return OclBoolean.TRUE;
  }

  public OclCollection select(OclIterator iter, OclBooleanEvaluatable eval) {
    if (isUndefined())
      return this;
    List l = selectToList(iter, eval);
    if (l == null)
      return new OclOrderedSet(0, "error in selectToList");
    else
      return new OclOrderedSet(l);
  }

  public OclCollection collect(OclIterator iter, OclRootEvaluatable eval) {
    if (isUndefined())
      return this;
    List l = collectToList(iter, eval);
    if (l == null)
      return new OclOrderedSet(0, "error in collectToList");
    else
      return new OclOrderedSet(l);
  }
  
  public OclCollection collectNested(OclIterator iter, OclRootEvaluatable eval) {
    if (isUndefined())
      return this;
    List l = collectNestedToList(iter, eval);
    if (l == null)
      return new OclOrderedSet(0, "error in collectNestedToList");
    else
      return new OclOrderedSet(l);
  }
  
  public OclCollection flatten() {
    if (isUndefined())
      return this;
    List l = flattenToList();
    if (l == null)
      return new OclOrderedSet(0, "error in flattenToList");
    else
      return new OclOrderedSet(l);
  }
  
  public OclCollection union(OclCollection col) {
    if (isUndefined())
      return this;
    if (col.isUndefined())
      return col;
    if (col instanceof OclOrderedSet)
      return union((OclOrderedSet)col);
    else if (col instanceof OclSequence)
      return union((OclSequence)col);
    else
      return new OclOrderedSet(0, "OclOrderedSet union() called with OclUnsortedCollection argument");
  }
  
  /**
   * 
   * @param oset
   * @return the OclOrderedSet consisting of all elements of this OclOrderedSet,
   *         followed by all elements of the OclOrderedSet given as argument.
   *         Dublicate elimination is done by the OclOrderedSet constructor.
   */
  public OclOrderedSet union(OclOrderedSet oset) {
    if (isUndefined())
      return this;
    if (oset.isUndefined())
      return oset;
    ArrayList list = new ArrayList(collection);
    list.addAll(oset.collection);
    return new OclOrderedSet(list);
  }
  
  public OclSequence union(OclSequence seq) {
    if (isUndefined())
      return new OclSequence(0, this.getUndefinedReason());
    if (seq.isUndefined())
      return seq;
    ArrayList list = new ArrayList(collection);
    list.addAll(seq.collection);
    return new OclSequence(list);
  }
  
  // TODO: Check OrderedSet-implementation of append
  public OclSortedCollection append(OclRoot obj) {
    if (isUndefined())
      return this;
    if (obj.isUndefined())
      return new OclOrderedSet(0, obj.getUndefinedReason());
    if (!(collection instanceof List)) {
      ArrayList list = new ArrayList(collection);
      list.add(obj);
      return new OclOrderedSet(list);
    } else {
      if (!collection.contains(obj))
        collection.add(obj);
      return this;
    }
  }
  
  // TODO: Check OrderedSet-implementation of prepend
  public OclSortedCollection prepend(OclRoot obj) {
    if (isUndefined())
      return this;
    if (obj.isUndefined())
      return new OclOrderedSet(0, obj.getUndefinedReason());
    ArrayList list = new ArrayList();
    list.add(obj);
    list.addAll(collection);
    return new OclOrderedSet(list);
  }

  // TODO: Check OrderedSet-implementation of insertAt
  public OclSortedCollection insertAt(OclInteger index, OclRoot obj) {
    if (isUndefined())
      return this;
    if (index.isUndefined())
      return new OclOrderedSet(0, index.getUndefinedReason());
    if (obj.isUndefined())
      return new OclOrderedSet(0, obj.getUndefinedReason());
    
    if (!(collection instanceof List)) {
      ArrayList list = new ArrayList(collection);
      if (!list.contains(obj))
        list.add(index.getInt() - 1, obj);
      return new OclOrderedSet(list);
    }
    if (!((List)collection).contains(obj))
      ((List)collection).add(index.getInt() - 1, obj);
    return this;
  }
  
  public OclOrderedSet subOrderedSet(OclInteger lower, OclInteger upper) {
    if (isUndefined())
      return this;
    if (lower.isUndefined())
      return new OclOrderedSet(0, lower.getUndefinedReason());
    if (upper.isUndefined())
      return new OclOrderedSet(0, upper.getUndefinedReason());
    try {
      List oldlist;
      if (!(collection instanceof List))
        oldlist = new ArrayList(collection);
      else
        oldlist = (List)collection;
      List newlist = oldlist.subList(lower.getInt() - 1, upper.getInt());
      
      return new OclOrderedSet(new ArrayList(newlist));
    } catch (IndexOutOfBoundsException e) {
      return new OclOrderedSet(0, "illegal index in OclOrderedSet substring("+lower+", "+upper+")");
    }
  }
  
}
