package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import org.eclipse.osgi.util.NLS;

/**
 * Localized String constants for the <code>UML2Plugin</code>.
 */
public class UML2ModelMessages extends NLS {

	private static final String BUNDLE_NAME = "tudresden.ocl20.pivot.metamodels.uML2.internal.model.messages"; //$NON-NLS-1$

	public static String UML2AdapterFactory_CreatingPivotModelAdapter;

	public static String UML2Model_LoadingUML2Model;

	public static String UML2_GetOwningType;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, UML2ModelMessages.class);
	}

	private UML2ModelMessages() {
		// no implementation necessary
	}
}