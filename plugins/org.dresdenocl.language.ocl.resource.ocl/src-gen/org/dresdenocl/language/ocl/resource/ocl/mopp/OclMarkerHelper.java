/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreValidator;

/**
 * Helper class to add markers to text files based on EMF's
 * <code>ResourceDiagnostic</code>. If a resource contains
 * <code>org.dresdenocl.language.ocl.resource.ocl.IOclTextDiagnostic</code>s it
 * uses the more precise information of this extended diagnostic type.
 */
public class OclMarkerHelper {
	
	/**
	 * The extension id of the custom marker type that is used by this text resource.
	 */
	public static final String MARKER_TYPE = org.dresdenocl.language.ocl.resource.ocl.mopp.OclPlugin.PLUGIN_ID + ".problem";
	
	/**
	 * The total number of markers per file is restricted with this constant.
	 * Restriction is needed because the performance of Eclipse decreases drastically
	 * if large amounts of markers are added to files.
	 */
	public static int MAXIMUM_MARKERS = 500;
	
	/**
	 * We use a queue to aggregate commands that create or remove markers. This is
	 * basically for performance reasons. Without the queue we would need to create a
	 * job for each marker creation/removal, which creates tons of threads and takes
	 * very long time.
	 */
	private final static MarkerCommandQueue COMMAND_QUEUE = new MarkerCommandQueue();
	
	public static class MutexRule implements ISchedulingRule {
		
		public boolean isConflicting(ISchedulingRule rule) {
			return rule == this;
		}
		
		public boolean contains(ISchedulingRule rule) {
			return rule == this;
		}
	}
	
	private static class MarkerCommandQueue {
		
		private List<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<Object>> commands = new ArrayList<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<Object>>();
		
		private MutexRule schedulingRule = new MutexRule();
		
		public void addCommand(org.dresdenocl.language.ocl.resource.ocl.IOclCommand<Object> command) {
			synchronized(commands) {
				commands.add(command);
				// we only need to schedule a job, if the queue was empty
				if (commands.size() == 1) {
					scheduleRunCommandsJob();
				}
			}
		}
		
		private void scheduleRunCommandsJob() {
			Job job = new Job(org.dresdenocl.language.ocl.resource.ocl.OclResourceBundle.UPDATING_MARKERS_JOB_NAME) {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					runCommands();
					return Status.OK_STATUS;
				}
			};
			job.setRule(schedulingRule);
			job.schedule();
		}
		
		public void runCommands() {
			List<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<Object>> commandsToProcess = new ArrayList<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<Object>>();
			synchronized(commands) {
				commandsToProcess.addAll(commands);
				commands.clear();
			}
			for (org.dresdenocl.language.ocl.resource.ocl.IOclCommand<Object> command : commandsToProcess) {
				command.execute(null);
			}
		}
		
	}
	
	/**
	 * <p>
	 * Creates a marker from the given diagnostics object and attaches the marker to
	 * the resource. Markers are created and removed asynchronously. Thus, they may
	 * not appear when calls to this method return. But, the order of marker additions
	 * and removals is preserved.
	 * </p>
	 * 
	 * @param resource The resource that is the file to mark.
	 * @param diagnostic The diagnostic with information for the marker.
	 */
	public void mark(Resource resource, org.dresdenocl.language.ocl.resource.ocl.IOclTextDiagnostic diagnostic) {
		final IFile file = getFile(resource);
		if (file == null) {
			return;
		}
		createMarkerFromDiagnostic(file, diagnostic);
	}
	
	protected void createMarkerFromDiagnostic(final IFile file, final org.dresdenocl.language.ocl.resource.ocl.IOclTextDiagnostic diagnostic) {
		final org.dresdenocl.language.ocl.resource.ocl.IOclProblem problem = diagnostic.getProblem();
		org.dresdenocl.language.ocl.resource.ocl.OclEProblemType problemType = problem.getType();
		final String markerID = getMarkerID(problemType);
		COMMAND_QUEUE.addCommand(new org.dresdenocl.language.ocl.resource.ocl.IOclCommand<Object>() {
			public boolean execute(Object context) {
				try {
					// if there are too many markers, we do not add new ones
					if (file.findMarkers(markerID, false, IResource.DEPTH_ZERO).length >= MAXIMUM_MARKERS) {
						return true;
					}
					
					IMarker marker = file.createMarker(markerID);
					if (problem.getSeverity() == org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity.ERROR) {
						marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
					} else {
						marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
					}
					marker.setAttribute(IMarker.MESSAGE, diagnostic.getMessage());
					org.dresdenocl.language.ocl.resource.ocl.IOclTextDiagnostic textDiagnostic = (org.dresdenocl.language.ocl.resource.ocl.IOclTextDiagnostic) diagnostic;
					marker.setAttribute(IMarker.LINE_NUMBER, textDiagnostic.getLine());
					marker.setAttribute(IMarker.CHAR_START, textDiagnostic.getCharStart());
					marker.setAttribute(IMarker.CHAR_END, textDiagnostic.getCharEnd() + 1);
					if (diagnostic instanceof org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource.ElementBasedTextDiagnostic) {
						EObject element = ((org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource.ElementBasedTextDiagnostic) diagnostic).getElement();
						String elementURI = getObjectURI(element);
						if (elementURI != null) {
							marker.setAttribute(EcoreValidator.URI_ATTRIBUTE, elementURI);
						}
					}
					Collection<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix> quickFixes = textDiagnostic.getProblem().getQuickFixes();
					Collection<Object> sourceIDs = new ArrayList<Object>();
					if (quickFixes != null) {
						for (org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix quickFix : quickFixes) {
							if (quickFix != null) {
								sourceIDs.add(quickFix.getContextAsString());
							}
						}
					}
					if (!sourceIDs.isEmpty()) {
						marker.setAttribute(IMarker.SOURCE_ID, org.dresdenocl.language.ocl.resource.ocl.util.OclStringUtil.explode(sourceIDs, "|"));
					}
				} catch (CoreException ce) {
					handleException(ce);
				}
				return true;
			}
		});
	}
	
	/**
	 * <p>
	 * Removes all markers from the given resource regardless of their type. Markers
	 * are created and removed asynchronously. Thus, they may not appear when calls to
	 * this method return. But, the order of marker additions and removals is
	 * preserved.
	 * </p>
	 * 
	 * @param resource The resource where to delete markers from
	 */
	public void unmark(Resource resource) {
		for (org.dresdenocl.language.ocl.resource.ocl.OclEProblemType nextType : org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.values()) {
			unmark(resource, nextType);
		}
	}
	
	/**
	 * <p>
	 * Removes all markers of the given type from the given resource. Markers are
	 * created and removed asynchronously. Thus, they may not appear when calls to
	 * this method return. But, the order of marker additions and removals is
	 * preserved.
	 * </p>
	 * 
	 * @param resource The resource where to delete markers from
	 * @param problemType The type of problem to remove
	 */
	public void unmark(Resource resource, org.dresdenocl.language.ocl.resource.ocl.OclEProblemType problemType) {
		final IFile file = getFile(resource);
		if (file == null) {
			return;
		}
		final String markerType = getMarkerID(problemType);
		COMMAND_QUEUE.addCommand(new org.dresdenocl.language.ocl.resource.ocl.IOclCommand<Object>() {
			public boolean execute(Object context) {
				try {
					file.deleteMarkers(markerType, false, IResource.DEPTH_ZERO);
				} catch (CoreException ce) {
					handleException(ce);
				}
				return true;
			}
		});
	}
	
	/**
	 * <p>
	 * Removes all markers that were caused by the given object from the resource.
	 * Markers are created and removed asynchronously. Thus, they may not appear when
	 * calls to this method return. But, the order of marker additions and removals is
	 * preserved.
	 * </p>
	 * 
	 * @param resource The resource where to delete markers from
	 * @param causingObject The cause of the problems to remove
	 */
	public void unmark(Resource resource, final EObject causingObject) {
		final IFile file = getFile(resource);
		if (file == null) {
			return;
		}
		final String markerID = getMarkerID(org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.UNKNOWN);
		final String causingObjectURI = getObjectURI(causingObject);
		if (causingObjectURI == null) {
			return;
		}
		COMMAND_QUEUE.addCommand(new org.dresdenocl.language.ocl.resource.ocl.IOclCommand<Object>() {
			public boolean execute(Object context) {
				try {
					IMarker[] markers = file.findMarkers(markerID, true, IResource.DEPTH_ZERO);
					for (IMarker marker : markers) {
						if (causingObjectURI.equals(marker.getAttribute(EcoreValidator.URI_ATTRIBUTE))) {
							marker.delete();
						}
					}
				} catch (CoreException ce) {
					handleException(ce);
				}
				return true;
			}
		});
	}
	
	/**
	 * Returns the ID of the marker type that is used to indicate problems of the
	 * given type.
	 */
	public String getMarkerID(org.dresdenocl.language.ocl.resource.ocl.OclEProblemType problemType) {
		String markerID = MARKER_TYPE;
		String typeID = problemType.getID();
		if (!"".equals(typeID)) {
			markerID += "." + typeID;
		}
		return markerID;
	}
	
	/**
	 * Tries to determine the file for the given resource. If the platform is not
	 * running, the resource is not a platform resource, or the resource cannot be
	 * found in the workspace, this method returns <code>null</code>.
	 */
	protected IFile getFile(Resource resource) {
		if (resource == null || !Platform.isRunning()) {
			return null;
		}
		String platformString = resource.getURI().toPlatformString(true);
		if (platformString == null) {
			return null;
		}
		IFile file = (IFile) ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
		return file;
	}
	
	/**
	 * Returns an URI that identifies the given object.
	 */
	protected String getObjectURI(EObject object) {
		if (object == null) {
			return null;
		}
		if (object.eIsProxy() && object instanceof BasicEObjectImpl) {
			return ((BasicEObjectImpl) object).eProxyURI().toString();
		}
		Resource eResource = object.eResource();
		if (eResource == null) {
			return null;
		}
		return eResource.getURI().toString() + "#" + eResource.getURIFragment(object);
	}
	
	protected void handleException(CoreException ce) {
		if (ce.getMessage().matches("Marker.*not found.")) {
			// ignore
		}else if (ce.getMessage().matches("Resource.*does not exist.")) {
			// ignore
		} else {
			new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil().logError("Error while removing markers from resource:", ce);
		}
	}
	
	/**
	 * <p>
	 * Removes all markers of the given type from the given resource. Markers are
	 * created and removed asynchronously. Thus, they may not appear when calls to
	 * this method return. But, the order of marker additions and removals is
	 * preserved.
	 * </p>
	 * 
	 * @param resource The resource where to delete markers from
	 * @param markerId The id of the marker type to remove
	 */
	public void removeAllMarkers(final IResource resource, final String markerId) {
		if (resource == null) {
			return;
		}
		COMMAND_QUEUE.addCommand(new org.dresdenocl.language.ocl.resource.ocl.IOclCommand<Object>() {
			public boolean execute(Object context) {
				try {
					resource.deleteMarkers(markerId, false, IResource.DEPTH_ZERO);
				} catch (CoreException ce) {
					handleException(ce);
				}
				return true;
			}
		});
	}
	
	public void createMarker(final IResource resource, final String markerId, final Map<String, Object> markerAttributes) {
		if (resource == null) {
			return;
		}
		
		COMMAND_QUEUE.addCommand(new org.dresdenocl.language.ocl.resource.ocl.IOclCommand<Object>() {
			public boolean execute(Object context) {
				try {
					IMarker marker = resource.createMarker(markerId);
					for (String key : markerAttributes.keySet()) {
						marker.setAttribute(key, markerAttributes.get(key));
					}
					return true;
				} catch (CoreException e) {
					org.dresdenocl.language.ocl.resource.ocl.mopp.OclPlugin.logError("Can't create marker.", e);
					return false;
				}
			}
		});
	}
	
	public void beginDeferMarkerUpdates() {
	}
	
	public void endDeferMarkerUpdates() {
	}
	
	public void runCommands() {
		COMMAND_QUEUE.runCommands();
	}
	
}
