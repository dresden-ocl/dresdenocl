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
package tudresden.ocl20.pivot.modelbus.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.ListenerList;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelRegistry;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener;

/**
 * Standard implementation of the {@link IModelRegistry} interface that uses a simple list for
 * storing references to {@link IModel models}.
 * 
 * @author Matthias Braeuer
 * @version 1.0 11.04.2007
 */
public class ModelRegistry implements IModelRegistry {

  // Logger for this class
  private static final Logger logger = ModelBusPlugin.getLogger(ModelRegistry.class);

  // the list of registered models
  private List<IModel> models;

  // a list of listeners
  private ListenerList listeners;

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelRegistry#addMetamodel(tudresden.ocl20.pivot.modelbus.IModel)
   */
  public void addModel(IModel model) {
    if (logger.isDebugEnabled()) {
      logger.debug("addModel(model=" + model + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (model == null) {
      throw new IllegalArgumentException("The parameter 'model' must not be null."); //$NON-NLS-1$
    }

    // lazily create the list of models
    if (models == null) {
      models = new ArrayList<IModel>();
    }

    models.add(model);

    if (logger.isDebugEnabled()) {
      logger.debug("addModel() - exit"); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelRegistry#dispose()
   */
  public void dispose() {
    if (models != null) {
      models.clear();
      models = null;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelRegistry#getModels()
   */
  public IModel[] getModels() {
    return models == null ? new IModel[] {} : models.toArray(new IModel[models.size()]);
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelRegistry#addModelBusListener(tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener)
   */
  public void addModelRegistryListener(IModelRegistryListener listener) {

    // lazily create the listener list
    if (listeners == null) {
      listeners = new ListenerList(ListenerList.IDENTITY);
    }

    listeners.add(listener);
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelRegistry#removeModelBusListener(tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener)
   */
  public void removeModelRegistryListener(IModelRegistryListener listener) {
    if (listeners != null) {
      listeners.remove(listener);
    }
  }

}
