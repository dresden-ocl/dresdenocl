/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis;

import java.util.List;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveHelper;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.OclReferenceResolveHelperProvider;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;

public class NamedLiteralExpCSNamedElementReferenceResolver
		implements
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS, tudresden.ocl20.pivot.pivotmodel.NamedElement> {

	private tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS, tudresden.ocl20.pivot.pivotmodel.NamedElement> delegate = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS, tudresden.ocl20.pivot.pivotmodel.NamedElement>();

	public void resolve(
			java.lang.String identifier,
			tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS container,
			org.eclipse.emf.ecore.EReference reference,
			int position,
			boolean resolveFuzzy,
			final tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult<tudresden.ocl20.pivot.pivotmodel.NamedElement> result) {
		
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
			tudresden.ocl20.pivot.pivotmodel.NamedElement element,
			tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS container,
			org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		// save options in a field or leave method empty if this resolver does not
		// depend on any option
	}

}
