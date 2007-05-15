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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS;
import tudresden.ocl20.pivot.xocl.CollectionOperationXS;
import tudresden.ocl20.pivot.xocl.OperationCallExpXS;
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * This is the item provider adapter for a
 * {@link tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class CollectionOperationCallExpXSItemProvider extends OperationCallExpXSItemProvider
    implements IEditingDomainItemProvider, IStructuredItemContentProvider,
    ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

  /**
   * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  public CollectionOperationCallExpXSItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
    if (itemPropertyDescriptors == null) {
      super.getPropertyDescriptors(object);

      addReferredCollectionOperationPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Referred Collection Operation feature. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addReferredCollectionOperationPropertyDescriptor(Object object) {
    itemPropertyDescriptors
        .add(createItemPropertyDescriptor(
            ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_CollectionOperationCallExpXS_referredCollectionOperation_feature"), //$NON-NLS-1$
            getString(
                "_UI_PropertyDescriptor_description","_UI_CollectionOperationCallExpXS_referredCollectionOperation_feature","_UI_CollectionOperationCallExpXS_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            XOCLPackage.Literals.COLLECTION_OPERATION_CALL_EXP_XS__REFERRED_COLLECTION_OPERATION,
            true,false,false,ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,null,null));
  }

  /**
   * This returns CollectionOperationCallExpXS.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object getImage(Object object) {
    return overlayImage(object,getResourceLocator().getImage(
        "full/obj16/CollectionOperationCallExpXS")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class.
   * 
   * <p>
   * Adapted the EMF implementation to use the super{@link #getText(Object) template method} in the
   * super class.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public String getText(Object object) {
    return super.getText(object);
  }

  /**
   * Returns {@code ->} as defined in the OCL syntax for collection operations.
   * 
   * @see tudresden.ocl20.pivot.xocl.provider.OperationCallExpXSItemProvider#getOperationDelimiter()
   */
  @Override
  protected String getOperationDelimiter() {
    return "->"; //$NON-NLS-1$
  }

  /**
   * Returns <code>false</code> since collection operations are never unary.
   * 
   * @see tudresden.ocl20.pivot.xocl.provider.OperationCallExpXSItemProvider#isUnary(tudresden.ocl20.pivot.xocl.OperationCallExpXS)
   */
  @Override
  @SuppressWarnings("unused")
  protected boolean isUnary(OperationCallExpXS operationCallExp) {
    return false;
  }

  /**
   * Returns <code>true</code> for {@link CollectionOperationXS#EQUALS},
   * {@link CollectionOperationXS#EQUALS_NOT} and {@link CollectionOperationXS#MINUS},
   * <code>false</code> otherwise.
   * 
   * @see tudresden.ocl20.pivot.xocl.provider.OperationCallExpXSItemProvider#isInfix(tudresden.ocl20.pivot.xocl.OperationCallExpXS)
   */
  @Override
  protected boolean isInfix(OperationCallExpXS operationCallExp) {

    switch (((CollectionOperationCallExpXS) operationCallExp).getReferredCollectionOperation()) {
      case EQUALS:
      case EQUALS_NOT:
      case MINUS:
        return true;
      default:
        return false;
    }

  }

  /**
   * Returns the name of the associated CO
   * 
   * @see tudresden.ocl20.pivot.xocl.provider.OperationCallExpXSItemProvider#getReferredOperationName(tudresden.ocl20.pivot.xocl.OperationCallExpXS)
   */
  @Override
  protected String getReferredOperationName(OperationCallExpXS operationCallExp) {
    CollectionOperationXS operation = ((CollectionOperationCallExpXS) operationCallExp)
        .getReferredCollectionOperation();
    return operation != null ? operation.toString() : null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.xocl.provider.OperationCallExpXSItemProvider#getDefaultString()
   */
  @Override
  protected String getDefaultString() {
    return getString("_UI_CollectionOperationCallExpXS_type"); //$NON-NLS-1$;
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification) {
    updateChildren(notification);

    switch (notification.getFeatureID(CollectionOperationCallExpXS.class)) {
      case XOCLPackage.COLLECTION_OPERATION_CALL_EXP_XS__REFERRED_COLLECTION_OPERATION:
        fireNotifyChanged(new ViewerNotification(notification,notification.getNotifier(),false,true));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that
   * can be created under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
    super.collectNewChildDescriptors(newChildDescriptors,object);
  }

  /**
   * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String getCreateChildText(Object owner, Object feature, Object child,
      Collection<?> selection) {
    Object childFeature = feature;
    Object childObject = child;

    boolean qualify = childFeature == XOCLPackage.Literals.CALL_EXP_XS__SOURCE
        || childFeature == XOCLPackage.Literals.OPERATION_CALL_EXP_XS__ARGUMENT;

    if (qualify) {
      return getString(
          "_UI_CreateChild_text2", //$NON-NLS-1$
          new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
    }
    return super.getCreateChildText(owner,feature,child,selection);
  }

  /**
   * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  @Override
  public ResourceLocator getResourceLocator() {
    return XOCLEditPlugin.INSTANCE;
  }

}
