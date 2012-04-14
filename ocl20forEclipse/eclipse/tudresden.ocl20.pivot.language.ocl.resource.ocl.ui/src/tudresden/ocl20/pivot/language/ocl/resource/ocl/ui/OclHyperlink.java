/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.util.ModelQueryUtility;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.ModelsView;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty;

/**
 * A hyperlink for the proxy elements in source code.
 * @author Lars Schütze implemented linking into ModelBrowser
 */
public class OclHyperlink implements
		org.eclipse.jface.text.hyperlink.IHyperlink {

	private String text;
	private org.eclipse.emf.ecore.EObject linkTarget;
	private org.eclipse.jface.text.IRegion region;
	
	/**
	 * Creates the hyperlink.
	 * 
	 * @param region
	 *          the region of the hyperlink to highlight
	 * @param linkTarget
	 *          the link target where this hyperlink should go to
	 * @param targetText
	 *          the text to specify the target position in the
	 *          <code>linkTarget</code>
	 */
	public OclHyperlink(org.eclipse.jface.text.IRegion region,
			org.eclipse.emf.ecore.EObject linkTarget, String targetText) {

		this.region = region;
		this.linkTarget = linkTarget;
		this.text = targetText;
	}

	public String getHyperlinkText() {

		return text;
	}

	/**
	 * 
	 * @return the length of the hyperlink text
	 */
	public int length() {

		return text.length();
	}

	public String getTypeLabel() {

		return null;
	}

	/**
	 * Opens the resource in <code>linkTarget</code> with the generated editor, if
	 * it supports the file extension of this resource, and tries to jump to the
	 * definition. Otherwise it tries to open the target with the default editor.
	 */
	public void open() {

		if (linkTarget == null) {
			return;
		}
		
		/* Show the ModelBrowser */
		try {
			final IViewPart mbViewPart =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
							.showView(ModelsView.ID);

			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {

					showSelectionInModelBrowser(mbViewPart);
				}
			});
		} catch (PartInitException e) {
			tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclPlugin.logError(
					"Exception while opening hyperlink target.", e);
		}

		/*
		 * org.eclipse.core.resources.IFile file = getIFileFromResource(); if (file
		 * != null) { org.eclipse.ui.IWorkbench workbench =
		 * org.eclipse.ui.PlatformUI.getWorkbench(); org.eclipse.ui.IWorkbenchPage
		 * page = workbench.getActiveWorkbenchWindow().getActivePage(); try {
		 * org.eclipse.ui.IEditorDescriptor desc =
		 * workbench.getEditorRegistry().getDefaultEditor(file.getName()); if (desc
		 * == null) { desc = workbench.getEditorRegistry().findEditor(
		 * "org.eclipse.emf.ecore.presentation.ReflectiveEditorID"); }
		 * org.eclipse.ui.IEditorPart editorPart = page.openEditor(new
		 * org.eclipse.ui.part.FileEditorInput(file), desc.getId()); if (editorPart
		 * instanceof org.eclipse.emf.edit.domain.IEditingDomainProvider) {
		 * org.eclipse.emf.edit.domain.IEditingDomainProvider editingDomainProvider
		 * = (org.eclipse.emf.edit.domain.IEditingDomainProvider) editorPart;
		 * org.eclipse.emf.edit.domain.EditingDomain editingDomain =
		 * editingDomainProvider.getEditingDomain(); org.eclipse.emf.common.util.URI
		 * uri = org.eclipse.emf.ecore.util.EcoreUtil.getURI(linkTarget);
		 * org.eclipse.emf.ecore.EObject originalObject =
		 * editingDomain.getResourceSet().getEObject(uri, true); if
		 * (editingDomainProvider instanceof
		 * org.eclipse.emf.common.ui.viewer.IViewerProvider) {
		 * org.eclipse.emf.common.ui.viewer.IViewerProvider viewerProvider =
		 * (org.eclipse.emf.common.ui.viewer.IViewerProvider) editingDomainProvider;
		 * org.eclipse.jface.viewers.Viewer viewer = viewerProvider.getViewer();
		 * viewer.setSelection(new
		 * org.eclipse.jface.viewers.StructuredSelection(originalObject), true); } }
		 * } catch (org.eclipse.ui.PartInitException e) {
		 * tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclPlugin.logError(
		 * "Exception while opening hyperlink target.", e); } }
		 */
	}

	private org.eclipse.core.resources.IFile getIFileFromResource() {

		org.eclipse.emf.ecore.resource.Resource linkTargetResource =
				linkTarget.eResource();
		org.eclipse.emf.common.util.URI resourceURI = linkTargetResource.getURI();
		if (linkTargetResource.getResourceSet() != null
				&& linkTargetResource.getResourceSet().getURIConverter() != null) {
			resourceURI =
					linkTargetResource.getResourceSet().getURIConverter()
							.normalize(resourceURI);
		}
		if (resourceURI.isPlatformResource()) {
			String platformString = resourceURI.toPlatformString(true);
			if (platformString != null) {
				org.eclipse.core.resources.IWorkspace workspace =
						org.eclipse.core.resources.ResourcesPlugin.getWorkspace();
				org.eclipse.core.resources.IWorkspaceRoot root = workspace.getRoot();
				return root.getFile(new org.eclipse.core.runtime.Path(platformString));
			}
		}
		return null;
	}

	public org.eclipse.jface.text.IRegion getHyperlinkRegion() {

		return region;
	}

	private void showSelectionInModelBrowser(IViewPart mbViewPart) {

		if (mbViewPart == null) {
			return;
		}
		
		mbViewPart.setFocus();
		ISelectionProvider mbSelectionProvider =
				mbViewPart.getViewSite().getSelectionProvider();
		
		/* Since we know that the Model Browser is a TreeViewer */
		if (mbSelectionProvider instanceof TreeViewer) {
			
			/** The Model Browser Viewer */
			TreeViewer mbViewer = (TreeViewer) mbSelectionProvider;
			/** The way in the viewer to the linkTarget */
			List<EObject> typeNamespace = null;

			if (linkTarget instanceof Type) {
				typeNamespace = getAllNestingNamespacesFromType((Type) linkTarget);
			}
			//no else
			
			if(linkTarget instanceof AbstractProperty) {
				typeNamespace = getAllNestingNamespacesFromType(((AbstractProperty) linkTarget).getOwningType());
				typeNamespace.add(((AbstractProperty) linkTarget).getOwningType());
			}
			//no else

			if (linkTarget instanceof Operation) {
				try {
					Collection<Type> activeModelsTypes =
							ModelQueryUtility.getAllTypes(ModelBusPlugin.getModelRegistry()
									.getActiveModel());
					
					for(Type type : activeModelsTypes) {
						if(type.getOwnedOperation().contains((Operation) linkTarget)) {
							typeNamespace = getAllNestingNamespacesFromType(type);
							break;
						}
						//no else
					}
					
				} catch (ModelAccessException e) {
					/* do nothing here */
				}
			}
			//no else

			if (typeNamespace != null && !typeNamespace.isEmpty()) {
				mbViewer.expandToLevel(new TreePath(typeNamespace.toArray()), 1);
				mbViewer.expandToLevel(linkTarget, 1);
				mbViewer.setSelection(new StructuredSelection(linkTarget), true);
				mbViewer.getTree().showSelection();
			}
			//no else
		}
		//could not happen
	}

	private List<EObject> getAllNestingNamespacesFromType(Type type) {

		List<EObject> result = new ArrayList<EObject>();

		Namespace namespace = type.getNamespace();
		Namespace oldNamespace = null;
		while (namespace != null && oldNamespace != namespace) {
			result.add(namespace);
			oldNamespace = namespace;
			namespace = namespace.getNestingNamespace();
		}
		/* Remove root namespace */
		result.remove(result.size() - 1);

		/* Reverse the list so that it is in the right order */
		Collections.reverse(result);
		return result;
	}
}