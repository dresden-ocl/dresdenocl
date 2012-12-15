package tudresden.ocl20.pivot.tools.codegen.ui.impl.wizards;

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;

import tudresden.ocl20.pivot.pivotmodel.Constraint;

public abstract class ConstraintViewPage extends WizardPage {

	protected ConstraintViewPage(String pageName) {

		super(pageName);
		// TODO Auto-generated constructor stub
	}

	public abstract void updateConstraintViewer(List<Constraint> newConstraints);

}
