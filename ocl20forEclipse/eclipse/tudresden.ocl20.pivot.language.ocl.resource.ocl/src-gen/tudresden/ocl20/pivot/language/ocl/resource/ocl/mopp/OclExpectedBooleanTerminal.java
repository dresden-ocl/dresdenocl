/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

/**
 * A representation for a range in a document where a boolean attribute is
 * expected.
 */
public class OclExpectedBooleanTerminal extends tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclAbstractExpectedElement {
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclBooleanTerminal booleanTerminal;
	
	public OclExpectedBooleanTerminal(tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclBooleanTerminal booleanTerminal) {
		super(booleanTerminal.getMetaclass());
		this.booleanTerminal = booleanTerminal;
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclBooleanTerminal getBooleanTerminal() {
		return booleanTerminal;
	}
	
	/**
	 * Returns the expected boolean terminal.
	 */
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement getSymtaxElement() {
		return booleanTerminal;
	}
	
	private org.eclipse.emf.ecore.EStructuralFeature getFeature() {
		return booleanTerminal.getFeature();
	}
	
	public String toString() {
		return "EFeature " + getFeature().getEContainingClass().getName() + "." + getFeature().getName();
	}
	
	public boolean equals(Object o) {
		if (o instanceof OclExpectedBooleanTerminal) {
			return getFeature().equals(((OclExpectedBooleanTerminal) o).getFeature());
		}
		return false;
	}
	
	@Override	
	public int hashCode() {
		return getFeature().hashCode();
	}
	
	public java.util.Set<String> getTokenNames() {
		// BooleanTerminals are associated with two or one token(s)
		java.util.Set<String> tokenNames = new java.util.LinkedHashSet<String>(2);
		String trueLiteral = booleanTerminal.getTrueLiteral();
		if (!"".equals(trueLiteral)) {
			tokenNames.add("'" + trueLiteral + "'");
		}
		String falseLiteral = booleanTerminal.getFalseLiteral();
		if (!"".equals(falseLiteral)) {
			tokenNames.add("'" + falseLiteral + "'");
		}
		return tokenNames;
	}
	
}
