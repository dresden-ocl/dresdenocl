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

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;

/**
 * <p>
 * Provides an implementation of {@link OclType} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaOclType extends JavaOclRoot implements OclType {

	/** The predefined types. */
	protected static Map<String, OclType> predefinedTypes = new HashMap<String, OclType>();

	/** The instance of OclAny. */
	private static OclType OCLANYTYPE = new JavaOclType(Class.class, "OclAny");

	/** The instance of OclVoid. */
	private static OclType OCLVOIDTYPE = new JavaOclType(Class.class, "OclVoid");

	/** The instance of OclType. */
	private static OclType OCLTYPETYPE = new JavaOclType(Class.class, "OclType");

	/** The name of the implemented type. */
	protected String name;

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclType}.
	 * </p>
	 * 
	 * @param adaptable
	 *            The adaptable {@link Class} of this {@link JavaOclType}.
	 */
	public JavaOclType(Class<?> adaptable) {
		super(adaptable);

		this.name = adaptable.getName();
	}

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclType}.
	 * </p>
	 * 
	 * @param adaptable
	 *            The {@link Class} which shall be adapted.
	 * @param name
	 *            The name of the Type which shall be adapted.
	 */
	protected JavaOclType(Class<?> adaptable, String name) {
		super(adaptable);
		this.name = name;
		predefinedTypes.put(name, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType#createInstance
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclRoot createInstance(OclRoot anOclRoot) {

		OclRoot result;

		Class<?> typeOfThisObject;
		Class<?> typeOfGivenObject;

		typeOfThisObject = (Class<?>) this.getAdaptee();
		typeOfGivenObject = anOclRoot.getAdapteeClass();

		if (typeOfThisObject.isAssignableFrom(typeOfGivenObject)) {

			if (this == OCLVOIDTYPE) {
				result = JavaOclVoid.getInstance();
			}

			else if (this == OCLTYPETYPE) {
				result = new JavaOclType(typeOfGivenObject);
			}

			else if (this == OCLANYTYPE) {
				return new JavaOclAny(typeOfGivenObject);
			}

			else {
				result = new JavaOclObject(anOclRoot.getAdaptee());
				result.setAdapteeClass(typeOfThisObject);
			}
		}

		else {
			result = JavaOclVoid.getInstance();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType#isOfKind(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isOfKind(OclRoot anOclRoot) {

		OclBoolean result;

		if (this == OCLANYTYPE) {
			/* OclAny has no direct instances. */
			result = JavaOclBoolean.getInstance(false);
		}

		else if (this == OCLVOIDTYPE && anOclRoot.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(true);
		}

		else {
			Class<?> thisAdaptee;

			thisAdaptee = (Class<?>) this.getAdaptee();

			return JavaOclBoolean.getInstance((thisAdaptee)
					.isInstance(anOclRoot.getAdaptee()));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType#isOfType(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isOfType(OclRoot anOclRoot) {

		OclBoolean result;

		if (this == OCLANYTYPE) {
			/* OclAny has no direct instances. */
			result = JavaOclBoolean.getInstance(false);
		}

		else if (this == OCLVOIDTYPE && anOclRoot.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(true);
		}

		else {
			OclType aType;
			
			aType = anOclRoot.getType();
			
			result = this.isEqualTo(aType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #getType()
	 */
	@Override
	public OclType getType() {
		return OCLTYPETYPE;
	}

	/**
	 * <p>
	 * Returns an {@link OclType} to a given name.
	 * </p>
	 * 
	 * @param name
	 *            The name of the {@link OclType} which shall be returned.
	 * 
	 * @return An {@link OclType} to a given name.
	 */
	public static OclType getType(String name) {
		OclType result;

		result = predefinedTypes.get(name);

		return result;
	}
}