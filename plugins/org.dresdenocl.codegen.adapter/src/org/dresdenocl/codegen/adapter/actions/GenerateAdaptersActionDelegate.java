package org.dresdenocl.codegen.adapter.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import org.dresdenocl.codegen.adapter.PivotAdapterGeneratorPlugin;
import org.dresdenocl.codegen.adapter.PivotAdapterGeneratorUtil;

/**
 * ActionDelegate for the popup menu point "Generate Pivot Model adapters".
 * 
 * @author Michael Thiele
 * 
 */
public class GenerateAdaptersActionDelegate implements IObjectActionDelegate {

	private IStructuredSelection selection;

	private IWorkbenchPart targetPart;

	private static final String PIVOTADAPTER_PROJECT_TYPE = "org.dresdenocl.codegen.generator.PivotAdapter";

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	public void run(IAction action) {
		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			// This is the method that gets invoked when the operation runs.
			@Override
			protected void execute(IProgressMonitor progressMonitor)
					throws CoreException {
				if (selection != null) {
					progressMonitor.beginTask("", 1);

					// reset the GeneratorUtils -> caches are refreshed
					PivotAdapterGeneratorUtil.reset();

					org.eclipse.emf.codegen.ecore.generator.Generator gen = new org.eclipse.emf.codegen.ecore.generator.Generator();
					GenModel genModel = (GenModel) selection.getFirstElement();
					gen.setInput(genModel);

					try {
						BasicDiagnostic diagnostic = new BasicDiagnostic(
								PivotAdapterGeneratorPlugin.ID, 0,
								"generating Pivot Model adapters", null);
						// actual code generation is called here
						diagnostic.add(gen.generate(genModel, PIVOTADAPTER_PROJECT_TYPE,
								BasicMonitor.toMonitor(new SubProgressMonitor(progressMonitor,
										1))));

						if (diagnostic.getSeverity() != Diagnostic.OK) {
							final Diagnostic finalDiagnostic = diagnostic;
							targetPart.getSite().getShell().getDisplay().asyncExec(
									new Runnable() {
										public void run() {
											DiagnosticDialog.openProblem(targetPart.getSite()
													.getShell(), PivotAdapterGeneratorPlugin.INSTANCE
													.getString("_UI_GenerationProblems_title"),
													PivotAdapterGeneratorPlugin.INSTANCE
															.getString("_UI_GenerationProblems_message"),
													finalDiagnostic);
										}
									});

						}
					} catch (Exception exception) {
						PivotAdapterGeneratorPlugin.INSTANCE.log(exception);
					}

					progressMonitor.done();
				}
			}
		};

		// This runs the options, and shows progress.
		// (It appears to be a bad thing to fork this onto another thread.)
		try {
			new ProgressMonitorDialog(targetPart.getSite().getShell()).run(true,
					true, operation);
		} catch (Exception exception) {
			// Something went wrong that shouldn't.
			PivotAdapterGeneratorPlugin.INSTANCE.log(exception);
		}

		if (selection != null) {

		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection)
			this.selection = (IStructuredSelection) selection;
	}

}
