package tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor;

import java.util.IdentityHashMap;
import java.util.Map;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource;

public class OclStaticSemanticsProvider {

	private static Map<OclResource, tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics> oclStaticSemantics = new IdentityHashMap<OclResource, tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics>();
	
	public static tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics getStaticSemantics(OclResource resource) {
		if (!oclStaticSemantics.containsKey(resource)) {
			oclStaticSemantics.put(resource, tudresden.ocl20.pivot.language.ocl.staticsemantics.StaticSemanticsFactory.createOclStaticSemantics(resource));
		}
		return oclStaticSemantics.get(resource);
	}
	
}
