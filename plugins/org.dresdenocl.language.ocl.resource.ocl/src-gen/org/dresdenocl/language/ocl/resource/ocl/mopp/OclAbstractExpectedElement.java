/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

/**
 * Abstract super class for all expected elements. Provides methods to add
 * followers.
 */
public abstract class OclAbstractExpectedElement implements org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement {
	
	private org.eclipse.emf.ecore.EClass ruleMetaclass;
	
	private java.util.Set<org.dresdenocl.language.ocl.resource.ocl.util.OclPair<org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement, org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[]>> followers = new java.util.LinkedHashSet<org.dresdenocl.language.ocl.resource.ocl.util.OclPair<org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement, org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[]>>();
	
	public OclAbstractExpectedElement(org.eclipse.emf.ecore.EClass ruleMetaclass) {
		super();
		this.ruleMetaclass = ruleMetaclass;
	}
	
	public org.eclipse.emf.ecore.EClass getRuleMetaclass() {
		return ruleMetaclass;
	}
	
	public void addFollower(org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement follower, org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[] path) {
		followers.add(new org.dresdenocl.language.ocl.resource.ocl.util.OclPair<org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement, org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[]>(follower, path));
	}
	
	public java.util.Collection<org.dresdenocl.language.ocl.resource.ocl.util.OclPair<org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement, org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[]>> getFollowers() {
		return followers;
	}
	
}
