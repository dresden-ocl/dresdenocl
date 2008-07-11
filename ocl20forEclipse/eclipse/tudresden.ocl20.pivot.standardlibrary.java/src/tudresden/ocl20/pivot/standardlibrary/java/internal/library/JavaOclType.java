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
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaOclType extends JavaOclRoot implements OclType {

	// The predefined types
	protected static Map<String, OclType> predefinedTypes = new HashMap<String, OclType>();

	// The OCLANYTYPE
	private static OclType OCLANYTYPE = new JavaOclType(Class.class, "OclAny");

	// The OCLINVALIDTYPE
	private static OclType OCLINVALIDTYPE = new JavaOclType(Class.class,
			"OclInvalid");

	// The OCLVOIDTYPE
	private static OclType OCLVOIDTYPE = new JavaOclType(Class.class, "OclVoid");

	// The OCLTYPETYPE
	private static OclType OCLTYPETYPE = new JavaOclType(Class.class, "OclType");

	// The name
	protected String name;

	/**
	 * Instantiates a new java ocl type.
	 * 
	 * @param adaptable
	 *            the adaptable
	 * @param name
	 *            the name
	 */
	protected JavaOclType(Class adaptable, String name) {
		super(adaptable);
		this.name = name;
		predefinedTypes.put(name, this);
	}

	/**
	 * Gets the type.
	 * 
	 * @param name
	 *            the name
	 * 
	 * @return the type
	 */
	public static OclType getType(String name) {
		OclType type = predefinedTypes.get(name);
		return type;
	}

	/**
	 * Instantiates a new java ocl type.
	 * 
	 * @param adaptable
	 *            the adaptable
	 */
	public JavaOclType(Class adaptable) {
		super(adaptable);
		this.name = adaptable.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType#isOfKind(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isOfKind(OclRoot o) {
		if (this == OCLANYTYPE)
			return JavaOclBoolean.getInstance(false); // oclAny has no direct
														// instances
		if (this == OCLVOIDTYPE && o.isOclUndefined().isTrue())
			return JavaOclBoolean.getInstance(true);
		return JavaOclBoolean.getInstance(((Class) getAdaptee()).isInstance(o
				.getAdaptee()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType#isOfType(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isOfType(OclRoot o) {
		if (this == OCLANYTYPE)
			return JavaOclBoolean.getInstance(false); // oclAny has no direct
														// instances
		if (this == OCLVOIDTYPE && o.isOclUndefined().isTrue())
			return JavaOclBoolean.getInstance(true);
		return JavaOclBoolean.getInstance(this.isEqualTo(o.getType()).isTrue());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot#getType()
	 */
	@Override
	public OclType getType() {
		return OCLTYPETYPE;
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
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType#createInstance(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclRoot createInstance(OclRoot o) {
		if (o.getAdapteeClass().isAssignableFrom((Class) getAdaptee())) {
			if (this == OCLVOIDTYPE)
				return JavaOclVoid.getInstance();
			if (this == OCLTYPETYPE)
				return new JavaOclType(o.getAdaptee().getClass());
			if (this == OCLANYTYPE)
				return new JavaOclAny(o.getAdaptee());
			OclRoot ret = new JavaOclObject(o.getAdaptee());
			ret.setAdapteeClass((Class) getAdaptee());
			return ret;
		}
		return JavaOclVoid.getInstance();
	}
}
