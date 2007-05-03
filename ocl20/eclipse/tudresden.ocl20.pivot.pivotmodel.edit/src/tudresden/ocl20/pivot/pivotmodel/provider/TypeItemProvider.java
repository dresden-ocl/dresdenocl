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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import tudresden.ocl20.pivot.pivotmodel.ComplexGenericType;
import tudresden.ocl20.pivot.pivotmodel.GenericType;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypeParameter;
import tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl;

/**
 * This is the item provider adapter for a {@link tudresden.ocl20.pivot.pivotmodel.Type} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class TypeItemProvider extends NamedElementItemProvider implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
    IItemLabelProvider, IItemPropertySource {

  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @generated
   */
  public TypeItemProvider(AdapterFactory adapterFactory) {
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

      addSuperTypePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Super Type feature.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @generated
   */
  protected void addSuperTypePropertyDescriptor(Object object) {
    itemPropertyDescriptors
        .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory)
            .getRootAdapterFactory(),getResourceLocator(),getString("_UI_Type_superType_feature"), //$NON-NLS-1$
            getString(
                "_UI_PropertyDescriptor_description","_UI_Type_superType_feature","_UI_Type_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            PivotModelPackageImpl.Literals.TYPE__SUPER_TYPE,true,true,true,null,null,null));
  }

  /**
   * A special property descriptor for the super types of a {@link Type} that additionally includes
   * all {@link TypeParameter}s of the {@link Type}.
   */
  protected class SuperTypePropertyDescriptor extends ItemPropertyDescriptor {

    /**
     * Creates a new <code>TypePropertyDescriptor</code> instance using the default EMF
     */
    public SuperTypePropertyDescriptor(String displayName, String description) {
      super(((ComposeableAdapterFactory) getAdapterFactory()).getRootAdapterFactory(),
          getResourceLocator(),displayName,description,
          PivotModelPackageImpl.Literals.TYPE__SUPER_TYPE,true,false,true,null,null,null);
    }

    /**
     * 
     * 
     * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#setPropertyValue(java.lang.Object,
     *      java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setPropertyValue(Object object, Object value) {
      EList<Type> superTypes = (EList<Type>) value;
      EList<GenericType> genericSuperTypes = new BasicEList<GenericType>();

      for (Iterator<Type> it = superTypes.iterator(); it.hasNext();) {
        Type type = it.next();

        // check if the type has any type parameters
        if (!type.getOwnedTypeParameter().isEmpty()) {

          // remove that type from the list of super types
          it.remove();

          // create a new complex generic type and set the given type as a reference
          ComplexGenericType genericType = PivotModelFactory.INSTANCE.createComplexGenericType();
          genericType.setUnboundType(type);

          // append type arguments for each type parameter
          for (int i = 0, size = type.getOwnedTypeParameter().size(); i < size; i++) {
            genericType.getTypeArgument().add(PivotModelFactory.INSTANCE.createTypeArgument());
          }

          // add the new generic type to the list of generic super types
          genericSuperTypes.add(genericType);
        }
      }

      // set the non-generic super types
      super.setPropertyValue(object,superTypes);

      // if any generic super types have been found, add these as children
      if (!genericSuperTypes.isEmpty()) {
        addGenericSuperTypes((Type) object,genericSuperTypes);
      }
    }

    /**
     * Helper method to add the generic super type to a {@link Type}. Uses a
     * <code>CreateChildCommand</code> for this purpose so the action can be undone.
     */
    protected void addGenericSuperTypes(Type type, List<GenericType> genericSuperTypes) {
      EditingDomain editingDomain = getEditingDomain(type);

      if (editingDomain != null) {
        editingDomain.getCommandStack().execute(
            SetCommand.create(editingDomain,type,
                PivotModelPackageImpl.Literals.TYPE__GENERIC_SUPER_TYPE,genericSuperTypes));
      }

      else {
        type.getGenericSuperType().addAll(genericSuperTypes);
      }

    }
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
      childrenFeatures.add(PivotModelPackageImpl.Literals.TYPE__OWNED_OPERATION);
      childrenFeatures.add(PivotModelPackageImpl.Literals.TYPE__OWNED_PROPERTY);
      childrenFeatures.add(PivotModelPackageImpl.Literals.TYPE__GENERIC_SUPER_TYPE);
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
   * This returns Type.gif.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object) {
    return overlayImage(object,getResourceLocator().getImage("full/obj16/Type")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class.
   * 
   * <p>
   * The EMF implementation is extended by the display of super types, in analogy to the Ecore
   * editor.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public String getText(Object object) {
    Type type;
    StringBuilder label;

    // provide temporary casted variable
    type = (Type) object;

    // get the name including the type arguments
    label = new StringBuilder(getTypeNameWithTypeArguments(type));

    // append supertypes if there are any
    if (!type.getSuperType().isEmpty()) {
      label.append(" -> "); //$NON-NLS-1$

      for (Iterator<Type> it = type.getSuperType().iterator(); it.hasNext();) {
        label.append(it.next().getName());

        if (it.hasNext()) {
          label.append(", "); //$NON-NLS-1$
        }
      }
    }

    // append generic super types if any
    if (!type.getGenericSuperType().isEmpty()) {

      // no non-generic super types
      if (type.getSuperType().isEmpty()) {
        label.append(" -> "); //$NON-NLS-1$
      }

      else {
        label.append(", "); //$NON-NLS-1$
      }

      for (Iterator<GenericType> it = type.getGenericSuperType().iterator(); it.hasNext();) {
        GenericType genericType = it.next();
        label.append(getLabelProvider(genericType).getText(genericType));

        if (it.hasNext()) {
          label.append(", "); //$NON-NLS-1$
        }
      }
    }

    return label.toString();
  }

  /**
   * Helper method that returns the name of the adapted {@link Type} including any
   * {@link TypeParameter}s that may have been defined.
   * 
   * @return a <code>CharSequence</code> representing
   */
  protected CharSequence getTypeNameWithTypeArguments(Type type) {
    StringBuilder name;

    // initialize with the type's name
    name = new StringBuilder(getTypeName(type));

    // append type parameters if there are any
    if (!type.getOwnedTypeParameter().isEmpty()) {
      name.append(getTypeParameterListOpeningDelimiter());

      for (Iterator<TypeParameter> it = type.getOwnedTypeParameter().iterator(); it.hasNext();) {
        name.append(it.next().getName());

        if (it.hasNext()) {
          name.append(", "); //$NON-NLS-1$
        }
      }

      name.append(getTypeParameterListClosingDelimiter());
    }

    return name;
  }

  /**
   * Helper method that returns the name of the {@link Type}. This is meant as a chance for
   * subclasses to alter the way the type name is displayed. The default implementation simply
   * returns the name of the type.
   * 
   * @return a <code>CharSequence</code> with the name of the type
   */
  protected CharSequence getTypeName(Type type) {
    return type.getName();
  }

  /**
   * Overridden to create a generic super type if the type to be added has type parameters.
   * 
   * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createCreateChildCommand(org.eclipse.emf.edit.domain.EditingDomain,
   *      org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object,
   *      int, java.util.Collection)
   */
  @Override
  protected Command createCreateChildCommand(EditingDomain domain, EObject owner,
      EStructuralFeature feature, Object value, int index, Collection<?> collection) {

    // check whether a new supertype is about to be added
    if (feature == PivotModelPackageImpl.Literals.TYPE__SUPER_TYPE) {
      Type superType = (Type) value;

      // check if this is a generic super type
      if (superType.getOwnedTypeParameter().size() > 0) {

        // create a new complex generic type and set the given type as a reference
        ComplexGenericType genericType = PivotModelFactory.INSTANCE.createComplexGenericType();
        genericType.setUnboundType(superType);

        // append type arguments for each type parameter
        for (int i = 0, size = superType.getOwnedTypeParameter().size(); i < size; i++) {
          genericType.getTypeArgument().add(PivotModelFactory.INSTANCE.createTypeArgument());
        }

        return new CreateChildCommand(domain,owner,
            PivotModelPackageImpl.Literals.TYPE__GENERIC_SUPER_TYPE,genericType,index,collection,
            this);
      }
    }

    return super.createCreateChildCommand(domain,owner,feature,value,index,collection);
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * 
   * <p>
   * The EMF implementation is extended to update the label of the type if the generic type
   * parameters change.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public void notifyChanged(Notification notification) {
    updateChildren(notification);

    switch (notification.getFeatureID(Type.class)) {
      case PivotModelPackageImpl.TYPE__OWNED_TYPE_PARAMETER:
        fireNotifyChanged(new ViewerNotification(notification,notification.getNotifier(),true,true));
        return;
      case PivotModelPackageImpl.TYPE__OWNED_OPERATION:
      case PivotModelPackageImpl.TYPE__OWNED_PROPERTY:
        fireNotifyChanged(new ViewerNotification(notification,notification.getNotifier(),true,false));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
   * describing all of the children that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
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
        PivotModelPackageImpl.Literals.TYPE__OWNED_OPERATION,PivotModelFactory.INSTANCE
            .createOperation()));

    newChildDescriptors.add(createChildParameter(
        PivotModelPackageImpl.Literals.TYPE__OWNED_PROPERTY,PivotModelFactory.INSTANCE
            .createProperty()));
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @generated
   */
  @Override
  public ResourceLocator getResourceLocator() {
    return PivotModelEditPlugin.INSTANCE;
  }

}
