/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

/**
 * A basic implementation of the
 * tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult
 * interface that collects mappings in a list.
 * 
 * @param <ReferenceType> the type of the references that can be contained in this
 * result
 */
public class OclReferenceResolveResult<ReferenceType> implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult<ReferenceType> {
	
	private java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>> mappings;
	private String errorMessage;
	private boolean resolveFuzzy;
	
	public OclReferenceResolveResult(boolean resolveFuzzy) {
		super();
		this.resolveFuzzy = resolveFuzzy;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>> getMappings() {
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
			mappings = new java.util.ArrayList<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>>();
		}
		mappings.add(new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclElementMapping<ReferenceType>(identifier, target, warning));
		errorMessage = null;
	}
	
	public void addMapping(String identifier, org.eclipse.emf.common.util.URI uri) {
		addMapping(identifier, uri, null);
	}
	
	public void addMapping(String identifier, org.eclipse.emf.common.util.URI uri, String warning) {
		if (mappings == null) {
			mappings = new java.util.ArrayList<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceMapping<ReferenceType>>();
		}
		mappings.add(new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclURIMapping<ReferenceType>(identifier, uri, warning));
	}
}
