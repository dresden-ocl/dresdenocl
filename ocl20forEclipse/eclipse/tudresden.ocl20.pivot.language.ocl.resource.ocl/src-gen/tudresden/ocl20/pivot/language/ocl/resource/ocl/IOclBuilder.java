/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl;

public interface IOclBuilder {
	
	public boolean isBuildingNeeded(org.eclipse.emf.common.util.URI uri);
	
	public org.eclipse.core.runtime.IStatus build(tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource resource, org.eclipse.core.runtime.IProgressMonitor monitor);
}
