package org.dresdenocl.logging.appender;

import java.io.IOException;

import org.apache.log4j.Layout;
import org.apache.log4j.RollingFileAppender;
import org.eclipse.core.runtime.IPath;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class is a custom log4j appender that logs to a file in an Eclipse
 * plugin's state location. It can be configured using the standard
 * configuration options available for {@link RollingFileAppender}. The state
 * location can either be provided using one of the overloaded constructors or
 * by calling the {@link #setStateLocation(IPath)} method. The appender won't be
 * {@link #activateOptions() activated} until the state location of the
 * corresponding plugin has been {@link #setStateLocation(IPath) set}. Clients
 * may extend this class to provide specialized behaviour.
 * 
 * @author Matthias Braeuer (based on version by Manoel Marques)
 */
public class PluginLogFileAppender extends RollingFileAppender {

  // the path to the plugin's state location
  private IPath stateLocation;

  // a flag indicating whether we still have to activate the appender
  private boolean activateOptionsPending;

  /**
   * Creates a new PluginLogFileAppender.
   */
  public PluginLogFileAppender() {
    super();
  }

  /**
   * Creates a new PluginLogFileAppender providing the plugin state location, a
   * layout and the file name for the log file.
   * 
   * @param layout the {@link Layout} instance to use
   * @param stateLocation the path to the plug-in state location
   * @param file the file name for the log file
   * 
   * @throws IOException if an I/O error occurs
   */
  public PluginLogFileAppender(Layout layout, IPath stateLocation,
      String fileName) throws IOException {
    this(layout, stateLocation, fileName, true);
  }

  /**
   * Creates a new PluginLogFileAppender with a complete set of options.
   * 
   * @param layout the {@link Layout} instance to use
   * @param stateLocation the path to the plug-in state location
   * @param file the file name for the log file
   * @param append true if file is to be appended
   * 
   * @throws IOException if an I/O error occurs
   */
  public PluginLogFileAppender(Layout layout, IPath stateLocation,
      String fileName, boolean append) throws IOException {
    super(layout, fileName, append);

    // set the state location and activate options
    setStateLocation(stateLocation);
    activateOptions();
  }

  /**
   * Sets the state location. If {@link #activateOptions()} call is pending,
   * translates the file name and calls {@link #activateOptions()}.
   * 
   * @param stateLocation an <code>IPath</code> instance representing the
   *          plug-in state location
   */
  public void setStateLocation(IPath stateLocation) {

    // precondition check
    if (stateLocation == null) {
      throw new IllegalArgumentException("Parameter 'stateLocation' was null."); //$NON-NLS-1$
    }

    this.stateLocation = stateLocation;

    // activate options if necessary
    if (activateOptionsPending) {
      activateOptionsPending = false;
      setFile(getFile());
      activateOptions();
    }
  }

  /**
   * Sets the file name for the log file. Overridden to resolve the file name in
   * relation to the plugin's config directory once the state location has been
   * set.
   * 
   * @param file file name
   * 
   * @see #setStateLocation(IPath)
   */
  @Override
  public void setFile(String file) {

    // state location has not been set yet
    if (stateLocation == null) {
      super.setFile(file);
    }
    else {
      super.setFile(getTranslatedFileName(file));
    }
  }

  // helper method to resolve a file name relative to the plugin's state path
  // any directory path names in the filename are discarded
  protected String getTranslatedFileName(String fileName) {

    // state check
    if (stateLocation == null) {
      throw new IllegalStateException(
          "Plugin state location has not been set yet."); //$NON-NLS-1$
    }

    // precondition check
    if (fileName == null) {
      throw new IllegalArgumentException("The parameter 'fileName' was null."); //$NON-NLS-1$
    }

    // trim whitespace
    fileName = fileName.trim();

    // validity check
    if (fileName.length() == 0) {
      throw new IllegalArgumentException(
          "The parameter 'fileName' consisted only of whitespace."); //$NON-NLS-1$
    }

    // find the last path segment of the file
    int index = fileName.lastIndexOf('/');
    if (index == -1) {
      index = fileName.lastIndexOf('\\');
    }

    // get the file name
    if (index != -1) {
      fileName = fileName.substring(index + 1);
    }

    // append the file name to the state path
    IPath filePath = stateLocation.append(fileName);

    return filePath.toString();
  }

  /**
   * Finishes instance initialization. If state location was not set, set
   * activate as pending and does nothing.
   */
  @Override
  public void activateOptions() {
    if (stateLocation == null) {
      activateOptionsPending = true;
      return;
    }

    super.activateOptions();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(
        "stateLocation", //$NON-NLS-1$
        stateLocation)
        .append("activateOptionsPending", activateOptionsPending).toString(); //$NON-NLS-1$
  }

}
