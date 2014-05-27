package org.dresdenocl.examples.ocl22java.wizard;

import java.util.ArrayList;
import java.util.Collection;

import org.dresdenocl.examples.ui.wizard.AbstractExampleWizard;

/**
 * Wizard used to create the simple example project within the current Eclipse
 * workspace.
 * 
 * @author Claas Wilke
 */
public class RoyalAndLoyalExampleWizard extends AbstractExampleWizard {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.examples.ui.wizard.AbstractExampleWizard#
	 * getExamplePlugins()
	 */
	@Override
	protected Collection<String[]> getExamplePlugins() {

		Collection<String[]> result = new ArrayList<String[]>(2);
		result.add(new String[] { "org.dresdenocl.examples.royalandloyal",
				"Dresden OCL - Royal & Loyal UML/Java Example" });
		result.add(new String[] {
				"org.dresdenocl.examples.royalandloyal.ocl22javacode",
				"Dresden OCL - Royal and Loyal Ocl2Java Example" });

		return result;
	}
}
