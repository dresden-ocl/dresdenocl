package org.dresdenocl.language.ocl.resource.ocl.mopp;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.dresdenocl.model.IModel;
import org.dresdenocl.model.IModelRegistry;

/**
 * Mix-in this interface into {@link Resource}s that belong to languages that
 * refer to OCL constructs.
 * 
 * @author Michael Thiele
 * @author Lars Schuetze
 * 
 */
public interface IOclResource extends Resource {

	/**
	 * Set the active {@link IModel}. Use this for standalone applications as
	 * otherwise the model can be extracted from the {@link IModelRegistry}.
	 * 
	 * @param model
	 *          can be <code>null</code>
	 */
	public void setModel(IModel model);

	/**
	 * Get the active {@link IModel} for this OCL resource.
	 * 
	 * @return the active {@link IModel} for this OCL resource
	 */
	public IModel getModel(List<EObject> from);

}
