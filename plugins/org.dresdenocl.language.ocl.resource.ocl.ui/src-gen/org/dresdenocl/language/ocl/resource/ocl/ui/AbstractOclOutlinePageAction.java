/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.ui;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;

public abstract class AbstractOclOutlinePageAction extends Action {
	
	private String preferenceKey = this.getClass().getSimpleName() + ".isChecked";
	
	private org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewer treeViewer;
	
	public AbstractOclOutlinePageAction(org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewer treeViewer, String text, int style) {
		super(text, style);
		this.treeViewer = treeViewer;
	}
	
	public void initialize(String imagePath) {
		ImageDescriptor descriptor = org.dresdenocl.language.ocl.resource.ocl.ui.OclImageProvider.INSTANCE.getImageDescriptor(imagePath);
		setDisabledImageDescriptor(descriptor);
		setImageDescriptor(descriptor);
		setHoverImageDescriptor(descriptor);
		boolean checked = org.dresdenocl.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore().getBoolean(preferenceKey);
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
		BusyIndicator.showWhile(Display.getCurrent(), new Runnable() {
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
			org.dresdenocl.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore().setValue(preferenceKey, on);
		}
	}
	
	public boolean keepState() {
		return true;
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewer getTreeViewer() {
		return treeViewer;
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewerComparator getTreeViewerComparator() {
		return (org.dresdenocl.language.ocl.resource.ocl.ui.OclOutlinePageTreeViewerComparator) treeViewer.getComparator();
	}
	
}
