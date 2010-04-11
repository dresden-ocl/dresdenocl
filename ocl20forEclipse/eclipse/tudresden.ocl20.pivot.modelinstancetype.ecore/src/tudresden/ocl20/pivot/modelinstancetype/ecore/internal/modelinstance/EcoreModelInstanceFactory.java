/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the EMF Ecore Model Instance Type of Dresden OCL2 for Eclipse.

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
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelinstancetype.ecore.EcoreModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.msg.EcoreModelInstanceTypeMessages;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.util.EcoreModelInstanceTypeUtility;
import tudresden.ocl20.pivot.modelinstancetype.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelinstancetype.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelinstancetype.types.base.ModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This factory can be used to create {@link IModelInstanceElement} for given
 * {@link EObject}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class EcoreModelInstanceFactory extends BasisJavaModelInstanceFactory
		implements IModelInstanceFactory {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = EcoreModelInstanceTypePlugin
			.getLogger(EcoreModelInstanceFactory.class);

	/**
	 * The already adapted {@link IModelInstanceElement}s of this
	 * {@link EcoreModelInstanceFactory}. <strong>This is a {@link WeakHashMap}!
	 * If an {@link EObject} is disposed, its adapted
	 * {@link IModelInstanceElement} will be disposed as well.</p>
	 */
	private Map<EObject, IModelInstanceElement> myCachedAdaptedObjects = new WeakHashMap<EObject, IModelInstanceElement>();

	/** The IModel for that the {@link IModelInstanceElement}s will be created. */
	private IModel myModel;

	/**
	 * The {@link EcoreModelInstanceTypeUtility} used to find {@link Type}s in
	 * the {@link IModel}.
	 */
	private EcoreModelInstanceTypeUtility myTypeUtility;

	/**
	 * <p>
	 * Creates a new {@link EcoreModelInstanceFactory} for a given
	 * {@link IModel}.
	 * </p>
	 */
	public EcoreModelInstanceFactory(IModel model) {

		this.myModel = model;
		this.myTypeUtility = new EcoreModelInstanceTypeUtility(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory
	 * #createModelInstanceElement(java.lang.Object)
	 */
	public IModelInstanceElement createModelInstanceElement(Object adapted)
			throws TypeNotFoundInModelException {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceElement("; //$NON-NLS-1$
			msg += "adapted = " + adapted; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceElement result;

		/*
		 * Try to delegate the basis factory to create a primitive or collection
		 * instance.
		 */
		result = super.createModelInstanceElement(adapted);

		if (result == null) {

			/* Check if the given Object is an EnumerationLiteral. */
			if (adapted instanceof Enum<?>) {

				result = this
						.createEcoreModelInstanceEnumerationLiteral((Enum<?>) adapted);
			}

			/* Else check if the given Object is an EObject. */
			else if (adapted instanceof EObject) {

				EObject eObject;
				eObject = (EObject) adapted;

				/* Check if the given Object has been adapted already. */
				if (this.myCachedAdaptedObjects.containsKey(eObject)) {
					result = this.myCachedAdaptedObjects.get(eObject);
				}

				else {
					result = this.createEcoreModelInstanceObject(eObject);

					this.myCachedAdaptedObjects.put(eObject, result);
				}
			}

			else {
				String msg;

				msg = EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_AdapteeIsNoEObjectInstance;
				msg = NLS.bind(msg, adapted);

				throw new TypeNotFoundInModelException(msg);
			}
		}
		// no else.

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceElement(Object) - exit"; //$NON-NLS-1$
			msg += " return value = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory
	 * #createModelInstanceElement(java.lang.Object,
	 * tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public IModelInstanceElement createModelInstanceElement(Object adapted,
			Type type) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceElement("; //$NON-NLS-1$
			msg += "adapted = " + adapted; //$NON-NLS-1$
			msg += ", type = " + type; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceElement result;

		/* Check if the object has already been adapted. */
		result = this.myCachedAdaptedObjects.get(adapted);

		/* Else create a new adapter. */
		if (result == null) {

			/*
			 * Try to use the BasisJavaModelInstanceFactory to probably create
			 * an adapter for a primitive type or a collection.
			 */
			result = super.createModelInstanceElement(adapted, type);

			/* Check if no primitive type or collection has been created. */
			if (result == null) {

				/* Check if the given type is an Enumeration. */
				if (type instanceof Enumeration) {

					/*
					 * If adapted == null, i.e. a PropertyCallExp returned a
					 * null value, simply try to create an undefined value.
					 */
					if (adapted == null) {
						result = BasisJavaModelInstanceFactory
								.createModelInstanceEnumerationLiteral(null);
					}

					else {
						/*
						 * Check if the object is an EnumerationLiteral and has
						 * the same type as the given type.
						 */
						if (adapted.getClass().isEnum()) {

							List<String> modelInstanceElementTypeName;
							modelInstanceElementTypeName = EcoreModelInstanceTypeUtility
									.toQualifiedNameList(adapted.getClass()
											.getCanonicalName());

							if (modelInstanceElementTypeName.size() > 0) {

								try {
									Type modelInstanceElementType;
									modelInstanceElementType = null;

									while (modelInstanceElementTypeName.size() > 0
											&& modelInstanceElementType == null) {
										modelInstanceElementType = this.myModel
												.findType(modelInstanceElementTypeName);
										modelInstanceElementTypeName.remove(0);
									}
									// end while.

									if (modelInstanceElementType != null
											&& modelInstanceElementType
													.conformsTo(type)) {
										result = this
												.createEcoreModelInstanceEnumerationLiteral((Enum<?>) adapted);
									}
									// no else.
								}

								catch (TypeNotFoundInModelException e) {
									String msg;

									msg = EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_CannotAdaptToType;
									msg = NLS.bind(msg, adapted, type);

									throw new IllegalArgumentException();
								}

								catch (ModelAccessException e) {
									String msg;

									msg = EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_CannotAdaptToType;
									msg = NLS.bind(msg, adapted, type);

									throw new IllegalArgumentException();
								}
							}
							// no else.
						}
						// no else.
					}

					/* Else throw an exception. */
					if (result == null) {
						String msg;

						msg = EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_CannotAdaptToType;
						msg = NLS.bind(msg, adapted, type);

						throw new IllegalArgumentException(msg);
					}
					// no else.
				}

				/* Else adapt to an IModelInstanceObject. */
				else {

					if (adapted instanceof EObject) {

						EObject eObject;
						eObject = (EObject) adapted;

						/*
						 * Try to find the type of the adaptable EObject (really
						 * necessary! Could be a subtype of the given type).
						 */
						try {
							Type modelInstanceElementType;
							modelInstanceElementType = this.myTypeUtility
									.findTypeOfEObjectInModel(eObject);

							/*
							 * Only adapt if the found type conforms to the
							 * given type.
							 */
							if (modelInstanceElementType.conformsTo(type)) {
								result = this
										.createEcoreModelInstanceObject(
												eObject, type,
												modelInstanceElementType);

								/* Cache the adapted object. */
								this.myCachedAdaptedObjects
										.put(eObject, result);
							}

							/* Else throw an exception. */
							else {
								String msg;

								msg = EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_CannotAdaptToType;
								msg = NLS.bind(msg, adapted, type);

								throw new IllegalArgumentException(msg);
							}
						}
						// end try.

						catch (TypeNotFoundInModelException e) {
							String msg;

							msg = EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_CannotAdaptToType;
							msg = NLS.bind(msg, adapted, type);

							throw new IllegalArgumentException(msg);
						}
						// end catch.
					}

					/* Else adapted is either 'null' or an exception is thrown */
					else {
						if (adapted == null) {
							result = new EcoreModelInstanceObject(null, type,
									type, this);
						}

						/* Else the throw an exception. */
						else {
							String msg;

							msg = EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_CannotAdaptToType;
							msg = NLS.bind(msg, adapted, type);

							throw new IllegalArgumentException(msg);
						}
					}
				}
				// end else.
			}
			// no else.
		}
		// no else.

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceElement(Object) - exit"; //$NON-NLS-1$
			msg += " - rseult = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that creates a new {@link EcoreModelInstanceObject} for a
	 * given {@link EObject}.
	 * </p>
	 * 
	 * @param eObject
	 *            The {@link EObject} that shall be adapted.
	 * @return The adapted {@link EcoreModelInstanceObject}.
	 * @throws TypeNotFoundInModelException
	 *             Thrown, if the given {@link EObject} cannot be adapted to any
	 *             {@link Type} of the {@link IModel} of this
	 *             {@link EcoreModelInstanceFactory}.
	 */
	private IModelInstanceElement createEcoreModelInstanceObject(EObject eObject)
			throws TypeNotFoundInModelException {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEcoreModelInstanceObject("; //$NON-NLS-1$
			msg += "eObject = " + eObject; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceElement result;

		Type type;
		type = this.myTypeUtility.findTypeOfEObjectInModel(eObject);

		result = new EcoreModelInstanceObject(eObject, type, type, this);

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEcoreModelInstanceObject(EObject) - exit"; //$NON-NLS-1$
			msg += " return value = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that creates a new {@link EcoreModelInstanceObject} for a
	 * given {@link EObject} and a given {@link Type}.
	 * </p>
	 * 
	 * @param eObject
	 *            The {@link EObject} that shall be adapted.
	 * @param type
	 *            The {@link Type} to that the {@link EObject} shall be adapted.
	 * @param originalType
	 *            The original {@link Type} to that the {@link EObject} shall be
	 *            adapted.
	 * @return The adapted {@link EcoreModelInstanceObject}.
	 */
	private IModelInstanceElement createEcoreModelInstanceObject(
			EObject eObject, Type type, Type originalType) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEcoreModelInstanceObject("; //$NON-NLS-1$
			msg += "eObject = " + eObject; //$NON-NLS-1$
			msg += ", type = " + type; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceElement result;

		result = new EcoreModelInstanceObject(eObject, type, originalType, this);

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEcoreModelInstanceObject(EObject) - exit"; //$NON-NLS-1$
			msg += " return value = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a new {@link ModelInstanceEnumerationLiteral} for a given
	 * {@link Enum}.
	 * </p>
	 * 
	 * @param anEnum
	 *            The {@link Enum} that shall be adapted.
	 * @return The created {@link ModelInstanceEnumerationLiteral}.
	 * @throws TypeNotFoundInModelException
	 *             Thrown, if the given {@link Enum} cannot be adapted to the
	 *             {@link IModel} of this {@link EcoreModelInstanceFactory}.
	 */
	private IModelInstanceElement createEcoreModelInstanceEnumerationLiteral(
			Enum<?> anEnum) throws TypeNotFoundInModelException {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEcoreModelInstanceEnumerationLiteral("; //$NON-NLS-1$
			msg += "anEnum = " + anEnum; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceEnumerationLiteral result;
		Type type;

		result = null;

		/* Try to find the enumeration of the enum in the model. */
		type = this.myTypeUtility.findTypeOfClassInModel(anEnum.getClass());

		/* Check if the enumeration has been found in the model. */
		if (type != null && type instanceof Enumeration) {

			Enumeration enumeration;
			enumeration = (Enumeration) type;

			/* Try to find a literal with the right value. */
			for (EnumerationLiteral aLiteral : enumeration.getOwnedLiteral()) {

				if (aLiteral.getName().equalsIgnoreCase(anEnum.toString())) {

					result = createModelInstanceEnumerationLiteral(aLiteral);
					break;
				}
				// no else.
			}
			// end for.

			if (result == null) {
				String msg;

				msg = EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_TypeNotFoundInModel;
				msg = NLS.bind(msg, anEnum);

				throw new TypeNotFoundInModelException(msg);
			}
		}

		else {
			String msg;

			msg = EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_TypeNotFoundInModel;
			msg = NLS.bind(msg, anEnum);

			throw new TypeNotFoundInModelException(msg);
		}

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEcoreModelInstanceEnumerationLiteral(Enum<?>) - exit"; //$NON-NLS-1$
			msg += " return value = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

}