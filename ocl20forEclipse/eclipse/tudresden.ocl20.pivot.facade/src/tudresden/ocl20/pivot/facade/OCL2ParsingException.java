/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Facade of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.facade;

/**
 * <p>
 * This exception is used to decorate the different {@link Exception}s the
 * current OCL2 Parser throws to keep the {@link Ocl2ForEclipseFacade}'s
 * interfaces as simple as possible. It could be enriched in future works with
 * parsing details required for syntax highlighting and appropriate error
 * messages.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OCL2ParsingException extends Exception {

	/** Generated ID for serialization. */
	private static final long serialVersionUID = 3847515956745834052L;

	/**
	 * <p>
	 * Creates a new {@link OCL2ParsingException}.
	 * </p>
	 * 
	 * @param cause
	 *          The cause {@link Exception}.
	 */
	public OCL2ParsingException(Exception cause) {

		super(cause);
	}
}