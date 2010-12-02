/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar;

public class OclCompound extends tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement {
	
	public OclCompound(tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclChoice choice, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality cardinality) {
		super(cardinality, new tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement[] {choice});
	}
	
	public String toString() {
		return "(" + getChildren()[0] + ")";
	}
	
}
