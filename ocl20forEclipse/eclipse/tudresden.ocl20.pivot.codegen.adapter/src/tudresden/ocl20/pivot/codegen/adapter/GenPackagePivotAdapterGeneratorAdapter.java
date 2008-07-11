package tudresden.ocl20.pivot.codegen.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;

/**
 * Just overrides the method
 * <code>getGenerateChildren(Object object, Object projectType)</code> that
 * the <code>Generator</code> can operate on child elements of packages.
 * 
 * @author Michael Thiele
 * 
 */
public class GenPackagePivotAdapterGeneratorAdapter extends
		GenBaseGeneratorAdapter {

	public GenPackagePivotAdapterGeneratorAdapter(
			PivotAdapterGeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	@Override
	public Collection<?> getGenerateChildren(Object object, Object projectType) {
		GenPackage genPackage = (GenPackage) object;
		List<GenBase> result = new ArrayList<GenBase>(genPackage.getGenClasses());
		result.addAll(genPackage.getNestedGenPackages());
		return result;
	}

}
