package org.dresdenocl.debug.ui.resource;

import org.dresdenocl.language.ocl.resource.ocl.ui.OclUIPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

public class OclDebugModelPresentation implements IDebugModelPresentation {

	public OclDebugModelPresentation() {

		super();
	}

	@Override
	public void addListener(ILabelProviderListener listener) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {

		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {

	}

	@Override
	public IEditorInput getEditorInput(Object element) {

		if (element instanceof IFile) {
			return new FileEditorInput((IFile) element);
		}
		else if (element instanceof ILineBreakpoint) {
			return new FileEditorInput((IFile) ((ILineBreakpoint) element)
					.getMarker().getResource());
		}
		else {
			return null;
		}
	}

	@Override
	public String getEditorId(IEditorInput input, Object element) {

		if (element instanceof IFile || element instanceof ILineBreakpoint) {
			return OclUIPlugin.EDITOR_ID;
		}
		return null;
	}

	@Override
	public void setAttribute(String attribute, Object value) {

		System.out.println("OclDebugModelPresentation.getImage: attribute = "
				+ attribute + "value = " + value);
	}

	@Override
	public Image getImage(Object element) {

		System.out.println("OclDebugModelPresentation.getImage: element = "
				+ element);
		return null;
	}

	@Override
	public String getText(Object element) {

		System.out.println("OclDebugModelPresentation.getText: element = "
				+ element);
		return null;
	}

	@Override
	public void computeDetail(IValue value, IValueDetailListener listener) {

		System.out.println("OclDebugModelPresentation.computeDetail: value = "
				+ value);
	}
}
