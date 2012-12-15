/*
    Copyright (C) 2008  Nils (s0006383@inf.tu-dresden.de)

    This file is part of the OCL parser of the Dresden OCL2 for Eclipse.

    The OCL parser is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The OCL parser is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with the OCL parser.  If not, see <http://www.gnu.org/licenses/>.
.
 */

package tudresden.ocl20.pivot.parser;


/**
 * <p>
 * Represents {@link Exception}s that occur during parsing a constraint file.
 * </p>
 * 
 * @author Nils Thieme
 * @author Claas Wilke (refactoring and documentation)
 */
public class ParsingException extends ParseException {

	/** Generated ID for serialization. */
	private static final long serialVersionUID = 2141132068538512605L;

	/** The token at which the {@link ParsingException} occurred. */
	private String tocen;

	/** The line at which the {@link ParsingException} occurred. */
	private int line;

	/** The column at which the {@link ParsingException} occurred. */
	private int column;

	/**
	 * <p>
	 * Creates a new {@link ParsingException}.
	 * </p>
	 * 
	 * @param message
	 *            The message of this {@link Exception}.
	 * @param token
	 *            The token at which the {@link ParsingException} occurred.
	 * @param line
	 *            The line at which the {@link ParsingException} occurred.
	 * @param column
	 *            The column at which the {@link ParsingException} occurred.
	 */
	public ParsingException(String message, String token, int line, int column) {
		super(message);
		this.line = line;
		this.column = column;
		this.tocen = token;
	}

	/**
	 * <p>
	 * Creates a new {@link ParsingException}.
	 * </p>
	 * 
	 * @param message
	 *            The message of this {@link Exception}.
	 * @param cause
	 *            The cause of this {@link Exception}.
	 * @param token
	 *            The token at which the {@link ParsingException} occurred.
	 * @param line
	 *            The line at which the {@link ParsingException} occurred.
	 * @param column
	 *            The column at which the {@link ParsingException} occurred.
	 */
	public ParsingException(String message, Throwable cause, String token,
			int line, int column) {
		super(message, cause);
		this.line = line;
		this.column = column;
		this.tocen = token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return super.getMessage() + " The error ocurred in line " + line
				+ " and column " + column + " at the token " + tocen + ".";
	}
}