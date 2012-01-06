/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.tracer.tracermodel.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

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

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;

import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelFactory;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage;

/**
 * This is the item provider adapter for a
 * {@link tudresden.ocl20.pivot.tracer.tracermodel.TracerItem} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class TracerItemItemProvider extends ItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright =
			"Copyright (C) 2011 by Lars Schütze (lschuetze@gmx.net)\n\nThis file is part of the OCL 2 Interpreter of Dresden OCL2 for Eclipse.\n\nDresden OCL2 for Eclipse is free software: you can redistribute it and/or modify \nit under the terms of the GNU Lesser General Public License as published by the \nFree Software Foundation, either version 3 of the License, or (at your option)\nany later version.\n\nDresden OCL2 for Eclipse is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY \nor FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License \nfor more details.\n\nYou should have received a copy of the GNU Lesser General Public License along \nwith Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>."; //$NON-NLS-1$

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TracerItemItemProvider(AdapterFactory adapterFactory) {

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

			addResultPropertyDescriptor(object);
			addParentPropertyDescriptor(object);
			addChildrenPropertyDescriptor(object);
			addUUIDPropertyDescriptor(object);
			addModelInstanceElementPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Result feature.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResultPropertyDescriptor(Object object) {

		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_TracerItem_result_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_TracerItem_result_feature", "_UI_TracerItem_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						TracermodelPackage.Literals.TRACER_ITEM__RESULT, true, false, true,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Parent feature.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParentPropertyDescriptor(Object object) {

		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_TracerItem_parent_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_TracerItem_parent_feature", "_UI_TracerItem_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						TracermodelPackage.Literals.TRACER_ITEM__PARENT, true, false, true,
						null, null, null));
	}

	/**
	 * This adds a property descriptor for the Children feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addChildrenPropertyDescriptor(Object object) {

		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_TracerItem_children_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_TracerItem_children_feature", "_UI_TracerItem_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						TracermodelPackage.Literals.TRACER_ITEM__CHILDREN, true, false,
						true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the UUID feature.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUUIDPropertyDescriptor(Object object) {

		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_TracerItem_UUID_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_TracerItem_UUID_feature", "_UI_TracerItem_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						TracermodelPackage.Literals.TRACER_ITEM__UUID, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Model Instance Element feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addModelInstanceElementPropertyDescriptor(Object object) {

		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_TracerItem_modelInstanceElement_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_TracerItem_modelInstanceElement_feature", "_UI_TracerItem_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						TracermodelPackage.Literals.TRACER_ITEM__MODEL_INSTANCE_ELEMENT,
						true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
						null, null));
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
			childrenFeatures.add(TracermodelPackage.Literals.TRACER_ITEM__EXPRESSION);
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {

		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {

		OclAny labelValue = ((TracerItem) object).getResult();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ? getString("_UI_TracerItem_type") : //$NON-NLS-1$
				getString("_UI_TracerItem_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(TracerItem.class)) {
		case TracermodelPackage.TRACER_ITEM__UUID:
		case TracermodelPackage.TRACER_ITEM__MODEL_INSTANCE_ELEMENT:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), false, true));
			return;
		case TracermodelPackage.TRACER_ITEM__EXPRESSION:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), true, false));
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

		newChildDescriptors.add(createChildParameter(
				TracermodelPackage.Literals.TRACER_ITEM__EXPRESSION,
				TracermodelFactory.eINSTANCE.createTracerItem()));

		newChildDescriptors.add(createChildParameter(
				TracermodelPackage.Literals.TRACER_ITEM__EXPRESSION,
				TracermodelFactory.eINSTANCE.createTracerRoot()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {

		return TracermodelEditPlugin.INSTANCE;
	}

}
