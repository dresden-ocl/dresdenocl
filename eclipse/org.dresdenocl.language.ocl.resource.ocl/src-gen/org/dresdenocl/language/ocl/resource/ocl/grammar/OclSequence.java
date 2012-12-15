/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.grammar;

public class OclSequence extends org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement {
	
	public OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality cardinality, org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement... elements) {
		super(cardinality, elements);
	}
	
	public String toString() {
		return org.dresdenocl.language.ocl.resource.ocl.util.OclStringUtil.explode(getChildren(), " ");
	}
	
}
