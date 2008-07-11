/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * $Id$
 */
package tudresden.ocl20.pivot.xocl.presentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

import tudresden.ocl20.pivot.xocl.NamespaceXS;
import tudresden.ocl20.pivot.xocl.XOCLFactory;
import tudresden.ocl20.pivot.xocl.XOCLPackage;
import tudresden.ocl20.pivot.xocl.provider.XOCLEditPlugin;

/**
 * This is a simple wizard for creating a new model file.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class XOCLModelWizard extends Wizard implements INewWizard {

  /**
   * This caches an instance of the model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XOCLPackage xoclPackage = XOCLPackage.eINSTANCE;

  /**
   * This caches an instance of the model factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XOCLFactory xoclFactory = xoclPackage.getXOCLFactory();

  /**
   * This is the file creation page.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XOCLModelWizardNewFileCreationPage newFileCreationPage;

  /**
   * This is the initial object creation page.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XOCLModelWizardInitialObjectCreationPage initialObjectCreationPage;

  /**
   * Remember the selection during initialization for populating the default container.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IStructuredSelection selection;

  /**
   * Remember the workbench during initialization.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IWorkbench workbench;

  /**
   * Caches the names of the types that can be created as the root object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected List<String> initialObjectNames;

  /**
   * This just records the information.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    this.workbench = workbench;
    this.selection = selection;
    setWindowTitle(XOCLEditorPlugin.INSTANCE.getString("_UI_Wizard_label")); //$NON-NLS-1$
    setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE
        .getImageDescriptor(XOCLEditorPlugin.INSTANCE.getImage("full/wizban/NewXOCL"))); //$NON-NLS-1$
  }

  /**
   * Returns the names of the types that can be created as the root object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Collection<String> getInitialObjectNames() {
    if (initialObjectNames == null) {
      initialObjectNames = new ArrayList<String>();
      for (EClassifier eClassifier : xoclPackage.getEClassifiers()) {
        if (eClassifier instanceof EClass) {
          EClass eClass = (EClass) eClassifier;
          if (!eClass.isAbstract()) {
            initialObjectNames.add(eClass.getName());
          }
        }
      }
      Collections.sort(initialObjectNames,java.text.Collator.getInstance());
    }
    return initialObjectNames;
  }

  /**
   * Create a new model.
   * 
   * <p>
   * Changed the EMF implementation to always create a {@link NamespaceXS} as the root object.
   * </p>
   * 
   * @generated NOT
   */
  protected EObject createInitialModel() {
    return xoclFactory.createNamespaceXS();
  }

  /**
   * Do the work after everything is specified.
   * 
   * <p>
   * The EMF implementation is altered to always use UTF-8 encoding. Since we do not have a
   * wizard page for selecting the initial model object, we also leave out the selection of 
   * the encoding.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public boolean performFinish() {
    try {
      // Remember the file.
      //
      final IFile modelFile = getModelFile();

      // Do the work within an operation.
      //
      WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {

        @Override
        protected void execute(IProgressMonitor progressMonitor) {
          try {
            // Create a resource set
            //
            ResourceSet resourceSet = new ResourceSetImpl();

            // Get the URI of the model file.
            //
            URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(),true);

            // Create a resource for this file.
            //
            Resource resource = resourceSet.createResource(fileURI);

            // Add the initial model object to the contents.
            //
            EObject rootObject = createInitialModel();
            if (rootObject != null) {
              resource.getContents().add(rootObject);
            }

            // Save the contents of the resource to the file system.
            //
            Map<Object, Object> options = new HashMap<Object, Object>();
            options.put(XMLResource.OPTION_ENCODING,"UTF-8"); //$NON-NLS-1$
            resource.save(options);
          }
          catch (Exception exception) {
            XOCLEditorPlugin.INSTANCE.log(exception);
          }
          finally {
            progressMonitor.done();
          }
        }
      };

      getContainer().run(false,false,operation);

      // Select the new file resource in the current view.
      //
      IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
      IWorkbenchPage page = workbenchWindow.getActivePage();
      final IWorkbenchPart activePart = page.getActivePart();
      if (activePart instanceof ISetSelectionTarget) {
        final ISelection targetSelection = new StructuredSelection(modelFile);
        getShell().getDisplay().asyncExec(new Runnable() {

          public void run() {
            ((ISetSelectionTarget) activePart).selectReveal(targetSelection);
          }
        });
      }

      // Open an editor on the new file.
      //
      try {
        page.openEditor(new FileEditorInput(modelFile),workbench.getEditorRegistry()
            .getDefaultEditor(modelFile.getFullPath().toString()).getId());
      }
      catch (PartInitException exception) {
        MessageDialog.openError(workbenchWindow.getShell(),XOCLEditorPlugin.INSTANCE
            .getString("_UI_OpenEditorError_label"),exception.getMessage()); //$NON-NLS-1$
        return false;
      }

      return true;
    }
    catch (Exception exception) {
      XOCLEditorPlugin.INSTANCE.log(exception);
      return false;
    }
  }

  /**
   * This is the one page of the wizard.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class XOCLModelWizardNewFileCreationPage extends WizardNewFileCreationPage {

    /**
     * Pass in the selection.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XOCLModelWizardNewFileCreationPage(String pageId, IStructuredSelection selection) {
      super(pageId,selection);
    }

    /**
     * The framework calls this to see if the file is correct.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected boolean validatePage() {
      if (super.validatePage()) {
        // Make sure the file ends in ".xocl".
        //
        String requiredExt = XOCLEditorPlugin.INSTANCE.getString("_UI_XOCLEditorFilenameExtension"); //$NON-NLS-1$
        String enteredExt = new Path(getFileName()).getFileExtension();
        if (enteredExt == null || !enteredExt.equals(requiredExt)) {
          setErrorMessage(XOCLEditorPlugin.INSTANCE.getString(
              "_WARN_FilenameExtension",new Object[] { requiredExt })); //$NON-NLS-1$
          return false;
        }
        else {
          return true;
        }
      }
      else {
        return false;
      }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IFile getModelFile() {
      return ResourcesPlugin.getWorkspace().getRoot().getFile(
          getContainerFullPath().append(getFileName()));
    }
  }

  /**
   * This is the page where the type of object to create is selected.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class XOCLModelWizardInitialObjectCreationPage extends WizardPage {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Combo initialObjectField;
    /**
     * @generated
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    protected List<String> encodings;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Combo encodingField;

    /**
     * Pass in the selection.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XOCLModelWizardInitialObjectCreationPage(String pageId) {
      super(pageId);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createControl(Composite parent) {
      Composite composite = new Composite(parent,SWT.NONE);
      {
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.verticalSpacing = 12;
        composite.setLayout(layout);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.grabExcessVerticalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        composite.setLayoutData(data);
      }

      Label containerLabel = new Label(composite,SWT.LEFT);
      {
        containerLabel.setText(XOCLEditorPlugin.INSTANCE.getString("_UI_ModelObject")); //$NON-NLS-1$

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        containerLabel.setLayoutData(data);
      }

      initialObjectField = new Combo(composite,SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        initialObjectField.setLayoutData(data);
      }

      for (String objectName : getInitialObjectNames()) {
        initialObjectField.add(getLabel(objectName));
      }

      if (initialObjectField.getItemCount() == 1) {
        initialObjectField.select(0);
      }
      initialObjectField.addModifyListener(validator);

      Label encodingLabel = new Label(composite,SWT.LEFT);
      {
        encodingLabel.setText(XOCLEditorPlugin.INSTANCE.getString("_UI_XMLEncoding")); //$NON-NLS-1$

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        encodingLabel.setLayoutData(data);
      }
      encodingField = new Combo(composite,SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        encodingField.setLayoutData(data);
      }

      for (String encoding : getEncodings()) {
        encodingField.add(encoding);
      }

      encodingField.select(0);
      encodingField.addModifyListener(validator);

      setPageComplete(validatePage());
      setControl(composite);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ModifyListener validator = new ModifyListener() {

      public void modifyText(ModifyEvent e) {
        setPageComplete(validatePage());
      }
    };

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected boolean validatePage() {
      return getInitialObjectName() != null && getEncodings().contains(encodingField.getText());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (visible) {
        if (initialObjectField.getItemCount() == 1) {
          initialObjectField.clearSelection();
          encodingField.setFocus();
        }
        else {
          encodingField.clearSelection();
          initialObjectField.setFocus();
        }
      }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getInitialObjectName() {
      String label = initialObjectField.getText();

      for (String name : getInitialObjectNames()) {
        if (getLabel(name).equals(label)) {
          return name;
        }
      }
      return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getEncoding() {
      return encodingField.getText();
    }

    /**
     * Returns the label for the specified type name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getLabel(String typeName) {
      try {
        return XOCLEditPlugin.INSTANCE.getString("_UI_" + typeName + "_type"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      catch (MissingResourceException mre) {
        XOCLEditorPlugin.INSTANCE.log(mre);
      }
      return typeName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Collection<String> getEncodings() {
      if (encodings == null) {
        encodings = new ArrayList<String>();
        for (StringTokenizer stringTokenizer = new StringTokenizer(XOCLEditorPlugin.INSTANCE
            .getString("_UI_XMLEncodingChoices")); stringTokenizer.hasMoreTokens();) //$NON-NLS-1$
        {
          encodings.add(stringTokenizer.nextToken());
        }
      }
      return encodings;
    }

  }

  /**
   * The framework calls this to create the contents of the wizard.
   * 
   * <p>
   * The EMF implementation is altered to only add the page for creating a new file. The model 
   * root is automatically created as a {@link NamespaceXS}.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public void addPages() {
    // Create a page, set the title, and the initial model file name.
    //
    newFileCreationPage = new XOCLModelWizardNewFileCreationPage("Whatever",selection); //$NON-NLS-1$
    newFileCreationPage.setTitle(XOCLEditorPlugin.INSTANCE.getString("_UI_XOCLModelWizard_label")); //$NON-NLS-1$
    newFileCreationPage.setDescription(XOCLEditorPlugin.INSTANCE
        .getString("_UI_XOCLModelWizard_description")); //$NON-NLS-1$
    newFileCreationPage
        .setFileName(XOCLEditorPlugin.INSTANCE.getString("_UI_XOCLEditorFilenameDefaultBase") + "." + XOCLEditorPlugin.INSTANCE.getString("_UI_XOCLEditorFilenameExtension")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    addPage(newFileCreationPage);

    // Try and get the resource selection to determine a current directory for the file dialog.
    //
    if (selection != null && !selection.isEmpty()) {
      // Get the resource...
      //
      Object selectedElement = selection.iterator().next();
      if (selectedElement instanceof IResource) {
        // Get the resource parent, if its a file.
        //
        IResource selectedResource = (IResource) selectedElement;
        if (selectedResource.getType() == IResource.FILE) {
          selectedResource = selectedResource.getParent();
        }

        // This gives us a directory...
        //
        if (selectedResource instanceof IFolder || selectedResource instanceof IProject) {
          // Set this for the container.
          //
          newFileCreationPage.setContainerFullPath(selectedResource.getFullPath());

          // Make up a unique new name here.
          //
          String defaultModelBaseFilename = XOCLEditorPlugin.INSTANCE
              .getString("_UI_XOCLEditorFilenameDefaultBase"); //$NON-NLS-1$
          String defaultModelFilenameExtension = XOCLEditorPlugin.INSTANCE
              .getString("_UI_XOCLEditorFilenameExtension"); //$NON-NLS-1$
          String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension; //$NON-NLS-1$
          for (int i = 1; ((IContainer) selectedResource).findMember(modelFilename) != null; ++i) {
            modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension; //$NON-NLS-1$
          }
          newFileCreationPage.setFileName(modelFilename);
        }
      }
    }
  }

  /**
   * Get the file from the page.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IFile getModelFile() {
    return newFileCreationPage.getModelFile();
  }

}
