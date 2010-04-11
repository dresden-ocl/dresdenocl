/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
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
 */
package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclModelInstanceObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.types.TypeConstants;
import tudresden.ocl20.pivot.modelinstancetype.exception.OperationAccessException;
import tudresden.ocl20.pivot.modelinstancetype.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelinstancetype.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelinstancetype.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * This class implements the OCL type {@link OclModelInstanceObject} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
public class JavaOclModelInstanceObject extends JavaOclAny implements
		OclModelInstanceObject, IAddableElement {

	protected Type metaType;

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclObject}.
	 * </p>
	 * 
	 * @param adaptee
	 *          The adapted model instance object.
	 */
	public JavaOclModelInstanceObject(IModelInstanceObject imiObject,
			Type metaType) {

		super(imiObject);
		this.metaType = metaType;
	}

	public JavaOclModelInstanceObject(String undefinedReason, Type metaType) {

		super(undefinedReason);
		this.metaType = metaType;
	}

	public JavaOclModelInstanceObject(Throwable invalidReason, Type metaType) {

		super(invalidReason);
		this.metaType = metaType;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclModelInstanceObject
	 * #getModelInstanceObject()
	 */
	public IModelInstanceObject getModelInstanceObject() {

		return (IModelInstanceObject) this.imiElement;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny#
	 * invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny[])
	 */
	public OclAny invokeOperation(Operation operation, OclAny... args) {

		OclAny result = null;
		final String operationName = operation.getName();

		/*
		 * Handle oclIsTypeOf(), oclIsKindOf() and oclAsType() separately as the
		 * argument has no IMIElement.
		 */
		if ((operationName.equals("oclIsTypeOf")
				|| operationName.equals("oclIsKindOf") || operationName
				.equals("oclAsType"))
				&& args.length == 1) {
			result = super.invokeOperation(operation, args);
		}

		/*
		 * Handle oclIsInvalid() and oclIsUndefined separately as they cannot be
		 * invoked on IMIElements and are definitely defined in the Standard
		 * Library.
		 */
		if (result == null
				&& (operationName.equals("oclIsUndefined") || operationName
						.equals("oclIsInvalid")) && args.length == 0)
			result = super.invokeOperation(operation, args);

		if (result == null
				&& (operationName.equals("asSet") && args.length == 0))
			result = this.asSet();

		if (result == null)
			result = checkInvalid(operation, args);
		
		if (result == null) {
			/* Else try to invoke the operation. */
			IModelInstanceElement imiResult;

			List<IModelInstanceElement> imiArguments;
			imiArguments = new LinkedList<IModelInstanceElement>();

			/* Try to invoke the operation. */
			try {

				/* Get the model instance arguments. */
				for (OclAny arg : args) {
					imiArguments.add(arg.getModelInstanceElement());
				}

				imiResult =
						getModelInstanceObject().invokeOperation(operation, imiArguments);
				result = JavaStandardLibraryFactory.INSTANCE.createOclAny(imiResult);
			}

			/*
			 * If the operation is not defined on the model element, it may be an
			 * operation on OclAny.
			 */
			catch (OperationNotFoundException e) {

				result = super.invokeOperation(operation, args);
			}

			catch (OperationAccessException e) {

				result = super.invokeOperation(operation, args);
			}
			// end catch.
		}
		// no else.

		return result;
	}

	/**
	 * Used to determine invalid return values for {@link Operation}s.
	 * <code>this</code> is checked to be <code>undefined</code> or
	 * <code>invalid</code> and the arguments are checked for <code>invalid</code>
	 * .
	 * 
	 * @param operation
	 *          the operation to call
	 * @param args
	 *          the arguments of the operation
	 * @return <code>null</code> if neither the source nor the args are undefined
	 *         or invalid, the undefined or invalid source else
	 */
	protected OclAny checkInvalid(Operation operation, OclAny... args) {

		OclAny result = null;

		/*
		 * see standard, p. 138: all operations on OclInvalid are illegal, except
		 * oclIsInvalid(); oclIsUndefined() is not considered to catch invalid ->
		 * this is not conform to the standard!
		 */
		if (this.oclIsInvalid().isTrue()
				&& !(operation.getName().equals("oclIsInvalid") && args.length == 0)
				&& !(operation.getName().equals("isEqualTo") && args.length == 1)) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
							.getType(), this.getInvalidReason());
		}

		/*
		 * see standard, p. 138: all operations on OclVoid are illegal, except
		 * oclIsInvalid() and oclIsUndefined()
		 */
		else if (this.oclIsUndefined().isTrue()
				&& !(operation.getName().equals("oclIsInvalid") && args.length == 0)
				&& !(operation.getName().equals("oclIsUndefined") && args.length == 0)
				&& !(operation.getName().equals("isEqualTo") && args.length == 1)) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
							.getType(), new RuntimeException("Tried to invoke operation "
							+ operation.getName() + " on null. Reason: "
							+ this.getUndefinedReason()));
		}

		if (result == null) {
			/* The same for all the arguments */
			for (OclAny arg : args) {

				if (arg.oclIsInvalid().isTrue()) {
					result =
							JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
									.getType(), arg.getInvalidReason());
					break;
				}
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#asSet()
	 */
	@SuppressWarnings("unchecked")
	public OclSet<OclModelInstanceObject> asSet() {

		OclSet<OclModelInstanceObject> result;

		result = checkInvalid(TypeConstants.SET(metaType), this);

		if (result == null)
			result = checkAsSet(metaType);

		if (result == null) {
			Set<IModelInstanceElement> resultSet =
					new HashSet<IModelInstanceElement>();
			resultSet.add(this.getModelInstanceObject());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSet(resultSet,
							TypeConstants.SET(metaType));
		}

		return result;
	}

	/**
	 * <p>
	 * Returns the {@link OclAny} of a given {@link Property} that is defined on
	 * this {@link IModelInstanceObject}.
	 * </p>
	 * 
	 * @param property
	 *          The {@link Property} whose value shall be returned.
	 * @return The result as an {@link OclAny}.
	 */
	public OclAny getProperty(Property property) {

		OclAny result;

		/* Check if the source is invalid. */
		if (this.oclIsInvalid().isTrue()) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(property
							.getType(), this.getInvalidReason());
		}

		else if (this.oclIsUndefined().isTrue()) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(property
							.getType(), new NullPointerException(this.getUndefinedReason()));
		}

		/* Else try to get the property. */
		else {

			IModelInstanceElement imiResult;

			try {
				imiResult = getModelInstanceObject().getProperty(property);

				if (imiResult.isUndefined()) {
					result =
							JavaStandardLibraryFactory.INSTANCE.createOclUndefined(property
									.getType(), imiResult.getName() + " is null.");
				}

				else {
					result = JavaStandardLibraryFactory.INSTANCE.createOclAny(imiResult);
				}
			}

			/* Probably create an undefined or invalid result. */
			catch (PropertyNotFoundException e) {

				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(property
								.getType(), e);
			}

			catch (PropertyAccessException e) {

				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(property
								.getType(), e);
			}
			// end catch.
		}
		// end else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#isEqualTo(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclAny)
	 */
	public OclBoolean isEqualTo(OclAny that) {

		OclBoolean result;

		result = checkIsEqualTo(that);

		if (result == null) {
			if (!(that instanceof OclModelInstanceObject)) {
				result = JavaOclBoolean.getInstance(false);
			}

			else {
				Object thatObject =
						((IModelInstanceObject) that.getModelInstanceElement()).getObject();

				if (getModelInstanceObject().getObject().equals(thatObject)) {
					result = JavaOclBoolean.getInstance(true);
				}
				else {
					result = JavaOclBoolean.getInstance(false);
				}
			}
		}

		return result;
	}

	@Override
	public String toString() {

		StringBuilder result = new StringBuilder();

		result.append(this.getClass().getSimpleName());
		result.append("[");

		if (!toStringUndefinedOrInvalid(result)) {
			result.append(getModelInstanceObject().getName());
			result.append(" - ");
			result.append(getModelInstanceObject().getObject());
		}

		result.append("]");

		return result.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.IAddableElement
	 * #add(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny)
	 */
	public OclAny add(OclAny that) {

		/*
		 * convention: any ModelInstanceElement that has an operation called "+",
		 * "add" or "plus" is considered to be addable. Else return invalid.
		 */
		OclAny result;

		result = checkInvalid(metaType, this, that);

		if (result == null)
			result = checkUndefined("+", metaType, this, that);

		if (result == null) {
			if (that.getModelInstanceElement().isKindOf(metaType)) {
				result = findMethod("+", that);
				if (result == null)
					result = findMethod("add", that);
				if (result == null)
					result = findMethod("plus", that);
				if (result == null)
					// not found any matching method -> invalid
					result =
							JavaStandardLibraryFactory.INSTANCE.createOclInvalid(metaType,
									new NoSuchMethodException(
											"Cannot find operation +, add or plus on " + this));
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private OclAny findMethod(String methodName, OclAny that) {

		OclAny result = null;
		try {
			findMethod(methodName, this.getClass(), that.getClass());

			/*
			 * if no exception has been thrown, search for the operation in the
			 * PivotModel
			 */
			Operation operation = null;
			List<Type> argTypes;
			argTypes = new ArrayList<Type>();
			argTypes.add(that.getModelInstanceElement().getType());
			operation =
					this.imiElement.getType().lookupOperation(methodName, argTypes);

			if (operation != null)
				result = super.invokeOperation(operation, that);
			else
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(metaType,
								new NoSuchMethodException("Cannot find " + methodName + " on "
										+ this));
		} catch (NoSuchMethodException e) {
			// ignore
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.IAddableElement
	 * #getNeutralElement()
	 */
	public OclAny getNeutralElement() {

		// FIXME Michael: how to get an instance of an unknown neutral element?
		return null;
	}
}