/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.grammar;

/**
 * A class to represent a keyword in the grammar.
 */
public class OclKeyword extends org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement {
	
	private final String value;
	
	public OclKeyword(String value, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality cardinality) {
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
