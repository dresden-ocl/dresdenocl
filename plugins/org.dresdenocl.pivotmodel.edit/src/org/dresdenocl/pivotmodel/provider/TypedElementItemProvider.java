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
package org.dresdenocl.pivotmodel.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.dresdenocl.pivotmodel.ComplexGenericType;
import org.dresdenocl.pivotmodel.GenericElement;
import org.dresdenocl.pivotmodel.GenericType;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.ParameterGenericType;
import org.dresdenocl.pivotmodel.PivotModelFactory;
import org.dresdenocl.pivotmodel.PivotModelPackage;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.TypeParameter;
import org.dresdenocl.pivotmodel.TypedElement;
import org.dresdenocl.pivotmodel.impl.PivotModelPackageImpl;

/**
 * This is the item provider adapter for a
 * {@link org.dresdenocl.pivotmodel.TypedElement} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class TypedElementItemProvider extends NamedElementItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TypedElementItemProvider(AdapterFactory adapterFactory) {

		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {

		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addTypePropertyDescriptor(object);
			addGenericTypePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * 
	 * <p>
	 * Extended the EMF implementation to add the custom
	 * {@link TypePropertyDescriptor}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	protected void addTypePropertyDescriptor(Object object) {

		itemPropertyDescriptors
				.add(new TypePropertyDescriptor(
						getString("_UI_TypedElement_type_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_TypedElement_type_feature", //$NON-NLS-1$ //$NON-NLS-2$
								"_UI_TypedElement_type"))); //$NON-NLS-1$
	}

	/**
	 * This adds a property descriptor for the Generic Type feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addGenericTypePropertyDescriptor(Object object) {

		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_TypedElement_genericType_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_TypedElement_genericType_feature", "_UI_TypedElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						PivotModelPackage.Literals.TYPED_ELEMENT__GENERIC_TYPE, false,
						false, false, null, null, null));
	}

	/**
	 * A special property descriptor for the type of a {@link TypedElement} that
	 * additionally includes all {@link TypeParameter}s of the {@link Type}.
	 */
	protected class TypePropertyDescriptor extends ItemPropertyDescriptor {

		/**
		 * Creates a new <code>TypePropertyDescriptor</code> instance using the
		 * default EMF
		 */
		public TypePropertyDescriptor(String displayName, String description) {

			super(((ComposeableAdapterFactory) getAdapterFactory())
					.getRootAdapterFactory(), getResourceLocator(), displayName,
					description, PivotModelPackageImpl.Literals.TYPED_ELEMENT__TYPE,
					true, false, true, null, null, null);
		}

		/**
		 * Returns the normal choices (i.e, all {@link Type}s in the model) as well
		 * as the {@link TypeParameter}s of all owning elements.
		 * 
		 * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#getChoiceOfValues(java.lang.Object)
		 */
		@Override
		public Collection<?> getChoiceOfValues(Object object) {

			Collection<Object> result =
					new ArrayList<Object>(super.getChoiceOfValues(object));

			// go up the containment hierachy and collect all type parameters
			for (NamedElement e = (NamedElement) object; e != null; e = e.getOwner()) {

				// the owner (operation, type, namespace) should usually be a
				// GenericElement
				if (e instanceof GenericElement) {
					result.addAll(((GenericElement) e).getOwnedTypeParameter());
				}
			}

			return result;
		}

		/**
		 * 
		 * 
		 * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#setPropertyValue(java.lang.Object,
		 *      java.lang.Object)
		 */
		@Override
		public void setPropertyValue(Object object, Object value) {

			TypedElement typedElement = (TypedElement) object;

			// the value can either be a type parameter ...
			if (value instanceof TypeParameter) {
				TypeParameter typeParameter = (TypeParameter) value;

				// create a new generic type for type parameters and set its
				// referenced type parameter
				ParameterGenericType genericType =
						PivotModelFactory.eINSTANCE.createParameterGenericType();
				genericType.setTypeParameter(typeParameter);

				setGenericType(typedElement, genericType);
			}

			// ... or a normal type
			else if (value instanceof Type) {
				Type type = (Type) value;

				// check if the type has any type parameters
				if (!type.getOwnedTypeParameter().isEmpty()) {

					// create a new complex generic type and set the given type
					// as a reference
					ComplexGenericType genericType =
							PivotModelFactory.eINSTANCE.createComplexGenericType();
					genericType.setUnboundType(type);

					// append type arguments for each type parameter
					for (int i = 0, size = type.getOwnedTypeParameter().size(); i < size; i++) {
						genericType.getTypeArgument().add(
								PivotModelFactory.eINSTANCE.createTypeArgument());
					}

					setGenericType(typedElement, genericType);
				}

				// just a normal type without type parameters
				else {
					// remove any previously created generic types
					setGenericType(typedElement, null);
					super.setPropertyValue(object, value);
				}
			}
		}

		/**
		 * Helper method that sets the generic type on a {@link TypedElement}. Uses
		 * a <code>SetCommand</code> for this purpose so the action can be undone.
		 * 
		 * @param genericType
		 */
		protected void setGenericType(TypedElement typedElement,
				GenericType genericType) {

			EditingDomain editingDomain = getEditingDomain(typedElement);

			if (editingDomain != null) {
				editingDomain.getCommandStack().execute(
						SetCommand.create(editingDomain, typedElement,
								PivotModelPackageImpl.Literals.TYPED_ELEMENT__GENERIC_TYPE,
								genericType));
			}

			else {
				typedElement.setGenericType(genericType);
			}

		}

	}

	/**
	 * Overridden to return the type arguments of the generic type of the adapted
	 * {@link TypedElement}.
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	@Override
	public Collection<?> getChildren(Object object) {

		TypedElement typedElement;
		Collection<Object> result = null;

		// cast the typed element
		typedElement = (TypedElement) object;

		// check if the typed element has a generic type and that one is a
		// ComplexGenericType
		if (typedElement.getGenericType() != null
				&& typedElement.getGenericType() instanceof ComplexGenericType) {
			ComplexGenericType genericType =
					(ComplexGenericType) typedElement.getGenericType();

			// check if the generic type has type arguments
			if (!genericType.getTypeArgument().isEmpty()) {
				// add the type arguments to the list of children
				result = new ArrayList<Object>(genericType.getTypeArgument());
				result.addAll(super.getChildren(object));
			}
		}

		return result == null ? super.getChildren(object) : result;
	}

	/**
	 * <p>
	 * This returns the label text for the adapted class.
	 * </p>
	 * 
	 * <p>
	 * The EMF implementation is extended to append the type of the
	 * {@link TypedElement}. This is a template method that can be extended by
	 * subclasses by overriding {@link #getTypedElementName(TypedElement)}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {

		StringBuilder label;

		TypedElement typedElement;
		CharSequence typedElementName;

		CharSequence typeName;

		/* Cast the typed element. */
		typedElement = (TypedElement) object;

		/*
		 * Initialize with the name of the typed element determined by subclasses.
		 */
		typedElementName = this.getTypedElementName(typedElement);

		if (typedElementName != null) {
			label = new StringBuilder(typedElementName);
		}

		else {
			label = new StringBuilder();
		}

		typeName = getTypedElementTypeName(typedElement);

		/* Eventually separate the packages of the name. */
		if (typeName.length() > 0) {
			label.append(" : ").append(typeName); //$NON-NLS-1$
		}
		// no else.

		return label.toString();
	}

	/**
	 * This method can be overridden in subclasses to specify the rendering of the
	 * name of the {@link TypedElement}. This default implementation simply
	 * returns the name of the <code>TypedElement</code> unchanged.
	 * 
	 * @param typedElement
	 *          the <code>TypedElement</code> instance
	 * 
	 * @return a <code>String</code> with the name of the
	 *         <code>TypedElement</code>
	 */
	protected CharSequence getTypedElementName(TypedElement typedElement) {

		return typedElement.getName();
	}

	/**
	 * Helper method to return a representation of the type of the
	 * {@link TypedElement}. This is either the name of the {@link Type} or the
	 * name of the {@link GenericType} of the <code>TypedElement</code>. If
	 * neither a type nor a generic type is set, the empty string is returned.
	 * 
	 * @param typedElement
	 *          the <code>TypedElement</code> instance
	 * 
	 * @return a <code>String</code> representing the type of the
	 *         <code>TypedElement</code>
	 */
	protected CharSequence getTypedElementTypeName(TypedElement typedElement) {

		return typedElement.getType() != null ? getTypeName(typedElement.getType())
				: (typedElement.getGenericType() != null ? getGenericTypeName(typedElement
						.getGenericType()) : ""); //$NON-NLS-1$
	}

	/**
	 * Helper method to return the name of the {@link Type} of a
	 * {@link TypedElement}. This default implementation simply returns the name
	 * if the type, but subclasses may override to provide a custom rendering.
	 * 
	 * @param type
	 *          the <code>Type</code>, guaranteed not to be <code>null</code>
	 * 
	 * @return a formatted name for the type
	 */
	protected CharSequence getTypeName(Type type) {

		return type.getName();
	}

	/**
	 * Helper method to return the name of the {@link GenericType} of a
	 * {@link TypedElement}. This default implementation delegates to
	 * {@link GenericTypeItemProvider#getText()} for determining the rendering.
	 * 
	 * @param genericType
	 *          the <code>GenericType</code>, guaranteed not to be
	 *          <code>null</code>
	 * 
	 * @return a formatted name for the generic type
	 */
	protected CharSequence getGenericTypeName(GenericType genericType) {

		return getLabel(genericType);
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {

		updateChildren(notification);

		switch (notification.getFeatureID(TypedElement.class)) {
		case PivotModelPackage.TYPED_ELEMENT__TYPE:
		case PivotModelPackage.TYPED_ELEMENT__GENERIC_TYPE:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), false, true));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {

		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
