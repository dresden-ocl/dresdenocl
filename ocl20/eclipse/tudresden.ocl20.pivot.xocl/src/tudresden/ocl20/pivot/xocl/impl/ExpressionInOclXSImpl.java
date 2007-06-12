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
package tudresden.ocl20.pivot.xocl.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.xocl.ConstraintKindXS;
import tudresden.ocl20.pivot.xocl.ConstraintXS;
import tudresden.ocl20.pivot.xocl.ExpressionInOclXS;
import tudresden.ocl20.pivot.xocl.OclExpressionXS;
import tudresden.ocl20.pivot.xocl.VariableExpXS;
import tudresden.ocl20.pivot.xocl.VariableXS;
import tudresden.ocl20.pivot.xocl.XOCLFactory;
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Expression In Ocl XS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link tudresden.ocl20.pivot.xocl.impl.ExpressionInOclXSImpl#getBody <em>Body</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.xocl.impl.ExpressionInOclXSImpl#getConstraint <em>Constraint</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.xocl.impl.ExpressionInOclXSImpl#getBodyExpression <em>Body Expression</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.xocl.impl.ExpressionInOclXSImpl#getContext <em>Context</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.xocl.impl.ExpressionInOclXSImpl#getParameter <em>Parameter</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.xocl.impl.ExpressionInOclXSImpl#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ExpressionInOclXSImpl extends EObjectImpl implements ExpressionInOclXS {

  /**
   * The default value of the '{@link #getBody() <em>Body</em>}' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @see #getBody()
   * @generated
   * @ordered
   */
  protected static final String BODY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBody() <em>Body</em>}' attribute. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @see #getBody()
   * @generated
   * @ordered
   */
  protected String body = BODY_EDEFAULT;

  /**
   * The cached value of the '{@link #getBodyExpression() <em>Body Expression</em>}' containment
   * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getBodyExpression()
   * @generated
   * @ordered
   */
  protected OclExpressionXS bodyExpression = null;

  /**
   * The cached value of the '{@link #getContext() <em>Context</em>}' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getContext()
   * @generated
   * @ordered
   */
  protected VariableXS context = null;

  /**
   * The cached value of the '{@link #getParameter() <em>Parameter</em>}' containment reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getParameter()
   * @generated
   * @ordered
   */
  protected EList<VariableXS> parameter = null;

  /**
   * The cached value of the '{@link #getResult() <em>Result</em>}' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getResult()
   * @generated
   * @ordered
   */
  protected VariableXS result = null;

  /**
   * A regular expression that matches an operation signature.
   */
  protected Pattern operationPattern;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected ExpressionInOclXSImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return XOCLPackage.Literals.EXPRESSION_IN_OCL_XS;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String getBody() {
    return body;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setBody(String newBody) {
    String oldBody = body;
    body = newBody;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,XOCLPackage.EXPRESSION_IN_OCL_XS__BODY,
          oldBody,body));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public OclExpressionXS getBodyExpression() {
    return bodyExpression;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotificationChain basicSetBodyExpression(OclExpressionXS newBodyExpression,
      NotificationChain msgs) {
    OclExpressionXS oldBodyExpression = bodyExpression;
    bodyExpression = newBodyExpression;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,Notification.SET,
          XOCLPackage.EXPRESSION_IN_OCL_XS__BODY_EXPRESSION,oldBodyExpression,newBodyExpression);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setBodyExpression(OclExpressionXS newBodyExpression) {
    if (newBodyExpression != bodyExpression) {
      NotificationChain msgs = null;
      if (bodyExpression != null)
        msgs = ((InternalEObject) bodyExpression).eInverseRemove(this,EOPPOSITE_FEATURE_BASE
            - XOCLPackage.EXPRESSION_IN_OCL_XS__BODY_EXPRESSION,null,msgs);
      if (newBodyExpression != null)
        msgs = ((InternalEObject) newBodyExpression).eInverseAdd(this,EOPPOSITE_FEATURE_BASE
            - XOCLPackage.EXPRESSION_IN_OCL_XS__BODY_EXPRESSION,null,msgs);
      msgs = basicSetBodyExpression(newBodyExpression,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          XOCLPackage.EXPRESSION_IN_OCL_XS__BODY_EXPRESSION,newBodyExpression,newBodyExpression));
  }

  /**
   * Adapted the EMF implementation to create the context variable if it does not exist yet. The
   * type of the variable is based on the value of the
   * {@link ConstraintXS#getConstrainedElement() constrainedElement} reference of the associated
   * {@link ConstraintXS constraint}. The variable is named <code>self</code>.
   * 
   * @generated NOT
   */
  public VariableXS getContext() {

    // lazily create the context variable
    if (context == null) {
      VariableXS context = XOCLFactory.eINSTANCE.createVariableXS();
      context.setName("self"); //$NON-NLS-1$
      setContext(context);
    }

    // determine the contextual type
    String contextualType = getContextualType();

    // only set the new type if different from current type (setType checks only for identity)
    if (contextualType == null && context.getType() != null || contextualType != null
        && !contextualType.equals(context.getType())) {
      context.setType(contextualType);
    }

    return context;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotificationChain basicSetContext(VariableXS newContext, NotificationChain msgs) {
    VariableXS oldContext = context;
    context = newContext;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,Notification.SET,
          XOCLPackage.EXPRESSION_IN_OCL_XS__CONTEXT,oldContext,newContext);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setContext(VariableXS newContext) {
    if (newContext != context) {
      NotificationChain msgs = null;
      if (context != null)
        msgs = ((InternalEObject) context).eInverseRemove(this,EOPPOSITE_FEATURE_BASE
            - XOCLPackage.EXPRESSION_IN_OCL_XS__CONTEXT,null,msgs);
      if (newContext != null)
        msgs = ((InternalEObject) newContext).eInverseAdd(this,EOPPOSITE_FEATURE_BASE
            - XOCLPackage.EXPRESSION_IN_OCL_XS__CONTEXT,null,msgs);
      msgs = basicSetContext(newContext,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          XOCLPackage.EXPRESSION_IN_OCL_XS__CONTEXT,newContext,newContext));
  }

  /**
   * Adapted the EMF implementation to create the <code>parameter</code> variables if the
   * associated {@link ConstraintXS constraint} refers to an operation (i.e., it is a
   * {@link ConstraintKindXS#BODY body}, {@link ConstraintKindXS#PRECONDITION precondition} or
   * {@link ConstraintKindXS#POSTCONDITION postcondition} constraint).
   * 
   * @generated NOT
   */
  public EList<VariableXS> getParameter() {

    if (parameter == null) {
      parameter = new EObjectContainmentEList<VariableXS>(VariableXS.class,this,
          XOCLPackage.EXPRESSION_IN_OCL_XS__PARAMETER);
    }

    // clear parameter variables that have been created previously
    if (!isOperationConstraint()) {
      parameter.clear();
    }

    else {
      String[][] parameterArray = getConstrainedOperationParameters();

      // the parameter array might be null if no valid operation signature was found
      if (parameterArray == null) {
        parameter.clear();
      }

      else {

        // check if the number of parameters has changed since last time
        if (parameter.size() != parameterArray.length) {
          parameter.clear();

          // create a variable for each parameter
          for (int i = 0; i < parameterArray.length; i++) {
            String[] parameterNameAndType = parameterArray[i];

            VariableXS parameterVariable = XOCLFactory.eINSTANCE.createVariableXS();
            parameterVariable.setName(parameterNameAndType[0]);
            parameterVariable.setType(parameterNameAndType[1]);

            parameter.add(parameterVariable);
          }
        }

        // else check each parameter if its name and type needs to be updated
        else {
          for (ListIterator<VariableXS> it = parameter.listIterator(); it.hasNext();) {
            VariableXS parameterVariable = it.next();

            String parameterName = parameterArray[it.previousIndex()][0];

            if (parameterVariable.getName() != parameterName) {
              parameterVariable.setName(parameterName);
            }

            String parameterType = parameterArray[it.previousIndex()][1];

            if (parameterVariable.getType() != parameterType) {
              parameterVariable.setType(parameterType);
            }
          }
        }
      }
    }

    return parameter;
  }

  /**
   * Adapted the EMF implementation to create the <code>result</code> variable if the associated
   * {@link ConstraintXS constraint} refers to an operation (i.e., it is a
   * {@link ConstraintKindXS#BODY body}, {@link ConstraintKindXS#PRECONDITION precondition} or
   * {@link ConstraintKindXS#POSTCONDITION postcondition} constraint).
   * 
   * @generated NOT
   */
  public VariableXS getResult() {

    if (isOperationConstraint()) {

      // determine the type of the result
      String returnType = getConstrainedOperationReturnType();

      // if the return type is given
      if (returnType != null) {

        // lazily create the result
        if (result == null) {
          VariableXS result = XOCLFactory.eINSTANCE.createVariableXS();
          result.setName("result"); //$NON-NLS-1$
          setResult(result);
        }

        // update the type of the result if it has changed
        String resultType = result.getType();

        if (resultType == null || !resultType.equals(returnType)) {
          result.setType(returnType);
        }
      }

      // reset the result if the user has removed the return type
      else if (result != null) {
        setResult(null);
      }
    }

    // reset the result if the constraint is not an operation constraint any more
    else if (result != null) {
      setResult(null);
    }

    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotificationChain basicSetResult(VariableXS newResult, NotificationChain msgs) {
    VariableXS oldResult = result;
    result = newResult;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,Notification.SET,
          XOCLPackage.EXPRESSION_IN_OCL_XS__RESULT,oldResult,newResult);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setResult(VariableXS newResult) {
    if (newResult != result) {
      NotificationChain msgs = null;
      if (result != null)
        msgs = ((InternalEObject) result).eInverseRemove(this,EOPPOSITE_FEATURE_BASE
            - XOCLPackage.EXPRESSION_IN_OCL_XS__RESULT,null,msgs);
      if (newResult != null)
        msgs = ((InternalEObject) newResult).eInverseAdd(this,EOPPOSITE_FEATURE_BASE
            - XOCLPackage.EXPRESSION_IN_OCL_XS__RESULT,null,msgs);
      msgs = basicSetResult(newResult,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,XOCLPackage.EXPRESSION_IN_OCL_XS__RESULT,
          newResult,newResult));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public ConstraintXS getConstraint() {
    if (eContainerFeatureID != XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT) return null;
    return (ConstraintXS) eContainer();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotificationChain basicSetConstraint(ConstraintXS newConstraint, NotificationChain msgs) {
    msgs = eBasicSetContainer((InternalEObject) newConstraint,
        XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT,msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setConstraint(ConstraintXS newConstraint) {
    if (newConstraint != eInternalContainer()
        || (eContainerFeatureID != XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT && newConstraint != null)) {
      if (EcoreUtil.isAncestor(this,newConstraint))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
      NotificationChain msgs = null;
      if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
      if (newConstraint != null)
        msgs = ((InternalEObject) newConstraint).eInverseAdd(this,
            XOCLPackage.CONSTRAINT_XS__SPECIFICATION,ConstraintXS.class,msgs);
      msgs = basicSetConstraint(newConstraint,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT,newConstraint,newConstraint));
  }

  /**
   * Helper method that returns the name of the constrained element of the associated
   * {@link ConstraintXS constraint}.
   */
  protected String getConstrainedElement() {
    return getConstraint().getConstrainedElement();
  }

  /**
   * Helper method that returns the contextual type of the OCL expression. This follows the
   * definitions given in the OCL specification.
   */
  protected String getContextualType() {
    String contextualType;

    // initialize with the path of the constrained element
    contextualType = getConstrainedElement();

    // if this is a property or operation constraint
    if (!isTypeConstraint()) {

      // the type is the part of the string before the last '::' characters
      if (contextualType != null) {
        int lastSeparatorIndex = contextualType.lastIndexOf("::"); //$NON-NLS-1$

        if (lastSeparatorIndex > 0) {
          contextualType = contextualType.substring(0,lastSeparatorIndex);
        }
      }
    }

    return contextualType;
  }

  /**
   * Returns the specification of the feature that is constrained by the associated
   * {@link ConstraintXS constraint}. Returns <code>null</code> if the constraint simply
   * constrains a type.
   */
  protected String getConstrainedFeature() {

    if (!isTypeConstraint()) {
      String[] constrainedElementPathName = tokenizePathName(getConstrainedElement());

      if (constrainedElementPathName.length >= 2) {
        return constrainedElementPathName[constrainedElementPathName.length - 1];
      }
    }

    return null;
  }

  /**
   * Returns the return type of the constrained operation. This will only work if the associated
   * {@link ConstraintXS constraint} indeed constrains an operation, the string denoting the
   * constrained element follows the syntax for operations (see {@link #getOperationPattern()}) and
   * and the return type is given. Otherwise, <code>null</code> is returned.
   */
  protected String getConstrainedOperationReturnType() {

    if (isOperationConstraint() && getConstrainedFeature() != null) {
      Matcher matcher = getOperationPattern().matcher(getConstrainedFeature());

      if (matcher.matches()) {
        // the return type corresponds to the third capture group
        return matcher.group(3);
      }
    }

    return null;
  }

  /**
   * Returns a map of parameters with their corresponding types. If the associated
   * {@link ConstraintXS constraint} does not constrain an operation or the string denoting the
   * constrained element does not follow the syntax for operations (see
   * {@link #getOperationPattern()}), <code>null</code> is returned.
   */
  protected String[][] getConstrainedOperationParameters() {

    if (isOperationConstraint() && getConstrainedFeature() != null) {
      Matcher matcher = getOperationPattern().matcher(getConstrainedFeature());

      if (matcher.matches()) {
        // the parameters are in the second capture group
        String parameters = matcher.group(2);

        // no parameters, only leading and trailing parenthesis
        if (parameters.length() == 2) {
          return new String[][] {};
        }

        // trim leading and trailing parenthesis
        parameters = parameters.substring(1,parameters.length() - 1);

        // tokenize around the commas
        String[] parametersArray = parameters.split("\\s*,\\s*"); //$NON-NLS-1$

        // collect the parameters and their types
        List<String[]> parametersList = new ArrayList<String[]>(parametersArray.length);

        for (int i = 0; i < parametersArray.length; i++) {
          String[] parameter = parametersArray[i].trim().split("\\s*:\\s*"); //$NON-NLS-1$
          parametersList.add(parameter);
        }

        return parametersList.toArray(new String[parametersList.size()][]);
      }
    }

    return null;
  }

  /**
   * Returns whether the associated {@link ConstraintXS constraint} constrains a type.
   */
  protected boolean isTypeConstraint() {
    ConstraintKindXS kind = getConstraint().getKind();
    return kind == ConstraintKindXS.INVARIANT || kind == ConstraintKindXS.DEFINITION;
  }

  /**
   * Returns whether the associated {@link ConstraintXS constraint} constrains a property.
   */
  protected boolean isPropertyConstraint() {
    ConstraintKindXS kind = getConstraint().getKind();
    return kind == ConstraintKindXS.INITIALVALUE || kind == ConstraintKindXS.DERIVEDVALUE;
  }

  /**
   * Returns whether the associated {@link ConstraintXS constraint} constrains a type.
   */
  protected boolean isOperationConstraint() {
    ConstraintKindXS kind = getConstraint().getKind();
    return kind == ConstraintKindXS.BODY || kind == ConstraintKindXS.PRECONDITION
        || kind == ConstraintKindXS.POSTCONDITION;
  }

  /**
   * Returns an array of path name segments.
   */
  protected String[] tokenizePathName(String pathName) {

    if (pathName == null) {
      return new String[] {};
    }

    return pathName.split("::"); //$NON-NLS-1$
  }

  /**
   * Lazily creates a regular expression that will match operation signatures, e.g.
   * 
   * <pre>
   * operation(param1 : type1,param2 : type2) : returntype
   * </pre>
   * 
   * The parameters and the return type may be missing. Parameter names are required. The pattern
   * defines three capture groups:
   * 
   * <ul>
   * <li>the operation name: <code>operation</code>
   * <li>the operation parameters including the parenthesis:
   * <code>(param1 : type1, param2 : type2)</code>
   * <li>the return type: <code>returntype</code>
   * </ul>
   */
  protected Pattern getOperationPattern() {

    if (operationPattern == null) {
      operationPattern = Pattern
          .compile("(\\w+)(\\(\\s*(?:(?:\\w+\\s*:\\s*\\w+,\\s*)*\\w+\\s*:\\s*\\w+)?\\s*\\))\\s*(?::\\s*(\\w+))?"); //$NON-NLS-1$
    }

    return operationPattern;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT:
        if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
        return basicSetConstraint((ConstraintXS) otherEnd,msgs);
    }
    return super.eInverseAdd(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT:
        return basicSetConstraint(null,msgs);
      case XOCLPackage.EXPRESSION_IN_OCL_XS__BODY_EXPRESSION:
        return basicSetBodyExpression(null,msgs);
      case XOCLPackage.EXPRESSION_IN_OCL_XS__CONTEXT:
        return basicSetContext(null,msgs);
      case XOCLPackage.EXPRESSION_IN_OCL_XS__PARAMETER:
        return ((InternalEList<?>) getParameter()).basicRemove(otherEnd,msgs);
      case XOCLPackage.EXPRESSION_IN_OCL_XS__RESULT:
        return basicSetResult(null,msgs);
    }
    return super.eInverseRemove(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
    switch (eContainerFeatureID) {
      case XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT:
        return eInternalContainer().eInverseRemove(this,XOCLPackage.CONSTRAINT_XS__SPECIFICATION,
            ConstraintXS.class,msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case XOCLPackage.EXPRESSION_IN_OCL_XS__BODY:
        return getBody();
      case XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT:
        return getConstraint();
      case XOCLPackage.EXPRESSION_IN_OCL_XS__BODY_EXPRESSION:
        return getBodyExpression();
      case XOCLPackage.EXPRESSION_IN_OCL_XS__CONTEXT:
        return getContext();
      case XOCLPackage.EXPRESSION_IN_OCL_XS__PARAMETER:
        return getParameter();
      case XOCLPackage.EXPRESSION_IN_OCL_XS__RESULT:
        return getResult();
    }
    return super.eGet(featureID,resolve,coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case XOCLPackage.EXPRESSION_IN_OCL_XS__BODY:
        setBody((String) newValue);
        return;
      case XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT:
        setConstraint((ConstraintXS) newValue);
        return;
      case XOCLPackage.EXPRESSION_IN_OCL_XS__BODY_EXPRESSION:
        setBodyExpression((OclExpressionXS) newValue);
        return;
      case XOCLPackage.EXPRESSION_IN_OCL_XS__CONTEXT:
        setContext((VariableXS) newValue);
        return;
      case XOCLPackage.EXPRESSION_IN_OCL_XS__PARAMETER:
        getParameter().clear();
        getParameter().addAll((Collection<? extends VariableXS>) newValue);
        return;
      case XOCLPackage.EXPRESSION_IN_OCL_XS__RESULT:
        setResult((VariableXS) newValue);
        return;
    }
    super.eSet(featureID,newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case XOCLPackage.EXPRESSION_IN_OCL_XS__BODY:
        setBody(BODY_EDEFAULT);
        return;
      case XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT:
        setConstraint((ConstraintXS) null);
        return;
      case XOCLPackage.EXPRESSION_IN_OCL_XS__BODY_EXPRESSION:
        setBodyExpression((OclExpressionXS) null);
        return;
      case XOCLPackage.EXPRESSION_IN_OCL_XS__CONTEXT:
        setContext((VariableXS) null);
        return;
      case XOCLPackage.EXPRESSION_IN_OCL_XS__PARAMETER:
        getParameter().clear();
        return;
      case XOCLPackage.EXPRESSION_IN_OCL_XS__RESULT:
        setResult((VariableXS) null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * The EMF implementation is adapted to always return <code>false</code> for the
   * {@link #getContext() context}, {@link #getResult() result} and
   * {@link #getParameter() parameter} properties to prevent serialization.
   * 
   * This is a bit of a hack to prevent dangling references that may occur during deserialization
   * when both the call to the corresponding getter methods and the parsing of the serialized
   * variables create new {@link VariableXS} instances. Making these properties
   * <code>volatile</code> is not an alternative because then the GUI won't show the corresponding
   * variable as a possible choice for {@link VariableExpXS} expressions.
   * 
   * @generated NOT
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case XOCLPackage.EXPRESSION_IN_OCL_XS__BODY:
        return BODY_EDEFAULT == null ? body != null : !BODY_EDEFAULT.equals(body);
      case XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT:
        return getConstraint() != null;
      case XOCLPackage.EXPRESSION_IN_OCL_XS__BODY_EXPRESSION:
        return bodyExpression != null;
      case XOCLPackage.EXPRESSION_IN_OCL_XS__CONTEXT:
      case XOCLPackage.EXPRESSION_IN_OCL_XS__RESULT:
      case XOCLPackage.EXPRESSION_IN_OCL_XS__PARAMETER:
        return false;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (body: "); //$NON-NLS-1$
    result.append(body);
    result.append(')');
    return result.toString();
  }

} // ExpressionInOclXSImpl
