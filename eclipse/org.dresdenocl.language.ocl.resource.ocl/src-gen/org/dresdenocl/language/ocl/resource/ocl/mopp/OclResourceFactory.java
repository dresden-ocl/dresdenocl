/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

public class OclResourceFactory implements org.eclipse.emf.ecore.resource.Resource.Factory {
	
	public OclResourceFactory() {
		super();
	}
	
	public org.eclipse.emf.ecore.resource.Resource createResource(org.eclipse.emf.common.util.URI uri) {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource(uri);
	}
	
}
