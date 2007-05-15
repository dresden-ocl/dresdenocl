/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
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
package tudresden.ocl20.pivot.xocl.provider;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import org.eclipse.emf.edit.provider.ViewerNotification;

import tudresden.ocl20.pivot.xocl.OclExpressionXS;
import tudresden.ocl20.pivot.xocl.PropertyCallExpXS;
import tudresden.ocl20.pivot.xocl.XOCLFactory;
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * This is the item provider adapter for a {@link tudresden.ocl20.pivot.xocl.PropertyCallExpXS} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PropertyCallExpXSItemProvider extends FeatureCallExpXSItemProvider implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
    IItemLabelProvider, IItemPropertySource {

  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertyCallExpXSItemProvider(AdapterFactory adapterFactory) {
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

      addReferredPropertyNamePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Referred Property Name feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addReferredPropertyNamePropertyDescriptor(Object object) {
    itemPropertyDescriptors
        .add(createItemPropertyDescriptor(
            ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_PropertyCallExpXS_referredPropertyName_feature"), //$NON-NLS-1$
            getString(
                "_UI_PropertyDescriptor_description","_UI_PropertyCallExpXS_referredPropertyName_feature","_UI_PropertyCallExpXS_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__REFERRED_PROPERTY_NAME,true,false,false,
            ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,null,null));
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
      childrenFeatures.add(XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER);
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

    return super.getChildFeature(object,child);
  }

  /**
   * This returns PropertyCallExpXS.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object) {
    return overlayImage(object,getResourceLocator().getImage("full/obj16/PropertyCallExpXS")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class.
   * 
   * <p>
   * Changed the EMF implementation to return the name of the referred property if present,
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public String getText(Object object) {
    PropertyCallExpXS propertyCallExp = (PropertyCallExpXS) object;
    StringBuilder label = new StringBuilder();

    // get the string for the source expression
    OclExpressionXS source = propertyCallExp.getSource();

    if (source != null) {
      label.append(getLabel(source));
      label.append('.');

      // get the referred property
      String referredProperty = propertyCallExp.getReferredPropertyName();
      label.append(StringUtils.isNotEmpty(referredProperty) ? referredProperty : UNDEFINED);
    }

    // if some parts are undefined return the default string
    return label.length() != 0 ? label.toString() : getString("_UI_PropertyCallExpXS_type"); //$NON-NLS-1$
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * 
   * <p>
   * Adapted to update the whole expression tree if the referred property name or the qualifiers
   * change.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public void notifyChanged(Notification notification) {
    updateChildren(notification);

    switch (notification.getFeatureID(PropertyCallExpXS.class)) {
      case XOCLPackage.PROPERTY_CALL_EXP_XS__REFERRED_PROPERTY_NAME:
        updateLabel(notification);
        return;
      case XOCLPackage.PROPERTY_CALL_EXP_XS__QUALIFIER:
        fireNotifyChanged(new ViewerNotification(notification,notification.getNotifier(),true,false));
        updateLabel(notification);
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
    super.collectNewChildDescriptors(newChildDescriptors,object);

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createBooleanLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createCollectionLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createCollectionOperationCallExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createEnumLiteralExpXS()));

    newChildDescriptors
        .add(createChildParameter(XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,
            XOCLFactory.eINSTANCE.createIfExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createIntegerLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createIterateExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createInvalidLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createIteratorExpXS()));

    newChildDescriptors
        .add(createChildParameter(XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,
            XOCLFactory.eINSTANCE.createLetExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createModelOperationCallExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createPropertyCallExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createRealLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createStaticOperationCallExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createStaticPropertyCallExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createStringLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createTupleLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createTypeLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createUndefinedLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createUnlimitedNaturalExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER,XOCLFactory.eINSTANCE
            .createVariableExpXS()));
  }

  /**
   * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getCreateChildText(Object owner, Object feature, Object child,
      Collection<?> selection) {
    Object childFeature = feature;
    Object childObject = child;

    boolean qualify = childFeature == XOCLPackage.Literals.CALL_EXP_XS__SOURCE
        || childFeature == XOCLPackage.Literals.PROPERTY_CALL_EXP_XS__QUALIFIER;

    if (qualify) {
      return getString(
          "_UI_CreateChild_text2", //$NON-NLS-1$
          new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
    }
    return super.getCreateChildText(owner,feature,child,selection);
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ResourceLocator getResourceLocator() {
    return XOCLEditPlugin.INSTANCE;
  }

}
