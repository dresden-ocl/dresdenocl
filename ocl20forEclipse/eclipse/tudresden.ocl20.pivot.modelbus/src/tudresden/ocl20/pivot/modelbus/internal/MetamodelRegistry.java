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

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.ui.PlatformUI;

import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IMetamodelDescriptor;
import tudresden.ocl20.pivot.modelbus.IMetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;

/**
 * A default implementation of the {@link IMetamodelRegistry} interface to be used in an Eclipse
 * workbench.
 * 
 * @author Matthias Braeuer
 * @version 1.0 03.04.2007
 */
public final class MetamodelRegistry implements IMetamodelRegistry, IExtensionChangeHandler {

  // Logger for this class
  private static final Logger logger = ModelBusPlugin.getLogger(MetamodelRegistry.class);

  // the full identifier of the metamodels extension point
  private static final String METAMODEL_EXTENSION_POINT_ID = ModelBusPlugin.ID + '.'
      + IModelBusConstants.EXT_METAMODELS;

  // a map for caching the metamodels
  private Map<String, IMetamodel> metamodels;

  // a helper class to read the metamodel configuration from the Eclipse extension registry
  private MetamodelRegistryReader reader;

  /**
   * Creates a new <code>MetamodelRegistry</code> instance
   */
  public MetamodelRegistry() {
    if (logger.isDebugEnabled()) {
      logger.debug("MetamodelRegistry() - enter"); //$NON-NLS-1$
    }

    // create a new reader and read in the configuration
    reader = new MetamodelRegistryReader();
    reader.read(getExtensionPoint(),this);

    // register the registry as a listener for plugin events
    PlatformUI.getWorkbench().getExtensionTracker().registerHandler(this,
        ExtensionTracker.createExtensionPointFilter(getExtensionPoint()));

    if (logger.isDebugEnabled()) {
      logger.debug("MetamodelRegistry() - exit"); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IMetamodelRegistry#addMetamodel(tudresden.ocl20.pivot.modelbus.IMetamodel)
   */
  public void addMetamodel(IMetamodel metamodel) {
    if (logger.isDebugEnabled()) {
      logger.debug("addMetamodel(metamodel=" + metamodel + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // precondition check
    if (metamodel == null) {
      throw new IllegalArgumentException("The parameter 'metamodel' was null."); //$NON-NLS-1$
    }

    // lazily create the metamodels cache
    if (metamodels == null) {
      metamodels = new HashMap<String, IMetamodel>();
    }
    
    // add the metamodel to the cache
    metamodels.put(metamodel.getId(),metamodel);

    // register with the Eclipse platform if contributed via an extension
    registerMetamodelDescriptor(metamodel);

    if (logger.isDebugEnabled()) {
      logger.debug("addMetamodel() - exit"); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IMetamodelRegistry#getMetamodel(java.lang.String)
   */
  public IMetamodel getMetamodel(String id) {
    return metamodels == null ? null : metamodels.get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IMetamodelRegistry#getMetamodels()
   */
  public IMetamodel[] getMetamodels() {
    return metamodels == null ? new IMetamodel[] {} : metamodels.values().toArray(
        new IMetamodel[metamodels.size()]);
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IMetamodelRegistry#dispose()
   */
  public void dispose() {

    // remove from the extension tracker
    PlatformUI.getWorkbench().getExtensionTracker().unregisterHandler(this);

    // clear metamodels cache
    if (metamodels != null) {
      metamodels.clear();
      metamodels = null;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler#addExtension(org.eclipse.core.runtime.dynamichelpers.IExtensionTracker,
   *      org.eclipse.core.runtime.IExtension)
   */
  public void addExtension(IExtensionTracker tracker, IExtension extension) {
    if (logger.isDebugEnabled()) {
      logger.debug("addExtension(tracker=" + tracker + ", extension=" + extension + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    // use the registry reader to read in the new extension
    reader.read(extension,this);

    if (logger.isDebugEnabled()) {
      logger.debug("addExtension() - exit"); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler#removeExtension(org.eclipse.core.runtime.IExtension,
   *      java.lang.Object[])
   */
  public void removeExtension(IExtension extension, Object[] objects) {
    if (logger.isDebugEnabled()) {
      logger.debug("removeExtension(extension=" + extension + ", objects=" + objects + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    // remove all registered objects from the metamodel cache
    if (metamodels != null) {
      for (int i = 0; i < objects.length; i++) {
        metamodels.remove(objects[i]);
      }
    }

    if (logger.isDebugEnabled()) {
      logger.debug("removeExtension() - exit"); //$NON-NLS-1$
    }
  }

  // helper method to get the worklist task extension point
  private IExtensionPoint getExtensionPoint() {
    IExtensionPoint point;

    // get the point from the registry
    point = Platform.getExtensionRegistry().getExtensionPoint(METAMODEL_EXTENSION_POINT_ID);

    // this should not happen unless the id changes
    if (point == null) {
      throw new IllegalStateException(
          "The extension point for new metamodels could not be found under the id " //$NON-NLS-1$
              + METAMODEL_EXTENSION_POINT_ID);
    }

    return point;
  }

  // helper method to register an Eclipse-based metamodel with the extension tracker
  private void registerMetamodelDescriptor(IMetamodel metamodel) {

    if (metamodel instanceof IMetamodelDescriptor) {
      IMetamodelDescriptor descriptor = (IMetamodelDescriptor) metamodel;

      PlatformUI.getWorkbench().getExtensionTracker().registerObject(
          descriptor.getDeclaringExtension(),descriptor,IExtensionTracker.REF_WEAK);

    }
  }

}
