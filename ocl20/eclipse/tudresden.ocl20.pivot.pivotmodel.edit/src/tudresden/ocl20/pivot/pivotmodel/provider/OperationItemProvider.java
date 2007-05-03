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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.TypeParameter;
import tudresden.ocl20.pivot.pivotmodel.TypedElement;
import tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl;

/**
 * This is the item provider adapter for a {@link tudresden.ocl20.pivot.pivotmodel.Operation}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class OperationItemProvider extends FeatureItemProvider implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
    IItemLabelProvider, IItemPropertySource {

  /**
   * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  public OperationItemProvider(AdapterFactory adapterFactory) {
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

      addOrderedPropertyDescriptor(object);
      addUniquePropertyDescriptor(object);
      addMultiplePropertyDescriptor(object);
      addInputParameterPropertyDescriptor(object);
      addOutputParameterPropertyDescriptor(object);
      addReturnParameterPropertyDescriptor(object);
      addSignatureParameterPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Ordered feature. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  protected void addOrderedPropertyDescriptor(Object object) {
    itemPropertyDescriptors
        .add(createItemPropertyDescriptor(
            ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_MultiplicityElement_ordered_feature"), //$NON-NLS-1$
            getString(
                "_UI_PropertyDescriptor_description","_UI_MultiplicityElement_ordered_feature","_UI_MultiplicityElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            PivotModelPackageImpl.Literals.MULTIPLICITY_ELEMENT__ORDERED,true,false,false,
            ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,null,null));
  }

  /**
   * This adds a property descriptor for the Unique feature. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  protected void addUniquePropertyDescriptor(Object object) {
    itemPropertyDescriptors
        .add(createItemPropertyDescriptor(
            ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_MultiplicityElement_unique_feature"), //$NON-NLS-1$
            getString(
                "_UI_PropertyDescriptor_description","_UI_MultiplicityElement_unique_feature","_UI_MultiplicityElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            PivotModelPackageImpl.Literals.MULTIPLICITY_ELEMENT__UNIQUE,true,false,false,
            ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,null,null));
  }

  /**
   * This adds a property descriptor for the Multiple feature. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  protected void addMultiplePropertyDescriptor(Object object) {
    itemPropertyDescriptors
        .add(createItemPropertyDescriptor(
            ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_MultiplicityElement_multiple_feature"), //$NON-NLS-1$
            getString(
                "_UI_PropertyDescriptor_description","_UI_MultiplicityElement_multiple_feature","_UI_MultiplicityElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            PivotModelPackageImpl.Literals.MULTIPLICITY_ELEMENT__MULTIPLE,true,false,false,
            ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,null,null));
  }

  /**
   * This adds a property descriptor for the Input Parameter feature. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  protected void addInputParameterPropertyDescriptor(Object object) {
    itemPropertyDescriptors
        .add(createItemPropertyDescriptor(
            ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_Operation_inputParameter_feature"), //$NON-NLS-1$
            getString(
                "_UI_PropertyDescriptor_description","_UI_Operation_inputParameter_feature","_UI_Operation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            PivotModelPackageImpl.Literals.OPERATION__INPUT_PARAMETER,false,false,false,null,null,
            null));
  }

  /**
   * This adds a property descriptor for the Output Parameter feature. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  protected void addOutputParameterPropertyDescriptor(Object object) {
    itemPropertyDescriptors
        .add(createItemPropertyDescriptor(
            ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_Operation_outputParameter_feature"), //$NON-NLS-1$
            getString(
                "_UI_PropertyDescriptor_description","_UI_Operation_outputParameter_feature","_UI_Operation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            PivotModelPackageImpl.Literals.OPERATION__OUTPUT_PARAMETER,false,false,false,null,null,
            null));
  }

  /**
   * This adds a property descriptor for the Return Parameter feature. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  protected void addReturnParameterPropertyDescriptor(Object object) {
    itemPropertyDescriptors
        .add(createItemPropertyDescriptor(
            ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_Operation_returnParameter_feature"), //$NON-NLS-1$
            getString(
                "_UI_PropertyDescriptor_description","_UI_Operation_returnParameter_feature","_UI_Operation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            PivotModelPackageImpl.Literals.OPERATION__RETURN_PARAMETER,false,false,false,null,null,
            null));
  }

  /**
   * This adds a property descriptor for the Signature Parameter feature. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addSignatureParameterPropertyDescriptor(Object object) {
    itemPropertyDescriptors
        .add(createItemPropertyDescriptor(
            ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_Operation_signatureParameter_feature"), //$NON-NLS-1$
            getString(
                "_UI_PropertyDescriptor_description","_UI_Operation_signatureParameter_feature","_UI_Operation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            PivotModelPackageImpl.Literals.OPERATION__SIGNATURE_PARAMETER,false,false,false,null,
            null,null));
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
      childrenFeatures.add(PivotModelPackageImpl.Literals.GENERIC_ELEMENT__OWNED_TYPE_PARAMETER);
      childrenFeatures.add(PivotModelPackageImpl.Literals.OPERATION__OWNED_PARAMETER);
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
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object,child);
  }

  /**
   * This returns Operation.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object getImage(Object object) {
    return overlayImage(object,getResourceLocator().getImage("full/obj16/Operation")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class.
   * 
   * <p>
   * The EMF-generated code is extended to simply call
   * {@link super#getText(Object) the super implementation}.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public String getText(Object object) {
    return super.getText(object);
  }

  /**
   * Overridden to return the name of the operation and a list of parameters. If the operation is
   * generic, it will also prepend any type parameters in analogy to Java generics.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.provider.TypedElementItemProvider#getTypedElementName(tudresden.ocl20.pivot.pivotmodel.TypedElement)
   */
  @Override
  protected CharSequence getTypedElementName(TypedElement typedElement) {
    StringBuilder name;
    Operation operation;

    // create a casted version of the typed element
    operation = (Operation) typedElement;

    // initialize with empty string
    name = new StringBuilder();

    // append type parameters if there are any
    if (!operation.getOwnedTypeParameter().isEmpty()) {
      name.append(getTypeParameterListOpeningDelimiter());

      for (Iterator<TypeParameter> it = operation.getOwnedTypeParameter().iterator(); it.hasNext();) {
        name.append(it.next().getName());

        if (it.hasNext()) {
          name.append(", "); //$NON-NLS-1$
        }
      }

      name.append(getTypeParameterListClosingDelimiter()).append(' ');
    }

    // append the name of the operation
    name.append(operation.getName());

    // append parameters
    name.append('(');

    for (Iterator<Parameter> it = operation.getSignatureParameter().iterator(); it.hasNext();) {
      Parameter parameter = it.next();

      // use the parameter-specific label provider for rendering the text
      name.append(getLabelProvider(parameter).getText(parameter));

      if (it.hasNext()) {
        name.append(", "); //$NON-NLS-1$
      }
    }

    name.append(')');

    return name;
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * 
   * <p>
   * The EMF implementation is altered here to send content refresh and label refresh notifications
   * if the owned parameters or the (generic) type of the operation changes (an update to a
   * contained return parameter might be necessary). In addition, changes to the owned type
   * parameters should trigger a label update.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public void notifyChanged(Notification notification) {
    updateChildren(notification);

    switch (notification.getFeatureID(Operation.class)) {
      case PivotModelPackageImpl.OPERATION__TYPE:
      case PivotModelPackageImpl.OPERATION__GENERIC_TYPE:
      case PivotModelPackageImpl.OPERATION__OWNED_PARAMETER:
      case PivotModelPackageImpl.OPERATION__OWNED_TYPE_PARAMETER:
        fireNotifyChanged(new ViewerNotification(notification,notification.getNotifier(),true,true));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
   * describing all of the children that can be created under this object. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<CommandParameter> newChildDescriptors,
      Object object) {
    super.collectNewChildDescriptors(newChildDescriptors,object);

    newChildDescriptors.add(createChildParameter(
        PivotModelPackageImpl.Literals.GENERIC_ELEMENT__OWNED_TYPE_PARAMETER,
        PivotModelFactory.INSTANCE.createTypeParameter()));

    newChildDescriptors.add(createChildParameter(
        PivotModelPackageImpl.Literals.OPERATION__OWNED_PARAMETER,PivotModelFactory.INSTANCE
            .createParameter()));
  }

  /**
   * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  @Override
  public ResourceLocator getResourceLocator() {
    return PivotModelEditPlugin.INSTANCE;
  }

}
