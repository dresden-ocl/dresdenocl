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

import java.util.HashMap;
import java.util.Map;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclLibraryObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.UndefinedException;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.UndefinedOrInvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * Supertype of any Ocl Object/PrimitiveType/Collection in the Java standard
 * library.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
public abstract class JavaOclAny implements OclAny {

	/** The reason why this element is undefined - if it is. */
	protected String undefinedreason = null;

	/** The reason why this element is invalid - if it is. */
	protected Throwable invalidReason = null;

	private IModelInstanceElement imiElement;

	/**
	 * <p>
	 * Constructor for null elements. These elements can provide a reason for
	 * being undefined.
	 * </p>
	 * <p>
	 * <strong>Note:</strong> Since <code>OclUndefined</code> is typed, i.e.
	 * according to the OCL standard it conforms to all other types except
	 * <code>OclInvalid</code>, <strong>every subclass of {@link OclAny} needs to
	 * implement this constructor</strong>.
	 * </p>
	 * 
	 * @param undefinedReason
	 *          the reason why this element is undefined
	 */
	public JavaOclAny(String undefinedReason) {

		if (undefinedReason == null)
			throw new IllegalArgumentException("Undefined reason cannot be null.");

		this.undefinedreason = undefinedReason;
	}

	/**
	 * <p>
	 * Constructor for invalid elements. These elements have to provide a reason
	 * for being invalid.
	 * </p>
	 * <p>
	 * <strong>Note:</strong> Since <code>OclInvalid</code> is typed, i.e.
	 * according to the OCL standard it conforms to all other types except
	 * <code>OclUndefined</code>, <strong>every subclass of {@link OclAny} needs
	 * to implement this constructor</strong>.
	 * </p>
	 * 
	 * @param invalidReason
	 *          the reason why this element is invalid, given by a
	 *          {@link Throwable}
	 */
	public JavaOclAny(Throwable invalidReason) {

		if (invalidReason == null)
			throw new IllegalArgumentException("Invalid reason cannot be null.");

		this.invalidReason = invalidReason;
	}

	/**
	 * Constructor for normal {@link IModelInstanceElement}s that are neither
	 * <code>undefined</code> nor <code>invalid</code>.
	 * 
	 * @param imiElement
	 *          the {@link IModelInstanceElement} to adapt.
	 */
	public JavaOclAny(IModelInstanceElement imiElement) {

		if (imiElement == null)
			throw new IllegalArgumentException(
					"IModelInstanceElement cannot be null.");

		this.imiElement = imiElement;
	}

	// FIXME Michael: Bad smell! Each class should have its own static map -> what
	// to do with getOperationNames; operations of supertypes have to be
	// considered
	/**
	 * Contains the operation names which are different in the standard library
	 * than in the OCL specification. The names are separated in sub maps
	 * depending on their number of arguments.
	 */
	protected static Map<Integer, Map<String, String>> operationNames =
			new HashMap<Integer, Map<String, String>>();

	/* Initializes the operation names map. */
	static {
		Map<String, String> binaryOperations;

		binaryOperations = new HashMap<String, String>();
		binaryOperations.put("=", "isEqualTo");
		binaryOperations.put("<>", "isNotEqualTo");
		operationNames.put(2, binaryOperations);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getOperationName(java.lang
	 * .String, int)
	 */
	protected String getOperationName(String name, int operatorCount) {

		String result;
		Map<String, String> operationMap;

		result = null;
		operationMap = operationNames.get(operatorCount);

		if (operationMap != null) {

			result = operationMap.get(name);
		}
		// no else.

		if (result == null) {
			result = name;
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#getUndefinedreason
	 * ()
	 */
	public String getUndefinedreason() {

		return undefinedreason;
	}

	public Throwable getInvalidReason() {

		return invalidReason;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#isNotEqualTo
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isNotEqualTo(OclAny that) {

		checkUndefinedAndInvalid(this, that);

		return isEqualTo(that).not();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#isOclUndefined
	 * ()
	 */
	public OclBoolean oclIsUndefined() {

		return JavaOclBoolean.getInstance(this.undefinedreason != null);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#oclIsInvalid()
	 */
	public OclBoolean oclIsInvalid() {

		return JavaOclBoolean.getInstance(false);
	}

	// TODO Michael: catch exception in invokeOperation
	@SuppressWarnings("unchecked")
	public <T extends OclAny> T oclAsType(OclType<T> type)
			throws AsTypeCastException {

		checkUndefinedAndInvalid(this, type);

		final IModelInstanceElement castedTo = imiElement.asType(type.getType());
		return (T) JavaStandardLibraryFactory.INSTANCE.createOclAny(castedTo);
	}

	public <T extends OclAny> OclBoolean oclIsKindOf(OclType<T> typespec) {

		checkUndefinedAndInvalid(this, typespec);

		final boolean contains = imiElement.isKindOf(typespec.getType());
		return JavaStandardLibraryFactory.INSTANCE.createOclBoolean(contains);
	}

	public <T extends OclAny> OclBoolean oclIsTypeOf(OclType<T> typespec) {

		// TODO Michael: Implement, when Claas' implementation of isTypeOf is ready.
		return null;
	}

	public IModelInstanceElement getModelInstanceElement() {

		return imiElement;
	}

	/**
	 * This methods checks for all given {@link OclAny}s if they are either
	 * invalid or undefined. If one of them is, an {@link InvalidException} or
	 * {@link UndefinedException} is thrown. The direct caller (a method of the
	 * standard library) should not catch these exceptions. The
	 * {@link OclLibraryObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, OclAny...)
	 * invokeOperation} method of {@link OclLibraryObject} will do that and will
	 * generate an appropriate return type (since OclUndefined and OclVoid conform
	 * to every other type except each other).
	 * 
	 * @param objects
	 *          the {@link OclAny}s to check whether they are invalid or undefined
	 * @throws UndefinedOrInvalidException
	 *           a concrete sub-class ({@link InvalidException} or
	 *           {@link UndefinedException}) to indicate that one element was
	 *           invalid or undefined
	 */
	protected void checkUndefinedAndInvalid(OclAny... objects)
			throws UndefinedOrInvalidException {

		for (OclAny object : objects) {

			if (object.oclIsInvalid().isTrue())
				throw new InvalidException(object.getInvalidReason());

			if (object.oclIsUndefined().isTrue())
				throw new UndefinedException(object.getUndefinedreason());

		}
	}

}