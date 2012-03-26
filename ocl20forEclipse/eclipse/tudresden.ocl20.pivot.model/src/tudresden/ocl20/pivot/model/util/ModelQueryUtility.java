package tudresden.ocl20.pivot.model.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This Class provides some static methods for querying 
 * model elements and constraints which are defined on a given model.
 * </p>
 * @author Ronny Marx
 *
 */
public class ModelQueryUtility {

	/**
	 * Dont let anyone instantiate this class.
	 */
	private ModelQueryUtility(){};
	
	/**
	 * <p>
	 * Returns all {@link Namespace}s, which are defined in the
	 * given {@link IModel}.
	 * </p>
	 * @param model The {@link IModel} where to get all defined {@link Namespace}s from.
	 * @return A {@link List} of {@link Namespace}s defined in the given model.
	 * @throws ModelAccessException
	 */
	public static List<Namespace> getAllNamespaces(IModel model)
			throws ModelAccessException {

		List<Namespace> result;

		result = getNestedNamespaces(model.getRootNamespace());
		result.add(model.getRootNamespace());
		
		return result;
	}
	
	/**
	 * <p>
	 * Returns all {@link Type}s, which are defined in the
	 * given {@link IModel}.
	 * </p>
	 * @param model The {@link IModel} where to get all defined {@link Type}s from.
	 * @return A {@link Collection} of {@link Type}s defined in the given model.
	 * @throws ModelAccessException
	 */
	public static Collection<Type> getAllTypes(IModel model)
			throws ModelAccessException {

		Collection<Type> result = new LinkedList<Type>();
		
		for(Namespace namespace : getAllNamespaces(model)){
			result.addAll(namespace.getOwnedType());
		}
		

		return result;
	}
	
	/**
	 * <p>
	 * Returns all {@link Constraint}s defined on a given {@link IModel}
	 * </p> 
	 * @param model The {@link IModel} on that the {@link Constraint}s are defined. 
	 * @return A {@link Collection} of {@link Constraint}s defined on the given model.
	 * @throws ModelAccessException
	 */
	public static Collection<Constraint> getAllConstraints(IModel model)
			throws ModelAccessException {

		return model.getConstraints();
	}
	
	private static List<Namespace> getNestedNamespaces(Namespace namespace) {

		List<Namespace> nestedNamespaces = namespace.getNestedNamespace();
		List<Namespace> result = null;

		for (Namespace nestedNamespace : nestedNamespaces) {
			if (result == null) {
				result = getNestedNamespaces(nestedNamespace);
			}
			else {
				result.addAll(getNestedNamespaces(nestedNamespace));
			}
		}

		if (result == null) {
			result = nestedNamespaces;
		}
		else {
			result.addAll(nestedNamespaces);
		}

		return result;
	}
	
	/**
	 * <p>
	 * Returns the {@link Constraint}s on a {@link ConstrainableElement} which is
	 * defined in a given {@link IModel}
	 * </p>
	 * @param model The {@link IModel} where the {@link Constraints} and {@link ConstrainableElement}s are defined.
	 * @param constrainableElement The {@link ConstrainableElement} whose {@link Constraint}s shall be retrieved
	 * @return A {@link Collection} of {@link Constraint}s defined on  
	 * @throws ModelAccessException
	 */
	public static Collection<Constraint> getConstraints(IModel model, ConstrainableElement constrainableElement) throws ModelAccessException{
		Collection<Constraint> result = new LinkedList<Constraint>();
		
		for(Constraint constraint : getAllConstraints(model)){
			for(ConstrainableElement element : constraint.getConstrainedElement()){
				if(element.equals(constrainableElement)){
					result.add(constraint);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * <p>
	 * Retrieves all {@link ConstrainableElement}s from an {@link IModel} where a {@link Constraint}
	 * has been defined on
	 * </p>
	 * @param model The {@link IModel} to be searched for constrained elements.
	 * @return A {@link Collection} of {@link ConstrainableElement}s where {@link Constraint}s have been defined on.
	 * @throws ModelAccessException
	 */
	public static Collection<ConstrainableElement> getAllConstrainedElements(IModel model) throws ModelAccessException{
		Collection<ConstrainableElement> result = new HashSet<ConstrainableElement>();
		
		for (Constraint constraint : getAllConstraints(model)) {
			result.addAll(constraint.getConstrainedElement());
		}
			
		return result;
	}
	
}
