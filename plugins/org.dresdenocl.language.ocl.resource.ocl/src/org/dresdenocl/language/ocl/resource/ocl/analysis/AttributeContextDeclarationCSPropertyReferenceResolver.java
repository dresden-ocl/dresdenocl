/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.analysis;

import java.util.List;

import org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveHelper;
import org.dresdenocl.language.ocl.resource.ocl.OclReferenceResolveHelperProvider;
import org.dresdenocl.pivotmodel.Property;

public class AttributeContextDeclarationCSPropertyReferenceResolver
		implements
		org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolver<org.dresdenocl.language.ocl.AttributeContextDeclarationCS, org.dresdenocl.pivotmodel.Property> {

	private org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<org.dresdenocl.language.ocl.AttributeContextDeclarationCS, org.dresdenocl.pivotmodel.Property> delegate = new org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<org.dresdenocl.language.ocl.AttributeContextDeclarationCS, org.dresdenocl.pivotmodel.Property>();

	public void resolve(
			java.lang.String identifier,
			org.dresdenocl.language.ocl.AttributeContextDeclarationCS container,
			org.eclipse.emf.ecore.EReference reference,
			int position,
			boolean resolveFuzzy,
			final org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveResult<org.dresdenocl.pivotmodel.Property> result) {
		IOclReferenceResolveHelper rrHelper = OclReferenceResolveHelperProvider
				.getOclReferenceResolveHelper();
		if (rrHelper != null) {
			List<Property> properties = rrHelper.resolvePropertyDefinition(identifier,
					resolveFuzzy, container);
			for (Property property : properties) {
				if (!resolveFuzzy)
					result.addMapping(identifier, property);
				else
					result.addMapping(property.getName(), property);
			}
		}
	}

	public java.lang.String deResolve(
			org.dresdenocl.pivotmodel.Property element,
			org.dresdenocl.language.ocl.AttributeContextDeclarationCS container,
			org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		// save options in a field or leave method empty if this resolver does not
		// depend on any option
	}

}
