/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class OclNature implements IProjectNature {
	
	public static final String NATURE_ID = "org.dresdenocl.language.ocl.resource.ocl.nature";
	
	private IProject project;
	
	/**
	 * the IDs of all builders, IDs of additional builders can be added here
	 */
	public final static String[] BUILDER_IDS = {org.dresdenocl.language.ocl.resource.ocl.mopp.OclBuilderAdapter.BUILDER_ID};
	
	public static void activate(IProject project) {
		try {
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();
			
			for (int i = 0; i < natures.length; ++i) {
				if (NATURE_ID.equals(natures[i])) {
					// already active
					return;
				}
			}
			// Add the nature
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = NATURE_ID;
			description.setNatureIds(newNatures);
			project.setDescription(description, null);
		} catch (CoreException e) {
		}
	}
	
	public static void deactivate(IProject project) {
		try {
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();
			
			for (int i = 0; i < natures.length; ++i) {
				if (NATURE_ID.equals(natures[i])) {
					// Remove the nature
					String[] newNatures = new String[natures.length - 1];
					System.arraycopy(natures, 0, newNatures, 0, i);
					System.arraycopy(natures, i + 1, newNatures, i, natures.length - i - 1);
					description.setNatureIds(newNatures);
					project.setDescription(description, null);
					return;
				}
			}
		} catch (CoreException e) {
		}
	}
	
	public static boolean hasNature(IProject project) {
		try {
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();
			for (int i = 0; i < natures.length; ++i) {
				if (NATURE_ID.equals(natures[i])) {
					return true;
				}
			}
		} catch (CoreException e) {
		}
		return false;
	}
	
	public void configure() throws CoreException {
		IProjectDescription desc = project.getDescription();
		ICommand[] commands = desc.getBuildSpec();
		
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(org.dresdenocl.language.ocl.resource.ocl.mopp.OclBuilderAdapter.BUILDER_ID)) {
				return;
			}
		}
		ICommand[] newCommands = commands;
		outer: for (int j = 0; j < BUILDER_IDS.length; j++) {
			for (int i = 0; i < commands.length; ++i) {
				if (commands[i].getBuilderName().equals(BUILDER_IDS[j])) {
					continue outer;
				}
			}
			ICommand[] tempCommands = new ICommand[newCommands.length + 1];
			System.arraycopy(newCommands, 0, tempCommands, 0, newCommands.length);
			ICommand command = desc.newCommand();
			command.setBuilderName(BUILDER_IDS[j]);
			tempCommands[tempCommands.length - 1] = command;
			newCommands = tempCommands;
		}
		if (newCommands != commands) {
			desc.setBuildSpec(newCommands);
			project.setDescription(desc, null);
		}
	}
	
	public void deconfigure() throws CoreException {
		IProjectDescription description = getProject().getDescription();
		ICommand[] commands = description.getBuildSpec();
		ICommand[] newCommands = commands;
		for (int j = 0; j < BUILDER_IDS.length; j++) {
			for (int i = 0; i < newCommands.length; ++i) {
				if (newCommands[i].getBuilderName().equals(BUILDER_IDS[j])) {
					ICommand[] tempCommands = new ICommand[newCommands.length - 1];
					System.arraycopy(newCommands, 0, tempCommands, 0, i);
					System.arraycopy(newCommands, i + 1, tempCommands, i, newCommands.length - i - 1);
					newCommands = tempCommands;
					break;
				}
			}
		}
		if (newCommands != commands) {
			description.setBuildSpec(newCommands);
		}
	}
	
	public IProject getProject() {
		return project;
	}
	
	public void setProject(IProject project) {
		this.project = project;
	}
	
}
