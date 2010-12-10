/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

import tudresden.ocl20.pivot.model.IModel;

public class OclResource extends org.eclipse.emf.ecore.resource.impl.ResourceImpl implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextResource, IOclResource {
	
	private IModel model;
	
	public void setModel(IModel model) {
		this.model = model;
	}
	
	public IModel getModel() {
		return model;
	}
	
	public class ElementBasedTextDiagnostic implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextDiagnostic {
		
		private final tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclLocationMap locationMap;
		private final org.eclipse.emf.common.util.URI uri;
		private final org.eclipse.emf.ecore.EObject element;
		private final tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclProblem problem;
		
		public ElementBasedTextDiagnostic(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclLocationMap locationMap, org.eclipse.emf.common.util.URI uri, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclProblem problem, org.eclipse.emf.ecore.EObject element) {
			super();
			this.uri = uri;
			this.locationMap = locationMap;
			this.element = element;
			this.problem = problem;
		}
		
		public String getMessage() {
			return problem.getMessage();
		}
		
		public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclProblem getProblem() {
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
			return getMessage() + " at " + getLocation() + " line " + getLine() + ", column " + getColumn();
		}
	}
	
	public class PositionBasedTextDiagnostic implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextDiagnostic {
		
		private final org.eclipse.emf.common.util.URI uri;
		
		private int column;
		private int line;
		private int charStart;
		private int charEnd;
		private tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclProblem problem;
		
		public PositionBasedTextDiagnostic(org.eclipse.emf.common.util.URI uri, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclProblem problem, int column, int line, int charStart, int charEnd) {
			
			super();
			this.uri = uri;
			this.column = column;
			this.line = line;
			this.charStart = charStart;
			this.charEnd = charEnd;
			this.problem = problem;
		}
		
		public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclProblem getProblem() {
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
			return getMessage() + " at " + getLocation() + " line " + getLine() + ", column " + getColumn();
		}
	}
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolverSwitch resolverSwitch;
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclLocationMap locationMap;
	private int proxyCounter = 0;
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextParser parser;
	private java.util.Map<String, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject>> internalURIFragmentMap = new java.util.LinkedHashMap<String, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject>>();
	private java.util.Map<String, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> quickFixMap = new java.util.LinkedHashMap<String, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix>();
	private java.util.Map<?, ?> loadOptions;
	
	public OclResource() {
		super();
		resetLocationMap();
	}
	
	public OclResource(org.eclipse.emf.common.util.URI uri) {
		super(uri);
		resetLocationMap();
	}
	
	protected void doLoad(java.io.InputStream inputStream, java.util.Map<?,?> options) throws java.io.IOException {
		this.loadOptions = options;
		String encoding = null;
		java.io.InputStream actualInputStream = inputStream;
		Object inputStreamPreProcessorProvider = null;
		if (options!=null) {
			inputStreamPreProcessorProvider = options.get(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptions.INPUT_STREAM_PREPROCESSOR_PROVIDER);
		}
		if (inputStreamPreProcessorProvider != null) {
			if (inputStreamPreProcessorProvider instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclInputStreamProcessorProvider) {
				tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclInputStreamProcessorProvider provider = (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclInputStreamProcessorProvider) inputStreamPreProcessorProvider;
				tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclInputStreamProcessor processor = provider.getInputStreamProcessor(inputStream);
				actualInputStream = processor;
				encoding = processor.getOutputEncoding();
			}
		}
		
		parser = getMetaInformation().createParser(actualInputStream, encoding);
		parser.setOptions(options);
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolverSwitch referenceResolverSwitch = getReferenceResolverSwitch();
		referenceResolverSwitch.setOptions(options);
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclParseResult result = parser.parse();
		clearState();
		getContentsInternal().clear();
		org.eclipse.emf.ecore.EObject root = null;
		if (result != null) {
			root = result.getRoot();
			if (root != null) {
				getContentsInternal().add(root);
			}
			java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclCommand<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextResource>> commands = result.getPostParseCommands();
			if (commands != null) {
				for (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclCommand<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextResource>  command : commands) {
					command.execute(this);
				}
			}
		}
		getReferenceResolverSwitch().setOptions(options);
		if (getErrors().isEmpty()) {
			runPostProcessors(options);
			if (root != null) {
				runValidators(root);
			}
		}
	}
	
	public void reload(java.io.InputStream inputStream, java.util.Map<?,?> options) throws java.io.IOException {
		try {
			isLoaded = false;
			java.util.Map<Object, Object> loadOptions = addDefaultLoadOptions(options);
			doLoad(inputStream, loadOptions);
		} catch (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclTerminateParsingException tpe) {
			// do nothing - the resource is left unchanged if this exception is thrown
		}
		isLoaded = true;
	}
	
	public void cancelReload() {
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextParser parserCopy = parser;
		parserCopy.terminate();
	}
	
	protected void doSave(java.io.OutputStream outputStream, java.util.Map<?,?> options) throws java.io.IOException {
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextPrinter printer = getMetaInformation().createPrinter(outputStream, this);
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolverSwitch referenceResolverSwitch = getReferenceResolverSwitch();
		referenceResolverSwitch.setOptions(options);
		for (org.eclipse.emf.ecore.EObject root : getContentsInternal()) {
			printer.print(root);
		}
	}
	
	protected String getSyntaxName() {
		return "ocl";
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolverSwitch getReferenceResolverSwitch() {
		if (resolverSwitch == null) {
			resolverSwitch = new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclReferenceResolverSwitch();
		}
		return resolverSwitch;
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMetaInformation getMetaInformation() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMetaInformation();
	}
	
	protected void resetLocationMap() {
		locationMap = new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLocationMap();
	}
	
	public void addURIFragment(String internalURIFragment, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject> uriFragment) {
		internalURIFragmentMap.put(internalURIFragment, uriFragment);
	}
	
	public <ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject> void registerContextDependentProxy(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclContextDependentURIFragmentFactory<ContainerType, ReferenceType> factory, ContainerType container, org.eclipse.emf.ecore.EReference reference, String id, org.eclipse.emf.ecore.EObject proxyElement, int position) {
		org.eclipse.emf.ecore.InternalEObject proxy = (org.eclipse.emf.ecore.InternalEObject) proxyElement;
		String internalURIFragment = tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclContextDependentURIFragment.INTERNAL_URI_FRAGMENT_PREFIX + (proxyCounter++) + "_" + id;
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclContextDependentURIFragment<?> uriFragment = factory.create(id, container, reference, position, proxy);
		proxy.eSetProxyURI(getURI().appendFragment(internalURIFragment));
		addURIFragment(internalURIFragment, uriFragment);
	}
	
	public org.eclipse.emf.ecore.EObject getEObject(String id) {
		if (internalURIFragmentMap.containsKey(id)) {
			tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject> uriFragment = internalURIFragmentMap.get(id);
			boolean wasResolvedBefore = uriFragment.isResolved();
			tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult<? extends org.eclipse.emf.ecore.EObject> result = uriFragment.resolve();
			if (result == null) {
				// the resolving did call itself
				return null;
			}
			if (!wasResolvedBefore && !result.wasResolved()) {
				attachResolveError(result, uriFragment.getProxy());
				return null;
			} else if (!result.wasResolved()) {
				return null;
			} else {
				org.eclipse.emf.ecore.EObject proxy = uriFragment.getProxy();
				// remove an error that might have been added by an earlier attempt
				removeDiagnostics(proxy, getErrors());
				// remove old warnings and attach new
				removeDiagnostics(proxy, getWarnings());
				attachResolveWarnings(result, proxy);
				tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceMapping<? extends org.eclipse.emf.ecore.EObject> mapping = result.getMappings().iterator().next();
				org.eclipse.emf.ecore.EObject resultElement = getResultElement(uriFragment, mapping, proxy, result.getErrorMessage());
				org.eclipse.emf.ecore.EObject container = uriFragment.getContainer();
				replaceProxyInLayoutAdapters(container, proxy, resultElement);
				return resultElement;
			}
		} else {
			return super.getEObject(id);
		}
	}
	
	protected void replaceProxyInLayoutAdapters(org.eclipse.emf.ecore.EObject container, org.eclipse.emf.ecore.EObject proxy, org.eclipse.emf.ecore.EObject target) {
		for (org.eclipse.emf.common.notify.Adapter adapter : container.eAdapters()) {
			if (adapter instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter) {
				tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter layoutInformationAdapter = (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter) adapter;
				layoutInformationAdapter.replaceProxy(proxy, target);
			}
		}
	}
	
	private org.eclipse.emf.ecore.EObject getResultElement(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject> uriFragment, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceMapping<? extends org.eclipse.emf.ecore.EObject> mapping, org.eclipse.emf.ecore.EObject proxy, final String errorMessage) {
		if (mapping instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclURIMapping<?>) {
			org.eclipse.emf.common.util.URI uri = ((tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclURIMapping<? extends org.eclipse.emf.ecore.EObject>)mapping).getTargetIdentifier();
			if (uri != null) {
				org.eclipse.emf.ecore.EObject result = null;
				try {
					result = this.getResourceSet().getEObject(uri, true);
				} catch (Exception e) {
					// we can catch exceptions here, because EMF will try to resolve again and handle
					// the exception
				}
				if (result == null || result.eIsProxy()) {
					// unable to resolve: attach error
					if (errorMessage == null) {
						assert(false);
					} else {
						addProblem(new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclProblem(errorMessage, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType.UNRESOLVED_REFERENCE, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemSeverity.ERROR), proxy);
					}
				}
				return result;
			}
			return null;
		} else if (mapping instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclElementMapping<?>) {
			org.eclipse.emf.ecore.EObject element = ((tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclElementMapping<? extends org.eclipse.emf.ecore.EObject>)mapping).getTargetElement();
			org.eclipse.emf.ecore.EReference reference = uriFragment.getReference();
			org.eclipse.emf.ecore.EReference oppositeReference = uriFragment.getReference().getEOpposite();
			if (!uriFragment.getReference().isContainment() && oppositeReference != null) {
				if (reference.isMany()) {
					org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList.ManyInverse<org.eclipse.emf.ecore.EObject> list = tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclCastUtil.cast(element.eGet(oppositeReference, false));										// avoids duplicate entries in the reference caused by adding to the
					// oppositeReference
					list.basicAdd(uriFragment.getContainer(),null);
				} else {
					uriFragment.getContainer().eSet(uriFragment.getReference(), element);
				}
			}
			return element;
		} else {
			assert(false);
			return null;
		}
	}
	
	private void removeDiagnostics(org.eclipse.emf.ecore.EObject cause, java.util.List<org.eclipse.emf.ecore.resource.Resource.Diagnostic> diagnostics) {
		// remove all errors/warnings from this resource
		for (org.eclipse.emf.ecore.resource.Resource.Diagnostic errorCand : new org.eclipse.emf.common.util.BasicEList<org.eclipse.emf.ecore.resource.Resource.Diagnostic>(diagnostics)) {
			if (errorCand instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextDiagnostic) {
				if (((tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextDiagnostic) errorCand).wasCausedBy(cause)) {
					diagnostics.remove(errorCand);
					tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMarkerHelper.unmark(this, cause);
				}
			}
		}
	}
	
	private void attachResolveError(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult<?> result, org.eclipse.emf.ecore.EObject proxy) {
		// attach errors to this resource
		assert result != null;
		final String errorMessage = result.getErrorMessage();
		if (errorMessage == null) {
			assert(false);
		} else {
			addProblem(new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclProblem(errorMessage, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType.UNRESOLVED_REFERENCE, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemSeverity.ERROR, result.getQuickFixes()), proxy);
		}
	}
	
	private void attachResolveWarnings(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult<? extends org.eclipse.emf.ecore.EObject> result, org.eclipse.emf.ecore.EObject proxy) {
		assert result != null;
		assert result.wasResolved();
		if (result.wasResolved()) {
			for (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceMapping<? extends org.eclipse.emf.ecore.EObject> mapping : result.getMappings()) {
				final String warningMessage = mapping.getWarning();
				if (warningMessage == null) {
					continue;
				}
				addProblem(new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclProblem(warningMessage, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType.UNRESOLVED_REFERENCE, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemSeverity.WARNING), proxy);
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
	
	protected void runPostProcessors(java.util.Map<?, ?> loadOptions) {
		if (loadOptions == null) {
			return;
		}
		tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMarkerHelper.unmark(this, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType.ANALYSIS_PROBLEM);
		Object resourcePostProcessorProvider = loadOptions.get(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptions.RESOURCE_POSTPROCESSOR_PROVIDER);
		if (resourcePostProcessorProvider != null) {
			if (resourcePostProcessorProvider instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessorProvider) {
				runPostProcessor(((tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessorProvider) resourcePostProcessorProvider).getResourcePostProcessor());
			} else if (resourcePostProcessorProvider instanceof java.util.Collection<?>) {
				java.util.Collection<?> resourcePostProcessorProviderCollection = (java.util.Collection<?>) resourcePostProcessorProvider;
				for (Object processorProvider : resourcePostProcessorProviderCollection) {
					if (processorProvider instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessorProvider) {
						tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessorProvider csProcessorProvider = (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessorProvider) processorProvider;
						tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessor postProcessor = csProcessorProvider.getResourcePostProcessor();
						runPostProcessor(postProcessor);
					}
				}
			}
		}
	}
	
	protected void runPostProcessor(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessor postProcessor) {
		try {
			postProcessor.process(this);
		} catch (Exception e) {
			tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclPlugin.logError("Exception while running a post-processor.", e);
		}
	}
	
	public void load(java.util.Map<?, ?> options) throws java.io.IOException {
		java.util.Map<Object, Object> loadOptions = addDefaultLoadOptions(options);
		super.load(loadOptions);
		org.eclipse.emf.ecore.util.EcoreUtil.resolveAll(this);
	}
	
	public void setURI(org.eclipse.emf.common.util.URI uri) {
		// because of the context dependent proxy resolving it is essential to resolve all
		// proxies before the URI is changed which can cause loss of object identities
		org.eclipse.emf.ecore.util.EcoreUtil.resolveAll(this);
		super.setURI(uri);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclLocationMap getLocationMap() {
		return locationMap;
	}
	
	public void addProblem(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclProblem problem, org.eclipse.emf.ecore.EObject element) {
		ElementBasedTextDiagnostic diagnostic = new ElementBasedTextDiagnostic(locationMap, getURI(), problem, element);
		getDiagnostics(problem.getSeverity()).add(diagnostic);
		if (isMarkerCreationEnabled()) {
			tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMarkerHelper.mark(this, diagnostic);
		}
		java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> quickFixes = problem.getQuickFixes();
		if (quickFixes != null) {
			for (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix quickFix : quickFixes) {
				if (quickFix != null) {
					quickFixMap.put(quickFix.getContextAsString(), quickFix);
				}
			}
		}
	}
	
	public void addProblem(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclProblem problem, int column, int line, int charStart, int charEnd) {
		PositionBasedTextDiagnostic diagnostic = new PositionBasedTextDiagnostic(getURI(), problem, column, line, charStart, charEnd);
		getDiagnostics(problem.getSeverity()).add(diagnostic);
		if (isMarkerCreationEnabled()) {
			tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMarkerHelper.mark(this, diagnostic);
		}
	}
	
	@Deprecated	
	public void addError(String message, org.eclipse.emf.ecore.EObject cause) {
		addError(message, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType.UNKNOWN, cause);
	}
	
	public void addError(String message, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType type, org.eclipse.emf.ecore.EObject cause) {
		addProblem(new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclProblem(message, type, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemSeverity.ERROR), cause);
	}
	
	@Deprecated	
	public void addWarning(String message, org.eclipse.emf.ecore.EObject cause) {
		addWarning(message, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType.UNKNOWN, cause);
	}
	
	public void addWarning(String message, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType type, org.eclipse.emf.ecore.EObject cause) {
		addProblem(new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclProblem(message, type, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemSeverity.WARNING), cause);
	}
	
	private java.util.List<org.eclipse.emf.ecore.resource.Resource.Diagnostic> getDiagnostics(tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemSeverity severity) {
		if (severity == tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemSeverity.ERROR) {
			return getErrors();
		} else {
			return getWarnings();
		}
	}
	
	protected java.util.Map<Object, Object> addDefaultLoadOptions(java.util.Map<?, ?> loadOptions) {
		java.util.Map<Object, Object> loadOptionsCopy = tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclMapUtil.copySafelyToObjectToObjectMap(loadOptions);
		if (org.eclipse.core.runtime.Platform.isRunning()) {
			// find default load option providers
			org.eclipse.core.runtime.IExtensionRegistry extensionRegistry = org.eclipse.core.runtime.Platform.getExtensionRegistry();
			org.eclipse.core.runtime.IConfigurationElement configurationElements[] = extensionRegistry.getConfigurationElementsFor(tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclPlugin.EP_DEFAULT_LOAD_OPTIONS_ID);
			for (org.eclipse.core.runtime.IConfigurationElement element : configurationElements) {
				try {
					tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptionProvider provider = (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptionProvider) element.createExecutableExtension("class");
					final java.util.Map<?, ?> options = provider.getOptions();
					final java.util.Collection<?> keys = options.keySet();
					for (Object key : keys) {
						addLoadOption(loadOptionsCopy, key, options.get(key));
					}
				} catch (org.eclipse.core.runtime.CoreException ce) {
					tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclPlugin.logError("Exception while getting default options.", ce);
				}
			}
		}
		return loadOptionsCopy;
	}
	
	/**
	 * Adds a new key,value pair to the list of options. If there is already an option
	 * with the same key, the two values are collected in a list.
	 */
	private void addLoadOption(java.util.Map<Object, Object> options,Object key, Object value) {
		// check if there is already an option set
		if (options.containsKey(key)) {
			Object currentValue = options.get(key);
			if (currentValue instanceof java.util.List<?>) {
				// if the current value is a list, we add the new value to this list
				java.util.List<?> currentValueAsList = (java.util.List<?>) currentValue;
				java.util.List<Object> currentValueAsObjectList = tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclListUtil.copySafelyToObjectList(currentValueAsList);
				if (value instanceof java.util.Collection<?>) {
					currentValueAsObjectList.addAll((java.util.Collection<?>) value);
				} else {
					currentValueAsObjectList.add(value);
				}
				options.put(key, currentValueAsObjectList);
			} else {
				// if the current value is not a list, we create a fresh list and add both the old
				// (current) and the new value to this list
				java.util.List<Object> newValueList = new java.util.ArrayList<Object>();
				newValueList.add(currentValue);
				if (value instanceof java.util.Collection<?>) {
					newValueList.addAll((java.util.Collection<?>) value);
				} else {
					newValueList.add(value);
				}
				options.put(key, newValueList);
			}
		} else {
			options.put(key, value);
		}
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
		if (isMarkerCreationEnabled()) {
			tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMarkerHelper.unmark(this, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType.UNKNOWN);
			tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMarkerHelper.unmark(this, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType.SYNTAX_ERROR);
			tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMarkerHelper.unmark(this, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType.UNRESOLVED_REFERENCE);
		}
		proxyCounter = 0;
		resolverSwitch = null;
	}
	
	/**
	 * Returns a copy of the contents of this resource wrapped in a list that
	 * propagates changes to the original resource list. Wrapping is required to make
	 * sure that clients which obtain a reference to the list of contents do not
	 * interfere when changing the list.
	 */
	public org.eclipse.emf.common.util.EList<org.eclipse.emf.ecore.EObject> getContents() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclCopiedEObjectInternalEList((org.eclipse.emf.ecore.util.InternalEList<org.eclipse.emf.ecore.EObject>) super.getContents());
	}
	
	/**
	 * Returns the raw contents of this resource.
	 */
	public org.eclipse.emf.common.util.EList<org.eclipse.emf.ecore.EObject> getContentsInternal() {
		return super.getContents();
	}
	
	public org.eclipse.emf.common.util.EList<org.eclipse.emf.ecore.resource.Resource.Diagnostic> getWarnings() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclCopiedEList<org.eclipse.emf.ecore.resource.Resource.Diagnostic>(super.getWarnings());
	}
	
	public org.eclipse.emf.common.util.EList<org.eclipse.emf.ecore.resource.Resource.Diagnostic> getErrors() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclCopiedEList<org.eclipse.emf.ecore.resource.Resource.Diagnostic>(super.getErrors());
	}
	
	@SuppressWarnings("restriction")	
	private void runValidators(org.eclipse.emf.ecore.EObject root) {
		// checking constraints provided by EMF validator classes was disabled
		
		// check EMF validation constraints
		// EMF validation does not work if OSGi is not running
		// The EMF validation framework code throws a NPE if the validation plug-in is not
		// loaded. This is a bug, which is fixed in the Helios release. Nonetheless, we
		// need to catch the exception here.
		if (org.eclipse.core.runtime.Platform.isRunning()) {
			// The EMF validation framework code throws a NPE if the validation plug-in is not
			// loaded. This is a workaround for bug 322079.
			if (org.eclipse.emf.validation.internal.EMFModelValidationPlugin.getPlugin() != null) {
				try {
					org.eclipse.emf.validation.service.ModelValidationService service = org.eclipse.emf.validation.service.ModelValidationService.getInstance();
					org.eclipse.emf.validation.service.IBatchValidator validator = (org.eclipse.emf.validation.service.IBatchValidator) service.newValidator(org.eclipse.emf.validation.model.EvaluationMode.BATCH);
					validator.setIncludeLiveConstraints(true);
					org.eclipse.core.runtime.IStatus status = validator.validate(root);
					addStatus(status, root);
				} catch (Throwable t) {
					tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclPlugin.logError("Exception while checking contraints provided by EMF validator classes.", t);
				}
			}
		}
	}
	
	private void addStatus(org.eclipse.core.runtime.IStatus status, org.eclipse.emf.ecore.EObject root) {
		java.util.List<org.eclipse.emf.ecore.EObject> causes = new java.util.ArrayList<org.eclipse.emf.ecore.EObject>();
		causes.add(root);
		if (status instanceof org.eclipse.emf.validation.model.ConstraintStatus) {
			org.eclipse.emf.validation.model.ConstraintStatus constraintStatus = (org.eclipse.emf.validation.model.ConstraintStatus) status;
			java.util.Set<org.eclipse.emf.ecore.EObject> resultLocus = constraintStatus.getResultLocus();
			causes.clear();
			causes.addAll(resultLocus);
		}
		if (status.getSeverity() == org.eclipse.core.runtime.IStatus.ERROR) {
			for (org.eclipse.emf.ecore.EObject cause : causes) {
				addError(status.getMessage(), cause);
			}
		}
		if (status.getSeverity() == org.eclipse.core.runtime.IStatus.WARNING) {
			for (org.eclipse.emf.ecore.EObject cause : causes) {
				addWarning(status.getMessage(), cause);
			}
		}
		for (org.eclipse.core.runtime.IStatus child : status.getChildren()) {
			addStatus(child, root);
		}
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix getQuickFix(String quickFixContext) {
		return quickFixMap.get(quickFixContext);
	}
	
	public boolean isMarkerCreationEnabled() {
		if (loadOptions == null) {
			return true;
		}
		return !loadOptions.containsKey(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptions.DISABLE_CREATING_MARKERS_FOR_PROBLEMS);
	}
}
