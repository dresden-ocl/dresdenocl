/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.debug.ui.resource;

import org.dresdenocl.debug.OclDebugPlugin;
import org.dresdenocl.debug.model.OclLineBreakpoint;
import org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

public class OclLineBreakpointAdapter implements IToggleBreakpointsTarget {

	public void toggleLineBreakpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {

		ITextEditor textEditor = getEditor(part);
		if (textEditor != null) {
			IResource resource =
					(IResource) textEditor.getEditorInput().getAdapter(IResource.class);
			ITextSelection textSelection = (ITextSelection) selection;
			int lineNumber = textSelection.getStartLine();
			IBreakpoint[] breakpoints =
					DebugPlugin.getDefault().getBreakpointManager()
							.getBreakpoints(OclDebugPlugin.DEBUG_MODEL_ID);
			for (int i = 0; i < breakpoints.length; i++) {
				IBreakpoint breakpoint = breakpoints[i];
				if (resource.equals(breakpoint.getMarker().getResource())) {
					if (((ILineBreakpoint) breakpoint).getLineNumber() == (lineNumber + 1)) {
						// remove
						breakpoint.delete();
						return;
					}
				}
			}
			// create line breakpoint (document line numbers start at 0)
			OclLineBreakpoint lineBreakpoint =
					new OclLineBreakpoint(resource, lineNumber + 1);
			DebugPlugin.getDefault().getBreakpointManager()
					.addBreakpoint(lineBreakpoint);
		}
	}

	public boolean canToggleLineBreakpoints(IWorkbenchPart part,
			ISelection selection) {

		ITextEditor editor = getEditor(part);
		return editor != null;
	}

	/**
	 * Returns the editor being used, associated with the given part, or
	 * <code>null</code> if none.
	 * 
	 * @param part
	 *          workbench part
	 */
	private ITextEditor getEditor(IWorkbenchPart part) {

		if (part instanceof ITextEditor) {
			ITextEditor editorPart = (ITextEditor) part;
			IResource resource =
					(IResource) editorPart.getEditorInput().getAdapter(IResource.class);
			if (resource != null) {
				String extension = resource.getFileExtension();
				if (extension != null
						&& extension.equals(new OclMetaInformation().getSyntaxName())) {
					return editorPart;
				}
			}
		}
		return null;
	}

	public void toggleMethodBreakpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {

	}

	public boolean canToggleMethodBreakpoints(IWorkbenchPart part,
			ISelection selection) {

		return false;
	}

	public void toggleWatchpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {

	}

	public boolean canToggleWatchpoints(IWorkbenchPart part, ISelection selection) {

		return false;
	}

}