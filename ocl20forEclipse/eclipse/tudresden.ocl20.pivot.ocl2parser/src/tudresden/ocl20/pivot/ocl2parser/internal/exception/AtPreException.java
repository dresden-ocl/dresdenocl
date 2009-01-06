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


package tudresden.ocl20.pivot.ocl2parser.internal.exception;

import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.TokenAS;

/**
 * This exception is thrown if a @pre is found although it isn't in a post-constraint.
 * The exception gets a message and a token. From the token a short position message
 * is created.
 * @author Nils
 * @version 0.1
 *
 */
public class AtPreException extends Exception {
	/**
	 * The whole message of the exception. Concatenation of the user message and
	 * the created position message.
	 */
	private String message;
	
	/**
	 * The token at which position the error occured.
	 */
	private TokenAS token;
	
	/**
	 * Creates a new exception with the given message and the token. The token is used
	 * to create a short position message to give the user a hint where the error is.
	 * The position message is appended to the message.
	 * @param message a user message
	 * @param token the token at which position the error occured
	 */
	public AtPreException(String message, TokenAS token) {
		this.token = token;
		String outputMessage = new String();
		String positionMessage = "The error occur in line" + token.getLine() + " and column " + token.getColumn();
		this.message = message + positionMessage;
	}

	/**
	 * Gets the whole message, inclusive the position message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the error token.
	 * @return
	 */
	public TokenAS getToken() {
		return token;
	}
}
