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
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceString;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * Provides an implementation of {@link OclString} in Java.
 * </p>
 * 
 * @author Michael Thiele
 * @author Ronny Brandt
 */
public class JavaOclString extends JavaOclLibraryObject implements OclString {

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclString}.
	 * </p>
	 * 
	 * @param adaptee
	 *            The adapted model instance object of this
	 *            {@link JavaOclString}.
	 */
	public JavaOclString(IModelInstanceString imiString) {

		super(imiString);
	}

	public JavaOclString(String undefinedReason) {

		super(undefinedReason);
	}

	public JavaOclString(Throwable invalidReason) {

		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#asSet()
	 */
	public <T extends OclAny> OclSet<T> asSet() {

		OclSet<T> result = null;

		result = checkInvalid(
				EssentialOclPlugin
						.getOclLibraryProvider()
						.getOclLibrary()
						.getSetType(
								EssentialOclPlugin.getOclLibraryProvider()
										.getOclLibrary().getOclString()), this);

		if (result == null)
			result = checkAsSet(EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getOclString());

		if (result == null) {
			Set<IModelInstanceElement> imiSet = new HashSet<IModelInstanceElement>();
			imiSet.add(getModelInstanceString());

			result = JavaStandardLibraryFactory.INSTANCE.createOclSet(imiSet,
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getOclInteger());
		}

		return result;
	}

	public OclString at(OclInteger i) {

		OclString result;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclString(), this, i);

		if (result == null)
			result = checkUndefined("at", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclString(),
					this, i);

		if (result == null) {
			int javaIndex = i.getModelInstanceInteger().getLong().intValue() - 1;

			try {
				Character c = this.getModelInstanceString().getString()
						.charAt(javaIndex);
				result = JavaStandardLibraryFactory.INSTANCE.createOclString(c
						.toString());

			} catch (IndexOutOfBoundsException e) {
				result = JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
						EssentialOclPlugin.getOclLibraryProvider()
								.getOclLibrary().getOclString(), e);
			}
		}

		return result;
	}

	public OclSequence<OclString> characters() {

		OclSequence<OclString> result;

		result = checkInvalid(
				EssentialOclPlugin
						.getOclLibraryProvider()
						.getOclLibrary()
						.getSequenceType(
								EssentialOclPlugin.getOclLibraryProvider()
										.getOclLibrary().getOclString()), this);

		if (result == null)
			result = checkUndefined(
					"characters",
					EssentialOclPlugin
							.getOclLibraryProvider()
							.getOclLibrary()
							.getSequenceType(
									EssentialOclPlugin.getOclLibraryProvider()
											.getOclLibrary().getOclString()),
					this);

		if (result == null) {
			List<OclString> oclCharacters = new ArrayList<OclString>();

			for (Character aChar : getModelInstanceString().getString()
					.toCharArray()) {
				OclString oclChar = JavaStandardLibraryFactory.INSTANCE
						.createOclString(aChar.toString());
				oclCharacters.add(oclChar);
			}

			result = JavaStandardLibraryFactory.INSTANCE.createOclSequence(
					oclCharacters, EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclString());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#concat(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclString)
	 */
	public OclString concat(OclString aString) {

		OclString result = null;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclString(), this, aString);

		if (result == null)
			result = checkUndefined("concat", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclString(),
					this, aString);

		if (result == null) {
			StringBuilder concat = new StringBuilder();

			concat.append(getModelInstanceString().getString());
			concat.append(aString.getModelInstanceString().getString());

			result = JavaStandardLibraryFactory.INSTANCE.createOclString(concat
					.toString());
		}

		return result;
	}

	public OclBoolean equalsIgnoreCase(OclString s) {

		OclBoolean result;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclBoolean(), this, s);

		if (result == null)
			result = checkUndefined("equalsIgnoreCase", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclBoolean(),
					this, s);

		if (result == null)
			result = JavaStandardLibraryFactory.INSTANCE.createOclBoolean(this
					.getModelInstanceString().getString()
					.equalsIgnoreCase(s.getModelInstanceString().getString()));

		return result;
	}

	public IModelInstanceString getModelInstanceString() {

		return (IModelInstanceString) this.imiElement;
	}

	public OclInteger indexOf(OclString s) {

		OclInteger result;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclInteger(), this, s);

		if (result == null)
			result = checkUndefined("indexOf", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclInteger(),
					this, s);

		if (result == null) {
			String thisString = this.getModelInstanceString().getString();
			String thatString = s.getModelInstanceString().getString();

			/*
			 * "No string is a substring of the empty string." (standard, p.145)
			 */
			if (thisString.equals(""))
				result = JavaStandardLibraryFactory.INSTANCE
						.createOclInteger(0L);

			else {

				/*
				 * "The empty string is considered to be a substring of every
				 * string but the empty string, at index 1." (standard, p.145)
				 */
				if (thatString.equals(""))
					result = JavaStandardLibraryFactory.INSTANCE
							.createOclInteger(1L);

				else {

					int javaResult = this.getModelInstanceString().getString()
							.indexOf(s.getModelInstanceString().getString()) + 1;
					result = JavaStandardLibraryFactory.INSTANCE
							.createOclInteger(Long.valueOf(javaResult));
				}
			}

		}

		return result;
	}

	public OclBoolean matches(OclString s) {

		OclBoolean result;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclBoolean(), this, s);

		if (result == null)
			result = checkUndefined("matches", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclBoolean(),
					this, s);

		if (result == null) {
			String thisString = this.getModelInstanceString().getString();
			String thatString = s.getModelInstanceString().getString();
			Matcher m = Pattern.compile("\\[:([a-zA-Z])([a-zA-Z]*):\\]").matcher(thatString);
			while ( m.find() )  {
				thatString = thatString.replaceAll("\\[:"+m.group(1)+m.group(2)+":\\]", "\\\\p\\{"+m.group(1).toUpperCase()+m.group(2).toLowerCase()+"\\}");
			}
			try {
				result =  JavaStandardLibraryFactory.INSTANCE.createOclBoolean(thisString.matches(thatString));
			} catch (PatternSyntaxException e) {
				result = JavaStandardLibraryFactory.INSTANCE.createOclInvalid(EssentialOclPlugin.getOclLibraryProvider()
						.getOclLibrary().getOclBoolean(), e);
			}

		}

		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isEqualTo(OclAny that) {

		OclBoolean result;

		result = checkIsEqualTo(that);

		if (result == null) {
			/* Check if the given object is a String. */
			if (!(that instanceof OclString)) {
				result = JavaOclBoolean.getInstance(false);
			}

			else {
				String thatString = ((IModelInstanceString) that
						.getModelInstanceElement()).getString();

				if (getModelInstanceString().getString().equals(thatString)) {
					result = JavaOclBoolean.getInstance(true);
				} else {
					result = JavaOclBoolean.getInstance(false);
				}
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#isGreaterEqual
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString)
	 */
	public OclBoolean isGreaterEqual(OclString that) {

		OclBoolean result = null;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclBoolean(), this, that);

		if (result == null)
			result = checkUndefined(">=", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclBoolean(),
					this, that);

		if (result == null) {
			/* Else compute the result. */
			String string1 = getModelInstanceString().getString();
			String string2 = that.getModelInstanceString().getString();

			int intResult = string1.compareTo(string2);

			result = JavaOclBoolean.getInstance(intResult >= 0);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#isGreaterThan
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString)
	 */
	public OclBoolean isGreaterThan(OclString that) {

		OclBoolean result = null;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclBoolean(), this, that);

		if (result == null)
			result = checkUndefined(">", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclBoolean(),
					this, that);

		if (result == null) {
			/* Else compute the result. */
			String string1 = getModelInstanceString().getString();
			String string2 = that.getModelInstanceString().getString();

			int intResult = string1.compareTo(string2);

			result = JavaOclBoolean.getInstance(intResult > 0);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#isLessEqual
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString)
	 */
	public OclBoolean isLessEqual(OclString that) {

		OclBoolean result = null;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclBoolean(), this, that);

		if (result == null)
			result = checkUndefined("<=", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclBoolean(),
					this, that);

		if (result == null) {
			/* Else compute the result. */
			String string1 = getModelInstanceString().getString();
			String string2 = that.getModelInstanceString().getString();

			int intResult = string1.compareTo(string2);

			result = JavaOclBoolean.getInstance(intResult <= 0);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#isLessThan
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString)
	 */
	public OclBoolean isLessThan(OclString that) {

		OclBoolean result = null;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclBoolean(), this, that);

		if (result == null)
			result = checkUndefined("<", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclBoolean(),
					this, that);

		if (result == null) {
			/* Else compute the result. */
			String string1 = getModelInstanceString().getString();
			String string2 = that.getModelInstanceString().getString();

			int intResult = string1.compareTo(string2);

			result = JavaOclBoolean.getInstance(intResult < 0);

		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#plus(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclString)
	 */
	public OclString plus(OclString s) {

		return concat(s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#size()
	 */
	public OclInteger size() {

		OclInteger result = null;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclInteger(), this);

		if (result == null)
			result = checkUndefined("size", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclInteger(),
					this);

		if (result == null) {
			/* Else compute the result. */
			Long size = Long.valueOf(getModelInstanceString().getString()
					.length());
			result = JavaStandardLibraryFactory.INSTANCE.createOclInteger(size);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#substring
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclString substring(OclInteger lower, OclInteger upper) {

		OclString result = null;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclString(), this, lower, upper);

		if (result == null)
			result = checkUndefined("substring", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclString(),
					this, lower, upper);

		if (result == null) {
			/* Indices in OCL are different from Java indices! */
			int intLower = ((IModelInstanceInteger) lower
					.getModelInstanceElement()).getLong().intValue() - 1;

			int intUpper = ((IModelInstanceInteger) upper
					.getModelInstanceElement()).getLong().intValue();

			try {
				if (intLower >= intUpper)
					throw new IndexOutOfBoundsException(
							"Cannot use substring() with greater lower bound.");

				result = JavaStandardLibraryFactory.INSTANCE
						.createOclString(getModelInstanceString().getString()
								.substring(intLower, intUpper));
			} catch (IndexOutOfBoundsException e) {
				result = JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
						EssentialOclPlugin.getOclLibraryProvider()
								.getOclLibrary().getOclString(), e);
			}
		}

		return result;
	}

	public OclBoolean toBoolean() {

		OclBoolean result;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclBoolean(), this);

		if (result == null)
			result = checkUndefined("toBoolean", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclBoolean(),
					this);

		if (result == null) {
			if (this.getModelInstanceString().getString().equals("true"))
				result = JavaStandardLibraryFactory.INSTANCE
						.createOclBoolean(true);
			else
				result = JavaStandardLibraryFactory.INSTANCE
						.createOclBoolean(false);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#toInteger()
	 */
	public OclInteger toInteger() {

		OclInteger result = null;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclInteger(), this);

		if (result == null)
			result = checkUndefined("toInteger", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclInteger(),
					this);

		if (result == null) {
			/* Else compute the result. */
			try {
				Long toInteger = new Long(getModelInstanceString().getString());

				result = JavaStandardLibraryFactory.INSTANCE
						.createOclInteger(toInteger);
			}

			catch (NumberFormatException e) {
				result = JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
						EssentialOclPlugin.getOclLibraryProvider()
								.getOclLibrary().getOclInteger(), e);
			}
		}

		return result;
	}

	public OclString toLowerCase() {

		OclString result;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclString(), this);

		if (result == null)
			result = checkUndefined("toLowerCase", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclString(),
					this);

		if (result == null) {
			result = JavaStandardLibraryFactory.INSTANCE.createOclString(this
					.getModelInstanceString().getString().toLowerCase());
		}

		return result;
	}

	public OclString toUpperCase() {

		OclString result;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclString(), this);

		if (result == null)
			result = checkUndefined("toUpperCase", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclString(),
					this);

		if (result == null) {
			result = JavaStandardLibraryFactory.INSTANCE.createOclString(this
					.getModelInstanceString().getString().toUpperCase());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#toReal()
	 */
	public OclReal toReal() {

		OclReal result = null;

		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclReal(), this);

		if (result == null)
			result = checkUndefined("toReal", EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclReal(), this);

		if (result == null) {
			/* Else compute the result. */
			try {
				Double toReal = new Double(getModelInstanceString().getString());

				result = JavaStandardLibraryFactory.INSTANCE
						.createOclReal(toReal);
			}

			catch (NumberFormatException e) {
				result = JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
						EssentialOclPlugin.getOclLibraryProvider()
								.getOclLibrary().getOclReal(), e);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder result = new StringBuilder();

		result.append(this.getClass().getSimpleName());
		result.append("[");

		if (!toStringUndefinedOrInvalid(result))
			result.append(getModelInstanceString().getString());

		result.append("]");

		return result.toString();
	}
}