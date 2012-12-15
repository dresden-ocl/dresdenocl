/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar;

/**
 * A class to represent a keyword in the grammar.
 */
public class OclKeyword extends tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement {
	
	private final String value;
	
	public OclKeyword(String value, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality cardinality) {
		super(cardinality, null);
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return value;
	}
	
}
