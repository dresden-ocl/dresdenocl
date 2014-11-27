/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import org.eclipse.emf.common.util.URI;

/**
 * <p>
 * A basic implementation of the
 * org.dresdenocl.language.ocl.resource.ocl.IOclURIMapping interface that can map
 * identifiers to URIs.
 * </p>
 * 
 * @param <ReferenceType> unused type parameter which is needed to implement
 * org.dresdenocl.language.ocl.resource.ocl.IOclURIMapping.
 */
public class OclURIMapping<ReferenceType> implements org.dresdenocl.language.ocl.resource.ocl.IOclURIMapping<ReferenceType> {
	
	private URI uri;
	private String identifier;
	private String warning;
	
	public OclURIMapping(String identifier, URI newIdentifier, String warning) {
		super();
		this.uri = newIdentifier;
		this.identifier = identifier;
		this.warning = warning;
	}
	
	public URI getTargetIdentifier() {
		return uri;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getWarning() {
		return warning;
	}
	
}
