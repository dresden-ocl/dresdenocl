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
package tudresden.ocl20.pivot.modelbus.metamodel.internal;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.resource.ImageDescriptor;

import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.descriptor.AbstractDescriptor;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodelDescriptor;
import tudresden.ocl20.pivot.modelbus.model.IModelProvider;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 03.04.2007
 */
public class MetamodelDescriptor extends AbstractDescriptor implements IMetamodel,
    IMetamodelDescriptor {

  // a logger for this class
  private static final Logger logger = ModelBusPlugin.getLogger(MetamodelDescriptor.class);

  // the translatable name of the metamodel
  private String name;

  // the icon used for the metamodel
  private ImageDescriptor icon;

  // the cached instance of the model provider
  private IModelProvider modelProvider;

  /**
   * @param configElement
   */
  public MetamodelDescriptor(IConfigurationElement configElement) {
    super(configElement);
    
    if (logger.isDebugEnabled()) {
      logger.debug("MetamodelDescriptor(configElement=" + configElement + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // initialize the descriptor
    loadFromExtension();

    if (logger.isDebugEnabled()) {
      logger.debug("MetamodelDescriptor() - exit"); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IMetamodel#getModelProvider()
   */
  public IModelProvider getModelProvider() {

    if (modelProvider == null) {
      modelProvider = createInstance(IModelBusConstants.ATT_MODELPROVIDER,IModelProvider.class);
    }
    
    return modelProvider;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IMetamodel#getName()
   */
  public String getName() {
    return name;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.internal.IMetamodelDescriptor#getIcon()
   */
  public ImageDescriptor getIcon() {
    
    // lazily create the image descriptor
    if (icon == null) {
      icon = getImageDescriptor(IModelBusConstants.ATT_ICON);
    }
    
    return icon;
  }

  /**
   * Helper method that loads the name and checks the 'itemProvider' attribute
   */
  protected void loadFromExtension(){
    name = getAttribute(IModelBusConstants.ATT_NAME,false);
    
    if (name == null) {
      name = getId();
    }
    
    checkAttribute(IModelBusConstants.ATT_MODELPROVIDER);
  }
}
