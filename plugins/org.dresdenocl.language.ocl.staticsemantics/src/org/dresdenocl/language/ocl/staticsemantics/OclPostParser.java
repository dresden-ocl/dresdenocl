package org.dresdenocl.language.ocl.staticsemantics;

import java.util.List;

import org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource;
import org.dresdenocl.language.ocl.staticsemantics.postporcessor.OclStaticSemanticsProvider;
import org.dresdenocl.pivotmodel.Constraint;

public class OclPostParser {

	public static List<Constraint> concreteSyntaxToEssentialOcl(
			OclResource resource) throws OclStaticSemanticsException {
		org.dresdenocl.language.ocl.staticsemantics.OclStaticSemantics staticSemantics = OclStaticSemanticsProvider
				.getStaticSemantics(resource);
		List<Constraint> constraints;
			constraints = staticSemantics.cs2EssentialOcl(resource.getContents().get(
					0));
		return constraints;
	}

}
