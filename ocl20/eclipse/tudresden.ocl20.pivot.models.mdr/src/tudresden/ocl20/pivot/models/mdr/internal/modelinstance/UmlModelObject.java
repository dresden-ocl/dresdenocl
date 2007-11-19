package tudresden.ocl20.pivot.models.mdr.internal.modelinstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

public class UmlModelObject extends AbstractModelObject implements IModelObject {

	private Object object;

	public UmlModelObject(Object object, String name, List<String> qualifiedName) {
		this.name = name;
		this.object = object;
		this.qualifiedName = qualifiedName;
	}

	@Override
	public OclRoot getOclObject() {
		if (oclObject == null)
			oclObject = (OclRoot) Platform.getAdapterManager().getAdapter(
					object, OclObject.class);
		return oclObject;
	}

	@Override
	public String toString() {
		return "UmlModelObject(" + object.toString() + ")";
	}
}
