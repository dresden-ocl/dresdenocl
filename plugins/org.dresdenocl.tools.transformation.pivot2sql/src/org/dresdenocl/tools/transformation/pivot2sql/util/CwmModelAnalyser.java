package org.dresdenocl.tools.transformation.pivot2sql.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.dresdenocl.tools.transformation.util.ModelAnalyser;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Namespace;
import orgomg.cwm.objectmodel.core.Package;

public class CwmModelAnalyser extends
		ModelAnalyser<Package, ModelElement, ModelElement> {

	private Set<ModelElement> globalAllModelElements = null;

	public CwmModelAnalyser(Package model) {

		super(model);
	}

	private Set<ModelElement> query_AllModelElements(Package pkg) {

		Set<ModelElement> modelElements = new HashSet<ModelElement>();
		modelElements.addAll(pkg.getOwnedElement());
		for (ModelElement subPackage : pkg.getOwnedElement()) {
			if (subPackage instanceof Package) modelElements.addAll(query_AllModelElements((Package) subPackage));
		}
		return modelElements;
	}

	protected Collection<ModelElement> getAllElements() {

		if (this.globalAllModelElements == null) {
			this.globalAllModelElements = query_AllModelElements(model);
		}
		return this.globalAllModelElements;
	}

	public Set<ModelElement> getAllInstances() {

		Set<ModelElement> instances = new HashSet<ModelElement>();

		for (ModelElement cls : getAllElements()) {
			instances.add(cls);
			if (cls instanceof Namespace)
				instances.addAll(((Namespace) cls).getOwnedElement());
		}
		return instances;
	}

	protected Comparator<ModelElement> createComparator() {

		return new ModelElementComparator();
	}

}
