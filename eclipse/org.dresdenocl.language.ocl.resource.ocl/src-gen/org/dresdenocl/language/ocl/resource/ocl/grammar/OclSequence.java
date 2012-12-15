/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar;

public class OclSequence extends tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement {
	
	public OclSequence(tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality cardinality, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement... elements) {
		super(cardinality, elements);
	}
	
	public String toString() {
		return tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclStringUtil.explode(getChildren(), " ");
	}
	
}
