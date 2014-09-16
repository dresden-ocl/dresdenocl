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
package org.dresdenocl.essentialocl.expressions.impl;

import org.eclipse.emf.ecore.EClass;

import org.dresdenocl.essentialocl.expressions.IteratorExp;
import org.dresdenocl.essentialocl.expressions.WellformednessException;
import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.essentialocl.types.OrderedSetType;
import org.dresdenocl.essentialocl.types.SequenceType;
import org.dresdenocl.essentialocl.types.SetType;
import org.dresdenocl.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Iterator Exp</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class IteratorExpImpl extends LoopExpImpl implements IteratorExp {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected IteratorExpImpl() {
		super();
	}

	/**
	 * Overridden to determine the type of the <code>IteratorExp</code>
	 * according to the OCL specification (Section 8.3). Note that the
	 * specification is incomplete and this implementation adds a few more
	 * rules.
	 * 
	 * <p>
	 * [1] If the iterator is ‘forAll,’ ‘isUnique,’ or ‘exists’ the type of the
	 * iterator must be Boolean.
	 * 
	 * <pre>
	 *   context IteratorExp
	 *   inv: name = ‘exists’ or name = ‘forAll’ or name = ‘isUnique’
	 *      implies type.oclIsKindOf(PrimitiveType) and type.name = ‘Boolean’
	 * </pre>
	 * 
	 * [2] The result type of the collect operation on a sequence type is a
	 * sequence, the result type of ‘collect’ on any other collection type is a
	 * Bag. The type of the body is always the type of the elements in the
	 * return collection.
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
	 * </p>
	 * 
	 * @see org.dresdenocl.essentialocl.expressions.impl.OclExpressionImpl#evaluateType()
	 */
	@Override
	protected Type evaluateType() {

		Type type, sourceType, elementType, bodyType;

		// check for wellformedness of loop expression
		validateWellformednessRules();

		// determine the types of the source collection, its elements and the
		// body
		// expression
		sourceType = source.getType();
		elementType = ((CollectionType) sourceType).getElementType();
		bodyType = body.getType();

		// implement rule [1], but additionally check for iterator expression
		// "one"
		if (name.equals("exists") || name.equals("forAll") || name.equals("isUnique") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				|| name.equals("one")) { //$NON-NLS-1$
			type = getValidOclLibrary().getOclBoolean();
		}

		// additional rule missing in the spec
		else if (name.equals("any")) { //$NON-NLS-1$
			type = elementType;
		}

		// implement rule [3]
		else if (name.equals("select") || name.equals("reject")) { //$NON-NLS-1$ //$NON-NLS-2$
			type = sourceType;
		}

		// additional rule missing in the spec
		else if (name.equals("sortedBy")) { //$NON-NLS-1$

			if (sourceType instanceof SetType
					|| sourceType instanceof OrderedSetType) {
				type = getValidOclLibrary().getOrderedSetType(elementType);
			}

			else {
				type = getValidOclLibrary().getSequenceType(elementType);
			}
		}

		else if (name.equals("closure")) {

			if (sourceType instanceof SequenceType
					|| sourceType instanceof OrderedSetType) {
				type = getValidOclLibrary().getOrderedSetType(elementType);
			}

			else {
				type = getValidOclLibrary().getSetType(elementType);
			}
		}

		// additional rule missing in the spec
		else if (name.equals("collectNested")) { //$NON-NLS-1$

			if (sourceType instanceof SequenceType
					|| sourceType instanceof OrderedSetType) {
				type = getValidOclLibrary().getSequenceType(bodyType);
			}

			else {
				type = getValidOclLibrary().getBagType(bodyType);
			}
		}

		// implement rule [2]
		else if (name.equals("collect")) { //$NON-NLS-1$
			Type resultElementType;

			// flatten the type of the body expression
			if (bodyType instanceof CollectionType) {
				resultElementType = ((CollectionType) bodyType)
						.getElementType();
			}

			else {
				resultElementType = bodyType;
			}

			// we enhance rule [2] with a treatment of ordered sets
			if (sourceType instanceof SequenceType
					|| sourceType instanceof OrderedSetType) {
				type = getValidOclLibrary().getSequenceType(resultElementType);
			}

			else {
				type = getValidOclLibrary().getBagType(resultElementType);
			}
		}

		else {
			throw new WellformednessException(this,
					"Unknown iterator expression: '" + name + "'."); //$NON-NLS-1$//$NON-NLS-2$
		}

		return type;
	}

	/**
	 * Overridden to additionally check the following wellformedness rule
	 * 
	 * <p>
	 * [4] The type of the body of the select, reject, exists, and forAll must
	 * be boolean.
	 * 
	 * <pre>
	 *   context IteratorExp
	 *   inv: name = ‘exists’ or name = ‘forAll’ or name = ‘select’ or name = ‘reject’
	 *      implies body.type.name = ‘Boolean’
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @see org.dresdenocl.essentialocl.expressions.impl.LoopExpImpl#validateWellformednessRules()
	 */
	@Override
	protected void validateWellformednessRules() {

		super.validateWellformednessRules();

		// validate [4], but also check for iterator expressions "any" and "one"
		if (name.equals("exists") || name.equals("forAll") || name.equals("select") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				|| name.equals("reject") || name.equals("any") || name.equals("one")) { //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

			if (body.getType() != oclLibrary.getOclBoolean()) {
				throw new WellformednessException(
						this,
						"The body expression of an '" //$NON-NLS-1$
								+ name
								+ "' iterator expression must have the type Boolean."); //$NON-NLS-1$
			}
			// no else.
		}

		/* Check result type of closure iterator. */
		if (name.equals("closure")) {

			String msg = "The body expression of a closure iterator expression must conform to the source expression's element type.";
			Type bodyType = body.getType();
			Type sourceType = source.getType();
			Type sourceElementType = ((CollectionType) sourceType)
					.getElementType();

			if (!bodyType.conformsTo(sourceElementType)
					&& !(bodyType instanceof CollectionType)) {
				throw new WellformednessException(this, msg);
			}

			else if (bodyType instanceof CollectionType) {
				Type bodyElementType = ((CollectionType) bodyType)
						.getElementType();

				if (!bodyElementType.conformsTo(sourceElementType)) {
					throw new WellformednessException(this, msg);
				}
				// no else.
			}
			// no else.
		}
		// no else.

		/* Check count of variables. */
		if (name.equals("any") || name.equals("collect")
				|| name.equals("collectNested") || name.equals("closure")
				|| name.equals("isUnique") || name.equals("one")
				|| name.equals("reject") || name.equals("select")
				|| name.equals("sortedBy")) {

			if (this.getIterator().size() > 1) {
				throw new WellformednessException(this, "The iterator " + name
						+ " may have at most one iterator variable.");
			}
			// no else.
		}
		// no else.
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackageImpl.Literals.ITERATOR_EXP;
	}

} // IteratorExpImpl
