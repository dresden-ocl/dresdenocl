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

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.standardlibrary.java.internal.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * This class implements the OCL type {@link OclInteger} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
public class JavaOclInteger extends JavaOclReal implements OclInteger {

	private IModelInstanceInteger imiInteger;

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclInteger}.
	 * </p>
	 * 
	 * @param imiInteger
	 *          The literal of this {@link JavaOclInteger}.
	 */
	public JavaOclInteger(IModelInstanceInteger imiInteger) {

		super(BasisJavaModelInstanceFactory.createModelInstanceReal(imiInteger
				.getLong()));
		this.imiInteger = imiInteger;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#add(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger add(OclInteger that) {

		OclInteger result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Long summand1 = this.imiInteger.getLong();
		Long summand2 =
				((IModelInstanceInteger) that.getModelInstanceElement()).getLong();
		result =
				JavaStandardLibraryFactory.INSTANCE.createOclInteger(summand1
						+ summand2);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#div(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger div(OclInteger that) {

		OclInteger result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Long dividend = this.imiInteger.getLong();
		Long divisor =
				((IModelInstanceInteger) that.getModelInstanceElement()).getLong();

		if (divisor == 0) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason("Division by zero.");
		}
		else {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInteger(dividend
							/ divisor);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#divide(
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclReal divide(OclInteger anInteger) {

		Long longValue =
				((IModelInstanceInteger) anInteger.getModelInstanceElement()).getLong();

		return super.divide(JavaStandardLibraryFactory.INSTANCE
				.createOclReal(longValue));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#max(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger max(OclInteger that) {

		OclInteger result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Long int1 = this.imiInteger.getLong();
		Long int2 =
				((IModelInstanceInteger) that.getModelInstanceElement()).getLong();
		result =
				JavaStandardLibraryFactory.INSTANCE.createOclInteger(Math.max(int1,
						int2));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#min(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger min(OclInteger that) {

		OclInteger result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Long int1 = this.imiInteger.getLong();
		Long int2 =
				((IModelInstanceInteger) that.getModelInstanceElement()).getLong();
		result =
				JavaStandardLibraryFactory.INSTANCE.createOclInteger(Math.min(int1,
						int2));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#mod(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger mod(OclInteger that) {

		OclInteger result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Long dividend = this.imiInteger.getLong();
		Long divisor =
				((IModelInstanceInteger) that.getModelInstanceElement()).getLong();

		if (divisor == 0) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason("Division by zero.");
		}
		else {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInteger(dividend
							% divisor);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#multiply
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger multiply(OclInteger that) {

		OclInteger result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Long int1 = this.imiInteger.getLong();
		Long int2 =
				((IModelInstanceInteger) that.getModelInstanceElement()).getLong();
		result = JavaStandardLibraryFactory.INSTANCE.createOclInteger(int1 * int2);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#subtract
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger subtract(OclInteger that) {

		OclInteger result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Long int1 = this.imiInteger.getLong();
		Long int2 =
				((IModelInstanceInteger) that.getModelInstanceElement()).getLong();
		result = JavaStandardLibraryFactory.INSTANCE.createOclInteger(int1 - int2);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal
	 * #abs()
	 */
	@Override
	public OclInteger abs() {

		OclInteger result;

		checkUndefinedAndInvalid(this);

		/* Else compute the result. */
		result =
				JavaStandardLibraryFactory.INSTANCE.createOclInteger(Math
						.abs(imiInteger.getLong()));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal
	 * #negative()
	 */
	@Override
	public OclInteger negative() {

		OclInteger result;

		checkUndefinedAndInvalid(this);

		/* Else compute the result. */
		result =
				JavaStandardLibraryFactory.INSTANCE.createOclInteger(-(imiInteger
						.getLong()));

		return result;
	}

}
