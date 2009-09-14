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
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.modelinstancetype.ecore.EcoreModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.msg.EcoreModelInstanceTypeMessages;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.util.EcoreModelInstanceTypeUtilitiy;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This factory can be used to create {@link IModelInstanceElement} for given
 * {@link EObject}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class EcoreModelInstanceFactory implements IModelInstanceFactory {

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

		/* Check if the given Object is an EObject. */
		if (adapted instanceof EObject) {

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

		/* FIXME Claas: Else Check if the EObject is a collection. */
		// else if (this.isCollectionClass(eClass)) {
		// result = this.createEcoreModelInstanceCollection(eObject);
		// }
		//
		// /* Else check if the EObject is an EnumerationLiteral. */
		// else if (this.isEnumerationClass(eClass)) {
		// result = this.createEcoreModelInstanceEnumerationLiteral(eObject);
		// }
		//
		// /* Else check if the EObject is a primitive type's implementation. */
		// else if (eClass instanceof EDataType) {
		// result = this.createEcoreModelInstancePrimitiveType(eObject);
		// }
		/* Else throw an exception (only EObjects can be adapted). */
		else {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_AdapteeIsNoEObjectInstance;
			msg = NLS.bind(msg, adapted);

			throw new TypeNotFoundInModelException(msg);
		}

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

		result = new EcoreModelInstanceObject(eObject, types);

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
	 * A helper method that tries to find the direct {@link Type} in the
	 * {@link IModel} that corresponds to a given {@link EClass}.
	 * </p>
	 * 
	 * @param eClass
	 *          The {@link EClass} for that a {@link Type} shall be found.
	 * @return A found {@link Type}.
	 * @throw {@link TypeNotFoundInModelException} Thrown, if the given
	 *        {@link EClass} cannot be associated directly to a {@link Type} of
	 *        the {@link IModel} of this {@link EcoreModelInstanceFactory}.
	 */
	private Type findTypeOfEClassInModel(EClass eClass)
			throws TypeNotFoundInModelException {

		Type result;

		List<String> qualifiedName =
				EcoreModelInstanceTypeUtilitiy.toQualifiedNameList(eClass);

		try {
			result = this.myModel.findType(qualifiedName);
		}

		catch (ModelAccessException e) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_TypeNotFoundInModel;
			msg = NLS.bind(msg, eClass);

			throw new TypeNotFoundInModelException(msg, e);
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
		EClass eClass;

		result = new HashSet<Type>();

		/* Get the EObject's EClass. */
		eClass = eObject.eClass();

		/* Try to find all implemented Types of the EClass in the IModel. */
		result = this.findTypesOfEClassInModel(eClass);

		return result;
	}

	/**
	 * <p>
	 * A helper method that tries to find all {@link Type}s in the {@link IModel}
	 * that corresponds to a given {@link EClass} or one of its super
	 * {@link EClass}es. If a sub {@link Type} of a {@link Type} is also
	 * implemented, these redundant super {@link Type} will not be part of the
	 * result.
	 * </p>
	 * 
	 * @param eClass
	 *          The {@link EClass} for that {@link Type}s shall be found.
	 * @return A {@link Set} of found {@link Type}s.
	 * @throw {@link TypeNotFoundInModelException} Thrown, if the given
	 *        {@link EClass} cannot be associated to any {@link Type} of the
	 *        {@link IModel} of this {@link EcoreModelInstanceFactory}.
	 */
	private Set<Type> findTypesOfEClassInModel(EClass eClass)
			throws TypeNotFoundInModelException {

		Set<Type> result;
		result = new HashSet<Type>();

		/* Try to find a direct implemented Type of the EClass in the IModel. */
		try {
			result.add(this.findTypeOfEClassInModel(eClass));
		}

		/* Else try to find a implemented super type in the IModel. */
		catch (TypeNotFoundInModelException e1) {

			/* Iterate through all super classes. */
			for (EClass aSuperClass : eClass.getESuperTypes()) {
				try {
					result.addAll(this.findTypesOfEClassInModel(aSuperClass));
				}

				catch (TypeNotFoundInModelException e2) {
					/* Do nothing. */
				}
			}
			// end for.

			/* Probably remove redundant types from the result. */
			result = this.removeRedundantSuperTypes(result);
		}
		// end catch.

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
	private Set<Type> removeRedundantSuperTypes(Set<Type> types) {

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

	/** FIXME Claas: REFACTORED_TILL_HERE. */
	private static final int REFACTORED_TILL_HERE = 0;

	private IModelInstanceElement createEcoreModelInstanceCollection(
			EObject object) {

		// TODO Auto-generated method stub
		return null;
	}

	private IModelInstanceElement createEcoreModelInstancePrimitiveType(
			EObject object) {

		// TODO Auto-generated method stub
		return null;
	}

	private IModelInstanceElement createEcoreModelInstanceEnumerationLiteral(
			EObject object) {

		// TODO Auto-generated method stub
		return null;
	}

	private boolean isEnumerationClass(EClass class1) {

		// TODO Auto-generated method stub
		return false;
	}

	private boolean isPrimitiveTypeClass(EClass class1) {

		// TODO Auto-generated method stub
		return false;
	}

	private boolean isCollectionClass(EClass class1) {

		// TODO Auto-generated method stub
		return false;
	}

	public <T extends IModelInstanceElement> IModelInstanceCollection<T> createModelInstanceCollection(
			Collection<T> collection, OclCollectionTypeKind kind) {

		// TODO Auto-generated method stub
		return null;
	}

	public IModelInstanceElement createModelInstanceElement(Object adapted,
			Type type) {

		// TODO Auto-generated method stub
		return null;
	}
}