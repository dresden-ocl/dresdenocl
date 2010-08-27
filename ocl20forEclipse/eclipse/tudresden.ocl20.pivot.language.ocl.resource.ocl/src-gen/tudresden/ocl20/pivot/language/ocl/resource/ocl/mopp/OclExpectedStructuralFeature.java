/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

/**
 * A representation for a range in a document where a structural feature (e.g., a
 * reference) is expected.
 */
public class OclExpectedStructuralFeature extends tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclAbstractExpectedElement {
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclPlaceholder placeholder;
	
	public OclExpectedStructuralFeature(tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclPlaceholder placeholder) {
		super(placeholder.getMetaclass());
		this.placeholder = placeholder;
	}
	
	public org.eclipse.emf.ecore.EStructuralFeature getFeature() {
		return placeholder.getFeature();
	}
	
	public String getTokenName() {
		return placeholder.getTokenName();
	}
	
	public String toString() {
		return "EFeature " + getFeature().getEContainingClass().getName() + "." + getFeature().getName();
	}
	
	public boolean equals(Object o) {
		if (o instanceof OclExpectedStructuralFeature) {
			return getFeature().equals(((OclExpectedStructuralFeature) o).getFeature());
		}
		return false;
	}
}
