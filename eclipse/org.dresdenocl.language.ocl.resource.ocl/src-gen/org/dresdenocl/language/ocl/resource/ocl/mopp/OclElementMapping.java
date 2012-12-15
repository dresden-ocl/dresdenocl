/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

/**
 * A basic implementation of the
 * tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclElementMapping interface.
 * 
 * @param <ReferenceType> the type of the reference that can be mapped to
 */
public class OclElementMapping<ReferenceType> implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclElementMapping<ReferenceType> {
	
	private final ReferenceType target;
	private String identifier;
	private String warning;
	
	public OclElementMapping(String identifier, ReferenceType target, String warning) {
		super();
		this.target = target;
		this.identifier = identifier;
		this.warning = warning;
	}
	
	public ReferenceType getTargetElement() {
		return target;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getWarning() {
		return warning;
	}
	
}
