/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar;

public class OclChoice extends tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement {
	
	public OclChoice(tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality cardinality, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement... choices) {
		super(cardinality, choices);
	}
	
	public String toString() {
		return tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclStringUtil.explode(getChildren(), "|");
	}
	
}
