/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar;

/**
 * A class to represent a rules in the grammar.
 */
public class OclRule extends tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement {
	
	private final org.eclipse.emf.ecore.EClass metaclass;
	
	public OclRule(org.eclipse.emf.ecore.EClass metaclass, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclChoice choice, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality cardinality) {
		super(cardinality, new tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement[] {choice});
		this.metaclass = metaclass;
	}
	
	public org.eclipse.emf.ecore.EClass getMetaclass() {
		return metaclass;
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclChoice getDefinition() {
		return (tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclChoice) getChildren()[0];
	}
	
}

