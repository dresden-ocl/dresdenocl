/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.grammar;

public class OclWhiteSpace extends org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement {
	
	private final int amount;
	
	public OclWhiteSpace(int amount, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality cardinality) {
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
