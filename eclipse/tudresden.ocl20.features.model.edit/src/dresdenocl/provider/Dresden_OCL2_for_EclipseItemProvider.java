/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dresdenocl.provider;


import dresdenocl.Dresden_OCL2_for_Eclipse;
import dresdenocl.DresdenoclPackage;

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

/**
 * This is the item provider adapter for a {@link dresdenocl.Dresden_OCL2_for_Eclipse} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class Dresden_OCL2_for_EclipseItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dresden_OCL2_for_EclipseItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addSupportsDifferentMetamodelsPropertyDescriptor(object);
			addFullOCLStandardPropertyDescriptor(object);
			addInterpretationAndCodeGenerationPropertyDescriptor(object);
			addSupportsDifferentModelInstanceTypesPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Supports Different Metamodels feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSupportsDifferentMetamodelsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Dresden_OCL2_for_Eclipse_supportsDifferentMetamodels_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Dresden_OCL2_for_Eclipse_supportsDifferentMetamodels_feature", "_UI_Dresden_OCL2_for_Eclipse_type"),
				 DresdenoclPackage.Literals.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_METAMODELS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Full OCL Standard feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFullOCLStandardPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Dresden_OCL2_for_Eclipse_fullOCLStandard_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Dresden_OCL2_for_Eclipse_fullOCLStandard_feature", "_UI_Dresden_OCL2_for_Eclipse_type"),
				 DresdenoclPackage.Literals.DRESDEN_OCL2_FOR_ECLIPSE__FULL_OCL_STANDARD,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Interpretation And Code Generation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInterpretationAndCodeGenerationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Dresden_OCL2_for_Eclipse_interpretationAndCodeGeneration_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Dresden_OCL2_for_Eclipse_interpretationAndCodeGeneration_feature", "_UI_Dresden_OCL2_for_Eclipse_type"),
				 DresdenoclPackage.Literals.DRESDEN_OCL2_FOR_ECLIPSE__INTERPRETATION_AND_CODE_GENERATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Supports Different Model Instance Types feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSupportsDifferentModelInstanceTypesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Dresden_OCL2_for_Eclipse_supportsDifferentModelInstanceTypes_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Dresden_OCL2_for_Eclipse_supportsDifferentModelInstanceTypes_feature", "_UI_Dresden_OCL2_for_Eclipse_type"),
				 DresdenoclPackage.Literals.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns Dresden_OCL2_for_Eclipse.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Dresden_OCL2_for_Eclipse"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		Dresden_OCL2_for_Eclipse dresden_OCL2_for_Eclipse = (Dresden_OCL2_for_Eclipse)object;
		return getString("_UI_Dresden_OCL2_for_Eclipse_type") + " " + dresden_OCL2_for_Eclipse.isSupportsDifferentMetamodels();
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Dresden_OCL2_for_Eclipse.class)) {
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_METAMODELS:
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__FULL_OCL_STANDARD:
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__INTERPRETATION_AND_CODE_GENERATION:
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return FeauresEditPlugin.INSTANCE;
	}

}
