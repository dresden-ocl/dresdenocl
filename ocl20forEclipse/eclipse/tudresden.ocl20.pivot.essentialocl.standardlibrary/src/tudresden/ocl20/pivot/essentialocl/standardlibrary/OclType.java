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

import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface OclType<T extends OclAny> extends OclAny {

	/**
	 * The only method valid for OclType except "=" and "<>". Returns the wrapped
	 * {@link Type} of the PivotModel.
	 * 
	 * @return the wrapped {@link Type} of the PivotModel
	 */
	Type getType();

	OclBoolean isEqualTo(OclType<OclAny> type2);

	OclBoolean isNotEqualTo(OclType<OclAny> type2);

	/**
	 * <p>
	 * Returns the value of a given static {@link Property} defined on this
	 * {@link OclType}.
	 * </p>
	 * 
	 * @param property
	 *          The static {@link Property} whose value shall be returned.
	 * @param modelInstance
	 *          The {@link IModelInstance} that shall be used to retrieve the
	 *          {@link Property}'s value.
	 * @return The value(as an {@link OclAny}) of the static {@link Property}.
	 */
	OclAny getStaticProperty(Property property, IModelInstance modelInstance);

	/**
	 * <p>
	 * Tries to invoke a given static {@link Operation} of this {@link OclType}
	 * and returns the invocation result.
	 * </p>
	 * 
	 * @param operation
	 *          The static {@link Operation} that shall be invoked.
	 * @param oclAnyParameters
	 *          The probably existing parameters of the {@link Operation} as an
	 *          array of {@link OclAny}.
	 * @param modelInstance
	 *          The {@link IModelInstance} that shall be used to invoke the static
	 *          {@link Operation}.
	 * @return The result as an {@link OclAny}.
	 */
	OclAny invokeStaticOperation(Operation operation, OclAny[] oclAnyParameters,
			IModelInstance modelInstance);
}