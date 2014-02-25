/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.debug.util;

/**
 * A simple interface for generic functions with one argument.
 */
public interface IOclFunction1<ReturnType, ArgumentType1> {

	public ReturnType execute(ArgumentType1 argument1);
}
