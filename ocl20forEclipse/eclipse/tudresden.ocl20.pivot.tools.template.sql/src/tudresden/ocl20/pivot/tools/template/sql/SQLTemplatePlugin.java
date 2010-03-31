package tudresden.ocl20.pivot.tools.template.sql;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.tools.template.ITemplateEngine;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;
import tudresden.ocl20.pivot.tools.template.impl.TemplateGroup;

/**
 * The only purpose of this plugin is to initialize the {@link LoggingPlugin
 * logging support}. This enables fine-tuning the log behaviour by providing a
 * custom <code>log4j.properties</code> file. If the Essential OCL
 * implementation is used outside of Eclipse, this plugin is not needed. The
 * classes in this plugin only refer to the log4j {@link Logger} class, never to
 * the custom Eclipse logging support provided by the <code>LoggingPlugin</code>
 * . Thus, logging behaviour can be retained even outside of Eclipse if the
 * log4j libraries are added to the classpath.
 * 
 * @author Bjoern Freitag
 * 
 */
public class SQLTemplatePlugin extends Plugin {

	/**
	 * The id of this plugin
	 */
	public static final String ID = "tudresden.ocl20.pivot.tools.template.sql"; //$NON-NLS-1$

	// The shared instance
	private static SQLTemplatePlugin plugin;

	/**
	 * Creates a new <code>PivotModelPlugin</code>. This is done by the workbench.
	 *  
	 */
	public SQLTemplatePlugin() {
		super();
		plugin = this;
		
		ITemplateEngine templateEngine = TemplatePlugin.getTemplateEngineRegistry().getNewTemplateEngine("StringTemplate");
		URL stream = SQLTemplatePlugin.class.getResource("/resources/templates/Standard.stg");
		URL stream2 = SQLTemplatePlugin.class.getResource("/resources/templates/Standard-inv.stg");
		List<URL> streams = new LinkedList<URL>();
		streams.add(stream); streams.add(stream2);
		ITemplateGroup standardGroup = null;
		try {
			standardGroup = new TemplateGroup("Standard(SQL)", null, templateEngine, streams);
			TemplatePlugin.getTemplateGroupRegistry().addTemplateGroup(standardGroup);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		stream = SQLTemplatePlugin.class.getResource("/resources/templates/MySQL.stg");
		ITemplateGroup mysqlGroup;
		try {
			mysqlGroup = new TemplateGroup("MySQL(SQL)", standardGroup, templateEngine, stream);
			TemplatePlugin.getTemplateGroupRegistry().addTemplateGroup(mysqlGroup);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		stream = SQLTemplatePlugin.class.getResource("/resources/templates/Oracle8i.stg");
		stream2 = SQLTemplatePlugin.class.getResource("/resources/templates/Oracle8i-inv.stg");
		streams = new LinkedList<URL>();
		streams.add(stream); streams.add(stream2);
		ITemplateGroup oracleGroup;
		try {
			oracleGroup = new TemplateGroup("Oracle 8i(SQL)", standardGroup, templateEngine, streams);
			TemplatePlugin.getTemplateGroupRegistry().addTemplateGroup(oracleGroup);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		stream = SQLTemplatePlugin.class.getResource("/resources/templates/PostgreSQL81.stg");
		stream2 = SQLTemplatePlugin.class.getResource("/resources/templates/PostgreSQL81-inv.stg");
		streams = new LinkedList<URL>();
		streams.add(stream); streams.add(stream2);
		ITemplateGroup postgreGroup;
		try {
			postgreGroup = new TemplateGroup("PostgreSQL 8.1(SQL)", standardGroup, templateEngine, streams);
			TemplatePlugin.getTemplateGroupRegistry().addTemplateGroup(postgreGroup);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Overridden to additionally configure the logging support for this plugin.
	 * 
	 * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {

		super.start(context);

		// configure custom logging properties
		LoggingPlugin.configureDefaultLogging(plugin);
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {

		super.stop(context);
	}

	/**
	 * <p>
	 * Facade method for the classes in this plug-in that hides the dependency
	 * from the <code>tudresden.ocl20.logging</code> plug-in.
	 * </p>
	 * 
	 * @param clazz
	 *          The {@link Class} to return the {@link Logger} for.
	 * 
	 * @return A log4j {@link Logger}> instance.
	 * 
	 * @generated NOT
	 */
	public static Logger getLogger(Class<?> clazz) {

		return LoggingPlugin.getLogManager(plugin).getLogger(clazz);
	}

}
