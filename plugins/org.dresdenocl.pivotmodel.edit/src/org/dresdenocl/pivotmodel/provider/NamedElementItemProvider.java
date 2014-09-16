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
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.dresdenocl.pivotmodel.GenericElement;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.PivotModelPackage;

/**
 * This is the item provider adapter for a
 * {@link org.dresdenocl.pivotmodel.NamedElement} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class NamedElementItemProvider extends ItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NamedElementItemProvider(AdapterFactory adapterFactory) {

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

			addNamePropertyDescriptor(object);
			addQualifiedNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {

		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_NamedElement_name_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_NamedElement_name_feature", "_UI_NamedElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						PivotModelPackage.Literals.NAMED_ELEMENT__NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Qualified Name feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addQualifiedNamePropertyDescriptor(Object object) {

		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_NamedElement_qualifiedName_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_NamedElement_qualifiedName_feature", "_UI_NamedElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						PivotModelPackage.Literals.NAMED_ELEMENT__QUALIFIED_NAME, false,
						false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null,
						null));
	}

	/**
	 * This returns the label text for the adapted class.
	 * 
	 * <p>
	 * The EMF implementation is adapted in order to only return the name of the
	 * {@link NamedElement}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {

		return (((NamedElement) object).getName());
	}

	/**
	 * Helper method that returns the character used as the opening delimiter for type parameters.
	 * This can be overridden by subclasses to alter the way their type parameters are displayed. The
	 * default implementation returns <code>'<'</code>.
	 * 
	 * <p>
	 * Due to multiple inheritance, there is no concrete item provider class for
	 * {@link GenericElement}s. That's why shared methods have been moved up the inheritance
	 * hierarchy to <code>NamedElementItemProvider</code>.
	 * </p>
	 * 
	 * @return the type parameter list opening character
	 */
	protected char getTypeParameterListOpeningDelimiter() {

		return '<';
	}

	/**
	 * Helper method that returns the character used as the closing delimiter for
	 * type parameters. This can be overridden by subclasses to alter the way
	 * their type parameters are displayed. The default implementation returns
	 * <code>'>'</code>.
	 * 
	 * <p>
	 * Due to multiple inheritance, there is no concrete item provider class for
	 * {@link GenericElement}s. That's why shared methods have been moved up the
	 * inheritance hierarchy to <code>NamedElementItemProvider</code>.
	 * </p>
	 * 
	 * @return the type parameter list opening character
	 */
	protected char getTypeParameterListClosingDelimiter() {

		return '>';
	}

	/**
	 * Helper method for subclasses that adapts the given {@link NamedElement} to
	 * an {@link IItemLabelProvider}.
	 * 
	 * @param namedElement
	 *          the named element
	 * 
	 * @return an {@link IItemLabelProvider} for the named element
	 */
	protected IItemLabelProvider getLabelProvider(NamedElement namedElement) {

		return (IItemLabelProvider) ((ComposeableAdapterFactory) getAdapterFactory())
				.getRootAdapterFactory().adapt(namedElement, IItemLabelProvider.class);
	}

	/**
	 * Helper method for subclasses that returns the label for a given
	 * {@link NamedElement}.
	 * 
	 * @param namedElement
	 *          the named element
	 * 
	 * @return a <code>String</code> representing the named element
	 */
	protected String getLabel(NamedElement namedElement) {

		return getLabelProvider(namedElement).getText(namedElement);
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

		switch (notification.getFeatureID(NamedElement.class)) {
		case PivotModelPackage.NAMED_ELEMENT__NAME:
		case PivotModelPackage.NAMED_ELEMENT__QUALIFIED_NAME:
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

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {

		return PivotModelEditPlugin.INSTANCE;
	}

}
