/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar;

public class OclLineBreak extends tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement {
	
	private final int tabs;
	
	public OclLineBreak(tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality cardinality, int tabs) {
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
