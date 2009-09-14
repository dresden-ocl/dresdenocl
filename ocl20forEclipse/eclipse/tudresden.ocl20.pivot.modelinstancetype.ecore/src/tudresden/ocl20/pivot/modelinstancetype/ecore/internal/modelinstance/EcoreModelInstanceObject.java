/*
Copyright (C) 2007-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Ecore Meta Model of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.modelinstancetype.ecore.internal.modelinstance;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.AbstractModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.ecore.EcoreModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.provider.EcoreModelInstanceProvider;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Implements the interface {@link IModelInstanceObject} for EMF Ecore
 * {@link EObject}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class EcoreModelInstanceObject extends AbstractModelInstanceElement
		implements IModelInstanceObject {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			EcoreModelInstanceTypePlugin.getLogger(EcoreModelInstanceProvider.class);

	/** The {@link EObject} adapted by this {@link EcoreModelInstanceObject}. */
	private EObject myEObject;

	/**
	 * <p>
	 * Creates a new {@link EcoreModelInstanceObject} for a given {@link EObject}
	 * and a given {@link Set} of {@link Type}s.
	 * </p>
	 * 
	 * @param object
	 *          The {@link EObject} that shall be adapted by this
	 *          {@link EcoreModelInstanceObject}.
	 * @param types
	 *          The {@link Type}s the adapted {@link EObject} implements.
	 */
	public EcoreModelInstanceObject(EObject eObject, Set<Type> types) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreModelInstanceObject("; //$NON-NLS-1$
			msg += "eObject = " + eObject; //$NON-NLS-1$
			msg += ", types = " + types; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myEObject = eObject;
		this.myTypes = types;

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreModelInstanceObject(EObject, Set<Type>) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject
	 * #getObject()
	 */
	public Object getObject() {

		return this.myEObject;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #isUndefined()
	 */
	public boolean isUndefined() {

		return this.myEObject != null;
	}

	/** FIXME Claas: IMPLEMENTED_TILL_HERE. */
	private static final int IMPLEMENTED_TILL_HERE = 0;

	public IModelInstanceElement getProperty(Property property)
			throws PropertyAccessException, PropertyNotFoundException {

		// TODO Auto-generated method stub
		return null;
	}

	public IModelInstanceElement invokeOperation(Operation operation,
			List<IModelInstanceElement> args) throws OperationNotFoundException,
			OperationAccessException {

		// TODO Auto-generated method stub
		return null;
	}

	public IModelInstanceElement asType(Type type) throws AsTypeCastException {

		// TODO Auto-generated method stub
		return null;
	}

	public IModelInstanceElement copyForAtPre() throws CopyForAtPreException {

		// TODO Auto-generated method stub
		return null;
	}
}