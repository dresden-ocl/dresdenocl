/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * $Id$
 */
package tudresden.ocl20.pivot.parser;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.parser.internal.xocl.XOCLParser;

/**
 * The activator class controls the plug-in life cycle
 */
public class ParserPlugin extends Plugin {

  // The plug-in ID
  public static final String ID = "tudresden.ocl20.pivot.parser"; //$NON-NLS-1$

  // The shared instance
  private static ParserPlugin plugin;
  
  // a map of cached parsers
  private Map<IModel,IOclParser> parsers;

  /**
   * The constructor
   */
  public ParserPlugin() {
    // no implementation necessary
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    
    // dipose all parsers
    for (IOclParser parser : parsers.values()) {
      parser.dispose();
    }
    
    parsers.clear();
    
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static ParserPlugin getDefault() {
    return plugin;
  }
  
  
  /**
   * Returns the {@link IOclParser OCL parser} managed by the plugin.
   * 
   * @return an <code>IOclParser</code> instance
   */
  public static IOclParser getParser(IModel model) {
    IOclParser parser;
    
    // check that the plugin has been activated
    if (plugin == null) {
      throw new IllegalStateException("The OCL parser plug-in has not been activated."); //$NON-NLS-1$
    }
    
    // check the argument
    if (model == null) {
      throw new IllegalArgumentException("The model to be used for parsing must not be null."); //$NON-NLS-1$
    }
    
    // lazily create the map of parsers
    if (plugin.parsers == null) {
      plugin.parsers = new HashMap<IModel, IOclParser>();
    }
    
    // try to retrieve a previously created parser
    parser = plugin.parsers.get(model);
    
    // create a new parser if necessary
    if (parser == null) {
      parser = new XOCLParser(model);
      plugin.parsers.put(model,parser);
    }
    
    return parser;
  }

  /**
   * Facade method for the classes in this plugin that hides the dependency from the
   * <code>tudresden.ocl20.logging</code> plugin.
   * 
   * @param clazz the class to return the logger for
   * 
   * @return a log4j <code>Logger</code> instance
   */
  public static Logger getLogger(Class<?> clazz) {
    return LoggingPlugin.getLogManager(plugin).getLogger(clazz);
  }

}
