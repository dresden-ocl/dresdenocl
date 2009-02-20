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
package tudresden.ocl20.interpreter.ui.internal.views;

import java.util.List;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import tudresden.ocl20.interpreter.ui.internal.OclInterpreterUIMessages;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;

/**
 * <p>
 * The {@link ResultsLabelProvider} provides Labels for results which shall be
 * shown in the {@link InterpreterView}.
 * </p>
 * 
 * @author Claas Wilke
 */
class ResultsLabelProvider extends LabelProvider implements ITableLabelProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang
	 * .Object, int)
	 */
	public String getColumnText(Object anObject, int index) {

		String result;

		/* Check if the given object is a list. */
		if (anObject instanceof List) {

			/* Check which element shall be displayed. */
			switch (index) {

			case ResultsContentProvider.MODELOBJECT: {

				Object anElement;

				anElement = ((List<?>) anObject).get(index);
				if (anElement != null) {
					result = anElement.toString();
				}

				else {
					result = OclInterpreterUIMessages.InterpreterView_Error_WrongTypeOfResult;
				}

				break;
			}

			case ResultsContentProvider.RESULT: {

				Object anElement;

				anElement = ((List<?>) anObject).get(index);
				if (anElement != null) {
					result = anElement.toString();
				}

				else {
					result = OclInterpreterUIMessages.InterpreterView_Error_WrongTypeOfResult;
				}

				break;
			}

			case ResultsContentProvider.CONSTRAINT: {

				Constraint aConstraint;

				String constrainedElemName;
				String body;

				aConstraint = (Constraint) ((List<?>) anObject).get(index);

				constrainedElemName = ((NamedElement) aConstraint
						.getConstrainedElement().get(0)).getQualifiedName();
				
				body = aConstraint.getSpecification().getBody();

				result = "context " + constrainedElemName;
				result += " " + aConstraint.getKind() + " ";
				result += aConstraint.getName() + ": ";
				
				if (body != null) {
					result += body;
				}
				// no else.

				break;
			}

			default: {
				result = OclInterpreterUIMessages.InterpreterView_Error_WrongIndex;
			}

			}
		}

		/* Else show an error message. */
		else {
			result = OclInterpreterUIMessages.InterpreterView_Error_WrongTypeOfResult;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java
	 * .lang.Object, int)
	 */
	public Image getColumnImage(Object obj, int index) {

		Image result;

		result = this.getImage(obj);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object obj) {

		Image result;

		result = PlatformUI.getWorkbench().getSharedImages().getImage(
				ISharedImages.IMG_OBJ_ELEMENT);

		return result;
	}
}