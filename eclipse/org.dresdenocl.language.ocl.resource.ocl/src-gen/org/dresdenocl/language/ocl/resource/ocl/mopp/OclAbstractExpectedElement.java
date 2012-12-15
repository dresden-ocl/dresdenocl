/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

/**
 * Abstract super class for all expected elements. Provides methods to add
 * followers.
 */
public abstract class OclAbstractExpectedElement implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclExpectedElement {
	
	private org.eclipse.emf.ecore.EClass ruleMetaclass;
	
	private java.util.Set<tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclPair<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclExpectedElement, tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclContainedFeature[]>> followers = new java.util.LinkedHashSet<tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclPair<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclExpectedElement, tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclContainedFeature[]>>();
	
	public OclAbstractExpectedElement(org.eclipse.emf.ecore.EClass ruleMetaclass) {
		super();
		this.ruleMetaclass = ruleMetaclass;
	}
	
	public org.eclipse.emf.ecore.EClass getRuleMetaclass() {
		return ruleMetaclass;
	}
	
	public void addFollower(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclExpectedElement follower, tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclContainedFeature[] path) {
		followers.add(new tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclPair<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclExpectedElement, tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclContainedFeature[]>(follower, path));
	}
	
	public java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclPair<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclExpectedElement, tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclContainedFeature[]>> getFollowers() {
		return followers;
	}
	
}
