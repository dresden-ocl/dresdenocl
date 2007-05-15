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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import tudresden.ocl20.pivot.xocl.CallExpXS;
import tudresden.ocl20.pivot.xocl.XOCLFactory;
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * This is the item provider adapter for a {@link tudresden.ocl20.pivot.xocl.CallExpXS} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CallExpXSItemProvider extends OclExpressionXSItemProvider implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
    IItemLabelProvider, IItemPropertySource {

  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CallExpXSItemProvider(AdapterFactory adapterFactory) {
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
      childrenFeatures.add(XOCLPackage.Literals.CALL_EXP_XS__SOURCE);
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
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getText(Object object) {
    return getString("_UI_CallExpXS_type"); //$NON-NLS-1$
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * 
   * <p>
   * Adapted to post label update notifications when the source changes.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public void notifyChanged(Notification notification) {
    updateChildren(notification);

    switch (notification.getFeatureID(CallExpXS.class)) {
      case XOCLPackage.CALL_EXP_XS__SOURCE:
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

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createBooleanLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createCollectionLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createCollectionOperationCallExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createEnumLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createIfExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createIntegerLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createIterateExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createInvalidLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createIteratorExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createLetExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createModelOperationCallExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createPropertyCallExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createRealLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createStaticOperationCallExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createStaticPropertyCallExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createStringLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createTupleLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createTypeLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createUndefinedLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createUnlimitedNaturalExpXS()));

    newChildDescriptors.add(createChildParameter(XOCLPackage.Literals.CALL_EXP_XS__SOURCE,
        XOCLFactory.eINSTANCE.createVariableExpXS()));
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
