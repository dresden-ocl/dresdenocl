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
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import tudresden.ocl20.pivot.language.ocl.IfExpCS;
import tudresden.ocl20.pivot.language.ocl.OclFactory;
import tudresden.ocl20.pivot.language.ocl.OclPackage;

/**
 * This is the item provider adapter for a {@link tudresden.ocl20.pivot.language.ocl.IfExpCS} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IfExpCSItemProvider
	extends OclExpressionCSItemProvider
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
	public IfExpCSItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(OclPackage.Literals.IF_EXP_CS__CONDITION);
			childrenFeatures.add(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH);
			childrenFeatures.add(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns IfExpCS.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/IfExpCS"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		return "if then else";
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

		switch (notification.getFeatureID(IfExpCS.class)) {
			case OclPackage.IF_EXP_CS__CONDITION:
			case OclPackage.IF_EXP_CS__THEN_BRANCH:
			case OclPackage.IF_EXP_CS__ELSE_BRANCH:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createBracketExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createNamedLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createCollectionTypeLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createTupleTypeLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createEnumLiteralOrStaticPropertyExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createCollectionLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createIteratorExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createIterateExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createNavigationCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createPropertyCallOnSelfExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createPropertyCallExplicitPathExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createOperationCallOnSelfExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createStaticOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createUnaryOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createLogicalNotOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createOperationCallWithSourceExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createAdditiveOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createMultOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createRelationalOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createEqualityOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createLogicalAndOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createLogicalOrOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createLogicalXorOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createLogicalImpliesOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createOperationCallWithImlicitSourceExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createTupleLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createIntegerLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createRealLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createBooleanLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createStringLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createInvalidLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createNullLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createLetExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__CONDITION,
				 OclFactory.eINSTANCE.createIfExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createBracketExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createNamedLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createCollectionTypeLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createTupleTypeLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createEnumLiteralOrStaticPropertyExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createCollectionLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createIteratorExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createIterateExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createNavigationCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createPropertyCallOnSelfExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createPropertyCallExplicitPathExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createOperationCallOnSelfExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createStaticOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createUnaryOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createLogicalNotOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createOperationCallWithSourceExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createAdditiveOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createMultOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createRelationalOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createEqualityOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createLogicalAndOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createLogicalOrOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createLogicalXorOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createLogicalImpliesOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createOperationCallWithImlicitSourceExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createTupleLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createIntegerLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createRealLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createBooleanLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createStringLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createInvalidLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createNullLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createLetExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__THEN_BRANCH,
				 OclFactory.eINSTANCE.createIfExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createBracketExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createNamedLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createCollectionTypeLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createTupleTypeLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createEnumLiteralOrStaticPropertyExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createCollectionLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createIteratorExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createIterateExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createNavigationCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createPropertyCallOnSelfExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createPropertyCallExplicitPathExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createOperationCallOnSelfExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createStaticOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createUnaryOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createLogicalNotOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createOperationCallWithSourceExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createAdditiveOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createMultOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createRelationalOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createEqualityOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createLogicalAndOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createLogicalOrOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createLogicalXorOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createLogicalImpliesOperationCallExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createOperationCallWithImlicitSourceExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createTupleLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createIntegerLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createRealLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createBooleanLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createStringLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createInvalidLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createNullLiteralExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createLetExpCS()));

		newChildDescriptors.add
			(createChildParameter
				(OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH,
				 OclFactory.eINSTANCE.createIfExpCS()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == OclPackage.Literals.IF_EXP_CS__CONDITION ||
			childFeature == OclPackage.Literals.IF_EXP_CS__THEN_BRANCH ||
			childFeature == OclPackage.Literals.IF_EXP_CS__ELSE_BRANCH;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
