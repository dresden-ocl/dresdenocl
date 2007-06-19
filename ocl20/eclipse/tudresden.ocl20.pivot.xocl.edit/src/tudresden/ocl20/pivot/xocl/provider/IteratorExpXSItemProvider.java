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
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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

import tudresden.ocl20.pivot.xocl.IteratorExpXS;
import tudresden.ocl20.pivot.xocl.OclExpressionXS;
import tudresden.ocl20.pivot.xocl.VariableXS;
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * This is the item provider adapter for a {@link tudresden.ocl20.pivot.xocl.IteratorExpXS} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IteratorExpXSItemProvider extends LoopExpXSItemProvider implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
    IItemLabelProvider, IItemPropertySource {

  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IteratorExpXSItemProvider(AdapterFactory adapterFactory) {
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

      addNamePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Name feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addNamePropertyDescriptor(Object object) {
    itemPropertyDescriptors
        .add(createItemPropertyDescriptor(
            ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_IteratorExpXS_name_feature"), //$NON-NLS-1$
            getString(
                "_UI_PropertyDescriptor_description","_UI_IteratorExpXS_name_feature","_UI_IteratorExpXS_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            XOCLPackage.Literals.ITERATOR_EXP_XS__NAME,true,false,false,
            ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,null,null));
  }

  /**
   * This returns IteratorExpXS.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object) {
    return overlayImage(object,getResourceLocator().getImage("full/obj16/IteratorExpXS")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class.
   * 
   * <p>
   * Adapted to reflect the OCL concrete syntax.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public String getText(Object object) {
    IteratorExpXS iteratorExp = (IteratorExpXS) object;
    
    // get the source of the iterator expression
    OclExpressionXS source = iteratorExp.getSource();
    
    // initialize the label with the source label or the default label
    StringBuilder label = new StringBuilder(source != null ? getLabel(source) : UNDEFINED);
    
    // get the referred iterator expression
    String referredIterator = iteratorExp.getName().toString();
    
    // only add the iterator stuff when an iterator has been selected
    if (StringUtils.isNotEmpty(referredIterator)) {
      
      // add the operator, the name of the iterator and the opening parenthesis
      label.append("->").append(referredIterator).append('('); //$NON-NLS-1$
      
      // append the iterators
      for (Iterator<VariableXS> it = iteratorExp.getIterator().iterator(); it.hasNext();) {
        label.append(getLabel(it.next()));
        
        if (it.hasNext()) {
          label.append(", "); //$NON-NLS-1$
        }
      }
      
      // append the vertical bar if there have been iterators defined
      if (!iteratorExp.getIterator().isEmpty()) {
        label.append(" | "); //$NON-NLS-1$
      }
      
      // append the body expression
      String bodyLabel = getLabel(iteratorExp.getBody());
      label.append(StringUtils.isNotEmpty(bodyLabel) ? bodyLabel : UNDEFINED);
      
      // append closing parenthesis
      label.append(')');
    }

    // if all parts are missing return the default string
    return label.length() != 0 ? label.toString() : getString("_UI_IteratorExpXS_type"); //$NON-NLS-1$
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * 
   * <p>
   * Adapted to change the entire tree if the name of the iterator changes
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public void notifyChanged(Notification notification) {
    updateChildren(notification);

    switch (notification.getFeatureID(IteratorExpXS.class)) {
      case XOCLPackage.ITERATOR_EXP_XS__NAME:
        updateLabel(notification);
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
        || childFeature == XOCLPackage.Literals.LOOP_EXP_XS__BODY;

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
