package tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.IOclResource;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.factory.OclStaticSemanticsFactoryEP;

public class OclStaticSemanticsProvider {

	private static Map<IOclResource, tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics> oclStaticSemantics = new IdentityHashMap<IOclResource, tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics>();
	private static Map<String, tudresden.ocl20.pivot.language.ocl.staticsemantics.factory.OclStaticSemanticsFactory> oclStaticSemanticFactories = new HashMap<String, tudresden.ocl20.pivot.language.ocl.staticsemantics.factory.OclStaticSemanticsFactory>();

	public static tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics getStaticSemantics(
			IOclResource resource) {
		if (!oclStaticSemantics.containsKey(resource)) {
			if (org.eclipse.core.runtime.Platform.isRunning()) {
				// find default load option providers
				org.eclipse.core.runtime.IExtensionRegistry extensionRegistry = org.eclipse.core.runtime.Platform
						.getExtensionRegistry();
				org.eclipse.core.runtime.IConfigurationElement configurationElements[] = extensionRegistry
						.getConfigurationElementsFor(OclStaticSemanticsFactoryEP.OCL_STATIC_SEMANTICS_FACTORY_ID);
				for (org.eclipse.core.runtime.IConfigurationElement element : configurationElements) {
					try {
						String fileExtension = element.getAttribute("fileExtension");
						tudresden.ocl20.pivot.language.ocl.staticsemantics.factory.OclStaticSemanticsFactory oclStaticSemanticsFactory = (tudresden.ocl20.pivot.language.ocl.staticsemantics.factory.OclStaticSemanticsFactory) element
								.createExecutableExtension("oclStaticSemanticsFactory");
						// FIXME: Add warning if same file extension is registered multiple
						// times
						oclStaticSemanticFactories.put(fileExtension,
								oclStaticSemanticsFactory);
					} catch (org.eclipse.core.runtime.CoreException ce) {
						tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclPlugin
								.logError("Exception while getting default options.", ce);
					}
				}
			}
			tudresden.ocl20.pivot.language.ocl.staticsemantics.factory.OclStaticSemanticsFactory rightFactory = null;
			String fileExtension = resource.getURI().fileExtension();
			if (oclStaticSemanticFactories.containsKey(fileExtension))
				rightFactory = oclStaticSemanticFactories.get(fileExtension);
			else
				throw new IllegalArgumentException(
						"Cannot find registered StaticSemanticsFactory for file extension "
								+ fileExtension);
			
			oclStaticSemantics.put(resource, rightFactory
					.createOclStaticSemantics(resource));
		
		}
		return oclStaticSemantics.get(resource);
	}

}
