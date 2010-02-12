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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.w3c.dom.Node;

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelinstancetype.xml.XmlModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.xml.internal.msg.XmlModelInstanceTypeMessages;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * {@link IModelInstanceFactory} implementation for {@link XmlModelInstance}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class XmlModelInstanceFactory extends BasisJavaModelInstanceFactory {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			XmlModelInstanceTypePlugin.getLogger(XmlModelInstanceFactory.class);

	/**
	 * The {@link IModel} for whose {@link Type}s {@link IModelInstanceElement}s
	 * shall be created.
	 */
	private IModel model;

	/**
	 * The {@link XmlModelInstance} for which the {@link IModelInstanceElement}s
	 * shall be created.
	 */
	private XmlModelInstance modelInstance;

	/**
	 * <p>
	 * Creates a new {@link XmlModelInstanceFactory}.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} for whose {@link Type}s
	 *          {@link IModelInstanceElement}s shall be created.
	 * @param modelInstance
	 *          The {@link XmlModelInstance} for which the
	 *          {@link IModelInstanceElement}s shall be created.
	 */
	public XmlModelInstanceFactory(IModel model, XmlModelInstance modelInstance) {

		if (model == null) {
			throw new IllegalArgumentException("Parameter 'model' must not be null.");
		}
		// no else.

		if (modelInstance == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstance' must not be null.");
		}
		// no else.

		this.model = model;
		this.modelInstance = modelInstance;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory
	 * #createModelInstanceElement(java.lang.Object)
	 */
	@Override
	public IModelInstanceElement createModelInstanceElement(Object adapted)
			throws TypeNotFoundInModelException {

		/* FIXME Built-in caching. */
		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceElement("; //$NON-NLS-1$
			msg += "adapted = " + adapted; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceElement result;

		if (adapted instanceof Node) {

			/* FIXME Claas adapt the object according to the found type. */
			Node node;
			node = (Node) adapted;

			result = this.createModelInstanceObject(node);

			if (result instanceof IModelInstanceObject) {
				this.modelInstance
						.addModelInstanceObject((IModelInstanceObject) result);
			}
			// no else.
		}

		else {
			throw new IllegalArgumentException(
					NLS
							.bind(
									XmlModelInstanceTypeMessages.XmlModelInstanceFactory_UnknownClassOfAdaptee,
									adapted.getClass().getCanonicalName()));
		}

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceElement(Object) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory
	 * #createModelInstanceElement(java.lang.Object,
	 * tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	@Override
	public IModelInstanceElement createModelInstanceElement(Object adapted,
			Type type) {

		/* FIXME Built-in caching. */
		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceElement("; //$NON-NLS-1$
			msg += "adapted = " + adapted; //$NON-NLS-1$
			msg += "type = " + type; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceElement result;

		if (adapted == null || adapted instanceof Node) {

			Node node;
			node = null;

			if (adapted != null) {
				node = (Node) adapted;
			}
			// no else.

			/* Probably adapt a literal. */
			if (type instanceof Enumeration) {

				result =
						this
								.createModelInstanceEnumerationLiteral(node, (Enumeration) type);
			}

			/* Else probably adapt a primitive type. */
			else if (type instanceof PrimitiveType) {

				switch (((PrimitiveType) type).getKind()) {

				case BOOLEAN:
					result = this.createModelInstanceBoolean(node, type);
					break;

				case INTEGER:
					result = this.createModelInstanceInteger(node, type);
					break;

				case REAL:
					result = this.createModelInstanceReal(node, type);
					break;

				case STRING:
					result = this.createModelInstanceString(node, type);
					break;

				default:
					// FIXME -> exception.
					result = null;
				}
				// end select.
			}

			else {
				result = this.createModelInstanceObject(node, type);

				this.modelInstance
						.addModelInstanceObject((IModelInstanceObject) result);
			}
		}

		else {
			throw new IllegalArgumentException(
					NLS
							.bind(
									XmlModelInstanceTypeMessages.XmlModelInstanceFactory_UnknownClassOfAdaptee,
									adapted.getClass().getCanonicalName()));
		}

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceElement(Object, Set<Type>) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link IModelInstanceBoolean} for a given {@link Node} and a
	 * given {@link Type}.
	 * 
	 * @param node
	 *          The {@link Node} that shall be adapted.
	 * @param type
	 *          The {@link Type} of the {@link IModelInstanceBoolean} in the
	 *          {@link IModel}.
	 * @return The created {@link IModelInstanceBoolean}.
	 */
	private IModelInstanceBoolean createModelInstanceBoolean(Node node, Type type) {

		IModelInstanceBoolean result;

		/*
		 * Use the java basis types here because the adaptation of a node would not
		 * help. If you adapt a node, cast it to boolean and then to string, you
		 * have to alter the nodes' value to get the right result such as 'true',
		 * 'false' or null in all other cases!
		 */
		if (node == null || node.getTextContent() == null) {
			result = super.createModelInstanceBoolean(null);
		}

		else if (node.getTextContent().trim().equalsIgnoreCase("true")) {
			result = super.createModelInstanceBoolean(true);
		}

		else if (node.getTextContent().trim().equalsIgnoreCase("false")) {
			result = super.createModelInstanceBoolean(false);
		}

		else {
			result = super.createModelInstanceBoolean(null);
		}

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link IModelInstanceEnumerationLiteral} for the given
	 * {@link Node} and the given {@link Enumeration}.
	 * </p>
	 * 
	 * @param node
	 *          The {@link Node} for that an
	 *          {@link IModelInstanceEnumerationLiteral} shall be created.
	 * @param enumeration
	 *          The {@link Enumeration} type for that the
	 *          {@link IModelInstanceEnumerationLiteral} shall be created.
	 * @return The created {@link IModelInstanceEnumerationLiteral}.
	 */
	private IModelInstanceEnumerationLiteral createModelInstanceEnumerationLiteral(
			Node node, Enumeration enumeration) {

		IModelInstanceEnumerationLiteral result;

		if (node == null || node.getTextContent() == null) {
			result = super.createModelInstanceEnumerationLiteral(null);
		}

		else {
			EnumerationLiteral literal;
			literal = null;

			/* Try to find a literal that matches to the node's value. */
			for (EnumerationLiteral aLiteral : enumeration.getOwnedLiteral()) {
				if (aLiteral.getName().equalsIgnoreCase(node.getTextContent().trim())) {
					literal = aLiteral;
					break;
				}
				// no else.
			}
			// end for.

			result = super.createModelInstanceEnumerationLiteral(literal);
		}

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link IModelInstanceInteger} for a given {@link Node} and a
	 * given {@link Type}.
	 * 
	 * @param node
	 *          The {@link Node} that shall be adapted.
	 * @param type
	 *          The {@link Type} of the {@link IModelInstanceInteger} in the
	 *          {@link IModel}.
	 * @return The created {@link IModelInstanceInteger}.
	 */
	private IModelInstanceInteger createModelInstanceInteger(Node node, Type type) {

		IModelInstanceInteger result;

		/*
		 * Use the java basis types here because the adaptation of a node would not
		 * help. If you adapt a node, cast it to integer and then to string, you
		 * have to alter the nodes' value to get the right result such as '1' except
		 * of '1.23', or null in many cases!
		 */
		if (node == null || node.getTextContent() == null) {
			result = super.createModelInstanceInteger(null);
		}

		else {
			Long longValue;
			try {
				longValue =
						new Double(Double.parseDouble(node.getTextContent())).longValue();
			}

			catch (NumberFormatException e) {
				longValue = null;
			}

			result = super.createModelInstanceInteger(longValue);
		}

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link IModelInstanceReal} for a given {@link Node} and a given
	 * {@link Type}.
	 * 
	 * @param node
	 *          The {@link Node} that shall be adapted.
	 * @param type
	 *          The {@link Type} of the {@link IModelInstanceReal} in the
	 *          {@link IModel}.
	 * @return The created {@link IModelInstanceReal}.
	 */
	private IModelInstanceReal createModelInstanceReal(Node node, Type type) {

		IModelInstanceReal result;

		/*
		 * Use the java basis types here because the adaptation of a node would not
		 * help. If you adapt a node, cast it to real and then to string, you have
		 * to alter the nodes' value to get the right result such as '1' except of
		 * '1.0', or null in many cases!
		 */
		if (node == null || node.getTextContent() == null) {
			result = super.createModelInstanceReal(null);
		}

		else {
			Double doubleValue;

			try {
				doubleValue = new Double(Double.parseDouble(node.getTextContent()));
			}

			catch (NumberFormatException e) {
				doubleValue = null;
			}

			result = super.createModelInstanceReal(doubleValue);
		}

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link IModelInstanceString} for a given {@link Node} and a
	 * given {@link Type}.
	 * 
	 * @param node
	 *          The {@link Node} that shall be adapted.
	 * @param type
	 *          The {@link Type} of the {@link IModelInstanceString} in the
	 *          {@link IModel}.
	 * @return The created {@link IModelInstanceString}.
	 */
	private IModelInstanceString createModelInstanceString(Node node, Type type) {

		IModelInstanceString result;

		/*
		 * Use the java basis types here because the adaptation of a node would not
		 * help. If you adapt a node, cast it to integer and then to string, you
		 * have to alter the nodes' value to get the right result such as
		 * 'truefalse' except of null!
		 */
		if (node == null || node.getTextContent() == null) {
			result = super.createModelInstanceString(null);
		}

		else {
			result = super.createModelInstanceString(node.getTextContent());
		}

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link XmlModelInstanceObject} for a given {@link Node}.
	 * 
	 * @param node
	 *          The {@link Node} that shall be adapted.
	 * @return The created {@link XmlModelInstanceObject}.
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if now {@link Type} in the {@link IModel} can be found
	 *           that matches to the given {@link Node}.
	 */
	private XmlModelInstanceObject createModelInstanceObject(Node node)
			throws TypeNotFoundInModelException {

		XmlModelInstanceObject result;

		Type type;
		type = this.findTypeInModel(node);

		result = this.createModelInstanceObject(node, type);

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link XmlModelInstanceObject} for a given {@link Node} and a
	 * given {@link Type}.
	 * 
	 * @param node
	 *          The {@link Node} that shall be adapted.
	 * @param type
	 *          The {@link Type} of the {@link XmlModelInstanceObject} in the
	 *          {@link IModel}.
	 * @return The created {@link XmlModelInstanceObject}.
	 */
	private XmlModelInstanceObject createModelInstanceObject(Node node, Type type) {

		XmlModelInstanceObject result;

		Set<Type> types;
		types = new HashSet<Type>();
		types.add(type);

		result = new XmlModelInstanceObject(node, types, this);

		return result;
	}

	/**
	 * <p>
	 * A helper method that searches for the {@link Type} of a given {@link Node}
	 * in the {@link IModel}.
	 * </p>
	 * 
	 * @param node
	 *          The {@link Node} for that a {@link Type} shall be found.
	 * @return The found {@link Type}.
	 */
	private Type findTypeInModel(Node node) throws TypeNotFoundInModelException {

		Type result;
		result = null;

		String nodeName;
		nodeName = node.getNodeName();

		List<String> pathName;
		pathName = new ArrayList<String>();

		pathName.add(nodeName);

		/* FIXME Claas: Probably handle the node's name space. */
		try {
			result = this.model.findType(pathName);

			if (result == null) {
				throw new TypeNotFoundInModelException(
						NLS
								.bind(
										XmlModelInstanceTypeMessages.XmlModelInstanceFactory_UnknownTypeOfAdaptee,
										node));
			}
			// no else.
		}
		// end try.

		catch (ModelAccessException e) {
			throw new TypeNotFoundInModelException(
					NLS
							.bind(
									XmlModelInstanceTypeMessages.XmlModelInstanceFactory_UnknownTypeOfAdaptee,
									node), e);
		}
		// end catch.

		return result;
	}
}