package org.dresdenocl.modelinstancetype.types;

import org.eclipse.osgi.util.NLS;

import org.dresdenocl.modelinstancetype.exception.AsTypeCastException;
import org.dresdenocl.modelinstancetype.exception.CopyForAtPreException;
import org.dresdenocl.modelinstancetype.internal.ModelInstanceMessages;
import org.dresdenocl.pivotmodel.PrimitiveTypeKind;
import org.dresdenocl.pivotmodel.Type;

public interface IModelInstanceInvalid extends IModelInstancePrimitiveType {

	static final IModelInstanceInvalid INSTANCE = new IModelInstanceInvalid() {

		/*
		 * (non-Javadoc)
		 * @see
		 * org.dresdenocl.modelinstancetype.types.IModelInstanceElement
		 * #asType(org.dresdenocl.pivotmodel.Type)
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
		 * org.dresdenocl.modelinstancetype.types.IModelInstanceElement
		 * #copyForAtPre()
		 */
		public IModelInstanceElement copyForAtPre() throws CopyForAtPreException {

			return this;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.dresdenocl.modelinstancetype.types.IModelInstanceElement
		 * #getName()
		 */
		public String getName() {

			return "invalid";
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.dresdenocl.modelinstancetype.types.IModelInstanceElement
		 * #getType()
		 */
		// FIXME Michael: needs own meta-type
		public Type getType() {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.dresdenocl.modelinstancetype.types.IModelInstanceElement
		 * #isKindOf(org.dresdenocl.pivotmodel.Type)
		 */
		// FIXME Michael: needs own meta-type
		public boolean isKindOf(Type aType) {

			return false;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.dresdenocl.modelinstancetype.types.IModelInstanceElement
		 * #isTypeOf(org.dresdenocl.pivotmodel.Type)
		 */
		// FIXME Michael: needs own meta-type
		public boolean isTypeOf(Type aType) {

			return false;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.dresdenocl.modelinstancetype.types.IModelInstanceElement
		 * #isUndefined()
		 */
		public boolean isUndefined() {

			return false;
		}

	};
}