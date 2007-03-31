/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
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

/**
 * <code>OclRoot</code> is the base interface for the OCL Standard Library type hierarchy. It
 * defines common operations of {@link OclObject objects} and {@link OclType types}. In essence,
 * these provide the means to reflectively retrieve property values and invoke operations. Note that
 * classes realizing this interface (or one of its sub-interfaces) always represent a
 * domain-specific implementation of the corresponding OCL concept. Thus, any property values or
 * operations return values have to be wrapped into a corresponding <code>OclRoot</code> object
 * again. <code>OclRoot</code> extends {@link OclAdapter} to enable easy access to the adapted
 * domain-specific object.
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface OclRoot extends OclAdapter {

  /**
   * Returns the value of the property with the given name.
   * 
   * @param propertyName the name of the property
   * 
   * @return the value of the property as an <code>OclRoot</code>
   */
  OclRoot getPropertyValue(String propertyName);

  /**
   * Returns the qualified property value with the given name. This is meant to support hashtable
   * properties or the access to multi-valued properties via an index.
   * 
   * @param propertyName
   * @param qualified
   * 
   * @return
   */
  OclRoot getPropertyValue(String propertyName, OclRoot qualifier);

  /**
   * @param propertyName
   * @return
   */
  OclSequence<OclRoot> getPropertyValueAsSequence(String propertyName);

  /**
   * @param propertyName
   * @return
   */
  OclBag<OclRoot> getPropertyValueAsBag(String propertyName);

  /**
   * @param propertyName
   * @return
   */
  OclSet<OclRoot> getPropertyValueAsSet(String propertyName);

  /**
   * @param propertyName
   * @return
   */
  OclOrderedSet<OclRoot> getPropertyValueAsOrderedSet(String propertyName);

  /**
   * @param operationName
   * @param parameters
   * @return
   */
  OclRoot invokeOperation(String operationName, OclRoot[] parameters);

}
