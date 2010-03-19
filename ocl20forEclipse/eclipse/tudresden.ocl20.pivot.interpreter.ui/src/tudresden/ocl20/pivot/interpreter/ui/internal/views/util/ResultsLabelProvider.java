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
package tudresden.ocl20.pivot.interpreter.ui.internal.views.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.interpreter.ui.InterpreterUIPlugin;
import tudresden.ocl20.pivot.interpreter.ui.internal.msg.OclInterpreterUIMessages;
import tudresden.ocl20.pivot.interpreter.ui.internal.views.InterpreterView;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;

/**
 * <p>
 * The {@link ResultsLabelProvider} provides Labels for results that shall be
 * shown in the {@link InterpreterView}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ResultsLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	/** Path to icon for {@link Constraint}s. */
	private final static String ICON_CONSTRAINT = "icons/constraint.gif";

	/** Path to icon for {@link IModelInstanceElement}s. */
	private final static String ICON_MODEL_OBJECT = "icons/instance.gif";

	/** Path to icon for true results. */
	private final static String ICON_RESULT_TRUE = "icons/result_true.gif";

	/** Path to icon for false results. */
	private final static String ICON_RESULT_FALSE = "icons/result_false.gif";

	/** Path to icon for undefined results. */
	private final static String ICON_RESULT_UNDEFINED = "icons/result_undefined.gif";

	/** Path to icon for undefined results. */
	private final static String ICON_RESULT_INVALID = "icons/result_invalid.gif";

	private Map<ImageDescriptor, Image> cachedImages = new HashMap<ImageDescriptor, Image>();

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java
	 * .lang.Object, int)
	 */
	public Image getColumnImage(Object anObject, int index) {

		Image result;
		ImageDescriptor imageDescriptor;
		imageDescriptor = null;

		/* Check which icon shall be returned. */
		switch (index) {

		case ResultsContentProvider.CONSTRAINT: {
			imageDescriptor = InterpreterUIPlugin
					.getImageDescriptor(ICON_CONSTRAINT);
			break;
		}

		case ResultsContentProvider.MODELOBJECT: {
			imageDescriptor = InterpreterUIPlugin
					.getImageDescriptor(ICON_MODEL_OBJECT);
			break;
		}

		case ResultsContentProvider.RESULT: {

			result = null;

			if (anObject.getClass().isArray()) {

				Object[] aRow;
				aRow = (Object[]) anObject;

				/* Check the size of the array. */
				if (ResultsContentProvider.RESULT <= aRow.length) {

					/* Get the result object. */
					Object aResult;
					aResult = aRow[index];

					/* Check if the result is not null. */
					if (aResult != null) {

						/* Check if the result is boolean. */
						if (aResult instanceof OclBoolean) {

							OclBoolean anOclBoolean;
							anOclBoolean = (OclBoolean) aResult;

							/* Check if the boolean is invalid. */
							if (anOclBoolean.oclIsInvalid().isTrue()) {
								imageDescriptor = InterpreterUIPlugin
										.getImageDescriptor(ICON_RESULT_INVALID);
							}

							/* Else check if the boolean is undefined. */
							else if (anOclBoolean.oclIsUndefined().isTrue()) {
								imageDescriptor = InterpreterUIPlugin
										.getImageDescriptor(ICON_RESULT_UNDEFINED);
							}

							/* Else check if the boolean is true. */
							else if (anOclBoolean.isTrue()) {
								imageDescriptor = InterpreterUIPlugin
										.getImageDescriptor(ICON_RESULT_TRUE);
							}

							/* Else the result is false. */
							else {
								imageDescriptor = InterpreterUIPlugin
										.getImageDescriptor(ICON_RESULT_FALSE);
							}
						}
						// no else.
					}
					// no else.
				}
				// no else.
			}
			// no else.

			/* If the icon could not be defined, use the undefined icon. */
			if (imageDescriptor == null) {

				imageDescriptor = InterpreterUIPlugin
						.getImageDescriptor(ICON_RESULT_UNDEFINED);
			}
			// no else.

			break;
		}
			// no default.
		}
		// end switch.

		if (imageDescriptor != null) {

			if (this.cachedImages.containsKey(imageDescriptor)) {
				result = this.cachedImages.get(imageDescriptor);
			}

			else {
				result = imageDescriptor.createImage();
				this.cachedImages.put(imageDescriptor, result);
			}
		}

		else {
			result = PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}

		return result;
	}

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
		if (anObject.getClass().isArray()) {

			Object[] aRow;

			aRow = (Object[]) anObject;

			if (index <= aRow.length) {

				/* Check which element shall be displayed. */
				switch (index) {

				case ResultsContentProvider.MODELOBJECT:
				case ResultsContentProvider.RESULT: {

					if (aRow[index] != null) {
						result = aRow[index].toString();
					}

					else {
						result = OclInterpreterUIMessages.InterpreterView_Error_WrongTypeOfResult;
					}

					break;
				}

					/* Create the output for the constraint column. */
				case ResultsContentProvider.CONSTRAINT: {

					if (aRow[index] instanceof Constraint) {
						Constraint aConstraint;
						NamedElement constrainedElement;

						String body;

						aConstraint = (Constraint) aRow[index];

						constrainedElement = (NamedElement) aConstraint
								.getConstrainedElement().get(0);

						/*
						 * If the context is an operation, add context
						 * information.
						 */
						if (constrainedElement instanceof Operation) {

							String qualifiedName;

							qualifiedName = constrainedElement
									.getQualifiedName();
							result = "context ";
							result += qualifiedName.substring(qualifiedName
									.lastIndexOf(":") + 1);
							result += ": ";
						}

						/*
						 * Else the context is not needed because it is clear by
						 * the model object.
						 */
						else {
							result = "";
						}

						body = aConstraint.getSpecification().getBody();

						result += aConstraint.getKind() + " ";

						if (aConstraint.getName() != null) {
							result += aConstraint.getName();
						}
						// no else.

						result += ": ";

						if (body != null) {
							result += body;
						}
						// no else.
					}

					else {
						result = OclInterpreterUIMessages.InterpreterView_Error_WrongTypeOfResult;
					}

					break;
				}

				default: {
					result = OclInterpreterUIMessages.InterpreterView_Error_WrongIndex;
				}

				}
				// end switch.
			}

			/* Else show an error message. */
			else {
				result = OclInterpreterUIMessages.InterpreterView_Error_WrongTypeOfResult;
			}

		}

		/* Else show an error message. */
		else {
			result = OclInterpreterUIMessages.InterpreterView_Error_WrongTypeOfResult;
		}

		return result;
	}
}