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
package org.dresdenocl.tracer.ui.actions;

/**
 * <p>
 * This enumeration describes which possible values for
 * {@link TracerViewMenuAction} can exist.
 * </p>
 * 
 * @author Lars Schuetze
 */
public enum TracerViewMenuActionType {
	/* Action types for the tool bar */
	CLEAR_ALL_ELEMTENTS,
	
	/* Action types for the menu bar */
	FILTER_FALSE_ELEMENTS, FILTER_TRUE_ELEMENTS, FILTER_NOTHING, REMOVE_SELECTION
}
