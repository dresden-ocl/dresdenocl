package tudresden.ocl20.pivot.language.ocl.staticsemantics.impl;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.IOclResource;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.factory.OclStaticSemanticsFactory;

public class OclStaticSemanticsFactoryImpl implements OclStaticSemanticsFactory {

	public OclStaticSemanticsFactoryImpl() {
		
	}
	
	public OclStaticSemantics createOclStaticSemantics(IOclResource _resource) {
		return OclStaticSemanticsFactoryImplHelper.createOclStaticSemantics(_resource);
	}
	
}
