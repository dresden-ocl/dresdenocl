package org.dresdenocl.language.ocl.staticsemantics.impl;

import org.dresdenocl.language.ocl.resource.ocl.mopp.IOclResource;
import org.dresdenocl.language.ocl.staticsemantics.OclStaticSemantics;
import org.dresdenocl.language.ocl.staticsemantics.factory.OclStaticSemanticsFactory;

public class OclStaticSemanticsFactoryImpl implements OclStaticSemanticsFactory {

	public OclStaticSemanticsFactoryImpl() {
		
	}
	
	public OclStaticSemantics createOclStaticSemantics(IOclResource _resource) {
		return OclStaticSemanticsFactoryImplHelper.createOclStaticSemantics(_resource);
	}
	
}
