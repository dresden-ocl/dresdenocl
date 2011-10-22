package tudresden.ocl20.pivot.tracer.ui.internal.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

import tudresden.ocl20.pivot.tracer.TracerPlugin;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.tracermodel.provider.TracermodelItemProviderAdapterFactory;
import tudresden.ocl20.pivot.tracer.ui.internal.views.util.TracerItemAdapterFactoryContentProvider;
import tudresden.ocl20.pivot.tracer.ui.internal.views.util.TracerItemAdapterFactoryLabelProvider;

public class TracerView extends ViewPart {
	
	private TreeViewer myTreeViewer;
	private TracerRoot root;
	private Action refreshAction;
	private ComposedAdapterFactory myAdapterFactory;
	
	@SuppressWarnings("unchecked")
	public TracerView() {
		super();
		List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
		Collections.addAll(factories, 
			new TracermodelItemProviderAdapterFactory(),
			new ReflectiveItemProviderAdapterFactory(),
			new ResourceItemProviderAdapterFactory());
		
		myAdapterFactory = new ComposedAdapterFactory(factories);
		root = TracerPlugin.getInterpreterTraceListener().getTree();
	}
	
	public void dispose() {
		myAdapterFactory.dispose();
		TracerPlugin.disposeInterpreterTraceListener();
		super.dispose();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		myTreeViewer = new TreeViewer(parent, SWT.MULTI |SWT.H_SCROLL| SWT.V_SCROLL);
		
		myTreeViewer.setContentProvider(
				new TracerItemAdapterFactoryContentProvider(myAdapterFactory));
		myTreeViewer.setLabelProvider(
				new TracerItemAdapterFactoryLabelProvider(myAdapterFactory));
		
		createRefreshMenu();
		myTreeViewer.setInput(root);
		myTreeViewer.expandAll();
	}

	@Override
	public void setFocus() {
		myTreeViewer.getControl().setFocus();
	}
	
	private void createRefreshMenu() {
		refreshAction = new Action("Refresh the view") {
            public void run() {
            	root = TracerPlugin.getInterpreterTraceListener().getTree();
            	myTreeViewer.setInput(root);
            	myTreeViewer.expandAll();
            }
		};
		IMenuManager mgr = getViewSite().getActionBars().getMenuManager();
        mgr.add(refreshAction);
	}
	
	public void setInputSource(TracerItem source) {
		myTreeViewer.setInput(source);
		myTreeViewer.expandAll();
	}
}
