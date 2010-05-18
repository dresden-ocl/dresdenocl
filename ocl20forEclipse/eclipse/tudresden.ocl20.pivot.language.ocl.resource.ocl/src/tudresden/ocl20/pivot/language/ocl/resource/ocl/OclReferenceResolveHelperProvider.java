package tudresden.ocl20.pivot.language.ocl.resource.ocl;


public class OclReferenceResolveHelperProvider {

	private static IOclReferenceResolveHelper oclReferenceResolveHelper;
	
	static {
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
							oclReferenceResolveHelper = (IOclReferenceResolveHelper) options.get(key);
						}
					}
				} catch (org.eclipse.core.runtime.CoreException ce) {
					tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclPlugin
							.logError("Exception while getting default options.", ce);
				}
			}
		}
	}
	
	public static IOclReferenceResolveHelper getOclReferenceResolveHelper() {
		return oclReferenceResolveHelper;
	}
	
}
