package org.dresdenocl.examples.ui.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.ui.wizard.AbstractExampleInstallerWizard;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.INewWizard;

/**
 * Wizard used to create the simple example project within the current Eclipse
 * workspace.
 * 
 * @author Claas Wilke
 */
public abstract class AbstractExampleWizard extends
		AbstractExampleInstallerWizard implements INewWizard {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.ui.wizard.AbstractExampleInstallerWizard#performFinish
	 * ()
	 */
	@Override
	public boolean performFinish() {

		super.performFinish();
		/* Returns always true to suppress the unexpected not closing wizard. */
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.ui.wizard.AbstractExampleInstallerWizard#
	 * getProjectDescriptors()
	 */
	@Override
	protected List<ProjectDescriptor> getProjectDescriptors() {

		Collection<String[]> plugins = this.getExamplePlugins();

		List<ProjectDescriptor> result = new ArrayList<AbstractExampleInstallerWizard.ProjectDescriptor>(
				plugins.size());

		for (String[] plugin : plugins) {

			if (plugin.length > 0) {
				ProjectDescriptor descriptor = new ProjectDescriptor();
				descriptor.setName(plugin[0]);

				URI contentURI = URI.createPlatformPluginURI(plugin[0] + "/",
						true);
				descriptor.setContentURI(contentURI);

				if (plugin.length > 1)
					descriptor.setDescription(plugin[1]);
				// no else.

				result.add(descriptor);
			}
			// no else.
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.ui.wizard.AbstractExampleInstallerWizard#
	 * getFilesToOpen()
	 */
	@Override
	protected List<FileToOpen> getFilesToOpen() {
		/* No files should be open after the wizard finished. */
		return null;
	}

	/**
	 * Returns a {@link Collection} containing {@link String} arrays consisting
	 * of (1) the id of the plug-in containing the example files and (2)
	 * optionally a description of the plug-ins content.
	 * 
	 * @return A {@link Collection} describing the plug-ins being part of this
	 *         example.
	 */
	protected abstract Collection<String[]> getExamplePlugins();
}
