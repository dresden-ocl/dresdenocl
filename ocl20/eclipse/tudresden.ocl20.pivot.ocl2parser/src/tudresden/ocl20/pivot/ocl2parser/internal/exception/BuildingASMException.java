/*
    Copyright (C) 2007  Nils (s0006383@inf.tu-dresden.de)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package tudresden.ocl20.pivot.ocl2parser.internal.exception;

import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.TokenAS;

public class BuildingASMException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9015478079658887234L;
	protected TokenAS token;
	
	public BuildingASMException(String message, TokenAS errorToken) {
		super(message);
		token = errorToken;
	}
	
	public TokenAS getErrorToken() {
		return token;
	}
	
	public String getMessage() {
		String message = super.getMessage();
		if (token == null) return message;
		message = message + " Error occured at line " + token.getLine() +
		" and column " + token.getColumn() + ". The error occured at the token " + token.getValue() + ".";
		return message;
	}
}
