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

import org.apache.log4j.Logger;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;

import tudresden.ocl20.pivot.modelbus.IMetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;

/**
 * A simple helper class that can fill an {@link IMetamodelRegistry} with metamodels read from
 * extensions of the 'metamodels' extension point.
 * 
 * @author Matthias Braeuer
 * @version 1.0 03.04.2007
 */
public class MetamodelRegistryReader {

  // a Logger for this class
  private static final Logger logger = ModelBusPlugin.getLogger(MetamodelRegistryReader.class);

  /**
   * @param extensionPoint
   * @param registry
   */
  public void read(IExtensionPoint extensionPoint, IMetamodelRegistry registry) {
    IExtension[] extensions = extensionPoint.getExtensions();

    for (int i = 0; i < extensions.length; i++) {
      read(extensions[i],registry);
    }
  }

  /**
   * @param extension
   * @param registry
   */
  public void read(IExtension extension, IMetamodelRegistry registry) {
    IConfigurationElement[] elements = extension.getConfigurationElements();

    for (int i = 0; i < elements.length; i++) {
      read(elements[i],registry);
    }
  }

  /**
   * @param configElement
   * @param registry
   */
  public void read(IConfigurationElement configElement, IMetamodelRegistry registry) {

    if (configElement.getName().equals(IModelBusConstants.TAG_METAMODEL)) {

      try {
        registry.addMetamodel(new MetamodelDescriptor(configElement));
      }

      catch (Exception e) {
        logger.warn("An error was encountered when reading config element " + configElement,e); //$NON-NLS-1$
      }

    }

    else {
      logger.warn("Unable to read config element " + configElement //$NON-NLS-1$
          + " because its tag name is not recognized."); //$NON-NLS-1$
    }

  }
}
