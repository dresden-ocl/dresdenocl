/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Model Bus Plug-in of Dresden OCL2 for Eclipse.

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
package org.dresdenocl.modelinstancetype.types.base;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.modelinstancetype.ModelInstanceTypePlugin;
import org.dresdenocl.modelinstancetype.exception.AsTypeCastException;
import org.dresdenocl.modelinstancetype.internal.ModelInstanceMessages;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceString;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.PrimitiveTypeKind;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Represents an adaptation for {@link String}s of a {@link JavaModelInstance}.
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
public class JavaModelInstanceString extends AbstractModelInstanceString
		implements IModelInstanceString {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelInstanceTypePlugin.getLogger(JavaModelInstanceString.class);

	/** The adapted {@link String} of this {@link JavaModelInstanceString}. */
	private String myString;

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceString}.
	 * </p>
	 * 
	 * @param string
	 *          The {@link String} that shall be adapted by this
	 *          {@link JavaModelInstanceString}.
	 */
	protected JavaModelInstanceString(String string) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceString("; //$NON-NLS-1$
			msg += "string = " + string; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myString = string;

		/* Initialize the type. */
		this.myType =
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclString();

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceString(String) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.modelinstancetype.types.IModelInstanceElement
	 * #asType(org.dresdenocl.pivotmodel.Type)
	 */
	public IModelInstanceElement asType(Type type) throws AsTypeCastException {

		if (type == null) {
			throw new IllegalArgumentException("Parameter 'type' must not be null.");
		}
		// no else.

		IModelInstanceElement result;

		/* By default the result is null. */
		result = null;

		/* Strings can only be casted to primitive types. */
		if (type instanceof PrimitiveType) {
			PrimitiveType primitiveType;
			primitiveType = (PrimitiveType) type;

			/* Check the given PrimitiveTypeKind. */
			if (primitiveType.getKind().equals(PrimitiveTypeKind.BOOLEAN)) {

				if (this.myString == null) {
					result = new JavaModelInstanceBoolean(null);
				}

				else if (this.myString.toLowerCase().equals("true")) {
					result = new JavaModelInstanceBoolean(true);
				}

				else if (this.myString.toLowerCase().equals("false")) {
					result = new JavaModelInstanceBoolean(false);
				}

				/* OCL has undefined booleans as well. Uncertain states are undefined. */
				else {
					result = new JavaModelInstanceBoolean(null);
				}
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.INTEGER)) {

				if (this.myString == null) {
					result = new JavaModelInstanceInteger(null);
				}

				else {
					try {
						result =
								new JavaModelInstanceInteger(Long.parseLong(this.myString));
					}

					catch (NumberFormatException e) {
						result = new JavaModelInstanceInteger(null);
					}
				}
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.REAL)) {

				if (this.myString == null) {
					result = new JavaModelInstanceReal(null);
				}

				else {
					try {
						result =
								new JavaModelInstanceReal(Double.parseDouble(this.myString));
					}

					catch (NumberFormatException e) {
						result = new JavaModelInstanceReal(null);
					}
				}
				// end else.
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.STRING)) {

				/* Create a new string to avoid side effects. */
				result = new JavaModelInstanceString(this.myString);
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
	 * @see org.dresdenocl.modelinstancetype.types.IModelInstanceElement
	 * #deepCopy()
	 */
	public IModelInstanceElement copyForAtPre() {

		return new JavaModelInstanceString(this.myString);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.modelinstancetype.types.IModelInstanceString
	 * #getString()
	 */
	public String getString() {

		return this.myString;
	}
}