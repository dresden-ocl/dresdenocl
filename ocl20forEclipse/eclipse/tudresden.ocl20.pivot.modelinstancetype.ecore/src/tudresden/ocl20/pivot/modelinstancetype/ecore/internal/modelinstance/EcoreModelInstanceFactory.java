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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.ModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelinstancetype.ecore.EcoreModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.msg.EcoreModelInstanceTypeMessages;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.util.EcoreModelInstanceTypeUtility;
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
	private static final Logger LOGGER =
			EcoreModelInstanceTypePlugin.getLogger(EcoreModelInstanceFactory.class);

	/**
	 * The already adapted {@link IModelInstanceElement}s of this
	 * {@link EcoreModelInstanceFactory}. <strong>This is a {@link WeakHashMap}!
	 * If an {@link EObject} is disposed, its adapted
	 * {@link IModelInstanceElement} will be disposed as well.</p>
	 */
	private Map<EObject, IModelInstanceElement> myCachedAdaptedObjects =
			new WeakHashMap<EObject, IModelInstanceElement>();

	/** The IModel for that the {@link IModelInstanceElement}s will be created. */
	private IModel myModel;

	/**
	 * <p>
	 * Creates a new {@link EcoreModelInstanceFactory} for a given {@link IModel}.
	 * </p>
	 */
	public EcoreModelInstanceFactory(IModel model) {

		this.myModel = model;
	}

	/*
	 * (non-Javadoc)
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

				result =
						this.createEcoreModelInstanceEnumerationLiteral((Enum<?>) adapted);
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

				msg =
						EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_AdapteeIsNoEObjectInstance;
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
			 * Try to use the BasisJavaModelInstanceFactory to probably create an
			 * adapter for a primitive type or a collection.
			 */
			result = super.createModelInstanceElement(adapted, type);

			/* Check if no primitive type or collection has been created. */
			if (result == null) {

				/* Check if the given type is an Enumeration. */
				if (type instanceof Enumeration) {

					/*
					 * If adapted == null, i.e. a PropertyCallExp returned a null value,
					 * simply try to create an undefined value.
					 */
					if (adapted == null) {
						result =
								BasisJavaModelInstanceFactory
										.createModelInstanceEnumerationLiteral(null);
					}

					else {
						/*
						 * Check if the object is an EnumerationLiteral and has the same
						 * type as the given type.
						 */
						if (adapted.getClass().isEnum()

								&& EcoreModelInstanceTypeUtility.toQualifiedNameList(
										adapted.getClass().getCanonicalName()).equals(
										type.getQualifiedNameList())) {
							try {
								result =
										this
												.createEcoreModelInstanceEnumerationLiteral((Enum<?>) adapted);
							}

							catch (TypeNotFoundInModelException e) {
								String msg;

								msg =
										EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_CannotAdaptToType;
								msg = NLS.bind(msg, adapted, type);

								throw new IllegalArgumentException();
							}
						}
					}

					/* Else throw an exception. */
					if (result == null) {
						String msg;

						msg =
								EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_CannotAdaptToType;
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

						result = this.createEcoreModelInstanceObject(eObject, type);

						/* Cache the adapted object. */
						this.myCachedAdaptedObjects.put(eObject, result);
					}

					/* Else adapted is either 'null' or an exception is thrown */
					else {

						if (adapted == null) {
							Set<Type> types = new HashSet<Type>();
							types.add(type);
							result = new EcoreModelInstanceObject(null, types, this);
						}

						/* Else the throw an exception. */
						else {
							String msg;

							msg =
									EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_CannotAdaptToType;
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
	 *          The {@link EObject} that shall be adapted.
	 * @return The adapted {@link EcoreModelInstanceObject}.
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if the given {@link EObject} cannot be adapted to any
	 *           {@link Type} of the {@link IModel} of this
	 *           {@link EcoreModelInstanceFactory}.
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

		Set<Type> types;
		types = this.findTypesOfEObjectInModel(eObject);

		result = new EcoreModelInstanceObject(eObject, types, this);

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
	 *          The {@link EObject} that shall be adapted.
	 * @param The
	 *          {@link Type} to that the {@link EObject} shall be adapted.
	 * @return The adapted {@link EcoreModelInstanceObject}.
	 */
	private IModelInstanceElement createEcoreModelInstanceObject(EObject eObject,
			Type type) {

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

		Set<Type> types;
		types = new HashSet<Type>();

		/* Try to find the types of the given object in the model. */
		try {

			/* Search for a type that conforms to the given type. */
			for (Type aModelType : this.findTypesOfEObjectInModel(eObject)) {

				if (aModelType.conformsTo(type)) {
					types.add(aModelType);
					break;
				}
				// no else.
			}
		}

		catch (TypeNotFoundInModelException e1) {
			types.add(type);
		}

		result = new EcoreModelInstanceObject(eObject, types, this);

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
	 *          The {@link Enum} that shall be adapted.
	 * @return The created {@link ModelInstanceEnumerationLiteral}.
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if the given {@link Enum} cannot be adapted to the
	 *           {@link IModel} of this {@link EcoreModelInstanceFactory}.
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
		type = this.findTypeOfClassInModel(anEnum.getClass());

		/* Check if the enumeration has been found in the model. */
		if (type != null && type instanceof Enumeration) {

			Enumeration enumeration;
			enumeration = (Enumeration) type;

			/* Try to find a literal with the right value. */
			for (EnumerationLiteral aLiteral : enumeration.getOwnedLiteral()) {

				if (aLiteral.getName().equals(anEnum.toString())) {

					result = createModelInstanceEnumerationLiteral(aLiteral);
					break;
				}
				// no else.
			}
			// end for.

			if (result == null) {
				String msg;

				msg =
						EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_TypeNotFoundInModel;
				msg = NLS.bind(msg, anEnum);

				throw new TypeNotFoundInModelException(msg);
			}
		}

		else {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_TypeNotFoundInModel;
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

	/**
	 * <p>
	 * A helper method that returns the {@link Type} in a given {@link IModel}
	 * that correspond to a given {@link Class}.
	 * </p>
	 * 
	 * @param aClass
	 *          The {@link Class} for that the {@link Type} shall be returned.
	 * @return The found {@link Type}.
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if a given {@link Object} cannot be adapted to a
	 *           {@link Type} in the {@link IModel}.
	 */
	private Type findTypeOfClassInModel(Class<?> aClass)
			throws TypeNotFoundInModelException {

		Type result;

		try {
			List<String> typePath;
			typePath =
					EcoreModelInstanceTypeUtility.toQualifiedNameList(aClass
							.getCanonicalName());

			result = null;

			/*
			 * The problem with Ecore models is that Ecore models do not contain the
			 * complete package hierarchy of the implementation class. Thus, remove
			 * package per package from the path and search for a type again.
			 */
			while (result == null && typePath.size() >= 2) {
				result = this.myModel.findType(typePath);

				typePath.remove(0);
			}
			// end while.
		}

		catch (ModelAccessException e) {
			result = null;
		}

		/* Probably throw an exception. */
		if (result == null) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_TypeNotFoundInModel;
			msg = NLS.bind(msg, aClass);

			throw new TypeNotFoundInModelException(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that tries to find a {@link Type} in the {@link IModel}
	 * that corresponds to the given class.
	 * </p>
	 * 
	 * <p>
	 * Probably a {@link Class} could be related to different {@link Type}s in the
	 * {@link IModel} that do not know each other. E.g., multiple interface
	 * inheritance. In these cases, the result will be a {@link Set} containing
	 * all {@link Type}s, the {@link Class} could be related to.
	 * </p>
	 * 
	 * <p>
	 * The search strategy will work as follows:
	 * </p>
	 * <ul>
	 * <li>If the {@link Class} itself is represented by a {@link Type} in the
	 * {@link IModel}, only this {@link Type} will be returned.</li>
	 * <li>Else the method will collect all {@link Type}s that are related to
	 * implemented interfaces of the {@link Class} and probably also the
	 * {@link Type} of its super {@link Class}.</li>
	 * </ul>
	 * 
	 * @param aClass
	 *          The {@link Class} for that the {@link Type}s shall be returned.
	 * @return The found {@link Type}s as an array.
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if a given {@link Object} cannot be adapted to a
	 *           {@link Type} in the {@link IModel}.
	 */
	private Set<Type> findTypesOfClassInModel(Class<?> clazz)
			throws TypeNotFoundInModelException {

		Set<Type> result;

		result = new HashSet<Type>();

		/* Check that the given class is not null. */
		if (clazz != null) {

			/* Try to find the type corresponding to the class itself. */
			try {
				result.add(this.findTypeOfClassInModel(clazz));
			}

			/* Else search for the interfaces in the model and for the super type. */
			catch (TypeNotFoundInModelException e) {

				/* Add the types of the implemented interfaces. */
				for (Class<?> anInterface : clazz.getInterfaces()) {
					try {
						result.addAll(findTypesOfClassInModel(anInterface));
					}

					catch (TypeNotFoundInModelException e2) {
						/* Continue probably the class will implement a type. */
					}
				}

				/* Add recursively found types for the super class. */
				try {
					result.addAll(findTypesOfClassInModel(clazz.getSuperclass()));
				}

				catch (TypeNotFoundInModelException e2) {
					/* Continue probably one of the interfaces will implement a type. */
				}

				/* Remove types, that are already represented by sub types in the model. */
				result = this.removeRedundantModelTypes(result);
			}
			// end else.
		}

		/* Check if any implemented type has been found. Else throw an exception. */
		if (result.size() == 0) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_TypeNotFoundInModel;
			msg = NLS.bind(msg, clazz);

			throw new TypeNotFoundInModelException(msg);
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method that tries to find at least one {@link Type} in the
	 * {@link IModel} that a given {@link EObject} implements.
	 * </p>
	 * 
	 * @param eObject
	 *          The {@link EObject} for that {@link Type}s shall be found.
	 * @return A {@link Set} of found {@link Type}s.
	 * @throw {@link TypeNotFoundInModelException} Thrown, if the given
	 *        {@link EObject} cannot be adapted to any {@link Type} of the
	 *        {@link IModel} of this {@link EcoreModelInstanceFactory}.
	 */
	private Set<Type> findTypesOfEObjectInModel(EObject eObject)
			throws TypeNotFoundInModelException {

		Set<Type> result;

		Class<?> objectClass;
		objectClass = eObject.getClass();

		result = findTypesOfClassInModel(objectClass);

		return result;
	}

	/**
	 * <p>
	 * A helper method that removes {@link Type}s from a given {@link Set} that
	 * are transitively described by other {@link Type}s of the {@link Set}
	 * because they are super {@link Type}s.
	 * </p>
	 * 
	 * @param types
	 *          The {@link Set} from which super {@link Type}s shall be removed.
	 * @return The {@link Set} without redundant super {@link Type}s.
	 */
	private Set<Type> removeRedundantModelTypes(Set<Type> types) {

		List<Type> typeList;
		Set<Type> result;

		typeList = new ArrayList<Type>(types);
		result = new HashSet<Type>();

		for (int index1 = 0; index1 < typeList.size(); index1++) {

			Type type1;
			boolean isRedundant;

			type1 = typeList.get(index1);
			isRedundant = false;

			/* Check if any other type is a sub type of type 1. */
			for (int index2 = 0; index2 < typeList.size(); index2++) {

				Type type2;
				type2 = typeList.get(index2);

				if (index1 != index2 && type2.conformsTo(type1)) {
					isRedundant = true;
					break;
				}
				// no else.
			}

			if (!isRedundant) {
				result.add(type1);
			}
			// no else.
		}

		return result;
	}
}