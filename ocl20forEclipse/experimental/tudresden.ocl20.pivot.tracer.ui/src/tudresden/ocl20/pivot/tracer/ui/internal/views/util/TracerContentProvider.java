package tudresden.ocl20.pivot.tracer.ui.internal.views.util;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;

public class TracerContentProvider implements ITreeContentProvider {

	public void dispose() {
		// TODO Auto-generated method stub
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		oldInput = newInput;
	}

	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof TracerItem) {
			return ((TracerItem)inputElement).getChildren().toArray();
		} else {
			return new Object[0];
		}
	}

	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof TracerItem) {
			return ((TracerItem)parentElement).getChildren().toArray();
		} else {
			return new Object[0];
		}
	}

	public Object getParent(Object element) {
		if(element instanceof TracerItem) {
			return ((TracerItem)element).getParent();
		} else {
			return null;
		}
	}

	public boolean hasChildren(Object element) {
		if(element instanceof TracerItem) {
			return ((TracerItem) element).hasChildren();
		} else {
			return false;
		}
	}
}