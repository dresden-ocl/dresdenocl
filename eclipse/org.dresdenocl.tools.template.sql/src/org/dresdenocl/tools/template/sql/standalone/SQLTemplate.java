package org.dresdenocl.tools.template.sql.standalone;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;

import org.dresdenocl.tools.template.ITemplateGroup;
import org.dresdenocl.tools.template.TemplatePlugin;
import org.dresdenocl.tools.template.exception.TemplateException;
import org.dresdenocl.tools.template.sql.SQLTemplatePlugin;

public class SQLTemplate {

	public static void loadTemplates() {

		List<String> streams = new LinkedList<String>();
		String stream = null;
		String stream2 = null;
		ITemplateGroup standardGroup = null;

		try {
			stream = getUrl("/resources/templates/Standard/schema.stg");
			stream2 = getUrl("/resources/templates/Standard/ocl.stg");
			streams.add(stream);
			streams.add(stream2);
			standardGroup = TemplatePlugin.getTemplateGroupRegistry()
					.addDefaultTemplateGroup("Standard(SQL)", null);
			standardGroup.addFiles(streams);
		} catch (TemplateException e) {
			/* FIXME Replace with logging message. */
			e.printStackTrace();
		} catch (IOException e) {
			/* FIXME Replace with logging message. */
			e.printStackTrace();
		}

		try {
			stream = getUrl("/resources/templates/MySQL/schema.stg");
			stream2 = getUrl("/resources/templates/MySQL/ocl.stg");
			streams = new LinkedList<String>();
			streams.add(stream);
			streams.add(stream2);
			ITemplateGroup mysqlGroup;
			mysqlGroup = TemplatePlugin.getTemplateGroupRegistry()
					.addDefaultTemplateGroup("MySQL(SQL)", standardGroup);
			mysqlGroup.addFiles(streams);
		} catch (TemplateException e) {
			/* FIXME Replace with logging message. */
			e.printStackTrace();
		} catch (IOException e) {
			/* FIXME Replace with logging message. */
			e.printStackTrace();
		}

		try {
			stream = getUrl("/resources/templates/MySQL55/schema.stg");
			streams = new LinkedList<String>();
			streams.add(stream);
			ITemplateGroup mysqlGroup;
			mysqlGroup = TemplatePlugin.getTemplateGroupRegistry()
					.addDefaultTemplateGroup("MySQL ab 5.5(SQL)", TemplatePlugin.getTemplateGroupRegistry().getTemplateGroup("MySQL(SQL)"));
			mysqlGroup.addFiles(streams);
		} catch (TemplateException e) {
			/* FIXME Replace with logging message. */
			e.printStackTrace();
		} catch (IOException e) {
			/* FIXME Replace with logging message. */
			e.printStackTrace();
		}
		
		try {
			stream = getUrl("/resources/templates/Oracle8i/schema.stg");
			stream2 = getUrl("/resources/templates/Oracle8i/ocl.stg");
			streams = new LinkedList<String>();
			streams.add(stream);
			streams.add(stream2);
			ITemplateGroup oracleGroup;
			oracleGroup = TemplatePlugin.getTemplateGroupRegistry()
					.addDefaultTemplateGroup("Oracle 8i(SQL)", standardGroup);
			oracleGroup.addFiles(streams);
		} catch (TemplateException e) {
			/* FIXME Replace with logging message. */
			e.printStackTrace();
		} catch (IOException e) {
			/* FIXME Replace with logging message. */
			e.printStackTrace();
		}

		try {
			stream = getUrl("/resources/templates/PostgreSQL81/schema.stg");
			stream2 = getUrl("/resources/templates/PostgreSQL81/ocl.stg");
			streams = new LinkedList<String>();
			streams.add(stream);
			streams.add(stream2);
			ITemplateGroup postgreGroup;
			postgreGroup = TemplatePlugin.getTemplateGroupRegistry()
					.addDefaultTemplateGroup("PostgreSQL 8.1(SQL)",
							standardGroup);
			postgreGroup.addFiles(streams);
		} catch (TemplateException e) {
			/* FIXME Replace with logging message. */
			e.printStackTrace();
		} catch (IOException e) {
			/* FIXME Replace with logging message. */
			e.printStackTrace();
		}
	}

	/**
	 * Helper method to get the URL for a given resource in this plug-in.
	 * 
	 * @param path
	 *            The path of the resource.
	 * @throws IOException
	 */
	private static String getUrl(String path) throws IOException {

		URL fileLocation;
		File file;

		if (Platform.isRunning()) {
			fileLocation = Platform.getBundle(SQLTemplatePlugin.ID)
					.getResource(path);
			fileLocation = FileLocator.toFileURL(fileLocation);
			file = new File(fileLocation.getFile());
		}

		else {
			File testLocation = new File(System.getProperty("DRESDENOCL_LOCATION_TESTS") + SQLTemplatePlugin.ID);
			File eclipseLocation = new File(System.getProperty("DRESDENOCL_LOCATION_ECLIPSE") + SQLTemplatePlugin.ID);
			
			File bundleFile = null;

			
			if (testLocation != null && testLocation.exists() && testLocation.isDirectory()) {
				bundleFile = testLocation;
			} else if (eclipseLocation != null && eclipseLocation.exists() && eclipseLocation.isDirectory()) {
				bundleFile = eclipseLocation;
			}

			if (bundleFile != null)
				file = new File(bundleFile + File.separator + path);

			else
				throw new RuntimeException("Bundle or directory '"
						+ SQLTemplatePlugin.ID + "' was not found.");
		}

		return file.getPath();
	}
}
