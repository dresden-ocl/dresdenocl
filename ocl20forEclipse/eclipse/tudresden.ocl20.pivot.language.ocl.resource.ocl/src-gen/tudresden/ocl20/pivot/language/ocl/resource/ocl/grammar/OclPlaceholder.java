/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar;

/**
 * A class to represent placeholders in a grammar.
 */
public class OclPlaceholder extends tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclTerminal {
	
	private final java.lang.String tokenName;
	
	public OclPlaceholder(org.eclipse.emf.ecore.EStructuralFeature feature, java.lang.String tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality cardinality, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
		this.tokenName = tokenName;
	}
	
	public java.lang.String getTokenName() {
		return tokenName;
	}
	
}
