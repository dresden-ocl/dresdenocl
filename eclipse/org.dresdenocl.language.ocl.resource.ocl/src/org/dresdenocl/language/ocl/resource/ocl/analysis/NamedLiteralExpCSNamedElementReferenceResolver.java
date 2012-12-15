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
import org.dresdenocl.pivotmodel.NamedElement;

public class NamedLiteralExpCSNamedElementReferenceResolver
		implements
		org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolver<org.dresdenocl.language.ocl.NamedLiteralExpCS, org.dresdenocl.pivotmodel.NamedElement> {

	private org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<org.dresdenocl.language.ocl.NamedLiteralExpCS, org.dresdenocl.pivotmodel.NamedElement> delegate = new org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<org.dresdenocl.language.ocl.NamedLiteralExpCS, org.dresdenocl.pivotmodel.NamedElement>();

	public void resolve(
			java.lang.String identifier,
			org.dresdenocl.language.ocl.NamedLiteralExpCS container,
			org.eclipse.emf.ecore.EReference reference,
			int position,
			boolean resolveFuzzy,
			final org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveResult<org.dresdenocl.pivotmodel.NamedElement> result) {
		
		IOclReferenceResolveHelper rrHelper = OclReferenceResolveHelperProvider
				.getOclReferenceResolveHelper();
		if (rrHelper != null) {
			List<NamedElement> namedElements = rrHelper.resolveNamedElement(
					identifier, resolveFuzzy, container);
			for (NamedElement namedElement : namedElements) {
				if (!resolveFuzzy)
					result.addMapping(identifier, namedElement);
				else
					result.addMapping(namedElement.getName(), namedElement);
			}
		}
	}

	public java.lang.String deResolve(
			org.dresdenocl.pivotmodel.NamedElement element,
			org.dresdenocl.language.ocl.NamedLiteralExpCS container,
			org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		// save options in a field or leave method empty if this resolver does not
		// depend on any option
	}

}
