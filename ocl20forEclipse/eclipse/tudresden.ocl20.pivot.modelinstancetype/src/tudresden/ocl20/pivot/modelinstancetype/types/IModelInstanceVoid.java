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
package tudresden.ocl20.pivot.modelinstancetype.types;

import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.modelinstancetype.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelinstancetype.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelinstancetype.internal.ModelInstanceMessages;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents results of {@link Operation}s' invocations with a result
 * {@link Type} of the {@link PrimitiveTypeKind#VOID}.
 * </p>
 * 
 * <p>
 * <strong>This interface should not be implemented!<strong> The singleton
 * instance is available via the constant {@link IModelInstanceVoid#INSTANCE}.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IModelInstanceVoid extends IModelInstancePrimitiveType {

	/**
	 * <p>
	 * The singleton instance of {@link IModelInstanceVoid}.
	 * </p>
	 */
	static final IModelInstanceVoid INSTANCE = new IModelInstanceVoid() {

		/*
		 * (non-Javadoc)
		 * @see tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #asType(tudresden.ocl20.pivot.pivotmodel.Type)
		 */
		public IModelInstanceElement asType(Type type) throws AsTypeCastException {

			String msg;
			msg = ModelInstanceMessages.IModelInstanceElement_CannotCast;
			msg =
					NLS.bind(msg, PrimitiveTypeKind.VOID.getName(), type
							.getQualifiedName());

			throw new AsTypeCastException(msg);
		}

		/*
		 * (non-Javadoc)
		 * @see tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #copyForAtPre()
		 */
		public IModelInstanceElement copyForAtPre() throws CopyForAtPreException {

			return this;
		}

		/*
		 * (non-Javadoc)
		 * @see tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #getName()
		 */
		public String getName() {

			return "null";
		}

		/*
		 * (non-Javadoc)
		 * @see tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #getType()
		 */
		public Type getType() {

			return EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
					.getOclVoid();
		}

		/*
		 * (non-Javadoc)
		 * @see tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #isInstanceOf(tudresden.ocl20.pivot.pivotmodel.Type)
		 */
		// FIXME Michael: if OclInvalid has own meta-type, this should check for it
		public boolean isKindOf(Type type) {

			/*
			 * According to OCL 2.0 specification p. 138:
			 * "The type OclVoid is a type that conforms to all other types."
			 */
			return true;
		}

		/*
		 * (non-Javadoc)
		 * @see tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #isTypeOf(tudresden.ocl20.pivot.pivotmodel.Type)
		 */
		public boolean isTypeOf(Type type) {

			return EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
					.getOclVoid().equals(type);
		}

		/*
		 * (non-Javadoc)
		 * @see tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #isUndefined()
		 */
		public boolean isUndefined() {

			return true;
		}
	};
}