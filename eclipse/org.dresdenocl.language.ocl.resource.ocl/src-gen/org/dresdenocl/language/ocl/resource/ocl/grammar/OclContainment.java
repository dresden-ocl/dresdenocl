/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.grammar;

public class OclContainment extends org.dresdenocl.language.ocl.resource.ocl.grammar.OclTerminal {
	
	private final org.eclipse.emf.ecore.EClass[] allowedTypes;
	
	public OclContainment(org.eclipse.emf.ecore.EStructuralFeature feature, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality cardinality, org.eclipse.emf.ecore.EClass[] allowedTypes, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
		this.allowedTypes = allowedTypes;
	}
	
	public org.eclipse.emf.ecore.EClass[] getAllowedTypes() {
		return allowedTypes;
	}
	
	public String toString() {
		String typeRestrictions = null;
		if (allowedTypes != null && allowedTypes.length > 0) {
			typeRestrictions = org.dresdenocl.language.ocl.resource.ocl.util.OclStringUtil.explode(allowedTypes, ", ", new org.dresdenocl.language.ocl.resource.ocl.IOclFunction1<String, org.eclipse.emf.ecore.EClass>() {
				public String execute(org.eclipse.emf.ecore.EClass eClass) {
					return eClass.getName();
				}
			});
		}
		return getFeature().getName() + (typeRestrictions == null ? "" : "[" + typeRestrictions + "]");
	}
	
}
