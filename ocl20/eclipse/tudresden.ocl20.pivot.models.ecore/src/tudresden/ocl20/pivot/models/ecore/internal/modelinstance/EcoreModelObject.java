package tudresden.ocl20.pivot.models.ecore.internal.modelinstance;

import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelObject;

public class EcoreModelObject extends AbstractModelObject implements
		IModelObject {

	private EObject object;

	public EcoreModelObject(EObject object, List<String> qualifiedName) {
		this.object = object;
		this.qualifiedName = qualifiedName;
	}

	public OclRoot getOclObject() {
		if (oclObject == null)
			oclObject = (OclObject) Platform.getAdapterManager().getAdapter(
					(Object) object, OclObject.class);
		return oclObject;
	}

	@Override
	public String toString() {
		return "EcoreModelObject(" + object.toString() + ")";
	}
}
