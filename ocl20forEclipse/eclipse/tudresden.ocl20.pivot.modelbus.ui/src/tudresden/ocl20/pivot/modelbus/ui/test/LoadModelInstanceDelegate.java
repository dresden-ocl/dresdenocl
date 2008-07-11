/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
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
package tudresden.ocl20.pivot.modelbus.ui.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.ActionDelegate;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelRegistry;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class LoadModelInstanceDelegate extends ActionDelegate implements
		IWorkbenchWindowActionDelegate {

	// The window
	private IWorkbenchWindow window;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionDelegate#dispose()
	 */
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		IModelRegistry registry = ModelBusPlugin.getModelRegistry();

		IModel[] models = registry.getModels();

		IModel umlexample = null, pmlexample = null;

		Iterator<IModel> it = Arrays.asList(models).iterator();

		while (it.hasNext()) {
			IModel m = it.next();
			if (m.getDisplayName().endsWith("UML-Beispiel.xmi"))
				umlexample = m;
			if (m.getDisplayName().endsWith("pml.ecore"))
				pmlexample = m;
		}

		URL miUrl = null;

		try {
			miUrl = new URL(
					"file:/D:/Dokumente und Einstellungen/Ronny Brandt/Eclipse/workspaceDA1/tudresden.ocl20.pivot.examples.living/src/tudresden/ocl20/pivot/examples/living/ModelProviderClass.java");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		IModelInstance umlexampleInstance = null;

		try {
			umlexampleInstance = umlexample.getModelInstanceProvider()
					.getModelInstance(miUrl);
			ModelBusPlugin.getModelInstanceRegistry().addModelInstance(
					umlexample, umlexampleInstance);
		} catch (ModelAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ModelBusPlugin.getModelInstanceRegistry().setActiveModelInstance(
				umlexample, umlexampleInstance);

		IModelInstance pmlexampleInstance = null;

		URI modelURI = null;
		URL modelURL = null;
		try {
			modelURL = FileLocator.resolve(FileLocator.find(Platform
					.getBundle("tudresden.ocl20.pivot.examples.pml"), new Path(
					"model/My.pml"), null));
			modelURI = URI.createURI(modelURL.toString());
		} catch (Exception e) {
		}

		try {
			pmlexampleInstance = pmlexample.getModelInstanceProvider()
					.getModelInstance(modelURL);
			ModelBusPlugin.getModelInstanceRegistry().addModelInstance(
					pmlexample, pmlexampleInstance);
		} catch (ModelAccessException e1) {
			e1.printStackTrace();
		}

		IModelInstance pmlexampleInstanceETS = null;

		try {
			modelURL = FileLocator.resolve(FileLocator.find(Platform
					.getBundle("tudresden.ocl20.pivot.examples.pml"), new Path(
					"model/ExpressionTestSuite.pml"), null));
			modelURI = URI.createURI(modelURL.toString());
		} catch (Exception e) {
		}

		try {
			pmlexampleInstanceETS = pmlexample.getModelInstanceProvider()
					.getModelInstance(modelURL);
			ModelBusPlugin.getModelInstanceRegistry().addModelInstance(
					pmlexample, pmlexampleInstanceETS);
		} catch (ModelAccessException e1) {
			e1.printStackTrace();
		}

		IModelInstance pmlexampleInstanceCT = null;

		try {
			modelURL = FileLocator.resolve(FileLocator.find(Platform
					.getBundle("tudresden.ocl20.pivot.examples.pml"), new Path(
					"model/CachingTest.pml"), null));
			modelURI = URI.createURI(modelURL.toString());
		} catch (Exception e) {
		}

		try {
			pmlexampleInstanceCT = pmlexample.getModelInstanceProvider()
					.getModelInstance(modelURL);
			ModelBusPlugin.getModelInstanceRegistry().addModelInstance(
					pmlexample, pmlexampleInstanceCT);
		} catch (ModelAccessException e1) {
			e1.printStackTrace();
		}

		ModelBusPlugin.getModelInstanceRegistry().setActiveModelInstance(
				pmlexample, pmlexampleInstance);
	}
}
