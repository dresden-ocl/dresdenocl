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
import tudresden.ocl20.pivot.pivotmodel.Namespace;

public class PackageDeclarationNestedNamespaceCSNamespaceReferenceResolver
		implements
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS, tudresden.ocl20.pivot.pivotmodel.Namespace> {

	private tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS, tudresden.ocl20.pivot.pivotmodel.Namespace> delegate = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS, tudresden.ocl20.pivot.pivotmodel.Namespace>();

	public void resolve(
			java.lang.String identifier,
			tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS container,
			org.eclipse.emf.ecore.EReference reference,
			int position,
			boolean resolveFuzzy,
			final tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult<tudresden.ocl20.pivot.pivotmodel.Namespace> result) {

		IOclReferenceResolveHelper rrHelper = OclReferenceResolveHelperProvider
				.getOclReferenceResolveHelper();
		if (rrHelper != null) {
			List<Namespace> namespaces = rrHelper.resolveNamespace(identifier,
					resolveFuzzy, container);
			String cutOff = "";
			if (!namespaces.isEmpty()) {
				cutOff = namespaces.get(0).getNestingNamespace().getQualifiedName() + "::";
			}
			for (Namespace namespace : namespaces) {
				String qualifiedName = namespace.getQualifiedName();
				result.addMapping(qualifiedName.replace(cutOff, ""), namespace);
			}
		}
	}

	public java.lang.String deResolve(
			tudresden.ocl20.pivot.pivotmodel.Namespace element,
			tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS container,
			org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		// save options in a field or leave method empty if this resolver does not
		// depend on any option
	}

}
