/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

public class OclOutlinePageActionProvider {
	
	public java.util.List<org.eclipse.jface.action.IAction> getActions(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewer treeViewer) {
		// To add custom actions to the outline view, set the
		// 'overrideOutlinePageActionProvider' option to <code>false</code> and modify
		// this method.
		java.util.List<org.eclipse.jface.action.IAction> defaultActions = new java.util.ArrayList<org.eclipse.jface.action.IAction>();
		defaultActions.add(new tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageLinkWithEditorAction(treeViewer));
		defaultActions.add(new tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageCollapseAllAction(treeViewer));
		defaultActions.add(new tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageExpandAllAction(treeViewer));
		defaultActions.add(new tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageAutoExpandAction(treeViewer));
		defaultActions.add(new tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageLexicalSortingAction(treeViewer));
		defaultActions.add(new tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageTypeSortingAction(treeViewer));
		return defaultActions;
	}
	
}
