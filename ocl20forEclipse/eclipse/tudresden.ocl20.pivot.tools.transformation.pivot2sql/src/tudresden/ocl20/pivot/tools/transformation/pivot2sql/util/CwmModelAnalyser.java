package tudresden.ocl20.pivot.tools.transformation.pivot2sql.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Namespace;
import orgomg.cwm.objectmodel.core.Package;

import tudresden.ocl20.pivot.tools.transformation.util.ModelAnalyser;

public class CwmModelAnalyser extends ModelAnalyser<Package, ModelElement> {

	private Set<ModelElement> globalAllModelElements = null;
	
	
	public CwmModelAnalyser(Package model) {
		super(model);
	}
	
	private Set<ModelElement> query_AllModelElements(Package pkg) {
		Set<ModelElement> modelElements = new HashSet<ModelElement>();
		modelElements.addAll(pkg.getOwnedElement());
		for(Package subPackage: pkg.getImporter()) {
			modelElements.addAll(query_AllModelElements(subPackage));
		}
		return modelElements;
	}

	protected Collection<ModelElement> getAllElements() {
		if (this.globalAllModelElements == null) {
			this.globalAllModelElements= query_AllModelElements(model);
		}
		return this.globalAllModelElements;
	}

	public Set<EObject> getAllInstances() {
		Set<EObject> instances = new HashSet<EObject>();
		
		for (ModelElement cls : getAllElements()){
			instances.add(cls);
			if (cls instanceof Namespace) instances.addAll(((Namespace)cls).getOwnedElement());
		}
		return instances;
	}

}
