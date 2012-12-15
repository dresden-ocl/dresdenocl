package org.dresdenocl.language.ocl.resource.ocl.mopp;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EObject;
import org.emftext.commons.layout.LayoutInformation;

import org.dresdenocl.language.ocl.PackageDeclarationCS;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.metamodel.IMetamodel;
import org.dresdenocl.modelbus.ModelBusPlugin;
import org.dresdenocl.modelbus.util.ModelLoaderUtility;

/**
 * 
 * @author Lars Schuetze
 * 
 */
public class OclResource extends
		org.eclipse.emf.ecore.resource.impl.ResourceImpl implements
		org.dresdenocl.language.ocl.resource.ocl.IOclTextResource,
		IOclResource {

	private IModel model = null;

	@Override
	public void setModel(IModel model) {

		if (model != null) {
			this.model = model;
			ModelBusPlugin.getModelRegistry().addModel(model);
			// Set this model as active model, if it is not already so
			if (ModelBusPlugin.getModelRegistry().getActiveModel() != model) {
				ModelBusPlugin.getModelRegistry().setActiveModel(model);
			}
			// no else
		}
		// no else
	}

	@Override
	public IModel getModel(List<EObject> from) {

		if (model != null) {
			return model;
		}

		if (from == null) {
			return null;
		}
		// no else
		for (EObject eFrom : from) {
			if (eFrom instanceof PackageDeclarationCS) {
				PackageDeclarationCS pckgDeclCs = (PackageDeclarationCS) eFrom;
				// Check every PackageDeclarationCS for appropriate LayoutInformation
				for (LayoutInformation li : pckgDeclCs.getLayoutInformation()) {
					String hiddenToken = li.getHiddenTokenText().trim();
					if (hiddenToken == null || hiddenToken.isEmpty()) {
						continue;
					}
					if (hiddenToken.contains("@model{")) {
						String modelFilePath =
								hiddenToken.substring(hiddenToken.indexOf("@model{") + 7,
										hiddenToken.lastIndexOf("}"));
						if (modelFilePath == null || modelFilePath.isEmpty()) {
							return null;
						}
						// change to OS specific separators
						modelFilePath = FilenameUtils.separatorsToSystem(modelFilePath);
						final String extension = FilenameUtils.getExtension(modelFilePath);
						if (extension != null && extension.equals("java")) {
							modelFilePath =
									ModelLoaderUtility
											.getCorrespondingClassFileName(modelFilePath);
						}

						File file = new File(modelFilePath);
						if (!file.isAbsolute()) {
							// make the path absolute if it is not
							String absResourceUrl =
									CommonPlugin.resolve(getURI()).toFileString();
							absResourceUrl =
									"file:"
											+ absResourceUrl.substring(0,
													absResourceUrl.lastIndexOf(File.separator) + 1);
							try {
								URL modelFileUrl =
										new URL(new URL(absResourceUrl), modelFilePath);
								file = new File(modelFileUrl.getFile());
							} catch (Exception e) {
								return null;
							}
						}
						
						if(!file.exists()) {
							return null;
						}
						
						final IMetamodel metamodel =
								ModelLoaderUtility.getMetamodelByExtension(extension);
						if (metamodel != null) {
							IModel model;
							try {
								model = metamodel.getModelProvider().getModel(file);
							} catch (Exception e) {
								return null;
							}
							this.setModel(model);
							return this.model;
						}
					}
					// no else (hiddenToken does not contain the metadata)
				}
			}
			// no else (Other CS elements do not contain needed information)
		}
		// end for
		return null;
	}

	public class ElementBasedTextDiagnostic implements
			org.dresdenocl.language.ocl.resource.ocl.IOclTextDiagnostic {

		private final org.dresdenocl.language.ocl.resource.ocl.IOclLocationMap locationMap;
		private final org.eclipse.emf.common.util.URI uri;
		private final org.eclipse.emf.ecore.EObject element;
		private final org.dresdenocl.language.ocl.resource.ocl.IOclProblem problem;

		public ElementBasedTextDiagnostic(
				org.dresdenocl.language.ocl.resource.ocl.IOclLocationMap locationMap,
				org.eclipse.emf.common.util.URI uri,
				org.dresdenocl.language.ocl.resource.ocl.IOclProblem problem,
				org.eclipse.emf.ecore.EObject element) {

			super();
			this.uri = uri;
			this.locationMap = locationMap;
			this.element = element;
			this.problem = problem;
		}

		public String getMessage() {

			return problem.getMessage();
		}

		public org.dresdenocl.language.ocl.resource.ocl.IOclProblem getProblem() {

			return problem;
		}

		public String getLocation() {

			return uri.toString();
		}

		public int getCharStart() {

			return Math.max(0, locationMap.getCharStart(element));
		}

		public int getCharEnd() {

			return Math.max(0, locationMap.getCharEnd(element));
		}

		public int getColumn() {

			return Math.max(0, locationMap.getColumn(element));
		}

		public int getLine() {

			return Math.max(0, locationMap.getLine(element));
		}

		public org.eclipse.emf.ecore.EObject getElement() {

			return element;
		}

		public boolean wasCausedBy(org.eclipse.emf.ecore.EObject element) {

			if (this.element == null) {
				return false;
			}
			return this.element.equals(element);
		}

		public String toString() {

			return getMessage() + " at " + getLocation() + " line " + getLine()
					+ ", column " + getColumn();
		}
	}

	public class PositionBasedTextDiagnostic implements
			org.dresdenocl.language.ocl.resource.ocl.IOclTextDiagnostic {

		private final org.eclipse.emf.common.util.URI uri;

		private int column;
		private int line;
		private int charStart;
		private int charEnd;
		private org.dresdenocl.language.ocl.resource.ocl.IOclProblem problem;

		public PositionBasedTextDiagnostic(org.eclipse.emf.common.util.URI uri,
				org.dresdenocl.language.ocl.resource.ocl.IOclProblem problem,
				int column, int line, int charStart, int charEnd) {

			super();
			this.uri = uri;
			this.column = column;
			this.line = line;
			this.charStart = charStart;
			this.charEnd = charEnd;
			this.problem = problem;
		}

		public org.dresdenocl.language.ocl.resource.ocl.IOclProblem getProblem() {

			return problem;
		}

		public int getCharStart() {

			return charStart;
		}

		public int getCharEnd() {

			return charEnd;
		}

		public int getColumn() {

			return column;
		}

		public int getLine() {

			return line;
		}

		public String getLocation() {

			return uri.toString();
		}

		public String getMessage() {

			return problem.getMessage();
		}

		public boolean wasCausedBy(org.eclipse.emf.ecore.EObject element) {

			return false;
		}

		public String toString() {

			return getMessage() + " at " + getLocation() + " line " + getLine()
					+ ", column " + getColumn();
		}
	}

	private org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolverSwitch resolverSwitch;
	private org.dresdenocl.language.ocl.resource.ocl.IOclLocationMap locationMap;
	private int proxyCounter = 0;
	private org.dresdenocl.language.ocl.resource.ocl.IOclTextParser parser;
	private org.dresdenocl.language.ocl.resource.ocl.util.OclLayoutUtil layoutUtil =
			new org.dresdenocl.language.ocl.resource.ocl.util.OclLayoutUtil();
	private org.dresdenocl.language.ocl.resource.ocl.mopp.OclMarkerHelper markerHelper;
	private java.util.Map<String, org.dresdenocl.language.ocl.resource.ocl.IOclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject>> internalURIFragmentMap =
			new java.util.LinkedHashMap<String, org.dresdenocl.language.ocl.resource.ocl.IOclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject>>();
	private java.util.Map<String, org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix> quickFixMap =
			new java.util.LinkedHashMap<String, org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix>();
	private java.util.Map<?, ?> loadOptions;

	/**
	 * If a post-processor is currently running, this field holds a reference to
	 * it. This reference is used to terminate post-processing if needed.
	 */
	private org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessor runningPostProcessor;

	/**
	 * A flag (and lock) to indicate whether reloading of the resource shall be
	 * cancelled.
	 */
	private Boolean terminateReload = false;
	private Object terminateReloadLock = new Object();
	private Object loadingLock = new Object();
	private boolean delayNotifications = false;
	private java.util.List<org.eclipse.emf.common.notify.Notification> delayedNotifications =
			new java.util.ArrayList<org.eclipse.emf.common.notify.Notification>();
	private java.io.InputStream latestReloadInputStream = null;
	private java.util.Map<?, ?> latestReloadOptions = null;
	private org.dresdenocl.language.ocl.resource.ocl.util.OclInterruptibleEcoreResolver interruptibleResolver;

	protected org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation metaInformation =
			new org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation();

	public OclResource() {

		super();
		resetLocationMap();
	}

	public OclResource(org.eclipse.emf.common.util.URI uri) {

		super(uri);
		resetLocationMap();
	}

	protected void doLoad(java.io.InputStream inputStream,
			java.util.Map<?, ?> options) throws java.io.IOException {

		synchronized (loadingLock) {
			if (processTerminationRequested()) {
				return;
			}
			this.loadOptions = options;
			delayNotifications = true;
			resetLocationMap();
			String encoding = getEncoding(options);
			java.io.InputStream actualInputStream = inputStream;
			Object inputStreamPreProcessorProvider = null;
			if (options != null) {
				inputStreamPreProcessorProvider =
						options
								.get(org.dresdenocl.language.ocl.resource.ocl.IOclOptions.INPUT_STREAM_PREPROCESSOR_PROVIDER);
			}
			if (inputStreamPreProcessorProvider != null) {
				if (inputStreamPreProcessorProvider instanceof org.dresdenocl.language.ocl.resource.ocl.IOclInputStreamProcessorProvider) {
					org.dresdenocl.language.ocl.resource.ocl.IOclInputStreamProcessorProvider provider =
							(org.dresdenocl.language.ocl.resource.ocl.IOclInputStreamProcessorProvider) inputStreamPreProcessorProvider;
					org.dresdenocl.language.ocl.resource.ocl.mopp.OclInputStreamProcessor processor =
							provider.getInputStreamProcessor(inputStream);
					actualInputStream = processor;
				}
			}

			parser = getMetaInformation().createParser(actualInputStream, encoding);
			parser.setOptions(options);
			org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolverSwitch referenceResolverSwitch =
					getReferenceResolverSwitch();
			referenceResolverSwitch.setOptions(options);
			org.dresdenocl.language.ocl.resource.ocl.IOclParseResult result =
					parser.parse();
			// dispose parser, we don't need it anymore
			parser = null;

			if (processTerminationRequested()) {
				// do nothing if reload was already restarted
				return;
			}

			clearState();
			getContentsInternal().clear();
			org.eclipse.emf.ecore.EObject root = null;
			if (result != null) {
				root = result.getRoot();
				if (root != null) {
					if (isLayoutInformationRecordingEnabled()) {
						layoutUtil.transferAllLayoutInformationToModel(root);
					}
					if (processTerminationRequested()) {
						// the next reload will add new content
						return;
					}
					getContentsInternal().add(root);
				}
				java.util.Collection<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>> commands =
						result.getPostParseCommands();
				if (commands != null) {
					for (org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource> command : commands) {
						command.execute(this);
					}
				}
			}
			getReferenceResolverSwitch().setOptions(options);
			if (getErrors().isEmpty()) {
				if (!runPostProcessors(options)) {
					return;
				}
				if (root != null) {
					runValidators(root);
				}
			}
			notifyDelayed();
		}
	}

	protected boolean processTerminationRequested() {

		if (terminateReload) {
			delayNotifications = false;
			delayedNotifications.clear();
			return true;
		}
		return false;
	}

	protected void notifyDelayed() {

		delayNotifications = false;
		for (org.eclipse.emf.common.notify.Notification delayedNotification : delayedNotifications) {
			super.eNotify(delayedNotification);
		}
		delayedNotifications.clear();
	}

	public void eNotify(org.eclipse.emf.common.notify.Notification notification) {

		if (delayNotifications) {
			delayedNotifications.add(notification);
		}
		else {
			super.eNotify(notification);
		}
	}

	/**
	 * Reloads the contents of this resource from the given stream.
	 */
	public void reload(java.io.InputStream inputStream,
			java.util.Map<?, ?> options) throws java.io.IOException {

		synchronized (terminateReloadLock) {
			latestReloadInputStream = inputStream;
			latestReloadOptions = options;
			if (terminateReload == true) {
				// //reload already requested
			}
			terminateReload = true;
		}
		cancelReload();
		synchronized (loadingLock) {
			synchronized (terminateReloadLock) {
				terminateReload = false;
			}
			isLoaded = false;
			java.util.Map<Object, Object> loadOptions =
					addDefaultLoadOptions(latestReloadOptions);
			try {
				doLoad(latestReloadInputStream, loadOptions);
			} catch (org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException tpe) {
				// do nothing - the resource is left unchanged if this exception
				// is
				// thrown
			}
			resolveAfterParsing();
			isLoaded = true;
		}
	}

	/**
	 * Cancels reloading this resource. The running parser and post processors are
	 * terminated.
	 */
	protected void cancelReload() {

		// Cancel parser
		org.dresdenocl.language.ocl.resource.ocl.IOclTextParser parserCopy =
				parser;
		if (parserCopy != null) {
			parserCopy.terminate();
		}
		// Cancel post processor(s)
		org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessor runningPostProcessorCopy =
				runningPostProcessor;
		if (runningPostProcessorCopy != null) {
			runningPostProcessorCopy.terminate();
		}
		// Cancel reference resolving
		org.dresdenocl.language.ocl.resource.ocl.util.OclInterruptibleEcoreResolver interruptibleResolverCopy =
				interruptibleResolver;
		if (interruptibleResolverCopy != null) {
			interruptibleResolverCopy.terminate();
		}
	}

	protected void doSave(java.io.OutputStream outputStream,
			java.util.Map<?, ?> options) throws java.io.IOException {

		org.dresdenocl.language.ocl.resource.ocl.IOclTextPrinter printer =
				getMetaInformation().createPrinter(outputStream, this);
		org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolverSwitch referenceResolverSwitch =
				getReferenceResolverSwitch();
		printer.setEncoding(getEncoding(options));
		referenceResolverSwitch.setOptions(options);
		for (org.eclipse.emf.ecore.EObject root : getContentsInternal()) {
			if (isLayoutInformationRecordingEnabled()) {
				layoutUtil.transferAllLayoutInformationFromModel(root);
			}
			printer.print(root);
			if (isLayoutInformationRecordingEnabled()) {
				layoutUtil.transferAllLayoutInformationToModel(root);
			}
		}
	}

	protected String getSyntaxName() {

		return "ocl";
	}

	public String getEncoding(java.util.Map<?, ?> options) {

		String encoding = null;
		if (new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil()
				.isEclipsePlatformAvailable()) {
			encoding =
					new org.dresdenocl.language.ocl.resource.ocl.util.OclEclipseProxy()
							.getPlatformResourceEncoding(uri);
		}
		if (options != null) {
			Object encodingOption =
					options
							.get(org.dresdenocl.language.ocl.resource.ocl.IOclOptions.OPTION_ENCODING);
			if (encodingOption != null) {
				encoding = encodingOption.toString();
			}
		}
		return encoding;
	}

	public org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolverSwitch getReferenceResolverSwitch() {

		if (resolverSwitch == null) {
			resolverSwitch =
					new org.dresdenocl.language.ocl.resource.ocl.mopp.OclReferenceResolverSwitch();
		}
		return resolverSwitch;
	}

	public org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation getMetaInformation() {

		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation();
	}

	/**
	 * Clears the location map by replacing it with a new instance.
	 */
	protected void resetLocationMap() {

		if (isLocationMapEnabled()) {
			locationMap =
					new org.dresdenocl.language.ocl.resource.ocl.mopp.OclLocationMap();
		}
		else {
			locationMap =
					new org.dresdenocl.language.ocl.resource.ocl.mopp.OclDevNullLocationMap();
		}
	}

	public void addURIFragment(
			String internalURIFragment,
			org.dresdenocl.language.ocl.resource.ocl.IOclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject> uriFragment) {

		internalURIFragmentMap.put(internalURIFragment, uriFragment);
	}

	public <ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject> void registerContextDependentProxy(
			org.dresdenocl.language.ocl.resource.ocl.IOclContextDependentURIFragmentFactory<ContainerType, ReferenceType> factory,
			ContainerType container, org.eclipse.emf.ecore.EReference reference,
			String id, org.eclipse.emf.ecore.EObject proxyElement, int position) {

		org.eclipse.emf.ecore.InternalEObject proxy =
				(org.eclipse.emf.ecore.InternalEObject) proxyElement;
		String internalURIFragment =
				org.dresdenocl.language.ocl.resource.ocl.IOclContextDependentURIFragment.INTERNAL_URI_FRAGMENT_PREFIX
						+ (proxyCounter++) + "_" + id;
		org.dresdenocl.language.ocl.resource.ocl.IOclContextDependentURIFragment<?> uriFragment =
				factory.create(id, container, reference, position, proxy);
		proxy.eSetProxyURI(getURI().appendFragment(internalURIFragment));
		addURIFragment(internalURIFragment, uriFragment);
	}

	public org.eclipse.emf.ecore.EObject getEObject(String id) {

		if (internalURIFragmentMap.containsKey(id)) {
			org.dresdenocl.language.ocl.resource.ocl.IOclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject> uriFragment =
					internalURIFragmentMap.get(id);
			boolean wasResolvedBefore = uriFragment.isResolved();
			org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveResult<? extends org.eclipse.emf.ecore.EObject> result =
					null;
			// catch and report all Exceptions that occur during proxy resolving
			try {
				result = uriFragment.resolve();
			} catch (Exception e) {

				String message =
						"An expection occured while resolving the proxy for: " + id + ". ("
								+ e.toString() + ")";

				addProblem(
						new org.dresdenocl.language.ocl.resource.ocl.mopp.OclProblem(
								message,
								org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.UNRESOLVED_REFERENCE,
								org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity.ERROR),
						uriFragment.getProxy());
				new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil()
						.logError(message, e);
			}
			if (result == null) {
				// the resolving did call itself
				return null;
			}
			if (!wasResolvedBefore && !result.wasResolved()) {
				attachResolveError(result, uriFragment.getProxy());
				return null;
			}
			else if (!result.wasResolved()) {
				return null;
			}
			else {
				org.eclipse.emf.ecore.EObject proxy = uriFragment.getProxy();
				// remove an error that might have been added by an earlier
				// attempt
				removeDiagnostics(proxy, getErrors());
				// remove old warnings and attach new
				removeDiagnostics(proxy, getWarnings());
				attachResolveWarnings(result, proxy);
				org.dresdenocl.language.ocl.resource.ocl.IOclReferenceMapping<? extends org.eclipse.emf.ecore.EObject> mapping =
						result.getMappings().iterator().next();
				org.eclipse.emf.ecore.EObject resultElement =
						getResultElement(uriFragment, mapping, proxy,
								result.getErrorMessage());
				org.eclipse.emf.ecore.EObject container = uriFragment.getContainer();
				replaceProxyInLayoutAdapters(container, proxy, resultElement);
				return resultElement;
			}
		}
		else {
			return super.getEObject(id);
		}
	}

	protected void replaceProxyInLayoutAdapters(
			org.eclipse.emf.ecore.EObject container,
			org.eclipse.emf.ecore.EObject proxy, org.eclipse.emf.ecore.EObject target) {

		for (org.eclipse.emf.common.notify.Adapter adapter : container.eAdapters()) {
			if (adapter instanceof org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter) {
				org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter layoutInformationAdapter =
						(org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter) adapter;
				layoutInformationAdapter.replaceProxy(proxy, target);
			}
		}
	}

	protected org.eclipse.emf.ecore.EObject getResultElement(
			org.dresdenocl.language.ocl.resource.ocl.IOclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject> uriFragment,
			org.dresdenocl.language.ocl.resource.ocl.IOclReferenceMapping<? extends org.eclipse.emf.ecore.EObject> mapping,
			org.eclipse.emf.ecore.EObject proxy, final String errorMessage) {

		if (mapping instanceof org.dresdenocl.language.ocl.resource.ocl.IOclURIMapping<?>) {
			org.eclipse.emf.common.util.URI uri =
					((org.dresdenocl.language.ocl.resource.ocl.IOclURIMapping<? extends org.eclipse.emf.ecore.EObject>) mapping)
							.getTargetIdentifier();
			if (uri != null) {
				org.eclipse.emf.ecore.EObject result = null;
				try {
					result = this.getResourceSet().getEObject(uri, true);
				} catch (Exception e) {
					// we can catch exceptions here, because EMF will try to
					// resolve again
					// and handle
					// the exception
				}
				if (result == null || result.eIsProxy()) {
					// unable to resolve: attach error
					if (errorMessage == null) {
						assert (false);
					}
					else {
						addProblem(
								new org.dresdenocl.language.ocl.resource.ocl.mopp.OclProblem(
										errorMessage,
										org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.UNRESOLVED_REFERENCE,
										org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity.ERROR),
								proxy);
					}
				}
				return result;
			}
			return null;
		}
		else if (mapping instanceof org.dresdenocl.language.ocl.resource.ocl.IOclElementMapping<?>) {
			org.eclipse.emf.ecore.EObject element =
					((org.dresdenocl.language.ocl.resource.ocl.IOclElementMapping<? extends org.eclipse.emf.ecore.EObject>) mapping)
							.getTargetElement();
			org.eclipse.emf.ecore.EReference reference = uriFragment.getReference();
			org.eclipse.emf.ecore.EReference oppositeReference =
					uriFragment.getReference().getEOpposite();
			if (!uriFragment.getReference().isContainment()
					&& oppositeReference != null) {
				if (reference.isMany()) {
					org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList.ManyInverse<org.eclipse.emf.ecore.EObject> list =
							org.dresdenocl.language.ocl.resource.ocl.util.OclCastUtil
									.cast(element.eGet(oppositeReference, false)); // avoids
					// duplicate
					// entries
					// in
					// the
					// reference
					// caused
					// by
					// adding
					// to
					// the
					// oppositeReference
					list.basicAdd(uriFragment.getContainer(), null);
				}
				else {
					uriFragment.getContainer().eSet(uriFragment.getReference(), element);
				}
			}
			return element;
		}
		else {
			assert (false);
			return null;
		}
	}

	protected void removeDiagnostics(
			org.eclipse.emf.ecore.EObject cause,
			java.util.List<org.eclipse.emf.ecore.resource.Resource.Diagnostic> diagnostics) {

		// remove all errors/warnings from this resource
		for (org.eclipse.emf.ecore.resource.Resource.Diagnostic errorCand : new org.eclipse.emf.common.util.BasicEList<org.eclipse.emf.ecore.resource.Resource.Diagnostic>(
				diagnostics)) {
			if (errorCand instanceof org.dresdenocl.language.ocl.resource.ocl.IOclTextDiagnostic) {
				if (((org.dresdenocl.language.ocl.resource.ocl.IOclTextDiagnostic) errorCand)
						.wasCausedBy(cause)) {
					diagnostics.remove(errorCand);
					unmark(cause);
				}
			}
		}
	}

	protected void attachResolveError(
			org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveResult<?> result,
			org.eclipse.emf.ecore.EObject proxy) {

		// attach errors to this resource
		assert result != null;
		final String errorMessage = result.getErrorMessage();
		if (errorMessage == null) {
			assert (false);
		}
		else {
			addProblem(
					new org.dresdenocl.language.ocl.resource.ocl.mopp.OclProblem(
							errorMessage,
							org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.UNRESOLVED_REFERENCE,
							org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity.ERROR,
							result.getQuickFixes()), proxy);
		}
	}

	protected void attachResolveWarnings(
			org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveResult<? extends org.eclipse.emf.ecore.EObject> result,
			org.eclipse.emf.ecore.EObject proxy) {

		assert result != null;
		assert result.wasResolved();
		if (result.wasResolved()) {
			for (org.dresdenocl.language.ocl.resource.ocl.IOclReferenceMapping<? extends org.eclipse.emf.ecore.EObject> mapping : result
					.getMappings()) {
				final String warningMessage = mapping.getWarning();
				if (warningMessage == null) {
					continue;
				}
				addProblem(
						new org.dresdenocl.language.ocl.resource.ocl.mopp.OclProblem(
								warningMessage,
								org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.UNRESOLVED_REFERENCE,
								org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity.WARNING),
						proxy);
			}
		}
	}

	/**
	 * Extends the super implementation by clearing all information about element
	 * positions.
	 */
	protected void doUnload() {

		super.doUnload();
		clearState();
		loadOptions = null;
	}

	/**
	 * Runs all post processors to process this resource.
	 */
	protected boolean runPostProcessors(java.util.Map<?, ?> loadOptions) {

		unmark(org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.ANALYSIS_PROBLEM);
		if (processTerminationRequested()) {
			return false;
		}
		// first, run the generated post processor
		runPostProcessor(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclResourcePostProcessor());
		if (loadOptions == null) {
			return true;
		}
		// then, run post processors that are registered via the load options
		// extension
		// point
		Object resourcePostProcessorProvider =
				loadOptions
						.get(org.dresdenocl.language.ocl.resource.ocl.IOclOptions.RESOURCE_POSTPROCESSOR_PROVIDER);
		if (resourcePostProcessorProvider != null) {
			if (resourcePostProcessorProvider instanceof org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessorProvider) {
				runPostProcessor(((org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessorProvider) resourcePostProcessorProvider)
						.getResourcePostProcessor());
			}
			else if (resourcePostProcessorProvider instanceof java.util.Collection<?>) {
				java.util.Collection<?> resourcePostProcessorProviderCollection =
						(java.util.Collection<?>) resourcePostProcessorProvider;
				for (Object processorProvider : resourcePostProcessorProviderCollection) {
					if (processTerminationRequested()) {
						return false;
					}
					if (processorProvider instanceof org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessorProvider) {
						org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessorProvider csProcessorProvider =
								(org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessorProvider) processorProvider;
						org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessor postProcessor =
								csProcessorProvider.getResourcePostProcessor();
						runPostProcessor(postProcessor);
					}
				}
			}
		}
		return true;
	}

	/**
	 * Runs the given post processor to process this resource.
	 */
	protected void runPostProcessor(
			org.dresdenocl.language.ocl.resource.ocl.IOclResourcePostProcessor postProcessor) {

		try {
			this.runningPostProcessor = postProcessor;
			postProcessor.process(this);
		} catch (Exception e) {
			new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil()
					.logError("Exception while running a post-processor.", e);
		}
		this.runningPostProcessor = null;
	}

	public void load(java.util.Map<?, ?> options) throws java.io.IOException {

		java.util.Map<Object, Object> loadOptions = addDefaultLoadOptions(options);
		super.load(loadOptions);
		resolveAfterParsing();
	}

	protected void resolveAfterParsing() {

		interruptibleResolver =
				new org.dresdenocl.language.ocl.resource.ocl.util.OclInterruptibleEcoreResolver();
		interruptibleResolver.resolveAll(this);
		interruptibleResolver = null;
	}

	public void setURI(org.eclipse.emf.common.util.URI uri) {

		// because of the context dependent proxy resolving it is essential to
		// resolve all
		// proxies before the URI is changed which can cause loss of object
		// identities
		org.eclipse.emf.ecore.util.EcoreUtil.resolveAll(this);
		super.setURI(uri);
	}

	/**
	 * Returns the location map that contains information about the position of
	 * the contents of this resource in the original textual representation.
	 */
	public org.dresdenocl.language.ocl.resource.ocl.IOclLocationMap getLocationMap() {

		return locationMap;
	}

	public void addProblem(
			org.dresdenocl.language.ocl.resource.ocl.IOclProblem problem,
			org.eclipse.emf.ecore.EObject element) {

		ElementBasedTextDiagnostic diagnostic =
				new ElementBasedTextDiagnostic(locationMap, getURI(), problem, element);
		getDiagnostics(problem.getSeverity()).add(diagnostic);
		mark(diagnostic);
		addQuickFixesToQuickFixMap(problem);
	}

	public void addProblem(
			org.dresdenocl.language.ocl.resource.ocl.IOclProblem problem,
			int column, int line, int charStart, int charEnd) {

		PositionBasedTextDiagnostic diagnostic =
				new PositionBasedTextDiagnostic(getURI(), problem, column, line,
						charStart, charEnd);
		getDiagnostics(problem.getSeverity()).add(diagnostic);
		mark(diagnostic);
		addQuickFixesToQuickFixMap(problem);
	}

	protected void addQuickFixesToQuickFixMap(
			org.dresdenocl.language.ocl.resource.ocl.IOclProblem problem) {

		java.util.Collection<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix> quickFixes =
				problem.getQuickFixes();
		if (quickFixes != null) {
			for (org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix quickFix : quickFixes) {
				if (quickFix != null) {
					quickFixMap.put(quickFix.getContextAsString(), quickFix);
				}
			}
		}
	}

	@Deprecated
	public void addError(String message, org.eclipse.emf.ecore.EObject cause) {

		addError(
				message,
				org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.UNKNOWN,
				cause);
	}

	public void addError(String message,
			org.dresdenocl.language.ocl.resource.ocl.OclEProblemType type,
			org.eclipse.emf.ecore.EObject cause) {

		addProblem(
				new org.dresdenocl.language.ocl.resource.ocl.mopp.OclProblem(
						message,
						type,
						org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity.ERROR),
				cause);
	}

	@Deprecated
	public void addWarning(String message, org.eclipse.emf.ecore.EObject cause) {

		addWarning(
				message,
				org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.UNKNOWN,
				cause);
	}

	public void addWarning(String message,
			org.dresdenocl.language.ocl.resource.ocl.OclEProblemType type,
			org.eclipse.emf.ecore.EObject cause) {

		addProblem(
				new org.dresdenocl.language.ocl.resource.ocl.mopp.OclProblem(
						message,
						type,
						org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity.WARNING),
				cause);
	}

	protected java.util.List<org.eclipse.emf.ecore.resource.Resource.Diagnostic> getDiagnostics(
			org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity severity) {

		if (severity == org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity.ERROR) {
			return getErrors();
		}
		else {
			return getWarnings();
		}
	}

	protected java.util.Map<Object, Object> addDefaultLoadOptions(
			java.util.Map<?, ?> loadOptions) {

		java.util.Map<Object, Object> loadOptionsCopy =
				org.dresdenocl.language.ocl.resource.ocl.util.OclMapUtil
						.copySafelyToObjectToObjectMap(loadOptions);
		// first add static option provider
		loadOptionsCopy
				.putAll(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclOptionProvider()
						.getOptions());

		// second, add dynamic option providers that are registered via
		// extension
		if (new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil()
				.isEclipsePlatformAvailable()) {
			new org.dresdenocl.language.ocl.resource.ocl.util.OclEclipseProxy()
					.getDefaultLoadOptionProviderExtensions(loadOptionsCopy);
		}
		return loadOptionsCopy;
	}

	/**
	 * Extends the super implementation by clearing all information about element
	 * positions.
	 */
	protected void clearState() {

		// clear concrete syntax information
		resetLocationMap();
		internalURIFragmentMap.clear();
		getErrors().clear();
		getWarnings().clear();
		unmark(org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.UNKNOWN);
		unmark(org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.SYNTAX_ERROR);
		unmark(org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.UNRESOLVED_REFERENCE);
		proxyCounter = 0;
		resolverSwitch = null;
	}

	/**
	 * Returns a copy of the contents of this resource wrapped in a list that
	 * propagates changes to the original resource list. Wrapping is required to
	 * make sure that clients which obtain a reference to the list of contents do
	 * not interfere when changing the list.
	 */
	public org.eclipse.emf.common.util.EList<org.eclipse.emf.ecore.EObject> getContents() {

		if (terminateReload) {
			// the contents' state is currently unclear
			return new org.eclipse.emf.common.util.BasicEList<org.eclipse.emf.ecore.EObject>();
		}
		return new org.dresdenocl.language.ocl.resource.ocl.util.OclCopiedEObjectInternalEList(
				(org.eclipse.emf.ecore.util.InternalEList<org.eclipse.emf.ecore.EObject>) super
						.getContents());
	}

	/**
	 * Returns the raw contents of this resource. In contrast to getContents(),
	 * this methods does not return a copy of the content list, but the original
	 * list.
	 */
	public org.eclipse.emf.common.util.EList<org.eclipse.emf.ecore.EObject> getContentsInternal() {

		if (terminateReload) {
			// the contents' state is currently unclear
			return new org.eclipse.emf.common.util.BasicEList<org.eclipse.emf.ecore.EObject>();
		}
		return super.getContents();
	}

	/**
	 * Returns all warnings that are associated with this resource.
	 */
	public org.eclipse.emf.common.util.EList<org.eclipse.emf.ecore.resource.Resource.Diagnostic> getWarnings() {

		if (terminateReload) {
			// the contents' state is currently unclear
			return new org.eclipse.emf.common.util.BasicEList<org.eclipse.emf.ecore.resource.Resource.Diagnostic>();
		}
		return new org.dresdenocl.language.ocl.resource.ocl.util.OclCopiedEList<org.eclipse.emf.ecore.resource.Resource.Diagnostic>(
				super.getWarnings());
	}

	/**
	 * Returns all errors that are associated with this resource.
	 */
	public org.eclipse.emf.common.util.EList<org.eclipse.emf.ecore.resource.Resource.Diagnostic> getErrors() {

		if (terminateReload) {
			// the contents' state is currently unclear
			return new org.eclipse.emf.common.util.BasicEList<org.eclipse.emf.ecore.resource.Resource.Diagnostic>();
		}
		return new org.dresdenocl.language.ocl.resource.ocl.util.OclCopiedEList<org.eclipse.emf.ecore.resource.Resource.Diagnostic>(
				super.getErrors());
	}

	protected void runValidators(org.eclipse.emf.ecore.EObject root) {

		// checking constraints provided by EMF validator classes was disabled
		// by
		// option
		// 'disableEValidators'.

		if (new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil()
				.isEclipsePlatformAvailable()) {
			new org.dresdenocl.language.ocl.resource.ocl.util.OclEclipseProxy()
					.checkEMFValidationConstraints(this, root);
		}
	}

	public org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix getQuickFix(
			String quickFixContext) {

		return quickFixMap.get(quickFixContext);
	}

	protected void mark(
			org.dresdenocl.language.ocl.resource.ocl.IOclTextDiagnostic diagnostic) {

		org.dresdenocl.language.ocl.resource.ocl.mopp.OclMarkerHelper markerHelper =
				getMarkerHelper();
		if (markerHelper != null) {
			markerHelper.mark(this, diagnostic);
		}
	}

	protected void unmark(org.eclipse.emf.ecore.EObject cause) {

		org.dresdenocl.language.ocl.resource.ocl.mopp.OclMarkerHelper markerHelper =
				getMarkerHelper();
		if (markerHelper != null) {
			markerHelper.unmark(this, cause);
		}
	}

	protected void unmark(
			org.dresdenocl.language.ocl.resource.ocl.OclEProblemType analysisProblem) {

		org.dresdenocl.language.ocl.resource.ocl.mopp.OclMarkerHelper markerHelper =
				getMarkerHelper();
		if (markerHelper != null) {
			markerHelper.unmark(this, analysisProblem);
		}
	}

	protected org.dresdenocl.language.ocl.resource.ocl.mopp.OclMarkerHelper getMarkerHelper() {

		if (isMarkerCreationEnabled()
				&& new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil()
						.isEclipsePlatformAvailable()) {
			if (markerHelper == null) {
				markerHelper =
						new org.dresdenocl.language.ocl.resource.ocl.mopp.OclMarkerHelper();
			}
			return markerHelper;
		}
		return null;
	}

	public boolean isMarkerCreationEnabled() {

		if (loadOptions == null) {
			return true;
		}
		return !loadOptions
				.containsKey(org.dresdenocl.language.ocl.resource.ocl.IOclOptions.DISABLE_CREATING_MARKERS_FOR_PROBLEMS);
	}

	protected boolean isLocationMapEnabled() {

		if (loadOptions == null) {
			return true;
		}
		return !loadOptions
				.containsKey(org.dresdenocl.language.ocl.resource.ocl.IOclOptions.DISABLE_LOCATION_MAP);
	}

	protected boolean isLayoutInformationRecordingEnabled() {

		if (loadOptions == null) {
			return true;
		}
		return !loadOptions
				.containsKey(org.dresdenocl.language.ocl.resource.ocl.IOclOptions.DISABLE_LAYOUT_INFORMATION_RECORDING);
	}

}
