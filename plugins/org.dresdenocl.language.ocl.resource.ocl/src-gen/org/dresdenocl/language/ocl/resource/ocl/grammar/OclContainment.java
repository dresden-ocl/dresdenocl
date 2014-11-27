/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.grammar;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class OclContainment extends org.dresdenocl.language.ocl.resource.ocl.grammar.OclTerminal {
	
	private final EClass[] allowedTypes;
	
	public OclContainment(EStructuralFeature feature, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality cardinality, EClass[] allowedTypes, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
		this.allowedTypes = allowedTypes;
	}
	
	public EClass[] getAllowedTypes() {
		return allowedTypes;
	}
	
	public String toString() {
		String typeRestrictions = null;
		if (allowedTypes != null && allowedTypes.length > 0) {
			typeRestrictions = org.dresdenocl.language.ocl.resource.ocl.util.OclStringUtil.explode(allowedTypes, ", ", new org.dresdenocl.language.ocl.resource.ocl.IOclFunction1<String, EClass>() {
				public String execute(EClass eClass) {
					return eClass.getName();
				}
			});
		}
		return getFeature().getName() + (typeRestrictions == null ? "" : "[" + typeRestrictions + "]");
	}
	
}
