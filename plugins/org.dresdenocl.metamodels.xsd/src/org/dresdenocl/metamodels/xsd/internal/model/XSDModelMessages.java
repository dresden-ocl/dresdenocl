package org.dresdenocl.metamodels.xsd.internal.model;

import org.eclipse.osgi.util.NLS;

/**
 * Localized String constants for the <code>XSDPlugin</code>.
 */
public class XSDModelMessages extends NLS {

	private static final String BUNDLE_NAME =
			"org.dresdenocl.metamodels.xSD.internal.model.messages"; //$NON-NLS-1$

	public static String XSDAdapterFactory_CreatingPivotModelAdapter;

	public static String XSDModel_LoadingXSDModel;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, XSDModelMessages.class);
	}

	private XSDModelMessages() {

		// no implementation necessary
	}
}