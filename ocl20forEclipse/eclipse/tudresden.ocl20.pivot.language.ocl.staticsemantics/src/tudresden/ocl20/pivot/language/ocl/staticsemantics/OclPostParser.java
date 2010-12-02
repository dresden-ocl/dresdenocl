package tudresden.ocl20.pivot.language.ocl.staticsemantics;

import java.util.List;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor.OclStaticSemanticsProvider;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

public class OclPostParser {

	public static List<Constraint> concreteSyntaxToEssentialOcl(
			OclResource resource) throws OclStaticSemanticsException {
		tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics staticSemantics = OclStaticSemanticsProvider
				.getStaticSemantics(resource);
		List<Constraint> constraints;
			constraints = staticSemantics.cs2EssentialOcl(resource.getContents().get(
					0));
		return constraints;
	}

}
