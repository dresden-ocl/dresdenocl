/*
Copyright (C) 2011 by Lars Schütze (lschuetze@gmx.net)

This file is part of the OCL 2 Interpreter of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.tracer.ui.internal.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import tudresden.ocl20.pivot.tracer.TracerPlugin;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.tracermodel.provider.TracermodelItemProviderAdapterFactory;
import tudresden.ocl20.pivot.tracer.ui.TracerUIPlugin;
import tudresden.ocl20.pivot.tracer.ui.actions.TracerViewMenuAction;
import tudresden.ocl20.pivot.tracer.ui.actions.TracerViewMenuActionType;
import tudresden.ocl20.pivot.tracer.ui.internal.views.util.TracerItemAdapterFactoryContentProvider;
import tudresden.ocl20.pivot.tracer.ui.internal.views.util.TracerItemAdapterFactoryLabelProvider;

/**
 * @author Lars Schütze
 * 
 */
public class TracerView extends ViewPart {
    /** Icon to clear the view. */
    public static String CLEAR_IMAGE = "icons/remove.gif";
    
    /** Icon to refresh the view. */
    public static String REFRESH_IMAGE = "icons/refresh.gif";

    /** The {@link TreeViewer} for this {@link TracerView}. */
    private TreeViewer myTreeViewer;

    /** The {@link TracerRoot} of the tree. */
    private TracerRoot root;

    /** The {@link ComposedAdapterFactory} of the {@link tracer}. */
    private ComposedAdapterFactory myAdapterFactory;

    /** The {@link IMenuManager} of this {@link TracerView}. */
    private IToolBarManager myToolBarManager;
    
    /**
     * 
     */
    //TracerRootListener myRootListener;

    /**
     * <p>
     * Instantiates this view.
     * </p>
     */
    @SuppressWarnings("unchecked")
    public TracerView() {
	super();

	List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
	Collections.addAll(factories,
		new TracermodelItemProviderAdapterFactory(),
		new ReflectiveItemProviderAdapterFactory(),
		new ResourceItemProviderAdapterFactory());

	myAdapterFactory = new ComposedAdapterFactory(factories);
	root = getRoot();
    }

    public void dispose() {
	myAdapterFactory.dispose();
	TracerPlugin.disposeInterpreterTraceListener();
	super.dispose();
    }

    @Override
    public void createPartControl(Composite parent) {

	myTreeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
		| SWT.V_SCROLL);
	myTreeViewer
		.setContentProvider(new TracerItemAdapterFactoryContentProvider(
			myAdapterFactory));
	myTreeViewer
		.setLabelProvider(new TracerItemAdapterFactoryLabelProvider(
			myAdapterFactory));
	
	root = getRoot();
	myTreeViewer.setInput(root);

	this.initMenu();
	/*
	 * Try to initialize the listeners so that the view is updating/refreshing
	 * automatically by changes to the model
	this.initListeners();
	*/
    }
    /*
    private void initListeners() {
	myRootListener = new TracerRootListener();
	getRoot().eAdapters().add(myRootListener);
    }
    */

    private TracerRoot getRoot() {
	return TracerPlugin.getInterpreterTraceListener().getTree();
    }

    /**
     * <p>
     * Creates the menu of this {@link TracerView}.
     * </p>
     */
    private void initMenu() {
	TracerViewMenuAction clearAllTracedElements;
	TracerViewMenuAction refreshTheView;
	
	/*
	 * --- TOOLBAR 
	 */
	myToolBarManager = this.getToolBarManager();
	
	/* Add the refresh button to the toolbar */
	{
	    refreshTheView = new TracerViewMenuAction(
		    TracerViewMenuActionType.TRACER_VIEW_REFRESH, this);
	    
	    refreshTheView.setImageDescriptor(TracerUIPlugin
		    .getImageDescriptor(REFRESH_IMAGE));
	    
	    myToolBarManager.add(refreshTheView);
	}
	
	/* Add the clear all traced elements button to the toolbar */
	{
	    clearAllTracedElements = new TracerViewMenuAction(
		    TracerViewMenuActionType.CLEAR_ALL_ELEMTENTS, this);

	    clearAllTracedElements.setImageDescriptor(TracerUIPlugin
		    .getImageDescriptor(CLEAR_IMAGE));

	    myToolBarManager.add(clearAllTracedElements);
	}
    }

    private IToolBarManager getToolBarManager() {
	if (myToolBarManager == null) {
	    myToolBarManager = this.getViewSite().getActionBars()
		    .getToolBarManager();
	}
	return myToolBarManager;
    }

    @Override
    public void setFocus() {
	myTreeViewer.getControl().setFocus();
    }

    public TracerView getTracerView() {
	return this;
    }

    public void clearTracerView() {
	myTreeViewer.setInput(null);
    }
    /*
    private final class TracerRootListener extends AdapterImpl {
	@Override
	public void notifyChanged(Notification msg) {
	    if(msg.getFeature().equals(
		   TracermodelPackage.eINSTANCE.getTracerRoot_RootItems())) {
		myTreeViewer.setInput(getRoot());
	    }
	    super.notifyChanged(msg);
	}
    }
    */
    
    /**
     * <p>
     * This method will just be here until the tree viewer can refresh
     * itself automatically
     * </p>
     */
    public void refreshTreeView() {
	myTreeViewer.setInput(this.getRoot());
    }
}
