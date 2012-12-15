/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl;

/**
 * A delegating reference resolver is an extension of a normal reference resolver
 * that can be configured with another resolver that it may delegate method calls
 * to. This interface can be implemented by additional resolvers to customize
 * resolving using the load option ADDITIONAL_REFERENCE_RESOLVERS.
 * 
 * @see tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptions
 */
public interface IOclDelegatingReferenceResolver<ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject> extends tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<ContainerType, ReferenceType> {
	
	/**
	 * Sets the delegate for this resolver.
	 */
	public void setDelegate(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<ContainerType, ReferenceType> delegate);
	
}
