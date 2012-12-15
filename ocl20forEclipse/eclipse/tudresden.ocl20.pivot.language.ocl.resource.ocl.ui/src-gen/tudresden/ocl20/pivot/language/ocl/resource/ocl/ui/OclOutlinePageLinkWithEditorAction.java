/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

public class OclOutlinePageLinkWithEditorAction extends tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.AbstractOclOutlinePageAction {
	
	public OclOutlinePageLinkWithEditorAction(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Link with Editor", org.eclipse.jface.action.IAction.AS_CHECK_BOX);
		initialize("icons/link_with_editor_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewer().setLinkWithEditor(on);
	}
	
}
