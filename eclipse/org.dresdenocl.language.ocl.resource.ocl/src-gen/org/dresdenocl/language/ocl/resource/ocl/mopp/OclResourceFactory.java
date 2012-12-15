/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclResourceFactory implements org.eclipse.emf.ecore.resource.Resource.Factory {
	
	public OclResourceFactory() {
		super();
	}
	
	public org.eclipse.emf.ecore.resource.Resource createResource(org.eclipse.emf.common.util.URI uri) {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource(uri);
	}
	
}
