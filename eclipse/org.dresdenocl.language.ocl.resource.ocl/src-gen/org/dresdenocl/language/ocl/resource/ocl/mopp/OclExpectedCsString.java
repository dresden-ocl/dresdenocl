/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

/**
 * A representation for a range in a document where a keyword (i.e., a static
 * string) is expected.
 */
public class OclExpectedCsString extends org.dresdenocl.language.ocl.resource.ocl.mopp.OclAbstractExpectedElement {
	
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword keyword;
	
	public OclExpectedCsString(org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword keyword) {
		super(keyword.getMetaclass());
		this.keyword = keyword;
	}
	
	public String getValue() {
		return keyword.getValue();
	}
	
	/**
	 * Returns the expected keyword.
	 */
	public org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement getSymtaxElement() {
		return keyword;
	}
	
	public java.util.Set<String> getTokenNames() {
		return java.util.Collections.singleton("'" + getValue() + "'");
	}
	
	public String toString() {
		return "CsString \"" + getValue() + "\"";
	}
	
	public boolean equals(Object o) {
		if (o instanceof OclExpectedCsString) {
			return getValue().equals(((OclExpectedCsString) o).getValue());
		}
		return false;
	}
	
	@Override	
	public int hashCode() {
		return getValue().hashCode();
	}
	
}
