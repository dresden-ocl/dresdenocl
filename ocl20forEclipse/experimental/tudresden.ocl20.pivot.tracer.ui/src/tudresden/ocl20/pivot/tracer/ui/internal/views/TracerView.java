package tudresden.ocl20.pivot.tracer.ui.internal.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import tudresden.ocl20.pivot.tracer.TracerPlugin;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.ui.internal.views.util.TracerContentProvider;
import tudresden.ocl20.pivot.tracer.ui.internal.views.util.TracerLabelProvider;

public class TracerView extends ViewPart {
	
	private TreeViewer myTreeViewer;
	private TracerItem root;
	private Action refreshAction;
	
	public TracerView() {
		super();
		root = TracerPlugin.getInterpreterTraceListener().getTree();
	}
	
	public void dispose() {
		super.dispose();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		myTreeViewer = new TreeViewer(parent);
		myTreeViewer.setContentProvider(new TracerContentProvider());
		myTreeViewer.setLabelProvider(new TracerLabelProvider());
		createRefreshMenu();
		myTreeViewer.setInput(root);
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
		root = TracerPlugin.getInterpreterTraceListener().getTree();
		myTreeViewer.setInput(root);
		IMenuManager mgr = getViewSite().getActionBars().getMenuManager();
        mgr.add(refreshAction);
	}
}
