/*
 * Created on 02.11.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.eclipse.plugins.ocleditor.views;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.IOCLEditorExtensionPoint;
import tudresden.ocl20.eclipse.plugins.ocleditor.views.highlighting.OCLScanner;
import tudresden.ocl20.integration.OCLChecker;

/**
 * This class represents the OCL editor.
 * @author Mirko
 *
 * 
 */
public class OCLEditor extends ViewPart implements ISelectionChangedListener, ISelectionListener 
{	
	private IOCLEditorExtensionPoint actExtension = null;
	private ArrayList extensions = null;	
	private Object selectedModelElement = null; 
	private Object selectedConstraint = null;

	private Composite constraintsViewerComp = null;
	private TableViewer constraintsViewer = null;
	private Table table = null;

	private Composite constraintEditorComp = null;
	private SourceViewer constraintEditor = null;	
	
	private IMarker problem = null;	
	
	class ConstraintsViewerLabelProvider extends LabelProvider implements ITableLabelProvider 
	{
		public Image getColumnImage(Object element, int columnIndex) 
		{
			return null;
		}

		public String getColumnText(Object element, int columnIndex) 
		{
			if (columnIndex == 0)
				return String.valueOf(getRowNumber(element));
			return String.valueOf(actExtension.getText(element));
		}

		public void addListener(ILabelProviderListener listener) 
		{}

		public void dispose() 
		{}

		public boolean isLabelProperty(Object element, String property) 
		{
			return false;
		}

		public void removeListener(ILabelProviderListener listener) 
		{}
		
		private int getRowNumber(Object element)
		{
			Object[] constraints = actExtension.getOCLConstraints(selectedModelElement);
			
			for (int i = 0; i < constraints.length; i++)
				if (constraints[i].equals(element))
					return i+1;
			return 0;
		}
	}
	
	class ConstraintsViewerContentProvider implements IStructuredContentProvider 
	{
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) 
		{}

		public Object[] getElements(Object parent) 
		{
			if (parent != null && actExtension != null)
				return actExtension.getOCLConstraints(parent);
			else
				return (new ArrayList()).toArray();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
			// TODO Auto-generated method stub
			
		}
	}
	
	public void createPartControl(Composite parent) 
	{
		Composite main = new Composite(parent, SWT.NONE);
		FormLayout layout = new FormLayout();
		main.setLayout(layout);
		main.setFont(parent.getFont());
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 0);
		formData.right = new FormAttachment(100, 0);
		formData.top = new FormAttachment(0, 0);
		formData.bottom = new FormAttachment(100, 0);

		this.constraintsViewerComp = new Composite(main, SWT.NONE);
		this.constraintsViewerComp.setLayout(new FillLayout());
		this.constraintsViewerComp.setLayoutData(formData);
		this.constraintsViewer = new TableViewer(this.constraintsViewerComp,
				SWT.FULL_SELECTION);
		table = constraintsViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableLayout tableLayout = new TableLayout();
		ColumnWeightData weightData = new ColumnWeightData(5, true);
		tableLayout.addColumnData(weightData);
		weightData = new ColumnWeightData(95, true);		
		tableLayout.addColumnData(weightData);
		table.setLayout(tableLayout);
		
		TableColumn column = new TableColumn(table, SWT.CENTER);		
		column.setText ("");
		column = new TableColumn(table, SWT.LEFT);		
		column.setText ("Constraint");
		
		this.constraintsViewer.addSelectionChangedListener(this);
		this.constraintsViewer
				.setContentProvider(new ConstraintsViewerContentProvider());
		this.constraintsViewer
				.setLabelProvider(new ConstraintsViewerLabelProvider());
		this.constraintsViewer.setInput(null);

		this.constraintEditorComp = new Composite(main, SWT.NONE);
		this.constraintEditorComp.setLayout(new FillLayout());
		this.constraintEditorComp.setLayoutData(formData);
		constraintEditor = new SourceViewer(this.constraintEditorComp,
				new VerticalRuler(10), SWT.NONE);

		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(
				new OCLScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.install(this.constraintEditor);
		constraintEditor.setDocument(new Document());

		this.constraintEditorComp.setVisible(false);

		this.getSite().getPage().addSelectionListener(this);		

		this.searchExtensions();		
		System.setProperty("tudresden.ocl20.eclipse.ocleditor.state", "1");
		this.initMDR();
	}

	public void selectionChanged(IWorkbenchPart part, ISelection sel) 
	{
		if (this.problem == null)
		{
			boolean found = false;
			Iterator it = this.extensions.iterator();		
			while (it.hasNext() && !found) 
			{
				Object obj = it.next();
				if (((IOCLEditorExtensionPoint) obj).isValidSelection(sel) != null)
				{
					if (this.actExtension == null || !this.actExtension.equals(obj))
					{
						this.selectedModelElement = ((IOCLEditorExtensionPoint) obj).isValidSelection(sel);
						this.actExtension = (IOCLEditorExtensionPoint) obj;
						this.constraintsViewer.setInput(this.selectedModelElement);
						this.actExtension.setOCLEditor(this);
						this.setGUIState(1);
						found = true;
					}
				}			
			}
			if (!found)
			{
				if (!this.constraintEditorComp.isVisible())
				{
					this.actExtension = null;
					this.setGUIState(0);
				}			
			}
		}
	}

	public void selectionChanged(SelectionChangedEvent event) 
	{
		this.setGUIState(3);
	}

	/**
	 * @see   org.eclipse.ui.IWorkbenchPart#setFocus()
	 */
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	/**
	 * Called if the new button was clicked. Opens the sourceviewer.
	 */
	public void newEvent() 
	{
		constraintEditor.getDocument().set("");
		this.setGUIState(2);
    	
	}

	/**
	 * Called if the delete button was clicked. Deletes the selected constraint.
	 */
	public void deleteEvent() 
	{
		int sel = constraintsViewer.getTable().getSelectionIndex();
    	Object selectedConstraint = actExtension.getOCLConstraints(selectedModelElement)[sel];
    	actExtension.deleteOCLConstraint(selectedModelElement, selectedConstraint);    	
	}

	/**
	 * Called if the edit button was clicked. Opens the sourceviewer for the selected constraint.
	 */
	public void editEvent() 
	{
		IStructuredSelection sel = (IStructuredSelection) constraintsViewer.getSelection();
    	selectedConstraint = sel.getFirstElement();
    	constraintEditor.getDocument().set(actExtension.getText(selectedConstraint));
    	this.setGUIState(2);
	}

	/**
	 * Called if the validate button was clicked. Validates the constraint.
	 */
	public void editConstraintEvent() 
	{
		OCLChecker checker = OCLChecker.getInstance(this.actExtension.getTopPackage());
		
		try 
		{
			if (this.problem != null)
			{
				this.problem.delete();
				this.problem = null;
			}
			checker.setModelFacade(this.actExtension.getModelFacade());
			checker.validate(constraintEditor.getDocument().get());
			if (selectedConstraint != null)
				actExtension.editOCLConstraint(selectedConstraint, constraintEditor.getDocument().get());
			else
				actExtension.createOCLConstraint(selectedModelElement, constraintEditor.getDocument().get());
	    	selectedConstraint = null;
	    	this.setGUIState(1);
	    	constraintsViewer.refresh();
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			try 
			{
				problem = ResourcesPlugin.getWorkspace().getRoot().createMarker(IMarker.PROBLEM);
				problem.setAttribute(IMarker.MESSAGE, e.getMessage());
				problem.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
				
				IWorkbenchPart active = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView ("org.eclipse.ui.views.ProblemView");
				if (active != null)
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(active);				
			}
			catch (PartInitException es)
		    {
		         System.out.println(e.getMessage());
		    }
		   	catch (CoreException es) 
			{
		   		System.out.println(e.getMessage());
			}
			
		} 		
	}

	/**
	 * Called if the abort button was clicked. Opens the tableviewer.
	 */
	public void abortEvent() 
	{
		this.setGUIState(0);		
	}
	
	private void initMDR()
	{
		RepositoryManager.getRepository();
	}

	/**
	 * Activates and deactivates the buttons and opens or closes the viewers.
	 */
	private void setGUIState(int state) 
	{		
		System.setProperty("tudresden.ocl20.eclipse.ocleditor.state", "2");
		IToolBarManager toolbar = getViewSite().getActionBars().getToolBarManager();
		ActionContributionItem actionItem = (ActionContributionItem) toolbar.find("tudresden.ocl20.eclipse.ocleditor.new");
		IAction newAction = actionItem.getAction();
		actionItem = (ActionContributionItem) toolbar.find("tudresden.ocl20.eclipse.ocleditor.delete");
		IAction deleteAction = actionItem.getAction();
		actionItem = (ActionContributionItem) toolbar.find("tudresden.ocl20.eclipse.ocleditor.edit");
		IAction editAction = actionItem.getAction();
		actionItem = (ActionContributionItem) toolbar.find("tudresden.ocl20.eclipse.ocleditor.validate");
		IAction validateAction = actionItem.getAction();
		actionItem = (ActionContributionItem) toolbar.find("tudresden.ocl20.eclipse.ocleditor.abort");
		IAction abortAction = actionItem.getAction();
			
		newAction.setEnabled(false);
		deleteAction.setEnabled(false);
		editAction.setEnabled(false);
		validateAction.setEnabled(false);
		abortAction.setEnabled(false);
			
		switch (state)
		{
			case 0:
			{
				this.constraintEditorComp.setVisible(false);
				this.constraintsViewerComp.setVisible(true);
				this.constraintsViewer.getTable().deselectAll();
				this.constraintsViewer.refresh();
				try 
				{
					if (this.problem != null)
						this.problem.delete();
				} 
				catch (CoreException e) 
				{
					
				}
				break;
			}
			case 1:
			{
				newAction.setEnabled(true);
				this.constraintEditorComp.setVisible(false);
				this.constraintsViewerComp.setVisible(true);
				this.constraintsViewer.getTable().deselectAll();
				this.constraintsViewer.refresh();
				try 
				{
					if (this.problem != null)
						this.problem.delete();
				} 
				catch (CoreException e) 
				{
					
				}
				break;
			}			
			case 2:
			{
				validateAction.setEnabled(true);
				abortAction.setEnabled(true);
				this.constraintEditorComp.setVisible(true);
				this.constraintsViewerComp.setVisible(false);				
				break;
			}
			case 3:
			{
				newAction.setEnabled(true);
				deleteAction.setEnabled(true);
				editAction.setEnabled(true);
				break;
			}					
		}
	}
	
	/**
	 * Repaints the editor.
	 */
	public void refresh()
	{
		this.constraintsViewer.refresh();
	}

	/**
	 * Searches for all extensions.
	 */
	private void searchExtensions() {
		this.extensions = new ArrayList();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint point = registry
				.getExtensionPoint(IOCLEditorExtensionPoint.ID);
		IExtension[] extensions = point.getExtensions();
		
		for (int i = 0; i < extensions.length; i++) {
			IConfigurationElement[] elements = extensions[i]
					.getConfigurationElements();
			for (int j = 0; j < elements.length; j++) {
				try {
					Object obj = elements[j].createExecutableExtension("class");
					if (obj instanceof IOCLEditorExtensionPoint) {
						this.extensions.add(obj);
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}

			}
		}

	}
	
	/**
	 * Deletes all markers.
	 */
	public void dispose()
	{
		try 
		{
			if (this.problem != null)
				this.problem.delete();
		} 
		catch (CoreException e) 
		{
			
		}
		super.dispose();
	}

}
