package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.model;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.IOCLExtension;
import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.management.ExtensionConfiguration;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.OCLEditor;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.CreateConstraintEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.AbortEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.CreateEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.DeletedEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.DisableAllEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.EditEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.ModelChangeEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.ParseDisableEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.ParseEnableEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.ParseEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.SelectionEvent;

/**
 * An instance of this class is used by the class OCLEditor as model
 * @author Mirko Stölzel
 *
 */
public class OCLEditorModel implements ITableLabelProvider, IStructuredContentProvider
{
	//the ocl editor
	private OCLEditor editor = null;
	
	//all extensions which can create OCL constraints for the actual modelelement
	private ArrayList<ExtensionConfiguration> extensionConfigs = null;

	/**
	 * Constructor
	 * @param editor
	 */
	public OCLEditorModel(OCLEditor editor) 
	{
		this.editor = editor;
	}
	
	/**
	 * Returns the ocl editor which use this model
	 * @return the ocl editor
	 */
	public OCLEditor getEditor() {
		return editor;
	}
	
	/**
	 * Is called to create an OCL constraint and to open the
	 * editor component of the given extension.
	 * 
	 * @param extensionConfig
	 */
	public void fireCreateEvent(ExtensionConfiguration extensionConfig)
	{
		CreateEvent evt = new CreateEvent();
		evt.setExtensionConfig(extensionConfig);
		this.editor.handleChangeEvent(evt);
	}	
	
	/**
	 * Is called to edit an OCL constraint and to open the
	 * editor component of the given extension.
	 * @param extensionConfig
	 * @param constraint 
	 */
	public void fireEditEvent(ExtensionConfiguration extensionConfig, Object constraint)
	{
		EditEvent e = new EditEvent();
		e.setExtensionConfig(extensionConfig);
		this.editor.handleChangeEvent(e);
	}
	
	/**
	 * Called to enable the parse button
	 */
	public void fireParseEnable()
	{
		this.editor.handleChangeEvent(new ParseEnableEvent());
	}
	
	/**
	 * Called after an OCL constraint was parsed.
	 * @param problem
	 */
	public void fireParseEvent(String problem)
	{
		ParseEvent evt = new ParseEvent();
		evt.setProblem(problem);
		this.editor.handleChangeEvent(evt);	
	}
	
	/**
	 * Is called after the editing process was aborted
	 *
	 */
	public void fireAbortEvent()
	{
		this.editor.handleChangeEvent(new AbortEvent());
	}

	/**
	 * Is called after an OCL constraint was deleted
	 *
	 */
	public void fireDeletedEvent()
	{
		this.editor.handleChangeEvent(new DeletedEvent());
	}
	
	/**
	 * Called after the selection in the ocl editor table has changed
	 */
	public void fireSelectionChangeEvent()
	{
		this.editor.handleChangeEvent(new SelectionEvent());
	}
	
	/**
	 * Called after the parse button was pressed to disable all buttons
	 */
	public void fireDisableAllEvent()
	{
		this.editor.handleChangeEvent(new DisableAllEvent());
	}
	
	public void fireParseDisable()
	{
		this.editor.handleChangeEvent(new ParseDisableEvent());
	}
	
	/**
	 * Called after the model has changed
	 */
	public void fireModelChangeEvent()
	{
		this.editor.handleChangeEvent(new ModelChangeEvent());
	}
	
	/**
	 * Called after the selected modelelement has changed to set all
	 * extensions for the new element 
	 * @param extensionConfigs
	 */
	public void setActions(ArrayList<ExtensionConfiguration> extensionConfigs) 
	{
		this.extensionConfigs = extensionConfigs;
		this.fireModelChangeEvent();
	}
	
	/**
	 * Returns the actual extension configurations of the model
	 * @return
	 */
	public ArrayList<ExtensionConfiguration> getExtensionConfigurations()
	{
		return this.extensionConfigs;
	}
	
	public Image getColumnImage(Object element, int columnIndex) 
	{
		return null;
	}

	public String getColumnText(Object element, int columnIndex) 
	{
		if (columnIndex == 0)
			return ((TableContentObject)element).getRow();
		return ((TableContentObject)element).getConstraintText();
	}	
	
	public void addListener(ILabelProviderListener listener) {}

	public boolean isLabelProperty(Object element, String property) 
	{
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {}
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) 
	{}

	public Object[] getElements(Object parent) 
	{
		if (extensionConfigs != null)
		{
			int count = 1;
			ArrayList<TableContentObject> result = new ArrayList<TableContentObject>();
			for (int i = 0; i < extensionConfigs.size(); i++)
			{
				IOCLExtension extension = extensionConfigs.get(i).getExtension();
				if (extension.isEnabled())
				{
					Object[] constraints = extension.getConstraints();
					for (int j = 0; j < constraints.length; j++)
					{
						TableContentObject object = new TableContentObject(count, 
																		   extensionConfigs.get(i), 
																	       constraints[j]);
						result.add(object);
						count++;
					}
				}
			}
				
			return result.toArray();
		}
		else
			return (new ArrayList()).toArray();
	}

	public void dispose() 
	{}
	
	/**
	 * An instance of this class represents an content object of the constraints
	 * table of the ocl-editor
	 * @author Mirk
	 *
	 */
	public class TableContentObject
	{
		private int row = 0;
		private ExtensionConfiguration extensionConfig = null;
		private Object constraint = null;
		
		/**
		 * Constructor
		 * @param row
		 * @param extensionConfig
		 * @param constraint
		 */
		public TableContentObject(int row, ExtensionConfiguration extensionConfig, Object constraint) {
			this.row = row;
			this.extensionConfig = extensionConfig;
			this.constraint = constraint;
		}
		
		/**
		 * Return the textual representation of an OCL constraint
		 * @return
		 */
		public String getConstraintText()
		{
			String constraintText = extensionConfig.getExtension().getConstraintText(constraint);
			constraintText = constraintText.replace("\n", " ");
			return constraintText;
		}
		
		/**
		 * Returns the table row of this table content object
		 * @return
		 */
		public String getRow()
		{
			return "" + row;
		}

		/**
		 * Returns the extension of the constraint
		 * @return
		 */
		public ExtensionConfiguration getExtensionConfig()
		{
			return extensionConfig;
		}

		/**
		 * Returns the constraint object
		 * @return
		 */
		public Object getConstraint()
		{
			return constraint;
		}		
	}		
}
