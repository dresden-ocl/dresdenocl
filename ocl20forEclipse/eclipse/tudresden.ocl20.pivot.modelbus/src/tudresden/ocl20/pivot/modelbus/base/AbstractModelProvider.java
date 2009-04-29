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
package tudresden.ocl20.pivot.modelbus.base;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.internal.ModelBusMessages;

/**
 * This is a base implementation for the {@link IModelProvider} interface that assumes models to be
 * stored in files or accessible as a resource, respectively. Metamodeling technologies that support
 * these concepts may subclass this class for an easier implementation.
 * 
 * @author Matthias Braeuer
 * @version 1.0 10.04.2007
 */
public abstract class AbstractModelProvider implements IModelProvider {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(AbstractModelProvider.class);

  /**
   * This implementation tries to create a URL from the given model name. See
   * {@link Class#getResource(String)} for a description of the required format of the name.
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelProvider#getModel(java.lang.String)
   */
  public IModel getModel(String modelName) throws ModelAccessException {
    if (logger.isDebugEnabled()) {
      logger.debug("getModel(modelName=" + modelName + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    URL modelUrl;
    IModel model;

    modelUrl = AbstractModelProvider.class.getResource(modelName);

    if (modelUrl == null) {
      throw new ModelAccessException("Failed to create a URL for model " + modelName); //$NON-NLS-1$
    }

    logger.info(NLS.bind(ModelBusMessages.AbstractModelProvider_LoadingModel,modelName));

    // delegate to user implementation
    model = getModel(modelUrl);

    if (logger.isDebugEnabled()) {
      logger.debug("getModel() - exit - return value=" + model); //$NON-NLS-1$
    }

    return model;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelProvider#getModel(java.io.File)
   */
  public IModel getModel(File modelFile) throws ModelAccessException {
    if (logger.isDebugEnabled()) {
      logger.debug("getModel(modelFile=" + modelFile + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (modelFile == null) {
      throw new IllegalArgumentException("The argument 'modelFile' was null."); //$NON-NLS-1$
    }

    IModel model;
    URL modelFileUrl;

    try {
      modelFileUrl = modelFile.toURI().toURL();
    }

    catch (MalformedURLException e) {
      throw new ModelAccessException("Failed to create a URL for file " //$NON-NLS-1$
          + modelFile.getAbsolutePath(),e);
    }

    logger.info(NLS.bind(ModelBusMessages.AbstractModelProvider_LoadingModel,modelFile
        .getAbsolutePath()));

    // delegate to user implementation
    model = getModel(modelFileUrl);

    if (logger.isDebugEnabled()) {
      logger.debug("getModel() - exit - return value=" + model); //$NON-NLS-1$
    }

    return model;
  }
}