package tudresden.ocl20.pivot.tracer.ui.internal.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import tudresden.ocl20.pivot.tracer.TracerPlugin;
import tudresden.ocl20.pivot.tracer.model.TracerTree;
import tudresden.ocl20.pivot.tracer.ui.internal.views.util.TracerContentProvider;
import tudresden.ocl20.pivot.tracer.ui.internal.views.util.TracerLabelProvider;

public class TracerView extends ViewPart {
	
	private TreeViewer myTreeViewer;
	private TracerTree tree;
	private Action refreshAction;
	
	public TracerView() {
		super();
		tree = TracerPlugin.getInterpreterTraceListener().getTree();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		myTreeViewer = new TreeViewer(parent);
		myTreeViewer.setContentProvider(new TracerContentProvider());
		myTreeViewer.setLabelProvider(new TracerLabelProvider());
		createRefreshMenu();
		myTreeViewer.setInput((tree == null) ? null : tree.getRootElement());
	}

	@Override
	public void setFocus() {
		myTreeViewer.getControl().setFocus();
	}
	
	private void createRefreshMenu() {
		refreshAction = new Action("Refresh the View") {
            public void run() {
            	myTreeViewer.refresh();
            }
		};
		
		IMenuManager mgr = getViewSite().getActionBars().getMenuManager();
        mgr.add(refreshAction);
	}
}
