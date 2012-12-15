/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.ui;

public class OclOutlinePageAutoExpandAction extends org.dresdenocl.language.ocl.resource.ocl.ui.AbstractOclOutlinePageAction {
	
	public OclOutlinePageAutoExpandAction(org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Auto expand", org.eclipse.jface.action.IAction.AS_CHECK_BOX);
		initialize("icons/auto_expand_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewer().setAutoExpand(on);
		getTreeViewer().refresh();
	}
	
}
