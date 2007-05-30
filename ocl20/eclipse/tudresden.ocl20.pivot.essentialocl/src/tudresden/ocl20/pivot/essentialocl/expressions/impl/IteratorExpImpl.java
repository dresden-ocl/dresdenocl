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
package tudresden.ocl20.pivot.essentialocl.expressions.impl;

import org.eclipse.emf.ecore.EClass;

import tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Iterator Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class IteratorExpImpl extends LoopExpImpl implements IteratorExp {

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected IteratorExpImpl() {
    super();
  }

  /**
   * Overridden to determine the type of the <code>IteratorExp</code> according to the OCL
   * specification (Section 8.3):
   * 
   * [1] If the iterator is ‘forAll,’ ‘isUnique,’ or ‘exists’ the type of the iterator must be
   * Boolean.
   * 
   * <pre>
   *   context IteratorExp
   *   inv: name = ‘exists’ or name = ‘forAll’ or name = ‘isUnique’
   *      implies type.oclIsKindOf(PrimitiveType) and type.name = ‘Boolean’
   * </pre>
   * 
   * [2] The result type of the collect operation on a sequence type is a sequence, the result type
   * of ‘collect’ on any other collection type is a Bag. The type of the body is always the type of
   * the elements in the return collection.
   * 
   * <pre>
   *   context IteratorExp
   *   inv: name = ‘collect’ implies
   *      if source.type.oclIsKindOf(SequenceType) then
   *         type = expression.type.collectionType-&gt;select(oclIsTypeOf(SequenceType))-&gt;first()
   *      else
   *         type = expression.type.collectionType-&gt;select(oclIsTypeOf(BagType))-&gt;first()
   *      endif
   * </pre>
   * 
   * [3] The ‘select’ and ‘reject’ iterators have the same type as its source.
   * 
   * <pre>
   *   context IteratorExp
   *   inv: name = ‘select’ or name = ‘reject’ implies type = source.type
   * </pre>
   * 
   * [4] The type of the body of the select, reject, exists, and forAll must be boolean.
   * 
   * <pre>
   *   context IteratorExp
   *   inv: name = ‘exists’ or name = ‘forAll’ or name = ‘select’ or name = ‘reject’
   *      implies body.type.name = ‘Boolean’
   * </pre>
   * 
   * </p>
   * 
   * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.OclExpressionImpl#evaluateType()
   */
  @Override
  protected Type evaluateType() {
    // TODO: implement type evaluation
    throw new UnsupportedOperationException(
        "The type evaluation for 'IteratorExp' has still to be implemented."); //$NON-NLS-1$
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ExpressionsPackageImpl.Literals.ITERATOR_EXP;
  }

} // IteratorExpImpl
