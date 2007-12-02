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

package tudresden.ocl20.pivot.ocl2parser.internal;

/**
 * While computing the iterator expression, we sometimes must create a new
 * iterator variable if the user hasn't specify any. Every variable must have
 * a name. This class generated a name of the form '$GeneratedVariable$number$'
 * whereas 'number' an integer is. This class is implemented as singleton, so we
 * can access these class from everywhere.
 * @author Nils
 *
 */
public class VariableNameGenerator {
	/**
	 * The private self variable that holds the only
	 * instance of the generator.
	 */
	static private VariableNameGenerator self;
	
	/**
	 * The number which is used to form the variable name.
	 */
	private int number = 0;
	
	
	/**
	 * The private constructor.
	 */
	private VariableNameGenerator() {
		
	}
	
	/**
	 * Get the singleton instance.
	 * @return
	 */
	static public VariableNameGenerator getDefault() {
		if (self == null) self = new VariableNameGenerator();
		
		return self;
	}
	
	/**
	 * Returns a new variable name. For each method call
	 * the number is incremented.
	 * @return a new variable name
	 */
	public String getVariableName() {
		String variableName = "$GeneratedVariable$" + number + "$";
		number = number + 1;
		
		return variableName;
	}
}
