package tudresden.ocl20.pivot.essentialocl.standardlibrary.factory;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTypeObject;

/**
 * Provides methods to adapt {@link IModelInstanceElement}s to instances of the OCL
 * standard library (called OCL instances).
 * 
 * @author Michael Thiele
 * 
 */
public interface IOclInstanceAdapterFactory {

	/**
	 * Creates an {@link OclBoolean}.
	 * 
	 * @param modelInstanceBoolean
	 *          the adapter for a runtime-object that is a boolean
	 * 
	 * @return an {@link OclBoolean}
	 */
	public OclBoolean createOclBoolean(IModelInstanceBoolean modelInstanceBoolean);

	/**
	 * <p>
	 * Creates an empty {@link OclCollection} (i.e., an {@link OclBag},
	 * {@link OclSet}, {@link OclSequence} or {@link OclOrderedSet}) depending on
	 * the attributes {@link IModelInstanceCollection#isSorted()} and
	 * {@link IModelInstanceCollection#isUnique()}.
	 * </p>
	 * 
	 * @param modelInstanceCollection
	 *          the adapter for a runtime-object that is a collection or array of
	 *          any kind
	 * 
	 * @return an empty {@link OclCollection}
	 */
	public OclCollection<OclRoot> createOclCollection(
			IModelInstanceCollection modelInstanceCollection);

	/**
	 * Creates an {@link OclEnumLiteral}.
	 * 
	 * @param modelInstanceEnumerationLiteral
	 *          the adapter for a runtime-object that is an enumeration literal
	 * 
	 * @return an {@link OclEnumLiteral}
	 */
	public OclEnumLiteral createOclEnumLiteral(
			IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral);

	/**
	 * Creates an {@link OclInteger}.
	 * 
	 * @param modelInstanceInteger
	 *          the adapter for a runtime-object that is an integer
	 * 
	 * @return an {@link OclInteger}
	 */
	public OclInteger createOclInteger(IModelInstanceInteger modelInstanceInteger);

	/**
	 * Creates an {@link OclObject}.
	 * 
	 * @param modelInstanceObject
	 *          the adapter for a runtime-object that is an element of the model
	 *          and cannot be mapped to a type of the standard library
	 * 
	 * @return an {@link OclObject}
	 */
	public OclObject createOclObject(IModelInstanceObject modelInstanceObject);

	/**
	 * Creates an {@link OclReal}.
	 * 
	 * @param modelInstanceReal
	 *          the adpater for a runtime-object that is a real (float, double,
	 *          ...)
	 * 
	 * @return an {@link OclReal}
	 */
	public OclReal createOclReal(IModelInstanceReal modelInstanceReal);

	/**
	 * Creates an {@link OclString}.
	 * 
	 * @param modelInstanceString
	 *          the adpater for a runtime-object that is a string
	 * 
	 * @return an {@link OclString}
	 */
	public OclString createOclString(IModelInstanceString modelInstanceString);

	/**
	 * <p>
	 * Creates an {@link OclType} for a given {@link IModelInstanceTypeObject}.
	 * </p>
	 * 
	 * @param modelInstanceTypeObject
	 *          The {@link IModelInstanceTypeObject} that shall be adapted.
	 * @return The created {@link OclType}.
	 */
	public OclType createOclType(IModelInstanceTypeObject modelInstanceTypeObject);

	/**
	 * Creates an element that implements {@link OclRoot}. It does some
	 * type-checks to determine the type of the {@link IModelInstanceElement}. If no
	 * particular type is found, the {@link IModelInstanceElement} is adapted to
	 * {@link OclObject}.
	 * 
	 * @param modelInstanceObject
	 *          the adapter for a runtime-object whose type is not known in
	 *          advance
	 * 
	 * @return an {@link OclRoot}
	 */
	public OclRoot createOclRoot(IModelInstanceElement modelInstanceObject);

}
