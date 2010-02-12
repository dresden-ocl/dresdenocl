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
import org.w3c.dom.NodeList;

import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.AbstractModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.modelinstancetype.xml.XmlModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.xml.internal.msg.XmlModelInstanceTypeMessages;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents model objects of a {@link XmlModelInstance}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class XmlModelInstanceObject extends AbstractModelInstanceElement
		implements IModelInstanceObject {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			XmlModelInstanceTypePlugin.getLogger(XmlModelInstanceObject.class);

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
	 *          The {@link Node} for which an {@link XmlModelInstanceObject} shall
	 *          be created.
	 * @param types
	 *          The {@link Type}s this {@link IModelObject} belongs to.
	 * @param factory
	 *          The {@link IModelInstanceFactory} to adapt properties of this
	 *          {@link XmlModelInstanceObject}.
	 */
	protected XmlModelInstanceObject(Node node, Set<Type> types,
			IModelInstanceFactory factory) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "XMLModelInstanceObject("; //$NON-NLS-1$
			msg += "node = " + node; //$NON-NLS-1$
			msg += ", types = " + types; //$NON-NLS-1$
			msg += ", factory = " + factory; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		if (types == null || types.size() == 0) {
			throw new IllegalArgumentException(
					"Parameter 'types' must not be null or empty.");
		}
		// no else.

		if (factory == null) {
			throw new IllegalArgumentException(
					"Parameter 'factory' must not be null.");
		}
		// no else.

		this.adaptedNode = node;
		this.myTypes = types;
		this.modelInstanceFactory = factory;

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "XMLModelInstanceObject(Object, "; //$NON-NLS-1$
			msg += "Set<Type>, IModelInstanceFactory) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #asType(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public IModelInstanceElement asType(Type type) throws AsTypeCastException {

		if (type == null) {
			throw new IllegalArgumentException("Parameter 'type' must not be null.");
		}
		// no else.

		IModelInstanceElement result;

		Set<Type> types;

		types = new HashSet<Type>();
		types.add(type);

		result = null;

		/* If the type can be casted in the model, cast it. */
		for (Type oneOfMyTypes : this.myTypes) {
			if (type.conformsTo(oneOfMyTypes)) {
				result =
						new XmlModelInstanceObject(null, types, this.modelInstanceFactory);
				break;
			}
			// no else.
		}
		// end for.

		/* If no cast has been done, throw an exception. */
		if (result == null) {
			String msg;

			msg =
					XmlModelInstanceTypeMessages.XmlModelInstanceElement_CannotCastToType;
			msg = NLS.bind(msg, this.getName(), type);

			throw new AsTypeCastException(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #copyForAtPre()
	 */
	public IModelInstanceElement copyForAtPre() throws CopyForAtPreException {

		IModelInstanceElement result;

		Node copiedNode;
		copiedNode = this.adaptedNode.cloneNode(true);

		result =
				new XmlModelInstanceObject(copiedNode, this.myTypes,
						this.modelInstanceFactory);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.base.
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
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject
	 * #getObject()
	 */
	public Object getObject() {

		return this.adaptedNode;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject
	 * #getProperty(tudresden.ocl20.pivot.pivotmodel.Property)
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
			if (!property.isMultiple()
					&& (property.getType() instanceof PrimitiveType)
					|| property.getType() instanceof Enumeration) {

				Node propertyNode;
				propertyNode =
						this.adaptedNode.getAttributes().getNamedItem(property.getName());

				if (propertyNode != null) {
					result =
							this.modelInstanceFactory.createModelInstanceElement(
									propertyNode, property.getType());
				}
				// no else (is handled below).
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

					String nodeName;
					nodeName = node.getNodeName().replaceAll("\\.", "");

					// TODO Improve compare strategy
					if (nodeName.equalsIgnoreCase(property.getName())) {
						propertyNodes.add(node);
					}
					// no else.
				}
				// end for (on nodes).

				if (property.isMultiple()) {

					List<IModelInstanceElement> imiList;
					imiList = new ArrayList<IModelInstanceElement>();

					for (Node node : propertyNodes) {
						imiList.add(this.modelInstanceFactory.createModelInstanceElement(
								node, property.getType()));
					}
					// end for.

					if (property.isOrdered()) {

						if (property.isUnique()) {
							result =
									this.modelInstanceFactory.createModelInstanceCollection(
											imiList, OclCollectionTypeKind.ORDEREDSET);
						}

						else {
							result =
									this.modelInstanceFactory.createModelInstanceCollection(
											imiList, OclCollectionTypeKind.SEQUENCE);
						}
						// end else.
					}

					else {
						if (property.isUnique()) {
							result =
									this.modelInstanceFactory.createModelInstanceCollection(
											imiList, OclCollectionTypeKind.SET);
						}

						else {
							result =
									this.modelInstanceFactory.createModelInstanceCollection(
											imiList, OclCollectionTypeKind.BAG);
						}
						// end else.
					}
					// end else.
				}

				else {
					if (propertyNodes.size() > 0) {
						result =
								this.modelInstanceFactory.createModelInstanceElement(
										propertyNodes.get(0), property.getType());
					}

					else {
						result =
								this.modelInstanceFactory.createModelInstanceElement(null,
										property.getType());
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
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.base.
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
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject
	 * #invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation,
	 * java.util.List)
	 */
	public IModelInstanceElement invokeOperation(Operation operation,
			List<IModelInstanceElement> args) throws OperationNotFoundException,
			OperationAccessException {

		if (operation == null) {
			throw new IllegalArgumentException(
					"Parameter 'operation' must not be null.");
		}
		// no else.

		else if (args == null) {
			throw new IllegalArgumentException("Parameter 'args' must not be null.");
		}
		// no else.

		throw new OperationNotFoundException(
				XmlModelInstanceTypeMessages.XmlModelInstanceObject_OperationsAreNotSupported);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #isUndefined()
	 */
	public boolean isUndefined() {

		return this.adaptedNode == null;
	}
}