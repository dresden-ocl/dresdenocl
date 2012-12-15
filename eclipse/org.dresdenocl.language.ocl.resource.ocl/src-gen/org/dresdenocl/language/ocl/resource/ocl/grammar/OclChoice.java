/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.grammar;

public class OclChoice extends org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement {
	
	public OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality cardinality, org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement... choices) {
		super(cardinality, choices);
	}
	
	public String toString() {
		return org.dresdenocl.language.ocl.resource.ocl.util.OclStringUtil.explode(getChildren(), "|");
	}
	
}
