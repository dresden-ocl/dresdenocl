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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import tudresden.ocl20.pivot.xocl.LoopExpXS;
import tudresden.ocl20.pivot.xocl.OclExpressionXS;
import tudresden.ocl20.pivot.xocl.VariableXS;
import tudresden.ocl20.pivot.xocl.XOCLFactory;
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * This is the item provider adapter for a
 * {@link tudresden.ocl20.pivot.xocl.LoopExpXS} object.
 */
public abstract class LoopExpXSItemProvider extends CallExpXSItemProvider
    implements IEditingDomainItemProvider, IStructuredItemContentProvider,
    ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

  /**
   * This constructs an instance from a factory and a notifier. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public LoopExpXSItemProvider(AdapterFactory adapterFactory) {
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

    }
    return itemPropertyDescriptors;
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce
   * an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand},
   * {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(
      Object object) {
    if (childrenFeatures == null) {
      super.getChildrenFeatures(object);
      childrenFeatures.add(XOCLPackage.Literals.LOOP_EXP_XS__ITERATOR);
      childrenFeatures.add(XOCLPackage.Literals.LOOP_EXP_XS__BODY);
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
    // Check the type of the specified child object and return the proper
    // feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

  /**
   * This returns the label text for the adapted class.
   * 
   * <p>
   * Adapted to reflect OCL concrete syntax.
   * </p>
   * 
   * @generated NOT
   */
  @Override
  public String getText(Object object) {
    LoopExpXS loopExp = (LoopExpXS) object;

    StringBuilder label;
    OclExpressionXS source;
    String loopOperation;

    // get the source of the loop expression
    source = loopExp.getSource();

    // initialize the label with the source label or the default label
    label = new StringBuilder(source != null ? getLabel(source) : UNDEFINED);

    // get the referred iterator expression
    loopOperation = getLoopOperationName(loopExp);

    // only add the iterator stuff when an iterator has been selected
    if (StringUtils.isNotEmpty(loopOperation)) {

      // add operator, name of loop operation and opening parenthesis
      label.append("->").append(loopOperation).append('('); //$NON-NLS-1$

      // append the iterators
      for (Iterator<VariableXS> it = loopExp.getIterator().iterator(); it
          .hasNext();) {
        label.append(getLabel(it.next()));

        if (it.hasNext()) {
          label.append(", "); //$NON-NLS-1$
        }
      }

      // append an additional result variable if provided by subclasses
      String resultVariable = getResultVariable(loopExp);
      
      if (resultVariable != null) {
        label.append("; ").append(resultVariable).append(" ");  //$NON-NLS-1$//$NON-NLS-2$
      }

      // append the vertical bar if there have been iterators defined
      if (!loopExp.getIterator().isEmpty() || resultVariable != null) {
        label.append(" | "); //$NON-NLS-1$
      }

      // append the body expression
      String bodyLabel = getLabel(loopExp.getBody());
      label.append(StringUtils.isNotEmpty(bodyLabel) ? bodyLabel : UNDEFINED);

      // append closing parenthesis
      label.append(')');
    }

    // if all parts are missing return the default string
    return label.length() != 0 ? label.toString() : getDefaultString();
  }

  /**
   * Is overridden by {@link IterateExpXSItemProvider} to add the result
   * variabke. This implementation simply returns <code>null</code>.
   */
  protected String getResultVariable(LoopExpXS loopExp) {
    return null;
  }

  /**
   * Returns the default string for this loop expression when not enough
   * information has been provided to render a proper label.
   */
  protected abstract String getDefaultString();

  /**
   * Returns the name of the loop operation. This can be the name of the
   * iterator, for instance.
   */
  protected abstract String getLoopOperationName(LoopExpXS loopExp);

  /**
   * This handles model notifications by calling {@link #updateChildren} to
   * update any cached children and by creating a viewer notification, which it
   * passes to {@link #fireNotifyChanged}.
   * 
   * <p>
   * Adapted to update the entire tree if the iterator or body expression
   * changes
   * 
   * @generated NOT
   */
  @Override
  public void notifyChanged(Notification notification) {
    updateChildren(notification);

    switch (notification.getFeatureID(LoopExpXS.class)) {
      case XOCLPackage.LOOP_EXP_XS__ITERATOR:
      case XOCLPackage.LOOP_EXP_XS__BODY:
        fireNotifyChanged(new ViewerNotification(notification, notification
            .getNotifier(), true, false));
        updateLabel(notification);
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

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__ITERATOR, XOCLFactory.eINSTANCE
            .createVariableXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createPropertyCallExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createBooleanLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createCollectionLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createCollectionOperationCallExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createEnumLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createIfExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createIntegerLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createIterateExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createInvalidLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createIteratorExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createLetExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createModelOperationCallExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createRealLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createStaticOperationCallExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createStaticPropertyCallExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createStringLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createTupleLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createTypeLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createUndefinedLiteralExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createUnlimitedNaturalExpXS()));

    newChildDescriptors.add(createChildParameter(
        XOCLPackage.Literals.LOOP_EXP_XS__BODY, XOCLFactory.eINSTANCE
            .createVariableExpXS()));
  }

  /**
   * This returns the label text for
   * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
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
        || childFeature == XOCLPackage.Literals.LOOP_EXP_XS__BODY;

    if (qualify) {
      return getString("_UI_CreateChild_text2", //$NON-NLS-1$
          new Object[] { getTypeText(childObject),
              getFeatureText(childFeature), getTypeText(owner) });
    }
    return super.getCreateChildText(owner, feature, child, selection);
  }

  /**
   * Return the resource locator for this item provider's resources. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public ResourceLocator getResourceLocator() {
    return XOCLEditPlugin.INSTANCE;
  }

}
