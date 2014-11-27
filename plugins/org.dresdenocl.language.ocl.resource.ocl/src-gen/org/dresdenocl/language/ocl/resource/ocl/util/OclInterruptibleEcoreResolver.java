/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.util;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * A utility class to resolve proxy objects that allows to terminate resolving.
 */
public class OclInterruptibleEcoreResolver {
	
	private boolean terminate = false;
	
	public void terminate() {
		terminate = true;
	}
	
	/**
	 * <p>
	 * Visits all proxies in the resource set and tries to resolve them.
	 * </p>
	 * 
	 * @param resourceSet the objects to visit.
	 */
	public void resolveAll(ResourceSet resourceSet) {
		List<Resource> resources = resourceSet.getResources();
		for (int i = 0; i < resources.size() && !terminate; i++) {
			resolveAll(resources.get(i));
		}
	}
	
	/**
	 * <p>
	 * Visits all proxies in the resource and tries to resolve them.
	 * </p>
	 * 
	 * @param resource the objects to visit.
	 */
	public void resolveAll(Resource resource) {
		for (EObject eObject : resource.getContents()) {
			if (terminate) {
				return;
			}
			resolveAll(eObject);
		}
	}
	
	/**
	 * <p>
	 * Visits all proxies referenced by the object and recursively any of its
	 * contained objects.
	 * </p>
	 * 
	 * @param eObject the object to visit.
	 */
	public void resolveAll(EObject eObject) {
		eObject.eContainer();
		resolveCrossReferences(eObject);
		for (Iterator<EObject> i = eObject.eAllContents(); i.hasNext();) {
			if (terminate) {
				return;
			}
			EObject childEObject = i.next();
			resolveCrossReferences(childEObject);
		}
	}
	
	protected void resolveCrossReferences(EObject eObject) {
		for (Iterator<EObject> i = eObject.eCrossReferences().iterator(); i.hasNext(); i.next()) {
			// The loop resolves the cross references by visiting them.
			if (terminate) {
				return;
			}
		}
	}
	
	/**
	 * <p>
	 * Searches for all unresolved proxy objects in the given resource.
	 * </p>
	 * 
	 * @param resource
	 * 
	 * @return all proxy objects that are not resolvable
	 */
	public Set<EObject> findUnresolvedProxies(Resource resource) {
		Set<EObject> unresolvedProxies = new LinkedHashSet<EObject>();
		
		for (Iterator<EObject> elementIt = EcoreUtil.getAllContents(resource, true); elementIt.hasNext(); ) {
			InternalEObject nextElement = (InternalEObject) elementIt.next();
			if (terminate) {
				return unresolvedProxies;
			}
			if (nextElement.eIsProxy()) {
				unresolvedProxies.add(nextElement);
			}
			for (EObject crElement : nextElement.eCrossReferences()) {
				if (terminate) {
					return unresolvedProxies;
				}
				crElement = EcoreUtil.resolve(crElement, resource);
				if (crElement.eIsProxy()) {
					unresolvedProxies.add(crElement);
				}
			}
		}
		return unresolvedProxies;
	}
	
	/**
	 * <p>
	 * Searches for all unresolved proxy objects in the given resource set.
	 * </p>
	 * 
	 * @param resourceSet
	 * 
	 * @return all proxy objects that are not resolvable
	 */
	public Set<EObject> findUnresolvedProxies(ResourceSet resourceSet) {
		Set<EObject> unresolvedProxies = new LinkedHashSet<EObject>();
		
		for (Resource resource : resourceSet.getResources()) {
			if (terminate) {
				return unresolvedProxies;
			}
			unresolvedProxies.addAll(findUnresolvedProxies(resource));
		}
		return unresolvedProxies;
	}
	
}
