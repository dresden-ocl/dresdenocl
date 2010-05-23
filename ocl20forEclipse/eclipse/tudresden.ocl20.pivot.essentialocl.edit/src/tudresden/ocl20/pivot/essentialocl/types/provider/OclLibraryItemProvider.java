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
package tudresden.ocl20.pivot.essentialocl.types.provider;

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
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.essentialocl.types.TypesFactory;
import tudresden.ocl20.pivot.essentialocl.types.impl.TypesPackageImpl;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;

/**
 * This is the item provider adapter for a {@link tudresden.ocl20.pivot.essentialocl.types.OclLibrary} object.
 * <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * @generated
 */
public class OclLibraryItemProvider extends ItemProviderAdapter implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
    IItemLabelProvider, IItemPropertySource {

  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @generated
   */
  public OclLibraryItemProvider(AdapterFactory adapterFactory) {
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
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate
   * feature for an {@link org.eclipse.emf.edit.command.AddCommand},
   * {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
    if (childrenFeatures == null) {
      super.getChildrenFeatures(object);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_BOOLEAN);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_STRING);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_INTEGER);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_REAL);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_ANY);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_VOID);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_INVALID);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_TYPE);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_COLLECTION);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_SEQUENCE);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_BAG);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_SET);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_ORDERED_SET);
      childrenFeatures.add(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_TUPLE);
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

    return super.getChildFeature(object,child);
  }

  /**
   * This returns OclLibrary.gif.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object) {
    return overlayImage(object,getResourceLocator().getImage("full/obj16/OclLibrary")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   */
  @Override
  public String getText(Object object) {
    return getString("_UI_OclLibrary_type"); //$NON-NLS-1$
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

    switch (notification.getFeatureID(OclLibrary.class)) {
      case TypesPackageImpl.OCL_LIBRARY__OCL_BOOLEAN:
      case TypesPackageImpl.OCL_LIBRARY__OCL_STRING:
      case TypesPackageImpl.OCL_LIBRARY__OCL_INTEGER:
      case TypesPackageImpl.OCL_LIBRARY__OCL_REAL:
      case TypesPackageImpl.OCL_LIBRARY__OCL_ANY:
      case TypesPackageImpl.OCL_LIBRARY__OCL_VOID:
      case TypesPackageImpl.OCL_LIBRARY__OCL_INVALID:
      case TypesPackageImpl.OCL_LIBRARY__OCL_TYPE:
      case TypesPackageImpl.OCL_LIBRARY__OCL_COLLECTION:
      case TypesPackageImpl.OCL_LIBRARY__OCL_SEQUENCE:
      case TypesPackageImpl.OCL_LIBRARY__OCL_BAG:
      case TypesPackageImpl.OCL_LIBRARY__OCL_SET:
      case TypesPackageImpl.OCL_LIBRARY__OCL_ORDERED_SET:
      case TypesPackageImpl.OCL_LIBRARY__OCL_TUPLE:
        fireNotifyChanged(new ViewerNotification(notification,notification.getNotifier(),true,false));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
   * describing all of the children that can be created under this object.
   * 
   * <p>
   * The EMF implementation has been changed to prevent {@link CollectionType}s to be created for
   * the specific collection references
   * {@link TypesPackageImpl.Literals#OCL_LIBRARY__OCL_SEQUENCE oclSequence},
   * {@link TypesPackageImpl.Literals#OCL_LIBRARY__OCL_BAG oclBag}, etc.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors,
      Object object) {
    super.collectNewChildDescriptors(newChildDescriptors,object);

    newChildDescriptors.add(createChildParameter(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_VOID,
        TypesFactory.INSTANCE.createVoidType()));

    newChildDescriptors.add(createChildParameter(
        TypesPackageImpl.Literals.OCL_LIBRARY__OCL_BOOLEAN,PivotModelFactory.eINSTANCE
            .createPrimitiveType()));

    newChildDescriptors.add(createChildParameter(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_STRING,
        PivotModelFactory.eINSTANCE.createPrimitiveType()));

    newChildDescriptors.add(createChildParameter(
        TypesPackageImpl.Literals.OCL_LIBRARY__OCL_INTEGER,PivotModelFactory.eINSTANCE
            .createPrimitiveType()));

    newChildDescriptors.add(createChildParameter(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_REAL,
        PivotModelFactory.eINSTANCE.createPrimitiveType()));

    newChildDescriptors.add(createChildParameter(
        TypesPackageImpl.Literals.OCL_LIBRARY__OCL_INVALID,TypesFactory.INSTANCE
            .createInvalidType()));

    newChildDescriptors.add(createChildParameter(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_TYPE,
        TypesFactory.INSTANCE.createTypeType()));

    newChildDescriptors.add(createChildParameter(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_ANY,
        TypesFactory.INSTANCE.createAnyType()));

    newChildDescriptors.add(createChildParameter(
        TypesPackageImpl.Literals.OCL_LIBRARY__OCL_COLLECTION,TypesFactory.INSTANCE
            .createCollectionType()));

    newChildDescriptors.add(createChildParameter(
        TypesPackageImpl.Literals.OCL_LIBRARY__OCL_SEQUENCE,TypesFactory.INSTANCE
            .createSequenceType()));

    newChildDescriptors.add(createChildParameter(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_BAG,
        TypesFactory.INSTANCE.createBagType()));

    newChildDescriptors.add(createChildParameter(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_SET,
        TypesFactory.INSTANCE.createSetType()));

    newChildDescriptors.add(createChildParameter(
        TypesPackageImpl.Literals.OCL_LIBRARY__OCL_ORDERED_SET,TypesFactory.INSTANCE
            .createOrderedSetType()));

    newChildDescriptors.add(createChildParameter(TypesPackageImpl.Literals.OCL_LIBRARY__OCL_TUPLE,
        TypesFactory.INSTANCE.createTupleType()));
  }

  /**
   * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
   * 
   * <p>
   * The EMF implementation has been changed to prevent the collection features to be qualified with
   * their type.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public String getCreateChildText(Object owner, Object feature, Object child,
      Collection<?> selection) {
    Object childFeature = feature;
    Object childObject = child;

    boolean qualify = childFeature == TypesPackageImpl.Literals.OCL_LIBRARY__OCL_BOOLEAN
        || childFeature == TypesPackageImpl.Literals.OCL_LIBRARY__OCL_STRING
        || childFeature == TypesPackageImpl.Literals.OCL_LIBRARY__OCL_INTEGER
        || childFeature == TypesPackageImpl.Literals.OCL_LIBRARY__OCL_REAL;

    if (qualify) {
      return getString(
          "_UI_CreateChild_text2", //$NON-NLS-1$
          new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
    }
    return super.getCreateChildText(owner,feature,child,selection);
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
