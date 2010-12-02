/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

/**
 * A representation for a range in a document where a keyword (i.e., a static
 * string) is expected.
 */
public class OclExpectedCsString extends tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclAbstractExpectedElement {
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclKeyword keyword;
	
	public OclExpectedCsString(tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclKeyword keyword) {
		super(keyword.getMetaclass());
		this.keyword = keyword;
	}
	
	public String getValue() {
		return keyword.getValue();
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
	
}
