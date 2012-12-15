/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.ui;

public class OclOutlinePageLexicalSortingAction extends org.dresdenocl.language.ocl.resource.ocl.ui.AbstractOclOutlinePageAction {
	
	public OclOutlinePageLexicalSortingAction(org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Sort alphabetically", org.eclipse.jface.action.IAction.AS_CHECK_BOX);
		initialize("icons/sort_lexically_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewerComparator().setSortLexically(on);
		getTreeViewer().refresh();
	}
	
}
