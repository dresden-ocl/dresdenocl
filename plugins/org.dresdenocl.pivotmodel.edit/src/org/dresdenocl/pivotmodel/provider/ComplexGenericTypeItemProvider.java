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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.dresdenocl.pivotmodel.ComplexGenericType;
import org.dresdenocl.pivotmodel.PivotModelPackage;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.TypeArgument;

/**
 * This is the item provider adapter for a
 * {@link org.dresdenocl.pivotmodel.ComplexGenericType} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ComplexGenericTypeItemProvider extends GenericTypeItemProvider {

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ComplexGenericTypeItemProvider(AdapterFactory adapterFactory) {
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

			addUnboundTypePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Unbound Type feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addUnboundTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_ComplexGenericType_unboundType_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_ComplexGenericType_unboundType_feature", "_UI_ComplexGenericType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						PivotModelPackage.Literals.COMPLEX_GENERIC_TYPE__UNBOUND_TYPE,
						false, false, true, null, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(
			Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures
					.add(PivotModelPackage.Literals.COMPLEX_GENERIC_TYPE__TYPE_ARGUMENT);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * Adapted to simply default to the super{@link #getImage(Object) super
	 * implementation}.
	 * 
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {

		return super.getImage(object);
	}

	/**
	 * This returns the label text for the adapted class.
	 * 
	 * <p>
	 * The EMF implementation is altered to return the formatted name of the
	 * {@link Type} wrapped by the {@link ComplexGenericType} as well as a list of
	 * the {@link TypeArgument}s.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {

		StringBuilder label;

		// do some casting
		ComplexGenericType genericType = (ComplexGenericType) object;
		Type unboundType = genericType.getUnboundType();

		// the label provider should be a TypeItemProvider, maybe we should check,
		// though
		TypeItemProvider typeItemProvider = (TypeItemProvider) getLabelProvider(unboundType);

		// get the type name
		label = new StringBuilder(typeItemProvider.getTypeName(unboundType));

		// add a type-specific delimiter for the type arguments
		label.append(typeItemProvider.getTypeParameterListOpeningDelimiter());

		// append the type arguments
		for (Iterator<TypeArgument> it = genericType.getTypeArgument()
				.iterator(); it.hasNext();) {
			TypeArgument typeArg = it.next();

			// append the type or generic type name, or a '?' if nothing bound
			label.append(typeArg.getType() != null ? typeArg.getType()
					.getName()
					: (typeArg.getGenericType() != null ? getLabelProvider(
							typeArg.getGenericType()).getText(
							typeArg.getGenericType()) : '?'));

			if (it.hasNext()) {
				label.append(", "); //$NON-NLS-1$
			}
		}

		label.append(typeItemProvider.getTypeParameterListClosingDelimiter());

		return label.toString();
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
