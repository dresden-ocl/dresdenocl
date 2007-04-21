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
package tudresden.ocl20.pivot.pivotmodel.impl;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.pivotmodel.GenericElement;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypeParameter;

/**
 * This class contains utility methods that are used by {@link GenericElement}s. This is necessary
 * because generic elements do not share a common superclass. Due to overlapping inheritance
 * hierachies, <code>GenericElement</code> is only realized as a mixin interface.
 * 
 * @author Matthias Braeuer
 * @version 1.0 29.03.2007
 */
public class GenericElements {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(GenericElements.class);

  /**
   * This class is not meant to be instantiated.
   */
  private GenericElements() {
    // no implementation necessary
  }

  /**
   * This method determines a <code>String</code> key to be used for caching already bound
   * {@link GenericElement}s. The key will contain the name of the generic element and the type
   * parameters that have not yet been bound (<code>unboundParameters</code>), the parameters
   * that are supposed to be bound (<code>parametersToBind</code>) and the types that will be
   * bound to the type parameters (<code>typesToBind</code>). Thus, the key uniquely identifies
   * a particular binding, whether the generic element had previously been bound or not.
   * 
   * <p>
   * Example: Binding both type parameters of {@code Map<K,V>} with the type named
   * <code>Boolean</code> will result in the key "<code>Map<K,V>:K->Boolean,V->Boolean</code>".
   * Note that the given lists must not contain <code>null</code> values.
   * </p>
   */
  public static String determineBindingKey(GenericElement genericElement,
      List<TypeParameter> parametersToBind, List<? extends Type> typesToBind) {

    // initialize empty
    StringBuilder bindingKey = new StringBuilder();

    // append fully qualified name of genericElement
    bindingKey.append(genericElement.getQualifiedName());

    // append unbound type parameters
    bindingKey.append('<');

    for (Iterator<TypeParameter> i = genericElement.getOwnedTypeParameter().iterator(); i.hasNext();) {
      bindingKey.append(i.next().getName());

      if (i.hasNext()) {
        bindingKey.append(',');
      }
    }

    bindingKey.append('>');
    bindingKey.append(':');

    // append type parameters to bind and the corresponding type
    for (ListIterator<TypeParameter> it = parametersToBind.listIterator(); it.hasNext();) {
      bindingKey.append(it.next().getName()).append("->").append( //$NON-NLS-1$
          typesToBind.get(it.previousIndex()).getQualifiedName());

      if (it.hasNext()) {
        bindingKey.append(',');
      }
    }

    if (logger.isDebugEnabled()) {
      logger.debug("determineBindingKey() - exit - return value=" + bindingKey); //$NON-NLS-1$
    }

    return bindingKey.toString();
  }
}
