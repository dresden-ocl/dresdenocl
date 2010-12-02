/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar;

public class OclContainment extends tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclTerminal {
	
	public OclContainment(org.eclipse.emf.ecore.EStructuralFeature feature, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality cardinality, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
	}
	
	public String toString() {
		return getFeature().getName();
	}
	
}
