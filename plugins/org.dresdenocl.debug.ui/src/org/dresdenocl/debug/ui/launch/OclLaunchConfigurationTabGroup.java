/**
 * 
 */
package org.dresdenocl.debug.ui.launch;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

/**
 * @author Lars Schuetze
 * 
 */
public class OclLaunchConfigurationTabGroup extends
		AbstractLaunchConfigurationTabGroup {

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse
	 * .debug.ui.ILaunchConfigurationDialog, java.lang.String)
	 */
	@Override
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {

		setTabs(new ILaunchConfigurationTab[] { new OclLaunchConfigurationMainTab() });
	}

}
