/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.grammar;

public class OclLineBreak extends org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement {
	
	private final int tabs;
	
	public OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality cardinality, int tabs) {
		super(cardinality);
		this.tabs = tabs;
	}
	
	public int getTabs() {
		return tabs;
	}
	
	public String toString() {
		return "!" + getTabs();
	}
	
}
