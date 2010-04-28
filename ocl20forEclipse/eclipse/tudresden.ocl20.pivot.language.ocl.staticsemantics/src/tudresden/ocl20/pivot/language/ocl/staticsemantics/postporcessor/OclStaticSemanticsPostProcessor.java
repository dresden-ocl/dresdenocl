package tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor;

import java.util.List;

import kiama.attribution.Attributable;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.provider.IOclLibraryProvider;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessor;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemanticsException;
import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.IModelProvider;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

public class OclStaticSemanticsPostProcessor implements
		IOclResourcePostProcessor {

	@Override
	public void process(OclResource resource) {
		EList<EObject> contents = resource.getContents();
		if (!contents.isEmpty()) {
			EObject root = contents.get(0);

			try {
				IMetamodel metaModel = ModelBusPlugin.getMetamodelRegistry()
						.getMetamodel(UML2MetamodelPlugin.ID);
				IModelProvider modelProvider = metaModel.getModelProvider();
				IModel model = ModelBusPlugin.getModelRegistry().getActiveModel();
				if (model == null) {
					resource.addError("No active model", root);
					throw new ModelAccessException("No active model");
				}
				// modelProvider
				// .getModel(new File(
				// "/Users/mt/Documents/workspace/dresden-ocl_trunk/tudresden.ocl20.pivot.examples.simple/model/simple.uml"));

				IOclLibraryProvider oclLibraryProvider = EssentialOclPlugin
						.getOclLibraryProvider();
				OclLibrary oclLibrary = oclLibraryProvider.getOclLibrary();

				tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics oclStaticSemantics = OclStaticSemanticsProvider
						.getStaticSemantics(model, oclLibrary, resource);

				List<Constraint> result = oclStaticSemantics.cs2EssentialOcl(root);

				printResult(result);

			} catch (ModelAccessException e) {
				e.printStackTrace();
			} catch (OclStaticSemanticsException e) {
				e.printStackTrace();
			}
		}

	}

	private void printResult(List<Constraint> result) {
		System.out.println();
		for (Constraint constraint : result) {
			printConstraint(constraint);
		}
	}

	private void printConstraint(Constraint constraint) {
		System.out.print(constraint.getConstrainedElement() + "   ");
		System.out.print(constraint.getName());
		System.out.println("(" + constraint.getKind() + ")");
		printExpression((ExpressionInOcl) constraint.getSpecification());
	}

	private void printExpression(ExpressionInOcl specification) {
		printBodyExpression(specification.getBodyExpression());
	}

	private void printBodyExpression(OclExpression bodyExpression) {
		System.out.println(bodyExpression);
	}

}
