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

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.ui.internal.views.TracerView;

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

	private Map<UUID, ViewerFilterType> addedParents;

	private Map<UUID, TracerItem> whiteList;

	/**
	 * <p>
	 * The constructor of this class.
	 * </p>
	 */
	public TracerItemViewerFilter() {

		super();

		filterType = ViewerFilterType.FILTER_NONE;
		addedParents = new WeakHashMap<UUID, ViewerFilterType>();
		whiteList = new WeakHashMap<UUID, TracerItem>();
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (element instanceof TracerRoot) {
			return false;
		}
		// no else

		if (element instanceof TracerItem) {
			TracerItem item = (TracerItem) element;

			if (whiteList.containsKey(item.getUUID())) {

				if (filterType.equals(ViewerFilterType.FILTER_NONE)) {
					return true;
				}
				// no else

				// If the parentElement is a TracerItem we will already
				// have adapted it into our cache
				//
				if (parentElement instanceof TracerItem) {
					TracerItem parentItem = (TracerItem) parentElement;

					// Check if the parent has been cached and could pass the filter.
					//
					if (addedParents.containsKey(parentItem.getUUID())) {
						if (filterType.equals(addedParents.get(parentItem.getUUID()))) {
							// Then this item can cached added too.
							//
							addedParents.put(item.getUUID(), filterType);
							return true;
						}
					}
				}

				// There was no parent found so we need to determine our self.
				//
				OclAny result = item.getResult();

				if (result instanceof OclBoolean) {
					OclBoolean anOclBoolean = (OclBoolean) result;

					if (anOclBoolean.oclIsInvalid().isTrue()
							|| anOclBoolean.oclIsUndefined().isTrue()) {
						return false;
					}
					// no else

					switch (filterType) {
					case FILTER_FALSE:
						addedParents.put(item.getUUID(), filterType);
						return !anOclBoolean.isTrue();

					case FILTER_TRUE:
						addedParents.put(item.getUUID(), filterType);
						return anOclBoolean.isTrue();
					default:
						return true;
					}
					// end switch
				}
				// no else (result is no boolean)
			}
			// no else (element is not whitelisted)
		}
		// no else (not an TracerItem)

		// Nothing could be applied.
		//
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

	public void setFilterElements(Map<UUID, TracerItem> whiteList) {

		this.whiteList = whiteList;
	}

}
