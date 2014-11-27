/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl;

import java.util.Collection;
import org.eclipse.emf.common.util.URI;

/**
 * <p>
 * The result of a single attempt to resolve an identifier. The result can either
 * be successful (identifier was resolved to one or more objects) or failed
 * (identifier was not resolved). In the case of failure, the result provides an
 * error message.
 * </p>
 * <p>
 * This interface must not be implemented by clients.
 * </p>
 * 
 * @param <ReferenceType> the type of the references that can be contained in this
 * result
 */
public interface IOclReferenceResolveResult<ReferenceType> {
	
	/**
	 * Returns the error message that describes what went wrong while resolving a
	 * reference.
	 */
	public String getErrorMessage();
	
	/**
	 * Returns an unmodifiable collection of the quick fixes that can be used to
	 * resolve the resolving error.
	 */
	public Collection<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix> getQuickFixes();
	
	/**
	 * Adds a quick fix to the set of quick fixes that can be used to resolve the
	 * resolving error.
	 */
	public void addQuickFix(org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix quickFix);
	
	/**
	 * <p>
	 * Sets the error message that describes what went wrong while resolving a
	 * reference. If a mapping for the reference was already found (i.e., addMapping()
	 * was called before), the call to this method is ignored. If addMapping() is
	 * called afterwards, the error message is also discarded.
	 * </p>
	 * 
	 * @param message the error that prevented resolving the reference
	 */
	public void setErrorMessage(String message);
	
	/**
	 * <p>
	 * Adds a mapping from the given identifier to the given object. Adding such a
	 * mapping means that the identifier was resolved to reference the target object.
	 * Previous errors as well as future ones will be discarded. Once a mapping is
	 * found, resolve errors have no meaning any more.
	 * </p>
	 * <p>
	 * The target object can be null if the resolution is fuzzy. Otherwise target must
	 * not be null and implementations of this method can throw an
	 * IllegalArgumentException if this rule is violated.
	 * </p>
	 * <p>
	 * Optionally a warning can be passed to this method if resolving the reference
	 * was successful, but not accurate.
	 * </p>
	 */
	public void addMapping(String identifier, ReferenceType target, String warning);
	
	/**
	 * 
	 * @see addMapping(String, ReferenceType, String)
	 */
	public void addMapping(String identifier, ReferenceType target);
	
	/**
	 * <p>
	 * Adds a mapping from the given identifier to another identifier. This is useful
	 * for multilevel resolving where internal identifiers are replaced by external
	 * ones depending on the context. Usually the external identifiers are replaced by
	 * target object later on.
	 * </p>
	 * <p>
	 * Optionally a warning can be passed to this method if resolving reference was
	 * successful, but not accurate.
	 * </p>
	 * 
	 * @param identifier
	 * @param newIdentifier
	 */
	public void addMapping(String identifier, URI newIdentifier, String warning);
	
	/**
	 * 
	 * @see addMapping(String, URI, String)
	 */
	public void addMapping(String identifier, URI newIdentifier);
	
	/**
	 * <p>
	 * Indicates the type of the result. Depending on the type of the result different
	 * information is available (e.g., the error message is only set if the resolve
	 * operation failed).
	 * </p>
	 * 
	 * @return true if the reference was successfully resolved
	 */
	public boolean wasResolved();
	
	/**
	 * <p>
	 * Indicates the type of the result. Depending on the type of the result different
	 * information is available (e.g., the unique mapping is only set if the resolve
	 * operation returned a unique result).
	 * </p>
	 * 
	 * @return true if the reference was resolved to exactly one target object
	 */
	public boolean wasResolvedUniquely();
	
	/**
	 * Indicates the type of the result. Depending on the type of the result different
	 * information is available (e.g., the multiple mappings are only set if the
	 * resolve operation returned multiple result).
	 * 
	 * @return true the reference was resolved to more than one target object
	 */
	public boolean wasResolvedMultiple();
	
	/**
	 * Returns all mappings that were found while resolving an identifier.
	 */
	public Collection<org.dresdenocl.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>> getMappings();
	
}
