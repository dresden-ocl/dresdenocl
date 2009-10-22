package tudresden.ocl20.pivot.modelbus.event;

import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.model.IModelRegistry;

/**
 * An <code>IModelRegistryListener</code> is informed about activities in an
 * {@link IModelRegistry}, such as loading models.
 * 
 * @author Matthias Braeuer
 * @version 1.0 11.04.2007
 */
public interface IModelRegistryListener {

  /**
   * This method is called when a {@link IModel model} has been added to a
   * {@link IModelRegistry model registry}.
   * 
   * @param e an event object with more details
   */
  void modelAdded(ModelRegistryEvent e);
  
  /**
   * This method is called when the active {@link IModel model} is changed in a 
   * {@link IModelRegistry registry}.
   * 
   * @param e an event object with more details
   */
  void activeModelChanged(ModelRegistryEvent e);

}
