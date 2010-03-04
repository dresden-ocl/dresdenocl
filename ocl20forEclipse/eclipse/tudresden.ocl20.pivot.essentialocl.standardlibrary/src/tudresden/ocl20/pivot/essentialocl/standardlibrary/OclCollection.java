/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * $Id$
 */
package tudresden.ocl20.pivot.essentialocl.standardlibrary;

import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface OclCollection<T extends OclAny> extends OclLibraryObject {

	/**
	 * Returns the wrapped {@link IModelInstanceCollection}.
	 * 
	 * @return the wrapped {@link IModelInstanceCollection}
	 */
	IModelInstanceCollection<IModelInstanceElement> getModelInstanceCollection();

	/**
	 * Returns the generic/element type of this collection.
	 * 
	 * @return the generic/element type of this collection
	 */
	Type getGenericType();

	/**
	 * 
	 * @return the number of elements in the collection <code>this</code>.
	 */
	OclInteger size();

	/**
	 * 
	 * @param object
	 * @return true if <code>object</code> is an element of <code>this</code>,
	 *         false otherwise.
	 */
	OclBoolean includes(T object);

	/**
	 * 
	 * @param object
	 * @return true if <code>object</code> is not an element of <code>this</code>,
	 *         false otherwise.
	 */
	OclBoolean excludes(T object);

	/**
	 * 
	 * @param object
	 * @return the number of times that <code>object</code> occurs in the
	 *         collection <code>this</code>.
	 */
	OclInteger count(T object);

	/**
	 * 
	 * @param c2
	 * @return does <code>this</code> contain all the elements of <code>c2</code>?
	 */
	OclBoolean includesAll(OclCollection<T> c2);

	/**
	 * 
	 * @param c2
	 * @return does <code>this</code> contain none of the elements of
	 *         <code>c2</code>?
	 */
	OclBoolean excludesAll(OclCollection<T> c2);

	/**
	 * 
	 * @return is <code>this</code> the empty collection?
	 */
	OclBoolean isEmpty();

	/**
	 * 
	 * @return is <code>this</code> not the empty collection?
	 */
	OclBoolean notEmpty();

	/**
	 * The addition of all elements in <code>this</code>. Elements must be of a
	 * type supporting the + operation. The + operation must take one parameter of
	 * type <code>T</code> and be both associative: (a+b)+c = a+(b+c), and
	 * commutative: a+b = b+a. {@link OclInteger} and {@link OclReal} fulfill this
	 * condition.
	 * 
	 * @return the addition of all elements in <code>this</code>.
	 */
	T sum();

	/**
	 * 
	 * @return the {@link OclBag} that contains all the elements from
	 *         <code>this</code>.
	 */
	OclBag<T> asBag();

	/**
	 * 
	 * @return an {@link OclOrderedSet} that contains all the elements from
	 *         <code>this</code>, in undefined order.
	 */
	OclOrderedSet<T> asOrderedSet();

	/**
	 * 
	 * @return a {@link OclSequence} that contains all the elements from
	 *         <code>this</code>, in undefined order.
	 */
	OclSequence<T> asSequence();

	/**
	 * 
	 * @return a {@link OclSet} that contains all the elements from
	 *         <code>this</code>, with duplbicates removed.
	 */
	OclSet<T> asSet();

	/**
	 * 
	 * @param <T2>
	 * @param c2
	 * @return the cartesian product operation of <code>this</code> and
	 *         <code>c2</code>.
	 */
	<T2 extends OclAny> OclSet<OclTuple> product(OclCollection<T2> c2);

	/**
	 * If the element type is not a collection type, this results in the same bag.
	 * If the element type is a collection type.
	 * 
	 * @return the bag containing all the elements of all the elements of
	 *         <code>this</code>.
	 */
	<T2 extends OclAny> OclCollection<T2> flatten();

	/**
	 * Returns an {@link OclIterator} to iterate over this collection of T's. The
	 * OCL Interpreter can use this mechanism to not get in touch with
	 * {@link IModelInstanceElement}s and still get the content of the
	 * {@link OclCollection}.
	 * 
	 * @return an iterator for this collection
	 */
	OclIterator<T> getIterator();

	/**
	 * The element with the maximum value of all elements in <code>this</code>.
	 * Elements must be of a type supporting the max operation. The max operation
	 * - supported by the elements - must take one parameter of type T and be both
	 * associative and commutative. Integer and Real fulfill this condition.
	 * 
	 * @return the element with the maximum value of all elements in
	 *         <code>this</code>
	 */
	T max();

	/**
	 * The element with the minimum value of all elements in <code>this</code>.
	 * Elements must be of a type supporting the min operation. The min operation
	 * - supported by the elements - must take one parameter of type T and be both
	 * associative and commutative. Integer and Real fulfill this condition.
	 * 
	 * @return the element with the minimum value of all elements in
	 *         <code>this</code>
	 */
	T min();

}
