/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Model Instance Plug-in of Dresden OCL2 for Eclipse.

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
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.internal.ModelBusMessages;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * An adapter {@link Class} for {@link ModelInstanceEnumerationLiteral}s of
 * {@link IModelInstance}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelInstanceEnumerationLiteral extends
		AbstractModelInstanceEnumerationLiteral implements
		IModelInstanceEnumerationLiteral {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelBusPlugin.getLogger(ModelInstanceEnumerationLiteral.class);

	/**
	 * <p>
	 * The adapted {@link EnumerationLiteral} of this
	 * {@link ModelInstanceEnumerationLiteral}.
	 * </p>
	 */
	private EnumerationLiteral myLiteral;

	/**
	 * <p>
	 * Creates a new {@link ModelInstanceEnumerationLiteral} for a given
	 * {@link EnumerationLiteral}.
	 * </p>
	 * 
	 * @param literal
	 *          The {@link EnumerationLiteral} that shall be adapted.
	 */
	protected ModelInstanceEnumerationLiteral(EnumerationLiteral literal) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "ModelInstanceEnumerationLiteral("; //$NON-NLS-1$
			msg += "literal = " + literal; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myLiteral = literal;

		this.myTypes = new HashSet<Type>();
		// FIXME How to determine the type of the enumeration literal?
		if (myLiteral != null)
			this.myTypes.add(this.myLiteral.getEnumeration());

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "ModelInstanceEnumerationLiteral(Enum<?>, "; //$NON-NLS-1$
			msg += "Set<Type>) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #asType(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public IModelInstanceElement asType(Type type) throws AsTypeCastException {

		if (type == null) {
			throw new IllegalArgumentException("Parameter 'type' must not be null.");
		}
		// no else.

		IModelInstanceElement result;

		result = null;

		/* Check if the given type is the type of this literal. */
		if (this.myTypes.contains(type)) {

			Set<Type> types;
			types = new HashSet<Type>();
			types.add(type);

			result = new ModelInstanceEnumerationLiteral(this.myLiteral);
		}
		// no else.

		/* Probably throw an AsTypeCastException. */
		if (result == null) {
			String msg;

			msg = ModelBusMessages.IModelInstanceElement_CannotCast;
			msg = NLS.bind(msg, this.getName(), type.getName());

			throw new AsTypeCastException(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #copyForAtPre()
	 */
	public IModelInstanceElement copyForAtPre() {

		/* Return a copy of the literal. */
		return new ModelInstanceEnumerationLiteral(this.myLiteral);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceEnumerationLiteral
	 * #getLiteral()
	 */
	public EnumerationLiteral getLiteral() {

		return this.myLiteral;
	}
}