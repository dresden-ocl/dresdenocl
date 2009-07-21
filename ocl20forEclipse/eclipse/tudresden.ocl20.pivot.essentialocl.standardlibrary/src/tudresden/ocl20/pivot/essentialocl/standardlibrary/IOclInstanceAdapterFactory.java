package tudresden.ocl20.pivot.essentialocl.standardlibrary;

import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceString;

/**
 * Provides methods to adapt {@link IModelObject}s to instances of the OCL
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
	 * FIXME[Michael]: at the moment just throw in Java {@link Object}s; later
	 * delegate the work to the {@link IModelObject}.
	 * 
	 * @param modelInstanceObject
	 *          the adapter for a runtime-object that is an element of the model
	 *          and cannot be mapped to a type of the standard library
	 * 
	 * @return an {@link OclObject}
	 */
	public OclObject createOclObject(IModelObject modelInstanceObject);

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
	 * Creates an element that implements {@link OclRoot}. It does some
	 * type-checks to determine the type of the {@link IModelObject}. If no
	 * particular type is found, the {@link IModelObject} is adapted to
	 * {@link OclObject}.
	 * 
	 * @param modelInstanceObject
	 *          the adapter for a runtime-object whose type is not known in
	 *          advance
	 * 
	 * @return an {@link OclRoot}
	 */
	public OclRoot createOclRoot(IModelObject modelInstanceObject);

}
