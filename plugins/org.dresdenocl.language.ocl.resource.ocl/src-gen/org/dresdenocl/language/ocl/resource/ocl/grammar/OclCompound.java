/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.grammar;


public class OclCompound extends org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement {
	
	public OclCompound(org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice choice, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality cardinality) {
		super(cardinality, new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement[] {choice});
	}
	
	public String toString() {
		return "(" + getChildren()[0] + ")";
	}
	
}
