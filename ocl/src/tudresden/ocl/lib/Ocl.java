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
// FILE: d:/java/classes/de/tudresden/ocl/Ocl.java

package tudresden.ocl.lib;
import java.util.*;

/** This class constitutes the central interface to generating classes in
 *  the OCL class library. An application should call one of the
 *  <code>getOclRepresentationFor</code> class methods whenever it wants to use
 *  any object or value with the library. This method than consults the
 *  installed OclFactory to create the correct representative for this object
 *  or value. The <CODE>getFor</CODE> methods are nothing but a shorthand for
 *  <CODE>getOclRepresentationFor</CODE>, which is self-speaking but tedious
 *  to write.
 *
 *  <p>If the OCL class library is to be used in a new context, especially with
 *  a new code generator, a new OclFactory might become necessary. It can be
 *  installed in the Ocl class using the <CODE>setFactory</CODE> method.
 *  Adaptation to name changes by code generators is possible through
 *  <code>NameAdapter</code>. A standard implementation of this interface is
 *  contained in the library with <code>SimpleNameAdapter</code> and 
 *  <code>ArgoNameAdapter</code>, but that is not set by default.
 *
 *  <p>Also, an implementation of <CODE>OclStateAdapter</CODE>
 *  might become necessary if
 *  the operation <CODE>oclInState</CODE> of <CODE>OclAny</CODE> is to be used.
 *  The implementation must be registered using <CODE>setStateAdapter</CODE>.
 *  In contrast to <CODE>OclFactory</CODE>, there is no standard
 *  implementation for <CODE>OclStateAdapter</CODE>. The same is true for the
 *  interface <code>AllInstancesAdapter</code>. Implemenentations might access
 *  a CASE platforms repository.
 *
 *  <p>Additionally, the switch <CODE>STRICT_VALUE_TYPES</CODE> can be set in
 *  this class to determine the runtime behaviour of the class library.
 *
 *  @see OclFactory
 *  @see DefaultOclFactory
 *  @see NameAdapter
 *  @see ArgoNameAdapter
 *  @see SimpleNameAdapter
 *  @see OclStateAdapter
 *  @see AllInstancesAdapter
 *  @see #setFactory(OclFactory of)
 *  @see #setNameAdapter(NameAdapter na)
 *  @see #STRICT_VALUE_TYPES
 *  @author Frank Finger
 */
public final class Ocl {

  /** If this attribute is set to true, an access to a non-existent attribute or
   *  method is evaluated as an undefined value; otherwise, an exception
   *  is thrown. Default is <code>true</code>.
   */
  public static boolean TOLERATE_NONEXISTENT_FIELDS=true;

  /** Some methods can be implemented more runtime efficient if their value
   *  type nature is ignored. An obvious example is the operation
   *  <code>including</code> defined on Collections. Its faster to just add the
   *  new Object to the existing collection and return this than to create a copy of
   *  the collection, add the new object to the copy and then return the copy.<br>
   *  Therefore, some operations are implemented in a specification-compliant
   *  and a fast way. If a class of this class library contains such an
   *  operation, it chooses the implementation by its instance attribute
   *  <CODE>STRICT_VALUE_TYPES</CODE>. The value of this attribute is copied
   *  in the concerning classes constructor from the class attribute
   *  <CODE>Ocl.STRICT_VALUE_TYPES</code>, which is set to <CODE>true</CODE>
   *  by default.
   */
  public static boolean STRICT_VALUE_TYPES = true;

  /** This class attribute determines wether OclType.name() will return
   *  fully-qualified or simple class names (i.e. the part fully qualified name
   *  after the last &quot;.&quot; character). By default, it is set to
   *  <CODE>false</CODE>
   */
  public static boolean JAVA_CLASS_NAMES=false;

  /** If this class attribute is set to <CODE>true</CODE>, a new OclCollection
   *  is automatically flattened upon creation. This means that every element
   *  of the <CODE>java.util.Collection</CODE> given as constructor parameter
   *  is tested for being an <CODE>OclCollection</CODE> object itself and,
   *  if so, is flattened. Flattening means that the original collection is
   *  removed from the new collection and instead all its elements are stored
   *  in the new collection. By default, this is set to <CODE>false</CODE>
   *  for performance reasons. The collection constructors are not
   *  called illegally from within the library, so strict flattening is not
   *  necessary usually.
   */
  public static boolean STRICT_FLATTENING = false;
  
  /** Determines, whether attributes of type java.util.Vector are treated
   * as Sequences or Sets.
   * Default is Sequence (false).
   * The other way is needed for older CASE-Tools and their code generators.
   * This effects both the ocl library and the reflection facade.
   * @see DefaultOclFactory#getOclRepresentationFor(Object)
   * @see tudresden.ocl.check.types.DefaultReflectionAdapter#getTypeForClass
   */
  public static boolean TAKE_VECTORS_AS_SET = false;

  /** this objects contains factory methods to generate OCL representations
   *  for application objects and values
   *
   *  @see #setFactory(OclFactory of)
   */
  protected static OclFactory factory=new DefaultOclFactory();

  /** a reference to the object that will be consulted if
   *  <CODE>oclInState</CODE> is called; by default it is
   *  <CODE>null</CODE>
   *
   *  @see OclAny#oclInState(OclState stat)
   */
  protected static OclStateAdapter stateAdapter;

  /** setting this adapter makes the operation allInstances evaluatable
   *
   *  @see OclType#allInstances()
   */
  protected static AllInstancesAdapter allInstancesAdapter;

  /** 
     This object transforms diagram names to possible implementation names.
     Default value is null, which causes the ocl library to throw 
     NullPointerExceptions, unless a NameAdapter has been set.
     @see #setNameAdapter
   */
  protected static NameAdapter nameAdapter=null;

  public static OclRoot getFor(Object o) {
    return getOclRepresentationFor(o);
  }

  public static OclRoot getOclRepresentationFor(Object o) {
    return factory.getOclRepresentationFor(o);
  }

	public static OclRoot getOclRepresentationForNull(Class c) {
		return factory.getOclRepresentationForNull(c);
	}
	
  public static OclBoolean getFor(boolean b) {
    return getOclRepresentationFor(b);
  }

  /** the representation of <CODE>boolean</CODE> is restricted to be OclBoolean
   */
  public static OclBoolean getOclRepresentationFor(boolean b) {
    return factory.getOclRepresentationFor(b);
  }

  public static OclRoot getFor(byte b) {
    return getOclRepresentationFor(b);
  }

  public static OclRoot getOclRepresentationFor(byte b) {
    return factory.getOclRepresentationFor(b);
  }

  public static OclRoot getFor(short s) {
    return getOclRepresentationFor(s);
  }

  public static OclRoot getOclRepresentationFor(short s) {
    return factory.getOclRepresentationFor(s);
  }

  public static OclRoot getFor(int i) {
    return getOclRepresentationFor(i);
  }

  public static OclRoot getOclRepresentationFor(int i) {
    return factory.getOclRepresentationFor(i);
  }

  public static OclRoot getFor(long l) {
    return getOclRepresentationFor(l);
  }

  public static OclRoot getOclRepresentationFor(long l) {
    return factory.getOclRepresentationFor(l);
  }

  public static OclRoot getFor(float f) {
    return getOclRepresentationFor(f);
  }

  public static OclRoot getOclRepresentationFor(float f) {
    return factory.getOclRepresentationFor(f);
  }

  public static OclRoot getFor(double d) {
    return getOclRepresentationFor(d);
  }

  public static OclRoot getOclRepresentationFor(double d) {
    return factory.getOclRepresentationFor(d);
  }

  public static OclRoot getFor(char c) {
    return getOclRepresentationFor(c);
  }

  public static OclRoot getOclRepresentationFor(char c) {
    return factory.getOclRepresentationFor(c);
  }

  /** calls the factory to retransform the OCL library representation of an
   *  object into the application representation denoted by the parameter type
   *
   *  @see OclFactory#reconvert(Class c, OclRoot or)
   */
  public static Object reconvert(Class type, OclRoot oclObject) {
    return factory.reconvert(type, oclObject);
  }

  /** This method uses the factory to create an OclSequence representation for
   *  the given Object. It is called from
   *  OclAnyImpl.getFeatureAsSequence(String).
   *
   *  @see OclFactory#getOclSequenceFor(Object o)
   *  @see OclAnyImpl#getFeatureAsSequence(String s)
   */
  public static OclSequence getOclSequenceFor(Object o) {
    return factory.getOclSequenceFor(o);
  }

  public static void setFactory(OclFactory of) {
    factory=of;
  }


  // -------------------- state adapter methods -------------------------------

  public static OclState getOclStateFor(String s) {
    return factory.getOclStateFor(s);
  }

  public static void setStateAdapter(OclStateAdapter adapter) {
    stateAdapter=adapter;
  }

  public static OclBoolean objectInState(Object o, OclState state) {
    boolean ret=stateAdapter.objectInState(o, state.getStateName());
    return getOclRepresentationFor(ret);
  }


  // -------------------- name adapter methods --------------------------------

  static
  {
    String na=System.getProperty("tudresden.ocl.lib.nameadapter");
    if(na==null)
      nameAdapter=new SimpleNameAdapter();
    else
    {
      try
      {
        nameAdapter=(NameAdapter)(Class.forName(na).newInstance());
      }
      catch(ClassNotFoundException e) { throw new RuntimeException(e.toString()); }
      catch(InstantiationException e) { throw new RuntimeException(e.toString()); }
      catch(IllegalAccessException e) { throw new RuntimeException(e.toString()); }
    }
  }
  
  /** makes the methods <CODE>getName</CODE> and
   *  <CODE>getPossibleAssociationNames</CODE> use the given name adapter
   *  The ocl library will not work until a NameAdapter is provided.
   *
   *  @see #getNames(String n)
   *  @see #getPossibleAssociationNames(String n)
   */
  public static void setNameAdapter(NameAdapter na) 
  {
    nameAdapter=na;
  }

  /** 
     Use the NameAdapter to find possible implementation names.
     Throws NullPointerException, if no NameAdapter is set.
     @see #setNameAdapter
  */
  public static String[] getNames(String n) 
  {
    return nameAdapter.getNames(n);
  }

  /** 
     Use the NameAdapter to find association names that might have been
     converted into the given implementation name.
     Throws NullPointerException, if no NameAdapter is set.
     @see #setNameAdapter
  */
  public static String[] getPossibleAssociationNames(String n) 
  {
    return nameAdapter.getPossibleAssociationNames(n);
  }


  // -------------------- converter methods -----------------------------------

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclSizable;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclSizable toOclSizable(OclRoot or) {
    if(or.isUndefined())
      return new OclString(0,or.getUndefinedReason());
    try {
      return (OclSizable) or;
    } catch (ClassCastException e) {
      return new OclSequence(0,"tried to cast "+or+" to OclSizable");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclCollection;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclCollection toOclCollection(OclRoot or) {
    if(or.isUndefined())
      return new OclSet(0,or.getUndefinedReason());
    try {
      return (OclCollection) or;
    } catch (ClassCastException e) {
      return new OclSequence(0,"tried to cast "+or+" to OclCollection");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclSubtractable;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclSubtractable toOclSubtractable(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclSubtractable) or;
    } catch (ClassCastException e) {
      return new OclReal(0,"tried to cast "+or+" to OclSubtractable");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclUnsortedCollection;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclUnsortedCollection toOclUnsortedCollection(OclRoot or) {
    if(or.isUndefined())
      return new OclSet(0,or.getUndefinedReason());
    try {
      return (OclUnsortedCollection) or;
    } catch (ClassCastException e) {
      return new OclSet(0,"tried to cast "+or+" to OclUnsortedCollection");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclSet;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclSet toOclSet(OclRoot or) {
    if(or.isUndefined())
      return new OclSet(0,or.getUndefinedReason());
    try {
      return (OclSet) or;
    } catch (ClassCastException e) {
      return new OclSet(0,"tried to cast "+or+" to OclSet");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclBag;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclBag toOclBag(OclRoot or) {
    if(or.isUndefined())
      return new OclBag(0,or.getUndefinedReason());
    try {
      return (OclBag) or;
    } catch (ClassCastException e) {
      return new OclBag(0,"tried to cast "+or+" to OclBag");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclSequence;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclSequence toOclSequence(OclRoot or) {
    if(or.isUndefined())
      return new OclSequence(0,or.getUndefinedReason());
    try {
      return (OclSequence) or;
    } catch (ClassCastException e) {
      return new OclSequence(0,"tried to cast "+or+" to OclSequence");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclString;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclString toOclString(OclRoot or) {
    if(or.isUndefined())
      return new OclString(0,or.getUndefinedReason());
    try {
      return (OclString) or;
    } catch (ClassCastException e) {
      return new OclString(0,"tried to cast "+or+" to OclString");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclBoolean;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclBoolean toOclBoolean(OclRoot or) {
    if(or.isUndefined())
      return new OclBoolean(0,or.getUndefinedReason());
    try {
      return (OclBoolean) or;
    } catch (ClassCastException e) {
      return new OclBoolean(0,"tried to cast "+or+" to OclBoolean");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclComparable;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclComparable toOclComparable(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclComparable) or;
    } catch (ClassCastException e) {
      return new OclReal(0,"tried to cast "+or+" to OclComparable");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclAddable;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclAddable toOclAddable(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclAddable) or;
    } catch (ClassCastException e) {
      return new OclReal(0,"tried to cast "+or+" to OclAddable");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclMultiplyable;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclMultiplyable toOclMultiplyable(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclMultiplyable) or;
    } catch (ClassCastException e) {
      return new OclReal(0,"tried to cast "+or+" to OclMultiplyable");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclReal;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclReal toOclReal(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclReal) or;
    } catch (ClassCastException e) {
      return new OclReal(0,"tried to cast "+or+" to OclReal");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclInteger;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclInteger toOclInteger(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclInteger) or;
    } catch (ClassCastException e) {
      return new OclInteger(0,"tried to cast "+or+" to OclInteger");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclAny;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclAny toOclAny(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclAny) or;
    } catch (ClassCastException e) {
      return new OclAnyImpl(0,"tried to cast "+or+" to OclAny");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclAnyImpl;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclAnyImpl toOclAnyImpl(OclRoot or) {
    if(or.isUndefined())
      return new OclAnyImpl(0,or.getUndefinedReason());
    try {
      return (OclAnyImpl) or;
    } catch (ClassCastException e) {
      return new OclAnyImpl(0,"tried to cast "+or+" to OclAnyImpl");
    }
  }

  // ------------- all instances adapter methods -------

  public static void setAllInstancesAdapter(AllInstancesAdapter aia) {
    allInstancesAdapter=aia;
  }

  /** @throws OclException no AllInstancesAdapter has been set
   */
  public static OclSet getAllInstances(OclType t) {
    if (allInstancesAdapter!=null) {
      return allInstancesAdapter.getAllInstances(t);
    } else {
      throw new OclException("allInstances not evaluatable");
    }
  }


} /* end class Ocl */

