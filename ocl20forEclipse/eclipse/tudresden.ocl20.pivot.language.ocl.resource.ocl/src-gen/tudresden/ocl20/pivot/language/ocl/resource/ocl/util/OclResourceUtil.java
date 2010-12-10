/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.util;

/**
 * Class ResourceUtil can be used to perform common tasks on resources, such as
 * resolving proxy object, saving resources, as well as, checking them for errors.
 */
public class OclResourceUtil {
	
	/**
	 * Searches for all unresolved proxy object in the given resource.
	 * 
	 * @param resource
	 * 
	 * @return all proxy object that are not resolvable
	 */
	public static java.util.List<org.eclipse.emf.ecore.EObject> findUnresolvedProxies(org.eclipse.emf.ecore.resource.Resource resource) {
		java.util.List<org.eclipse.emf.ecore.EObject> unresolvedProxies = new java.util.ArrayList<org.eclipse.emf.ecore.EObject>();
		
		for (java.util.Iterator<org.eclipse.emf.ecore.EObject> elementIt = org.eclipse.emf.ecore.util.EcoreUtil.getAllContents(resource, true); elementIt.hasNext(); ) {
			org.eclipse.emf.ecore.InternalEObject nextElement = (org.eclipse.emf.ecore.InternalEObject) elementIt.next();
			if (nextElement.eIsProxy()) {
				unresolvedProxies.add(nextElement);
			}
			for (org.eclipse.emf.ecore.EObject crElement : nextElement.eCrossReferences()) {
				crElement = org.eclipse.emf.ecore.util.EcoreUtil.resolve(crElement, resource);
				if (crElement.eIsProxy()) {
					unresolvedProxies.add(crElement);
				}
			}
		}
		return unresolvedProxies;
	}
	
	/**
	 * Tries to resolve all unresolved proxy objects in the given resource. If all
	 * proxies were resolved true is returned. If some could not be resolved, false is
	 * returned.
	 * 
	 * @param resource the resource containing the proxy object
	 * 
	 * @return true on success
	 */
	public static boolean resolveAll(org.eclipse.emf.ecore.resource.Resource resource) {
		org.eclipse.emf.ecore.util.EcoreUtil.resolveAll(resource);
		if (findUnresolvedProxies(resource).size() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void saveResource(java.io.File file, org.eclipse.emf.ecore.resource.Resource resource) throws java.io.IOException {
		java.util.Map<?, ?> options = java.util.Collections.EMPTY_MAP;
		java.io.OutputStream outputStream = new java.io.FileOutputStream(file);
		resource.save(outputStream, options);
		outputStream.close();
	}
	
	public static boolean containsErrors(org.eclipse.emf.ecore.resource.Resource resource) {
		return !resource.getErrors().isEmpty();
	}
	
	public static boolean containsWarnings(org.eclipse.emf.ecore.resource.Resource resource) {
		return !resource.getWarnings().isEmpty();
	}
	
	public static boolean containsProblems(org.eclipse.emf.ecore.resource.Resource resource) {
		return containsErrors(resource) || containsWarnings(resource);
	}
	
}
