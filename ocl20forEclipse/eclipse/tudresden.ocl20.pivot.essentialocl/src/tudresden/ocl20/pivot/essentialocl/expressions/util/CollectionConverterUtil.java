package tudresden.ocl20.pivot.essentialocl.expressions.util;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.pivotmodel.MultiplicityElement;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypedElement;

/**
 * As the Pivot Model has {@link MultiplicityElement}s, but OCL has the 4
 * collection types for multiple elements, the Pivot Model types have to be
 * converted to OCL collection types.
 * 
 * @author Michael Thiele
 * 
 */
public class CollectionConverterUtil {

	private OclLibrary oclLibrary;

	public CollectionConverterUtil(OclLibrary oclLibrary) {
		this.oclLibrary = oclLibrary;
	}

	/**
	 * Converts a Pivot Model type into an OCL type. The Pivot Model element is a
	 * multiplicity element, so isMultiple(), isUnique() and isOrdered() have to
	 * be checked.
	 * 
	 * @param <T>
	 * @param typedElement
	 *          a Pivot Model {@link TypedElement} that is a
	 *          {@link MultiplicityElement}.
	 * @return an OCL {@link Type}
	 */
	public <T extends TypedElement & MultiplicityElement> Type convertPivotModelTypeToOclType(
			T typedElement) {
		Type result;

		if (typedElement.isMultiple()) {
			if (typedElement.isOrdered()) {
				if (typedElement.isUnique())
					result = oclLibrary.getOrderedSetType(typedElement.getType());
				else
					result = oclLibrary.getSequenceType(typedElement.getType());
			} else {
				if (typedElement.isUnique())
					result = oclLibrary.getSetType(typedElement.getType());
				else
					result = oclLibrary.getBagType(typedElement.getType());
			}
		}
		// else, the element is not multiple -> no conversion needed
		else {
			result = typedElement.getType();
		}

		return result;
	}

}
