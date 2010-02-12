/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the XML Model Instance Plug-in of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */

package tudresden.ocl20.pivot.modelinstancetype.xml.internal.modelinstance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.base.AbstractModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelinstancetype.xml.XmlModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.xml.internal.msg.XmlModelInstanceTypeMessages;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;

/**
 * <p>
 * Represents instances of {@link IModel}s represented by XML files.
 * </p>
 * 
 * @author Claas Wilke
 */
public class XmlModelInstance extends AbstractModelInstance {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			XmlModelInstanceTypePlugin.getLogger(XmlModelInstance.class);

	/**
	 * A counter used to generated default names for empty
	 * {@link XmlModelInstance}s.
	 */
	private static int nameCounter = 0;

	/**
	 * <p>
	 * Creates an empty {@link XmlModelInstance}.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} for that the {@link IModelInstance} shall be
	 *          loaded.
	 */
	public XmlModelInstance(IModel model) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "XMLModelInstance("; //$NON-NLS-1$
			msg += ", model = " + model; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		/* Initialize the instance. */
		this.myModel = model;

		this.myName = model.getDisplayName() + "_XMLInstance" + nameCounter;
		nameCounter++;

		this.myModelInstanceFactory =
				new XmlModelInstanceFactory(this.myModel, this);

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;
			msg = "XMLModelInstance(IModel) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/**
	 * <p>
	 * Creates a new {@link XmlModelInstance} for a given XML {@link File} and a
	 * given {@link IModel}.
	 * </p>
	 * 
	 * @param modelInstanceFile
	 *          The {@link File} that represents the XML document.
	 * @param model
	 *          The {@link IModel} for that the {@link IModelInstance} shall be
	 *          loaded.
	 * @throws ModelAccessException
	 *           Thrown, if an error during loading the {@link IModelInstance}
	 *           occurs.
	 */
	public XmlModelInstance(File modelInstanceFile, IModel model)
			throws ModelAccessException {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "XMLModelInstance("; //$NON-NLS-1$
			msg += "modelInstanceFile = " + modelInstanceFile; //$NON-NLS-1$
			msg += ", model = " + model; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		/* Initialize the instance. */
		this.myModel = model;
		this.myName = modelInstanceFile.toString();

		this.myModelInstanceFactory =
				new XmlModelInstanceFactory(this.myModel, this);

		/* Parse the XML file. */
		this.parseXMLFile(modelInstanceFile);

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;
			msg = "XMLModelInstance(File, IModel) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#
	 * addModelInstanceElement(java.lang.Object)
	 */
	public IModelInstanceElement addModelInstanceElement(Object object)
			throws TypeNotFoundInModelException {

		if (object == null) {
			throw new IllegalArgumentException("Parameter 'object' must not be null.");
		}
		// no else.

		IModelInstanceElement result;

		result = this.myModelInstanceFactory.createModelInstanceElement(object);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#getStaticProperty
	 * (tudresden.ocl20.pivot.pivotmodel.Property)
	 */
	public IModelInstanceElement getStaticProperty(Property property)
			throws PropertyAccessException, PropertyNotFoundException {

		if (property == null) {
			throw new IllegalArgumentException(
					"Parameter 'property' must not be null.");
		}
		// no else.

		throw new PropertyNotFoundException(
				XmlModelInstanceTypeMessages.XmlModelInstance_StaticPropertiesAreNotSupported);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#
	 * invokeStaticOperation(tudresden.ocl20.pivot.pivotmodel.Operation,
	 * java.util.List)
	 */
	public IModelInstanceElement invokeStaticOperation(Operation operation,
			List<IModelInstanceElement> args) throws OperationAccessException,
			OperationNotFoundException {

		if (operation == null) {
			throw new IllegalArgumentException(
					"Parameter 'operation' must not be null.");
		}

		else if (args == null) {
			throw new IllegalArgumentException("Parameter 'args' must not be null.");
		}
		// no else.

		throw new OperationNotFoundException(
				XmlModelInstanceTypeMessages.XmlModelInstance_StaticOperationsAreNotSupported);
	}

	/**
	 * <p>
	 * Adds an already adapted {@link IModelInstanceObject} to this
	 * {@link IModelInstance}.
	 * </p>
	 */
	protected void addModelInstanceObject(IModelInstanceObject imiObject) {

		this.myModelInstanceObjects.add(imiObject);
		this.initializeTypeMapping();
	}

	/**
	 * <p>
	 * Parses a given {@link File} as XML file and adds all its elements to this
	 * {@link IModelInstance}.
	 * </p>
	 * 
	 * @param modelInstanceFile
	 *          The {@link File} that shall be parsed.
	 * @throws ModelAccessException
	 *           Thrown, if an error during parsing or {@link IModelObject}
	 *           adaptation occurs.
	 */
	private void parseXMLFile(File modelInstanceFile) throws ModelAccessException {

		/* Parse the XML file. */
		try {
			DocumentBuilder documentBuilder;
			Document instanceDocument;
			Node rootNode;

			documentBuilder =
					DocumentBuilderFactory.newInstance().newDocumentBuilder();
			instanceDocument = documentBuilder.parse(modelInstanceFile);
			rootNode = instanceDocument.getFirstChild();

			/* Add the container node. */
			this.addModelInstanceElement(rootNode);

			/* Initialize the caching maps for the operations getObjectsOfType etc. */
			this.initializeTypeMapping();
		}
		// end try.

		catch (ParserConfigurationException e) {
			String msg;
			msg = XmlModelInstanceTypeMessages.XMLModelInstance_ParserWrongConfigured;

			LOGGER.error(msg);
			throw new ModelAccessException(msg, e);
		}

		catch (SAXException e) {
			String msg;

			msg = XmlModelInstanceTypeMessages.XMLModelInstance_SaxExceptionOccurred;
			NLS.bind(msg, e.getMessage());

			LOGGER.error(msg);
			throw new ModelAccessException(msg, e);
		}

		catch (IOException e) {
			String msg;

			msg = XmlModelInstanceTypeMessages.XMLModelInstance_IOExceptionOccurred;
			NLS.bind(msg, e.getMessage());

			LOGGER.error(msg);
			throw new ModelAccessException(msg, e);
		}

		catch (TypeNotFoundInModelException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}
		// end catch.
	}
}