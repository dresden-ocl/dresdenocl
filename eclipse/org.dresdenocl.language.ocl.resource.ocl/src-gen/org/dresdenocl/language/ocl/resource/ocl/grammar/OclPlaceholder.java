/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.grammar;

/**
 * A class to represent placeholders in a grammar.
 */
public class OclPlaceholder extends org.dresdenocl.language.ocl.resource.ocl.grammar.OclTerminal {
	
	private final String tokenName;
	
	public OclPlaceholder(org.eclipse.emf.ecore.EStructuralFeature feature, String tokenName, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality cardinality, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
		this.tokenName = tokenName;
	}
	
	public String getTokenName() {
		return tokenName;
	}
	
}
