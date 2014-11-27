/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl;


/**
 * Implementors of this interface provide an EMF resource.
 */
public interface IOclResourceProvider {
	
	/**
	 * Returns the resource.
	 */
	public org.dresdenocl.language.ocl.resource.ocl.IOclTextResource getResource();
	
}
