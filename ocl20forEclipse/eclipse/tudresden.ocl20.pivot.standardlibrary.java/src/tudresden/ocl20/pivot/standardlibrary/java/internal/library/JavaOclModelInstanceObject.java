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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclModelInstanceObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;
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
		OclModelInstanceObject {

	private IModelInstanceObject imiObject;

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclObject}.
	 * </p>
	 * 
	 * @param adaptee
	 *          The adapted model instance object.
	 */
	public JavaOclModelInstanceObject(IModelInstanceObject imiObject) {

		super(imiObject);
		this.imiObject = imiObject;
	}

	public JavaOclModelInstanceObject(String undefinedReason) {

		super(undefinedReason);
	}

	public JavaOclModelInstanceObject(Throwable invalidReason) {

		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny#
	 * invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny[])
	 */
	public OclAny invokeOperation(Operation operation, OclAny... args) {

		OclAny result;

		result = checkUndefinedAndInvalid(operation, args);

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

				imiResult = imiObject.invokeOperation(operation, imiArguments);
				result = JavaStandardLibraryFactory.INSTANCE.createOclAny(imiResult);
			}

			/*
			 * An invalid exception can occur if a OclType object shall be adapted to
			 * its IModelInstanceElement. OclTypes can only be arguments of operations
			 * defined in the standard library. Thus, try to invoke a library
			 * operation.
			 */
			catch (InvalidException e) {

				result = super.invokeOperation(operation, args);
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

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#asSet()
	 */
	@SuppressWarnings("unchecked")
	public OclSet<OclModelInstanceObject> asSet() {

		OclSet<OclModelInstanceObject> result;

		checkUndefinedAndInvalid(this);

		Set<IModelInstanceElement> resultSet = new HashSet<IModelInstanceElement>();
		resultSet.add(this.imiObject);

		result = JavaStandardLibraryFactory.INSTANCE.createOclSet(resultSet);

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

		/* Else check if the source is undefined. */
		else if (this.oclIsUndefined().isTrue()) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclUndefined(property
							.getType(), this.getUndefinedreason());
		}

		/* Else try to get the property. */
		else {

			IModelInstanceElement imiResult;

			try {
				imiResult = imiObject.getProperty(property);

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

		checkUndefinedAndInvalid(this, that);

		if (!(that instanceof OclModelInstanceObject)) {
			result = JavaOclBoolean.getInstance(false);
		}

		else {
			Object thatObject =
					((IModelInstanceObject) that.getModelInstanceElement()).getObject();

			if (imiObject.getObject().equals(thatObject)) {
				result = JavaOclBoolean.getInstance(true);
			}
			else {
				result = JavaOclBoolean.getInstance(false);
			}
		}

		return result;
	}
}