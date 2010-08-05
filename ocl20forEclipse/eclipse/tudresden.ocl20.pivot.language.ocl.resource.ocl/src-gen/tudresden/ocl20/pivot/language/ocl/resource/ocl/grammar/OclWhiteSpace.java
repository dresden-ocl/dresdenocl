/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar;

public class OclWhiteSpace extends tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement {
	
	private final int amount;
	
	public OclWhiteSpace(int amount, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality cardinality) {
		super(cardinality);
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String toString() {
		return "#" + getAmount();
	}
	
}
