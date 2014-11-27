/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.eclipse.emf.common.util.URI;

/**
 * <p>
 * A basic implementation of the
 * org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveResult interface
 * that collects mappings in a list.
 * </p>
 * 
 * @param <ReferenceType> the type of the references that can be contained in this
 * result
 */
public class OclReferenceResolveResult<ReferenceType> implements org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveResult<ReferenceType> {
	
	private Collection<org.dresdenocl.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>> mappings;
	private String errorMessage;
	private boolean resolveFuzzy;
	private Set<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix> quickFixes;
	
	public OclReferenceResolveResult(boolean resolveFuzzy) {
		super();
		this.resolveFuzzy = resolveFuzzy;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public Collection<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix> getQuickFixes() {
		if (quickFixes == null) {
			quickFixes = new LinkedHashSet<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix>();
		}
		return Collections.unmodifiableSet(quickFixes);
	}
	
	public void addQuickFix(org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix quickFix) {
		if (quickFixes == null) {
			quickFixes = new LinkedHashSet<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix>();
		}
		quickFixes.add(quickFix);
	}
	
	public Collection<org.dresdenocl.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>> getMappings() {
		return mappings;
	}
	
	public boolean wasResolved() {
		return mappings != null;
	}
	
	public boolean wasResolvedMultiple() {
		return mappings != null && mappings.size() > 1;
	}
	
	public boolean wasResolvedUniquely() {
		return mappings != null && mappings.size() == 1;
	}
	
	public void setErrorMessage(String message) {
		errorMessage = message;
	}
	
	public void addMapping(String identifier, ReferenceType target) {
		if (!resolveFuzzy && target == null) {
			throw new IllegalArgumentException("Mapping references to null is only allowed for fuzzy resolution.");
		}
		addMapping(identifier, target, null);
	}
	
	public void addMapping(String identifier, ReferenceType target, String warning) {
		if (mappings == null) {
			mappings = new ArrayList<org.dresdenocl.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>>(1);
		}
		mappings.add(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclElementMapping<ReferenceType>(identifier, target, warning));
		errorMessage = null;
	}
	
	public void addMapping(String identifier, URI uri) {
		addMapping(identifier, uri, null);
	}
	
	public void addMapping(String identifier, URI uri, String warning) {
		if (mappings == null) {
			mappings = new ArrayList<org.dresdenocl.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>>(1);
		}
		mappings.add(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclURIMapping<ReferenceType>(identifier, uri, warning));
	}
}
