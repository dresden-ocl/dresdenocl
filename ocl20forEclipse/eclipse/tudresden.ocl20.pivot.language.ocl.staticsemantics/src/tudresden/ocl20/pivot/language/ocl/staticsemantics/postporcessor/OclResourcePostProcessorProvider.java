package tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessor;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessorProvider;

public class OclResourcePostProcessorProvider implements
		IOclResourcePostProcessorProvider {

	private IOclResourcePostProcessor oclResourcePostProcessor;

	public OclResourcePostProcessorProvider() {
		oclResourcePostProcessor = new OclStaticSemanticsPostProcessor();
	}

	@Override
	public IOclResourcePostProcessor getResourcePostProcessor() {
		return oclResourcePostProcessor;
	}

}
