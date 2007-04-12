package tudresden.ocl20.pivot.modelbus.event;

import java.util.EventObject;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelRegistry;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 11.04.2007
 */
public class ModelRegistryEvent extends EventObject {

  // generated serial version id
  private static final long serialVersionUID = -5135930089493176962L;

  // the affected model
  private IModel affectedModel;

  /**
   * Creates a new <code>ModelregistryEvent</code>.
   * 
   * @param source the <code>IModelRegistry</code> that is the source of this event
   * @param affectedModel the model affected by the operation that caused this event
   */
  public ModelRegistryEvent(IModelRegistry source, IModel affectedModel) {
    super(source);
    this.affectedModel = affectedModel;
  }

  /**
   * Returns the {@link IModel} that is affected by the operation that caused this event.
   * 
   * @return an <code>IModel</code> instance
   */
  public IModel getAffectedModel() {
    return affectedModel;
  }

  /**
   * Does the same as {@link #getSource()}, but has a more concise name. 
   * 
   * @return the <code>IModelRegistry</code> that is the source of this event
   */
  public IModelRegistry getModelRegistry() {
    return getSource();
  }

  /**
   * Returns the {@link IModelRegistry} that is the source of this event.
   * 
   * @see java.util.EventObject#getSource()
   */
  @Override
  public IModelRegistry getSource() {
    return (IModelRegistry) super.getSource();
  }

}
