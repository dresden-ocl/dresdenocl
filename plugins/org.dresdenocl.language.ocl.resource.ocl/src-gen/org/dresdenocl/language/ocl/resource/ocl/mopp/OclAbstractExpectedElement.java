/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;

/**
 * Abstract super class for all expected elements. Provides methods to add
 * followers.
 */
public abstract class OclAbstractExpectedElement implements org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement {
	
	private EClass ruleMetaclass;
	
	private Set<org.dresdenocl.language.ocl.resource.ocl.util.OclPair<org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement, org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[]>> followers = new LinkedHashSet<org.dresdenocl.language.ocl.resource.ocl.util.OclPair<org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement, org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[]>>();
	
	public OclAbstractExpectedElement(EClass ruleMetaclass) {
		super();
		this.ruleMetaclass = ruleMetaclass;
	}
	
	public EClass getRuleMetaclass() {
		return ruleMetaclass;
	}
	
	public void addFollower(org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement follower, org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[] path) {
		followers.add(new org.dresdenocl.language.ocl.resource.ocl.util.OclPair<org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement, org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[]>(follower, path));
	}
	
	public Collection<org.dresdenocl.language.ocl.resource.ocl.util.OclPair<org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement, org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[]>> getFollowers() {
		return followers;
	}
	
}
