/*
Copyright (C) 2012 by Lars Schuetze (lschuetze@gmx.net)

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
package tudresden.ocl20.pivot.tracer.ui.internal.views.util;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;

/**
 * <p>
 * This class adds filter support for the {@link TracerView}
 * </p>
 * 
 * @author Lars Schuetze
 * 
 */
public class TracerItemViewerFilter extends ViewerFilter {

	private ViewerFilterType filterType;

	/**
	 * <p>
	 * The constructor of this class.
	 * </p>
	 */
	public TracerItemViewerFilter() {

		super();
		filterType = ViewerFilterType.FILTER_NONE;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (element instanceof TracerRoot) {
			return true;
		}
		// no else

		if (element instanceof TracerItem) {

			OclAny result = ((TracerItem) element).getResult();

			if (result instanceof OclBoolean) {
				OclBoolean anOclBoolean = (OclBoolean) result;

				if (anOclBoolean.oclIsInvalid().isTrue()
						|| anOclBoolean.oclIsUndefined().isTrue()) {
					/* Return true if there is no filtering, else false */
					return filterType.equals(ViewerFilterType.FILTER_NONE);
				}
				//no else

				switch (filterType) {
				case FILTER_FALSE:
					return !anOclBoolean.isTrue();

				case FILTER_TRUE:
					return anOclBoolean.isTrue();

				case FILTER_NONE:
					return true;
				}
				// end switch
			}
			/* result is no boolean */
			else {
				switch (filterType) {
				case FILTER_NONE:
					return true;

				default:
					return false;
				}
				// end switch
			}
		}
		// no else

		/* Nothing could be applied. */
		return false;
	}

	/**
	 * <p>
	 * This method has to be called <b>before</b> filters are applied. By default
	 * it filters nothing.
	 * </p>
	 * 
	 * @param filterType
	 *          the type to set the filter to
	 */
	public void setFilterType(ViewerFilterType filterType) {

		this.filterType = filterType;
	}

}
