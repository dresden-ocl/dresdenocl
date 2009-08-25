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
package tudresden.ocl20.pivot.modelbus.modelinstance.types.base;

import java.util.HashSet;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

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
public class JavaModelInstanceString extends AbstractModelInstanceElement
		implements IModelInstanceString {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelBusPlugin.getLogger(JavaModelInstanceString.class);

	/**
	 * The {@link Type} of this {@link Type} implementation. Because
	 * {@link PrimitiveType}s are not part of the {@link IModel}, their
	 * {@link Type} must be created externally. This field represents the
	 * {@link PrimitiveType} instance that is the only {@link Type} of all
	 * {@link JavaModelInstanceString}s.
	 */
	private static final PrimitiveType MODEL_TYPE =
			PivotModelFactory.INSTANCE.createPrimitiveType();
	{
		MODEL_TYPE.setKind(PrimitiveTypeKind.STRING);
	}

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
		this.myTypes = new HashSet<Type>();
		this.myTypes.add(JavaModelInstanceString.MODEL_TYPE);

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
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceString#getString
	 * ()
	 */
	public String getName() {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		resultBuffer.append(this.getClass().getSimpleName());
		resultBuffer.append("[");
		resultBuffer.append(this.myString);
		resultBuffer.append("]");

		return resultBuffer.toString();
	}

	public IModelInstanceElement asType(Type type) {

		IModelInstanceElement result;

		/*
		 * FIXME Claas: Ask Micha how the undefined problem can be solved. By
		 * default the result is null.
		 */
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
					result = new JavaModelInstanceInteger(Long.parseLong(this.myString));
				}
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.REAL)) {

				if (this.myString == null) {
					result = new JavaModelInstanceReal(null);
				}

				else {
					result = new JavaModelInstanceReal(Double.parseDouble(this.myString));
				}
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.STRING)) {

				/* Create a new string to avoid side effects. */
				result = new JavaModelInstanceString(this.myString);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #deepCopy()
	 */
	public Object copyForAtPre() {

		return new JavaModelInstanceString(this.myString);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString
	 * #getString()
	 */
	public String getString() {

		return this.myString;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #isUndefined()
	 */
	public boolean isUndefined() {

		return (this.myString == null);
	}
}