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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

import org.dresdenocl.tracer.ui.internal.msg.OclTracerUIMessages;
import org.dresdenocl.tracer.ui.internal.views.TracerView;
import org.dresdenocl.tracer.ui.internal.views.util.ViewerFilterType;

/**
 * @author Lars Schuetze
 * 
 */
public class TracerViewMenuAction extends Action implements IAction {

	/** The {@link TracerView} of this {@link TracerViewMenuAction}. */
	private TracerView myTracerView;

	/**
	 * The {@link TracerViewMenuActionType} of this {@link TracerViewMenuAction} .
	 */
	private TracerViewMenuActionType myType;

	/**
	 * <p>
	 * Instantiates an {@link TracerViewMenuAction}.
	 * </p>
	 * 
	 * @param type
	 *          The {@link TracerViewMenuActionType}.
	 * @param view
	 *          The {@link TracerView} on which the action shall be performed.
	 */
	public TracerViewMenuAction(TracerViewMenuActionType actionType,
			TracerView tracerView) {

		super(null, IAction.AS_PUSH_BUTTON);

		myTracerView = tracerView;
		myType = actionType;

		setText(toString());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		switch (myType) {
		case CLEAR_ALL_ELEMTENTS:
			result = OclTracerUIMessages.TracerView_Menu_Clear;
			break;

		case FILTER_FALSE_ELEMENTS:
			result = OclTracerUIMessages.TracerView_Menu_Filter_False;
			break;

		case FILTER_TRUE_ELEMENTS:
			result = OclTracerUIMessages.TracerView_Menu_Filter_True;
			break;

		case FILTER_NOTHING:
			result = OclTracerUIMessages.TracerView_Menu_Filter_Nothing;
			break;

		case REMOVE_SELECTION:
			result = OclTracerUIMessages.TracerView_Menu_Remove_Selection;
			break;

		default:
			result = "No action selected.";
		}

		return result;
	}

	public void run() {

		/* Depending on the type we have to do different things */
		switch (myType) {
		case CLEAR_ALL_ELEMTENTS:
			myTracerView.clearTracerView();
			break;

		case FILTER_NOTHING:
			myTracerView.setFilterType(ViewerFilterType.FILTER_NONE);
			break;

		case FILTER_FALSE_ELEMENTS:
			myTracerView.setFilterType(ViewerFilterType.FILTER_FALSE);
			break;

		case FILTER_TRUE_ELEMENTS:
			myTracerView.setFilterType(ViewerFilterType.FILTER_TRUE);
			break;
			
		case REMOVE_SELECTION:
			myTracerView.setFilterElements(null);
			break;
		}
		// end switch
	}
}
