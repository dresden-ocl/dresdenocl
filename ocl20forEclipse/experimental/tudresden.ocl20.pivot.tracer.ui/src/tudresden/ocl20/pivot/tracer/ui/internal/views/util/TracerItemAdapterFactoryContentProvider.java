package tudresden.ocl20.pivot.tracer.ui.internal.views.util;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;

import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import org.eclipse.emf.common.notify.AdapterFactory;

public class TracerItemAdapterFactoryContentProvider extends
		AdapterFactoryContentProvider {

	public TracerItemAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	public Object[] getElements(Object object) {
		return this.getChildren(object);
	}
	
	public Object[] getChildren(Object object) {
		if(object instanceof TracerItem) {
			return ((TracerItem)object).getChildren().toArray();
		}
		else return super.getChildren(object);
	}
	
	public boolean hasChildren(Object object) {
		return this.getChildren(object).length > 0;
	}
	
	public Object getParent(Object object) {
		if(object instanceof TracerItem) {
			return ((TracerItem)object).getParent();
		}
		else return super.getParent(object);
	}
}