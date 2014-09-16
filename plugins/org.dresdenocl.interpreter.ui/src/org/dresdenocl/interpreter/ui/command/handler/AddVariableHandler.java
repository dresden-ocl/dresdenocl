/*
Copyright (C) 2012 by Lars Schuetze (lschuetze@gmx.net)

This file is part of the OCL Interpreter of DresdenOCL.

DresdenOCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

DresdenOCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with DresdenOCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.interpreter.ui.command.handler;

import org.dresdenocl.interpreter.ui.internal.views.AddVariableDialog;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * @author Lars Schuetze
 * 
 */
public class AddVariableHandler extends AbstractInterpreterViewHandler {

	public static final String COMMAND_ADD_VARIABLE =
			"org.dresdenocl.interpreter.ui.command.AddVariable";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		super.execute(event);
		Command command = event.getCommand();

		if (command.getId().equals(COMMAND_ADD_VARIABLE)) {
			new AddVariableDialog(this.m_view).open();
		}
		return null;
	}

}
