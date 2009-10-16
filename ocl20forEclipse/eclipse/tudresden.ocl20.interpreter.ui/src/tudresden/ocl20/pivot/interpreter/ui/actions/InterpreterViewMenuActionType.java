/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Interpreter of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.interpreter.ui.actions;

/**
 * <p>
 * This enumerations describes the different possible types of
 * {@link InterpreterViewMenuAction} than can exist.
 * </p>
 * 
 * @author Claas Wilke
 */
public enum InterpreterViewMenuActionType {
	CLEAR_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS,
	CLEAR_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS,
	INTERPRET_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS,
	INTERPRET_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS,
	PREPARE_ALL_CONSTRAINTS, PREPARE_SELECTED_CONSTRAINTS,
	REMOVE_SELECTED_RESULTS, ADD_VARIABLE_TO_ENVIRONMENT, ENABLE_DISABLE_CACHING
}