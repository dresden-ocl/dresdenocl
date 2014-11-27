/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.util;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EClass;

/**
 * A utility class that provides methods to handle EClasses.
 */
public class OclEClassUtil {
	
	public boolean isSubClass(EClass subClassCandidate, EClass superClass) {
		for (EClass superClassCandidate : subClassCandidate.getEAllSuperTypes()) {
			// There seem to be multiple instances of meta classes when accessed through the
			// generator model. Therefore, we compare by name.
			if (namesAndPackageURIsAreEqual(superClassCandidate, superClass)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <p>
	 * Returns all subclasses of 'superClass' that are contained in 'availableClasses'.
	 * </p>
	 * 
	 * @param superClass the superclass
	 * @param availableClasses the set of classes to search in
	 * 
	 * @return a list of all subclasses of 'superClass'
	 */
	public List<EClass> getSubClasses(EClass superClass, EClass[] availableClasses) {
		
		List<EClass> result = new ArrayList<EClass>();
		for (EClass next : availableClasses) {
			if (isSubClass(next, superClass) &&			isConcrete(next)) {
				result.add(next);
			}
		}
		return result;
	}
	
	public boolean namesAndPackageURIsAreEqual(EClass classA,
	EClass classB) {
		return namesAreEqual(classA, classB) &&		packageURIsAreEqual(classA, classB);
	}
	
	public boolean packageURIsAreEqual(EClass classA,
	EClass classB) {
		String nsURI_A = classA.getEPackage().getNsURI();
		String nsURI_B = classB.getEPackage().getNsURI();
		if (nsURI_A == null && nsURI_B == null) {
			return true;
		}
		if (nsURI_A != null) {
			return nsURI_A.equals(nsURI_B);
		} else {
			return false;
		}
	}
	
	public boolean namesAreEqual(EClass classA, EClass classB) {
		return classA.getName().equals(classB.getName());
	}
	
	public boolean isConcrete(EClass eClass) {
		return !eClass.isAbstract() && !eClass.isInterface();
	}
	
	public boolean isNotConcrete(EClass eClass) {
		return !isConcrete(eClass);
	}
	
	/**
	 * Returns true if the given object is an instance of one of the EClasses.
	 */
	public boolean isInstance(Object object, EClass[] allowedTypes) {
		for (EClass allowedType : allowedTypes) {
			if (allowedType.isInstance(object)) {
				return true;
			}
		}
		return false;
	}
	
}
