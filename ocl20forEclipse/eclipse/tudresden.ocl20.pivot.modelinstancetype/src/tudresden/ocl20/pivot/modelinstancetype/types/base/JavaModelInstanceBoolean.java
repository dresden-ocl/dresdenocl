/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Model Instance implementation of Dresden OCL2
for Eclipse.

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
package tudresden.ocl20.pivot.modelinstancetype.types.base;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.essentialocl.types.TypeConstants;
import tudresden.ocl20.pivot.modelinstancetype.ModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelinstancetype.internal.ModelInstanceMessages;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents an adaptation for {@link Boolean}s of a {@link JavaModelInstance}.
 * </p>
 * 
 * <p>
 * This type is located in the ModelBus plug-in because the standard library and
 * the Java model instance type plug-in both require such an implementation but
 * are not allowed to know each other.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceBoolean extends AbstractModelInstanceBoolean
		implements IModelInstanceBoolean {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelInstanceTypePlugin.getLogger(JavaModelInstanceBoolean.class);

	/** The adapted {@link Boolean} of this {@link JavaModelInstanceBoolean}. */
	private Boolean myBoolean;

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceBoolean}.
	 * </p>
	 * 
	 * @param aBoolean
	 *          The adapted {@link Boolean} of this
	 *          {@link JavaModelInstanceBoolean}.
	 */
	protected JavaModelInstanceBoolean(Boolean aBoolean) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceBoolean("; //$NON-NLS-1$
			msg += "aBoolean = " + aBoolean; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myBoolean = aBoolean;

		/* Initialize the type. */
		this.myType = TypeConstants.BOOLEAN;

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceBoolean(Boolean) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
	 * #asType(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public IModelInstanceElement asType(Type type) throws AsTypeCastException {

		if (type == null) {
			throw new IllegalArgumentException("Parameter 'type' must not be null.");
		}
		// no else.

		IModelInstanceElement result;

		/* By default the result is null. */
		result = null;

		/* Booleans can only be casted to primitive types. */
		if (type instanceof PrimitiveType) {

			PrimitiveType primitiveType;
			primitiveType = (PrimitiveType) type;

			/* Check the given PrimitiveTypeKind. */
			if (primitiveType.getKind().equals(PrimitiveTypeKind.BOOLEAN)) {

				/* Create a new boolean to avoid side effects. */
				result = new JavaModelInstanceBoolean(this.myBoolean);
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.STRING)) {

				if (this.myBoolean == null) {
					result = new JavaModelInstanceString(null);
				}

				else {
					result = new JavaModelInstanceString(this.myBoolean.toString());
				}
			}
			// no else.
		}
		// no else.

		/* Probably throw an AsTypeCastException. */
		if (result == null) {
			String msg;

			msg = ModelInstanceMessages.IModelInstanceElement_CannotCast;
			msg = NLS.bind(msg, this.getName(), type.getName());

			throw new AsTypeCastException(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
	 * #deepCopy()
	 */
	public IModelInstanceElement copyForAtPre() {

		return new JavaModelInstanceBoolean(this.myBoolean);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceBoolean#getBoolean
	 * ()
	 */
	public Boolean getBoolean() {

		return this.myBoolean;
	}
}