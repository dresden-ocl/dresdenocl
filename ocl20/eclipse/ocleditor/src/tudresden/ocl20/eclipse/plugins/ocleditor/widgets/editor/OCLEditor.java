package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IActionBars;

import tudresden.ocl20.eclipse.plugins.ocleditor.OCLEditorConst;
import tudresden.ocl20.eclipse.plugins.ocleditor.OCLEditorControler;
import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.IOCLExtension;
import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.management.ExtensionConfiguration;
import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.management.ExtensionManager;
import tudresden.ocl20.eclipse.plugins.ocleditor.utils.OpenProblemsView;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions.AbortAction;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions.CreateConstraintAction;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions.CreateConstraintDropDownAction;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions.DeleteConstraintAction;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions.EditConstraintAction;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions.ParseConstraintAction;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.SelectionEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.IChangeListener;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.AbortEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.AbstractChangeEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.CreateEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.DeletedEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.DisableAllEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.EditEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.ModelChangeEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.ParseDisableEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.ParseEnableEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.ParseEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.model.OCLEditorModel;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.model.OCLEditorModel.TableContentObject;

/**
 * This class implements the main ocl-editor component.
 * @author Mirko Stölzel
 *
 */
public class OCLEditor implements IMenuCreator, IChangeListener, ISelectionChangedListener
{
	private final static int EDIT = 0;
	private final static int VIEW = 1;
	private final static int DISABLE = 2;
	private final static int EDIT_DISABLE = 3;
	private final static int EDIT_ENABLE = 4;
	private static final int EDIT_DISABLE_ALL = 5;
	
	//ocl-editor model
	private OCLEditorModel model = null;
	
	private Composite main;
		
	private Composite constraintsTableComp = null;
	//constraints table viewer
	private TableViewer constraintsTable;
	
	private Composite actionComp;
	
	private Menu menu;
	private MenuManager manager;

	private OCLEditorControler editorControler;
	private CreateConstraintDropDownAction createBtn;
	private DeleteConstraintAction deleteBtn;
	private EditConstraintAction editBtn;
	private ParseConstraintAction parseBtn;
	private AbortAction abortBtn;
	private IOCLExtension lastAction;
	private boolean create;	
	
	private IMarker problem = null;

	/**
	 * Constructor
	 * @param parent
	 * @param editor
	 */
	public OCLEditor(Composite parent, 
				     OCLEditorControler editor) 
	{
		main = new Composite(parent, SWT.NONE);
		main.setLayout(new FormLayout());
		main.setFont(parent.getFont());
		this.editorControler = editor;
		
		this.model = new OCLEditorModel(this);
		this.initConstraintsTable();
				
		this.initToolbar(editor);
		this.initMenuManager();
	}	

	/**
	 * Creates a new composite which is used as parent composite for
	 * the gui component of the actual extension
	 *
	 */
	private void initActionComp() 
	{
		if (this.actionComp != null)
			this.actionComp.dispose();
		this.actionComp = new Composite(main, SWT.NONE);	
		this.actionComp.setLayout(new FillLayout());
		
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 0);
		formData.right = new FormAttachment(100, 0);
		formData.top = new FormAttachment(0, 0);
		formData.bottom = new FormAttachment(100, 0);
		this.actionComp.setLayoutData(formData);
	}
	
	/**
	 * Initialize the constraints table of the ocl-editor
	 *
	 */
	private void initConstraintsTable()
	{
		try
		{
		this.constraintsTableComp = new Composite(main, SWT.NONE);
		this.constraintsTableComp.setLayout(new FillLayout());
		
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 0);
		formData.right = new FormAttachment(100, 0);
		formData.top = new FormAttachment(0, 0);
		formData.bottom = new FormAttachment(100, 0);
		this.constraintsTableComp.setLayoutData(formData);
		this.constraintsTable = new TableViewer(this.constraintsTableComp,
												SWT.FULL_SELECTION);
		Table table = constraintsTable.getTable();
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
		
		this.constraintsTable
				.setContentProvider(this.model);
		this.constraintsTable
				.setLabelProvider(this.model);
		this.constraintsTable.setInput(this.model);

		this.constraintsTable.addSelectionChangedListener(this);		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the toolbar of the ocl-editor.
	 * @param editor
	 */
	private void initToolbar(OCLEditorControler editor) {
		IActionBars bars = editor.getViewSite().getActionBars();
		IToolBarManager manager = bars.getToolBarManager();
	
		this.createBtn = new CreateConstraintDropDownAction(Action.AS_DROP_DOWN_MENU);				
		this.createBtn.setEnabledImage(OCLEditorConst.OCL_CREATE_ENABLED, 
							   OCLEditorConst.PLUGIN_ID);		
		this.createBtn.setDisabledImage(OCLEditorConst.OCL_CREATE_DISABLED, 
				   			   OCLEditorConst.PLUGIN_ID);
		this.createBtn.setMenuCreator(this);
		this.createBtn.setEnabled(false);		
		manager.add(this.createBtn);
		
		this.deleteBtn = new DeleteConstraintAction(Action.AS_PUSH_BUTTON);
		this.deleteBtn.setEnabledImage(OCLEditorConst.OCL_DELETE_ENABLED, 
							   OCLEditorConst.PLUGIN_ID);		
		this.deleteBtn.setDisabledImage(OCLEditorConst.OCL_DELETE_DISABLED, 
								OCLEditorConst.PLUGIN_ID);
		this.deleteBtn.setEnabled(false);
		this.deleteBtn.addListener(this.editorControler);
		manager.add(this.deleteBtn);
		
		this.editBtn = new EditConstraintAction(Action.AS_PUSH_BUTTON);
		this.editBtn.setEnabledImage(OCLEditorConst.OCL_EDIT_ENABLED, 
							   OCLEditorConst.PLUGIN_ID);		
		this.editBtn.setDisabledImage(OCLEditorConst.OCL_EDIT_DISABLED, 
								OCLEditorConst.PLUGIN_ID);
		this.editBtn.setEnabled(false);
		this.editBtn.addListener(this.editorControler);
		manager.add(this.editBtn);		
		
		new ToolItem(((ToolBarManager)manager).getControl(),SWT.SEPARATOR);
		
		this.parseBtn = new ParseConstraintAction(Action.AS_PUSH_BUTTON);
		this.parseBtn.setEnabledImage(OCLEditorConst.OCL_PARSE_ENABLED, 
						   	   OCLEditorConst.PLUGIN_ID);		
		this.parseBtn.setDisabledImage(OCLEditorConst.OCL_PARSE_DISABLED, 
								OCLEditorConst.PLUGIN_ID);
		this.parseBtn.setEnabled(false);
		this.parseBtn.addListener(this.editorControler);
		manager.add(this.parseBtn);
			
		this.abortBtn = new AbortAction(Action.AS_PUSH_BUTTON);
		this.abortBtn.setEnabledImage(OCLEditorConst.OCL_ABORT_ENABLED, 
							   		OCLEditorConst.PLUGIN_ID);		
		this.abortBtn.setDisabledImage(OCLEditorConst.OCL_ABORT_DISABLED, 
									 OCLEditorConst.PLUGIN_ID);
		this.abortBtn.setEnabled(false);
		this.abortBtn.addListener(this.editorControler);
		manager.add(this.abortBtn);		
	}
	
	public Menu getMenu(Control parent) 
	{
		try
		{
			if (this.menu == null || this.menu.isDisposed())
			{
				this.menu = manager.createContextMenu(parent);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return menu;
	}
	
	/**
	 * Initialize the menu manager of the ocl-editor and creates the menu
	 * actions for the found extension point implementations
	 */
	private void initMenuManager() 
	{
		try
		{
			manager = new MenuManager();
			
			Iterator<ExtensionConfiguration> itExtension = ExtensionManager.getInstance().getExtensions().iterator();
			ArrayList<String> groups = new ArrayList<String>();
			while (itExtension.hasNext())
			{
				ExtensionConfiguration extensionConfig = itExtension.next();
				CreateConstraintAction createAction = new CreateConstraintAction(Action.AS_PUSH_BUTTON);
				createAction.setEnabledImage(extensionConfig.getIcon(), 
											 extensionConfig.getPluginID());		
				createAction.setDisabledImage(extensionConfig.getDisabledIcon(), 
											  extensionConfig.getPluginID());
				createAction.setEnabled(false);
				createAction.addListener(this.editorControler);
				createAction.setAction(extensionConfig);
				createAction.setText(extensionConfig.getLabel());
				
				String group = extensionConfig.getGroup();
				if (group != null)
				{
					if (!groups.contains(group))
					{
						if (manager.getItems().length > 0)
							manager.add(new Separator());
						GroupMarker groupMarker = new GroupMarker(group);
						manager.add(groupMarker);						
						groups.add(group);						
					}
					manager.appendToGroup(group, createAction);
				}
				else
				{
					if (manager.getItems().length > 0)
						manager.add(new Separator());
					manager.add(createAction);
				}
			}				
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void dispose() 
	{
		if (menu != null && !menu.isDisposed())
			menu.dispose();
		this.clearProblems();
	}	

	public Menu getMenu(Menu parent) 
	{
		return null;
	}		
	
	/**
	 * Repaints the editor.
	 */
	public void refresh()
	{
		/*Display display = this.editor.getViewSite().getShell().getDisplay();
		
		display.asyncExec(new Runnable()
		{
			public void run()
			{
				constraintsTable.refresh();
			}
		});	*/	
	}
	
	/**
	 * Handles the events fired by the ocl-editor model
	 */
	public void handleChangeEvent(AbstractChangeEvent e) 
	{
		if (e instanceof CreateEvent)
		{						
			this.create = true;
			this.initActionComp();
			ExtensionConfiguration action = ((CreateEvent)e).getExtensionConfig();
			action.getExtension().createPartControl(this.actionComp, null);	
			this.lastAction = action.getExtension();
			this.constraintsTable.getTable().deselectAll();
			this.constraintsTable.refresh();
			this.update(EDIT, e);
			this.main.layout(true, true);			
		}
		if (e instanceof EditEvent)
		{
			this.create = false;
			this.initActionComp();
			int index = this.constraintsTable.getTable().getSelectionIndex();
			Object[] elements = this.model.getElements(null);
			ExtensionConfiguration action = ((TableContentObject)elements[index]).getExtensionConfig(); 
			Object constraint = ((TableContentObject)elements[index]).getConstraint();
			action.getExtension().createPartControl(this.actionComp, constraint);
			this.lastAction = action.getExtension();
			this.update(EDIT, e);
			this.main.layout(true, true);			
		}
		if (e instanceof ParseEvent)
		{
			this.clearProblems();
			this.create = false;
			if (!((ParseEvent)e).problemsOccured())
				this.update(VIEW, e);
			else
			{
				this.createProblem(((ParseEvent)e).getProblem());
				this.update(EDIT_ENABLE, e);
			}
		}
		if (e instanceof AbortEvent)
		{
			this.create = false;
			this.update(VIEW, e);
		}	
		if (e instanceof ParseEnableEvent)
		{
			this.parseBtn.setEnabled(true);
		}
		if (e instanceof ParseDisableEvent)
		{
			this.parseBtn.setEnabled(false);
		}
		if (e instanceof DeletedEvent)
		{
			this.constraintsTable.getTable().deselectAll();
			this.update(VIEW, e);
		}			
		if (e instanceof ModelChangeEvent)
		{
			if (this.model.getExtensionConfigurations().size() == 0)
				this.update(DISABLE, e);
			else
				this.update(VIEW, e);
		}
		if (e instanceof tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.SelectionEvent)
		{
			this.update(VIEW, e);
		}	
		if (e instanceof DisableAllEvent)
		{
			this.update(EDIT_DISABLE_ALL, e);
		}		
	}
	
	/**
	 * Updates the ocl-editor view.
	 * @param state
	 * @param e
	 */
	private void update(int state, AbstractChangeEvent e)
	{
		try{
		switch(state)
		{
			case EDIT:
			{
				if (actionComp != null && !actionComp.isDisposed())
					actionComp.setVisible(true);
				constraintsTableComp.setVisible(false);
				updateToolbarState(true, e);
				break;
			}
			case VIEW:
			{
				if (this.lastAction != null)
				{
					this.lastAction.dispose();
					this.lastAction = null;
				}
				if (actionComp != null && !actionComp.isDisposed())
				{
					actionComp.setVisible(false);
					actionComp.dispose();
				}
				constraintsTableComp.setVisible(true);
				if (!(e instanceof tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.SelectionEvent))
					constraintsTable.refresh();
				updateToolbarState(true, e);
				break;
			}
			case DISABLE:
			{
				if (this.lastAction != null)
				{
					this.lastAction.dispose();
					this.lastAction = null;
				}
				if (actionComp != null && !actionComp.isDisposed())
				{
					actionComp.setVisible(false);
					actionComp.dispose();
				}
				constraintsTableComp.setVisible(true);
				constraintsTable.refresh();
				updateToolbarState(false, e);				
				break;
			}
			case EDIT_DISABLE_ALL:
			{
				this.parseBtn.setEnabled(false);
				this.abortBtn.setEnabled(false);
				break;
			}
			case EDIT_ENABLE:
			{
				this.parseBtn.setEnabled(true);
				this.abortBtn.setEnabled(true);
				break;
			}
		}}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}	
	
	/**
	 * Enables and disables the buttons of the ocl-editor
	 * @param selected
	 * @param e
	 */
	private void updateToolbarState(boolean selected, AbstractChangeEvent e)
	{
		this.createBtn.setEnabled(false);
		this.editBtn.setEnabled(false);
		this.editBtn.clear();
		this.deleteBtn.setEnabled(false);
		this.deleteBtn.clear();
		this.parseBtn.setEnabled(false);
		this.parseBtn.clear();
		this.abortBtn.setEnabled(false);
		
		int index = constraintsTable.getTable().getSelectionIndex();
		
		if (constraintsTableComp.isVisible())
		{
			if (this.model.getExtensionConfigurations() != null &&
				this.model.getExtensionConfigurations().size() > 0)
			{
				updateCreateMenuState();
				if (index != -1)
				{
					Object[] elements = this.model.getElements(null);
					Object constraint = ((TableContentObject)elements[index]).getConstraint();
					ExtensionConfiguration config = ((TableContentObject)elements[index]).getExtensionConfig();
					this.editBtn.setConstraint(constraint);
					this.editBtn.setAction(config);
					this.editBtn.setEnabled(true);						
					this.deleteBtn.setConstraint(constraint);
					this.deleteBtn.setAction(config);
					this.deleteBtn.setEnabled(true);				
				}
			}
		}
		else
		{		
			ExtensionConfiguration config = null;
			if (!this.create && index != -1)
			{				
				Object[] elements = this.model.getElements(null);
				Object constraint = ((TableContentObject)elements[index]).getConstraint();
				config = ((TableContentObject)elements[index]).getExtensionConfig();
				this.parseBtn.setConstraint(constraint);
			}
			else
			{
				config = e.getExtensionConfig();
			}
			this.parseBtn.setAction(config);
			this.abortBtn.setEnabled(true);				
		}				
	}

	/**
	 * Enables and disables the menu items of the ocl-editor create menu
	 *
	 */
	private void updateCreateMenuState() 
	{
		ArrayList<ExtensionConfiguration> actions = this.model.getExtensionConfigurations();
		IContributionItem[] items = manager.getItems();
		for (int i = 0; i < items.length; i++)
		{
			if (!(items[i] instanceof Separator) &&
				!(items[i] instanceof GroupMarker))
			{
				ActionContributionItem actionItem = (ActionContributionItem) items[i];
				CreateConstraintAction action = (CreateConstraintAction) actionItem.getAction();
				action.setEnabled(false);
				for (int j = 0; j < actions.size(); j++)
				{
					if (actions.get(j).equals(action.getAction()) &&
						action.getAction().getExtension().isEnabled())
					{
						action.setEnabled(true);
						this.createBtn.setEnabled(true);						
					}
				}		
			}
		}
	}

	/**
	 * Used by the ocl-editor-controler to set the model
	 * @param model
	 */
	public void setModel(OCLEditorModel model) {
		this.model = model;
	}

	/**
	 * Returns the model of the ocl-editor
	 * @return the model
	 */
	public OCLEditorModel getModel() {
		return model;
	}	
	
	/**
	 * Called after the selection of the constraints table has changed
	 */
	public void selectionChanged(SelectionChangedEvent evt) 
	{
		SelectionEvent event = new SelectionEvent();
		this.editorControler.handleAction(event);
	}	
	
	/**
	 * Adds a problem to the eclipse problems view
	 * @param error
	 */
	private void createProblem(String error)
	{
		try 
		{
			problem = ResourcesPlugin.getWorkspace().getRoot().createMarker(IMarker.PROBLEM);
			problem.setAttribute(IMarker.MESSAGE, error);
			problem.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			
			OpenProblemsView run = new OpenProblemsView(this.editorControler);
			run.open("org.eclipse.ui.views.ProblemView");			
		}
		catch (Exception e)
	    {
	         e.printStackTrace();
	    }
	}
	
	/**
	 * Clears all parse errors.
	 *
	 */
	private void clearProblems()
	{
		try 
		{
			if (this.problem != null)
				problem.delete();
		} 
		catch (CoreException e) 
		{
			e.printStackTrace();
		}
	}
}
