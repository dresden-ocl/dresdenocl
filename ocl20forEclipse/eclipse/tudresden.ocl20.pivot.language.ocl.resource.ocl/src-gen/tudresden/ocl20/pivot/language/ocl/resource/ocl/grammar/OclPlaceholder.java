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
	
	private final String tokenName;
	
	public OclPlaceholder(org.eclipse.emf.ecore.EStructuralFeature feature, String tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality cardinality, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
		this.tokenName = tokenName;
	}
	
	public String getTokenName() {
		return tokenName;
	}
	
}
