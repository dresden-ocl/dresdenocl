/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.util;

/**
 * Class TextResourceUtil can be used to perform common tasks on text resources,
 * such as loading and saving resources, as well as, checking them for errors.
 */
public class OclTextResourceUtil {
	
	public static tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource getResource(org.eclipse.core.resources.IFile file) {
		org.eclipse.emf.ecore.resource.ResourceSet rs = new org.eclipse.emf.ecore.resource.impl.ResourceSetImpl();
		org.eclipse.emf.ecore.resource.Resource csResource = rs.getResource(org.eclipse.emf.common.util.URI.createPlatformResourceURI(file.getFullPath().toString(),true), true);
		return (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource) csResource;
	}
	
	public static tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource getResource(java.io.File file) {
		return getResource(file, null);
	}
	
	public static tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource getResource(java.io.File file, java.util.Map<?,?> options) {
		org.eclipse.emf.ecore.resource.ResourceSet rs = new org.eclipse.emf.ecore.resource.impl.ResourceSetImpl();
		if (options != null) {
			rs.getLoadOptions().putAll(options);
		}
		org.eclipse.emf.ecore.resource.Resource csResource = rs.getResource(org.eclipse.emf.common.util.URI.createFileURI(file.getAbsolutePath()), true);
		return (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource) csResource;
	}
}
