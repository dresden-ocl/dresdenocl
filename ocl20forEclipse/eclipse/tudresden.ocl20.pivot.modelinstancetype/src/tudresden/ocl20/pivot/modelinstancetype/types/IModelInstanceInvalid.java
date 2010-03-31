package tudresden.ocl20.pivot.modelinstancetype.types;

import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelinstancetype.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelinstancetype.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelinstancetype.internal.ModelInstanceMessages;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

public interface IModelInstanceInvalid extends IModelInstancePrimitiveType {

	static final IModelInstanceInvalid INSTANCE = new IModelInstanceInvalid() {

		/*
		 * (non-Javadoc)
		 * @see
		 * tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
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
		 * @see
		 * tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #copyForAtPre()
		 */
		public IModelInstanceElement copyForAtPre() throws CopyForAtPreException {

			return this;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #getName()
		 */
		public String getName() {

			return "invalid";
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #getType()
		 */
		// FIXME Michael: needs own meta-type
		public Type getType() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #isKindOf(tudresden.ocl20.pivot.pivotmodel.Type)
		 */
		// FIXME Michael: needs own meta-type
		public boolean isKindOf(Type aType) {

			return false;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #isTypeOf(tudresden.ocl20.pivot.pivotmodel.Type)
		 */
		// FIXME Michael: needs own meta-type
		public boolean isTypeOf(Type aType) {

			return false;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
		 * #isUndefined()
		 */
		public boolean isUndefined() {

			return false;
		}

	};
}