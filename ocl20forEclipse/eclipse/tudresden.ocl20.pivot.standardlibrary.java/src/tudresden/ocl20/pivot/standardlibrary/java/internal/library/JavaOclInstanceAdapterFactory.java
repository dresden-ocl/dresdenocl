/**
 * 
 */
package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceString;

/**
 * Java implementation for the {@link IOclInstanceAdapterFactory}.
 * 
 * @author Michael Thiele
 * 
 */
public class JavaOclInstanceAdapterFactory implements
		IOclInstanceAdapterFactory {

	/** The Logger for this class. */
	private static final Logger logger =
			Logger.getLogger(JavaOclInstanceAdapterFactory.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclBoolean(tudresden.ocl20.pivot.modelbus.modelinstance.
	 * IModelInstanceBoolean)
	 */
	public OclBoolean createOclBoolean(IModelInstanceBoolean modelInstanceBoolean) {

		if (logger.isDebugEnabled()) {
			logger.debug("createOclBoolean(" + modelInstanceBoolean + ") - start");
		}

		Boolean adaptedBoolean = modelInstanceBoolean.getBoolean();
		OclBoolean oclBoolean = JavaOclBoolean.getInstance(adaptedBoolean);

		if (logger.isDebugEnabled()) {
			logger.debug("createOclBoolean(" + modelInstanceBoolean
					+ ") - end - result = " + oclBoolean);
		}

		return oclBoolean;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclCollection(tudresden.ocl20.pivot.modelbus.modelinstance.
	 * IModelInstanceCollection)
	 */
	public OclCollection<OclRoot> createOclCollection(
			IModelInstanceCollection modelInstanceCollection) {

		if (logger.isDebugEnabled()) {
			logger.debug("createOclCollection(" + modelInstanceCollection
					+ ") - start");
		}

		boolean isOrdered = modelInstanceCollection.isOrdered();
		boolean isUnique = modelInstanceCollection.isUnique();

		OclCollection<OclRoot> oclCollection = null;

		Collection<OclRoot> containedElements;
		if (!isOrdered && isUnique)
			containedElements = new HashSet<OclRoot>();
		else
			containedElements = new LinkedList<OclRoot>();

		// convert contained elements to OCL instances
		for (IModelObject modelObject : modelInstanceCollection
				.getContainedElements()) {
			containedElements.add(createOclRoot(modelObject));
		}

		if (isOrdered && isUnique) {
			oclCollection =
					new JavaOclOrderedSet<OclRoot>((List<OclRoot>) containedElements);
		}
		else if (isOrdered && !isUnique) {
			oclCollection =
					new JavaOclSequence<OclRoot>((List<OclRoot>) containedElements);
		}
		else if (!isOrdered && isUnique) {
			oclCollection = new JavaOclSet<OclRoot>((Set<OclRoot>) containedElements);
		}
		else { // (!isOrdered && !isUnique)
			oclCollection =
					new JavaOclBag<OclRoot>((List<OclRoot>) containedElements);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("createOclCollection(" + modelInstanceCollection
					+ ") - end - result = " + oclCollection);
		}

		return oclCollection;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclEnumLiteral(tudresden.ocl20.pivot.modelbus.modelinstance.
	 * IModelInstanceEnumerationLiteral)
	 */
	public OclEnumLiteral createOclEnumLiteral(
			IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral) {

		if (logger.isDebugEnabled()) {
			logger.debug("createOclEnumLiteral(" + modelInstanceEnumerationLiteral
					+ ") - start");
		}

		Enum<?> adaptedEnumLiteral = modelInstanceEnumerationLiteral.getLiteral();
		OclEnumLiteral oclEnumLiteral = new JavaOclEnumLiteral(adaptedEnumLiteral);

		if (logger.isDebugEnabled()) {
			logger.debug("createOclEnumLiteral(" + modelInstanceEnumerationLiteral
					+ ") - end - result = " + oclEnumLiteral);
		}

		return oclEnumLiteral;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclInteger(tudresden.ocl20.pivot.modelbus.modelinstance.
	 * IModelInstanceInteger)
	 */
	public OclInteger createOclInteger(IModelInstanceInteger modelInstanceInteger) {

		if (logger.isDebugEnabled()) {
			logger.debug("createOclInteger(" + modelInstanceInteger + ") - start");
		}

		BigInteger adaptedInteger = modelInstanceInteger.getInteger();
		OclInteger oclInteger = new JavaOclInteger(adaptedInteger);

		if (logger.isDebugEnabled()) {
			logger.debug("createOclInteger(" + modelInstanceInteger
					+ ") - end - result = " + oclInteger);
		}

		return oclInteger;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclObject(tudresden.ocl20.pivot.modelbus.IModelObject)
	 */
	public OclObject createOclObject(IModelObject modelInstanceObject) {

		if (logger.isDebugEnabled()) {
			logger.debug("createOclObject(" + modelInstanceObject + ") - start");
		}

		// FIXME[Michael]: modelInstanceObject.getAdaptedObject()
		Object adaptedObject = modelInstanceObject;
		OclObject oclObject = new JavaOclObject(adaptedObject);

		if (logger.isDebugEnabled()) {
			logger.debug("createOclObject(" + modelInstanceObject
					+ ") - end - result = " + oclObject);
		}

		return oclObject;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #
	 * createOclReal(tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceReal
	 * )
	 */
	public OclReal createOclReal(IModelInstanceReal modelInstanceReal) {

		if (logger.isDebugEnabled()) {
			logger.debug("createOclReal(" + modelInstanceReal + ") - start");
		}

		BigDecimal adaptedReal = modelInstanceReal.getReal();
		OclReal oclReal = new JavaOclReal(adaptedReal);

		if (logger.isDebugEnabled()) {
			logger.debug("createOclReal(" + modelInstanceReal + ") - end - result = "
					+ oclReal);
		}

		return oclReal;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclString(tudresden.ocl20.pivot.modelbus.modelinstance.
	 * IModelInstanceString)
	 */
	public OclString createOclString(IModelInstanceString modelInstanceString) {

		if (logger.isDebugEnabled()) {
			logger.debug("createOclString(" + modelInstanceString + ") - start");
		}

		String adaptedString = modelInstanceString.getString();
		OclString oclString = new JavaOclString(adaptedString);

		if (logger.isDebugEnabled()) {
			logger.debug("createOclString(" + modelInstanceString
					+ ") - end - result = " + oclString);
		}

		return oclString;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclRoot(tudresden.ocl20.pivot.modelbus.IModelObject)
	 */
	public OclRoot createOclRoot(IModelObject modelInstanceObject) {

		if (logger.isDebugEnabled()) {
			logger.debug("createOclRoot(" + modelInstanceObject + ") - start");
		}

		if (modelInstanceObject instanceof IModelInstanceBoolean) {

			OclRoot result =
					createOclBoolean((IModelInstanceBoolean) modelInstanceObject);

			if (logger.isDebugEnabled()) {
				logger.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}
		else if (modelInstanceObject instanceof IModelInstanceCollection) {
			OclRoot result =
					createOclCollection((IModelInstanceCollection) modelInstanceObject);

			if (logger.isDebugEnabled()) {
				logger.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}
		else if (modelInstanceObject instanceof IModelInstanceEnumerationLiteral) {
			OclRoot result =
					createOclEnumLiteral((IModelInstanceEnumerationLiteral) modelInstanceObject);

			if (logger.isDebugEnabled()) {
				logger.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}
		else if (modelInstanceObject instanceof IModelInstanceInteger) {
			OclRoot result =
					createOclInteger((IModelInstanceInteger) modelInstanceObject);

			if (logger.isDebugEnabled()) {
				logger.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}
		else if (modelInstanceObject instanceof IModelInstanceReal) {
			OclRoot result = createOclReal((IModelInstanceReal) modelInstanceObject);

			if (logger.isDebugEnabled()) {
				logger.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}
		else if (modelInstanceObject instanceof IModelInstanceString) {
			OclRoot result =
					createOclString((IModelInstanceString) modelInstanceObject);

			if (logger.isDebugEnabled()) {
				logger.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}
		else {
			OclRoot result = createOclObject(modelInstanceObject);

			if (logger.isDebugEnabled()) {
				logger.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}

	}

}
