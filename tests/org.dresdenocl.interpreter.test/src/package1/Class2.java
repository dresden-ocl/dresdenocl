/*
Copyright (C) 2011 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of the PAIN Case Study of Dresden OCL.

Dresden OCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package package1;

/**
 * <p>
 * A class of a simple test model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Class2 {

	/** A property whose semantics could be defined by a constraint. */
	public Integer intProperty = null;

	/** A property whose semantics could be defined by a constraint. */
	public static Integer staticIntProperty = null;

	/** An operation whose semantics could be defined by a constraint. */
	public Integer intOperation() {
		return null;
	}

	/** An operation whose semantics could be defined by a constraint. */
	public static Integer staticIntOperation() {
		return null;
	}
}
