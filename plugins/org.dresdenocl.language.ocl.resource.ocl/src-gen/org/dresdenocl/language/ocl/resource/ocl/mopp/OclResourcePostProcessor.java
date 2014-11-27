/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;


public class OclResourcePostProcessor implements org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessor {
	
	public void process(org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource resource) {
		// Set the overrideResourcePostProcessor option to false to customize resource
		// post processing.
	}
	
	public void terminate() {
		// To signal termination to the process() method, setting a boolean field is
		// recommended. Depending on the value of this field process() can stop its
		// computation. However, this is only required for computation intensive
		// post-processors.
	}
	
}
