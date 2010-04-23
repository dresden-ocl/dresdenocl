package tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor;

import java.util.HashMap;
import java.util.Map;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclCodeCompletion;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptionProvider;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptions;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessorProvider;

public class OclLoadOptionsProvider implements IOclOptionProvider {

	private IOclResourcePostProcessorProvider oclResourcePostProcessorProvider;
	private IOclCodeCompletion oclCodeCompletion;
	
	public OclLoadOptionsProvider() {
		oclResourcePostProcessorProvider = new OclResourcePostProcessorProvider();
		oclCodeCompletion = new OclCodeCompletion();
	}

	@Override
	public Map<?, ?> getOptions() {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put(IOclOptions.RESOURCE_POSTPROCESSOR_PROVIDER, oclResourcePostProcessorProvider);
		result.put("codeCompletion", oclCodeCompletion);
		return result;
	}

}
