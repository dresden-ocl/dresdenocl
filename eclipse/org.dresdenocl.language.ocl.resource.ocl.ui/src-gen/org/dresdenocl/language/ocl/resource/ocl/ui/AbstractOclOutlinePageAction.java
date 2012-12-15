/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

public abstract class AbstractOclOutlinePageAction extends org.eclipse.jface.action.Action {
	
	private String preferenceKey = this.getClass().getSimpleName() + ".isChecked";
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewer treeViewer;
	
	public AbstractOclOutlinePageAction(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewer treeViewer, String text, int style) {
		super(text, style);
		this.treeViewer = treeViewer;
	}
	
	public void initialize(String imagePath) {
		org.eclipse.jface.resource.ImageDescriptor descriptor = tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclImageProvider.INSTANCE.getImageDescriptor(imagePath);
		setDisabledImageDescriptor(descriptor);
		setImageDescriptor(descriptor);
		setHoverImageDescriptor(descriptor);
		boolean checked = tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore().getBoolean(preferenceKey);
		valueChanged(checked, false);
	}
	
	@Override	
	public void run() {
		if (keepState()) {
			valueChanged(isChecked(), true);
		} else {
			runBusy(true);
		}
	}
	
	public void runBusy(final boolean on) {
		org.eclipse.swt.custom.BusyIndicator.showWhile(org.eclipse.swt.widgets.Display.getCurrent(), new Runnable() {
			public void run() {
				runInternal(on);
			}
		});
	}
	
	public abstract void runInternal(boolean on);
	
	private void valueChanged(boolean on, boolean store) {
		setChecked(on);
		runBusy(on);
		if (store) {
			tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore().setValue(preferenceKey, on);
		}
	}
	
	public boolean keepState() {
		return true;
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewer getTreeViewer() {
		return treeViewer;
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewerComparator getTreeViewerComparator() {
		return (tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewerComparator) treeViewer.getComparator();
	}
	
}
