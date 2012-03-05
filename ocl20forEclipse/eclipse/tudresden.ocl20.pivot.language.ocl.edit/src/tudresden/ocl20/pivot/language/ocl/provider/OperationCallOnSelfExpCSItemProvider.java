/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.provider;

import java.util.Collection;
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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import tudresden.ocl20.pivot.language.ocl.OclFactory;
import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS;

/**
 * This is the item provider adapter for a {@link tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class OperationCallOnSelfExpCSItemProvider extends
		OperationCallExpCSItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OperationCallOnSelfExpCSItemProvider(AdapterFactory adapterFactory) {
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

			addOperationNamePropertyDescriptor(object);
			addIsMarkedPrePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Operation Name feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addOperationNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_OperationCallBaseExpCS_operationName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_OperationCallBaseExpCS_operationName_feature", "_UI_OperationCallBaseExpCS_type"),
				 OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__OPERATION_NAME,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Marked Pre feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addIsMarkedPrePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_OperationCallBaseExpCS_isMarkedPre_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_OperationCallBaseExpCS_isMarkedPre_feature", "_UI_OperationCallBaseExpCS_type"),
				 OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__IS_MARKED_PRE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
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
			childrenFeatures.add(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS);
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
	 * This returns OperationCallOnSelfExpCS.gif.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/OperationCallOnSelfExpCS"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		OperationCallOnSelfExpCS operationCallOnSelfExpCS = (OperationCallOnSelfExpCS) object;
		return operationCallOnSelfExpCS.getOperationName().getName();
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

		switch (notification.getFeatureID(OperationCallOnSelfExpCS.class)) {
			case OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__ARGUMENTS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createBracketExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createNamedLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createCollectionTypeLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createTupleTypeLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createEnumLiteralOrStaticPropertyExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createCollectionLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createIteratorExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createIterateExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createNavigationCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createPropertyCallOnSelfExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createPropertyCallExplicitPathExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createOperationCallOnSelfExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createStaticOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createUnaryOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createLogicalNotOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createOperationCallWithSourceExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createAdditiveOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createMultOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createRelationalOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createEqualityOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createLogicalAndOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createLogicalOrOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createLogicalXorOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createLogicalImpliesOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createOperationCallWithImlicitSourceExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createTupleLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createIntegerLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createRealLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createBooleanLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createStringLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createInvalidLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createNullLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createLetExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.OPERATION_CALL_BASE_EXP_CS__ARGUMENTS,
				 OclFactory.eINSTANCE.createIfExpCS()));
	}

}
