package org.dresdenocl.standardlibrary.java.internal.library;

import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.essentialocl.standardlibrary.OclInteger;
import org.dresdenocl.essentialocl.standardlibrary.OclReal;
import org.dresdenocl.essentialocl.standardlibrary.OclString;


/**
 * All elements of the standard library that can be added using the sum()
 * function on collections have to implement this interface. It provides access
 * to the add function and the neutral element if the collection is empty.
 * 
 * @author Michael Thiele
 * 
 */
public interface IAddableElement {

	// FIXME Michael: need an instance to do this
	/**
	 * Returns the neutral element of the add-able element. For {@link OclInteger}
	 * and {@link OclReal} this is <strong>0</strong>, for {@link OclString} it
	 * would be <strong>""</strong>.
	 * 
	 * @return the neutral element of the add-able element
	 */
	OclAny getNeutralElement();

	/**
	 * Invokes the add-operation of the element.
	 * 
	 * @param that
	 *          the value to add to this element
	 * @return the result of the addition
	 */
	OclAny add(OclAny that);

}
