/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.grammar;

import org.eclipse.emf.ecore.EClass;

/**
 * A class to represent a rules in the grammar.
 */
public class OclRule extends org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement {
	
	private final EClass metaclass;
	
	public OclRule(EClass metaclass, org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice choice, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality cardinality) {
		super(cardinality, new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement[] {choice});
		this.metaclass = metaclass;
	}
	
	public EClass getMetaclass() {
		return metaclass;
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getDefinition() {
		return (org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice) getChildren()[0];
	}
	
}

