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
package org.dresdenocl.modelinstancetype.xml.internal.modelinstance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.modelinstance.base.AbstractModelInstance;
import org.dresdenocl.modelinstancetype.exception.AsTypeCastException;
import org.dresdenocl.modelinstancetype.exception.CopyForAtPreException;
import org.dresdenocl.modelinstancetype.exception.OperationAccessException;
import org.dresdenocl.modelinstancetype.exception.OperationNotFoundException;
import org.dresdenocl.modelinstancetype.exception.PropertyAccessException;
import org.dresdenocl.modelinstancetype.exception.PropertyNotFoundException;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceFactory;
import org.dresdenocl.modelinstancetype.types.IModelInstanceObject;
import org.dresdenocl.modelinstancetype.types.base.AbstractModelInstanceObject;
import org.dresdenocl.modelinstancetype.xml.XmlModelInstanceTypePlugin;
import org.dresdenocl.modelinstancetype.xml.internal.msg.XmlModelInstanceTypeMessages;
import org.dresdenocl.pivotmodel.Enumeration;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Represents model objects of a {@link XmlModelInstance}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class XmlModelInstanceObject extends AbstractModelInstanceObject
		implements IModelInstanceObject {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = XmlModelInstanceTypePlugin
			.getLogger(XmlModelInstanceObject.class);

	/** The XML {@link Node} adapted by this {@link XmlModelInstanceObject}. */
	protected Node adaptedNode;

	/**
	 * The {@link IModelInstanceFactory} to adapt properties of this
	 * {@link XmlModelInstanceObject}.
	 */
	protected IModelInstanceFactory modelInstanceFactory;

	/**
	 * <p>
	 * Creates a new {@link XmlModelInstanceObject}.
	 * </p>
	 * 
	 * @param node
	 *            The {@link Node} for which an {@link XmlModelInstanceObject}
	 *            shall be created.
	 * @param type
	 *            The {@link Type} this {@link IModelObject} belongs to.
	 * @param originalType
	 *            The original {@link Type} of this
	 *            {@link XmlModelInstanceObject} (required if a casted
	 *            {@link XmlModelInstanceObject} shall be recasted to a
	 *            sub-type.
	 * @param factory
	 *            The {@link IModelInstanceFactory} to adapt properties of this
	 *            {@link XmlModelInstanceObject}.
	 */
	protected XmlModelInstanceObject(Node node, Type type, Type originalType,
			IModelInstanceFactory factory) {

		super(type, originalType);

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "XMLModelInstanceObject("; //$NON-NLS-1$
			msg += "node = " + node; //$NON-NLS-1$
			msg += ", type = " + type; //$NON-NLS-1$
			msg += ", originalType = " + originalType; //$NON-NLS-1$
			msg += ", factory = " + factory; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		if (factory == null) {
			throw new IllegalArgumentException(
					"Parameter 'factory' must not be null.");
		}
		// no else.

		this.adaptedNode = node;
		this.modelInstanceFactory = factory;

		StringBuffer nameBuffer = new StringBuffer();
		if (this.adaptedNode != null) {
			if (this.adaptedNode.getNodeName() != null) {
				nameBuffer.append(this.adaptedNode.getNodeName());
				nameBuffer.append(": ");
			}
			// no else.
			if (this.adaptedNode.getNodeValue() != null)
				nameBuffer.append(this.adaptedNode.getNodeValue());
			else {
				nameBuffer.append("TextContent[");
				nameBuffer.append(this.adaptedNode.getTextContent().trim());
				nameBuffer.append("]");
			}
		}
		// no else.
		this.myName = nameBuffer.toString();

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "XMLModelInstanceObject(Object, "; //$NON-NLS-1$
			msg += "Type, Type, IModelInstanceFactory) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelbus.modelinstance.types.IModelInstanceElement
	 * #asType(org.dresdenocl.pivotmodel.Type)
	 */
	public IModelInstanceElement asType(Type type) throws AsTypeCastException {

		if (type == null) {
			throw new IllegalArgumentException(
					"Parameter 'type' must not be null.");
		}
		// no else.

		IModelInstanceElement result;

		Set<Type> types;

		types = new HashSet<Type>();
		types.add(type);

		result = null;

		/* If the type can be casted in the model, cast it. */
		if (this.getOriginalType().conformsTo(type)) {
			result = new XmlModelInstanceObject(this.adaptedNode, type,
					this.getOriginalType(), this.modelInstanceFactory);
		}
		// no else.

		/* If no cast has been done, throw an exception. */
		if (result == null) {
			String msg;

			msg = XmlModelInstanceTypeMessages.XmlModelInstanceElement_CannotCastToType;
			msg = NLS.bind(msg, this.getName(), type);

			throw new AsTypeCastException(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelbus.modelinstance.types.IModelInstanceElement
	 * #copyForAtPre()
	 */
	public IModelInstanceElement copyForAtPre() throws CopyForAtPreException {

		IModelInstanceElement result;

		Node copiedNode;
		copiedNode = this.adaptedNode.cloneNode(true);

		result = new XmlModelInstanceObject(copiedNode, this.myType,
				this.getOriginalType(), this.modelInstanceFactory);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {

		boolean result;

		if (object == null) {
			result = false;
		}

		else if (this == object) {
			result = true;
		}

		else if (this.getClass() != object.getClass()) {
			result = false;
		}

		else {
			XmlModelInstanceObject other;
			other = (XmlModelInstanceObject) object;

			if (this.adaptedNode == null) {
				if (other.adaptedNode != null) {
					result = false;
				}

				else {
					result = true;
				}
			}

			else if (!this.adaptedNode.equals(other.adaptedNode)) {
				result = false;
			}

			else {
				result = true;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelbus.modelinstance.types.IModelInstanceObject
	 * #getObject()
	 */
	public Object getObject() {

		return this.adaptedNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelbus.modelinstance.types.IModelInstanceObject
	 * #getProperty(org.dresdenocl.pivotmodel.Property)
	 */
	public IModelInstanceElement getProperty(Property property)
			throws PropertyAccessException, PropertyNotFoundException {

		if (property == null) {
			throw new IllegalArgumentException(
					"Parameter 'property' must not be null.");
		}
		// no else.

		IModelInstanceElement result;
		result = null;

		if (!this.isUndefined()) {

			/* Probably handle non-multiple primitive properties. */
			if ((!(property.getType() instanceof CollectionType))
					&& (property.getType() instanceof PrimitiveType)
					|| property.getType() instanceof Enumeration) {

				for (int index = 0; index < this.adaptedNode.getAttributes()
						.getLength(); index++) {

					Node propertyNode;
					propertyNode = this.adaptedNode.getAttributes().item(index);

					if (propertyNode.getNodeName().equalsIgnoreCase(
							property.getName())) {
						result = AbstractModelInstance.adaptInvocationResult(
								propertyNode, property.getType(),
								this.modelInstanceFactory);
						break;
					}
					// no else.
				}
				// end for.

				if (result == null
						&& property.getName().equalsIgnoreCase("value")
						&& this.adaptedNode.getTextContent() != null
						&& this.adaptedNode.getTextContent().length() > 0) {

					/*
					 * Adapt the text content of the node as a primitive type
					 * (is done inside the factory).
					 */
					result = AbstractModelInstance.adaptInvocationResult(
							this.adaptedNode, property.getType(),
							this.modelInstanceFactory);
				}
				// no else.
			}
			// no else.

			if (result == null) {
				NodeList containedNodes;
				containedNodes = this.adaptedNode.getChildNodes();

				List<Node> propertyNodes;
				propertyNodes = new ArrayList<Node>();

				/* Collect all nodes that belong to the given property. */
				for (int index = 0; index < containedNodes.getLength(); index++) {

					Node node;
					node = containedNodes.item(index);

					/* FIXME Probably solve this problem in the model. */
					String nodeName;
					nodeName = node.getNodeName().replaceAll("\\.", "");

					if (nodeName.equalsIgnoreCase(property.getName())) {
						propertyNodes.add(node);
					}
					// no else.
				}
				// end for (on nodes).

				if (property.getType() instanceof CollectionType) {

					List<IModelInstanceElement> imiList;
					imiList = new ArrayList<IModelInstanceElement>();

					for (Node node : propertyNodes) {
						imiList.add(this.modelInstanceFactory
								.createModelInstanceElement(node,
										((CollectionType) property.getType())
												.getElementType()));
					}
					// end for.

					result = AbstractModelInstance.adaptInvocationResult(
							imiList, property.getType(),
							this.modelInstanceFactory);
				}

				else {
					if (propertyNodes.size() > 0) {
						result = AbstractModelInstance.adaptInvocationResult(
								propertyNodes.get(0), property.getType(),
								this.modelInstanceFactory);
					}

					else {
						result = AbstractModelInstance.adaptInvocationResult(
								null, property.getType(),
								this.modelInstanceFactory);
					}
				}
			}
			// no else.
		}
		// no else (undefined).

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;

		int result;
		result = 42;

		result = prime * result;

		if (this.adaptedNode != null) {
			result += this.adaptedNode.hashCode();
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelbus.modelinstance.types.IModelInstanceObject
	 * #invokeOperation(org.dresdenocl.pivotmodel.Operation,
	 * java.util.List)
	 */
	public IModelInstanceElement invokeOperation(Operation operation,
			List<IModelInstanceElement> args)
			throws OperationNotFoundException, OperationAccessException {

		if (operation == null) {
			throw new IllegalArgumentException(
					"Parameter 'operation' must not be null.");
		}
		// no else.

		else if (args == null) {
			throw new IllegalArgumentException(
					"Parameter 'args' must not be null.");
		}
		// no else.

		throw new OperationNotFoundException(
				XmlModelInstanceTypeMessages.XmlModelInstanceObject_OperationsAreNotSupported);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelbus.modelinstance.types.IModelInstanceElement
	 * #isUndefined()
	 */
	public boolean isUndefined() {

		return this.adaptedNode == null;
	}
}