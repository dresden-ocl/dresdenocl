/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

 * OCL Compiler                                                      *

 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *

 * All rights reserved.                                              *

 *                                                                   *

 * This work was done as a diploma project at the Chair for Software *

 * Technology, Dresden University Of Technology, Germany             *

 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *

 * modification not identified as such is not covered by the         *

 * preceding statement.                                              *

 *                                                                   *

 * This work is free software; you can redistribute it and/or        *

 * modify it under the terms of the GNU Library General Public       *

 * License as published by the Free Software Foundation; either      *

 * version 2 of the License, or (at your option) any later version.  *

 *                                                                   *

 * This work is distributed in the hope that it will be useful,      *

 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *

 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *

 * Library General Public License for more details.                  *

 *                                                                   *

 * You should have received a copy of the GNU Library General Public *

 * License along with this library; if not, write to the             *

 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *

 * Boston, MA  02111-1307, USA.                                      *

 *                                                                   *

 * To submit a bug report, send a comment, or get the latest news on *

 * this project and other projects, please visit the web site:       *

 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *

 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *

 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

// FILE: d:/java/classes/de/tudresden/ocl/OclString.java

package tudresden.ocl20.core.lib;

/**
 * This class represents the basic OCL type String.
 * 
 * 
 * 
 * @author Frank Finger
 * 
 */

public class OclString extends OclAny implements OclSizable {

	/**
	 * The value of this OclString, stored as a java.lang.String.
	 * 
	 * Must not be null.
	 * 
	 */

	private String sValue;

	/**
	 * package-visible constructor for OclString
	 * 
	 * 
	 * 
	 * @param str
	 *            the String to be represented; must not be <code>null</code>.
	 * 
	 */

	public OclString(String str) {

		sValue = str;

		str.length();

	}

	/**
	 * <p>Constructs an instance representing an undefined <code>OclString</code>.</p>
	 * 
	 * <p>This Constructor is deprecated. To create an undefined <code>OclString</code> use
	 * Construtor <code>protected OclString(String undefinedreason)</code></p>
	 * 
	 * @param dummy Must be 0.
	 * @param undefinedreason The reason why the <code>OclString</code> is undefined.
	 * @deprecated <p>This Constructor is deprecated. To create an undefined <code>OclString</code> 
	 * use Construtor <code>protected OclString(String str, boolean isUndefined)</code></p>
	 */
	/* Deprecated during refacotring by Claas Wilke in July 2007. */
	public OclString(int dummy, String undefinedreason) {
		super(dummy, undefinedreason);
	}

    /**
     * <p>Constructs an instance representing an undefined oder defined 
     * <code>OclString</code>.</p>
     * 
	 * @param str <p><code>str</code> If the <code>OclString</code> 
	 * 		should be undefined, 
	 * 		<code>str</code> contains the reason why the <code>OclString</code> 
	 * 		is undefined. Else <code>str</code> contains the value of the 
	 * 		<code>OclString</code>.</p>
	 * @param isDefined <p><code>isDefined</code>specifies wether or not the 
	 * 		<code>OclString</code>
	 * 		should be undefined. <code>False</code> means the <code>OclString</code>
	 * 		will be undefined.</p>
     */
    /* Created during refacotring by Claas Wilke in July 2007. */
	/* New contructor contains boolean because an undefined OclString can't be
	 * created by the constructor OclString(String undefinedreason).
	 * This constructor allready exists to create a defined OclString! */
	public OclString(String str, boolean isDefined) {
		/* Create an undefined OclString first. */
		super(str);
		/* Then eventuelly change the undefined OclString into a defined one. */
		if (isDefined) {
			sValue = str;
			str.length();
			undefinedreason = null;
		}
	}

	/**
	 * Two OclStrings are equal if their java.lang.String values are equal
	 * 
	 * and none of them is undefined.
	 * 
	 */

	public OclBoolean isEqualTo(Object o) {

		if (!(o instanceof OclString)) {

			System.out.println(

			"OclString.isEqualTo() called with non-OclString parameter"

			);

			return OclBoolean.FALSE;

		} else {

			OclString os = (OclString) o;

			if (isUndefined())
				/* Constructor changed during refactoring by Claas Wilke in July 2007. */
				return new OclBoolean(getUndefinedReason());

			if (os.isUndefined())
				/* Constructor changed during refactoring by Claas Wilke in July 2007. */
				return new OclBoolean(os.getUndefinedReason());

			if (os.sValue.equals(this.sValue)) {

				return OclBoolean.TRUE;

			} else {

				return OclBoolean.FALSE;

			}

		}

	}

	public int hashCode() {

		return sValue.hashCode();

	}

	public boolean equals(Object o) {

		try {

			return isEqualTo(o).isTrue();

		} catch (OclException e) {

			return false;

		}

	}

	/**
	 * @return the negateted result of isEqualTo
	 * 
	 * @see #isEqualTo(Object o)
	 * 
	 */

	public OclBoolean isNotEqualTo(Object o) {

		return isEqualTo(o).not();

	}

	/**
	 * @return the number of characters of this OclString, or an undefined
	 * 
	 * Integer value if this string is undefined
	 * 
	 */

	public OclInteger size() {

		if (isUndefined())
			/* Constructor changed during refactoring by Claas Wilke in July 2007. */
			return new OclInteger(getUndefinedReason());

		else {

			return new OclInteger(sValue.length());

		}

	}

	/**
	 * @return the concatenation of this OclString and the operations parameter.
	 * 
	 */

	public OclString concat(OclString s) {

		if (isUndefined())

			return this;

		if (s.isUndefined())

			return s;

		return new OclString(sValue.concat(s.sValue));

	}

	/**
	 * @return an OclString like this with all characters converted to upper
	 *         case
	 * 
	 */

	public OclString toUpper() {

		if (isUndefined()) {

			return this;

		} else {

			String upper = sValue.toUpperCase();

			return new OclString(upper);

		}

	}

	/**
	 * @return an OclString like this with all characters converted to upper
	 *         case
	 * 
	 */

	public OclString toLower() {

		if (isUndefined()) {

			return this;

		} else {

			String lower = sValue.toLowerCase();

			return new OclString(lower);

		}

	}

	/**
	 * @return the substring of this OclString starting at character number
	 * 
	 * <code>lower</code>, up to and including character number
	 * 
	 * <code>upper</code>; the first character of an OclString has
	 * 
	 * the number 1
	 * 
	 * @throws OclRuntimeException
	 * 
	 */

	public OclString substring(OclInteger lower, OclInteger upper) {

		if (isUndefined())

			return this;

		try

		{

			int start = lower.getInt();

			int end = upper.getInt();

			String sub = sValue.substring(start - 1, end);

			return new OclString(sub);

		}

		catch (IndexOutOfBoundsException ex)

		{
			/* Constructor changed during refactoring by Claas Wilke in July 2007. */
			return new OclString(ex.toString(), false);

		}

	}

	public String getString() {

		return sValue;

	}

	public String toString() {

		return "OclString<" + sValue + ">";

	}

} /* end class OclString */
