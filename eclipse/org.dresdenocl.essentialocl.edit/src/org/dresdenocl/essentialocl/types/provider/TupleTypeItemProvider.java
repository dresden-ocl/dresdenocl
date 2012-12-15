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
package org.dresdenocl.essentialocl.types.provider;

import java.util.Collection;
import java.util.Iterator;
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

import org.dresdenocl.essentialocl.types.impl.TypesPackageImpl;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.provider.TypeItemProvider;

/**
 * This is the item provider adapter for a {@link org.dresdenocl.essentialocl.types.TupleType} object.
 * <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * @generated
 */
public class TupleTypeItemProvider extends TypeItemProvider implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public TupleTypeItemProvider(AdapterFactory adapterFactory) {

		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {

		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addOclLibraryPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Ocl Library feature.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected void addOclLibraryPropertyDescriptor(Object object) {

		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_TupleType_oclLibrary_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_TupleType_oclLibrary_feature", "_UI_TupleType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						TypesPackageImpl.Literals.TUPLE_TYPE__OCL_LIBRARY, true, false,
						true, null, null, null));
	}

	/**
	 * This returns TupleType.gif.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {

		return overlayImage(object,
				getResourceLocator().getImage("full/obj16/TupleType")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * 
	 * <p>
	 * The EMF implementation has been altered to simply call <code>super.getText()</code>.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {

		return super.getText(object);
	}

	/**
	 * Overridden to suppress the type arguments and add a OCL-conformant rendering of the elements of
	 * the <code>TupleType</code>.
	 * 
	 * @see org.dresdenocl.pivotmodel.provider.TypeItemProvider#getTypeNameWithTypeArguments(org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	protected CharSequence getTypeNameWithTypeArguments(Type type) {

		StringBuilder name = new StringBuilder(getTypeName(type));

		// append the properties of the tuple type
		name.append('(');

		for (Iterator<Property> it = type.getOwnedProperty().iterator(); it
				.hasNext();) {
			Property p = it.next();
			name.append(getLabel(p));

			if (it.hasNext()) {
				name.append(", "); //$NON-NLS-1$
			}
		}

		name.append(')');

		return name;
	}

	/**
	 * Overridden to implement custom rendering of the tuple type's name. This method returns the
	 * string <code>"Tuple"</code>.
	 * 
	 * @see org.dresdenocl.pivotmodel.provider.TypeItemProvider#getTypeName(org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	protected CharSequence getTypeName(Type type) {

		return "Tuple"; //$NON-NLS-1$
	}

	/**
	 * Overridden to return <code>'('</code>.
	 * 
	 * @see org.dresdenocl.pivotmodel.provider.TypeItemProvider#getTypeParameterListOpeningDelimiter()
	 */
	@Override
	protected char getTypeParameterListOpeningDelimiter() {

		return '(';
	}

	/**
	 * Overridden to return <code>')'</code>.
	 * 
	 * @see org.dresdenocl.pivotmodel.provider.TypeItemProvider#getTypeParameterListClosingDelimiter()
	 */
	@Override
	protected char getTypeParameterListClosingDelimiter() {

		return ')';
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {

		return EssentialOCLEditPlugin.INSTANCE;
	}

}
