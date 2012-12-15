package org.dresdenocl.language.ocl.resource.ocl;

public class OclReferenceResolveHelperProvider {

	private static IOclReferenceResolveHelper oclReferenceResolveHelper;

	static {
		if (org.eclipse.core.runtime.Platform.isRunning()) {
			// find default load option providers
			org.eclipse.core.runtime.IExtensionRegistry extensionRegistry =
					org.eclipse.core.runtime.Platform.getExtensionRegistry();
			org.eclipse.core.runtime.IConfigurationElement configurationElements[] =
					extensionRegistry
							.getConfigurationElementsFor(org.dresdenocl.language.ocl.resource.ocl.mopp.OclPlugin.EP_DEFAULT_LOAD_OPTIONS_ID);
			for (org.eclipse.core.runtime.IConfigurationElement element : configurationElements) {
				try {
					org.dresdenocl.language.ocl.resource.ocl.IOclOptionProvider provider =
							(org.dresdenocl.language.ocl.resource.ocl.IOclOptionProvider) element
									.createExecutableExtension("class");
					final java.util.Map<?, ?> options = provider.getOptions();
					final java.util.Collection<?> keys = options.keySet();
					for (java.lang.Object key : keys) {
						if (key.equals("ReferenceResolveHelper")) {
							oclReferenceResolveHelper =
									(IOclReferenceResolveHelper) options.get(key);
						}
					}
				} catch (org.eclipse.core.runtime.CoreException ce) {
					org.dresdenocl.language.ocl.resource.ocl.mopp.OclPlugin
							.logError("Exception while getting default options.", ce);
					throw new IllegalStateException(
							"An error occurred during loading the OclReferenceResolveHelper "
									+ "from the registry. Static semantics analysis cannot be performed correctly.",
							ce);
				}
			}
			if (oclReferenceResolveHelper == null)
				throw new IllegalStateException(
						"An error occurred during loading the OclReferenceResolveHelper "
								+ "from the registry. Static semantics analysis cannot be performed correctly.");
		}
	}

	public static IOclReferenceResolveHelper getOclReferenceResolveHelper() {

		if (oclReferenceResolveHelper == null)
			throw new IllegalStateException(
					"The oclReferenceResolveHelper must be set. If you are using a "
							+ "standalone version of Dresden OCL make sure to call "
							+ "OclReferenceResolveHelperProvider#"
							+ "setOclReferenceResolverHelper(OclReferenceResolveHelper)");
		return oclReferenceResolveHelper;
	}

	/**
	 * This method is used in the standalone version of Dresden OCL as the
	 * {@link OclReferenceResolveHelperProvider} cannot be set via the extension
	 * registry.
	 * 
	 * @param oclReferenceResolveHelper
	 *          the {@link OclReferenceResolveHelperProvider} to set
	 */
	public static void setOclReferenceResolveHelper(
			IOclReferenceResolveHelper oclReferenceResolveHelper) {

		OclReferenceResolveHelperProvider.oclReferenceResolveHelper =
				oclReferenceResolveHelper;
	}

}
