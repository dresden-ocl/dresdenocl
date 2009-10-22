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
package tudresden.ocl20.pivot.modelbus.model.internal;

import org.apache.log4j.Logger;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.internal.ModelBusMessages;
import tudresden.ocl20.pivot.modelbus.model.IOclLibraryProvider;

/**
 * Standard implementation of the {@link IOclLibraryProvider} interface that simply loads the model
 * file with the OCL Standard Library from the resources of this plugin.
 * 
 * @author Matthias Braeuer
 * @version 1.0 03.04.2007
 */
public class OclLibraryProvider implements IOclLibraryProvider {

  // logger for this class
  private static final Logger logger = ModelBusPlugin.getLogger(OclLibraryProvider.class);

  // the file name of the OCL Standard Library model
  private static final String OCL_LIBRARY_FILE = "/resources/oclstandardlibrary.types"; //$NON-NLS-1$

  // the cached library instance
  private OclLibrary oclLibrary;

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IOclLibraryProvider#getOclLibrary()
   */
  public OclLibrary getOclLibrary() {
    
    if (oclLibrary == null) {
      oclLibrary = loadOclLibrary();
    }
    
    return oclLibrary;
  }
  

  /**
   * Helper method that performs the actual loading. This should work without errors unless the
   * Model Bus plugin is changed.
   * 
   * @return an <code>oclLibrary</code> instance
   */
  protected OclLibrary loadOclLibrary() {
    EObject library;

    ResourceSet resourceSet = new ResourceSetImpl();
    Resource resource = resourceSet.createResource(URI.createPlatformPluginURI(ModelBusPlugin.ID
        + OCL_LIBRARY_FILE,false));
    
    if (logger.isInfoEnabled()) {
      logger.info(ModelBusMessages.OclLibraryProvider_LoadOclLibrary);
    }
    
    try {
      resource.load(null);
    }
    catch (IOException e) {
      throw new IllegalStateException("Failed to load the OCL Standard Library.",e); //$NON-NLS-1$
    }

    library = resource.getContents().get(0);

    if (!(library instanceof OclLibrary)) {
      throw new IllegalStateException("The root object of model '" + OCL_LIBRARY_FILE //$NON-NLS-1$
          + "' is not an instance of OclLibrary."); //$NON-NLS-1$
    }
    
    return (OclLibrary) library;
  }

}
