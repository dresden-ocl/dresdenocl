/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis;

import java.util.List;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveHelper;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.TypedElement;

public class VariableOrStaticPropertyOrEnumLiteralExpCSTypedElementReferenceResolver
		implements
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.VariableOrStaticPropertyOrEnumLiteralExpCS, tudresden.ocl20.pivot.pivotmodel.TypedElement> {

	private tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.VariableOrStaticPropertyOrEnumLiteralExpCS, tudresden.ocl20.pivot.pivotmodel.TypedElement> delegate = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.VariableOrStaticPropertyOrEnumLiteralExpCS, tudresden.ocl20.pivot.pivotmodel.TypedElement>();

	public void resolve(
			java.lang.String identifier,
			tudresden.ocl20.pivot.language.ocl.VariableOrStaticPropertyOrEnumLiteralExpCS container,
			org.eclipse.emf.ecore.EReference reference,
			int position,
			boolean resolveFuzzy,
			final tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult<tudresden.ocl20.pivot.pivotmodel.TypedElement> result) {
		IModel model = ModelBusPlugin.getModelRegistry().getActiveModel();
		if (model == null)
			return;
		OclLibrary oclLibrary = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary();

		IOclReferenceResolveHelper rrHelper = null;
		if (org.eclipse.core.runtime.Platform.isRunning()) {
			// find default load option providers
			org.eclipse.core.runtime.IExtensionRegistry extensionRegistry = org.eclipse.core.runtime.Platform
					.getExtensionRegistry();
			org.eclipse.core.runtime.IConfigurationElement configurationElements[] = extensionRegistry
					.getConfigurationElementsFor(tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclPlugin.EP_DEFAULT_LOAD_OPTIONS_ID);
			for (org.eclipse.core.runtime.IConfigurationElement element : configurationElements) {
				try {
					tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptionProvider provider = (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptionProvider) element
							.createExecutableExtension("class");
					final java.util.Map<?, ?> options = provider.getOptions();
					final java.util.Collection<?> keys = options.keySet();
					for (java.lang.Object key : keys) {
						if (key.equals("ReferenceResolveHelper")) {
							rrHelper = (IOclReferenceResolveHelper) options.get(key);
						}
					}
				} catch (org.eclipse.core.runtime.CoreException ce) {
					tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclPlugin
							.logError("Exception while getting default options.", ce);
				}
			}
		}
		if (rrHelper != null) {
			List<TypedElement> typedElements = rrHelper.resolveTypedElement(
					identifier, resolveFuzzy, container, model, oclLibrary);
			for (TypedElement typedElement : typedElements) {
				if (!resolveFuzzy)
					result.addMapping(identifier, typedElement);
				else
					result.addMapping(typedElement.getName(), typedElement);
			}
		}
	}

	public java.lang.String deResolve(
			tudresden.ocl20.pivot.pivotmodel.TypedElement element,
			tudresden.ocl20.pivot.language.ocl.VariableOrStaticPropertyOrEnumLiteralExpCS container,
			org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		// save options in a field or leave method empty if this resolver does not
		// depend on any option
	}

}