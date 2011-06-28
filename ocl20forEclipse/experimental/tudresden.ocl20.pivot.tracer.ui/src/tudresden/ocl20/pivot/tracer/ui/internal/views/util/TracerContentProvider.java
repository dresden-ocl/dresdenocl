package tudresden.ocl20.pivot.tracer.ui.internal.views.util;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import tudresden.ocl20.pivot.tracer.model.TracerNode;

public class TracerContentProvider implements ITreeContentProvider {

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		oldInput = newInput;
		/* DEBUG */
		System.out.println("oldInput: " + oldInput
				+ "newInput: " + newInput);
		/* DEBUG */
	}

	public Object[] getElements(Object inputElement) {
		Object[] result = new Object[3];
		if(inputElement instanceof TracerNode) {
			//result[0] = ((TracerNode) inputElement).getTracerItem()
				//.getExpression();
		}
		return result;
	}

	public Object[] getChildren(Object parentElement) {
		/* DEBUG */
		System.out.println("getChildren: parentElement.className = "
				+ parentElement.getClass().getName());
		/* DEBUG */
		
		if(parentElement instanceof TracerNode) {
			return ((TracerNode) parentElement).getChildren();
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
		return ((TracerNode) element).getChildren().length > 0;
	}

}
