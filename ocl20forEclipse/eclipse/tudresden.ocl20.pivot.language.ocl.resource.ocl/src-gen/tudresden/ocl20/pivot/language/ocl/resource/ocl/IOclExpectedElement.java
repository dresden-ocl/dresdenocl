/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl;

/**
 * An element that is expected at a given position in a resource stream.
 */
public interface IOclExpectedElement {
	
	public java.lang.String getTokenName();
	public org.eclipse.emf.ecore.EClass getRuleMetaclass();
	public void addFollower(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclExpectedElement follower, org.eclipse.emf.ecore.EStructuralFeature[] path);
	public java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclPair<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclExpectedElement, org.eclipse.emf.ecore.EStructuralFeature[]>> getFollowers();
}
