package tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessor;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemanticsException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

public class OclStaticSemanticsPostProcessor implements
		IOclResourcePostProcessor {

	public void process(OclResource resource) {
		EList<EObject> contents = resource.getContents();
		if (!contents.isEmpty()) {
			EObject root = contents.get(0);

			try {

				tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics oclStaticSemantics = OclStaticSemanticsProvider
						.getStaticSemantics(resource);

				List<Constraint> result = oclStaticSemantics
						.cs2EssentialOcl(root);

				/*
				 * Probably notify listeners of the model that constraints have
				 * been reparsed.
				 */
				if (result.size() > 0) {
					if (ModelBusPlugin.getModelRegistry().getActiveModel() != null) {
						ModelBusPlugin.getModelRegistry().getActiveModel()
								.setChanged();
						ModelBusPlugin.getModelRegistry().getActiveModel()
								.notifiyListeners();
					}
					// no else.
				}
				// no else.

				// printResult(result);

			} catch (OclStaticSemanticsException e) {
				// e.printStackTrace();
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
		System.out.println(specification.getBody());
	}

	private void printBodyExpression(OclExpression bodyExpression) {
		System.out.println(bodyExpression);
	}

	@Override
	public void terminate() {
		// left empty		
	}

}
