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
 */
package tudresden.ocl20.pivot.metamodels.mof.internal.model;

import org.apache.log4j.Logger;

import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.ModelManagerException;
import tudresden.ocl20.core.jmi.mof14.impl.model.ModelHelper;
import tudresden.ocl20.core.jmi.mof14.model.ModelPackage;
import tudresden.ocl20.core.jmi.mof14.model.MofPackage;
import tudresden.ocl20.pivot.metamodels.mof.MofMetamodelPlugin;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.base.AbstractModel;
import tudresden.ocl20.pivot.models.mdr.internal.provider.MdrModelInstanceProvider;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

/**
 * The root of the model is retrieved from {@link ModelHelper} with method
 * <code>getTopPackage()</code> and a corresponding {@link MofPackage} adapter
 * will be created.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class MofModel extends AbstractModel implements IModel {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MofModel.class);

	// the ModelPackage containing the corresponding MOF model
	private ModelPackage modelPackage;

	// the adapter for the top package of the associated MOF model
	private Namespace rootNamespace;

	private String metamodelname;

	/**
	 * Creates a new <code>MofModel</code> loading the {@link ModelPackage}
	 * with the given name from the repository.
	 * 
	 * @param modelname
	 *            the name of the model in the repository
	 */
	public MofModel(String modelname) {
		super(modelname, ModelBusPlugin.getMetamodelRegistry().getMetamodel(
				MofMetamodelPlugin.ID));
		if (logger.isDebugEnabled()) {
			logger
					.debug("MofModel(String modelname=" + modelname
							+ ") - enter");
		}

		metamodelname = modelname+"_as_MM";

		try {
			modelPackage = (ModelPackage) ModelManager.getInstance().getModel(
					modelname);
		} catch (ModelManagerException e) {
			logger.error("MofModel(String)", e);

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("MofModel(String) - exit");
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

		IModelInstanceProvider mip = new MdrModelInstanceProvider(metamodelname);

		if (logger.isDebugEnabled()) {
			logger.debug("getModelInstanceProvider() - exit - return value="
					+ null);
		}
		return mip;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModel#getRootNamespace()
	 */
	public Namespace getRootNamespace() throws ModelAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getRootNamespace() - enter");
		}

		if (rootNamespace == null) {
			MofPackage topPackage = (MofPackage) ModelHelper.getInstance(
					modelPackage).getTopPackage();
			rootNamespace = MofAdapterFactory.INSTANCE
					.createNamespace(topPackage);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getRootNamespace() - exit - return value="
					+ rootNamespace);
		}
		return rootNamespace;
	}
}
