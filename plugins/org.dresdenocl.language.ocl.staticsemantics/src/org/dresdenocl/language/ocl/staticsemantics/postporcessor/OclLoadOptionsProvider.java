package org.dresdenocl.language.ocl.staticsemantics.postporcessor;

import java.util.HashMap;
import java.util.Map;

import org.dresdenocl.language.ocl.resource.ocl.IOclOptionProvider;
import org.dresdenocl.language.ocl.resource.ocl.IOclOptions;
import org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveHelper;
import org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessorProvider;

public class OclLoadOptionsProvider implements IOclOptionProvider {

	private IOclResourcePostProcessorProvider oclResourcePostProcessorProvider;
	private IOclReferenceResolveHelper oclReferenceResolveHelper;
	
	public OclLoadOptionsProvider() {
		oclResourcePostProcessorProvider = new OclResourcePostProcessorProvider();
		oclReferenceResolveHelper = new OclReferenceResolveHelper();
	}

	public Map<?, ?> getOptions() {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put(IOclOptions.RESOURCE_POSTPROCESSOR_PROVIDER, oclResourcePostProcessorProvider);
		result.put("ReferenceResolveHelper", oclReferenceResolveHelper);
		return result;
	}

}
