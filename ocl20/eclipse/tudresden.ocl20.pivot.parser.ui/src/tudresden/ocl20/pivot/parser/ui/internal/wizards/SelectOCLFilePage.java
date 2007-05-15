/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
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
package tudresden.ocl20.pivot.parser.ui.internal.wizards;

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import tudresden.ocl20.pivot.parser.ui.internal.ParserUIMessages;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 09.05.2007
 */
public class SelectOCLFilePage extends WizardPage {

  // the editor to select the OCL file
  private FileFieldEditor fileSelectionEditor;

  // the selection in the workspace before the wizard was started
  private IStructuredSelection selection;
  
  /**
   * 
   * @param selection
   */
  public SelectOCLFilePage(IStructuredSelection selection) {
    super("ParseOclPage"); //$NON-NLS-1$

    this.selection = selection;

    setTitle(ParserUIMessages.ParseOCLPage_Title); // NON-NLS-1
    setDescription(ParserUIMessages.ParseOCLPage_Description);
  }

  /**
   * Returns the selected file or <code>null</code> if no file has been selected. 
   * 
   * @return a <code>File</code> instance or <code>null</code>
   */
  public File getSelectedOCLFile() {
    String fileName = fileSelectionEditor.getStringValue(); 
    
    if (fileName.length() != 0) {
      return new Path(fileName).toFile();
    }
    
    return null;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent) {
    Composite panel;
    GridLayout layout;

    // create panel
    panel = new Composite(parent,SWT.NONE);

    // set panel attributes
    layout = new GridLayout(1,true);
    layout.verticalSpacing = 20;
    panel.setLayout(layout);
    panel.setFont(parent.getFont());

    // create UI elements
    createFileSelectionGroup(panel);

    // set font
    Dialog.applyDialogFont(parent);

    // connect the wizard page with the wizard
    setControl(panel);
    
    // set initial selection
    initializeFromSelection();
    updatePageComplete();
  }

  /**
   * Creates the file selection area in the top part of the wizard.
   */
  private void createFileSelectionGroup(Composite parent) {
    Composite fileSelectionArea;
    GridLayout fileSelectionLayout;

    fileSelectionArea = new Composite(parent,SWT.NONE);
    fileSelectionArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // configure layout of file selection area
    fileSelectionLayout = new GridLayout();
    fileSelectionLayout.numColumns = 3;
    fileSelectionLayout.makeColumnsEqualWidth = false;
    fileSelectionLayout.marginWidth = 0;
    fileSelectionLayout.marginHeight = 0;
    fileSelectionArea.setLayout(fileSelectionLayout);

    fileSelectionEditor = new FileFieldEditor("oclFile",ParserUIMessages.ParseOCLPage_SelectOCLFileLabel,fileSelectionArea); //$NON-NLS-1$
    fileSelectionEditor.setFileExtensions(getFileExtensions());

    fileSelectionEditor.setPropertyChangeListener(new IPropertyChangeListener() {

      public void propertyChange(PropertyChangeEvent event) {
        
        if (event.getProperty().equals(FieldEditor.VALUE)) {
          updatePageComplete();
        }
        
      }
      
    });

  }

  // initializes the file selection area from the selecton in the workspace
  private void initializeFromSelection() {
    Object selectedObject = selection.getFirstElement();

    if (selectedObject instanceof IResource) {
      IResource selectedResource = (IResource) selectedObject;

      if (selectedResource.getType() == IResource.FILE) {
        fileSelectionEditor.setStringValue(selectedResource.getRawLocation().toString());
      }
    }
  }

  /**
   * Returns the file extensions accepted by the file editor of this page.
   */
  protected String[] getFileExtensions() {
    return new String[] { "*.xocl" }; //$NON-NLS-1$
  }

  /**
   * Helper method to update the completion status of this page.
   */
  protected void updatePageComplete() {
    setPageComplete(fileSelectionEditor.isValid());
  }

}
