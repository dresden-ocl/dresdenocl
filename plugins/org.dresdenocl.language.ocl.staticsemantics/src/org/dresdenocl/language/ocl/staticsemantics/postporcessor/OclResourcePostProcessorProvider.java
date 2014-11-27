package org.dresdenocl.language.ocl.staticsemantics.postporcessor;

import org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessor;
import org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessorProvider;

public class OclResourcePostProcessorProvider implements
		IOclResourcePostProcessorProvider {

	private IOclResourcePostProcessor oclResourcePostProcessor;

	public OclResourcePostProcessorProvider() {
		oclResourcePostProcessor = new OclStaticSemanticsPostProcessor();
	}

	public IOclResourcePostProcessor getResourcePostProcessor() {
		return oclResourcePostProcessor;
	}

}
