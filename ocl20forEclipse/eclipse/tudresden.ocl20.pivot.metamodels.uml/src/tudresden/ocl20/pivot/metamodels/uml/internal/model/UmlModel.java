/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).            *
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
 * $Id: UmlModel.java,v 1.1 2007/05/09 17:49:03 robra81 Exp $
 */
package tudresden.ocl20.pivot.metamodels.uml.internal.model;

import org.apache.log4j.Logger;

import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.ModelManagerException;
import tudresden.ocl20.core.jmi.uml15.impl.modelmanagement.ModelHelper;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.pivot.metamodels.uml.UmlMetamodelPlugin;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.base.AbstractModel;
import tudresden.ocl20.pivot.models.mdr.internal.provider.MdrModelInstanceProvider;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

/**
 * The root of the model is retrieved from {@link ModelHelper} with method
 * <code>getTopPackage()</code> and a corresponding {@link UmlNamespace}
 * adapter will be created.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class UmlModel extends AbstractModel implements IModel {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UmlModel.class);

  // the Uml15Package containing the corresponding UML model
	private Uml15Package uml15Package;
	
  // the adapter for the top package of the associated UML model
	private Namespace rootNamespace = null;
	
  /**
   * Creates a new <code>UmlModel</code> loading the {@link Uml15Package}
   * with the given name from the repository.
   * 
   * @param modelname the name of the model in the repository
   */
	public UmlModel(String modelname) throws ModelAccessException {
		super(modelname, ModelBusPlugin.getMetamodelRegistry().getMetamodel(UmlMetamodelPlugin.ID));
		if (logger.isDebugEnabled()) {
			logger.debug("UmlModel(String modelname=" + modelname + ") - enter");
		}
		
		try {
			uml15Package = (Uml15Package) ModelManager.getInstance().getModel(modelname);
		} catch (ModelManagerException e) {
			logger.error("UmlModel(String)", e);
			throw new ModelAccessException("Error: Model " + modelname + " not found in repository",e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("UmlModel(String) - exit");
		}
	}

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModel#getModelInstanceProvider()
   */
	public IModelInstanceProvider getModelInstanceProvider() {
		if (logger.isDebugEnabled()) {
			logger.debug("getModelInstanceProvider() - enter");
		}

		IModelInstanceProvider imip = new MdrModelInstanceProvider(getDisplayName(), this);

		if (logger.isDebugEnabled()) {
			logger.debug("getModelInstanceProvider() - exit - return value=" + null);
		}
		return imip;
	}
	
  /**
   * This method creates a {@link Namespace} adapter for the root package in the
   * associated UML model. The root namespace is retrieved or created by the 
   * {@link ModelHelper}.
   * 
   * @throws ModelAccessException if an error occurs when creating the adapter for the top namespace
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModel#getRootNamespace()
   */
	public Namespace getRootNamespace() throws ModelAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getRootNamespace() - enter");
		}

		if (rootNamespace == null)
			rootNamespace = UmlAdapterFactory.INSTANCE.createNamespace(
					ModelHelper.getInstance(uml15Package).getTopPackage());

		if (logger.isDebugEnabled()) {
			logger.debug("getRootNamespace() - exit - return value=" + rootNamespace);
		}
		return rootNamespace;
	}

}
