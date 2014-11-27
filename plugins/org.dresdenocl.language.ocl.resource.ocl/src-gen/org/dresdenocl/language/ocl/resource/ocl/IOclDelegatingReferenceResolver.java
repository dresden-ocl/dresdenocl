/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl;

import org.eclipse.emf.ecore.EObject;

/**
 * <p>
 * A delegating reference resolver is an extension of a normal reference resolver
 * that can be configured with another resolver that it may delegate method calls
 * to. This interface can be implemented by additional resolvers to customize
 * resolving using the load option ADDITIONAL_REFERENCE_RESOLVERS.
 * </p>
 * 
 * @see org.dresdenocl.language.ocl.resource.ocl.IOclOptions
 */
public interface IOclDelegatingReferenceResolver<ContainerType extends EObject, ReferenceType extends EObject> extends org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolver<ContainerType, ReferenceType> {
	
	/**
	 * Sets the delegate for this resolver.
	 */
	public void setDelegate(org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolver<ContainerType, ReferenceType> delegate);
	
}
