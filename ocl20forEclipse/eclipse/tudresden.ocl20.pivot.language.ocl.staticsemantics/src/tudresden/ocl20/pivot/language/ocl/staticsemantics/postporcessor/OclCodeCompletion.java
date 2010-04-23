package tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.StandardLibraryPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.provider.IOclLibraryProvider;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.PathNameCS;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclCodeCompletion;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclExpectedElement;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclKeyword;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclExpectedCsString;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclExpectedTerminal;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemanticsException;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Type;

public class OclCodeCompletion implements IOclCodeCompletion {

	@Override
	public OclExpectedTerminal[] getExpectedElements(EObject currentElement) {
		if (currentElement instanceof ClassifierContextDeclarationCS) {
			ClassifierContextDeclarationCS classifierContext = (ClassifierContextDeclarationCS) currentElement;
			PathNameCS pathName = classifierContext.getTypeName();
			IModel model = ModelBusPlugin.getModelRegistry().getActiveModel();
			try {
				if (model == null) {
					((OclResource) currentElement.eResource()).addError(
							"No active model", currentElement);
					throw new ModelAccessException("No active model");

				}
				IOclLibraryProvider oclLibraryProvider = StandardLibraryPlugin
						.getOclLibraryProvider();
				OclLibrary oclLibrary = oclLibraryProvider.getOclLibrary();
				tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics oclStaticSemantics = new tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics(
						model, oclLibrary, (OclResource) currentElement.eResource());
				kiama.attribution.Attributable pathNameAttr = oclStaticSemantics
						.eObject2Attributable(pathName);
				try {
					Type type = oclStaticSemantics.computeTypeFor(pathNameAttr);
					String typeName = type.getQualifiedName();
					OclKeyword keyword = new OclKeyword(typeName, OclCardinality.ONE);
					// NPE: since parent is not available
					IOclExpectedElement tName = new OclExpectedCsString(keyword);
					OclExpectedTerminal[] expectedTerminal = new OclExpectedTerminal[] { new OclExpectedTerminal(
							tName, 1) };
					return expectedTerminal;
				} catch (OclStaticSemanticsException e) {
					e.printStackTrace();
				}
			} catch (ModelAccessException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}
