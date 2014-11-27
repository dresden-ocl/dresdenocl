/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.ui;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.action.IAction;

public class OclOutlinePageActionProvider {
	
	public List<IAction> getActions(org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewer treeViewer) {
		// To add custom actions to the outline view, set the
		// 'overrideOutlinePageActionProvider' option to <code>false</code> and modify
		// this method.
		List<IAction> defaultActions = new ArrayList<IAction>();
		defaultActions.add(new org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageLinkWithEditorAction(treeViewer));
		defaultActions.add(new org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageCollapseAllAction(treeViewer));
		defaultActions.add(new org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageExpandAllAction(treeViewer));
		defaultActions.add(new org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageAutoExpandAction(treeViewer));
		defaultActions.add(new org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageLexicalSortingAction(treeViewer));
		defaultActions.add(new org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageTypeSortingAction(treeViewer));
		return defaultActions;
	}
	
}
