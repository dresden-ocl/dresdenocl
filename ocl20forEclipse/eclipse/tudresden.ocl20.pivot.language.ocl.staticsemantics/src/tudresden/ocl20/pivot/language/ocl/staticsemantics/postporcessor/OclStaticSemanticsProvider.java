package tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor;

import java.util.IdentityHashMap;
import java.util.Map;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource;
import tudresden.ocl20.pivot.model.IModel;

public class OclStaticSemanticsProvider {

	private static Map<OclResource, tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics> oclStaticSemantics = new IdentityHashMap<OclResource, tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics>();
	
	public static tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics getStaticSemantics(IModel model, OclLibrary oclLibrary, OclResource resource) {
		if (!oclStaticSemantics.containsKey(resource))
			oclStaticSemantics.put(resource, tudresden.ocl20.pivot.language.ocl.staticsemantics.StaticSemanticsFactory.createOclStaticSemantics(model, oclLibrary, resource));
		return oclStaticSemantics.get(resource);
	}
	
}
