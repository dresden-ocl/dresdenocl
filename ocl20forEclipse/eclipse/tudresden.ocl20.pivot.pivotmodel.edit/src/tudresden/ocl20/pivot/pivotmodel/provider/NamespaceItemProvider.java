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
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import org.eclipse.emf.edit.provider.ViewerNotification;

import tudresden.ocl20.pivot.pivotmodel.GenericType;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.PivotModelPackage;
import tudresden.ocl20.pivot.pivotmodel.TypeParameter;
import tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl;

/**
 * This is the item provider adapter for a
 * {@link tudresden.ocl20.pivot.pivotmodel.Namespace} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class NamespaceItemProvider extends NamedElementItemProvider implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NamespaceItemProvider(AdapterFactory adapterFactory) {

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

			addNestingNamespacePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Nesting Namespace feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addNestingNamespacePropertyDescriptor(Object object) {

		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_Namespace_nestingNamespace_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_Namespace_nestingNamespace_feature", "_UI_Namespace_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						PivotModelPackage.Literals.NAMESPACE__NESTING_NAMESPACE, false,
						false, false, null, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce
	 * an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand},
	 * {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(
			Object object) {

		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures
					.add(PivotModelPackage.Literals.GENERIC_ELEMENT__OWNED_TYPE_PARAMETER);
			childrenFeatures.add(PivotModelPackage.Literals.NAMESPACE__OWNED_TYPE);
			childrenFeatures.add(PivotModelPackage.Literals.NAMESPACE__OWNED_RULE);
			childrenFeatures
					.add(PivotModelPackage.Literals.NAMESPACE__NESTED_NAMESPACE);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {

		// Check the type of the specified child object and return the proper
		// feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Namespace.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {

		return overlayImage(object,
				getResourceLocator().getImage("full/obj16/Namespace")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * 
	 * <p>
	 * The EMF implementation is changed to return the name of the adapted
	 * {@link Namespace} or <code>null</code> if no name is set. Existing type
	 * parameters are appended as well.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {

		StringBuilder label;
		Namespace namespace;

		// cast namespace
		namespace = (Namespace) object;

		// initialize with name
		label =
				new StringBuilder(StringUtils.defaultIfEmpty(namespace.getName(),
						"null")); //$NON-NLS-1$

		// append type parameters if there are any
		if (!namespace.getOwnedTypeParameter().isEmpty()) {
			label.append('<');

			for (Iterator<TypeParameter> it =
					namespace.getOwnedTypeParameter().iterator(); it.hasNext();) {
				label.append(it.next().getName());

				if (it.hasNext()) {
					label.append(", "); //$NON-NLS-1$
				}
			}

			label.append('>');
		}

		return label.toString();
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to
	 * update any cached children and by creating a viewer notification, which it
	 * passes to {@link #fireNotifyChanged}.
	 * 
	 * <p>
	 * The EMF implementation is adapted to send label notifications if the owned
	 * type parameters change.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification) {

		updateChildren(notification);

		switch (notification.getFeatureID(Namespace.class)) {
		case PivotModelPackageImpl.NAMESPACE__OWNED_TYPE_PARAMETER:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), true, true));
			return;
		case PivotModelPackageImpl.NAMESPACE__OWNED_TYPE:
		case PivotModelPackageImpl.NAMESPACE__OWNED_RULE:
		case PivotModelPackageImpl.NAMESPACE__NESTED_NAMESPACE:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of
	 * {@link org.eclipse.emf.edit.command.CommandParameter}s describing all of
	 * the children that can be created under this object.
	 * 
	 * <p>
	 * The EMF implementation is adapted in order not to include the
	 * {@link GenericType}. Generic types are created automatically so we do not
	 * want the editor to show them.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {

		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(
				PivotModelPackageImpl.Literals.GENERIC_ELEMENT__OWNED_TYPE_PARAMETER,
				PivotModelFactory.eINSTANCE.createTypeParameter()));

		newChildDescriptors.add(createChildParameter(
				PivotModelPackageImpl.Literals.NAMESPACE__NESTED_NAMESPACE,
				PivotModelFactory.eINSTANCE.createNamespace()));

		newChildDescriptors.add(createChildParameter(
				PivotModelPackageImpl.Literals.NAMESPACE__OWNED_TYPE,
				PivotModelFactory.eINSTANCE.createType()));

		newChildDescriptors.add(createChildParameter(
				PivotModelPackageImpl.Literals.NAMESPACE__OWNED_TYPE,
				PivotModelFactory.eINSTANCE.createPrimitiveType()));

		newChildDescriptors.add(createChildParameter(
				PivotModelPackageImpl.Literals.NAMESPACE__OWNED_TYPE,
				PivotModelFactory.eINSTANCE.createEnumeration()));

		newChildDescriptors.add(createChildParameter(
				PivotModelPackageImpl.Literals.NAMESPACE__OWNED_RULE,
				PivotModelFactory.eINSTANCE.createConstraint()));
	}

}
