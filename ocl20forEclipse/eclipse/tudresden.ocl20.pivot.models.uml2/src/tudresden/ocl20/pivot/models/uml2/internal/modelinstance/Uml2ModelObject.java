package tudresden.ocl20.pivot.models.uml2.internal.modelinstance;

import java.util.List;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelObject;

/**
 * <p>
 * Represents model objects of a UML2 model instance.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Uml2ModelObject extends AbstractModelObject implements
		IModelObject {

	protected Object object;

	/**
	 * <p>
	 * Creates a new {@link Uml2ModelObject}.
	 * </p>
	 * 
	 * @param object
	 *            The {@link Object} for which an {@link Uml2ModelObject} shall
	 *            be created.
	 * @param name
	 *            The name of the created {@link Uml2ModelObject}.
	 * @param qualifiedName
	 *            The canonical name of the created {@link Uml2ModelObject}.
	 */
	public Uml2ModelObject(Object object, String name,
			List<String> qualifiedName) {
		this.name = name;
		this.object = object;
		this.qualifiedName = qualifiedName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getOclObject()
	 */
	public OclRoot getOclObject() {
		if (oclObject == null)
			oclObject = (OclObject) Platform.getAdapterManager().getAdapter(
					(Object) object, OclObject.class);
		return oclObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Uml2ModelObject(" + object.toString() + ")";
	}
}
