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
package org.dresdenocl.tracer.ui.internal.views.util;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;

import org.dresdenocl.tracer.tracermodel.TracerItem;
import org.dresdenocl.tracer.tracermodel.TracerRoot;

/**
 * 
 * @author Lars Schuetze
 */
public class TracerItemAdapterFactoryContentProvider extends
		AdapterFactoryContentProvider {

	public TracerItemAdapterFactoryContentProvider(AdapterFactory adapterFactory) {

		super(adapterFactory);
	}

	/*
	 * public void notifyChanged(Notification notification) {
	 * super.notifyChanged(new ViewerNotification(notification)); }
	 */

	public Object[] getElements(Object object) {
		
		if (object instanceof TracerRoot) {
			return ((TracerRoot) object).getRootItems().toArray();
		}
		return super.getElements(object);
	}

	public Object[] getChildren(Object object) {

		if (object instanceof TracerItem) {
			return ((TracerItem) object).getChildren().toArray();
		}
		else
			return super.getChildren(object);
	}

	public boolean hasChildren(Object object) {

		return this.getChildren(object).length > 0;
	}

	public Object getParent(Object object) {

		if (object instanceof TracerItem) {
			return ((TracerItem) object).getParent();
		}
		else
			return super.getParent(object);
	}
}