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
		oldInput = newInput;
	}

	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof TracerTree) {
			return new Object[] {((TracerTree) inputElement).getRootElement()};
		}
		if(inputElement instanceof TracerNode) {
			return ((TracerNode) inputElement).getChildren();
		}
		/* There has been no match before */
		return new Object[0];
	}

	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof TracerNode) {
			return ((TracerNode) parentElement).getChildren();
		}
		if(parentElement instanceof TracerTree) {
			try {
				return ((TracerTree) parentElement).getRootElement().getChildren();
			} catch (NullPointerException e) {				
				/* The tree has been empty so there is no root
				 * element and the getRootElement() returns null
				 */
				return new Object[0];
			}
		}
		/* There has been no match before */
		return new Object[0];
	}

	public Object getParent(Object element) {
		if(element instanceof TracerTree) {
			/* The root element does not have parents so return null */
			return null;
		}
		if(element instanceof TracerNode) {
			return ((TracerNode) element).getParent();
		}
		/* There has been no match before */
		return null;
	}

	public boolean hasChildren(Object element) {
		if(element instanceof TracerTree) {
			try {
				return ((TracerTree) element).getRootElement().getChildren().length > 0;
			} catch(NullPointerException e) {
				/* The tree has been empty so there is no root
				 * element and the getRootElement() returns null
				 */
				return false;
			}
		}
		if(element instanceof TracerNode) {
			return ((TracerNode) element).getChildren().length > 0;
		}
		/* There has been no match before */
		return false;
	}
}
