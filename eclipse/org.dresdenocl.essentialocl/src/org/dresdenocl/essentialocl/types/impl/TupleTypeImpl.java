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
package tudresden.ocl20.pivot.essentialocl.types.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEList;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.essentialocl.types.TupleType;
import tudresden.ocl20.pivot.essentialocl.types.TypesFactory;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl;
import tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl;
import tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Tuple Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.types.impl.TupleTypeImpl#getOclLibrary <em>Ocl Library</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TupleTypeImpl extends TypeImpl implements TupleType {

	/**
	 * An adapter that listens for changes in the properties list of this tuple
	 * type
	 */
	protected class PropertiesChangedAdapter extends AdapterImpl implements
			Adapter {

		// an adapter that will listen for changes to a property's type and name
		private Adapter propertyChangeAdapter = new AdapterImpl() {

			@Override
			public void notifyChanged(Notification msg) {

				switch (msg.getFeatureID(Property.class)) {
				case PivotModelPackageImpl.PROPERTY__NAME:
				case PivotModelPackageImpl.PROPERTY__TYPE:
					updateName();
				}
			}
		};

		@SuppressWarnings("unchecked")
		@Override
		public void notifyChanged(Notification msg) {

			if (msg.getFeatureID(TupleType.class) == TypesPackageImpl.TUPLE_TYPE__OWNED_PROPERTY) {

				// attach the PropertyChangeAdapter to added properties or remove it
				switch (msg.getEventType()) {
				case Notification.ADD:
					((PropertyImpl) msg.getNewValue()).eAdapters().add(
							propertyChangeAdapter);
					break;
				case Notification.ADD_MANY:
					for (PropertyImpl p : ((List<PropertyImpl>) msg.getNewValue())) {
						p.eAdapters().add(propertyChangeAdapter);
					}
					break;
				case Notification.REMOVE:
					((PropertyImpl) msg.getOldValue()).eAdapters().remove(
							propertyChangeAdapter);
					break;
				case Notification.REMOVE_MANY:
					for (PropertyImpl p : ((List<PropertyImpl>) msg.getNewValue())) {
						p.eAdapters().remove(propertyChangeAdapter);
					}
					break;
				}

				// update the name
				updateName();
			}
		}
	}

	/**
	 * The cached value of the '{@link #getOclLibrary() <em>Ocl Library</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclLibrary()
	 * @generated
	 * @ordered
	 */
	protected OclLibrary oclLibrary;

	// a map of tuple types that have been created previously
	private static Map<String, TupleType> tupleTypes;

	// a logger for this class
	private static final Logger logger = EssentialOclPlugin
			.getLogger(TupleTypeImpl.class);

	/**
	 * This is an additional operation defined in the OCL 2.0 Specification
	 * (Section 8.3.9). The operation returns a new <code>TupleType</code>. It is
	 * specified as follows:
	 * 
	 * <pre>
	 * context TupleType::make(atts : Sequence(Property) ) : TupleType
	 * post: Sequence{1..atts-&gt;size()}-&gt;forAll(i | result.ownedAttribute.at(i).cmpSlots(atts.at(i))
	 * </pre>
	 * 
	 * Note that this specification suggests that the given properties are NOT
	 * meant to be contained in the new tuple type. Thus, this implementation
	 * simply clones the given properties. This is necessary to avoid that the
	 * given properties are removed from another containment list.
	 * 
	 * @param atts
	 * @return
	 */
	public static TupleType make(List<Property> atts) {

		if (logger.isDebugEnabled()) {
			logger.debug("makeTupleType(atts=" + atts + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		String name;
		TupleType tupleType;

		// determine the name of the requested tuple type
		name = determineTupleTypeName(atts);

		// try to get a previously created tuple type with this name
		tupleType = getTupleTypes().get(name);

		// create a new tuple type if necessary
		if (tupleType == null) {
			tupleType = TypesFactory.INSTANCE.createTupleType();

			// clone the properties and add them to the new tuple type
			for (Property property : atts) {
				tupleType.getOwnedProperty().add(property.clone());
			}

			getTupleTypes().put(name, tupleType);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("makeTupleType() - exit - return value=" + tupleType); //$NON-NLS-1$
		}

		return tupleType;
	}

	/**
	 * Returns the <code>Map</code> with the cached OCL <code>Tuple</code> types.
	 * Lazily creates this map on demand.
	 * 
	 * @return a {@code Map<String,TupleType>} instance
	 */
	protected static Map<String, TupleType> getTupleTypes() {

		if (tupleTypes == null) {
			tupleTypes = new HashMap<String, TupleType>();
		}

		return tupleTypes;
	}

	/**
	 * Helper method that determines the name of a <code>TupleType</code> from a
	 * given list of properties based on the invariant given in the OCL
	 * specification. See {@link #getName()} for a full specification of this
	 * invariant.
	 * 
	 * @return a <code>String</code> with the name of a <code>TupleType</code>
	 *         with these properties
	 * 
	 * @see #getName()
	 */
	public static String determineTupleTypeName(List<Property> properties) {

		if (logger.isDebugEnabled()) {
			logger
					.debug("determineTupleTypeName(properties=" + properties + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		Property[] propertiesArray;
		StringBuilder name;

		// cache the properties in an array
		// TODO: investigate concurrency issues and concurrent modification
		// potential
		propertiesArray = properties.toArray(new Property[properties.size()]);

		// create a string buffer to store the name
		name = new StringBuilder("Tuple").append('('); //$NON-NLS-1$

		for (int i = 0; i < propertiesArray.length; i++) {

			if (i > 0) {
				name.append(',');
			}

			// append the name of the property
			name.append(propertiesArray[i].getName()).append(':');

			// append the name of the type of the property
			Type propertyType = propertiesArray[i].getType();
			if (propertyType != null) {
				name.append(propertyType.getName());
			}

			else {
				logger
						.warn("The property '" + propertiesArray[i].getName() + "' has no type!"); //$NON-NLS-1$ //$NON-NLS-2$
				name.append("null"); //$NON-NLS-1$
			}

		}

		name.append(')');

		if (logger.isDebugEnabled()) {
			logger.debug("determineTupleTypeName() - exit - return value=" + name); //$NON-NLS-1$
		}

		return name.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected TupleTypeImpl() {

		super();

		// tuples may not have operations, so we prevent this with an unmodifiable
		// list
		ownedOperation =
				new EcoreEList.UnmodifiableEList<Operation>(this,
						PivotModelPackageImpl.Literals.TYPE__OWNED_OPERATION, 0,
						new Operation[] {});

		// add an adapter that listens for changes to the owned properties
		eAdapters().add(new PropertiesChangedAdapter());
	}

	/**
	 * The name of a tuple type includes the names of the individual parts and the
	 * types of those parts (OCL 2.0 specification, Section 8.2.2).
	 * 
	 * <p>
	 * The corresponding OCL invariant is defined as follows:
	 * 
	 * <pre>
	 * context TupleType
	 * inv: name =
	 *   ‘Tuple(‘.concat (
	 *      Sequence{1..ownedProperty()-&gt;size()}-&gt;iterate (pn; s: String = ‘’ |
	 *         let p: Attribute = allProperties()-&gt;at (pn) in (
	 *            s.concat (if (pn&gt;1) then ‘,’ else ‘’ endif)
	 *            .concat (p.name).concat (':')
	 *            .concat (p.type.name)
	 *         )
	 *      )
	 *   ).concat (')')
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getName()
	 */
	@Override
	public String getName() {

		// update the name if it hasn't been calculated so far
		if (name == NAME_EDEFAULT) {
			updateName();
		}

		return name;
	}

	/**
	 * Overridden to prevent clients from changing the name of the
	 * <code>TupleType</code>. This method will throw an
	 * {@link UnsupportedOperationException}.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#setName(java.lang.String)
	 */
	@Override
	public final void setName(String newName) {

		throw new UnsupportedOperationException(
				"The name of tuple types cannot be changed,"); //$NON-NLS-1$
	}

	/**
	 * Helper method that updates the name of the <code>TupleType</code>.
	 */
	protected void updateName() {

		super.setName(determineTupleTypeName(allProperties()));
	}

	/**
	 * Simply returns the name of the <code>TupleType</code>. As a member of the
	 * OCL Standard Library, a <code>TupleType</code> does not really have a
	 * namespace. It is implicitly available in all namespaces.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getQualifiedName()
	 */
	@Override
	public final String getQualifiedName() {

		// prevent stack overflows when the qualified name of contained properties
		// is requested while the name of the tuple type is being determined
		if (name == NAME_EDEFAULT) {
			return ""; //$NON-NLS-1$
		}

		return getName();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclLibrary getOclLibrary() {

		return oclLibrary;
	}

	/**
	 * <!-- begin-user-doc --> The reference to the {@link OclLibrary} facade
	 * should be set after a <code>TupleType</code> instance is created.
	 * Otherwise, there might be errors when determining type conformance.<!--
	 * end-user-doc -->
	 * @generated
	 */
	public void setOclLibrary(OclLibrary newOclLibrary) {

		OclLibrary oldOclLibrary = oclLibrary;
		oclLibrary = newOclLibrary;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.TUPLE_TYPE__OCL_LIBRARY, oldOclLibrary, oclLibrary));
	}

	/**
	 * Tuple types conform to each other when their names and types conform to
	 * each other (OCL 2.0 specification, Section 8.2.2).
	 * 
	 * <p>
	 * The corresponding OCL invariant is defined as follows:
	 * 
	 * <pre>
	 * context TupleType
	 * inv: TupleType.allInstances()-&gt;forAll (t |
	 *    t.allProperties()-&gt;forAll (tp |
	 *    
	 *       -- make sure at least one tuplepart has the same name
	 *       -- (uniqueness of tuplepart names will ensure that not two
	 *       -- tupleparts have the same name within one tuple)
	 *       self.allProperties()-&gt;exists(stp|stp.name = tp.name) and
	 *       
	 *       -- make sure that all tupleparts with the same name conforms
	 *       self.allProperties()-&gt;forAll(
	 *          stp | (stp.name = tp.name) and stp.type.conformsTo(tp.type))
	 *    )
	 *    
	 *    implies self.conformsTo(t)
	 * )
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#conformsTo(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	@Override
	public boolean conformsTo(Type other) {

		if (logger.isDebugEnabled()) {
			logger.debug("conformsTo(other=" + other + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		boolean conformant;

		// by default no conformance
		conformant = false;

		// check other metatype
		if (other instanceof TupleType) {
			TupleType otherTupleType = (TupleType) other;

			// check for all properties of the other tuple type
			for (Property otherProperty : otherTupleType.allProperties()) {
				Property ownProperty = lookupProperty(otherProperty.getName());

				// no property with this name found
				if (ownProperty == null) {
					conformant = false;
				}

				else {
					Type ownPropertyType = ownProperty.getType();
					conformant =
							(ownPropertyType != null) ? ownPropertyType
									.conformsTo(otherProperty.getType()) : false;
				}
			}
		}

		else {
			conformant = super.conformsTo(other); // this will only return true if
			// other is OclAny
		}

		if (logger.isDebugEnabled()) {
			logger.debug("conformsTo() - exit - return value=" + conformant); //$NON-NLS-1$
		}

		return conformant;
	}

	/**
	 * Overridden to implement special rules to determine the common super type
	 * for tuple types.
	 * 
	 * <p>
	 * If the other type is a <code>TupleType</code> as well, then the common
	 * super type is the "intersection" of the two tuple types. For instance,
	 * <code>TupleType(a:Integer, b:Set(Real))</code> and
	 * <code>TupleType(b:Bag(Real), c:String)</code> yields a common super type
	 * <code>TupleType(b:Collection(Real))</code>.
	 * </p>
	 * 
	 * <p>
	 * If the other type is not a <code>TupleType</code> and not a
	 * {@link CollectionType}, the common super type is <code>OclAny</code>.
	 * </p>
	 * 
	 * <p>
	 * Otherwise, there is no common super type and <code>null</code> is returned.
	 * </p>
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#commonSuperType(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	@Override
	public Type commonSuperType(Type other) {

		if (logger.isDebugEnabled()) {
			logger.debug("commonSuperType(other=" + other + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		Type commonSuperType;

		// by default no common super type
		commonSuperType = null;

		// find a common tuple type if the other one is a tuple type as well
		if (other instanceof TupleType) {
			TupleType otherTupleType = (TupleType) other;
			List<Property> commonProperties = new LinkedList<Property>();

			// iterate over all properties of the other tuple type
			for (Property otherProperty : otherTupleType.allProperties()) {
				String propertyName = otherProperty.getName();
				Property ownProperty = lookupProperty(propertyName);

				// found a matching property in this tuple type
				if (ownProperty != null) {
					Type propertyType = ownProperty.getType();

					if (propertyType != null) {
						propertyType =
								propertyType.commonSuperType(otherProperty.getType());

						// we found a common super type, create a new property with name and
						// this type
						if (propertyType != null) {
							Property commmonProperty =
									PivotModelFactory.eINSTANCE.createProperty();
							commmonProperty.setName(propertyName);
							commmonProperty.setType(propertyType);
							commonProperties.add(commmonProperty);
						}
					}
				}
			}

			// create a new tuple type with the common properties
			commonSuperType = getOclLibrary().makeTupleType(commonProperties);
		}

		else {
			// will return OclAny if the other type is not a collection type
			commonSuperType = super.commonSuperType(other);
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("commonSuperType() - exit - return value=" + commonSuperType); //$NON-NLS-1$
		}

		return commonSuperType;
	}

	/**
	 * Overridden because <code>TupleType</code> instances may not have
	 * operations. This method will throw a
	 * <code>UnsupportedOperationException</code>.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#addOperation(tudresden.ocl20.pivot.pivotmodel.Operation)
	 */
	@Override
	public Type addOperation(Operation o) {

		if (logger.isDebugEnabled()) {
			logger.debug("addOperation(o=" + o + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		throw new UnsupportedOperationException("Tuples may not own operations!"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#clone()
	 */
	@Override
	public TupleType clone() {

		return initialize(TypesFactory.INSTANCE.createTupleType());
	}

	/**
	 * Overridden to additionally set the reference to the {@link OclLibrary}.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#initialize(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	protected TupleType initialize(TupleType clone) {

		super.initialize(clone);

		// set reference to OCL library
		clone.setOclLibrary(getOclLibrary());

		return clone;
	}

	/**
	 * Overridden to indicate that the name of a <code>TupleType</code> is
	 * determined automatically.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#hasVolatileName()
	 */
	@Override
	protected boolean hasVolatileName() {

		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {

		switch (featureID) {
		case TypesPackageImpl.TUPLE_TYPE__OCL_LIBRARY:
			return getOclLibrary();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {

		switch (featureID) {
		case TypesPackageImpl.TUPLE_TYPE__OCL_LIBRARY:
			setOclLibrary((OclLibrary) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {

		switch (featureID) {
		case TypesPackageImpl.TUPLE_TYPE__OCL_LIBRARY:
			setOclLibrary((OclLibrary) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * The EMF implementation is adapted to prevent that the name of the
	 * <code>TupleType</code> is serialized to XMI. This is necessary to prevent
	 * setting the name upon loading the document which would throw an exception.
	 * 
	 * @generated NOT
	 * 
	 * @see #setName(String)
	 */
	@Override
	public boolean eIsSet(int featureID) {

		switch (featureID) {
		case TypesPackageImpl.TUPLE_TYPE__OCL_LIBRARY:
			return oclLibrary != null;
		case TypesPackageImpl.TUPLE_TYPE__NAME:
			return false;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return TypesPackageImpl.Literals.TUPLE_TYPE;
	}

} // TupleTypeImpl
