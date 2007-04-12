package tudresden.ocl20.pivot.modelbus.event;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelRegistry;

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

}
