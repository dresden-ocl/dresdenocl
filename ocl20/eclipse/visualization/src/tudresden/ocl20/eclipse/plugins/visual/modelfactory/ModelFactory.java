package tudresden.ocl20.eclipse.plugins.visual.modelfactory;

import java.io.File;
import java.util.Collection;

import tudresden.ocl20.eclipse.plugins.visual.exceptions.ModelFactoryException;
import tudresden.ocl20.eclipse.plugins.visual.model.VisualModel;

/**
 * Interface for a general ModelFactory used by the visualization plugin
 * @author Kai-Uwe Gärtner
 *
 */
public interface ModelFactory {
	/**
	 * Generates a visual model displayed by the visualization
	 * @param context displayed context (model-specific)
	 * @return a VisualModel-Instance
	 * @throws ModelFactoryException 
	 */
	public VisualModel getVisualModel(Object context) throws ModelFactoryException;
	
	/**
	 * Loads an external ressource
	 * @param file
	 */
	public void loadRessource(File file);
	
	/**
	 * Returns all possible contexts (model-specifc)
	 * @return list of contexts
	 */
	public Collection getContextList();
	
	/**
	 * Returns a Display-Name of a context. Should be unique.
	 * @param context displayed context
	 * @return display name
	 */
	public String getContextDisplayName(Object context);
	
	/**
	 * Returns a context by a given display name. Works only with unique 
	 * display names.
	 * @param displayName display name
	 * @return the context
	 */
	public Object getContextByDisplayName(String displayName);
}
