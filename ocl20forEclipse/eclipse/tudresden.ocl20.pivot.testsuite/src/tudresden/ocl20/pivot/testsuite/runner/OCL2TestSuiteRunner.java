package tudresden.ocl20.pivot.testsuite.runner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import tudresden.ocl20.logging.appender.StringBufferAppender;
import tudresden.ocl20.pivot.testsuite.TestSuitePlugin;

/**
 * This is the heart of the <i>Extensible Test Suite</i> of Dresden OCL2 for
 * Eclipse. It can be run as a "JUnit Plug-in Test". Plug-ins that want to
 * register their test cases have to do this by providing an extension to the
 * extension point "tudresden.ocl20.pivot.testsuite".<br>
 * When the {@link OCL2TestSuiteRunner} is started, it collects all registered
 * test suites and executes them.
 * 
 * @author Michael Thiele
 * 
 */
@SuppressWarnings("deprecation")
public class OCL2TestSuiteRunner {

	protected static final String SHOW_WARNINGS =
			"ExtensibleTestSuite_showWarnings";
	private static final String EXECUTABLE_EXTENSION = "testSuite";
	private static final String CONFIGURATION_ELEMENT = "testSuites";
	private static final String TESTSUITE_EXTENSIONPOINT =
			"tudresden.ocl20.pivot.testsuite";

	/**
	 * 
	 * 
	 * @return the test suite containing all gathered tests or test suites
	 * @throws CoreException
	 *           if there is something wrong with the registry
	 */
	public static Test suite() throws CoreException {

		TestSuite allTestSuite = new TestSuite("Dresden OCL2 for Eclipse Tests");

		List<Test> registeredTestSuites = getRegisteredTestSuites();

		// display the found tests and test suites

		Display display = Display.getCurrent();
		if (display == null) {
			display = Display.getDefault();
		}
		final Shell parentShell = display.getActiveShell();
//
//		SelectTestSuitesDialog selectTestSuitesDialog =
//				new SelectTestSuitesDialog(parentShell, registeredTestSuites);
//
//		int result = selectTestSuitesDialog.open();

		Test defaultTest = new TestCase("User aborted Test Suite") {

			@SuppressWarnings("unused")
			public void testFail() {

				fail("User aborted Test Suite");
			}
		};

//		if (result == Dialog.CANCEL) {
//			return defaultTest;
//		}
//		else if (result == Dialog.OK) {

			for (Test test : registeredTestSuites) {
				allTestSuite.addTest(test);
			}

			return new TestSetup(allTestSuite) {

				@Override
				protected void tearDown() {

					String messages = StringBufferAppender.getMessages();

					if (messages.length() == 0)
						return;
					else {
						Preferences preferences =
								TestSuitePlugin.getDefault().getPluginPreferences();
						boolean showWarnings = preferences.getBoolean(SHOW_WARNINGS);
						try {
							preferences.store(new FileOutputStream(TestSuitePlugin
									.getDefault().getStateLocation().append("showWarnings.pref")
									.toFile()), null);
						} catch (FileNotFoundException e) {
							// ignore
						} catch (IllegalStateException e) {
							// ignore
						} catch (IOException e) {
							// ignore
						}

						if (showWarnings)
							MessageDialog.openInformation(parentShell, "Warnings", messages);
					}

				}

			};
//		}

//		return defaultTest;

	}

	/**
	 * Searches the extension registry for registered test (suites) and returns
	 * them.
	 * 
	 * @return the found {@link Test}s.
	 * @throws CoreException
	 *           if there is something wrong with the registry
	 */
	private static List<Test> getRegisteredTestSuites() throws CoreException {

		List<Test> retTestSuites = new LinkedList<Test>();

		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint =
				extensionRegistry.getExtensionPoint(TESTSUITE_EXTENSIONPOINT);

		for (IConfigurationElement configElement : extensionPoint
				.getConfigurationElements()) {

			if (configElement.getName().equals(CONFIGURATION_ELEMENT)) {
				Object testSuite =
						configElement.createExecutableExtension(EXECUTABLE_EXTENSION);
				retTestSuites.add(new JUnit4TestAdapter(testSuite.getClass()));
			}
			// no else.

		}

		Collections.sort(retTestSuites, new Comparator<Test>() {

			public int compare(Test o1, Test o2) {

				return o1.toString().compareTo(o2.toString());
			}

		});

		return retTestSuites;
	}
}
