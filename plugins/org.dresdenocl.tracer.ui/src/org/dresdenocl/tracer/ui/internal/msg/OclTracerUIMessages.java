/*
Copyright (C) 2011 by Lars Schuetze (lschuetze@gmx.net)

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
package org.dresdenocl.tracer.ui.internal.msg;

import org.eclipse.osgi.util.NLS;

/**
 * @author Lars Schuetze
 */
public class OclTracerUIMessages extends NLS {

	private static final String BUNDLE_NAME =
			"org.dresdenocl.tracer.ui.internal.msg.messages";

	public static String TracerView_Filter_Nothing_Title;
	public static String TracerView_Filter_True_Title;
	public static String TracerView_Filter_False_Title;
	public static String TracerView_Remove_Selection_Title;
	public static String TracerView_Menu_Clear;
	public static String TracerView_Menu_Filter_Nothing;
	public static String TracerView_Menu_Filter_False;
	public static String TracerView_Menu_Filter_True;
	public static String TracerView_Menu_Remove_Selection;
	public static String InterpreterView_ActionError_NoConstraintForTracingSelected;
	
	static {
		/* Initialize resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME, OclTracerUIMessages.class);
	}

	private OclTracerUIMessages() {

		// TODO Auto-generated constructor stub
	}
}
