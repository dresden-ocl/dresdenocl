package tudresden.ocl20.pivot.modelbus.ui.internal.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;

import tudresden.ocl20.pivot.essentialocl.expressions.provider.ExpressionsItemProviderAdapterFactory;
import tudresden.ocl20.pivot.essentialocl.types.provider.TypesItemProviderAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IMetamodelDescriptor;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener;
import tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.provider.PivotModelItemProviderAdapterFactory;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 13.04.2007
 */
/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 13.04.2007
 */
public class ModelsView extends ViewPart implements IModelRegistryListener {

  /**
   * The ID of this view.
   */
  public static final String ID = "tudresden.ocl20.pivot.modelbus.ui.views.models"; //$NON-NLS-1$

  // Logger for this class
  private static final Logger logger = ModelBusUIPlugin.getLogger(ModelsView.class);

  // the tree viewer displaying the model
  private TreeViewer viewer;

  // a helper class for back-forward navigation
  private DrillDownAdapter drillDownAdapter;

  // maps registered models to the activating actions
  private Map<IModel, ModelSelectionAction> modelSelectionActions;

  // the currently selected action representing the active model
  private ModelSelectionAction selectedAction;

  // a cached reference to the drop down menu
  private IMenuManager menu;

  // the adapter factory that provides the view of the model
  private ComposedAdapterFactory adapterFactory;

  // the property sheet page that displays model properties
  private PropertySheetPage propertySheet;

  /**
   * The constructor.
   */
  public ModelsView() {
    super();

    modelSelectionActions = new HashMap<IModel, ModelSelectionAction>();

    // add as a listener to the model registry
    ModelBusPlugin.getModelRegistry().addModelRegistryListener(this);

    // create the adapter factory that renders the model
    List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
    factories.add(new TypesItemProviderAdapterFactory());
    factories.add(new ExpressionsItemProviderAdapterFactory());
    factories.add(new PivotModelItemProviderAdapterFactory());
    adapterFactory = new ComposedAdapterFactory(factories);
  }

  /**
   * This is a callback that will allow us to create the viewer and initialize it.
   */
  @Override
  public void createPartControl(Composite parent) {

    // create a new tree viewer
    viewer = new TreeViewer(parent,SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

    // enable drill down behaviour
    drillDownAdapter = new DrillDownAdapter(viewer);
    drillDownAdapter.addNavigationActions(getViewSite().getActionBars().getToolBarManager());

    // set content and label provider
    viewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
    viewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    // automatically expand top-level packages
    viewer.setAutoExpandLevel(2);

    // initialize the drop down menu
    initMenu();

    // activate selection support
    getViewSite().setSelectionProvider(viewer);
  }

  /**
   * Lazily creates a cached version of the property sheet.
   */
  public IPropertySheetPage getPropertySheetPage() {

    if (propertySheet == null) {
      propertySheet = new PropertySheetPage();
      propertySheet.setPropertySourceProvider(new AdapterFactoryContentProvider(adapterFactory));
    }

    return propertySheet;
  }

  /**
   * Passing the focus request to the viewer's control.
   */
  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

  /**
   * Overridden to return the property sheet page if requested.
   * 
   * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(java.lang.Class)
   */
  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {

    if (adapter.equals(IPropertySheetPage.class)) {
      return getPropertySheetPage();
    }

    return super.getAdapter(adapter);
  }

  /**
   * 
   * 
   * @see org.eclipse.ui.part.WorkbenchPart#dispose()
   */
  @Override
  public void dispose() {

    // clear the cache for model selection actions
    modelSelectionActions.clear();
    selectedAction = null;

    // remove as listener from the model registry
    ModelBusPlugin.getModelRegistry().removeModelRegistryListener(this);

    // dispose the property sheet page
    if (propertySheet != null) {
      propertySheet.dispose();
    }

    super.dispose();
  }

  /**
   * Implemented to realize the {@link IModelRegistryListener} interface.
   * 
   * @see tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#activeModelChanged(tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
   */
  public void activeModelChanged(ModelRegistryEvent e) {
    setActiveModel(e.getAffectedModel());
  }

  /**
   * 
   * Implemented to realize the {@link IModelRegistryListener} interface.
   * 
   * @see tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#modelAdded(tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
   */
  public void modelAdded(ModelRegistryEvent e) {
    addModelSelectionAction(e.getAffectedModel());
  }

  /**
   * Helper method which lazily initializes the reference to the drop down menu.
   */
  protected IMenuManager getMenu() {
    if (menu == null) {
      menu = getViewSite().getActionBars().getMenuManager();
    }

    return menu;
  }

  /**
   * Initializes the drop-down menu of the view with all models currently registered.
   */
  protected void initMenu() {
    IModel[] models = ModelBusPlugin.getModelRegistry().getModels();

    for (int i = 0; i < models.length; i++) {
      addModelSelectionAction(models[i]);
    }

    // update the currently active model
    updateActiveModel();
  }

  /**
   * Creates a new {@link ModelSelectionAction} for the given {@link IModel model} and adds it to
   * the menu. Duplicate actions are prevented by checking whether an action for the given model
   * already exists.
   */
  protected ModelSelectionAction addModelSelectionAction(IModel model) {
    ModelSelectionAction action;

    if (modelSelectionActions.containsKey(model)) {
      action = modelSelectionActions.get(model);
    }

    else {
      action = new ModelSelectionAction(model);
      modelSelectionActions.put(model,action);
      getMenu().add(action);
      getViewSite().getActionBars().updateActionBars();
    }

    return action;
  }

  /**
   * Activates the action corresponding to the given model and updates the input for the viewer.
   */
  protected void updateActiveModel() {
    IModel model = ModelBusPlugin.getModelRegistry().getActiveModel();

    if (model == null) {
      resetActiveModel();
    }

    else {
      setActiveModel(model);
    }
  }

  /**
   * Resets the currently active model and the associated action.
   */
  protected void resetActiveModel() {

    if (selectedAction != null) {
      selectedAction.setChecked(false);
      selectedAction = null;
    }

    if (viewer.getInput() != null) {
      setInput(null);
    }
  }

  /**
   * Checks the corresponding action and sets the input of the viewer.
   */
  protected void setActiveModel(IModel model) {
    ModelSelectionAction action = modelSelectionActions.get(model);

    if (action != null && action != selectedAction) {
      action.setChecked(true);
      selectedAction = action;
    }

    else {
      // this should not happen
      throw new IllegalStateException("No model selection action has been created for model '" //$NON-NLS-1$
          + model.getDisplayName() + "'"); //$NON-NLS-1$
    }

    // get the root namespace from the new active model
    Namespace rootNamespace = null;

    try {
      rootNamespace = model.getRootNamespace();
    }

    catch (ModelAccessException e) {
      logger.error("Error when accessing model " + model,e); //$NON-NLS-1$

      MessageDialog.openError(getSite().getShell(),ModelBusUIMessages.ModelsView_Error,NLS.bind(
          ModelBusUIMessages.ModelsView_AccessRootNamespace,model.getDisplayName()));

      // do not exit here to reset the input to null below
    }

    // update the input; we do not use equals for comparison of current input with new root
    // namespace because the root namespace may be a transient one with an empty name
    if (viewer.getInput() == null && rootNamespace != null || viewer.getInput() != null
        && viewer.getInput() != rootNamespace) {
      setInput(rootNamespace);
    }
  }

  /**
   * Helper method to set the input of the tree viewer.
   */
  protected void setInput(Namespace rootNamespace) {
    viewer.setInput(rootNamespace);
    viewer.refresh();
    viewer.setSelection(new StructuredSelection(
        rootNamespace.getNestedNamespace().isEmpty() ? StructuredSelection.EMPTY : rootNamespace
            .getNestedNamespace().get(0)));
    drillDownAdapter.reset();
  }

  /**
   * An action representing a registered {@link IModel} which will set the input of the Models view
   * to this model.
   */
  protected class ModelSelectionAction extends Action implements IAction {

    // the model represented by this action
    private IModel model;

    /**
     * Creates a new <code>ModelSelectionAction</code> instance
     * 
     * @param model the represented model
     */
    protected ModelSelectionAction(IModel model) {
      super(model.getDisplayName(),IAction.AS_RADIO_BUTTON);

      // initialize
      this.model = model;

      // set the icon if the metamodel is realized by an Eclipse descriptor
      IMetamodel metamodel = model.getMetamodel();

      if (metamodel instanceof IMetamodelDescriptor) {
        setImageDescriptor(((IMetamodelDescriptor) metamodel).getIcon());
      }

      // set the string representation of the model as the action's id, in the hope that it uniquely
      // identifies the model
      setId(model.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
      // set the new active model
      ModelBusPlugin.getModelRegistry().setActiveModel(model);
    }

    /**
     * Returns a string representation of this action.
     */
    @Override
    public String toString() {
      return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE).append("model",model) //$NON-NLS-1$
          .toString();
    }

  }

}
