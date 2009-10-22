package tudresden.ocl20.pivot.parser;

import org.apache.log4j.Logger;

import java.net.URL;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.model.IModelFactory;
import tudresden.ocl20.pivot.modelbus.model.ITypeResolver;
import tudresden.ocl20.pivot.parser.internal.ParserMessages;

/**
 * An abstract base implementation of the {@link IOclParser} interface.
 * 
 * @author Matthias Braeuer
 * @version 1.0 23.08.2007
 */
public abstract class AbstractOclParser implements IOclParser {

  // logger for this class and subclasses
  protected final Logger logger = ParserPlugin.getLogger(getClass());

  // a cached reference to the model for which to evaluate OCL expressions
  private IModel model;

  // a cached reference to the model factory
  private IModelFactory modelFactory;

  // a cached reference to the type resolver
  private ITypeResolver typeResolver;

  /**
   * Checks the parameter and verifies that a model has been set.
   * 
   * @param url the URL with OCL expressions
   * 
   * @throws ParseException if something goes wrong while parsing
   */
  public final void parse(URL url) throws ParseException {
    if (logger.isDebugEnabled()) {
      logger.debug("parse(url=" + url + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // check precondition
    if (url == null) {
      throw new NullArgumentException("url"); //$NON-NLS-1$
    }

    // check invariant
    if (model == null) {
      throw new IllegalStateException(
          "No model has been set for the OCL parser."); //$NON-NLS-1$
    }

    // log an info message
    if (logger.isInfoEnabled()) {
      logger.info(NLS.bind(ParserMessages.XOCLParser_Parsing, url));
    }

    // leave actual parsing to subclasses
    doParse(url);

    if (logger.isDebugEnabled()) {
      logger.debug("parse() - exit"); //$NON-NLS-1$
    }
  }

  /**
   * Primitive method used by the template method {@link #parse(URL)}.
   * Subclasses should override and implement
   * 
   * @param url the URL to load expressions from
   */
  public abstract void doParse(URL url) throws ParseException;

  /**
   * Returns the cached model or <code>null</code>.
   */
  public IModel getModel() {
    return model;
  }

  /**
   * Sets the model to be used by the parser. 
   * 
   * @param model the model, must not be <code>null</code>
   * 
   * @throws NullArgumentException if <code>model</code> is
   *           {@link NullArgumentException}
   */
  public void setModel(IModel model) {

    // precondition check
    if (model == null) {
      throw new NullArgumentException("model"); //$NON-NLS-1$
    }

    // initialize
    this.model = model;

    // reset the model factory and type resolver
    modelFactory = null;
    typeResolver = null;

  }

  /**
   * This implementation does nothing
   */
  public void dispose() {
    // do nothing by default
  }

  /**
   * Returns the {@link IModelFactory model factory} that is associated with the
   * currently set {@link IModel model}. This method lazily gets the model
   * factory and checks that a model has been initialized. If not, an
   * {@link IllegalStateException} is thrown. Subclasses might want to cache the
   * model factory once obtained to improve performance.
   * 
   * @return an <code>IModelFactory</code> instance
   */
  protected IModelFactory getModelFactory() {

    // lazily get the model factory
    if (modelFactory == null) {

      if (model == null) {
        throw new IllegalStateException(
            "No model has been set for this parser."); //$NON-NLS-1$
      }

      // get the model factory from the model
      modelFactory = model.getFactory();

      if (modelFactory == null) {
        throw new IllegalStateException(
            "No model factory found for model '" + model.getDisplayName() + "'."); //$NON-NLS-1$ //$NON-NLS-2$
      }

    }

    return modelFactory;
  }

  /**
   * Returns the {@link ITypeResolver type resolver} that is associated with the
   * currently set {@link IModel model}. This method lazily gets the type
   * resolver and checks that a model has been initialized. If not, an
   * {@link IllegalStateException} is thrown. Subclasses might want to cache the
   * type resolver once obtained to improve performance.
   * 
   * @return an <code>ITypeResolver</code> instance
   */
  protected ITypeResolver getTypeResolver() {

    // lazily get the model factory
    if (typeResolver == null) {

      if (model == null) {
        throw new IllegalStateException(
            "No model has been set for this parser."); //$NON-NLS-1$
      }

      // get the model factory from the model
      typeResolver = model.getTypeResolver();

      if (typeResolver == null) {
        throw new IllegalStateException(
            "No type resolver found for model '" + model.getDisplayName() + "'."); //$NON-NLS-1$ //$NON-NLS-2$
      }

    }

    return typeResolver;
  }

  /**
   * Return a string representation of this parser.
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(
        "model", model).toString(); //$NON-NLS-1$
  }

}
