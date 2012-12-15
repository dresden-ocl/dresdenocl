package org.dresdenocl.metamodels.xsd.test;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class XmlSchemaMetamodelTestPlugin implements BundleActivator {

	/** The ID of this plugin. */
	public static final String ID = "org.dresdenocl.metamodels.xsd.test";

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		XmlSchemaMetamodelTestPlugin.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		XmlSchemaMetamodelTestPlugin.context = null;
	}

}
