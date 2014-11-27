grammar Ocl;

options {
	superClass = OclANTLRParserBase;
	backtrack = true;
	memoize = true;
}

@lexer::header {
	package org.dresdenocl.language.ocl.resource.ocl.mopp;
	
	import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime3_4_0.ANTLRStringStream;
import org.antlr.runtime3_4_0.RecognitionException;
}

@lexer::members {
	public List<RecognitionException> lexerExceptions  = new ArrayList<RecognitionException>();
	public List<Integer> lexerExceptionPositions = new ArrayList<Integer>();
	
	public void reportError(RecognitionException e) {
		lexerExceptions.add(e);
		lexerExceptionPositions.add(((ANTLRStringStream) input).index());
	}
}
@header{
	package org.dresdenocl.language.ocl.resource.ocl.mopp;
	
	import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.antlr.runtime3_4_0.ANTLRInputStream;
import org.antlr.runtime3_4_0.BitSet;
import org.antlr.runtime3_4_0.CommonToken;
import org.antlr.runtime3_4_0.CommonTokenStream;
import org.antlr.runtime3_4_0.IntStream;
import org.antlr.runtime3_4_0.Lexer;
import org.antlr.runtime3_4_0.RecognitionException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
}

@members{
	private org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolverFactory tokenResolverFactory = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenResolverFactory();
	
	/**
	 * the index of the last token that was handled by collectHiddenTokens()
	 */
	private int lastPosition;
	
	/**
	 * A flag that indicates whether the parser should remember all expected elements.
	 * This flag is set to true when using the parse for code completion. Otherwise it
	 * is set to false.
	 */
	private boolean rememberExpectedElements = false;
	
	private Object parseToIndexTypeObject;
	private int lastTokenIndex = 0;
	
	/**
	 * A list of expected elements the were collected while parsing the input stream.
	 * This list is only filled if <code>rememberExpectedElements</code> is set to
	 * true.
	 */
	private List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal> expectedElements = new ArrayList<org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal>();
	
	private int mismatchedTokenRecoveryTries = 0;
	/**
	 * A helper list to allow a lexer to pass errors to its parser
	 */
	protected List<RecognitionException> lexerExceptions = Collections.synchronizedList(new ArrayList<RecognitionException>());
	
	/**
	 * Another helper list to allow a lexer to pass positions of errors to its parser
	 */
	protected List<Integer> lexerExceptionPositions = Collections.synchronizedList(new ArrayList<Integer>());
	
	/**
	 * A stack for incomplete objects. This stack is used filled when the parser is
	 * used for code completion. Whenever the parser starts to read an object it is
	 * pushed on the stack. Once the element was parser completely it is popped from
	 * the stack.
	 */
	List<EObject> incompleteObjects = new ArrayList<EObject>();
	
	private int stopIncludingHiddenTokens;
	private int stopExcludingHiddenTokens;
	private int tokenIndexOfLastCompleteElement;
	
	private int expectedElementsIndexOfLastCompleteElement;
	
	/**
	 * The offset indicating the cursor position when the parser is used for code
	 * completion by calling parseToExpectedElements().
	 */
	private int cursorOffset;
	
	/**
	 * The offset of the first hidden token of the last expected element. This offset
	 * is used to discard expected elements, which are not needed for code completion.
	 */
	private int lastStartIncludingHidden;
	
	private org.dresdenocl.language.ocl.resource.ocl.IOclLocationMap locationMap;
	
	private org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxErrorMessageConverter syntaxErrorMessageConverter = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxErrorMessageConverter(tokenNames);
	
	@Override
	public void reportError(RecognitionException re) {
		addErrorToResource(syntaxErrorMessageConverter.translateParseError(re));
	}
	
	protected void addErrorToResource(final String errorMessage, final int column, final int line, final int startIndex, final int stopIndex) {
		postParseCommands.add(new org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>() {
			public boolean execute(org.dresdenocl.language.ocl.resource.ocl.IOclTextResource resource) {
				if (resource == null) {
					// the resource can be null if the parser is used for code completion
					return true;
				}
				resource.addProblem(new org.dresdenocl.language.ocl.resource.ocl.IOclProblem() {
					public org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity getSeverity() {
						return org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity.ERROR;
					}
					public org.dresdenocl.language.ocl.resource.ocl.OclEProblemType getType() {
						return org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.SYNTAX_ERROR;
					}
					public String getMessage() {
						return errorMessage;
					}
					public Collection<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix> getQuickFixes() {
						return null;
					}
				}, column, line, startIndex, stopIndex);
				return true;
			}
		});
	}
	
	protected void addErrorToResource(org.dresdenocl.language.ocl.resource.ocl.mopp.OclLocalizedMessage message) {
		if (message == null) {
			return;
		}
		addErrorToResource(message.getMessage(), message.getColumn(), message.getLine(), message.getCharStart(), message.getCharEnd());
	}
	
	public void addExpectedElement(EClass eClass, int[] ids) {
		if (!this.rememberExpectedElements) {
			return;
		}
		int terminalID = ids[0];
		int followSetID = ids[1];
		org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement terminal = org.dresdenocl.language.ocl.resource.ocl.grammar.OclFollowSetProvider.TERMINALS[terminalID];
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[] containmentFeatures = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[ids.length - 2];
		for (int i = 2; i < ids.length; i++) {
			containmentFeatures[i - 2] = org.dresdenocl.language.ocl.resource.ocl.grammar.OclFollowSetProvider.LINKS[ids[i]];
		}
		org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainmentTrace containmentTrace = new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainmentTrace(eClass, containmentFeatures);
		EObject container = getLastIncompleteElement();
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal expectedElement = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal(container, terminal, followSetID, containmentTrace);
		setPosition(expectedElement, input.index());
		int startIncludingHiddenTokens = expectedElement.getStartIncludingHiddenTokens();
		if (lastStartIncludingHidden >= 0 && lastStartIncludingHidden < startIncludingHiddenTokens && cursorOffset > startIncludingHiddenTokens) {
			// clear list of expected elements
			this.expectedElements.clear();
			this.expectedElementsIndexOfLastCompleteElement = 0;
		}
		lastStartIncludingHidden = startIncludingHiddenTokens;
		this.expectedElements.add(expectedElement);
	}
	
	protected void collectHiddenTokens(EObject element) {
	}
	
	protected void copyLocalizationInfos(final EObject source, final EObject target) {
		if (disableLocationMap) {
			return;
		}
		final org.dresdenocl.language.ocl.resource.ocl.IOclLocationMap locationMap = this.locationMap;
		if (locationMap == null) {
			// the locationMap can be null if the parser is used for code completion
			return;
		}
		postParseCommands.add(new org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>() {
			public boolean execute(org.dresdenocl.language.ocl.resource.ocl.IOclTextResource resource) {
				locationMap.setCharStart(target, locationMap.getCharStart(source));
				locationMap.setCharEnd(target, locationMap.getCharEnd(source));
				locationMap.setColumn(target, locationMap.getColumn(source));
				locationMap.setLine(target, locationMap.getLine(source));
				return true;
			}
		});
	}
	
	protected void copyLocalizationInfos(final CommonToken source, final EObject target) {
		if (disableLocationMap) {
			return;
		}
		final org.dresdenocl.language.ocl.resource.ocl.IOclLocationMap locationMap = this.locationMap;
		if (locationMap == null) {
			// the locationMap can be null if the parser is used for code completion
			return;
		}
		postParseCommands.add(new org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>() {
			public boolean execute(org.dresdenocl.language.ocl.resource.ocl.IOclTextResource resource) {
				if (source == null) {
					return true;
				}
				locationMap.setCharStart(target, source.getStartIndex());
				locationMap.setCharEnd(target, source.getStopIndex());
				locationMap.setColumn(target, source.getCharPositionInLine());
				locationMap.setLine(target, source.getLine());
				return true;
			}
		});
	}
	
	/**
	 * Sets the end character index and the last line for the given object in the
	 * location map.
	 */
	protected void setLocalizationEnd(Collection<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>> postParseCommands , final EObject object, final int endChar, final int endLine) {
		if (disableLocationMap) {
			return;
		}
		final org.dresdenocl.language.ocl.resource.ocl.IOclLocationMap locationMap = this.locationMap;
		if (locationMap == null) {
			// the locationMap can be null if the parser is used for code completion
			return;
		}
		postParseCommands.add(new org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>() {
			public boolean execute(org.dresdenocl.language.ocl.resource.ocl.IOclTextResource resource) {
				locationMap.setCharEnd(object, endChar);
				locationMap.setLine(object, endLine);
				return true;
			}
		});
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclTextParser createInstance(InputStream actualInputStream, String encoding) {
		try {
			if (encoding == null) {
				return new OclParser(new CommonTokenStream(new OclLexer(new ANTLRInputStream(actualInputStream))));
			} else {
				return new OclParser(new CommonTokenStream(new OclLexer(new ANTLRInputStream(actualInputStream, encoding))));
			}
		} catch (IOException e) {
			new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil().logError("Error while creating parser.", e);
			return null;
		}
	}
	
	/**
	 * This default constructor is only used to call createInstance() on it.
	 */
	public OclParser() {
		super(null);
	}
	
	protected EObject doParse() throws RecognitionException {
		this.lastPosition = 0;
		// required because the lexer class can not be subclassed
		((OclLexer) getTokenStream().getTokenSource()).lexerExceptions = lexerExceptions;
		((OclLexer) getTokenStream().getTokenSource()).lexerExceptionPositions = lexerExceptionPositions;
		Object typeObject = getTypeObject();
		if (typeObject == null) {
			return start();
		} else if (typeObject instanceof EClass) {
			EClass type = (EClass) typeObject;
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.SimpleNameCS.class) {
				return parse_org_dresdenocl_language_ocl_SimpleNameCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.PackageDeclarationWithNamespaceCS.class) {
				return parse_org_dresdenocl_language_ocl_PackageDeclarationWithNamespaceCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS.class) {
				return parse_org_dresdenocl_language_ocl_PackageDeclarationNestedNamespaceCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.PackageDeclarationWithoutNamespaceCS.class) {
				return parse_org_dresdenocl_language_ocl_PackageDeclarationWithoutNamespaceCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.OperationContextDeclarationCS.class) {
				return parse_org_dresdenocl_language_ocl_OperationContextDeclarationCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.AttributeContextDeclarationCS.class) {
				return parse_org_dresdenocl_language_ocl_AttributeContextDeclarationCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.ClassifierContextDeclarationCS.class) {
				return parse_org_dresdenocl_language_ocl_ClassifierContextDeclarationCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.InitValueCS.class) {
				return parse_org_dresdenocl_language_ocl_InitValueCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.DeriveValueCS.class) {
				return parse_org_dresdenocl_language_ocl_DeriveValueCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.InvariantExpCS.class) {
				return parse_org_dresdenocl_language_ocl_InvariantExpCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.DefinitionExpCS.class) {
				return parse_org_dresdenocl_language_ocl_DefinitionExpCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.DefinitionExpPropertyCS.class) {
				return parse_org_dresdenocl_language_ocl_DefinitionExpPropertyCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.DefinitionExpOperationCS.class) {
				return parse_org_dresdenocl_language_ocl_DefinitionExpOperationCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.PreConditionDeclarationCS.class) {
				return parse_org_dresdenocl_language_ocl_PreConditionDeclarationCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.PostConditionDeclarationCS.class) {
				return parse_org_dresdenocl_language_ocl_PostConditionDeclarationCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.BodyDeclarationCS.class) {
				return parse_org_dresdenocl_language_ocl_BodyDeclarationCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.OperationDefinitionInContextCS.class) {
				return parse_org_dresdenocl_language_ocl_OperationDefinitionInContextCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.OperationDefinitionInDefCS.class) {
				return parse_org_dresdenocl_language_ocl_OperationDefinitionInDefCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.ParameterCS.class) {
				return parse_org_dresdenocl_language_ocl_ParameterCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.ImplicitOperationCallCS.class) {
				return parse_org_dresdenocl_language_ocl_ImplicitOperationCallCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.ImplicitPropertyCallCS.class) {
				return parse_org_dresdenocl_language_ocl_ImplicitPropertyCallCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.IteratorExpCS.class) {
				return parse_org_dresdenocl_language_ocl_IteratorExpCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.IterateExpCS.class) {
				return parse_org_dresdenocl_language_ocl_IterateExpCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.IteratorExpVariableCS.class) {
				return parse_org_dresdenocl_language_ocl_IteratorExpVariableCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.TupleTypeCS.class) {
				return parse_org_dresdenocl_language_ocl_TupleTypeCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.CollectionTypeIdentifierCS.class) {
				return parse_org_dresdenocl_language_ocl_CollectionTypeIdentifierCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.TypeModelElementCS.class) {
				return parse_org_dresdenocl_language_ocl_TypeModelElementCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS.class) {
				return parse_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.VariableDeclarationWithoutInitListCS.class) {
				return parse_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitListCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.VariableDeclarationWithInitCS.class) {
				return parse_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS.class) {
				return parse_org_dresdenocl_language_ocl_VariableDeclarationWithInitListCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.PathNameSimpleCS.class) {
				return parse_org_dresdenocl_language_ocl_PathNameSimpleCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.PathNamePathCS.class) {
				return parse_org_dresdenocl_language_ocl_PathNamePathCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.NamedElementCS.class) {
				return parse_org_dresdenocl_language_ocl_NamedElementCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.CollectionRangeCS.class) {
				return parse_org_dresdenocl_language_ocl_CollectionRangeCS();
			}
			if (type.getInstanceClass() == org.dresdenocl.language.ocl.CollectionLiteralPartsOclExpCS.class) {
				return parse_org_dresdenocl_language_ocl_CollectionLiteralPartsOclExpCS();
			}
		}
		throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclUnexpectedContentTypeException(typeObject);
	}
	
	public int getMismatchedTokenRecoveryTries() {
		return mismatchedTokenRecoveryTries;
	}
	
	public Object getMissingSymbol(IntStream arg0, RecognitionException arg1, int arg2, BitSet arg3) {
		mismatchedTokenRecoveryTries++;
		return super.getMissingSymbol(arg0, arg1, arg2, arg3);
	}
	
	public Object getParseToIndexTypeObject() {
		return parseToIndexTypeObject;
	}
	
	protected Object getTypeObject() {
		Object typeObject = getParseToIndexTypeObject();
		if (typeObject != null) {
			return typeObject;
		}
		Map<?,?> options = getOptions();
		if (options != null) {
			typeObject = options.get(org.dresdenocl.language.ocl.resource.ocl.IOclOptions.RESOURCE_CONTENT_TYPE);
		}
		return typeObject;
	}
	
	/**
	 * Implementation that calls {@link #doParse()} and handles the thrown
	 * RecognitionExceptions.
	 */
	public org.dresdenocl.language.ocl.resource.ocl.IOclParseResult parse() {
		// Reset parser state
		terminateParsing = false;
		postParseCommands = new ArrayList<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>>();
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclParseResult parseResult = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclParseResult();
		if (disableLocationMap) {
			locationMap = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclDevNullLocationMap();
		} else {
			locationMap = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclLocationMap();
		}
		// Run parser
		try {
			EObject result =  doParse();
			if (lexerExceptions.isEmpty()) {
				parseResult.setRoot(result);
				parseResult.setLocationMap(locationMap);
			}
		} catch (RecognitionException re) {
			addErrorToResource(syntaxErrorMessageConverter.translateParseError(re));
		} catch (IllegalArgumentException iae) {
			if ("The 'no null' constraint is violated".equals(iae.getMessage())) {
				// can be caused if a null is set on EMF models where not allowed. this will just
				// happen if other errors occurred before
			} else {
				iae.printStackTrace();
			}
		}
		for (RecognitionException re : lexerExceptions) {
			addErrorToResource(syntaxErrorMessageConverter.translateLexicalError(re, lexerExceptions, lexerExceptionPositions));
		}
		parseResult.getPostParseCommands().addAll(postParseCommands);
		return parseResult;
	}
	
	public List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal> parseToExpectedElements(EClass type, org.dresdenocl.language.ocl.resource.ocl.IOclTextResource dummyResource, int cursorOffset) {
		this.rememberExpectedElements = true;
		this.parseToIndexTypeObject = type;
		this.cursorOffset = cursorOffset;
		this.lastStartIncludingHidden = -1;
		final CommonTokenStream tokenStream = (CommonTokenStream) getTokenStream();
		org.dresdenocl.language.ocl.resource.ocl.IOclParseResult result = parse();
		for (EObject incompleteObject : incompleteObjects) {
			Lexer lexer = (Lexer) tokenStream.getTokenSource();
			int endChar = lexer.getCharIndex();
			int endLine = lexer.getLine();
			setLocalizationEnd(result.getPostParseCommands(), incompleteObject, endChar, endLine);
		}
		if (result != null) {
			EObject root = result.getRoot();
			if (root != null) {
				dummyResource.getContentsInternal().add(root);
			}
			for (org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource> command : result.getPostParseCommands()) {
				command.execute(dummyResource);
			}
		}
		// remove all expected elements that were added after the last complete element
		expectedElements = expectedElements.subList(0, expectedElementsIndexOfLastCompleteElement + 1);
		int lastFollowSetID = expectedElements.get(expectedElementsIndexOfLastCompleteElement).getFollowSetID();
		Set<org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal> currentFollowSet = new LinkedHashSet<org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal>();
		List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal> newFollowSet = new ArrayList<org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal>();
		for (int i = expectedElementsIndexOfLastCompleteElement; i >= 0; i--) {
			org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal expectedElementI = expectedElements.get(i);
			if (expectedElementI.getFollowSetID() == lastFollowSetID) {
				currentFollowSet.add(expectedElementI);
			} else {
				break;
			}
		}
		int followSetID = 255;
		int i;
		for (i = tokenIndexOfLastCompleteElement; i < tokenStream.size(); i++) {
			CommonToken nextToken = (CommonToken) tokenStream.get(i);
			if (nextToken.getType() < 0) {
				break;
			}
			if (nextToken.getChannel() == 99) {
				// hidden tokens do not reduce the follow set
			} else {
				// now that we have found the next visible token the position for that expected
				// terminals can be set
				for (org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal nextFollow : newFollowSet) {
					lastTokenIndex = 0;
					setPosition(nextFollow, i);
				}
				newFollowSet.clear();
				// normal tokens do reduce the follow set - only elements that match the token are
				// kept
				for (org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal nextFollow : currentFollowSet) {
					if (nextFollow.getTerminal().getTokenNames().contains(getTokenNames()[nextToken.getType()])) {
						// keep this one - it matches
						Collection<org.dresdenocl.language.ocl.resource.ocl.util.OclPair<org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement, org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[]>> newFollowers = nextFollow.getTerminal().getFollowers();
						for (org.dresdenocl.language.ocl.resource.ocl.util.OclPair<org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement, org.dresdenocl.language.ocl.resource.ocl.mopp.OclContainedFeature[]> newFollowerPair : newFollowers) {
							org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement newFollower = newFollowerPair.getLeft();
							EObject container = getLastIncompleteElement();
							org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainmentTrace containmentTrace = new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainmentTrace(null, newFollowerPair.getRight());
							org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal newFollowTerminal = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal(container, newFollower, followSetID, containmentTrace);
							newFollowSet.add(newFollowTerminal);
							expectedElements.add(newFollowTerminal);
						}
					}
				}
				currentFollowSet.clear();
				currentFollowSet.addAll(newFollowSet);
			}
			followSetID++;
		}
		// after the last token in the stream we must set the position for the elements
		// that were added during the last iteration of the loop
		for (org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal nextFollow : newFollowSet) {
			lastTokenIndex = 0;
			setPosition(nextFollow, i);
		}
		return this.expectedElements;
	}
	
	public void setPosition(org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectedTerminal expectedElement, int tokenIndex) {
		int currentIndex = Math.max(0, tokenIndex);
		for (int index = lastTokenIndex; index < currentIndex; index++) {
			if (index >= input.size()) {
				break;
			}
			CommonToken tokenAtIndex = (CommonToken) input.get(index);
			stopIncludingHiddenTokens = tokenAtIndex.getStopIndex() + 1;
			if (tokenAtIndex.getChannel() != 99 && !anonymousTokens.contains(tokenAtIndex)) {
				stopExcludingHiddenTokens = tokenAtIndex.getStopIndex() + 1;
			}
		}
		lastTokenIndex = Math.max(0, currentIndex);
		expectedElement.setPosition(stopExcludingHiddenTokens, stopIncludingHiddenTokens);
	}
	
	public Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
		if (!rememberExpectedElements) {
			return super.recoverFromMismatchedToken(input, ttype, follow);
		} else {
			return null;
		}
	}
	
	private void startIncompleteElement(Object object) {
		if (object instanceof EObject) {
			this.incompleteObjects.add((EObject) object);
		}
	}
	
	private void completedElement(Object object, boolean isContainment) {
		if (isContainment && !this.incompleteObjects.isEmpty()) {
			boolean exists = this.incompleteObjects.remove(object);
			if (!exists) {
			}
		}
		if (object instanceof EObject) {
			this.tokenIndexOfLastCompleteElement = getTokenStream().index();
			this.expectedElementsIndexOfLastCompleteElement = expectedElements.size() - 1;
		}
	}
	
	private EObject getLastIncompleteElement() {
		if (incompleteObjects.isEmpty()) {
			return null;
		}
		return incompleteObjects.get(incompleteObjects.size() - 1);
	}
	
}

start returns [ EObject element = null]
:
	{
		// follow set for start rule(s)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[0]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithoutNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithoutNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithoutNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[3]);
		expectedElementsIndexOfLastCompleteElement = 0;
	}
	(
		c0 = parse_org_dresdenocl_language_ocl_PackageDeclarationWithNamespaceCS{ element = c0; }
		|  		c1 = parse_org_dresdenocl_language_ocl_PackageDeclarationWithoutNamespaceCS{ element = c1; }
	)
	EOF	{
		retrieveLayoutInformation(element, null, null, false);
	}
	
;

parse_org_dresdenocl_language_ocl_SimpleNameCS returns [org.dresdenocl.language.ocl.SimpleNameCS element = null]
@init{
}
:
	(
		a0 = ITERATOR_NAME		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createSimpleNameCS();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("ITERATOR_NAME");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.SIMPLE_NAME_CS__SIMPLE_NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.SIMPLE_NAME_CS__SIMPLE_NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_0_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[4]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[5]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[6]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[7]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[8]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[9]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[10]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[11]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[12]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[13]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[14]);
	}
	
	
	|	(
		a1 = SIMPLE_NAME		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createSimpleNameCS();
				startIncompleteElement(element);
			}
			if (a1 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.SIMPLE_NAME_CS__SIMPLE_NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.SIMPLE_NAME_CS__SIMPLE_NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_0_0_1_0, resolved, true);
				copyLocalizationInfos((CommonToken) a1, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[15]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[16]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[17]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[18]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[19]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[20]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[21]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[22]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[23]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[24]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[25]);
	}
	
;

parse_org_dresdenocl_language_ocl_PackageDeclarationWithNamespaceCS returns [org.dresdenocl.language.ocl.PackageDeclarationWithNamespaceCS element = null]
@init{
}
:
	a0 = 'package' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPackageDeclarationWithNamespaceCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_1_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[26]);
	}
	
	(
		a1_0 = parse_org_dresdenocl_language_ocl_PackageDeclarationNestedNamespaceCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPackageDeclarationWithNamespaceCS();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_1_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[27]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[28]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[29]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[30]);
	}
	
	(
		(
			(
				a2_0 = parse_org_dresdenocl_language_ocl_ContextDeclarationCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPackageDeclarationWithNamespaceCS();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__CONTEXT_DECLARATIONS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_1_0_0_3_0_0_0, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[31]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[32]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[33]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[34]);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[35]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[36]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[37]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[38]);
	}
	
	a3 = 'endpackage' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPackageDeclarationWithNamespaceCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_1_0_0_5, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
	}
	
;

parse_org_dresdenocl_language_ocl_PackageDeclarationNestedNamespaceCS returns [org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS element = null]
@init{
}
:
	(
		a0 = SIMPLE_NAME		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPackageDeclarationNestedNamespaceCS();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NAMESPACE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.dresdenocl.pivotmodel.Namespace proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createNamespace();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS, org.dresdenocl.pivotmodel.Namespace>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getPackageDeclarationNestedNamespaceCSNamespaceReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NAMESPACE), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NAMESPACE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_2_0_0_0, proxy, true);
				copyLocalizationInfos((CommonToken) a0, element);
				copyLocalizationInfos((CommonToken) a0, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[39]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[40]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[41]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[42]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[43]);
	}
	
	(
		(
			a1 = '::' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPackageDeclarationNestedNamespaceCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_2_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationNestedNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[44]);
			}
			
			(
				a2_0 = parse_org_dresdenocl_language_ocl_PackageDeclarationNestedNamespaceCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPackageDeclarationNestedNamespaceCS();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NESTED_NAMESPACE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_2_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[45]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[46]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[47]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[48]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[49]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[50]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[51]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[52]);
	}
	
;

parse_org_dresdenocl_language_ocl_PackageDeclarationWithoutNamespaceCS returns [org.dresdenocl.language.ocl.PackageDeclarationWithoutNamespaceCS element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_dresdenocl_language_ocl_ContextDeclarationCS			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPackageDeclarationWithoutNamespaceCS();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS__CONTEXT_DECLARATIONS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_3_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithoutNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[53]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithoutNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[54]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithoutNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[55]);
	}
	
;

parse_org_dresdenocl_language_ocl_OperationContextDeclarationCS returns [org.dresdenocl.language.ocl.OperationContextDeclarationCS element = null]
@init{
}
:
	a0 = 'context' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationContextDeclarationCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_4_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[56]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[57]);
	}
	
	(
		a1_0 = parse_org_dresdenocl_language_ocl_OperationDefinitionInContextCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationContextDeclarationCS();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_4_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[58]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[59]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[60]);
	}
	
	(
		(
			a2_0 = parse_org_dresdenocl_language_ocl_PrePostOrBodyDeclarationCS			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationContextDeclarationCS();
					startIncompleteElement(element);
				}
				if (a2_0 != null) {
					if (a2_0 != null) {
						Object value = a2_0;
						addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_4_0_0_3, a2_0, true);
					copyLocalizationInfos(a2_0, element);
				}
			}
		)
		
	)+	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[61]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[62]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[63]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[64]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[65]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[66]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[67]);
	}
	
;

parse_org_dresdenocl_language_ocl_AttributeContextDeclarationCS returns [org.dresdenocl.language.ocl.AttributeContextDeclarationCS element = null]
@init{
}
:
	a0 = 'context' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createAttributeContextDeclarationCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_5_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[68]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[69]);
	}
	
	(
		a1_0 = parse_org_dresdenocl_language_ocl_ModelElementCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createAttributeContextDeclarationCS();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_5_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[70]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[71]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[72]);
	}
	
	(
		(
			a2 = ':' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createAttributeContextDeclarationCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_5_0_0_2_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[73]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[74]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[75]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[76]);
			}
			
			(
				a3_0 = parse_org_dresdenocl_language_ocl_TypeCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createAttributeContextDeclarationCS();
						startIncompleteElement(element);
					}
					if (a3_0 != null) {
						if (a3_0 != null) {
							Object value = a3_0;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_5_0_0_2_0_0_1, a3_0, true);
						copyLocalizationInfos(a3_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[77]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[78]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[79]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[80]);
	}
	
	(
		a4_0 = parse_org_dresdenocl_language_ocl_InitOrDeriveValueCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createAttributeContextDeclarationCS();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_5_0_0_4, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[81]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[82]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[83]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[84]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[85]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[86]);
	}
	
	(
		(
			a5_0 = parse_org_dresdenocl_language_ocl_InitOrDeriveValueCS			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createAttributeContextDeclarationCS();
					startIncompleteElement(element);
				}
				if (a5_0 != null) {
					if (a5_0 != null) {
						Object value = a5_0;
						addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_5_0_0_5, a5_0, true);
					copyLocalizationInfos(a5_0, element);
				}
			}
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[87]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[88]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[89]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[90]);
	}
	
;

parse_org_dresdenocl_language_ocl_ClassifierContextDeclarationCS returns [org.dresdenocl.language.ocl.ClassifierContextDeclarationCS element = null]
@init{
}
:
	a0 = 'context' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createClassifierContextDeclarationCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_6_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[91]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[92]);
	}
	
	(
		a1_0 = parse_org_dresdenocl_language_ocl_ModelElementCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createClassifierContextDeclarationCS();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_6_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[93]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[94]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[95]);
	}
	
	(
		(
			a2_0 = parse_org_dresdenocl_language_ocl_InvariantOrDefinitionCS			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createClassifierContextDeclarationCS();
					startIncompleteElement(element);
				}
				if (a2_0 != null) {
					if (a2_0 != null) {
						Object value = a2_0;
						addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_6_0_0_3, a2_0, true);
					copyLocalizationInfos(a2_0, element);
				}
			}
		)
		
	)+	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[96]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[97]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[98]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[99]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[100]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[101]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[102]);
	}
	
;

parse_org_dresdenocl_language_ocl_InitValueCS returns [org.dresdenocl.language.ocl.InitValueCS element = null]
@init{
}
:
	a0 = 'init' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createInitValueCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_7_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[103]);
	}
	
	a1 = ':' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createInitValueCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_7_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[104]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[105]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[106]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[107]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[108]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[109]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[110]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[111]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[112]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[113]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[114]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[115]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[116]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[117]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[118]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[119]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[120]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[121]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[122]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[123]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[124]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[125]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[126]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[127]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[128]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[129]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[130]);
	}
	
	(
		a2_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createInitValueCS();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INIT_VALUE_CS__OCL_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_7_0_0_3, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[131]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[132]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[133]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[134]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[135]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[136]);
	}
	
;

parse_org_dresdenocl_language_ocl_DeriveValueCS returns [org.dresdenocl.language.ocl.DeriveValueCS element = null]
@init{
}
:
	a0 = 'derive' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createDeriveValueCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_8_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[137]);
	}
	
	a1 = ':' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createDeriveValueCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_8_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[138]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[139]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[140]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[141]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[142]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[143]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[144]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[145]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[146]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[147]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[148]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[149]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[150]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[151]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[152]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[153]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[154]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[155]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[156]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[157]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[158]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[159]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[160]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[161]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[162]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[163]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[164]);
	}
	
	(
		a2_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createDeriveValueCS();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DERIVE_VALUE_CS__OCL_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_8_0_0_3, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[165]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[166]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[167]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[168]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[169]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[170]);
	}
	
;

parse_org_dresdenocl_language_ocl_InvariantExpCS returns [org.dresdenocl.language.ocl.InvariantExpCS element = null]
@init{
}
:
	a0 = 'inv' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createInvariantExpCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_9_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[171]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[172]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[173]);
	}
	
	(
		(
			a1_0 = parse_org_dresdenocl_language_ocl_SimpleNameCS			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createInvariantExpCS();
					startIncompleteElement(element);
				}
				if (a1_0 != null) {
					if (a1_0 != null) {
						Object value = a1_0;
						element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INVARIANT_EXP_CS__NAME), value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_9_0_0_1, a1_0, true);
					copyLocalizationInfos(a1_0, element);
				}
			}
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[174]);
	}
	
	a2 = ':' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createInvariantExpCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_9_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[175]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[176]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[177]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[178]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[179]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[180]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[181]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[182]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[183]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[184]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[185]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[186]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[187]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[188]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[189]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[190]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[191]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[192]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[193]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[194]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[195]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[196]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[197]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[198]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[199]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[200]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[201]);
	}
	
	(
		a3_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createInvariantExpCS();
				startIncompleteElement(element);
			}
			if (a3_0 != null) {
				if (a3_0 != null) {
					Object value = a3_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_9_0_0_4, a3_0, true);
				copyLocalizationInfos(a3_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[202]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[203]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[204]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[205]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[206]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[207]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[208]);
	}
	
;

parse_org_dresdenocl_language_ocl_DefinitionExpCS returns [org.dresdenocl.language.ocl.DefinitionExpCS element = null]
@init{
}
:
	(
		(
			(
				a0 = STATIC				
				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createDefinitionExpCS();
						startIncompleteElement(element);
					}
					if (a0 != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("STATIC");
						tokenResolver.setOptions(getOptions());
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_CS__STATIC), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
						}
						java.lang.Boolean resolved = (java.lang.Boolean) resolvedObject;
						if (resolved != null) {
							Object value = resolved;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_CS__STATIC), value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_10_0_0_0_0_0_0, resolved, true);
						copyLocalizationInfos((CommonToken) a0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[209]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[210]);
	}
	
	a1 = 'def' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createDefinitionExpCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_10_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[211]);
	}
	
	a2 = ':' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createDefinitionExpCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_10_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[212]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[213]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[214]);
	}
	
	(
		a3_0 = parse_org_dresdenocl_language_ocl_DefinitionExpPartCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createDefinitionExpCS();
				startIncompleteElement(element);
			}
			if (a3_0 != null) {
				if (a3_0 != null) {
					Object value = a3_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_10_0_0_4, a3_0, true);
				copyLocalizationInfos(a3_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[215]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[216]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[217]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[218]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[219]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[220]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[221]);
	}
	
;

parse_org_dresdenocl_language_ocl_DefinitionExpPropertyCS returns [org.dresdenocl.language.ocl.DefinitionExpPropertyCS element = null]
@init{
}
:
	(
		a0_0 = parse_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createDefinitionExpPropertyCS();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_PROPERTY_CS__VARIABLE_DECLARATION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_11_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[222]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[223]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[224]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[225]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[226]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[227]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[228]);
	}
	
;

parse_org_dresdenocl_language_ocl_DefinitionExpOperationCS returns [org.dresdenocl.language.ocl.DefinitionExpOperationCS element = null]
@init{
}
:
	(
		a0_0 = parse_org_dresdenocl_language_ocl_OperationDefinitionInDefCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createDefinitionExpOperationCS();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__OPERATION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_12_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[229]);
	}
	
	(
		a1 = EQUALITY_OPERATOR		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createDefinitionExpOperationCS();
				startIncompleteElement(element);
			}
			if (a1 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("EQUALITY_OPERATOR");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__EQUAL), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__EQUAL), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_12_0_0_1, resolved, true);
				copyLocalizationInfos((CommonToken) a1, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[230]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[231]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[232]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[233]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[234]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[235]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[236]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[237]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[238]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[239]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[240]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[241]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[242]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[243]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[244]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[245]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[246]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[247]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[248]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[249]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[250]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[251]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[252]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[253]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[254]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[255]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[256]);
	}
	
	(
		a2_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createDefinitionExpOperationCS();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__OCL_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_12_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[257]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[258]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[259]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[260]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[261]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[262]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[263]);
	}
	
;

parse_org_dresdenocl_language_ocl_PreConditionDeclarationCS returns [org.dresdenocl.language.ocl.PreConditionDeclarationCS element = null]
@init{
}
:
	a0 = 'pre' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPreConditionDeclarationCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_13_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[264]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[265]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[266]);
	}
	
	(
		(
			(
				a1_0 = parse_org_dresdenocl_language_ocl_SimpleNameCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPreConditionDeclarationCS();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PRE_CONDITION_DECLARATION_CS__NAME), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_13_0_0_1_0_0_0, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[267]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[268]);
	}
	
	a2 = ':' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPreConditionDeclarationCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_13_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[269]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[270]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[271]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[272]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[273]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[274]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[275]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[276]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[277]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[278]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[279]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[280]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[281]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[282]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[283]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[284]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[285]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[286]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[287]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[288]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[289]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[290]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[291]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[292]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[293]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[294]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[295]);
	}
	
	(
		a3_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPreConditionDeclarationCS();
				startIncompleteElement(element);
			}
			if (a3_0 != null) {
				if (a3_0 != null) {
					Object value = a3_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PRE_CONDITION_DECLARATION_CS__OCL_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_13_0_0_4, a3_0, true);
				copyLocalizationInfos(a3_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[296]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[297]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[298]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[299]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[300]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[301]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[302]);
	}
	
;

parse_org_dresdenocl_language_ocl_PostConditionDeclarationCS returns [org.dresdenocl.language.ocl.PostConditionDeclarationCS element = null]
@init{
}
:
	a0 = 'post' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPostConditionDeclarationCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_14_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[303]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[304]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[305]);
	}
	
	(
		(
			(
				a1_0 = parse_org_dresdenocl_language_ocl_SimpleNameCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPostConditionDeclarationCS();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.POST_CONDITION_DECLARATION_CS__NAME), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_14_0_0_1_0_0_0, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[306]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[307]);
	}
	
	a2 = ':' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPostConditionDeclarationCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_14_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[308]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[309]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[310]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[311]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[312]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[313]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[314]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[315]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[316]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[317]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[318]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[319]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[320]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[321]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[322]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[323]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[324]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[325]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[326]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[327]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[328]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[329]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[330]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[331]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[332]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[333]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[334]);
	}
	
	(
		a3_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPostConditionDeclarationCS();
				startIncompleteElement(element);
			}
			if (a3_0 != null) {
				if (a3_0 != null) {
					Object value = a3_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.POST_CONDITION_DECLARATION_CS__OCL_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_14_0_0_4, a3_0, true);
				copyLocalizationInfos(a3_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[335]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[336]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[337]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[338]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[339]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[340]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[341]);
	}
	
;

parse_org_dresdenocl_language_ocl_BodyDeclarationCS returns [org.dresdenocl.language.ocl.BodyDeclarationCS element = null]
@init{
}
:
	a0 = 'body' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createBodyDeclarationCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_15_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[342]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[343]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[344]);
	}
	
	(
		(
			(
				a1_0 = parse_org_dresdenocl_language_ocl_SimpleNameCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createBodyDeclarationCS();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BODY_DECLARATION_CS__NAME), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_15_0_0_1_0_0_0, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[345]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[346]);
	}
	
	a2 = ':' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createBodyDeclarationCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_15_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[347]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[348]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[349]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[350]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[351]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[352]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[353]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[354]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[355]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[356]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[357]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[358]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[359]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[360]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[361]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[362]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[363]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[364]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[365]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[366]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[367]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[368]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[369]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[370]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[371]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[372]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[373]);
	}
	
	(
		a3_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createBodyDeclarationCS();
				startIncompleteElement(element);
			}
			if (a3_0 != null) {
				if (a3_0 != null) {
					Object value = a3_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BODY_DECLARATION_CS__OCL_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_15_0_0_4, a3_0, true);
				copyLocalizationInfos(a3_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[374]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[375]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[376]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[377]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[378]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[379]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[380]);
	}
	
;

parse_org_dresdenocl_language_ocl_OperationDefinitionInContextCS returns [org.dresdenocl.language.ocl.OperationDefinitionInContextCS element = null]
@init{
}
:
	(
		a0_0 = parse_org_dresdenocl_language_ocl_ModelElementCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInContextCS();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__TYPE_NAME), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_16_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[381]);
	}
	
	a1 = '::' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInContextCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_16_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[382]);
	}
	
	(
		a2 = SIMPLE_NAME		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInContextCS();
				startIncompleteElement(element);
			}
			if (a2 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__OPERATION), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationDefinitionCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationDefinitionCSOperationReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__OPERATION), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__OPERATION), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_16_0_0_4, proxy, true);
				copyLocalizationInfos((CommonToken) a2, element);
				copyLocalizationInfos((CommonToken) a2, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[383]);
	}
	
	a3 = '(' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInContextCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_16_0_0_6, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[384]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[385]);
	}
	
	(
		(
			(
				a4_0 = parse_org_dresdenocl_language_ocl_ParameterCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInContextCS();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_16_0_0_8_0_0_0, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[386]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[387]);
			}
			
			(
				(
					a5 = ',' {
						if (element == null) {
							element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInContextCS();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_16_0_0_8_0_0_1_0_0_1, null, true);
						copyLocalizationInfos((CommonToken)a5, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[388]);
					}
					
					(
						a6_0 = parse_org_dresdenocl_language_ocl_ParameterCS						{
							if (terminateParsing) {
								throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
							}
							if (element == null) {
								element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInContextCS();
								startIncompleteElement(element);
							}
							if (a6_0 != null) {
								if (a6_0 != null) {
									Object value = a6_0;
									addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_16_0_0_8_0_0_1_0_0_2, a6_0, true);
								copyLocalizationInfos(a6_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[389]);
						addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[390]);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[391]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[392]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[393]);
	}
	
	a7 = ')' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInContextCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_16_0_0_10, null, true);
		copyLocalizationInfos((CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[394]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[395]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[396]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[397]);
	}
	
	(
		(
			a8 = ':' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInContextCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_16_0_0_11_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a8, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[398]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[399]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[400]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[401]);
			}
			
			(
				a9_0 = parse_org_dresdenocl_language_ocl_TypeCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInContextCS();
						startIncompleteElement(element);
					}
					if (a9_0 != null) {
						if (a9_0 != null) {
							Object value = a9_0;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__RETURN_TYPE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_16_0_0_11_0_0_1, a9_0, true);
						copyLocalizationInfos(a9_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[402]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[403]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[404]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[405]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[406]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[407]);
	}
	
;

parse_org_dresdenocl_language_ocl_OperationDefinitionInDefCS returns [org.dresdenocl.language.ocl.OperationDefinitionInDefCS element = null]
@init{
}
:
	(
		a0 = SIMPLE_NAME		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInDefCS();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__OPERATION), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationDefinitionCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationDefinitionCSOperationReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__OPERATION), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__OPERATION), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_17_0_0_0, proxy, true);
				copyLocalizationInfos((CommonToken) a0, element);
				copyLocalizationInfos((CommonToken) a0, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[408]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInDefCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_17_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[409]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[410]);
	}
	
	(
		(
			(
				a2_0 = parse_org_dresdenocl_language_ocl_ParameterCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInDefCS();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_17_0_0_2_0_0_0, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[411]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[412]);
			}
			
			(
				(
					a3 = ',' {
						if (element == null) {
							element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInDefCS();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_17_0_0_2_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a3, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[413]);
					}
					
					(
						a4_0 = parse_org_dresdenocl_language_ocl_ParameterCS						{
							if (terminateParsing) {
								throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
							}
							if (element == null) {
								element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInDefCS();
								startIncompleteElement(element);
							}
							if (a4_0 != null) {
								if (a4_0 != null) {
									Object value = a4_0;
									addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_17_0_0_2_0_0_1_0_0_1, a4_0, true);
								copyLocalizationInfos(a4_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[414]);
						addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[415]);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[416]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[417]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[418]);
	}
	
	a5 = ')' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInDefCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_17_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[419]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[420]);
	}
	
	(
		(
			a6 = ':' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInDefCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_17_0_0_4_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a6, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[421]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[422]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[423]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[424]);
			}
			
			(
				a7_0 = parse_org_dresdenocl_language_ocl_TypeCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInDefCS();
						startIncompleteElement(element);
					}
					if (a7_0 != null) {
						if (a7_0 != null) {
							Object value = a7_0;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__RETURN_TYPE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_17_0_0_4_0_0_1, a7_0, true);
						copyLocalizationInfos(a7_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[425]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[426]);
	}
	
;

parse_org_dresdenocl_language_ocl_ParameterCS returns [org.dresdenocl.language.ocl.ParameterCS element = null]
@init{
}
:
	(
		a0 = SIMPLE_NAME		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createParameterCS();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PARAMETER_CS__PARAMETER), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.dresdenocl.pivotmodel.Parameter proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createParameter();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.ParameterCS, org.dresdenocl.pivotmodel.Parameter>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getParameterCSParameterReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PARAMETER_CS__PARAMETER), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PARAMETER_CS__PARAMETER), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_18_0_0_0, proxy, true);
				copyLocalizationInfos((CommonToken) a0, element);
				copyLocalizationInfos((CommonToken) a0, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[427]);
	}
	
	a1 = ':' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createParameterCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_18_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getParameterCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[428]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getParameterCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[429]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getParameterCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[430]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getParameterCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[431]);
	}
	
	(
		a2_0 = parse_org_dresdenocl_language_ocl_TypeCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createParameterCS();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PARAMETER_CS__PARAMETER_TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_18_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[432]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[433]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[434]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[435]);
	}
	
;

parse_org_dresdenocl_language_ocl_ImplicitOperationCallCS returns [org.dresdenocl.language.ocl.ImplicitOperationCallCS element = null]
@init{
}
:
	(
		(
			a0 = EQUALITY_OPERATOR			
			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
					startIncompleteElement(element);
				}
				if (a0 != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("EQUALITY_OPERATOR");
					tokenResolver.setOptions(getOptions());
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
					}
					String resolved = (String) resolvedObject;
					org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
					collectHiddenTokens(element);
					registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), resolved, proxy);
					if (proxy != null) {
						Object value = proxy;
						element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_0_0_0_0, proxy, true);
					copyLocalizationInfos((CommonToken) a0, element);
					copyLocalizationInfos((CommonToken) a0, proxy);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[436]);
		}
		
		
		|		(
			a1 = NEQUALITY_OPERATOR			
			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("NEQUALITY_OPERATOR");
					tokenResolver.setOptions(getOptions());
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
					}
					String resolved = (String) resolvedObject;
					org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
					collectHiddenTokens(element);
					registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), resolved, proxy);
					if (proxy != null) {
						Object value = proxy;
						element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_0_0_1_0, proxy, true);
					copyLocalizationInfos((CommonToken) a1, element);
					copyLocalizationInfos((CommonToken) a1, proxy);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[437]);
		}
		
		
		|		(
			a2 = NOT_OPERATOR			
			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
					startIncompleteElement(element);
				}
				if (a2 != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("NOT_OPERATOR");
					tokenResolver.setOptions(getOptions());
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
					}
					String resolved = (String) resolvedObject;
					org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
					collectHiddenTokens(element);
					registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), resolved, proxy);
					if (proxy != null) {
						Object value = proxy;
						element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_0_0_2_0, proxy, true);
					copyLocalizationInfos((CommonToken) a2, element);
					copyLocalizationInfos((CommonToken) a2, proxy);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[438]);
		}
		
		
		|		(
			a3 = AND_OPERATOR			
			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
					startIncompleteElement(element);
				}
				if (a3 != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("AND_OPERATOR");
					tokenResolver.setOptions(getOptions());
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((CommonToken) a3).getLine(), ((CommonToken) a3).getCharPositionInLine(), ((CommonToken) a3).getStartIndex(), ((CommonToken) a3).getStopIndex());
					}
					String resolved = (String) resolvedObject;
					org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
					collectHiddenTokens(element);
					registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), resolved, proxy);
					if (proxy != null) {
						Object value = proxy;
						element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_0_0_3_0, proxy, true);
					copyLocalizationInfos((CommonToken) a3, element);
					copyLocalizationInfos((CommonToken) a3, proxy);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[439]);
		}
		
		
		|		(
			a4 = OR_OPERATOR			
			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
					startIncompleteElement(element);
				}
				if (a4 != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("OR_OPERATOR");
					tokenResolver.setOptions(getOptions());
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((CommonToken) a4).getLine(), ((CommonToken) a4).getCharPositionInLine(), ((CommonToken) a4).getStartIndex(), ((CommonToken) a4).getStopIndex());
					}
					String resolved = (String) resolvedObject;
					org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
					collectHiddenTokens(element);
					registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), resolved, proxy);
					if (proxy != null) {
						Object value = proxy;
						element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_0_0_4_0, proxy, true);
					copyLocalizationInfos((CommonToken) a4, element);
					copyLocalizationInfos((CommonToken) a4, proxy);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[440]);
		}
		
		
		|		(
			a5 = XOR_OPERATOR			
			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
					startIncompleteElement(element);
				}
				if (a5 != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("XOR_OPERATOR");
					tokenResolver.setOptions(getOptions());
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a5.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((CommonToken) a5).getLine(), ((CommonToken) a5).getCharPositionInLine(), ((CommonToken) a5).getStartIndex(), ((CommonToken) a5).getStopIndex());
					}
					String resolved = (String) resolvedObject;
					org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
					collectHiddenTokens(element);
					registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), resolved, proxy);
					if (proxy != null) {
						Object value = proxy;
						element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_0_0_5_0, proxy, true);
					copyLocalizationInfos((CommonToken) a5, element);
					copyLocalizationInfos((CommonToken) a5, proxy);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[441]);
		}
		
		
		|		(
			a6 = IMPLIES_OPERATOR			
			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
					startIncompleteElement(element);
				}
				if (a6 != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IMPLIES_OPERATOR");
					tokenResolver.setOptions(getOptions());
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a6.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((CommonToken) a6).getLine(), ((CommonToken) a6).getCharPositionInLine(), ((CommonToken) a6).getStartIndex(), ((CommonToken) a6).getStopIndex());
					}
					String resolved = (String) resolvedObject;
					org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
					collectHiddenTokens(element);
					registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), resolved, proxy);
					if (proxy != null) {
						Object value = proxy;
						element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_0_0_6_0, proxy, true);
					copyLocalizationInfos((CommonToken) a6, element);
					copyLocalizationInfos((CommonToken) a6, proxy);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[442]);
		}
		
		
		|		(
			a7 = SIMPLE_NAME			
			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
					startIncompleteElement(element);
				}
				if (a7 != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
					tokenResolver.setOptions(getOptions());
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a7.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((CommonToken) a7).getLine(), ((CommonToken) a7).getCharPositionInLine(), ((CommonToken) a7).getStartIndex(), ((CommonToken) a7).getStopIndex());
					}
					String resolved = (String) resolvedObject;
					org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
					collectHiddenTokens(element);
					registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), resolved, proxy);
					if (proxy != null) {
						Object value = proxy;
						element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_0_0_7_0, proxy, true);
					copyLocalizationInfos((CommonToken) a7, element);
					copyLocalizationInfos((CommonToken) a7, proxy);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[443]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[444]);
	}
	
	a8 = '(' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a8, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[445]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[446]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[447]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[448]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[449]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[450]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[451]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[452]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[453]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[454]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[455]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[456]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[457]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[458]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[459]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[460]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[461]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[462]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[463]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[464]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[465]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[466]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[467]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[468]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[469]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[470]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[471]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[472]);
	}
	
	(
		(
			(
				a9_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
						startIncompleteElement(element);
					}
					if (a9_0 != null) {
						if (a9_0 != null) {
							Object value = a9_0;
							addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_3_0_0_1, a9_0, true);
						copyLocalizationInfos(a9_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[473]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[474]);
			}
			
			(
				(
					a10 = ',' {
						if (element == null) {
							element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_3_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a10, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[475]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[476]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[477]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[478]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[479]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[480]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[481]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[482]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[483]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[484]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[485]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[486]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[487]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[488]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[489]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[490]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[491]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[492]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[493]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[494]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[495]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[496]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[497]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[498]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[499]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[500]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[501]);
					}
					
					(
						a11_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS						{
							if (terminateParsing) {
								throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
							}
							if (element == null) {
								element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
								startIncompleteElement(element);
							}
							if (a11_0 != null) {
								if (a11_0 != null) {
									Object value = a11_0;
									addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_3_0_0_2_0_0_1, a11_0, true);
								copyLocalizationInfos(a11_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[502]);
						addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[503]);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[504]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[505]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[506]);
	}
	
	a12 = ')' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30_0_0_5, null, true);
		copyLocalizationInfos((CommonToken)a12, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[507]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[508]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[509]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[510]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[511]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[512]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[513]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[514]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[515]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[516]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[517]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[518]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[519]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[520]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[521]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[522]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[523]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[524]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[525]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[526]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[527]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[528]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[529]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[530]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[531]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[532]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[533]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[534]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[535]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[536]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[537]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[538]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[539]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[540]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[541]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[542]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[543]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[544]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[545]);
	}
	
;

parse_org_dresdenocl_language_ocl_ImplicitPropertyCallCS returns [org.dresdenocl.language.ocl.ImplicitPropertyCallCS element = null]
@init{
}
:
	(
		a0 = SIMPLE_NAME		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitPropertyCallCS();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__PROPERTY), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.dresdenocl.pivotmodel.Property proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createProperty();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.PropertyCallBaseExpCS, org.dresdenocl.pivotmodel.Property>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getPropertyCallBaseExpCSPropertyReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__PROPERTY), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__PROPERTY), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_31_0_0_0, proxy, true);
				copyLocalizationInfos((CommonToken) a0, element);
				copyLocalizationInfos((CommonToken) a0, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[546]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[547]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[548]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[549]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[550]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[551]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[552]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[553]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[554]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[555]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[556]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[557]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[558]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[559]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[560]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[561]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[562]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[563]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[564]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[565]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[566]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[567]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[568]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[569]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[570]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[571]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[572]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[573]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[574]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[575]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[576]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[577]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[578]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[579]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[580]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[581]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[582]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[583]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[584]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[585]);
	}
	
	(
		(
			(
				a1 = IS_MARKED_PRE				
				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createImplicitPropertyCallCS();
						startIncompleteElement(element);
					}
					if (a1 != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IS_MARKED_PRE");
						tokenResolver.setOptions(getOptions());
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__IS_MARKED_PRE), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
						}
						java.lang.Boolean resolved = (java.lang.Boolean) resolvedObject;
						if (resolved != null) {
							Object value = resolved;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__IS_MARKED_PRE), value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_31_0_0_1_0_0_1, resolved, true);
						copyLocalizationInfos((CommonToken) a1, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[586]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[587]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[588]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[589]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[590]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[591]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[592]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[593]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[594]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[595]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[596]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[597]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[598]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[599]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[600]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[601]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[602]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[603]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[604]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[605]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[606]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[607]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[608]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[609]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[610]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[611]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[612]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[613]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[614]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[615]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[616]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[617]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[618]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[619]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[620]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[621]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[622]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[623]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[624]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[625]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[626]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[627]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[628]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[629]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[630]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[631]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[632]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[633]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[634]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[635]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[636]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[637]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[638]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[639]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[640]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[641]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[642]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[643]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[644]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[645]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[646]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[647]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[648]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[649]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[650]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[651]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[652]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[653]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[654]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[655]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[656]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[657]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[658]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[659]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[660]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[661]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[662]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[663]);
	}
	
;

parse_org_dresdenocl_language_ocl_IteratorExpCS returns [org.dresdenocl.language.ocl.IteratorExpCS element = null]
@init{
}
:
	(
		a0 = ITERATOR_NAME		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIteratorExpCS();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("ITERATOR_NAME");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_32_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[664]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIteratorExpCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_32_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[665]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[666]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[667]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[668]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[669]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[670]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[671]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[672]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[673]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[674]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[675]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[676]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[677]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[678]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[679]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[680]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[681]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[682]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[683]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[684]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[685]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[686]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[687]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[688]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[689]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[690]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[691]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[692]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[693]);
	}
	
	(
		(
			(
				a2_0 = parse_org_dresdenocl_language_ocl_IteratorExpVariableCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIteratorExpCS();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_32_0_0_3_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[694]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[695]);
			}
			
			(
				(
					a3 = ',' {
						if (element == null) {
							element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIteratorExpCS();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_32_0_0_3_0_0_2_0_0_1, null, true);
						copyLocalizationInfos((CommonToken)a3, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[696]);
						addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[697]);
					}
					
					(
						a4_0 = parse_org_dresdenocl_language_ocl_IteratorExpVariableCS						{
							if (terminateParsing) {
								throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
							}
							if (element == null) {
								element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIteratorExpCS();
								startIncompleteElement(element);
							}
							if (a4_0 != null) {
								if (a4_0 != null) {
									Object value = a4_0;
									addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_32_0_0_3_0_0_2_0_0_2, a4_0, true);
								copyLocalizationInfos(a4_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[698]);
					}
					
				)
				
			)?			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[699]);
			}
			
			a5 = '|' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIteratorExpCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_32_0_0_3_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a5, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[700]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[701]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[702]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[703]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[704]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[705]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[706]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[707]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[708]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[709]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[710]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[711]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[712]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[713]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[714]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[715]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[716]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[717]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[718]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[719]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[720]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[721]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[722]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[723]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[724]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[725]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[726]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[727]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[728]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[729]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[730]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[731]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[732]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[733]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[734]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[735]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[736]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[737]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[738]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[739]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[740]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[741]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[742]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[743]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[744]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[745]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[746]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[747]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[748]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[749]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[750]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[751]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[752]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[753]);
	}
	
	(
		a6_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIteratorExpCS();
				startIncompleteElement(element);
			}
			if (a6_0 != null) {
				if (a6_0 != null) {
					Object value = a6_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_32_0_0_5, a6_0, true);
				copyLocalizationInfos(a6_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[754]);
	}
	
	a7 = ')' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIteratorExpCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_32_0_0_7, null, true);
		copyLocalizationInfos((CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[755]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[756]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[757]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[758]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[759]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[760]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[761]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[762]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[763]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[764]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[765]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[766]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[767]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[768]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[769]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[770]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[771]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[772]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[773]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[774]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[775]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[776]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[777]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[778]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[779]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[780]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[781]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[782]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[783]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[784]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[785]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[786]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[787]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[788]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[789]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[790]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[791]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[792]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[793]);
	}
	
;

parse_org_dresdenocl_language_ocl_IterateExpCS returns [org.dresdenocl.language.ocl.IterateExpCS element = null]
@init{
}
:
	a0 = 'iterate' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIterateExpCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_33_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[794]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIterateExpCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_33_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[795]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[796]);
	}
	
	(
		(
			(
				a2_0 = parse_org_dresdenocl_language_ocl_IteratorExpVariableCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIterateExpCS();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_33_0_0_3_0_0_0, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[797]);
			}
			
			a3 = ';' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIterateExpCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_33_0_0_3_0_0_2, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[798]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[799]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[800]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[801]);
	}
	
	(
		a4_0 = parse_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIterateExpCS();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_33_0_0_4, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[802]);
	}
	
	a5 = '|' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIterateExpCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_33_0_0_5, null, true);
		copyLocalizationInfos((CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[803]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[804]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[805]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[806]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[807]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[808]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[809]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[810]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[811]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[812]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[813]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[814]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[815]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[816]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[817]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[818]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[819]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[820]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[821]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[822]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[823]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[824]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[825]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[826]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[827]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[828]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[829]);
	}
	
	(
		a6_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIterateExpCS();
				startIncompleteElement(element);
			}
			if (a6_0 != null) {
				if (a6_0 != null) {
					Object value = a6_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_33_0_0_6, a6_0, true);
				copyLocalizationInfos(a6_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[830]);
	}
	
	a7 = ')' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIterateExpCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_33_0_0_8, null, true);
		copyLocalizationInfos((CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[831]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[832]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[833]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[834]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[835]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[836]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[837]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[838]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[839]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[840]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[841]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[842]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[843]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[844]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[845]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[846]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[847]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[848]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[849]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[850]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[851]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[852]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[853]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[854]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[855]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[856]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[857]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[858]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[859]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[860]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[861]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[862]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[863]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[864]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[865]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[866]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[867]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[868]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[869]);
	}
	
;

parse_org_dresdenocl_language_ocl_IteratorExpVariableCS returns [org.dresdenocl.language.ocl.IteratorExpVariableCS element = null]
@init{
}
:
	(
		a0_0 = parse_org_dresdenocl_language_ocl_SimpleNameCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIteratorExpVariableCS();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_34_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[870]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[871]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[872]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[873]);
	}
	
	(
		(
			a1 = ':' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIteratorExpVariableCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_34_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpVariableCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[874]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpVariableCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[875]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpVariableCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[876]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpVariableCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[877]);
			}
			
			(
				a2_0 = parse_org_dresdenocl_language_ocl_TypeCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIteratorExpVariableCS();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_34_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[878]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[879]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[880]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[881]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[882]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[883]);
	}
	
;

parse_org_dresdenocl_language_ocl_TupleTypeCS returns [org.dresdenocl.language.ocl.TupleTypeCS element = null]
@init{
}
:
	a0 = 'Tuple' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createTupleTypeCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_35_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[884]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createTupleTypeCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_35_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleTypeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[885]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleTypeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[886]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[887]);
	}
	
	(
		(
			a2_0 = parse_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitListCS			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createTupleTypeCS();
					startIncompleteElement(element);
				}
				if (a2_0 != null) {
					if (a2_0 != null) {
						Object value = a2_0;
						element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST), value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_35_0_0_3, a2_0, true);
					copyLocalizationInfos(a2_0, element);
				}
			}
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[888]);
	}
	
	a3 = ')' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createTupleTypeCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_35_0_0_5, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[889]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[890]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[891]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[892]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[893]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[894]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[895]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[896]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[897]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[898]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[899]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[900]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[901]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[902]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[903]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[904]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[905]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[906]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[907]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[908]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[909]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[910]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[911]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[912]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[913]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[914]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[915]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[916]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[917]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[918]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[919]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[920]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[921]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[922]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[923]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[924]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[925]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[926]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[927]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[928]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[929]);
	}
	
;

parse_org_dresdenocl_language_ocl_CollectionTypeIdentifierCS returns [org.dresdenocl.language.ocl.CollectionTypeIdentifierCS element = null]
@init{
}
:
	(
		a0 = COLLECTION_TYPES		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionTypeIdentifierCS();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("COLLECTION_TYPES");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.dresdenocl.pivotmodel.Type proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createType();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.CollectionTypeIdentifierCS, org.dresdenocl.pivotmodel.Type>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getCollectionTypeIdentifierCSTypeNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_36_0_0_0, proxy, true);
				copyLocalizationInfos((CommonToken) a0, element);
				copyLocalizationInfos((CommonToken) a0, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[930]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[931]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[932]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[933]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[934]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[935]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[936]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[937]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[938]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[939]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[940]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[941]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[942]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[943]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[944]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[945]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[946]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[947]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[948]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[949]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[950]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[951]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[952]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[953]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[954]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[955]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[956]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[957]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[958]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[959]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[960]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[961]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[962]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[963]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[964]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[965]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[966]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[967]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[968]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[969]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[970]);
	}
	
	(
		(
			a1 = '(' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionTypeIdentifierCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_36_0_0_1_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[971]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[972]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[973]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[974]);
			}
			
			(
				a2_0 = parse_org_dresdenocl_language_ocl_TypeCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionTypeIdentifierCS();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_36_0_0_1_0_0_3, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[975]);
			}
			
			a3 = ')' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionTypeIdentifierCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_36_0_0_1_0_0_5, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[976]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[977]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[978]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[979]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[980]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[981]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[982]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[983]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[984]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[985]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[986]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[987]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[988]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[989]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[990]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[991]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[992]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[993]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[994]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[995]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[996]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[997]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[998]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[999]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1000]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1001]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1002]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1003]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1004]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1005]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1006]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1007]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1008]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1009]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1010]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1011]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1012]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1013]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1014]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1015]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1016]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1017]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1018]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1019]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1020]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1021]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1022]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1023]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1024]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1025]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1026]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1027]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1028]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1029]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1030]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1031]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1032]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1033]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1034]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1035]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1036]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1037]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1038]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1039]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1040]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1041]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1042]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1043]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1044]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1045]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1046]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1047]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1048]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1049]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1050]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1051]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1052]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1053]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1054]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1055]);
	}
	
;

parse_org_dresdenocl_language_ocl_TypeModelElementCS returns [org.dresdenocl.language.ocl.TypeModelElementCS element = null]
@init{
}
:
	(
		a0_0 = parse_org_dresdenocl_language_ocl_ModelElementCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createTypeModelElementCS();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TYPE_MODEL_ELEMENT_CS__MODEL_ELEMENT), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_37_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1056]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1057]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1058]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1059]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1060]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1061]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1062]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1063]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1064]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1065]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1066]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1067]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1068]);
	}
	
;

parse_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitCS returns [org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS element = null]
@init{
}
:
	(
		a0_0 = parse_org_dresdenocl_language_ocl_SimpleNameCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithoutInitCS();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_CS__VARIABLE_NAME), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_38_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1069]);
	}
	
	a1 = ':' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithoutInitCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_38_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1070]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1071]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1072]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1073]);
	}
	
	(
		a2_0 = parse_org_dresdenocl_language_ocl_TypeCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithoutInitCS();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_CS__TYPE_NAME), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_38_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1074]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1075]);
	}
	
;

parse_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitListCS returns [org.dresdenocl.language.ocl.VariableDeclarationWithoutInitListCS element = null]
@init{
}
:
	(
		a0_0 = parse_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithoutInitListCS();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS__VARIABLE_DECLARATIONS, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_39_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1076]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1077]);
	}
	
	(
		(
			a1 = ',' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithoutInitListCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_39_0_0_1_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitListCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1078]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitListCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1079]);
			}
			
			(
				a2_0 = parse_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithoutInitListCS();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS__VARIABLE_DECLARATIONS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_39_0_0_1_0_0_2, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1080]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1081]);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1082]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1083]);
	}
	
;

parse_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS returns [org.dresdenocl.language.ocl.VariableDeclarationWithInitCS element = null]
@init{
}
:
	(
		a0_0 = parse_org_dresdenocl_language_ocl_SimpleNameCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithInitCS();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__VARIABLE_NAME), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_40_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1084]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1085]);
	}
	
	(
		(
			a1 = ':' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithInitCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_40_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1086]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1087]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1088]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1089]);
			}
			
			(
				a2_0 = parse_org_dresdenocl_language_ocl_TypeCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithInitCS();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_40_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1090]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1091]);
	}
	
	(
		a3 = EQUALITY_OPERATOR		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithInitCS();
				startIncompleteElement(element);
			}
			if (a3 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("EQUALITY_OPERATOR");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a3).getLine(), ((CommonToken) a3).getCharPositionInLine(), ((CommonToken) a3).getStartIndex(), ((CommonToken) a3).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_40_0_0_2, resolved, true);
				copyLocalizationInfos((CommonToken) a3, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1092]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1093]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1094]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1095]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1096]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1097]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1098]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1099]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1100]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1101]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1102]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1103]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1104]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1105]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1106]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1107]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1108]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1109]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1110]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1111]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1112]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1113]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1114]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1115]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1116]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1117]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1118]);
	}
	
	(
		a4_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithInitCS();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_40_0_0_3, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1119]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1120]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1121]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1122]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1123]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1124]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1125]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1126]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1127]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1128]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1129]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1130]);
	}
	
;

parse_org_dresdenocl_language_ocl_VariableDeclarationWithInitListCS returns [org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS element = null]
@init{
}
:
	(
		a0_0 = parse_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithInitListCS();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_41_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1131]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1132]);
	}
	
	(
		(
			a1 = ',' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithInitListCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_41_0_0_1_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitListCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1133]);
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitListCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1134]);
			}
			
			(
				a2_0 = parse_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithInitListCS();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_41_0_0_1_0_0_2, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1135]);
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1136]);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1137]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1138]);
	}
	
;

parse_org_dresdenocl_language_ocl_PathNameSimpleCS returns [org.dresdenocl.language.ocl.PathNameSimpleCS element = null]
@init{
}
:
	(
		a0 = SIMPLE_NAME		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPathNameSimpleCS();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PATH_NAME_SIMPLE_CS__NAMED_ELEMENT), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.dresdenocl.pivotmodel.NamedElement proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createNamespace();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.PathNameSimpleCS, org.dresdenocl.pivotmodel.NamedElement>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getPathNameSimpleCSNamedElementReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PATH_NAME_SIMPLE_CS__NAMED_ELEMENT), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PATH_NAME_SIMPLE_CS__NAMED_ELEMENT), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_45_0_0_0, proxy, true);
				copyLocalizationInfos((CommonToken) a0, element);
				copyLocalizationInfos((CommonToken) a0, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1139]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1140]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1141]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1142]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1143]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1144]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1145]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1146]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1147]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1148]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1149]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1150]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1151]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1152]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1153]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1154]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1155]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1156]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1157]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1158]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1159]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1160]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1161]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1162]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1163]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1164]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1165]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1166]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1167]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1168]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1169]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1170]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1171]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1172]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1173]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1174]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1175]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1176]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1177]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1178]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1179]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1180]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1181]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1182]);
	}
	
;

parse_org_dresdenocl_language_ocl_PathNamePathCS returns [org.dresdenocl.language.ocl.PathNamePathCS element = null]
@init{
}
:
	(
		(
			(
				a0_0 = parse_org_dresdenocl_language_ocl_UnreservedSimpleNameCS				{
					if (terminateParsing) {
						throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
					}
					if (element == null) {
						element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPathNamePathCS();
						startIncompleteElement(element);
					}
					if (a0_0 != null) {
						if (a0_0 != null) {
							Object value = a0_0;
							addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.PATH_NAME_PATH_CS__PATH_NAME, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_46_0_0_0_0_0_0, a0_0, true);
						copyLocalizationInfos(a0_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1183]);
			}
			
			a1 = '::' {
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPathNamePathCS();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_46_0_0_0_0_0_2, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPathNamePathCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1184]);
			}
			
		)
		
	)+	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPathNamePathCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1185]);
	}
	
	(
		a2_0 = parse_org_dresdenocl_language_ocl_UnreservedSimpleNameCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPathNamePathCS();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.PATH_NAME_PATH_CS__PATH_NAME, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_46_0_0_1, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1186]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1187]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1188]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1189]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1190]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1191]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1192]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1193]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1194]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1195]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1196]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1197]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1198]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1199]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1200]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1201]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1202]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1203]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1204]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1205]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1206]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1207]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1208]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1209]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1210]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1211]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1212]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1213]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1214]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1215]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1216]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1217]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1218]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1219]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1220]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1221]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1222]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1223]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1224]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1225]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1226]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1227]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1228]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1229]);
	}
	
;

parse_org_dresdenocl_language_ocl_NamedElementCS returns [org.dresdenocl.language.ocl.NamedElementCS element = null]
@init{
}
:
	(
		a0 = SIMPLE_NAME		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createNamedElementCS();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAMED_ELEMENT_CS__NAMED_ELEMENT), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.dresdenocl.pivotmodel.NamedElement proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createNamespace();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.NamedElementCS, org.dresdenocl.pivotmodel.NamedElement>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getNamedElementCSNamedElementReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAMED_ELEMENT_CS__NAMED_ELEMENT), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAMED_ELEMENT_CS__NAMED_ELEMENT), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_47_0_0_0, proxy, true);
				copyLocalizationInfos((CommonToken) a0, element);
				copyLocalizationInfos((CommonToken) a0, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1230]);
	}
	
;

parse_org_dresdenocl_language_ocl_CollectionRangeCS returns [org.dresdenocl.language.ocl.CollectionRangeCS element = null]
@init{
}
:
	(
		a0_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionRangeCS();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_RANGE_CS__FROM), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_51_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1231]);
	}
	
	a1 = '..' {
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionRangeCS();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_51_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1232]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1233]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1234]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1235]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1236]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1237]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1238]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1239]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1240]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1241]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1242]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1243]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1244]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1245]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1246]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1247]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1248]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1249]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1250]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1251]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1252]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1253]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1254]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1255]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1256]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1257]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1258]);
	}
	
	(
		a2_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionRangeCS();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_RANGE_CS__TO), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_51_0_0_4, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1259]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1260]);
	}
	
;

parse_org_dresdenocl_language_ocl_CollectionLiteralPartsOclExpCS returns [org.dresdenocl.language.ocl.CollectionLiteralPartsOclExpCS element = null]
@init{
}
:
	(
		a0_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionLiteralPartsOclExpCS();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_PARTS_OCL_EXP_CS__OCL_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_52_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1261]);
		addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1262]);
	}
	
;

parseop_OclExpressionCS_level_4 returns [org.dresdenocl.language.ocl.OclExpressionCS element = null]
@init{
}
:
	leftArg = parseop_OclExpressionCS_level_5	((
		()
		{ element = null; }
		(
			a0 = IMPLIES_OPERATOR			
			{
				if (terminateParsing) {
					throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
				}
				if (element == null) {
					element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalImpliesOperationCallExpCS();
					startIncompleteElement(element);
				}
				if (a0 != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IMPLIES_OPERATOR");
					tokenResolver.setOptions(getOptions());
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_19_0_0_1, resolved, true);
					copyLocalizationInfos((CommonToken) a0, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1263]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1264]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1265]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1266]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1267]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1268]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1269]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1270]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1271]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1272]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1273]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1274]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1275]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1276]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1277]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1278]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1279]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1280]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1281]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1282]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1283]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1284]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1285]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1286]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1287]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1288]);
			addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1289]);
		}
		
		rightArg = parseop_OclExpressionCS_level_5		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalImpliesOperationCallExpCS();
				startIncompleteElement(element);
			}
			if (leftArg != null) {
				if (leftArg != null) {
					Object value = leftArg;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__SOURCE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_19_0_0_0, leftArg, true);
				copyLocalizationInfos(leftArg, element);
			}
		}
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalImpliesOperationCallExpCS();
				startIncompleteElement(element);
			}
			if (rightArg != null) {
				if (rightArg != null) {
					Object value = rightArg;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__TARGET), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_19_0_0_2, rightArg, true);
				copyLocalizationInfos(rightArg, element);
			}
		}
		{ leftArg = element; /* this may become an argument in the next iteration */ }
	)+ | /* epsilon */ { element = leftArg; }
	
)
;

parseop_OclExpressionCS_level_5 returns [org.dresdenocl.language.ocl.OclExpressionCS element = null]
@init{
}
:
leftArg = parseop_OclExpressionCS_level_6((
	()
	{ element = null; }
	(
		a0 = XOR_OPERATOR		
		{
			if (terminateParsing) {
				throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
			}
			if (element == null) {
				element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalXorOperationCallExpCS();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("XOR_OPERATOR");
				tokenResolver.setOptions(getOptions());
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__OPERATION_NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__OPERATION_NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_20_0_0_1, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1290]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1291]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1292]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1293]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1294]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1295]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1296]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1297]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1298]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1299]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1300]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1301]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1302]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1303]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1304]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1305]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1306]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1307]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1308]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1309]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1310]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1311]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1312]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1313]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1314]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1315]);
		addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1316]);
	}
	
	rightArg = parseop_OclExpressionCS_level_6	{
		if (terminateParsing) {
			throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
		}
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalXorOperationCallExpCS();
			startIncompleteElement(element);
		}
		if (leftArg != null) {
			if (leftArg != null) {
				Object value = leftArg;
				element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__SOURCE), value);
				completedElement(value, true);
			}
			collectHiddenTokens(element);
			retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_20_0_0_0, leftArg, true);
			copyLocalizationInfos(leftArg, element);
		}
	}
	{
		if (terminateParsing) {
			throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
		}
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalXorOperationCallExpCS();
			startIncompleteElement(element);
		}
		if (rightArg != null) {
			if (rightArg != null) {
				Object value = rightArg;
				element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__TARGET), value);
				completedElement(value, true);
			}
			collectHiddenTokens(element);
			retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_20_0_0_2, rightArg, true);
			copyLocalizationInfos(rightArg, element);
		}
	}
	{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_OclExpressionCS_level_6 returns [org.dresdenocl.language.ocl.OclExpressionCS element = null]
@init{
}
:
leftArg = parseop_OclExpressionCS_level_7((
()
{ element = null; }
(
	a0 = OR_OPERATOR	
	{
		if (terminateParsing) {
			throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
		}
		if (element == null) {
			element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalOrOperationCallExpCS();
			startIncompleteElement(element);
		}
		if (a0 != null) {
			org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("OR_OPERATOR");
			tokenResolver.setOptions(getOptions());
			org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
			tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__OPERATION_NAME), result);
			Object resolvedObject = result.getResolvedToken();
			if (resolvedObject == null) {
				addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
			}
			java.lang.String resolved = (java.lang.String) resolvedObject;
			if (resolved != null) {
				Object value = resolved;
				element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__OPERATION_NAME), value);
				completedElement(value, false);
			}
			collectHiddenTokens(element);
			retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_21_0_0_1, resolved, true);
			copyLocalizationInfos((CommonToken) a0, element);
		}
	}
)
{
	// expected elements (follow set)
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1317]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1318]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1319]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1320]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1321]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1322]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1323]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1324]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1325]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1326]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1327]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1328]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1329]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1330]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1331]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1332]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1333]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1334]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1335]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1336]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1337]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1338]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1339]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1340]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1341]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1342]);
	addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1343]);
}

rightArg = parseop_OclExpressionCS_level_7{
	if (terminateParsing) {
		throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
	}
	if (element == null) {
		element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalOrOperationCallExpCS();
		startIncompleteElement(element);
	}
	if (leftArg != null) {
		if (leftArg != null) {
			Object value = leftArg;
			element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__SOURCE), value);
			completedElement(value, true);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_21_0_0_0, leftArg, true);
		copyLocalizationInfos(leftArg, element);
	}
}
{
	if (terminateParsing) {
		throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
	}
	if (element == null) {
		element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalOrOperationCallExpCS();
		startIncompleteElement(element);
	}
	if (rightArg != null) {
		if (rightArg != null) {
			Object value = rightArg;
			element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__TARGET), value);
			completedElement(value, true);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_21_0_0_2, rightArg, true);
		copyLocalizationInfos(rightArg, element);
	}
}
{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_OclExpressionCS_level_7 returns [org.dresdenocl.language.ocl.OclExpressionCS element = null]
@init{
}
:
leftArg = parseop_OclExpressionCS_level_8((
()
{ element = null; }
(
a0 = AND_OPERATOR
{
	if (terminateParsing) {
		throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
	}
	if (element == null) {
		element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalAndOperationCallExpCS();
		startIncompleteElement(element);
	}
	if (a0 != null) {
		org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("AND_OPERATOR");
		tokenResolver.setOptions(getOptions());
		org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
		tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__OPERATION_NAME), result);
		Object resolvedObject = result.getResolvedToken();
		if (resolvedObject == null) {
			addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
		}
		java.lang.String resolved = (java.lang.String) resolvedObject;
		if (resolved != null) {
			Object value = resolved;
			element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__OPERATION_NAME), value);
			completedElement(value, false);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_22_0_0_1, resolved, true);
		copyLocalizationInfos((CommonToken) a0, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1344]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1345]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1346]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1347]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1348]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1349]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1350]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1351]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1352]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1353]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1354]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1355]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1356]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1357]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1358]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1359]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1360]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1361]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1362]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1363]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1364]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1365]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1366]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1367]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1368]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1369]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1370]);
}

rightArg = parseop_OclExpressionCS_level_8{
if (terminateParsing) {
	throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
	element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalAndOperationCallExpCS();
	startIncompleteElement(element);
}
if (leftArg != null) {
	if (leftArg != null) {
		Object value = leftArg;
		element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__SOURCE), value);
		completedElement(value, true);
	}
	collectHiddenTokens(element);
	retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_22_0_0_0, leftArg, true);
	copyLocalizationInfos(leftArg, element);
}
}
{
if (terminateParsing) {
	throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
	element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalAndOperationCallExpCS();
	startIncompleteElement(element);
}
if (rightArg != null) {
	if (rightArg != null) {
		Object value = rightArg;
		element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__TARGET), value);
		completedElement(value, true);
	}
	collectHiddenTokens(element);
	retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_22_0_0_2, rightArg, true);
	copyLocalizationInfos(rightArg, element);
}
}
{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_OclExpressionCS_level_8 returns [org.dresdenocl.language.ocl.OclExpressionCS element = null]
@init{
}
:
leftArg = parseop_OclExpressionCS_level_9((
()
{ element = null; }
(
(
a0 = EQUALITY_OPERATOR
{
	if (terminateParsing) {
		throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
	}
	if (element == null) {
		element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createEqualityOperationCallExpCS();
		startIncompleteElement(element);
	}
	if (a0 != null) {
		org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("EQUALITY_OPERATOR");
		tokenResolver.setOptions(getOptions());
		org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
		tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__OPERATION_NAME), result);
		Object resolvedObject = result.getResolvedToken();
		if (resolvedObject == null) {
			addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
		}
		java.lang.String resolved = (java.lang.String) resolvedObject;
		if (resolved != null) {
			Object value = resolved;
			element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__OPERATION_NAME), value);
			completedElement(value, false);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_23_0_0_1_0_0_0, resolved, true);
		copyLocalizationInfos((CommonToken) a0, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1371]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1372]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1373]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1374]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1375]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1376]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1377]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1378]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1379]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1380]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1381]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1382]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1383]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1384]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1385]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1386]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1387]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1388]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1389]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1390]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1391]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1392]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1393]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1394]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1395]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1396]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1397]);
}


|(
a1 = NEQUALITY_OPERATOR
{
	if (terminateParsing) {
		throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
	}
	if (element == null) {
		element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createEqualityOperationCallExpCS();
		startIncompleteElement(element);
	}
	if (a1 != null) {
		org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("NEQUALITY_OPERATOR");
		tokenResolver.setOptions(getOptions());
		org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
		tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__OPERATION_NAME), result);
		Object resolvedObject = result.getResolvedToken();
		if (resolvedObject == null) {
			addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
		}
		java.lang.String resolved = (java.lang.String) resolvedObject;
		if (resolved != null) {
			Object value = resolved;
			element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__OPERATION_NAME), value);
			completedElement(value, false);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_23_0_0_1_0_1_0, resolved, true);
		copyLocalizationInfos((CommonToken) a1, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1398]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1399]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1400]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1401]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1402]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1403]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1404]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1405]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1406]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1407]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1408]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1409]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1410]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1411]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1412]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1413]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1414]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1415]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1416]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1417]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1418]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1419]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1420]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1421]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1422]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1423]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1424]);
}

)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1425]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1426]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1427]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1428]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1429]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1430]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1431]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1432]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1433]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1434]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1435]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1436]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1437]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1438]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1439]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1440]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1441]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1442]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1443]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1444]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1445]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1446]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1447]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1448]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1449]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1450]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1451]);
}

rightArg = parseop_OclExpressionCS_level_9{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createEqualityOperationCallExpCS();
startIncompleteElement(element);
}
if (leftArg != null) {
if (leftArg != null) {
	Object value = leftArg;
	element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__SOURCE), value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_23_0_0_0, leftArg, true);
copyLocalizationInfos(leftArg, element);
}
}
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createEqualityOperationCallExpCS();
startIncompleteElement(element);
}
if (rightArg != null) {
if (rightArg != null) {
	Object value = rightArg;
	element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__TARGET), value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_23_0_0_2, rightArg, true);
copyLocalizationInfos(rightArg, element);
}
}
{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_OclExpressionCS_level_9 returns [org.dresdenocl.language.ocl.OclExpressionCS element = null]
@init{
}
:
leftArg = parseop_OclExpressionCS_level_11((
()
{ element = null; }
(
a0 = RELATIONAL_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createRelationalOperationCallExpCS();
startIncompleteElement(element);
}
if (a0 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("RELATIONAL_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
	addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
	Object value = resolved;
	element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__OPERATION_NAME), value);
	completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_24_0_0_1, resolved, true);
copyLocalizationInfos((CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1452]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1453]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1454]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1455]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1456]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1457]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1458]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1459]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1460]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1461]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1462]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1463]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1464]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1465]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1466]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1467]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1468]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1469]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1470]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1471]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1472]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1473]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1474]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1475]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1476]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1477]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1478]);
}

rightArg = parseop_OclExpressionCS_level_11{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createRelationalOperationCallExpCS();
startIncompleteElement(element);
}
if (leftArg != null) {
if (leftArg != null) {
Object value = leftArg;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__SOURCE), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_24_0_0_0, leftArg, true);
copyLocalizationInfos(leftArg, element);
}
}
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createRelationalOperationCallExpCS();
startIncompleteElement(element);
}
if (rightArg != null) {
if (rightArg != null) {
Object value = rightArg;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__TARGET), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_24_0_0_2, rightArg, true);
copyLocalizationInfos(rightArg, element);
}
}
{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_OclExpressionCS_level_11 returns [org.dresdenocl.language.ocl.OclExpressionCS element = null]
@init{
}
:
leftArg = parseop_OclExpressionCS_level_12((
()
{ element = null; }
(
a0 = ADDITIVE_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createAdditiveOperationCallExpCS();
startIncompleteElement(element);
}
if (a0 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("ADDITIVE_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_25_0_0_1, resolved, true);
copyLocalizationInfos((CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1479]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1480]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1481]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1482]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1483]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1484]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1485]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1486]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1487]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1488]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1489]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1490]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1491]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1492]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1493]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1494]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1495]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1496]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1497]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1498]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1499]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1500]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1501]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1502]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1503]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1504]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1505]);
}

rightArg = parseop_OclExpressionCS_level_12{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createAdditiveOperationCallExpCS();
startIncompleteElement(element);
}
if (leftArg != null) {
if (leftArg != null) {
Object value = leftArg;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__SOURCE), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_25_0_0_0, leftArg, true);
copyLocalizationInfos(leftArg, element);
}
}
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createAdditiveOperationCallExpCS();
startIncompleteElement(element);
}
if (rightArg != null) {
if (rightArg != null) {
Object value = rightArg;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__TARGET), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_25_0_0_2, rightArg, true);
copyLocalizationInfos(rightArg, element);
}
}
{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_OclExpressionCS_level_12 returns [org.dresdenocl.language.ocl.OclExpressionCS element = null]
@init{
}
:
leftArg = parseop_OclExpressionCS_level_13((
()
{ element = null; }
(
a0 = MULT_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createMultOperationCallExpCS();
startIncompleteElement(element);
}
if (a0 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("MULT_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_26_0_0_1, resolved, true);
copyLocalizationInfos((CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1506]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1507]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1508]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1509]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1510]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1511]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1512]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1513]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1514]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1515]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1516]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1517]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1518]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1519]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1520]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1521]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1522]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1523]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1524]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1525]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1526]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1527]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1528]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1529]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1530]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1531]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1532]);
}

rightArg = parseop_OclExpressionCS_level_13{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createMultOperationCallExpCS();
startIncompleteElement(element);
}
if (leftArg != null) {
if (leftArg != null) {
Object value = leftArg;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__SOURCE), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_26_0_0_0, leftArg, true);
copyLocalizationInfos(leftArg, element);
}
}
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createMultOperationCallExpCS();
startIncompleteElement(element);
}
if (rightArg != null) {
if (rightArg != null) {
Object value = rightArg;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__TARGET), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_26_0_0_2, rightArg, true);
copyLocalizationInfos(rightArg, element);
}
}
{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_OclExpressionCS_level_13 returns [org.dresdenocl.language.ocl.OclExpressionCS element = null]
@init{
}
:
(
a0 = ADDITIVE_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createUnaryOperationCallExpCS();
startIncompleteElement(element);
}
if (a0 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("ADDITIVE_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.UNARY_OPERATION_CALL_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.UNARY_OPERATION_CALL_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_27_0_0_0, resolved, true);
copyLocalizationInfos((CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1533]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1534]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1535]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1536]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1537]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1538]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1539]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1540]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1541]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1542]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1543]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1544]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1545]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1546]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1547]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1548]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1549]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1550]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1551]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1552]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1553]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1554]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1555]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1556]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1557]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1558]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1559]);
}

arg = parseop_OclExpressionCS_level_14{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createUnaryOperationCallExpCS();
startIncompleteElement(element);
}
if (arg != null) {
if (arg != null) {
Object value = arg;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.UNARY_OPERATION_CALL_EXP_CS__TARGET), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_27_0_0_2, arg, true);
copyLocalizationInfos(arg, element);
}
}
|
(
a2 = NOT_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalNotOperationCallExpCS();
startIncompleteElement(element);
}
if (a2 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("NOT_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_NOT_OPERATION_CALL_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_NOT_OPERATION_CALL_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_28_0_0_0, resolved, true);
copyLocalizationInfos((CommonToken) a2, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1560]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1561]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1562]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1563]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1564]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1565]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1566]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1567]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1568]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1569]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1570]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1571]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1572]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1573]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1574]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1575]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1576]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1577]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1578]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1579]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1580]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1581]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1582]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1583]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1584]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1585]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1586]);
}

arg = parseop_OclExpressionCS_level_14{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLogicalNotOperationCallExpCS();
startIncompleteElement(element);
}
if (arg != null) {
if (arg != null) {
Object value = arg;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_NOT_OPERATION_CALL_EXP_CS__TARGET), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_28_0_0_2, arg, true);
copyLocalizationInfos(arg, element);
}
}
|

arg = parseop_OclExpressionCS_level_14{ element = arg; }
;

parseop_OclExpressionCS_level_14 returns [org.dresdenocl.language.ocl.OclExpressionCS element = null]
@init{
}
:
arg = parseop_OclExpressionCS_level_20(
(
a0 = NAVIGATION_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createNavigationCallExp();
startIncompleteElement(element);
}
if (a0 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("NAVIGATION_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR, value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_29_0_0_2, resolved, true);
copyLocalizationInfos((CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1587]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1588]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1589]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1590]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1591]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1592]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1593]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1594]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1595]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1596]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1597]);
}

(
a1_0 = parse_org_dresdenocl_language_ocl_ImplicitFeatureCallCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createNavigationCallExp();
startIncompleteElement(element);
}
if (a1_0 != null) {
if (a1_0 != null) {
Object value = a1_0;
addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS, value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_29_0_0_4, a1_0, true);
copyLocalizationInfos(a1_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1598]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1599]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1600]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1601]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1602]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1603]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1604]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1605]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1606]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1607]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1608]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1609]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1610]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1611]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1612]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1613]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1614]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1615]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1616]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1617]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1618]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1619]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1620]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1621]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1622]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1623]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1624]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1625]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1626]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1627]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1628]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1629]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1630]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1631]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1632]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1633]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1634]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1635]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1636]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1637]);
}

(
(
(
a2 = NAVIGATION_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createNavigationCallExp();
startIncompleteElement(element);
}
if (a2 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("NAVIGATION_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR, value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_29_0_0_5_0_0_1, resolved, true);
copyLocalizationInfos((CommonToken) a2, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1638]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1639]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1640]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1641]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1642]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1643]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1644]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1645]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1646]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1647]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1648]);
}

(
a3_0 = parse_org_dresdenocl_language_ocl_ImplicitFeatureCallCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createNavigationCallExp();
startIncompleteElement(element);
}
if (a3_0 != null) {
if (a3_0 != null) {
Object value = a3_0;
addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS, value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_29_0_0_5_0_0_3, a3_0, true);
copyLocalizationInfos(a3_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1649]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1650]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1651]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1652]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1653]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1654]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1655]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1656]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1657]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1658]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1659]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1660]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1661]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1662]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1663]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1664]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1665]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1666]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1667]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1668]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1669]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1670]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1671]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1672]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1673]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1674]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1675]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1676]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1677]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1678]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1679]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1680]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1681]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1682]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1683]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1684]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1685]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1686]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1687]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1688]);
}

)

)*{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1689]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1690]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1691]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1692]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1693]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1694]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1695]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1696]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1697]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1698]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1699]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1700]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1701]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1702]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1703]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1704]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1705]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1706]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1707]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1708]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1709]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1710]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1711]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1712]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1713]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1714]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1715]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1716]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1717]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1718]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1719]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1720]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1721]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1722]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1723]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1724]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1725]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1726]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1727]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1728]);
}

{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createNavigationCallExp();
startIncompleteElement(element);
}
if (arg != null) {
if (arg != null) {
Object value = arg;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__SOURCE), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_29_0_0_0, arg, true);
copyLocalizationInfos(arg, element);
}
}
|
/* epsilon */ { element = arg; } 
)
;

parseop_OclExpressionCS_level_20 returns [org.dresdenocl.language.ocl.OclExpressionCS element = null]
@init{
}
:
c0 = parse_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_dresdenocl_language_ocl_StaticOperationCallExpCS{ element = c1; /* this is a subclass or primitive expression choice */ }
|c2 = parse_org_dresdenocl_language_ocl_ModelElementCS{ element = c2; /* this is a subclass or primitive expression choice */ }
|c3 = parse_org_dresdenocl_language_ocl_TupleLiteralExpCS{ element = c3; /* this is a subclass or primitive expression choice */ }
|c4 = parse_org_dresdenocl_language_ocl_IfExpCS{ element = c4; /* this is a subclass or primitive expression choice */ }
|c5 = parse_org_dresdenocl_language_ocl_CollectionLiteralExpCS{ element = c5; /* this is a subclass or primitive expression choice */ }
|c6 = parse_org_dresdenocl_language_ocl_CollectionTypeLiteralExpCS{ element = c6; /* this is a subclass or primitive expression choice */ }
|c7 = parse_org_dresdenocl_language_ocl_TupleTypeLiteralExpCS{ element = c7; /* this is a subclass or primitive expression choice */ }
|c8 = parse_org_dresdenocl_language_ocl_PropertyCallOnSelfExpCS{ element = c8; /* this is a subclass or primitive expression choice */ }
|c9 = parse_org_dresdenocl_language_ocl_LetExpCS{ element = c9; /* this is a subclass or primitive expression choice */ }
|c10 = parse_org_dresdenocl_language_ocl_RealLiteralExpCS{ element = c10; /* this is a subclass or primitive expression choice */ }
|c11 = parse_org_dresdenocl_language_ocl_IntegerLiteralExpCS{ element = c11; /* this is a subclass or primitive expression choice */ }
|c12 = parse_org_dresdenocl_language_ocl_BooleanLiteralExpCS{ element = c12; /* this is a subclass or primitive expression choice */ }
|c13 = parse_org_dresdenocl_language_ocl_StringLiteralExpCS{ element = c13; /* this is a subclass or primitive expression choice */ }
|c14 = parse_org_dresdenocl_language_ocl_InvalidLiteralExpCS{ element = c14; /* this is a subclass or primitive expression choice */ }
|c15 = parse_org_dresdenocl_language_ocl_NullLiteralExpCS{ element = c15; /* this is a subclass or primitive expression choice */ }
|c16 = parse_org_dresdenocl_language_ocl_BracketExpCS{ element = c16; /* this is a subclass or primitive expression choice */ }
;

parse_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS returns [org.dresdenocl.language.ocl.OperationCallOnSelfExpCS element = null]
@init{
}
:
(
(
a0 = EQUALITY_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a0 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("EQUALITY_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
}
String resolved = (String) resolvedObject;
org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
collectHiddenTokens(element);
registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_0_0_0_0, proxy, true);
copyLocalizationInfos((CommonToken) a0, element);
copyLocalizationInfos((CommonToken) a0, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1729]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1730]);
}


|(
a1 = NEQUALITY_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a1 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("NEQUALITY_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
}
String resolved = (String) resolvedObject;
org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
collectHiddenTokens(element);
registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_0_0_1_0, proxy, true);
copyLocalizationInfos((CommonToken) a1, element);
copyLocalizationInfos((CommonToken) a1, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1731]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1732]);
}


|(
a2 = NOT_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a2 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("NOT_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
}
String resolved = (String) resolvedObject;
org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
collectHiddenTokens(element);
registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_0_0_2_0, proxy, true);
copyLocalizationInfos((CommonToken) a2, element);
copyLocalizationInfos((CommonToken) a2, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1733]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1734]);
}


|(
a3 = AND_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a3 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("AND_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a3).getLine(), ((CommonToken) a3).getCharPositionInLine(), ((CommonToken) a3).getStartIndex(), ((CommonToken) a3).getStopIndex());
}
String resolved = (String) resolvedObject;
org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
collectHiddenTokens(element);
registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_0_0_3_0, proxy, true);
copyLocalizationInfos((CommonToken) a3, element);
copyLocalizationInfos((CommonToken) a3, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1735]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1736]);
}


|(
a4 = OR_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a4 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("OR_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a4).getLine(), ((CommonToken) a4).getCharPositionInLine(), ((CommonToken) a4).getStartIndex(), ((CommonToken) a4).getStopIndex());
}
String resolved = (String) resolvedObject;
org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
collectHiddenTokens(element);
registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_0_0_4_0, proxy, true);
copyLocalizationInfos((CommonToken) a4, element);
copyLocalizationInfos((CommonToken) a4, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1737]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1738]);
}


|(
a5 = XOR_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a5 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("XOR_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a5.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a5).getLine(), ((CommonToken) a5).getCharPositionInLine(), ((CommonToken) a5).getStartIndex(), ((CommonToken) a5).getStopIndex());
}
String resolved = (String) resolvedObject;
org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
collectHiddenTokens(element);
registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_0_0_5_0, proxy, true);
copyLocalizationInfos((CommonToken) a5, element);
copyLocalizationInfos((CommonToken) a5, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1739]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1740]);
}


|(
a6 = IMPLIES_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a6 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IMPLIES_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a6.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a6).getLine(), ((CommonToken) a6).getCharPositionInLine(), ((CommonToken) a6).getStartIndex(), ((CommonToken) a6).getStopIndex());
}
String resolved = (String) resolvedObject;
org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
collectHiddenTokens(element);
registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_0_0_6_0, proxy, true);
copyLocalizationInfos((CommonToken) a6, element);
copyLocalizationInfos((CommonToken) a6, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1741]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1742]);
}


|(
a7 = SIMPLE_NAME
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a7 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a7.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a7).getLine(), ((CommonToken) a7).getCharPositionInLine(), ((CommonToken) a7).getStartIndex(), ((CommonToken) a7).getStopIndex());
}
String resolved = (String) resolvedObject;
org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
collectHiddenTokens(element);
registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.OperationCallBaseExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_0_0_7_0, proxy, true);
copyLocalizationInfos((CommonToken) a7, element);
copyLocalizationInfos((CommonToken) a7, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1743]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1744]);
}

)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1745]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1746]);
}

(
(
(
a8 = IS_MARKED_PRE
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a8 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IS_MARKED_PRE");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a8.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a8).getLine(), ((CommonToken) a8).getCharPositionInLine(), ((CommonToken) a8).getStartIndex(), ((CommonToken) a8).getStopIndex());
}
java.lang.Boolean resolved = (java.lang.Boolean) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_2_0_0_0, resolved, true);
copyLocalizationInfos((CommonToken) a8, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1747]);
}

)

)?{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1748]);
}

a9 = '(' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_3, null, true);
copyLocalizationInfos((CommonToken)a9, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1749]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1750]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1751]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1752]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1753]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1754]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1755]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1756]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1757]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1758]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1759]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1760]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1761]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1762]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1763]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1764]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1765]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1766]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1767]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1768]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1769]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1770]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1771]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1772]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1773]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1774]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1775]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1776]);
}

(
(
(
a10_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a10_0 != null) {
if (a10_0 != null) {
Object value = a10_0;
addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__ARGUMENTS, value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_4_0_0_1, a10_0, true);
copyLocalizationInfos(a10_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1777]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1778]);
}

(
(
a11 = ',' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_4_0_0_2_0_0_0, null, true);
copyLocalizationInfos((CommonToken)a11, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1779]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1780]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1781]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1782]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1783]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1784]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1785]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1786]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1787]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1788]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1789]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1790]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1791]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1792]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1793]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1794]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1795]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1796]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1797]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1798]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1799]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1800]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1801]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1802]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1803]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1804]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1805]);
}

(
a12_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a12_0 != null) {
if (a12_0 != null) {
	Object value = a12_0;
	addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__ARGUMENTS, value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_4_0_0_2_0_0_1, a12_0, true);
copyLocalizationInfos(a12_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1806]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1807]);
}

)

)*{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1808]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1809]);
}

)

)?{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1810]);
}

a13 = ')' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42_0_0_6, null, true);
copyLocalizationInfos((CommonToken)a13, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1811]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1812]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1813]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1814]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1815]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1816]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1817]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1818]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1819]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1820]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1821]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1822]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1823]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1824]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1825]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1826]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1827]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1828]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1829]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1830]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1831]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1832]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1833]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1834]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1835]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1836]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1837]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1838]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1839]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1840]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1841]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1842]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1843]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1844]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1845]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1846]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1847]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1848]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1849]);
}

;

parse_org_dresdenocl_language_ocl_StaticOperationCallExpCS returns [org.dresdenocl.language.ocl.StaticOperationCallExpCS element = null]
@init{
}
:
(
a0_0 = parse_org_dresdenocl_language_ocl_ModelElementCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createStaticOperationCallExpCS();
startIncompleteElement(element);
}
if (a0_0 != null) {
if (a0_0 != null) {
Object value = a0_0;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_43_0_0_0, a0_0, true);
copyLocalizationInfos(a0_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1850]);
}

a1 = '::' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createStaticOperationCallExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_43_0_0_2, null, true);
copyLocalizationInfos((CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1851]);
}

(
a2 = SIMPLE_NAME
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createStaticOperationCallExpCS();
startIncompleteElement(element);
}
if (a2 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
}
String resolved = (String) resolvedObject;
org.dresdenocl.pivotmodel.Operation proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createOperation();
collectHiddenTokens(element);
registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.StaticOperationCallExpCS, org.dresdenocl.pivotmodel.Operation>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getStaticOperationCallExpCSOperationNameReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_43_0_0_4, proxy, true);
copyLocalizationInfos((CommonToken) a2, element);
copyLocalizationInfos((CommonToken) a2, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1852]);
}

a3 = '(' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createStaticOperationCallExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_43_0_0_6, null, true);
copyLocalizationInfos((CommonToken)a3, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1853]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1854]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1855]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1856]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1857]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1858]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1859]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1860]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1861]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1862]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1863]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1864]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1865]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1866]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1867]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1868]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1869]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1870]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1871]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1872]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1873]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1874]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1875]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1876]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1877]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1878]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1879]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1880]);
}

(
(
(
a4_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createStaticOperationCallExpCS();
startIncompleteElement(element);
}
if (a4_0 != null) {
if (a4_0 != null) {
Object value = a4_0;
addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS, value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_43_0_0_7_0_0_1, a4_0, true);
copyLocalizationInfos(a4_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1881]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1882]);
}

(
(
a5 = ',' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createStaticOperationCallExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_43_0_0_7_0_0_2_0_0_0, null, true);
copyLocalizationInfos((CommonToken)a5, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1883]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1884]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1885]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1886]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1887]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1888]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1889]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1890]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1891]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1892]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1893]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1894]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1895]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1896]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1897]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1898]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1899]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1900]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1901]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1902]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1903]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1904]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1905]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1906]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1907]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1908]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1909]);
}

(
a6_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createStaticOperationCallExpCS();
startIncompleteElement(element);
}
if (a6_0 != null) {
if (a6_0 != null) {
	Object value = a6_0;
	addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS, value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_43_0_0_7_0_0_2_0_0_1, a6_0, true);
copyLocalizationInfos(a6_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1910]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1911]);
}

)

)*{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1912]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1913]);
}

)

)?{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1914]);
}

a7 = ')' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createStaticOperationCallExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_43_0_0_9, null, true);
copyLocalizationInfos((CommonToken)a7, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1915]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1916]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1917]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1918]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1919]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1920]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1921]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1922]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1923]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1924]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1925]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1926]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1927]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1928]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1929]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1930]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1931]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1932]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1933]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1934]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1935]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1936]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1937]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1938]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1939]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1940]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1941]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1942]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1943]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1944]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1945]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1946]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1947]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1948]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1949]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1950]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1951]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1952]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1953]);
}

;

parse_org_dresdenocl_language_ocl_ModelElementCS returns [org.dresdenocl.language.ocl.ModelElementCS element = null]
@init{
}
:
(
a0_0 = parse_org_dresdenocl_language_ocl_PathNameCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createModelElementCS();
startIncompleteElement(element);
}
if (a0_0 != null) {
if (a0_0 != null) {
Object value = a0_0;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MODEL_ELEMENT_CS__PATH_NAME), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_44_0_0_0, a0_0, true);
copyLocalizationInfos(a0_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1954]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1955]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1956]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1957]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1958]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1959]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1960]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1961]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1962]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1963]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1964]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1965]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1966]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1967]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1968]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1969]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1970]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1971]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1972]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1973]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1974]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1975]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1976]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1977]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1978]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1979]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1980]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1981]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1982]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1983]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1984]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1985]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1986]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1987]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1988]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1989]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1990]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1991]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1992]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1993]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1994]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1995]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1996]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1997]);
}

;

parse_org_dresdenocl_language_ocl_TupleLiteralExpCS returns [org.dresdenocl.language.ocl.TupleLiteralExpCS element = null]
@init{
}
:
a0 = 'Tuple' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createTupleLiteralExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_48_0_0_0, null, true);
copyLocalizationInfos((CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1998]);
}

a1 = '{' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createTupleLiteralExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_48_0_0_1, null, true);
copyLocalizationInfos((CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[1999]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2000]);
}

(
a2_0 = parse_org_dresdenocl_language_ocl_VariableDeclarationWithInitListCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createTupleLiteralExpCS();
startIncompleteElement(element);
}
if (a2_0 != null) {
if (a2_0 != null) {
Object value = a2_0;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_48_0_0_2, a2_0, true);
copyLocalizationInfos(a2_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2001]);
}

a3 = '}' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createTupleLiteralExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_48_0_0_3, null, true);
copyLocalizationInfos((CommonToken)a3, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2002]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2003]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2004]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2005]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2006]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2007]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2008]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2009]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2010]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2011]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2012]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2013]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2014]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2015]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2016]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2017]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2018]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2019]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2020]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2021]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2022]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2023]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2024]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2025]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2026]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2027]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2028]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2029]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2030]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2031]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2032]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2033]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2034]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2035]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2036]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2037]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2038]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2039]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2040]);
}

;

parse_org_dresdenocl_language_ocl_IfExpCS returns [org.dresdenocl.language.ocl.IfExpCS element = null]
@init{
}
:
a0 = 'if' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIfExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_49_0_0_0, null, true);
copyLocalizationInfos((CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2041]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2042]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2043]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2044]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2045]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2046]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2047]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2048]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2049]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2050]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2051]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2052]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2053]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2054]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2055]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2056]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2057]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2058]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2059]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2060]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2061]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2062]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2063]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2064]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2065]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2066]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2067]);
}

(
a1_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIfExpCS();
startIncompleteElement(element);
}
if (a1_0 != null) {
if (a1_0 != null) {
Object value = a1_0;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IF_EXP_CS__CONDITION), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_49_0_0_1, a1_0, true);
copyLocalizationInfos(a1_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2068]);
}

a2 = 'then' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIfExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_49_0_0_3, null, true);
copyLocalizationInfos((CommonToken)a2, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2069]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2070]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2071]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2072]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2073]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2074]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2075]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2076]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2077]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2078]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2079]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2080]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2081]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2082]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2083]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2084]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2085]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2086]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2087]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2088]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2089]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2090]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2091]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2092]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2093]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2094]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2095]);
}

(
a3_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIfExpCS();
startIncompleteElement(element);
}
if (a3_0 != null) {
if (a3_0 != null) {
Object value = a3_0;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IF_EXP_CS__THEN_BRANCH), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_49_0_0_5, a3_0, true);
copyLocalizationInfos(a3_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2096]);
}

a4 = 'else' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIfExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_49_0_0_7, null, true);
copyLocalizationInfos((CommonToken)a4, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2097]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2098]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2099]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2100]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2101]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2102]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2103]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2104]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2105]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2106]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2107]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2108]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2109]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2110]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2111]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2112]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2113]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2114]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2115]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2116]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2117]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2118]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2119]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2120]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2121]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2122]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2123]);
}

(
a5_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIfExpCS();
startIncompleteElement(element);
}
if (a5_0 != null) {
if (a5_0 != null) {
Object value = a5_0;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IF_EXP_CS__ELSE_BRANCH), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_49_0_0_9, a5_0, true);
copyLocalizationInfos(a5_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2124]);
}

a6 = 'endif' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIfExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_49_0_0_11, null, true);
copyLocalizationInfos((CommonToken)a6, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2125]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2126]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2127]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2128]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2129]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2130]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2131]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2132]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2133]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2134]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2135]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2136]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2137]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2138]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2139]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2140]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2141]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2142]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2143]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2144]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2145]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2146]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2147]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2148]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2149]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2150]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2151]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2152]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2153]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2154]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2155]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2156]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2157]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2158]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2159]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2160]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2161]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2162]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2163]);
}

;

parse_org_dresdenocl_language_ocl_CollectionLiteralExpCS returns [org.dresdenocl.language.ocl.CollectionLiteralExpCS element = null]
@init{
}
:
(
a0_0 = parse_org_dresdenocl_language_ocl_CollectionTypeIdentifierCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionLiteralExpCS();
startIncompleteElement(element);
}
if (a0_0 != null) {
if (a0_0 != null) {
Object value = a0_0;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_50_0_0_0, a0_0, true);
copyLocalizationInfos(a0_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2164]);
}

a1 = '{' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionLiteralExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_50_0_0_1, null, true);
copyLocalizationInfos((CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2165]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2166]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2167]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2168]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2169]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2170]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2171]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2172]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2173]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2174]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2175]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2176]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2177]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2178]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2179]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2180]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2181]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2182]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2183]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2184]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2185]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2186]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2187]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2188]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2189]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2190]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2191]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2192]);
}

(
(
(
a2_0 = parse_org_dresdenocl_language_ocl_CollectionLiteralPartsCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionLiteralExpCS();
startIncompleteElement(element);
}
if (a2_0 != null) {
if (a2_0 != null) {
Object value = a2_0;
addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS, value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_50_0_0_2_0_0_0, a2_0, true);
copyLocalizationInfos(a2_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2193]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2194]);
}

(
(
a3 = ',' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionLiteralExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_50_0_0_2_0_0_1_0_0_1, null, true);
copyLocalizationInfos((CommonToken)a3, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2195]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2196]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2197]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2198]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2199]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2200]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2201]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2202]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2203]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2204]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2205]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2206]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2207]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2208]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2209]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2210]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2211]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2212]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2213]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2214]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2215]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2216]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2217]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2218]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2219]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2220]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2221]);
}

(
a4_0 = parse_org_dresdenocl_language_ocl_CollectionLiteralPartsCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionLiteralExpCS();
startIncompleteElement(element);
}
if (a4_0 != null) {
if (a4_0 != null) {
	Object value = a4_0;
	addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS, value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_50_0_0_2_0_0_1_0_0_2, a4_0, true);
copyLocalizationInfos(a4_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2222]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2223]);
}

)

)*{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2224]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2225]);
}

)

)?{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2226]);
}

a5 = '}' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionLiteralExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_50_0_0_3, null, true);
copyLocalizationInfos((CommonToken)a5, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2227]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2228]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2229]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2230]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2231]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2232]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2233]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2234]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2235]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2236]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2237]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2238]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2239]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2240]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2241]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2242]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2243]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2244]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2245]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2246]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2247]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2248]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2249]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2250]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2251]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2252]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2253]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2254]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2255]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2256]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2257]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2258]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2259]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2260]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2261]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2262]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2263]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2264]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2265]);
}

;

parse_org_dresdenocl_language_ocl_CollectionTypeLiteralExpCS returns [org.dresdenocl.language.ocl.CollectionTypeLiteralExpCS element = null]
@init{
}
:
(
a0_0 = parse_org_dresdenocl_language_ocl_CollectionTypeIdentifierCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createCollectionTypeLiteralExpCS();
startIncompleteElement(element);
}
if (a0_0 != null) {
if (a0_0 != null) {
Object value = a0_0;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_53_0_0_0, a0_0, true);
copyLocalizationInfos(a0_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2266]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2267]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2268]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2269]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2270]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2271]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2272]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2273]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2274]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2275]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2276]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2277]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2278]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2279]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2280]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2281]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2282]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2283]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2284]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2285]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2286]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2287]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2288]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2289]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2290]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2291]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2292]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2293]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2294]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2295]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2296]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2297]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2298]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2299]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2300]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2301]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2302]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2303]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2304]);
}

;

parse_org_dresdenocl_language_ocl_TupleTypeLiteralExpCS returns [org.dresdenocl.language.ocl.TupleTypeLiteralExpCS element = null]
@init{
}
:
(
a0_0 = parse_org_dresdenocl_language_ocl_TupleTypeCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createTupleTypeLiteralExpCS();
startIncompleteElement(element);
}
if (a0_0 != null) {
if (a0_0 != null) {
Object value = a0_0;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_54_0_0_0, a0_0, true);
copyLocalizationInfos(a0_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2305]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2306]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2307]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2308]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2309]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2310]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2311]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2312]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2313]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2314]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2315]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2316]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2317]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2318]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2319]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2320]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2321]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2322]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2323]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2324]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2325]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2326]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2327]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2328]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2329]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2330]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2331]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2332]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2333]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2334]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2335]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2336]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2337]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2338]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2339]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2340]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2341]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2342]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2343]);
}

;

parse_org_dresdenocl_language_ocl_PropertyCallOnSelfExpCS returns [org.dresdenocl.language.ocl.PropertyCallOnSelfExpCS element = null]
@init{
}
:
(
a0 = SIMPLE_NAME
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPropertyCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a0 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
}
String resolved = (String) resolvedObject;
org.dresdenocl.pivotmodel.Property proxy = org.dresdenocl.pivotmodel.PivotModelFactory.eINSTANCE.createProperty();
collectHiddenTokens(element);
registerContextDependentProxy(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclContextDependentURIFragmentFactory<org.dresdenocl.language.ocl.PropertyCallBaseExpCS, org.dresdenocl.pivotmodel.Property>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getPropertyCallBaseExpCSPropertyReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_55_0_0_0, proxy, true);
copyLocalizationInfos((CommonToken) a0, element);
copyLocalizationInfos((CommonToken) a0, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2344]);
}

(
a1 = IS_MARKED_PRE
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createPropertyCallOnSelfExpCS();
startIncompleteElement(element);
}
if (a1 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IS_MARKED_PRE");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
}
java.lang.Boolean resolved = (java.lang.Boolean) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_55_0_0_2, resolved, true);
copyLocalizationInfos((CommonToken) a1, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2345]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2346]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2347]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2348]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2349]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2350]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2351]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2352]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2353]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2354]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2355]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2356]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2357]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2358]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2359]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2360]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2361]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2362]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2363]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2364]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2365]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2366]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2367]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2368]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2369]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2370]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2371]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2372]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2373]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2374]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2375]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2376]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2377]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2378]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2379]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2380]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2381]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2382]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2383]);
}

;

parse_org_dresdenocl_language_ocl_LetExpCS returns [org.dresdenocl.language.ocl.LetExpCS element = null]
@init{
}
:
a0 = 'let' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLetExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_56_0_0_0, null, true);
copyLocalizationInfos((CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2384]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2385]);
}

(
a1_0 = parse_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLetExpCS();
startIncompleteElement(element);
}
if (a1_0 != null) {
if (a1_0 != null) {
Object value = a1_0;
addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS, value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_56_0_0_1, a1_0, true);
copyLocalizationInfos(a1_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2386]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2387]);
}

(
(
a2 = ',' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLetExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_56_0_0_2_0_0_1, null, true);
copyLocalizationInfos((CommonToken)a2, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2388]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2389]);
}

(
a3_0 = parse_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLetExpCS();
startIncompleteElement(element);
}
if (a3_0 != null) {
if (a3_0 != null) {
Object value = a3_0;
addObjectToList(element, org.dresdenocl.language.ocl.OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS, value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_56_0_0_2_0_0_2, a3_0, true);
copyLocalizationInfos(a3_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2390]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2391]);
}

)

)*{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2392]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2393]);
}

a4 = 'in' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLetExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_56_0_0_3, null, true);
copyLocalizationInfos((CommonToken)a4, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2394]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2395]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2396]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2397]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2398]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2399]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2400]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2401]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2402]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2403]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2404]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2405]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2406]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2407]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2408]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2409]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2410]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2411]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2412]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2413]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2414]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2415]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2416]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2417]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2418]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2419]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2420]);
}

(
a5_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createLetExpCS();
startIncompleteElement(element);
}
if (a5_0 != null) {
if (a5_0 != null) {
Object value = a5_0;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LET_EXP_CS__OCL_EXPRESSION), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_56_0_0_5, a5_0, true);
copyLocalizationInfos(a5_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2421]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2422]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2423]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2424]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2425]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2426]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2427]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2428]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2429]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2430]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2431]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2432]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2433]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2434]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2435]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2436]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2437]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2438]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2439]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2440]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2441]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2442]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2443]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2444]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2445]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2446]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2447]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2448]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2449]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2450]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2451]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2452]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2453]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2454]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2455]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2456]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2457]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2458]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2459]);
}

;

parse_org_dresdenocl_language_ocl_RealLiteralExpCS returns [org.dresdenocl.language.ocl.RealLiteralExpCS element = null]
@init{
}
:
(
a0 = INTEGER_LITERAL
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createRealLiteralExpCS();
startIncompleteElement(element);
}
if (a0 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("INTEGER_LITERAL");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__INT_VALUE), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
}
java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__INT_VALUE), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_57_0_0_0, resolved, true);
copyLocalizationInfos((CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2460]);
}

(
a1 = NAVIGATION_OPERATOR
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createRealLiteralExpCS();
startIncompleteElement(element);
}
if (a1 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("NAVIGATION_OPERATOR");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_57_0_0_2, resolved, true);
copyLocalizationInfos((CommonToken) a1, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2461]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2462]);
}

(
(
a2 = INTEGER_0
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createRealLiteralExpCS();
startIncompleteElement(element);
}
if (a2 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("INTEGER_0");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_57_0_0_4_0_0_0, resolved, true);
copyLocalizationInfos((CommonToken) a2, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2463]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2464]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2465]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2466]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2467]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2468]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2469]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2470]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2471]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2472]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2473]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2474]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2475]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2476]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2477]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2478]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2479]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2480]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2481]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2482]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2483]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2484]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2485]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2486]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2487]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2488]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2489]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2490]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2491]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2492]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2493]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2494]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2495]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2496]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2497]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2498]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2499]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2500]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2501]);
}


|(
a3 = INTEGER_LITERAL
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createRealLiteralExpCS();
startIncompleteElement(element);
}
if (a3 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("INTEGER_LITERAL");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a3).getLine(), ((CommonToken) a3).getCharPositionInLine(), ((CommonToken) a3).getStartIndex(), ((CommonToken) a3).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_57_0_0_4_0_1_0, resolved, true);
copyLocalizationInfos((CommonToken) a3, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2502]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2503]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2504]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2505]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2506]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2507]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2508]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2509]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2510]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2511]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2512]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2513]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2514]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2515]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2516]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2517]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2518]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2519]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2520]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2521]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2522]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2523]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2524]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2525]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2526]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2527]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2528]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2529]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2530]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2531]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2532]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2533]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2534]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2535]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2536]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2537]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2538]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2539]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2540]);
}

)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2541]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2542]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2543]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2544]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2545]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2546]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2547]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2548]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2549]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2550]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2551]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2552]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2553]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2554]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2555]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2556]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2557]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2558]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2559]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2560]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2561]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2562]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2563]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2564]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2565]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2566]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2567]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2568]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2569]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2570]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2571]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2572]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2573]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2574]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2575]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2576]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2577]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2578]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2579]);
}

;

parse_org_dresdenocl_language_ocl_IntegerLiteralExpCS returns [org.dresdenocl.language.ocl.IntegerLiteralExpCS element = null]
@init{
}
:
(
a0 = INTEGER_LITERAL
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createIntegerLiteralExpCS();
startIncompleteElement(element);
}
if (a0 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("INTEGER_LITERAL");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INTEGER_LITERAL_EXP_CS__INTEGER_LITERAL), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
}
java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INTEGER_LITERAL_EXP_CS__INTEGER_LITERAL), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_58_0_0_0, resolved, true);
copyLocalizationInfos((CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2580]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2581]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2582]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2583]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2584]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2585]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2586]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2587]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2588]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2589]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2590]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2591]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2592]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2593]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2594]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2595]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2596]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2597]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2598]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2599]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2600]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2601]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2602]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2603]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2604]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2605]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2606]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2607]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2608]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2609]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2610]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2611]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2612]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2613]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2614]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2615]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2616]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2617]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2618]);
}

;

parse_org_dresdenocl_language_ocl_BooleanLiteralExpCS returns [org.dresdenocl.language.ocl.BooleanLiteralExpCS element = null]
@init{
}
:
(
a0 = BOOLEAN_LITERAL
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createBooleanLiteralExpCS();
startIncompleteElement(element);
}
if (a0 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("BOOLEAN_LITERAL");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
}
java.lang.Boolean resolved = (java.lang.Boolean) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_59_0_0_0, resolved, true);
copyLocalizationInfos((CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2619]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2620]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2621]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2622]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2623]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2624]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2625]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2626]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2627]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2628]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2629]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2630]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2631]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2632]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2633]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2634]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2635]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2636]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2637]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2638]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2639]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2640]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2641]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2642]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2643]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2644]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2645]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2646]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2647]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2648]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2649]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2650]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2651]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2652]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2653]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2654]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2655]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2656]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2657]);
}

;

parse_org_dresdenocl_language_ocl_StringLiteralExpCS returns [org.dresdenocl.language.ocl.StringLiteralExpCS element = null]
@init{
}
:
(
a0 = QUOTED_39_39
{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createStringLiteralExpCS();
startIncompleteElement(element);
}
if (a0 != null) {
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_39_39");
tokenResolver.setOptions(getOptions());
org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STRING_LITERAL_EXP_CS__STRING_LITERAL), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STRING_LITERAL_EXP_CS__STRING_LITERAL), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_60_0_0_0, resolved, true);
copyLocalizationInfos((CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2658]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2659]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2660]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2661]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2662]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2663]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2664]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2665]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2666]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2667]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2668]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2669]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2670]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2671]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2672]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2673]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2674]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2675]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2676]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2677]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2678]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2679]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2680]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2681]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2682]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2683]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2684]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2685]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2686]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2687]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2688]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2689]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2690]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2691]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2692]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2693]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2694]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2695]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2696]);
}

;

parse_org_dresdenocl_language_ocl_InvalidLiteralExpCS returns [org.dresdenocl.language.ocl.InvalidLiteralExpCS element = null]
@init{
}
:
a0 = 'invalid' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createInvalidLiteralExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_61_0_0_0, null, true);
copyLocalizationInfos((CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2697]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2698]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2699]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2700]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2701]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2702]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2703]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2704]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2705]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2706]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2707]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2708]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2709]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2710]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2711]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2712]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2713]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2714]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2715]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2716]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2717]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2718]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2719]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2720]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2721]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2722]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2723]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2724]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2725]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2726]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2727]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2728]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2729]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2730]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2731]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2732]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2733]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2734]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2735]);
}

;

parse_org_dresdenocl_language_ocl_NullLiteralExpCS returns [org.dresdenocl.language.ocl.NullLiteralExpCS element = null]
@init{
}
:
a0 = 'null' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createNullLiteralExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_62_0_0_0, null, true);
copyLocalizationInfos((CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2736]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2737]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2738]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2739]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2740]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2741]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2742]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2743]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2744]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2745]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2746]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2747]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2748]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2749]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2750]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2751]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2752]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2753]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2754]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2755]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2756]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2757]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2758]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2759]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2760]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2761]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2762]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2763]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2764]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2765]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2766]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2767]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2768]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2769]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2770]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2771]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2772]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2773]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2774]);
}

;

parse_org_dresdenocl_language_ocl_BracketExpCS returns [org.dresdenocl.language.ocl.BracketExpCS element = null]
@init{
}
:
a0 = '(' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createBracketExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_63_0_0_0, null, true);
copyLocalizationInfos((CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2775]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2776]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2777]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2778]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2779]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2780]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2781]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2782]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2783]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2784]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2785]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2786]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2787]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2788]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2789]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2790]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2791]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2792]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2793]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2794]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2795]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2796]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2797]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2798]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2799]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2800]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2801]);
}

(
a1_0 = parse_org_dresdenocl_language_ocl_OclExpressionCS{
if (terminateParsing) {
throw new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTerminateParsingException();
}
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createBracketExpCS();
startIncompleteElement(element);
}
if (a1_0 != null) {
if (a1_0 != null) {
Object value = a1_0;
element.eSet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BRACKET_EXP_CS__OCL_EXPRESSION), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_63_0_0_2, a1_0, true);
copyLocalizationInfos(a1_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2802]);
}

a2 = ')' {
if (element == null) {
element = org.dresdenocl.language.ocl.OclFactory.eINSTANCE.createBracketExpCS();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_63_0_0_4, null, true);
copyLocalizationInfos((CommonToken)a2, element);
}
{
// expected elements (follow set)
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2803]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2804]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2805]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2806]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2807]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2808]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2809]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2810]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2811]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2812]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2813]);
addExpectedElement(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2814]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2815]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2816]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2817]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2818]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2819]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2820]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2821]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2822]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2823]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2824]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2825]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2826]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2827]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2828]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2829]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2830]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2831]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2832]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2833]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2834]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2835]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2836]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2837]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2838]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2839]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2840]);
addExpectedElement(null, org.dresdenocl.language.ocl.resource.ocl.mopp.OclExpectationConstants.EXPECTATIONS[2841]);
}

;

parse_org_dresdenocl_language_ocl_ContextDeclarationCS returns [org.dresdenocl.language.ocl.ContextDeclarationCS element = null]
:
c0 = parse_org_dresdenocl_language_ocl_OperationContextDeclarationCS{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_dresdenocl_language_ocl_AttributeContextDeclarationCS{ element = c1; /* this is a subclass or primitive expression choice */ }
|c2 = parse_org_dresdenocl_language_ocl_ClassifierContextDeclarationCS{ element = c2; /* this is a subclass or primitive expression choice */ }

;

parse_org_dresdenocl_language_ocl_PrePostOrBodyDeclarationCS returns [org.dresdenocl.language.ocl.PrePostOrBodyDeclarationCS element = null]
:
c0 = parse_org_dresdenocl_language_ocl_PreConditionDeclarationCS{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_dresdenocl_language_ocl_PostConditionDeclarationCS{ element = c1; /* this is a subclass or primitive expression choice */ }
|c2 = parse_org_dresdenocl_language_ocl_BodyDeclarationCS{ element = c2; /* this is a subclass or primitive expression choice */ }

;

parse_org_dresdenocl_language_ocl_TypeCS returns [org.dresdenocl.language.ocl.TypeCS element = null]
:
c0 = parse_org_dresdenocl_language_ocl_TupleTypeCS{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_dresdenocl_language_ocl_CollectionTypeIdentifierCS{ element = c1; /* this is a subclass or primitive expression choice */ }
|c2 = parse_org_dresdenocl_language_ocl_TypeModelElementCS{ element = c2; /* this is a subclass or primitive expression choice */ }

;

parse_org_dresdenocl_language_ocl_InitOrDeriveValueCS returns [org.dresdenocl.language.ocl.InitOrDeriveValueCS element = null]
:
c0 = parse_org_dresdenocl_language_ocl_InitValueCS{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_dresdenocl_language_ocl_DeriveValueCS{ element = c1; /* this is a subclass or primitive expression choice */ }

;

parse_org_dresdenocl_language_ocl_InvariantOrDefinitionCS returns [org.dresdenocl.language.ocl.InvariantOrDefinitionCS element = null]
:
c0 = parse_org_dresdenocl_language_ocl_InvariantExpCS{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_dresdenocl_language_ocl_DefinitionExpCS{ element = c1; /* this is a subclass or primitive expression choice */ }

;

parse_org_dresdenocl_language_ocl_OclExpressionCS returns [org.dresdenocl.language.ocl.OclExpressionCS element = null]
:
c = parseop_OclExpressionCS_level_4{ element = c; /* this rule is an expression root */ }

;

parse_org_dresdenocl_language_ocl_DefinitionExpPartCS returns [org.dresdenocl.language.ocl.DefinitionExpPartCS element = null]
:
c0 = parse_org_dresdenocl_language_ocl_DefinitionExpPropertyCS{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_dresdenocl_language_ocl_DefinitionExpOperationCS{ element = c1; /* this is a subclass or primitive expression choice */ }

;

parse_org_dresdenocl_language_ocl_UnreservedSimpleNameCS returns [org.dresdenocl.language.ocl.UnreservedSimpleNameCS element = null]
:
c0 = parse_org_dresdenocl_language_ocl_NamedElementCS{ element = c0; /* this is a subclass or primitive expression choice */ }

;

parse_org_dresdenocl_language_ocl_ImplicitFeatureCallCS returns [org.dresdenocl.language.ocl.ImplicitFeatureCallCS element = null]
:
c0 = parse_org_dresdenocl_language_ocl_ImplicitOperationCallCS{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_dresdenocl_language_ocl_ImplicitPropertyCallCS{ element = c1; /* this is a subclass or primitive expression choice */ }
|c2 = parse_org_dresdenocl_language_ocl_IteratorExpCS{ element = c2; /* this is a subclass or primitive expression choice */ }
|c3 = parse_org_dresdenocl_language_ocl_IterateExpCS{ element = c3; /* this is a subclass or primitive expression choice */ }

;

parse_org_dresdenocl_language_ocl_PathNameCS returns [org.dresdenocl.language.ocl.PathNameCS element = null]
:
c0 = parse_org_dresdenocl_language_ocl_PathNameSimpleCS{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_dresdenocl_language_ocl_PathNamePathCS{ element = c1; /* this is a subclass or primitive expression choice */ }

;

parse_org_dresdenocl_language_ocl_CollectionLiteralPartsCS returns [org.dresdenocl.language.ocl.CollectionLiteralPartsCS element = null]
:
c0 = parse_org_dresdenocl_language_ocl_CollectionRangeCS{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_dresdenocl_language_ocl_CollectionLiteralPartsOclExpCS{ element = c1; /* this is a subclass or primitive expression choice */ }

;

SL_COMMENT:
( '--'(~('\n'|'\r'|'\uffff'))* )
{ _channel = 99; }
;
ML_COMMENT:
( '/*'.*'*/')
{ _channel = 99; }
;
NAVIGATION_OPERATOR:
( '.' | '->' )
;
ADDITIVE_OPERATOR:
( '+' | '-' )
;
MULT_OPERATOR:
( '*' | '/' | '%' )
;
RELATIONAL_OPERATOR:
( '<' | '>' | '<=' | '>=')
;
EQUALITY_OPERATOR:
( '=' )
;
NEQUALITY_OPERATOR:
( '<>' )
;
NOT_OPERATOR:
( 'not' )
;
AND_OPERATOR:
( 'and' )
;
OR_OPERATOR:
( 'or' )
;
XOR_OPERATOR:
( 'xor' )
;
IMPLIES_OPERATOR:
( 'implies' )
;
IS_MARKED_PRE:
( '@pre')
;
BOOLEAN_LITERAL:
( 'true' | 'false' )
;
COLLECTION_TYPES:
( 'Set' | 'Bag' | 'Sequence' | 'Collection' | 'OrderedSet' )
;
ITERATOR_NAME:
( 'select' | 'reject' | 'forAll' | 'collect' | 'any' | 'exists' | 'one' | 'isUnique' | 'collectNested' | 'sortedBy' | 'closure' )
;
STATIC:
( 'static')
;
INTEGER_0:
( '0'+ ('0'..'9'))
;
INTEGER_LITERAL:
( ('1'..'9') ('0'..'9')* | '0')
;
SIMPLE_NAME:
( ('A'..'Z'|'a'..'z'|'_') ('A'..'Z'|'a'..'z'|'0'..'9'|'_')*)
;
WHITESPACE:
((' '|'\t'|'\f'))
{ _channel = 99; }
;
LINEBREAKS:
(('\r\n'|'\r'|'\n'))
{ _channel = 99; }
;
QUOTED_39_39:
(('\'')(~('\''))*('\''))
;

