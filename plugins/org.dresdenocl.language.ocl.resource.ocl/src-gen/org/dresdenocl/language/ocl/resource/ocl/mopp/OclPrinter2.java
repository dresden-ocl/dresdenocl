/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

public class OclPrinter2 implements org.dresdenocl.language.ocl.resource.ocl.IOclTextPrinter {
	
	protected class PrintToken {
		
		private String text;
		private String tokenName;
		private org.eclipse.emf.ecore.EObject container;
		
		public PrintToken(String text, String tokenName, org.eclipse.emf.ecore.EObject container) {
			this.text = text;
			this.tokenName = tokenName;
			this.container = container;
		}
		
		public String getText() {
			return text;
		}
		
		public String getTokenName() {
			return tokenName;
		}
		
		public org.eclipse.emf.ecore.EObject getContainer() {
			return container;
		}
		
		public String toString() {
			return "'" + text + "' [" + tokenName + "]";
		}
		
	}
	
	/**
	 * The PrintCountingMap keeps tracks of the values that must be printed for each
	 * feature of an EObject. It is also used to store the indices of all values that
	 * have been printed. This knowledge is used to avoid printing values twice. We
	 * must store the concrete indices of the printed values instead of basically
	 * counting them, because values may be printed in an order that differs from the
	 * order in which they are stored in the EObject's feature.
	 */
	protected class PrintCountingMap {
		
		private java.util.Map<String, java.util.List<Object>> featureToValuesMap = new java.util.LinkedHashMap<String, java.util.List<Object>>();
		private java.util.Map<String, java.util.Set<Integer>> featureToPrintedIndicesMap = new java.util.LinkedHashMap<String, java.util.Set<Integer>>();
		
		public void setFeatureValues(String featureName, java.util.List<Object> values) {
			featureToValuesMap.put(featureName, values);
			// If the feature does not have values it won't be printed. An entry in
			// 'featureToPrintedIndicesMap' is therefore not needed in this case.
			if (values != null) {
				featureToPrintedIndicesMap.put(featureName, new java.util.LinkedHashSet<Integer>());
			}
		}
		
		public java.util.Set<Integer> getIndicesToPrint(String featureName) {
			return featureToPrintedIndicesMap.get(featureName);
		}
		
		public void addIndexToPrint(String featureName, int index) {
			featureToPrintedIndicesMap.get(featureName).add(index);
		}
		
		public int getCountLeft(org.dresdenocl.language.ocl.resource.ocl.grammar.OclTerminal terminal) {
			org.eclipse.emf.ecore.EStructuralFeature feature = terminal.getFeature();
			String featureName = feature.getName();
			java.util.List<Object> totalValuesToPrint = featureToValuesMap.get(featureName);
			java.util.Set<Integer> printedIndices = featureToPrintedIndicesMap.get(featureName);
			if (totalValuesToPrint == null) {
				return 0;
			}
			if (feature instanceof org.eclipse.emf.ecore.EAttribute) {
				// for attributes we do not need to check the type, since the CS languages does
				// not allow type restrictions for attributes.
				return totalValuesToPrint.size() - printedIndices.size();
			} else if (feature instanceof org.eclipse.emf.ecore.EReference) {
				org.eclipse.emf.ecore.EReference reference = (org.eclipse.emf.ecore.EReference) feature;
				if (!reference.isContainment()) {
					// for non-containment references we also do not need to check the type, since the
					// CS languages does not allow type restrictions for these either.
					return totalValuesToPrint.size() - printedIndices.size();
				}
			}
			// now we're left with containment references for which we check the type of the
			// objects to print
			java.util.List<Class<?>> allowedTypes = getAllowedTypes(terminal);
			java.util.Set<Integer> indicesWithCorrectType = new java.util.LinkedHashSet<Integer>();
			int index = 0;
			for (Object valueToPrint : totalValuesToPrint) {
				for (Class<?> allowedType : allowedTypes) {
					if (allowedType.isInstance(valueToPrint)) {
						indicesWithCorrectType.add(index);
					}
				}
				index++;
			}
			indicesWithCorrectType.removeAll(printedIndices);
			return indicesWithCorrectType.size();
		}
		
		public int getNextIndexToPrint(String featureName) {
			int printedValues = featureToPrintedIndicesMap.get(featureName).size();
			return printedValues;
		}
		
	}
	
	public final static String NEW_LINE = java.lang.System.getProperties().getProperty("line.separator");
	
	private final org.dresdenocl.language.ocl.resource.ocl.util.OclEClassUtil eClassUtil = new org.dresdenocl.language.ocl.resource.ocl.util.OclEClassUtil();
	
	/**
	 * Holds the resource that is associated with this printer. May be null if the
	 * printer is used stand alone.
	 */
	private org.dresdenocl.language.ocl.resource.ocl.IOclTextResource resource;
	
	private java.util.Map<?, ?> options;
	private java.io.OutputStream outputStream;
	private String encoding = System.getProperty("file.encoding");
	protected java.util.List<PrintToken> tokenOutputStream;
	private org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolverFactory tokenResolverFactory = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenResolverFactory();
	private boolean handleTokenSpaceAutomatically = false;
	private int tokenSpace = 1;
	/**
	 * A flag that indicates whether tokens have already been printed for some object.
	 * The flag is set to false whenever printing of an EObject tree is started. The
	 * status of the flag is used to avoid printing default token space in front of
	 * the root object.
	 */
	private boolean startedPrintingObject = false;
	/**
	 * The number of tab characters that were printed before the current line. This
	 * number is used to calculate the relative indentation when printing contained
	 * objects, because all contained objects must start with this indentation
	 * (tabsBeforeCurrentObject + currentTabs).
	 */
	private int currentTabs;
	/**
	 * The number of tab characters that must be printed before the current object.
	 * This number is used to calculate the indentation of new lines, when line breaks
	 * are printed within one object.
	 */
	private int tabsBeforeCurrentObject;
	/**
	 * This flag is used to indicate whether the number of tabs before the current
	 * object has been set for the current object. The flag is needed, because setting
	 * the number of tabs must be performed when the first token of the contained
	 * element is printed.
	 */
	private boolean startedPrintingContainedObject;
	
	public OclPrinter2(java.io.OutputStream outputStream, org.dresdenocl.language.ocl.resource.ocl.IOclTextResource resource) {
		super();
		this.outputStream = outputStream;
		this.resource = resource;
	}
	
	public void print(org.eclipse.emf.ecore.EObject element) throws java.io.IOException {
		tokenOutputStream = new java.util.ArrayList<PrintToken>();
		currentTabs = 0;
		tabsBeforeCurrentObject = 0;
		startedPrintingObject = true;
		startedPrintingContainedObject = false;
		java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement>  formattingElements = new java.util.ArrayList<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement>();
		doPrint(element, formattingElements);
		// print all remaining formatting elements
		java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations = getCopyOfLayoutInformation(element);
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation eofLayoutInformation = getLayoutInformation(layoutInformations, null, null, null);
		printFormattingElements(element, formattingElements, layoutInformations, eofLayoutInformation);
		java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.OutputStreamWriter(new java.io.BufferedOutputStream(outputStream), encoding));
		if (handleTokenSpaceAutomatically) {
			printSmart(writer);
		} else {
			printBasic(writer);
		}
		writer.flush();
	}
	
	protected void doPrint(org.eclipse.emf.ecore.EObject element, java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements) {
		if (element == null) {
			throw new java.lang.IllegalArgumentException("Nothing to write.");
		}
		if (outputStream == null) {
			throw new java.lang.IllegalArgumentException("Nothing to write on.");
		}
		
		if (element instanceof org.dresdenocl.language.ocl.SimpleNameCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_0, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PackageDeclarationWithNamespaceCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_1, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_2, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PackageDeclarationWithoutNamespaceCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_3, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.OperationContextDeclarationCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_4, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.AttributeContextDeclarationCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_5, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.ClassifierContextDeclarationCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_6, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.InitValueCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_7, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.DeriveValueCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_8, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.InvariantExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_9, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.DefinitionExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_10, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.DefinitionExpPropertyCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_11, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.DefinitionExpOperationCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_12, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PreConditionDeclarationCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_13, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PostConditionDeclarationCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_14, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.BodyDeclarationCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_15, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.OperationDefinitionInContextCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_16, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.OperationDefinitionInDefCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_17, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.ParameterCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_18, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.LogicalImpliesOperationCallExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_19, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.LogicalXorOperationCallExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_20, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.LogicalOrOperationCallExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_21, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.LogicalAndOperationCallExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_22, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.EqualityOperationCallExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_23, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.RelationalOperationCallExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_24, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.AdditiveOperationCallExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_25, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.MultOperationCallExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_26, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.UnaryOperationCallExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_27, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.LogicalNotOperationCallExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_28, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.NavigationCallExp) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_29, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.ImplicitOperationCallCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_30, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.ImplicitPropertyCallCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_31, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.IteratorExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_32, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.IterateExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_33, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.IteratorExpVariableCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_34, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.TupleTypeCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_35, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.CollectionTypeIdentifierCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_36, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.TypeModelElementCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_37, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_38, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.VariableDeclarationWithoutInitListCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_39, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.VariableDeclarationWithInitCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_40, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_41, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.OperationCallOnSelfExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_42, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.StaticOperationCallExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_43, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.ModelElementCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_44, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PathNameSimpleCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_45, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PathNamePathCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_46, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.NamedElementCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_47, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.TupleLiteralExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_48, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.IfExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_49, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.CollectionLiteralExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_50, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.CollectionRangeCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_51, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.CollectionLiteralPartsOclExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_52, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.CollectionTypeLiteralExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_53, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.TupleTypeLiteralExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_54, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PropertyCallOnSelfExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_55, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.LetExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_56, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.RealLiteralExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_57, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.IntegerLiteralExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_58, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.BooleanLiteralExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_59, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.StringLiteralExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_60, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.InvalidLiteralExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_61, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.NullLiteralExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_62, foundFormattingElements);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.BracketExpCS) {
			printInternal(element, org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.OCL_63, foundFormattingElements);
			return;
		}
		
		addWarningToResource("The printer can not handle " + element.eClass().getName() + " elements", element);
	}
	
	public void printInternal(org.eclipse.emf.ecore.EObject eObject, org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement ruleElement, java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements) {
		java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations = getCopyOfLayoutInformation(eObject);
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator decoratorTree = getDecoratorTree(ruleElement);
		decorateTree(decoratorTree, eObject);
		printTree(decoratorTree, eObject, foundFormattingElements, layoutInformations);
	}
	
	/**
	 * creates a tree of decorator objects which reflects the syntax tree that is
	 * attached to the given syntax element
	 */
	public org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator getDecoratorTree(org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement syntaxElement) {
		org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement[] children = syntaxElement.getChildren();
		int childCount = children.length;
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator[] childDecorators = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator[childCount];
		for (int i = 0; i < childCount; i++) {
			childDecorators[i] = getDecoratorTree(children[i]);
		}
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator decorator = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator(syntaxElement, childDecorators);
		return decorator;
	}
	
	public void decorateTree(org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator decorator, org.eclipse.emf.ecore.EObject eObject) {
		PrintCountingMap printCountingMap = initializePrintCountingMap(eObject);
		java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator> keywordsToPrint = new java.util.ArrayList<org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator>();
		decorateTreeBasic(decorator, eObject, printCountingMap, keywordsToPrint);
		for (org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator keywordToPrint : keywordsToPrint) {
			// for keywords the concrete index does not matter, but we must add one to
			// indicate that the keyword needs to be printed here. Thus, we use 0 as index.
			keywordToPrint.addIndexToPrint(0);
		}
	}
	
	/**
	 * Tries to decorate the decorator with an attribute value, or reference held by
	 * the given EObject. Returns true if an attribute value or reference was found.
	 */
	public boolean decorateTreeBasic(org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator decorator, org.eclipse.emf.ecore.EObject eObject, PrintCountingMap printCountingMap, java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator> keywordsToPrint) {
		boolean foundFeatureToPrint = false;
		org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement syntaxElement = decorator.getDecoratedElement();
		org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality cardinality = syntaxElement.getCardinality();
		boolean isFirstIteration = true;
		while (true) {
			java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator> subKeywordsToPrint = new java.util.ArrayList<org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator>();
			boolean keepDecorating = false;
			if (syntaxElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword) {
				subKeywordsToPrint.add(decorator);
			} else if (syntaxElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclTerminal) {
				org.dresdenocl.language.ocl.resource.ocl.grammar.OclTerminal terminal = (org.dresdenocl.language.ocl.resource.ocl.grammar.OclTerminal) syntaxElement;
				org.eclipse.emf.ecore.EStructuralFeature feature = terminal.getFeature();
				if (feature == org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.ANONYMOUS_FEATURE) {
					return false;
				}
				String featureName = feature.getName();
				int countLeft = printCountingMap.getCountLeft(terminal);
				if (countLeft > terminal.getMandatoryOccurencesAfter()) {
					// normally we print the element at the next index
					int indexToPrint = printCountingMap.getNextIndexToPrint(featureName);
					// But, if there are type restrictions for containments, we must choose an index
					// of an element that fits (i.e., which has the correct type)
					if (terminal instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment) {
						org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment containment = (org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment) terminal;
						indexToPrint = findElementWithCorrectType(eObject, feature, printCountingMap.getIndicesToPrint(featureName), containment);
					}
					if (indexToPrint >= 0) {
						decorator.addIndexToPrint(indexToPrint);
						printCountingMap.addIndexToPrint(featureName, indexToPrint);
						keepDecorating = true;
					}
				}
			}
			if (syntaxElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice) {
				// for choices we do print only the choice which does print at least one feature
				org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator childToPrint = null;
				for (org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator childDecorator : decorator.getChildDecorators()) {
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
				for (org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator childDecorator : decorator.getChildDecorators()) {
					keepDecorating |= decorateTreeBasic(childDecorator, eObject, printCountingMap, subKeywordsToPrint);
				}
			}
			foundFeatureToPrint |= keepDecorating;
			// only print keywords if a feature was printed or the syntax element is mandatory
			if (cardinality == org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE) {
				keywordsToPrint.addAll(subKeywordsToPrint);
			} else if (cardinality == org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.PLUS) {
				if (isFirstIteration) {
					keywordsToPrint.addAll(subKeywordsToPrint);
				} else {
					if (keepDecorating) {
						keywordsToPrint.addAll(subKeywordsToPrint);
					}
				}
			} else if (keepDecorating && (cardinality == org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR || cardinality == org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK)) {
				keywordsToPrint.addAll(subKeywordsToPrint);
			}
			if (cardinality == org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE || cardinality == org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK) {
				break;
			} else if (!keepDecorating) {
				break;
			}
			isFirstIteration = false;
		}
		return foundFeatureToPrint;
	}
	
	private int findElementWithCorrectType(org.eclipse.emf.ecore.EObject eObject, org.eclipse.emf.ecore.EStructuralFeature feature, java.util.Set<Integer> indicesToPrint, org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment containment) {
		// By default the type restrictions that are defined in the CS definition are
		// considered when printing models. You can change this behavior by setting the
		// 'ignoreTypeRestrictionsForPrinting' option to true.
		boolean ignoreTypeRestrictions = false;
		org.eclipse.emf.ecore.EClass[] allowedTypes = containment.getAllowedTypes();
		Object value = eObject.eGet(feature);
		if (value instanceof java.util.List<?>) {
			java.util.List<?> valueList = (java.util.List<?>) value;
			int listSize = valueList.size();
			for (int index = 0; index < listSize; index++) {
				if (indicesToPrint.contains(index)) {
					continue;
				}
				Object valueAtIndex = valueList.get(index);
				if (eClassUtil.isInstance(valueAtIndex, allowedTypes) || ignoreTypeRestrictions) {
					return index;
				}
			}
		} else {
			if (eClassUtil.isInstance(value, allowedTypes) || ignoreTypeRestrictions) {
				return 0;
			}
		}
		return -1;
	}
	
	/**
	 * Checks whether decorating the given node will use at least one attribute value,
	 * or reference held by eObject. Returns true if a printable attribute value or
	 * reference was found. This method is used to decide which choice to pick, when
	 * multiple choices are available. We pick the choice that prints at least one
	 * attribute or reference.
	 */
	public boolean doesPrintFeature(org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator decorator, org.eclipse.emf.ecore.EObject eObject, PrintCountingMap printCountingMap) {
		org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement syntaxElement = decorator.getDecoratedElement();
		if (syntaxElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclTerminal) {
			org.dresdenocl.language.ocl.resource.ocl.grammar.OclTerminal terminal = (org.dresdenocl.language.ocl.resource.ocl.grammar.OclTerminal) syntaxElement;
			org.eclipse.emf.ecore.EStructuralFeature feature = terminal.getFeature();
			if (feature == org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.ANONYMOUS_FEATURE) {
				return false;
			}
			int countLeft = printCountingMap.getCountLeft(terminal);
			if (countLeft > terminal.getMandatoryOccurencesAfter()) {
				// found a feature to print
				return true;
			}
		}
		for (org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator childDecorator : decorator.getChildDecorators()) {
			if (doesPrintFeature(childDecorator, eObject, printCountingMap)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean printTree(org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator decorator, org.eclipse.emf.ecore.EObject eObject, java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement printElement = decorator.getDecoratedElement();
		org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality cardinality = printElement.getCardinality();
		java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement> cloned = new java.util.ArrayList<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement>();
		cloned.addAll(foundFormattingElements);
		boolean foundSomethingAtAll = false;
		boolean foundSomethingToPrint;
		while (true) {
			foundSomethingToPrint = false;
			Integer indexToPrint = decorator.getNextIndexToPrint();
			if (indexToPrint != null) {
				if (printElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword) {
					printKeyword(eObject, (org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword) printElement, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				} else if (printElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder) {
					org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder placeholder = (org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder) printElement;
					printFeature(eObject, placeholder, indexToPrint, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				} else if (printElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment) {
					org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment containment = (org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment) printElement;
					printContainedObject(eObject, containment, indexToPrint, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				} else if (printElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclBooleanTerminal) {
					org.dresdenocl.language.ocl.resource.ocl.grammar.OclBooleanTerminal booleanTerminal = (org.dresdenocl.language.ocl.resource.ocl.grammar.OclBooleanTerminal) printElement;
					printBooleanTerminal(eObject, booleanTerminal, indexToPrint, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				} else if (printElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclEnumerationTerminal) {
					org.dresdenocl.language.ocl.resource.ocl.grammar.OclEnumerationTerminal enumTerminal = (org.dresdenocl.language.ocl.resource.ocl.grammar.OclEnumerationTerminal) printElement;
					printEnumerationTerminal(eObject, enumTerminal, indexToPrint, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				}
			}
			if (foundSomethingToPrint) {
				foundSomethingAtAll = true;
			}
			if (printElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace) {
				foundFormattingElements.add((org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace) printElement);
			}
			if (printElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak) {
				foundFormattingElements.add((org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak) printElement);
			}
			for (org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxElementDecorator childDecorator : decorator.getChildDecorators()) {
				foundSomethingToPrint |= printTree(childDecorator, eObject, foundFormattingElements, layoutInformations);
				org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement decoratedElement = decorator.getDecoratedElement();
				if (foundSomethingToPrint && decoratedElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice) {
					break;
				}
			}
			if (cardinality == org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE || cardinality == org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK) {
				break;
			} else if (!foundSomethingToPrint) {
				break;
			}
		}
		// only print formatting elements if a feature was printed or the syntax element
		// is mandatory
		if (!foundSomethingAtAll && (cardinality == org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR || cardinality == org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK)) {
			foundFormattingElements.clear();
			foundFormattingElements.addAll(cloned);
		}
		return foundSomethingToPrint;
	}
	
	public void printKeyword(org.eclipse.emf.ecore.EObject eObject, org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword keyword, java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation keywordLayout = getLayoutInformation(layoutInformations, keyword, null, eObject);
		printFormattingElements(eObject, foundFormattingElements, layoutInformations, keywordLayout);
		String value = keyword.getValue();
		tokenOutputStream.add(new PrintToken(value, "'" + org.dresdenocl.language.ocl.resource.ocl.util.OclStringUtil.escapeToANTLRKeyword(value) + "'", eObject));
	}
	
	public void printFeature(org.eclipse.emf.ecore.EObject eObject, org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder placeholder, int count, java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		org.eclipse.emf.ecore.EStructuralFeature feature = placeholder.getFeature();
		if (feature instanceof org.eclipse.emf.ecore.EAttribute) {
			printAttribute(eObject, (org.eclipse.emf.ecore.EAttribute) feature, placeholder, count, foundFormattingElements, layoutInformations);
		} else {
			printReference(eObject, (org.eclipse.emf.ecore.EReference) feature, placeholder, count, foundFormattingElements, layoutInformations);
		}
	}
	
	public void printAttribute(org.eclipse.emf.ecore.EObject eObject, org.eclipse.emf.ecore.EAttribute attribute, org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder placeholder, int index, java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		String result = null;
		Object attributeValue = org.dresdenocl.language.ocl.resource.ocl.util.OclEObjectUtil.getFeatureValue(eObject, attribute, index);
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation attributeLayout = getLayoutInformation(layoutInformations, placeholder, attributeValue, eObject);
		String visibleTokenText = getVisibleTokenText(attributeLayout);
		// if there is text for the attribute we use it
		if (visibleTokenText != null) {
			result = visibleTokenText;
		}
		
		if (result == null) {
			// if no text is available, the attribute is deresolved to obtain its textual
			// representation
			org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver(placeholder.getTokenName());
			tokenResolver.setOptions(getOptions());
			String deResolvedValue = tokenResolver.deResolve(attributeValue, attribute, eObject);
			result = deResolvedValue;
		}
		
		if (result != null && !"".equals(result)) {
			printFormattingElements(eObject, foundFormattingElements, layoutInformations, attributeLayout);
			// write result to the output stream
			tokenOutputStream.add(new PrintToken(result, placeholder.getTokenName(), eObject));
		}
	}
	
	
	public void printBooleanTerminal(org.eclipse.emf.ecore.EObject eObject, org.dresdenocl.language.ocl.resource.ocl.grammar.OclBooleanTerminal booleanTerminal, int index, java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		org.eclipse.emf.ecore.EAttribute attribute = booleanTerminal.getAttribute();
		String result = null;
		Object attributeValue = org.dresdenocl.language.ocl.resource.ocl.util.OclEObjectUtil.getFeatureValue(eObject, attribute, index);
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation attributeLayout = getLayoutInformation(layoutInformations, booleanTerminal, attributeValue, eObject);
		String visibleTokenText = getVisibleTokenText(attributeLayout);
		// if there is text for the attribute we use it
		if (visibleTokenText != null) {
			result = visibleTokenText;
		}
		
		if (result == null) {
			// if no text is available, the boolean attribute is converted to its textual
			// representation using the literals of the boolean terminal
			if (Boolean.TRUE.equals(attributeValue)) {
				result = booleanTerminal.getTrueLiteral();
			} else {
				result = booleanTerminal.getFalseLiteral();
			}
		}
		
		if (result != null && !"".equals(result)) {
			printFormattingElements(eObject, foundFormattingElements, layoutInformations, attributeLayout);
			// write result to the output stream
			tokenOutputStream.add(new PrintToken(result, "'" + org.dresdenocl.language.ocl.resource.ocl.util.OclStringUtil.escapeToANTLRKeyword(result) + "'", eObject));
		}
	}
	
	
	public void printEnumerationTerminal(org.eclipse.emf.ecore.EObject eObject, org.dresdenocl.language.ocl.resource.ocl.grammar.OclEnumerationTerminal enumTerminal, int index, java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		org.eclipse.emf.ecore.EAttribute attribute = enumTerminal.getAttribute();
		String result = null;
		Object attributeValue = org.dresdenocl.language.ocl.resource.ocl.util.OclEObjectUtil.getFeatureValue(eObject, attribute, index);
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation attributeLayout = getLayoutInformation(layoutInformations, enumTerminal, attributeValue, eObject);
		String visibleTokenText = getVisibleTokenText(attributeLayout);
		// if there is text for the attribute we use it
		if (visibleTokenText != null) {
			result = visibleTokenText;
		}
		
		if (result == null) {
			// if no text is available, the enumeration attribute is converted to its textual
			// representation using the literals of the enumeration terminal
			assert attributeValue instanceof org.eclipse.emf.common.util.Enumerator;
			result = enumTerminal.getText(((org.eclipse.emf.common.util.Enumerator) attributeValue).getName());
		}
		
		if (result != null && !"".equals(result)) {
			printFormattingElements(eObject, foundFormattingElements, layoutInformations, attributeLayout);
			// write result to the output stream
			tokenOutputStream.add(new PrintToken(result, "'" + org.dresdenocl.language.ocl.resource.ocl.util.OclStringUtil.escapeToANTLRKeyword(result) + "'", eObject));
		}
	}
	
	
	public void printContainedObject(org.eclipse.emf.ecore.EObject eObject, org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment containment, int index, java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		org.eclipse.emf.ecore.EStructuralFeature reference = containment.getFeature();
		Object o = org.dresdenocl.language.ocl.resource.ocl.util.OclEObjectUtil.getFeatureValue(eObject, reference, index);
		// save current number of tabs to restore them after printing the contained object
		int oldTabsBeforeCurrentObject = tabsBeforeCurrentObject;
		int oldCurrentTabs = currentTabs;
		// use current number of tabs to indent contained object. we do not directly set
		// 'tabsBeforeCurrentObject' because the first element of the new object must be
		// printed with the old number of tabs.
		startedPrintingContainedObject = false;
		currentTabs = 0;
		doPrint((org.eclipse.emf.ecore.EObject) o, foundFormattingElements);
		// restore number of tabs after printing the contained object
		tabsBeforeCurrentObject = oldTabsBeforeCurrentObject;
		currentTabs = oldCurrentTabs;
	}
	
	public void printFormattingElements(org.eclipse.emf.ecore.EObject eObject, java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations, org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation layoutInformation) {
		String hiddenTokenText = getHiddenTokenText(layoutInformation);
		if (hiddenTokenText != null) {
			// removed used information
			if (layoutInformations != null) {
				layoutInformations.remove(layoutInformation);
			}
			tokenOutputStream.add(new PrintToken(hiddenTokenText, null, eObject));
			foundFormattingElements.clear();
			startedPrintingObject = false;
			setTabsBeforeCurrentObject(0);
			return;
		}
		int printedTabs = 0;
		if (foundFormattingElements.size() > 0) {
			for (org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement foundFormattingElement : foundFormattingElements) {
				if (foundFormattingElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace) {
					int amount = ((org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace) foundFormattingElement).getAmount();
					for (int i = 0; i < amount; i++) {
						tokenOutputStream.add(createSpaceToken(eObject));
					}
				}
				if (foundFormattingElement instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak) {
					currentTabs = ((org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak) foundFormattingElement).getTabs();
					printedTabs += currentTabs;
					tokenOutputStream.add(createNewLineToken(eObject));
					for (int i = 0; i < tabsBeforeCurrentObject + currentTabs; i++) {
						tokenOutputStream.add(createTabToken(eObject));
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
					tokenOutputStream.add(new PrintToken(getWhiteSpaceString(tokenSpace), null, eObject));
				}
			}
		}
		// after printing the first element, we can use the new number of tabs.
		setTabsBeforeCurrentObject(printedTabs);
	}
	
	private void setTabsBeforeCurrentObject(int tabs) {
		if (startedPrintingContainedObject) {
			return;
		}
		tabsBeforeCurrentObject = tabsBeforeCurrentObject + tabs;
		startedPrintingContainedObject = true;
	}
	
	@SuppressWarnings("unchecked")	
	public void printReference(org.eclipse.emf.ecore.EObject eObject, org.eclipse.emf.ecore.EReference reference, org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder placeholder, int index, java.util.List<org.dresdenocl.language.ocl.resource.ocl.grammar.OclFormattingElement> foundFormattingElements, java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations) {
		String tokenName = placeholder.getTokenName();
		Object referencedObject = org.dresdenocl.language.ocl.resource.ocl.util.OclEObjectUtil.getFeatureValue(eObject, reference, index, false);
		// first add layout before the reference
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation referenceLayout = getLayoutInformation(layoutInformations, placeholder, referencedObject, eObject);
		printFormattingElements(eObject, foundFormattingElements, layoutInformations, referenceLayout);
		// proxy objects must be printed differently
		String deresolvedReference = null;
		if (referencedObject instanceof org.eclipse.emf.ecore.EObject) {
			org.eclipse.emf.ecore.EObject eObjectToDeResolve = (org.eclipse.emf.ecore.EObject) referencedObject;
			if (eObjectToDeResolve.eIsProxy()) {
				deresolvedReference = ((org.eclipse.emf.ecore.InternalEObject) eObjectToDeResolve).eProxyURI().fragment();
				// If the proxy was created by EMFText, we can try to recover the identifier from
				// the proxy URI
				if (deresolvedReference != null && deresolvedReference.startsWith(org.dresdenocl.language.ocl.resource.ocl.IOclContextDependentURIFragment.INTERNAL_URI_FRAGMENT_PREFIX)) {
					deresolvedReference = deresolvedReference.substring(org.dresdenocl.language.ocl.resource.ocl.IOclContextDependentURIFragment.INTERNAL_URI_FRAGMENT_PREFIX.length());
					deresolvedReference = deresolvedReference.substring(deresolvedReference.indexOf("_") + 1);
				}
			}
		}
		if (deresolvedReference == null) {
			// NC-References must always be printed by deresolving the reference. We cannot
			// use the visible token information, because deresolving usually depends on
			// attribute values of the referenced object instead of the object itself.
			@SuppressWarnings("rawtypes")			
			org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolver referenceResolver = getReferenceResolverSwitch().getResolver(reference);
			referenceResolver.setOptions(getOptions());
			deresolvedReference = referenceResolver.deResolve((org.eclipse.emf.ecore.EObject) referencedObject, eObject, reference);
		}
		org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver(tokenName);
		tokenResolver.setOptions(getOptions());
		String deresolvedToken = tokenResolver.deResolve(deresolvedReference, reference, eObject);
		// write result to output stream
		tokenOutputStream.add(new PrintToken(deresolvedToken, tokenName, eObject));
	}
	
	@SuppressWarnings("unchecked")	
	public PrintCountingMap initializePrintCountingMap(org.eclipse.emf.ecore.EObject eObject) {
		// The PrintCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		PrintCountingMap printCountingMap = new PrintCountingMap();
		java.util.List<org.eclipse.emf.ecore.EStructuralFeature> features = eObject.eClass().getEAllStructuralFeatures();
		for (org.eclipse.emf.ecore.EStructuralFeature feature : features) {
			// We get the feature value without resolving it, because resolving is not
			// required to count the number of elements that are referenced by the feature.
			// Moreover, triggering reference resolving is not desired here, because we'd also
			// like to print models that contain unresolved references.
			Object featureValue = eObject.eGet(feature, false);
			if (featureValue != null) {
				if (featureValue instanceof java.util.List<?>) {
					printCountingMap.setFeatureValues(feature.getName(), (java.util.List<Object>) featureValue);
				} else {
					printCountingMap.setFeatureValues(feature.getName(), java.util.Collections.singletonList(featureValue));
				}
			} else {
				printCountingMap.setFeatureValues(feature.getName(), null);
			}
		}
		return printCountingMap;
	}
	
	public java.util.Map<?,?> getOptions() {
		return options;
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		this.options = options;
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public void setEncoding(String encoding) {
		if (encoding != null) {
			this.encoding = encoding;
		}
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclTextResource getResource() {
		return resource;
	}
	
	protected org.dresdenocl.language.ocl.resource.ocl.mopp.OclReferenceResolverSwitch getReferenceResolverSwitch() {
		return (org.dresdenocl.language.ocl.resource.ocl.mopp.OclReferenceResolverSwitch) new org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation().getReferenceResolverSwitch();
	}
	
	protected void addWarningToResource(final String errorMessage, org.eclipse.emf.ecore.EObject cause) {
		org.dresdenocl.language.ocl.resource.ocl.IOclTextResource resource = getResource();
		if (resource == null) {
			// the resource can be null if the printer is used stand alone
			return;
		}
		resource.addProblem(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclProblem(errorMessage, org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.PRINT_PROBLEM, org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity.WARNING), cause);
	}
	
	protected org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter getLayoutInformationAdapter(org.eclipse.emf.ecore.EObject element) {
		for (org.eclipse.emf.common.notify.Adapter adapter : element.eAdapters()) {
			if (adapter instanceof org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter) {
				return (org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter) adapter;
			}
		}
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter newAdapter = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter();
		element.eAdapters().add(newAdapter);
		return newAdapter;
	}
	
	private org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation getLayoutInformation(java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations, org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement syntaxElement, Object object, org.eclipse.emf.ecore.EObject container) {
		for (org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation layoutInformation : layoutInformations) {
			if (syntaxElement == layoutInformation.getSyntaxElement()) {
				if (object == null) {
					return layoutInformation;
				}
				// The layout information adapter must only try to resolve the object it refers
				// to, if we compare with a non-proxy object. If we're printing a resource that
				// contains proxy objects, resolving must not be triggered.
				boolean isNoProxy = true;
				if (object instanceof org.eclipse.emf.ecore.EObject) {
					org.eclipse.emf.ecore.EObject eObject = (org.eclipse.emf.ecore.EObject) object;
					isNoProxy = !eObject.eIsProxy();
				}
				if (isSame(object, layoutInformation.getObject(container, isNoProxy))) {
					return layoutInformation;
				}
			}
		}
		return null;
	}
	
	public java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> getCopyOfLayoutInformation(org.eclipse.emf.ecore.EObject eObject) {
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformationAdapter layoutInformationAdapter = getLayoutInformationAdapter(eObject);
		java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> originalLayoutInformations = layoutInformationAdapter.getLayoutInformations();
		// create a copy of the original list of layout information object in order to be
		// able to remove used informations during printing
		java.util.List<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation> layoutInformations = new java.util.ArrayList<org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation>(originalLayoutInformations.size());
		layoutInformations.addAll(originalLayoutInformations);
		return layoutInformations;
	}
	
	private String getHiddenTokenText(org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation layoutInformation) {
		if (layoutInformation != null) {
			return layoutInformation.getHiddenTokenText();
		} else {
			return null;
		}
	}
	
	private String getVisibleTokenText(org.dresdenocl.language.ocl.resource.ocl.mopp.OclLayoutInformation layoutInformation) {
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
		char lastCharWritten = ' ';
		for (int i = 0; i < tokenOutputStream.size(); i++) {
			PrintToken tokenI = tokenOutputStream.get(i);
			currentBlock.append(tokenI.getText());
			// if declared or preserved whitespace is found - print block
			if (tokenI.getTokenName() == null) {
				char[] charArray = currentBlock.toString().toCharArray();
				writer.write(charArray);
				if (charArray.length > 0) {
					lastCharWritten = charArray[charArray.length - 1];
				}
				// reset all values
				currentBlock = new StringBuilder();
				currentBlockStart = i + 1;
				validBlock = "";
				continue;
			}
			// now check whether the current block can be scanned
			org.dresdenocl.language.ocl.resource.ocl.IOclTextScanner scanner = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation().createLexer();
			scanner.setText(currentBlock.toString());
			// retrieve all tokens from scanner and add them to list 'tempTokens'
			java.util.List<org.dresdenocl.language.ocl.resource.ocl.IOclTextToken> tempTokens = new java.util.ArrayList<org.dresdenocl.language.ocl.resource.ocl.IOclTextToken>();
			org.dresdenocl.language.ocl.resource.ocl.IOclTextToken nextToken = scanner.getNextToken();
			while (nextToken != null && nextToken.getText() != null) {
				tempTokens.add(nextToken);
				nextToken = scanner.getNextToken();
			}
			boolean sequenceIsValid = true;
			// check whether the current block was scanned to the same token sequence
			for (int t = 0; t < tempTokens.size(); t++) {
				PrintToken printTokenT = tokenOutputStream.get(currentBlockStart + t);
				org.dresdenocl.language.ocl.resource.ocl.IOclTextToken tempToken = tempTokens.get(t);
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
				char[] charArray = validBlock.toString().toCharArray();
				writer.write(charArray);
				if (charArray.length > 0) {
					lastCharWritten = charArray[charArray.length - 1];
				}
				// print separating whitespace
				// if no whitespace (or tab or linebreak) is already there
				if (lastCharWritten != ' ' && lastCharWritten != '\t' && lastCharWritten != '\n' && lastCharWritten != '\r') {
					lastCharWritten = ' ';
					writer.write(lastCharWritten);
				}
				// add current token as initial value for next iteration
				currentBlock = new StringBuilder(tokenI.getText());
				currentBlockStart = i;
				validBlock = tokenI.getText();
			}
		}
		// flush remaining valid text to writer
		writer.write(validBlock);
	}
	
	private boolean isSame(Object o1, Object o2) {
		if (o1 instanceof String || o1 instanceof Integer || o1 instanceof Long || o1 instanceof Byte || o1 instanceof Short || o1 instanceof Float || o2 instanceof Double) {
			return o1.equals(o2);
		}
		return o1 == o2;
	}
	
	protected java.util.List<Class<?>> getAllowedTypes(org.dresdenocl.language.ocl.resource.ocl.grammar.OclTerminal terminal) {
		java.util.List<Class<?>> allowedTypes = new java.util.ArrayList<Class<?>>();
		allowedTypes.add(terminal.getFeature().getEType().getInstanceClass());
		if (terminal instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment) {
			org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment printingContainment = (org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment) terminal;
			org.eclipse.emf.ecore.EClass[] typeRestrictions = printingContainment.getAllowedTypes();
			if (typeRestrictions != null && typeRestrictions.length > 0) {
				allowedTypes.clear();
				for (org.eclipse.emf.ecore.EClass eClass : typeRestrictions) {
					allowedTypes.add(eClass.getInstanceClass());
				}
			}
		}
		return allowedTypes;
	}
	
	protected PrintToken createSpaceToken(org.eclipse.emf.ecore.EObject container) {
		return new PrintToken(" ", null, container);
	}
	
	protected PrintToken createTabToken(org.eclipse.emf.ecore.EObject container) {
		return new PrintToken("\t", null, container);
	}
	
	protected PrintToken createNewLineToken(org.eclipse.emf.ecore.EObject container) {
		return new PrintToken(NEW_LINE, null, container);
	}
	
}
