package org.dresdenocl.interpreter.ui.command.handler;

import java.util.HashSet;
import java.util.Set;

import org.dresdenocl.interpreter.ui.InterpreterUIPlugin;
import org.dresdenocl.interpreter.ui.internal.views.InterpreterView;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Namespace;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractInterpreterViewHandler extends AbstractHandler {

	protected InterpreterView m_view;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		if (this.m_view == null) {
			this.m_view =
					(InterpreterView) PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage()
							.findView(InterpreterUIPlugin.INTERPRETER_VIEW_ID);
		}
		return null;
	}

	/**
	 * <p>
	 * Returns all {@link Constraint}s contained in a given {@link IModel}.
	 * 
	 * @param aModel
	 *          The {@link IModel} which {@link Constraint}s shall be returned.
	 * @return A {@link Set} of {@link Constraint}s.
	 */
	protected Set<Constraint> getAllConstraintsForModel(IModel aModel) {

		Set<Constraint> result;
		Namespace rootNamespace;

		try {
			rootNamespace = aModel.getRootNamespace();
			result = new HashSet<Constraint>(rootNamespace.getOwnedAndNestedRules());
		} catch (ModelAccessException e) {
			result = null;
		}
		return result;
	}
}
