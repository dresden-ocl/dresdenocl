/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

public class OclResourceFactory implements Resource.Factory {
	
	public OclResourceFactory() {
		super();
	}
	
	public Resource createResource(URI uri) {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource(uri);
	}
	
}
