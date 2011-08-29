package tudresden.ocl20.pivot.tracer.ui.internal.views.util;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import tudresden.ocl20.pivot.tracer.model.TracerItem;
import tudresden.ocl20.pivot.tracer.model.TracerNode;
import tudresden.ocl20.pivot.tracer.model.TracerTree;

public class TracerContentProvider implements ITreeContentProvider {

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		/* DEBUG */
		System.out.println("oldInput: " + oldInput
				+ " newInput: " + newInput);
		/* DEBUG */
		
		oldInput = newInput;
	}

	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof TracerTree) {
			return ((TracerTree) inputElement).getRootElement().getChildren();
		}
		if(inputElement instanceof TracerNode) {
			return ((TracerNode) inputElement).getChildren();
		}
		return new Object[0];
	}

	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof TracerNode) {
			return ((TracerNode) parentElement).getChildren();
		}
		if(parentElement instanceof TracerTree) {
			return ((TracerTree) parentElement).getRootElement().getChildren();
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		if(element instanceof TracerNode) {
			return ((TracerNode) element).getParent();
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		if(element instanceof TracerNode) {
			return ((TracerNode) element).getChildren().length > 0;
		}
		else {
			if(element instanceof String)
				return false;
		}
		return false;
	}

}
