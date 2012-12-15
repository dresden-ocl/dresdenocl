/*
Copyright (C) 2011 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of Dresden OCL.

Dresden OCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metrics.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

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

	/* TODO Create icons. */

	/** Path to icon for description of metric. */
	private final static String ICON_DESCRIPTION = "icons/description.gif";

	/** Path to icon for value of metric. */
	private final static String ICON_VALUE = "icons/value.gif";

	private Map<ImageDescriptor, Image> cachedImages = new HashMap<ImageDescriptor, Image>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object obj) {

		Image result;

		result = PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_OBJ_ELEMENT);

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

		/* Check if the given object is a list. */
		if (anObject.getClass().isArray()) {

			Object[] aRow;

			aRow = (Object[]) anObject;
			/* Check which icon shall be returned. */
			switch (index) {

			case ResultsContentProvider.DESCRIPTION: {
				if (aRow[index] != null && aRow[index].toString().length() > 0)
					imageDescriptor = MetricsUiPlugin
							.getImageDescriptor(ICON_DESCRIPTION);
				break;
			}

			case ResultsContentProvider.VALUE: {
				if (aRow[index] != null && aRow[index].toString().length() > 0)
				imageDescriptor = MetricsUiPlugin
						.getImageDescriptor(ICON_VALUE);
				break;
			}
			// no default.
			}
			// end switch.
		}

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
			result = PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_ELEMENT);
			result = null;
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

				case ResultsContentProvider.VALUE:
				case ResultsContentProvider.DESCRIPTION: {

					result = aRow[index].toString();
					break;
				}

				default: {
					result = "Invalid column";
				}

				}
				// end switch.
			}

			/* Else show an error message. */
			else {
				result = "The given row to display is not an array.";
			}

		}

		/* Else show an error message. */
		else {
			result = "The given row to display is not an array.";
		}

		return result;
	}
}