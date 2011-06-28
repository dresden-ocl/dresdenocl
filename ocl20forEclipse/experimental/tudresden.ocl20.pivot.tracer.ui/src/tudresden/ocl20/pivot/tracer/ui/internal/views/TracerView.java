package tudresden.ocl20.pivot.tracer.ui.internal.views;

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
	
	public TracerView() {
		super();
		tree = TracerPlugin.getInterpreterTraceListener().getTree();
		/* DEBUG */
		System.out.println("MOEP");
		/* DEBUG */
	}
	
	@Override
	public void createPartControl(Composite parent) {
		myTreeViewer = new TreeViewer(parent);
		myTreeViewer.setContentProvider(new TracerContentProvider());
		myTreeViewer.setLabelProvider(new LabelProvider());
		myTreeViewer.setInput((tree == null) ? null : tree.getRootElement());
	}

	@Override
	public void setFocus() {
		myTreeViewer.getControl().setFocus();
	}	
}
