/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

/**
 * A basic implementation of the
 * org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveResult interface
 * that collects mappings in a list.
 * 
 * @param <ReferenceType> the type of the references that can be contained in this
 * result
 */
public class OclReferenceResolveResult<ReferenceType> implements org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveResult<ReferenceType> {
	
	private java.util.Collection<org.dresdenocl.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>> mappings;
	private String errorMessage;
	private boolean resolveFuzzy;
	private java.util.Set<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix> quickFixes;
	
	public OclReferenceResolveResult(boolean resolveFuzzy) {
		super();
		this.resolveFuzzy = resolveFuzzy;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public java.util.Collection<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix> getQuickFixes() {
		if (quickFixes == null) {
			quickFixes = new java.util.LinkedHashSet<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix>();
		}
		return java.util.Collections.unmodifiableSet(quickFixes);
	}
	
	public void addQuickFix(org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix quickFix) {
		if (quickFixes == null) {
			quickFixes = new java.util.LinkedHashSet<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix>();
		}
		quickFixes.add(quickFix);
	}
	
	public java.util.Collection<org.dresdenocl.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>> getMappings() {
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
			mappings = new java.util.ArrayList<org.dresdenocl.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>>(1);
		}
		mappings.add(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclElementMapping<ReferenceType>(identifier, target, warning));
		errorMessage = null;
	}
	
	public void addMapping(String identifier, org.eclipse.emf.common.util.URI uri) {
		addMapping(identifier, uri, null);
	}
	
	public void addMapping(String identifier, org.eclipse.emf.common.util.URI uri, String warning) {
		if (mappings == null) {
			mappings = new java.util.ArrayList<org.dresdenocl.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>>(1);
		}
		mappings.add(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclURIMapping<ReferenceType>(identifier, uri, warning));
	}
}
