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
package tudresden.ocl20.interpreter.ui.internal;

import org.eclipse.osgi.util.NLS;

/**
 * <p>
 * This class contains localized string constants.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclInterpreterUIMessages extends NLS {

	private static final String BUNDLE_NAME = "tudresden.ocl20.interpreter.ui.internal.messages";

	public static String InterpreterView_ObjectColumn;

	public static String InterpreterView_ConstraintColumn;

	public static String InterpreterView_ResultColumn;

	public static String InterpreterView_ResultSelection;

	public static String InterpreterView_InterpreterResults;
	
	public static String InterpreterView_UseCache;

	public static String InterpreterView_Menu_Body;

	public static String InterpreterView_Menu_Def;

	public static String InterpreterView_Menu_Derive;

	public static String InterpreterView_Menu_Init;

	public static String InterpreterView_Menu_Post;

	public static String InterpreterView_Menu_Inv;

	public static String InterpreterView_Menu_Pre;

	public static String InterpreterView_ActionError_NoConstraintSelected;

	public static String InterpreterView_ActionError_NoModelObectSelected;

	public static String InterpreterView_Error_NoActiveModel;

	public static String InterpreterView_Error_NoActiveModelInstance;

	public static String InterpreterView_Error_WrongTypeOfResult;

	public static String InterpreterView_Error_NullResult;

	public static String InterpreterView_Error_WrongIndex;

	public static String InterpreterView_AddVariable_Title;

	public static String InterpreterView_AddVariable_VariableLabel;

	public static String InterpreterView_AddVariable_TypeLabel;

	public static String InterpreterView_AddVariable_ValueLabel;

	public static String InterpreterView_AddVariable_ResultLabel;

	public static String InterpreterView_AddVariable_Error_NoValue;

	public static String InterpreterView_AddVariable_Error_NoIntegerValue;

	public static String InterpreterView_AddVariable_Error_NoRealValue;

	public static String InterpreterView_AddVariable_Error_NoBooleanValue;

	public static String InterpreterView_AddVariable_Error_NoResult;

	public static String InterpreterView_AddVariable_Error_NoPathName;

	public static String InterpretAction_InterpretSelectedConstraintsForSelectedModelObjects;

	public static String InterpretAction_InterpretSelectedConstraintsForAllModelObjects;

	public static String InterpretAction_InterpretAllConstraintsForSelectedModelObjects;

	public static String InterpretAction_InterpretAllConstraintsForAllModelObjects;

	public static String InterpretAction_ClearSelectedConstraintsForSelectedModelObjects;

	public static String InterpretAction_ClearSelectedConstraintsForAllModelObjects;

	public static String InterpretAction_ClearAllConstraintsForSelectedModelObjects;

	public static String InterpretAction_ClearAllConstraintsForAllModelObjects;

	public static String InterpretAction_PrepareSelectedConstraints;

	public static String InterpretAction_PrepareAllConstraints;

	public static String InterpretAction_RemoveSelectedResults;

	static {
		/* Initialize resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME, OclInterpreterUIMessages.class);
	}

	private OclInterpreterUIMessages() {
		/* No implementation necessary. */
	}
}
