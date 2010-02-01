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

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;

/**
 * <p>
 * This class implements the OCL type {@link OclEnumLiteral} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
public class JavaOclEnumLiteral extends JavaOclLibraryObject implements
		OclEnumLiteral {

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclEnumLiteral}.
	 * </p>
	 * 
	 * @param adaptee
	 *          The adapted element of this {@link JavaOclEnumLiteral}.
	 */
	public JavaOclEnumLiteral(
			IModelInstanceEnumerationLiteral imiEnumerationLiteral) {

		super(imiEnumerationLiteral);
	}

	public JavaOclEnumLiteral(String undefinedReason) {

		super(undefinedReason);
	}

	public JavaOclEnumLiteral(Throwable invalidReason) {

		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral#
	 * getModelInstanceEnumerationLiteral()
	 */
	public IModelInstanceEnumerationLiteral getModelInstanceEnumerationLiteral() {

		return (IModelInstanceEnumerationLiteral) this.imiElement;
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
			if (that instanceof JavaOclEnumLiteral) {
				JavaOclEnumLiteral enumLiteral = (JavaOclEnumLiteral) that;
				boolean boolResult =
						getModelInstanceEnumerationLiteral().equals(
								enumLiteral.getModelInstanceEnumerationLiteral());
				result = JavaOclBoolean.getInstance(boolResult);
			}
			else {
				result = JavaOclBoolean.getInstance(false);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#asSet()
	 */
	public <T extends OclAny> OclSet<T> asSet() {

		// checkInvalid(this);
		//
		// OclSet<T> result;
		//
		// Set<IModelInstanceElement> imiSet = new HashSet<IModelInstanceElement>();
		// imiSet.add(getModelInstanceEnumerationLiteral());
		//
		// result = JavaStandardLibraryFactory.INSTANCE.createOclSet(imiSet);
		//
		// return result;
		// FIXME Michael: What generic type should the OclSet have?
		throw new UnsupportedOperationException(
				"asSet() on EnumerationLiteral is not supported yet");
	}

	@Override
	public String toString() {

		StringBuilder result = new StringBuilder();

		result.append(this.getClass().getSimpleName());
		result.append("[");

		if (this.oclIsUndefined().isTrue()) {
			result.append("undefined: " + this.undefinedreason);
		}

		else if (this.oclIsInvalid().isTrue()) {
			result.append("invalid: " + this.invalidReason.getMessage());
		}

		else {
			result.append(getModelInstanceEnumerationLiteral().getName());
		}

		result.append("]");

		return result.toString();
	}

}
