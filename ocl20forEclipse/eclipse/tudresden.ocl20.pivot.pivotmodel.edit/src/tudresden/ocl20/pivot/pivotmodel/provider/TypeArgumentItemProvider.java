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
package tudresden.ocl20.pivot.pivotmodel.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypeArgument;
import tudresden.ocl20.pivot.pivotmodel.TypedElement;
import tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl;

/**
 * This is the item provider adapter for a {@link tudresden.ocl20.pivot.pivotmodel.TypeArgument} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class TypeArgumentItemProvider extends TypedElementItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public TypeArgumentItemProvider(AdapterFactory adapterFactory) {

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

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This returns TypeArgument.gif.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {

		return overlayImage(object,
				getResourceLocator().getImage("full/obj16/TypeArgument")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * 
	 * <p>
	 * Changed the EMF-generated code to return the name of the type of the adapted
	 * {@link TypeArgument}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {

		return getTypedElementTypeName((TypeArgument) object).toString();
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * 
	 * <p>
	 * Extended the EMF implementation to update the label of the owner if the type of the
	 * {@link TypeArgument} is changed.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification) {

		updateChildren(notification);

		switch (notification.getFeatureID(TypedElement.class)) {
		case PivotModelPackageImpl.TYPE_ARGUMENT__TYPE:
		case PivotModelPackageImpl.TYPE_ARGUMENT__GENERIC_TYPE: {

			// update all element labels until we arrive at the Type that contains the type argument
			for (NamedElement element = (NamedElement) notification.getNotifier(); // start at notifier 
			element != null; element = element.getOwner()) {
				fireNotifyChanged(new ViewerNotification(notification, element, false,
						true));

				// stop iteration when we have reached the type
				if (element instanceof Type) {
					break;
				}
			}

			return;
		}
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
