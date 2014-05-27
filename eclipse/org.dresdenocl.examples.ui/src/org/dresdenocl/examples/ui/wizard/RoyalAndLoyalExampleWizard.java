package org.dresdenocl.examples.ui.wizard;

import java.util.ArrayList;
import java.util.Collection;

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

		Collection<String[]> result = new ArrayList<String[]>(1);
		result.add(new String[] { "org.dresdenocl.examples.royalandloyal",
				"Dresden OCL - Royal & Loyal UML/Java Example" });

		return result;
	}
}
