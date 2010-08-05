/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclPrinter2 implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextPrinter {
	
	private class PrintToken {
		
		private String text;
		private String tokenName;
		
		public PrintToken(String text, String tokenName) {
			this.text = text;
			this.tokenName = tokenName;
		}
		
		public String getText() {
			return text;
		}
		
		public String getTokenName() {
			return tokenName;
		}
		
	}
	
	public final static java.lang.String NEW_LINE = java.lang.System.getProperties().getProperty("line.separator");
	
	/**
	 * Holds the resource that is associated with this printer. May be null if the
	 * printer is used stand alone.
	 */
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextResource resource;
	
	private java.util.Map<?, ?> options;
	private java.io.OutputStream outputStream;
	private java.util.List<PrintToken> tokenOutputStream;
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenResolverFactory tokenResolverFactory = new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclTokenResolverFactory();
	private boolean handleTokenSpaceAutomatically = false;
	private int tokenSpace = 1;
	/**
	 * A flag that indicates whether token have already been printed for the some
	 * object. The flag is set to false whenever printing of an EObject tree is
	 * started. The status of the flag is used to avoid printing default token space
	 * in front of the root object.
	 */
	private boolean startedPrintingObject = false;
	/**
	 * The number of tab characters that were printed before the current line. This
	 * number is used to calculate the relative indendation when printing contained
	 * objects, because all contained objects must start with this indendation
	 * (tabsBeforeCurrentObject + currentTabs).
	 */
	private int currentTabs;
	/**
	 * The number of tab characters that must be printed before the current object.
	 * This number is used to calculate the indendation of new lines, when line breaks
	 * are printed within one object.
	 */
	private int tabsBeforeCurrentObject;
	private int newTabsBeforeCurrentObject;
	
	public OclPrinter2(java.io.OutputStream outputStream, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextResource resource) {
		super();
		this.outputStream = outputStream;
		this.resource = resource;
	}
	
	public void print(org.eclipse.emf.ecore.EObject element) throws java.io.IOException {
		tokenOutputStream = new java.util.ArrayList<PrintToken>();
		currentTabs = 0;
		tabsBeforeCurrentObject = 0;
		startedPrintingObject = true;
		doPrint(element, new java.util.ArrayList<tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement>());
		java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.BufferedOutputStream(outputStream));
		if (handleTokenSpaceAutomatically) {
			printSmart(writer);
		} else {
			printBasic(writer);
		}
		writer.flush();
	}
	
	protected void doPrint(org.eclipse.emf.ecore.EObject element, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements) {
		if (element == null) {
			throw new java.lang.IllegalArgumentException("Nothing to write.");
		}
		if (outputStream == null) {
			throw new java.lang.IllegalArgumentException("Nothing to write on.");
		}
		
		if (element instanceof tudresden.ocl20.pivot.language.ocl.SimpleNameCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_0, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_1, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_2, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_3, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_4, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_5, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_6, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.InitValueCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_7, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.DeriveValueCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_8, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.InvariantExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_9, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.DefinitionExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_10, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_11, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_12, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_13, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_14, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_15, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_16, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_17, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.ParameterCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_18, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_19, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_20, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_21, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_22, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_23, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_24, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_25, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_26, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_27, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_28, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.NavigationCallExp) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_29, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_31, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.IteratorExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_32, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.IterateExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_33, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_34, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_35, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_36, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.TupleTypeCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_37, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_38, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_39, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_40, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_41, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_43, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_44, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_45, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_46, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.IfExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_47, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_48, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.CollectionRangeCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_49, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_50, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_51, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.LetExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_52, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_53, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_54, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_55, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_56, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_57, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_58, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_59, foundFormattingElements);
			return;
		}
		if (element instanceof tudresden.ocl20.pivot.language.ocl.BracketExpCS) {
			printInternal(element, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_60, foundFormattingElements);
			return;
		}
		
		addWarningToResource("The printer can not handle " + element.eClass().getName() + " elements", element);
	}
	
	public void printInternal(org.eclipse.emf.ecore.EObject eObject, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement ruleElement, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements) {
		tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter layoutInformationAdapter = getLayoutInformationAdapter(eObject);
		java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation> originalLayoutInformations = layoutInformationAdapter.getLayoutInformations();
		// create a copy of the original list of layout information object in order to be
		// able to remove used informations during printing
		java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations = new java.util.ArrayList<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation>(originalLayoutInformations.size());
		layoutInformations.addAll(originalLayoutInformations);
		tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator decoratorTree = getDecoratorTree(ruleElement);
		decorateTree(decoratorTree, eObject);
		printTree(decoratorTree, eObject, foundFormattingElements, layoutInformations);
	}
	
	/**
	 * creates a tree of decorator objects which reflects the syntax tree that is
	 * attached to the given syntax element
	 */
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator getDecoratorTree(tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement syntaxElement) {
		tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement[] children = syntaxElement.getChildren();
		int childCount = children.length;
		tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator[] childDecorators = new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator[childCount];
		for (int i = 0; i < childCount; i++) {
			childDecorators[i] = getDecoratorTree(children[i]);
		}
		tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator decorator = new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator(syntaxElement, childDecorators);
		return decorator;
	}
	
	public void decorateTree(tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator decorator, org.eclipse.emf.ecore.EObject eObject) {
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = initializePrintCountingMap(eObject);
		java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator> keywordsToPrint = new java.util.ArrayList<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator>();
		decorateTreeBasic(decorator, eObject, printCountingMap, keywordsToPrint);
		for (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator keywordToPrint : keywordsToPrint) {
			// for keywords the concrete index does not matter, but we must add one to
			// indicate that the keyword needs to be printed here. Thus, we use 0 as index.
			keywordToPrint.addIndexToPrint(0);
		}
	}
	
	/**
	 * Tries to decorate the decorator with an attribute value, or reference holded by
	 * eObject. Returns true if an attribute value or reference was found.
	 */
	public boolean decorateTreeBasic(tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator decorator, org.eclipse.emf.ecore.EObject eObject, java.util.Map<java.lang.String, java.lang.Integer> printCountingMap, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator> keywordsToPrint) {
		boolean foundFeatureToPrint = false;
		tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement syntaxElement = decorator.getDecoratedElement();
		tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality cardinality = syntaxElement.getCardinality();
		boolean isFirstIteration = true;
		while (true) {
			java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator> subKeywordsToPrint = new java.util.ArrayList<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator>();
			boolean keepDecorating = false;
			if (syntaxElement instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclKeyword) {
				subKeywordsToPrint.add(decorator);
			} else if (syntaxElement instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclTerminal) {
				tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclTerminal terminal = (tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclTerminal) syntaxElement;
				org.eclipse.emf.ecore.EStructuralFeature feature = terminal.getFeature();
				if (feature == tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.ANONYMOUS_FEATURE) {
					return false;
				}
				int countLeft = printCountingMap.get(feature.getName());
				if (countLeft > terminal.getMandatoryOccurencesAfter()) {
					decorator.addIndexToPrint(countLeft);
					printCountingMap.put(feature.getName(), countLeft - 1);
					keepDecorating = true;
				}
			}
			if (syntaxElement instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclChoice) {
				// for choices we do print only the choice which does print at least one feature
				tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator childToPrint = null;
				for (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator childDecorator : decorator.getChildDecorators()) {
					// pick first choice as default, will be overridden if a choice that prints a
					// feature is found
					if (childToPrint == null) {
						childToPrint = childDecorator;
					}
					if (doesPrintFeature(childDecorator, eObject, printCountingMap)) {
						childToPrint = childDecorator;
						break;
					}
				}
				keepDecorating |= decorateTreeBasic(childToPrint, eObject, printCountingMap, subKeywordsToPrint);
			} else {
				// for all other syntax element we do print all children
				for (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator childDecorator : decorator.getChildDecorators()) {
					keepDecorating |= decorateTreeBasic(childDecorator, eObject, printCountingMap, subKeywordsToPrint);
				}
			}
			foundFeatureToPrint |= keepDecorating;
			// only print keywords if a feature was printed or the syntax element is mandatory
			if (cardinality == tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality.ONE) {
				keywordsToPrint.addAll(subKeywordsToPrint);
			} else if (cardinality == tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality.PLUS) {
				if (isFirstIteration) {
					keywordsToPrint.addAll(subKeywordsToPrint);
				} else {
					if (keepDecorating) {
						keywordsToPrint.addAll(subKeywordsToPrint);
					}
				}
			} else if (keepDecorating && (cardinality == tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality.STAR || cardinality == tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK)) {
				keywordsToPrint.addAll(subKeywordsToPrint);
			}
			if (cardinality == tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality.ONE || cardinality == tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK) {
				break;
			} else if (!keepDecorating) {
				break;
			}
			isFirstIteration = false;
		}
		return foundFeatureToPrint;
	}
	
	/**
	 * Checks whether decorating the given node will use at least one attribute value,
	 * or reference holded by eObject. Returns true if a printable attribute value or
	 * reference was found. This method is used to decide which choice to pick, when
	 * multiple choices are available. We pick the choice that prints at least one
	 * attribute or reference.
	 */
	public boolean doesPrintFeature(tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator decorator, org.eclipse.emf.ecore.EObject eObject, java.util.Map<java.lang.String, java.lang.Integer> printCountingMap) {
		tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement syntaxElement = decorator.getDecoratedElement();
		if (syntaxElement instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclTerminal) {
			tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclTerminal terminal = (tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclTerminal) syntaxElement;
			org.eclipse.emf.ecore.EStructuralFeature feature = terminal.getFeature();
			if (feature == tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.ANONYMOUS_FEATURE) {
				return false;
			}
			int countLeft = printCountingMap.get(feature.getName());
			if (countLeft > terminal.getMandatoryOccurencesAfter()) {
				// found a feature to print
				return true;
			}
		}
		for (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator childDecorator : decorator.getChildDecorators()) {
			if (doesPrintFeature(childDecorator, eObject, printCountingMap)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean printTree(tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator decorator, org.eclipse.emf.ecore.EObject eObject, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement printElement = decorator.getDecoratedElement();
		tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality cardinality = printElement.getCardinality();
		java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement> cloned = new java.util.ArrayList<tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement>();
		cloned.addAll(foundFormattingElements);
		boolean foundSomethingAtAll = false;
		boolean foundSomethingToPrint;
		while (true) {
			foundSomethingToPrint = false;
			java.lang.Integer indexToPrint = decorator.getNextIndexToPrint();
			if (indexToPrint != null) {
				if (printElement instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclKeyword) {
					printKeyword(eObject, (tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclKeyword) printElement, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				} else if (printElement instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclPlaceholder) {
					tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclPlaceholder placeholder = (tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclPlaceholder) printElement;
					printFeature(eObject, placeholder, indexToPrint, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				} else if (printElement instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclContainment) {
					tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclContainment containment = (tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclContainment) printElement;
					printContainedObject(eObject, containment, indexToPrint, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				}
			}
			if (foundSomethingToPrint) {
				foundSomethingAtAll = true;
			}
			if (printElement instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclWhiteSpace) {
				foundFormattingElements.add((tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclWhiteSpace) printElement);
			}
			if (printElement instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclLineBreak) {
				foundFormattingElements.add((tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclLineBreak) printElement);
			}
			for (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator childDecorator : decorator.getChildDecorators()) {
				foundSomethingToPrint |= printTree(childDecorator, eObject, foundFormattingElements, layoutInformations);
				tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement decoratedElement = decorator.getDecoratedElement();
				if (foundSomethingToPrint && decoratedElement instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclChoice) {
					break;
				}
			}
			if (cardinality == tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality.ONE || cardinality == tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK) {
				break;
			} else if (!foundSomethingToPrint) {
				break;
			}
		}
		// only print formatting elements if a feature was printed or the syntax element
		// is mandatory
		if (!foundSomethingAtAll && (cardinality == tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality.STAR || cardinality == tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK)) {
			foundFormattingElements.clear();
			foundFormattingElements.addAll(cloned);
		}
		return foundSomethingToPrint;
	}
	
	public void printKeyword(org.eclipse.emf.ecore.EObject eObject, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclKeyword keyword, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation layoutInformation = getLayoutInformation(layoutInformations, keyword, null, eObject);
		printFormattingElements(foundFormattingElements, layoutInformations, layoutInformation);
		String value = keyword.getValue();
		tokenOutputStream.add(new PrintToken(value, "'" + tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclStringUtil.escapeToANTLRKeyword(value) + "'"));
	}
	
	public void printFeature(org.eclipse.emf.ecore.EObject eObject, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclPlaceholder placeholder, int count, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		org.eclipse.emf.ecore.EStructuralFeature feature = placeholder.getFeature();
		if (feature instanceof org.eclipse.emf.ecore.EAttribute) {
			printAttribute(eObject, (org.eclipse.emf.ecore.EAttribute) feature, placeholder, count, foundFormattingElements, layoutInformations);
		} else {
			printReference(eObject, (org.eclipse.emf.ecore.EReference) feature, placeholder, count, foundFormattingElements, layoutInformations);
		}
	}
	
	public void printAttribute(org.eclipse.emf.ecore.EObject eObject, org.eclipse.emf.ecore.EAttribute attribute, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclPlaceholder placeholder, int count, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		java.lang.String result;
		java.lang.Object attributeValue = getValue(eObject, attribute, count);
		tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation layoutInformation = getLayoutInformation(layoutInformations, placeholder, attributeValue, eObject);
		java.lang.String visibleTokenText = getVisibleTokenText(layoutInformation);
		// if there is text for the attribute we use it
		if (visibleTokenText != null) {
			result = visibleTokenText;
		} else {
			// if no text is available, the attribute is deresolved to obtain its textual
			// representation
			tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver(placeholder.getTokenName());
			tokenResolver.setOptions(getOptions());
			java.lang.String deResolvedValue = tokenResolver.deResolve(attributeValue, attribute, eObject);
			result = deResolvedValue;
		}
		if (result != null && !"".equals(result)) {
			printFormattingElements(foundFormattingElements, layoutInformations, layoutInformation);
		}
		// write result to the output stream
		tokenOutputStream.add(new PrintToken(result, placeholder.getTokenName()));
	}
	
	public void printContainedObject(org.eclipse.emf.ecore.EObject eObject, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclContainment containment, int count, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		org.eclipse.emf.ecore.EStructuralFeature reference = containment.getFeature();
		java.lang.Object o = getValue(eObject, reference, count);
		// save current number of tabs to restore them after printing the contained object
		int oldTabsBeforeCurrentObject = tabsBeforeCurrentObject;
		int oldCurrentTabs = currentTabs;
		// use current number of tabs to indent contained object. we do not directly set
		// 'tabsBeforeCurrentObject' because the first element of the new object must be
		// printed with the old number of tabs.
		newTabsBeforeCurrentObject = tabsBeforeCurrentObject + currentTabs;
		currentTabs = 0;
		doPrint((org.eclipse.emf.ecore.EObject) o, foundFormattingElements);
		// restore number of tabs after printing the contained object
		tabsBeforeCurrentObject = oldTabsBeforeCurrentObject;
		currentTabs = oldCurrentTabs;
	}
	
	public void printFormattingElements(java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations, tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation layoutInformation) {
		java.lang.String hiddenTokenText = getHiddenTokenText(layoutInformation);
		if (hiddenTokenText != null) {
			// removed used information
			layoutInformations.remove(layoutInformation);
			tokenOutputStream.add(new PrintToken(hiddenTokenText, null));
			foundFormattingElements.clear();
			startedPrintingObject = false;
			tabsBeforeCurrentObject = newTabsBeforeCurrentObject;
			return;
		}
		if (foundFormattingElements.size() > 0) {
			for (tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement foundFormattingElement : foundFormattingElements) {
				if (foundFormattingElement instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclWhiteSpace) {
					int amount = ((tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclWhiteSpace) foundFormattingElement).getAmount();
					for (int i = 0; i < amount; i++) {
						tokenOutputStream.add(new PrintToken(" ", null));
					}
				}
				if (foundFormattingElement instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclLineBreak) {
					currentTabs = ((tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclLineBreak) foundFormattingElement).getTabs();
					tokenOutputStream.add(new PrintToken(NEW_LINE, null));
					for (int i = 0; i < tabsBeforeCurrentObject + currentTabs; i++) {
						tokenOutputStream.add(new PrintToken("\t", null));
					}
				}
			}
			foundFormattingElements.clear();
			startedPrintingObject = false;
		} else {
			if (startedPrintingObject) {
				// if no elements have been printed yet, we do not add the default token space,
				// because spaces before the first element are not desired.
				startedPrintingObject = false;
			} else {
				if (!handleTokenSpaceAutomatically) {
					tokenOutputStream.add(new PrintToken(getWhiteSpaceString(tokenSpace), null));
				}
			}
		}
		// after printing the first element, we can use the new number of tabs.
		tabsBeforeCurrentObject = newTabsBeforeCurrentObject;
	}
	
	private Object getValue(org.eclipse.emf.ecore.EObject eObject, org.eclipse.emf.ecore.EStructuralFeature feature, int count) {
		// get value of feature
		java.lang.Object o = eObject.eGet(feature);
		if (o instanceof java.util.List<?>) {
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			o = list.get(index);
		}
		return o;
	}
	
	@SuppressWarnings("unchecked")	
	public void printReference(org.eclipse.emf.ecore.EObject eObject, org.eclipse.emf.ecore.EReference reference, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclPlaceholder placeholder, int count, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		java.lang.Object referencedObject = getValue(eObject, reference, count);
		tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation layoutInformation = getLayoutInformation(layoutInformations, placeholder, referencedObject, eObject);
		printFormattingElements(foundFormattingElements, layoutInformations, layoutInformation);
		// NC-References must always be printed by deresolving the reference. We cannot
		// use the visible token information, because deresolving usually depends on
		// attribute values of the referenced object instead of the object itself.
		String tokenName = placeholder.getTokenName();
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver(tokenName);
		tokenResolver.setOptions(getOptions());
		@SuppressWarnings("rawtypes")		
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver referenceResolver = getReferenceResolverSwitch().getResolver(reference);
		referenceResolver.setOptions(getOptions());
		java.lang.String deresolvedReference = referenceResolver.deResolve((org.eclipse.emf.ecore.EObject) referencedObject, eObject, reference);
		java.lang.String deresolvedToken = tokenResolver.deResolve(deresolvedReference, reference, eObject);
		// write result to output stream
		tokenOutputStream.add(new PrintToken(deresolvedToken, tokenName));
	}
	
	public java.util.Map<java.lang.String, java.lang.Integer> initializePrintCountingMap(org.eclipse.emf.ecore.EObject eObject) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>();
		java.util.List<org.eclipse.emf.ecore.EStructuralFeature> features = eObject.eClass().getEAllStructuralFeatures();
		for (org.eclipse.emf.ecore.EStructuralFeature feature : features) {
			int count = 0;
			java.lang.Object featureValue = eObject.eGet(feature);
			if (featureValue != null) {
				if (featureValue instanceof java.util.List<?>) {
					count = ((java.util.List<?>) featureValue).size();
				} else {
					count = 1;
				}
			}
			printCountingMap.put(feature.getName(), count);
		}
		return printCountingMap;
	}
	
	public java.util.Map<?,?> getOptions() {
		return options;
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		this.options = options;
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextResource getResource() {
		return resource;
	}
	
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclReferenceResolverSwitch getReferenceResolverSwitch() {
		return (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclReferenceResolverSwitch) new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMetaInformation().getReferenceResolverSwitch();
	}
	
	protected void addWarningToResource(final java.lang.String errorMessage, org.eclipse.emf.ecore.EObject cause) {
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextResource resource = getResource();
		if (resource == null) {
			// the resource can be null if the printer is used stand alone
			return;
		}
		resource.addProblem(new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclProblem(errorMessage, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType.ERROR), cause);
	}
	
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter getLayoutInformationAdapter(org.eclipse.emf.ecore.EObject element) {
		for (org.eclipse.emf.common.notify.Adapter adapter : element.eAdapters()) {
			if (adapter instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter) {
				return (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter) adapter;
			}
		}
		tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter newAdapter = new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter();
		element.eAdapters().add(newAdapter);
		return newAdapter;
	}
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation getLayoutInformation(java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations, tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement syntaxElement, java.lang.Object object, org.eclipse.emf.ecore.EObject container) {
		for (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation layoutInformation : layoutInformations) {
			if (syntaxElement == layoutInformation.getSyntaxElement()) {
				if (object == null) {
					return layoutInformation;
				} else if (object == layoutInformation.getObject(container)) {
					return layoutInformation;
				}
			}
		}
		return null;
	}
	
	private java.lang.String getHiddenTokenText(tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation layoutInformation) {
		if (layoutInformation != null) {
			return layoutInformation.getHiddenTokenText();
		} else {
			return null;
		}
	}
	
	private java.lang.String getVisibleTokenText(tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLayoutInformation layoutInformation) {
		if (layoutInformation != null) {
			return layoutInformation.getVisibleTokenText();
		} else {
			return null;
		}
	}
	
	protected String getWhiteSpaceString(int count) {
		return getRepeatingString(count, ' ');
	}
	
	private String getRepeatingString(int count, char character) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < count; i++) {
			result.append(character);
		}
		return result.toString();
	}
	
	public void setHandleTokenSpaceAutomatically(boolean handleTokenSpaceAutomatically) {
		this.handleTokenSpaceAutomatically = handleTokenSpaceAutomatically;
	}
	
	public void setTokenSpace(int tokenSpace) {
		this.tokenSpace = tokenSpace;
	}
	
	/**
	 * Prints the current tokenOutputStream to the given writer (as it is).
	 */
	public void printBasic(java.io.PrintWriter writer) throws java.io.IOException {
		for (PrintToken nextToken : tokenOutputStream) {
			writer.write(nextToken.getText());
		}
	}
	
	/**
	 * Prints the current tokenOutputStream to the given writer.
	 * 
	 * This methods implements smart whitespace printing. It does so by writing output
	 * to a token stream instead of printing the raw token text to a PrintWriter.
	 * Tokens in this stream hold both the text and the type of the token (i.e., its
	 * name).
	 * 
	 * To decide where whitespace is needed, sequences of successive tokens are
	 * searched that can be printed without separating whitespace. To determine such
	 * groups we start with two successive non-whitespace tokens, concatenate their
	 * text and use the generated ANTLR lexer to split the text. If the resulting
	 * token sequence of the concatenated text is exactly the same as the one that is
	 * to be printed, no whitespace is needed. The tokens in the sequence are checked
	 * both regarding their type and their text. If two tokens successfully form a
	 * group a third one is added and so on.
	 */
	public void printSmart(java.io.PrintWriter writer) throws java.io.IOException {
		// stores the text of the current group of tokens. this text is given to the lexer
		// to check whether it can be correctly scanned.
		StringBuilder currentBlock = new StringBuilder();
		// stores the index of the first token of the current group.
		int currentBlockStart = 0;
		// stores the text that was already successfully checked (i.e., is can be scanned
		// correctly and can thus be printed).
		String validBlock = "";
		for (int i = 0; i < tokenOutputStream.size(); i++) {
			PrintToken tokenI = tokenOutputStream.get(i);
			currentBlock.append(tokenI.getText());
			// if declared or preserved whitespace is found - print block
			if (tokenI.getTokenName() == null) {
				writer.write(currentBlock.toString());
				// reset all values
				currentBlock = new StringBuilder();
				currentBlockStart = i + 1;
				validBlock = "";
				continue;
			}
			// now check whether the current block can be scanned
			tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextScanner scanner = new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMetaInformation().createLexer();
			scanner.setText(currentBlock.toString());
			// retrieve all tokens from scanner and add them to list 'tempTokens'
			java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextToken> tempTokens = new java.util.ArrayList<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextToken>();
			tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextToken nextToken = scanner.getNextToken();
			while (nextToken != null && nextToken.getText() != null) {
				tempTokens.add(nextToken);
				nextToken = scanner.getNextToken();
			}
			boolean sequenceIsValid = true;
			// check whether the current block was scanned to the same token sequence
			for (int t = 0; t < tempTokens.size(); t++) {
				PrintToken printTokenT = tokenOutputStream.get(currentBlockStart + t);
				tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextToken tempToken = tempTokens.get(t);
				if (!tempToken.getText().equals(printTokenT.getText())) {
					sequenceIsValid = false;
					break;
				}
				String commonTokenName = tempToken.getName();
				String printTokenName = printTokenT.getTokenName();
				if (printTokenName.length() > 2 && printTokenName.startsWith("'") && printTokenName.endsWith("'")) {
					printTokenName = printTokenName.substring(1, printTokenName.length() - 1);
				}
				if (!commonTokenName.equals(printTokenName)) {
					sequenceIsValid = false;
					break;
				}
			}
			if (sequenceIsValid) {
				// sequence is still valid, try adding one more token in the next iteration of the
				// loop
				validBlock += tokenI.getText();
			} else {
				// sequence is not valid, must print whitespace to separate tokens
				// print text that is valid so far
				writer.write(validBlock);
				// print separating whitespace
				writer.write(" ");
				// add current token as initial value for next iteration
				currentBlock = new StringBuilder(tokenI.getText());
				currentBlockStart = i;
				validBlock = tokenI.getText();
			}
		}
		// flush remaining valid text to writer
		writer.write(validBlock);
	}
	
}
